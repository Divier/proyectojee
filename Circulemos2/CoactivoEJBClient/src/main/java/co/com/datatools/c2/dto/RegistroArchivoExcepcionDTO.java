package co.com.datatools.c2.dto;

import java.io.Serializable;

import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * 
 * @author giovanni.velandia
 *
 */
public class RegistroArchivoExcepcionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArchivoTransportableDTO archivoTransportableDTO;
    private int consecutivo;
    private boolean falloExcepcion;

    public ArchivoTransportableDTO getArchivoTransportableDTO() {
        return archivoTransportableDTO;
    }

    public void setArchivoTransportableDTO(ArchivoTransportableDTO archivoTransportableDTO) {
        this.archivoTransportableDTO = archivoTransportableDTO;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public boolean isFalloExcepcion() {
        return falloExcepcion;
    }

    public void setFalloExcepcion(boolean falloExcepcion) {
        this.falloExcepcion = falloExcepcion;
    }

}
