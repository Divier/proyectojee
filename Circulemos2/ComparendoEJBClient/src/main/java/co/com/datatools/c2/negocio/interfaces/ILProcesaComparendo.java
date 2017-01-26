package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comparendo.BloqueoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.UnificacionInconsistenciasComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;

/**
 * Interfaz Local de Procesa comparendo
 * 
 * @author giovanni.velandia
 */
@Local
public interface ILProcesaComparendo {

    /**
     * @see IRProcesaComparendo#consultarProcesaComparendo(ConsultaProcesaComparendoDTO)
     */
    public List<ProcesaComparendoDTO> consultarProcesaComparendo(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO);

    /**
     * @see IRProcesaComparendo#existeProcesaComparendo(String, int)
     */
    public boolean existeProcesaComparendo(String numeroComparendo, int codigoOrganismo);

    /**
     * Consulta un procesa comparendo por id y retorna el resultado en una entidad
     * 
     * @param idProcesaComparendo
     * @return
     * @author giovanni.velandia
     */
    public ProcesaComparendoDTO consultarProcesaComparendo(Long idProcesaComparendo);

    /**
     * Actualiza el procesa comparendo
     * 
     * @param procesarComparendoDTO
     * @author giovanni.velandia
     */
    public void actualizarProcesaComparendo(ProcesaComparendoDTO procesaComparendoDTO);

    /**
     * @see IRProcesaComparendo#enviarInconsistenciasComparendos(OrganismoTransitoDTO)
     */
    public Integer enviarInconsistenciasComparendos(OrganismoTransitoDTO organismoDTO);

    /**
     * 
     * @see IRProcesaComparendo#consultarBloqueoComparendo(ConsultaProcesaComparendoDTO)
     */
    public List<BloqueoComparendoDTO> consultarBloqueoComparendo(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO);

    /**
     * @see IRProcesaComparendo#consularErrorComparendo(ConsultaProcesaComparendoDTO)
     */
    public List<UnificacionInconsistenciasComparendoDTO> consularErrorComparendo(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO);

    /**
     * @see IRProcesaComparendo#unificacionInconsistencias(List, List)
     */
    public List<UnificacionInconsistenciasComparendoDTO> unificacionInconsistencias(
            List<ProcesaComparendoDTO> procesaComparendoDTOs, List<BloqueoComparendoDTO> bloqueoComparendoDTOs);

    /**
     * 
     * @see IRProcesaComparendo#actualizarBloqueoComparendo(BloqueoComparendoDTO)
     */
    public void actualizarBloqueoComparendo(BloqueoComparendoDTO bloqueoComparendoDTO);

    /**
     * 
     * @see IRProcesaComparendo#consularErrorComparendoReporte(ConsultaProcesaComparendoDTO)
     */
    public List<UnificacionInconsistenciasComparendoDTO> consularErrorComparendoReporte(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO);
}
