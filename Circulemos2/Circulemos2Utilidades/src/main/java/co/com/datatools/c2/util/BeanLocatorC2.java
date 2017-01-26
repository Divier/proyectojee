package co.com.datatools.c2.util;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.jboss.logging.Logger;

/**
 * Servicio de localizacion de beans en el naming context local, para recursos de C2
 * 
 * @author felipe.martinez
 */
public class BeanLocatorC2 {

    private final static Logger LOGGER = Logger.getLogger(BeanLocatorC2.class.getName());

    public enum Beans {
        IRComparendo, //
        IRNotificacionComparendoSimit, //
        IRInconsistenciaRecaudo, //
        IRFachadaAdminGeneral, //
        IRProcesaComparendo, //
        IRSeguridadCirculemos, //
        IRParametro, //
        IRFachadaRecaudo, //
        IRFachadaComparendo, //
        IRSac, //
        IRFachadaSac, //
        IRFachadaFinanciacion, //
        IRFachadaIntegracionTerceros, //
        IRFachadaImpugnacion, //
        IRFachadaCoactivo, //
        IRFachadaNotificacionTerceros, //
        IRCargueMasivo, //
    }

    private static String version;

    // TODO dependiendo del volumen de beans a exponer, pasar este mapa a una base de datos
    public static final Map<String, String> BEANS = new HashMap<>();
    static {
        version = Utilidades.cargarVersionArtefacto();

        BEANS.put(Beans.IRComparendo.toString(), "java:global/Circulemo2EAR-" + version + "/ComparendoEJB-" + version
                + "/ComparendoEJB!co.com.datatools.c2.negocio.interfaces.IRComparendo");
        BEANS.put(Beans.IRNotificacionComparendoSimit.toString(), "java:global/Circulemo2EAR-" + version + "/SimitEJB-"
                + version + "/SimitEJB!co.com.datatools.c2.negocio.interfaces.IRNotificacionComparendoSimit");
        BEANS.put(Beans.IRFachadaAdminGeneral.toString(),
                "java:global/Circulemo2EAR-" + version + "/AdministracionEJB-" + version
                        + "/FachadaAdminGeneralEJB!co.com.datatools.c2.negocio.fachada."
                        + Beans.IRFachadaAdminGeneral.toString());
        BEANS.put(Beans.IRProcesaComparendo.toString(), "java:global/Circulemo2EAR-" + version + "/ComparendoEJB-"
                + version + "/ProcesaComparendoEJB!co.com.datatools.c2.negocio.interfaces.IRProcesaComparendo");
        BEANS.put(Beans.IRInconsistenciaRecaudo.toString(), "java:global/Circulemo2EAR-" + version + "/RecaudoEJB-"
                + version + "/InconsistenciaRecaudoEJB!co.com.datatools.c2.negocio.interfaces.IRInconsistenciaRecaudo");
        BEANS.put(Beans.IRSeguridadCirculemos.toString(), "java:global/Circulemo2EAR-" + version + "/AdministracionEJB-"
                + version + "/SeguridadCirculemosEJB!co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos");
        BEANS.put(Beans.IRParametro.toString(), "java:global/Circulemo2EAR-" + version + "/AdministracionEJB-" + version
                + "/ParametroEJB!co.com.datatools.c2.negocio.interfaces.IRParametro");
        BEANS.put(Beans.IRFachadaRecaudo.toString(), "java:global/Circulemo2EAR-" + version + "/RecaudoEJB-" + version
                + "/FachadaRecaudoEJB!co.com.datatools.c2.negocio.fachada.IRFachadaRecaudo");
        BEANS.put(Beans.IRFachadaComparendo.toString(),
                "java:global/Circulemo2EAR-" + version + "/ComparendoEJB-" + version
                        + "/FachadaComparendoEJB!co.com.datatools.c2.negocio.fachada."
                        + Beans.IRFachadaComparendo.toString());
        BEANS.put(Beans.IRSac.toString(), "java:global/Circulemo2EAR-" + version + "/SacEJB-" + version
                + "/SacEJB!co.com.datatools.c2.negocio.interfaces.IRSac");
        BEANS.put(Beans.IRFachadaSac.toString(), "java:global/Circulemo2EAR-" + version + "/SacEJB-" + version
                + "/FachadaSacEJB!co.com.datatools.c2.negocio.fachada.interfaces.IRFachadaSac");
        BEANS.put(Beans.IRFachadaFinanciacion.toString(),
                "java:global/Circulemo2EAR-" + version + "/FinanciacionEJB-" + version
                        + "/FachadaFinanciacionEJB!co.com.datatools.c2.negocio.fachada."
                        + Beans.IRFachadaFinanciacion.toString());
        BEANS.put(Beans.IRFachadaIntegracionTerceros.toString(),
                "java:global/Circulemo2EAR-" + version + "/IntegracionTercerosEJB-" + version
                        + "/FachadaIntegracionTercerosEJB!co.com.datatools.c2.negocio.fachada."
                        + Beans.IRFachadaIntegracionTerceros.toString());
        BEANS.put(Beans.IRFachadaImpugnacion.toString(), "java:global/Circulemo2EAR-" + version + "/ImpugnacionEJB-"
                + version + "/FachadaImpugnacionEJB!co.com.datatools.c2.negocio.fachada.IRFachadaImpugnacion");
        BEANS.put(Beans.IRFachadaCoactivo.toString(), "java:global/Circulemo2EAR-" + version + "/CoactivoEJB-" + version
        		+ "/FachadaCoactivoEJB!co.com.datatools.c2.negocio.fachada." + Beans.IRFachadaCoactivo.toString());
        BEANS.put(Beans.IRFachadaNotificacionTerceros.toString(),
                "java:global/Circulemo2EAR-" + version + "/NotificacionTercerosC2EJB-" + version
                        + "/FachadaNotificacionTercerosEJB!co.com.datatools.c2.negocio.interfaces."
                        + Beans.IRFachadaNotificacionTerceros.toString());
        BEANS.put(Beans.IRCargueMasivo.toString(), "java:global/Circulemo2EAR-" + version + "/AdministracionEJB-" + version
        		+ "/CargueMasivoEJB!co.com.datatools.c2.negocio.interfaces." + Beans.IRCargueMasivo.toString());
    }

