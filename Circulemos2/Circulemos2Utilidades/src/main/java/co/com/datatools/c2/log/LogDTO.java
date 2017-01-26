/**
 * 
 */
package co.com.datatools.c2.log;

import java.io.Serializable;

import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumTipoLog;

/**
 * @author julio.pinzon
 * 
 */
public class LogDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Tipo de Log que configurado.
     */
    private EnumTipoLog tipoLog;

    /**
     * Contiene el log del sistema configurado.
     */
    private EnumLogSistema tipoLogSistema;

    /**
     * Contiene la ubicación donde se almacena el log.
     */
    private String ubicacion;

    public EnumTipoLog getTipoLog() {
        return tipoLog;
    }

    public void setTipoLog(EnumTipoLog tipoLog) {
        this.tipoLog = tipoLog;
    }

    public EnumLogSistema getTipoLogSistema() {
        return tipoLogSistema;
    }

    public void setTipoLogSistema(EnumLogSistema tipoLogSistema) {
        this.tipoLogSistema = tipoLogSistema;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}
