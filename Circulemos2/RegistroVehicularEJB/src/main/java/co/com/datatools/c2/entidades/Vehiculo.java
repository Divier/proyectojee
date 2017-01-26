package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author dixon.alvarez
 * @version Iteracion 6
 */
@Entity
@Table(name = "vehiculo")
@NamedQueries({ @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v") })
public class Vehiculo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private Long id;

    @Basic(optional = false)
    @Column(name = "licencia_transito")
    private String licenciaTransito;

    @Basic(optional = false)
    @Column(name = "numero_motor")
    private String numeroMotor;

    @Basic(optional = false)
    @Column(name = "modelo")
    private Integer modelo;

    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;

    @Column(name = "fecha_expedicion_tarjeta_opera")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionTarjeOpera;

    @Column(name = "fecha_vencimiento_tarje_opera")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoTarjeOpera;

    @Column(name = "numero_tarjeta_opera")
    private Integer numeroTarjetaOpera;

    @JoinColumn(name = "id_color", referencedColumnName = "id_color")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Color color;

    @JoinColumn(name = "id_linea_vehiculo", referencedColumnName = "id_linea_vehiculo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LineaVehiculo linea;

    @JoinColumn(name = "id_modalidad", referencedColumnName = "id_modalidad")
    @ManyToOne(fetch = FetchType.LAZY)
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<PropietarioVehiculo> propietarioVehiculoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<EmpresaVehiculo> empresaVehiculoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<HistoricoVehiculo> historicoVehiculoList;

    public Vehiculo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenciaTransito() {
        return licenciaTransito;
    }

    public void setLicenciaTransito(String licenciaTransito) {
        this.licenciaTransito = licenciaTransito;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public Integer getNumeroTarjetaOpera() {
        return numeroTarjetaOpera;
    }

    public void setNumeroTarjetaOpera(Integer numeroTarjetaOpera) {
        this.numeroTarjetaOpera = numeroTarjetaOpera;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LineaVehiculo getLinea() {
        return linea;
    }

    public void setLinea(LineaVehiculo linea) {
        this.linea = linea;
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

    public List<PropietarioVehiculo> getPropietarioVehiculoList() {
        return propietarioVehiculoList;
    }

    public void setPropietarioVehiculoList(List<PropietarioVehiculo> propietarioVehiculoList) {
        this.propietarioVehiculoList = propietarioVehiculoList;
    }

    public List<EmpresaVehiculo> getEmpresaVehiculoList() {
        return empresaVehiculoList;
    }

    public void setEmpresaVehiculoList(List<EmpresaVehiculo> empresaVehiculoList) {
        this.empresaVehiculoList = empresaVehiculoList;
    }

    public List<HistoricoVehiculo> getHistoricoVehiculoList() {
        return historicoVehiculoList;
    }

    public void setHistoricoVehiculoList(List<HistoricoVehiculo> historicoVehiculoList) {
        this.historicoVehiculoList = historicoVehiculoList;
    }
}