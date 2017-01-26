package co.com.datatools.seguridad.mb.opcionesmenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.component.menubutton.MenuButton;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.panelmenu.PanelMenu;
import org.primefaces.component.separator.UISeparator;
import org.primefaces.component.submenu.UISubmenu;
import org.primefaces.context.RequestContext;

import co.com.datatools.seguridad.dto.autorizacion.EdicionMenuDto;
import co.com.datatools.seguridad.dto.autorizacion.MenuDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.dto.comun.LlaveValorDto;
import co.com.datatools.seguridad.interfaces.IRCatalogosSeguridad;
import co.com.datatools.seguridad.interfaces.IRMenu;
import co.com.datatools.seguridad.mb.recursos.RecursosFL;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean que gestiona la pagina de administracion de menu
 * 
 * @author claudia.rodriguez
 */
@ManagedBean
@SessionScoped
public class AdminMenuMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(AdminMenuMB.class.getName());

    private static final String NOMBRE_BUNDLE_OPCIONES_MENU = "mensajesOpcionMenu";

    @EJB
    private IRCatalogosSeguridad catalogoSeguridadEjb;

    @EJB
    private IRMenu menuEjb;

    @ManagedProperty(value = "#{crearOpcionMenuMB}")
    private CrearOpcionMenuMB crearOpcionMenuMB;

    @ManagedProperty(value = "#{modificarOpcionMenuMB}")
    private ModificarOpcionMenuMB modificarOpcionMenuMB;

    @ManagedProperty(value = "#{ordenarOpcionesMenuMB}")
    private OrdenarOpcionesMenuMB ordenarOpcionesMenuMB;

    @ManagedProperty(value = "#{moverOpcionesMenuMB}")
    private MoverOpcionesMenuMB moverOpcionesMenuMB;

    /**
     * Map que mantiene el inventario de todas las opciones contenidas en el menu
     */
    private Map<String, WrapperOpcionMenu> wrapperOpciones = new HashMap<String, WrapperOpcionMenu>();

    private transient PanelMenu menuAplicacion;
    private transient Menubar menubar;
    private Map<String, String> lAplicaciones;
    private String idAplicacion;
    private List<MenuDto> opcionesMenuEliminadas = new ArrayList<MenuDto>();

    public void reset() {
        wrapperOpciones = new HashMap<String, WrapperOpcionMenu>();
        idAplicacion = null;
    }

    public AdminMenuMB() {
        logger.debug("AdminMenuMB::AdminMenuMB");
    }

    /**
     * Consulta las opciones del menu para la aplicacion seleccionada y construye la previsualizacion del menu de la aplicacion
     */
    public void consultarOpcionesMenu() {
        logger.debug("AdminMenuMB.consultarOpcionesMenu");
        wrapperOpciones = new HashMap<String, WrapperOpcionMenu>();
        menuAplicacion = new PanelMenu();
        menubar.getFacets().put("options", construirBotonAccionesMenu());
        List<MenuDto> opcionesExistentes = new ArrayList<MenuDto>();
        opcionesExistentes = menuEjb.consultarOpcionesMenu(Integer.valueOf(idAplicacion));
        for (MenuDto menuDto : opcionesExistentes) {// Solo trae las opciones principales con todo lo interno
            crearOpcionMenuMB.setIdParent(null);// limpiar idParent, por si queda con valor del anterior menudto
            if (menuDto.getMenuPadre() == null) {
                // Es una opcion raiz/principal
                crearMenuInicial(menuDto);
            }
        }
        crearOpcionMenuMB.setIdParent(null);
    }

    /**
     * Construye recursivamente una opcion de menu de la aplicacion con todos sus submenus relacionados
     * 
     * @param menuDto
     *            Dto de la opcion de menu raiz
     */
    public void crearMenuInicial(MenuDto menuDto) {
        WrapperOpcionMenu opcionSub = crearSubmenu(menuDto);
        if (menuDto.getSubmenu() != null) {
            for (MenuDto menuDto2 : menuDto.getSubmenu()) {
                crearOpcionMenuMB.setIdParent(opcionSub.getId());
                crearMenuInicial(menuDto2);
            }
        }
    }

    /**
     * Guarda la configuracion del menu y muestra un mensaje exitoso
     */
    public void guardarOpcionesMenu() {
        logger.debug("AdminMenuMB.guardarOpcionesMenu");
        /*
         * if (wrapperOpciones.size() == 0) { addInfoMessage(NOMBRE_BUNDLE_OPCIONES_MENU, "msg_requiere_opciones"); } else {
         */
        List<MenuDto> opcionesPersistir = new ArrayList<MenuDto>();
        for (Iterator<Entry<String, WrapperOpcionMenu>> iterator = wrapperOpciones.entrySet().iterator(); iterator
                .hasNext();) {
            Entry<String, WrapperOpcionMenu> opcion = iterator.next();
            WrapperOpcionMenu wrapOpcion = opcion.getValue();
            if (wrapOpcion.getPadre() == null) {
                construirMenuDto(wrapOpcion.getMenuDto(), wrapOpcion);
                opcionesPersistir.add(wrapOpcion.getMenuDto());
            }
        }
        EdicionMenuDto edicionMenuDto = new EdicionMenuDto();
        edicionMenuDto.setOpcionesMenu(opcionesPersistir);
        edicionMenuDto.setOpcionesMenuEliminadas(opcionesMenuEliminadas);
        menuEjb.registrarOpcionesMenu(edicionMenuDto);
        addInfoMessage(NOMBRE_BUNDLE_OPCIONES_MENU, "msg_menu_guardado");
        reset();
        // }
    }

    /**
     * Asigna todos los MenuDto hijos al menuDto padre enviado como parametro, a partir de los hijos en su correspondiente WrapperOpcionMenu
     * 
     * @param menuDto
     *            Dto raiz cuya jerarquia de submenus sera construida
     * @param wrapOpcion
     *            WrapperOpcionMenu de la opcion cuyo Dto es enviado
     */
    public void construirMenuDto(MenuDto menuDto, WrapperOpcionMenu wrapOpcion) {
        // Hijos
        if (wrapOpcion.getHijos() != null) {
            List<MenuDto> submenu = new ArrayList<MenuDto>();
            for (WrapperOpcionMenu hijo : wrapOpcion.getHijos()) {
                if (hijo.getHijos() != null) {
                    construirMenuDto(hijo.getMenuDto(), hijo);
                }
                hijo.getMenuDto().setMenuPadre(menuDto);
                submenu.add(hijo.getMenuDto());
            }
            menuDto.setSubmenu(submenu);
        }
    }

    /**
     * Construye el MenuButton de acciones para la menubar
     * 
     * @return MenuButton de acciones del menubar
     */
    private UIComponent construirBotonAccionesMenu() {
        logger.debug("AdminMenuMB.construirBotonAccionesMenu");
        MenuButton boton = new MenuButton();
        boton.setValue("Acciones");
        boton.setId("acciones-menuBar");

        final ExpressionFactory exprFactory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
        final ELContext elCtxt = FacesContext.getCurrentInstance().getELContext();

        final UIMenuItem newMenuItem = buildMenuItem(null, "Agregar Entrada", "ui-icon-plus");
        final MethodExpression methodExpr = exprFactory.createMethodExpression(elCtxt,
                "#{adminMenuMB.inicioAgregarMenuItem()}", Void.class, new Class[] {});

        newMenuItem.setUpdate("@form");
        newMenuItem.setActionExpression(methodExpr);
        boton.getChildren().add(newMenuItem);

        final UIMenuItem sortMenuItem = buildMenuItem(null, "Ordenar", "ui-icon-transfer-e-w");
        final MethodExpression mthExprSort = exprFactory.createMethodExpression(elCtxt,
                "#{adminMenuMB.inicioOrdenarHijosSubmenu('')}", Void.class, new Class[] {});

        sortMenuItem.setUpdate("@form :dialog-ordenar-submenu");
        sortMenuItem.setActionExpression(mthExprSort);
        boton.getChildren().add(sortMenuItem);

        return boton;
    }

    /**
     * Consulta la lista de aplicaciones existentes para visualizar en la pagina de administracion de menu
     */
    public void cargarListaAplicaciones() {
        logger.debug("AdminMenuMB.cargarListaAplicaciones");
        if (lAplicaciones == null) {
            lAplicaciones = new HashMap<>();
            lAplicaciones = catalogoSeguridadEjb.consultarAplicaciones();
        }
    }

    /**
     * Punto de entrada para desplegar formulario de creacion de menu item
     */
    public void inicioAgregarMenuItem() {
        logger.debug("AdminMenuMB::inicioAgregarMenuItem");
        RequestContext.getCurrentInstance().execute("PF('dlgCrearSubmenu').show()");
    }

    /**
     * Punto de entrada para desplegar formulario de creacion de un submenu con acciones asociado al padre especificado
     * 
     * @param idParent
     *            identificador del elemento padre dentro del indice de elementos de menu
     */
    public void inicioAgregarSubMenu(String idParent) {
        logger.debug("AdminMenuMB::inicioAgregarSubMenu");
        crearOpcionMenuMB.setIdParent(idParent);
        RequestContext.getCurrentInstance().execute("PF('dlgCrearSubmenu').show()");
    }

    /**
     * Se invoca desde la interfaz para agregar un submenu sobre una opcion especifica, instancia el MenuDto, le asigna los datos ingresados en el
     * formulario de creacion e invoca al metodo crearSubmenu con dicho Dto
     */
    public String agregarSubMenu() {
        logger.debug("AdminMenuMB::agregarSubMenu");
        if (crearOpcionMenuMB.getIdRecurso() == null) { // Mostrar mensaje requerido para el recurso asignado a la opcion
            ResourceBundle bundleGeneral = getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL);
            getFacesContext().addMessage("form-crear-submenu:recurso",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, bundleGeneral.getString("mensaje_requerido")));
        } else {
            // crear el menuDto a partir de los datos de creacion
            MenuDto menuDto = new MenuDto();
            menuDto.setLabel(crearOpcionMenuMB.getNombre());

            /*
             * Validamos que no se encuentren repetidos los atributos
             */
            for (int x = 0; x < crearOpcionMenuMB.getAtributos().size(); x++) {
                for (int y = 0; y < crearOpcionMenuMB.getAtributos().size(); y++) {
                    if (x != y) {
                        if (crearOpcionMenuMB.getAtributos().get(x).getId()
                                .equals(crearOpcionMenuMB.getAtributos().get(y).getId())) {
                            getFacesContext().addMessage(
                                    "form-crear-submenu:adicion:" + x + ":nombreAtr",
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                            NOMBRE_BUNDLE_OPCIONES_MENU).getString("msg_requerido_atributo")));
                            getFacesContext().addMessage(
                                    "form-crear-submenu:adicion:" + y + ":nombreAtr",
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                            NOMBRE_BUNDLE_OPCIONES_MENU).getString("msg_requerido_atributo")));
                            return null;
                        }
                    }
                }
            }

            menuDto.setAtributos(crearOpcionMenuMB.getAtributos());

            RecursoDto recursoAsignado = new RecursoDto();
            recursoAsignado.setIdRecurso(Integer.valueOf(crearOpcionMenuMB.getIdRecurso()));
            recursoAsignado.setNombreRecurso(crearOpcionMenuMB.getNombreRecurso());
            menuDto.setRecurso(recursoAsignado);
            crearSubmenu(menuDto);
            crearOpcionMenuMB.reset();
            recorrerMenuCreado();
            RequestContext.getCurrentInstance().execute("PF('dlgCrearSubmenu').hide()");
        }
        return null;
    }

    /**
     * Invoca al metodo construirWrapperOpcionMenu para obtener un WrapperOpcionMenu con el MenuDto enviado como parametro, lo agrega en los hijos del
     * WrapperOpcionMenu con Id contenido en crearOpcionMenuMB.idParent o directamente en el menuBar. Finalmente lo agrega al map:wrapperOpciones que
     * mantiene el inventario de todas las opciones de menu creadas
     * 
     * @param menuDto
     * @return
     */
    public WrapperOpcionMenu crearSubmenu(MenuDto menuDto) {
        WrapperOpcionMenu subMenu = null;
        if (crearOpcionMenuMB.getIdParent() != null) {
            logger.debug("Va a agregar submenu al idParent= " + crearOpcionMenuMB.getIdParent());
            subMenu = construirWrapperOpcionMenu(menuDto);
            subMenu.setPadre(wrapperOpciones.get(crearOpcionMenuMB.getIdParent()));
            List<UIComponent> children = wrapperOpciones.get(crearOpcionMenuMB.getIdParent()).getItemMenu()// aquiii
                    .getChildren();
            subMenu.getMenuDto().setOrden(children.size());
            children.add(children.size(), subMenu.getItemMenu());// lo pone en los items hijos
            wrapperOpciones.get(crearOpcionMenuMB.getIdParent()).getHijos().add(subMenu);
            wrapperOpciones.put(subMenu.getId(), subMenu);
            logger.debug("Agrega a wrapperOpciones: " + subMenu.getId() + " - " + subMenu.getItemMenu().getLabel());
        } else {
            subMenu = construirWrapperOpcionMenu(menuDto);
            menuAplicacion.getChildren().add(subMenu.getItemMenu());
            subMenu.getMenuDto().setOrden(menuAplicacion.getChildren().size());
            wrapperOpciones.put(subMenu.getId(), subMenu);
            logger.debug("Agrega a wrapperOpciones: " + subMenu.getId() + " - " + subMenu.getItemMenu().getLabel());
        }
        return subMenu;
    }

    /**
     * Instancia un WrapperOpcionMenu con su respectivo UISubmenu y le asigna el MenuDto enviado como parametro
     * 
     * @param menuDto
     *            Dto enviado para ser asignado al nuevo WrapperOpcionMenu
     * @return WrapperOpcionMenu creado
     */
    private WrapperOpcionMenu construirWrapperOpcionMenu(MenuDto menuDto) {
        UISubmenu subMenu = new UISubmenu();
        subMenu.setLabel(menuDto.getLabel());

        // Llave de un elemento dentro del menubar
        String hashCode = "_" + String.valueOf(subMenu.hashCode());
        subMenu.setId(hashCode);
        subMenu.getChildren().add(buildMenuAccionesItem(hashCode));

        // Crear el wrapper de la opcion de menu
        WrapperOpcionMenu wrapperOpcionMenu = new WrapperOpcionMenu();
        wrapperOpcionMenu.setId(hashCode);
        wrapperOpcionMenu.setItemMenu(subMenu);

        wrapperOpcionMenu.setMenuDto(menuDto);
        wrapperOpcionMenu.setHijos(new ArrayList<WrapperOpcionMenu>());
        return wrapperOpcionMenu;
    }

    /**
     * Abre el pop-up de busqueda de recursos que pueden ser asignados cuando se cre o modifica una opcion de menu
     * 
     * @param modificacion
     *            Indica si el pop-up se abre o no, desde el formualrio de modificacion de opcion de menu
     */
    public void abrirBusquedaRecursos(boolean modificacion) {
        logger.debug("adminMenuMB::abrirBusquedaRecursos");
        // Mostrar popup de busqueda, filtrando por la aplicacion seleccionada
        RecursosFL recursosFl = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);
        recursosFl.setIdAplicacion(idAplicacion);
        recursosFl.setAsignacionRecursoPadre(false);
        if (modificacion)
            recursosFl.setModificacionMenu(true);
        else
            recursosFl.setModificacionMenu(false);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgBusqueda').show();");

    }

    /**
     * Punto de entrada para desplegar el formulario que permite ordenar las opciones del submenu de la opcion seleccionada
     */
    public void inicioOrdenarHijosSubmenu(String idParent) {
        logger.debug("AdminMenuMB::inicioOrdenarHijosSubmenu");
        List<String> menuItemList = new ArrayList<String>();
        ordenarOpcionesMenuMB.setIdParent(idParent);
        List<UIComponent> submenus = null;
        if (idParent.equals("")) {
            // Ordenar las opciones de menuBar
            submenus = menuAplicacion.getChildren();
        } else {
            // Ordenar el submenu del menu con id especificado
            submenus = wrapperOpciones.get(idParent).getItemMenu().getChildren();
        }
        for (UIComponent uiComponent : submenus) {
            UISubmenu submenu = (UISubmenu) uiComponent;
            if (!submenu.getLabel().equals("Acciones"))
                menuItemList.add(submenu.getLabel());
        }
        ordenarOpcionesMenuMB.setMenuItemList(menuItemList);
        RequestContext.getCurrentInstance().update("dialog-ordenar-submenu");
        RequestContext.getCurrentInstance().execute("PF('dlgOrdenarSubmenu').show()");

    }

    /**
     * Coloca las opciones de menu de un submenu especifico en el orden definido por el usuario en la interfaz
     */
    public void ordenarHijosSubmenu() {
        logger.debug("AdminMenuMB::ordenarHijosSubmenu");
        List<String> listaOrdenada = ordenarOpcionesMenuMB.getMenuItemList();
        List<UIComponent> listaItemsOrdenados = new ArrayList<UIComponent>();
        if (ordenarOpcionesMenuMB.getIdParent().equals("")) {
            // Ordenar las opciones del menuBar
            for (String label : listaOrdenada) {
                for (UIComponent uiComponent : menuAplicacion.getChildren()) {
                    if (((UISubmenu) uiComponent).getLabel().equals(label)) {
                        listaItemsOrdenados.add(uiComponent);
                        break;
                    }
                }
            }
            // Cambiar el orden de los elementos en el menu bar
            for (int i = 0; i < listaItemsOrdenados.size(); i++) {
                final UIComponent uiComponent = listaItemsOrdenados.get(i);
                menuAplicacion.getChildren().remove(uiComponent);
                menuAplicacion.getChildren().add(uiComponent);
                wrapperOpciones.get(((UISubmenu) uiComponent).getId()).getMenuDto().setOrden(i + 1);
                wrapperOpciones.get(((UISubmenu) uiComponent).getId()).getMenuDto().setModificado(true);

            }
        } else {
            // Ordenar las opciones del submenu cuyo padre sea el menu con el id especificado
            List<UIComponent> submenus = new ArrayList<UIComponent>();
            submenus.addAll(wrapperOpciones.get(ordenarOpcionesMenuMB.getIdParent()).getItemMenu().getChildren());
            for (String label : listaOrdenada) {
                for (UIComponent uiComponent : submenus) {
                    if (((UISubmenu) uiComponent).getLabel().equals(label)) {
                        listaItemsOrdenados.add(uiComponent);
                        break;
                    }
                }
            }
            UIComponent itemAcciones = submenus.get(0);
            wrapperOpciones.get(ordenarOpcionesMenuMB.getIdParent()).getItemMenu().getChildren().clear();
            wrapperOpciones.get(ordenarOpcionesMenuMB.getIdParent()).getItemMenu().getChildren().add(0, itemAcciones);

            for (UIComponent uiComponent : listaItemsOrdenados) {
                wrapperOpciones.get(ordenarOpcionesMenuMB.getIdParent()).getItemMenu().getChildren()
                        .add(listaItemsOrdenados.indexOf(uiComponent) + 1, uiComponent);
                // Cambiar el atributo orden de los MenuDto en el Map: wrapperOpciones
                wrapperOpciones.get(((UISubmenu) uiComponent).getId()).getMenuDto()
                        .setOrden(listaItemsOrdenados.indexOf(uiComponent) + 1);
                wrapperOpciones.get(((UISubmenu) uiComponent).getId()).getMenuDto().setModificado(true);
            }

        }
        RequestContext.getCurrentInstance().execute("PF('dlgOrdenarSubmenu').hide()");
        recorrerMenuCreado();
    }

    /**
     * Elimina del menu la opcion con el Id enviado como parametro y todos sus submenus asociados
     * 
     * @param idParent
     */
    public void eliminarSubmenu(String idParent) {
        logger.debug("AdminMenuMB::eliminarSubmenu");

        if (wrapperOpciones.get(idParent).getPadre() != null) {
            wrapperOpciones.get(idParent).getPadre().getHijos().remove(wrapperOpciones.get(idParent));
        }
        UISubmenu subMenuBase = wrapperOpciones.get(idParent).getItemMenu();
        subMenuBase.getParent().getChildren().remove(subMenuBase);
        if (wrapperOpciones.get(idParent).getMenuDto().getIdMenu() != null)
            opcionesMenuEliminadas.add(wrapperOpciones.get(idParent).getMenuDto());
        if (wrapperOpciones.get(idParent).getHijos().size() > 0) {
            for (WrapperOpcionMenu hijo : wrapperOpciones.get(idParent).getHijos()) {
                eliminarSubmenu(hijo);
            }
        }
        wrapperOpciones.remove(idParent);
        recorrerMenuCreado();
    }

    /**
     * Elimina el WrapperOpcionMenu enviado como parametro y todos sus hijos del mapa wrapperOpciones que mantiene el inventario de las opciones del
     * menu
     * 
     * @param wrappMenu
     *            WrapperOpcionMenu que se desea eliminar
     */
    public void eliminarSubmenu(WrapperOpcionMenu wrappMenu) {
        wrapperOpciones.remove(wrappMenu.getId());
        if (wrappMenu.getMenuDto().getIdMenu() != null)
            opcionesMenuEliminadas.add(wrappMenu.getMenuDto());
        if (wrappMenu.getHijos().size() > 0) {
            for (WrapperOpcionMenu hijo : wrappMenu.getHijos()) {
                eliminarSubmenu(hijo);
            }
        }
    }

    /**
     * Punto de entrada para desplegar formulario de creacion de un submenu con acciones asociado al padre especificado
     * 
     * @param idParent
     *            identificador del elemento padre dentro del indice de elementos de menu
     */
    public void inicioEditarSubMenu(String idSubmenu) {
        logger.debug("AdminMenuMB::inicioEditarSubMenu");
        modificarOpcionMenuMB.setIdSubmenuModificar(idSubmenu);
        modificarOpcionMenuMB.setMenuModificar(wrapperOpciones.get(idSubmenu).getMenuDto());
        List<LlaveValorDto> atributos = new ArrayList<LlaveValorDto>();
        for (LlaveValorDto llaveValorDto : wrapperOpciones.get(idSubmenu).getMenuDto().getAtributos()) {
            LlaveValorDto atr = new LlaveValorDto(llaveValorDto.getId(), llaveValorDto.getValor());
            atributos.add(atr);
        }
        modificarOpcionMenuMB.setAtributos(atributos);
        RequestContext.getCurrentInstance().update(":dialog-modificar-submenu");
        RequestContext.getCurrentInstance().execute("PF('dlgModificarSubmenu').show()");
    }

    /**
     * Modifica una opcion de menu con los datos definidos por el usuario en el formulacion de modificacion
     * 
     * @author giovanni.velandia (mod 2015-07-09)
     */
    public String editarSubmenu() {
        if (modificarOpcionMenuMB.getMenuModificar().getRecurso().getIdRecurso() == null) { // Mostrar mensaje requerido para el recurso asignado a la
                                                                                            // opcion
            ResourceBundle bundleGeneral = getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL);
            getFacesContext().addMessage("form-modificar-submenu:recurso",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, bundleGeneral.getString("mensaje_requerido")));
        } else {

            wrapperOpciones.get(modificarOpcionMenuMB.getIdSubmenuModificar()).getItemMenu()
                    .setLabel(modificarOpcionMenuMB.getMenuModificar().getLabel());
            modificarOpcionMenuMB.getMenuModificar().setModificado(true);
            List<LlaveValorDto> atributos = new ArrayList<LlaveValorDto>();
            atributos = modificarOpcionMenuMB.getAtributos();

            /*
             * Validamos que no se encuentren repetidos los atributos
             */
            for (int x = 0; x < modificarOpcionMenuMB.getAtributos().size(); x++) {
                for (int y = 0; y < modificarOpcionMenuMB.getAtributos().size(); y++) {
                    if (x != y) {
                        if (modificarOpcionMenuMB.getAtributos().get(x).getId()
                                .equals(modificarOpcionMenuMB.getAtributos().get(y).getId())) {
                            getFacesContext().addMessage(
                                    "form-modificar-submenu:adicion:" + x + ":nombreAtr",
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                            NOMBRE_BUNDLE_OPCIONES_MENU).getString("msg_requerido_atributo")));
                            getFacesContext().addMessage(
                                    "form-modificar-submenu:adicion:" + y + ":nombreAtr",
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                            NOMBRE_BUNDLE_OPCIONES_MENU).getString("msg_requerido_atributo")));
                            return null;
                        }
                    }
                }
            }

            wrapperOpciones.get(modificarOpcionMenuMB.getIdSubmenuModificar()).getMenuDto().setAtributos(atributos);
            modificarOpcionMenuMB.reset();
            recorrerMenuCreado();
            RequestContext.getCurrentInstance().execute("PF('dlgModificarSubmenu').hide()");
        }
        return null;
    }

    /**
     * Punto de entrada para desplegar formulario que permite mover una opcion como submenu de otra opcion de menu diferente a la actual
     * 
     * @param idSubmenu
     *            identificador del submenu a mover
     */
    public void inicioMoverSubmenu(String idSubmenu) {
        moverOpcionesMenuMB.setIdSubmenu(idSubmenu);
        List<SelectItem> listaPadresDisponibles = new ArrayList<SelectItem>();
        String idPadre = null;
        boolean agregarItem;
        if (wrapperOpciones.get(idSubmenu).getPadre() != null)
            idPadre = wrapperOpciones.get(idSubmenu).getPadre().getId();
        for (Iterator<Entry<String, WrapperOpcionMenu>> iterator = wrapperOpciones.entrySet().iterator(); iterator
                .hasNext();) {
            agregarItem = true;
            Entry<String, WrapperOpcionMenu> opcion = iterator.next();
            if (!opcion.getKey().equals(idSubmenu)) {
                if (idPadre != null) {
                    if (opcion.getKey().equals(idPadre)) {
                        agregarItem = false;
                    }
                }
                if (agregarItem) {
                    SelectItem item = new SelectItem();
                    item.setValue(opcion.getKey());
                    item.setLabel(opcion.getValue().getMenuDto().getLabel());
                    listaPadresDisponibles.add(item);
                }
            }
        }

        eliminarHijosListaPadresDisponibles(wrapperOpciones.get(idSubmenu), listaPadresDisponibles);
        moverOpcionesMenuMB.setListaPadresDisponibles(listaPadresDisponibles);
        RequestContext.getCurrentInstance().update("dialog-mover-submenu");
        RequestContext.getCurrentInstance().execute("PF('dlgMoverSubmenu').show()");

    }

    /**
     * Elimina de la lista de SelectItem enviada como parametro todas las opciones de menu de los submenus relacionados con el WrapperOpcionMenu
     * enviado
     * 
     * @param wrapOpcion
     * @param listaPadresDisponibles
     */
    private void eliminarHijosListaPadresDisponibles(WrapperOpcionMenu wrapOpcion,
            List<SelectItem> listaPadresDisponibles) {
        if (wrapOpcion.getHijos() != null) {
            for (WrapperOpcionMenu wrapHijo : wrapOpcion.getHijos()) {
                for (SelectItem selectItem : listaPadresDisponibles) {
                    if (selectItem.getValue().equals(wrapHijo.getId())) {
                        listaPadresDisponibles.remove(selectItem);
                        break;
                    }
                }
                if (wrapHijo.getHijos() != null) {
                    eliminarHijosListaPadresDisponibles(wrapHijo, listaPadresDisponibles);
                }
            }
        }

    }

    /**
     * Mueve una opcion como submenu de otra opcion de menu diferente a la actual, cambia el orden en los MenuDto que sean necesarios
     */
    /**
     * 
     */
    public void moverSubmenu() {
        String idSubmenu = moverOpcionesMenuMB.getIdSubmenu();
        String idSubmenuMover = moverOpcionesMenuMB.getIdPadreMover();// Si el id es vacio, quiere decir que se va a mover al Menu Ppal
        boolean cambiarOrdenmenuBar = false;
        // Lo elimina del padre aterior en el mapa de wrapperOpciones y lo agrega a los hijos del nuevo padre donde se esta moviendo
        // pero el orden de las opciones de menu cambia tanto en el padre origen como en el destino
        if (wrapperOpciones.get(idSubmenu).getPadre() != null) {
            // El item a mover no esta en el menu principal
            wrapperOpciones.get(idSubmenu).getPadre().getHijos().remove(wrapperOpciones.get(idSubmenu));
            ArrayList<WrapperOpcionMenu> hijos = wrapperOpciones.get(idSubmenu).getPadre().getHijos();
            for (int i = 0; i < hijos.size(); i++) {
                hijos.get(i).getMenuDto().setOrden(i + 1);
                hijos.get(i).getMenuDto().setModificado(true);
            }
        } else {
            // Se esta moviendo una opcion del menu principal, debe cambiar el orden de las opciones
            cambiarOrdenmenuBar = true;
        }
        WrapperOpcionMenu wraperPadreOriginal = wrapperOpciones.get(idSubmenu).getPadre();
        if (idSubmenuMover != null && !idSubmenuMover.equals("")) {
            wrapperOpciones.get(idSubmenu).setPadre(wrapperOpciones.get(idSubmenuMover));
            wrapperOpciones.get(idSubmenuMover).getHijos().add(wrapperOpciones.get(idSubmenu));
            wrapperOpciones.get(idSubmenu).getMenuDto().setOrden(wrapperOpciones.get(idSubmenuMover).getHijos().size());
        } else {
            // Se esta moviendo una opcion al menu principal
            cambiarOrdenmenuBar = true;
            wrapperOpciones.get(idSubmenu).setPadre(null);
            wrapperOpciones.get(idSubmenu).getMenuDto().setMenuPadre(null);
        }

        // Orden
        wrapperOpciones.get(idSubmenu).getMenuDto().setModificado(true);

        // Lo elimina del padre UISubmenu o de menu ppal segun sea el caso
        WrapperOpcionMenu wraperSubMenuBase = wrapperOpciones.get(idSubmenu);// wraper que se movió
        if (wraperPadreOriginal != null) {
            eliminarSubMenu(wraperPadreOriginal.getItemMenu().getChildren(), wraperSubMenuBase.getItemMenu().getId());// Lo eliminadel uiSubmenuPadre
        } else {
            // El padre original era nulo pues estaba en el menu ppal, se remueve del mennubar
            eliminarSubMenu(menuAplicacion.getChildren(), wraperSubMenuBase.getItemMenu().getId());
        }
        // Lo agrega como hijo en el nuevo padre:UIsubmenu o menu ppal segun sea el caso
        if (wraperSubMenuBase.getPadre() != null) {
            wrapperOpciones.get(wraperSubMenuBase.getPadre().getId()).getItemMenu().getChildren()
                    .add(wraperSubMenuBase.getItemMenu());
        } else {
            menuAplicacion.getChildren().add(wraperSubMenuBase.getItemMenu());
        }

        if (cambiarOrdenmenuBar) {
            List<UIComponent> submenu = menuAplicacion.getChildren();
            for (int i = 0; i < submenu.size(); i++) {
                String id = ((UISubmenu) submenu.get(i)).getId();
                wrapperOpciones.get(id).getMenuDto().setOrden(i + 1);
                wrapperOpciones.get(id).getMenuDto().setModificado(true);

            }
        }
        RequestContext.getCurrentInstance().execute("PF('dlgMoverSubmenu').hide()");
        recorrerMenuCreado();
    }

    private boolean eliminarSubMenu(List<UIComponent> children, String id) {
        for (Iterator<UIComponent> iterator = children.iterator(); iterator.hasNext();) {
            UIComponent uiComponent = (UIComponent) iterator.next();
            if (uiComponent.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Construye el menu estatico de opciones de cada submenu graficado en el menubar
     * 
     * @param parentId
     *            identificador del padre al q se le asocia el menu de acciones
     * @return menu de acciones para un submenu
     */
    private UISubmenu buildMenuAccionesItem(String parentId) {
        final ExpressionFactory exprFactory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
        final ELContext elCtxt = FacesContext.getCurrentInstance().getELContext();

        final UISubmenu subMenuAcciones = new UISubmenu();

        subMenuAcciones.setLabel("Acciones");
        subMenuAcciones.setIcon("ui-icon-wrench");
        subMenuAcciones.setStyleClass("ui-menuitem-highlighted");

        // Accion para agregar subitem a parent
        final UIMenuItem addMenuItem = buildMenuItem(null, "Agregar SubMenu", "ui-icon-plus");
        final MethodExpression mthExprAdd = exprFactory.createMethodExpression(elCtxt,
                "#{adminMenuMB.inicioAgregarSubMenu(\'" + parentId + "\')}", Void.class, new Class[] {});

        addMenuItem.setActionExpression(mthExprAdd);
        addMenuItem.setUpdate("@form");

        subMenuAcciones.getChildren().add(addMenuItem);
        // ---
        subMenuAcciones.getChildren().add(new UISeparator());

        // Accion ordenar hijos del submenu
        final UIMenuItem sortMenuItem = buildMenuItem(null, "Ordenar Hijos", "ui-icon-transfer-e-w");
        final MethodExpression mthExprSort = exprFactory.createMethodExpression(elCtxt,
                "#{adminMenuMB.inicioOrdenarHijosSubmenu(\'" + parentId + "\')}", Void.class, new Class[] {});
        sortMenuItem.setActionExpression(mthExprSort);
        sortMenuItem.setUpdate("@form :dialog-ordenar-submenu");
        subMenuAcciones.getChildren().add(sortMenuItem);

        // Accion mover el submenu
        final UIMenuItem moveMenuItem = buildMenuItem(null, "Mover", "ui-icon-scissors");
        final MethodExpression mthExprMove = exprFactory.createMethodExpression(elCtxt,
                "#{adminMenuMB.inicioMoverSubmenu(\'" + parentId + "\')}", Void.class, new Class[] {});
        moveMenuItem.setActionExpression(mthExprMove);
        moveMenuItem.setUpdate("@form :dialog-mover-submenu");
        subMenuAcciones.getChildren().add(moveMenuItem);

        // Accion editar info del submenu
        final UIMenuItem editMenuItem = buildMenuItem(null, "Modificar", "ui-icon-pencil");
        final MethodExpression mthExprEdit = exprFactory.createMethodExpression(elCtxt,
                "#{adminMenuMB.inicioEditarSubMenu(\'" + parentId + "\')}", Void.class, new Class[] {});
        editMenuItem.setActionExpression(mthExprEdit);
        editMenuItem.setUpdate("@form :dialog-modificar-submenu");
        subMenuAcciones.getChildren().add(editMenuItem);

        // Accion para eliminar submenu
        final UIMenuItem delMenuItem = buildMenuItem(null, "Borrar", "ui-icon-close");
        final MethodExpression mthExprDel = exprFactory.createMethodExpression(elCtxt,
                "#{adminMenuMB.eliminarSubmenu(\'" + parentId + "\')}", Void.class, new Class[] {});

        delMenuItem.setActionExpression(mthExprDel);
        delMenuItem.setUpdate("@form");
        subMenuAcciones.getChildren().add(delMenuItem);

        return subMenuAcciones;
    }

    /**
     * Construye un UIMenuItem y le asigna los datos enviado como parametros
     * 
     * @param id
     * @param value
     * @param icon
     * @return UIMenuItem creado
     */
    private UIMenuItem buildMenuItem(String id, Object value, String icon) {
        UIMenuItem item = new UIMenuItem();
        if (id != null) {
            item.setId(id);
        }
        if (value != null) {
            item.setValue(value);
        }
        item.setIcon(icon);
        return item;
    }

    /**
     * Metodo que permite validar las opciones de menu contenidas en en el map wrapperOpciones
     */
    public void recorrerMenuCreado() {
        for (Iterator<Entry<String, WrapperOpcionMenu>> iterator = wrapperOpciones.entrySet().iterator(); iterator
                .hasNext();) {
            Entry<String, WrapperOpcionMenu> opcion = iterator.next();
            WrapperOpcionMenu wrapOpcion = opcion.getValue();
            logger.debug("**** OPCION=" + wrapOpcion.getId() + " - " + wrapOpcion.getMenuDto().getLabel() + " orden:"
                    + wrapOpcion.getMenuDto().getOrden() + " modificado:" + wrapOpcion.getMenuDto().isModificado());
            logger.debug("****** item menu=" + wrapOpcion.getItemMenu().getId() + " - "
                    + wrapOpcion.getItemMenu().getLabel());
            if (wrapOpcion.getHijos() != null) {
                for (WrapperOpcionMenu hijo : wrapOpcion.getHijos()) {
                    logger.debug("********* con hijo=" + hijo.getMenuDto().getLabel() + " modificado: "
                            + hijo.getMenuDto().isModificado());
                }
            }
            UIComponent padre = wrapOpcion.getItemMenu().getParent();
            if (padre != null) {
                if (padre instanceof UISubmenu) {
                    logger.debug("El padre item menu===" + ((UISubmenu) padre).getLabel());
                } else
                    logger.debug("El padre item menu===" + padre.getId());
            }
            if (wrapOpcion.getItemMenu().getChildren() != null) {
                for (UIComponent itemhijo : wrapOpcion.getItemMenu().getChildren()) {
                    logger.debug("********* con item menu hijo=" + ((UISubmenu) itemhijo).getLabel());
                }
            }
        }
    }

    public OrdenarOpcionesMenuMB getOrdenarOpcionesMenuMB() {
        return ordenarOpcionesMenuMB;
    }

    public void setOrdenarOpcionesMenuMB(OrdenarOpcionesMenuMB ordenarOpcionesMenuMB) {
        this.ordenarOpcionesMenuMB = ordenarOpcionesMenuMB;
    }

    public Map<String, String> getlAplicaciones() {
        return lAplicaciones;
    }

    public void setlAplicaciones(Map<String, String> lAplicaciones) {
        this.lAplicaciones = lAplicaciones;
    }

    public String getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public CrearOpcionMenuMB getCrearOpcionMenuMB() {
        return crearOpcionMenuMB;
    }

    public void setCrearOpcionMenuMB(CrearOpcionMenuMB crearOpcionMenuMB) {
        this.crearOpcionMenuMB = crearOpcionMenuMB;
    }

    public ModificarOpcionMenuMB getModificarOpcionMenuMB() {
        return modificarOpcionMenuMB;
    }

    public void setModificarOpcionMenuMB(ModificarOpcionMenuMB modificarOpcionMenuMB) {
        this.modificarOpcionMenuMB = modificarOpcionMenuMB;
    }

    public MoverOpcionesMenuMB getMoverOpcionesMenuMB() {
        return moverOpcionesMenuMB;
    }

    public void setMoverOpcionesMenuMB(MoverOpcionesMenuMB moverOpcionesMenuMB) {
        this.moverOpcionesMenuMB = moverOpcionesMenuMB;
    }

    public PanelMenu getMenuAplicacion() {
        return menuAplicacion;
    }

    public void setMenuAplicacion(PanelMenu menuAplicacion) {
        this.menuAplicacion = menuAplicacion;
    }

    public Menubar getMenubar() {
        return menubar;
    }

    public void setMenubar(Menubar menubar) {
        this.menubar = menubar;
    }

}
