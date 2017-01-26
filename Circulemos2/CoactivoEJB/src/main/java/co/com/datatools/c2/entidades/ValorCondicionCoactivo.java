package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "valor_condicion_coactivo")
public class ValorCondicionCoactivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_valor_condicion_coac")
    private Integer id;

    @Column(name = "valor")
    private String valor;

    @JoinColumn(name = "id_configuracion_coactivo", referencedColumnName = "id_configuracion_coactivo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ConfiguracionCoactivo configuracionCoactivo;

    @JoinColumn(name = "id_variable_condicion_coac", referencedColumnName = "id_variable_condicion_coac")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VariableCondicionCoac variableCondicionCoac;

    public ValorCondicionCoactivo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ValorCondicionCoactivo))
            return false;
        ValorCondicionCoactivo other = (ValorCondicionCoactivo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

    public ConfiguracionCoactivo getConfiguracionCoactivo() {
        return configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivo configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

    public VariableCondicionCoac getVariableCondicionCoac() {
        return variableCondicionCoac;
    }

    public void setVariableCondicionCoac(VariableCondicionCoac variableCondicionCoac) {
        this.variableCondicionCoac = variableCondicionCoac;
    }

}
