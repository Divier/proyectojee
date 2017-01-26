package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoDocumentoProceso implements SearchableEnumeration<Integer> {

    IMPUGNACION_APERTURA(1, "Impugnación Apertura"), //
    IMPUGNACION_REALIZAR_FALLO(2, "Impugnación Realizar Fallo"), //
    IMPUGNACION_CIERRE_PRUEBAS(3, "Impugnación Cierre Pruebas"), //
    IMPUGNACION_SOLICITUD_PRUEBAS(4, "Impugnación Solicitud Pruebas"), //
    FINANCIACION_ACUERDO_PAGO(5, "Financiación Acuerdo De Pago"), //
    FINANCIACION_PONER_FIRME(6, "Financiación Poner En Firme"), //
    ANULACION_NOTIFICACION_ANULACION(7, "Anulación Notificación"), //
    COACTIVO_AUTO_PAGO(8, "Coactivo Auto Pago"), //
    COACTIVO_ACTA_DE_POSESION(9, "Acta de posesion"), //
    COACTIVO_NOTIFICACION(10, "Notificacion de coactivo"), //
    COACTIVO_AUTO_PAGO_SIN_DIRECCION(11, "Coactivo auto pago sin direccion"), //
    SOLICITUD_OFICIO_BIEN(12, "Generacion de solicitud de oficio bien"),//
    ;

    private int codigo;
    private String nombre;

    EnumTipoDocumentoProceso(int codigo) {
        this.codigo = codigo;
    }

    EnumTipoDocumentoProceso(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}