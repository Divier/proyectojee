package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * Contiene la información de las plantillas
 * 
 * @author julio.pinzon 2016-09-06
 */
public class PlantillaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Nombre de la plantilla
     */
    private String nombrePlantilla;

    /**
     * Codigo de la plantilla
     */
    private String codigoPlantilla;

    // Start Methods Declaration

    public String getNombrePlantilla() {
        return nombrePlantilla;
    }

    public void setNombrePlantilla(String nombrePlantilla) {
        this.nombrePlantilla = nombrePlantilla;
    }

    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public void setCodigoPlantilla(String codigoPlantilla) {
        this.codigoPlantilla = codigoPlantilla;
    }

    // Finish the class

}