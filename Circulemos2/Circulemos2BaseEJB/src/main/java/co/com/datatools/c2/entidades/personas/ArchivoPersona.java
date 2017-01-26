package co.com.datatools.c2.entidades.personas;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.ubicabilidad.TipoArchivoPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the archivo_persona database table.
 * 
 */
@Entity
@Table(name = "archivo_persona")
@NamedQuery(name = "ArchivoPersona.findAll", query = "SELECT a FROM ArchivoPersona a")
public class ArchivoPersona implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo_persona")
    private Long id;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Column(name = "numero_archivo")
    private String numeroArchivo;

    @Column(name = "pagina")
    private String pagina;

    // bi-directional many-to-many association to PersonaHistorico
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "archivo_persona_historico",
            joinColumns = { @JoinColumn(name = "id_archivo_persona") },
            inverseJoinColumns = { @JoinColumn(name = "id_persona_historico") })
    private List<PersonaHistorico> personaHistoricos;

    // bi-directional many-to-one association to TipoTelefono
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_archivo_persona")
    private TipoArchivoPersona tipoArchivoPersona;

    // bi-directional many-to-one association to Persona
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_registro")
    private UsuarioPersona usuarioRegistro;

    // bi-directional many-to-one association to TipoFuenteInformacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_fuente_informacion")
    private TipoFuenteInformacion tipoFuenteInformacion;

    public ArchivoPersona() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroArchivo() {
        return numeroArchivo;
    }

    public void setNumeroArchivo(String numeroArchivo) {
        this.numeroArchivo = numeroArchivo;
    }

    public List<PersonaHistorico> getPersonaHistoricos() {
        return personaHistoricos;
    }

    public void setPersonaHistoricos(List<PersonaHistorico> personaHistoricos) {
        this.personaHistoricos = personaHistoricos;
    }

    public TipoArchivoPersona getTipoArchivoPersona() {
        return tipoArchivoPersona;
    }

    public void setTipoArchivoPersona(TipoArchivoPersona tipoArchivoPersona) {
        this.tipoArchivoPersona = tipoArchivoPersona;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public UsuarioPersona getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(UsuarioPersona usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public TipoFuenteInformacion getTipoFuenteInformacion() {
        return tipoFuenteInformacion;
    }

    public void setTipoFuenteInformacion(TipoFuenteInformacion tipoFuenteInformacion) {
        this.tipoFuenteInformacion = tipoFuenteInformacion;
    }

}