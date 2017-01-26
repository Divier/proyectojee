package co.com.datatools.seguridad.mb.general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto;
import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto.EstadoAutenticacion;
import co.com.datatools.seguridad.dto.autorizacion.MenuDto;
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.interfaces.IRAutorizacion;
import co.com.datatools.seguridad.util.AbstractSeguridadManagedBean;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.web.mb.AutenticacionBean;

@ManagedBean(name = ConstantesSeguridad.NombresManagedBeans.MENU_SESION_BEAN)
@SessionScoped
public class MenuSesionSeguridadMB extends AbstractSeguridadManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(MenuSesionSeguridadMB.class.getName());

    private static final String URL_CAMBIAR_PASS = "/app/" + ConstantesSeguridad.RECURSO_MAIN + "?stateId=cambioPw";

    @ManagedProperty(value = "#{" + ConstantesSeguridad.NombresManagedBeans.AUTENTICACION_BEAN + "}")
    private AutenticacionBean autenticador;

    private static final String URL_PATTERN_SWF = "SeguridadWEB/app";

    @EJB
    private IRAutorizacion autorizacionEjb;

    private String htmlMenu = "";

    private String nombreAplicacion;

    @ManagedProperty(value = "#{cambioContrasenaMB}")
    private CambioContrasenaMB cambioContrasenaMB;

    public MenuSesionSeguridadMB() {
        super();
        logger.debug("MenuSesionSeguridadMB.MenuSesionSeguridadMB()");
    }

    @PostConstruct
    public void init() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        nombreAplicacion = servletContext.getInitParameter(ConstantesSeguridad.NombresPropiedades.ID_APLICACION_PROP);
        addSessionObject(ConstantesSeguridad.NombresManagedBeans.AUTENTICACION_BEAN, autenticador);

        // validar el estado del password que ingreso al sistema
        if (!validarEstadoPassword()) {
            try {
                getFacesContext().getExternalContext().redirect(URL_CAMBIAR_PASS);
            } catch (IOException e) {
                // No debe ocurrir
                e.printStackTrace();
            }
        }
    }

    public String htmlMenu() {
        if (htmlMenu.equals(""))
            actualizarMenuSesion(null);
        return htmlMenu;
    }

    public void actualizarMenuSesion(List<String> nombresRoles) {
        List<MenuDto> opcionesMenuPpal = new ArrayList<MenuDto>();

        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String nombreAplicacion = context.getInitParameter(ConstantesSeguridad.NombresPropiedades.ID_APLICACION_PROP);

        StringBuilder html = new StringBuilder();
        html.append("<div class=\"navbar navbar-default navbar-static-top\" role=\"navigation\">");
        html.append("<div class=\"container\">");
        html.append("<div class=\"navbar-header\">");
        html.append("<button data-target=\"#menu-navbar-collapse\" data-toggle=\"collapse\" type=\"button\" class=\"navbar-toggle\">");
        html.append("<span class=\"sr-only\">Toggle navigation</span>");
        html.append("<span class=\"icon-bar\"></span>");
        html.append("<span class=\"icon-bar\"></span>");
        html.append("<span class=\"icon-bar\"></span>");
        html.append("</button>");

        if (nombresRoles != null) {
            // Menu basado en los roles del usuario
            // Obtener las opciones del menu principal para la aplicacion
            opcionesMenuPpal = autorizacionEjb.consultarRecursosMenu(nombreAplicacion);

            /*
             * for (MenuDto menuDto : opcionesMenuPpal) { if (menuDto.getRecurso().getNombreRecurso().equals(ConstantesSeguridad.RECURSO_MAIN)) {
             * boolean recursoPermitido = autorizacionEjb.esRecursoPermitidoRoles(nombreAplicacion, nombresRoles,
             * menuDto.getRecurso().getNombreRecurso()); if (recursoPermitido) { html.append("<a class=\"navbar-brand\" href=\"" + "/" +
             * URL_PATTERN_SWF + "/" + menuDto.getRecurso().getNombreRecurso() + "\">" + menuDto.getLabel() + "</a>"); break; } } }
             */
        }

        html.append("</div>");
        html.append("<div class=\"navbar-collapse collapse\" id=\"menu-navbar-collapse\">");

        if (nombresRoles != null) {
            // Menu basado en los roles del usuario
            // Obtener las opciones del menu principal para la aplicacion
            MenuDto menuMiCuenta = null;
            // Agregar opciones al menubar de acuerdo a los permisos
            boolean recursoPermitido = false;

            if (opcionesMenuPpal.size() > 0) {
                html.append("<ul class=\"nav navbar-nav\">");
            }
            for (MenuDto menuDto : opcionesMenuPpal) {
                // Si tiene permiso de ingresar al recurso, agregarla al menu
                recursoPermitido = autorizacionEjb.esRecursoPermitidoRoles(nombreAplicacion, nombresRoles, menuDto
                        .getRecurso().getNombreRecurso());
                if (recursoPermitido) {
                    if (!menuDto.getRecurso().getNombreRecurso()
                            .equalsIgnoreCase(ConstantesSeguridad.RECURSO_MI_CUENTA)) {
                        if (!menuDto.getRecurso().getNombreRecurso().equals(ConstantesSeguridad.RECURSO_MAIN)) {
                            Collection<OperacionDto> operacionesPermitidasRecurso = autorizacionEjb
                                    .consultarOperacionesPermitidasRoles(nombreAplicacion, nombresRoles, menuDto
                                            .getRecurso().getNombreRecurso());
                            html.append(construirHtmlMenu(menuDto, operacionesPermitidasRecurso));
                        }
                    } else {
                        // Recurso Mi Cuenta
                        menuMiCuenta = menuDto;
                    }
                }
            }
            if (opcionesMenuPpal.size() > 0) {
                html.append("</ul>");
            }
            // Opciones a la derecha del menu
            html.append("<ul class=\"nav navbar-nav navbar-right\">");
            html.append("<li class=\"dropdown\">");
            html.append(" <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"
                    + getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_opciones")
                    + "<b class=\"caret\"></b></a>");
            html.append(" <ul class=\"dropdown-menu\">");

            if (menuMiCuenta != null) {
                // Boton mi cuenta
                html.append("<li>");
                html.append("<a href=\"" + "/" + URL_PATTERN_SWF + "/" + menuMiCuenta.getRecurso().getNombreRecurso()
                        + "\">" + menuMiCuenta.getLabel() + ": " + getLogin() + "</a>");
                html.append("</li>");
            }
            // Separador
            html.append("<li class=\"divider\"></li>");
            // Boton logout
            html.append("<li>");
            html.append("<a href=\"" + "/" + URL_PATTERN_SWF + "/" + ConstantesSeguridad.RECURSO_MAIN
                    + "?stateId=logout\">"
                    + getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("boton_logout") + "</a>");
            html.append("</li>");

            html.append("</ul>");
            html.append("</li>");
            html.append("</ul>");
            html.append("</div>");
            html.append("</div>");
            html.append("</div>");
        } else {

            // Boton logout
            html.append("<ul class=\"nav navbar-nav\">");
            html.append("<li>");
            html.append("<a href=\"" + "/" + URL_PATTERN_SWF + "/" + ConstantesSeguridad.RECURSO_MAIN
                    + "?stateId=logout\">"
                    + getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("boton_logout") + "</a>");
            html.append("</li>");
            html.append("</ul>");
            html.append("</div>");
            html.append("</div>");
            html.append("</div>");
        }
        htmlMenu = html.toString();
    }

    private String construirHtmlMenu(MenuDto menuDto, Collection<OperacionDto> operacionesPermitidasRecurso) {
        String stateId;
        String cadenaParametro;
        StringBuilder html = new StringBuilder();
        // Crear su submenu
        if (menuDto.getSubmenu() != null && menuDto.getSubmenu().size() > 0) {
            html.append("<li class=\"dropdown\">");
            html.append(" <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">" + menuDto.getLabel()
                    + "<b class=\"caret\"></b></a>");
            html.append(" <ul class=\"dropdown-menu\">");

            for (MenuDto submenuDto : menuDto.getSubmenu()) {
                stateId = "";
                cadenaParametro = "";
                stateId = submenuDto.obtenerValorAtributo("stateId");
                if (!stateId.equals("")) {
                    // Tiene una operacion, validar si tiene permiso a esa operacion
                    for (OperacionDto operacionDto : operacionesPermitidasRecurso) {
                        if (stateId.equals(operacionDto.getNombreOperacion())) {
                            cadenaParametro = "?stateId=" + stateId;
                            html.append("<li><a href=\"" + "/" + URL_PATTERN_SWF + "/"
                                    + submenuDto.getRecurso().getNombreRecurso() + cadenaParametro + "\">"
                                    + submenuDto.getLabel() + "</a></li>");
                            break;
                        }
                    }
                } else {
                    // No tiene operacion, se agrega opcion ya que se permite "ingresar"
                    html.append("<li><a href=\"" + "/" + URL_PATTERN_SWF + "/"
                            + submenuDto.getRecurso().getNombreRecurso() + cadenaParametro + "\">"
                            + submenuDto.getLabel() + "</a></li>");
                }
            }
            html.append("</ul>");
            html.append("</li>");
        } else {
            html.append("<li><a href=\"" + "/" + URL_PATTERN_SWF + "/" + menuDto.getRecurso().getNombreRecurso()
                    + "\">" + menuDto.getLabel() + "</a></li>");
        }
        return html.toString();
    }

    /**
     * Se encarga de registrar el ingreso exitoso del usuario y a la vez evaluar: si el usuario esta bloqueado pero debe levantarse el bloqueo y si el
     * usuario tiene password temporal para que solicite cambio, tambien cierra otra sesion que puede encontrarse abierta
     */
    public boolean validarEstadoPassword() {
        ResultadoAutenticacionDto resultadoAutenticacionDto = autenticador.validarEstadoPassword(nombreAplicacion,
                getUsuarioSesion(), getIp());

        actualizarMenuSesion(resultadoAutenticacionDto.getRoles());

        if (!resultadoAutenticacionDto.getEstadoAutenticacion().equals(EstadoAutenticacion.OK)) {

            EstadoAutenticacion estadoAutenticacion = resultadoAutenticacionDto.getEstadoAutenticacion();
            if (estadoAutenticacion.equals(EstadoAutenticacion.PASS_TEMPORAL)
                    || estadoAutenticacion.equals(EstadoAutenticacion.PASS_VENCIDO)) {
                cambioContrasenaMB.setLogin(resultadoAutenticacionDto.getUsuario().getLogin());
                cambioContrasenaMB.setMostrarFormulario(true);
                if (estadoAutenticacion.equals(EstadoAutenticacion.PASS_VENCIDO)) {
                    cambioContrasenaMB.setPwVencido(true);
                }

                HttpServletRequest httpRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();
                HttpServletResponse httpResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                        .getExternalContext().getResponse();
                String url = httpRequest.getContextPath() + URL_CAMBIAR_PASS;
                try {
                    httpResponse.sendRedirect(url);
                } catch (IOException e) {
                    logger.error("Error realizando el redirect a la url inicial: " + url + ". ", e);
                }
            }
        } else {
            final String urlInicial = findSessionObject(String.class, ConstantesManagedBean.NOMBRE_OBJ_URL_INICIAL);
            if (!StringUtils.isBlank(urlInicial)) {
                HttpServletResponse httpRs = (HttpServletResponse) FacesContext.getCurrentInstance()
                        .getExternalContext().getResponse();
                try {
                    httpRs.sendRedirect(urlInicial);
                } catch (IOException e) {
                    throw new RuntimeException("Error realizando el redirect a la url: " + urlInicial + " - "
                            + e.getMessage(), e);
                }
            }
        }
        return true;
    }

    private String getIp() {
        return getHttpRequest().getRemoteAddr();
    }

    public CambioContrasenaMB getCambioContrasenaMB() {
        return cambioContrasenaMB;
    }

    public void setCambioContrasenaMB(CambioContrasenaMB cambioContrasenaMB) {
        this.cambioContrasenaMB = cambioContrasenaMB;
    }

    public AutenticacionBean getAutenticador() {
        return autenticador;
    }

    public void setAutenticador(AutenticacionBean autenticador) {
        this.autenticador = autenticador;
    }
}
