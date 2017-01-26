package co.com.datatools.c2.dto;

import java.io.Serializable;

public class AdjuntosAccidentalidadDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private byte[] archivo;
    private ArchivoAccidentalidadDTO adjuntos;
    private int consecutivo;

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public ArchivoAccidentalidadDTO getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(ArchivoAccidentalidadDTO adjuntos) {
        this.adjuntos = adjuntos;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

}