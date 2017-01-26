package co.com.datatools.seguridad.mb.instalador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;
import co.com.datatools.seguridad.dto.comun.InstalacionDto;
import co.com.datatools.seguridad.dto.comun.LlaveValorDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRAutenticacion;
import co.com.datatools.seguridad.interfaces.IRCatalogosSeguridad;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.seguridad.util.InicioAppSeguridad;
import co.com.datatools.seguridad.util.SeguridadErrorHandler;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.ObjectToXML;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Bean de control de acceso al servicio de instalacion del modulo de seguridad
 * 
 * @author felipe.martinez
 */
@ManagedBean
@SessionScoped
public class InstaladorMB extends AbstractManagedBean {
    private static final long serialVersionUID = 1L;

    private static final String NOMBRE_BUNDLE_PARAMETROS = "parametrosBase";
    private static final String NOMBRE_BUNDLE_INSTALADOR = "mensajesInstalador";

    @EJB
    private IRCatalogosSeguridad catalogosEjb;

    @EJB
    private IRAutenticacion autenticacionEjb;

    @EJB
    private IRCatalogosSeguridad catalogoSeguridadEJB;

    @ManagedProperty(value = "#{inicioAppSeguridad}")
    private InicioAppSeguridad initAppSegMB;

    /**
     * Correo electronico del usuario SUPER-ADMIN
     */
    private String email;
    /**
     * Nombres del usuario SUPER-ADMIN
     */
    private String nombres;
    /**
     * Apellidos del usuario SUPER-ADMIN
     */
    private String apellidos;
    /**
     * Contraseña ingresada para el usuario SUPER-ADMIN
     */
    private String password;
    /**
     * Confirmacion de la contraseña ingresada para el usuario SUPER-ADMIN
     */
    private String confirmacionPassword;
    /**
     * Valor para el parametro de niveles de herencia de roles
     */
    private String nivelHerencia;
    /**
     * Valor para el parametro que indica si se permitira autenticacion con LDAP o no
     */
    private boolean permiteLdap;
    /**
     * Almacena el valor del parametro URL_APLICACION
     */
    private String urlProvider;
    /**
     * Almacena el valor del parametro SECURITY_PRINCIPAL
     */
    private String usuarioConexion;
    /**
     * Almacena el valor del parametro SECURITY_CREDENTIALS
     */
    private String pwConexionLdap;
    /**
     * Almacena el valor del parametro USERS_PATH
     */
    private String rutaUsuarios;
    /**
     * Almacena el valor del parametro AUTHORIZED_GROUP_PATH
     */
    private String rutaGrupos;
    /**
     * Almacena el valor del parametro NOMBRE_ATRIBUTO_IDUSUARIO_LDAP
     */
    private String atrIdUsuarioLdap;
    /**
     * Almacena el valor del parametro NOMBRE_ATRIBUTO_MIEMBRODE_LDAP
     */
    private String atrGrupoUsuario;
    /**
     * Almacena el valor del parametro NOMBRE_ATRIBUTO_LOGIN_LDAP
     */
    private String atrLoginUsuario;
    /**
     * Almacena el valor del parametro NOMBRE_ATRIBUTO_APELLIDOS_LDAP
     */
    private String atrApellidos;
    /**
     * Almacena el valor del parametro NOMBRE_ATRIBUTO_NOMBRES_LDAP
     */
    private String atrNombres;
    /**
     * Almacena el valor del parametro NOMBRE_ATRIBUTO_CORREO_LDAP
     */
    private String atrCorreoLdap;

    private List<SelectItem> lSiNo;
    /**
     * Almacena el parametro URL_APLICACION para cada aplicacion disponible en el sistema en la base de datos
     */
    private List<LlaveValorDto> aplicaciones;

    private String mensajePruebaConexion;

    private Pattern patronNumericoEntero = Pattern.compile("[0-9]+");

    public InstaladorMB() {
        cargarListaSiNo();
    }

    public void validarIngresoInstalador() {
        if (initAppSegMB.instaladorEjecutado(null)) {
            try {
                super.getFacesContext().getExternalContext()
                        .redirect(super.getHttpRequest().getContextPath() + ConstantesManagedBean.URL_MAIN);
            } catch (IOException e) {
                throw new RuntimeException("Inesperado", e);
            }
        }
    }

