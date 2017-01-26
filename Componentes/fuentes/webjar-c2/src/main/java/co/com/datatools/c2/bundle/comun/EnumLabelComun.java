package co.com.datatools.c2.bundle.comun;

/**
 * Enumeracion con los nombres de las propiedades configuradas en<br/>
 * co.com.datatools.c2.bundle.comun.LabelComun.properties
 * 
 * @author felipe.martinez
 */
public enum EnumLabelComun {
    // Etiquetas Boton
    btnConsultar, //
    btnCrear, //
    btnEditar, //
    btnEliminar, //
    btnVerDet, //
    btnGuardar, //
    btnLimpiar, //
    btnCancelar, //
    btnVolver, //
    btnAgregarDet, //
    btnEditarDet, //
    btnRemoverDet, //

    // Opciones Generales
    val_sel_activo, //
    val_sel_inactivo, //

    // Tooltip Generales
    tooltip_limpiar, //
    tooltip_volver, //

    // Mensajes Comun
    msg_tit_confirmar, //
    msg_confirm_guardar, //
    msg_confirm_eliminar, //
    msg_confirm_volver_cancelar, //

    // Layout
    msg_fieldset_consultar, //
    msg_fieldset_registro, //
    msg_fieldset_edicion;

    /**
     * Retorna el nombre estandar que se utiliza en las aplicaciones para importar el bundle, <br>
     * depende de la configuracion del faces-config.xml
     * 
     * @return <b>lbComun</b>
     */
    public static String getBundleName() {
        return "lbComun";
    }
}
