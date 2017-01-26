package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.enumeracion.EnumEstadoTransaccion;

public class RespuestaProcesarRecaudoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private List<ErrorInconsistenciaPagoDTO> errores;
    private EnumEstadoTransaccion estadoTransaccion;

    // --- Constructor
    public RespuestaProcesarRecaudoDTO() {
    }

    // --- Getters-Setters
    public List<ErrorInconsistenciaPagoDTO> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorInconsistenciaPagoDTO> errores) {
        this.errores = errores;
    }

    public EnumEstadoTransaccion getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setEstadoTransaccion(EnumEstadoTransaccion estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }

}
