package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.DetalleFuenteConsultaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaReincidenciaDTO;
import co.com.datatools.c2.dto.comparendo.HistoricoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaReincidenciaDTO;
import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;
import co.com.datatools.c2.dto.configuracion.ValoresBusquedaReincidenciaDTO;
import co.com.datatools.c2.enumeraciones.EnumConfiguracion;
import co.com.datatools.c2.enumeraciones.EnumFuenteConsulta;
import co.com.datatools.c2.enumeraciones.EnumTipoFuenteConsulta;
import co.com.datatools.c2.enumeraciones.EnumTipoReincidencia;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.helpers.extencion.ReincidenciaHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILComparendo;
import co.com.datatools.c2.negocio.interfaces.ILReincidencia;
import co.com.datatools.c2.negocio.interfaces.IRReincidencia;
import co.com.datatools.c2.negocio.interfaces.administracion.ILTarifaInfraccion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.date.UtilFecha;

/**
 * Implementa los servicios relacionados a reincidencias de infractor expuestos por {@link IReincidencia}
 */
@Stateless(mappedName = "ReincidenciaEJB")
@LocalBean
public class ReincidenciaEJB implements IRReincidencia, ILReincidencia {

    private final static Logger LOGGER = Logger.getLogger(ReincidenciaEJB.class.getName());

