package co.com.datatools.seguridad.web.mb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.comun.InfoAutorizacion;
import co.com.datatools.seguridad.excepciones.SeguridadRuntimeException;
import co.com.datatools.seguridad.interfaces.IRAutorizacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad.NombresPropiedades.ModoAutorizacion;
import co.com.datatools.seguridad.web.excepciones.SesionInvalidaRuntimeException;
import co.com.datatools.seguridad.web.listener.NodosAutorizables;
import co.com.datatools.util.web.mb.AbstractManagedBean;

import com.sun.faces.component.visit.FullVisitContext;

/**
 * Recurso de AUTORIZACION<br>
 * Sesion Bean encargado de controlar la informacion y servicios asociados a la autorizacion de recursos del usuario en sesion
 * 
 * @author Felipe Martinez
 */
public class AutorizacionBean extends AbstractManagedBean {
    private final static Logger logger = Logger.getLogger(AutorizacionBean.class.getName());
    private static final long serialVersionUID = 1L;

    private static final String AUTORIZACION_EJB = "AutorizacionEJB!co.com.datatools.seguridad.interfaces.IRAutorizacion";

    private static final Pattern PATTERN_ESPACIOS = Pattern.compile("\\s+");
    private static final String ATTR_OPERACION = "styleClass";

    private transient IRAutorizacion autorizacionEjb;

    private NodosAutorizables nodosAutorizables;

    /**
     * Identificacion de la aplicacion sobre la q se validaran los recursos y operaciones
     */
    private String nombreAplicacion;

    /**
     * Modo de autorizacion a utilizar por esta instancia del bean de Autorizaciones
     */
    private ModoAutorizacion modoAutorizacion;

    private InfoAutorizacion errorAutorizacion;

    private String ubicacionJarSeguridadEjb;

    public AutorizacionBean() {
        super();
        logger.info("Nueva Instancia del Managed Bean:" + AutorizacionBean.class.getName());
        try {
            Properties propiedades = new Properties();
            propiedades.load(AutenticacionBean.class.getResourceAsStream("/artefacto.properties"));
            String version = propiedades.getProperty("version");
            logger.debug("Version= " + version);
            ubicacionJarSeguridadEjb = "SeguridadEAR-" + version + "/" + "SeguridadEJB-" + version + "/";

        } catch (IOException e) {
            logger.info("Error cargando el archivo de propiedades " + e.getMessage());
        }

        final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        this.nombreAplicacion = context.getInitParameter(ConstantesSeguridad.NombresPropiedades.ID_APLICACION_PROP);
        final String stringModoAut = context
                .getInitParameter(ConstantesSeguridad.NombresPropiedades.MODO_AUTORIZACION_PROP);
        this.modoAutorizacion = ModoAutorizacion.valueOf(stringModoAut);
        // buscar e instanciar la clase parametrizada en el web.xml con la definicion
        // de los nodos relevantes del view de la aplicacion
        final String claseNodosAutorizables = context
                .getInitParameter(ConstantesSeguridad.NombresPropiedades.CLASE_NODOS_AUTORIZABLES_PROP);
        if (claseNodosAutorizables == null) {
            throw new IllegalArgumentException(
                    "Configuracion incorrecta para el parametro de contexto co.com.datatools.seguridad.NODOS_AUTORIZABLES");
        } else {
            try {
                final Object newInstance = Class.forName(claseNodosAutorizables).newInstance();
                if (!(newInstance instanceof NodosAutorizables)) {
                    throw new IllegalArgumentException("La clase " + claseNodosAutorizables + " no es del tipo "
                            + NodosAutorizables.class.getName());
                }
                nodosAutorizables = (NodosAutorizables) newInstance;
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                throw new IllegalArgumentException("No es posible instanciar la clase " + claseNodosAutorizables, e);
            }

        }

    }

