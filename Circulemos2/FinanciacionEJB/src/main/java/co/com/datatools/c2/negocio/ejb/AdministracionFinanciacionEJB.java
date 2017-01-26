package co.com.datatools.c2.negocio.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.comparendo.IRComparendoFinanciacion;
import co.com.datatools.c2.dto.ClaseGarantiaDTO;
import co.com.datatools.c2.dto.CondicionFinanciacionDTO;
import co.com.datatools.c2.dto.ConfiguracionFinanciacionDTO;
import co.com.datatools.c2.dto.DetalleCantidadCuotaDTO;
import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.dto.DetallePorcentajeCuotaIniciDTO;
import co.com.datatools.c2.dto.ErrorFinanciacionDTO;
import co.com.datatools.c2.dto.EstadoCondicionFinanciacionDTO;
import co.com.datatools.c2.dto.EstadoGarantiaDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaFinanciacionDTO;
import co.com.datatools.c2.dto.TipoGarantiaDTO;
import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;
import co.com.datatools.c2.dto.VariableCondicionFinanDTO;
import co.com.datatools.c2.entidades.CondicionFinanciacion;
import co.com.datatools.c2.entidades.ConfiguracionFinanciacion;
import co.com.datatools.c2.entidades.DetalleCantidadCuota;
import co.com.datatools.c2.entidades.DetallePorcentajeCuotaInici;
import co.com.datatools.c2.entidades.EstadoCondicionFinanciacion;
import co.com.datatools.c2.entidades.Financiacion;
import co.com.datatools.c2.entidades.ValorCondicionFinanciacion;
import co.com.datatools.c2.enumeracion.EnumCampoConfFinanciacion;
import co.com.datatools.c2.enumeracion.EnumErrorConfiguracionFinanciacion;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeracion.financiacion.EnumVariableCondicionFinanciacion;
import co.com.datatools.c2.negocio.error.ErrorFinanciacion.EnumErroresResultadoFinanciacion;
import co.com.datatools.c2.negocio.helpers.CondicionFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.ConfiguracionFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.DetalleCantidadCuotaHelper;
import co.com.datatools.c2.negocio.helpers.DetallePorcentajeCuotaIniciHelper;
import co.com.datatools.c2.negocio.helpers.EstadoCondicionFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.FinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.ValorCondicionFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.extend.DetalleCantidadCuotaHelperExtend;
import co.com.datatools.c2.negocio.helpers.extend.DetallePorcentajeCuotaIniciHelperExtend;
import co.com.datatools.c2.negocio.helpers.extend.ValorCondicionFinanciacionHelperExtend;
import co.com.datatools.c2.negocio.helpers.financiaciones.AdminConfiguracionFinanciacionHelper;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionFinanciacion;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionFinanciacion;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class AdministracionFinanciacionEJB
 */
@Stateless(name = "AdministracionFinanciacionEJB")
@LocalBean
public class AdministracionFinanciacionEJB implements IRAdministracionFinanciacion, ILAdministracionFinanciacion {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(AdministracionFinanciacionEJB.class);

    // @EJB
    // private ILReglaNegocio reglaNegocioEjb;
    @EJB
    private IRComparendoFinanciacion irCompaFin;
    @EJB
    private ILSeguridadCirculemos seguridadCirculemosEJB;

    @Override
    public List<ClaseGarantiaDTO> consultarClaseGarantia(ClaseGarantiaDTO claseGarantiaDTO) {
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT cg FROM ClaseGarantia cg WHERE 1 = 1");

        if (claseGarantiaDTO != null && claseGarantiaDTO.getCodigoClaseGarantia() != null) {
            jpql.append(" AND cg.codigoClaseGarantia = :codigoClaseGarantia");
        }
        if (claseGarantiaDTO != null && claseGarantiaDTO.getNombreClaseGarantia() != null) {
            jpql.append(" AND cg.nombreClaseGarantia = :nombreClaseGarantia");
        }

        jpql.append(" ORDER BY cg.nombreClaseGarantia");

        // TODO FIX Cambio Negocio
        // TypedQuery<ClaseGarantia> query = em.createQuery(jpql.toString(), ClaseGarantia.class);
        //
        // if (claseGarantiaDTO != null && claseGarantiaDTO.getCodigoClaseGarantia() != null) {
        // query.setParameter("codigoClaseGarantia", claseGarantiaDTO.getCodigoClaseGarantia());
        // }
        // if (claseGarantiaDTO != null && claseGarantiaDTO.getNombreClaseGarantia() != null) {
        // query.setParameter("nombreClaseGarantia", claseGarantiaDTO.getNombreClaseGarantia());
        // }
        //
        // List<ClaseGarantia> clasesGarantia = query.getResultList();
        List<ClaseGarantiaDTO> clasesGarantiasDTO = null;
        // ClaseGarantiaHelper.toListLevel0DTO(clasesGarantia);

        return clasesGarantiasDTO;
    }

    @Override
    public List<EstadoGarantiaDTO> consultarEstadoGarantia(EstadoGarantiaDTO estadoGarantiaDTO) {
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT eg FROM EstadoGarantia eg WHERE 1 = 1");

        if (estadoGarantiaDTO != null && estadoGarantiaDTO.getCodigoEstadoGarantia() != null) {
            jpql.append(" AND eg.codigoEstadoGarantia = :codigoEstadoGarantia");
        }
        if (estadoGarantiaDTO != null && estadoGarantiaDTO.getNombreEstadoGarantia() != null) {
            jpql.append(" AND eg.nombreEstadoGarantia = :nombreEstadoGarantia");
        }

        jpql.append(" ORDER BY eg.nombreEstadoGarantia");

        // TODO FIX Cambio Negocio
        // TypedQuery<EstadoGarantia> query = em.createQuery(jpql.toString(), EstadoGarantia.class);
        //
        // if (estadoGarantiaDTO != null && estadoGarantiaDTO.getCodigoEstadoGarantia() != null) {
        // query.setParameter("codigoEstadoGarantia", estadoGarantiaDTO.getCodigoEstadoGarantia());
        // }
        // if (estadoGarantiaDTO != null && estadoGarantiaDTO.getNombreEstadoGarantia() != null) {
        // query.setParameter("nombreEstadoGarantia", estadoGarantiaDTO.getNombreEstadoGarantia());
        // }
        //
        // List<EstadoGarantia> estadosGarantia = query.getResultList();
        List<EstadoGarantiaDTO> estadosGarantiasDTO = null;
        // EstadoGarantiaHelper.toListLevel0DTO(estadosGarantia);

        return estadosGarantiasDTO;
    }

