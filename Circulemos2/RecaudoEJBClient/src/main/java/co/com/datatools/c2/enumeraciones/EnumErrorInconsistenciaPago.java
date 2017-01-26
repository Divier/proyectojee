package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumErrorInconsistenciaPago implements SearchableEnumeration<Integer> {

    // Reglas basicas de negocio encabezado de pago
    PAGO_YA_APLICADO(3, "102", "Registro duplicado Pago ya aplicado"), //

    // Reglas basicas de negocio detalles de pago
    FALTA_NUMERO_CUOTA(9, "203", "Falta Numero de Cuota (solo para acuerdo de pago)"), //
    ;

    private int id;
    private String codigo;
    private String nombre;

    private EnumErrorInconsistenciaPago(int id) {
        this.id = id;
    }

    private EnumErrorInconsistenciaPago(int id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    private EnumErrorInconsistenciaPago(int id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public Integer getValue() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

}
