package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

public class DatosAccidentalidadDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private AccidentalidadDTO accidentalidad;
    private List<DetalleAccidentalidadDTO> lsDetalleAccidentalidad;
    private List<AdjuntosAccidentalidadDTO> adjuntos;
    private byte[] capturaInforme;

    public AccidentalidadDTO getAccidentalidad() {
        return accidentalidad;
    }

    public void setAccidentalidad(AccidentalidadDTO accidentalidad) {
        this.accidentalidad = accidentalidad;
    }

    public List<DetalleAccidentalidadDTO> getLsDetalleAccidentalidad() {
        return lsDetalleAccidentalidad;
    }

    public void setLsDetalleAccidentalidad(List<DetalleAccidentalidadDTO> lsDetalleAccidentalidad) {
        this.lsDetalleAccidentalidad = lsDetalleAccidentalidad;
    }

    public List<AdjuntosAccidentalidadDTO> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<AdjuntosAccidentalidadDTO> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public byte[] getCapturaInforme() {
        return capturaInforme;
    }

    public void setCapturaInforme(byte[] capturaInforme) {
        this.capturaInforme = capturaInforme;
    }

}
