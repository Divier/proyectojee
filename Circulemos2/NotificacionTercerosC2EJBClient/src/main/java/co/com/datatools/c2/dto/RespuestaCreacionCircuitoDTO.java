package co.com.datatools.c2.dto;

import java.io.Serializable;

public class RespuestaCreacionCircuitoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String idCircuito;
    private Long idLogEjecucionWS;

    public String getIdCircuito() {
        return idCircuito;
    }

    public void setIdCircuito(String idCircuito) {
        this.idCircuito = idCircuito;
    }

    public Long getIdLogEjecucionWS() {
        return idLogEjecucionWS;
    }

    public void setIdLogEjecucionWS(Long idLogEjecucionWS) {
        this.idLogEjecucionWS = idLogEjecucionWS;
    }
}