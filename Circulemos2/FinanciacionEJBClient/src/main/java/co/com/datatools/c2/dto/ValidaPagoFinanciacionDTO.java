package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * @author divier.casas
 * 
 */
public class ValidaPagoFinanciacionDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer cantFinanValidadas;
    private Integer cantFinanAnuladas;

    public Integer getCantFinanValidadas() {
        return cantFinanValidadas;
    }

    public void setCantFinanValidadas(Integer cantFinanValidadas) {
        this.cantFinanValidadas = cantFinanValidadas;
    }

    public Integer getCantFinanAnuladas() {
        return cantFinanAnuladas;
    }

    public void setCantFinanAnuladas(Integer cantFinanAnuladas) {
        this.cantFinanAnuladas = cantFinanAnuladas;
    }
}