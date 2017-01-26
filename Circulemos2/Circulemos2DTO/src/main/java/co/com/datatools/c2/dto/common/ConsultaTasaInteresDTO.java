package co.com.datatools.c2.dto.common;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO utilitario para consultar las tasas de interes- CU_CIR20_DAT_CAR_032
 * 
 * @author divier.casas
 * 
 */
public class ConsultaTasaInteresDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer claseInteres;
    private Date fechaAplicacion;
    private Integer periodoInteres;

    public ConsultaTasaInteresDTO() {
        super();
    }

    public Integer getClaseInteres() {
        return claseInteres;
    }

    public void setClaseInteres(Integer claseInteres) {
        this.claseInteres = claseInteres;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public Integer getPeriodoInteres() {
        return periodoInteres;
    }

    public void setPeriodoInteres(Integer periodoInteres) {
        this.periodoInteres = periodoInteres;
    }
}