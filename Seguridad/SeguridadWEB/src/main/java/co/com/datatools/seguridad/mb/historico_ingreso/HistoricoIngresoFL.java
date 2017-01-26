package co.com.datatools.seguridad.mb.historico_ingreso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.IngresoDto;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.web.mb.AbstractManagedBean;

public class HistoricoIngresoFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HistoricoIngresoFL.class);

    public static final String NOMBRE_BEAN = "historicoIngresosFL";

    private Map<String, String> lAplicaciones;
    private String aplicacionSeleccionada;

    private List<SelectItem> lRecursos;
    private String recursoSeleccionado;

    private List<SelectItem> lEstadosIngreso;

    private String usuario;
    private String estadoIngresoSeleccionado;
    private Date fechaInicial;
    private Date fechaFinal;
    private Date fechaActual;

    private List<IngresoDto> resultadoConsulta;
    private boolean consultaRealizada;

    private IngresoDto ingresoSeleccionado;
    private String descripcionRecursoSel;
    private String nombreEstadoSel;
    private String nombreAppSel;

    public HistoricoIngresoFL() {
        logger.debug("HistoricoIngresoFL::HistoricoIngresoFL");

        Calendar fechaMax = UtilFecha.buildCalendar(null);
        fechaMax.set(Calendar.HOUR_OF_DAY, 23);
        fechaMax.set(Calendar.MINUTE, 59);
        fechaMax.set(Calendar.SECOND, 59);
        fechaActual = fechaMax.getTime();

    }

    public Map<String, String> getlAplicaciones() {
        return lAplicaciones;
    }

    public void setlAplicaciones(Map<String, String> lAplicaciones) {
        this.lAplicaciones = lAplicaciones;
    }

    public String getAplicacionSeleccionada() {
        return aplicacionSeleccionada;
    }

    public void setAplicacionSeleccionada(String aplicacionSeleccionada) {
        this.aplicacionSeleccionada = aplicacionSeleccionada;
    }

    public List<SelectItem> getlRecursos() {
        return lRecursos;
    }

    public void setlRecursos(List<SelectItem> lRecursos) {
        this.lRecursos = lRecursos;
    }

    public String getRecursoSeleccionado() {
        return recursoSeleccionado;
    }

    public void setRecursoSeleccionado(String recursoSeleccionado) {
        this.recursoSeleccionado = recursoSeleccionado;
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

    public List<SelectItem> getlEstadosIngreso() {
        return lEstadosIngreso;
    }

    public void setlEstadosIngreso(List<SelectItem> lEstadosIngreso) {
        this.lEstadosIngreso = lEstadosIngreso;
    }

    public String getEstadoIngresoSeleccionado() {
        return estadoIngresoSeleccionado;
    }

    public void setEstadoIngresoSeleccionado(String estadoIngresoSeleccionado) {
        this.estadoIngresoSeleccionado = estadoIngresoSeleccionado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<IngresoDto> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<IngresoDto> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    public IngresoDto getIngresoSeleccionado() {
        return ingresoSeleccionado;
    }

    public void setIngresoSeleccionado(IngresoDto ingresoSeleccionado) {
        this.ingresoSeleccionado = ingresoSeleccionado;
    }

    public String getDescripcionRecursoSel() {
        for (SelectItem itemRecurso : lRecursos) {
            if (itemRecurso.getValue().equals(recursoSeleccionado)) {
                descripcionRecursoSel = itemRecurso.getLabel();
                break;
            }
        }
        return descripcionRecursoSel;
    }

    public void setDescripcionRecursoSel(String descripcionRecursoSel) {
        this.descripcionRecursoSel = descripcionRecursoSel;
    }

    public String getNombreEstadoSel() {
        for (SelectItem itemEstado : lEstadosIngreso) {
            if (itemEstado.getValue().toString().equals(estadoIngresoSeleccionado)) {
                nombreEstadoSel = itemEstado.getLabel();
                break;
            }
        }
        return nombreEstadoSel;
    }

    public void setNombreEstadoSel(String nombreEstadoSel) {
        this.nombreEstadoSel = nombreEstadoSel;
    }

    public String getNombreAppSel() {
        for (Entry<String, String> entry : lAplicaciones.entrySet()) {
            if (aplicacionSeleccionada.equals(entry.getValue())) {
                nombreAppSel = entry.getKey();
                break;
            }
        }
        return nombreAppSel;
    }

    public void setNombreAppSel(String nombreAppSel) {
        this.nombreAppSel = nombreAppSel;
    }

}
