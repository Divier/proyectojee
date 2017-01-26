package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "comparendo_vehiculo")
@NamedQuery(name = "ComparendoVehiculo.findAll", query = "SELECT c FROM ComparendoVehiculo c")
public class ComparendoVehiculo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_comparendo_vehiculo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codigo_organismo_matri_vehic")
    private OrganismoTransito organismoMatriVehic;

    @ManyToOne
    @JoinColumn(name = "codigo_organismo_licen_trans")
    private OrganismoTransito organismoLicenciaTransito;

    @Column(name = "identificacion_vehiculo")
    private String identificacionVehiculo;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "numero_licencia_transito")
    private String numeroLicenciaTransito;

    @Column(name = "numero_motor")
    private String numeroMotor;

    @Column(name = "numero_tarjeta_operacion")
    private String numeroTarjetaOperacion;

    @Column(name = "peso_neto")
    private Integer pesoNeto;

    @Column(name = "peso_seco")
    private Integer pesoSeco;

    @Column(name = "placa_vehiculo")
    private String placaVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clase_vehiculo")
    private ClaseVehiculo claseVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_color")
    private Color color;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comparendo_vehiculo")
    private Comparendo comparendo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linea_vehiculo")
    private LineaVehiculo lineaVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca_vehiculo")
    private MarcaVehiculo marcaVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidad")
    private Modalidad modalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_servicio")
    private NivelServicio nivelServicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_radio_accion")
    private RadioAccion radioAccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_servicio")
    private TipoServicio tipoServicio;

    @JoinColumn(name = "codigo_tipo_transporte_pasaj", referencedColumnName = "codigo_tipo_transporte_pasaj")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoTransportePasajero tipoTransPasajero;

    public ComparendoVehiculo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrganismoTransito getOrganismoMatriVehic() {
        return this.organismoMatriVehic;
    }

    public void setOrganismoMatriVehic(OrganismoTransito organismoMatriVehic) {
        this.organismoMatriVehic = organismoMatriVehic;
    }

    public String getIdentificacionVehiculo() {
        return this.identificacionVehiculo;
    }

    public void setIdentificacionVehiculo(String identificacionVehiculo) {
        this.identificacionVehiculo = identificacionVehiculo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroLicenciaTransito() {
        return this.numeroLicenciaTransito;
    }

    public void setNumeroLicenciaTransito(String numeroLicenciaTransito) {
        this.numeroLicenciaTransito = numeroLicenciaTransito;
    }

    public String getNumeroMotor() {
        return this.numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroTarjetaOperacion() {
        return this.numeroTarjetaOperacion;
    }

    public void setNumeroTarjetaOperacion(String numeroTarjetaOperacion) {
        this.numeroTarjetaOperacion = numeroTarjetaOperacion;
    }

    public Integer getPesoNeto() {
        return this.pesoNeto;
    }

    public void setPesoNeto(int pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public Integer getPesoSeco() {
        return this.pesoSeco;
    }

    public void setPesoSeco(int pesoSeco) {
        this.pesoSeco = pesoSeco;
    }

    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public ClaseVehiculo getClaseVehiculo() {
        return this.claseVehiculo;
    }

    public void setClaseVehiculo(ClaseVehiculo claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Comparendo getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public LineaVehiculo getLineaVehiculo() {
        return this.lineaVehiculo;
    }

    public void setLineaVehiculo(LineaVehiculo lineaVehiculo) {
        this.lineaVehiculo = lineaVehiculo;
    }

    public MarcaVehiculo getMarcaVehiculo() {
        return this.marcaVehiculo;
    }

    public void setMarcaVehiculo(MarcaVehiculo marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public Modalidad getModalidad() {
        return this.modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public NivelServicio getNivelServicio() {
        return this.nivelServicio;
    }

    public void setNivelServicio(NivelServicio nivelServicio) {
        this.nivelServicio = nivelServicio;
    }

    public RadioAccion getRadioAccion() {
        return this.radioAccion;
    }

    public void setRadioAccion(RadioAccion radioAccion) {
        this.radioAccion = radioAccion;
    }

    public TipoServicio getTipoServicio() {
        return this.tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public TipoTransportePasajero getTipoTransPasajero() {
        return tipoTransPasajero;
    }

    public void setTipoTransPasajero(TipoTransportePasajero tipoTransPasajero) {
        this.tipoTransPasajero = tipoTransPasajero;
    }

    public void setPesoNeto(Integer pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public void setPesoSeco(Integer pesoSeco) {
        this.pesoSeco = pesoSeco;
    }

    public OrganismoTransito getOrganismoLicenciaTransito() {
        return organismoLicenciaTransito;
    }

    public void setOrganismoLicenciaTransito(OrganismoTransito organismoLicenciaTransito) {
        this.organismoLicenciaTransito = organismoLicenciaTransito;
    }

}