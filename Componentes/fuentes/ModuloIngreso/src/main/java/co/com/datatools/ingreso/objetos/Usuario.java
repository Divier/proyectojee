package co.com.datatools.ingreso.objetos;

import java.io.Serializable;
import java.security.Principal;
import java.sql.Timestamp;

public class Usuario implements Serializable, Principal {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String login;
    private String password;
    private boolean ldap;
    private EstadoUsuario estadoUsuario;
    private EstadoPassword estadoPassword;
    private Timestamp fechaBloqueoPassword;

    public enum EstadoUsuario {
        ACTIVO(1), //
        INACTIVO(2), //
        CANCELADO(3), //
        ;

        private int identificador;

        private EstadoUsuario(int identificador) {
            this.identificador = identificador;
        }

        public int getId() {
            return identificador;
        }

        public static EstadoUsuario obtenerValor(int identificador) {
            for (EstadoUsuario estado : EstadoUsuario.values()) {
                if (estado.getId() == identificador) {
                    return estado;
                }
            }
            return null;
        }

    }

    public enum EstadoPassword {
        ACTIVO(1), //
        VENCIDO(2, EnumEstadoIngreso.FALLO_PW_VENCIDO), //
        TEMPORAL(3, EnumEstadoIngreso.FALLO_PW_TEMPORAL), //
        BLOQUEADO(4, EnumEstadoIngreso.FALLO_PW_BLOQUEADO), //
        ;

        private int identificador;
        private EnumEstadoIngreso enumEstadoIngreso;

        private EstadoPassword(int identificador, EnumEstadoIngreso estadoIngreso) {
            this.identificador = identificador;
            this.enumEstadoIngreso = estadoIngreso;
        }

        private EstadoPassword(int identificador) {
            this.identificador = identificador;
        }

        public int getId() {
            return identificador;
        }

        public EnumEstadoIngreso getEstadoIngreso() {
            return this.enumEstadoIngreso;
        }

        public static EstadoPassword obtenerValor(int identificador) {
            for (EstadoPassword estado : EstadoPassword.values()) {
                if (estado.getId() == identificador) {
                    return estado;
                }
            }
            return null;
        }
    }

    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLdap() {
        return ldap;
    }

    public void setLdap(boolean ldap) {
        this.ldap = ldap;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public EstadoPassword getEstadoPassword() {
        return estadoPassword;
    }

    public void setEstadoPassword(EstadoPassword estadoPassword) {
        this.estadoPassword = estadoPassword;
    }

    @Override
    public String getName() {
        return login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFechaBloqueoPassword() {
        return fechaBloqueoPassword;
    }

    public void setFechaBloqueoPassword(Timestamp fechaBloqueoPassword) {
        this.fechaBloqueoPassword = fechaBloqueoPassword;
    }

}
