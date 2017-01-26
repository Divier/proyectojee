package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.CondicionCoactivoDTO;
import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.dto.EstadoCondicionCoactivoDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.ValorCondicionCoactivoDTO;
import co.com.datatools.c2.dto.VariableCondicionCoacDTO;
import co.com.datatools.c2.entidades.Coactivo;
import co.com.datatools.c2.entidades.CondicionCoactivo;
import co.com.datatools.c2.entidades.ConfiguracionCoactivo;
import co.com.datatools.c2.entidades.EstadoCondicionCoactivo;
import co.com.datatools.c2.entidades.ValorCondicionCoactivo;
import co.com.datatools.c2.entidades.VariableCondicionCoac;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeraciones.EnumCampoConfiguracionCoactivo;
import co.com.datatools.c2.enumeraciones.EnumErrorConfiguracionCoactivo;
import co.com.datatools.c2.negocio.helpers.CoactivoHelper;
import co.com.datatools.c2.negocio.helpers.CondicionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.ConfiguracionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.EstadoCondicionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.ValorCondicionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.extend.ValorCondicionCoactivoHelperExtend;
import co.com.datatools.c2.negocio.helpers.extend.VariableCondicionCoacHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionCoactivo;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class AdministracionCoactivoEJB
 */
@Stateless(name = "AdministracionCoactivoEJB")
@LocalBean
public class AdministracionCoactivoEJB implements IRAdministracionCoactivo, ILAdministracionCoactivo {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(AdministracionCoactivoEJB.class);