    public void cargarListaSiNo() {
        lSiNo = new ArrayList<>();
        lSiNo.add(new SelectItem(true, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_si")));
        lSiNo.add(new SelectItem(false, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_no")));
    }

    @PostConstruct
    private void consultarAplicaciones() {
        Map<String, String> aplicacionesDisponibles = catalogoSeguridadEJB.consultarAplicaciones();
        aplicaciones = new ArrayList<LlaveValorDto>();
        for (Entry<String, String> entry : aplicacionesDisponibles.entrySet()) {
            LlaveValorDto llaveValorDto = new LlaveValorDto(entry.getKey(), "");
            aplicaciones.add(llaveValorDto);
        }
    }

    /**
     * Guarda los parametros de configuracion ingresados por el usuario SUPER-ADMIN
     */
    public void guardar() {
        Matcher matcher = patronNumericoEntero.matcher(nivelHerencia);// Validar que los niveles de herencia ingresados sea numerico entero
        if (!matcher.matches()) {
            // Mostrar mensaje localizado por tipo de parametro invalido
            ResourceBundle bundleGeneral = getBundle(NOMBRE_BUNDLE_PARAMETROS);
            getFacesContext()
                    .addMessage(
                            "form-content:niveles",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, null, bundleGeneral
                                    .getString("msg_error_tipo_dato")));
        } else {
            // Validar que la contraseña y la confirmacion de la contraseña son iguales
            if (!password.equals(confirmacionPassword)) {
                addErrorMessage(NOMBRE_BUNDLE_INSTALADOR, "msg_confirmacion_pw");
            } else {
                InstalacionDto instalacionDto = new InstalacionDto();
                instalacionDto.setNombresSuperAdmin(nombres);
                instalacionDto.setApellidosSuperAdmin(apellidos);
                instalacionDto.setPwSuperAdmin(password);
                instalacionDto.setEmailSuperAdmin(email);
                instalacionDto.setParametrosConfiguracion(resolverParametrosActualizar());
                try {
                    catalogosEjb.guardarDatosInstalacion(instalacionDto);
                } catch (SeguridadException e) {
                    SeguridadErrorHandler.handleException(e);
                }
            }
        }
    }

    /**
     * Crea un listado con los nombres de los parametros de configuracion que se deben actualizar y sus respectivos valores
     * 
     * @return Lista con los parametros a actualizar
     */
    private List<ParametroSeguridadDto> resolverParametrosActualizar() {
        List<ParametroSeguridadDto> parametrosConfiguracion = new ArrayList<ParametroSeguridadDto>();
        parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.NIVELES_HERENCIA_ROLES.toString(),
                nivelHerencia));
        String permiteAutenticacionLdap;
        if (permiteLdap) {
            permiteAutenticacionLdap = ConstantesSeguridad.VALOR_SI;
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.PROVIDER_URL.toString(), urlProvider));
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.SECURITY_PRINCIPAL.toString(),
                    usuarioConexion));
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.SECURITY_CREDENTIALS.toString(),
                    pwConexionLdap));
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.USERS_PATH.toString(), rutaUsuarios));
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.AUTHORIZED_GROUP_PATH.toString(),
                    rutaGrupos));
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.NOMBRE_ATRIBUTO_IDUSUARIO_LDAP
                    .toString(), atrIdUsuarioLdap));
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.NOMBRE_ATRIBUTO_MIEMBRODE_LDAP
                    .toString(), atrGrupoUsuario));
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.NOMBRE_ATRIBUTO_LOGIN_LDAP.toString(),
                    atrLoginUsuario));
            parametrosConfiguracion.add(new ParametroSeguridadDto(
                    EnumParametro.NOMBRE_ATRIBUTO_NOMBRES_LDAP.toString(), atrNombres));
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.NOMBRE_ATRIBUTO_APELLIDOS_LDAP
                    .toString(), atrApellidos));
            parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.NOMBRE_ATRIBUTO_CORREO_LDAP.toString(),
                    atrCorreoLdap));
        } else {
            permiteAutenticacionLdap = ConstantesSeguridad.VALOR_NO;
        }
        parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.PERMITE_AUTENTICACION_LDAP.toString(),
                permiteAutenticacionLdap));

        Map<String, String> urlsApp = new HashMap<String, String>();
        for (LlaveValorDto urlApp : aplicaciones) {
            urlsApp.put(urlApp.getId(), urlApp.getValor());
        }
        parametrosConfiguracion.add(new ParametroSeguridadDto(EnumParametro.URL_APLICACION.toString(), ObjectToXML
                .objectToXml(urlsApp)));
        return parametrosConfiguracion;
    }

    public void probarConexionLDAP() {
        boolean autenticado = autenticacionEjb.probarConexionLDAP(urlProvider, usuarioConexion, pwConexionLdap);
        if (autenticado) {
            mensajePruebaConexion = getBundle(NOMBRE_BUNDLE_INSTALADOR).getString("msg_conexion_exitosa");
        } else {
            mensajePruebaConexion = getBundle(NOMBRE_BUNDLE_INSTALADOR).getString("msg_conexion_fallida");
        }
        RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show()");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InicioAppSeguridad getInitAppSegMB() {
        return initAppSegMB;
    }

    public void setInitAppSegMB(InicioAppSeguridad initAppSegMB) {
        this.initAppSegMB = initAppSegMB;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmacionPassword() {
        return confirmacionPassword;
    }

    public void setConfirmacionPassword(String confirmacionPassword) {
        this.confirmacionPassword = confirmacionPassword;
    }

    public boolean isPermiteLdap() {
        return permiteLdap;
    }

    public void setPermiteLdap(boolean permiteLdap) {
        this.permiteLdap = permiteLdap;
    }

    public List<SelectItem> getlSiNo() {
        return lSiNo;
    }

    public void setlSiNo(List<SelectItem> lSiNo) {
        this.lSiNo = lSiNo;
    }

    public String getNivelHerencia() {
        return nivelHerencia;
    }

    public void setNivelHerencia(String nivelHerencia) {
        this.nivelHerencia = nivelHerencia;
    }

    public IRCatalogosSeguridad getCatalogosEjb() {
        return catalogosEjb;
    }

    public void setCatalogosEjb(IRCatalogosSeguridad catalogosEjb) {
        this.catalogosEjb = catalogosEjb;
    }

    public String getUrlProvider() {
        return urlProvider;
    }

    public void setUrlProvider(String urlProvider) {
        this.urlProvider = urlProvider;
    }

    public String getUsuarioConexion() {
        return usuarioConexion;
    }

    public void setUsuarioConexion(String usuarioConexion) {
        this.usuarioConexion = usuarioConexion;
    }

    public String getPwConexionLdap() {
        return pwConexionLdap;
    }

    public void setPwConexionLdap(String pwConexionLdap) {
        this.pwConexionLdap = pwConexionLdap;
    }

    public String getRutaUsuarios() {
        return rutaUsuarios;
    }

    public void setRutaUsuarios(String rutaUsuarios) {
        this.rutaUsuarios = rutaUsuarios;
    }

    public String getRutaGrupos() {
        return rutaGrupos;
    }

    public void setRutaGrupos(String rutaGrupos) {
        this.rutaGrupos = rutaGrupos;
    }

    public String getAtrIdUsuarioLdap() {
        return atrIdUsuarioLdap;
    }

    public void setAtrIdUsuarioLdap(String atrIdUsuarioLdap) {
        this.atrIdUsuarioLdap = atrIdUsuarioLdap;
    }

    public String getAtrGrupoUsuario() {
        return atrGrupoUsuario;
    }

    public void setAtrGrupoUsuario(String atrGrupoUsuario) {
        this.atrGrupoUsuario = atrGrupoUsuario;
    }

    public String getAtrLoginUsuario() {
        return atrLoginUsuario;
    }

    public void setAtrLoginUsuario(String atrLoginUsuario) {
        this.atrLoginUsuario = atrLoginUsuario;
    }

    public String getAtrApellidos() {
        return atrApellidos;
    }

    public void setAtrApellidos(String atrApellidos) {
        this.atrApellidos = atrApellidos;
    }

    public String getAtrNombres() {
        return atrNombres;
    }

    public void setAtrNombres(String atrNombres) {
        this.atrNombres = atrNombres;
    }

    public String getAtrCorreoLdap() {
        return atrCorreoLdap;
    }

    public void setAtrCorreoLdap(String atrCorreoLdap) {
        this.atrCorreoLdap = atrCorreoLdap;
    }

    public List<LlaveValorDto> getAplicaciones() {
        return aplicaciones;
    }

    public void setAplicaciones(List<LlaveValorDto> aplicaciones) {
        this.aplicaciones = aplicaciones;
    }

    public String getMensajePruebaConexion() {
        return mensajePruebaConexion;
    }

    public void setMensajePruebaConexion(String mensajePruebaConexion) {
        this.mensajePruebaConexion = mensajePruebaConexion;
    }

}
