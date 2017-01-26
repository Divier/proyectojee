package co.com.datatools.c2.negocio.ejb;

import static co.com.datatools.c2.util.Utilidades.safeList;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ActividadDTO;
import co.com.datatools.c2.dto.ConfiguracionEntidadDTO;
import co.com.datatools.c2.dto.ConsultaOrganismoTransitoDTO;
import co.com.datatools.c2.dto.ProcesoNegocioDTO;
import co.com.datatools.c2.dto.RespuestaIngresarDireccionDTO;
import co.com.datatools.c2.dto.comun.CardinalidadDireccionDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionOrganismoDTO;
import co.com.datatools.c2.dto.comun.EntidadOficioDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.NombreViaDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.dto.configuracion.AsociaDocumentoIdentidadFormatoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.personas.ClaseActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.DivisionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.EstadoCivilDTO;
import co.com.datatools.c2.dto.personas.GrupoActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.NivelEducativoDTO;
import co.com.datatools.c2.dto.personas.SeccionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.personas.TipoSociedadDTO;
import co.com.datatools.c2.dto.personas.TipoViviendaDTO;
import co.com.datatools.c2.dto.reglas.ConsEstadCivilConyPais;
import co.com.datatools.c2.dto.reglas.ConsTipoFuenInfoPaisDTO;
import co.com.datatools.c2.entidades.ConfiguracionEntidad;
import co.com.datatools.c2.entidades.ProcesoNegocio;
import co.com.datatools.c2.entidades.comun.CardinalidadDireccion;
import co.com.datatools.c2.entidades.comun.Departamento;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.DireccionOrganismo;
import co.com.datatools.c2.entidades.comun.Localidad;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.NombreVia;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.comun.TipoVia;
import co.com.datatools.c2.entidades.personas.ClaseActividadEconomica;
import co.com.datatools.c2.entidades.personas.DivisionActividadEconomica;
import co.com.datatools.c2.entidades.personas.EstadoCivil;
import co.com.datatools.c2.entidades.personas.GrupoActividadEconomica;
import co.com.datatools.c2.entidades.personas.NivelEducativo;
import co.com.datatools.c2.entidades.personas.SeccionActividadEconomica;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.personas.TipoSociedad;
import co.com.datatools.c2.entidades.personas.TipoVivienda;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumConfiguracion;
import co.com.datatools.c2.excepciones.CirculemosIllegalArgumentException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.error.ErrorAdministracion.AdministrarOrganismoTransito;
import co.com.datatools.c2.negocio.helpers.ConfiguracionEntidadHelper;
import co.com.datatools.c2.negocio.helpers.comun.CardinalidadDireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.DepartamentoHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelperExtend;
import co.com.datatools.c2.negocio.helpers.comun.DireccionOrganismoHelper;
import co.com.datatools.c2.negocio.helpers.comun.LocalidadHelper;
import co.com.datatools.c2.negocio.helpers.comun.MunicipioHelper;
import co.com.datatools.c2.negocio.helpers.comun.NombreViaHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.PaisHelper;
import co.com.datatools.c2.negocio.helpers.comun.TipoViaHelper;
import co.com.datatools.c2.negocio.helpers.personas.ClaseActividadEconomicaHelper;
import co.com.datatools.c2.negocio.helpers.personas.DivisionActividadEconomicaHelper;
import co.com.datatools.c2.negocio.helpers.personas.EstadoCivilHelper;
import co.com.datatools.c2.negocio.helpers.personas.GrupoActividadEconomicaHelper;
import co.com.datatools.c2.negocio.helpers.personas.NivelEducativoHelper;
import co.com.datatools.c2.negocio.helpers.personas.SeccionActividadEconomicaHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoIdentificacionPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoSociedadHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoViviendaHelper;
import co.com.datatools.c2.negocio.helpers.v2.ProcesoNegocioHelper;
import co.com.datatools.c2.negocio.interfaces.ILAdministracion;
import co.com.datatools.c2.negocio.interfaces.ILDireccion;
import co.com.datatools.c2.negocio.interfaces.ILPersona;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.numeraciones.EnumFormatoDocumentoIdentidad;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.mail.EmailValidator;

/**
 * Session Bean implementation class AdministracionEJB
 */
@Stateless(name = "AdministracionEJB")
@LocalBean
public class AdministracionEJB implements ILAdministracion, IRAdministracion {

