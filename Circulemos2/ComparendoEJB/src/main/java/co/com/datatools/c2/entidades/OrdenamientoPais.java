package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "ordenamiento_pais")
@NamedQueries({ @NamedQuery(name = "OrdenamientoPais.findAll", query = "SELECT o FROM OrdenamientoPais o"),
/*
 * @NamedQuery( name = "OrdenamientoPais.consultarOrdenamientosPaisXPais", query =
 * "SELECT op FROM OrdenamientoPais AS op WHERE op.pais.id = :pIdPais ORDER BY op.nombre")
 */})
public class OrdenamientoPais implements EntidadC2, EntidadCatalogoC2 {
    private static final long serialVersionUID = 1L;

    public static final String CONSULTA_ORDENAMIENTOS_PAIS_X_PAIS = "OrdenamientoPais.consultarOrdenamientosPaisXPais";

    @Id
    @Basic(optional = false)
    @Column(name = "id_ordenamiento_pais")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sigla")
    private String sigla;

    @OneToMany(mappedBy = "ordenamientoPais")
    private List<ConfiguracionInfraccion> configuracionInfraccions;

    public OrdenamientoPais() {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<ConfiguracionInfraccion> getConfiguracionInfraccions() {
        return configuracionInfraccions;
    }

    public void setConfiguracionInfraccions(List<ConfiguracionInfraccion> configuracionInfraccions) {
        this.configuracionInfraccions = configuracionInfraccions;
    }

    @Override
    public Boolean getActivo() {
        return this.estado;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.estado = activo;
    }

}