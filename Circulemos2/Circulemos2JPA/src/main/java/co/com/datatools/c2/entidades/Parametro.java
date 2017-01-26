package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author robert.bautista
 * @version sprint 07
 */
@Entity
@Table(name = "parametro")
@NamedQueries({ @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p") })
public class Parametro implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "codigo_parametro")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nombre_parametro")
    private String nombre;

    @Column(name = "valor_parametro_defecto")
    private String valorDefecto;

    @Column(name = "codigo_unidad_parametro")
    private Integer codigoUnidad;

    @Column(name = "formato")
    private String formato;

    @Basic(optional = false)
    @Column(name = "editable_organismo")
    private Boolean editableOrganismo;

    @JoinColumn(name = "codigo_tipo_variable", referencedColumnName = "codigo_tipo_variable")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoVariable tipoVariable;

    @JoinColumn(name = "codigo_modulo", referencedColumnName = "codigo_modulo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modulo modulo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parametro")
    private List<ParametroOrganismo> parametroOrganismoList;

    public Parametro() {
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

    public String getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public Integer getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(Integer codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Boolean getEditableOrganismo() {
        return editableOrganismo;
    }

    public void setEditableOrganismo(Boolean editableOrganismo) {
        this.editableOrganismo = editableOrganismo;
    }

    public TipoVariable getTipoVariable() {
        return tipoVariable;
    }

    public void setTipoVariable(TipoVariable tipoVariable) {
        this.tipoVariable = tipoVariable;
    }

    public List<ParametroOrganismo> getParametroOrganismoList() {
        return parametroOrganismoList;
    }

    public void setParametroOrganismoList(List<ParametroOrganismo> parametroOrganismoList) {
        this.parametroOrganismoList = parametroOrganismoList;
    }

    public Modulo getModulo() {
        return this.modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

}
