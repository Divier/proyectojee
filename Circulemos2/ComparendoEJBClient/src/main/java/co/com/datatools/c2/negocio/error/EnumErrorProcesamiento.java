package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enum que se encarga de los errores de procesamiento que se encuentran en al base de datos para validar los comparendos
 * 
 * @author giovanni.velandia 2015-10-06
 * 
 */
public enum EnumErrorProcesamiento implements SearchableEnumeration<String> {

    /*
     * Validaciones de encabezado
     */
    REGISTRADO(1, "0000", null), //
    INCONSISTENTE(3, "0002", null), //
    RECHAZADO(75, "9999", null), //

    /*
     * Validaciones campos bloqueantes version 1
     */

    // Encabezado
    FALTA_TIPO_COMPARENDO(5, "1001", EnumGrupoCampoComparendo.ENCABEZADO), //
    FALTA_NUMERO_COMPARENDO(6, "1002", EnumGrupoCampoComparendo.ENCABEZADO), //
    NUMERO_COMPARENDO_INVALIDO(7, "4025", EnumGrupoCampoComparendo.ENCABEZADO), //
    NUMERO_CITACION_INVALIDO(166, "4026", EnumGrupoCampoComparendo.ENCABEZADO), //
    NUMERO_FACTURA_INVALIDO(167, "4027", EnumGrupoCampoComparendo.ENCABEZADO), //
    INFRACCION_INVALIDA(14, "4007", EnumGrupoCampoComparendo.ENCABEZADO), //
    INFRACCION_NO_EXISTE(15, "6111", null), //
    LONGITUD_INFRACCION_INVALIDA(16, "1007", null), //
    FALTA_CAMPO_POLCA(4, "1000", EnumGrupoCampoComparendo.ENCABEZADO), //
    COMPARENDO_YA_EXISTE(31, "1022", EnumGrupoCampoComparendo.ENCABEZADO), //
    FALTA_TIPO_AGENTE_IMPOSITOR(163, "1152", null), //

    // Vehiculo
    FALTA_PLACA_VEHICULO(36, "1027", EnumGrupoCampoComparendo.VEHICULO), //
    FALTA_IDENTIFICACION_VEHICULO(38, "1029", EnumGrupoCampoComparendo.VEHICULO), //
    FORMATO_IDENTIFICACION_VEHICULO_INVALIDO(144, "1133",
            EnumGrupoCampoComparendo.VEHICULO), PLACA_VEHICULO_INVALIDA(37, "1028", EnumGrupoCampoComparendo.VEHICULO), //
    TIPO_DOCUMENTO_EMPRESA_INVALIDO(90, "1079", EnumGrupoCampoComparendo.VEHICULO), //
    FALTA_NUMERO_DOCUMENTO_EMPRESA(91, "1080", EnumGrupoCampoComparendo.VEHICULO), //
    FORMATO_DOCUMENTO_EMPRESA_INVALIDO(92, "1081", EnumGrupoCampoComparendo.VEHICULO), //
    LONGITUD_DOCUMENTO_EMPRESA_INVALIDO(93, "1082", EnumGrupoCampoComparendo.VEHICULO), //
    FALTA_TIPO_DOCUMENTO_EMPRESA(117, "1106", EnumGrupoCampoComparendo.VEHICULO), //
    FALTA_RAZON_SOCIAL_EMPRESA(118, "1107", EnumGrupoCampoComparendo.VEHICULO), //
    CLASE_DE_SERVICIO(57, "1048", EnumGrupoCampoComparendo.VEHICULO), //
    FALTA_RADIO_DE_ACCION(57, "1121", EnumGrupoCampoComparendo.VEHICULO), //
    LONGITUD_NUMERO_TARJETA_OPERACION_INVALIDO(144, "1134", EnumGrupoCampoComparendo.VEHICULO), //
    FORMATO_TARJETA_OPERACION_INVALIDO(145, "1135", EnumGrupoCampoComparendo.VEHICULO), //
    FORMATO_ORGANISMO_MATRICULA_VEHICULO_INVALIDO(160, "1149", EnumGrupoCampoComparendo.VEHICULO), //

