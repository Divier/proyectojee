package co.com.datatools.seguridad.dto.autorizacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.seguridad.dto.comun.LlaveValorDto;

/**
 * DTO que encapsula los datos de una opcion de menu
 * 
 * @author claudia.rodriguez
 * 
 */
public class MenuDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idMenu;
    private int orden;
    private String label;

    /**
     * Recurso asociado a este elemento del menu
     */
    private RecursoDto recurso;

    /**
     * Menu padre del elemento de menu
     */
    private MenuDto menuPadre;

    /**
     * Submenus de este elemento del menu
     */
    private List<MenuDto> submenu;

    /**
     * List con los nombres y valores de cada atributo del menu
     */
    private List<LlaveValorDto> atributos;

    /**
     * Indica si el MenuDto ha sido modificado
     */
    private boolean modificado = false;

    /**
     * Obtiene el listado de MenuDto hijos(submenu) del MenuDto, en caso de que sea nulo, retorna nueva instancia
     * 
     * @return Listado de MenuDto hijos(submenu)
     */
    public List<MenuDto> getSubmenu() {
        if (submenu == null) {
            this.submenu = new ArrayList<>();
        }
        return submenu;
    }

    /**
     * Asigna el listado de MenuDto hijos(submenu) del MenuDto, en caso de que sea nulo, le asigna una nueva instancia
     * 
     * @param submenu
     *            Listado de MenuDto hijos(submenu) a asignar
     */
    public void setSubmenu(List<MenuDto> submenu) {
        if (submenu == null) {
            this.submenu = new ArrayList<>();
        } else {
            this.submenu = submenu;
        }
    }

    /**
     * Obtiene de la Lista de atributos, el valor del atributo cuyo nombre(id) sea igual al enviado como parametro
     * 
     * @param id
     *            Id del atributo a obtener su valor
     * @return Valor del atributo, o vacio si no lo encuentra en la lista
     */
    public String obtenerValorAtributo(String id) {
        for (LlaveValorDto atributo : atributos) {
            if (atributo.getId().equals(id)) {
                return atributo.getValor();

            }
        }
        return "";
    }

    /**
     * Obtiene de la Lista de atributos, el valor del atributo cuyo nombre sea igual al enviado como parametro de imagen menu
     * 
     * @param id
     *            Id del atributo a obtener su valor
     * @return Valor del atributo, o vacio si no lo encuentra en la lista
     * @author giovanni.velandia
     */
    public String obtenerValorAtributo() {
        String id = "imgmenu";
        for (LlaveValorDto atributo : atributos) {
            if (atributo.getId().equals(id)) {
                return atributo.getValor();

            }
        }
        return "none";
    }

    public RecursoDto getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoDto recurso) {
        this.recurso = recurso;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public MenuDto getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(MenuDto menuPadre) {
        this.menuPadre = menuPadre;
    }

    public List<LlaveValorDto> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<LlaveValorDto> atributos) {
        this.atributos = atributos;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

}
