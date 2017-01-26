package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.EntidadOficio;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "configuracion_entidad")
public class ConfiguracionEntidad implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_entidad")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad_oficio")
    private EntidadOficio entidadOficio;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "sigla_oficio")
    private String siglaOficio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_entidad")
    private TipoEntidad tipoEntidad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin")
    private Date fechaFin;

    public ConfiguracionEntidad() {
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSiglaOficio() {
        return siglaOficio;
    }

    public void setSiglaOficio(String siglaOficio) {
        this.siglaOficio = siglaOficio;
    }

    public TipoEntidad getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(TipoEntidad tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EntidadOficio getEntidadOficio() {
        return entidadOficio;
    }

    public void setEntidadOficio(EntidadOficio entidadOficio) {
        this.entidadOficio = entidadOficio;
    }

}