    @Override
    public List<TipoGarantiaDTO> consultarTipoGarantia(TipoGarantiaDTO tipoGarantiaDTO, int codigoOrganismo) {
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT tg");
        jpql.append("  FROM OrganismoTransito ot");
        jpql.append("  JOIN ot.tipoGarantias tg");
        jpql.append(" WHERE ot.codigoOrganismo = :codigoOrganismo");

        if (tipoGarantiaDTO != null && tipoGarantiaDTO.getCodigoTipoGarantia() != null) {
            jpql.append(" AND tg.codigoTipoGarantia = :codigoTipoGarantia");
        }
        if (tipoGarantiaDTO != null && tipoGarantiaDTO.getNombreGarantia() != null) {
            jpql.append(" AND tg.nombreGarantia = :nombreGarantia");
        }

        jpql.append(" ORDER BY tg.nombreGarantia");

        // TODO FIX Cambio Negocio
        // TypedQuery<TipoGarantia> query = em.createQuery(jpql.toString(), TipoGarantia.class);
        //
        // // TODO Obtener de sesion el codigo de organismo
        // query.setParameter("codigoOrganismo", codigoOrganismo);
        //
        // if (tipoGarantiaDTO != null && tipoGarantiaDTO.getCodigoTipoGarantia() != null) {
        // query.setParameter("codigoTipoGarantia", tipoGarantiaDTO.getCodigoTipoGarantia());
        // }
        // if (tipoGarantiaDTO != null && tipoGarantiaDTO.getNombreGarantia() != null) {
        // query.setParameter("nombreGarantia", tipoGarantiaDTO.getNombreGarantia());
        // }
        //
        // List<TipoGarantia> tiposGarantia = query.getResultList();
        List<TipoGarantiaDTO> tiposGarantiaDTO = null;
        // TipoGarantiaHelper.toListLevel0DTO(tiposGarantia);

        return tiposGarantiaDTO;
    }

    @Override
    public List<CondicionFinanciacionDTO> consultarCondicionFinanciacion(int pCodigoOrganismo) {
        List<CondicionFinanciacionDTO> lstCondicionFinanciacionDTOs = new ArrayList<CondicionFinanciacionDTO>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cf FROM CondicionFinanciacion cf LEFT JOIN cf.lstCondicionOrganismo org");
        sql.append(" WHERE org.codigoOrganismo = :codOrg");
        sql.append(" ORDER BY cf.orden ASC");

        TypedQuery<CondicionFinanciacion> query = em.createQuery(sql.toString(), CondicionFinanciacion.class);
        query.setParameter("codOrg", pCodigoOrganismo);

        List<CondicionFinanciacion> lstCondicionesFinanciacion = query.getResultList();
        if (!lstCondicionesFinanciacion.isEmpty()) {
            lstCondicionFinanciacionDTOs = CondicionFinanciacionHelper.toListLevel1DTO(lstCondicionesFinanciacion);
        }

        return lstCondicionFinanciacionDTOs;
    }