    private final static Logger logger = Logger.getLogger(AdministracionEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILPersona personaEjb;
    @EJB
    private ILDireccion direccionEJB;
    @EJB
    private IRFachadaConfiguracion fachadaConfiguracion;
    @EJB
    private IRCatalogo iRCatalogo;

    public AdministracionEJB() {
        logger.info("AdministracionEJB::Constructor");
    }

    @Override
    public List<OrganismoTransitoDTO> consultarOrganismoTransito(String codigo) {
        List<OrganismoTransitoDTO> lstOrganismosTransitoDTO = new ArrayList<OrganismoTransitoDTO>(1);
        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT ot FROM OrganismoTransito AS ot");
        jpql.append(" WHERE 1=1");

        if (StringUtils.isNotBlank(codigo)) {
            jpql.append(" AND str(ot.codigoOrganismo) LIKE :pCodOrg");
        }

        Session session = em.unwrap(Session.class);
        org.hibernate.Query query = session.createQuery(jpql.toString());
        if (StringUtils.isNotBlank(codigo)) {
            query.setParameter("pCodOrg", "%" + codigo + "%");
        }

        @SuppressWarnings("unchecked")
        List<OrganismoTransito> resultado = query.list();
        if (!resultado.isEmpty()) {
            lstOrganismosTransitoDTO = OrganismoTransitoHelper.toListLevel1DTO(resultado);
        }

        return lstOrganismosTransitoDTO;
    }

    @Override
    public List<OrganismoTransitoDTO> consultarOrganismoTransito(
            ConsultaOrganismoTransitoDTO consultaOrganismoTransito) {
        final List<OrganismoTransitoDTO> organismosTransitoDTO = new ArrayList<OrganismoTransitoDTO>(1);

        final StringBuilder jpql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        jpql.append("SELECT DISTINCT(ot) FROM OrganismoTransito ot ");
        if (consultaOrganismoTransito != null) {
            jpql.append(" LEFT JOIN FETCH ot.municipio AS m");
            jpql.append(" LEFT JOIN FETCH m.departamento AS md");
            jpql.append(" LEFT JOIN FETCH md.pais AS mdp");
            jpql.append(" LEFT JOIN FETCH ot.departamento AS d");
            jpql.append(" LEFT JOIN FETCH d.pais AS dp");
            jpql.append(" LEFT JOIN FETCH ot.direccionOrganismo");
            jpql.append(" WHERE 1=1");

            if (consultaOrganismoTransito != null) {
                if (consultaOrganismoTransito.getCodigoOrganismo() != null) {
                    jpql.append(" AND ot.codigoOrganismo = :pCodOrg");
                    params.put("pCodOrg", consultaOrganismoTransito.getCodigoOrganismo());
                }
                if (StringUtils.isNotBlank(consultaOrganismoTransito.getCodigoRunt())) {
                    jpql.append(" AND ot.codigoRunt LIKE :pCodRunt");
                    params.put("pCodRunt", "%" + consultaOrganismoTransito.getCodigoRunt() + "%");
                }
                if (StringUtils.isNotBlank(consultaOrganismoTransito.getCodigoMinisterio())) {
                    jpql.append(" AND ot.codigoMinisterio LIKE :pCodMin");
                    params.put("pCodMin", "%" + consultaOrganismoTransito.getCodigoMinisterio() + "%");
                }
                if (StringUtils.isNotBlank(consultaOrganismoTransito.getNombreOrganismo())) {
                    jpql.append(" AND ot.nombreOrganismo LIKE :pNomOrg");
                    params.put("pNomOrg", "%" + consultaOrganismoTransito.getNombreOrganismo() + "%");
                }
                if (StringUtils.isNotBlank(consultaOrganismoTransito.getNitOrganismo())) {
                    jpql.append(" AND ot.nit LIKE :pNit");
                    params.put("pNit", "%" + consultaOrganismoTransito.getNitOrganismo() + "%");
                }
                if (consultaOrganismoTransito.getEstadoOrganismo() != null) {
                    jpql.append(" AND ot.activo = :pActivo");
                    params.put("pActivo", consultaOrganismoTransito.getEstadoOrganismo());
                }
                if (consultaOrganismoTransito.getIdPais() != null) {
                    jpql.append(" AND (dp.id = :pIdPais OR mdp.id = :pIdPais)");
                    params.put("pIdPais", consultaOrganismoTransito.getIdPais());
                }
            }
        }

        jpql.append(" ORDER BY ot.codigoOrganismo");

        final GenericDao<OrganismoTransito> dao = new GenericDao<>(OrganismoTransito.class, em);
        final List<OrganismoTransito> organismosTransito = dao.buildAndExecuteQuery(jpql.toString(), params);

        for (OrganismoTransito ot : organismosTransito) {
            final OrganismoTransitoDTO organismoTransitoDTO = OrganismoTransitoHelper.toLevel1DTO(ot);
            if (consultaOrganismoTransito != null) {
                if (ot.getDepartamento() != null) {
                    organismoTransitoDTO.setDepartamento(DepartamentoHelper.toLevel1DTO(ot.getDepartamento()));
                }

                if (ot.getMunicipio() != null) {
                    organismoTransitoDTO.setMunicipio(MunicipioHelper.toLevel1DTO(ot.getMunicipio()));
                    organismoTransitoDTO.getMunicipio()
                            .setDepartamento(DepartamentoHelper.toLevel1DTO(ot.getMunicipio().getDepartamento()));
                }

                DireccionOrganismoDTO direccionOrganismoDTO = obtenerDireccionOrganismoVigente(ot.getCodigoOrganismo());
                if (direccionOrganismoDTO != null) {
                    organismoTransitoDTO.setDireccionOrganismo(new ArrayList<DireccionOrganismoDTO>(1));
                    organismoTransitoDTO.getDireccionOrganismo().add(direccionOrganismoDTO);
                }
            }
            organismosTransitoDTO.add(organismoTransitoDTO);
        }

        return organismosTransitoDTO;
    }

    @Override
    public List<OrganismoTransitoDTO> consultarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO) {
        final List<OrganismoTransitoDTO> organismosTransitoDTO = new ArrayList<OrganismoTransitoDTO>(1);

        final StringBuilder jpql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        jpql.append("SELECT ot FROM OrganismoTransito ot ");
        jpql.append(" WHERE 1=1");

        // Codigo organismo
        if (organismoTransitoDTO.getCodigoOrganismo() != null) {
            jpql.append(" AND ot.codigoOrganismo = :pCodOrg");
            params.put("pCodOrg", organismoTransitoDTO.getCodigoOrganismo());
        }
        if (StringUtils.isNotBlank(organismoTransitoDTO.getCodigoRunt())) {
            jpql.append(" AND ot.codigoRunt LIKE :pCodRunt");
            params.put("pCodRunt", organismoTransitoDTO.getCodigoRunt());
        }
        if (StringUtils.isNotBlank(organismoTransitoDTO.getCodigoMinisterio())) {
            jpql.append(" AND ot.codigoMinisterio LIKE :pCodMin");
            params.put("pCodMin", organismoTransitoDTO.getCodigoMinisterio());
        }
        if (StringUtils.isNotBlank(organismoTransitoDTO.getNombreOrganismo())) {
            jpql.append(" AND ot.nombreOrganismo LIKE :pNomOrg");
            params.put("pNomOrg", organismoTransitoDTO.getNombreOrganismo());
        }
        if (organismoTransitoDTO.getActivo() != null) {
            jpql.append(" AND ot.activo = :pActivo");
            params.put("pActivo", organismoTransitoDTO.getActivo());
        }

        jpql.append(" ORDER BY ot.codigoOrganismo");

        final GenericDao<OrganismoTransito> dao = new GenericDao<>(OrganismoTransito.class, em);
        final List<OrganismoTransito> organismosTransito = dao.buildAndExecuteQuery(jpql.toString(), params);

        for (OrganismoTransito ot : organismosTransito) {
            final OrganismoTransitoDTO organismoTransitoBD = OrganismoTransitoHelper.toLevel1DTO(ot);
            organismosTransitoDTO.add(organismoTransitoBD);
        }

        return organismosTransitoDTO;
    }

    @Override
    public OrganismoTransitoDTO consultarOrganismoTransito(Integer codigoOrganismo) {
        OrganismoTransitoDTO organismoTransitoDTO = null;
        checkNotNull(codigoOrganismo, "Codigo organismo de transito a consultar vacio.");

        final TypedQuery<OrganismoTransito> query = em.createNamedQuery(OrganismoTransito.SQ_FIND_BY_COD_ORG,
                OrganismoTransito.class);
        query.setParameter("pCodOrg", codigoOrganismo);

        List<OrganismoTransito> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            OrganismoTransito ot = resultList.get(0);
            organismoTransitoDTO = OrganismoTransitoHelper.toLevel1DTO(ot);

            if (ot.getDepartamento() != null) {
                organismoTransitoDTO.setDepartamento(DepartamentoHelper.toLevel1DTO(ot.getDepartamento()));
            }

            if (ot.getMunicipio() != null) {
                organismoTransitoDTO.setMunicipio(MunicipioHelper.toLevel1DTO(ot.getMunicipio()));
                organismoTransitoDTO.getMunicipio()
                        .setDepartamento(DepartamentoHelper.toLevel1DTO(ot.getMunicipio().getDepartamento()));
            }

            DireccionOrganismoDTO direccionOrganismoDTO = obtenerDireccionOrganismoVigente(ot.getCodigoOrganismo());
            if (direccionOrganismoDTO != null) {
                organismoTransitoDTO.setDireccionOrganismo(new ArrayList<DireccionOrganismoDTO>(1));
                organismoTransitoDTO.getDireccionOrganismo().add(direccionOrganismoDTO);
            }
        }

