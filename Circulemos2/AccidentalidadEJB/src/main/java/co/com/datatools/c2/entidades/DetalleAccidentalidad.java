package co.com.datatools.c2.entidades;

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

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "detalle_accidentalidad")
@NamedQueries({ @NamedQuery(name = "DetalleAccidentalidad.findAll", query = "SELECT c FROM DetalleAccidentalidad c") })
public class DetalleAccidentalidad implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_accidentalidad")
    private Long id;

    @JoinColumn(name = "id_accidentalidad", referencedColumnName = "id_accidentalidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Accidentalidad accidentalidad;

    @Basic(optional = false)
    @Column(name = "breve_relacion")
    private String breveRelacion;

    @Basic(optional = false)
    @Column(name = "nombre_persona")
    private String nombrePersona;

    @JoinColumn(name = "id_tipo_persona_ipat", referencedColumnName = "id_tipo_persona_ipat")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPersonaIPAT tipoPersonaIPAT;

    @Basic(optional = false)
    @Column(name = "licencia")
    private String licencia;

    @JoinColumn(name = "id_categoria_licencia_conduc", referencedColumnName = "id_categoria_licencia_conduc")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoCategLicenciaConduccion tipoLicencia;

    @Column(name = "origen")
    private String origen;

    @JoinColumn(name = "id_estado_fisico", referencedColumnName = "id_estado_fisico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoFisico estadoFisico;

    @Basic(optional = false)
    @Column(name = "edad")
    private Integer edad;

    @Basic(optional = false)
    @Column(name = "embriaguez")
    private Boolean embriaguez;

    @Basic(optional = false)
    @Column(name = "aprehendido")
    private Boolean aprehendido;

    @JoinColumn(name = "id_clase_vehiculo", referencedColumnName = "id_clase_vehiculo")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClaseVehiculo claseVehiculo;

    @Column(name = "tipo_vehiculo")
    private String tipoVehiculo;

    @Column(name = "placa")
    private String placa;

    @JoinColumn(name = "id_tipo_servicio", referencedColumnName = "id_tipo_servicio")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoServicio tipoServicio;

    @Column(name = "calle_carretera")
    private String calleCarretera;

    @JoinColumn(name = "id_sentido", referencedColumnName = "id_sentido")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sentido sentido;

    @JoinColumn(name = "id_carril", referencedColumnName = "id_carril")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carril carril;

    @Column(name = "retenido")
    private Boolean retenido;

    @Basic(optional = false)
    @Column(name = "version")
    private String version;

    @Basic(optional = false)
    @Column(name = "daños_vehiculo")
    private String danosVehiculo;

    @Basic(optional = false)
    @Column(name = "circustancias")
    private String circustancias;

    @Basic(optional = false)
    @Column(name = "observaciones")
    private String observaciones;

    @Basic(optional = false)
    @Column(name = "zona_impacto")
    private String zona_impacto;

    @Basic(optional = false)
    @Column(name = "datos_adjuntos")
    private String datos_adjuntos;

    public DetalleAccidentalidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accidentalidad getAccidentalidad() {
        return accidentalidad;
    }

    public void setAccidentalidad(Accidentalidad accidentalidad) {
        this.accidentalidad = accidentalidad;
    }

    public String getBreveRelacion() {
        return breveRelacion;
    }

    public void setBreveRelacion(String breveRelacion) {
        this.breveRelacion = breveRelacion;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public TipoPersonaIPAT getTipoPersonaIPAT() {
        return tipoPersonaIPAT;
    }

    public void setTipoPersonaIPAT(TipoPersonaIPAT tipoPersonaIPAT) {
        this.tipoPersonaIPAT = tipoPersonaIPAT;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public TipoCategLicenciaConduccion getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(TipoCategLicenciaConduccion tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public EstadoFisico getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(EstadoFisico estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getEmbriaguez() {
        return embriaguez;
    }

    public void setEmbriaguez(Boolean embriaguez) {
        this.embriaguez = embriaguez;
    }

    public Boolean getAprehendido() {
        return aprehendido;
    }

    public void setAprehendido(Boolean aprehendido) {
        this.aprehendido = aprehendido;
    }

    public ClaseVehiculo getClaseVehiculo() {
        return claseVehiculo;
    }

    public void setClaseVehiculo(ClaseVehiculo claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getCalleCarretera() {
        return calleCarretera;
    }

    public void setCalleCarretera(String calleCarretera) {
        this.calleCarretera = calleCarretera;
    }

    public Sentido getSentido() {
        return sentido;
    }

    public void setSentido(Sentido sentido) {
        this.sentido = sentido;
    }

    public Carril getCarril() {
        return carril;
    }

    public void setCarril(Carril carril) {
        this.carril = carril;
    }

    public Boolean getRetenido() {
        return retenido;
    }

    public void setRetenido(Boolean retenido) {
        this.retenido = retenido;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDanosVehiculo() {
        return danosVehiculo;
    }

    public void setDanosVehiculo(String danosVehiculo) {
        this.danosVehiculo = danosVehiculo;
    }

    public String getCircustancias() {
        return circustancias;
    }

    public void setCircustancias(String circustancias) {
        this.circustancias = circustancias;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getZona_impacto() {
        return zona_impacto;
    }

    public void setZona_impacto(String zona_impacto) {
        this.zona_impacto = zona_impacto;
    }

    public String getDatos_adjuntos() {
        return datos_adjuntos;
    }

    public void setDatos_adjuntos(String datos_adjuntos) {
        this.datos_adjuntos = datos_adjuntos;
    }

}
