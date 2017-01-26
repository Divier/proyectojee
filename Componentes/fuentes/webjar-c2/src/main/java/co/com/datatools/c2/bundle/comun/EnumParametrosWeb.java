package co.com.datatools.c2.bundle.comun;

/**
 * Enumeracion con los nombres de las propiedades configuradas en<br/>
 * co.com.datatools.c2.bundle.comun.ParametrosWeb.properties
 * 
 * @author felipe.martinez
 */
public enum EnumParametrosWeb {
    lab_calendar_pattern, //
    lab_calendar_pattern_full, //
    lab_calendar_mask_pattern, //
    lab_calendar_hour_pattern, //
    lab_calendar_mask_hour_pattern, //
    param_rows_per_page, //
    param_initial_rows, //
    param_paginator_template;

    /**
     * Retorna el nombre estandar que se utiliza en las aplicaciones para importar el bundle, <br>
     * depende de la configuracion del faces-config.xml
     * 
     * @return <b>webPrm</b>
     */
    public static String getBundleName() {
        return "webPrm";
    }
}