    private BeanLocatorC2() {
        LOGGER.debug(BeanLocatorC2.class.getName());
    }

    /**
     * Localizacion de un bean parametrizado en el sistema
     * 
     * @param clazz
     *            tipo del recurso a localizar
     * @param idBean
     *            identificador del bean parametrizado
     * @return objecto JNDI encontrado
     */
    public static <T> T locate(Class<T> clazz, String idBean) {
        return lookup(clazz, BEANS.get(idBean));
    }

    /**
     * Look up JNDI del tipo especificado en el contexto local
     * 
     * @param clazz
     *            tipo del objeto que se quiere encontrar
     * @param jndiName
     *            nombre JNDI del recurso a buscar en el servidor local
     * @return recurso encontrado
     */
    private static <T> T lookup(Class<T> clazz, String jndiName) {
        Object bean = lookup(jndiName);
        return clazz.cast(PortableRemoteObject.narrow(bean, clazz));
    }

    /**
     * Look up JNDI generico sobre el contexto local
     * 
     * @param jndiName
     *            nombre nombre JNDI del recurso a buscar en el servidor local
     * @return recurso encontrado
     */
    public static Object lookup(String jndiName) {
        Context context = null;
        try {
            context = new InitialContext();
            return context.lookup(jndiName);
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot connect to bean: " + jndiName + " Reason: " + ex, ex.getCause());
        } finally {
            try {
                if (context != null) {
                    context.close();
                }
            } catch (NamingException ex) {
                throw new IllegalStateException("Cannot close InitialContext. Reason: " + ex, ex.getCause());
            }
        }
    }
}