    @Override
    public List<ConfiguracionFinanciacionDTO> consultarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO pConfiguracionFinanciacionDTO, boolean vigente) {
        List<ConfiguracionFinanciacionDTO> lstConfiguracionFinanciacionDTOs = new ArrayList<ConfiguracionFinanciacionDTO>();

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT conf FROM ConfiguracionFinanciacion conf");
        jpql.append(" WHERE 1=1 ");
        Map<String, Object> filtros = new HashMap<String, Object>();
        if (pConfiguracionFinanciacionDTO != null) {
            if (pConfiguracionFinanciacionDTO.getId() != null) {
                jpql.append(" AND conf.id = :pCodConfFinan");
                filtros.put("pCodConfFinan", pConfiguracionFinanciacionDTO.getId());
            } else {
                if (pConfiguracionFinanciacionDTO.getOrganismoTransito() != null
                        && pConfiguracionFinanciacionDTO.getOrganismoTransito().getCodigoOrganismo() != null) {
                    jpql.append(" AND conf.organismoTransito.codigoOrganismo = :pCodOrganismo ");
                    filtros.put("pCodOrganismo",
                            pConfiguracionFinanciacionDTO.getOrganismoTransito().getCodigoOrganismo());
                }
                if (StringUtils.isNotBlank(pConfiguracionFinanciacionDTO.getNombre())) {
                    jpql.append(" AND UPPER(conf.nombre) LIKE :pNomConf");
                    filtros.put("pNomConf", "%" + pConfiguracionFinanciacionDTO.getNombre() + "%");
                }
                if (pConfiguracionFinanciacionDTO.getFechaInicial() != null) {
                    jpql.append(" AND conf.fechaInicial = :pFechaIniVigencia");
                    filtros.put("pFechaIniVigencia", pConfiguracionFinanciacionDTO.getFechaInicial());
                }
                if (pConfiguracionFinanciacionDTO.getFechaFinal() != null) {
                    jpql.append(" AND conf.fechaFinal = :pFechaFinVigencia");
                    filtros.put("pFechaFinVigencia", pConfiguracionFinanciacionDTO.getFechaFinal());
                }
            }
        }
        if (vigente) {
            jpql.append(" AND conf.fechaInicial <= :fechaActual ");
            jpql.append(" AND (conf.fechaFinal IS NULL or conf.fechaFinal >= :fechaActual) ");
            filtros.put("fechaActual", new Date());
        }

        GenericDao<ConfiguracionFinanciacion> dao = new GenericDao<ConfiguracionFinanciacion>(
                ConfiguracionFinanciacion.class, em);

        List<ConfiguracionFinanciacion> resultados = dao.buildAndExecuteQuery(jpql, filtros);
        if (!resultados.isEmpty()) {
            lstConfiguracionFinanciacionDTOs = ConfiguracionFinanciacionHelper.toListLevel1DTO(resultados);
            if ((pConfiguracionFinanciacionDTO != null && pConfiguracionFinanciacionDTO.getId() != null) || vigente) {
                ConfiguracionFinanciacionDTO configuracionFinanciacionDTO = lstConfiguracionFinanciacionDTOs.get(0);
                ConfiguracionFinanciacion configuracionFinanciacion = resultados.get(0);
                configuracionFinanciacionDTO.setLstValorCondicionFinanciacion(ValorCondicionFinanciacionHelperExtend
                        .toListLevel1DTO(configuracionFinanciacion.getLstValorCondicionFinanciacion()));
                configuracionFinanciacionDTO.setLstDetalleCantidadCuota(DetalleCantidadCuotaHelperExtend
                        .toListLevel1DTO(configuracionFinanciacion.getLstDetalleCantidadCuota()));
                configuracionFinanciacionDTO.setLstDetallePorcentajeCuotaInici(DetallePorcentajeCuotaIniciHelperExtend
                        .toListLevel1DTO(configuracionFinanciacion.getLstDetallePorcentajeCuotaInici()));
                configuracionFinanciacionDTO.setLstEstadoCondicionFinanciacion(EstadoCondicionFinanciacionHelper
                        .toListLevel1DTO(configuracionFinanciacion.getLstEstadoCondicionFinanciacion()));
            }
        }

        return lstConfiguracionFinanciacionDTOs;
    }

    /**
     * Valida si ya existe una configuracion de financiacion para el rango de fechas en el que queda la configuracion que se va registrar
     * 
     * @param configuracionFinanciacionDTO
     *            Corresponde a la informacion de la configuracion a validar
     * @return True, si existe una configuracion de financiacion que se cruce
     */
    private boolean validarConfiguracionVigente(ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        boolean existeConfiguracionVigente = false;
        if (configuracionFinanciacionDTO.getFechaInicial() != null) {
            ConfiguracionFinanciacionDTO filtro = new ConfiguracionFinanciacionDTO();
            configuracionFinanciacionDTO.setOrganismoTransito(configuracionFinanciacionDTO.getOrganismoTransito());
            List<ConfiguracionFinanciacionDTO> configuraciones = consultarConfiguracionFinanciacion(filtro, false);
            for (ConfiguracionFinanciacionDTO configuracion : configuraciones) {
                if (configuracionFinanciacionDTO.getId() == null
                        || configuracionFinanciacionDTO.getId().intValue() != configuracion.getId().intValue()) {
                    if (configuracion.getFechaFinal() == null
                            || UtilFecha.betweenDate(configuracion.getFechaInicial(), configuracion.getFechaFinal(),
                                    configuracionFinanciacionDTO.getFechaInicial())
                            || (configuracionFinanciacionDTO.getFechaFinal() == null && UtilFecha.betweenDate(
                                    configuracion.getFechaInicial(), configuracion.getFechaFinal(), new Date()))
                            || (configuracionFinanciacionDTO.getFechaFinal() != null && UtilFecha.betweenDate(
                                    configuracion.getFechaInicial(), configuracion.getFechaFinal(),
                                    configuracionFinanciacionDTO.getFechaFinal()))) {
                        existeConfiguracionVigente = true;
                    }
                }
            }
        }
        return existeConfiguracionVigente;
    }

    @Override
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> registrarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> response = validarRegistroConfiguracionFinanciacion(
                configuracionFinanciacionDTO);
        Iterator<RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> elements = response
                .values().iterator();

        while (elements.hasNext()) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respDTO = elements.next();
            if (respDTO.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respConfFinanDTO = response
                        .get(configuracionFinanciacionDTO);
                respConfFinanDTO.setRespuesta(EnumRespuestaSistema.ERRORES);
                respConfFinanDTO.setNombreRespuesta(
                        EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CONFIGURACION_FINANCIACION
                                .getDescripcion());
                respConfFinanDTO.setDescripcionRespuesta(
                        EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CONFIGURACION_FINANCIACION
                                .getDescripcion());
                return response;
            }
        }

        /**
         * Construccion de objetos a persistir.
         */
        ConfiguracionFinanciacion configuracionFinanciacion = ConfiguracionFinanciacionHelper
                .toLevel1Entity(configuracionFinanciacionDTO, null);
        configuracionFinanciacion.setLstValorCondicionFinanciacion(new ArrayList<ValorCondicionFinanciacion>());
        /*
         * Se lleva a cabo asignacion de lo valores respectivos.
         */
        for (ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO : configuracionFinanciacionDTO
                .getLstValorCondicionFinanciacion()) {
            // TODO FIX Cambio Negocio
            ValorCondicionFinanciacion valorCondicionFinanciacion = ValorCondicionFinanciacionHelperExtend
                    .toLevel1Entity(valorCondicionFinanciacionDTO, null);
            valorCondicionFinanciacion.setConfiguracionFinanciacion(configuracionFinanciacion);
            configuracionFinanciacion.getLstValorCondicionFinanciacion().add(valorCondicionFinanciacion);
        }
        /*
         * Se lleva a cabo asignacion de los detalles de cantidad de cuota ingresados.
         */
        configuracionFinanciacion.setLstDetalleCantidadCuota(new ArrayList<DetalleCantidadCuota>());
        for (DetalleCantidadCuotaDTO detCantidadCuotaDTO : configuracionFinanciacionDTO.getLstDetalleCantidadCuota()) {
            // TODO FIX Cambio Negocio
            DetalleCantidadCuota cantidadCuota = DetalleCantidadCuotaHelper.toLevel1Entity(detCantidadCuotaDTO, null);
            cantidadCuota.setConfiguracionFinanciacion(configuracionFinanciacion);
            configuracionFinanciacion.getLstDetalleCantidadCuota().add(cantidadCuota);
        }
        /*
         * Se lleva a cabo asignacion de los detalles de porcentaje ingresados.
         */
        configuracionFinanciacion.setLstDetallePorcentajeCuotaInici(new ArrayList<DetallePorcentajeCuotaInici>());
        for (DetallePorcentajeCuotaIniciDTO detallePorcentajeCuotaIniciDTO : configuracionFinanciacionDTO
                .getLstDetallePorcentajeCuotaInici()) {
            // TODO FIX Cambio Negocio
            DetallePorcentajeCuotaInici detallePorcentajeCuotaInici = DetallePorcentajeCuotaIniciHelper
                    .toLevel1Entity(detallePorcentajeCuotaIniciDTO, null);
            detallePorcentajeCuotaInici.setConfiguracionFinanciacion(configuracionFinanciacion);
            configuracionFinanciacion.getLstDetallePorcentajeCuotaInici().add(detallePorcentajeCuotaInici);
        }

        /*
         * Se lleva a cabo la asignacion de los estados de las condiciones.
         */
        configuracionFinanciacion.setLstEstadoCondicionFinanciacion(new ArrayList<EstadoCondicionFinanciacion>());
        for (EstadoCondicionFinanciacionDTO estCondFinanDTO : configuracionFinanciacionDTO
                .getLstEstadoCondicionFinanciacion()) {
            // TODO FIX Cambio Negocio
            EstadoCondicionFinanciacion estadoCondicionFinanciacion = EstadoCondicionFinanciacionHelper
                    .toLevel1Entity(estCondFinanDTO, null);
            estadoCondicionFinanciacion.setConfiguracionFinanciacion(configuracionFinanciacion);
            configuracionFinanciacion.getLstEstadoCondicionFinanciacion().add(estadoCondicionFinanciacion);
        }

        em.persist(configuracionFinanciacion);
        em.flush();

        response.get(configuracionFinanciacionDTO).setDescripcionRespuesta(
                EnumErrorConfiguracionFinanciacion.CONFIGURACION_FINANCIACION_REGISTRADA.getDescripcion());

        return response;

    }

    @Override
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> modificarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        /**
         * Validar configuracion extrallendo información de la configuracion
         */
        HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> response = validarModificacionFinanciacion(
                configuracionFinanciacionDTO);
        Iterator<RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> elements = response
                .values().iterator();
        while (elements.hasNext()) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respDTO = elements.next();
            if (respDTO.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respConfFinanDTO = response
                        .get(configuracionFinanciacionDTO);
                respConfFinanDTO.setRespuesta(EnumRespuestaSistema.ERRORES);
                respConfFinanDTO.setNombreRespuesta(
                        EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CONFIGURACION_FINANCIACION
                                .getDescripcion());
                respConfFinanDTO.setDescripcionRespuesta(
                        EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CONFIGURACION_FINANCIACION
                                .getDescripcion());
                return response;
            }
        }
        /**
         * Se lleva a cabo la modificacion de los registros
         */
        ConfiguracionFinanciacion configuracionFinanciacion = em.find(ConfiguracionFinanciacion.class,
                configuracionFinanciacionDTO.getId());
        configuracionFinanciacion = ConfiguracionFinanciacionHelper.toLevel1Entity(configuracionFinanciacionDTO,
                configuracionFinanciacion);
        /**
         * Se lleva a cabo la asignacion de los valores de condicion.
         */
        configuracionFinanciacion.getLstValorCondicionFinanciacion().clear();
        for (ValorCondicionFinanciacionDTO valCondFinanDTO : configuracionFinanciacionDTO
                .getLstValorCondicionFinanciacion()) {
            ValorCondicionFinanciacion valorCondicionFinanciacion = ValorCondicionFinanciacionHelper
                    .toLevel1Entity(valCondFinanDTO, null);
            valorCondicionFinanciacion.setConfiguracionFinanciacion(configuracionFinanciacion);
            configuracionFinanciacion.getLstValorCondicionFinanciacion().add(valorCondicionFinanciacion);
        }

        /**
         * Se lleva a cabo la asignacion de los detalles de cantidad de cuota
         */
        configuracionFinanciacion.getLstDetalleCantidadCuota().clear();
        for (DetalleCantidadCuotaDTO detalleCantidadCuotaDTO : configuracionFinanciacionDTO
                .getLstDetalleCantidadCuota()) {
            DetalleCantidadCuota detalleCantidadCuota = DetalleCantidadCuotaHelper
                    .toLevel1Entity(detalleCantidadCuotaDTO, null);
            detalleCantidadCuota.setConfiguracionFinanciacion(configuracionFinanciacion);
            configuracionFinanciacion.getLstDetalleCantidadCuota().add(detalleCantidadCuota);
        }

        /**
         * Se lleva a cabo la asignacion de los detalles de porcentajes de cuota inocial
         */
        configuracionFinanciacion.getLstDetallePorcentajeCuotaInici().clear();
        for (DetallePorcentajeCuotaIniciDTO detallePorcentajeCuotaIniciDTO : configuracionFinanciacionDTO
                .getLstDetallePorcentajeCuotaInici()) {
            DetallePorcentajeCuotaInici detallePorcentajeCuotaInici = DetallePorcentajeCuotaIniciHelper
                    .toLevel1Entity(detallePorcentajeCuotaIniciDTO, null);
            detallePorcentajeCuotaInici.setConfiguracionFinanciacion(configuracionFinanciacion);
            configuracionFinanciacion.getLstDetallePorcentajeCuotaInici().add(detallePorcentajeCuotaInici);
        }

        /**
         * Se lleva a cabo la asignacion de los estados de las condiciones
         */
        configuracionFinanciacion.getLstEstadoCondicionFinanciacion().clear();
        for (EstadoCondicionFinanciacionDTO estadoCondicionFinanciacionDTO : configuracionFinanciacionDTO
                .getLstEstadoCondicionFinanciacion()) {
            EstadoCondicionFinanciacion estadoCondicionFinanciacion = EstadoCondicionFinanciacionHelper
                    .toLevel1Entity(estadoCondicionFinanciacionDTO, null);
            estadoCondicionFinanciacion.setConfiguracionFinanciacion(configuracionFinanciacion);
            configuracionFinanciacion.getLstEstadoCondicionFinanciacion().add(estadoCondicionFinanciacion);
        }

        /**
         * Se lleva a cabo la actualizacion del registro modificado
         */
        em.merge(configuracionFinanciacion);

        response.get(configuracionFinanciacionDTO).setNombreRespuesta(
                EnumErrorConfiguracionFinanciacion.CONFIGURACION_FINANCIACION_MODIFICADA.toString());
        response.get(configuracionFinanciacionDTO).setDescripcionRespuesta(
                EnumErrorConfiguracionFinanciacion.CONFIGURACION_FINANCIACION_MODIFICADA.getDescripcion());

        return response;
    }

    // metodo interfaz que lleve a cabo validacion return Configuracion

    @Override
    public RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> eliminarConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> response = validacionUtilizacionConfiguracionFinanciacion(
                configuracionFinanciacionDTO);
        if (response.getRespuesta().equals(EnumRespuestaSistema.OK)) {
            ConfiguracionFinanciacion configuracionFinanciacion = em.find(ConfiguracionFinanciacion.class,
                    configuracionFinanciacionDTO.getId());
            em.remove(configuracionFinanciacion);
            em.flush();

            response.setNombreRespuesta(
                    EnumErrorConfiguracionFinanciacion.ELIMINACION_CONFIGURACION_FINAN_SATISFACTORIA.toString());
            response.setDescripcionRespuesta(
                    EnumErrorConfiguracionFinanciacion.ELIMINACION_CONFIGURACION_FINAN_SATISFACTORIA.getDescripcion());
        } else {
            response.setNombreRespuesta(
                    EnumErrorConfiguracionFinanciacion.ERROR_ELIMINACION_CONFIGURACION_FINAN.toString());
            response.setDescripcionRespuesta(
                    EnumErrorConfiguracionFinanciacion.ERROR_ELIMINACION_CONFIGURACION_FINAN.getDescripcion());
        }
        return response;

    }

    /**
     * Valida si una configuracion de financiacion esta siendo utilizada en alguana financiacion.
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    private RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacionUtilizacionConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacion;
        FinanciacionDTO financiacionDTOAsociada = configuracionFinanciacionAsociadaFinanciacion(
                configuracionFinanciacionDTO);
        if (financiacionDTOAsociada != null) {

            // String fecha = Utilidades.dateToStringFormatApp(financiacionDTOAsociada.getFechaFinanciacion(), false);//TODO traer del proceso

            validacion = new RespuestaDTO<>(false);
            // TODO FIX cambio en el objeto respuesta de DTO
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CONFIGURACION_FINANCIACION));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CONFIGURACION_UTILIZADA);
            validacion.setNombreRespuesta(
                    EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CONFIGURACION_UTILIZADA.getDescripcion());
            /*
             * validacion .setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CONFIGURACION_UTILIZADA .getDescripcion() +
             * " Fecha Última Financiación asociada: " + fecha);
             */// TODO traer del proceso

            return validacion;
        }
        validacion = new RespuestaDTO<>(true);
        validacion.setNombreRespuesta(
                EnumErrorConfiguracionFinanciacion.CONFIGURACION_FINANCIACION_VALIDA.getDescripcion());
        return validacion;

    }

    @Override
    public FinanciacionDTO configuracionFinanciacionAsociadaFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        FinanciacionDTO financiacionDTO = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT fin FROM Financiacion fin");
        sql.append(" JOIN fin.proceso p");
        sql.append(" WHERE fin.configuracionFinanciacion.id = :pIdConfFinan");
        sql.append(" ORDER BY p.fechaInicio DESC");
        /**
         * CONSULTA PARA VALIDACION DE CONFIGURACION DE FINANCIACION UTILIZADA.
         */
        TypedQuery<Financiacion> query = em.createQuery(sql.toString(), Financiacion.class);
        query.setParameter("pIdConfFinan", configuracionFinanciacionDTO.getId());

        List<Financiacion> resultados = query.getResultList();
        if (!resultados.isEmpty()) {
            financiacionDTO = FinanciacionHelper.toLevel1DTO(resultados.get(0));
        }
        return financiacionDTO;
    }

    /**
     * Consulta la existencia una configuracion
     * 
     * <pre>
     * Fecha de inicio no nula
     * </pre>
     * 
     * <pre>
     * Fecha fin puede ser nula cuyo caso evaluara unicamente la fecha de inicio se cruza con alguna otra
     * </pre>
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     *            : Configuracion
     * @return {@link ConfiguracionFinanciacionDTO}
     */
    private boolean existeConfiguracionFinanciacion(ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        /**
         * Filtros posibles
         */
        boolean filFecFinVig = false;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT COUNT(cf.id) FROM ConfiguracionFinanciacion AS cf");
        jpql.append(" WHERE cf.organismoTransito.codigoOrganismo = :pCodOrg");
        jpql.append(" AND cf.id != :pIdConFin");
        jpql.append(" AND (:fechaIni BETWEEN cf.fechaInicial AND cf.fechaFinal");
        jpql.append(" OR cf.fechaInicial = :fechaIni)");
        if (configuracionFinanciacionDTO.getFechaFinal() != null) {
            jpql.append(" AND :fechaFin BETWEEN cf.fechaInicial AND cf.fechaFinal ");
            filFecFinVig = true;
        }

        Query query = em.createQuery(jpql.toString());
        query.setParameter("pCodOrg", configuracionFinanciacionDTO.getOrganismoTransito().getCodigoOrganismo());
        query.setParameter("pIdConFin", configuracionFinanciacionDTO.getId());
        query.setParameter("fechaIni", configuracionFinanciacionDTO.getFechaInicial());
        if (filFecFinVig) {
            query.setParameter("fechaFin", configuracionFinanciacionDTO.getFechaFinal());
        }

        Long count = (Long) query.getSingleResult();
        return count > 0;

    }

    /**
     * Validacion de la informacion basica:
     * 
     * -Fecha Inicial no ingresadas
     * 
     * -Fecha inicial de vigencia de la condicion debe ser mayor a la actual.
     * 
     * -Fecha Final no puede ser menor que fecha inicial de vigencia.
     * 
     * -Validacion de cruce con las demas configuraciones existentes
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    private RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validarInfoBasicaConfFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        AdminConfiguracionFinanciacionHelper adminConfiguracionFinanciacionHelper = new AdminConfiguracionFinanciacionHelper();

        // /*
        // * Fecha inicial no ingresada
        // */
        // if (configuracionFinanciacionDTO.getFechaInicial() == null) {
        // RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> val = new RespuestaDTO<>(false);
        // // TODO FIX cambio en el objeto respuesta de DTO
        // // val.getErrorEntidad().add(
        // // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
        // // EnumCampoConfFinanciacion.FECHA_INICIAL_VIGENCIA));
        // // val.getErrorEntidad().get(val.getErrorEntidad().size() - 1)
        // // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_REQUERIDO);
        // val.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_REQUERIDO.getDescripcion());
        // val.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_REQUERIDO
        // .getDescripcion());
        //
        // return val;
        // }
        //
        // /*
        // * Fecha inicial de vigencia de la condicion debe ser mayor a la actual
        // */
        // RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> valFecIni = adminConfiguracionFinanciacionHelper
        // .validarFecIniConfFinMenIgualActual(configuracionFinanciacionDTO);
        // if (valFecIni.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
        // return valFecIni;
        // }
        // /*
        // * Fecha Final no puede ser menor que fecha inicial de vigencia.
        // */
        // RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> valFecFinFecIni = adminConfiguracionFinanciacionHelper
        // .validarFecFinConfMenorFecIni(configuracionFinanciacionDTO);
        // if (valFecFinFecIni.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
        // return valFecFinFecIni;
        // }
        //
        // /*
        // * Se lleva a cabo la validacion de cruce con las demas configuraciones existentes
        // */
        // if (existeConfiguracionFinanciacion(configuracionFinanciacionDTO)) {
        // RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> val = new RespuestaDTO<>(false);
        // // TODO FIX cambio en el objeto respuesta de DTO
        // // val.getErrorEntidad().add(
        // // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
        // // EnumCampoConfFinanciacion.FECHAS_VIGENCIA));
        // // val.getErrorEntidad().get(val.getErrorEntidad().size() - 1)
        // // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_FECHAS_VIGENCIA_CRUZADAS);
        // val.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_FECHAS_VIGENCIA_CRUZADAS.getDescripcion());
        // val.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_FECHAS_VIGENCIA_CRUZADAS
        // .getDescripcion());
        //
        // return val;
        // }

        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacion = new RespuestaDTO<>(
                true);
        validacion.setNombreRespuesta(
                EnumErrorConfiguracionFinanciacion.CONFIGURACION_FINANCIACION_VALIDA.getDescripcion());
        validacion.setDescripcionRespuesta(
                EnumErrorConfiguracionFinanciacion.CONFIGURACION_FINANCIACION_VALIDA.getDescripcion());

        return validacion;
    }

    /**
     * Consulta la existencia de una configuracion de financiacion sin cerrar.
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     *            : configuracion de financiacion con el organismo a consultar
     * @return ConfiguracionFinanciacionDTO
     */
    private ConfiguracionFinanciacionDTO consultarExistenciaConfFinanSinCerrar(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        ConfiguracionFinanciacionDTO confFinanDTOExistente = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cf FROM ConfiguracionFinanciacion AS cf");
        sql.append(" WHERE cf.organismoTransito.codigoOrganismo = :pCodOrgTrans");
        sql.append(" AND cf.fechaFinal IS NULL");

        TypedQuery<ConfiguracionFinanciacion> query = em.createQuery(sql.toString(), ConfiguracionFinanciacion.class);
        query.setParameter("pCodOrgTrans", configuracionFinanciacionDTO.getOrganismoTransito().getCodigoOrganismo());
        List<ConfiguracionFinanciacion> resultados = query.getResultList();
        if (!resultados.isEmpty()) {
            confFinanDTOExistente = ConfiguracionFinanciacionHelper.toLevel1DTO(resultados.get(0));
        }

        return confFinanDTOExistente;
    }

    @Override
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validarRegistroConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {

        HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validacion = new HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>>();

        /**
         * Se lleva a cabo la validacion de existencia de una Configuracion de financiacion sin cerrar. En este primer caso se retorna la validacion
         * inmediatamente.
         */
        ConfiguracionFinanciacionDTO confFinanDTOExistente = consultarExistenciaConfFinanSinCerrar(
                configuracionFinanciacionDTO);
        if (confFinanDTOExistente != null) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respuesta = new RespuestaDTO<>(
                    false);
            // TODO FIX cambio en el objeto respuesta de DTO
            // respuesta.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CONFIGURACION_FINANCIACION));
            // respuesta
            // .getErrorEntidad()
            // .get(respuesta.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(
            // EnumErrorConfiguracionFinanciacion.ERROR_CONFIGURACION_FINANCIACION_SIN_CERRAR);
            respuesta.setNombreRespuesta(
                    EnumErrorConfiguracionFinanciacion.ERROR_CONFIGURACION_FINANCIACION_SIN_CERRAR.toString());
            respuesta.setDescripcionRespuesta(
                    EnumErrorConfiguracionFinanciacion.ERROR_CONFIGURACION_FINANCIACION_SIN_CERRAR.getDescripcion()
                            + " Nombre Configuración: " + confFinanDTOExistente.getNombre());

            validacion.put(configuracionFinanciacionDTO, respuesta);
            return validacion;
        }

        /**
         * Se valida si existe una configuracion que se cruce con la fecha de vigencia de la configuracion a registrar
         */
        if (validarConfiguracionVigente(configuracionFinanciacionDTO)) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respuestaConfiguracionVigente = new RespuestaDTO<>(
                    false);
            respuestaConfiguracionVigente
                    .setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_FECHAS_VIGENCIA_CRUZADAS.toString());
            respuestaConfiguracionVigente.setDescripcionRespuesta(
                    EnumErrorConfiguracionFinanciacion.ERROR_FECHAS_VIGENCIA_CRUZADAS.getDescripcion());

            validacion.put(configuracionFinanciacionDTO, respuestaConfiguracionVigente);
            return validacion;
        }

        /**
         * Se lleva a cabo la validacion de info basica de la configuracion de financiacion.
         */
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validarInfoBasicaConfFinanciacion = validarInfoBasicaConfFinanciacion(
                configuracionFinanciacionDTO);
        validacion.put(configuracionFinanciacionDTO, validarInfoBasicaConfFinanciacion);

        /**
         * Se lleva a cabo las validaciones de los campos de condiciones.
         */
        AdminConfiguracionFinanciacionHelper adminConfiguracionFinanciacionHelper = new AdminConfiguracionFinanciacionHelper();
        // TODO FIX NEGOCIO BORRADO DROOLS
        // Hashtable<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validacionValoresConfFinanciacion =
        // adminConfiguracionFinanciacionHelper
        // .validacionValoresConfFinanciacion(configuracionFinanciacionDTO.getLstValorCondicionFinanciacion(),
        // reglaNegocioEjb);
        // validacion.putAll(validacionValoresConfFinanciacion);

        /**
         * Se lleva a cabo las validaciones de los detalles de cantidades de cuotas.
         */
        for (DetalleCantidadCuotaDTO detCantCuotaDTO : configuracionFinanciacionDTO.getLstDetalleCantidadCuota()) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacionCamposDetCantCuotas = adminConfiguracionFinanciacionHelper
                    .validarCampoDetalleCantCuotas(detCantCuotaDTO);
            validacion.put(detCantCuotaDTO, validacionCamposDetCantCuotas);
        }

        /**
         * Se lleva a cabo las validaciones de lo detalles de porcentaje cuota inicial
         */
        for (DetallePorcentajeCuotaIniciDTO detPorcCuotIniciDTO : configuracionFinanciacionDTO
                .getLstDetallePorcentajeCuotaInici()) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validarCampoDetallePorcCuotaIni = adminConfiguracionFinanciacionHelper
                    .validarCampoDetallePorcCuotaIni(detPorcCuotIniciDTO);
            validacion.put(detPorcCuotIniciDTO, validarCampoDetallePorcCuotaIni);
        }

        return validacion;
    }

    @Override
    public HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validarModificacionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {

        HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validacion = new HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>>();

        /**
         * Se lleva a cabo validacion de configuracion de financiacion ya cerrada
         */
        // ConfiguracionFinanciacionDTO confFinanAnt = consultarConfiguracionFinanciacion(configuracionFinanciacionDTO,
        // false).get(0);
        // if (confFinanAnt.getFechaFinal() != null) {
        // RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respValConfFinanCerrada = new
        // RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
        // false);
        // // TODO FIX cambio en el objeto respuesta de DTO
        // // respValConfFinanCerrada.getErrorEntidad().add(
        // // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
        // // EnumCampoConfFinanciacion.CONFIGURACION_FINANCIACION));
        // // respValConfFinanCerrada.getErrorEntidad().get(respValConfFinanCerrada.getErrorEntidad().size() - 1)
        // // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_CONFIGURACION_CERRADA);
        // respValConfFinanCerrada.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_CONFIGURACION_CERRADA
        // .toString());
        // respValConfFinanCerrada
        // .setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_CONFIGURACION_CERRADA
        // .getDescripcion());
        //
        // validacion.put(configuracionFinanciacionDTO, respValConfFinanCerrada);
        //
        // return validacion;
        // }

        /**
         * Se valida si existe una configuracion que se cruce con la fecha de vigencia de la configuracion a registrar
         */
        if (validarConfiguracionVigente(configuracionFinanciacionDTO)) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respuestaConfiguracionVigente = new RespuestaDTO<>(
                    false);
            respuestaConfiguracionVigente
                    .setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_FECHAS_VIGENCIA_CRUZADAS.toString());
            respuestaConfiguracionVigente.setDescripcionRespuesta(
                    EnumErrorConfiguracionFinanciacion.ERROR_FECHAS_VIGENCIA_CRUZADAS.getDescripcion());

            validacion.put(configuracionFinanciacionDTO, respuestaConfiguracionVigente);
            return validacion;
        }

        /**
         * Se lleva a cabo la validacion si se encuentra asociada al menos a una financiacion
         */
        // RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respValUtilizConfFinan =
        // validacionUtilizacionConfiguracionFinanciacion(configuracionFinanciacionDTO);
        // if (respValUtilizConfFinan.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
        // validacion.put(configuracionFinanciacionDTO, respValUtilizConfFinan);
        // return validacion;
        // }

        /**
         * Se lleva a cabo la validacion de la informacion basica
         */
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> valInfoBasicaConfFinan = validarInfoBasicaConfFinanciacion(
                configuracionFinanciacionDTO);
        validacion.put(configuracionFinanciacionDTO, valInfoBasicaConfFinan);

        /**
         * Se lleva a cabo las validaciones de los campos de condiciones.
         */
        AdminConfiguracionFinanciacionHelper adminConfiguracionFinanciacionHelper = new AdminConfiguracionFinanciacionHelper();
        // TODO FIX NEGOCIO BORRADO DROOLS
        // Hashtable<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validacionValoresConfFinanciacion =
        // adminConfiguracionFinanciacionHelper
        // .validacionValoresConfFinanciacion(configuracionFinanciacionDTO.getLstValorCondicionFinanciacion(),
        // reglaNegocioEjb);
        // validacion.putAll(validacionValoresConfFinanciacion);

        /**
         * Se lleva a cabo las validaciones de los detalles de cantidades de cuotas.
         */
        for (DetalleCantidadCuotaDTO detCantCuotaDTO : configuracionFinanciacionDTO.getLstDetalleCantidadCuota()) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacionCamposDetCantCuotas = adminConfiguracionFinanciacionHelper
                    .validarCampoDetalleCantCuotas(detCantCuotaDTO);
            validacion.put(detCantCuotaDTO, validacionCamposDetCantCuotas);
        }

        /**
         * Se lleva a cabo las validaciones de lo detalles de porcentaje cuota inicial
         */
        for (DetallePorcentajeCuotaIniciDTO detPorcCuotIniciDTO : configuracionFinanciacionDTO
                .getLstDetallePorcentajeCuotaInici()) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validarCampoDetallePorcCuotaIni = adminConfiguracionFinanciacionHelper
                    .validarCampoDetallePorcCuotaIni(detPorcCuotIniciDTO);
            validacion.put(detPorcCuotIniciDTO, validarCampoDetallePorcCuotaIni);
        }

        return validacion;
    }

    @Override
    public RespuestaFinanciacionDTO validarFinanciacion(FinanciacionDTO financiacionDTO) {
        logger.debug("FinanciacionEJB::validarFinanciacion(FinanciacionDTO)");
        RespuestaFinanciacionDTO resultado = new RespuestaFinanciacionDTO();
        List<ErrorFinanciacionDTO> errores = new ArrayList<ErrorFinanciacionDTO>();
        ConfiguracionFinanciacionDTO configuracion = new ConfiguracionFinanciacionDTO();
        configuracion.setOrganismoTransito(financiacionDTO.getOrganismoTransito());
        for (ConfiguracionFinanciacionDTO configuracionFinanciacionDTO : consultarConfiguracionFinanciacion(
                configuracion, true)) {
            for (CondicionFinanciacionDTO condicionFinanciacionDTO : consultarCondicionesConfiguracionFinanciacion(
                    configuracionFinanciacionDTO, true)) {
                for (VariableCondicionFinanDTO variableCondicionDTO : condicionFinanciacionDTO
                        .getLstVariablesCondicionFinan()) {
                    EnumVariableCondicionFinanciacion variable = EnumVariableCondicionFinanciacion
                            .getEnum(variableCondicionDTO.getId());
                    switch (variable) {
                    case TIPO_OBLIGACIONES_PERMITEN_FINANCIACION:
                        Set<Integer> tipoObligacionPermitidos = new HashSet<Integer>();
                        for (ValorCondicionFinanciacionDTO valor : variableCondicionDTO
                                .getLstValorCondicionFinanciacion()) {
                            tipoObligacionPermitidos.add(Integer.valueOf(valor.getValor()));
                        }
                        for (ObligacionFinanciacionDTO obligacion : financiacionDTO.getObligacionesFinanciacion()) {
                            if (!tipoObligacionPermitidos.contains(obligacion.getCodigoTipoObligacion())) {
                                errores.add(obtenerErrorFinanciacion(
                                        "La financiación contiene tipos de obligación que no se permiten financiar"));
                                break;
                            }
                        }
                        break;
                    case VALOR_MINIMO_FINANCIAR:
                        for (ValorCondicionFinanciacionDTO valor : variableCondicionDTO
                                .getLstValorCondicionFinanciacion()) {
                            if (financiacionDTO.getValorTotal()
                                    .compareTo(BigDecimal.valueOf(Long.valueOf(valor.getValor()))) == -1) {
                                errores.add(obtenerErrorFinanciacion(
                                        "El valor de la financiación debe ser mayor a " + valor.getValor()));
                                break;
                            }
                        }
                        break;
                    case VALOR_MAXIMO_FINANCIAR:
                        for (ValorCondicionFinanciacionDTO valor : variableCondicionDTO
                                .getLstValorCondicionFinanciacion()) {
                            if (financiacionDTO.getValorTotal()
                                    .compareTo(BigDecimal.valueOf(Long.valueOf(valor.getValor()))) == 1) {
                                errores.add(obtenerErrorFinanciacion(
                                        "El valor de la financiación debe ser menor a " + valor.getValor()));
                                break;
                            }
                        }
                        break;
                    case TIPO_CONVERSION_CANTIDAD_CUOTAS:

                        break;
                    case TIPO_CONVERSION_PORCENTAJE:

                        break;
                    case DETALLE_CANTIDAD_CUOTAS:
                        boolean detalleNoConfiguradoCuotas = true;
                        for (DetalleCantidadCuotaDTO detalle : configuracionFinanciacionDTO
                                .getLstDetalleCantidadCuota()) {
                            if (financiacionDTO.getNumeroCuotas().intValue() >= detalle.getCantidadMinimaCoutas()
                                    .intValue()
                                    && financiacionDTO.getNumeroCuotas().intValue() <= detalle.getCantidadMaximaCouta()
                                            .intValue()) {
                                if (financiacionDTO.getValorTotal().compareTo(detalle.getValorMinimoFinanciar()) == -1
                                        || financiacionDTO.getValorTotal()
                                                .compareTo(detalle.getValorMaximoFinanciar()) == 1) {
                                    errores.add(obtenerErrorFinanciacion(
                                            "Para la cantidad de cuotas, el valor a financiar debe estar entre "
                                                    + detalle.getValorMinimoFinanciar().toPlainString() + " y "
                                                    + detalle.getValorMaximoFinanciar().toString()));
                                }
                                detalleNoConfiguradoCuotas = false;
                                break;
                            }
                        }
                        if (detalleNoConfiguradoCuotas) {
                            errores.add(obtenerErrorFinanciacion(
                                    "No existe una configuración para la cantidad de cuotas y valor a financiar"));
                        }
                        break;
                    case DETALLE_PORCENTAJE_CUOTA_INICIAL:
                        boolean detalleNoConfiguradoPorcentaje = true;
                        for (DetallePorcentajeCuotaIniciDTO detalle : configuracionFinanciacionDTO
                                .getLstDetallePorcentajeCuotaInici()) {
                            if (financiacionDTO.getValorTotal().compareTo(detalle.getValorMinimoFinanciar()) >= 0
                                    && financiacionDTO.getValorTotal()
                                            .compareTo(detalle.getValorMaximoFinanciar()) <= 0) {
                                BigDecimal valorCuotaInicial = BigDecimal.valueOf(-1);
                                for (DetalleFinanciacionDTO detalleFinanciacion : financiacionDTO
                                        .getDetallesFinanciacion()) {
                                    if (detalleFinanciacion.getNumeroCuota().intValue() == 1) {
                                        valorCuotaInicial = detalleFinanciacion.getValorTotal();
                                        break;
                                    }
                                }
                                BigDecimal porcentajeValorCuota = BigDecimal.valueOf(valorCuotaInicial.doubleValue()
                                        * 100 / financiacionDTO.getValorTotal().doubleValue());
                                if (porcentajeValorCuota.compareTo(detalle.getPorcentajeCuotaInicial()) == -1) {
                                    errores.add(obtenerErrorFinanciacion(
                                            "El porcentaje a pagar de la cuota inicial debe ser mayor al "
                                                    + detalle.getPorcentajeCuotaInicial().toString() + "%"));
                                }
                                detalleNoConfiguradoPorcentaje = false;
                                break;
                            }
                        }
                        if (detalleNoConfiguradoPorcentaje) {
                            errores.add(obtenerErrorFinanciacion(
                                    "No existe una configuración para el porcentaje de la cuota inicial y valor a financiar"));
                        }
                        break;

                    case FINANCIAR_OBLIGACIONES_NO_NOTIFICADAS:
                        for (ValorCondicionFinanciacionDTO valor : variableCondicionDTO
                                .getLstValorCondicionFinanciacion()) {
                            boolean notificadas = Boolean.parseBoolean(valor.getValor());
                            if (!notificadas) {
                                for (ObligacionFinanciacionDTO obligaciones : financiacionDTO
                                        .getObligacionesFinanciacion()) {
                                    boolean consulta = irCompaFin.validarComparendoNotificacion(
                                            obligaciones.getNumeroObligacion(), seguridadCirculemosEJB
                                                    .obtenerOrganismoTransitoUsuario().getCodigoOrganismo());
                                    if (!consulta) {
                                        errores.add(obtenerErrorFinanciacion("Existen obligaciones no notificadas"));
                                    }
                                }

                            }
                        }
                        break;

                    default:
                        errores.add(obtenerErrorFinanciacion("Variable de condiciona no validada" + variable));
                        break;
                    }
                }
            }
            financiacionDTO.setConfiguracionFinanciacion(configuracionFinanciacionDTO);
            resultado.setFinanciacion(financiacionDTO);
        }
        if (errores.isEmpty()) {
            resultado.setCodigoError(EnumErroresResultadoFinanciacion.RESULTADO_VALIDACION_EXITOSO.getValue());
        } else {
            resultado.setCodigoError(EnumErroresResultadoFinanciacion.RESULTADO_VALIDACION_ERROR.getValue());
        }
        return resultado;
    }

    private ErrorFinanciacionDTO obtenerErrorFinanciacion(String descripcion) {
        ErrorFinanciacionDTO error = new ErrorFinanciacionDTO();
        error.setDescripcion(descripcion);
        return error;
    }

    @Override
    public List<CondicionFinanciacionDTO> consultarCondicionesConfiguracionFinanciacion(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO, boolean activo) {
        logger.debug("FinanciacionEJB::consultarCondicionesConfiguracion(ConfiguracionFinanciacionDTO)");
        List<CondicionFinanciacionDTO> condiciones = new ArrayList<CondicionFinanciacionDTO>();
        Map<String, Object> filtros = new HashMap<String, Object>();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT cf FROM CondicionFinanciacion cf ");
        jpql.append("JOIN cf.lstEstadoCondicionFinanciacion ecf ");
        jpql.append("WHERE ecf.configuracionFinanciacion.id = :idConfiguracion ");
        filtros.put("idConfiguracion", configuracionFinanciacionDTO.getId());
        if (activo) {
            jpql.append("AND ecf.activo = :activo ");
            filtros.put("activo", true);
        }

        GenericDao<CondicionFinanciacion> dao = new GenericDao<CondicionFinanciacion>(CondicionFinanciacion.class, em);
        List<CondicionFinanciacion> resultados = dao.buildAndExecuteQuery(jpql, filtros);
        if (!resultados.isEmpty()) {
            condiciones = CondicionFinanciacionHelper.toListLevel1DTO(resultados);
        }
        return condiciones;
    }

    @Override
    public List<ValorCondicionFinanciacionDTO> consultarValorCondicionFinanciacion(Integer variableCondicion) {
        logger.debug("FinanciacionEJB::consultarValorCondicionFinanciacion(String)");
        List<ValorCondicionFinanciacionDTO> condicion = new ArrayList<ValorCondicionFinanciacionDTO>();
        Map<String, Object> filtros = new HashMap<String, Object>();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT vf FROM ValorCondicionFinanciacion vf ");
        jpql.append("WHERE vf.variableCondicionFinan.id = :id_variable ");
        // filtros.put("id_variable", variableCondicion);

        TypedQuery<ValorCondicionFinanciacion> query = em.createQuery(jpql.toString(),
                ValorCondicionFinanciacion.class);
        query.setParameter("id_variable", variableCondicion);

        List<ValorCondicionFinanciacion> resultado = query.getResultList();
        if (!resultado.isEmpty()) {
            condicion = ValorCondicionFinanciacionHelper.toListLevel1DTO(resultado);
        }
        return condicion;

    }
}
