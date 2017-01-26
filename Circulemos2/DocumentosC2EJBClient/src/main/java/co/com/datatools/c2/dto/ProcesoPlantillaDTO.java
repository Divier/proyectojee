package co.com.datatools.c2.dto;

import co.com.datatools.c2.enumeraciones.EnumProcesoPlantilla;
import co.com.datatools.c2.negocio.interfaces.IPlantillaGenerable;

/**
 * Contiene la información para generar un documento desde el sistema con el módulo encargado de documentos.
 * 
 * @author julio.pinzon
 */
public class ProcesoPlantillaDTO implements IPlantillaGenerable {

    private static final long serialVersionUID = 1L;

    private String codigoPlantilla;
    private String descripcion;
    private EnumProcesoPlantilla enumProcesoPlantilla;

    public String getCodigo() {
        return enumProcesoPlantilla.getCodigo();
    }

    @Override
    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public void setCodigoPlantilla(String codigoPlantilla) {
        this.codigoPlantilla = codigoPlantilla;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EnumProcesoPlantilla getEnumProcesoPlantilla() {
        return enumProcesoPlantilla;
    }

    public void setEnumProcesoPlantilla(EnumProcesoPlantilla enumProcesoPlantilla) {
        this.enumProcesoPlantilla = enumProcesoPlantilla;
    }

    @Override
    public String getUbicacion() {
        return enumProcesoPlantilla.getRuta();
    }

}