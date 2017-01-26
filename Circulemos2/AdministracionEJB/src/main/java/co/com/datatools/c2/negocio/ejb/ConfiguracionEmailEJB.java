package co.com.datatools.c2.negocio.ejb;

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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConfiguracionEmailDTO;
import co.com.datatools.c2.dto.CorreoEnvioDTO;
import co.com.datatools.c2.dto.TipoEmailDTO;
import co.com.datatools.c2.entidades.ConfiguracionEmail;
import co.com.datatools.c2.entidades.CorreoEnvio;
import co.com.datatools.c2.entidades.TipoEmail;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.excepciones.CirculemosIllegalArgumentException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion.AdministrarConfiguracionEmail;
import co.com.datatools.c2.negocio.helpers.extension.ConfiguracionEmailHelperExtend;
import co.com.datatools.c2.negocio.helpers.v2.ConfiguracionEmailHelper;
import co.com.datatools.c2.negocio.helpers.v2.CorreoEnvioHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoEmailHelper;
import co.com.datatools.c2.negocio.interfaces.ILConfiguracionEmail;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRConfiguracionEmail;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.mail.EmailValidator;

/**
 * Session Bean implementation class ConfiguracionEmailEJB
 */
@Stateless(name = "ConfiguracionEmailEJB")
@LocalBean
public class ConfiguracionEmailEJB implements IRConfiguracionEmail, ILConfiguracionEmail {
    private final static Logger logger = Logger.getLogger(ConfiguracionEmailEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILSeguridadCirculemos usuarioAppEjb;

    @Override
    public List<ConfiguracionEmailDTO> consultarConfiguracionEmail(Boolean estadoConfiguracion) {
        logger.debug("ConfiguracionEmailEJB::consultarConfiguracionEmail(Boolean)");
        checkNotNull(estadoConfiguracion, "Estado de configuracion requerida");
        // TODO: Debe arreglarse cuando se desarrolle el CU, por el momento envia siempre 0
        return consultarConfiguracionEmail(0, null, estadoConfiguracion);
    }

    @Override
    public List<ConfiguracionEmailDTO> consultarConfiguracionEmail(int codigoOrganismo, EnumTipoCorreo enumTipoCorreo,
            Boolean estadoConfiguracion) {
        logger.debug("ConfiguracionEmailEJB::consultarConfiguracionEmail(int, EnumTipoCorreo, Boolean)");
        List<ConfiguracionEmailDTO> resultado = new ArrayList<>();

        final StringBuilder jpql = new StringBuilder();
        final Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT DISTINCT(ce) FROM ConfiguracionEmail AS ce");
        jpql.append(" LEFT JOIN FETCH ce.tipoEmail AS te");
        jpql.append(" LEFT JOIN FETCH ce.usuario AS us");
        jpql.append(" LEFT JOIN FETCH ce.organismoTransito AS ot");
        jpql.append(" LEFT JOIN FETCH ce.emailList AS crre");

        jpql.append(" WHERE ");
        jpql.append(" ot.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", codigoOrganismo);

        if (null != estadoConfiguracion) {
            jpql.append(" AND ce.estado = :pEstado");
            filtros.put("pEstado", estadoConfiguracion);
        }
        if (null != enumTipoCorreo) {
            jpql.append(" AND te.codigo = :pCodTipEm");
            filtros.put("pCodTipEm", enumTipoCorreo.getCodigo());
        }

        jpql.append(" ORDER BY ce.id");

        final GenericDao<ConfiguracionEmail> query = new GenericDao<>(ConfiguracionEmail.class, em);
        final List<ConfiguracionEmail> resultList = query.buildAndExecuteQuery(jpql, filtros);
        if (!resultList.isEmpty()) {
            for (ConfiguracionEmail configuracionEmail : resultList) {
                ConfiguracionEmailDTO configuracionEmailDTO = ConfiguracionEmailHelperExtend
                        .toLevel2DTO(configuracionEmail);

                configuracionEmailDTO.setEmailList(new ArrayList<CorreoEnvioDTO>());
                for (CorreoEnvio correoEnvio : configuracionEmail.getEmailList()) {
                    CorreoEnvioDTO correoEnvioDTO = CorreoEnvioHelper.toLevel1DTO(correoEnvio);

                    configuracionEmailDTO.getEmailList().add(correoEnvioDTO);
                }

                resultado.add(configuracionEmailDTO);
            }
        }

        return resultado;
    }

    @Override
    public List<TipoEmailDTO> consultarTiposEmail(int codigoOrganismo) {
        logger.debug("ConfiguracionEmailEJB::consultarTiposEmail(int)");
        List<TipoEmailDTO> resultado = new ArrayList<>();

        final TypedQuery<TipoEmail> query = em.createNamedQuery(TipoEmail.SQ_FIND_BY_COD_ORGANISMO, TipoEmail.class);
        query.setParameter("pCodOrg", codigoOrganismo);

        final List<TipoEmail> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            resultado = TipoEmailHelper.toListLevel1DTO(resultList);
        }

        return resultado;
    }

