package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.TipoPersonaComparendoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.TipoCategLicenciaConduccionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:26:54 COT 2015
 */
public class ComparendoPersonaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ComparendoDTO comparendo;
    private TipoPersonaComparendoDTO tipoPersonaComparendo;
    private PersonaDTO persona;
    private TipoIdentificacionPersonaDTO tipoIdentificacion;
    private String numeroIdentificacion;
    private Short digitoVerificacionNit;
    private String numeroLicencia;
    private Date fechaExpedicionLicenCondu;
    private Date fechaVencimientoLicenCondu;
    private TipoCategLicenciaConduccionDTO categoriaLicencia;
    private OrganismoTransitoDTO organismoTransito;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private String razonSocial;
    private String telefonoFijo;
    private String telefonoMovil;
    private Short edad;
    private DireccionDTO direccion;
    private String email;
    private Date fechaInicio;
    private Date fechaFin;

    // --- Constructor
    public ComparendoPersonaDTO() {
    }

    public ComparendoPersonaDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComparendoDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public TipoPersonaComparendoDTO getTipoPersonaComparendo() {
        return this.tipoPersonaComparendo;
    }

    public void setTipoPersonaComparendo(TipoPersonaComparendoDTO tipoPersonaComparendo) {
        this.tipoPersonaComparendo = tipoPersonaComparendo;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public TipoIdentificacionPersonaDTO getTipoIdentificacion() {
        return this.tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacionPersonaDTO tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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

    public TipoCategLicenciaConduccionDTO getCategoriaLicencia() {
        return this.categoriaLicencia;
    }

    public void setCategoriaLicencia(TipoCategLicenciaConduccionDTO categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public String getApellido1() {
        return this.apellido1;
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

    public Short getEdad() {
        return this.edad;
    }

    public void setEdad(Short edad) {
        this.edad = edad;
    }

    public DireccionDTO getDireccion() {
        return this.direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Metodo que presenta el nombre en la siguiente estructura separada por un espacio: - Nombre 1 - Apellido 1
     * 
     * @return Nombre corto
     * 
     * @author julio.pinzon
     */
    public String getNombreCorto() {
        ComparendoPersonaDTO persona = this;
        StringBuilder strNombre = new StringBuilder(1);
        if (persona != null) {
            if (StringUtils.isNotEmpty(persona.getRazonSocial())) {
                strNombre.append(persona.getRazonSocial());
            } else {
                strNombre.append(StringUtils.isNotEmpty(persona.getNombre1()) ? persona.getNombre1() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getApellido1()) ? persona.getApellido1() : "");
            }
        }
        return strNombre.toString();
    }

    /**
     * Metodo que presenta el nombre en la siguiente estructura separada por un espacio: - Nombre 1 - Nombre 2 - Apellido 1 Apellido 2
     * 
     * @return Nombre completo
     * 
     * @author julio.pinzon
     */
    public String getNombreCompleto() {
        ComparendoPersonaDTO persona = this;
        StringBuilder strNombre = new StringBuilder(1);
        if (persona != null) {
            if (StringUtils.isNotEmpty(persona.getRazonSocial())) {
                strNombre.append(persona.getRazonSocial());
            } else {
                strNombre.append(StringUtils.isNotEmpty(persona.getNombre1()) ? persona.getNombre1() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getNombre2()) ? persona.getNombre2() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getApellido1()) ? persona.getApellido1() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getApellido2()) ? persona.getApellido2() : "");
            }
        }
        return strNombre.toString();
    }

    /**
     * Metodo que presenta los nombres en la siguiente estructura separada por un espacio: - Nombre 1 - Nombre 2
     * 
     * @return Nombres
     * 
     * @author julio.pinzon
     */
    public String getNombres() {
        ComparendoPersonaDTO persona = this;
        StringBuilder strNombre = new StringBuilder(1);
        if (persona != null) {
            if (StringUtils.isNotEmpty(persona.getRazonSocial())) {
                strNombre.append(persona.getRazonSocial());
            } else {
                strNombre.append(StringUtils.isNotEmpty(persona.getNombre1()) ? persona.getNombre1() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getNombre2()) ? persona.getNombre2() : "");
            }
        }
        return strNombre.toString();
    }

    /**
     * Metodo que presenta los apellidos en la siguiente estructura separada por un espacio: - Apellido 1 - Apellido 2
     * 
     * @return Apellidos
     * 
     * @author julio.pinzon
     */
    public String getApellidos() {
        ComparendoPersonaDTO persona = this;
        StringBuilder strNombre = new StringBuilder(1);
        if (persona != null) {
            if (StringUtils.isNotEmpty(persona.getRazonSocial())) {
                strNombre.append("");
            } else {
                strNombre.append(StringUtils.isNotEmpty(persona.getApellido1()) ? persona.getApellido1() + " " : "");
                strNombre.append(StringUtils.isNotEmpty(persona.getApellido2()) ? persona.getApellido2() : "");
            }
        }
        return strNombre.toString();
    }

    /**
     * Clona el objeto a una nueva instancia
     * 
     * @author luis.forero(2016-02-02)
     */
    public ComparendoPersonaDTO clone() {
        ComparendoPersonaDTO clone = new ComparendoPersonaDTO(this.id != null ? new Long(this.id) : null);

        if (this.tipoPersonaComparendo != null) {
            TipoPersonaComparendoDTO tipoPersonaComparendoDTO = new TipoPersonaComparendoDTO(
                    this.tipoPersonaComparendo.getCodigo() != null ? new Integer(this.tipoPersonaComparendo.getCodigo())
                            : null);
            tipoPersonaComparendoDTO.setNombre(this.tipoPersonaComparendo.getNombre() != null ? new String(
                    this.tipoPersonaComparendo.getNombre()) : null);
            clone.setTipoPersonaComparendo(tipoPersonaComparendoDTO);
        }
        if (this.persona != null) {
            clone.setPersona(new PersonaDTO(this.persona.getId() != null ? new Long(this.persona.getId()) : null));
        }
        if (this.tipoIdentificacion != null) {
            TipoIdentificacionPersonaDTO identificacionPersonaDTO = new TipoIdentificacionPersonaDTO(
                    this.tipoIdentificacion.getId() != null ? new Integer(this.tipoIdentificacion.getId()) : null);
            identificacionPersonaDTO.setNombre(this.tipoIdentificacion.getNombre() != null ? new String(
                    this.tipoIdentificacion.getNombre()) : null);
            identificacionPersonaDTO.setSigla(this.tipoIdentificacion.getSigla() != null ? new String(
                    this.tipoIdentificacion.getSigla()) : null);
            clone.setTipoIdentificacion(identificacionPersonaDTO);
        }
        clone.setNumeroIdentificacion(this.numeroIdentificacion != null ? new String(this.numeroIdentificacion) : null);
        clone.setDigitoVerificacionNit(this.digitoVerificacionNit != null ? new Short(this.digitoVerificacionNit)
                : null);
        clone.setNumeroLicencia(this.numeroLicencia != null ? new String(this.numeroLicencia) : null);
        clone.setFechaExpedicionLicenCondu(this.fechaExpedicionLicenCondu != null ? new Date(
                this.fechaExpedicionLicenCondu.getTime()) : null);
        clone.setFechaVencimientoLicenCondu(this.fechaVencimientoLicenCondu != null ? new Date(
                this.fechaVencimientoLicenCondu.getTime()) : null);
        if (this.categoriaLicencia != null) {
            TipoCategLicenciaConduccionDTO categLicenciaConduccionDTO = new TipoCategLicenciaConduccionDTO(
                    this.categoriaLicencia.getId() != null ? new Integer(this.categoriaLicencia.getId()) : null);
            categLicenciaConduccionDTO.setNombre(this.categoriaLicencia.getNombre() != null ? new String(
                    this.categoriaLicencia.getNombre()) : null);
            clone.setCategoriaLicencia(categLicenciaConduccionDTO);
        }
        if (this.organismoTransito != null) {
            clone.setOrganismoTransito(this.organismoTransito.clone());
        }
        clone.setApellido1(this.apellido1 != null ? new String(this.apellido1) : null);
        clone.setApellido2(this.apellido2 != null ? new String(this.apellido2) : null);
        clone.setNombre1(this.nombre1 != null ? new String(this.nombre1) : null);
        clone.setNombre2(this.nombre2 != null ? new String(this.nombre2) : null);
        clone.setRazonSocial(this.razonSocial != null ? new String(this.razonSocial) : null);
        clone.setTelefonoFijo(this.telefonoFijo != null ? new String(this.telefonoFijo) : null);
        clone.setTelefonoMovil(this.telefonoMovil != null ? new String(this.telefonoMovil) : null);
        clone.setEdad(this.edad != null ? new Short(this.edad) : null);
        clone.setDireccion(this.direccion != null ? this.direccion.clone() : null);
        clone.setEmail(this.email != null ? new String(this.email) : null);
        clone.setFechaInicio(this.fechaInicio != null ? new Date(this.fechaInicio.getTime()) : null);
        clone.setFechaFin(this.fechaFin != null ? new Date(this.fechaFin.getTime()) : null);

        return clone;
    }
}
