package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "comparendo_agente")
@NamedQuery(name = "ComparendoAgente.findAll", query = "SELECT c FROM ComparendoAgente c")
public class ComparendoAgente implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comparendo_agente")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cicomparendo")
    private Comparendo comparendo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agente")
    private Agente agente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_identificacion")
    private TipoIdentificacionPersona tipoIdentificacionPersona;

    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @Column(name = "placa")
    private String placa;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "nombre1")
    private String nombre1;

    @Column(name = "nombre2")
    private String nombre2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unificacion_responsable")
    private UnificacionResponsable unificacionResponsable;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    @Column(name = "observaciones_agente")
    private String observacionesAgente;

    public ComparendoAgente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comparendo getComparendo() {
        return comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public TipoIdentificacionPersona getTipoIdentificacionPersona() {
        return tipoIdentificacionPersona;
    }

    public void setTipoIdentificacionPersona(TipoIdentificacionPersona tipoIdentificacionPersona) {
        this.tipoIdentificacionPersona = tipoIdentificacionPersona;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public UnificacionResponsable getUnificacionResponsable() {
        return unificacionResponsable;
    }

    public void setUnificacionResponsable(UnificacionResponsable unificacionResponsable) {
        this.unificacionResponsable = unificacionResponsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getObservacionesAgente() {
        return observacionesAgente;
    }

    public void setObservacionesAgente(String observacionesAgente) {
        this.observacionesAgente = observacionesAgente;
    }

}