package co.com.datatools.c2.entidades;

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
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author dixon.alvarez
 * @version Iteracion 6
 */
@Entity
@Table(name = "historico_vehiculo")
@NamedQueries({ @NamedQuery(name = "HistoricoVehiculo.findAll", query = "SELECT h FROM HistoricoVehiculo h") })
public class HistoricoVehiculo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historico_vehiculo")
    private Long id;

    @Basic(optional = false)
    @Column(name = "numero_motor")
    private Long numeroMotor;

    @Column(name = "fecha_expedicion_tarje_opera")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionTarjeOpera;

    @Column(name = "fecha_vencimiento_tarje_opera")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoTarjeOpera;

    @Column(name = "numero_tarjeta_operacion")
    private Integer numeroTarjetaOperacion;

    @Basic(optional = false)
    @Column(name = "campo_cambio")
    private String campoCambio;

    @Basic(optional = false)
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @JoinColumn(name = "id_color", referencedColumnName = "id_color")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Color color;

    @JoinColumn(name = "id_modalidad", referencedColumnName = "id_modalidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modalidad modalidad;

    @JoinColumn(name = "id_radio_accion", referencedColumnName = "id_radio_accion")
    @ManyToOne(fetch = FetchType.LAZY)
    private RadioAccion radioAccion;

    @JoinColumn(name = "id_tipo_servicio", referencedColumnName = "id_tipo_servicio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoServicio tipoServicio;

    @JoinColumn(name = "id_clase_vehiculo", referencedColumnName = "id_clase_vehiculo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClaseVehiculo claseVehiculo;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vehiculo vehiculo;

    public HistoricoVehiculo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(Long numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public Date getFechaExpedicionTarjeOpera() {
        return fechaExpedicionTarjeOpera;
    }

    public void setFechaExpedicionTarjeOpera(Date fechaExpedicionTarjeOpera) {
        this.fechaExpedicionTarjeOpera = fechaExpedicionTarjeOpera;
    }

    public Date getFechaVencimientoTarjeOpera() {
        return fechaVencimientoTarjeOpera;
    }

    public void setFechaVencimientoTarjeOpera(Date fechaVencimientoTarjeOpera) {
        this.fechaVencimientoTarjeOpera = fechaVencimientoTarjeOpera;
    }

    public Integer getNumeroTarjetaOperacion() {
        return numeroTarjetaOperacion;
    }

    public void setNumeroTarjetaOperacion(Integer numeroTarjetaOperacion) {
        this.numeroTarjetaOperacion = numeroTarjetaOperacion;
    }

    public String getCampoCambio() {
        return campoCambio;
    }

    public void setCampoCambio(String campoCambio) {
        this.campoCambio = campoCambio;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public RadioAccion getRadioAccion() {
        return radioAccion;
    }

    public void setRadioAccion(RadioAccion radioAccion) {
        this.radioAccion = radioAccion;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public ClaseVehiculo getClaseVehiculo() {
        return claseVehiculo;
    }

    public void setClaseVehiculo(ClaseVehiculo claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
