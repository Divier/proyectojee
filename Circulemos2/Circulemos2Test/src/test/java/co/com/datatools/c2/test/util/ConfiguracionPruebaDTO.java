package co.com.datatools.c2.test.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO para probar el consumo de una configuracion del caso de uso ADM_066
 * 
 * @author Felipe.Martinez
 */
public class ConfiguracionPruebaDTO implements Serializable {
    private static final long serialVersionUID = 2997266356372063157L;

    private Date inicialEntrada;
    private Date finalEntrada;
    private BigDecimal numeroSalida;
    private String cadenaSalida;

    public Date getInicialEntrada() {
        return inicialEntrada;
    }

    public void setInicialEntrada(Date inicialEntrada) {
        this.inicialEntrada = inicialEntrada;
    }

    public Date getFinalEntrada() {
        return finalEntrada;
    }

    public void setFinalEntrada(Date finalEntrada) {
        this.finalEntrada = finalEntrada;
    }

    public BigDecimal getNumeroSalida() {
        return numeroSalida;
    }

    public void setNumeroSalida(BigDecimal numeroSalida) {
        this.numeroSalida = numeroSalida;
    }

    public String getCadenaSalida() {
        return cadenaSalida;
    }

    public void setCadenaSalida(String cadenaSalida) {
        this.cadenaSalida = cadenaSalida;
    }

}
