package co.com.datatools.datatimer.c2.utilidades;

import co.com.datatools.c2.log.ILogueable;

/**
 * @author divier.casas
 *
 */
public class LogConsultaCircuitoENotifica implements ILogueable {

    private static final long serialVersionUID = 1L;
    private Integer solicitudes;
    private Integer cantProcesados;
    private Integer cantNoProcesados;

    public Integer getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(Integer solicitudes) {
        this.solicitudes = solicitudes;
    }

    public Integer getCantProcesados() {
        return cantProcesados;
    }

    public void setCantProcesados(Integer cantProcesados) {
        this.cantProcesados = cantProcesados;
    }

    public Integer getCantNoProcesados() {
        return cantNoProcesados;
    }

    public void setCantNoProcesados(Integer cantNoProcesados) {
        this.cantNoProcesados = cantNoProcesados;
    }
}