    // Infractor
    FALTA_TIPO_DOCUMENTO_INFRACTOR(39, "1030", EnumGrupoCampoComparendo.INFRACTOR), //
    FALTA_NUMERO_DOCUMENTO_INFRACTOR(40, "1031", EnumGrupoCampoComparendo.INFRACTOR), //
    LONGITUD_DOCUMENTO_INFRACTOR_INVALIDA(41, "1032", EnumGrupoCampoComparendo.INFRACTOR), //
    FORMATO_DOCUMENTO_INFRACTOR_INVALIDO(42, "1033", EnumGrupoCampoComparendo.INFRACTOR), //
    FALTA_PRIMER_NOMBRE_INFRACTOR(45, "1036", EnumGrupoCampoComparendo.INFRACTOR), //
    PRIMER_APELLIDO_INFRACTOR_INVALIDO_FORMATO(68, "1058", EnumGrupoCampoComparendo.INFRACTOR), //
    SEGUNDO_APELLIDO_INFRACTOR_INVALIDO(69, "1059", EnumGrupoCampoComparendo.INFRACTOR), //
    PRIMER_NOMBRE_INFRACTOR_INVALIDO_FORMATO(70, "1060", EnumGrupoCampoComparendo.INFRACTOR), //
    SEGUNDO_NOMBRE_INFRACTOR_INVALIDO(71, "1061", EnumGrupoCampoComparendo.INFRACTOR), //
    FALTA_PRIMER_APELLIDO_INFRACTOR(43, "1034", EnumGrupoCampoComparendo.INFRACTOR), //
    PRIMER_APELLIDO_INFRACTOR_INVALIDO(44, "1035", EnumGrupoCampoComparendo.INFRACTOR), //
    PRIMER_NOMBRE_INFRACTOR_INVALIDO(46, "1037", EnumGrupoCampoComparendo.INFRACTOR), //
    FALTA_RAZON_SOCIAL_INFRACTOR(122, "1111", EnumGrupoCampoComparendo.INFRACTOR), //
    DIRECCION_INFRACTOR_INVALIDA(140, "1129", EnumGrupoCampoComparendo.INFRACTOR), //
    EMAIL_INFRACTOR_INVALIDO(147, "1136", EnumGrupoCampoComparendo.INFRACTOR), //
    FORMATO_LICENCIA_INFRACTOR_INVALIDO(148, "1137", EnumGrupoCampoComparendo.INFRACTOR), //
    FORMATO_TELEFONO_FIJO_INFRACTOR_INVALIDO(149, "1138", EnumGrupoCampoComparendo.INFRACTOR), //
    FORMATO_TELEFONO_MOVIL_INFRACTOR_INVALIDO(150, "1139", EnumGrupoCampoComparendo.INFRACTOR), //

    // Propietario
    FALTA_PRIMER_APELLIDO_PROPIETARIO(80, "1069", EnumGrupoCampoComparendo.PROPIETARIO), //
    PRIMER_APELLIDO_PROPIETARIO_INVALIDO(81, "1070", EnumGrupoCampoComparendo.PROPIETARIO), //
    PRIMER_APELLIDO_PROPIETARIO_INVALIDO_FORMATO(82, "1071", EnumGrupoCampoComparendo.PROPIETARIO), //
    SEGUNDO_APELLIDO_PROPIETARIO_INVALIDO(83, "1072", EnumGrupoCampoComparendo.PROPIETARIO), //
    SEGUNDO_APELLIDO_PROPIETARIO_INVALIDO_FORMATO(84, "1073", EnumGrupoCampoComparendo.PROPIETARIO), //
    FALTA_PRIMER_NOMBRE_PROPIETARIO(85, "1074", EnumGrupoCampoComparendo.PROPIETARIO), //
    PRIMER_NOMBRE_PROPIETARIO_INVALIDO(86, "1075", EnumGrupoCampoComparendo.PROPIETARIO), //
    PRIMER_NOMBRE_PROPIETARIO_INVALIDO_FORMATO(87, "1076", EnumGrupoCampoComparendo.PROPIETARIO), //
    SEGUNDO_NOMBRE_PROPIETARIO_INVALIDO(88, "1077", EnumGrupoCampoComparendo.PROPIETARIO), //
    SEGUNDO_NOMBRE_PROPIETARIO_INVALIDO_FORMATO(89, "1078", EnumGrupoCampoComparendo.PROPIETARIO), //
    FALTA_TIPO_DOCUMENTO_PROPIETARIO(115, "1104", EnumGrupoCampoComparendo.PROPIETARIO), //
    FALTA_NUMERO_DOCUMENTO_PROPIETARIO(116, "1105", EnumGrupoCampoComparendo.PROPIETARIO), //
    FORMATO_DOCUMENTO_PROPIETARIO_INVALIDO(79, "1068", EnumGrupoCampoComparendo.PROPIETARIO), //
    FALTA_INFORMACION_PROPIETARIO(130, "1119", EnumGrupoCampoComparendo.PROPIETARIO), //
    FALTA_RAZON_SOCIAL_PROPIETARIO(123, "1112", EnumGrupoCampoComparendo.PROPIETARIO), //
    FORMATO_LICENCIA_TRANSITO_INVALIDO(151, "1140", EnumGrupoCampoComparendo.PROPIETARIO),

