package co.com.datatools.c2.dto.common.cartera;

import java.io.Serializable;
import java.util.Date;

import co.com.datatools.c2.enumeraciones.EnumActividadCartera;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;

/**
 * DTO utilitario para cambiar estado de cartera - CU_CIR20_DAT_CAR_031
 * 
 * @author rodrigo.cruz
 * 
 */
public class CambioEstadoCarteraDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date fechaCambio;
    private EnumActividadCartera actividadCartera;
    private long idCartera;
    private EnumEstadoObligacion estadoObligacion;

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(long idCartera) {
        this.idCartera = idCartera;
    }

    public EnumActividadCartera getActividadCartera() {
        return actividadCartera;
    }

    public void setActividadCartera(EnumActividadCartera actividadCartera) {
        this.actividadCartera = actividadCartera;
    }

    public EnumEstadoObligacion getEstadoObligacion() {
        return estadoObligacion;
    }

    public void setEstadoObligacion(EnumEstadoObligacion estadoObligacion) {
        this.estadoObligacion = estadoObligacion;
    }
}