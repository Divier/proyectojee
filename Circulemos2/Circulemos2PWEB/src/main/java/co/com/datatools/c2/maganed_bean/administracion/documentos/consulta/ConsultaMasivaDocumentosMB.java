package co.com.datatools.c2.maganed_bean.administracion.documentos.consulta;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.dto.DocumentoMasivoDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.negocio.interfaces.IRDocumentoProceso;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Se encarga de controlar la consulta de generacion masiva de documentos
 * 
 * @author divier.casas
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaMasivaDocumentosMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ConsultaMasivaDocumentosMB.class.getName());

    @EJB
    private IRDocumentoProceso iRDocumentoProceso;

    @EJB
    private IRRepositorioArchivo reposArchivoEJB;

    /**
     * Consulta de documentos generados masivamente
     * 
     * @author divier.casas
     */
    public void consultarDocumentosGenerados() {
        LOGGER.debug("ConsultaMasivaDocumentosMB::consultarDocumentosGenerados()");
        ConsultaMasivaHolderFL conMasivaHolderFL = findFlowObject(ConsultaMasivaHolderFL.class,
                ConsultaMasivaHolderFL.NOMBRE_BEAN);

        List<DocumentoMasivoDTO> lsRespuesta = iRDocumentoProceso
                .consultarDocumentoMasivo(conMasivaHolderFL.getIdArchivo());
        if (lsRespuesta == null || lsRespuesta.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            conMasivaHolderFL.setLsRespuesta(new ArrayList<DocumentoMasivoDTO>(0));
            return;
        } else {
            conMasivaHolderFL.setLsRespuesta(lsRespuesta);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lsRespuesta.size());
        }
    }

    /**
     * Permite descargar el documento asociado al registro, del repositorio de archivos.
     * 
     * @param idArchivoGenerado
     * 
     * @author divier.casas(2016-08-22)
     */
    public StreamedContent descargarDocumento(Long idArchivoGenerado) {
        LOGGER.debug("ConsultaMasivaDocumentosMB::descargarDocumento(Long)");
        StreamedContent streamedContent = null;
        ArchivoTransportableDTO archivoTransportableDTO;
        try {
            archivoTransportableDTO = reposArchivoEJB.consultarDocumento(idArchivoGenerado.toString());
            streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), null,
                    archivoTransportableDTO.getNombre());
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        }
        return streamedContent;
    }
}