package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

public class ConsIncrInfrCompDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idInfraccion;
    private Integer idTipoServicio;
    private Double incremento;

    public ConsIncrInfrCompDTO() {
    }

    public int getIdInfraccion() {
        return idInfraccion;
    }

    public void setIdInfraccion(int idInfraccion) {
        this.idInfraccion = idInfraccion;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Double getIncremento() {
        return incremento;
    }

    public void setIncremento(Double incremento) {
        this.incremento = incremento;
    }

}
