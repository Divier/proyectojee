package co.com.datatools.c2.negocio.opciones;

import java.io.Serializable;

import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * Clase que se encarga de definir el supertipo de clase que sabe como escribi cada archivo
 * 
 * @author sergio.torres (03-feb-2016)
 * 
 */
public interface ProcesadorArchivos extends Serializable {

    public void procesarArchivo(ArchivoTransportableDTO archivo) throws CirculemosAlertaException;

    public GestorArchivosOpciones getOpcion();

    public void setOpcion(GestorArchivosOpciones opcion);

    /**
     * Obtiene un archivo guardado por filesystem
     * 
     * @param directorio
     * @param nombre
     * @return
     * @throws CirculemosAlertaException
     * @author julio.pinzon
     */
    public byte[] obtenerArchivo(String directorio, String nombre) throws CirculemosAlertaException;

}