    @Override
    public void eliminarConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO)
            throws CirculemosNegocioException {
        logger.debug("ConfiguracionEmailEJB::eliminarConfiguracionEmail(ConfiguracionEmailDTO)");
        ConfiguracionEmail configuracionEmail = em.find(ConfiguracionEmail.class, configuracionEmailDTO.getId());
        if (configuracionEmail == null) {
            throw new CirculemosNegocioException(AdministrarConfiguracionEmail.ADM_029006);
        }
        em.remove(configuracionEmail);
    }

    @Override
    public void modificarConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO)
            throws CirculemosNegocioException {
        logger.debug("ConfiguracionEmailEJB::modificarConfiguracionEmail(ConfiguracionEmailDTO)");

        validarDatosConfiguracionEmail(configuracionEmailDTO);

        ConfiguracionEmail configuracionEmail = em.find(ConfiguracionEmail.class, configuracionEmailDTO.getId());
        if (configuracionEmail == null) {
            throw new CirculemosNegocioException(AdministrarConfiguracionEmail.ADM_029006);
        }
        configuracionEmail = ConfiguracionEmailHelper.toLevel1Entity(configuracionEmailDTO, configuracionEmail);

        configuracionEmail.setUsuario(new UsuarioPersona(usuarioAppEjb.obtenerUsuarioDto()));
        configuracionEmail.setFechaCambio(new Date());

        contruirCorreosEnvio(configuracionEmailDTO.getEmailList(), configuracionEmail);

        em.merge(configuracionEmail);

    }

    @Override
    public int registrarConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO)
            throws CirculemosNegocioException {
        logger.debug("ConfiguracionEmailEJB::registrarConfiguracionEmail(ConfiguracionEmailDTO)");

        validarDatosConfiguracionEmail(configuracionEmailDTO);

        if (existeConfiguracionEmail(configuracionEmailDTO)) {
            throw new CirculemosNegocioException(AdministrarConfiguracionEmail.ADM_029001);
        }

        ConfiguracionEmail configuracionEmail = ConfiguracionEmailHelper.toLevel1Entity(configuracionEmailDTO, null);

        configuracionEmail.setUsuario(new UsuarioPersona(usuarioAppEjb.obtenerUsuarioDto()));
        configuracionEmail.setFechaCambio(new Date());
        configuracionEmail.setEstado(true);

        contruirCorreosEnvio(configuracionEmailDTO.getEmailList(), configuracionEmail);

        em.persist(configuracionEmail);

        return configuracionEmail.getId();
    }

    /**
     * Permite llevar a cabo la construccion de correos enviados para una configuracion de email con respecto a un listado recibiod de correos en el
     * servicio
     * 
     * @param lstEmails
     *            listado de correos de envio recibidos en el servicio a asignar a la configuracion de email.
     * @param configuracionEmail
     *            configuracion de email a la cual se le ingresan los correos de envios
     * @throws CirculemosNegocioException
     * @author luis.forero
     */
    private void contruirCorreosEnvio(List<CorreoEnvioDTO> emailList, ConfiguracionEmail configuracionEmail)
            throws CirculemosNegocioException {
        logger.debug("ConfiguracionEmailEJB::contruirCorreosEnvio(List<CorreoEnvioDTO>, ConfiguracionEmail)");
        if (emailList != null) {
            List<CorreoEnvio> listCorreosExistente = configuracionEmail.getEmailList();
            if (listCorreosExistente == null) {
                listCorreosExistente = new ArrayList<CorreoEnvio>(1);
                configuracionEmail.setEmailList(listCorreosExistente);
            }

            for (Iterator<CorreoEnvio> iterator = listCorreosExistente.iterator(); iterator.hasNext();) {
                CorreoEnvio correoEnvio = (CorreoEnvio) iterator.next();
                boolean removerCorreo = true;
                if (emailList != null) {
                    for (CorreoEnvioDTO correoEnvioDTO : emailList) {
                        if (StringUtils.isNotBlank(correoEnvioDTO.getEmail())
                                && correoEnvio.getEmail().equals(correoEnvioDTO.getEmail())) {
                            // correoEnvio = CorreoEnvioHelper.toLevel1Entity(correoEnvioDTO, correoEnvio);
                            // correoEnvio.setConfiguracionEmail(configuracionEmail);

                            emailList.remove(correoEnvioDTO);

                            removerCorreo = false;
                            break;
                        }
                    }
                }
                if (removerCorreo) {
                    iterator.remove();
                }

            }

            for (CorreoEnvioDTO correoEnvioDTO : emailList) {

                correoEnvioDTO.setId(null);
                correoEnvioDTO.setConfiguracionEmail(null);

                for (CorreoEnvio correoEnvio : listCorreosExistente) {
                    if (correoEnvio.getEmail().equals(correoEnvioDTO.getEmail())) {
                        throw new CirculemosNegocioException(AdministrarConfiguracionEmail.ADM_029003);
                    }
                }

                if (validarEmail(correoEnvioDTO)) {
                    final CorreoEnvio correoEnvio = CorreoEnvioHelper.toLevel1Entity(correoEnvioDTO, null);

                    correoEnvio.setOrganismoTransito(configuracionEmail.getOrganismoTransito());
                    correoEnvio.setConfiguracionEmail(configuracionEmail);

                    listCorreosExistente.add(correoEnvio);
                }
            }
        }
    }

    /**
     * Valida la existencia de una configuracion con el mismo tipo de email y codigo de organismo en el sistema
     * 
     * @param configuracionEmailDTO
     *            Objeto que contiene tanto el organismo de transito como el tipo de email a ingresar
     * @return true si la configuracion con el tipo de email(tipo comunicacion) y organismo se encuentra ya ingresada en el sistema, false de lo
     *         contrario
     * @author luis.forero
     */
    private boolean existeConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO) {
        logger.debug("ConfiguracionEmailEJB::existeConfiguracionEmail(ConfiguracionEmailDTO)");

        final Query query = em.createNamedQuery(ConfiguracionEmail.SQ_COUNT_BY_TIP_EMAIL_ORG, Long.class);
        query.setParameter("pCodTipEmail", configuracionEmailDTO.getTipoEmail().getCodigo());
        query.setParameter("pCodOrg", configuracionEmailDTO.getOrganismoTransito().getCodigoOrganismo());

        final long count = (long) query.getSingleResult();
        return count > 0;

    }

    @Override
    public List<CorreoEnvioDTO> consultarEmails(String email, int codOrganismo) {
        logger.debug("ConfiguracionEmailEJB::consultarEmails(String, int)");
        List<CorreoEnvioDTO> emails = new ArrayList<>();

        final String namedQuery = StringUtils.isNotBlank(email) ? CorreoEnvio.SQ_BY_COD_ORG_AND_CORREO
                : CorreoEnvio.SQ_BY_COD_ORGANISMO;
        final TypedQuery<CorreoEnvio> query = em.createNamedQuery(namedQuery, CorreoEnvio.class);
        if (namedQuery.equals(CorreoEnvio.SQ_BY_COD_ORG_AND_CORREO)) {
            query.setParameter("pEmail", email.toLowerCase() + "%");
        }
        query.setParameter("pCodOrg", codOrganismo);

        final List<CorreoEnvio> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            emails = CorreoEnvioHelper.toListLevel1DTO(resultList);
        }

        return emails;
    }

    /**
     * Permite llevar a cabo la validacion de los datos ingresados de la configuracion de email
     * 
     * @param confEmailDTO
     * @return true si son validos
     * @throws CirculemosNegocioException
     *             retorna el error de validaciones de negocio de los datos ingrersados
     * @author luis.forero
     */
    private boolean validarDatosConfiguracionEmail(ConfiguracionEmailDTO confEmailDTO)
            throws CirculemosNegocioException {
        logger.debug("ConfiguracionEmailEJB::validarDatosConfiguracionEmail(ConfiguracionEmailDTO)");
        if (confEmailDTO.getTipoEmail() == null || confEmailDTO.getTipoEmail().getCodigo() == null) {
            throw new CirculemosNegocioException(AdministrarConfiguracionEmail.ADM_029005);
        }
        if (confEmailDTO.getOrganismoTransito() == null
                || confEmailDTO.getOrganismoTransito().getCodigoOrganismo() == null) {
            throw new CirculemosNegocioException(AdministrarConfiguracionEmail.ADM_029002);
        }

        return true;
    }

    /**
     * Valida el correo electronico
     * 
     * @param correoEnvioDTO
     * @return true si la validacion fue exitosa.
     * @throws CirculemosNegocioException
     * @author luis.forero
     */
    private boolean validarEmail(CorreoEnvioDTO correoEnvioDTO) throws CirculemosNegocioException {
        logger.debug("ConfiguracionEmailEJB::validarEmail(CorreoEnvioDTO)");
        if (!EmailValidator.validate(correoEnvioDTO.getEmail())) {
            throw new CirculemosNegocioException(AdministrarConfiguracionEmail.ADM_029004);
        }
        if (correoEnvioDTO.getEmail().length() > 50) {
            throw new CirculemosIllegalArgumentException(AdministrarConfiguracionEmail.ADM_029007.getDescError());
        }
        return true;
    }

    @Override
    public CorreoEnvioDTO consultarEmailsConfigurados(int codigoOrganismo, Integer idConfiguracion) {
        logger.debug("ConfiguracionEmailEJB::consultarEmailsConfigurados(int, Integer)");
        CorreoEnvioDTO email = null;

        final TypedQuery<CorreoEnvio> query = em.createNamedQuery(CorreoEnvio.SQ_FIND_BY_ORG_IDCONFCORR,
                CorreoEnvio.class);
        query.setParameter("pCodOrg", codigoOrganismo);
        query.setParameter("pIdCnfE", idConfiguracion);

        final List<CorreoEnvio> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            email = CorreoEnvioHelper.toLevel1DTO(resultList.get(0));
        }

        return email;
    }
}
