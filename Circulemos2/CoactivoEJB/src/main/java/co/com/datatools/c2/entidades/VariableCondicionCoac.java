package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "variable_condicion_coac")
public class VariableCondicionCoac implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_variable_condicion_coac")
    private Integer id;

    @Column(name = "nombre_variable")
    private String nombre;

    @Column(name = "orden")
    private Integer orden;

    @Lob
    @Column(name = "fuente_data")
    private String fuenteData;

    @JoinColumn(name = "codigo_condicion", referencedColumnName = "codigo_condicion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CondicionCoactivo condicionCoactivo;

    @JoinColumn(name = "codigo_tipo_variable", referencedColumnName = "codigo_tipo_variable")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoVariableCoactivo tipoVariableCoactivo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variableCondicionCoac", fetch = FetchType.LAZY)
    private List<ValorCondicionCoactivo> lstValorCondicionCoactivo;

    public VariableCondicionCoac() {
    }

    public VariableCondicionCoac(Integer id) {
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getFuenteData() {
        return fuenteData;
    }

    public void setFuenteData(String fuenteData) {
        this.fuenteData = fuenteData;
    }

    public CondicionCoactivo getCondicionCoactivo() {
        return condicionCoactivo;
    }

    public void setCondicionCoactivo(CondicionCoactivo condicionCoactivo) {
        this.condicionCoactivo = condicionCoactivo;
    }

    public TipoVariableCoactivo getTipoVariableCoactivo() {
        return tipoVariableCoactivo;
    }

    public void setTipoVariableCoactivo(TipoVariableCoactivo tipoVariableCoactivo) {
        this.tipoVariableCoactivo = tipoVariableCoactivo;
    }

    public List<ValorCondicionCoactivo> getLstValorCondicionCoactivo() {
        return lstValorCondicionCoactivo;
    }

    public void setLstValorCondicionCoactivo(List<ValorCondicionCoactivo> lstValorCondicionCoactivo) {
        this.lstValorCondicionCoactivo = lstValorCondicionCoactivo;
    }

}
