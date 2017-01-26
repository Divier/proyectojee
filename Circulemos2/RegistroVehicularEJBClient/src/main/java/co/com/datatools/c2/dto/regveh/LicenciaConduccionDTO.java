package co.com.datatools.c2.dto.regveh;

import java.math.BigInteger;
import java.util.Date;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.TipoCategLicenciaConduccionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Fri Feb 27 16:38:59 COT 2015
 */
public class LicenciaConduccionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String licenciaConduccion;
    private Date fechaExpedicionLicencia;
    private BigInteger fechaVencimientoLicencia;
    private PersonaDTO persona;
    private OrganismoTransitoDTO organismoTransito;
    private EstadoLicenciaDTO estadoLicencia;
    private TipoCategLicenciaConduccionDTO categoria;

    // --- Constructor
    public LicenciaConduccionDTO() {
    }

    public LicenciaConduccionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenciaConduccion() {
        return this.licenciaConduccion;
    }

    public void setLicenciaConduccion(String licenciaConduccion) {
        this.licenciaConduccion = licenciaConduccion;
    }

    public Date getFechaExpedicionLicencia() {
        return this.fechaExpedicionLicencia;
    }

    public void setFechaExpedicionLicencia(Date fechaExpedicionLicencia) {
        this.fechaExpedicionLicencia = fechaExpedicionLicencia;
    }

    public BigInteger getFechaVencimientoLicencia() {
        return this.fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(BigInteger fechaVencimientoLicencia) {
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public EstadoLicenciaDTO getEstadoLicencia() {
        return this.estadoLicencia;
    }

    public void setEstadoLicencia(EstadoLicenciaDTO estadoLicencia) {
        this.estadoLicencia = estadoLicencia;
    }

    public TipoCategLicenciaConduccionDTO getCategoria() {
        return this.categoria;
    }

    public void setCategoria(TipoCategLicenciaConduccionDTO categoria) {
        this.categoria = categoria;
    }

}
