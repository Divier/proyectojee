package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumErrorCargueCourier implements SearchableEnumeration<Integer>{
	
	DATOS_DE_PROCESO_INCOSISTENTE(1),
	NUMERO_PROCESO_NO_EXISTE_EN_SISTEMA(2),
	PROCESO_NO_ES_COACTIVO(3),
	REGISTRO_NO_TIENE_NOTIFICACION(4),
	REGISTRO_NO_TIENE_FECHA_NOTIFICACION(5),
	REGISTRO_NO_TIENE_MOTIVO(6),
	NOTIFICACION_NO_REGISTRADA(7),
	DIRECCION_NO_EXISTE_EN_SISTEMA(8),
	FECHA_NOTIFICACION_MENOR_A_LA_DE_COACTIVO(9),
	;
	
	private Integer value;

	EnumErrorCargueCourier(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
