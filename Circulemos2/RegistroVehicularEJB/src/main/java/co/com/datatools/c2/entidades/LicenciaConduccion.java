package co.com.datatools.c2.entidades;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author dixon.alvarez
 * @version Iteracion 6
 */
@Entity
@Table(name = "licencia_conduccion")
@NamedQueries({ @NamedQuery(name = "LicenciaConduccion.findAll", query = "SELECT l FROM LicenciaConduccion l") })
public class LicenciaConduccion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_licencia")
    private Long id;

    @Basic(optional = false)
    @Column(name = "licencia_conduccion")
    private String licenciaConduccion;

    @Basic(optional = false)
    @Column(name = "fecha_expedicion_licencia")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionLicencia;

    @Column(name = "fecha_vencimiento_licencia")
    private BigInteger fechaVencimientoLicencia;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @JoinColumn(name = "id_estado_licencia", referencedColumnName = "id_estado_licencia")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoLicencia estadoLicencia;

    @JoinColumn(name = "id_categoria_licencia_conduc", referencedColumnName = "id_categoria_licencia_conduc")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoCategLicenciaConduccion categoria;

    public LicenciaConduccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenciaConduccion() {
        return licenciaConduccion;
    }

    public void setLicenciaConduccion(String licenciaConduccion) {
        this.licenciaConduccion = licenciaConduccion;
    }

    public Date getFechaExpedicionLicencia() {
        return fechaExpedicionLicencia;
    }

    public void setFechaExpedicionLicencia(Date fechaExpedicionLicencia) {
        this.fechaExpedicionLicencia = fechaExpedicionLicencia;
    }

    public BigInteger getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(BigInteger fechaVencimientoLicencia) {
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public EstadoLicencia getEstadoLicencia() {
        return estadoLicencia;
    }

    public void setEstadoLicencia(EstadoLicencia estadoLicencia) {
        this.estadoLicencia = estadoLicencia;
    }

    public TipoCategLicenciaConduccion getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoCategLicenciaConduccion categoria) {
        this.categoria = categoria;
    }

}
