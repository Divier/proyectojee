package co.com.datatools.c2.negocio.fachada;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ConsultaResolucionesDTO;
import co.com.datatools.c2.dto.ResolucionDTO;
import co.com.datatools.c2.negocio.interfaces.Resoluble;

/**
 * Fachada que ofrece los servicios para el manejo de Resoluciones.
 * 
 * @author julio.pinzon
 */
@Remote
public interface IRFachadaAdminResolucion {

    /**
     * Actualiza la resoluci�n indicada. Valida que no sea nula.
     * 
     * @param resoluci�n
     */
    public void actualizarResolucion(ResolucionDTO resoluci�n);

    /**
     * Registra la resoluci�n con la informaci�n del Resoluble indicado y genera el documento acorde al tipo de resoluci�n indicado.
     * 
     * @param resolucion
     * @return Retorna el identificador de la resoluci�n registrada.
     * @author julio.pinzon
     */
    public long registrarResolucion(Resoluble resolucion);

    /**
     * Permite consultar resoluciones por los diversos filtros de busquedas indicados en el objeto resolucion.
     * 
     * @param consulta
     * @return resoluciones
     */
    public List<ResolucionDTO> consultarResoluciones(ConsultaResolucionesDTO consulta);

}