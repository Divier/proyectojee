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

/**
 * 
 * @author felipe.martinez
 */
@Entity
@Table(name = "valor_condicion_financiacion")
public class ValorCondicionFinanciacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_condicion_financi")
    private Integer id;

    @Column(name = "valor")
    private String valor;

    @JoinColumn(name = "id_configuracion_financiacion", referencedColumnName = "id_configuracion_financiacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ConfiguracionFinanciacion configuracionFinanciacion;

    @JoinColumn(name = "id_variable_condicion_finan", referencedColumnName = "id_variable_condicion_finan")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VariableCondicionFinan variableCondicionFinan;

    public ValorCondicionFinanciacion() {
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

    public ConfiguracionFinanciacion getConfiguracionFinanciacion() {
        return configuracionFinanciacion;
    }

    public void setConfiguracionFinanciacion(ConfiguracionFinanciacion configuracionFinanciacion) {
        this.configuracionFinanciacion = configuracionFinanciacion;
    }

    public VariableCondicionFinan getVariableCondicionFinan() {
        return variableCondicionFinan;
    }

    public void setVariableCondicionFinan(VariableCondicionFinan variableCondicionFinan) {
        this.variableCondicionFinan = variableCondicionFinan;
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
        if (!(obj instanceof ValorCondicionFinanciacion))
            return false;
        ValorCondicionFinanciacion other = (ValorCondicionFinanciacion) obj;
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

}
