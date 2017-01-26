package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.HomologacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.common.RespuestaConsultaComparendoDTO;
import co.com.datatools.c2.dto.common.RespuestaNotificacionDTO;

/**
 * @see IRClienteWSSIMIT
 * 
 * @author luis.forero(2016-03-29)
 * 
 */
@Local
public interface ILClienteWSSIMIT {

    /**
     * @see IRClienteWSSIMIT#notificarComparendoSIMIT(HomologacionComparendoSIMITDTO)
     */
    RespuestaNotificacionDTO notificarComparendoSIMIT(HomologacionComparendoSIMITDTO comparendo);

    /**
     * @see IRClienteWSSIMIT#consultarComparendos(Integer, String)
     */
    RespuestaConsultaComparendoDTO consultarComparendos(Integer idTipoIdentificacion, String numeroIdentificacion);
}