    @PersistenceContext(name = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminGeneral iFachadaAdminGeneral;
    @EJB
    private IRFachadaConfiguracion iFachadaConfiguracion;
    @EJB
    private ILComparendo comparendoEJB;
    @EJB
    private ILTarifaInfraccion tarifaInfracionEJB;

    public ReincidenciaEJB() {
        LOGGER.debug("ReincidenciaEJB()");
    }

    @Override
    public ResultadoConsultaReincidenciaDTO consultarReincidencias(ConsultaReincidenciaDTO consultaReincidencia)
            throws CirculemosNegocioException {
        LOGGER.debug("ReincidenciaEJB::consultarReincidencias(ConsultaReincidenciaDTO)");

        Integer codigoOrganismo = checkNotNull(consultaReincidencia.getCodigoOrganismo(),
                "Código de organismo es obligatorio");
        Integer idTipoDocumento = checkNotNull(consultaReincidencia.getIdTipoDocumento(),
                "Tipo de documento es obligatorio");
        String numeroDocumento = checkNotNull(consultaReincidencia.getNumeroDocumento(),
                "Número de documento es obligatorio");
        EnumTipoReincidencia tipoReincidencia = checkNotNull(consultaReincidencia.getTipoReincidencia(),
                "Tipo de reincidencia es obligatorio");

        // Consultar configuracion de reincidencias
        ValoresBusquedaReincidenciaDTO configuracionDTO = new ValoresBusquedaReincidenciaDTO();
        configuracionDTO.setCodigoOrganismo(Arrays.asList(String.valueOf(codigoOrganismo)));
        configuracionDTO.setTipoReincidencia(Arrays.asList(String.valueOf(tipoReincidencia.getId())));

        configuracionDTO = iFachadaConfiguracion.consultarValorConfiguracion(
                EnumConfiguracion.VALORES_BUSQUEDA_REINCIDENCIA.getCodigo(), configuracionDTO);

        ResultadoConsultaReincidenciaDTO resultado = new ResultadoConsultaReincidenciaDTO();
        resultado.setComparendos(new ArrayList<HistoricoComparendoDTO>());
        Map<String, HistoricoComparendoDTO> historicoComparendos = new HashMap<>();
        List<HistoricoComparendoDTO> resultadosFuente = new ArrayList<>();

        // Consultar detalles de fuentes de consulta por prioridad
        List<DetalleFuenteConsultaDTO> detalleFuentesConsultaDTOList = iFachadaAdminGeneral
                .consultarDetalleFuenteConsulta(codigoOrganismo, EnumTipoFuenteConsulta.REINCIDENCIA.getId());

        for (DetalleFuenteConsultaDTO detalleFuenteConsultaDTO : detalleFuentesConsultaDTOList) {
            if (detalleFuenteConsultaDTO.getFuenteConsulta().getEstado()) {
                // Validar prioridad y consultar fuentes
                EnumFuenteConsulta fuenteConsulta = Utilidades.buscarElemEnum(EnumFuenteConsulta.class,
                        detalleFuenteConsultaDTO.getFuenteConsulta().getId());

                switch (fuenteConsulta) {
                case SIMIT:
                    resultadosFuente = consultarReincidenciasFuenteSimit(codigoOrganismo, idTipoDocumento,
                            numeroDocumento, configuracionDTO);
                    break;
                case CIRCULEMOS_1:
                    resultadosFuente = consultarReincidenciasFuenteCirculemos1(codigoOrganismo, idTipoDocumento,
                            numeroDocumento, configuracionDTO);
                    break;
                case CIRCULEMOS_2:
                    resultadosFuente = consultarReincidenciasFuenteCirculemos2(codigoOrganismo, idTipoDocumento,
                            numeroDocumento, configuracionDTO);
                    break;
                case RUNT:
                    resultadosFuente = consultarReincidenciasFuenteRunt(codigoOrganismo, idTipoDocumento,
                            numeroDocumento, configuracionDTO);
                    break;
                default:
                    break;
                }

                // Agregar comparendos y validar repetidos segun prioridad
                for (HistoricoComparendoDTO resultadoFuente : resultadosFuente)
                    if (!historicoComparendos.containsKey(resultadoFuente.getNumeroComparendo()))
                        historicoComparendos.put(resultadoFuente.getNumeroComparendo(), resultadoFuente);
            }
        }

        resultado.getComparendos().addAll(historicoComparendos.values());

        return resultado;
    }

    private List<HistoricoComparendoDTO> consultarReincidenciasFuenteCirculemos2(Integer codigoOrganismo,
            Integer idTipoDocumento, String numeroDocumento, ValoresBusquedaReincidenciaDTO configuracionDTO)
            throws CirculemosNegocioException {
        LOGGER.debug("ReincidenciaEJB::consultarReincidenciasFuenteCirculemos2(Integer,Integer,String,ValoresBusquedaReincidenciaDTO)");
        List<ComparendoDTO> comparendoList = null;
        List<HistoricoComparendoDTO> reincidenciaList = new ArrayList<HistoricoComparendoDTO>();
        ;

        ConsultaComparendoDTO consultaComparendoDTO = new ConsultaComparendoDTO();
        consultaComparendoDTO.setCodigoOrganismo(codigoOrganismo);
        consultaComparendoDTO.setTipoDocumentoInfractor(idTipoDocumento);
        consultaComparendoDTO.setNumeroDocumentoInfractor(numeroDocumento);

        comparendoList = comparendoEJB.consultarComparendos(consultaComparendoDTO);

        if (comparendoList != null && !comparendoList.isEmpty()) {
            for (ComparendoDTO comparendo : comparendoList) {

                HistoricoComparendoDTO reincidencia = new HistoricoComparendoDTO();
                ReincidenciaHelperExtend.toHistoricoComparendoDTO(reincidencia, comparendo);

                LiquidarTarifaInfraccionDTO liquidarTarifa = new LiquidarTarifaInfraccionDTO();
                liquidarTarifa.setCodigoInfraccion(comparendo.getInfraccion().getCodigo());
                if (comparendo.getGradoAlcoholemia() != null) {
                    liquidarTarifa.setGradoAlcoholemia(comparendo.getGradoAlcoholemia().getId());
                }

                liquidarTarifa.setFechaLiquidacion(comparendo.getFechaInfraccion());
                liquidarTarifa.setIdClaseServicio(comparendo.getComparendoVehiculo().getTipoServicio().getId());
                liquidarTarifa.setNiegaPruebaAlcoholemia(comparendo.getNiegaPruebaAlcoholemia());
                liquidarTarifa.setNumeroReincidencias(comparendo.getNumeroReincidencia());

                TarifaLiquidacionDTO tarifaLiquidacion = tarifaInfracionEJB.liquidarTarifaInfraccion(liquidarTarifa);
                reincidencia.setValorInfraccion(tarifaLiquidacion.getValorLiquidado());
                reincidenciaList.add(reincidencia);
            }

            reincidenciaList = this.filtrarReincidencia(reincidenciaList, configuracionDTO);
        }

        return reincidenciaList;
    }

    private List<HistoricoComparendoDTO> filtrarReincidencia(List<HistoricoComparendoDTO> historicoComparendoList,
            ValoresBusquedaReincidenciaDTO configuracionDTO) {
        LOGGER.debug("ReincidenciaEJB::consultarReincidencias(List<HistoricoComparendoDTO>,ValoresBusquedaReincidenciaDTO)");
        Date fechaActual = UtilFecha.currentZeroTimeDate();
        Date fechaReincidencia = null;
        List<Integer> codigosInfraccion = null;
        List<HistoricoComparendoDTO> historicoComparendoRespuestaList = new ArrayList<>();
        int cantidadDias = 0;

        if (configuracionDTO.getCantidadDias() != null) {
            cantidadDias = configuracionDTO.getCantidadDias().intValue();
            fechaReincidencia = UtilFecha.sumarDias(fechaActual, -cantidadDias);
        }

        if (configuracionDTO.getCodigoInfraccion() != null) {
            codigosInfraccion = new ArrayList<>();
            for (String idInfraccion : configuracionDTO.getCodigoInfraccion()) {
                codigosInfraccion.add(Integer.parseInt(idInfraccion));
            }
        }

        for (HistoricoComparendoDTO historicoComparendo : historicoComparendoList) {
            boolean seAdiciona = true;
            if (fechaReincidencia != null) {
                if (historicoComparendo.getFechaImposicion().compareTo(fechaReincidencia) < 0) {
                    seAdiciona = false;
                }
            }

            if (codigosInfraccion != null && seAdiciona == true) {
                if (!(codigosInfraccion.contains(historicoComparendo.getIdInfraccion()))) {
                    seAdiciona = false;
                }
            }

            if (seAdiciona) {
                historicoComparendoRespuestaList.add(historicoComparendo);
            }
        }

        return historicoComparendoRespuestaList;
    }

    private List<HistoricoComparendoDTO> consultarReincidenciasFuenteCirculemos1(Integer codigoOrganismo,
            Integer idTipoDocumento, String numeroDocumento, ValoresBusquedaReincidenciaDTO configuracionDTO) {
        LOGGER.warn("ReincidenciaEJB::consultarReincidenciasFuenteCirculemos1(Integer, Integer, String, ValoresBusquedaReincidenciaDTO) - METODO NO IMPLEMENTADO");
        return null;
    }

    private List<HistoricoComparendoDTO> consultarReincidenciasFuenteSimit(Integer codigoOrganismo,
            Integer idTipoDocumento, String numeroDocumento, ValoresBusquedaReincidenciaDTO configuracionDTO) {
        LOGGER.warn("ReincidenciaEJB::consultarReincidenciasFuenteSimit(Integer, Integer, String, ValoresBusquedaReincidenciaDTO) - METODO NO IMPLEMENTADO");
        return null;
    }

    private List<HistoricoComparendoDTO> consultarReincidenciasFuenteRunt(Integer codigoOrganismo,
            Integer idTipoDocumento, String numeroDocumento, ValoresBusquedaReincidenciaDTO configuracionDTO) {
        LOGGER.warn("ReincidenciaEJB::consultarReincidenciasFuenteRunt(Integer, Integer, String, ValoresBusquedaReincidenciaDTO) - METODO NO IMPLEMENTADO");
        return null;
    }
}
