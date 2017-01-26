package co.com.datatools.seguridad.util;

import co.com.datatools.seguridad.web.listener.NodosAutorizables;

/**
 * Implementacion de la definicion de nodos para la aplicacion de seguridad
 * 
 * @author claudia.rodriguez
 */
public class NodosAutorizablesCirculemos implements NodosAutorizables {

    @Override
    public String getMenuId() {
        return "";
    }

    @Override
    public String getPrefijoOperaciones() {
        return "_op-";
    }

    @Override
    public String getContentId() {
        return "contenido";
    }

}
