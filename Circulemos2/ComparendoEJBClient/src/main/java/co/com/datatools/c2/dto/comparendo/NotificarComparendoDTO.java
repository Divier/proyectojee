package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

/**
 * CU_CIR20_DAT_COM_054: DTO utilitario
 * 
 * @author rodrigo.cruz
 * 
 */
public class NotificarComparendoDTO {

    private Date fechaNotificacion;
    private int idTipoNotificacion;
    private int idActividadTrazabilidad;
    private boolean activaCartera;

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public int getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(int idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    public int getIdActividadTrazabilidad() {
        return idActividadTrazabilidad;
    }

    public void setIdActividadTrazabilidad(int idActividadTrazabilidad) {
        this.idActividadTrazabilidad = idActividadTrazabilidad;
    }

    public boolean isActivaCartera() {
        return activaCartera;
    }

    public void setActivaCartera(boolean activaCartera) {
        this.activaCartera = activaCartera;
    }

}
