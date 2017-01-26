package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the bloqueo_comparendo database table.
 * 
 */
@Entity
@Table(name = "bloqueo_comparendo")
@NamedQuery(name = "BloqueoComparendo.findAll", query = "SELECT b FROM BloqueoComparendo b")
public class BloqueoComparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloqueo_comparendo")
    private Long id;

    @Column(name = "numero_comparendo")
    private String numeroComparendo;

    @Column(name = "codigo_origen")
    private Integer codigoOrigen;

    @Column(name = "codigo_infraccion")
    private String codigoInfraccion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_reporte")
    private Date fechaReporte;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_infraccion")
    private Date fechaInfraccion;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_infraccion")
    private Date horaInfraccion;

    @Column(name = "placa_vehiculo")
    private String placaVehiculo;

    @Column(name = "id_tipo_identificacion_infract")
    private Integer idTipoIdentificacionInfractor;

    @Column(name = "numero_identificacion_infract")
    private String numeroIdentificacionInfractor;

    @Column(name = "nombre1_infractor")
    private String nombre1Infractor;

    @Column(name = "nombre2_infractor")
    private String nombre2Infractor;

    @Column(name = "apellido1_infractor")
    private String apellido1Infractor;

    @Column(name = "apellido2_infractor")
    private String apellido2Infractor;

    // bi-directional many-to-one association to DetalleBloqueante
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "bloqueoComparendo")
    private List<DetalleBloqueo> detalleBloqueantes;

    // bi-directional many-to-one association to UsuarioPersona
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioPersona usuarioPersona;

    public BloqueoComparendo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Integer getCodigoOrigen() {
        return codigoOrigen;
    }

    public void setCodigoOrigen(Integer codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Date getFechaInfraccion() {
        return fechaInfraccion;
    }

    public void setFechaInfraccion(Date fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public Date getHoraInfraccion() {
        return horaInfraccion;
    }

    public void setHoraInfraccion(Date horaInfraccion) {
        this.horaInfraccion = horaInfraccion;
    }

    public Integer getIdTipoIdentificacionInfractor() {
        return idTipoIdentificacionInfractor;
    }

    public void setIdTipoIdentificacionInfractor(Integer idTipoIdentificacionInfractor) {
        this.idTipoIdentificacionInfractor = idTipoIdentificacionInfractor;
    }

    public String getNumeroIdentificacionInfractor() {
        return numeroIdentificacionInfractor;
    }

    public void setNumeroIdentificacionInfractor(String numeroIdentificacionInfractor) {
        this.numeroIdentificacionInfractor = numeroIdentificacionInfractor;
    }

    public String getNombre1Infractor() {
        return nombre1Infractor;
    }

    public void setNombre1Infractor(String nombre1Infractor) {
        this.nombre1Infractor = nombre1Infractor;
    }

    public String getNombre2Infractor() {
        return nombre2Infractor;
    }

    public void setNombre2Infractor(String nombre2Infractor) {
        this.nombre2Infractor = nombre2Infractor;
    }

    public String getApellido1Infractor() {
        return apellido1Infractor;
    }

    public void setApellido1Infractor(String apellido1Infractor) {
        this.apellido1Infractor = apellido1Infractor;
    }

    public String getApellido2Infractor() {
        return apellido2Infractor;
    }

    public void setApellido2Infractor(String apellido2Infractor) {
        this.apellido2Infractor = apellido2Infractor;
    }

    public List<DetalleBloqueo> getDetalleBloqueantes() {
        return detalleBloqueantes;
    }

    public void setDetalleBloqueantes(List<DetalleBloqueo> detalleBloqueantes) {
        this.detalleBloqueantes = detalleBloqueantes;
    }

    public UsuarioPersona getUsuarioPersona() {
        return usuarioPersona;
    }

    public void setUsuarioPersona(UsuarioPersona usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

}