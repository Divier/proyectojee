package co.com.datatools.c2.ws.utilidades.enumeracion;

import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILogueable;
import co.com.datatools.c2.util.SearchableEnumeration;
import co.com.datatools.c2.util.log.LogReplicarPago;
import co.com.datatools.c2.ws.comparendo.utilidades.LogGestionCobroSAC;
import co.com.datatools.c2.ws.comparendo.utilidades.LogRecibirComparendo;
import co.com.datatools.c2.ws.comparendo.utilidades.LogServiciosSogit;
import co.com.datatools.c2.ws.comparendo.utilidades.LogSolicitarNumeroComparendo;

/**
 * Enumera todos los servicios web expuestos en el sistema
 * 
 * @author luis.forero(2015-11-17)<br>
 *         luis.forero(mod 2015-11-27) <br>
 *         giovanni.velandia(mod 2016-11-09)
 */
public enum EnumServicioWS implements SearchableEnumeration<String> {

    COMPARENDOS_COLOMBIA(1, "ServicioRecibirComparendoService", "webServices/recibir-comparendo",
            EnumLogSistema.RECIBIR_COMPARENDO_WS, LogRecibirComparendo.class), //
    SOLICITAR_NUMERO_COMPARENDO(2, "SolicitarNumeroComparendoService", "webServices/solicitar-numero-comparendo",
            EnumLogSistema.SOLICITUD_OCN, LogSolicitarNumeroComparendo.class), //
    REPLICAR_PAGO(5, "ServicioReplicarPagoService", "webServices/replicar-pago", EnumLogSistema.REPLICAR_PAGO_WS,
            LogReplicarPago.class), //
    GESTION_COBRO_SAC(2, "ServicioGestionCobroService", "webServices/servicio-gestion-cobro-sac",
            EnumLogSistema.GESTION_COBRO_SAC, LogGestionCobroSAC.class), //
    SERVICIOS_SOGIT(13, "ServiciosSogitService", "webServices/servicios-sogit", EnumLogSistema.SERVICIOS_SOGIT_WS,
            LogServiciosSogit.class), //
    ;

    private Integer idServicio;
    private String nombreServicio;
    private String nombreRecursoServicio;
    private EnumLogSistema enumLogSistema;
    private Class<? extends ILogueable> claseLogServicio;

    private EnumServicioWS(Integer idServicio, String nombreServicio, String nombreRecursoServicio,
            EnumLogSistema enumLogSistema, Class<? extends ILogueable> claseLogServicio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.nombreRecursoServicio = nombreRecursoServicio;
        this.enumLogSistema = enumLogSistema;
        this.claseLogServicio = claseLogServicio;
    }

    @Override
    public String getValue() {
        return nombreServicio;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public String getNombreRecursoServicio() {
        return nombreRecursoServicio;
    }

    public EnumLogSistema getEnumLogSistema() {
        return enumLogSistema;
    }

    public Class<? extends ILogueable> getClaseLogServicio() {
        return claseLogServicio;
    }

}
