package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

public class ComparendoConsultaAnulacionDTO implements Serializable {
    // --- Atributos
    private Long cicomparendo;
    private String numeroMulta;
    private String codigoInfraccion;
    private String fechaImposicion;
    private String horaImposicion;
    private String fechaNotificacion;
    private String estadoMulta;
    private String direccionInfraccion;
    private String placa;
    private String tipoDocumentoInfractor;
    private String numeroDocumentoInfractor;
    private String nombreInfractor;
    private String fechaRegistro;
    private String correoElectronico;
    private Long idCartera;

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public String getNumeroMulta() {
        return numeroMulta;
    }

    public void setNumeroMulta(String numeroMulta) {
        this.numeroMulta = numeroMulta;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public String getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(String fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public String getHoraImposicion() {
        return horaImposicion;
    }

    public void setHoraImposicion(String horaImposicion) {
        this.horaImposicion = horaImposicion;
    }

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getEstadoMulta() {
        return estadoMulta;
    }

    public void setEstadoMulta(String estadoMulta) {
        this.estadoMulta = estadoMulta;
    }

    public String getDireccionInfraccion() {
        return direccionInfraccion;
    }

    public void setDireccionInfraccion(String direccionInfraccion) {
        this.direccionInfraccion = direccionInfraccion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoDocumentoInfractor() {
        return tipoDocumentoInfractor;
    }

    public void setTipoDocumentoInfractor(String tipoDocumentoInfractor) {
        this.tipoDocumentoInfractor = tipoDocumentoInfractor;
    }

    public String getNumeroDocumentoInfractor() {
        return numeroDocumentoInfractor;
    }

    public void setNumeroDocumentoInfractor(String numeroDocumentoInfractor) {
        this.numeroDocumentoInfractor = numeroDocumentoInfractor;
    }

    public String getNombreInfractor() {
        return nombreInfractor;
    }

    public void setNombreInfractor(String nombreInfractor) {
        this.nombreInfractor = nombreInfractor;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

}
