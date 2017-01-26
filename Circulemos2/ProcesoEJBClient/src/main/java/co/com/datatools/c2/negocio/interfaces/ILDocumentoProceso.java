package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ConsultaGeneracionMasivaDocumentosDTO;
import co.com.datatools.c2.dto.DocumentoMasivoDTO;
import co.com.datatools.c2.dto.RespuestaConsultaMasivaDocumentosDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILDocumentoProceso {

    /**
     * @see IRDocumentoProceso#consultarDocumentos(ConsultaGeneracionMasivaDocumentosDTO)
     */
    public List<RespuestaConsultaMasivaDocumentosDTO> consultarDocumentos(
            ConsultaGeneracionMasivaDocumentosDTO consulta);

    /**
     * @see IRDocumentoProceso#registrarDocumentoMasivo(DocumentoMasivoDTO, List<RespuestaConsultaMasivaDocumentosDTO>)
     */
    public Long registrarDocumentoMasivo(DocumentoMasivoDTO documento,
            List<RespuestaConsultaMasivaDocumentosDTO> lsResConMasDoc);

    /**
     * @see IRDocumentoProceso#procesarDocumentoMasivo(Long)
     */
    public void procesarDocumentoMasivo(Long idArchivo) throws CirculemosNegocioException;

    /**
     * @see IRDocumentoProceso#consultarDocumentoMasivo(Long)
     */
    public List<DocumentoMasivoDTO> consultarDocumentoMasivo(Long idArchivo);
}