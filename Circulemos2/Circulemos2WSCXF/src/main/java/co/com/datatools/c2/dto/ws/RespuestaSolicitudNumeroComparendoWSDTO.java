package co.com.datatools.c2.dto.ws;

/**
 * Respuesta de web service que contiene información del propietario y vehiculo
 * 
 * @author diego.fonseca
 */
public class RespuestaSolicitudNumeroComparendoWSDTO extends DetalleRespuestaWSDTO {

    private static final long serialVersionUID = 1L;
    private String numeroComparendo;
    private VehiculoWSDTO vehiculoWSDTO;
    private PropietarioVehiculoWSDTO propietarioVehiculoWSDTO;

    public VehiculoWSDTO getVehiculoWSDTO() {
        return vehiculoWSDTO;
    }

    public void setVehiculoWSDTO(VehiculoWSDTO vehiculoWSDTO) {
        this.vehiculoWSDTO = vehiculoWSDTO;
    }

    public PropietarioVehiculoWSDTO getPropietarioVehiculoWSDTO() {
        return propietarioVehiculoWSDTO;
    }

    public void setPropietarioVehiculoWSDTO(PropietarioVehiculoWSDTO propietarioVehiculoWSDTO) {
        this.propietarioVehiculoWSDTO = propietarioVehiculoWSDTO;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

}
