package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import co.com.datatools.c2.constantes.ConstantesCargaArchivos;

/**
 * DTO de datos de aprobacion de coactivo
 * 
 * @author julio.pinzon 2016-09-30
 */
public class ValidacionPrecoactivoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private PrecoactivoDTO precoactivo;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombreCompleto;
    private String direccion;
    private String medioImposicion;
    private Long idFacturaAxis;
    private BigDecimal valorMulta;
    private String numeroCitacion;
    private String fechaInfraccion;
    private String codigoInfraccion;
    private String fechaNotificacion;
    private String descripcionInfraccion;

    // --- Constructor
    public ValidacionPrecoactivoDTO() {
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMedioImposicion() {
        return medioImposicion;
    }

    public void setMedioImposicion(String medioImposicion) {
        this.medioImposicion = medioImposicion;
    }

    public Long getIdFacturaAxis() {
        return idFacturaAxis;
    }

    public void setIdFacturaAxis(Long idFacturaAxis) {
        this.idFacturaAxis = idFacturaAxis;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public String getFechaInfraccion() {
        return fechaInfraccion;
    }

    public void setFechaInfraccion(String fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public PrecoactivoDTO getPrecoactivo() {
        return precoactivo;
    }

    public void setPrecoactivo(PrecoactivoDTO precoactivo) {
        this.precoactivo = precoactivo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (tipoIdentificacion != null) {
            builder.append(tipoIdentificacion);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (numeroIdentificacion != null) {
            builder.append(numeroIdentificacion);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (nombreCompleto != null) {
            builder.append(nombreCompleto);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (direccion != null) {
            builder.append(direccion);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (medioImposicion != null) {
            builder.append(medioImposicion);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (idFacturaAxis != null) {
            builder.append(idFacturaAxis);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (numeroCitacion != null) {
            builder.append(numeroCitacion);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (fechaInfraccion != null) {
            builder.append(fechaInfraccion);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (fechaNotificacion != null) {
            builder.append(fechaNotificacion);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (valorMulta != null) {
            builder.append(valorMulta);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (codigoInfraccion != null) {
            builder.append(codigoInfraccion);
        }
        builder.append(ConstantesCargaArchivos.CSV_SEPARADOR);
        if (descripcionInfraccion != null)
            builder.append(descripcionInfraccion);
        return builder.toString();
    }

}