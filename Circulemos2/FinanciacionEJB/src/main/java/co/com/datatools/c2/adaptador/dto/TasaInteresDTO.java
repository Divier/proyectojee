package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author luis.forero
 */
public class TasaInteresDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal porcentajeInteresDiario;
    private BigDecimal porcentajeTasaInteres;

    /**
     * 
     */
    public TasaInteresDTO() {
        // TODO Auto-generated constructor stub
    }

    public BigDecimal getPorcentajeInteresDiario() {
        return porcentajeInteresDiario;
    }

    public void setPorcentajeInteresDiario(BigDecimal porcentajeInteresDiario) {
        this.porcentajeInteresDiario = porcentajeInteresDiario;
    }

    public BigDecimal getPorcentajeTasaInteres() {
        return porcentajeTasaInteres;
    }

    public void setPorcentajeTasaInteres(BigDecimal porcentajeTasaInteres) {
        this.porcentajeTasaInteres = porcentajeTasaInteres;
    }

}
