package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Interfaz Remota de recibir comparendo
 * 
 * @author giovanni.velandia
 */
@Remote
public interface IRRecibirComparendo {

    /**
     * Metodo que se encarga del procesamiento de recibir un comparendo
     * 
     * @param procesaComparendoDTO
     *            informacion del comparendo a preocesar
     * @return RespuestaValidacionDTO
     * @author felipe.martinez giovanni.velandia(mod 2015-10-05)
     */
    public RespuestaValidacionDTO recibirComparendo(ProcesarComparendoDTO procesarComparendoDTO)
            throws CirculemosNegocioException;

    /**
     * Metodo que se encarga de verificar la existencia de un comparendo o un procesa comparendo en el sistema
     * 
     * @param numeroComparendo
     * @param codigoOrganismo
     * @return
     * @author giovanni.velandia
     */
    public boolean comparendoIngresado(String numeroComparendo, Integer codigoOrganismo);

    /**
     * Realiza las validaciones para poder procesar un comparendo que está en proceso de rectificación.
     * 
     * @param cicomparendo
     * @return
     * @author giovanni.velandia<br>
     *         luis(mod 2016-01-29)
     */
    public RespuestaValidacionDTO validarRectificaComparendo(Long cicomparendo);

    /**
     * Rectifica el comparendo recibido con los valores modificados recibidos. Valida que cumpla con las restricciones para ser rectificado, actualiza
     * el comparendo y genera una resolución de sanción de rectificacion, en caso de tener una vigente, la cambia a estado anulada.
     * 
     * @param comparendoRectificadoDTO
     *            comparendo a ser rectificado junto con sus campos respectivos cambiados.
     * @return respuesta de validaciones efectuadas en el proceso de rectificacion.
     * @exception CirculemosNegocioException
     * @author luis.forero(2016-02-01)
     */
    public RespuestaValidacionDTO rectificarComparendo(ProcesaComparendoRectificadoDTO comparendoRectificadoDTO)
            throws CirculemosNegocioException;

}
