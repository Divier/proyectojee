package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Interfaz Local de recibir comparendo
 * 
 * @author giovanni.velandia
 */
@Local
public interface ILRecibirComparendo {

    /**
     * @see IRRecibirComparendo#recibirComparendo(ProcesarComparendoDTO)
     */
    public RespuestaValidacionDTO recibirComparendo(ProcesarComparendoDTO procesarComparendoDTO)
            throws CirculemosNegocioException;
}
