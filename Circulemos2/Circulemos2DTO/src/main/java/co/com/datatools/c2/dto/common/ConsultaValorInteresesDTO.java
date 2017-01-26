package co.com.datatools.c2.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO utilitario para consultar el valor de intereses - CU_CIR20_DAT_CAR_032
 * 
 * @author divier.casas
 * 
 */
public class ConsultaValorInteresesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer claseInteres;
    private Date fechaFinal;
    private Date fechaInicial;
    private Integer periodoInteres;
    private BigDecimal valSujetoAplicacion;

    public ConsultaValorInteresesDTO() {
        super();
    }

    public Integer getClaseInteres() {
        return claseInteres;
    }

    public void setClaseInteres(Integer claseInteres) {
        this.claseInteres = claseInteres;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Integer getPeriodoInteres() {
        return periodoInteres;
    }

    public void setPeriodoInteres(Integer periodoInteres) {
        this.periodoInteres = periodoInteres;
    }

    public BigDecimal getValSujetoAplicacion() {
        return valSujetoAplicacion;
    }

    public void setValSujetoAplicacion(BigDecimal valSujetoAplicacion) {
        this.valSujetoAplicacion = valSujetoAplicacion;
    }
}