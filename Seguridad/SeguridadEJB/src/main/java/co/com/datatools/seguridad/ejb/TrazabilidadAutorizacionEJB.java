package co.com.datatools.seguridad.ejb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections.CollectionUtils;
import org.infinispan.Cache;
import org.infinispan.CacheImpl;
import org.jboss.logging.Logger;
import org.xml.sax.SAXException;

import co.com.datatools.seguridad.dto.autenticacion.IngresoDto;
import co.com.datatools.seguridad.dto.comun.InfoAutorizacion;
import co.com.datatools.seguridad.entidades.IngresoUsuario;
import co.com.datatools.seguridad.entidades.XsdHistorico;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.helper.IngresoHelper;
import co.com.datatools.seguridad.util.XmlSchemaValidatorUtil;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.util.ObjectToXML.Customized;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

/**
 * @author luis.martinez Se modifico la clase de tipo EJB Singleton a Stateless con el fin de evitar problemas con la trazabilidad de usuario cuando
 *         el producto, se despliegue como un cluster permitiendo el manejo de replicación de session web a traves del cache de infinispan. La
 *         trazabilidad de peticiones autorizadas del usuario
 */
@Stateless(name = "TrazabilidadAutorizacionEJB")
@LocalBean
public class TrazabilidadAutorizacionEJB {

    private final static Logger logger = Logger.getLogger(TrazabilidadAutorizacionEJB.class.getName());

    /**
     * Inyeccion (o JNDI lookup) de objeto CacheContainer Infinispan
     */
    @Resource(lookup = "java:jboss/infinispan/container/trazabilidadAutorizacion")
    private org.infinispan.manager.CacheContainer trazabilidadAutorizacionContainer;

    private Cache<Object, Object> cacheAutorizaciones;

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    public TrazabilidadAutorizacionEJB() {
        logger.debug("TrazabilidadAutorizacionEJB::nueva instancia de TrazabilidadAutorizacionEJB");
    }

    @PostConstruct
    private void init() {
        logger.info("init");
        final StringBuilder strb = new StringBuilder("TrazabilidadAutorizacionEJB - Obteniendo Cache Container ");
        if (trazabilidadAutorizacionContainer != null) {
            strb.append("--> se encuentra Cache Container ");
            this.cacheAutorizaciones = this.trazabilidadAutorizacionContainer
                    .getCache(ConstantesSeguridad.NOMBRE_CACHE_AUTORIZACIONES);
        }
        if (this.cacheAutorizaciones == null) {
            this.cacheAutorizaciones = new CacheImpl<Object, Object>(ConstantesSeguridad.NOMBRE_CACHE_AUTORIZACIONES);
        } else {
            strb.append("--> Se encuentra instancia de Cache: " + ConstantesSeguridad.NOMBRE_CACHE_AUTORIZACIONES
                    + " administrada por el contenedor");
        }
        logger.debug(strb);
    }

    @SuppressWarnings("unchecked")
    @Asynchronous
    public void adicionarSolicitudAutorizacion(Integer idIngresoUsuario, InfoAutorizacion infoAutorizacion) {
        logger.debug("TrazabilidadAutorizacionEJB::adicionarSolicitudAutorizacion");
        List<InfoAutorizacion> autIngreso = (List<InfoAutorizacion>) cacheAutorizaciones.get(idIngresoUsuario);
        if (autIngreso != null) {
            autIngreso.add(infoAutorizacion);
        } else {
            autIngreso = new ArrayList<>(20);
            if (infoAutorizacion != null) {
                autIngreso.add(infoAutorizacion);
            }
            cacheAutorizaciones.put(idIngresoUsuario, autIngreso);
        }
        logger.infov("Tamaño {0}, contenido {1}", autIngreso.size(), autIngreso);
    }

