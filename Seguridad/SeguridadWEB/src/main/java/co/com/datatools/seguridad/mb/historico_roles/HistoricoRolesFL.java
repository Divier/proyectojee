package co.com.datatools.seguridad.mb.historico_roles;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoRolDto;
import co.com.datatools.seguridad.mb.roles.RecursoDetallePermisos;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Dto para el flujo historico-roles
 * 
 * @author claudia.rodriguez
 * 
 */
public class HistoricoRolesFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HistoricoRolesFL.class);

    public static final String NOMBRE_BEAN = "historicoRolesFL";

    /**
     * Id del rol para el cual se consulta el historico
     */
    private Integer idRol;
    /**
     * Nombre del rol para el cual se consulta el historico
     */
    private String nombreRol;
    /**
     * Fecha inicial del rango usado para consultar el historico de cambios
     */
    private Date fechaInicial;
    /**
     * Fecha final del rango usado para consultar el historico de cambios
     */
    private Date fechaFinal;

    /**
     * Fecha del sistema cuando se solicita la consulta del historico
     */
    private Date fechaActual;
    /**
     * Lista con los resultados de la consulta del historico de cambios del rol
     */
    private List<HistoricoRolDto> resultadoConsulta;
    /**
     * Indica si la accion de consulta ya ha sido realizada por el usuario desde la interfaz, se usa para controlar la visualizacion del mensaje que
     * indica que no se encontraron resultados
     */
    private boolean consultaRealizada;
    /**
     * Dto del historico de cambio seleccionado en la interfaz para el cual se puede consultar su detalle
     */
    private HistoricoRolDto historicoSeleccionado;
    /**
     * Listado de permisos asignados al rol,es visualizado en el detalle del historico
     */
    private List<RecursoDetallePermisos> permisosAsignados;
    /**
     * Listado de permisos heredados por el rol, es visualizado en el detalle del historico
     */
    private List<RecursoDetallePermisos> permisosHeredados;
    /**
     * Indica si se deben validar o no ciertas reglas de negocio sobre las fechas utilizadas para realizar la consulta
     */
    private boolean validacionFechas = true;
    /**
     * Indica si se deben deshabilitar o no, los campos de ingreso de los fitros de fecha en la pagina de consulta del historico
     */
    private boolean deshabilitaIngresofechas;

    private String msgNoResultados;

    /**
     * Indica si la consulta de historico no trajo resultados con los filtros ingresados, por tanto muestra el historico vigente para el rol
     */
    private boolean muestraHistoricoVigente;

    public HistoricoRolesFL() {
        logger.debug("HistoricoRolesFL::HistoricoRolesFL");
    }

    /**
     * Asigna a los atributos de la clase idRol,fechaInicial y fechaFinal los valores recibidos como parametros, los cuales son usados como filtros
     * para consultar el historico
     * 
     * @param idRol
     *            Id del rol para el cual se consulta el historico
     * @param fechaInicial
     *            Fecha inicial del rango usado para consultar el historico de cambios
     * @param fechaFinal
     *            Fecha final del rango usado para consultar el historico de cambios
     * @param deshabilitarFechas
     *            Indica si se deben deshabilitar o no, los campos de ingreso de los fitros de fecha en la pagina de consulta del historico
     */
    public void inicializarFiltrosBusqueda(Integer idRol, Date fechaInicial, Date fechaFinal, boolean deshabilitarFechas) {
        this.idRol = idRol;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        deshabilitaIngresofechas = deshabilitarFechas;
        if (deshabilitarFechas) {
            // Las fechas se deshabilitan cuando el historico de roles es invocado desde el historico de usuarios con unas fechas fijas, por tanto no
            // se deben validar los dias existentes entre las fechas de filtro contra el parametro de numero de dias
            validacionFechas = false;
        }
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<HistoricoRolDto> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<HistoricoRolDto> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    public HistoricoRolDto getHistoricoSeleccionado() {
        return historicoSeleccionado;
    }

    public void setHistoricoSeleccionado(HistoricoRolDto historicoSeleccionado) {
        this.historicoSeleccionado = historicoSeleccionado;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public List<RecursoDetallePermisos> getPermisosAsignados() {
        return permisosAsignados;
    }

    public void setPermisosAsignados(List<RecursoDetallePermisos> permisosAsignados) {
        this.permisosAsignados = permisosAsignados;
    }

    public List<RecursoDetallePermisos> getPermisosHeredados() {
        return permisosHeredados;
    }

    public void setPermisosHeredados(List<RecursoDetallePermisos> permisosHeredados) {
        this.permisosHeredados = permisosHeredados;
    }

    public boolean isValidacionFechas() {
        return validacionFechas;
    }

    public void setValidacionFechas(boolean validacionFechas) {
        this.validacionFechas = validacionFechas;
    }

    public boolean isDeshabilitaIngresofechas() {
        return deshabilitaIngresofechas;
    }

    public void setDeshabilitaIngresofechas(boolean deshabilitaIngresofechas) {
        this.deshabilitaIngresofechas = deshabilitaIngresofechas;
    }

    public String getMsgNoResultados() {
        return msgNoResultados;
    }

    public void setMsgNoResultados(String msgNoResultados) {
        this.msgNoResultados = msgNoResultados;
    }

    public boolean isMuestraHistoricoVigente() {
        return muestraHistoricoVigente;
    }

    public void setMuestraHistoricoVigente(boolean muestraHistoricoVigente) {
        this.muestraHistoricoVigente = muestraHistoricoVigente;
    }

}
