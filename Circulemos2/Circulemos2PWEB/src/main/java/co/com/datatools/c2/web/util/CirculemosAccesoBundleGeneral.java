package co.com.datatools.c2.web.util;

/**
 * Clase que define los mensajes estandarizados y acceso al bundle general
 * 
 * @author felipe.martinez
 */
public final class CirculemosAccesoBundleGeneral extends AbstractC2ManagedBean {

    private static final long serialVersionUID = -1434403951083822880L;

    /**
     * Nombre del bundle con mensajes generales para todos los servicios
     */
    public static final String NOMBRE_BUNDLE_GENERAL = "labelGeneral";

    private static final CirculemosAccesoBundleGeneral instance = new CirculemosAccesoBundleGeneral();

    private CirculemosAccesoBundleGeneral() {
    }

    /**
     * Llaves de los mensajes contenidos en el bundle general
     * 
     * @author felipe.martinez
     */
    public enum MensajesGeneral {
        val_campo_requerido, //
        val_campo_numerico, //
        val_campo_porcentaje, //
        val_campo_dias_anuales, //
        val_combo_primer_item, //
        val_sel_rad_yes, //
        val_sel_rad_no, //
        val_campo_dias_habiles, //
        val_campo_dias_calendario, //
        val_campo_email, //
        format_regex_email, //
        lab_button_reporte, //
        lab_button_image, //
        lab_button_validate, //
        lab_panel_modulo_registro, //
        lab_fieldset_informacion, //
        title_transaction_ok, //
        title_transaction_ko, //
        title_transaction_inf, //
        msj_circulemos_excepcion, //
        msj_consulta_ok, //
        msj_consulta_ko, //
        msj_no_item_selected, //
        msj_registro_ok, //
        msj_actualizacion_ok, //
        msj_eliminacion_ok, //
        lab_genero_masculino, //
        lab_genero_femenino, // 
        msj_ano_invalido//
    }

    /**
     * Retorna un mensaje almacenado en el bundle general de circulemos web
     * 
     * @param key
     *            identificador de la llave a buscar
     * @return mensaje internacionalizado encontrado
     */
    public static String getStringGeneral(MensajesGeneral key) {
        return instance.getBundle(NOMBRE_BUNDLE_GENERAL).getString(key.toString());
    }

    /**
     * Mensaje a desplegar cuando una consulta retorna resultados
     * 
     * @param numeroRegistros
     *            numero de registros encontrados
     */
    public static void addMensajeResultadoConsulta(int numeroRegistros) {
        instance.addInfoMessage(NOMBRE_BUNDLE_GENERAL, MensajesGeneral.title_transaction_ok.toString(),
                NOMBRE_BUNDLE_GENERAL, MensajesGeneral.msj_consulta_ok.toString(), numeroRegistros);
    }

    /**
     * Mensaje de warning cuando una consulta no trae resultados
     */
    public static void addMensajeResultadoConsultaVacio() {
        instance.addInfoMessage(NOMBRE_BUNDLE_GENERAL, MensajesGeneral.title_transaction_inf.toString(),
                NOMBRE_BUNDLE_GENERAL, MensajesGeneral.msj_consulta_ko.toString());
    }

    /**
     * Mensaje de informacion cuando se realiza un registro correcto
     */
    public static void addMensajeRegistroCreado() {
        instance.addInfoMessage(NOMBRE_BUNDLE_GENERAL, MensajesGeneral.title_transaction_ok.toString(),
                NOMBRE_BUNDLE_GENERAL, MensajesGeneral.msj_registro_ok.toString());
    }

    /**
     * Mensaje de informacion cuando se realiza un registro incorrecto
     */
    public static void addMensajeRegistroNoCreado() {
        instance.addErrorMessage(NOMBRE_BUNDLE_GENERAL, MensajesGeneral.title_transaction_ko.toString(),
                NOMBRE_BUNDLE_GENERAL, MensajesGeneral.msj_circulemos_excepcion.toString());
    }

    /**
     * Mensaje de informacion cuando se realiza una eliminacion correcta
     */
    public static void addMensajeRegistroEliminado() {
        instance.addInfoMessage(NOMBRE_BUNDLE_GENERAL, MensajesGeneral.title_transaction_ok.toString(),
                NOMBRE_BUNDLE_GENERAL, MensajesGeneral.msj_eliminacion_ok.toString());
    }

    /**
     * Mensaje de informacion cuando se realiza una actualizacion correcta
     */
    public static void addMensajeRegistroActualizado() {
        instance.addInfoMessage(NOMBRE_BUNDLE_GENERAL, MensajesGeneral.title_transaction_ok.toString(),
                NOMBRE_BUNDLE_GENERAL, MensajesGeneral.msj_actualizacion_ok.toString());
    }

    /**
     * Mensaje de warning cuando no se ha seleccionado un elemento de una tabla
     */
    public static void addMensajeNoSeleccionItem() {
        instance.addWarningMessage(NOMBRE_BUNDLE_GENERAL, MensajesGeneral.title_transaction_ko.toString(),
                NOMBRE_BUNDLE_GENERAL, MensajesGeneral.msj_no_item_selected.toString());
    }

    public static String booleanToLabel(boolean bool) {
        return bool ? getStringGeneral(MensajesGeneral.val_sel_rad_yes)
                : getStringGeneral(MensajesGeneral.val_sel_rad_no);
    }

    /**
     * Mensaje de arror cuando el año seleccionado supera el año actual
     */
    public static void addMensajeAnoMayorActual() {
        instance.addWarningMessage(NOMBRE_BUNDLE_GENERAL, MensajesGeneral.title_transaction_ko.toString(),
                NOMBRE_BUNDLE_GENERAL, MensajesGeneral.msj_ano_invalido.toString());
    }

}