    @SuppressWarnings("unchecked")
    public void registrarSolicitudesAutorizacion(Integer idIngresoUsuario) {
        logger.infov("TrazabilidadAutorizacionEJB::registrarSolicitudAutorizacion para el idIngresoUsuario= {0}",
                idIngresoUsuario);
        List<InfoAutorizacion> autIngreso = (List<InfoAutorizacion>) cacheAutorizaciones.get(idIngresoUsuario);
        logger.info("El cache de autorizaciones tiene: " + cacheAutorizaciones.toString());
        // Ponerle fecha cierre al ingreso
        final IngresoUsuario ingreso = em.find(IngresoUsuario.class, idIngresoUsuario);
        ingreso.setFechaCierre(new Date());
        em.merge(ingreso);
        // Si se tiene alguna actividad de ingreso del usuario en el cache de autorizacion, guardarla en formato xml en el campo xml_actividad_ingreso
        // del ingreso relacionado
        if (autIngreso != null && !autIngreso.isEmpty()) {
            // XML
            Customized customized = new Customized(obtenerXStremInfoAutorizacion());
            String xmlString = customized.objectToXml(autIngreso);
            ingreso.setXmlActividadIngreso(xmlString);
            // XSD
            XsdHistorico xsdHistorico = new XsdHistorico();
            xsdHistorico.setIdXsdHistorico(ConstantesSeguridad.ID_XSD_ACTIVIDAD_INGRESO);
            ingreso.setXsdHistorico(xsdHistorico);
            em.merge(ingreso);
            logger.infov(
                    "TrazabilidadAutorizacionEJB::registrarSolicitudAutorizacion: actualizo con fecha cierra {0} ",
                    ingreso.getFechaCierre());
        }
        cacheAutorizaciones.remove(idIngresoUsuario);
    }

    /**
     * Consulta los ingresos del usuario con ID enviado como parametro, validando el contenido del campo
     * 
     * xmlActividadIngreso contra el XSD respectivo que tenga el registro
     * 
     * @param idUsuario
     *            Id del usuario al que se le consultan los ingresos
     * @return Listado de los ingresos del usuario
     * @throws SeguridadException
     *             US0008:Error validando el xml de la actividad de ingreso
     */
    public List<IngresoDto> consultarSolicitudesAutorizacion(Integer idUsuario) throws SeguridadException {
        XmlSchemaValidatorUtil xmlValidator = new XmlSchemaValidatorUtil();
        IngresoHelper helper = new IngresoHelper();
        List<IngresoDto> ingresos = new ArrayList<IngresoDto>();

        TypedQuery<IngresoUsuario> queryIngreso = em.createNamedQuery(IngresoUsuario.SQ_INGRESO_BY_USUARIO,
                IngresoUsuario.class);
        queryIngreso.setParameter("idUsuario", idUsuario);
        List<IngresoUsuario> ingresosUsuario = queryIngreso.getResultList();

        if (!CollectionUtils.isEmpty(ingresosUsuario)) {
            for (IngresoUsuario ingresoUsuario : ingresosUsuario) {
                if (ingresoUsuario.getXmlActividadIngreso() != null) {
                    XsdHistorico xsd = em
                            .find(XsdHistorico.class, ingresoUsuario.getXsdHistorico().getIdXsdHistorico());
                    ingresos.add(helper.toDto(ingresoUsuario));
                    try {
                        xmlValidator.validarXml(ingresoUsuario.getXmlActividadIngreso(), xsd.getContenidoXsd());
                        logger.debug("Valida el xml del ingreso con ID= " + ingresoUsuario.getIdIngresoUsuario());
                    } catch (ParserConfigurationException | SAXException | IOException e) {
                        logger.info("Error al Validar el xml del ingreso con ID= "
                                + ingresoUsuario.getIdIngresoUsuario());
                        throw new SeguridadException(CatalogoError.ERROR_VALIDACION_XML_ACTIVIDAD_INGRESO_USUARIO);
                    }
                }
            }
        }
        return ingresos;
    }

    /**
     * Metodo que se encarga de consultar un Ingreso de usuario en el cache por idIngresoUsuario
     * 
     * @param idIngresoUsuario
     * @author giovanni.velandia
     */
    public boolean consultarCacheAutorizaionXIdIngresoUsuario(Integer idIngresoUsuario) {
        if (idIngresoUsuario != null) {
            if (cacheAutorizaciones.containsKey(idIngresoUsuario)) {
                return true;
            }

        } else {
            return true;
        }
        return false;
    }

    /**
     * Obtiene una instancia del procesador de xml con una configuracion personalizada para la deserializacion y
     * 
     * serializacion de una lista de objetos de tipo InfoAutorizacion asignando alias a ciertas clases para simplificar algunos tags
     * 
     * @return instancia de Xstream
     */
    public XStream obtenerXStremInfoAutorizacion() {
        XStream xstream = new XStream();
        xstream.alias("InfoAutorizacion", InfoAutorizacion.class);
        xstream.alias("actividadIngreso", List.class);
        DateConverter dateConverter = new DateConverter("yyyy-MM-dd HH:mm:ss", new String[] {}, TimeZone.getDefault());
        xstream.registerConverter(dateConverter);
        return xstream;
    }

}