    /**
     * Este metodo se encuentra encargado de buscar todos los componententes hijos (busqueda completa del arbol) de nodo,<br>
     * para los cuales el atributo id empieza con prefijoOperaciones
     * 
     * @param viewRoot
     *            elemento padre donde se encontraran los componentes q graficos de operaciones
     * @param nombreRecurso
     *            identificador de recurso a validar
     * @return la misma variable nodo, sin los componentes (operaciones) q no tiene permiso a ejecutar el usuario de la aplicacion
     */
    public UIComponent removerOperaciones(final UIViewRoot viewRoot, final String nombreRecurso) {
        final UIComponent nodo = encontrarComponente(viewRoot, nodosAutorizables.getContentId());

        if (nodo == null) {
            logger.debugv("No se encuentra nodo principal id:{0} para remover componentes",
                    nodosAutorizables.getContentId());
            return null;
        }
        logger.debug("Procesando UIComponent id=" + nodo.getId()
                + " remover operaciones no permitidas para recurso id=" + nombreRecurso);

        final String prefijoOperaciones = nodosAutorizables.getPrefijoOperaciones();

        Collection<OperacionDto> operaciones = null;
        AutenticacionBean autenticacion = findSessionObject(AutenticacionBean.class,
                ConstantesSeguridad.NombresManagedBeans.AUTENTICACION_BEAN);
        if (autenticacion == null || autenticacion.getUsuario() == null) {
            // Buscar las accciones permitidas para el recurso actual, cuando no hay usuario autenticado
            operaciones = getAutorizacionEjb().consultarOperacionesPermitidasSinAutenticacion(getNombreAplicacion(),
                    nombreRecurso);
        } else {
            // Buscar para el usuario autenticado cuales son las acciones permitidas para el recurso actual de acuerdo a su rol...
            operaciones = getAutorizacionEjb().consultarOperacionesPermitidasUsuario(getNombreAplicacion(),
                    autenticacion.getUsuario().getLogin(), nombreRecurso);
        }

        final List<WrapperComponenteOperacion> uiOperaciones = encontrarOperaciones(nodo, prefijoOperaciones);
        if (!uiOperaciones.isEmpty()) {
            logger.debug("Se encontraron " + uiOperaciones.size() + " componentes con el prefijo " + prefijoOperaciones);
            OperacionDto opDto = new OperacionDto();
            for (WrapperComponenteOperacion operacion : uiOperaciones) {
                opDto.setNombreOperacion(operacion.nombreOperacion.replaceFirst(prefijoOperaciones, ""));
                if (!operaciones.contains(opDto)) {
                    operacion.component.getParent().getChildren().remove(operacion.component);
                }
            }
        }

        return nodo;
    }

