package co.com.datatools.seguridad.ejb.historico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections.CollectionUtils;
import org.jboss.logging.Logger;
import org.xml.sax.SAXException;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoRolDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.ejb.CatalogoError;
import co.com.datatools.seguridad.entidades.HistoricoRol;
import co.com.datatools.seguridad.entidades.Rol;
import co.com.datatools.seguridad.entidades.XsdHistorico;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.excepciones.SeguridadRuntimeException;
import co.com.datatools.seguridad.helper.DatosSesionHelper;
import co.com.datatools.seguridad.helper.HistoricoRolHelper;
import co.com.datatools.seguridad.util.XmlSchemaValidatorUtil;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.util.ObjectToXML.Customized;
import co.com.datatools.util.date.UtilFecha;

import com.thoughtworks.xstream.XStream;

/**
 * EJB que provee los metodos para realizar las operaciones CRUD sobre la entidad HistoricoRol
 * 
 * @author claudia.rodriguez
 * 
 */
@Stateless(name = "HistoricoRolEJB")
@LocalBean
public class HistoricoRolEJB {
    private final static Logger logger = Logger.getLogger(HistoricoRolEJB.class.getName());

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    /**
     * Construye un objeto HistoricoRol a partir de un Rol
     * 
     * @param rolDetalle
     *            informacion del rol con los datos para construir el historico
     * @param fechaInicial
     *            Fecha de inicio del historico a construir
     * @return Historico construido
     */
    private HistoricoRol construirHistoricoRol(RolDetalleDto rolDetalle, Date fechaInicial) {
        HistoricoRol historicoRol = new HistoricoRol();
        historicoRol.setRol(em.find(Rol.class, rolDetalle.getIdRol()));
        historicoRol.setFechaInicioRol(fechaInicial);
        if (rolDetalle.getRolPadre() != null)
            historicoRol.setRolPadre(em.find(Rol.class, rolDetalle.getRolPadre().getIdRol()));
        historicoRol.setXsdHistorico(em.find(XsdHistorico.class, ConstantesSeguridad.ID_XSD_HISTORICO_ROL));
        historicoRol.setUsuarioCambio(new DatosSesionHelper().getUsuarioSesion());
        return historicoRol;
    }

    /**
     * Persiste un objeto de HistoricoRol
     * 
     * @param rolDetalle
     *            informacion del rol con los datos para construir el historico
     * @param fechaInicial
     *            Fecha de inicio del historico, si es null, se asigna la fecha del sistema
     * @return Id del HistoricoRol persistido
     */
    public HistoricoRol registrarHistorico(RolDetalleDto rolDetalle, Date fechaInicial) {
        if (fechaInicial == null)
            fechaInicial = new Date();
        HistoricoRol historicoRol = construirHistoricoRol(rolDetalle, fechaInicial);
        // Si el dto no trae grupos, instanciar la lista para que en XMl quede el tag de grupos pero vacio
        if (rolDetalle.getGrupos() == null) {
            List<GrupoDto> grupos = new ArrayList<GrupoDto>();
            rolDetalle.setGrupos(grupos);
        }

        Customized customized = new Customized(obtenerXStremRolDetalleDto());
        // Limpiar este atributo para no guardarlo en el contenido xml ya que esta de manera explicita como columna en la tabla de historico
        rolDetalle.setUsuarioRealizaCambio(null);
        historicoRol.setXmlHistorico(customized.objectToXml(rolDetalle));
        em.persist(historicoRol);

        return historicoRol;
    }

    /**
     * Persiste un objeto HistoricoRol a partir de una modificacion de un Rol, modifica el ultimo historico del rol para ponerle fecha final y
     * registra el nuevo historico
     * 
     * @param rolDetalle
     *            Entidad Rol para la cual se crea el historico
     * @param fechaFinHistorico
     *            fecha fin de vigencia del historico del rol
     * @return
     */
    public HistoricoRol registrarHistoricoPorModificacion(RolDetalleDto rolDetalleDto, Date fechaFinHistorico) {

        // Consultar ultimo historico y ponerle fecha fin
        final Calendar finHist = UtilFecha.buildCalendar(fechaFinHistorico);
        HistoricoRol ultimoHistorico = consultarUltimoHistoricoRol(rolDetalleDto.getIdRol());
        if (ultimoHistorico != null) {
            ultimoHistorico.setFechaFinRol(finHist.getTime());
            em.merge(ultimoHistorico);
        }

        // Crear el nuevo historico
        finHist.add(Calendar.SECOND, 1);
        HistoricoRol historico = registrarHistorico(rolDetalleDto, finHist.getTime());
        return historico;
    }

