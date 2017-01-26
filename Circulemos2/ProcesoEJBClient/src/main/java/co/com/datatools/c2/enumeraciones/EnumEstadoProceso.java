package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.enumeracion.EnumPais;
import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoProceso implements SearchableEnumeration<Integer> {
    // COLOMBIA
    COLOMBIA_IMPUGNACION_ABIERTO(1, EnumPais.COLOMBIA, EnumTipoProceso.IMPUGNACION_COMPARENDO, "01", "ABIERTO"), //
    COLOMBIA_IMPUGNACION_CERRADO(1, EnumPais.COLOMBIA, EnumTipoProceso.IMPUGNACION_COMPARENDO, "02", "CERRADO"), //

    // ECUADOR
    ECUADOR_IMPUGNACION_IMPUGNADO(1, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "01", "IMPUGNADO"), //
    ECUADOR_IMPUGNACION_EVALUADO(2, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "02", "EVALUADO"), //
    ECUADOR_IMPUGNACION_FALLADO(3, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "03", "FALLADO"), //
    ECUADOR_IMPUGNACION_CERRADO(4, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "04", "CERRADO"), //
    ECUADOR_IMPUGNACION_RECHAZADO(5, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "05", "RECHAZADO"), //
    ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION(6, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "06",
            "ENVIADO A ABOGADO DE CONTINUACION"), //
    ECUADOR_IMPUGNACION_PENDIENTE_GESTION_ESPECIALISTA(7, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO,
            "07", "PENDIENTE GESTION ESPECIALISTA"), //
    ECUADOR_IMPUGNACION_PENDIENTE_PRUEBAS(8, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "08",
            "PENDIENTE PRUEBAS"), //
    ECUADOR_IMPUGNACION_REGISTRO_ACCIONES_ESPECIALISTA(9, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO,
            "09", "REGISTRO ACCIONES ESPECIALISTA"), //
    ECUADOR_IMPUGNACION_CIERRE_PRUEBAS(10, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "10",
            "CIERRE PRUEBAS"), //
    ECUADOR_IMPUGNACION_RECHAZADO_ACONTINUACION(11, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "11",
            "RECHAZADO ABOGADO CONTINUACION"), //
    ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION(12, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "12",
            "EVALUADO ABOGADO CONTINUACION"), //
    ECUADOR_IMPUGNACION_SOLICITUD_PRUEBAS(13, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "13",
            "SOLICITUD PRUEBAS"), //
    ECUADOR_IMPUGNACION_FALLADO_ACONTINUACION(14, EnumPais.ECUADOR, EnumTipoProceso.IMPUGNACION_COMPARENDO, "14",
            "FALLADO ABOGADO CONTINUACION"), //
    ECUADOR_FINANCIACION_PREFINANCIADO(15, EnumPais.ECUADOR, EnumTipoProceso.FINANCIACION_COMPARENDO, "15",
            "PREFINANCIADO"), //
    ECUADOR_FINANCIACION_EN_FIRME(16, EnumPais.ECUADOR, EnumTipoProceso.FINANCIACION_COMPARENDO, "16", "EN FIRME"), //
    ECUADOR_FINANCIACION_ANULADO(17, EnumPais.ECUADOR, EnumTipoProceso.FINANCIACION_COMPARENDO, "17", "ANULADO"), //
    ECUADOR_FINANCIACION_PAGADO(18, EnumPais.ECUADOR, EnumTipoProceso.FINANCIACION_COMPARENDO, "18", "PAGADO"), //
    ECUADOR_FINANCIACION_INCUMPLIDO(19, EnumPais.ECUADOR, EnumTipoProceso.FINANCIACION_COMPARENDO, "19", "INCUMPLIDO"), //
    ECUADOR_ANULACION_ANULADO(20, EnumPais.ECUADOR, EnumTipoProceso.ANULACION_COMPARENDO, "20", "ANULADO"), //
    ECUADOR_COACTIVO_RADICACION(21, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "21", "RADICACION COBRO COACTIVO"), //
    ECUADOR_COACTIVO_MANDAMIENTO(22, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "22", "MANDAMIENTO DE PAGO"), //
    ECUADOR_COACTIVO_NOTIFICADO(23, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "23", "NOTIFICADO POR COURIER"), //
    ECUADOR_COACTIVO_ENVIO_NOTIFICACION(24, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "24", "ENVIO NOTIFICACION"), //
    ECUADOR_COACTIVO_RADICAR_EXCEPCION(25, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "25", "RADICAR_EXCEPCIÓN"), //
    ECUADOR_COACTIVO_REGISTRO_FALLO(26, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "26", "REGISTRO_FALLO"), //
    ECUADOR_COACTIVO_CERRADO(27, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "27", "CERRADO"), //
    ECUADOR_COACTIVO_COACTIVO_CONVENIO(28, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "28", "COACTIVO EN CONVENIO"), //
    SOLICITUD_BUSQUEDA_DE_BIENES(29, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "29", "SOLICITUD BUSQUEDA DE BIENES"), //
    ECUADOR_COACTIVO_CERRADO_POR_FALLO(30, EnumPais.ECUADOR, EnumTipoProceso.COACTIVO, "30", "CERRADO POR FALLO"), //
    ;

    private int id;
    private EnumPais pais;
    private EnumTipoProceso tipoProceso;
    private String codigo;
    private String nombre;

    EnumEstadoProceso(int id, EnumPais pais, EnumTipoProceso tipoProceso, String codigo, String nombre) {
        this.id = id;
        this.pais = pais;
        this.tipoProceso = tipoProceso;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumPais getPais() {
        return pais;
    }

    public void setPais(EnumPais pais) {
        this.pais = pais;
    }

    public EnumTipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(EnumTipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
