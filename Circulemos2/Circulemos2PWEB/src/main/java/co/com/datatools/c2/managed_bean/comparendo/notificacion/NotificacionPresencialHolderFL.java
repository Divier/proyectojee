package co.com.datatools.c2.managed_bean.comparendo.notificacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Clase que encapsula los datos para la consulta de comparendos para notificacion presencial
 * 
 * @author julio.pinzon
 * 
 */
public class NotificacionPresencialHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "notificacionPresencialHolderFL";

    private ConsultaNotificacionComparendoDTO consultaNotificacionComparendoDTO;
    private String codOrganismoNumeroComparendo;
    private String numeroComparendo;
    private String codigoPolca;
    private Integer idTipoNotificacion;
    private Date fechaNotificacion;
    private boolean requiereTipoDocInfractor;

    /**
     * listado de comparendos consultados
     */
    private List<ResultadoConsultaNotificacionComparendoDTO> lstComparendos;

    /**
     * Listado de comparendos seleccionados
     */
    private List<ResultadoConsultaNotificacionComparendoDTO> comparendosSeleccionados;

    public NotificacionPresencialHolderFL() {
        consultaNotificacionComparendoDTO = new ConsultaNotificacionComparendoDTO();
        consultaNotificacionComparendoDTO.setEsPolca(false);
        comparendosSeleccionados = new ArrayList<ResultadoConsultaNotificacionComparendoDTO>();
    }

    public String getCodOrganismoNumeroComparendo() {
        return codOrganismoNumeroComparendo;
    }

    public void setCodOrganismoNumeroComparendo(String codOrganismoNumeroComparendo) {
        this.codOrganismoNumeroComparendo = codOrganismoNumeroComparendo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public List<ResultadoConsultaNotificacionComparendoDTO> getLstComparendos() {
        return lstComparendos;
    }

    public void setLstComparendos(List<ResultadoConsultaNotificacionComparendoDTO> lstComparendos) {
        this.lstComparendos = lstComparendos;
    }

    public List<ResultadoConsultaNotificacionComparendoDTO> getComparendosSeleccionados() {
        return comparendosSeleccionados;
    }

    public void setComparendosSeleccionados(List<ResultadoConsultaNotificacionComparendoDTO> comparendosSeleccionados) {
        this.comparendosSeleccionados = comparendosSeleccionados;
    }

    public String getCodigoPolca() {
        return codigoPolca;
    }

    public void setCodigoPolca(String codigoPolca) {
        this.codigoPolca = codigoPolca;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public ConsultaNotificacionComparendoDTO getConsultaNotificacionComparendoDTO() {
        return consultaNotificacionComparendoDTO;
    }

    public void setConsultaNotificacionComparendoDTO(ConsultaNotificacionComparendoDTO consultaNotificacionComparendoDTO) {
        this.consultaNotificacionComparendoDTO = consultaNotificacionComparendoDTO;
    }

    public Integer getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(Integer idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    public boolean isRequiereTipoDocInfractor() {
        return requiereTipoDocInfractor;
    }

    public void setRequiereTipoDocInfractor(boolean requiereTipoDocInfractor) {
        this.requiereTipoDocInfractor = requiereTipoDocInfractor;
    }

}