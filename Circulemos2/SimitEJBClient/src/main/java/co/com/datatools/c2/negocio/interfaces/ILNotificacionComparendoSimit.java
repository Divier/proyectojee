package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ArchivoNotificacionComparendoDTO;
import co.com.datatools.c2.dto.HomologacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.NotificacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.NotificacionSimitDTO;
import co.com.datatools.c2.dto.ValidacionComparendoSimitDTO;
import co.com.datatools.c2.enumeraciones.EnumOpcionGeneracionArchivo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILNotificacionComparendoSimit {

    /**
     * @see IRNotificacionComparendoSimit#consultarNotificacionesSimit(int, Date, Date, int)
     */
    public List<NotificacionSimitDTO> consultarNotificacionesSimit(int codigoOrganismo, Date fechaInicial,
            Date fechaFinal, int tipoDocumentoEnvioSimit) throws CirculemosNegocioException;

    /**
     * @see IRNotificacionComparendoSimit#generarNotificacionComparendo(int, EnumOpcionGeneracionArchivo)
     */
    public List<ArchivoNotificacionComparendoDTO> generarNotificacionComparendo(int codigoOrganismo,
            EnumOpcionGeneracionArchivo... opciones) throws CirculemosNegocioException;

    /**
     * @see IRNotificacionComparendoSimit#registrarArchivoNotificacionComparendo(int)
     */
    void registrarArchivoNotificacionComparendo(int codigoOrganismo) throws CirculemosNegocioException;

    /**
     * @see IRNotificacionComparendoSimit#registrarArchivoNotificacionComparendoAutomatico(int)
     */
    void registrarArchivoNotificacionComparendoAutomatico(int codigoOrganismo) throws CirculemosNegocioException;

    /**
     * @see IRNotificacionComparendoSimit#validarComparendo(ValidacionComparendoSimitDTO)
     */
    HomologacionComparendoSIMITDTO validarComparendo(ValidacionComparendoSimitDTO validacionComparendoDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRNotificacionComparendoSimit#notificarComparendo(NotificacionComparendoSIMITDTO)
     */
    boolean notificarComparendo(NotificacionComparendoSIMITDTO notificacion) throws CirculemosNegocioException;
}
