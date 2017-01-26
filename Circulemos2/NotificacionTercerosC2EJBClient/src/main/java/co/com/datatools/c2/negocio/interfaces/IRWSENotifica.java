package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.CreacionCircuitoDTO;
import co.com.datatools.c2.dto.DetalleNotificacionDTO;
import co.com.datatools.c2.dto.RespuestaConsultaCircuitoDTO;
import co.com.datatools.c2.dto.RespuestaConsultaEvidenciaDTO;
import co.com.datatools.c2.dto.RespuestaCreacionCircuitoDTO;
import co.com.datatools.c2.excepciones.NotificacionException;

@Remote
public interface IRWSENotifica {

    /**
     * Permite firmar documento de notificacion
     * 
     * @param archivo
     * @return
     * @throws NotificacionException
     * @author divier.casas
     */
    public byte[] firmarDocumento(byte[] archivo) throws NotificacionException;

    /**
     * Permite autenticarse al servicio web de e-notifica para la obtencion del token de seguridad
     * 
     * @throws NotificacionException
     * @return
     * @author divier.casas
     */
    public String autenticar() throws NotificacionException;

    /**
     * Permite enviar al servicio web de e-notifica la creacion de un circuito
     * 
     * @param cCircuitoDTO
     * @return
     * @throws NotificacionException
     * @author divier.casas
     */
    public RespuestaCreacionCircuitoDTO crearCircuito(CreacionCircuitoDTO cCircuitoDTO) throws NotificacionException;

    /**
     * Permite consultar el estado de notificacion de un circuito
     * 
     * @param idExternal
     * @return
     * @throws NotificacionException
     * @author divier.casas
     */
    public RespuestaConsultaCircuitoDTO consultarCircuito(String idExternal) throws NotificacionException;

    /**
     * Permite consultar los id de las evidencias de las notificaciones
     * 
     * @param detNotifDTO
     * @return
     * @throws NotificacionException
     * @author divier.casas
     */
    public List<RespuestaConsultaEvidenciaDTO> consultarEvidencias(DetalleNotificacionDTO detNotifDTO)
            throws NotificacionException;

    /**
     * Permite obtener el archivo asociado a una evidencia
     * 
     * @param detNotifDTO
     * @return
     * @throws NotificacionException
     * @author divier.casas
     */
    public byte[] obtenerEvidencias(RespuestaConsultaEvidenciaDTO rConsultaEv) throws NotificacionException;
}