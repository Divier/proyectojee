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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the precoactivo database table.
 * 
 */
@Entity
@Table(name = "bien")
public class Bien implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bien")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_entidad")
    private TipoEntidad tipoEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_bien")
    private TipoBien tipoBien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad_bien")
    private EntidadBien entidadBien;

    @Column(name = "numero_referencia")
    private String numeroReferencia;

    @Column(name = "monto_retenido")
    private BigDecimal montoRetenido;

    @Column(name = "valor_embargado")
    private BigDecimal valorEmbargado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modifiacion")
    private Date fechaModifiacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    public Bien() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoEntidad getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(TipoEntidad tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public TipoBien getTipoBien() {
        return tipoBien;
    }

    public void setTipoBien(TipoBien tipoBien) {
        this.tipoBien = tipoBien;
    }

    public EntidadBien getEntidadBien() {
        return entidadBien;
    }

    public void setEntidadBien(EntidadBien entidadBien) {
        this.entidadBien = entidadBien;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public BigDecimal getMontoRetenido() {
        return montoRetenido;
    }

    public void setMontoRetenido(BigDecimal montoRetenido) {
        this.montoRetenido = montoRetenido;
    }

    public BigDecimal getValorEmbargado() {
        return valorEmbargado;
    }

    public void setValorEmbargado(BigDecimal valorEmbargado) {
        this.valorEmbargado = valorEmbargado;
    }

    public Date getFechaModifiacion() {
        return fechaModifiacion;
    }

    public void setFechaModifiacion(Date fechaModifiacion) {
        this.fechaModifiacion = fechaModifiacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}