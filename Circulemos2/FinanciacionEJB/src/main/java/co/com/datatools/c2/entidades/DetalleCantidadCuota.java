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

/**
 * 
 * @author felipe.martinez
 */
@Entity
@Table(name = "detalle_cantidad_cuota")
public class DetalleCantidadCuota implements co.com.datatools.util.dto.EntidadC2 {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_cantidad_couta")
    private Integer id;

    @Column(name = "valor_minimo_financiar")
    private BigDecimal valorMinimoFinanciar;

    @Column(name = "valor_maximo_financiar")
    private BigDecimal valorMaximoFinanciar;

    @Column(name = "cantidad_minima_coutas")
    private Integer cantidadMinimaCoutas;

    @Column(name = "cantidad_maxima_couta")
    private Integer cantidadMaximaCouta;

    @JoinColumn(name = "id_variable_condicion_finan", referencedColumnName = "id_variable_condicion_finan")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VariableCondicionFinan variableCondicionFinan;

    @JoinColumn(name = "id_configuracion_financiacion", referencedColumnName = "id_configuracion_financiacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ConfiguracionFinanciacion configuracionFinanciacion;

    public DetalleCantidadCuota() {
    }

    public DetalleCantidadCuota(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCantidadMinimaCoutas() {
        return cantidadMinimaCoutas;
    }

    public void setCantidadMinimaCoutas(Integer cantidadMinimaCoutas) {
        this.cantidadMinimaCoutas = cantidadMinimaCoutas;
    }

    public Integer getCantidadMaximaCouta() {
        return cantidadMaximaCouta;
    }

    public void setCantidadMaximaCouta(Integer cantidadMaximaCouta) {
        this.cantidadMaximaCouta = cantidadMaximaCouta;
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

}
