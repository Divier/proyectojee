package co.com.datatools.seguridad.ejb.historico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.xml.sax.SAXException;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoUsuarioDto;
import co.com.datatools.seguridad.dto.autenticacion.IngresoDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.seguridad.dto.comun.ConsultaIngresoDto;
import co.com.datatools.seguridad.ejb.CatalogoError;
import co.com.datatools.seguridad.entidades.HistoricoUsuario;
import co.com.datatools.seguridad.entidades.IngresoUsuario;
import co.com.datatools.seguridad.entidades.Rol;
import co.com.datatools.seguridad.entidades.Usuario;
import co.com.datatools.seguridad.entidades.XsdHistorico;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.excepciones.SeguridadRuntimeException;
import co.com.datatools.seguridad.helper.DatosSesionHelper;
import co.com.datatools.seguridad.helper.HistoricoUsuarioHelper;
import co.com.datatools.seguridad.helper.IngresoHelper;
import co.com.datatools.seguridad.util.XmlSchemaValidatorUtil;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.ObjectToXML.Customized;
import co.com.datatools.util.date.UtilFecha;

import com.thoughtworks.xstream.XStream;

/**
 * Servicios de negocio enfocados en crear los historicos de los usuarios
 * 
 * @author Felipe Martinez
 */
@Stateless(name = "HistoricoUsuarioEJB")
@LocalBean
public class HistoricoUsuarioEJB {

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(HistoricoUsuarioEJB.class.getName());

    public void crearHistorico(HistoricoUsuarioDto historicoUsuarioDto) {

        // Costruir objeto HistoricoUsuario
        UsuarioDetalleDto usuarioDto = historicoUsuarioDto.getUsuarioDto();
        final HistoricoUsuario nuevoHistorico = construirHistoricoUsuario(historicoUsuarioDto);

        // Las diferentes fechas del historico, se vuelven a crear con una instancia de util.Date para evitar que aquellas que hayan sido seteadas en
        // el dto desde la entidad, queden en el xml del historico con class=sql.Date
        if (usuarioDto.getFechaFinUsuario() != null) {
            Date fechaFinUsuario = new Date();
            fechaFinUsuario.setTime(usuarioDto.getFechaFinUsuario().getTime());
            usuarioDto.setFechaFinUsuario(fechaFinUsuario);
        }

        if (usuarioDto.getFechaInicioUsuario() != null) {
            Date fechaIniUsuario = new Date();
            fechaIniUsuario.setTime(usuarioDto.getFechaInicioUsuario().getTime());
            usuarioDto.setFechaInicioUsuario(fechaIniUsuario);
        }

        if (usuarioDto.getFechaModPass() != null) {
            Date fechaModPass = new Date();
            fechaModPass.setTime(usuarioDto.getFechaModPass().getTime());
            usuarioDto.setFechaModPass(fechaModPass);
        }

        if (usuarioDto.getFechaModUsuario() != null) {
            Date fechaModUsuario = new Date();
            fechaModUsuario.setTime(usuarioDto.getFechaModUsuario().getTime());
            usuarioDto.setFechaModUsuario(fechaModUsuario);
        }

        if (usuarioDto.getFechaBloqueoPassword() != null) {
            Date fechaBloqueoPw = new Date();
            fechaBloqueoPw.setTime(usuarioDto.getFechaBloqueoPassword().getTime());
            usuarioDto.setFechaBloqueoPassword(fechaBloqueoPw);
        }

        // Para los rolesDetalle del usuario, asignar en null el map de RecursosAplicacion para que no quede en el historico
        if (!CollectionUtils.isEmpty(usuarioDto.getRoles())) {
            for (RolDto rolDto : usuarioDto.getRoles()) {
                ((RolDetalleDto) rolDto).setRecursosAplicacion(null);
            }
        }

        // No guardar el Id del estado, solo el nombre
        usuarioDto.setIdEstadoUsuario(null);
        // No guardar el Id del estado, solo el nombre
        usuarioDto.setIdEstadoPassword(null);

        Customized customized = new Customized(obtenerXStremUsuarioDetalleDto());
        nuevoHistorico.setXmlHistorico(customized.objectToXml(usuarioDto));

        final Calendar inicioHist = UtilFecha.buildCalendar(null);
        nuevoHistorico.setFechaInicio(inicioHist.getTime());
        nuevoHistorico.setFechaFinal(null);

        // Grupos
        if (usuarioDto.getGrupos() == null) {
            usuarioDto.setGrupos(new ArrayList<GrupoDto>());
        }

        if (historicoUsuarioDto.isCierraHistoricoAnterior()) {
            // Consultar el ultimo historico y ponerle fecha fin
            inicioHist.add(Calendar.SECOND, -1);
            HistoricoUsuario ultimoHistorico = consultarUltimoHistoricoUsuario(usuarioDto.getId());
            if (ultimoHistorico != null) {
                ultimoHistorico.setFechaFinal(inicioHist.getTime());
                em.merge(ultimoHistorico);
            }
        }
        em.persist(nuevoHistorico);
        logger.debug("Se registró el historico del usuario con login= " + usuarioDto.getLogin());
    }