    @Override
    public List<ConfiguracionCoactivoDTO> consultarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO pConfiguracionCoactivoDTO, Boolean vigente) {
        logger.debug("AdministracionCoactivoEJB::consultarConfiguracionCoactivo(ConfiguracionCoactivoDTO, Boolean)");
        List<ConfiguracionCoactivoDTO> lstConfiguracionCoactivoDTOs = new ArrayList<ConfiguracionCoactivoDTO>();

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT conf FROM ConfiguracionCoactivo conf");
        jpql.append(" WHERE 1=1 ");
        Map<String, Object> filtros = new HashMap<String, Object>();
        if (pConfiguracionCoactivoDTO != null) {
            if (pConfiguracionCoactivoDTO.getId() != null) {
                jpql.append(" AND conf.id = :pCodConfCoac");
                filtros.put("pCodConfCoac", pConfiguracionCoactivoDTO.getId());
            } else {
                if (pConfiguracionCoactivoDTO.getOrganismoTransito() != null
                        && pConfiguracionCoactivoDTO.getOrganismoTransito().getCodigoOrganismo() != null) {
                    jpql.append(" AND conf.organismoTransito.codigoOrganismo = :pCodOrganismo ");
                    filtros.put("pCodOrganismo", pConfiguracionCoactivoDTO.getOrganismoTransito().getCodigoOrganismo());
                }
                if (StringUtils.isNotBlank(pConfiguracionCoactivoDTO.getNombre())) {
                    jpql.append(" AND UPPER(conf.nombre) LIKE :pNomConf");
                    filtros.put("pNomConf", "%" + pConfiguracionCoactivoDTO.getNombre() + "%");
                }
                if (pConfiguracionCoactivoDTO.getFechaInicial() != null) {
                    jpql.append(" AND conf.fechaInicial = :pFechaIniVigencia");
                    filtros.put("pFechaIniVigencia", pConfiguracionCoactivoDTO.getFechaInicial());
                }
                if (pConfiguracionCoactivoDTO.getFechaFinal() != null) {
                    jpql.append(" AND conf.fechaFinal = :pFechaFinVigencia");
                    filtros.put("pFechaFinVigencia", pConfiguracionCoactivoDTO.getFechaFinal());
                }
            }
        }
        if (vigente) {
            jpql.append(" AND conf.fechaInicial <= :fechaActual ");
            jpql.append(" AND (conf.fechaFinal IS NULL or conf.fechaFinal >= :fechaActual) ");
            filtros.put("fechaActual", new Date());
        }

        GenericDao<ConfiguracionCoactivo> dao = new GenericDao<ConfiguracionCoactivo>(ConfiguracionCoactivo.class, em);

        List<ConfiguracionCoactivo> resultados = dao.buildAndExecuteQuery(jpql, filtros);
        if (!resultados.isEmpty()) {
            lstConfiguracionCoactivoDTOs = ConfiguracionCoactivoHelper.toListLevel1DTO(resultados);
            if ((pConfiguracionCoactivoDTO != null && pConfiguracionCoactivoDTO.getId() != null) || vigente) {
                ConfiguracionCoactivoDTO configuracionCoactivoDTO = lstConfiguracionCoactivoDTOs.get(0);
                ConfiguracionCoactivo configuracionCoactivo = resultados.get(0);
                configuracionCoactivoDTO.setLstValorCondicionCoactivo(ValorCondicionCoactivoHelperExtend
                        .toListLevel1DTO(configuracionCoactivo.getLstValorCondicionCoactivo()));
                configuracionCoactivoDTO.setLstEstadoCondicionCoactivo(EstadoCondicionCoactivoHelper
                        .toListLevel1DTO(configuracionCoactivo.getLstEstadoCondicionCoactivo()));
            }
        }
        return lstConfiguracionCoactivoDTOs;
    }

    @Override
    public List<CondicionCoactivoDTO> consultarCondicionesConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO, Boolean activo) {
        logger.debug(
                "AdministracionCoactivoEJB::consultarCondicionesConfiguracionCoactivo(ConfiguracionCoactivoDTO,Boolean)");
        List<CondicionCoactivoDTO> condiciones = new ArrayList<CondicionCoactivoDTO>();
        Map<String, Object> filtros = new HashMap<String, Object>();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT cf FROM CondicionCoactivo cf ");
        jpql.append("JOIN cf.lstEstadoCondicionCoactivo ecf ");
        jpql.append("WHERE ecf.configuracionCoactivo.id = :idConfiguracion ");
        filtros.put("idConfiguracion", configuracionCoactivoDTO.getId());
        if (activo) {
            jpql.append("AND ecf.activo = :activo ");
            filtros.put("activo", true);
        }

        GenericDao<CondicionCoactivo> dao = new GenericDao<CondicionCoactivo>(CondicionCoactivo.class, em);
        List<CondicionCoactivo> resultados = dao.buildAndExecuteQuery(jpql, filtros);
        if (!resultados.isEmpty()) {
            condiciones = CondicionCoactivoHelper.toListLevel1DTO(resultados);
            for (CondicionCoactivoDTO condicionCoactivoDTO : condiciones) {
                condicionCoactivoDTO.setLstVariablesCondicionCoac(
                        consultarVariablesCondicionCoactivo(condicionCoactivoDTO, configuracionCoactivoDTO.getId()));
            }
        }
        return condiciones;
    }

    @Override
    public List<VariableCondicionCoacDTO> consultarVariablesCondicionCoactivo(CondicionCoactivoDTO condicionCoactivoDTO,
            Integer idConfiguracionCoactivo) {
        logger.debug("AdministracionCoactivoEJB::consultarVariablesCondicionCoactivo(CondicionCoactivoDTO)");
        List<VariableCondicionCoacDTO> condiciones = new ArrayList<>();
        Map<String, Object> filtros = new HashMap<String, Object>();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT var FROM VariableCondicionCoac var ");
        jpql.append("LEFT JOIN FETCH var.lstValorCondicionCoactivo val ");
        jpql.append("WHERE var.condicionCoactivo.codigo = :idCondicion ");
        filtros.put("idCondicion", condicionCoactivoDTO.getCodigo());
        if (idConfiguracionCoactivo != null) {
            jpql.append("AND val.configuracionCoactivo.id = :idConfiguracionCoactivo ");
            filtros.put("idConfiguracionCoactivo", idConfiguracionCoactivo);
        }

        GenericDao<VariableCondicionCoac> dao = new GenericDao<VariableCondicionCoac>(VariableCondicionCoac.class, em);
        List<VariableCondicionCoac> resultados = dao.buildAndExecuteQuery(jpql, filtros);
        if (!resultados.isEmpty()) {
            condiciones = VariableCondicionCoacHelperExtend.toListLevel2DTO(resultados);
        }
        return condiciones;
    }

    @Override
    public List<CondicionCoactivoDTO> consultarCondicionesCoactivo(int pCodigoOrganismo) {
        List<CondicionCoactivoDTO> lstCondicionesCoactivoDTO = new ArrayList<CondicionCoactivoDTO>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cf FROM CondicionCoactivo cf LEFT JOIN cf.lstCondicionOrganismo org");
        sql.append(" WHERE org.codigoOrganismo = :codOrg");
        sql.append(" ORDER BY cf.orden ASC");

        TypedQuery<CondicionCoactivo> query = em.createQuery(sql.toString(), CondicionCoactivo.class);
        query.setParameter("codOrg", pCodigoOrganismo);

        List<CondicionCoactivo> lstCondicionesCoactivo = query.getResultList();
        if (!lstCondicionesCoactivo.isEmpty()) {
            lstCondicionesCoactivoDTO = CondicionCoactivoHelper.toListLevel1DTO(lstCondicionesCoactivo);
        }

        return lstCondicionesCoactivoDTO;
    }

    @Override
    public RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> eliminarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO) {
        List<CoactivoDTO> coactivoDTOs = consultarCoactivosXConfiguracion(configuracionCoactivoDTO);
        RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> response = null;
        if (coactivoDTOs.isEmpty()) {
            ConfiguracionCoactivo configuracionCoactivo = em.find(ConfiguracionCoactivo.class,
                    configuracionCoactivoDTO.getId());
            em.remove(configuracionCoactivo);
            em.flush();
            response = new RespuestaDTO<>(true);
            response.setNombreRespuesta(
                    EnumErrorConfiguracionCoactivo.ELIMINACION_CONFIGURACION_COACTIVO_SATISFACTORIA.toString());
            response.setDescripcionRespuesta(
                    EnumErrorConfiguracionCoactivo.ELIMINACION_CONFIGURACION_COACTIVO_SATISFACTORIA.getDescripcion());
        } else {
            response = new RespuestaDTO<>(false);
            response.setNombreRespuesta(
                    EnumErrorConfiguracionCoactivo.ERROR_VALIDACION_CONFIGURACION_UTILIZADA.toString());
            response.setDescripcionRespuesta(
                    EnumErrorConfiguracionCoactivo.ERROR_VALIDACION_CONFIGURACION_UTILIZADA.getDescripcion());
        }
        return response;
    }

    @Override
    public List<CoactivoDTO> consultarCoactivosXConfiguracion(ConfiguracionCoactivoDTO configuracionCoactivoDTO) {
        logger.debug("AdministracionCoactivoEJB::consultarCoactivosXConfiguracion(ConfiguracionCoactivoDTO)");
        List<CoactivoDTO> coactivoDTOs = new ArrayList<CoactivoDTO>();
        Map<String, Object> filtros = new HashMap<String, Object>();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT c FROM Coactivo c");
        jpql.append(" WHERE 1=1");

        if (configuracionCoactivoDTO != null) {
            if (configuracionCoactivoDTO.getId() != null) {
                jpql.append(" AND c.configuracionCoactivo.id = :idConfiguracion");
                filtros.put("idConfiguracion", configuracionCoactivoDTO.getId());
            }
        }
        jpql.append(" ORDER BY c.proceso.fechaInicio DESC ");

        GenericDao<Coactivo> dao = new GenericDao<Coactivo>(Coactivo.class, em);
        List<Coactivo> resultados = dao.buildAndExecuteQuery(jpql, filtros);

        if (!resultados.isEmpty()) {
            coactivoDTOs.add(CoactivoHelper.toLevel1DTO(resultados.get(0)));
        }
        return coactivoDTOs;
    }

    @Override
    public HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> registrarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO) {
        HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> response = validarRegistroConfiguracionCoactivo(
                configuracionCoactivoDTO);
        Iterator<RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> elements = response
                .values().iterator();
        while (elements.hasNext()) {
            RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> respDTO = elements.next();
            if (respDTO.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                // RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> respConfCoactivoDTO = response
                // .get(configuracionCoactivoDTO);
                // respConfCoactivoDTO.setRespuesta(EnumRespuestaSistema.ERRORES);
                // respConfCoactivoDTO
                // .setNombreRespuesta(EnumErrorConfiguracionCoactivo.ERROR_VALIDACION_CONFIGURACION_COACTIVO
                // .getDescripcion());
                // respConfCoactivoDTO
                // .setDescripcionRespuesta(EnumErrorConfiguracionCoactivo.ERROR_VALIDACION_CONFIGURACION_COACTIVO
                // .getDescripcion());
                return response;
            }
        }

        /**
         * Construccion de objetos a persistir.
         */
        ConfiguracionCoactivo configuracionCoactivo = ConfiguracionCoactivoHelper
                .toLevel1Entity(configuracionCoactivoDTO, null);
        configuracionCoactivo.setLstValorCondicionCoactivo(new ArrayList<ValorCondicionCoactivo>());
        /*
         * Se lleva a cabo asignacion de lo valores respectivos.
         */
        for (ValorCondicionCoactivoDTO valorCondicionCoactivoDTO : configuracionCoactivoDTO
                .getLstValorCondicionCoactivo()) {
            ValorCondicionCoactivo valorCondicionCoactivo = ValorCondicionCoactivoHelperExtend
                    .toLevel1Entity(valorCondicionCoactivoDTO, null);
            valorCondicionCoactivo.setConfiguracionCoactivo(configuracionCoactivo);
            configuracionCoactivo.getLstValorCondicionCoactivo().add(valorCondicionCoactivo);
        }

        /*
         * Se lleva a cabo la asignacion de los estados de las condiciones.
         */
        configuracionCoactivo.setLstEstadoCondicionCoactivo(new ArrayList<EstadoCondicionCoactivo>());
        for (EstadoCondicionCoactivoDTO estCondCoactivoDTO : configuracionCoactivoDTO.getLstEstadoCondicionCoactivo()) {
            EstadoCondicionCoactivo estadoCondicionCoactivo = EstadoCondicionCoactivoHelper
                    .toLevel1Entity(estCondCoactivoDTO, null);
            estadoCondicionCoactivo.setConfiguracionCoactivo(configuracionCoactivo);
            configuracionCoactivo.getLstEstadoCondicionCoactivo().add(estadoCondicionCoactivo);
        }

        em.persist(configuracionCoactivo);
        em.flush();

        RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> respuestaConfiguracionRegistrada = new RespuestaDTO<>(
                true);
        respuestaConfiguracionRegistrada
                .setNombreRespuesta(EnumErrorConfiguracionCoactivo.CONFIGURACION_COACTIVO_REGISTRADA.toString());
        respuestaConfiguracionRegistrada.setDescripcionRespuesta(
                EnumErrorConfiguracionCoactivo.CONFIGURACION_COACTIVO_REGISTRADA.getDescripcion());

        response.put(configuracionCoactivoDTO, respuestaConfiguracionRegistrada);

        return response;
    }

    /**
     * Expone los servicios de validacion al registrar una nueva configuracion de coactivo. (Recorre los objetos que representan los campos de una
     * configuración de coactivo validandolos)
     * 
     * @param configuracionCoactivoDTO
     *            : Configuracion de coactivo a validar.
     * @return HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>>
     * @author Dixon.Alvarez 206-09-05
     */
    private HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> validarRegistroConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO) {

        HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> validacion = new HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>>();

        /**
         * Se lleva a cabo la validacion de existencia de una Configuracion de coactivo sin cerrar. En este primer caso se retorna la validacion
         * inmediatamente.
         */
        ConfiguracionCoactivoDTO configuracionExistente = consultarExistenciaConfCoactivoSinCerrar(
                configuracionCoactivoDTO);
        if (configuracionExistente != null) {
            RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> respuesta = new RespuestaDTO<>(
                    false);
            respuesta.setNombreRespuesta(
                    EnumErrorConfiguracionCoactivo.ERROR_CONFIGURACION_COACTIVO_SIN_CERRAR.toString());
            respuesta.setDescripcionRespuesta(
                    EnumErrorConfiguracionCoactivo.ERROR_CONFIGURACION_COACTIVO_SIN_CERRAR.getDescripcion()
                            + " Nombre Configuración: " + configuracionExistente.getNombre());

            validacion.put(configuracionCoactivoDTO, respuesta);
            return validacion;
        }

        /**
         * Se valida si existe una configuracion que se cruce con la fecha de vigencia de la configuracion a registrar
         */
        if (validarConfiguracionVigente(configuracionCoactivoDTO)) {
            RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> respuestaConfiguracionVigente = new RespuestaDTO<>(
                    false);
            respuestaConfiguracionVigente
                    .setNombreRespuesta(EnumErrorConfiguracionCoactivo.ERROR_FECHAS_VIGENCIA_CRUZADAS.toString());
            respuestaConfiguracionVigente.setDescripcionRespuesta(
                    EnumErrorConfiguracionCoactivo.ERROR_FECHAS_VIGENCIA_CRUZADAS.getDescripcion());

            validacion.put(configuracionCoactivoDTO, respuestaConfiguracionVigente);
            return validacion;
        }

        return validacion;
    }

    @Override
    public ConfiguracionCoactivoDTO consultarExistenciaConfCoactivoSinCerrar(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO) {
        ConfiguracionCoactivoDTO confCoactivoDTOExistente = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cf FROM ConfiguracionCoactivo AS cf");
        sql.append(" WHERE cf.organismoTransito.codigoOrganismo = :pCodOrgTrans");
        sql.append(" AND cf.fechaFinal IS NULL");

        TypedQuery<ConfiguracionCoactivo> query = em.createQuery(sql.toString(), ConfiguracionCoactivo.class);
        query.setParameter("pCodOrgTrans", configuracionCoactivoDTO.getOrganismoTransito().getCodigoOrganismo());
        List<ConfiguracionCoactivo> resultados = query.getResultList();
        if (!resultados.isEmpty()) {
            confCoactivoDTOExistente = ConfiguracionCoactivoHelper.toLevel1DTO(resultados.get(0));
        }

        return confCoactivoDTOExistente;
    }

    /**
     * Valida si ya existe una configuracion de Coactivo para el rango de fechas en el que queda la configuracion que se va registrar
     * 
     * @param configuracionCoactivoDTO
     *            Corresponde a la informacion de la configuracion a validar
     * @return True, si existe una configuracion de Coactivo que se cruce
     * @author Dixon.Alvarez 2016-09-05
     */
    private boolean validarConfiguracionVigente(ConfiguracionCoactivoDTO configuracionCoactivoDTO) {
        boolean existeConfiguracionVigente = false;
        if (configuracionCoactivoDTO.getFechaInicial() != null) {
            ConfiguracionCoactivoDTO filtro = new ConfiguracionCoactivoDTO();
            configuracionCoactivoDTO.setOrganismoTransito(configuracionCoactivoDTO.getOrganismoTransito());
            List<ConfiguracionCoactivoDTO> configuraciones = consultarConfiguracionCoactivo(filtro, false);
            for (ConfiguracionCoactivoDTO configuracion : configuraciones) {
                if (configuracionCoactivoDTO.getId() == null
                        || configuracionCoactivoDTO.getId().intValue() != configuracion.getId().intValue()) {
                    if (configuracion.getFechaFinal() != null) {
                        if (UtilFecha.betweenDate(configuracion.getFechaInicial(), configuracion.getFechaFinal(),
                                configuracionCoactivoDTO.getFechaInicial())
                                || (configuracionCoactivoDTO.getFechaFinal() == null && UtilFecha.betweenDate(
                                        configuracion.getFechaInicial(), configuracion.getFechaFinal(), new Date()))
                                || (configuracionCoactivoDTO.getFechaFinal() != null && UtilFecha.betweenDate(
                                        configuracion.getFechaInicial(), configuracion.getFechaFinal(),
                                        configuracionCoactivoDTO.getFechaFinal()))
                                || (configuracionCoactivoDTO.getFechaFinal() != null && UtilFecha.betweenDate(
                                        configuracionCoactivoDTO.getFechaInicial(),
                                        configuracionCoactivoDTO.getFechaFinal(), configuracion.getFechaFinal()))) {
                            existeConfiguracionVigente = true;
                        }
                    } else {
                        existeConfiguracionVigente = true;
                        if (configuracionCoactivoDTO.getFechaFinal() != null
                                && configuracionCoactivoDTO.getFechaFinal().before(configuracion.getFechaInicial())) {
                            existeConfiguracionVigente = false;
                        }
                    }

                    // if ((configuracion.getFechaFinal() == null && )
                    // || configuracion.getFechaFinal() == null
                    // || UtilFecha.betweenDate(configuracion.getFechaInicial(), configuracion.getFechaFinal(),
                    // configuracionCoactivoDTO.getFechaInicial())
                    // || (configuracionCoactivoDTO.getFechaFinal() == null && UtilFecha.betweenDate(
                    // configuracion.getFechaInicial(), configuracion.getFechaFinal(), new Date()))
                    // || (configuracionCoactivoDTO.getFechaFinal() != null && UtilFecha.betweenDate(
                    // configuracion.getFechaInicial(), configuracion.getFechaFinal(),
                    // configuracionCoactivoDTO.getFechaFinal()))
                    // || (configuracionCoactivoDTO.getFechaFinal() != null && UtilFecha.betweenDate(
                    // configuracionCoactivoDTO.getFechaInicial(),
                    // configuracionCoactivoDTO.getFechaFinal(), configuracion.getFechaFinal()))) {
                    //
                    // }
                }
            }
        }
        return existeConfiguracionVigente;
    }

    @Override
    public HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> modificarConfiguracionCoactivo(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO) {

        HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> response = new HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>>();
        /**
         * Se valida si existe una configuracion que se cruce con la fecha de vigencia de la configuracion a registrar
         */
        if (validarConfiguracionVigente(configuracionCoactivoDTO)) {
            RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> respuestaConfiguracionVigente = new RespuestaDTO<>(
                    false);
            respuestaConfiguracionVigente
                    .setNombreRespuesta(EnumErrorConfiguracionCoactivo.ERROR_FECHAS_VIGENCIA_CRUZADAS.toString());
            respuestaConfiguracionVigente.setDescripcionRespuesta(
                    EnumErrorConfiguracionCoactivo.ERROR_FECHAS_VIGENCIA_CRUZADAS.getDescripcion());

            response.put(configuracionCoactivoDTO, respuestaConfiguracionVigente);
            return response;
        }

        /**
         * Se lleva a cabo la modificacion de los registros
         */
        ConfiguracionCoactivo configuracionCoactivo = em.find(ConfiguracionCoactivo.class,
                configuracionCoactivoDTO.getId());
        configuracionCoactivo = ConfiguracionCoactivoHelper.toLevel1Entity(configuracionCoactivoDTO,
                configuracionCoactivo);
        /**
         * Se lleva a cabo la asignacion de los valores de condicion.
         */
        configuracionCoactivo.getLstValorCondicionCoactivo().clear();
        for (ValorCondicionCoactivoDTO valCondCoactivoDTO : configuracionCoactivoDTO.getLstValorCondicionCoactivo()) {
            ValorCondicionCoactivo valorCondicionCoactivo = ValorCondicionCoactivoHelper
                    .toLevel1Entity(valCondCoactivoDTO, null);
            valorCondicionCoactivo.setConfiguracionCoactivo(configuracionCoactivo);
            configuracionCoactivo.getLstValorCondicionCoactivo().add(valorCondicionCoactivo);
        }

        /**
         * Se lleva a cabo la asignacion de los estados de las condiciones
         */
        configuracionCoactivo.getLstEstadoCondicionCoactivo().clear();
        for (EstadoCondicionCoactivoDTO estadoCondicionCoactivoDTO : configuracionCoactivoDTO
                .getLstEstadoCondicionCoactivo()) {
            EstadoCondicionCoactivo estadoCondicionCoactivo = EstadoCondicionCoactivoHelper
                    .toLevel1Entity(estadoCondicionCoactivoDTO, null);
            estadoCondicionCoactivo.setConfiguracionCoactivo(configuracionCoactivo);
            configuracionCoactivo.getLstEstadoCondicionCoactivo().add(estadoCondicionCoactivo);
        }

        /**
         * Se lleva a cabo la actualizacion del registro modificado
         */
        em.merge(configuracionCoactivo);

        RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> respuestaConfiguracionModificada = new RespuestaDTO<>(
                true);
        respuestaConfiguracionModificada
                .setNombreRespuesta(EnumErrorConfiguracionCoactivo.CONFIGURACION_COACTIVO_MODIFICADA.toString());
        respuestaConfiguracionModificada.setDescripcionRespuesta(
                EnumErrorConfiguracionCoactivo.CONFIGURACION_COACTIVO_MODIFICADA.getDescripcion());

        response.put(configuracionCoactivoDTO, respuestaConfiguracionModificada);

        return response;
    }
}
