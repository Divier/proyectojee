package co.com.datatools.seguridad.interfaces;

import java.util.Map;

import javax.ejb.Remote;

import co.com.datatools.seguridad.dto.comun.InstalacionDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;

/**
 * Interfaz remota EJB para administracion de catalogos
 * 
 * @author Felipe Martinez
 */
@Remote
public interface IRCatalogosSeguridad {

    /**
     * Retorna el listado de aplicacion parametrizadas en la BD
     * 
     * @return Llaves - valor: [id_aplicacion, nombre_aplicacion]
     */
    Map<String, String> consultarAplicaciones();

    /**
     * Consulta el identificador asociado al nombre de aplicacion especificado, si no lo encuentra arroja el identificador <br>
     * para evitar realizar operaciones sobre una aplicacion desconocida
     * 
     * @param nombreAplicacion
     *            Nombre de aplicacion unico a buscar
     * @return identificador de la aplicacion
     */
    String consultarIdAplicacion(String nombreAplicacion);

    /**
     * Consulta todos los registros en el catalogo de estados de usuario
     * 
     * @return Mapa con los estados consultados
     * @author claudia.rodriguez
     */
    Map<String, String> consultarEstadosUsuario();

    /**
     * Consulta el identificador del estado de usuario con nombre enviado
     * 
     * @param nombreEstado
     *            Nombre del estado del que se desea consultar el identificador
     * @return Identificador del estado
     * 
     * @author claudia.rodriguez
     */
    String consultarIdEstadoUsuario(String nombreEstado);

    /**
     * Consulta el identificador del estado de password con nombre enviado
     * 
     * @param nombreEstado
     *            Nombre del estado del que se desea consultar el identificador
     * @return Identificador del estado
     * @author claudia.rodriguez
     */
    String consultarIdEstadoPassword(String nombreEstado);

    /**
     * Consulta el nombre de un estado de usuario con el Id enviado
     * 
     * @param id
     *            Identificador del estado a consultar
     * @return Nombre del estado consultado
     * @author claudia.rodriguez
     */
    String consultarNombreEstadoUsuario(Integer id);

    /**
     * Consulta todos los tipos de datos disponibles
     * 
     * @return mapa con los tipos de datos(contiene Id, nombre)
     * 
     * @author claudia.rodriguez
     */
    Map<String, String> consultarTiposDato();

    /**
     * Guarda los parametros y valores de configuracion de la instalacion de la aplicacion
     * 
     * @param dto
     *            objeto con los atributos de configuracion inicial de la aplicacion
     * 
     * @throws SeguridadException
     *             IN0002:Contraseña no cumple politicas
     * 
     * @author claudia.rodriguez
     */
    void guardarDatosInstalacion(InstalacionDto dto) throws SeguridadException;
}