    /**
     * Consulta el ultimo registro de historico del Rol para el Id de rol enviado
     * 
     * @param idRol
     *            Identificador del rol al cual se le consultara el ultimo historico
     * @return Ultimo historico encontrado, nulo si no encuentra resultado
     */
    public HistoricoRol consultarUltimoHistoricoRol(Integer idRol) {
        TypedQuery<HistoricoRol> query = em.createNamedQuery(HistoricoRol.SQ_HISTORICO_ULTIMO_BY_ROL,
                HistoricoRol.class);
        query.setParameter("idRol", idRol);
        List<HistoricoRol> resultado = query.getResultList();
        if (!CollectionUtils.isEmpty(resultado))
            return resultado.get(0);
        else
            return null;
    }

    /**
     * Elimina todos los registros de HistoricoRol asociados al Rol enviado como parametro
     * 
     * @param entidad
     *            Rol cuyo historico sera eliminado
     */
    public void eliminarHistoricoRol(Rol entidad) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM HistoricoRol h WHERE h.rol.idRol = :idRol");
        Query query = em.createQuery(sql.toString());
        query.setParameter("idRol", entidad.getIdRol());
        query.executeUpdate();
    }

    /**
     * Consulta los registros del historico del Rol con ID enviado como parametro, validando el contenido del campo xmlHistorico contra el XSD
     * respectivo que tenga el registro
     * 
     * @param idRol
     *            Id del rol al que se le consulta el historico
     * @return Historico consultado
     * @throws SeguridadException
     *             RL0008:Error validando el xml del historico
     */
    public List<HistoricoRolDto> consultarHistoricoRol(Integer idRol) throws SeguridadException {
        TypedQuery<HistoricoRol> queryHistorico = em.createNamedQuery(HistoricoRol.SQ_HISTORICO_ALL_BY_USUARIO,
                HistoricoRol.class);
        List<HistoricoRolDto> historico = new ArrayList<HistoricoRolDto>();
        HistoricoRolHelper helper = new HistoricoRolHelper();
        queryHistorico.setParameter("idRol", idRol);
        List<HistoricoRol> resultados = queryHistorico.getResultList();
        XmlSchemaValidatorUtil xmlValidator = new XmlSchemaValidatorUtil();
        for (HistoricoRol historicoRol : resultados) {
            XsdHistorico xsd = em.find(XsdHistorico.class, historicoRol.getXsdHistorico().getIdXsdHistorico());
            historico.add(helper.toDto(historicoRol));
            try {
                xmlValidator.validarXml(historicoRol.getXmlHistorico(), xsd.getContenidoXsd());
                logger.debug("Valida el xml del historico con ID= " + historicoRol.getIdHistoricoRol());
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.info("Error al Validar el xml del historico con ID= " + historicoRol.getIdHistoricoRol());
                throw new SeguridadException(CatalogoError.ERROR_VALIDACION_XML_HISTORICO_ROL);
            }
        }
        return historico;
    }

    public List<HistoricoRolDto> consultarDetalleHistoricoRol(int idRol, Date fechaInicial, Date fechaFinal) {
        List<HistoricoRolDto> historico = new ArrayList<HistoricoRolDto>();
        if (idRol == 0 || fechaInicial == null || fechaFinal == null) {
            throw new IllegalArgumentException("consultarHistoricoRol: Argumentos faltantes");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT h FROM HistoricoRol h WHERE h.rol.idRol =:idRol");
        sql.append(" AND h.fechaInicioRol <= :fechaFiltroFinal");
        sql.append(" AND (h.fechaFinRol >= :fechaFiltroInicial OR (h.fechaFinRol IS NULL AND h.fechaInicioRol >= :fechaFiltroInicial))");
        sql.append(" ORDER BY h.fechaInicioRol DESC");

        TypedQuery<HistoricoRol> consulta = em.createQuery(sql.toString(), HistoricoRol.class);
        consulta.setParameter("idRol", idRol);
        consulta.setParameter("fechaFiltroInicial", fechaInicial);
        consulta.setParameter("fechaFiltroFinal", fechaFinal);

        List<HistoricoRol> resultados = consulta.getResultList();
        if (CollectionUtils.isNotEmpty(resultados)) {
            XmlSchemaValidatorUtil xmlValidator = new XmlSchemaValidatorUtil();
            try {
                Customized customized = new Customized(obtenerXStremRolDetalleDto());
                HistoricoRolHelper helper = new HistoricoRolHelper();
                for (HistoricoRol historicoRol : resultados) {
                    xmlValidator.validarXml(historicoRol.getXmlHistorico(), historicoRol.getXsdHistorico()
                            .getContenidoXsd());
                    HistoricoRolDto historicoRolDto = helper.toDto(historicoRol);

                    RolDetalleDto rolDetalleDto = customized.xmlToObject(RolDetalleDto.class,
                            historicoRol.getXmlHistorico());
                    historicoRolDto.setRolDetalleDto(rolDetalleDto);
                    historico.add(historicoRolDto);
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new SeguridadRuntimeException(e);
            }

        }
        return historico;
    }

    /**
     * Consulta el ultimo registro de historico del Rol para el Id de rol enviado que sea inferior o igual a la fecha de referencia enviada
     * 
     * @param idRol
     *            Identificador del rol al cual se le consultara el ultimo historico inferior a la fecha enviada
     * @param fechaReferencia
     *            fecha de referencia para la cual se consulta le historico mas cercano o igual a dicha fecha
     * @return Ultimo historico encontrado, nulo si no encuentra resultado
     */
    public HistoricoRolDto consultarUltimoHistoricoRol(int idRol, Date fechaReferencia) {
        HistoricoRol historico = null;

        TypedQuery<HistoricoRol> query = em.createNamedQuery(HistoricoRol.SQ_HISTORICO_ULTIMO_BY_ROL_FECHA,
                HistoricoRol.class);
        query.setParameter("idRol", idRol);
        query.setParameter("fechaReferencia", fechaReferencia);
        List<HistoricoRol> resultado = query.getResultList();
        if (!CollectionUtils.isEmpty(resultado))
            historico = resultado.get(0);

        HistoricoRolDto historicoRolDto = null;

        if (historico != null) {
            XmlSchemaValidatorUtil xmlValidator = new XmlSchemaValidatorUtil();
            try {
                Customized customized = new Customized(obtenerXStremRolDetalleDto());
                HistoricoRolHelper helper = new HistoricoRolHelper();
                xmlValidator.validarXml(historico.getXmlHistorico(), historico.getXsdHistorico().getContenidoXsd());
                historicoRolDto = helper.toDto(historico);
                RolDetalleDto rolDetalleDto = customized.xmlToObject(RolDetalleDto.class, historico.getXmlHistorico());
                historicoRolDto.setRolDetalleDto(rolDetalleDto);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new SeguridadRuntimeException(e);
            }
        }
        return historicoRolDto;
    }

    /**
     * Obtiene una instancia del procesador de xml con una configuracion personalizada para la deserializacion y serializacion de un objeto de tipo
     * RolDetalleDto asignando alias a ciertas clases para simplificar algunos tags
     * 
     * @return instancia de Xstream
     */
    private XStream obtenerXStremRolDetalleDto() {
        XStream xstream = new XStream();
        xstream.alias("OperacionDto", OperacionDto.class);
        xstream.alias("RolDetalleDto", RolDetalleDto.class);
        xstream.alias("RecursoDetalleDto", RecursoDetalleDto.class);
        xstream.alias("GrupoDto", GrupoDto.class);
        return xstream;
    }

}
