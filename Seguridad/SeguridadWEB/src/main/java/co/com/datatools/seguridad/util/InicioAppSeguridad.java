package co.com.datatools.seguridad.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.reporte.data.ConectorArchivosReporte;
import co.com.datatools.c2.reporte.jsf.ReportHandlerComponent;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Managed bean de aplicacion inicializado automaticamente para:
 * <ul>
 * <li>Mantener un cache de los ResourceBundle para cada locale solicitado por los clientes</li>
 * <li>Referencia a parametros globales</li>
 * </ul>
 * 
 * @author felipe.martinez
 * 
 */
@ApplicationScoped
@ManagedBean(eager = true)
public class InicioAppSeguridad extends AbstractManagedBean {

    private static final long serialVersionUID = 2775744452533372706L;
    private final static Logger logger = Logger.getLogger(InicioAppSeguridad.class.getName());

    public static final String NOMBRE_BEAN = "inicioAppSeguridad";

    private transient Map<Locale, ResourceBundle> resources = new HashMap<Locale, ResourceBundle>(4, 1.0f);

    private final String versionArtefacto = SeguridadUtilMB.cargarVersionArtefacto();

    @EJB
    private IRParametrosSeguridad parametrosEjb;

    @PostConstruct
    public void init() {
        logger.infov("Inicializando ApplicationBean {0}", this.getClass().getName());
        try {
            instaladorEjecutado(null);
        } catch (Exception e) {
            logger.debug(
                    "Solo debe suceder cuando la base de datos no existe y se despliega la apicacion (Pruebas Unitarias)",
                    e);
        }
        inicializarGeneradorReportes();
    }

    /**
     * Permite consultar si el servicio de instalacion ha sido ejecutado correctamente, este metodo puede ser llamado por fuera de contexto de faces
     * por lo tanto el acceso al servlet context se termina realizando a traves de <b>rq</b><br>
     * 
     * Una vez que el flag de configuracion del asistente pase a <b>true<b> no volvera a consultar el valor en al base de datos y lo mantendra como
     * constante de la aplicacion
     * 
     * @param rq
     *            request si se necesita, si es <b>null</b> busca el contexto a traves del FacesContext
     * @return true, la pagina de instalacion de la aplicacion ha sido ejecutada; false, lo contrario
     */
    public boolean instaladorEjecutado(HttpServletRequest rq) {
        Boolean valorFlag = false;
        ServletContext srvContext = null;
        if (rq != null) {
            valorFlag = (Boolean) rq.getServletContext().getAttribute(ConstantesManagedBean.NOMBRE_OBJ_FLAG_INSTALADOR);
            srvContext = rq.getServletContext();
        } else {
            valorFlag = super.findApplicationObject(Boolean.class, ConstantesManagedBean.NOMBRE_OBJ_FLAG_INSTALADOR);
            srvContext = super.getServletContext();
        }

        if (valorFlag == null || !valorFlag) {
            final boolean instalado = StringUtils.equalsIgnoreCase(
                    parametrosEjb.consultarValorParametroSeguridad(EnumParametro.INSTALADOR_EJECUTADO),
                    ConstantesSeguridad.VALOR_SI);
            srvContext.setAttribute(ConstantesManagedBean.NOMBRE_OBJ_FLAG_INSTALADOR, valorFlag);
            valorFlag = instalado;
        }
        logger.debugv("El asistente de instalacion de la aplicacion ha sido ejecutado? : {0}", valorFlag);
        return valorFlag;
    }

    public ResourceBundle getResourceBundle(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        assert (null != locale);
        ResourceBundle resourceBundle = resources.get(locale);
        if (resourceBundle == null) {
            resourceBundle = loadBundle(locale);

            logger.infov("ResourceBundle de negocio para Locale {0} fue cargado", locale);
            logger.debugv("Locale {0} contiene las llaves {1}", locale, resourceBundle.keySet());
            resources.put(locale, resourceBundle);

        }
        return resourceBundle;

    }

    public String getVersionArtefacto() {
        return versionArtefacto;
    }

    private ResourceBundle loadBundle(Locale locale) {
        return ResourceBundle.getBundle("Mensajes", locale, new SeguridadMultiplePropertiesControl());
    }

    public void inicializarGeneradorReportes() {
        final ConectorArchivosReporte conectorArchivosReporte = new ConectorArchivosReporte(
                "resource:co/com/datatools/seguridad/reportes");
        addApplicationObject(ReportHandlerComponent.NOMBRE_OBJ_CONECTOR_PLANTILLA, conectorArchivosReporte);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        resources = new HashMap<Locale, ResourceBundle>(4, 1.0f);
    }
}
