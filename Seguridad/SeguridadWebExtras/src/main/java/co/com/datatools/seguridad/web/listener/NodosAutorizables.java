package co.com.datatools.seguridad.web.listener;

import java.io.Serializable;

/**
 * Recurso de AUTORIZACION<br>
 * Interfaz q define metodos para retornar identificadores de nodos autorizables dentro del ViewModel de la aplicacion
 * 
 * @author Felipe Martinez
 * 
 */
public interface NodosAutorizables extends Serializable {
    /**
     * Parametriza el nodo donde se encuentra el menu de la aplicacion web
     * 
     * @return
     */
    String getMenuId();

    /**
     * Parametriza el prefijo de los componentes que deben autorizarse
     * 
     * @return
     */
    String getPrefijoOperaciones();

    /**
     * Parametriza el id del nodo padre donde esta contenido autorizable en la aplicacion
     * 
     * @return
     */
    String getContentId();

}
