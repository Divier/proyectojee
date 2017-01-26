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
     * Recibe un objeto Resoluble (ver interfaz Resoluble) con los datos necesarios para la resoluci�n.
     * 
     * Realiza la generaci�n del consecutivo o n�mero de resoluci�n y la creaci�n del documento asociado.
     * 
     * Cuando ocurre un error al generar el documento, debe guardar la resoluci�n con los siguientes campos vac�os: a�o de resoluci�n, fecha de
     * resoluci�n, n�mero de resoluci�n, tipo de resoluci�n, estado de resoluci�n.
     * 
     * @param resoluble
     * @param datosDocumento
     * @return Retorna el ID de la resoluci�n generada.
     * @author julio.pinzon
     */
    public long generarResolucion(Resoluble resoluble);

}