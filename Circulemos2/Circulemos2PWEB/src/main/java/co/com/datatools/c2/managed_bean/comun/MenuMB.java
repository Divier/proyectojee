package co.com.datatools.c2.managed_bean.comun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
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
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.managed_bean.login.AdminCuentaUsuarioMB;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto;
import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto.EstadoAutenticacion;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.MenuDto;
import co.com.datatools.seguridad.interfaces.IRAutorizacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.web.mb.AutenticacionBean;
import co.com.datatools.seguridad.web.webflow.Breadcrumb;

/**
 * MB a utilizar para implementar la generacion dinamica del menu de C2
 * 
 * @author felipe.martinez
 * @author giovanni.velandia (Mod 03-03-2016)
 */
@ManagedBean
@SessionScoped
public class MenuMB extends AbstractC2ManagedBean {

    private final static Logger LOGGER = Logger.getLogger(MenuMB.class.getName());

    private static final long serialVersionUID = 1L;

    @EJB
    private IRAutorizacion autorizacionEjb;
    private List<MenuDto> menuDtos;
    private List<MenuDto> menuTempDtos;

    private String nombreAplicacion;

    @ManagedProperty(value = "#{adminCuentaUsuarioMB}")
    private AdminCuentaUsuarioMB adminCuentaUsuarioMB;

    @ManagedProperty(value = "#{fachadaCatalogosMB}")
    private FachadaCatalogosMB fachadaCatalogosMB;

    @EJB
    private IRSeguridadCirculemos seguridadCirculemosEjb;

    private static final String URL_CAMBIAR_PASS = "/app/" + ConstantesSeguridad.RECURSO_MAIN + "?stateId=cambioPw";
    private AutenticacionBean autenticador;

    /**
     * Constantes utilizados para la miga de pan
     */
    private static final String CONTEXT_KEY = "breadcrumb";
    private static final String ICON_HOME = "ui-icon-home";
    private static final String HOME_VALUE = "Inicio";

