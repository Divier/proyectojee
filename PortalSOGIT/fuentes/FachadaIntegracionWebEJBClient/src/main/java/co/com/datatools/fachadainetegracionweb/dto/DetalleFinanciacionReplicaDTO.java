package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DetalleFinanciacionReplicaDTO")
public class DetalleFinanciacionReplicaDTO implements Serializable {

    private static final long serialVersionUID = 7179338086171122538L;
    private BigDecimal numeroFinanciacion;
    private Date fechaFinanciacion;
    private BigDecimal valorTotalFinanciado;
    private int numeroCuotas;
    private short estadoLectura;
    private String tipoIdentificacionDeudor;
    private String numeroIdentificacionDeudor;
    private String nombresDeudor;
    private List<DetalleAmortizacionDTO> detalleAmortizacion;
    private List<ObligacionFinanciacionDTO> oblicacionesFinanciacion;
    private String nombreTipoIdentificacion;

    public Date getFechaFinanciacion() {
        return fechaFinanciacion;
    }

    public void setFechaFinanciacion(Date fechaFinanciacion) {
        this.fechaFinanciacion = fechaFinanciacion;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public short getEstadoLectura() {
        return estadoLectura;
    }

    public void setEstadoLectura(short estadoLectura) {
        this.estadoLectura = estadoLectura;
    }

    public String getTipoIdentificacionDeudor() {
        return tipoIdentificacionDeudor;
    }

    public void setTipoIdentificacionDeudor(String tipoIdentificacionDeudor) {
        this.tipoIdentificacionDeudor = tipoIdentificacionDeudor;
    }

    public String getNumeroIdentificacionDeudor() {
        return numeroIdentificacionDeudor;
    }

    public void setNumeroIdentificacionDeudor(String numeroIdentificacionDeudor) {
        this.numeroIdentificacionDeudor = numeroIdentificacionDeudor;
    }

    public String getNombresDeudor() {
        return nombresDeudor;
    }

    public void setNombresDeudor(String nombresDeudor) {
        this.nombresDeudor = nombresDeudor;
    }

    public BigDecimal getNumeroFinanciacion() {
        return numeroFinanciacion;
    }

    public void setNumeroFinanciacion(BigDecimal numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
    }

    public BigDecimal getValorTotalFinanciado() {
        return valorTotalFinanciado;
    }

    public void setValorTotalFinanciado(BigDecimal valorTotalFinanciado) {
        this.valorTotalFinanciado = valorTotalFinanciado;
    }

    public List<DetalleAmortizacionDTO> getDetalleAmortizacion() {
        return detalleAmortizacion;
    }

    public void setDetalleAmortizacion(List<DetalleAmortizacionDTO> detalleAmortizacion) {
        this.detalleAmortizacion = detalleAmortizacion;
    }

    public List<ObligacionFinanciacionDTO> getOblicacionesFinanciacion() {
        return oblicacionesFinanciacion;
    }

    public void setOblicacionesFinanciacion(List<ObligacionFinanciacionDTO> oblicacionesFinanciacion) {
        this.oblicacionesFinanciacion = oblicacionesFinanciacion;
    }

    public String getNombreTipoIdentificacion() {
        return nombreTipoIdentificacion;
    }

    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) {
        this.nombreTipoIdentificacion = nombreTipoIdentificacion;
    }
}
