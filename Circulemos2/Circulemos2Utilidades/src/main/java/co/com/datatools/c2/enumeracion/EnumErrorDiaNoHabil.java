package co.com.datatools.c2.enumeracion;

/**
 * Tipos de errores que pueden surgir en la validacion de un dia no habil CU_CIR20_DAT_ADM_019
 * 
 * @author luis.forero
 * 
 */
public enum EnumErrorDiaNoHabil {

    DIA_NO_HABIL_VALIDO(1000, "Día no hábil valido"), //
    FECHA_MENOR_ACTUAL(1001, "Fecha menor que actual no permitida"), //
    DIA_NO_HABIL_INGRESADO(1002, "Día no hábil registrado"), //
    ;

    private Integer codigo;
    private String desc;

    private EnumErrorDiaNoHabil(Integer codigo, String desc) {
        this.codigo = codigo;
        this.desc = desc;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDesc() {
        return desc;
    }

}
