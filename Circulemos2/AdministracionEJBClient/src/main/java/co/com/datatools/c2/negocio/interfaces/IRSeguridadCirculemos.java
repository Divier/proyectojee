package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.utilidades.EnumEstadoPassword;

/**
 * Interfa remota que define los metodos relacionados con la administracion de usuarios
 * 
 * @author claudia.rodriguez
 * 
 */
@Remote
public interface IRSeguridadCirculemos {

    /**
     * Obtener usuario autenticado
     * 
     * @return Retorna el usuario persona correspondiente al autenticado en el sistema
     * @author giovanni.velandia(mod 2015-08-18)
     */
    UsuarioPersonaDTO obtenerUsuarioDto();

    /**
     * Obtener el usuario asociado al login
     * 
     * @param login
     *            login a buscar en usuarios
     * @return Retorna el usuario persona correspondiente al login especificado, null si no se encuentra
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-12-19)
     */
    UsuarioPersonaDTO obtenerUsuarioDto(String login);

    /**
     * Invoca al componente de seguridad para consultar los roles existentes en el sistema, excepto los roles: PUBLICO Y SUPER-ADMIN
     * 
     * @param rolActivo
     *            si es igual a true, solo trae los roles que estan activos
     * @return Lista de roles consultados
     * 
     * 
     * @author claudia.rodriguez
     */
    List<RolDetalleDto> consultarRoles(boolean rolActivo);

    /**
     * Invoca al componente de seguridad para hacer la consulta de todos los usuarios, excluyendo el usuario con el rol super-admin, que cumplan con
     * los filtros enviados
     * 
     * @param usuarioPersonaDTO
     *            usuarioDto Dto con los filtros de consulta
     * @return listado de usuarios encontradados,exluyendo el usuario con rol super-admin, si no encuentra nada retorna lista vacia
     * @author claudia.rodriguez
     */
    List<UsuarioPersonaDTO> consultarUsuarios(UsuarioPersonaDTO usuarioPersonaDTO);

    /**
     * Consulta los registros de UsuarioPersona que cumplan con los filtros enviados, si no recibe filtros consulta todos los registros existentes de
     * UsuarioPersona
     * 
     * @param usuarioPersonaDTO
     *            Dto con los filtros de consulta
     * @return Listado con los dto de los UsuarioPersona encontrados, si no encuentra resultados retorna la lista vacia
     */
    List<UsuarioPersonaDTO> consultarUsuarioPersona(UsuarioPersonaDTO usuarioPersonaDTO);

    /**
     * Invoca al componente de seguridad para consultar la informacion de detalle de un usuario,el parametro soloRolesEditables indica si en los roles
     * del usuario se excluye el rol PUBLICO y SUPER-ADMIN(en caso que lo tenga)
     * 
     * @param login
     *            identificador del usuario
     * 
     * @param soloRolesEditables
     *            Indica si solo se consultaran los roles editables que tenga el usuario, es decir excluir los roles PUBLICO y SUPER-ADMIN(en caso que
     *            lo tenga) dentro de los roles del usuario
     * @return objeto con toda la informacion del usuario, null si no existe
     * 
     * @author claudia.rodriguez
     */
    UsuarioDetalleDto consultarUsuario(String login, boolean soloRolesEditables);

    /**
     * Invoca al componente de seguridad para eliminar un usuario con el id dado
     * 
     * @param idUsuario
     *            Login del usuario a eliminar
     * 
     * @throws SeguridadException
     *             US0003: El Usuario tiene registros de ingresos
     * 
     * @author claudia.rodriguez
     */
    void eliminarUsuario(final Integer idUsuario) throws SeguridadException;

    /**
     * Invoca al componente de seguridad para modificar la contraseña de un usuario asignando una contraseña nueva generada por el sistema y envia
     * correo al usuario notificando la modificacion
     * 
     * @param login
     *            Login del usuario al que se le modifica la contraseña
     * 
     * @param usuarioRealizaCambio
     *            Login del usuario autenticado en la aplicacion y que realiza la modificacion
     * @author claudia.rodriguez
     */
    void actualizarPwUsuario(String login, String usuarioRealizaCambio);

