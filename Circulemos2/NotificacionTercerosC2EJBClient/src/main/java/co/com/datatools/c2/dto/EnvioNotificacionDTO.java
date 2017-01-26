package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoNotificacion;

public class EnvioNotificacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ConsultarNotificacionesDTO> lsNotificaciones;
    private EnumTipoNotificacion tipoNotificacion;
    private EnumTipoCorreo tipoCorreo;
    private Map<String, Object> variablesMensaje;

    public List<ConsultarNotificacionesDTO> getLsNotificaciones() {
        return lsNotificaciones;
    }

    public void setLsNotificaciones(List<ConsultarNotificacionesDTO> lsNotificaciones) {
        this.lsNotificaciones = lsNotificaciones;
    }

    public EnumTipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(EnumTipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public EnumTipoCorreo getTipoCorreo() {
        return tipoCorreo;
    }

    public void setTipoCorreo(EnumTipoCorreo tipoCorreo) {
        this.tipoCorreo = tipoCorreo;
    }

    public Map<String, Object> getVariablesMensaje() {
        return variablesMensaje;
    }

    public void setVariablesMensaje(Map<String, Object> variablesMensaje) {
        this.variablesMensaje = variablesMensaje;
    }
}