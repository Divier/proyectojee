/**
 * 
 */
package co.com.datatools.c2.negocio.opciones;

import java.io.Serializable;

/**
 * Clase que permite definir las opciones adicionales para el gestor de archivos
 * 
 * @author sergio.torres
 * 
 */
public abstract class GestorArchivosOpciones implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private EnumTipoOpcionGestorArchivos tipoOpcion;
    private ProcesadorArchivos procesador;

    public GestorArchivosOpciones(EnumTipoOpcionGestorArchivos tipoOpcion, ProcesadorArchivos procesador) {
        this.tipoOpcion = tipoOpcion;
        this.procesador = procesador;
    }

    public EnumTipoOpcionGestorArchivos getTipoOpcion() {
        return tipoOpcion;
    }

    public ProcesadorArchivos getProcesador() {
        return procesador;
    }

}
