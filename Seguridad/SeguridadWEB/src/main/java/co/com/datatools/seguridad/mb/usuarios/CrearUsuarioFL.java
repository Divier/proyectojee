package co.com.datatools.seguridad.mb.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de creacion de Usuarios
 * 
 * @author claudia.rodriguez
 * 
 */
public class CrearUsuarioFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(CrearUsuarioFL.class.getName());

    public static final String NOMBRE_BEAN = "crearUsuarioFL";

    private String usuario;
    private String nombres;
    private String apellidos;
    private String email;
    private boolean ldap;
    private List<SelectItem> lSiNo;
    private String valorCampoBusquedaLdap;
    private String login;
    private boolean pedirDatos;
    private boolean permiteLdap;

    private DualListModel<RolDto> roles;
    private DualListModel<GrupoDto> grupos;

    private boolean ldapValidado;

    public CrearUsuarioFL() {
        super();
        cargarEstados();
        pedirDatos = true;
        ldap = false;
        permiteLdap = false;
        ldapValidado = false;
        logger.debug("CrearUsuarioFL.CrearUsuarioFL()");

    }

    public void cargarEstados() {
        lSiNo = new ArrayList<>();
        lSiNo.add(new SelectItem(true, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_si")));
        lSiNo.add(new SelectItem(false, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_no")));

    }

    public void reset() {
        login = null;
        nombres = null;
        apellidos = null;
        email = null;
        valorCampoBusquedaLdap = null;
        usuario = null;
    }

    public boolean isPedirDatos() {
        return pedirDatos;
    }

    public void setPedirDatos(boolean pedirDatos) {
        this.pedirDatos = pedirDatos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getLdap() {
        return ldap;
    }

    public void setLdap(boolean ldap) {
        this.ldap = ldap;
    }

    public List<SelectItem> getlSiNo() {
        return lSiNo;
    }

    public void setlSiNo(List<SelectItem> lSiNo) {
        this.lSiNo = lSiNo;
    }

    public String getValorCampoBusquedaLdap() {
        return valorCampoBusquedaLdap;
    }

    public void setValorCampoBusquedaLdap(String valorCampoBusquedaLdap) {
        this.valorCampoBusquedaLdap = valorCampoBusquedaLdap;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isPermiteLdap() {
        return permiteLdap;
    }

    public void setPermiteLdap(boolean permiteLdap) {
        this.permiteLdap = permiteLdap;
    }

    public DualListModel<RolDto> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<RolDto> roles) {
        this.roles = roles;
    }

    public DualListModel<GrupoDto> getGrupos() {
        return grupos;
    }

    public void setGrupos(DualListModel<GrupoDto> grupos) {
        this.grupos = grupos;
    }

    public boolean isLdapValidado() {
        return ldapValidado;
    }

    public void setLdapValidado(boolean ldapValidado) {
        this.ldapValidado = ldapValidado;
    }

}
