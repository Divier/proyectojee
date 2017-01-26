package co.com.datatools.c2.entidades.comun;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "nombre_via")
@NamedQueries({ @NamedQuery(name = "NombreVia.findAll", query = "SELECT n FROM NombreVia n") })
public class NombreVia implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "codigo_nombre_via")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nombre_via")
    private String nombre;

    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Municipio municipio;

    @JoinColumn(name = "codigo_tipo_via", referencedColumnName = "codigo_tipo_via")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoVia tipoVia;

    public NombreVia() {
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public TipoVia getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(TipoVia tipoVia) {
        this.tipoVia = tipoVia;
    }

}
