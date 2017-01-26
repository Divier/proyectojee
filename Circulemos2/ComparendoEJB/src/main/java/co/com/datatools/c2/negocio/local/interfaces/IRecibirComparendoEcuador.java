package co.com.datatools.c2.negocio.local.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaRecibirComparendoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface IRecibirComparendoEcuador {

    /**
     * 
     * Metodo encargado de recibir la informacion de un comparendo a validar, crear, modificar y rectificar comparendos // Este metodo debe recibir la
     * lista de las personas con el tipo de persona asignado el resto de informacion si puede ir vacia, no se debe ingresar una lista vacia por que de
     * esta forma no se puede realizar las respectivas validaciones, las personas que dene venir llenas son Propietario, Infractor, Testigo, Empresa.
     * 
     * @param procesarComparendoDTO
     * @return
     * @throws CirculemosNegocioException
     */
    public RespuestaRecibirComparendoDTO procesarRecibirComparendo(ProcesarComparendoDTO procesarComparendoDTO)
            throws CirculemosNegocioException;
}
