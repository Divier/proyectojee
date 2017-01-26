package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

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

@Local
public interface ILSeguridadCirculemos {

    /**
     * @see IRSeguridadCirculemos#obtenerUsuarioDto()
     */
    UsuarioPersonaDTO obtenerUsuarioDto();

    /**
     * @see IRSeguridadCirculemos#obtenerUsuarioDto(String)
     */
    UsuarioPersonaDTO obtenerUsuarioDto(String login);

    /**
     * @see IRSeguridadCirculemos#consultarRoles(boolean)
     */
    List<RolDetalleDto> consultarRoles(boolean rolActivo);

    /**
     * @see IRSeguridadCirculemos#consultarUsuarios(UsuarioPersonaDTO)
     */
    List<UsuarioPersonaDTO> consultarUsuarios(UsuarioPersonaDTO usuarioPersonaDTO);

    /**
     * @see IRSeguridadCirculemos#consultarUsuarioPersona(UsuarioPersonaDTO)
     */
    List<UsuarioPersonaDTO> consultarUsuarioPersona(UsuarioPersonaDTO usuarioPersonaDTO);

    /**
     * @see IRSeguridadCirculemos#consultarUsuario(String, boolean)
     */
    UsuarioDetalleDto consultarUsuario(String login, boolean soloRolesEditables);

    /**
     * @see IRSeguridadCirculemos#actualizarPwUsuario(String)
     */
    void actualizarPwUsuario(String login, String usuarioRealizaCambio);

    /**
     * @see IRSeguridadCirculemos#obtenerOrganismoTransitoUsuario()
     */
    OrganismoTransitoDTO obtenerOrganismoTransitoUsuario();

    /**
     * @see IRSeguridadCirculemos#obtenerPais()
     */
    public PaisDTO obtenerPais();

    /**
     * @see IRSeguridadCirculemos#actualizarEstadoPwUsuario(String, EnumEstadoPassword)
     */
    void actualizarEstadoPwUsuario(String login, EnumEstadoPassword estadoPw, String usuarioRealizaCambio);

    /**
     * @see IRSeguridadCirculemos#generarLogin(co.com.datatools.c2.dto.personas.PersonaDTO)
     */
    String generarLogin(PersonaDTO personaDTO);

    /**
     * @see IRSeguridadCirculemos#validarExisteLogin(String)
     */
    boolean validarExisteLogin(String login);

    /**
     * @see IRSeguridadCirculemos#registrarUsuario(UsuarioPersonaDTO)
     */
    void registrarUsuario(UsuarioPersonaDTO usuario) throws SeguridadException, CirculemosNegocioException;

    /**
     * @see IRSeguridadCirculemos#consultarUsuarioLdap(String)
     */
    UsuarioDto consultarUsuarioLdap(String campoBusqueda) throws SeguridadException;

    /**
     * @see IRSeguridadCirculemos#consultarRolesUsuario(String)
     */
    List<String> consultarRolesUsuario(String login);

    /**
     * @see IRSeguridadCirculemos#consultarUsuario(String)
     */
    public UsuarioDetalleDto consultarUsuario(String login);

}
