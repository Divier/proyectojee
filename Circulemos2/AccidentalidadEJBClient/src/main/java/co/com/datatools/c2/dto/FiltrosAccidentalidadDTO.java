package co.com.datatools.c2.dto;

import java.io.Serializable;

public class FiltrosAccidentalidadDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String consecutivo;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

}
