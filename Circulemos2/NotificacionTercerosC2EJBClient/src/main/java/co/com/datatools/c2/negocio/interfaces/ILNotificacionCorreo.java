package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ConfiguracionEmailDTO;
import co.com.datatools.c2.dto.ConsultarEvidenciasNotificacionDTO;
import co.com.datatools.c2.dto.DetalleNotificacionDTO;
import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.EvidenciaNotificacionDTO;
import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.dto.NotificacionDTO;
import co.com.datatools.c2.dto.RespuestaConsultaCircuitoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.NotificacionException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Local
public interface ILNotificacionCorreo {

    public Integer[] enviarNotificaciones(EnvioNotificacionDTO notificaciones);

    public NotificacionDTO guardarNotificacion(NotificacionDTO notificacion);

    public DetalleNotificacionDTO guardarDetalleNotificacion(DetalleNotificacionDTO detalleNotificacion);

    public LogEnvioCorreoDTO guardarTraza(String asunto, String contenido, Integer codigoOrganismo,
            ConfiguracionEmailDTO configuracion, EnumTipoCorreo tipoCorreo, List<ArchivoTransportableDTO> adjuntos,
            String[] direccionesEnvio);

    public DetalleNotificacionDTO actualizarDetalleNotificacion(DetalleNotificacionDTO detalleNotificacion);

    /**
     * @see IRNotificacionCorreo#consultarNotificaciones()
     */
    public Integer[] consultarNotificaciones();

    /**
     * @see IRNotificacionCorreo#consultarDetalleNotificacion()
     */
    public List<DetalleNotificacionDTO> consultarDetalleNotificacion();

    /**
     * @see IRNotificacionCorreo#procesarNotificacion(DetalleNotificacionDTO)
     */
    public void procesarNotificacion(DetalleNotificacionDTO detNotifDTO, RespuestaConsultaCircuitoDTO consultaCircuito)
            throws NotificacionException, CirculemosAlertaException;

    /**
     * @see IRNotificacionCorreo#consultarEvidencias(ConsultarEvidenciasNotificacionDTO)
     */
    public List<EvidenciaNotificacionDTO> consultarEvidencias(ConsultarEvidenciasNotificacionDTO consulta);

    /**
     * @see IRNotificacionCorreo#consultarParametroEnvioNotificaciones(int)
     */
    public ValorParametroDTO consultarParametroEnvioNotificaciones(int codigoOrganismo);
}