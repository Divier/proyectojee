package co.com.datatools.seguridad.mb.opcionesmenu;

import java.io.Serializable;
import java.util.ArrayList;

import org.primefaces.component.submenu.UISubmenu;

import co.com.datatools.seguridad.dto.autorizacion.MenuDto;

/**
 * Clase que encapsula los datos necesarios de una opcion de menu permitiendo relacionar un UISubmenu en un Menubar con los datos de su MenuDto
 * especifico y de sus MenuDtos hijos y padre
 * 
 * @author claudia.rodriguez
 * 
 */
public class WrapperOpcionMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private UISubmenu itemMenu;
    private MenuDto menuDto;
    private ArrayList<WrapperOpcionMenu> hijos;
    private WrapperOpcionMenu padre;

    public WrapperOpcionMenu() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UISubmenu getItemMenu() {
        return itemMenu;
    }

    public void setItemMenu(UISubmenu itemMenu) {
        this.itemMenu = itemMenu;
    }

    public MenuDto getMenuDto() {
        return menuDto;
    }

    public void setMenuDto(MenuDto menuDto) {
        this.menuDto = menuDto;
    }

    public ArrayList<WrapperOpcionMenu> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<WrapperOpcionMenu> hijos) {
        this.hijos = hijos;
    }

    public WrapperOpcionMenu getPadre() {
        return padre;
    }

    public void setPadre(WrapperOpcionMenu padre) {
        this.padre = padre;
    }

}