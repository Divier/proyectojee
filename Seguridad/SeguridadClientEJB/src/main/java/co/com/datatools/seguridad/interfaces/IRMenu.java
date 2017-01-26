package co.com.datatools.seguridad.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.seguridad.dto.autorizacion.EdicionMenuDto;
import co.com.datatools.seguridad.dto.autorizacion.MenuDto;

/**
 * Interfaz remota que define los metodos relacionados con la administracion de las opciones de menu
 * 
 * @author felipe.martinez
 */
@Remote
public interface IRMenu {

    /**
     * Consulta las opciones de menu para la aplicacion enviada como parametro. Si no encuentra resultados retorna la lista vacia
     * 
     * @param idAplicacion
     *            Id de la aplicacion cuyas opciones de menu seran consultadas
     * @return Opciones de menu consultadas
     * 
     * @author claudia.rodriguez
     */
    List<MenuDto> consultarOpcionesMenu(Integer idAplicacion);

    /**
     * Guarda las opciones de menu de una aplicacion
     * 
     * @param opcionesMenuDto
     *            Dto con las opciones de menu que deben ser persistidas o modificadas y aquellas que deben ser eliminadas
     * 
     * @author claudia.rodriguez
     */
    void registrarOpcionesMenu(EdicionMenuDto opcionesMenuDto);

}
