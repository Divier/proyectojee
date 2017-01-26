package co.com.datatools.c2.negocio.ejb.formularios;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.dto.formularios.RelacionEstadosDTO;
import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.StockTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.entidades.DetalleCambioEstado;
import co.com.datatools.c2.entidades.DetalleNumeracion;
import co.com.datatools.c2.entidades.NumeracionFormulario;
import co.com.datatools.c2.entidades.RangoFormulario;
import co.com.datatools.c2.entidades.RelacionEstados;
import co.com.datatools.c2.entidades.ResponsableFormulario;
import co.com.datatools.c2.entidades.StockTipoFormulario;
import co.com.datatools.c2.entidades.UnificacionResponsable;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios.AdministrarNumeracion;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios.AdministrarRelacionEstados;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios.AdministrarResponsableFormulario;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios.AdministrarStockTipoFormulario;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.formularios.DetalleNumeracionHelper;
import co.com.datatools.c2.negocio.helpers.formularios.NumeracionFormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.RelacionEstadosHelper;
import co.com.datatools.c2.negocio.helpers.formularios.ResponsableFormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.StockTipoFormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.UnificacionResponsableHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaJuridicaHelper;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.formularios.ILAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.mail.EmailValidator;

/**
 * Session Bean implementation class AdministracionFormulariosEJB
 */
@Stateless(name = "AdministracionFormulariosEJB")
@LocalBean
public class AdministracionFormulariosEJB implements ILAdministracionFormularios, IRAdministracionFormularios {

