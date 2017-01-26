package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 10 14:02:34 COT 2016
 */
public class UbicabilidadSacDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String correoElectronico;
    private String direccion;
    private String pais;
    private String departamento;
    private String municipio;
    private String localidad;
    private Date fechaRegistro;
    private Integer idEstadoTransaccionSac;
    private Integer idOrigenUbicabilidadSac;
    private Integer idTipoDocumentoSac;
    private String numeroDocumento;
    private String primerApellido;
    private String primerNombre;
    private String razonSocial;
    private String segundoApellido;
    private String segundoNombre;
    private String telefonoFijo;
    private String telefonoMovil;

    // --- Constructor
    public UbicabilidadSacDTO() {
    }

    public UbicabilidadSacDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdEstadoTransaccionSac() {
        return this.idEstadoTransaccionSac;
    }

    public void setIdEstadoTransaccionSac(Integer idEstadoTransaccionSac) {
        this.idEstadoTransaccionSac = idEstadoTransaccionSac;
    }

    public Integer getIdOrigenUbicabilidadSac() {
        return this.idOrigenUbicabilidadSac;
    }

    public void setIdOrigenUbicabilidadSac(Integer idOrigenUbicabilidadSac) {
        this.idOrigenUbicabilidadSac = idOrigenUbicabilidadSac;
    }

    public Integer getIdTipoDocumentoSac() {
        return this.idTipoDocumentoSac;
    }

    public void setIdTipoDocumentoSac(Integer idTipoDocumentoSac) {
        this.idTipoDocumentoSac = idTipoDocumentoSac;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerApellido() {
        return this.primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getPrimerNombre() {
        return this.primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSegundoApellido() {
        return this.segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSegundoNombre() {
        return this.segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

}
