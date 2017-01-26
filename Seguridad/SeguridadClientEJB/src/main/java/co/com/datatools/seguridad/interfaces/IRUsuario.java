package co.com.datatools.seguridad.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoUsuarioDto;
import co.com.datatools.seguridad.dto.autenticacion.IngresoDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.dto.comun.ConsultaIngresoDto;
import co.com.datatools.seguridad.dto.comun.InfoAutorizacion;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.utilidades.EnumEstadoPassword;

/**
 * Interfaz remota EJB USuario
 * 
 * @author Felipe Martinez
 */
@Remote
public interface IRUsuario {
    /**
     * Permite hacer la consulta de todos los usuarios, excluyendo el usuario con el rol super-admin, de acuerdo a los filtros enviados
     * 
     * @param usuarioDto
     *            Dto con los filtros de consulta
     * 
     * @param idsUsuario
     * @return listado de usuarios encontradados,exluyendo el usuario con rol super-admin, si no encuentra nada retorna lista vacia, null safe
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-10)
     */
    List<UsuarioDto> consultarUsuarios(UsuarioDetalleDto usuarioDto, List<Integer> idsUsuario);

    /**
     * Consulta la informacion de detalle de un usuario,el parametro soloRolesEditables indica si en los roles del usuario se excluye el rol PUBLICO y
     * SUPER-ADMIN(en caso que lo tenga)
     * 
     * @param login
     *            identificador del usuario
     * 
     * @param soloRolesEditables
     *            Indica si solo se consultaran los roles editables que tenga el usuario, es decir excluir los roles PUBLICO y SUPER-ADMIN(en caso que
     *            lo tenga) dentro de los roles del usuario
     * @return objeto con toda la informacion del usuario, null si no existe
     * 
     * @author Felipe Martinez
     */
    UsuarioDetalleDto consultarUsuario(String login, boolean soloRolesEditables);

    /**
     * Permite la creacion de un usuario
     * 
     * @param usuario
     * @return Id del usuario creado
     * @throws SeguridadException
     *             US0004:Ya existe un usuario con el correo electronico enviado<br>
     *             US0002:Ya existe el usuario con el login enviado
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-10)
     */
    Integer registrarUsuario(final UsuarioDetalleDto usuario) throws SeguridadException;

    /**
     * Permite eliminar un usuario con el id dado
     * 
     * @param idUsuario
     *            Identificador del usuario a eliminar
     * 
     * @throws SeguridadException
     *             US0003: El Usuario tiene registros de ingresos
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-10)
     */
    void eliminarUsuario(final Integer idUsuario) throws SeguridadException;

    /**
     * Permite la actualizacion de un usuario
     * 
     * @param usuario
     *            Usuario con los datos a modificar
     * @throws SeguridadException
     *             US0004:Ya existe un usuario con el correo electronico enviado
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-11)
     */
    void actualizarUsuario(final UsuarioDetalleDto usuario) throws SeguridadException;

    /**
     * Retorna los nombres de los roles asignados a un usuario
     * 
     * @param login
     *            identificador del usuario al q estan asociados
     * @return Lista con los nombres de los roles del usuario
     * 
     * @author Felipe Martinez
     */
    List<String> consultarRolesUsuario(final String login);

    /**
     * Consulta el usuario en el LDAP cuyo identificador coincida con el campo de busqueda enviado
     * 
     * @param campoBusqueda
     *            Valor del campo por el que se realiza la busqueda en el campo ID de usuario en LDAP
     * 
     * @return Dto del usuario encontrado, sino lo encuentra retorna null
     * 
     * @throws SeguridadException
     *             US0001:No se puede realizar la conexion con el LDAP US0006: Se encuentra mas de un resultado de usuario en el LDAP
     * 
     * @author claudia.rodriguez
     */
    UsuarioDto consultarUsuarioLdap(String campoBusqueda) throws SeguridadException;

    /**
     * Modifica la contraseña de un usuario asignado una contraseña nueva generada por el sistema y envia correo al usuario notificando la
     * modificacion
     * 
     * @param login
     *            Login del usuario al que se le modifica la contraseña
     * 
     * @author claudia.rodriguez
     */
    void actualizarPwUsuario(String login);

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
     * Genera un login basado en los nombres y apellidos del usuario, si ya existe el login lo concatena con dos numeros aleatorios
     * 
     * @param nombresUsuario
     *            Nombres del usuario
     * @param apellidosUsuario
     *            Apellidos del usuario
     * @return login generado
     * @author claudia.rodriguez
     */
    String generarLogin(String nombresUsuario, String apellidosUsuario);

    /**
     * Actualiza el estado de la contraseña de un usuario
     * 
     * @param login
     *            login del usuario al que se le actualiza el estado de la contraseña
     * @param estadoPw
     *            Valor de la enumeracion con el estado de la contraseña
     * 
     * @param usuarioRealizaCambio
     *            Login del usuario autenticado que genera el cambio
     * 
     * @author claudia.rodriguez
     */
    void actualizarEstadoPwUsuario(String login, EnumEstadoPassword estadoPw, String usuarioRealizaCambio);

    /**
     * Consulta los historicos de cambios de un usuario por Id del usuario y un rango de fechas
     * 
     * @param idUsuario
     *            Id del usuario al cual se le consulta el historico
     * @param fechaInicial
     *            Fecha desde la cual se consulta el historico
     * @param fechaFinal
     *            Fecha hasta la cual se consulta el historico
     * @return Listado con los historicos de cambios del usuario
     * 
     * @author claudia.rodriguez
     * 
     */
    List<HistoricoUsuarioDto> consultarHistoricoUsuario(int idUsuario, Date fechaInicial, Date fechaFinal);

    /**
     * Obtiene la fecha de creacion de un usuario consultando la fecha de inicio del primer historico
     * 
     * @param idUsuario
     *            Id del usuario a consultar la fecha de creacion
     * @return Fecha de creacion del usuario cuyo id es enviado como parametro
     * 
     * @author claudia.rodriguez
     */
    Date consultarFechaCreacionUsuario(Integer idUsuario);

    /**
     * Realiza la consulta de los ingresos que cumpla con los filtros enviados en el DTO parametro
     * 
     * @param consultaIngresoDto
     *            Dto con los filtros de consulta
     * @return Lista con los ingresos conusltados, si no hay resultados retorna la lista vacia
     * 
     * @author claudia.rodriguez
     */
    List<IngresoDto> consultarHistoricoIngresoUsuario(ConsultaIngresoDto consultaIngresoDto);

    /**
     * Obtiene un listado con actividad del ingreso enviado como parametro, el dto enviado debe contener los datos del xsd del historico y tambien
     * contenido en el atributo xmlActividadIngreso
     * 
     * @param ingresoDto
     *            Ingreso para el cual se obtendra la actividad de ingreso
     * @return Listado de objetos InfoAutorizacion correspondiente a la actividad del ingreso
     * 
     * @author claudia.rodriguez
     */
    List<InfoAutorizacion> obtenerDetalleActividadIngreso(IngresoDto ingresoDto);

    /**
     * Consulta el usuario por login
     * 
     * @author giovanni.velandia
     * @param login
     * @return
     */
    public UsuarioDetalleDto consultarUsuario(String login);

}
