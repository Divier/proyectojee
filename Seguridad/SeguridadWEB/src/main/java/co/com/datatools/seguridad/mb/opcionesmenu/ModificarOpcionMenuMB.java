package co.com.datatools.seguridad.mb.opcionesmenu;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.datatools.seguridad.dto.autorizacion.MenuDto;
import co.com.datatools.seguridad.dto.comun.LlaveValorDto;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean que gestiona la pagina de modiifcacion de opciones de menu
 * 
 * @author claudia.rodriguez
 */
@ManagedBean
@SessionScoped
public class ModificarOpcionMenuMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 6345289691809517515L;

    private MenuDto menuModificar;
    private String idSubmenuModificar;
    private List<LlaveValorDto> atributos = new ArrayList<LlaveValorDto>();

    public ModificarOpcionMenuMB() {

    }

    public MenuDto getMenuModificar() {
        return menuModificar;
    }

    public void setMenuModificar(MenuDto menuModificar) {
        this.menuModificar = menuModificar;
    }

    public String getIdSubmenuModificar() {
        return idSubmenuModificar;
    }

    public void setIdSubmenuModificar(String idSubmenuModificar) {
        this.idSubmenuModificar = idSubmenuModificar;
    }

    public void reset() {
        setIdSubmenuModificar(null);
        setMenuModificar(null);
        atributos = null;
    }

    public void quitarAtributo(LlaveValorDto dto) {
        atributos.remove(dto);
    }

    public void agregarAtributo() {
        atributos.add(new LlaveValorDto());
    }

    public List<LlaveValorDto> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<LlaveValorDto> atributos) {
        this.atributos = atributos;
    }

}