    public MenuMB() {
        super();
        autenticador = new AutenticacionBean();
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

    public void actualizarMenuSesion(List<String> nombresRoles) {

        AutenticacionBean autenticacionBean = findSessionObject(AutenticacionBean.class,
                ConstantesSeguridad.NombresManagedBeans.AUTENTICACION_BEAN);

        menuTempDtos = new ArrayList<MenuDto>();

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        String nombreAplicacion = servletContext
                .getInitParameter(ConstantesSeguridad.NombresPropiedades.ID_APLICACION_PROP);
        if (autenticacionBean != null && autenticacionBean.getUsuario() != null) {
            menuTempDtos = autorizacionEjb.consultarRecursosMenu(nombreAplicacion);
        }

        menuDtos = new ArrayList<MenuDto>();

        if (menuTempDtos != null && !menuTempDtos.isEmpty()) {
            // Agregar opciones al menubar de acuerdo a los permisos
            boolean recursoPermitido = false;

            // Menus padres
            for (MenuDto menuDto : menuTempDtos) {
                recursoPermitido = false;

                // Si tiene permiso de ingresar al recurso, agregarla al menu
                recursoPermitido = autorizacionEjb.esRecursoPermitidoRoles(nombreAplicacion, nombresRoles,
                        menuDto.getRecurso().getNombreRecurso());
                if (recursoPermitido) {
                    menuDtos.add(menuDto);
                }

            }

            // Menus hijos
            for (MenuDto menuDto : menuDtos) {

                List<MenuDto> menuHijos = menuDto.getSubmenu();
                if (menuHijos != null && !menuHijos.isEmpty()) {
                    ArrayList<MenuDto> hijosBorrables = new ArrayList<MenuDto>();
                    for (MenuDto menuHijo : menuHijos) {
                        recursoPermitido = false;
                        // Si tiene permiso de ingresar al recurso, agregarla al menu
                        recursoPermitido = autorizacionEjb.esRecursoPermitidoRoles(nombreAplicacion, nombresRoles,
                                menuHijo.getRecurso().getNombreRecurso());
                        if (!recursoPermitido) {
                            hijosBorrables.add(menuHijo);
                        }
                    }
                    menuDto.getSubmenu().removeAll(hijosBorrables);

                }

            }

            // Menus nietos
            for (MenuDto menuDto : menuDtos) {

                List<MenuDto> menuHijos = menuDto.getSubmenu();
                if (menuHijos != null && !menuHijos.isEmpty()) {
                    for (MenuDto menuHijo : menuHijos) {

                        List<MenuDto> menuNietos = menuHijo.getSubmenu();
                        if (menuNietos != null && !menuNietos.isEmpty()) {
                            ArrayList<MenuDto> nietosBorrables = new ArrayList<MenuDto>();
                            for (MenuDto menuNieto : menuNietos) {
                                recursoPermitido = false;
                                // Si tiene permiso de ingresar al recurso, agregarla al menu
                                recursoPermitido = autorizacionEjb.esRecursoPermitidoRoles(nombreAplicacion,
                                        nombresRoles, menuNieto.getRecurso().getNombreRecurso());
                                if (!recursoPermitido) {
                                    nietosBorrables.add(menuNieto);
                                }
                            }
                            menuHijo.getSubmenu().removeAll(nietosBorrables);
                        }

                    }

                }

            }
        }

    }

    /**
     * Se encarga de registrar el ingreso exitoso del usuario y a la vez evaluar: si el usuario esta bloqueado pero debe levantarse el bloqueo y si el
     * usuario tiene password temporal para que solicite cambio, tambien cierra otra sesion que puede encontrarse abierta
     */
    public boolean validarEstadoPassword() {

        ResultadoAutenticacionDto resultadoAutenticacionDto = autenticador.validarEstadoPassword(nombreAplicacion,
                getUsuarioSesion(), getIp());
        // usuarioDto = resultadoAutenticacionDto.getUsuario();

        actualizarMenuSesion(resultadoAutenticacionDto.getRoles());

        if (!resultadoAutenticacionDto.getEstadoAutenticacion().equals(EstadoAutenticacion.OK)) {

            EstadoAutenticacion estadoAutenticacion = resultadoAutenticacionDto.getEstadoAutenticacion();
            if (estadoAutenticacion.equals(EstadoAutenticacion.PASS_TEMPORAL)
                    || estadoAutenticacion.equals(EstadoAutenticacion.PASS_VENCIDO)) {
                adminCuentaUsuarioMB.setLogin(resultadoAutenticacionDto.getUsuario().getLogin());
                adminCuentaUsuarioMB.setMostrarFormulario(true);
                adminCuentaUsuarioMB.setUsuarioDto(resultadoAutenticacionDto.getUsuario());
                if (estadoAutenticacion.equals(EstadoAutenticacion.PASS_VENCIDO)) {
                    adminCuentaUsuarioMB.setPwVencido(true);
                }

                HttpServletRequest httpRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();
                HttpServletResponse httpResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                        .getExternalContext().getResponse();
                String url = httpRequest.getContextPath() + URL_CAMBIAR_PASS;
                try {
                    httpResponse.sendRedirect(url);
                } catch (IOException e) {
                    LOGGER.error("Error realizando el redirect a la url inicial: " + url + ". ", e);
                }
            }
        } else {

            // Agregar el usuario_persona a la sesion
            addSessionObject(ConstantesManagedBean.OBJ_USUARIO_PERSONA_AUTENTICA, validarUsuarioAplicativo().get(0));
            // Redireccionar en caso de que haya una url de inicio a la cual ir de un acceso sin sesion
            // valida realizado con anterioridad
            final String urlInicial = findSessionObject(String.class, ConstantesManagedBean.URL_INICIAL);
            if (!StringUtils.isBlank(urlInicial) && !urlInicial.contains(ConstantesManagedBean.PAGINA_MAIN)) {
                HttpServletResponse httpRs = (HttpServletResponse) FacesContext.getCurrentInstance()
                        .getExternalContext().getResponse();
                try {
                    httpRs.sendRedirect(urlInicial);
                } catch (IOException e) {
                    LOGGER.error("Error realizando el redirect a la url inicial: " + urlInicial + ". ", e);
                }
            }

            inicializarOrganismo();
            addSessionObject(ConstantesManagedBean.NOMBRE_OBJ_LOGIN, autenticador.getUsuario().getLogin());
            // Ingreso de roles usuario
            UsuarioDetalleDto usuarioDetalleDto = (UsuarioDetalleDto) autenticador.getUsuario();
            addSessionObject(ConstantesManagedBean.NOMBRE_OBJ_DETALLE_USU, usuarioDetalleDto);
        }
        return true;
    }

    /**
     * Se encarga de subir el organismo de transito y el pais a session
     * 
     * @author giovanni.velandia
     * 
     */
    private void inicializarOrganismo() {

        // Organismo de Tránsito
        OrganismoTransitoDTO organismoTransitoDTO = seguridadCirculemosEjb.obtenerOrganismoTransitoUsuario();

        addSessionObject(ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO, organismoTransitoDTO);
        LOGGER.infov("Se agrega {0} a la session", ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
        addSessionObject(ConstantesManagedBean.NOMBRE_OBJ_PAIS, seguridadCirculemosEjb.obtenerPais());
        LOGGER.infov("Se agrega {0} a la session", ConstantesManagedBean.NOMBRE_OBJ_PAIS);
    }

    /**
     * Metodo que se encarga de validar la existencia del usuario en el aplicativo de lo contrario dara un erro de autorizacion 500
     * 
     * @return
     */
    private List<UsuarioPersonaDTO> validarUsuarioAplicativo() {
        // validar que el usuario de seguridad este en la base de datos de circulemos
        UsuarioPersonaDTO usuarioPersonaDTO = new UsuarioPersonaDTO();
        UsuarioDetalleDto usuarioDetalleDto = new UsuarioDetalleDto();
        usuarioDetalleDto.setLogin(getUsuarioSesion());
        usuarioPersonaDTO.setUsuario(usuarioDetalleDto);
        return seguridadCirculemosEjb.consultarUsuarioPersona(usuarioPersonaDTO);
    }

    public List<MenuDto> getMenuDtos() {
        return menuDtos;
    }

    public void setMenuDtos(List<MenuDto> menuDtos) {
        this.menuDtos = menuDtos;
    }

    private String getIp() {
        return getHttpRequest().getRemoteAddr();
    }

    public AdminCuentaUsuarioMB getAdminCuentaUsuarioMB() {
        return adminCuentaUsuarioMB;
    }

    public void setAdminCuentaUsuarioMB(AdminCuentaUsuarioMB adminCuentaUsuarioMB) {
        this.adminCuentaUsuarioMB = adminCuentaUsuarioMB;
    }

    public FachadaCatalogosMB getFachadaCatalogosMB() {
        return fachadaCatalogosMB;
    }

    public void setFachadaCatalogosMB(FachadaCatalogosMB fachadaCatalogosMB) {
        this.fachadaCatalogosMB = fachadaCatalogosMB;
    }

    /**
     * Obtiene la miga de pan de circulemos a partir del menu
     * 
     * @return Modelo de menu
     * @author julio.pinzon 2016-09-15
     */
    public MenuModel getModel() {
        MenuModel model = new DefaultMenuModel();
        DefaultMenuItem item = new DefaultMenuItem(HOME_VALUE);
        item.setUrl("/");
        item.setIcon(ICON_HOME);
        model.addElement(item);
        RequestContext requestContext = RequestContextHolder.getRequestContext();
        if (requestContext != null) {
            // Obtiene los recursos que estan presentes en la ejecucion del flujo actual
            @SuppressWarnings("unchecked")
            LinkedList<Breadcrumb> breadcrumbs = (LinkedList<Breadcrumb>) requestContext.getConversationScope()
                    .get(CONTEXT_KEY);
            if (breadcrumbs != null) {
                List<MenuDto> menus = new ArrayList<>();
                // Busca los menus asociados a los recursos actuales del flujo
                for (Breadcrumb breadcrumb : breadcrumbs) {
                    List<MenuDto> menusEstado = menusMigaPan(breadcrumb.getRecurso());
                    Collections.reverse(menusEstado);
                    menus.addAll(menusEstado);
                }
                // Adiciona al modelo los menus encontrados para que se muestren en la miga de pan
                for (MenuDto menuDto : menus) {
                    item = new DefaultMenuItem(menuDto.getLabel());
                    item.setOnclick("return false;");
                    model.addElement(item);
                }
            }
        }
        return model;
    }

    /**
     * Encuentra la herencia de menus que coinciden con el recurso que se esta accediendo
     * 
     * @param recursoActual
     * @return Lista de menus
     * @author julio.pinzon 2016-09-15
     */
    private List<MenuDto> menusMigaPan(String recursoActual) {
        List<MenuDto> menus = new ArrayList<>();
        for (MenuDto menuDto : menuDtos) {
            if (encontrarMenuMigaPan(recursoActual, menuDto, menus) != null) {
                menus.add(menuDto);
                break;
            }
        }
        return menus;
    }

    /**
     * Agrega los menus adecuados a la lista para construir la miga de pan
     * 
     * @param recursoActual
     * @param menuDto
     * @param menus
     * @return Menu encontrado
     * @author julio.pinzon 2016-09-15
     */
    private MenuDto encontrarMenuMigaPan(String recursoActual, MenuDto menuDto, List<MenuDto> menus) {
        MenuDto menuEncontrado = null;

        if (menuDto.getRecurso().getNombreRecurso().equals(recursoActual)) {
            menuEncontrado = menuDto;
        } else {
            for (MenuDto subMenuDto : menuDto.getSubmenu()) {
                menuEncontrado = encontrarMenuMigaPan(recursoActual, subMenuDto, menus);
                if (menuEncontrado != null) {
                    menus.add(subMenuDto);
                    break;
                }
            }
        }
        return menuEncontrado;
    }

}
