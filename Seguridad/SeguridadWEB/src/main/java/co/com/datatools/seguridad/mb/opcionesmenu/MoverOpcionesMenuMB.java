package co.com.datatools.seguridad.mb.opcionesmenu;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean que gestiona la pagina para mover(cambiar opcion de menu padre) las opciones de menu
 * 
 * @author claudia.rodriguez
 */
@ManagedBean
@SessionScoped
public class MoverOpcionesMenuMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;

    private String idSubmenu;

    private String idPadreMover;

    private List<SelectItem> listaPadresDisponibles;

    public String getIdSubmenu() {
        return idSubmenu;
    }

    public void setIdSubmenu(String idSubmenu) {
        this.idSubmenu = idSubmenu;
    }

    public void reset() {
        setIdSubmenu(null);
    }

    public List<SelectItem> getListaPadresDisponibles() {
        return listaPadresDisponibles;
    }

    public void setListaPadresDisponibles(List<SelectItem> listaPadresDisponibles) {
        this.listaPadresDisponibles = listaPadresDisponibles;
    }

    public String getIdPadreMover() {
        return idPadreMover;
    }

    public void setIdPadreMover(String idPadreMover) {
        this.idPadreMover = idPadreMover;
    }

}
