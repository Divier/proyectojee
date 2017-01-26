package co.com.datatools.c2.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the solicitud_orden_compa_nacio database table.
 * 
 */
@Entity
@Table(name = "solicitud_orden_compa_nacio")
@NamedQuery(name = "SolicitudOrdenCompaNacio.findAll", query = "SELECT s FROM SolicitudOrdenCompaNacio s")
public class SolicitudOrdenCompaNacio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_orden_compa_nacio")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_consumo")
    private Date fechaConsumo;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_imposicion")
    private Date fechaImposicion;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Basic(optional = false)
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_imposicion")
    private Date horaImposicion;

    @Basic(optional = false)
    @Column(name = "id_agente")
    private Integer idAgente;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "identificacion_vehiculo")
    private String identificacionVehiculo;

    @Basic(optional = false)
    @Column(name = "numero_comparendo")
    private String numeroComparendo;

    @Basic(optional = false)
    @Column(name = "placa_agente")
    private String placaAgente;

    @Column(name = "placa_vehiculo")
    private String placaVehiculo;

    public SolicitudOrdenCompaNacio() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaConsumo() {
        return this.fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public Date getFechaImposicion() {
        return this.fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getHoraImposicion() {
        return this.horaImposicion;
    }

    public void setHoraImposicion(Date horaImposicion) {
        this.horaImposicion = horaImposicion;
    }

    public int getIdAgente() {
        return this.idAgente;
    }

    public void setIdAgente(int idAgente) {
        this.idAgente = idAgente;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdentificacionVehiculo() {
        return this.identificacionVehiculo;
    }

    public void setIdentificacionVehiculo(String identificacionVehiculo) {
        this.identificacionVehiculo = identificacionVehiculo;
    }

    public String getNumeroComparendo() {
        return this.numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getPlacaAgente() {
        return this.placaAgente;
    }

    public void setPlacaAgente(String placaAgente) {
        this.placaAgente = placaAgente;
    }

    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

}