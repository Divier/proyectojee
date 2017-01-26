package co.com.datatools.c2.maganed_bean.administracion.documentos;

import java.util.List;

import co.com.datatools.c2.dto.ConsultaGeneracionMasivaDocumentosDTO;
import co.com.datatools.c2.dto.RespuestaConsultaMasivaDocumentosDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * Objeto para el manejo de la informacion de consulta masiva de documentos
 * 
 * @author divier.casas
 * 
 */
public class GeneracionMasivaHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "generacionMasivaHolderFL";
    private ConsultaGeneracionMasivaDocumentosDTO filtros;
    private boolean registradaSolicitud;
    private Long consecutivo;

    /**
     * Listado de respuestas
     */
    private List<RespuestaConsultaMasivaDocumentosDTO> lsRespuesta;

    /**
     * Listado de respuestas seleccionadas
     */
    private List<RespuestaConsultaMasivaDocumentosDTO> lsRespuestaSelec;

    public GeneracionMasivaHolderFL() {
        filtros = new ConsultaGeneracionMasivaDocumentosDTO();
    }

    public ConsultaGeneracionMasivaDocumentosDTO getFiltros() {
        return filtros;
    }

    public void setFiltros(ConsultaGeneracionMasivaDocumentosDTO filtros) {
        this.filtros = filtros;
    }

    public List<RespuestaConsultaMasivaDocumentosDTO> getLsRespuesta() {
        return lsRespuesta;
    }

    public void setLsRespuesta(List<RespuestaConsultaMasivaDocumentosDTO> lsRespuesta) {
        this.lsRespuesta = lsRespuesta;
    }

    public List<RespuestaConsultaMasivaDocumentosDTO> getLsRespuestaSelec() {
        return lsRespuestaSelec;
    }

    public void setLsRespuestaSelec(List<RespuestaConsultaMasivaDocumentosDTO> lsRespuestaSelec) {
        this.lsRespuestaSelec = lsRespuestaSelec;
    }

    public boolean isRegistradaSolicitud() {
        return registradaSolicitud;
    }

    public void setRegistradaSolicitud(boolean registradaSolicitud) {
        this.registradaSolicitud = registradaSolicitud;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }
}