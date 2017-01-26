package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoDocumentoProceso implements SearchableEnumeration<Integer> {

    IMPUGNACION_APERTURA(1, "Impugnaci�n Apertura"), //
    IMPUGNACION_REALIZAR_FALLO(2, "Impugnaci�n Realizar Fallo"), //
    IMPUGNACION_CIERRE_PRUEBAS(3, "Impugnaci�n Cierre Pruebas"), //
    IMPUGNACION_SOLICITUD_PRUEBAS(4, "Impugnaci�n Solicitud Pruebas"), //
    FINANCIACION_ACUERDO_PAGO(5, "Financiaci�n Acuerdo De Pago"), //
    FINANCIACION_PONER_FIRME(6, "Financiaci�n Poner En Firme"), //
    ANULACION_NOTIFICACION_ANULACION(7, "Anulaci�n Notificaci�n"), //
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