    private final static Logger logger = Logger.getLogger(AdministracionFormulariosEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminGeneral fachadaAdministracionEJB;
    @EJB
    private IRFachadaAdminNegocio fachadaAdminNegocio;
    @EJB
    private IRSeguridadCirculemos usAppEJB;

    public static final int TRAMA_NUMERACION_NUMERO = 0;
    public static final String TRAMA_NUMERACION_LETRA_MINUSCULA = "a";
    public static final String TRAMA_NUMERACION_LETRA_MAYUSCULA = "A";

    @Override
    public List<NumeracionFormularioDTO> consultarNumeracionFormulario(Integer idTipoFormulario,
            Boolean estadoNumeracion) {
        List<NumeracionFormularioDTO> resultado = new ArrayList<NumeracionFormularioDTO>();
        StringBuilder sql = new StringBuilder();
        GenericDao<NumeracionFormulario> NumeracionFormularioDao = new GenericDao<>(NumeracionFormulario.class, em);
        Map<String, Object> params = new HashMap<>(5);
        sql.append("SELECT n FROM NumeracionFormulario n WHERE 1=1");
        if (idTipoFormulario != null) {
            sql.append(" AND n.tipoFormulario.id =:idTipoFormulario");
            params.put("idTipoFormulario", idTipoFormulario);
        }
        if (estadoNumeracion != null) {
            sql.append(" AND n.activo =:estadoNumeracion");
            params.put("estadoNumeracion", estadoNumeracion);
        }

        List<NumeracionFormulario> resultadoConsulta = NumeracionFormularioDao.buildAndExecuteQuery(sql.toString(),
                params);
        resultado = NumeracionFormularioHelper.toListLevel1DTO(resultadoConsulta);
        logger.debugv(
                "AdministracionFormulariosEJB::consultarNumeracionFormulario: Se consultaron {0} numeraciones de formulario con parametros {1}",
                resultado.size(), params);
        return resultado;
    }

    @Override
    public void registrarNumeracionFormulario(NumeracionFormularioDTO numeracionFormularioDTO)
            throws CirculemosNegocioException {
        validarDatosNumeracion(numeracionFormularioDTO);
        // Verificar que cada casilla sea valida
        for (DetalleNumeracionDTO detalleNumDto : numeracionFormularioDTO.getDetalleNumeracionList()) {
            validarDetalleNumeracion(detalleNumDto);
        }
        String trama = obtenerTramaNumeracion(numeracionFormularioDTO.getDetalleNumeracionList());
        numeracionFormularioDTO.setTrama(trama);
        // Verificar las casillas numericas
        identificarCasillasNumericas(numeracionFormularioDTO.getDetalleNumeracionList());
        NumeracionFormulario entidadNumeracion = NumeracionFormularioHelper.toLevel1Entity(numeracionFormularioDTO,
                new NumeracionFormulario());
        ArrayList<DetalleNumeracion> listaDetalle = new ArrayList<DetalleNumeracion>();
        List<DetalleNumeracionDTO> detalleNumeracion = numeracionFormularioDTO.getDetalleNumeracionList();
        for (DetalleNumeracionDTO detalleNumeracionDTO : detalleNumeracion) {
            DetalleNumeracion entidadDetalle = DetalleNumeracionHelper.toLevel0Entity(detalleNumeracionDTO,
                    new DetalleNumeracion());
            entidadDetalle.setNumeracion(entidadNumeracion);
            listaDetalle.add(entidadDetalle);
        }
        entidadNumeracion.setDetalleNumeracionList(listaDetalle);
        em.persist(entidadNumeracion);
        logger.debugv(
                "AdministracionFormulariosEJB::registrarNumeracionFormulario: Se registra la numeracion con id= {0}",
                entidadNumeracion.getId());
    }

    /**
     * Valia que la obligatoriedad y las reglas de los campos de la numeracion sean correctos
     * 
     * @param numeracionFormularioDTO
     *            DTO con los datos de la numeración a validar
     * @throws CirculemosNegocioException
     * <br>
     *             NUM_016008=La fecha final de vigencia debe ser mayor a la fecha inicial de vigencia <br>
     *             NUM_016009=La cantidad de digitos debe ser mayor a cero y menor o igual a 50 <br>
     *             NUM_016010=El tipo de formulario de la numeracion es obligatorio<br>
     *             NUM_016011=La fecha inicial de vigencia de la numeracion es obligatoria<br>
     *             NUM_016012=La fecha final de vigencia de la numeracion es obligatoria<br>
     *             NUM_016013=El detalle de la numeracion es obligatorio<br>
     *             NUM_016014=El usuario que registra la numeración es obligatorio<br>
     *             NUM_016015=Ya existe una numeracion para el mismo tipo de formulario cruzada con las fechas de vigencia ingresadas
     */
    private void validarDatosNumeracion(NumeracionFormularioDTO numeracionFormularioDTO)
            throws CirculemosNegocioException {
        if (numeracionFormularioDTO.getTipoFormulario() == null)
            throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016010);
        if (numeracionFormularioDTO.getFechaInicial() == null)
            throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016011);
        if (numeracionFormularioDTO.getFechaFinal() == null)
            throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016012);
        if (numeracionFormularioDTO.getDetalleNumeracionList() == null)
            throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016013);
        if (!(numeracionFormularioDTO.getFechaFinal().compareTo(numeracionFormularioDTO.getFechaInicial()) > 0))
            throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016008);
        if (!(numeracionFormularioDTO.getDigitos() >= 1))
            throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016009);

        // Valida si existe para la numeracion de un determinado tipo de formulario otro que se cruce con las fechas de vigencia ingresadas.
        TypedQuery<NumeracionFormulario> query = em.createNamedQuery(NumeracionFormulario.SQ_COUNT_TIPO_VIGENTE,
                NumeracionFormulario.class);
        query.setParameter("idTipoFormulario", numeracionFormularioDTO.getTipoFormulario().getId());
        query.setParameter("fechaIni", numeracionFormularioDTO.getFechaInicial());
        query.setParameter("fechaFin", numeracionFormularioDTO.getFechaFinal());
        // escenario cuando es nueva la numeración
        if (numeracionFormularioDTO.getId() != null)
            query.setParameter("idNumeracion", numeracionFormularioDTO.getId());
        else
            query.setParameter("idNumeracion", 0);

        List<NumeracionFormulario> count = Utilidades.safeList(query.getResultList());

        if (count.size() > 0)
            throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016015);
    }

    /**
     * Obtiene la trama de una numeracion a partir de su detalle. La trama es una cadena cuya longitud corresponde a la cantidad de digitos de la
     * numeracion y cada posicion de la misma indica si el caracter de dicah posicion es numerico o alfabetico:<br>
     * Si es 0: indica que el caracter es numerico<br>
     * Si es A: indica que el caracter es alfabetico y debe estar en mayuscula<br>
     * Si es a: indica que el caracter es alfabetico y debe estar en minuscula<br>
     * 
     * @param detalleNumeracion
     *            Detalle de la numeracion a la cual se le obtendra la trama
     * @return Trama de la numeracion
     */
    private String obtenerTramaNumeracion(List<DetalleNumeracionDTO> detalleNumeracion) {
        StringBuilder trama = new StringBuilder();
        for (DetalleNumeracionDTO detalleNumeracionDTO : detalleNumeracion) {
            if (Character.isDigit(detalleNumeracionDTO.getCaracterInicio())) {
                trama.append(TRAMA_NUMERACION_NUMERO);
            } else {
                if (Character.isLowerCase(detalleNumeracionDTO.getCaracterInicio())) {
                    trama.append(TRAMA_NUMERACION_LETRA_MINUSCULA);
                } else {
                    trama.append(TRAMA_NUMERACION_LETRA_MAYUSCULA);
                }
            }
        }
        logger.debugv("AdministracionFormulariosEJB::obtenerTramaNumeracion: Trama obtenida para la numeracion = {0}",
                trama);
        return trama.toString();
    }

    /**
     * Recibe una lista de detalles de numeracion y para cada una de ellas examina si el caracter en cada posicion es numerico
     * 
     * @param detalleNumeracion
     *            detalles de numeracion cuyas casillas deben ser analizadas para saber si corresponde a un caracter numerico o no
     */
    private void identificarCasillasNumericas(List<DetalleNumeracionDTO> detalleNumeracion) {
        for (DetalleNumeracionDTO detalleNumeracionDTO : detalleNumeracion) {
            if (Character.isDigit(detalleNumeracionDTO.getCaracterInicio())) {
                detalleNumeracionDTO.setNumerico(true);
            } else
                detalleNumeracionDTO.setNumerico(false);
        }

    }

    @Override
    public List<UnificacionResponsableDTO> consultarResponsablesFormularios(
            UnificacionResponsableDTO unificacionResponsableConsulta) {
        List<UnificacionResponsableDTO> responsablesFormulariosResponse = new ArrayList<>(1);

        checkNotNull(unificacionResponsableConsulta, "Datos del responsable son obligatorios");

        checkNotNull(unificacionResponsableConsulta.getResponsableFormulario(),
                "Datos del responsable son obligatorios");

        StringBuffer jpql = new StringBuffer();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT(ur) FROM UnificacionResponsable AS ur");
        jpql.append(" JOIN FETCH ur.responsableFormulario AS rf");
        jpql.append(" JOIN rf.tipoResponsable AS tr");
        jpql.append(" LEFT JOIN FETCH ur.persona AS p");
        jpql.append(" LEFT JOIN ur.organismoTransito AS ot");

        jpql.append(" WHERE 1=1 ");
        if (unificacionResponsableConsulta.getResponsableFormulario() != null
                && unificacionResponsableConsulta.getResponsableFormulario().getId() != null) {
            jpql.append(" AND rf.id = :pIdResFor");
            filtros.put("pIdResFor", unificacionResponsableConsulta.getResponsableFormulario().getId());
        } else {
            if (unificacionResponsableConsulta.getResponsableFormulario().getTipoResponsable() != null
                    && unificacionResponsableConsulta.getResponsableFormulario().getTipoResponsable().getId() != null) {
                jpql.append(" AND tr.id = :pIdTipRes");
                filtros.put("pIdTipRes", unificacionResponsableConsulta.getResponsableFormulario().getTipoResponsable()
                        .getId());
            }
            if (unificacionResponsableConsulta.getResponsableFormulario().getOrganismoTransito() != null
                    && unificacionResponsableConsulta.getResponsableFormulario().getOrganismoTransito()
                            .getCodigoOrganismo() != null) {
                jpql.append(" AND rf.organismoTransito.codigoOrganismo = :pCodOrgResp");
                filtros.put("pCodOrgResp", unificacionResponsableConsulta.getOrganismoTransito().getCodigoOrganismo());
            }
            if (unificacionResponsableConsulta != null) {
                if (unificacionResponsableConsulta.getPersona() != null) {
                    if (unificacionResponsableConsulta.getPersona().getTipoIdentificacion() != null
                            && unificacionResponsableConsulta.getPersona().getTipoIdentificacion().getId() != null) {
                        jpql.append(" AND p.tipoIdentificacion.id = :pTipDoc");
                        filtros.put("pTipDoc", unificacionResponsableConsulta.getPersona().getTipoIdentificacion()
                                .getId());
                    }
                    if (StringUtils.isNotBlank(unificacionResponsableConsulta.getPersona().getNumeroIdentificacion())) {
                        jpql.append(" AND UPPER(p.numeroIdentificacion) LIKE :pNumIden");
                        filtros.put("pNumIden", "%"
                                + unificacionResponsableConsulta.getPersona().getNumeroIdentificacion().toUpperCase()
                                + "%");
                    }
                }
                if (unificacionResponsableConsulta.getOrganismoTransito() != null
                        && unificacionResponsableConsulta.getOrganismoTransito().getCodigoOrganismo() != null) {
                    jpql.append(" AND str(ot.codigoOrganismo) LIKE :pCodigoOrg");
                    filtros.put("pCodigoOrg", "%"
                            + unificacionResponsableConsulta.getOrganismoTransito().getCodigoOrganismo() + "%");
                }
            }
        }
        if (unificacionResponsableConsulta.getOrganismoTransito() != null) {
            jpql.append(" ORDER BY ur.organismoTransito.codigoOrganismo ASC");
        } else {
            jpql.append(" ORDER BY ur.persona.numeroIdentificacion ASC");
        }
        Session session = em.unwrap(Session.class);
        org.hibernate.Query query = session.createQuery(jpql.toString());
        for (Iterator<String> iterator = filtros.keySet().iterator(); iterator.hasNext();) {
            String param = iterator.next();
            Object object = filtros.get(param);
            query.setParameter(param, object);
        }

        @SuppressWarnings("unchecked")
        List<UnificacionResponsable> resultado = query.list();
        if (!resultado.isEmpty()) {
            for (UnificacionResponsable unificacionResponsable : resultado) {
                UnificacionResponsableDTO unificacionResponsableDTO = UnificacionResponsableHelper
                        .toLevel1DTO(unificacionResponsable);
                ResponsableFormularioDTO responsableFormularioDTO = ResponsableFormularioHelper
                        .toLevel1DTO(unificacionResponsable.getResponsableFormulario());

                if (unificacionResponsable.getPersona() != null) {
                    PersonaDTO responsable = new PersonaDTO(unificacionResponsable.getPersona().getId());
                    List<PersonaDTO> consultarPersona = fachadaAdminNegocio.consultarPersona(responsable);
                    if (!consultarPersona.isEmpty()) {
                        unificacionResponsableDTO.setPersona(consultarPersona.get(0));
                    }
                }

                OrganismoTransito organismoTransito = unificacionResponsable.getOrganismoTransito();
                if (organismoTransito != null) {
                    OrganismoTransitoDTO otResponsable = new OrganismoTransitoDTO(
                            organismoTransito.getCodigoOrganismo());
                    List<OrganismoTransitoDTO> consultarOrganismoTransito = fachadaAdminNegocio
                            .consultarOrganismoTransito(otResponsable);
                    if (!consultarOrganismoTransito.isEmpty()) {
                        unificacionResponsableDTO.setOrganismoTransito(consultarOrganismoTransito.get(0));
                    }
                }

                unificacionResponsableDTO.setResponsableFormulario(responsableFormularioDTO);

                responsablesFormulariosResponse.add(unificacionResponsableDTO);
            }
        }

        return responsablesFormulariosResponse;
    }

    @Override
    public UnificacionResponsableDTO consultarResponsableFormularios(UnificacionResponsableDTO unificacionResponsableDTO)
            throws CirculemosNegocioException {
        GenericDao<UnificacionResponsable> genericDao = new GenericDao<>(UnificacionResponsable.class, em);
        Map<String, Object> parametros = new HashMap<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u FROM UnificacionResponsable u")
                .append(" LEFT JOIN FETCH u.persona p LEFT JOIN FETCH u.organismoTransito o")
                .append(" JOIN FETCH u.responsableFormulario rf WHERE 1 = 1");

        if (unificacionResponsableDTO.getId() != null) {
            sql.append(" AND u.id = :id");
            parametros.put("id", unificacionResponsableDTO.getId());
        }
        if (unificacionResponsableDTO.getResponsableFormulario() != null
                && unificacionResponsableDTO.getResponsableFormulario().getTipoResponsable() != null
                && unificacionResponsableDTO.getResponsableFormulario().getTipoResponsable().getId() != null) {
            sql.append(" AND u.responsableFormulario.tipoResponsable.id = :idTipoResponsable");
            parametros.put("idTipoResponsable", unificacionResponsableDTO.getResponsableFormulario()
                    .getTipoResponsable().getId());
        }
        if (unificacionResponsableDTO.getPersona() != null
                && unificacionResponsableDTO.getPersona().getTipoIdentificacion() != null
                && unificacionResponsableDTO.getPersona().getTipoIdentificacion().getId() != null) {
            sql.append(" AND p.tipoIdentificacion.id = :idTipoIdentificacion");
            parametros.put("idTipoIdentificacion", unificacionResponsableDTO.getPersona().getTipoIdentificacion()
                    .getId());
        }
        if (unificacionResponsableDTO.getPersona() != null
                && unificacionResponsableDTO.getPersona().getNumeroIdentificacion() != null) {
            sql.append(" AND p.numeroIdentificacion = :numeroIdentificacion");
            parametros.put("numeroIdentificacion", unificacionResponsableDTO.getPersona().getNumeroIdentificacion());
        }
        if (unificacionResponsableDTO.getOrganismoTransito() != null
                && unificacionResponsableDTO.getOrganismoTransito().getCodigoOrganismo() != null) {
            sql.append(" AND o.codigoOrganismo = :codigoOrganismo");
            parametros.put("codigoOrganismo", unificacionResponsableDTO.getOrganismoTransito().getCodigoOrganismo());
        }

        List<UnificacionResponsable> unificacionResponsableList = genericDao.buildAndExecuteQuery(sql, parametros);
        UnificacionResponsableDTO dto = null;

        for (UnificacionResponsable entidad : unificacionResponsableList) {
            Date ahora = UtilFecha.buildCalendar().getTime();

            if (entidad.getResponsableFormulario().getFechaFinVincula() != null) {
                if (!UtilFecha.betweenDate(entidad.getResponsableFormulario().getFechaInicioVincula(), entidad
                        .getResponsableFormulario().getFechaFinVincula(), ahora))
                    throw new CirculemosNegocioException(ErrorFormularios.CambioEstadosFormularios.NUM_001013);
            } else if (entidad.getResponsableFormulario().getFechaInicioVincula().compareTo(ahora) > 0)
                throw new CirculemosNegocioException(ErrorFormularios.CambioEstadosFormularios.NUM_001013);

            dto = new UnificacionResponsableDTO();

            if (entidad.getOrganismoTransito() != null)
                dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
            if (entidad.getPersona() != null && entidad.getPersona().getPersonaJuridica() != null) {
                entidad.getPersona().getPersonaJuridica().setPersona(entidad.getPersona());
                dto.setPersona(PersonaJuridicaHelper.toLevel1DTO(entidad.getPersona().getPersonaJuridica()));
            }
            if (entidad.getResponsableFormulario() != null)
                dto.setResponsableFormulario(ResponsableFormularioHelper.toLevel0DTO(entidad.getResponsableFormulario()));

            break;
        }

        return dto;
    }

    // Commented unused Sergio Torres 17-dic-2015
    // @Deprecated
    // @Override
    // public ResponsableFormularioDTO consultarResponsableFormularios(Integer tipoDocumento, String numeroDocumento) {
    // /*
    // * checkNotNull(tipoDocumento, "Filtro de tipo de documento vacio"); checkNotNull(numeroDocumento, "Filtro del Numero de documento vacio");
    // *
    // * TypedQuery<ResponsableFormulario> query = em.createNamedQuery( ResponsableFormulario.SQ_BY_TIPO_Y_NUMERO_DOCUMENTO,
    // * ResponsableFormulario.class); query.setParameter("pIdTipIden", tipoDocumento); query.setParameter("pNumIden", numeroDocumento);
    // *
    // * List<ResponsableFormulario> consultarResponsablesFormularios = query.getResultList(); if (!consultarResponsablesFormularios.isEmpty()) {
    // * ResponsableFormulario responsableFormulario = consultarResponsablesFormularios.get(0); ResponsableFormularioDTO responsableFormularioDTO =
    // * ResponsableFormularioHelper .toLevel1DTO(responsableFormulario);
    // *
    // * PersonaDTO responsable = personaEjb.consultarPersona(responsableFormulario.getPersona().getId());
    // * responsableFormularioDTO.setPersona(responsable);
    // *
    // * return responsableFormularioDTO; }
    // */
    //
    // return null;
    // }

    // Commented unused Sergio Torres 17-dic-2015
    // @Override
    // @Deprecated
    // public ResponsableFormularioDTO consultarResponsablePorPlaca(String numPlaca) {
    // checkNotNull(numPlaca, "Filtro de numero de placa vacio");
    // ResponsableFormularioDTO responsableFormularioDTO = null;
    //
    // GenericDao<ResponsableFormulario> genericDao = new GenericDao<>(ResponsableFormulario.class, em);
    // List<ResponsableFormulario> responsableFormularios = genericDao.findByAttribute("placa", numPlaca);
    // if (!responsableFormularios.isEmpty()) {
    // responsableFormularioDTO = ResponsableFormularioHelper.toLevel1DTO(responsableFormularios.get(0));
    // }
    //
    // return responsableFormularioDTO;
    // }

    @Override
    public Long registrarResponsableFormularios(UnificacionResponsableDTO unificacionResponsableDTO)
            throws CirculemosNegocioException {
        validarResponsableFormularios(unificacionResponsableDTO);

        UnificacionResponsable unificacionResponsable = UnificacionResponsableHelper.toLevel1Entity(
                unificacionResponsableDTO, null);
        ResponsableFormulario responsableFormulario = ResponsableFormularioHelper.toLevel1Entity(
                unificacionResponsableDTO.getResponsableFormulario(), null);
        em.persist(responsableFormulario);

        unificacionResponsable.setResponsableFormulario(responsableFormulario);

        em.persist(unificacionResponsable);

        return unificacionResponsable.getId();
    }

    /**
     * Permite llevar a cabo la validacion de los datos de un responsable de formularios
     * 
     * @param unificacionResponsableDTO
     *            responsable con los atributos o datos a validar
     * @throws CirculemosNegocioException
     * <br>
     *             ADM_031001=Organismo de transito asociado es obligatorio<br>
     *             ADM_031002=Persona de responsable es obligatoria<br>
     *             ADM_031003=La persona asociada al responsable es obligatoria<br>
     *             ADM_031004=Fecha fin de vincualcion es inferior a la fecha de inicio de vinculacion<br>
     *             ADM_031005=Fecha fin de Servicio debe ser mayor a la fecha de inicio de servicio<br>
     *             ADM_031006=Responsable de fomulario ya se encuentra registrado en el sistema<br>
     *             ADM_031007=Datos del responsable vacios.<br>
     *             ADM_031009=El correo del responsable es obligatorio<br>
     *             ADM_031010=Formato del correo del responsable no es valido<br>
     * @author luis.forero(mod 2015-09-09)
     */
    private void validarResponsableFormularios(UnificacionResponsableDTO unificacionResponsableDTO)
            throws CirculemosNegocioException {
        if (unificacionResponsableDTO.getId() == null) {
            OrganismoTransitoDTO organismoTransito = unificacionResponsableDTO.getOrganismoTransito();
            PersonaDTO persona = unificacionResponsableDTO.getPersona();
            if (organismoTransito != null && persona == null) {
                if (organismoTransito.getCodigoOrganismo() == null) {
                    throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031001);
                } else {
                    TypedQuery<Long> count = em.createNamedQuery(UnificacionResponsable.SQ_COUNT_BY_CODIGO_ORGANISMO,
                            Long.class);
                    count.setParameter("pCodOrg", organismoTransito.getCodigoOrganismo());
                    if (count.getSingleResult() > 0) {
                        throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031006);
                    }
                }
            } else if (organismoTransito == null && persona != null) {
                if (persona.getTipoIdentificacion() == null || persona.getTipoIdentificacion().getId() == null
                        || StringUtils.isBlank(persona.getNumeroIdentificacion())) {
                    throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031002);
                } else {
                    TypedQuery<Long> count = em.createNamedQuery(
                            UnificacionResponsable.SQ_COUNT_BY_TIP_NUMERO_DOC_PERSONA, Long.class);
                    count.setParameter("pIdTipIdent", persona.getTipoIdentificacion().getId());
                    count.setParameter("pNumIdent", persona.getNumeroIdentificacion());
                    if (count.getSingleResult() > 0) {
                        throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031006);
                    }
                }
            } else if (organismoTransito == null && persona == null) {
                throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031003);
            }
        }

        ResponsableFormularioDTO responsableFormularioDTO = unificacionResponsableDTO.getResponsableFormulario();
        if (responsableFormularioDTO == null) {
            throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031007);
        }

        if (responsableFormularioDTO.getFechaFinVincula() != null
                && responsableFormularioDTO.getFechaFinVincula().before(
                        responsableFormularioDTO.getFechaInicioVincula())) {
            throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031004);
        }
        if (responsableFormularioDTO.getFechaFinVincula() != null
                && responsableFormularioDTO.getFechaInicioVincula() != null
                && responsableFormularioDTO.getFechaFinVincula().before(
                        responsableFormularioDTO.getFechaInicioVincula())) {
            throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031005);
        }
        if (StringUtils.isBlank(responsableFormularioDTO.getCorreoResponsableFormulario())) {
            throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031009);
        } else if (!EmailValidator.validate(responsableFormularioDTO.getCorreoResponsableFormulario())) {
            throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031010);
        }

    }

    @Override
    public void actualizarResponsableFormularios(UnificacionResponsableDTO unificacionResponsableDTO)
            throws CirculemosNegocioException {
        validarResponsableFormularios(unificacionResponsableDTO);

        UnificacionResponsable unificacionResponsable = em.find(UnificacionResponsable.class,
                unificacionResponsableDTO.getId());
        unificacionResponsable = UnificacionResponsableHelper.toLevel1Entity(unificacionResponsableDTO, null);
        ResponsableFormulario responsableFormulario = unificacionResponsable.getResponsableFormulario();
        responsableFormulario = ResponsableFormularioHelper.toLevel1Entity(
                unificacionResponsableDTO.getResponsableFormulario(), responsableFormulario);
        em.merge(responsableFormulario);
        unificacionResponsable.setResponsableFormulario(responsableFormulario);

        em.merge(unificacionResponsable);

    }

    @Override
    public void eliminarResponsableFormularios(Long idResponsable) throws CirculemosNegocioException {
        validarEliminacionResponsable(idResponsable);
        TypedQuery<UnificacionResponsable> query = em.createNamedQuery(
                UnificacionResponsable.SQ_FIND_BY_ID_RESPONSABLE, UnificacionResponsable.class);
        query.setParameter("pIdRespForm", idResponsable);
        UnificacionResponsable unificacionResponsable = query.getSingleResult();
        ResponsableFormulario responsableFormulario = unificacionResponsable.getResponsableFormulario();
        em.remove(unificacionResponsable);
        em.remove(responsableFormulario);
    }

    /**
     * valida si se puede eliminar un determinado responsable de formulario determinando si existe alguna asignacion a el.
     * 
     * @param idResponsableFormulario
     * @throws CirculemosNegocioException
     * <br>
     *             ADM_031008 =El Responsable de formularios a eliminar tiene o ha tenido asociaciones de asignacion a numeros de formularios.
     * @author luis.forero(2015-09-09)
     */
    private void validarEliminacionResponsable(Long idResponsableFormulario) throws CirculemosNegocioException {
        if (validarAsignacionesResponsable(idResponsableFormulario)) {
            throw new CirculemosNegocioException(AdministrarResponsableFormulario.ADM_031008);
        }
    }

    private boolean validarAsignacionesResponsable(Long idResponsableFormulario) {
        checkNotNull(idResponsableFormulario, "Identificador del responsable de formularios obligatorio, no ingresado");

        TypedQuery<Long> qCountAsigRespForm = em.createNamedQuery(
                DetalleCambioEstado.SQ_COUNT_BY_RESPONSABLE_FORMULARIO, Long.class);
        qCountAsigRespForm.setParameter("idRespForm", idResponsableFormulario);

        Long countAsigRespForm = qCountAsigRespForm.getSingleResult();
        return countAsigRespForm > 0;

    }

    @Override
    public void validarDetalleNumeracion(DetalleNumeracionDTO detalleNumeracionDTO) throws CirculemosNegocioException {

        Character inicio = detalleNumeracionDTO.getCaracterInicio();
        Character fin = detalleNumeracionDTO.getCaracterFin();
        if (Character.isDigit(inicio)) {
            if (!Character.isDigit(fin)) {
                throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016003);
            }
        } else {
            if (Character.isLetter(fin)) {
                if (Character.isLowerCase(inicio) && Character.isUpperCase(fin) || Character.isUpperCase(inicio)
                        && Character.isLowerCase(fin)) {
                    throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016004);
                }
            } else {
                throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016003);
            }
        }
        // Si es no incremental caracteres inicio y fin deben ser iguales, si es incremental el final debe ser mayor al inicial
        if (!detalleNumeracionDTO.getIncremental()) {
            if (!inicio.equals(fin)) {
                throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016001);
            }
        } else {
            // Es incremental
            if (fin <= inicio) {
                throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016002);
            }
        }

    }

    @Override
    public List<DetalleNumeracionDTO> consultarDetalleNumeracionFormulario(int idNumeracionFormulario) {
        List<DetalleNumeracion> resultado = new ArrayList<DetalleNumeracion>();
        TypedQuery<DetalleNumeracion> q = em.createNamedQuery(DetalleNumeracion.SQ_DETALLE_BY_NUMERACION,
                DetalleNumeracion.class);
        q.setParameter("idNumeracion", idNumeracionFormulario);
        resultado = q.getResultList();
        logger.debugv(
                "AdministracionFormulariosEJB::consultarDetalleNumeracionFormulario: Para el idNumeracionFormulario = {0} se encontraron {1} detalles",
                idNumeracionFormulario, resultado.size());
        return DetalleNumeracionHelper.toListLevel0DTO(resultado);
    }

    @Override
    public void eliminarNumeracionFormulario(int idNumeracionFormulario) throws CirculemosNegocioException {
        // Validar que no hayan rangos de numeracion asociados a la numeracion q se desea eliminar
        if (!validarNumeracionEditable(idNumeracionFormulario)) {
            throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016007);
        }
        em.remove(em.find(NumeracionFormulario.class, idNumeracionFormulario));

    }

    @Override
    public boolean validarNumeracionEditable(int idNumeracionFormulario) {
        TypedQuery<RangoFormulario> query = em.createNamedQuery(RangoFormulario.SQ_RANGOS_BY_NUMERACION,
                RangoFormulario.class);
        query.setParameter("idNumeracion", idNumeracionFormulario);
        List<RangoFormulario> resultado = query.getResultList();
        if (!resultado.isEmpty()) {
            return false;
        }
        return true;

    }

    @Override
    public void actualizarNumeracionFormulario(NumeracionFormularioDTO numeracionFormularioDTO)
            throws CirculemosNegocioException {
        validarDatosNumeracion(numeracionFormularioDTO);
        // Validar que no hayan rangos de numeracion asociados a la numeracion
        if (!validarNumeracionEditable(numeracionFormularioDTO.getId())) {
            throw new CirculemosNegocioException(AdministrarNumeracion.NUM_016007);
        }

        String trama = obtenerTramaNumeracion(numeracionFormularioDTO.getDetalleNumeracionList());

        // Verificar que cada casilla sea valida
        for (DetalleNumeracionDTO detalleNumDto : numeracionFormularioDTO.getDetalleNumeracionList()) {
            validarDetalleNumeracion(detalleNumDto);
        }

        // Actualizar la numeracion
        numeracionFormularioDTO.setTrama(trama);
        identificarCasillasNumericas(numeracionFormularioDTO.getDetalleNumeracionList());
        NumeracionFormulario numeracion = em.find(NumeracionFormulario.class, numeracionFormularioDTO.getId());
        // Borrar todo el detalle existente
        final GenericDao<DetalleNumeracion> detalleNumeracionDao = new GenericDao<>(DetalleNumeracion.class, em);
        final Query delDetalleNum = detalleNumeracionDao.buildQuery(
                "DELETE DetalleNumeracion d WHERE d.numeracion.id=:idNumeracion",
                GenericDao.buildMap("idNumeracion", numeracion.getId()));

        logger.debug("Detalle numeracion borrados " + delDetalleNum.executeUpdate());
        em.refresh(numeracion);

        NumeracionFormularioHelper.toLevel1Entity(numeracionFormularioDTO, numeracion);

        numeracion.setDetalleNumeracionList(new ArrayList<DetalleNumeracion>());
        List<DetalleNumeracionDTO> detalleNumeracion = numeracionFormularioDTO.getDetalleNumeracionList();
        for (DetalleNumeracionDTO detalleNumeracionDTO : detalleNumeracion) {
            DetalleNumeracion entidadDetalle = DetalleNumeracionHelper.toLevel0Entity(detalleNumeracionDTO,
                    new DetalleNumeracion());
            entidadDetalle.setNumeracion(numeracion);
            numeracion.getDetalleNumeracionList().add(entidadDetalle);
        }

        em.merge(numeracion);

    }

    @Override
    public List<RelacionEstadosDTO> consultarRelacionesEstados(RelacionEstadosDTO relacionEstadoConsulta) {
        List<RelacionEstadosDTO> lstRelacionesConsultadas = new ArrayList<>();

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT re FROM RelacionEstados AS re");
        jpql.append(" LEFT JOIN FETCH re.estadoFormularioActual AS efa");
        jpql.append(" LEFT JOIN FETCH re.estadoFormularioSiguiente AS efs");
        jpql.append(" LEFT JOIN FETCH re.tipoFormulario AS tf");
        jpql.append(" LEFT JOIN FETCH re.codigoOrganismo AS co");
        jpql.append(" WHERE 1=1");
        if (relacionEstadoConsulta != null) {
            if (relacionEstadoConsulta.getId() != null) {
                jpql.append(" AND re.id = :pIdRelEst");
                filtros.put("pIdRelEst", relacionEstadoConsulta.getId());
            } else {
                if (relacionEstadoConsulta.getEstadoFormularioActual() != null
                        && relacionEstadoConsulta.getEstadoFormularioActual().getId() != null) {
                    jpql.append(" AND efa.id = :pIdEstAct");
                    filtros.put("pIdEstAct", relacionEstadoConsulta.getEstadoFormularioActual().getId());
                }
                if (relacionEstadoConsulta.getEstadoFormularioSiguiente() != null
                        && relacionEstadoConsulta.getEstadoFormularioSiguiente().getId() != null) {
                    jpql.append(" AND efs.id = :pIdEstSig");
                    filtros.put("pIdEstSig", relacionEstadoConsulta.getEstadoFormularioSiguiente().getId());
                }
                if (relacionEstadoConsulta.getTipoFormulario() != null
                        && relacionEstadoConsulta.getTipoFormulario().getId() != null) {
                    jpql.append(" AND tf.id = :pIdTipFor");
                    filtros.put("pIdTipFor", relacionEstadoConsulta.getTipoFormulario().getId());
                }
                if (relacionEstadoConsulta.getOrganismoTranisto() != null
                        && relacionEstadoConsulta.getOrganismoTranisto().getCodigoOrganismo() != null) {
                    jpql.append(" AND co.codigoOrganismo = :pIdCodOrg");
                    filtros.put("pIdCodOrg", relacionEstadoConsulta.getOrganismoTranisto().getCodigoOrganismo());
                }
            }
        }

        GenericDao<RelacionEstados> genericDao = new GenericDao<>(RelacionEstados.class, em);
        List<RelacionEstados> result = genericDao.buildAndExecuteQuery(jpql, filtros);
        if (!result.isEmpty()) {
            lstRelacionesConsultadas = RelacionEstadosHelper.toListLevel1DTO(result);
        }

        return lstRelacionesConsultadas;
    }

    @Override
    public Long registrarRelacionEstados(RelacionEstadosDTO relacionEstadosDTO) throws CirculemosNegocioException {
        validarExisteRelacion(relacionEstadosDTO);

        RelacionEstados relacionEstados = RelacionEstadosHelper.toLevel1Entity(relacionEstadosDTO, null);

        em.persist(relacionEstados);

        return relacionEstados.getId();
    }

    @Override
    public void actualizarRelacionEstados(RelacionEstadosDTO relacionEstadosDTO) throws CirculemosNegocioException {
        validarExisteRelacion(relacionEstadosDTO);

        RelacionEstados relacionEstados = em.find(RelacionEstados.class, relacionEstadosDTO.getId());
        relacionEstados = RelacionEstadosHelper.toLevel1Entity(relacionEstadosDTO, null);

        em.merge(relacionEstados);
    }

    @Override
    public void eliminarRelacionEstados(Long idRelacionEstados) {
        RelacionEstados relacionEstados = em.find(RelacionEstados.class, idRelacionEstados);
        em.remove(relacionEstados);
    }

    /**
     * Metodo que permite verificar si ya existe una relacion de estados para un tipo de formulario determinado y de acuerdo con el organismo
     * correspondiente.
     * 
     * @param relacionEstadosDTO
     * @throws CirculemosNegocioException
     */
    private void validarExisteRelacion(RelacionEstadosDTO relacionEstadosDTO) throws CirculemosNegocioException {
        Integer idEstadoActual = relacionEstadosDTO.getEstadoFormularioActual().getId();
        Integer idEstadoSiguiente = relacionEstadosDTO.getEstadoFormularioSiguiente().getId();
        Integer idTipoFormulario = relacionEstadosDTO.getTipoFormulario().getId();
        Integer idCodigoOrganismo = relacionEstadosDTO.getOrganismoTranisto().getCodigoOrganismo();
        checkNotNull(idEstadoActual, "Filtro identificador de estado actual (idEstadoActual) es vacio");
        checkNotNull(idEstadoSiguiente, "Filtro identificador de estado siguiente (idEstadoSiguiente) es vacio");
        checkNotNull(idTipoFormulario, "Filtro identificador del tipo de formulario (idTipoFormulario) es vacio");
        checkNotNull(idCodigoOrganismo, "Filtro identificador del codigo de organismo (idCodigoOrganismo) es vacio");

        TypedQuery<Long> query = null;
        if (relacionEstadosDTO.getId() != null) {
            query = em.createNamedQuery(RelacionEstados.SQ_COUNT_BY_EST_FORM_NOT_ID_REL, Long.class);
            query.setParameter("pIdRelEst", relacionEstadosDTO.getId());
        } else {
            query = em.createNamedQuery(RelacionEstados.SQ_COUNT_BY_ESTADOS_FORM, Long.class);
        }
        query.setParameter("pIdEstAct", idEstadoActual);
        query.setParameter("pIdEstSig", idEstadoSiguiente);
        query.setParameter("pIdTipForm", idTipoFormulario);
        query.setParameter("pIdCodOrg", idCodigoOrganismo);

        Long count = query.getSingleResult();
        if (count > 0) {
            throw new CirculemosNegocioException(AdministrarRelacionEstados.ADM_050001);
        }

    }

    @Override
    public List<StockTipoFormularioDTO> consultarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormulario) {
        List<StockTipoFormularioDTO> lstStockTipoFormularioDTOs = new ArrayList<>(1);

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT stf FROM StockTipoFormulario AS stf");
        jpql.append(" WHERE 1=1");
        if (stockTipoFormulario != null) {
            if (stockTipoFormulario.getTipoFormulario() != null
                    && stockTipoFormulario.getTipoFormulario().getId() != null) {
                jpql.append(" AND stf.tipoFormulario.id = :pIdTipForm");
                filtros.put("pIdTipForm", stockTipoFormulario.getTipoFormulario().getId());
            }
        }

        GenericDao<StockTipoFormulario> genericDao = new GenericDao<>(StockTipoFormulario.class, em);
        List<StockTipoFormulario> result = genericDao.buildAndExecuteQuery(jpql, filtros);
        if (!result.isEmpty()) {
            for (StockTipoFormulario stockTipoForm : result) {
                StockTipoFormularioDTO stockTipoFormularioDTO = StockTipoFormularioHelper.toLevel1DTO(stockTipoForm);
                lstStockTipoFormularioDTOs.add(stockTipoFormularioDTO);
            }
        }

        return lstStockTipoFormularioDTOs;
    }

    @Override
    public void registrarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormularioDTO)
            throws CirculemosNegocioException {
        validarStockTipoFormulario(stockTipoFormularioDTO);
        StockTipoFormulario stockTipoFormulario = StockTipoFormularioHelper
                .toLevel1Entity(stockTipoFormularioDTO, null);
        em.persist(stockTipoFormulario);
    }

    /**
     * Valida los datos ingresados de un stock tipo formulario
     * 
     * @param stockTipoFormularioDTO
     *            estructura con los datos a validar del stock por tipo de formulario
     * @throws CirculemosNegocioException
     * <br>
     *             NUM_013001=Ya existen datos iguales para el organismo de transito con el tipo de formulario seleccionado. No es posible realizar la
     *             operacion<br>
     *             NUM_013002=Stock ingresado debe estar en porcentaje sin decimales y se debe configurar entre 1% y 100%. No es posible realizar la
     *             operacion<br>
     *             NUM_013003=Stock de formularios es obligatorio. No es posible realizar la operacion.<br>
     *             NUM_013004=Tipo de Formulario debe ser seleccionado. No es posible realizar la operacion.<br>
     */
    private void validarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormularioDTO)
            throws CirculemosNegocioException {
        if (stockTipoFormularioDTO.getPorcentajeMaximoConsumo() == null) {
            throw new CirculemosNegocioException(AdministrarStockTipoFormulario.NUM_013003);
        }
        if (stockTipoFormularioDTO.getPorcentajeMaximoConsumo() < 0
                || stockTipoFormularioDTO.getPorcentajeMaximoConsumo() > 100) {
            throw new CirculemosNegocioException(AdministrarStockTipoFormulario.NUM_013002);
        }
        if (stockTipoFormularioDTO.getTipoFormulario() == null
                || stockTipoFormularioDTO.getTipoFormulario().getId() == null) {
            throw new CirculemosNegocioException(AdministrarStockTipoFormulario.NUM_013004);
        }
        if (stockTipoFormularioDTO.getId() == null) {
            StockTipoFormulario stockExistente = consultarStockTipoFormulario(stockTipoFormularioDTO
                    .getTipoFormulario().getId());
            if (stockExistente != null) {
                throw new CirculemosNegocioException(AdministrarStockTipoFormulario.NUM_013001);
            }
        }
    }

    @Override
    public void actualizarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormularioDTO)
            throws CirculemosNegocioException {
        validarStockTipoFormulario(stockTipoFormularioDTO);

        StockTipoFormulario stockTipoFormulario = em.find(StockTipoFormulario.class, stockTipoFormularioDTO.getId());
        stockTipoFormulario = StockTipoFormularioHelper.toLevel1Entity(stockTipoFormularioDTO, stockTipoFormulario);

        em.merge(stockTipoFormulario);
    }

    @Override
    public void eliminarStockTipoFormulario(Integer idTipoFormulario) throws CirculemosNegocioException {
        StockTipoFormulario stockEliminar = consultarStockTipoFormulario(idTipoFormulario);
        if (stockEliminar == null)
            throw new CirculemosNegocioException(AdministrarStockTipoFormulario.NUM_013005);
        else
            em.remove(stockEliminar);
    }

    private StockTipoFormulario consultarStockTipoFormulario(Integer idTipoFormulario) {
        try {
            TypedQuery<StockTipoFormulario> query = em.createNamedQuery(StockTipoFormulario.SQ_FIND_BY_TIPOFORM,
                    StockTipoFormulario.class);
            query.setParameter("idTipoForm", idTipoFormulario);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UnificacionResponsableDTO consultarResponsableFormulario(String numeroFormulario, int idTipoFormulario) {
        AdministracionFormulariosEJB.logger.debug("FormularioEJB.consultarResponsableFormulario(String, int)");
        UnificacionResponsableDTO unificacionResponsableDTO = null;
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT ur FROM Formulario f");
        jpql.append(" JOIN f.responsableFormulario rf");
        jpql.append(" JOIN rf.unificacionResponsable ur");
        jpql.append(" WHERE f.numeroFormulario = :numeroFormulario");
        jpql.append(" AND f.tipoFormulario.id = :idTipoFormulario");

        TypedQuery<UnificacionResponsable> query = em.createQuery(jpql.toString(), UnificacionResponsable.class);

        /*
         * Parametros
         */
        // Numero de formulario
        query.setParameter("numeroFormulario", numeroFormulario);
        // Tipo formulario
        query.setParameter("idTipoFormulario", idTipoFormulario);

        List<UnificacionResponsable> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            unificacionResponsableDTO = UnificacionResponsableHelper.toLevel1DTO(resultList.get(0));

            if (unificacionResponsableDTO.getPersona() != null) {

                /*
                 * Con esta facahda obtenemos el nombre de la presona
                 */
                unificacionResponsableDTO.setPersona(fachadaAdminNegocio.consultarPersona(
                        unificacionResponsableDTO.getPersona()).get(0));
            } else {

                /*
                 * Se debe tener el organismo de transito con la fachada
                 */
                unificacionResponsableDTO.setOrganismoTransito(fachadaAdminNegocio.consultarOrganismoTransito(
                        unificacionResponsableDTO.getOrganismoTransito()).get(0));
            }
        }
        return unificacionResponsableDTO;

    }

    @Override
    public List<UnificacionResponsableDTO> consultarResponsablesOrganismo(int codigoOrganismo, Integer tipoFormulario) {
        AdministracionFormulariosEJB.logger.debug("FormularioEJB.consultarResponsablesOrganismo");

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT DISTINCT rf.unificacionResponsable FROM  SeguimientoFormulario s");
        sql.append(" JOIN s.responsableFormulario rf");
        sql.append(" JOIN rf.organismoTransito ot");
        sql.append(" JOIN s.formulario f");
        sql.append(" JOIN f.tipoFormulario tf");
        sql.append(" where ot.codigoOrganismo=:organismo");
        sql.append(" AND tf.id=:tipoFormulario");

        Map<String, Object> params = new HashMap<>(3);
        params.put("organismo", codigoOrganismo);
        params.put("tipoFormulario", tipoFormulario);

        GenericDao<UnificacionResponsable> unificacionResponsableDao = new GenericDao<>(UnificacionResponsable.class,
                em);

        List<UnificacionResponsable> resultadoConsulta = unificacionResponsableDao.buildAndExecuteQuery(sql.toString(),
                params);

        List<UnificacionResponsableDTO> responsablesFormulariosResponse = new ArrayList<>(1);

        if (!resultadoConsulta.isEmpty()) {
            for (UnificacionResponsable unificacionResponsable : resultadoConsulta) {// resultado
                UnificacionResponsableDTO unificacionResponsableDTO = UnificacionResponsableHelper
                        .toLevel1DTO(unificacionResponsable);
                ResponsableFormularioDTO responsableFormularioDTO = ResponsableFormularioHelper
                        .toLevel1DTO(unificacionResponsable.getResponsableFormulario());

                if (unificacionResponsable.getPersona() != null) {
                    PersonaDTO responsable = new PersonaDTO(unificacionResponsable.getPersona().getId());
                    List<PersonaDTO> consultarPersona = fachadaAdminNegocio.consultarPersona(responsable);
                    if (!consultarPersona.isEmpty()) {
                        unificacionResponsableDTO.setPersona(consultarPersona.get(0));
                    }
                }

                OrganismoTransito organismoTransito = unificacionResponsable.getOrganismoTransito();
                if (organismoTransito != null) {
                    OrganismoTransitoDTO otResponsable = new OrganismoTransitoDTO(
                            organismoTransito.getCodigoOrganismo());
                    List<OrganismoTransitoDTO> consultarOrganismoTransito = fachadaAdminNegocio
                            .consultarOrganismoTransito(otResponsable);
                    if (!consultarOrganismoTransito.isEmpty()) {
                        unificacionResponsableDTO.setOrganismoTransito(consultarOrganismoTransito.get(0));
                    }
                }

                unificacionResponsableDTO.setResponsableFormulario(responsableFormularioDTO);

                responsablesFormulariosResponse.add(unificacionResponsableDTO);
            }
        }

        return responsablesFormulariosResponse;

    }
}
