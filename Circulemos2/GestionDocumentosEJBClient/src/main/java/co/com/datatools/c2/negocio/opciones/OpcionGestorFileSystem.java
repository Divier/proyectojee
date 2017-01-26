/**
 * 
 */
package co.com.datatools.c2.negocio.opciones;

/**
 * Clase que permite escribir en una ruta relativa del servidor de aplicaciones donde se tienen permisos
 * 
 * @author sergio.torres (03-febrero-2016)
 * 
 */
public class OpcionGestorFileSystem extends GestorArchivosOpciones {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String ubicacion;

    public OpcionGestorFileSystem() {
        super(EnumTipoOpcionGestorArchivos.FILE_SYSTEM, new ProcesadorArchivoFileSystem());
        getProcesador().setOpcion(this);
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}
