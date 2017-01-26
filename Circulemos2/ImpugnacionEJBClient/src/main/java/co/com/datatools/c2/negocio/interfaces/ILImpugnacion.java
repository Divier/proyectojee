package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.AccionImpugnacionBackDTO;
import co.com.datatools.c2.dto.ConsultaImpugnacionDTO;
import co.com.datatools.c2.dto.EncabezadoImpugnacionDTO;
import co.com.datatools.c2.dto.EvaluarImpugnacionBackDTO;
import co.com.datatools.c2.dto.EvaluarImpugnacionDTO;
import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.dto.HistoricoFalloDTO;
import co.com.datatools.c2.dto.MotivacionImpugnacionDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RadicarExpedienteDTO;
import co.com.datatools.c2.dto.RechazoImpugnacionDTO;
import co.com.datatools.c2.dto.RespuestaImpugnacionDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILImpugnacion {

    /**
     * @see IRImpugnacion#consultarImpugnacion(ConsultaImpugnacionDTO)
     */
    public List<RespuestaImpugnacionDTO> consultarImpugnacion(ConsultaImpugnacionDTO consulta)
            throws CirculemosNegocioException;

    /**
     * @see IRImpugnacion#radicarExpediente(RadicarExpedienteDTO)
     */
    public RadicarExpedienteDTO radicarExpediente(RadicarExpedienteDTO expediente) throws CirculemosNegocioException;

    /**
     * @see IRImpugnacion#consultarEncabezado(ProcesoDTO)
     */
    public EncabezadoImpugnacionDTO consultarEncabezado(ProcesoDTO proceso);

    /**
     * @see IRImpugnacion#evaluarExpediente(EvaluarImpugnacionDTO)
     */
    public EvaluarImpugnacionDTO evaluarExpediente(EvaluarImpugnacionDTO evaluar) throws CirculemosNegocioException;

    /**
     * @see IRImpugnacion#registrarFallo(FalloImpugnacionDTO)
     */
    public FalloImpugnacionDTO registrarFallo(FalloImpugnacionDTO fallo) throws CirculemosNegocioException;

    /**
     * @see IRImpugnacion#rechazarFallo(ProcesoDTO, RechazoImpugnacionDTO)
     */
    public RechazoImpugnacionDTO rechazarFallo(ProcesoDTO proceso, RechazoImpugnacionDTO rechazoImpugnacionDTO);

    /**
     * @see IRImpugnacion#aprobarImpugnacion(ProcesoDTO, Long)
     */
    public TrazabilidadProcesoDTO aprobarImpugnacion(ProcesoDTO proceso, Long cicomparendo)
            throws CirculemosNegocioException;

    /**
     * @see IRImpugnacion#evaluarExpedienteBack(EvaluarImpugnacionBackDTO,Long)
     */
    public EvaluarImpugnacionBackDTO evaluarExpedienteBack(EvaluarImpugnacionBackDTO evaluar, Long idProceso)
            throws CirculemosNegocioException;

    /**
     * @see IRImpugnacion#registrarAccionesBack(List<AccionImpugnacionBackDTO>, Long)
     */
    public void registrarAccionesBack(List<AccionImpugnacionBackDTO> acciones, Long idProceso)
            throws CirculemosNegocioException;

    /**
     * @see IRImpugnacion#consultarAccionesBack(Long)
     */
    public List<AccionImpugnacionBackDTO> consultarAccionesBack(Long idProceso);

    /**
     * @see IRImpugnacion#consultarUltimoFalloImpugnacion(Long)
     */
    public FalloImpugnacionDTO consultarUltimoFalloImpugnacion(Long idProceso);

    /**
     * @see IRImpugnacion#consultarHistroricoFallos(Long)
     */
    public List<HistoricoFalloDTO> consultarHistroricoFallos(Long idProceso);

    /**
     * @see IRImpugnacion#consultarMotivacionIpugnacion(Long)
     * @param idProceso
     * @return
     */
    public MotivacionImpugnacionDTO consultarMotivacionIpugnacion(Long idProceso);

}
