package co.com.datatools.c2.managed_bean.comparendo.reporte_inconsistencias;

import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaProcesaComparendoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Clase que encapsula los datos para la consulta inconsistencias de comparendos
 * 
 * @author giovanni.velandia
 * 
 */
public class GestionarInconsistenciasComparendoHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "gestionarInconsistenciasComparendoHolderFL";
    private String codigoPolca;
    private String codOrganismoNumeroComparendo;
    private String numeroComparendo;

    /**
     * listado de relaciones consultadas
     */
    private List<ProcesaComparendoVO> procesaComparendoVOs;
    private ProcesaComparendoVO procesaComparendoSeleccionadoVO;

    private ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO;

    /**
     * Estado del seguimiento seleccionado
     */
    private ComparendoDTO comparendoSeleccionado;

    public GestionarInconsistenciasComparendoHolderFL() {
        consultaProcesaComparendoDTO = new ConsultaProcesaComparendoDTO();
        consultaProcesaComparendoDTO.setEsPolca(false);
        consultaProcesaComparendoDTO.setOrigenExterno(false);
    }

    public ComparendoDTO getComparendoSeleccionado() {
        return comparendoSeleccionado;
    }

    public void setComparendoSeleccionado(ComparendoDTO comparendoSeleccionado) {
        this.comparendoSeleccionado = comparendoSeleccionado;
    }

    public String getCodigoPolca() {
        return codigoPolca;
    }

    public void setCodigoPolca(String codigoPolca) {
        this.codigoPolca = codigoPolca;
    }

    public String getCodOrganismoNumeroComparendo() {
        return codOrganismoNumeroComparendo;
    }

    public void setCodOrganismoNumeroComparendo(String codOrganismoNumeroComparendo) {
        this.codOrganismoNumeroComparendo = codOrganismoNumeroComparendo;
    }

    public ConsultaProcesaComparendoDTO getConsultaProcesaComparendoDTO() {
        return consultaProcesaComparendoDTO;
    }

    public void setConsultaProcesaComparendoDTO(ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO) {
        this.consultaProcesaComparendoDTO = consultaProcesaComparendoDTO;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public List<ProcesaComparendoVO> getProcesaComparendoVOs() {
        return procesaComparendoVOs;
    }

    public void setProcesaComparendoVOs(List<ProcesaComparendoVO> procesaComparendoVOs) {
        this.procesaComparendoVOs = procesaComparendoVOs;
    }

    public ProcesaComparendoVO getProcesaComparendoSeleccionadoVO() {
        return procesaComparendoSeleccionadoVO;
    }

    public void setProcesaComparendoSeleccionadoVO(ProcesaComparendoVO procesaComparendoSeleccionadoVO) {
        this.procesaComparendoSeleccionadoVO = procesaComparendoSeleccionadoVO;
    }
}