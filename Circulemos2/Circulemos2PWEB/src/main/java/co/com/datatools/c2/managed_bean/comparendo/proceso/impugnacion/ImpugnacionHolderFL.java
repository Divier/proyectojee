package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.List;

import co.com.datatools.c2.dto.ConsultaImpugnacionDTO;
import co.com.datatools.c2.dto.RespuestaImpugnacionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class ImpugnacionHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "impugnacionHolderFL";

    private PersonaDTO personaFiltroDTO;
    private ConsultaImpugnacionDTO consultaImpugnacionDTO;
    private Integer idTipoProceso;

    /**
     * listado de comparendos consultados
     */
    private List<RespuestaImpugnacionDTO> lstComparendos;

    /**
     * Listado de comparendos seleccionados
     */
    private List<RespuestaImpugnacionDTO> comparendosSeleccionados;

    /**
     * Comparendos seleccionado
     */
    private RespuestaImpugnacionDTO comparendoSeleccionado;

    /**
     * Comparendos seleccionado para el detalle
     */
    private RespuestaImpugnacionDTO comparendoSeleccionadoDetalle;

    public ImpugnacionHolderFL() {
        consultaImpugnacionDTO = new ConsultaImpugnacionDTO();
        idTipoProceso = EnumTipoProceso.IMPUGNACION_COMPARENDO.getValue();
    }

    public ConsultaImpugnacionDTO getConsultaImpugnacionDTO() {
        return consultaImpugnacionDTO;
    }

    public void setConsultaImpugnacionDTO(ConsultaImpugnacionDTO consultaImpugnacionDTO) {
        this.consultaImpugnacionDTO = consultaImpugnacionDTO;
    }

    public List<RespuestaImpugnacionDTO> getLstComparendos() {
        return lstComparendos;
    }

    public void setLstComparendos(List<RespuestaImpugnacionDTO> lstComparendos) {
        this.lstComparendos = lstComparendos;
    }

    public List<RespuestaImpugnacionDTO> getComparendosSeleccionados() {
        return comparendosSeleccionados;
    }

    public void setComparendosSeleccionados(List<RespuestaImpugnacionDTO> comparendosSeleccionados) {
        this.comparendosSeleccionados = comparendosSeleccionados;
    }

    public RespuestaImpugnacionDTO getComparendoSeleccionado() {
        return comparendoSeleccionado;
    }

    public void setComparendoSeleccionado(RespuestaImpugnacionDTO comparendoSeleccionado) {
        this.comparendoSeleccionado = comparendoSeleccionado;
    }

    public PersonaDTO getPersonaFiltroDTO() {
        return personaFiltroDTO;
    }

    public void setPersonaFiltroDTO(PersonaDTO personaFiltroDTO) {
        this.personaFiltroDTO = personaFiltroDTO;
    }

    public RespuestaImpugnacionDTO getComparendoSeleccionadoDetalle() {
        comparendoSeleccionadoDetalle = comparendoSeleccionado;
        if (comparendoSeleccionadoDetalle == null && comparendosSeleccionados != null) {
            for (RespuestaImpugnacionDTO respuestaImpugnacionDTO : comparendosSeleccionados) {
                comparendoSeleccionadoDetalle = respuestaImpugnacionDTO;
                break;
            }
        }
        return comparendoSeleccionadoDetalle;
    }

    public void setComparendoSeleccionadoDetalle(RespuestaImpugnacionDTO comparendoSeleccionadoDetalle) {
        this.comparendoSeleccionadoDetalle = comparendoSeleccionadoDetalle;
    }

    public Integer getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(Integer idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

}