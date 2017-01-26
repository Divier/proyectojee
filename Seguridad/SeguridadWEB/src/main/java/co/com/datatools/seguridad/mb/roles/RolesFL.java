package co.com.datatools.seguridad.mb.roles;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de consulta de Roles
 * 
 * @author claudia.rodriguez
 * 
 */
public class RolesFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "rolesFL";

    private String estadoSeleccionado;
    private String rolPadreFiltro;
    private String nombre;
    private boolean consultaRealizada;
    private List<RolDetalleDto> resultadoConsulta;
    private RolDetalleDto rolSeleccionado;
    private RolDto rolPadreSeleccionado;

    private boolean consultaPadreRealizada;
    private List<RolDetalleDto> resultadoConsultaRolPadre;

    private List<SelectItem> lRolesPadre;

    private Date fechaCreacionRolSel;
    private Date fechaSistema;

    private Date fechaInicioHistorico;
    private Date fechaFinHistorico;

    private String idAplicacion;

    // Lista de aplicaciones de seguridad
    private Map<String, String> lAplicaciones;

    public RolesFL() {
        consultaRealizada = false;
    }

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<RolDetalleDto> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<RolDetalleDto> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public RolDetalleDto getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(RolDetalleDto rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public RolDto getRolPadreSeleccionado() {
        return rolPadreSeleccionado;
    }

    public void setRolPadreSeleccionado(RolDto rolPadreSeleccionado) {
        this.rolPadreSeleccionado = rolPadreSeleccionado;
    }

    public boolean isConsultaPadreRealizada() {
        return consultaPadreRealizada;
    }

    public void setConsultaPadreRealizada(boolean consultaPadreRealizada) {
        this.consultaPadreRealizada = consultaPadreRealizada;
    }

    public List<RolDetalleDto> getResultadoConsultaRolPadre() {
        return resultadoConsultaRolPadre;
    }

    public void setResultadoConsultaRolPadre(List<RolDetalleDto> resultadoConsultaRolPadre) {
        this.resultadoConsultaRolPadre = resultadoConsultaRolPadre;
    }

    public String getRolPadreFiltro() {
        return rolPadreFiltro;
    }

    public void setRolPadreFiltro(String rolPadreFiltro) {
        this.rolPadreFiltro = rolPadreFiltro;
    }

    public List<SelectItem> getlRolesPadre() {
        return lRolesPadre;
    }

    public void setlRolesPadre(List<SelectItem> lRolesPadre) {
        this.lRolesPadre = lRolesPadre;
    }

    public Date getFechaCreacionRolSel() {
        return fechaCreacionRolSel;
    }

    public void setFechaCreacionRolSel(Date fechaCreacionRolSel) {
        this.fechaCreacionRolSel = fechaCreacionRolSel;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Date getFechaInicioHistorico() {
        return fechaInicioHistorico;
    }

    public void setFechaInicioHistorico(Date fechaInicioHistorico) {
        this.fechaInicioHistorico = fechaInicioHistorico;
    }

    public Date getFechaFinHistorico() {
        return fechaFinHistorico;
    }

    public void setFechaFinHistorico(Date fechaFinHistorico) {
        this.fechaFinHistorico = fechaFinHistorico;
    }

    public String getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public Map<String, String> getlAplicaciones() {
        return lAplicaciones;
    }

    public void setlAplicaciones(Map<String, String> lAplicaciones) {
        this.lAplicaciones = lAplicaciones;
    }

}
