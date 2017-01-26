package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RegistraProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;

@Local
public interface ILProceso {

    /**
     * @see IRProceso#existeProceso(ProcesoDTO)
     */
    public boolean existeProceso(ProcesoDTO proceso);

    /**
     * @see IRProceso#crearProceso(RegistraProcesoDTO)
     */
    public ProcesoDTO crearProceso(RegistraProcesoDTO registra);

    /**
     * @see IRProceso#actualizarEstadoProceso(Long,EnumEstadoProceso,Boolean)
     */
    public TrazabilidadProcesoDTO actualizarEstadoProceso(Long idProceso, EnumEstadoProceso estado,
            Boolean cerrarProceso);

    /**
     * @see IRProceso#consultarEstadoProceso(Long)
     */
    public EstadoProcesoDTO consultarEstadoProceso(Long idProceso);

    /**
     * @see IRProceso#consultarProceso(ProcesoDTO)
     */
    public List<ProcesoDTO> consultarProceso(ProcesoDTO proceso);

    /**
     * @see IRProceso#registrarDocumento(DocumentoProcesoDTO)
     */
    public DocumentoProcesoDTO registrarDocumento(DocumentoProcesoDTO documento);

    /**
     * @see IRProceso#consultarTrazabilidad(TrazabilidadProcesoDTO)
     */
    public List<TrazabilidadProcesoDTO> consultarTrazabilidad(TrazabilidadProcesoDTO trazabilidad);

    /**
     * @see IRProceso#consultarParticipantesProceso(ParticipanteProcesoDTO)
     */
    public List<ParticipanteProcesoDTO> consultarParticipantesProceso(ParticipanteProcesoDTO criterios);

    /**
     * @see IRProceso#consultarProceso(Long)
     */
    public ProcesoDTO consultarProceso(Long idProceso);
}