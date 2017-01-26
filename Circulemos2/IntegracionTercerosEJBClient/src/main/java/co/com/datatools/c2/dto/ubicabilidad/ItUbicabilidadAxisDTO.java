package co.com.datatools.c2.dto.ubicabilidad;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon May 16 17:10:38 COT 2016
 */
public class ItUbicabilidadAxisDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String idTipoDocumentoAxis;
    private String numeroDocumento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String razonSocial;
    private String direccion;
    private Double latitud;
    private Double longitud;
    private String telefonoFijo;
    private String telefonoMovil;
    private String correoElectronico;
    private String idFuenteInformacion;
    private String idEstadoLecturaAxis;
    private Date fechaReporte;

    // --- Constructor
    public ItUbicabilidadAxisDTO() {
    }

    public ItUbicabilidadAxisDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTipoDocumentoAxis() {
        return idTipoDocumentoAxis;
    }

    public void setIdTipoDocumentoAxis(String idTipoDocumentoAxis) {
        this.idTipoDocumentoAxis = idTipoDocumentoAxis;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombre() {
        return this.primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return this.segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return this.primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return this.segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLatitud() {
        return this.latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return this.longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getTelefonoFijo() {
        return this.telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return this.telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getIdFuenteInformacion() {
        return this.idFuenteInformacion;
    }

    public void setIdFuenteInformacion(String idFuenteInformacion) {
        this.idFuenteInformacion = idFuenteInformacion;
    }

    public String getIdEstadoLecturaAxis() {
        return this.idEstadoLecturaAxis;
    }

    public void setIdEstadoLecturaAxis(String idEstadoLecturaAxis) {
        this.idEstadoLecturaAxis = idEstadoLecturaAxis;
    }

    public Date getFechaReporte() {
        return this.fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
}