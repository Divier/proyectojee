package co.com.datatools.c2.dto.ws;

import javax.xml.datatype.XMLGregorianCalendar;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Datos de la persona del comparendo recibidos a través del WebService
 * 
 * @author luis.forero(2015-12-03)
 * 
 */
public class PersonaWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;
    private String codigoTipoIdentificacion;
    private String numeroIdentificacion;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private String nombreEmpresaRazonSocial;
    private String correoElectronico;
    private String telefonoFijo;
    private String telefonoMovil;
    private Short edad;

    private DireccionWSDTO direccion;

    private Integer codigoOrganismoLicencia;
    private String numeroLicenciaConduccion;
    private String codigoCategoriaLicenciaCondu;
    private XMLGregorianCalendar fechaExpedicionLicenciaConduccion;
    private XMLGregorianCalendar fechaVencimientoLicenciaConduccion;

    public String getCodigoTipoIdentificacion() {
        return codigoTipoIdentificacion;
    }

    public void setCodigoTipoIdentificacion(String codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getNombreEmpresaRazonSocial() {
        return nombreEmpresaRazonSocial;
    }

    public void setNombreEmpresaRazonSocial(String nombreEmpresaRazonSocial) {
        this.nombreEmpresaRazonSocial = nombreEmpresaRazonSocial;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Short getEdad() {
        return edad;
    }

    public void setEdad(Short edad) {
        this.edad = edad;
    }

    public DireccionWSDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionWSDTO direccion) {
        this.direccion = direccion;
    }

    public Integer getCodigoOrganismoLicencia() {
        return codigoOrganismoLicencia;
    }

    public void setCodigoOrganismoLicencia(Integer codigoOrganismoLicencia) {
        this.codigoOrganismoLicencia = codigoOrganismoLicencia;
    }

    public String getNumeroLicenciaConduccion() {
        return numeroLicenciaConduccion;
    }

    public void setNumeroLicenciaConduccion(String numeroLicenciaConduccion) {
        this.numeroLicenciaConduccion = numeroLicenciaConduccion;
    }

    public String getCodigoCategoriaLicenciaCondu() {
        return codigoCategoriaLicenciaCondu;
    }

    public void setCodigoCategoriaLicenciaCondu(String codigoCategoriaLicenciaCondu) {
        this.codigoCategoriaLicenciaCondu = codigoCategoriaLicenciaCondu;
    }

    public XMLGregorianCalendar getFechaExpedicionLicenciaConduccion() {
        return fechaExpedicionLicenciaConduccion;
    }

    public void setFechaExpedicionLicenciaConduccion(XMLGregorianCalendar fechaExpedicionLicenciaConduccion) {
        this.fechaExpedicionLicenciaConduccion = fechaExpedicionLicenciaConduccion;
    }

    public XMLGregorianCalendar getFechaVencimientoLicenciaConduccion() {
        return fechaVencimientoLicenciaConduccion;
    }

    public void setFechaVencimientoLicenciaConduccion(XMLGregorianCalendar fechaVencimientoLicenciaConduccion) {
        this.fechaVencimientoLicenciaConduccion = fechaVencimientoLicenciaConduccion;
    }

}