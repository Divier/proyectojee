package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "normatividad")
@NamedQueries({
        @NamedQuery(name = "Normatividad.findAll", query = "SELECT n FROM Normatividad n"),
        @NamedQuery(
                name = "Normatividad.consultarNormatividadesXOrganismo",
                query = "SELECT nor FROM Normatividad AS nor JOIN nor.organismoTransitoList AS org WHERE org.codigoOrganismo = :pCodOrg ORDER BY nor.nombre") })
public class Normatividad implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    public static final String CONSULTAR_NORMATIVIDADES_X_ORGANISMO = "Normatividad.consultarNormatividadesXOrganismo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_normatividad")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "codigo_normatividad")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "id_ordenamiento_pais", referencedColumnName = "id_ordenamiento_pais")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrdenamientoPais ordenamientoPais;

    @JoinTable(name = "normatividad_organismo", joinColumns = { @JoinColumn(
            name = "id_normatividad",
            referencedColumnName = "id_normatividad") }, inverseJoinColumns = { @JoinColumn(
            name = "codigo_organismo",
            referencedColumnName = "codigo_organismo") })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<OrganismoTransito> organismoTransitoList;

    public Normatividad() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public OrdenamientoPais getOrdenamientoPais() {
        return ordenamientoPais;
    }

    public void setOrdenamientoPais(OrdenamientoPais ordenamientoPais) {
        this.ordenamientoPais = ordenamientoPais;
    }

    public List<OrganismoTransito> getOrganismoTransitoList() {
        return organismoTransitoList;
    }

    public void setOrganismoTransitoList(List<OrganismoTransito> organismoTransitoList) {
        this.organismoTransitoList = organismoTransitoList;
    }

}
