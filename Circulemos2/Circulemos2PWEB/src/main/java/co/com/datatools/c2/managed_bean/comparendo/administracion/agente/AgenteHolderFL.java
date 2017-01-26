package co.com.datatools.c2.managed_bean.comparendo.administracion.agente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoAgenteInconsistenteDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * @author ricardo.chavarro
 * 
 */
public class AgenteHolderFL extends AbstractC2ManagedBean {

    /**
     * variables que almacenan datos de la consulta
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "agenteHolderFL";
    private Integer itemInconsistencia;
    private List<ComparendoAgenteInconsistenteDTO> comparendosResultado;
    private Date fechaInicialImposicion;
    private Date fechaFinalImposicion;
    private String mensajeConfirmar;
    private String mensajeNoConfirmar;
    private boolean confirmarActu;

    public AgenteHolderFL() {
        comparendosResultado = new ArrayList<>();
    }

    public Integer getItemInconsistencia() {
        return itemInconsistencia;
    }

    public void setItemInconsistencia(Integer itemInconsistencia) {
        this.itemInconsistencia = itemInconsistencia;
    }

    public List<ComparendoAgenteInconsistenteDTO> getComparendosResultado() {
        return comparendosResultado;
    }

    public void setComparendosResultado(List<ComparendoAgenteInconsistenteDTO> comparendosResultado) {
        this.comparendosResultado = comparendosResultado;
    }

    public Date getFechaInicialImposicion() {
        return fechaInicialImposicion;
    }

    public void setFechaInicialImposicion(Date fechaInicialImposicion) {
        this.fechaInicialImposicion = fechaInicialImposicion;
    }

    public Date getFechaFinalImposicion() {
        return fechaFinalImposicion;
    }

    public void setFechaFinalImposicion(Date fechaFinalImposicion) {
        this.fechaFinalImposicion = fechaFinalImposicion;
    }

    public boolean isConfirmarActu() {
        return confirmarActu;
    }

    public void setConfirmarActu(boolean confirmarActu) {
        this.confirmarActu = confirmarActu;
    }

    public String getMensajeConfirmar() {
        return mensajeConfirmar;
    }

    public String getMensajeNoConfirmar() {
        return mensajeNoConfirmar;
    }

    public void setMensajeNoConfirmar(String mensajeNoConfirmar) {
        this.mensajeNoConfirmar = mensajeNoConfirmar;
    }

    public void setMensajeConfirmar(String mensajeConfirmar) {
        this.mensajeConfirmar = mensajeConfirmar;
    }

}
