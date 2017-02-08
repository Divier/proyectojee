package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

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

@Remote
public interface IRImpugnacion {

    /**
     * Metodo que consulta los datos de impugnacion de acuerdo a unos criterios
     * 
     * @param consulta
     * @return
     */
    public List<RespuestaImpugnacionDTO> consultarImpugnacion(ConsultaImpugnacionDTO consulta)
            throws CirculemosNegocioException;

    /**
     * Radica el expediente creando el proceso de impugnacion
     * 
     * @param expediente
     * @return
     */
    public RadicarExpedienteDTO radicarExpediente(RadicarExpedienteDTO expediente) throws CirculemosNegocioException;

    /**
     * Realiza la consulta para crear el encabezado
     * 
     * @param proceso
     * @return
     */
    public EncabezadoImpugnacionDTO consultarEncabezado(ProcesoDTO proceso);

    /**
     * Evalua y determina la decision sobre el expediente
     * 
     * @param evaluar
     * @return EvaluarImpugnacionDTO persistido
     * @throws CirculemosNegocioException
     *             JUR_004001: El estado del proceso es diferente a IMPUGNADO <br>
     * @author dixon.alvarez
     */
    public EvaluarImpugnacionDTO evaluarExpediente(EvaluarImpugnacionDTO evaluar) throws CirculemosNegocioException;

    /**
     * Registra el fallo ingresado
     * 
     * @param fallo
     * @return
     * @throws CirculemosNegocioException
     */
    public FalloImpugnacionDTO registrarFallo(FalloImpugnacionDTO fallo) throws CirculemosNegocioException;

    /**
     * Rechaza el fallo realizado
     * 
     * @param ProcesoDTO
     * @param Integer
     * 
     * @return
     */
    public RechazoImpugnacionDTO rechazarFallo(ProcesoDTO proceso, RechazoImpugnacionDTO rechazoImpugnacionDTO);

    /**
     * Aprueba el fallo realizado
     * 
     * @param ProcesoDTO
     * @param Long
     * @return TrazabilidadProcesoDTO
     * @throws CirculemosNegocioException
     */
    public TrazabilidadProcesoDTO aprobarImpugnacion(ProcesoDTO proceso, Long cicomparendo)
            throws CirculemosNegocioException;

    /**
     * Consulta el ultimo fallo de la impugnacion para un proceso
     * 
     * @param idProceso
     * @return Fallo, si no encuentra null
     */
    public FalloImpugnacionDTO consultarUltimoFalloImpugnacion(Long idProceso);

    /**
     * Evalua y determina la decision sobre el expediente
     * 
     * @param evaluar
     * @return
     * @throws CirculemosNegocioException
     */
    public EvaluarImpugnacionBackDTO evaluarExpedienteBack(EvaluarImpugnacionBackDTO evaluar, Long idProceso)
            throws CirculemosNegocioException;

    /**
     * Almacena las acciones adicionadas por el especialista
     * 
     * @param acciones
     * @param idProceso
     * @throws CirculemosNegocioException
     */
    public void registrarAccionesBack(List<AccionImpugnacionBackDTO> acciones, Long idProceso)
            throws CirculemosNegocioException;

    /**
     * Consulta de Acciones por Proceso
     * 
     * @param idProceso
     * @return
     */
    public List<AccionImpugnacionBackDTO> consultarAccionesBack(Long idProceso);

    /**
     * Consulta el historico de los fallos
     * 
     * @param idProceso
     * @return RechazoImpugnacionDTO
     * @author Jeison.Rodriguez (2016-09-09)
     */
    public List<HistoricoFalloDTO> consultarHistroricoFallos(Long idProceso);

    /**
     * Consultar motivacion impugnaciones
     * 
     * @author giovanni.velandia
     * @param idProceso
     * @return
     */
    public MotivacionImpugnacionDTO consultarMotivacionIpugnacion(Long idProceso);

    /**
     * Envia el correo de evaluacion de impugnacion
     * 
     * @param idProceso
     * @param idDocumento
     * @param idTraza
     * @throws CirculemosNegocioException
     */
    public void enviarCorreoEvaluarImpugnacion(Long idProceso, Long idDocumento, Long idTraza)
            throws CirculemosNegocioException;

    /**
     * Envia el correo del fallo de impugnacion
     * 
     * @param idProceso
     * @param ciComparendo
     * @param trazaProceso
     * @throws CirculemosNegocioException
     */
    public void enviarCorreoFalloImpugnacion(Long idProceso, Long ciComparendo, TrazabilidadProcesoDTO trazaProceso)
            throws CirculemosNegocioException;
}