    // Agente de transito
    FALTA_PRIMER_APELLIDO_AGENTE(18, "1009", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    PRIMER_APELLIDO_AGENTE_INVALIDO(19, "1010", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    FALTA_PRIMER_NOMBRE_AGENTE(20, "1011", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    PRIMER_NOMBRE_AGENTE_INVALIDO(21, "1012", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    FALTA_TIPO_DOCUMENTO_AGENTE(22, "1013", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    FALTA_NUMERO_DOCUMENTO_AGENTE(23, "1014", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    FORMATO_DOCUMENTO_AGENTE_INVALIDO(24, "1015", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    LONGITUD_DOCUMENTO_AGENTE_INVALIDA(25, "1016", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    LONGITUD_RESPONSABLE_INVALIDO(152, "1141", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    FORMATO_RESPONSABLE_INVALIDO(153, "1142", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    LONGITUD_OBSERVACIONES_INVALIDO(154, "1143", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    FORMATO_OBSERVACIONES_INVALIDO(155, "1144", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //

    FORMATO_PLACA_AGENTE_INVALIDO(94, "1083", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    PRIMER_APELLIDO_AGENTE_INVALIDO_FORMATO(95, "1084", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    SEGUNDO_APELLIDO_AGENTE_INVALIDO_FORMATO(96, "1085", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    SEGUNDO_APELLIDO_AGENTE_INVALIDO(97, "1086", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    PRIMER_NOMBRE_AGENTE_INVALIDO_FORMATO(98, "1087", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    SEGUNDO_NOMBRE_AGENTE_INVALIDO_FORMATO(99, "1088", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    SEGUNDO_NOMBRE_AGENTE_INVALIDO(100, "1089", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    FALTA_PLACA_AGENTE(54, "1045", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    AGENTE_TRANSITO_INEXISTENTE(55, "1046", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    AGENTE_NO_VIGENTE(56, "1047", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //

    // Entidad Agente
    FALTA_ENTIDAD_AGENTE(26, "1017", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //
    ENTIDAD_AGENTE_INVALIADA(138, "1127", EnumGrupoCampoComparendo.AGENTE_TRANSITO), //

    // Inmovilizacion
    FALTA_CODIGO_PATIO(53, "1044", EnumGrupoCampoComparendo.INMOVILIZACION), //
    PLACA_GRUA_INVALIDO(101, "1090", EnumGrupoCampoComparendo.INMOVILIZACION), //
    LONGITUD_PLACA_GRUA_INVALIDO(102, "1091", EnumGrupoCampoComparendo.INMOVILIZACION), //
    DIRECCION_PATIO_INVALIDA(142, "1131", EnumGrupoCampoComparendo.INMOVILIZACION), //
    FORMATO_NOMBRE_PATIO_INVALIDO(156, "1145", EnumGrupoCampoComparendo.INMOVILIZACION), //
    CONSECUTIVO_INMOVILIZACION_INVALIDO_LONGITUD(158, "1147", EnumGrupoCampoComparendo.INMOVILIZACION), //
    CONSECUTIVO_INMOVILIZACION_INVALIDO(159, "1148", EnumGrupoCampoComparendo.INMOVILIZACION), //

    // Testigo
    FORMATO_NUMERO_DOCUMENTO_TESTIGO_INVALIDO(103, "1092", EnumGrupoCampoComparendo.TESTIGO), //
    NUMERO_DOCUMENTO_TESTIGO_INVALIDO(104, "1093", EnumGrupoCampoComparendo.TESTIGO), //
    FALTA_PRIMER_APELLIDO_TESTIGO(105, "1094", EnumGrupoCampoComparendo.TESTIGO), //
    PRIMER_APELLIDO_TESTIGO_INVALIDO_FORMATO(106, "1095", EnumGrupoCampoComparendo.TESTIGO), //
    PRIMER_APELLIDO_TESTIGO_INVALIDO(107, "1096", EnumGrupoCampoComparendo.TESTIGO), //
    SEGUNDO_APELLIDO_TESTIGO_INVALIDO_FORMATO(108, "1097", EnumGrupoCampoComparendo.TESTIGO), //
    SEGUNDO_APELLIDO_TESTIGO_INVALIDO(109, "1098", EnumGrupoCampoComparendo.TESTIGO), //
    FALTA_PRIMER_NOMBRE_TESTIGO(110, "1099", EnumGrupoCampoComparendo.TESTIGO), //
    PRIMER_NOMBRE_TESTIGO_INVALIDO_FORMATO(111, "1100", EnumGrupoCampoComparendo.TESTIGO), //
    PRIMER_NOMBRE_TESTIGO_INVALIDO(112, "1101", EnumGrupoCampoComparendo.TESTIGO), //
    SEGUNDO_NOMBRE_TESTIGO_INVALIDO_FORMATO(113, "1102", EnumGrupoCampoComparendo.TESTIGO), //
    SEGUNDO_NOMBRE_TESTIGO_INVALIDO(114, "1103", EnumGrupoCampoComparendo.TESTIGO), //
    FALTA_TIPO_DOCUMENTO_TESTIGO(119, "1108", EnumGrupoCampoComparendo.TESTIGO), //
    FALTA_NUMERO_DOCUMENTO_TESTIGO(120, "1109", EnumGrupoCampoComparendo.TESTIGO), //
    DIRECCION_TESTIGO_INVALIDA(141, "1130", EnumGrupoCampoComparendo.TESTIGO), //
    FORMATO_MOVIL_TESTIGO_INVALIDO(157, "1146", EnumGrupoCampoComparendo.TESTIGO), //

    // Embriaguez
    FALTA_CAMPO_NIEGA_PRUEBA_EMBRIAGUEZ(47, "1038", EnumGrupoCampoComparendo.EMBRIAGUEZ), //
    FALTA_GRADOS_ALCOHOLEMIA(48, "1039", EnumGrupoCampoComparendo.EMBRIAGUEZ), //
    FALTA_MILIGRAMOS_ETANOL(49, "1040", EnumGrupoCampoComparendo.EMBRIAGUEZ), //
    FALTA_NUMERO_REINCIDENCIAS(50, "1041", EnumGrupoCampoComparendo.EMBRIAGUEZ), //
    GRADO_ALCOHOLEMIA_INVALIDO(51, "1042", EnumGrupoCampoComparendo.EMBRIAGUEZ), //
    MILIGRAMOS_ETANOL_INVALIDOS(52, "1043", EnumGrupoCampoComparendo.EMBRIAGUEZ), //
    PRUEBA_ALCOHOLEMIA_INVALIDA(145, "1132", EnumGrupoCampoComparendo.EMBRIAGUEZ), //
    NUMERO_REINCIDENCIAS_INVALIDO(133, "1122", EnumGrupoCampoComparendo.EMBRIAGUEZ), //

    // Evidencias fisicas
    PROBLEMAS_ALMACENANDO_EVIDENCIAS(61, "1051", EnumGrupoCampoComparendo.EVIDENCIAS_FISICAS), //
    FALTA_TIPO_EVIDENCIA(2, "0001", EnumGrupoCampoComparendo.EVIDENCIAS_FISICAS), //

    // Otros Campos
    FALTA_FECHA_REGISTRO(8, "1003", null), //
    FECHA_INFRACCION_INVALIDA(9, "6113", null), //
    HORA_INFRACCION_INVALIDAD(10, "3002", null), //
    FALTA_CAMPO_ORGANISMO_TRANSITO(11, "1004", null), //
    ORGANISMO_TRANSITO_INACTIVO(12, "1005", null), //
    FALTA_DIRECCION_IMPOSICION(13, "1006", null), //
    FALTA_CAMPO_ORIGEN_VALIDACION(17, "1008", null), //
    NO_SE_PUEDE_CORREGIR_COMPARENDO(27, "1018", null), //
    FALTA_EXISTE_FUGA_INFRACTOR(58, "1049", null), //

    /*
     * Validaciones campos bloqueantes version 2
     */
    TIPO_EVIDENCIA_INVALIDO(64, "1054", null), //
    FECHA_NOTIFICACION_INVALIDA_DIA(62, "1052", null), //
    FECHA_NOTIFICACION_INVALIDA(63, "1053", null), //
    EXTENSION_INVALIDA(65, "1055", null), //
    FECHA_EXPED_LICENCIA_CONDUCCION_INVALIDA(66, "1056", null), //
    FECHA_VENCI_LICEN_CONDUCCION_INVALIDA(67, "1057", null), //
    ORGANISMO_TRANSITO_INEXISTENTE(72, "1062", null), //
    ORGANISMO_TRANSITO_INEXISTENTE_LIC_TRAN(73, "1063", null), //
    ORGANISMO_TRANSITO_INACTIVO_LIC_TRAN(74, "1064", null), //
    ORGANISMO_TRANSITO_INEXISTENTE_LIC_COND(76, "1065", null), //
    ORGANISMO_TRANSITO_INACTIVO_LIC_COND(77, "1066", null), //
    NO_EXISTE_COMPARENDO_INCONSISTENTE(27, "1018", null), //

    /*
     * Validaciones de Negocio
     */
    COMPARENDO_NO_ASIGNADO(28, "1019", null), //
    COMPARENDO_POLCA_EXISTENTE(29, "1020", null), //
    NUMERO_COMPARENDO_NO_EXISTE(30, "1021", null), //
    EXISTE_COMPARENDO_INCOSISTENTE(32, "1023", null), //
    FECHA_INFRACCION_INVALIDA_DIA(33, "1024", null), //
    FECHA_INFRACCION_VENCIDA(34, "1025", null), //
    INFRACCION_NO_SE_ENCUENTRA_VIGENTE(35, "1026", null), //
    NO_SE_PUDO_REALIZA_LA_TRANSACCION(59, "99", null), //
    INFRACCION_NO_TIENE_TARIFA(60, "1050", null), //
    VALOR_NO_MAPEADO(121, "1110", null), //
    DIRECCION_INFRACCION_INVALIDA(139, "1128", null), //
    VALOR_CONCERPTO(161, "1150", null), //
    FALTA_ESTADO_ORIGEN_COMPARENDO(162, "1151", null), //

    // RECTIFICACION
    COMPARENDO_NO_VIGENTE(124, "1113", null), //
    NUMERO_RECTIFICACIONES_PERMITIDAS_SUPERADO(125, "1114", null), //
    COMPARENDO_PROCESO_INPUGNACION(126, "1115", null), //
    COMPARENDO_PROCESO_FINANCIACION(127, "1116", null), //
    COMPARENDO_PROCESO_COACTIVO(128, "1117", null), //
    COMPARENDO_PROCESO_PAGOS_REALIZADOS(129, "1118", null), //
    CORRECCION_CODIGO_ORIGEN_DISTINTO(131, "1120", null), //

    FALTA_ACTIVIDAD_RECTIFICACION_COMPARENDO(164, "1153", null), //
    ESTADO_COMPARENDO_INVALIDO(165, "1154", null), //
    ;

    /**
     * Identifica el codigo del campo en la base de datos.
     */
    int id;
    String codigo;
    EnumGrupoCampoComparendo enumGrupoCampoComparendo;

    private EnumErrorProcesamiento(int id, String codigo, EnumGrupoCampoComparendo enumGrupoCampoComparendo) {
        this.codigo = codigo;
        this.id = id;
        this.enumGrupoCampoComparendo = enumGrupoCampoComparendo;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getId() {
        return id;
    }

    public EnumGrupoCampoComparendo getEnumGrupoCampoComparendo() {
        return enumGrupoCampoComparendo;
    }

    @Override
    public String getValue() {
        return codigo;
    }

}
