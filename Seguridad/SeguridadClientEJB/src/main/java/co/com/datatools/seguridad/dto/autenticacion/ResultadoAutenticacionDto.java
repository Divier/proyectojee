package co.com.datatools.seguridad.dto.autenticacion;

import java.io.Serializable;
import java.util.List;

/**
 * Informacion del resultado de intento de autenticacion
 * 
 * @author Felipe Martinez
 */
public class ResultadoAutenticacionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Posibles valores para el resultado de la autenticacion de un usuario
     * 
     * @author Felipe Martinez
     * 
     */
    public enum EstadoAutenticacion {
        OK, FALLIDO, USUARIO_NO_ACTIVO, PASS_BLOQUEADO, PASS_VENCIDO, PASS_TEMPORAL, ALCANZA_INTENTOS_FALLIDOS, SESION_USUARIO_ABIERTA;
    }

    private UsuarioDto usuario;

    private EstadoAutenticacion estadoAutenticacion;

    private String mensajeAutenticacion;

    // Roles
    private List<String> roles;

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public EstadoAutenticacion getEstadoAutenticacion() {
        return estadoAutenticacion;
    }

    public void setEstadoAutenticacion(EstadoAutenticacion estadoAutenticacion) {
        this.estadoAutenticacion = estadoAutenticacion;
    }

    public String getMensajeAutenticacion() {
        return mensajeAutenticacion;
    }

    public void setMensajeAutenticacion(String mensajeAutenticacion) {
        this.mensajeAutenticacion = mensajeAutenticacion;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
