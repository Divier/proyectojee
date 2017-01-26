package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoCargueArchivo implements SearchableEnumeration<Integer> {
    MASIVO_DIRECCION(1, 9, EnumParametro.NUM_MAX_LINEAS_CARGUE_ARCHIVO), //
    MASIVO_TELEFONO(2, 6, EnumParametro.NUM_MAX_LINEAS_CARGUE_ARCHIVO), //
    MASIVO_CORREO(3, 5, EnumParametro.NUM_MAX_LINEAS_CARGUE_ARCHIVO), //
    CONSULTA_UBICABILIDAD(4, 2, EnumParametro.NUM_MAX_LINEAS_CARGUE_ARCHIVO), //
    ANALISIS_CARTERA(5, 1, EnumParametro.NUM_MAX_LINEAS_CARGUE_COACTIVO);

    Integer idTipoCargue;
    Integer numeroColumnas;
    EnumParametro parametroValidacion;

    EnumTipoCargueArchivo(Integer idTipoCargue, Integer numeroColumnas, EnumParametro parametroValidacion) {
        this.idTipoCargue = idTipoCargue;
        this.numeroColumnas = numeroColumnas;
        this.parametroValidacion = parametroValidacion;
    }

    @Override
    public Integer getValue() {
        return idTipoCargue;
    }

    public static EnumTipoCargueArchivo obtenerPorValor(Integer idTipoCargue) {
        EnumTipoCargueArchivo tipoCargue = null;
        for (EnumTipoCargueArchivo tipo : EnumTipoCargueArchivo.values()) {
            if (tipo.getValue().equals(idTipoCargue)) {
                tipoCargue = tipo;
                break;
            }
        }
        return tipoCargue;
    }

    public Integer getNumeroColumnas() {
        return numeroColumnas;
    }

    public EnumParametro getParametroValidacion() {
        return parametroValidacion;
    }

}
