package co.com.datatools.c2.enumeraciones;

/**
 * Enum que se encarga de informar el nombre del DTO en el sistema para cada una de las configuraciones y dar el codigo de la base de datos
 * 
 * @author giovanni.velandia 2015-10-06
 * 
 */
public enum EnumConfiguracion {

    ORDEN_PRIORITARIO_PERS_FUENTE_INFORMACION("005", "OrdenPrioritarioPersFuenteInfoDTO"), //
    CONFIG_GRADOS_ALCOHOL("006", "ConfiguracionGradosAlcoholDTO"), //
    CONFIGURA_MEDIOS_IMPOSICION_NOTIFICACION("007", "ConfiguraMediosImposicionNotificacionDTO"), //
    ASOCIA_TIPOS_COMPARENDO_FORMULARIO("008", "AsociaComparendoFormularioDTO"), //
    ASOCIA_DIAS_INGRESAR_COMPARENDO("011", "AsociaDiasIngresoComparendoDTO"), //
    ASOCIA_MEDIO_IMPOSICION_TIPOS_DE_FECHA("010", "AsociaMedioImposicionTiposFechaDTO"), //
    ASOCIA_DOCUMENTO_IDENTIDAD_CON_UN_FORMATO("009", "AsociaDocumentoIdentidadFormatoDTO"), //
    EMBRIAGUEZ_TARIFAS("012", "EmbriaguezTarifasDTO"), //
    VALORES_BUSQUEDA_REINCIDENCIA("013", "ValoresBusquedaReincidenciaDTO"), //
    ;

    /**
     * Identifica el codigo del campo en la base de datos.
     */
    String codigo;
    /**
     * Identifica la longitud del campo.
     */
    String nombreDTO;

    private EnumConfiguracion(String codigo, String nombreDTO) {
        this.codigo = codigo;
        this.nombreDTO = nombreDTO;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreDTO() {
        return nombreDTO;
    }
}
