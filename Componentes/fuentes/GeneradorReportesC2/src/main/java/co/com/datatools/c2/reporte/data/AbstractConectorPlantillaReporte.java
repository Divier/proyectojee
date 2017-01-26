package co.com.datatools.c2.reporte.data;

/**
 * Clase super de los diferentes origenes de datos para las plantillas de reportes
 * 
 * @author felipe.martinez
 */
public abstract class AbstractConectorPlantillaReporte {
    /**
     * Inicializa el conector, metodo llamado para instanciacion del conector
     */
    public abstract void init();

    /**
     * Buscan una plantilla para un codigo de reporte definido
     * 
     * @param codigoReporte
     *            identificador de plantilla de reporte a buscar
     * @return informacion de la plantilla encontrada, null si no existe
     */
    public abstract PlantillaReporte findPlantilla(String codigoReporte);

}
