package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoRechazoRecaudo implements SearchableEnumeration<Integer> {

    // Detalles de pago
    ORGANISMO_NO_EXISTE(1, "001", "NO existe organismo de transito"), //
    BANCO_NO_EXISTE(2, "002", "NO existe banco"), //
    TIPO_RECAUDO_NO_EXISTE(3, "003", "NO existe tipo de recaudo"), //
    TIPO_DOCUMENTO_IDENTIFICACION_NO_EXISTE(4, "004", "NO existe tipo de documento del deudor"), //
    TIPO_CUENTA_NO_EXISTE(5, "005", "NO existe tipo de cuenta"), //
    FECHA_RECAUDO_SUPERIOR_ACTUAL(6, "006", "Fecha de recaudo superior a la actual"), //
    TOTAL_RECAUDO_MENOR_IGUAL_CERO(7, "007", "Total recaudo menor o igual a cero"), //
    VALOR_CHEQUE_MENOR_IGUAL_CERO(8, "008", "Valor cheque menor o igual a cero"), //
    VALOR_EFECTIVO_MENOR_IGUAL_CERO(9, "009", "Valor efectivo menor o igual a cero"), //
    TOTAL_RECAUDO_OBLIGATORIO(10, "010", "Total recaudo obligatorio"), //
    NUMERO_RECAUDO_OBLIGATORIO(11, "011", "Numero recaudo obligatorio"), //
    NUMERO_CUENTA_OBLIGATORIO(12, "012", "Numero cuenta obligatorio"), //
    CODIGO_TIPO_CUENTA_OBLIGATORIO(13, "013", "Tipo de cuenta obligatorio"), //
    CODIGO_BANCO_OBLIGATORIO(14, "014", "Banco obligatorio"), //
    CODIGO_TIPO_ID_OBLIGATORIO(15, "015", "Tipo de identificacion obligatorio"), //
    NUMERO_OBLIGACION_OBLIGATORIO(16, "016", "Numero obligacion obligatorio"), //
    CODIGO_TIPO_RECAUDO_OBLIGATORIO(17, "017", "Tipo recaudo obligatorio"), //
    TOTAL_RECAUDO_NO_SUMA_MEDIOS_PAGO(18, "018", "Total de recaudo no suma los medios de pago"), //
    VALOR_OBLIGACION_MENOR_IGUAL_CERO(19, "019", "Valor obligacion menor o igual a cero"), //
    NUMERO_IDENTIFICACION_OBLIGATORIO(20, "020", "Numero identificacion obligatorio"), //
    ORGANISMO_INCORRECTO(21, "021", "El codigo del organismo de transito es incorrecto"), //
    NUMERO_CUENTA_LONGITUD(22, "022", "El numero de cuenta excede el limite"), //
    NUMERO_CUOTA_MINIMO(23, "023", "El numero de la cuota es menor al permitido"), //
    NUMERO_CUOTA_INCORRECTO(24, "024", "El numero de la cuota no es correcto"), //
    NUMERO_OBLIGACION_LONGITUD(25, "025", "El numero de la obligacion excede el limite"), //
    FECHA_TRANSACCION_INCORRECTA(26, "026", "La fecha no es correcta"), //
    HORA_TRANSACCION_INCORRECTA(27, "027", "La hora no es correcta"), //
    NUMERO_IDENTIFICACION_LONGITUD(28, "028", "El numero de identificacion excede el limite"), //
    NUMERO_RECAUDO_INCORRECTO(29, "029", "El numero del recaudo no es correcto"), //
    FIRMA_NO_ENCONTRADA(30, "030", "Firma de la persona no encontrada");

    private int id;
    private String codigo;
    private String nombre;

    private EnumTipoRechazoRecaudo(int id) {
        this.id = id;
    }

    private EnumTipoRechazoRecaudo(int id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    private EnumTipoRechazoRecaudo(int id, String codigo, String nombre) {
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
