package co.com.datatools.seguridad.util;

import co.com.datatools.seguridad.web.listener.NodosAutorizables;

/**
 * Implementacion de la definicion de nodos para la aplicacion de seguridad
 * 
 * @author Felipe Martinez
 */
public class NodosAutorizablesSeguridad implements NodosAutorizables {

    private static final long serialVersionUID = 4320038374968103560L;

    @Override
    public String getMenuId() {
        return "menu-seguridad";
    }

    @Override
    public String getPrefijoOperaciones() {
        return "_op-";
    }

    @Override
    public String getContentId() {
        return "contenido-seguridad";
    }

}
