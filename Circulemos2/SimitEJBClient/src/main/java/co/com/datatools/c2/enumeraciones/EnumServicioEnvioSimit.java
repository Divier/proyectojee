package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.negocio.interfaces.parametrizacion.Mapeable;
import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion utilizada para mapear los tipos de servicios de homologacion para el servicio de envio a simit
 * 
 * @author julio.pinzon
 * 
 */
public enum EnumServicioEnvioSimit implements SearchableEnumeration<Long>, Mapeable {

    TIPO_VEHICULO(1, 1), //
    CLASE_SERVICIO(1, 2), //
    RADIO_ACCION(1, 3), //
    MODALIDAD_TRANSPORTE(1, 4), //
    TRANSPORTE_PASAJEROS(1, 5), //
    TIPO_DOCUMENTO(1, 6), //
    TIPO_INFRACTOR(1, 7), //
    ESTADO_COMPARENDO(1, 8), //
    ;

    private int idServicioHomologacion;
    private long idTipoHomologacion;

    private EnumServicioEnvioSimit(int idServicioHomologacion, int idTipoHomologacion) {
        this.idServicioHomologacion = idServicioHomologacion;
        this.idTipoHomologacion = idTipoHomologacion;
    }

    @Override
    public int getOrigen() {
        return idServicioHomologacion;
    }

    @Override
    public Long getValue() {
        return idTipoHomologacion;
    }

}