    /**
     * Invoca al componente de seguridad para actualizar el estado de la contraseña de un usuario
     * 
     * @param login
     *            login del usuario al que se le actualiza el estado de la contraseña
     * 
     * @param estadoPw
     *            Estado de la contraseña para modificar al usuario
     * 
     * @param usuarioRealizaCambio
     *            Login del usuario autenticado en la aplicacion y que realiza la modificacion
     * 
     * @author claudia.rodriguez
     */
    void actualizarEstadoPwUsuario(String login, EnumEstadoPassword estadoPw, String usuarioRealizaCambio);

    /**
     * Invoca al componente de seguridad para actualizar un usuario
     * 
     * @param usuario
     *            Usuario con los datos a modificar
     * @throws SeguridadException
     *             US0004:Ya existe un usuario con el correo electronico enviado
     * 
     * @author claudia.rodriguez
     */
    void actualizarUsuario(final UsuarioDetalleDto usuario) throws SeguridadException;

    /**
     * Para una persona natural:Invoca al componente de seguridad para generar un login basado en los nombres y apellidos del usuario y para una
     * persona juridica retorna la sigla como el login, en ambos casos si ya existe el login lo concatena con dos numeros aleatorios
     * 
     * @param personaDTO
     *            PersonaDTO con los datos para generacion de login
     * 
     * @return login generado
     * @author claudia.rodriguez
     */
    String generarLogin(PersonaDTO personaDTO);

    /**
     * Valida si exiten usuarios con el login enviado
     * 
     * @param login
     *            Login para validar los usuarios exixtentes
     * @return true si ya hay usuarios con ese login, de lo contrario false
     * @author claudia.rodriguez
     */
    boolean validarExisteLogin(String login);

    /**
     * Retorna el organismo de transito asociado al usuario autenticado
     * 
     * @return Organismo de Transito del usuario autenticado
     * @author giovanni.velandia(mod 2015-08-18)
     */
    OrganismoTransitoDTO obtenerOrganismoTransitoUsuario();

    /**
     * Metodo que se encarga de obtener el pais del usuario en session
     * 
     * @author giovanni.velandia
     * @return PaisDTO
     */
    public PaisDTO obtenerPais();

    /**
     * Invoca al componente de seguridad para la creacion de un usuario
     * 
     * @param usuario
     * 
     * @throws SeguridadException
     *             US0004:Ya existe un usuario con el correo electronico enviado
     * 
     * @author claudia.rodriguez
     */
    void registrarUsuario(UsuarioPersonaDTO usuario) throws SeguridadException, CirculemosNegocioException;

    /**
     * Invoca al componente de seguridad para consultar el usuario en el LDAP cuyo identificador coincida con el campo de busqueda enviado
     * 
     * @param campoBusqueda
     *            Valor del campo por el que se realiza la busqueda en el campo ID de usuario en LDAP
     * 
     * @return Dto del usuario encontrado, sino lo encuentra retorna null
     * 
     * @throws SeguridadException
     *             US0001:No se puede realizar la conexion con el LDAP<br>
     *             US0006:Se encuentra mas de un resultado de usuario en el LDAP<br>
     *             ADM_013001:Ya existe un usuario con el tipo y numero de documento de identificacion ingresado
     * 
     * @author claudia.rodriguez
     */
    UsuarioDto consultarUsuarioLdap(String campoBusqueda) throws SeguridadException;

    /**
     * Consultar roles por usuario
     * 
     * @param login
     * @return Listado de roles
     * @author julio.pinzon 2016-06-15
     */
    List<String> consultarRolesUsuario(String login);

    /**
     * Registra un usuario persona para realizar la asociacion
     * 
     * @author giovanni.velandia
     * @param usuarioPersonaDTO
     */
    public void registrarUsuarioPersona(UsuarioPersonaDTO usuarioPersonaDTO);

    /**
     * Se encarga de consultar el usuario por login
     * 
     * @author giovanni.velandia
     * @param login
     * @return
     */
    public UsuarioDetalleDto consultarUsuario(String login);

}
