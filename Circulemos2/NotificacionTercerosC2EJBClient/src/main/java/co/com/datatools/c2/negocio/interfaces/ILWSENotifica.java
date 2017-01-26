package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.CreacionCircuitoDTO;
import co.com.datatools.c2.dto.DetalleNotificacionDTO;
import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.RespuestaConsultaCircuitoDTO;
import co.com.datatools.c2.dto.RespuestaConsultaEvidenciaDTO;
import co.com.datatools.c2.dto.RespuestaCreacionCircuitoDTO;
import co.com.datatools.c2.excepciones.NotificacionException;

@Local
public interface ILWSENotifica {

    /**
     * @see IRWSENotifica#firmarDocumento(byte[])
     */
    public byte[] firmarDocumento(byte[] archivo) throws NotificacionException;

    /**
     * @see IRWSENotifica#autenticar()
     */
    public String autenticar() throws NotificacionException;

    /**
     * @see IRWSENotifica#crearCircuito(CreacionCircuitoDTO)
     */
    public RespuestaCreacionCircuitoDTO crearCircuito(CreacionCircuitoDTO cCircuitoDTO) throws NotificacionException;

    /**
     * Guardar log de la ejecucion del WS
     * 
     * @param log
     * @return
     * @author divier.casas 2016-09-22
     */
    public LogEjecucionWSDTO guardarLogWS(LogEjecucionWSDTO log);

    /**
     * @see IRWSENotifica#consultarCircuito(String)
     */
    public RespuestaConsultaCircuitoDTO consultarCircuito(String idExternal) throws NotificacionException;

    /**
     * @see IRWSENotifica#consultarEvidencias(DetalleNotificacionDTO)
     */
    public List<RespuestaConsultaEvidenciaDTO> consultarEvidencias(DetalleNotificacionDTO detNotifDTO)
            throws NotificacionException;

    /**
     * @see IRWSENotifica#obtenerEvidencias(RespuestaConsultaEvidenciaDTO)
     */
    public byte[] obtenerEvidencias(RespuestaConsultaEvidenciaDTO rConsultaEv) throws NotificacionException;
}