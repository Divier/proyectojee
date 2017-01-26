package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.ArchivoTransportableDTO;

public class ConsultarNotificacionesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombreInfractor;
    private String codigoInfraccion;
    private Date fechaImposicion;
    private List<String> lsCorreoElectronico;
    private Long externalId;
    private Long numeroCitacion;
    private String placa;
    private Boolean correoValido;
    private Long codSeguimientoInt;
    private List<ArchivoTransportableDTO> lsArchivos;

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Long getCodSeguimientoInt() {
        return codSeguimientoInt;
    }

    public void setCodSeguimientoInt(Long codSeguimientoInt) {
        this.codSeguimientoInt = codSeguimientoInt;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreInfractor() {
        return nombreInfractor;
    }

    public void setNombreInfractor(String nombreInfractor) {
        this.nombreInfractor = nombreInfractor;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Date getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public void addCorreoElectronico(String correo) {
        if (lsCorreoElectronico == null) {
            lsCorreoElectronico = new ArrayList<>();
        }
        if (correo != null) {
            lsCorreoElectronico.add(correo);
        }
    }

    public List<String> getLsCorreoElectronico() {
        return lsCorreoElectronico;
    }

    public void setLsCorreoElectronico(List<String> lsCorreoElectronico) {
        this.lsCorreoElectronico = lsCorreoElectronico;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public Long getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(Long numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Boolean getCorreoValido() {
        return correoValido;
    }

    public void setCorreoValido(Boolean correoValido) {
        this.correoValido = correoValido;
    }

    public List<ArchivoTransportableDTO> getLsArchivos() {
        return lsArchivos;
    }

    public void setLsArchivos(List<ArchivoTransportableDTO> lsArchivos) {
        this.lsArchivos = lsArchivos;
    }

    @Override
    public boolean equals(Object obj) {
        ConsultarNotificacionesDTO consultarNotificacionesDTO = (ConsultarNotificacionesDTO) obj;
        if (obj != null && consultarNotificacionesDTO.getLsCorreoElectronico() != null
                && consultarNotificacionesDTO.getCodSeguimientoInt().equals(this.codSeguimientoInt)
                && consultarNotificacionesDTO.getLsCorreoElectronico().equals(this.lsCorreoElectronico)) {
            return true;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "ConsultarNotificacionesDTO [tipoIdentificacion=" + tipoIdentificacion + ", numeroIdentificacion="
                + numeroIdentificacion + ", nombreInfractor=" + nombreInfractor + ", codigoInfraccion="
                + codigoInfraccion + ", fechaImposicion=" + fechaImposicion + ", lsCorreoElectronico="
                + lsCorreoElectronico + ", externalId=" + externalId + ", numeroCitacion=" + numeroCitacion + ", placa="
                + placa + ", correoValido=" + correoValido + ", codSeguimientoInt=" + codSeguimientoInt
                + ", lsArchivos=" + lsArchivos + "]";
    }
}