package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RegistraProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;

/**
 * Interface que ofrece los servicios de proceso
 * 
 * @author julio.pinzon
 * 
 */
@Remote
public interface IRProceso {

    /**
     * Valida si existe un proceso con las características del proceso indicado.
     * 
     * @param proceso
     * @return Retorna true si existe un proceso con las características del proceso indicado. false de lo contrario.
     * @author julio.pinzon 15/03/2016
     */
    public boolean existeProceso(ProcesoDTO proceso);

    /**
     * Crea un proceso con los parametros ingresados
     * 
     * @param registra
     * @return
     */
    public ProcesoDTO crearProceso(RegistraProcesoDTO registra);

    /**
     * Actualiza el estado de un proceso
     * 
     * @param idProceso
     * @param estado
     * @param cerrarProceso
     */
    public TrazabilidadProcesoDTO actualizarEstadoProceso(Long idProceso, EnumEstadoProceso estado,
            Boolean cerrarProceso);

    /**
     * Consulta el estado de un proceso
     * 
     * @param idProceso
     * @return
     */

    public EstadoProcesoDTO consultarEstadoProceso(Long idProceso);

    /**
     * Consulta un proceso de acuerdo a unos parametros
     * 
     * @param proceso
     * @return
     */
    public List<ProcesoDTO> consultarProceso(ProcesoDTO proceso);

    /**
     * Registra un documento al proceso
     * 
     * @param documento
     * @return
     */
    public DocumentoProcesoDTO registrarDocumento(DocumentoProcesoDTO documento);

    /**
     * Consulta una trazabilidad de acuerdo a los criterios
     * 
     * @param trazabilidad
     * @return
     */
    public List<TrazabilidadProcesoDTO> consultarTrazabilidad(TrazabilidadProcesoDTO trazabilidad);

    /**
     * Consulta los participantes del proceso de acuerdo a unos criterios
     * 
     * @return
     */
    public List<ParticipanteProcesoDTO> consultarParticipantesProceso(ParticipanteProcesoDTO criterios);

    /**
     * Consulta el proceso por id
     * 
     * @param idProceso
     * @return Proceso consultado
     * @author julio.pinzon 2016-07-06
     */
    public ProcesoDTO consultarProceso(Long idProceso);
}