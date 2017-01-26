package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.EdicionMenuDto;
import co.com.datatools.seguridad.dto.autorizacion.MenuDto;
import co.com.datatools.seguridad.entidades.Recurso;
import co.com.datatools.seguridad.entidades.RecursoMenu;
import co.com.datatools.seguridad.helper.MenuHelper;
import co.com.datatools.seguridad.interfaces.IRMenu;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;

/**
 * EJB que provee los metodos para realizar las operaciones CRUD sobre la entidad RecursoMenu
 * 
 * @author claudia.rodriguez
 * 
 */
@Stateless(name = "MenuEJB")
@LocalBean
public class MenuEJB implements IRMenu {

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(MenuEJB.class.getName());

    public MenuEJB() {
        super();
        logger.debug("MenuEJB::MenuEJB");
    }

    @Override
    public List<MenuDto> consultarOpcionesMenu(Integer idAplicacion) {
        List<MenuDto> resultado = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT(m)");
        sql.append(" FROM RecursoMenu m");
        sql.append(" JOIN FETCH m.recurso r");
        sql.append(" JOIN FETCH r.aplicacion a");
        sql.append(" LEFT JOIN FETCH m.menuPadre mp");
        sql.append(" LEFT JOIN FETCH m.submenuList sub");
        sql.append(" WHERE a.idAplicacion=:idApp");
        sql.append(" ORDER BY m.menuPadre,m.orden");

        TypedQuery<RecursoMenu> query = em.createQuery(sql.toString(), RecursoMenu.class);
        query.setParameter("idApp", idAplicacion);

        List<RecursoMenu> result = query.getResultList();
        if (!CollectionUtils.isEmpty(result)) {
            for (RecursoMenu recursoMenu : result) {
                if (recursoMenu.getMenuPadre() == null) {
                    MenuDto menuNivel1 = obtenerMenuDetalle(recursoMenu, result);
                    resultado.add(menuNivel1);
                }
            }
        }
        logger.debug("Se consultaron " + resultado.size() + " RecursoMenu para la aplicacion con Id= " + idAplicacion);
        return resultado;
    }

    /**
     * Obtiene un MenuDto apartir de la entidad enviada como parametro, con todo el detalle de todos sus submenus relacionados
     * 
     * @param recursoMenu
     *            Entidad cuyo detalle sera cargado al Dto
     * @param resultados
     *            Listado con todas las opciones de menu consultadas
     * @return Dto con el detalle completo de la opcion de menu
     */
    private MenuDto obtenerMenuDetalle(RecursoMenu recursoMenu, List<RecursoMenu> resultados) {
        // Datos basicos
        MenuHelper helper = new MenuHelper();
        MenuDto menuDto = helper.toDto(recursoMenu, null);
        // Padre
        if (recursoMenu.getMenuPadre() != null) {
            menuDto.setMenuPadre(helper.toDto(recursoMenu.getMenuPadre(), null));
        } else {
            menuDto.setMenuPadre(null);
        }
        // Hijos
        if (recursoMenu.getSubmenuList() != null) {
            List<MenuDto> hijos = new ArrayList<>();
            for (RecursoMenu recursoMenuHijo : recursoMenu.getSubmenuList()) {
                // Tomar el recursoMenu hijo de la lista de resultados ya que trae todos sus datos completos
                RecursoMenu recHijoCompleto = obtenerMenuLista(resultados, recursoMenuHijo);
                MenuDto menuDtoHijo = new MenuDto();
                menuDtoHijo = obtenerMenuDetalle(recHijoCompleto, resultados);
                hijos.add(menuDtoHijo);
            }
            menuDto.setSubmenu(hijos);
        }
        return menuDto;
    }

    /**
     * Busca en un listado el RecursoMenu enviado como parametro, si no lo encuentra retorna cero
     * 
     * @param listaBuscar
     *            Lista en la cual se va a buscar el RecursoMenu
     * @param recursoMenuBuscar
     *            Entidad a buscar en la lista
     * @return RecursoMenu encontrado, si no lo encuentra null
     */
    private RecursoMenu obtenerMenuLista(List<RecursoMenu> listaBuscar, RecursoMenu recursoMenuBuscar) {
        for (RecursoMenu opcionMenu : listaBuscar) {
            if (opcionMenu.getIdRecursoMenu().equals(recursoMenuBuscar.getIdRecursoMenu())) {
                return opcionMenu;
            }
        }
        return null;
    }

