package co.com.datatools.c2.negocio.ejb.gestiondocs;

import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.commons.io.FilenameUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.negocio.error.gestiondocs.ErrorGestionDocumentos.GestionDocumentos;
import co.com.datatools.c2.negocio.helpers.gestiondocs.ArchivoTransportableHelper;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.ILRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.opciones.GestorArchivosOpciones;
import co.com.datatools.c2.negocio.opciones.OpcionGestorFileSystem;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.documentos.cm.dto.Documento;
import co.com.datatools.documentos.cm.interfaces.IRGestorArchivos;

@Stateless(name = "RepositorioArchivoEJB")
@LocalBean
public class RepositorioArchivoEJB implements IRRepositorioArchivo, ILRepositorioArchivo {

    private final static Logger logger = Logger.getLogger(RepositorioArchivoEJB.class.getName());

    private static final String GESTOR_ARCHIVOS_JNDI_NAME = "ejb:GestorArchivosEAR-2.0.4/GestorArchivosSqlEJB-2.0.4/GestorArchivosSqlEJB!co.com.datatools.documentos.cm.interfaces.IRGestorArchivos";

    private IRGestorArchivos iGestorArchivos;

    public RepositorioArchivoEJB() {
        try {
            final Hashtable<String, String> props = new Hashtable<String, String>();
            // setup the ejb: namespace URL factory
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            // create the InitialContext
            final Context context = new javax.naming.InitialContext(props);
            // lookup the bean
            iGestorArchivos = (IRGestorArchivos) context.lookup(GESTOR_ARCHIVOS_JNDI_NAME);
        } catch (NamingException e) {
            logger.error("Error localizando Jndi EJB", e);
        }
    }

    @Override
    public String registrarDocumento(EnumCategoriaDocumento categoriaDocumento, ArchivoTransportableDTO archivo,
            GestorArchivosOpciones... opciones) throws CirculemosAlertaException {
        logger.debug("RepositorioArchivoEJB::registrarDocumento(EnumCategoriaDocumento, ArchivoTransportableDTO)");
        co.com.datatools.documentos.cm.dto.Documento documentCM = ArchivoTransportableHelper.toDocumento(archivo,
                new co.com.datatools.documentos.cm.dto.Documento());
        documentCM.setNombreReal(archivo.getNombre());
        documentCM.setNombre(System.currentTimeMillis() + new Random().nextInt(99) + "");
        if (archivo.getRuta() != null && !archivo.getRuta().equals("")) {
            documentCM.setCarpeta(archivo.getRuta());
        } else {
            documentCM.setCarpeta(categoriaDocumento.getCarpeta());
        }
        // Cuando se va a guardar unicamente la referencia pero va a quedar en el file system
        if (opciones != null && opciones.length > 0) {
            for (GestorArchivosOpciones opc : opciones) {
                switch (opc.getTipoOpcion()) {
                case FILE_SYSTEM:
                    documentCM.setContenido("".getBytes());
                    break;
                }
            }
        }
        co.com.datatools.documentos.cm.dto.Documento documentCMRespuesta = iGestorArchivos.guardarDocumento(documentCM);
        if (documentCMRespuesta == null) {
            // Error no puede guardar documento
            throw new CirculemosAlertaException(GestionDocumentos.GESDOC_002);
        }
        for (GestorArchivosOpciones opc : opciones) {
            opc.getProcesador().procesarArchivo(archivo);
        }
        return documentCMRespuesta.getIdDocumento();
    }

    @Override
    public String registrarDocumento(EnumCategoriaDocumento categoriaDocumento, ArchivoTransportableDTO archivo,
            String ruta) throws CirculemosAlertaException {
        logger.debug(
                "RepositorioArchivoEJB::registrarDocumento(EnumCategoriaDocumento, ArchivoTransportableDTO, String)");
        Documento documentCMRespuesta = null;
        if (ruta != null && !ruta.isEmpty()) {
            Documento documentCM = ArchivoTransportableHelper.toDocumento(archivo, new Documento());
            documentCM.setNombreReal(archivo.getNombre());
            documentCM.setNombre(System.currentTimeMillis() + new Random().nextInt(99) + "");
            documentCM.setCarpeta(ruta);
            documentCMRespuesta = iGestorArchivos.guardarDocumento(documentCM);
            if (documentCMRespuesta == null) {
                // Error no puede guardar documento
                throw new CirculemosAlertaException(GestionDocumentos.GESDOC_002);
            }
        } else {
            registrarDocumento(categoriaDocumento, archivo);
        }
        return documentCMRespuesta.getIdDocumento();
    }

    @Override
    public ArchivoTransportableDTO consultarDocumento(String idDocumento) throws CirculemosAlertaException {
        logger.debug("RepositorioArchivoEJB::consultarDocumento(String)");
        ArchivoTransportableDTO archivo = null;
        // Consulta la ultima version del documento
        co.com.datatools.documentos.cm.dto.Documento documentCM = iGestorArchivos.obtenerDocumento(idDocumento, null);
        // Si encuentra el documento en el repositorio
        if (documentCM != null) {
            archivo = new ArchivoTransportableDTO();
            archivo.setNombre(documentCM.getNombreReal());
            byte[] contenido = null;
            // Verifica si esta solo la referencia a un archivo que esta en file system
            if (new String(documentCM.getContenido(), StandardCharsets.UTF_8).isEmpty()) {
                contenido = new OpcionGestorFileSystem().getProcesador().obtenerArchivo(documentCM.getCarpeta(),
                        documentCM.getNombreReal());
            } else {
                contenido = documentCM.getContenido();
            }
            archivo.setContenido(contenido);
        } else {
            // Error no encuentra documento
            throw new CirculemosAlertaException(GestionDocumentos.GESDOC_001);
        }
        return archivo;
    }

    @Override
    public void actualizarDocumento(String idDocumento, ArchivoTransportableDTO archivo)
            throws CirculemosAlertaException {
        logger.debug("RepositorioArchivoEJB::actualizarDocumento(String, ArchivoTransportableDTO)");
        co.com.datatools.documentos.cm.dto.Documento documentCM = iGestorArchivos.obtenerDocumento(idDocumento, null);
        if (documentCM == null) {
            // Error no puede encontrar documento a actualizar
            throw new CirculemosAlertaException(GestionDocumentos.GESDOC_001);
        }
        documentCM = ArchivoTransportableHelper.toDocumento(archivo, documentCM);
        documentCM.setNombre(FilenameUtils.getBaseName(documentCM.getNombre()));
        documentCM = iGestorArchivos.actualizarDocumento(documentCM);
        if (documentCM == null) {
            // Error no puede guardar documento
            throw new CirculemosAlertaException(GestionDocumentos.GESDOC_002);
        }
    }
}
