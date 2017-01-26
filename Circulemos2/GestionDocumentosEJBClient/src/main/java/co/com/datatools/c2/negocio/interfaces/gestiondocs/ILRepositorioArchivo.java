package co.com.datatools.c2.negocio.interfaces.gestiondocs;

import javax.ejb.Local;

import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.negocio.opciones.GestorArchivosOpciones;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Local
public interface ILRepositorioArchivo {

    /**
     * 
     * @see IRRepositorioArchivo#registrarDocumento(ArchivoTransportableDTO)
     */
    public String registrarDocumento(EnumCategoriaDocumento categoriaDocumento, ArchivoTransportableDTO archivo,
            GestorArchivosOpciones... opciones) throws CirculemosAlertaException;

    /**
     * 
     * @see IRRepositorioArchivo#consultarDocumento(String)
     */
    public ArchivoTransportableDTO consultarDocumento(String idDocumento) throws CirculemosAlertaException;

    /**
     * 
     * @see IRRepositorioArchivo#actualizarDocumento(String, ArchivoTransportableDTO)
     */
    public void actualizarDocumento(String idDocumento, ArchivoTransportableDTO archivo)
            throws CirculemosAlertaException;

    /**
     * 
     * @see IRRepositorioArchivo#registrarDocumento(EnumCategoriaDocumento, ArchivoTransportableDTO, String)
     */
    public String registrarDocumento(EnumCategoriaDocumento categoriaDocumento, ArchivoTransportableDTO archivo,
            String ruta) throws CirculemosAlertaException;
}
