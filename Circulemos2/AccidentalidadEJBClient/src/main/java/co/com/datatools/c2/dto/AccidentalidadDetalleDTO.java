package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

public class AccidentalidadDetalleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private AccidentalidadDTO accidentalidad;
    private List<DetalleAccidentalidadDTO> lsDetalleAccidentalidad;
    private List<ArchivoAccidentalidadDTO> adjuntos;

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

    public List<ArchivoAccidentalidadDTO> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<ArchivoAccidentalidadDTO> adjuntos) {
        this.adjuntos = adjuntos;
    }

}
