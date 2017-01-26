package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ConsultarEvidenciasNotificacionDTO;
import co.com.datatools.c2.dto.DetalleNotificacionDTO;
import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.EvidenciaNotificacionDTO;
import co.com.datatools.c2.dto.RespuestaConsultaCircuitoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.NotificacionException;

/**
 * Ofrece el servicio de notificacion a terceros de las acciones realizadas a un comparendo. Almacena la notificacion a realizar y en caso de tener
 * fallo almacena el fallo para realizarlo nuevamente en caso de ser requerido.
 * 
 * @author rodrigo.cruz
 * 
 */
@Remote
public interface IRNotificacionCorreo {

    public Integer[] enviarNotificaciones(EnvioNotificacionDTO notificaciones);

    /**
     * Permite consultar el estado de notificacion de un circuito
     * 
     * @author divier.casas
     * @return
     */
    public Integer[] consultarNotificaciones();

    /**
     * Permite consultar el detalle de notificaciones
     * 
     * @return
     */
    public List<DetalleNotificacionDTO> consultarDetalleNotificacion();

    /**
     * Permite procesar las notificaciones
     * 
     * @param detNotifDTO
     * @param consultaCircuito
     */
    public void procesarNotificacion(DetalleNotificacionDTO detNotifDTO, RespuestaConsultaCircuitoDTO consultaCircuito)
            throws NotificacionException, CirculemosAlertaException;

    /**
     * Permite consultar las evidencias asociadas a una notificacion
     * 
     * @param consulta
     * 
     * @return
     * @author divier.casas
     */
    public List<EvidenciaNotificacionDTO> consultarEvidencias(ConsultarEvidenciasNotificacionDTO consulta);

    /**
     * Permite consultar el parametro de configuracion para determinar si una notificacion se envia por un tercero o no.
     * 
     * @param codigoOrganismo
     * @return
     * @author divier.casas
     */
    public ValorParametroDTO consultarParametroEnvioNotificaciones(int codigoOrganismo);
}