package co.com.datatools.c2.entidades;

import java.math.BigDecimal;

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
 * @author luis.forero
 */
@Entity
@Table(name = "detalle_porcentaje_cuota_inici")
public class DetallePorcentajeCuotaInici implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_porce_couta_inici")
    private Integer id;

    @Column(name = "valor_minimo_financiar")
    private BigDecimal valorMinimoFinanciar;

    @Column(name = "valor_maximo_financiar")
    private BigDecimal valorMaximoFinanciar;

    @Column(name = "porcentaje_cuota_inicial")
    private BigDecimal porcentajeCuotaInicial;

    @JoinColumn(name = "id_variable_condicion_finan", referencedColumnName = "id_variable_condicion_finan")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VariableCondicionFinan variableCondicionFinan;

    @JoinColumn(name = "id_configuracion_financiacion", referencedColumnName = "id_configuracion_financiacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ConfiguracionFinanciacion configuracionFinanciacion;

    public DetallePorcentajeCuotaInici() {
    }

    public DetallePorcentajeCuotaInici(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VariableCondicionFinan getVariableCondicionFinan() {
        return variableCondicionFinan;
    }

    public void setVariableCondicionFinan(VariableCondicionFinan variableCondicionFinan) {
        this.variableCondicionFinan = variableCondicionFinan;
    }

    public ConfiguracionFinanciacion getConfiguracionFinanciacion() {
        return configuracionFinanciacion;
    }

    public void setConfiguracionFinanciacion(ConfiguracionFinanciacion configuracionFinanciacion) {
        this.configuracionFinanciacion = configuracionFinanciacion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((porcentajeCuotaInicial == null) ? 0 : porcentajeCuotaInicial.hashCode());
        result = prime * result + ((valorMaximoFinanciar == null) ? 0 : valorMaximoFinanciar.hashCode());
        result = prime * result + ((valorMinimoFinanciar == null) ? 0 : valorMinimoFinanciar.hashCode());
        result = prime * result + ((variableCondicionFinan == null) ? 0 : variableCondicionFinan.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DetallePorcentajeCuotaInici))
            return false;
        DetallePorcentajeCuotaInici other = (DetallePorcentajeCuotaInici) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (porcentajeCuotaInicial == null) {
            if (other.porcentajeCuotaInicial != null)
                return false;
        } else if (!porcentajeCuotaInicial.equals(other.porcentajeCuotaInicial))
            return false;
        if (valorMaximoFinanciar == null) {
            if (other.valorMaximoFinanciar != null)
                return false;
        } else if (!valorMaximoFinanciar.equals(other.valorMaximoFinanciar))
            return false;
        if (valorMinimoFinanciar == null) {
            if (other.valorMinimoFinanciar != null)
                return false;
        } else if (!valorMinimoFinanciar.equals(other.valorMinimoFinanciar))
            return false;
        if (variableCondicionFinan == null) {
            if (other.variableCondicionFinan != null)
                return false;
        } else if (!variableCondicionFinan.equals(other.variableCondicionFinan))
            return false;
        return true;
    }

    public BigDecimal getValorMinimoFinanciar() {
        return valorMinimoFinanciar;
    }

    public void setValorMinimoFinanciar(BigDecimal valorMinimoFinanciar) {
        this.valorMinimoFinanciar = valorMinimoFinanciar;
    }

    public BigDecimal getValorMaximoFinanciar() {
        return valorMaximoFinanciar;
    }

    public void setValorMaximoFinanciar(BigDecimal valorMaximoFinanciar) {
        this.valorMaximoFinanciar = valorMaximoFinanciar;
    }

    public BigDecimal getPorcentajeCuotaInicial() {
        return porcentajeCuotaInicial;
    }

    public void setPorcentajeCuotaInicial(BigDecimal porcentajeCuotaInicial) {
        this.porcentajeCuotaInicial = porcentajeCuotaInicial;
    }

}
