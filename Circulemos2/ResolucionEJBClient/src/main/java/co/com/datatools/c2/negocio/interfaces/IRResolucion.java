package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ResolucionDTO;

/**
 * Interface que ofrece los servicios de resolucion
 * 
 * @author julio.pinzon
 * 
 */
@Remote
public interface IRResolucion {

    /**
     * Actualiza la resolucion indicada. Valida que no sea nula y que exista.
     * 
     * @param resolucion
     *            resolucion con sus respectivos datos a ser modifciada
     * @author luis.forero(2016-02-02)
     */
    public void actualizarResolucion(ResolucionDTO resolucion);

    /**
     * Permite consultar resoluciones por los diversos filtros de busquedas indicados en el objeto resolucion.
     * 
     * @param resolucion
     * @return resoluciones
     */
    public List<ResolucionDTO> consultarResolucion(ResolucionDTO resolucion);

    /**
     * Servicio para generar resoluciones.
     * 
     * Recibe un objeto Resoluble (ver interfaz Resoluble) con los datos necesarios para la resolución.
     * 
     * Realiza la generación del consecutivo o número de resolución y la creación del documento asociado.
     * 
     * Cuando ocurre un error al generar el documento, debe guardar la resolución con los siguientes campos vacíos: año de resolución, fecha de
     * resolución, número de resolución, tipo de resolución, estado de resolución.
     * 
     * @param resoluble
     * @param datosDocumento
     * @return Retorna el ID de la resolución generada.
     * @author julio.pinzon
     */
    public long generarResolucion(Resoluble resoluble);

}