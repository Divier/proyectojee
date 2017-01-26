package co.com.datatools.c2.dto.formularios;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * DTO util para la asignacion de rangos
 * 
 * @author rodrigo.cruz
 * 
 */
public class AsignacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<CantidadRangoDTO> cantidadRango;
    private DetalleCambioEstadoDTO detalleCambioEstado;
    private ArchivoTransportableDTO archivoSoporte;

    public List<CantidadRangoDTO> getCantidadRango() {
        return cantidadRango;
    }

    public void setCantidadRango(List<CantidadRangoDTO> cantidadRango) {
        this.cantidadRango = cantidadRango;
    }

    public DetalleCambioEstadoDTO getDetalleCambioEstado() {
        return detalleCambioEstado;
    }

    public void setDetalleCambioEstado(DetalleCambioEstadoDTO detalleCambioEstado) {
        this.detalleCambioEstado = detalleCambioEstado;
    }

    public ArchivoTransportableDTO getArchivoSoporte() {
        return archivoSoporte;
    }

    public void setArchivoSoporte(ArchivoTransportableDTO archivoSoporte) {
        this.archivoSoporte = archivoSoporte;
    }

}
