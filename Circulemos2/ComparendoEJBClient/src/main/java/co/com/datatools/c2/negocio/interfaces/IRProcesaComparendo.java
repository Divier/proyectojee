package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.BloqueoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.UnificacionInconsistenciasComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;

/**
 * Interfaz Remota de Porcesa comparendo
 * 
 * @author giovanni.velandia
 */
@Remote
public interface IRProcesaComparendo {

    /**
     * Consulta todos los comparendo incosistentes
     * 
     * @param consultaProcesaComparendoDTO
     * @return
     * @author giovanni.velandia
     */
    public List<ProcesaComparendoDTO> consultarProcesaComparendo(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO);

    /**
     * Se encarga de verificar si un comparendo ya fue procesado y ingresado con inconsistencias
     * 
     * @param numeroComparendo
     * @param codigoOrganismo
     * @return
     * @author giovanni.velandia
     */
    public boolean existeProcesaComparendo(String numeroComparendo, int codigoOrganismo);

    /**
     * Se encarga de enviar por correo la notificacion de inconsistencias de comparendos procesados
     * 
     * @param codigoOrganismo
     * @return organismoDTO
     * @author divier.casas
     */
    public Integer enviarInconsistenciasComparendos(OrganismoTransitoDTO organismoDTO);

    /**
     * Se encarga de actualizar la informacion de la entidad Procesa Comparendo
     * 
     * @param procesaComparendoDTO
     * 
     * @author divier.casas
     */
    public void actualizarProcesaComparendo(ProcesaComparendoDTO procesaComparendoDTO);

    /**
     * Consulta todos los comparendo rechazados
     * 
     * @param consultaProcesaComparendoDTO
     * @return
     * @author giovanni.velandia
     */
    public List<BloqueoComparendoDTO> consultarBloqueoComparendo(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO);

    /**
     * Se encarga de consultar tanto las incosistencias como los rechazos del procesamiento de la recepcion del comparendo
     * 
     * @author giovanni.velandia
     * @param consultaProcesaComparendoDTO
     * @return
     */
    public List<UnificacionInconsistenciasComparendoDTO> consularErrorComparendo(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO);

    /**
     * Se encarga de unificar las dos listas de errores del procesamiento de la recepcion del comparendo
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTOs
     * @param bloqueoComparendoDTOs
     * @return
     */
    public List<UnificacionInconsistenciasComparendoDTO> unificacionInconsistencias(
            List<ProcesaComparendoDTO> procesaComparendoDTOs, List<BloqueoComparendoDTO> bloqueoComparendoDTOs);

    /**
     * Se encarga de actualizar la informacion de la entidad Bloqueo Comparendo
     * 
     * @param bloqueoComparendoDTO
     * 
     * @author divier.casas
     */
    public void actualizarBloqueoComparendo(BloqueoComparendoDTO bloqueoComparendoDTO);

    /**
     * Se encarga de consultar tanto las inconsistencias como los rechazos del procesamiento de la recepcion del comparendo para el reporte
     * 
     * @author diego.lozano
     * @param consultaProcesaComparendoDTO
     * @return
     */
    public List<UnificacionInconsistenciasComparendoDTO> consularErrorComparendoReporte(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO);
}