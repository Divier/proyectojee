package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * CU_CIR20_DAT_COM_054: Enum utilitario con la estructura del archivo de acuse de recibo de notificacion de comparendo
 * 
 * @author rodrigo.cruz
 * 
 */
public enum EnumCampoArchivoAcuse implements SearchableEnumeration<Integer> {

    FECHA_NOTIFICACION(1, 0, "Fecha de notificación", "[Ff]echa[\\w\\s]+[Nn]otificación", "dd/MM/yyyy"), //
    OBSERVACIONES_NOTI(2, 1, "Observaciones de notificación", "[Oo]bservaciones[\\w\\s]+[Nn]otificación", 255), //
    NUMERO_DE_COMPAREN(3, 2, "Número de comparendo", "[Nn]úmero[\\w\\s]+[Cc]omparendo", 20), //
    FECHA_DE_IMPOSICIO(4, 3, "Fecha de imposición de comparendo",
            "[Ff]echa[\\w\\s]+[Ii]mposición[\\w\\s]+[Cc]omparendo", "dd/MM/yyyy HH:mm:ss"), //
    TIPO_DOCUMENTO_INF(5, 4, "Tipo de documento de infractor", "[Tt]ipo[\\w\\s]+[Dd]ocumento[\\w\\s]+[Ii]nfractor", 40), //
    NUMERO_DOCUMENTO_I(6, 5, "Número de documento de infractor", "[Nn]úmero[\\w\\s]+[Dd]ocumento[\\w\\s]+[Ii]nfractor",
            20), //
    NOMBRE_1_INFRACTOR(7, 6, "Nombre 1 de infractor", "[Nn]ombre[\\w\\s]+1[\\w\\s]+[Ii]nfractor", 30), //
    NOMBRE_2_INFRACTOR(8, 7, "Nombre 2 de infractor", "[Nn]ombre[\\w\\s]+2[\\w\\s]+[Ii]nfractor", 30), //
    APELLIDO_1_INFRACT(9, 8, "Apellido 1 de infractor", "[Aa]pellido[\\w\\s]+1[\\w\\s]+[Ii]nfractor", 30), //
    APELLIDO_2_INFRACT(10, 9, "Apellido 2 de infractor", "[Aa]pellido[\\w\\s]+2[\\w\\s]+[Ii]nfractor", 30), //
    DIRECCION_INFRACTO(11, 10, "Dirección de infractor", "[Dd]irección[\\w\\s]+[Ii]nfractor", 150), //
    NUMERO_CELULAR_INF(12, 11, "Número de celular de infractor", "[Nn]úmero[\\w\\s]+[Cc]elular[\\w\\s]+[Ii]nfractor",
            20), //
    CODIGO_DE_INFRACCI(13, 12, "Código de infracción", "[Cc]ódigo[\\w\\s]+[Ii]nfracción", 35), //
    DESCRIPCION_INFRAC(14, 13, "Descripción de infracción", "[Dd]escripción[\\w\\s]+[Ii]nfracción", 200), //
    VALOR_DE_COMPAREND(15, 14, "Valor de comparendo", "[Vv]alor[\\w\\s]+[Cc]omparendo"), //
    PLACA_DEL_VEHICULO(16, 15, "Placa de vehículo", "[Pp]laca[\\w\\s]+[Vv]ehículo", 10); //
    ;

    private int id;
    private int codigo;
    private String nombre;
    private String regexp;
    private int longitud;
    private String formatoFecha;

    EnumCampoArchivoAcuse(int id, int codigo, String nombre, String regexp) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.regexp = regexp;
    }

    EnumCampoArchivoAcuse(int id, int codigo, String nombre, String regexp, Integer longitud) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.regexp = regexp;
        this.longitud = longitud;
    }

    EnumCampoArchivoAcuse(int id, int codigo, String nombre, String regexp, String formatoFecha) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.regexp = regexp;
        this.formatoFecha = formatoFecha;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

    public int getId() {
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRegexp() {
        return regexp;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public String getFormatoFecha() {
        return formatoFecha;
    }

}
