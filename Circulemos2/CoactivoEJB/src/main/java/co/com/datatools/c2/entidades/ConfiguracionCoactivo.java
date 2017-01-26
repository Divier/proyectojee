package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "configuracion_coactivo")
public class ConfiguracionCoactivo implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_coactivo")
    private Integer id;

    @Column(name = "nombre_configuracion")
    private String nombre;

    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "configuracionCoactivo",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<ValorCondicionCoactivo> lstValorCondicionCoactivo;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "configuracionCoactivo",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<EstadoCondicionCoactivo> lstEstadoCondicionCoactivo;

    public ConfiguracionCoactivo() {
    }

    public ConfiguracionCoactivo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public List<ValorCondicionCoactivo> getLstValorCondicionCoactivo() {
        return lstValorCondicionCoactivo;
    }

    public void setLstValorCondicionCoactivo(List<ValorCondicionCoactivo> lstValorCondicionCoactivo) {
        this.lstValorCondicionCoactivo = lstValorCondicionCoactivo;
    }

    public List<EstadoCondicionCoactivo> getLstEstadoCondicionCoactivo() {
        return lstEstadoCondicionCoactivo;
    }

    public void setLstEstadoCondicionCoactivo(List<EstadoCondicionCoactivo> lstEstadoCondicionCoactivo) {
        this.lstEstadoCondicionCoactivo = lstEstadoCondicionCoactivo;
    }

}
