package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Ingresa logica para las personas
 * 
 * @author giovanni.velandia
 * @version 3.0 - Wed Oct 07 11:58:46 COT 2015
 */
public class ProcesaComparendoPersonaDTO implements EntidadDtoC2, Cloneable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer codigoTipoPersonaComparendo;
    private Integer idTipoIdentificacion;
    private String numeroIdentificacion;
    private Short digitoVerificacionNit;
    private String numeroLicencia;
    private Date fechaExpedicionLicenCondu;
    private Date fechaVencimientoLicenCondu;
    private Integer idCategoriaLicenciaCondu;
    private Integer codigoOrganismoLicencia;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private String razonSocial;
    private Short edad;
    private String email;
    private String telefonoFijo;
    private String telefonoMovil;
    private ProcesaDireccionDTO procesaDireccion;
    private ProcesaComparendoDTO procesaComparendo;

    private PersonaDTO personaDTO;

    // --- Constructor
    public ProcesaComparendoPersonaDTO() {
    }

    public ProcesaComparendoPersonaDTO(Long id) {
        this.id = id;

    }

    public ProcesaComparendoPersonaDTO clone() {
        try {
            return (ProcesaComparendoPersonaDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoTipoPersonaComparendo() {
        return this.codigoTipoPersonaComparendo;
    }

    public void setCodigoTipoPersonaComparendo(Integer codigoTipoPersonaComparendo) {
        this.codigoTipoPersonaComparendo = codigoTipoPersonaComparendo;
    }

    public Integer getIdTipoIdentificacion() {
        return this.idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return this.numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Short getDigitoVerificacionNit() {
        return this.digitoVerificacionNit;
    }

    public void setDigitoVerificacionNit(Short digitoVerificacionNit) {
        this.digitoVerificacionNit = digitoVerificacionNit;
    }

    public String getNumeroLicencia() {
        return this.numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public Date getFechaExpedicionLicenCondu() {
        return this.fechaExpedicionLicenCondu;
    }

    public void setFechaExpedicionLicenCondu(Date fechaExpedicionLicenCondu) {
        this.fechaExpedicionLicenCondu = fechaExpedicionLicenCondu;
    }

    public Date getFechaVencimientoLicenCondu() {
        return this.fechaVencimientoLicenCondu;
    }

    public void setFechaVencimientoLicenCondu(Date fechaVencimientoLicenCondu) {
        this.fechaVencimientoLicenCondu = fechaVencimientoLicenCondu;
    }

    public Integer getIdCategoriaLicenciaCondu() {
        return this.idCategoriaLicenciaCondu;
    }

    public void setIdCategoriaLicenciaCondu(Integer idCategoriaLicenciaCondu) {
        this.idCategoriaLicenciaCondu = idCategoriaLicenciaCondu;
    }

    public Integer getCodigoOrganismoLicencia() {
        return this.codigoOrganismoLicencia;
    }

    public void setCodigoOrganismoLicencia(Integer codigoOrganismoLicencia) {
        this.codigoOrganismoLicencia = codigoOrganismoLicencia;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public PersonaDTO getPersonaDTO() {
        return personaDTO;
    }

    public void setPersonaDTO(PersonaDTO personaDTO) {
        this.personaDTO = personaDTO;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return this.nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return this.nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Short getEdad() {
        return this.edad;
    }

    public void setEdad(Short edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public ProcesaDireccionDTO getProcesaDireccion() {
        return this.procesaDireccion;
    }

    public void setProcesaDireccion(ProcesaDireccionDTO procesaDireccion) {
        this.procesaDireccion = procesaDireccion;
    }

    public ProcesaComparendoDTO getProcesaComparendo() {
        return this.procesaComparendo;
    }

    public void setProcesaComparendo(ProcesaComparendoDTO procesaComparendo) {
        this.procesaComparendo = procesaComparendo;
    }

}
