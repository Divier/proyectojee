package co.com.datatools.c2.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "condicion_coactivo")
public class CondicionCoactivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo_condicion")
    private Integer codigo;

    @Column(name = "nombre_condicion")
    private String nombre;

    @Column(name = "orden")
    private Integer orden;

    @JoinTable(
            name = "condicion_coactivo_organ",
            joinColumns = { @JoinColumn(name = "codigo_condicion", referencedColumnName = "codigo_condicion") },
            inverseJoinColumns = { @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo") })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<OrganismoTransito> lstCondicionOrganismo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "condicionCoactivo", fetch = FetchType.LAZY)
    private List<VariableCondicionCoac> lstVariablesCondicionCoac;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "condicionCoactivo", fetch = FetchType.LAZY)
    private List<EstadoCondicionCoactivo> lstEstadoCondicionCoactivo;

    public CondicionCoactivo() {
    }

    public CondicionCoactivo(Integer codigo) {
        this.codigo = codigo;
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public List<OrganismoTransito> getLstCondicionOrganismo() {
        if (lstCondicionOrganismo == null) {
            lstCondicionOrganismo = new ArrayList<OrganismoTransito>(0);
        }
        return lstCondicionOrganismo;
    }

    public void setLstCondicionOrganismo(List<OrganismoTransito> lstCondicionOrganismo) {
        this.lstCondicionOrganismo = lstCondicionOrganismo;
    }

    public List<VariableCondicionCoac> getLstVariablesCondicionCoac() {
        return lstVariablesCondicionCoac;
    }

    public void setLstVariablesCondicionCoac(List<VariableCondicionCoac> lstVariablesCondicionCoac) {
        this.lstVariablesCondicionCoac = lstVariablesCondicionCoac;
    }

    public List<EstadoCondicionCoactivo> getLstEstadoCondicionCoactivo() {
        return lstEstadoCondicionCoactivo;
    }

    public void setLstEstadoCondicionCoactivo(List<EstadoCondicionCoactivo> lstEstadoCondicionCoactivo) {
        this.lstEstadoCondicionCoactivo = lstEstadoCondicionCoactivo;
    }

}