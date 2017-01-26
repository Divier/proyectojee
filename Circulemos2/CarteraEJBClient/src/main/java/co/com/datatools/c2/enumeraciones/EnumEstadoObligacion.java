package co.com.datatools.c2.enumeraciones;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoObligacion implements SearchableEnumeration<Integer> {

    PENDIENTE_POR_ACTIVAR(1, "1", EnumTipoEstadoObligacion.INICIAL), //
    ACTIVO(2, "2", EnumTipoEstadoObligacion.INTERMEDIO), //
    PAGADO(3, "3", EnumTipoEstadoObligacion.FINAL), //
    FINANCIADO(4, "4", EnumTipoEstadoObligacion.FINAL), //
    ANULADA(5, "5", EnumTipoEstadoObligacion.FINAL), //
    PREFINANCIADA(6, "6", EnumTipoEstadoObligacion.INTERMEDIO), //
    PAGADO_FINANCIACION(7, "7", EnumTipoEstadoObligacion.FINAL), //
    COACTIVO(8, "8", EnumTipoEstadoObligacion.INTERMEDIO), //
    ;

    /**
     * El tipo de estado de obligacion clasifica la obligacion entre inicial, intermedio o final
     * 
     */
    public static enum EnumTipoEstadoObligacion {
        INICIAL, INTERMEDIO, FINAL;
    }

    private String codigo;
    private Integer id;
    private EnumTipoEstadoObligacion tipoEstadoObligacion;

    EnumEstadoObligacion(int id, String codigo, EnumTipoEstadoObligacion tipoEstadoObligacion) {
        this.id = id;
        this.codigo = codigo;
        this.tipoEstadoObligacion = tipoEstadoObligacion;
    }

    public EnumTipoEstadoObligacion getTipoEstadoObligacion() {
        return tipoEstadoObligacion;
    }

    public void setTipoEstadoObligacion(EnumTipoEstadoObligacion tipoEstadoObligacion) {
        this.tipoEstadoObligacion = tipoEstadoObligacion;
    }

    /**
     * Estados de cartera iniciales o intermedios
     * 
     * @return Lista de {@link EnumEstadoObligacion#codigo}
     */
    public static List<Integer> obtenerEstadosNoFinales() {
        List<Integer> idEstadoObligacionL = new ArrayList<>();
        for (EnumEstadoObligacion estadoObligacion : EnumEstadoObligacion.values()) {
            if (estadoObligacion.getTipoEstadoObligacion().equals(EnumEstadoObligacion.EnumTipoEstadoObligacion.INICIAL)
                    || estadoObligacion.getTipoEstadoObligacion()
                            .equals(EnumEstadoObligacion.EnumTipoEstadoObligacion.INTERMEDIO)) {
                idEstadoObligacionL.add(estadoObligacion.getValue());
            }
        }
        return idEstadoObligacionL;
    }

    @Override
    public Integer getValue() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    /**
     * Busca la enumeracion por valor
     * 
     * @param id
     * @param ws
     * @return EnumEstadoObligacion
     * @author julio.pinzon 2016-08-17
     */
    public static EnumEstadoObligacion encontrarPorId(Integer id) {
        EnumEstadoObligacion estado = null;
        for (EnumEstadoObligacion respuesta : EnumEstadoObligacion.values()) {
            if (respuesta.getValue().equals(id)) {
                estado = respuesta;
                break;
            }
        }
        return estado;
    }

}
