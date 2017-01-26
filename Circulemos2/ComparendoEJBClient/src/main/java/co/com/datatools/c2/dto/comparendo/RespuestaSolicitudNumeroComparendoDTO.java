package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

import co.com.datatools.c2.dto.regveh.VehiculoDTO;

public class RespuestaSolicitudNumeroComparendoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String numeroComparendo;
    private VehiculoDTO vehiculo;

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

}
