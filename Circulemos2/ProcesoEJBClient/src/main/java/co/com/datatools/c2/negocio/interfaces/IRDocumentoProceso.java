package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ConsultaGeneracionMasivaDocumentosDTO;
import co.com.datatools.c2.dto.DocumentoMasivoDTO;
import co.com.datatools.c2.dto.RespuestaConsultaMasivaDocumentosDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Interface que ofrece los servicios de proceso
 * 
 * @author diego.lozano
 * 
 */
@Remote
public interface IRDocumentoProceso {

    /**
     * Consulta todos los documentos asociados a los estados de un proceso
     * 
     * @param ConsultaGeneracionMasivaDocumentosDTO
     *            consulta
     * @return List<RespuestaConsultaMasivaDocumentosDTO>
     * @author divier.casas 2016-08-11
     */
    public List<RespuestaConsultaMasivaDocumentosDTO> consultarDocumentos(
            ConsultaGeneracionMasivaDocumentosDTO consulta);

    /**
     * Registra un documento masivo
     * 
     * @param documento
     * @param lsResConMasDoc
     * @return
     */
    public Long registrarDocumentoMasivo(DocumentoMasivoDTO documento,
            List<RespuestaConsultaMasivaDocumentosDTO> lsResConMasDoc);

    /**
     * Procesa un documento masivo
     * 
     * @param idArchivo
     * @throws CirculemosNegocioException
     */
    public void procesarDocumentoMasivo(Long idArchivo) throws CirculemosNegocioException;

    /**
     * Consulta los documentos masivos
     * 
     * @return List<DocumentoMasivoDTO>
     */
    public List<DocumentoMasivoDTO> consultarDocumentoMasivo(Long idArchivo);
}