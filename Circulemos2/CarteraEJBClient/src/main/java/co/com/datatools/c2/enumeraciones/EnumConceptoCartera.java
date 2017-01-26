package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumConceptoCartera implements SearchableEnumeration<Integer> {

    REGISTRO_CARTERA(3, 94), //
    NC_RECTIFICAR_COMPARENDO(141, 908), //
    COMPARENDO_PAGADO(145, 912), //
    CUOTA_FINANCIACION_PAGADA(146, 913), //
    CREAR_INTERESES_COMPARENDO(147, 914), //
    PAGAR_INTERESES_COMPARENDO(148, 915), //
    EXONERACION(25, 214), //
    ANULACION_POR_OPERATIVO(149, 916), //
    ANULACION_POR_OPERATIVO_CAPITAL(150, 917), //
    COMPARENDO_PAGADO_FINANCIACION(151, 918), //
    PAGAR_INTERESES_COMPARENDO_FINANCIACION(152, 919), //
    CREAR_INTERESES_PREFINANCIACION(153, 920), //
    RECTIFICACION_AXIS_DEBITO(154, 921), //
    RECTIFICACION_AXIS_CREDITO(155, 922), //
    CREAR_COSTAS_PROCESALES(156, 923)//
    ;

    private Integer id;
    private Integer codigo;

    private EnumConceptoCartera(Integer id, Integer codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
