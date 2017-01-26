package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

import co.com.datatools.c2.enumeracion.EnumCamposDetalleComparendo;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;

/**
 * DTO que se encarga de capturarme un error en el procesamiento de los campos de un comparendo que me llegan por el procesaComparendo recibidos por
 * el metodo recibirComparendo
 * 
 * @author giovanni.velandia
 * 
 */
public class ErrorCampoComparendoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private EnumCamposDetalleComparendo enumCamposDetalleComparendo;
    private EnumErrorProcesamiento enumErrorProcesamiento;

    public EnumCamposDetalleComparendo getEnumCamposDetalleComparendo() {
        return enumCamposDetalleComparendo;
    }

    public void setEnumCamposDetalleComparendo(EnumCamposDetalleComparendo enumCamposDetalleComparendo) {
        this.enumCamposDetalleComparendo = enumCamposDetalleComparendo;
    }

    public EnumErrorProcesamiento getEnumErrorProcesamiento() {
        return enumErrorProcesamiento;
    }

    public void setEnumErrorProcesamiento(EnumErrorProcesamiento enumErrorProcesamiento) {
        this.enumErrorProcesamiento = enumErrorProcesamiento;
    }

}