    private HistoricoUsuario construirHistoricoUsuario(HistoricoUsuarioDto historicoUsuarioDto) {
        HistoricoUsuario historico = new HistoricoUsuario();
        historico.setUsuario(em.find(Usuario.class, historicoUsuarioDto.getUsuarioDto().getId()));
        historico.setPassword(historicoUsuarioDto.getUsuarioDto().getPassword());
        historico.setFechaModificaPassword(historicoUsuarioDto.getUsuarioDto().getFechaModPass());
        if (StringUtils.isNotBlank(historicoUsuarioDto.getUsuarioRealizaCambio()))
            historico.setUsuarioCambio(historicoUsuarioDto.getUsuarioRealizaCambio());
        else
            historico.setUsuarioCambio(new DatosSesionHelper().getUsuarioSesion());
        historico.setDescripcionCambio(historicoUsuarioDto.getDescripcionCambio());
        // Roles del historico
        List<Rol> rolList = new ArrayList<Rol>();
        for (RolDto rolDto : historicoUsuarioDto.getUsuarioDto().getRoles()) {
            rolList.add(em.find(Rol.class, rolDto.getIdRol()));
        }
        historico.setRolList(rolList);
        historico.setXsdHistorico(em.find(XsdHistorico.class, ConstantesSeguridad.ID_XSD_HISTORICO_USUARIO));
        return historico;
    }

    /**
     * Consulta el ultimo registro de historico del usuario con Id enviado
     * 
     * @param idUsuario
     *            Identificador del usurio al que se le consulta el ultimo historico
     * @return Ultimo historico encontrado, nulo si no encuentra resultado
     */
    private HistoricoUsuario consultarUltimoHistoricoUsuario(Integer idUsuario) {
        TypedQuery<HistoricoUsuario> query = em.createNamedQuery(HistoricoUsuario.SQ_HISTORICO_ULTIMO_BY_USUARIO,
                HistoricoUsuario.class);
        query.setParameter("idUsuario", idUsuario);
        List<HistoricoUsuario> resultado = query.getResultList();
        if (!CollectionUtils.isEmpty(resultado)) {
            logger.info("Se encontró registro del último histórico para el usuario con Id= " + idUsuario);
            return resultado.get(0);
        } else {
            logger.info("No se encontró registro del último histórico del usuario con Id= " + idUsuario);
            return null;
        }
    }

