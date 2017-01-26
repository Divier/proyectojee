package co.com.datatools.seguridad.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;
import co.com.datatools.seguridad.utilidades.EnumParametro;

/**
 * Interfaz remota que define los metodos relacionados con la administracion de parametros de seguridad
 * 
 * @author claudia.rodriguez
 * 
 */
@Remote
public interface IRParametrosSeguridad {
    /**
     * Consulta un parametro de configuracion en el sistema y retorna su valor
     * 
     * @param parametro
     *            Valor de la enumeracion EnumParametro con el nombre del parametro a consultar
     * @return Valor del parametro consultado, si no encuentra el parametro retorna vacio
     * 
     * @author claudia.rodriguez
     */
    String consultarValorParametroSeguridad(EnumParametro parametro);

    /**
     * Consulta los parametros que coincidan con el nombre enviado(o parte de este)
     * 
     * @param nombre
     *            Nombre del parametro a consultar, si es null o vacio, se consultan todos los parametros
     * @return Parametros consultados
     * 
     * @author claudia.rodriguez
     */
    List<ParametroSeguridadDto> consultarParametroSeguridad(String nombre);

    /**
     * Actualiza los datos de un parametro de seguridad
     * 
     * @param parametroSeguridadDto
     *            Dto con los datos del parametro a modificar
     * 
     * @author claudia.rodriguez
     */
    void actualizarParametroSeguridad(ParametroSeguridadDto parametroSeguridadDto);

    /**
     * Consulta el valor de la url de la aplicacion contenido en el parametro cuyo nombre es enviado como parametro.Se asume que el parametro esta
     * almacenado de la forma: <map><entry><string>idAplicacion</string><string>valorUrlAplicacion</string></entry></map>
     * 
     * @param nombreParametro
     *            Nombre del parametro que contiene las urls de las aplicaciones
     * @param idAplicacion
     *            Nombre de la aplicacion de la cual se va a obtener el valor de la url
     * @return url de la aplicacion
     */
    String consultarValorParametroUrlAplicacion(EnumParametro nombreParametro, String idAplicacion);

}
