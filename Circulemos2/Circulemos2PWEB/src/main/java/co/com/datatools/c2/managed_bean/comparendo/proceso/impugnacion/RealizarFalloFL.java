package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * FL para el manejo de registro de fallo
 * 
 * @author julio.pinzon 2016-06-10
 *
 */
public class RealizarFalloFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "realizarFalloFL";

    private Integer idTipoFallo;
    private String codigoPlantilla;
    private String motivoFallo;
    private Double porcentaje;
    private Double puntos;
    private Long idProceso;
    private List<SelectItem> lstPlantillas;
    private boolean mostrarPorcentaje;
    private boolean mostrarPuntos;
    private Long cicomparendo;
    private BigDecimal valorObligacion;

    public Integer getIdTipoFallo() {
        return idTipoFallo;
    }

    public void setIdTipoFallo(Integer idTipoFallo) {
        this.idTipoFallo = idTipoFallo;
    }

    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public void setCodigoPlantilla(String codigoPlantilla) {
        this.codigoPlantilla = codigoPlantilla;
    }

    public String getMotivoFallo() {
        return motivoFallo;
    }

    public void setMotivoFallo(String motivoFallo) {
        this.motivoFallo = motivoFallo;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public List<SelectItem> getLstPlantillas() {
        return lstPlantillas;
    }

    public void setLstPlantillas(List<SelectItem> lstPlantillas) {
        this.lstPlantillas = lstPlantillas;
    }

    public boolean isMostrarPorcentaje() {
        return mostrarPorcentaje;
    }

    public void setMostrarPorcentaje(boolean mostrarPorcentaje) {
        this.mostrarPorcentaje = mostrarPorcentaje;
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public boolean isMostrarPuntos() {
        return mostrarPuntos;
    }

    public void setMostrarPuntos(boolean mostrarPuntos) {
        this.mostrarPuntos = mostrarPuntos;
    }

    public Double getPuntos() {
        return puntos;
    }

    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

}
