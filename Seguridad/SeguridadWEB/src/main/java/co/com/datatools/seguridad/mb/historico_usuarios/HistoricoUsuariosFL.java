package co.com.datatools.seguridad.mb.historico_usuarios;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoUsuarioDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

public class HistoricoUsuariosFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HistoricoUsuariosFL.class);

    public static final String NOMBRE_BEAN = "historicoUsuariosFL";

    /**
     * Id del usuario para el cual se consulta el historico de cambios
     */
    private Integer idUsuario;
    /**
     * Login del usuario para el cual se consulta el historico de cambios
     */
    private String login;
    /**
     * Fecha inicial del rango usado para consultar el historico de cambios
     */
    private Date fechaInicial;
    /**
     * Fecha final del rango usado para consultar el historico de cambios
     */
    private Date fechaFinal;
    /**
     * Lista con los resultados de la consulta del historico de cambios del usuario
     */
    private List<HistoricoUsuarioDto> resultadoConsulta;
    /**
     * Indica si la accion de consulta ya ha sido realizada por el usuario desde la interfaz, se usa para controlar la visualizacion del mensaje que
     * indica que no se encontraron resultados
     */
    private boolean consultaRealizada;
    /**
     * Dto del historico de cambio seleccionado en la interfaz para el cual se puede consultar su detalle
     */
    private HistoricoUsuarioDto historicoSeleccionado;
    /**
     * Roles asignados al usuario en el historico del cambio y que son visualizados en el detalle
     */
    private List<RolDetalleDto> rolesAsignados;
    /**
     * Grupos asignados al usuario en el historico del cambio y que son visualizados en el detalle
     */
    private List<GrupoDto> gruposAsignados;

    /**
     * Rol seleccionado en el detalle del historico de cambios y para el cual se desean conocer los permisos asignados
     */
    private RolDto rolSeleccionado;

    /**
     * Fecha de cracion del usuario para el cual se consulta el historico y que es usada como fecha minima permitida al seleccionar la fecha desde la
     * cual se quiere consultar el historico
     */
    private Date fechaCreacionUsuario;
    /**
     * Fecha actual del sistema usada como fecha maxima permitida al seleccionar la fecha hasta la cual se quiere consultar el historico
     */
    private Date fechaActualMaxima;

    private String msgNoResultados;

    public HistoricoUsuariosFL() {
        logger.debug("HistoricoUsuariosFL::HistoricoUsuariosFL");
    }

    /**
     * Metodo para asignar los filtros de busqueda de la consulta del historico de cambios del usuario. Este metodo es invocado cuando se inicia el
     * flujo y desde el flujo se envia los argumentos al metodo
     * 
     * @param idUsuario
     *            Id del usuario para el cual se consulta el historico de cambios
     * @loginUsuario Login del usuario para el cual se consulta el historico de cambios
     * @param fechaInicial
     *            Fecha inicial del rango usado para consultar el historico de cambios
     * @param fechaFinal
     *            Fecha final del rango usado para consultar el historico de cambios
     */

    public void inicializarFiltrosBusqueda(Integer idUsuario, String loginUsuario, Date fechaInicial, Date fechaFinal) {
        this.idUsuario = idUsuario;
        this.fechaInicial = fechaInicial;
        setLogin(loginUsuario);
        this.fechaFinal = fechaFinal;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public List<HistoricoUsuarioDto> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<HistoricoUsuarioDto> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    public HistoricoUsuarioDto getHistoricoSeleccionado() {
        return historicoSeleccionado;
    }

    public void setHistoricoSeleccionado(HistoricoUsuarioDto historicoSeleccionado) {
        this.historicoSeleccionado = historicoSeleccionado;
    }

    public List<RolDetalleDto> getRolesAsignados() {
        return rolesAsignados;
    }

    public void setRolesAsignados(List<RolDetalleDto> rolesAsignados) {
        this.rolesAsignados = rolesAsignados;
    }

    public List<GrupoDto> getGruposAsignados() {
        return gruposAsignados;
    }

    public void setGruposAsignados(List<GrupoDto> gruposAsignados) {
        this.gruposAsignados = gruposAsignados;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RolDto getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(RolDto rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public Date getFechaCreacionUsuario() {
        return fechaCreacionUsuario;
    }

    public void setFechaCreacionUsuario(Date fechaCreacionUsuario) {
        this.fechaCreacionUsuario = fechaCreacionUsuario;
    }

    public Date getFechaActualMaxima() {
        return fechaActualMaxima;
    }

    public void setFechaActualMaxima(Date fechaActualMaxima) {
        this.fechaActualMaxima = fechaActualMaxima;
    }

    public String getMsgNoResultados() {
        return msgNoResultados;
    }

    public void setMsgNoResultados(String msgNoResultados) {
        this.msgNoResultados = msgNoResultados;
    }

}
