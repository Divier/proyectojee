package co.com.datatools.seguridad.mb.opcionesmenu;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean que gestiona la pagina para ordenar las opciones de menu
 * 
 * @author claudia.rodriguez
 */
@ManagedBean
@SessionScoped
public class OrdenarOpcionesMenuMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;

    private String idParent;

    private List<String> menuItemList;

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public void reset() {
        setIdParent(null);
    }

    public List<String> getMenuItemList() {
        if (menuItemList == null) {
            menuItemList = new ArrayList<>();
        }
        return menuItemList;
    }

    public void setMenuItemList(List<String> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