        return organismoTransitoDTO;
    }

    @Override
    public void registrarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO)
            throws CirculemosNegocioException {
        validarOrganismoTransito(organismoTransitoDTO);

        OrganismoTransito organismoTransito = OrganismoTransitoHelper.toLevel1Entity(organismoTransitoDTO, null);

        procesarDireccion(organismoTransitoDTO, organismoTransito);

        em.persist(organismoTransito);

    }

    @Override
    public void actualizarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO)
            throws CirculemosNegocioException {
        validarOrganismoTransito(organismoTransitoDTO);

        OrganismoTransito organismoTransito = em.find(OrganismoTransito.class,
                organismoTransitoDTO.getCodigoOrganismo());
        organismoTransito = OrganismoTransitoHelper.toLevel1Entity(organismoTransitoDTO, null);
        procesarDireccion(organismoTransitoDTO, organismoTransito);

        em.merge(organismoTransito);

    }

    /**
     * Metodo que permite procesar la direccion ingresada al organismo teniendo en cuenta que dentro del listado de las direcciones entrantes solo se
     * encuentran la direccion vigente junto con la direccion nueva.
     * 
     * @param organismoTransitoDTO
     *            organismo con la informacion entrante
     * @param organismoTransito
     *            organismo al cual se ingresara la informacion
     * @throws CirculemosNegocioException
     *             <br>
     *             ADM_056005=Direccion del organismo no fue correctamente ingresada. No se completo la operacion
     */
    private void procesarDireccion(OrganismoTransitoDTO organismoTransitoDTO, OrganismoTransito organismoTransito)
            throws CirculemosNegocioException {
        List<DireccionOrganismo> direccionOrganismoList = organismoTransito.getDireccionOrganismo();
        if (direccionOrganismoList == null) {
            direccionOrganismoList = new ArrayList<>(1);
            organismoTransito.setDireccionOrganismo(direccionOrganismoList);
        }

        for (DireccionOrganismoDTO direccionOrganismoDTO : organismoTransitoDTO.getDireccionOrganismo()) {
            if (direccionOrganismoDTO.getId() != null) {
                DireccionOrganismo direccionOrganismo = em.find(DireccionOrganismo.class,
                        direccionOrganismoDTO.getId());
                direccionOrganismo.setFechaFin(Utilidades.getFechaActual());
                em.merge(direccionOrganismo);
            } else {
                DireccionOrganismo direccionOrganismo = DireccionOrganismoHelper.toLevel1Entity(direccionOrganismoDTO,
                        null);
                RespuestaIngresarDireccionDTO responseDireccion = direccionEJB.ingresarDireccion(
                        direccionOrganismoDTO.getDireccion(), EnumTipoValidacionDireccion.INFRACCION);

                if (responseDireccion.getIdDireccion() == null) {
                    throw new CirculemosNegocioException(AdministrarOrganismoTransito.ADM_056005);
                }

                direccionOrganismo.setDireccion(new Direccion());
                direccionOrganismo.getDireccion().setId(responseDireccion.getIdDireccion());
                direccionOrganismo.setOrganismoTransito(organismoTransito);

                direccionOrganismoList.add(direccionOrganismo);
            }
        }

    }

    /**
     * Valida los datos del organismo de transito a modificar o ingresar
     * 
     * @param organismoTransitoDTO
     *            datos del organismo entrante
     * @throws CirculemosNegocioException
     *             <br>
     *             ADM_056001=Correo ingresado no fue correctamente validado en el sistema. No se ejecuto la operacion<br>
     *             ADM_056002=Codigo asignado al organismo de transito existente en el sistema. No se ejecuto la operacion<br>
     *             ADM_056003=Nombre del organismo de transito existente en el sistema. No se ejecuto la operacion<br>
     *             ADM_056004=Direccion del organismo de transito es obligatoria y no ha sido ingresada. No se ejecuto la operacion<br>
     *             ADM_056006=Departamento no ha sido seleccionado. Operacion no ejecutada<br>
     */
    private void validarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO) throws CirculemosNegocioException {
        if (StringUtils.isNotBlank(organismoTransitoDTO.getCorreoElectronico())
                && !EmailValidator.validate(organismoTransitoDTO.getCorreoElectronico())) {
            throw new CirculemosNegocioException(AdministrarOrganismoTransito.ADM_056001);
        }
        if (organismoTransitoDTO.getDireccionOrganismo() == null
                || organismoTransitoDTO.getDireccionOrganismo().isEmpty()) {
            throw new CirculemosNegocioException(AdministrarOrganismoTransito.ADM_056004);
        }

        existenciaOrganismoTransito(organismoTransitoDTO);

        if (organismoTransitoDTO.getMunicipio() == null || organismoTransitoDTO.getMunicipio().getId() == null) {
            if (organismoTransitoDTO.getDepartamento() == null
                    || organismoTransitoDTO.getDepartamento().getId() == null) {
                throw new CirculemosNegocioException(AdministrarOrganismoTransito.ADM_056006);
            }
        }

    }

    /**
     * 
     * @return
     */
    private void existenciaOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO)
            throws CirculemosNegocioException {
        TypedQuery<Long> query = null;
        if (!organismoTransitoDTO.isActualizando()) {
            query = em.createNamedQuery(OrganismoTransito.SQ_COUNT_BY_CODIGO, Long.class);
            query.setParameter("pCodigoOrganismo", organismoTransitoDTO.getCodigoOrganismo());

            Long count = query.getSingleResult();
            if (count > 0) {
                throw new CirculemosNegocioException(AdministrarOrganismoTransito.ADM_056002);
            }

            query = em.createNamedQuery(OrganismoTransito.SQ_COUNT_BY_NOMBRE, Long.class);
            query.setParameter("pNomOrg", organismoTransitoDTO.getNombreOrganismo());

            count = query.getSingleResult();
            if (count > 0) {
                throw new CirculemosNegocioException(AdministrarOrganismoTransito.ADM_056003);
            }
        } else {
            query = em.createNamedQuery(OrganismoTransito.SQ_COUNT_BY_NOMBRE_ORG, Long.class);
            query.setParameter("pCodigoOrganismo", organismoTransitoDTO.getCodigoOrganismo());
            query.setParameter("pNomOrg", organismoTransitoDTO.getNombreOrganismo());

            Long count = query.getSingleResult();
            if (count > 0) {
                throw new CirculemosNegocioException(AdministrarOrganismoTransito.ADM_056003);
            }
        }
    }

    @Override
    public void eliminarOrganismoTransito(Integer codigoOrganismo) throws CirculemosNegocioException {
        try {
            OrganismoTransito organismoTransito = em.find(OrganismoTransito.class, codigoOrganismo);
            em.remove(organismoTransito);
            em.flush();
        } catch (Exception e) {
            throw new CirculemosNegocioException(AdministrarOrganismoTransito.ADM_056007, e.toString());
        }

    }

    /**
     * Permite obtener del sistema la direccion del organismo vigente a la fecha actual
     * 
     * @param codigoOrganismo
     *            codigo o identificador unico del organismo
     * @return direccion vigente del organismo de transito
     * @author luis.forero (2015-01-21)
     */
    private DireccionOrganismoDTO obtenerDireccionOrganismoVigente(Integer codigoOrganismo) {
        DireccionOrganismoDTO direccionOrganismoDTO = null;

        TypedQuery<DireccionOrganismo> query = em.createNamedQuery(DireccionOrganismo.SQ_FIND_VIGENTE_BY_ORGANISMO,
                DireccionOrganismo.class);
        query.setParameter("pCodOrg", codigoOrganismo);
        query.setParameter("pFecAct", Utilidades.getFechaActual());

        List<DireccionOrganismo> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            DireccionOrganismo direccionOrganismo = resultList.get(0);
            direccionOrganismoDTO = DireccionOrganismoHelper.toLevel1DTO(direccionOrganismo);
            direccionOrganismoDTO.setDireccion(DireccionHelperExtend.toLevel1DTO(direccionOrganismo.getDireccion()));
        }

        return direccionOrganismoDTO;
    }

    @Override
    public List<TipoIdentificacionPersonaDTO> consultarTipoIdentificacionPersona(PaisDTO pPais,
            TipoIdentificacionPersonaDTO pTipIdPerDTO) {
        // TODO FM Verificar el uso de pTipIdPerDTO
        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> filtros = new HashMap<>();
        sql.append("SELECT tip FROM TipoIdentificacionPersona AS tip");
        sql.append(" WHERE 1=1");
        if (pTipIdPerDTO != null) {
            sql.append(" AND tip.id = :pTipIdPerDTO");
            filtros.put("pTipIdPerDTO", pTipIdPerDTO.getId());
        }
        final GenericDao<TipoIdentificacionPersona> query = new GenericDao<TipoIdentificacionPersona>(
                TipoIdentificacionPersona.class, em);
        final List<TipoIdentificacionPersona> listTipIdPer = safeList(
                query.buildAndExecuteQuery(sql.toString(), filtros));
        return TipoIdentificacionPersonaHelper.toListLevel0DTO(listTipIdPer);
    }

    @Override
    public List<TipoSociedadDTO> consultarTipoSociedad(int pais) {
        final StringBuilder jpql = new StringBuilder(" SELECT p FROM TipoSociedad AS p ");
        jpql.append(" WHERE p.pais.id= :pais ");
        final TypedQuery<TipoSociedad> query = this.em.createQuery(jpql.toString(), TipoSociedad.class);
        query.setParameter("pais", pais);
        final List<TipoSociedad> lTipoSociedad = query.getResultList();
        return TipoSociedadHelper.toListLevel0DTO(lTipoSociedad);
    }

    @Override
    public List<EstadoCivilDTO> consultarEstadoCivil(int pais) {
        final StringBuilder jpql = new StringBuilder(" SELECT p FROM EstadoCivil AS p ");
        jpql.append(" WHERE p.pais.id= :pais ");
        final TypedQuery<EstadoCivil> query = this.em.createQuery(jpql.toString(), EstadoCivil.class);
        query.setParameter("pais", pais);
        final List<EstadoCivil> lEstadoCivil = query.getResultList();

        return EstadoCivilHelper.toListLevel0DTO(lEstadoCivil);
    }

    @Override
    public List<TipoViviendaDTO> consultarTipoVivienda(int pais) {
        logger.info("AdministracionEJB::consultarTipoVivienda");
        final StringBuilder sb = new StringBuilder(" SELECT t FROM TipoVivienda AS t ");
        sb.append(" WHERE t.pais.id= :pais ");
        final TypedQuery<TipoVivienda> q = this.em.createQuery(sb.toString(), TipoVivienda.class);
        q.setParameter("pais", pais);
        final List<TipoVivienda> lTipoVivienda = q.getResultList();

        return TipoViviendaHelper.toListLevel0DTO(lTipoVivienda);
    }

    @Override
    public List<NivelEducativoDTO> consultarNivelEducativo(int pais) {
        final StringBuilder sb = new StringBuilder(" SELECT n FROM NivelEducativo AS n ");
        sb.append(" WHERE n.pais.id= :pais ");
        final TypedQuery<NivelEducativo> q = this.em.createQuery(sb.toString(), NivelEducativo.class);
        q.setParameter("pais", pais);
        final List<NivelEducativo> lNivelEducativo = q.getResultList();

        return NivelEducativoHelper.toListLevel0DTO(lNivelEducativo);
    }

    @Override
    public List<DepartamentoDTO> consultarDepartamentos(int pais) {
        final StringBuilder sb = new StringBuilder(" SELECT d FROM Departamento AS d ");
        sb.append(" WHERE d.pais.id= :pais ");
        sb.append(" ORDER BY d.nombre ");
        final TypedQuery<Departamento> q = this.em.createQuery(sb.toString(), Departamento.class);
        q.setParameter("pais", pais);
        final List<Departamento> lDepartamento = safeList(q.getResultList());
        return DepartamentoHelper.toListLevel0DTO(lDepartamento);
    }

    @Override
    public int obtenerTipoFuenteInformacionModuloAdministracion(int pais, int proceso) {
        ConsTipoFuenInfoPaisDTO tipoFuenteInfo = new ConsTipoFuenInfoPaisDTO();
        tipoFuenteInfo.setCodigoPais(this.em.find(Pais.class, Integer.valueOf(pais)).getCodigo());
        tipoFuenteInfo.setCodigoProceso(proceso);

        // tipoFuenteInfo = (ConsTipoFuenInfoPaisDTO) reglaNegocioEjb.consumirRegla(tipoFuenteInfo);

        return tipoFuenteInfo.getCodigoFuenteInformacion();
    }

    @Override
    public TipoIdentificacionPersonaDTO obtenerTipoIdentificacionEmpresaJuridicaPais(int idPais) {
        // CodTipoIdentEmpJurPaisDTO tipoIdJuridico = new CodTipoIdentEmpJurPaisDTO();
        // tipoIdJuridico.setPais(this.em.find(Pais.class, Integer.valueOf(idPais)).getCodigo());

        // tipoIdJuridico = (CodTipoIdentEmpJurPaisDTO) reglaNegocioEjb.consumirRegla(tipoIdJuridico);
        // TODO: Llamado de configuracion del tipo de identificacion jutridico
        TipoIdentificacionPersona tipoIdentEmpJuri = em.find(TipoIdentificacionPersona.class,
                EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor());
        return TipoIdentificacionPersonaHelper.toLevel0DTO(tipoIdentEmpJuri);
    }

    @Override
    public List<Integer> obtenerEstadosCivilesImpliquenConyugue(int pais) {
        ConsEstadCivilConyPais estadosCivilesPais = new ConsEstadCivilConyPais();
        estadosCivilesPais.setPais(this.em.find(Pais.class, Integer.valueOf(pais)).getCodigo());

        // estadosCivilesPais = (ConsEstadCivilConyPais) reglaNegocioEjb.consumirRegla(estadosCivilesPais);

        return estadosCivilesPais.getEstadosCiviles();
    }

    @Override
    public List<SeccionActividadEconomicaDTO> consultarSeccionActividadEconomica(int pais) {
        final StringBuilder sb = new StringBuilder(" SELECT s FROM SeccionActividadEconomica AS s ");
        sb.append(" WHERE s.pais.id= :pais ");
        TypedQuery<SeccionActividadEconomica> q = this.em.createQuery(sb.toString(), SeccionActividadEconomica.class);
        q.setParameter("pais", pais);
        List<SeccionActividadEconomica> lSeccionActividadEconomica = q.getResultList();

        return SeccionActividadEconomicaHelper.toListLevel0DTO(lSeccionActividadEconomica);
    }

    @Override
    public List<DivisionActividadEconomicaDTO> consultarDivisionActividadEconomica(int seccion) {
        final StringBuilder sb = new StringBuilder(" SELECT d FROM DivisionActividadEconomica AS d ");
        sb.append(" WHERE d.seccionActividadEconomica.id= :seccion ");
        final TypedQuery<DivisionActividadEconomica> q = this.em.createQuery(sb.toString(),
                DivisionActividadEconomica.class);
        q.setParameter("seccion", seccion);
        final List<DivisionActividadEconomica> lDivisionActividadEconomica = q.getResultList();

        return DivisionActividadEconomicaHelper.toListLevel0DTO(lDivisionActividadEconomica);
    }

    @Override
    public List<GrupoActividadEconomicaDTO> consultarGrupoActividadEconomica(int division) {
        final StringBuilder sb = new StringBuilder(" SELECT g FROM GrupoActividadEconomica AS g ");
        sb.append(" WHERE g.divisionActividadEconomica.id= :division ");
        final TypedQuery<GrupoActividadEconomica> q = this.em.createQuery(sb.toString(), GrupoActividadEconomica.class);
        q.setParameter("division", division);
        final List<GrupoActividadEconomica> lGrupoActividadEconomica = q.getResultList();

        return GrupoActividadEconomicaHelper.toListLevel0DTO(lGrupoActividadEconomica);
    }

    @Override
    public List<ClaseActividadEconomicaDTO> consultarClaseActividadEconomica(int grupo) {
        final StringBuilder sb = new StringBuilder(" SELECT c FROM ClaseActividadEconomica AS c ");
        sb.append(" WHERE c.grupoActividadEconomica.id= :grupo ");
        final TypedQuery<ClaseActividadEconomica> q = this.em.createQuery(sb.toString(), ClaseActividadEconomica.class);
        q.setParameter("grupo", grupo);
        final List<ClaseActividadEconomica> lClaseActividadEconomica = safeList(q.getResultList());
        return ClaseActividadEconomicaHelper.toListLevel0DTO(lClaseActividadEconomica);
    }

    @Override
    public boolean existeActividad(ActividadDTO actividadDTO, Integer codigoOrganismo) {
        final Integer idActividad = actividadDTO.getId();
        final Integer codActividad = actividadDTO.getCodigo();
        Integer codProceso = null;

        if (actividadDTO.getProceso() != null) {
            codProceso = actividadDTO.getProceso().getId();
        }

        if (codigoOrganismo == null
                || (codActividad == null && codProceso != null || codActividad != null && codProceso == null)) {
            throw new CirculemosIllegalArgumentException(
                    "AdministracionEJB::existeActividad(ActividadDTO, Integer) - Datos insuficientes");
        }

        final StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT COUNT(a)");
        jpql.append("  FROM Actividad a");
        jpql.append("  JOIN a.actividadOrganismo ao");
        jpql.append(" WHERE ao.codigoOrganismo = :codigoOrganismo");

        if (idActividad != null) {
            jpql.append(" AND a.id = :idActividad");
        }
        if (codActividad != null) {
            jpql.append(" AND a.codigo = :codActividad");
        }
        if (codProceso != null) {
            jpql.append(" AND a.proceso.codigo = :codProceso");
        }

        final Query query = em.createQuery(jpql.toString());
        query.setParameter("codigoOrganismo", codigoOrganismo);

        if (idActividad != null) {
            query.setParameter("idActividad", idActividad);
        }
        if (codActividad != null) {
            query.setParameter("codActividad", codActividad);
        }
        if (codProceso != null) {
            query.setParameter("codProceso", codProceso);
        }

        final Long cantidad = (Long) query.getSingleResult();

        return cantidad > 0;
    }

    @Override
    public List<TipoViaDTO> consultarTipoVia(Integer idPais) {
        if (idPais == null)
            return new ArrayList<TipoViaDTO>();

        final StringBuilder jpql2 = new StringBuilder();
        jpql2.append("SELECT tv");
        jpql2.append("  FROM TipoVia tv");
        jpql2.append(" WHERE tv.pais.id = :idPais");
        jpql2.append(" ORDER BY tv.nombre");

        final TypedQuery<TipoVia> query2 = em.createQuery(jpql2.toString(), TipoVia.class);
        query2.setParameter("idPais", idPais);

        final List<TipoVia> lTipoVia = safeList(query2.getResultList());
        return TipoViaHelper.toListLevel0DTO(lTipoVia);
    }

    @Override
    public List<NombreViaDTO> consultarNombreVia(Integer idMunicipio, Integer codTipoVia) {
        if (idMunicipio == null && codTipoVia == null)
            return new ArrayList<NombreViaDTO>();

        StringBuilder sql = new StringBuilder();
        GenericDao<NombreVia> nvDao = new GenericDao<>(NombreVia.class, em);
        Map<String, Object> params = new HashMap<>(5);

        sql.append("SELECT nv FROM NombreVia nv WHERE 1 = 1");
        if (idMunicipio != null) {
            sql.append(" AND nv.municipio.id = :idMunicipio");
            params.put("idMunicipio", idMunicipio);
        }
        if (codTipoVia != null) {
            sql.append(" AND nv.tipoVia.codigo = :codTipoVia");
            params.put("codTipoVia", codTipoVia);
        }
        sql.append(" ORDER BY nv.nombre");

        List<NombreVia> resultadoConsulta = nvDao.buildAndExecuteQuery(sql.toString(), params);
        List<NombreViaDTO> resultado = NombreViaHelper.toListLevel1DTO(resultadoConsulta);

        return resultado;
    }

    @Override
    public List<CardinalidadDireccionDTO> consultarCardinalidadDireccion(Integer idPais) {
        if (idPais == null)
            return new ArrayList<CardinalidadDireccionDTO>();

        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT cd");
        jpql.append("  FROM CardinalidadDireccion cd");
        jpql.append("  JOIN cd.pais p");
        jpql.append(" WHERE p.id = :idPais");
        jpql.append(" ORDER BY cd.nombre");

        final TypedQuery<CardinalidadDireccion> query = em.createQuery(jpql.toString(), CardinalidadDireccion.class);
        query.setParameter("idPais", idPais);

        final List<CardinalidadDireccion> lCardinalidadDir = safeList(query.getResultList());
        return CardinalidadDireccionHelper.toListLevel0DTO(lCardinalidadDir);
    }

    @Override
    public List<PaisDTO> consultarPais(PaisDTO paisDTO) {
        final StringBuilder jpql = new StringBuilder("SELECT p FROM Pais p");
        jpql.append(" WHERE 1=1");
        Map<String, Object> filtros = new HashMap<>(0);
        if (paisDTO != null) {
            if (StringUtils.isNotBlank(paisDTO.getCodigo())) {
                jpql.append(" AND p.codigo = :pCod");
                filtros.put("pCod", paisDTO.getCodigo());
            }
            if (paisDTO.getId() != null) {
                jpql.append(" AND p.id = :pId");
                filtros.put("pId", paisDTO.getId());
            }
        }
        jpql.append(" ORDER BY p.nombre");

        final GenericDao<Pais> dao = new GenericDao<>(Pais.class, em);
        return PaisHelper.toListLevel0DTO(dao.buildAndExecuteQuery(jpql, filtros));
    }

    @Override
    public List<DepartamentoDTO> consultarDepartamento(DepartamentoDTO departamentoDTO) {
        final StringBuilder jpql = new StringBuilder("SELECT d FROM Departamento d JOIN d.pais p");
        jpql.append(" WHERE 1=1");
        final Map<String, Object> filtros = new HashMap<>(1);
        if (departamentoDTO != null) {
            if (departamentoDTO.getPais() != null && departamentoDTO.getPais().getId() != null) {
                jpql.append(" AND p.id = :idPais");
                filtros.put("idPais", departamentoDTO.getPais().getId());
            }
            if (StringUtils.isNotBlank(departamentoDTO.getCodigo())) {
                jpql.append(" AND d.codigo = :pCod");
                filtros.put("pCod", departamentoDTO.getCodigo());
            }
        }
        jpql.append(" ORDER BY d.nombre");

        final GenericDao<Departamento> dao = new GenericDao<>(Departamento.class, em);
        return DepartamentoHelper.toListLevel0DTO(dao.buildAndExecuteQuery(jpql, filtros));
    }

    @Override
    public List<MunicipioDTO> consultarMunicipio(MunicipioDTO municipioDTO) {
        final StringBuilder jpql = new StringBuilder(" SELECT p FROM Municipio AS p");
        jpql.append(" WHERE 1=1");
        final Map<String, Object> filtros = new HashMap<>();
        if (municipioDTO != null) {
            if (municipioDTO.getId() != null) {
                jpql.append(" AND p.id = :pIdMun");
                filtros.put("pIdMun", municipioDTO.getId());
            }
            if (municipioDTO.getDepartamento() != null && municipioDTO.getDepartamento().getId() != null) {
                jpql.append(" AND p.departamento.id= :pIdDep");
                filtros.put("pIdDep", municipioDTO.getDepartamento().getId());
            }
            if (StringUtils.isNotBlank(municipioDTO.getCodigo())) {
                jpql.append(" AND p.codigo = :pCodMun");
                filtros.put("pCodMun", municipioDTO.getCodigo());
            }
        }

        jpql.append(" ORDER BY p.nombre");

        final GenericDao<Municipio> dao = new GenericDao<>(Municipio.class, em);
        return MunicipioHelper.toListLevel1DTO(dao.buildAndExecuteQuery(jpql, filtros));
    }

    @Override
    public List<LocalidadDTO> consultarLocalidad(LocalidadDTO localidadDTO) {
        final StringBuilder jpql = new StringBuilder("SELECT l FROM Localidad l JOIN l.municipio m");
        jpql.append(" WHERE 1=1");
        final Map<String, Object> filtros = new HashMap<>();
        if (localidadDTO != null) {
            if (localidadDTO.getId() != null) {
                jpql.append(" AND l.id = :pId");
                filtros.put("pId", localidadDTO.getId());
            }
            if (localidadDTO.getMunicipio() != null && localidadDTO.getMunicipio().getId() != null) {
                jpql.append(" AND m.id = :idMunicipio");
                filtros.put("idMunicipio", localidadDTO.getMunicipio().getId());
            }
            if (StringUtils.isNotBlank(localidadDTO.getCodigo())) {
                jpql.append(" AND l.codigo = :pCod");
                filtros.put("pCod", localidadDTO.getCodigo());
            }
        }
        jpql.append(" ORDER BY l.nombre");

        final GenericDao<Localidad> dao = new GenericDao<>(Localidad.class, em);
        return LocalidadHelper.toListLevel1DTO(dao.buildAndExecuteQuery(jpql, filtros));
    }

    @Override
    public List<ProcesoNegocioDTO> consultarProceso(ProcesoNegocioDTO proceso) {
        final Map<String, Object> filtro = new HashMap<String, Object>();

        final StringBuilder sQuery = new StringBuilder(" SELECT p FROM ProcesoNegocio p ");
        sQuery.append(" WHERE 1 = 1 ");

        if (proceso != null) {
            if (proceso.getId() != null) {
                sQuery.append(" AND p.id = :codigoProceso ");
                filtro.put("codigoProceso", proceso.getId());
            }

            if (proceso.getProcesoPadre() != null) {
                sQuery.append(" AND p.procesoPadre = :codigoProcesoPadre ");
                filtro.put("codigoProcesoPadre", proceso.getProcesoPadre().getId());
            }

        }

        sQuery.append(" ORDER BY p.procesoPadre, p.id ");
        final TypedQuery<ProcesoNegocio> query = this.em.createQuery(sQuery.toString(), ProcesoNegocio.class);
        for (Entry<String, Object> entrada : filtro.entrySet()) {
            query.setParameter(entrada.getKey(), entrada.getValue());
        }

        final List<ProcesoNegocio> lista = query.getResultList();
        return ProcesoNegocioHelper.toListLevel1DTO(lista);
    }

    @Override
    public List<OrganismoTransitoDTO> consultarOrganismosAsociados(int codigoOrganismo) {

        List<OrganismoTransitoDTO> lstOrganismosTransitoDTO = null;
        final StringBuilder jpql1 = new StringBuilder();
        jpql1.append("SELECT ot FROM OrganismoTransito AS ot");
        jpql1.append(" LEFT JOIN FETCH ot.departamento AS dp");
        jpql1.append(" LEFT JOIN FETCH ot.municipio AS mn");
        jpql1.append(" WHERE ot.organismoTransitoPadre.codigoOrganismo = :pCodOrg");

        Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("pCodOrg", codigoOrganismo);

        GenericDao<OrganismoTransito> organismoTransitoDao = new GenericDao<>(OrganismoTransito.class, em);
        List<OrganismoTransito> resultadoConsulta = organismoTransitoDao.buildAndExecuteQuery(jpql1.toString(),
                filtros);
        lstOrganismosTransitoDTO = OrganismoTransitoHelper.toListLevel1DTO(resultadoConsulta);

        if (lstOrganismosTransitoDTO.isEmpty()) {
            final StringBuilder jpql2 = new StringBuilder();
            jpql2.append("SELECT ot FROM OrganismoTransito AS ot");
            jpql2.append(" LEFT JOIN FETCH ot.departamento AS dp");
            jpql2.append(" LEFT JOIN FETCH ot.municipio AS mn");
            jpql2.append(" WHERE ot.codigoOrganismo = :pCodOrg");

            organismoTransitoDao = new GenericDao<>(OrganismoTransito.class, em);
            resultadoConsulta = organismoTransitoDao.buildAndExecuteQuery(jpql2.toString(), filtros);
            lstOrganismosTransitoDTO = OrganismoTransitoHelper.toListLevel1DTO(resultadoConsulta);
        }

        return lstOrganismosTransitoDTO;
    }

    @Override
    public List<ItemCatalogoDTO> consultarFormatosDocumento() {
        logger.debug("AdministracionEJB::consultarFormatosDocumento()");
        List<ItemCatalogoDTO> itemCatalogoDTOs = new ArrayList<ItemCatalogoDTO>();
        for (int i = 0; i < EnumFormatoDocumentoIdentidad.values().length; i++) {
            ItemCatalogoDTO itemCatalogoDTO = new ItemCatalogoDTO();
            itemCatalogoDTO.setId(EnumFormatoDocumentoIdentidad.values()[i].getValue());
            itemCatalogoDTO.setNombre(EnumFormatoDocumentoIdentidad.values()[i].name());
            itemCatalogoDTOs.add(itemCatalogoDTO);
        }
        return itemCatalogoDTOs;
    }

    @Override
    public List<ItemCatalogoDTO> consultarOrganismosTransito() {
        logger.debug("AdministracionEJB::consultarOrganismosTransito()");
        List<ItemCatalogoDTO> itemsCatalogo = new ArrayList<>();
        // Consultar todos los organismo
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT o FROM OrganismoTransito o");
        sql.append(" WHERE o.codigoOrganismo IS NOT NULL");

        GenericDao<OrganismoTransito> genericDao = new GenericDao<>(OrganismoTransito.class, em);
        List<OrganismoTransito> organismos = genericDao.buildAndExecuteQuery(sql, null);

        for (OrganismoTransito ot : organismos) {
            // Son todos aquellos organismo que no tienen <<hijos>> asociados
            if (!verificarOrganismoPadre(ot.getCodigoOrganismo())) {
                itemsCatalogo.add(OrganismoTransitoHelper.toItemCatalogoDTO(ot));
            }
        }
        return itemsCatalogo;
    }

    /**
     * Se encarga de verificar si el organismo de tránsito del argumento tiene hijos <<sucursales>> asociadas
     * 
     * @param codigoOrganismo
     *            - codigo del organismo que se esta verificando
     * @return true si el organismo de tránsito tienen sucursales asociadas
     */
    private boolean verificarOrganismoPadre(int codigoOrganismo) {
        StringBuilder sql = new StringBuilder("SELECT COUNT (org)");
        sql.append(" FROM OrganismoTransito org JOIN org.organismoTransitoPadre padre");
        sql.append(" WHERE padre.codigoOrganismo = :codigoOrganismo");

        TypedQuery<Long> query = em.createQuery(sql.toString(), Long.class);

        query.setParameter("codigoOrganismo", codigoOrganismo);
        Long cantidadHijos = query.getSingleResult();
        if (cantidadHijos != null && cantidadHijos.compareTo(new Long(0)) > 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validarNumeroDocumento(String numeroDocumento, Integer tipoDocumento, Date fechaVigencia)
            throws CirculemosNegocioException {
        // Configuracion 009
        AsociaDocumentoIdentidadFormatoDTO asociaDocumentoIdentidadFormatoDTO = new AsociaDocumentoIdentidadFormatoDTO();
        // Ingreso tipo de comparendo list correspondiente a medios de imposicion
        List<String> tipoIdentificacion = new ArrayList<String>();
        tipoIdentificacion.add(String.valueOf(tipoDocumento));
        asociaDocumentoIdentidadFormatoDTO.setTipoIdentificacion(tipoIdentificacion);
        asociaDocumentoIdentidadFormatoDTO.setInicioPeriodoVigencia(fechaVigencia);

        // Me devuelve el tipo de formato
        asociaDocumentoIdentidadFormatoDTO = fachadaConfiguracion.consultarValorConfiguracion(
                EnumConfiguracion.ASOCIA_DOCUMENTO_IDENTIDAD_CON_UN_FORMATO.getCodigo(),
                asociaDocumentoIdentidadFormatoDTO);
        boolean valido = false;
        // valida longitud
        if (numeroDocumento.length() >= asociaDocumentoIdentidadFormatoDTO.getLongitudMinima().longValue()
                && numeroDocumento.length() <= asociaDocumentoIdentidadFormatoDTO.getLongitudMaxima().longValue()) {

            if (EnumFormatoDocumentoIdentidad.NUMERICO.getValue() == Integer
                    .valueOf(asociaDocumentoIdentidadFormatoDTO.getTipoFormato().get(0))
                    && StringUtils.isNumeric(numeroDocumento)) {
                valido = true;
            } else if (EnumFormatoDocumentoIdentidad.ALFA_NUMERICO.getValue() == Integer
                    .valueOf(asociaDocumentoIdentidadFormatoDTO.getTipoFormato().get(0))
                    && StringUtils.isAlphanumeric(numeroDocumento)) {
                valido = true;
            }
        }

        return valido;
    }

    @Override
    public ConfiguracionEntidadDTO consultarConfiguracionEntidad(Long idConfiguracionEntidad) {
        logger.debug("AdministracionEJB.consultarConfiguracionEntidad(Long)");
        ConfiguracionEntidadDTO configuracionEntidadDTO = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT ce FROM ConfiguracionEntidad ce");
        jpql.append(" WHERE ce.id = :idConfiguracionEntidad");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("idConfiguracionEntidad", idConfiguracionEntidad);

        @SuppressWarnings("unchecked")
        List<ConfiguracionEntidad> configuracionEntidads = query.getResultList();
        if (configuracionEntidads != null && !configuracionEntidads.isEmpty()) {
            configuracionEntidadDTO = ConfiguracionEntidadHelper.toLevel1DTO(configuracionEntidads.get(0));
        }
        return configuracionEntidadDTO;
    }

    @Override
    public List<ConfiguracionEntidadDTO> consultarConfiguracionEntidad(
            ConfiguracionEntidadDTO configuracionEntidadDTO) {
        logger.debug("AdministracionEJB.consultarConfiguracionEntidad(ConfiguracionEntidadDTO)");
        List<ConfiguracionEntidadDTO> configuracionEntidadDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT ce FROM ConfiguracionEntidad ce");
        jpql.append(" WHERE 1=1");

        // Tipo entidad
        if (configuracionEntidadDTO.getTipoEntidadDTO() != null
                && configuracionEntidadDTO.getTipoEntidadDTO().getId() != null) {
            jpql.append(" AND ce.tipoEntidad.id = :idTipoEntidad");
        }

        // Entidad oficio
        if (configuracionEntidadDTO.getEntidadOficioDTO() != null
                && configuracionEntidadDTO.getEntidadOficioDTO().getId() != null) {
            jpql.append(" AND ce.entidadOficio.id = :idEntidadOficio");
        }

        Query query = em.createQuery(jpql.toString());

        // Tipo entidad
        if (configuracionEntidadDTO.getTipoEntidadDTO() != null
                && configuracionEntidadDTO.getTipoEntidadDTO().getId() != null) {
            query.setParameter("idTipoEntidad", configuracionEntidadDTO.getTipoEntidadDTO().getId());
        }

        // Entidad oficio
        if (configuracionEntidadDTO.getEntidadOficioDTO() != null
                && configuracionEntidadDTO.getEntidadOficioDTO().getId() != null) {
            query.setParameter("idEntidadOficio", configuracionEntidadDTO.getEntidadOficioDTO().getId());
        }

        @SuppressWarnings("unchecked")
        List<ConfiguracionEntidad> configuracionEntidads = query.getResultList();
        if (configuracionEntidads != null && !configuracionEntidads.isEmpty()) {
            configuracionEntidadDTOs = ConfiguracionEntidadHelper.toListLevel1DTO(configuracionEntidads);
        }
        return configuracionEntidadDTOs;
    }

    @Override
    public ConfiguracionEntidadDTO consultarConfiguracionEntidad(EntidadOficioDTO entidadOficioDTO) {
        ConfiguracionEntidadDTO configuracionEntidadDTO = null;
        ConfiguracionEntidadDTO configuracionEntidadConDTO = new ConfiguracionEntidadDTO();
        configuracionEntidadConDTO.setEntidadOficioDTO(entidadOficioDTO);
        List<ConfiguracionEntidadDTO> configuracionEntidadDTOs = consultarConfiguracionEntidad(
                configuracionEntidadConDTO);
        if (configuracionEntidadDTOs != null && !configuracionEntidadDTOs.isEmpty()) {
            configuracionEntidadDTO = configuracionEntidadDTOs.get(0);
        }
        return configuracionEntidadDTO;
    }

    @Override
    public void registrarConfiguracionEntidad(ConfiguracionEntidadDTO configuracionEntidadDTO)
            throws CirculemosNegocioException {
        logger.debug("AdministracionEJB.registrarConfiguracionEntidad(ConfiguracionEntidadDTO)");

        ConfiguracionEntidadDTO configuracionEntidadBDDTO = consultarConfiguracionEntidad(
                configuracionEntidadDTO.getEntidadOficioDTO());
        if (configuracionEntidadBDDTO != null) {
            throw new CirculemosNegocioException(ErrorAdministracion.ConfiguracionEntidad.ADM_076001);
        }

        // fecha inicio
        configuracionEntidadDTO.setFechaInicio(Calendar.getInstance().getTime());
        em.persist(ConfiguracionEntidadHelper.toLevel1Entity(configuracionEntidadDTO, null));

        // Camio de estado entidad oficio
        ItemCatalogoDTO itemCatalogoFindDTO = new ItemCatalogoDTO();
        itemCatalogoFindDTO.setId(configuracionEntidadDTO.getEntidadOficioDTO().getId());
        List<ItemCatalogoDTO> itemCatalogoDTOs = iRCatalogo
                .consultarItemsCatalogo(EnumCatalogo.EntidadOficio.toString(), itemCatalogoFindDTO);
        ItemCatalogoDTO itemCatalogoDTO = itemCatalogoDTOs.get(0);
        itemCatalogoDTO.setActivo(true);
        iRCatalogo.actualizarItemCatalogo(EnumCatalogo.EntidadOficio.toString(), "co.com.datatools.c2.entidades.comun",
                itemCatalogoDTOs.get(0));
    }

    @Override
    public void actualizarConfiguracionEntidad(ConfiguracionEntidadDTO configuracionEntidadDTO) {
        logger.debug("AdministracionEJB.registrarConfiguracionEntidad(ConfiguracionEntidadDTO)");

        // fecha fin
        configuracionEntidadDTO.setFechaFin(Calendar.getInstance().getTime());
        em.merge(ConfiguracionEntidadHelper.toLevel1Entity(configuracionEntidadDTO, null));
    }

    @Override
    public MunicipioDTO consultarMunicipio(int idMunicipio) {
        logger.debug("AdministracionEJB.consultarMUnicipio(int)");
        MunicipioDTO municipioDTO = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT m FROM Municipio m");
        jpql.append(" WHERE m.id = :idMunicipio");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("idMunicipio", idMunicipio);

        @SuppressWarnings("unchecked")
        List<Municipio> municipios = query.getResultList();
        if (municipios != null && !municipios.isEmpty()) {
            municipioDTO = MunicipioHelper.toLevel1DTO(municipios.get(0));
        }

        return municipioDTO;
    }
}