    /**
     * Elimina todos los registros de HistoricoUsuario asociados al Usuario enviado como parametro
     * 
     * @param usuario
     *            Usuario cuyo historico sera eliminado
     */
    public void eliminarHistoricoUsuario(Usuario usuario) {
        GenericDao<HistoricoUsuario> histUsDao = new GenericDao<>(HistoricoUsuario.class, em);
        // Se consulta cada entidad de historico y se borra explicitamente
        // para poder borrar en cascada la relacion en historico_rol_usuario
        final List<HistoricoUsuario> historicos = histUsDao
                .findByAttribute("usuario.idUsuario", usuario.getIdUsuario());
        for (HistoricoUsuario historico : historicos) {
            em.remove(historico);
            logger.debug("Se eliminó el histórico con Id= " + historico.getIdHistoricoUsuario()
                    + " para el usuario de login= " + usuario.getLogin());
        }

    }

    /**
     * Consulta los registros del historico del Usuario con ID enviado como parametro, validando el contenido del campo xmlHistorico contra el XSD
     * respectivo que tenga el registro
     * 
     * @param idUsuario
     *            Id del usuario al que se le consulta el historico
     * @return Historico consultado
     * @throws SeguridadException
     *             US0007:Error validando el xml del historico
     * 
     */
    public List<HistoricoUsuarioDto> consultarHistoricoUsuario(Integer idUsuario) throws SeguridadException {
        TypedQuery<HistoricoUsuario> queryHistorico = em.createNamedQuery(HistoricoUsuario.SQ_HISTORICO_ALL_BY_USUARIO,
                HistoricoUsuario.class);
        List<HistoricoUsuarioDto> historico = new ArrayList<HistoricoUsuarioDto>();
        HistoricoUsuarioHelper helper = new HistoricoUsuarioHelper();
        queryHistorico.setParameter("idUsuario", idUsuario);
        List<HistoricoUsuario> resultados = queryHistorico.getResultList();
        XmlSchemaValidatorUtil xmlValidator = new XmlSchemaValidatorUtil();
        for (HistoricoUsuario historicoUsuario : resultados) {
            XsdHistorico xsd = em.find(XsdHistorico.class, historicoUsuario.getXsdHistorico().getIdXsdHistorico());
            historico.add(helper.toDto(historicoUsuario));
            try {
                xmlValidator.validarXml(historicoUsuario.getXmlHistorico(), xsd.getContenidoXsd());
                logger.debug("Valida el xml del historico con ID= " + historicoUsuario.getIdHistoricoUsuario());
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.info("Error al Validar el xml del historico con ID= " + historicoUsuario.getIdHistoricoUsuario());
                throw new SeguridadException(CatalogoError.ERROR_VALIDACION_XML_HISTORICO_USUARIO);
            }
        }
        return historico;
    }

