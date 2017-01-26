package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "accidentalidad")
@NamedQueries({ @NamedQuery(name = "Accidentalidad.findAll", query = "SELECT c FROM Accidentalidad c") })
public class Accidentalidad implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accidentalidad")
    private Long id;

    @Basic(optional = false)
    @Column(name = "consecutivo")
    private String consecutivo;

    @JoinColumn(name = "id_tipo_accidente", referencedColumnName = "id_tipo_accidente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoAccidente tipoAccidente;

    @Basic(optional = false)
    @Column(name = "consecuencias")
    private String consecuencias;

    @Column(name = "ubicacion_gps")
    private String ubicacionGps;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "parte_finalizado")
    private Date parteFinalizado;

    @Basic(optional = false)
    @Column(name = "lugar_accidente")
    private String lugarAccidente;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_accidente")
    private Date fechaAccidente;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_aviso")
    private Date fechaAviso;

    @JoinColumn(name = "id_prevencion", referencedColumnName = "id_prevencion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Prevencion prevencion;

    @JoinColumn(name = "id_delegacion", referencedColumnName = "id_delegacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Delegacion delegacion;

    @Basic(optional = false)
    @Column(name = "referencia_hechos")
    private String referenciaHechos;

    @Basic(optional = false)
    @Column(name = "agente")
    private String agente;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioPersona usuario;

    @OneToMany(mappedBy = "accidentalidad", fetch = FetchType.LAZY)
    private List<ArchivoAccidentalidad> archivoAccidentalidad;

    @OneToMany(mappedBy = "accidentalidad", fetch = FetchType.LAZY)
    private List<DetalleAccidentalidad> detalleAccidentalidad;

    @JoinColumn(name = "id_estado_accidentalidad", referencedColumnName = "id_estado_accidentalidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoAccidentalidad estadoAccidentalidad;

    public Accidentalidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public TipoAccidente getTipoAccidente() {
        return tipoAccidente;
    }

    public void setTipoAccidente(TipoAccidente tipoAccidente) {
        this.tipoAccidente = tipoAccidente;
    }

    public String getConsecuencias() {
        return consecuencias;
    }

    public void setConsecuencias(String consecuencias) {
        this.consecuencias = consecuencias;
    }

    public String getUbicacionGps() {
        return ubicacionGps;
    }

    public void setUbicacionGps(String ubicacionGps) {
        this.ubicacionGps = ubicacionGps;
    }

    public Date getParteFinalizado() {
        return parteFinalizado;
    }

    public void setParteFinalizado(Date parteFinalizado) {
        this.parteFinalizado = parteFinalizado;
    }

    public String getLugarAccidente() {
        return lugarAccidente;
    }

    public void setLugarAccidente(String lugarAccidente) {
        this.lugarAccidente = lugarAccidente;
    }

    public Date getFechaAccidente() {
        return fechaAccidente;
    }

    public void setFechaAccidente(Date fechaAccidente) {
        this.fechaAccidente = fechaAccidente;
    }

    public Date getFechaAviso() {
        return fechaAviso;
    }

    public void setFechaAviso(Date fechaAviso) {
        this.fechaAviso = fechaAviso;
    }

    public Prevencion getPrevencion() {
        return prevencion;
    }

    public void setPrevencion(Prevencion prevencion) {
        this.prevencion = prevencion;
    }

    public Delegacion getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(Delegacion delegacion) {
        this.delegacion = delegacion;
    }

    public String getReferenciaHechos() {
        return referenciaHechos;
    }

    public void setReferenciaHechos(String referenciaHechos) {
        this.referenciaHechos = referenciaHechos;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UsuarioPersona getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersona usuario) {
        this.usuario = usuario;
    }

    public List<ArchivoAccidentalidad> getArchivoAccidentalidad() {
        return archivoAccidentalidad;
    }

    public void setArchivoAccidentalidad(List<ArchivoAccidentalidad> archivoAccidentalidad) {
        this.archivoAccidentalidad = archivoAccidentalidad;
    }

    public List<DetalleAccidentalidad> getDetalleAccidentalidad() {
        return detalleAccidentalidad;
    }

    public void setDetalleAccidentalidad(List<DetalleAccidentalidad> detalleAccidentalidad) {
        this.detalleAccidentalidad = detalleAccidentalidad;
    }

    public EstadoAccidentalidad getEstadoAccidentalidad() {
        return estadoAccidentalidad;
    }

    public void setEstadoAccidentalidad(EstadoAccidentalidad estadoAccidentalidad) {
        this.estadoAccidentalidad = estadoAccidentalidad;
    }
}