    private UIComponent encontrarComponente(UIComponent root, final String id) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final UIComponent[] found = new UIComponent[1];
        root.visitTree(new FullVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if (component.getId() != null && component.getId().equals(id)) {
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;
            }
        });
        return found[0];
    }

    private List<WrapperComponenteOperacion> encontrarOperaciones(UIComponent root, final String prefijoOperaciones) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final List<WrapperComponenteOperacion> operaciones = new ArrayList<>();
        root.visitTree(new FullVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                final Object styleValue = component.getAttributes().get(ATTR_OPERACION);
                if (styleValue != null) {
                    String[] toks = PATTERN_ESPACIOS.split((String) styleValue);
                    for (String styleName : toks) {
                        if (styleName.startsWith(prefijoOperaciones)) {
                            operaciones.add(new WrapperComponenteOperacion(styleName, component));
                            // Termina de buscar operaciones en esta rama del componente,
                            // si hay mas componente autorizables dentro de esta rama, no seran procesados
                            return VisitResult.REJECT;
                        }
                    }
                }
                return VisitResult.ACCEPT;
            }
        });
        return operaciones;
    }

    /**
     * Valida si el recurso actual que solicita el usuario es permitido
     * 
     * @param nombreRecurso
     *            identificador de recurso a validar
     * 
     * @return permitido:true, no permitido: false
     */
    public boolean validarSolicitudRecurso(String nombreRecurso) {

        final Integer idIngresoUsuario = findSessionObject(Integer.class,
                ConstantesSeguridad.NOMBRE_ATRIBUTO_SESION_ID_INGRESO);

        /*
         * Verificar que el ingreso todavia exista en la lista de actividad del usuario que esta en trazabilidadEJB, si se encuentra el sistema no
         * hace nada, pero si no esta es por que el ingreso ya fue cerrado
         */
        boolean estaCache = getAutorizacionEjb().consultarCacheAutorizaionXIdIngresoUsuario(idIngresoUsuario);

        if (!estaCache) {
            throw new SesionInvalidaRuntimeException("Sesion Invalidad");
        }

        final AutenticacionBean autenticacion = findSessionObject(AutenticacionBean.class,
                ConstantesSeguridad.NombresManagedBeans.AUTENTICACION_BEAN);
        boolean esValido = false;
        if (autenticacion == null || autenticacion.getUsuario() == null) {
            // Valida si el recurso es permitido sin autenticacion
            esValido = getAutorizacionEjb().esRecursoPermitidoSinAutenticacion(getNombreAplicacion(), nombreRecurso);
        } else {
            // Valida si el recurso es permitido de acuerdo al rol del usuario autenticado
            esValido = getAutorizacionEjb().esRecursoPermitidoUsuario(getNombreAplicacion(),
                    autenticacion.getUsuario().getLogin(), nombreRecurso);

        }

        if (idIngresoUsuario != null) {
            logger.info("Obtiene de la sesion el Id de ingreso= " + idIngresoUsuario);
            if (esValido) {
                errorAutorizacion = null;
                getAutorizacionEjb().registrarSolicitudAutorizacion(
                        idIngresoUsuario,
                        new InfoAutorizacion(nombreRecurso, ConstantesSeguridad.NOMBRE_OPERACION_INGRESO.toUpperCase(),
                                true));
            } else {
                errorAutorizacion = new InfoAutorizacion(nombreRecurso, null, false);
                getAutorizacionEjb().registrarSolicitudAutorizacion(idIngresoUsuario, errorAutorizacion);
            }
        }

        return esValido;
    }

    /**
     * Valida si la operacion actual que quiere realizar el usuario es permitida
     * 
     * @param nombreRecurso
     *            identificador de recurso a validar
     * @param nombreOperacion
     *            identificador de la operacion del recurso a validar
     * 
     * @return permitido:true, no permitido: false
     */
    public boolean validarSolicitudOperacion(final String nombreRecurso, final String nombreOperacion) {

        final Integer idIngresoUsuario = findSessionObject(Integer.class,
                ConstantesSeguridad.NOMBRE_ATRIBUTO_SESION_ID_INGRESO);

        /*
         * Verificar que el ingreso todavia exista en la lista de actividad del usuario que esta en trazabilidadEJB, si se encuentra el sistema no
         * hace nada, pero si no esta es por que el ingreso ya fue cerrado
         */
        boolean estaCache = getAutorizacionEjb().consultarCacheAutorizaionXIdIngresoUsuario(idIngresoUsuario);

        if (!estaCache) {
            throw new SesionInvalidaRuntimeException("Sesion Invalidad");
        }

        if (!nombreOperacion.startsWith(nodosAutorizables.getPrefijoOperaciones())) {
            return true;
        }

        final String operacionNoPrefijo = nombreOperacion.replaceFirst(nodosAutorizables.getPrefijoOperaciones(), "");

        Collection<OperacionDto> operaciones = null;

        final AutenticacionBean autenticacion = findSessionObject(AutenticacionBean.class,
                ConstantesSeguridad.NombresManagedBeans.AUTENTICACION_BEAN);
        if (autenticacion == null || autenticacion.getUsuario() == null) {
            // Buscar las accciones permitidas para el recurso actual, cuando no hay usuario autenticado
            operaciones = getAutorizacionEjb().consultarOperacionesPermitidasSinAutenticacion(getNombreAplicacion(),
                    nombreRecurso);
        } else if (modoAutorizacion.equals(ConstantesSeguridad.NombresPropiedades.ModoAutorizacion.USUARIO)) {
            // Buscar para el usuario autenticado cuales son las acciones permitidas para el recurso actual de acuerdo a su rol...
            operaciones = getAutorizacionEjb().consultarOperacionesPermitidasUsuario(getNombreAplicacion(),
                    autenticacion.getUsuario().getLogin(), nombreRecurso);
        } else if (modoAutorizacion.equals(ConstantesSeguridad.NombresPropiedades.ModoAutorizacion.ROLES)) {
            throw new SeguridadRuntimeException("Funcionalidad pendiente por implementar");
        }

        if (operaciones.contains(new OperacionDto(operacionNoPrefijo))) {
            errorAutorizacion = null;
            getAutorizacionEjb().registrarSolicitudAutorizacion(idIngresoUsuario,
                    new InfoAutorizacion(nombreRecurso, operacionNoPrefijo, true));
            return true;
        } else {
            errorAutorizacion = new InfoAutorizacion(nombreRecurso, operacionNoPrefijo, false);
            getAutorizacionEjb().registrarSolicitudAutorizacion(idIngresoUsuario, errorAutorizacion);
            return false;
        }
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String idAplicacion) {
        if (this.nombreAplicacion != null) {
            throw new IllegalAccessError("El identificador de la aplicacion ya fue inicializado");
        }
        logger.info("Se asigna el identificador de aplicacion a manejar, idAplicacion= " + idAplicacion);
        this.nombreAplicacion = idAplicacion;
    }

    public InfoAutorizacion getErrorAutorizacion() {
        return errorAutorizacion;
    }

    public void setErrorAutorizacion(InfoAutorizacion errorAutorizacion) {
        this.errorAutorizacion = errorAutorizacion;
    }

    class WrapperComponenteOperacion {
        String nombreOperacion;
        UIComponent component;

        public WrapperComponenteOperacion(String nombreOperacion, UIComponent component) {
            super();
            this.nombreOperacion = nombreOperacion;
            this.component = component;
        }

        @Override
        public String toString() {
            return "WrapperComponenteOperacion {nombreOperacion=" + nombreOperacion + "}";
        }

    }

    public IRAutorizacion getAutorizacionEjb() {
        if (autorizacionEjb == null) {
            autorizacionEjb = lookupEjb(IRAutorizacion.class, ubicacionJarSeguridadEjb + AUTORIZACION_EJB);
        }
        return autorizacionEjb;
    }
}