    @Override
    public void registrarOpcionesMenu(EdicionMenuDto opcionesMenuDto) {
        // Crear/Modificar opciones de menu
        for (MenuDto menuDto : opcionesMenuDto.getOpcionesMenu()) {
            logger.debug("MenuEJB::registrarOpcionesMenu: TRAE LA OPCION= " + menuDto.getIdMenu() + " - "
                    + menuDto.getLabel());
            gestionarOpcionMenu(menuDto);
        }
        // Si se eliminaron opciones, removerlas de la BD
        for (MenuDto menuDto : opcionesMenuDto.getOpcionesMenuEliminadas()) {
            eliminarOpcionMenu(em.find(RecursoMenu.class, menuDto.getIdMenu()));
        }
    }

    /**
     * Elimina un recurso menu, eliminando primero todos sus hijos recursivamente en caso que los tenga
     * 
     * @param menuEliminar
     *            Entidad a eliminar
     */
    private void eliminarOpcionMenu(RecursoMenu menuEliminar) {

        if (menuEliminar != null) {// Evaluar que la entidad no sea nula, ya que pudo haber sido eliminada desde el padre
            Integer idRecursoMenuEliminar = menuEliminar.getIdRecursoMenu();
            if (!CollectionUtils.isEmpty(menuEliminar.getSubmenuList())) {
                // Remover primero los hijos
                for (RecursoMenu submenu : menuEliminar.getSubmenuList()) {
                    eliminarOpcionMenu(submenu);
                }
            }
            em.remove(menuEliminar);
            logger.debug("** Se eliminó el RecursoMenu de Id= " + idRecursoMenuEliminar);
        }
    }

    /**
     * Gestiona la creacion o modificacion de la opcion de menu enviada como parametro y tambien crea o modifica las opciones de los submenus
     * relacionados recursivamente
     * 
     * @param menuGuardar
     */
    private void gestionarOpcionMenu(MenuDto menuGuardar) {
        if (menuGuardar.getIdMenu() == null) {
            MenuDto recursoPersistido = registrarRecursoMenu(menuGuardar);
            if (!CollectionUtils.isEmpty(menuGuardar.getSubmenu())) {
                for (MenuDto menuHijo : menuGuardar.getSubmenu()) {
                    menuHijo.setMenuPadre(recursoPersistido);
                }
            }
        } else {
            if (menuGuardar.isModificado())
                actualizarRecursoMenu(menuGuardar);
        }

        if (!CollectionUtils.isEmpty(menuGuardar.getSubmenu())) {
            for (MenuDto menuHijo : menuGuardar.getSubmenu()) {
                gestionarOpcionMenu(menuHijo);
            }
        }
    }

    /**
     * Persiste un RecursoMenu
     * 
     * @param menuDto
     *            Dto con los datos a persistir
     * @return Dto con el Id de la entidad persistida
     */
    private MenuDto registrarRecursoMenu(MenuDto menuDto) {
        MenuHelper helper = new MenuHelper();
        RecursoMenu entidad = helper.toEntity(menuDto, null);
        // Padre
        if (menuDto.getMenuPadre() != null)
            entidad.setMenuPadre(em.find(RecursoMenu.class, menuDto.getMenuPadre().getIdMenu()));
        // Recurso
        entidad.setRecurso(em.find(Recurso.class, menuDto.getRecurso().getIdRecurso()));
        em.persist(entidad);
        menuDto.setIdMenu(entidad.getIdRecursoMenu());
        logger.debug("**** Se registró el recurso menú= " + menuDto.getIdMenu() + " - " + menuDto.getLabel());
        return menuDto;

    }

    /**
     * Modifica un RecursoMenu
     * 
     * @param menuDto
     *            Dto con los datos a modificar del RecursoMenu
     */
    private void actualizarRecursoMenu(MenuDto menuDto) {
        RecursoMenu entidad = em.find(RecursoMenu.class, menuDto.getIdMenu());
        MenuHelper helper = new MenuHelper();
        RecursoMenu entidadModificada = helper.toEntity(menuDto, entidad);
        // Padre
        if (menuDto.getMenuPadre() != null)
            entidadModificada.setMenuPadre(em.find(RecursoMenu.class, menuDto.getMenuPadre().getIdMenu()));
        else
            entidadModificada.setMenuPadre(null);
        // Recurso
        entidadModificada.setRecurso(em.find(Recurso.class, menuDto.getRecurso().getIdRecurso()));
        em.merge(entidadModificada);
        logger.debug("**** Se modificó el RecursoMenu= " + menuDto.getIdMenu() + " - " + menuDto.getLabel());

    }
}
