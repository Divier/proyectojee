package co.com.datatools.c2.ws.comparendo.utilidades.enumeracion;

import co.com.datatools.c2.enumeracion.EnumRespuestaWebServices;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que identifica los posibles codigos de respuesta generales del web service
 * 
 * @author luis.forero (2015-11-17)
 */
public enum EnumProcesamientoWS implements SearchableEnumeration<EnumErrorProcesamiento> {
    ERROR_COMPARENDO_RECHAZADO(EnumErrorProcesamiento.RECHAZADO, EnumRespuestaWebServices.ERROR_BLOQUEANTE), // Define un error o rechazo
    REGISTRO_SATISFACTORIO(EnumErrorProcesamiento.REGISTRADO, EnumRespuestaWebServices.PROCESO_EXITOSO), // Define el estado satisfactorio
    COMPARENDO_RECIBIDO_SIN_PROCESAR(EnumErrorProcesamiento.INCONSISTENTE, EnumRespuestaWebServices.INCONSISTENCIA), // Comparendo recibido sin
                                                                                                                     // procesar
    ;
    private EnumErrorProcesamiento codigoOrigen;
    private EnumRespuestaWebServices codigo;

    private EnumProcesamientoWS(EnumErrorProcesamiento codigoOrigen, EnumRespuestaWebServices codigo) {
        this.codigoOrigen = codigoOrigen;
        this.codigo = codigo;
    }

    public EnumRespuestaWebServices getCodigo() {
        return codigo;
    }

    @Override
    public EnumErrorProcesamiento getValue() {
        return codigoOrigen;
    }

}