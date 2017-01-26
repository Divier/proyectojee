package co.com.datatools.seguridad.dto.autorizacion;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que permite encapsular los datos necesarios para una modificacion de las opciones de menu de una aplicacion
 * 
 * @author claudia.rodriguez
 * 
 */
public class EdicionMenuDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Opciones de menu que deben crearse o modificarse para una aplicacion, cada opcion de menu en la lista corresponde a una opcion de menu
     * principal y debe contener dentro toda la jerarquia de submenus
     */
    List<MenuDto> opcionesMenu;
    /**
     * Listado con todas las opciones de menu que fueron eliminadas en la interfaz
     */
    List<MenuDto> opcionesMenuEliminadas;

    /**
     * Obtiene las opciones de menu de la aplicacion
     * 
     * @return opciones de menu de la aplicacion
     */
    public List<MenuDto> getOpcionesMenu() {
        return opcionesMenu;
    }

    /**
     * Asigna las opciones de menu de la aplicacion
     * 
     * @param opcionesMenu
     *            opciones de menu de la aplicacion
     */
    public void setOpcionesMenu(List<MenuDto> opcionesMenu) {
        this.opcionesMenu = opcionesMenu;
    }

    /**
     * Obtiene las opciones de menu eliminadas de la aplicacion despues de realizar la edicion
     * 
     * @return opciones de menu de la aplicacion despues de realizar la edicion
     */
    public List<MenuDto> getOpcionesMenuEliminadas() {
        return opcionesMenuEliminadas;
    }

    /**
     * Asigna al dto las opciones de menu eliminadas de la aplicacion despues de realizar la edicion
     * 
     * @param opcionesMenuEliminadas
     *            opciones de menu eliminadas de la aplicacion despues de realizar la edicion
     */
    public void setOpcionesMenuEliminadas(List<MenuDto> opcionesMenuEliminadas) {
        this.opcionesMenuEliminadas = opcionesMenuEliminadas;
    }

}
