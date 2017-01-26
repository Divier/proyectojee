package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "detalle_gaceta")
@NamedQuery(name = "DetalleGaceta.findAll", query = "SELECT d FROM DetalleGaceta d")
public class DetalleGaceta implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_gaceta")
    private Long id;

    @JoinColumn(name = "id_gaceta", referencedColumnName = "id_gaceta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Gaceta gaceta;

    @Column(name = "placa_vehiculo")
    private String placaVehiculo;

    @Column(name = "propietario")
    private String propietario;

    @Column(name = "descripcion_infraccion")
    private String descripcionInfraccion;

    @Column(name = "lugar_infraccion")
    private String lugarInfraccion;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_imposicion")
    private Date fechaImposicion;

    @Column(name = "valor")
    private BigDecimal valor;

    public DetalleGaceta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gaceta getGaceta() {
        return gaceta;
    }

    public void setGaceta(Gaceta gaceta) {
        this.gaceta = gaceta;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public String getLugarInfraccion() {
        return lugarInfraccion;
    }

    public void setLugarInfraccion(String lugarInfraccion) {
        this.lugarInfraccion = lugarInfraccion;
    }

    public Date getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}