    public List<HistoricoUsuarioDto> consultarHistoricoUsuario(int idUsuario, Date fechaInicial, Date fechaFinal) {
        List<HistoricoUsuarioDto> historico = new ArrayList<HistoricoUsuarioDto>();
        if (idUsuario == 0 || fechaInicial == null || fechaFinal == null) {
            throw new IllegalArgumentException("consultarHistoricoUsuario: Argumentos faltantes");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT h FROM HistoricoUsuario h WHERE h.usuario.idUsuario =:idUsuario");
        sql.append(" AND h.fechaInicio >= :fechaFiltroInicial");
        sql.append(" AND (h.fechaFinal <= :fechaFiltroFinal OR (h.fechaFinal IS NULL AND h.fechaInicio <= :fechaFiltroFinal))");
        sql.append(" ORDER BY h.fechaInicio DESC");

        TypedQuery<HistoricoUsuario> consulta = em.createQuery(sql.toString(), HistoricoUsuario.class);
        consulta.setParameter("idUsuario", idUsuario);
        consulta.setParameter("fechaFiltroInicial", fechaInicial);
        consulta.setParameter("fechaFiltroFinal", fechaFinal);

        List<HistoricoUsuario> resultados = consulta.getResultList();
        if (CollectionUtils.isNotEmpty(resultados)) {
            XmlSchemaValidatorUtil xmlValidator = new XmlSchemaValidatorUtil();
            try {
                Customized customized = new Customized(obtenerXStremUsuarioDetalleDto());
                HistoricoUsuarioHelper helper = new HistoricoUsuarioHelper();
                for (HistoricoUsuario historicoUsuario : resultados) {
                    xmlValidator.validarXml(historicoUsuario.getXmlHistorico(), historicoUsuario.getXsdHistorico()
                            .getContenidoXsd());
                    HistoricoUsuarioDto historicoUsuarioDto = helper.toDto(historicoUsuario);
                    UsuarioDetalleDto usuarioDetalleDto = customized.xmlToObject(UsuarioDetalleDto.class,
                            historicoUsuario.getXmlHistorico());
                    historicoUsuarioDto.setUsuarioDto(usuarioDetalleDto);
                    historico.add(historicoUsuarioDto);
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new SeguridadRuntimeException(e);
            }

        }
        return historico;
    }

    public List<IngresoDto> consultarHistoricoIngresoUsuario(ConsultaIngresoDto consultaIngresoDto) {
        List<IngresoDto> resultado = new ArrayList<IngresoDto>();
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>(5);
        GenericDao<IngresoUsuario> ingresoDao = new GenericDao<IngresoUsuario>(IngresoUsuario.class, em);
        sql.append("SELECT i FROM IngresoUsuario i ");
        if (StringUtils.isNotBlank(consultaIngresoDto.getLogin())) {
            sql.append(" JOIN i.usuario u");

        }
        sql.append(" WHERE 1=1");
        if (StringUtils.isNotBlank(consultaIngresoDto.getLogin())) {
            sql.append(" AND UPPER(u.login) =:usuario");
            parametros.put("usuario", consultaIngresoDto.getLogin().toUpperCase());
        }

        if (StringUtils.isNotBlank(String.valueOf(consultaIngresoDto.getIdAplicacion()))) {
            sql.append(" AND i.aplicacion.idAplicacion=:idApp");
            parametros.put("idApp", consultaIngresoDto.getIdAplicacion());
        }
        if (consultaIngresoDto.getFechaIngresoInicial() != null && consultaIngresoDto.getFechaIngresoFinal() != null) {
            sql.append(" AND i.fechaInicio BETWEEN :fechaIngresoInicial AND :fechaIngresoFinal");
            parametros.put("fechaIngresoInicial", consultaIngresoDto.getFechaIngresoInicial());
            parametros.put("fechaIngresoFinal", consultaIngresoDto.getFechaIngresoFinal());
        }
        if (StringUtils.isNotBlank(consultaIngresoDto.getRecurso())) {
            sql.append(" AND i.xmlActividadIngreso LIKE :nombreRecurso");
            parametros.put("nombreRecurso", "%" + consultaIngresoDto.getRecurso() + "%");
        }

        if (StringUtils.isNotBlank(consultaIngresoDto.getEstadoIngreso())) {
            sql.append(" AND i.estadoIngreso.idEstadoIngreso= :idEstadoIngreso");
            parametros.put("idEstadoIngreso", Integer.valueOf(consultaIngresoDto.getEstadoIngreso()));
        }

        sql.append(" ORDER BY i.fechaInicio DESC");

        List<IngresoUsuario> resultados = ingresoDao.buildAndExecuteQuery(sql.toString(), parametros);
        IngresoHelper helper = new IngresoHelper();

        for (IngresoUsuario ingresoUsuario : resultados) {
            resultado.add(helper.toDto(ingresoUsuario));
        }
        return resultado;
    }

    /**
     * Obtiene una instancia del procesador de xml con una configuracion personalizada para la deserializacion y serializacion de un objeto de tipo
     * UsuarioDetalleDto asignando alias a ciertas clases para simplificar algunos tags
     * 
     * @return instancia de Xstream
     */
    private XStream obtenerXStremUsuarioDetalleDto() {
        XStream xstream = new XStream();
        xstream.alias("UsuarioDetalleDto", UsuarioDetalleDto.class);
        xstream.alias("RolDetalleDto", RolDetalleDto.class);
        xstream.alias("GrupoDto", GrupoDto.class);
        return xstream;
    }

}
