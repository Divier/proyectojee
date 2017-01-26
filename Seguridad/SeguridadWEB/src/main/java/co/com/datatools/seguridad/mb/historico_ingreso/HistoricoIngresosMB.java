package co.com.datatools.seguridad.mb.historico_ingreso;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;
import co.com.datatools.seguridad.dto.autenticacion.IngresoDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.dto.comun.ConsultaIngresoDto;
import co.com.datatools.seguridad.dto.comun.InfoAutorizacion;
import co.com.datatools.seguridad.interfaces.IRCatalogosSeguridad;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.interfaces.IRRecursoOperacion;
import co.com.datatools.seguridad.interfaces.IRUsuario;
import co.com.datatools.seguridad.mb.general.AutenticacionWebMB;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.seguridad.utilidades.EnumEstadoIngresoUsuario;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

@ManagedBean
@SessionScoped
public class HistoricoIngresosMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HistoricoIngresosMB.class);

    private static final String NOMBRE_BUNDLE_HISTORICO_INGRESOS = "mensajesHistoricoIngresos";

    @EJB
    private IRCatalogosSeguridad catalogoSeguridadEjb;

    @EJB
    private IRRecursoOperacion recursoOperacionEjb;

    @EJB
    private IRParametrosSeguridad parametrosEjb;

    @EJB
    private IRUsuario usuarioEjb;

    @ManagedProperty(value = "#{autenticacionWebMB}")
    private AutenticacionWebMB autenticacionWebMB;

    public HistoricoIngresosMB() {
        logger.debug("HistoricoIngresosMB::HistoricoIngresosMB");

    }

    /**
     * Inicializa los valores de los componentes en la interfaz
     */

    public void init() {
        logger.debug("HistoricoIngresosMB::init");
        cargarListaAplicaciones();
        cargarListaEstadosIngreso();
    }

    /**
     * Carga la lista de aplicaciones consultando las aplicaciones existentes
     */
    public void cargarListaAplicaciones() {
        logger.debug("HistoricoIngresosMB::cargarListaAplicaciones");
        HistoricoIngresoFL historicoIngresoFL = findFlowObject(HistoricoIngresoFL.class, HistoricoIngresoFL.NOMBRE_BEAN);
        if (historicoIngresoFL.getlAplicaciones() == null) {
            Map<String, String> lAplicaciones = new HashMap<>();
            lAplicaciones = catalogoSeguridadEjb.consultarAplicaciones();
            historicoIngresoFL.setlAplicaciones(lAplicaciones);
        }
    }

    /**
     * Carga la lista de recursos de acuerdo a la aplicacion seleccionada
     */
    public void cargarListaRecursos() {
        logger.debug("HistoricoIngresosMB::cargarListaRecursos");
        HistoricoIngresoFL historicoIngresoFL = findFlowObject(HistoricoIngresoFL.class, HistoricoIngresoFL.NOMBRE_BEAN);
        if (StringUtils.isNotBlank(historicoIngresoFL.getAplicacionSeleccionada())) {
            RecursoDto recursoFiltros = new RecursoDto();
            recursoFiltros.setIdAplicacion(historicoIngresoFL.getAplicacionSeleccionada());
            List<RecursoDto> recursosApp = recursoOperacionEjb.consultarRecursos(recursoFiltros, true);
            List<SelectItem> recursos = new ArrayList<SelectItem>();
            for (RecursoDto recursoDto : recursosApp) {
                recursos.add(new SelectItem(recursoDto.getNombreRecurso(), recursoDto.getDescripcion()));
            }
            historicoIngresoFL.setlRecursos(recursos);
        }

    }

    /**
     * Carga la lista de estados del ingreso con los valores de la enumeracion EnumEstadoIngresoUsuario
     */
    public void cargarListaEstadosIngreso() {
        logger.debug("HistoricoIngresosMB::cargarListaRecursos");
        HistoricoIngresoFL historicoIngresoFL = findFlowObject(HistoricoIngresoFL.class, HistoricoIngresoFL.NOMBRE_BEAN);
        List<SelectItem> estados = new ArrayList<SelectItem>();
        for (EnumEstadoIngresoUsuario estado : EnumEstadoIngresoUsuario.values()) {
            estados.add(new SelectItem(estado.getId(), estado.getNombre()));
        }
        historicoIngresoFL.setlEstadosIngreso(estados);
    }

    /**
     * Consulta los ingresos que cumplen con los filtros diligenciados
     */
    public void consultarIngresos() {
        logger.debug("HistoricoIngresosMB::consultarIngresos");

        // Validar las fechas inicial y final
        HistoricoIngresoFL historicoIngresoFL = findFlowObject(HistoricoIngresoFL.class, HistoricoIngresoFL.NOMBRE_BEAN);
        if (historicoIngresoFL.getFechaInicial().compareTo(historicoIngresoFL.getFechaFinal()) >= 0) {
            addErrorMessage(NOMBRE_BUNDLE_HISTORICO_INGRESOS, "msg_fechas_invalidas");
            historicoIngresoFL.setResultadoConsulta(new ArrayList<IngresoDto>());
            historicoIngresoFL.setConsultaRealizada(false);
        } else {
            String diasMax = parametrosEjb
                    .consultarValorParametroSeguridad(EnumParametro.CANTIDAD_DIAS_CONSULTA_INGRESOS);
            Calendar fechaFinalMaxima = UtilFecha.buildCalendar(historicoIngresoFL.getFechaInicial());
            fechaFinalMaxima.add(Calendar.DAY_OF_YEAR, Integer.valueOf(diasMax));
            if (historicoIngresoFL.getFechaFinal().compareTo(fechaFinalMaxima.getTime()) > 0) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageFormat.format(
                                (getBundle(NOMBRE_BUNDLE_HISTORICO_INGRESOS)).getString("msg_cantidad_dias_invalidos"),
                                diasMax)));
                historicoIngresoFL.setResultadoConsulta(new ArrayList<IngresoDto>());
                historicoIngresoFL.setConsultaRealizada(false);
                return;

            }
            // Como las fechas son validas, proceder a la consulta
            ConsultaIngresoDto consultaIngresoDto = new ConsultaIngresoDto();
            consultaIngresoDto.setEstadoIngreso(historicoIngresoFL.getEstadoIngresoSeleccionado());
            consultaIngresoDto.setIdAplicacion(Integer.valueOf(historicoIngresoFL.getAplicacionSeleccionada()));
            consultaIngresoDto.setLogin(historicoIngresoFL.getUsuario());
            consultaIngresoDto.setRecurso(historicoIngresoFL.getRecursoSeleccionado());
            consultaIngresoDto.setFechaIngresoInicial(historicoIngresoFL.getFechaInicial());
            consultaIngresoDto.setFechaIngresoFinal(historicoIngresoFL.getFechaFinal());
            List<IngresoDto> resultadoConsulta = usuarioEjb.consultarHistoricoIngresoUsuario(consultaIngresoDto);
            historicoIngresoFL.setResultadoConsulta(resultadoConsulta);
            historicoIngresoFL.setConsultaRealizada(true);
        }
    }

    /**
     * Consulta el detalle de actividad de ingreso del registro seleccionado
     */
    public void consultarDetalleIngreso() {
        logger.debug("HistoricoIngresosMB::consultarDetalleIngreso");
        HistoricoIngresoFL historicoIngresoFL = findFlowObject(HistoricoIngresoFL.class, HistoricoIngresoFL.NOMBRE_BEAN);
        List<InfoAutorizacion> actividadIngreso = usuarioEjb.obtenerDetalleActividadIngreso(historicoIngresoFL
                .getIngresoSeleccionado());
        historicoIngresoFL.getIngresoSeleccionado().setActividadIngreso(actividadIngreso);

    }

    public ContenidoReporte procesarContenidoReporte() {
        ContenidoReporte contenido = new ContenidoReporte();
        List<Object> listaContenido = new ArrayList<Object>();
        List<Object> registros;

        HistoricoIngresoFL historicoIngresoFL = findFlowObject(HistoricoIngresoFL.class, HistoricoIngresoFL.NOMBRE_BEAN);
        List<IngresoDto> resultadoConsulta = historicoIngresoFL.getResultadoConsulta();

        for (IngresoDto ingresoDto : resultadoConsulta) {

            registros = new ArrayList<Object>();
            registros.add(ingresoDto.getLogin());
            registros.add(ingresoDto.getNombreAplicacion());
            registros.add(ingresoDto.getFechaInicio());
            registros.add(ingresoDto.getFechaCierre());
            registros.add(ingresoDto.getIpIngreso());
            registros.add(ingresoDto.getEstadoIngreso());

            List<InfoAutorizacion> actividadIngreso = new ArrayList<InfoAutorizacion>();
            actividadIngreso = usuarioEjb.obtenerDetalleActividadIngreso(ingresoDto);
            if (actividadIngreso.isEmpty()) {
                listaContenido.add(registros);
            } else {
                for (InfoAutorizacion infoAutorizacion : actividadIngreso) {
                    ArrayList<Object> registroActividad = new ArrayList<Object>();
                    registroActividad.add(ingresoDto.getLogin());
                    registroActividad.add(ingresoDto.getNombreAplicacion());
                    registroActividad.add(ingresoDto.getFechaInicio());
                    registroActividad.add(ingresoDto.getFechaCierre());
                    registroActividad.add(ingresoDto.getIpIngreso());
                    registroActividad.add(ingresoDto.getEstadoIngreso());
                    registroActividad.add(infoAutorizacion.getDescripcionRecurso());
                    registroActividad.add(infoAutorizacion.getNombreRecurso());
                    registroActividad.add(infoAutorizacion.getNombreOperacion());

                    if (infoAutorizacion.isPermitido())
                        registroActividad.add(getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString(
                                "label_si"));
                    else
                        registroActividad.add(getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString(
                                "label_no"));

                    registroActividad.add(infoAutorizacion.getHoraSolicitud());
                    listaContenido.add(registroActividad);
                }
            }
        }
        contenido.setContenido(listaContenido);
        // TODO
        /*
         * contenido.getParametrosEncabezado().put( 1, new Object[] { getLogin(), autenticacionWebMB.getUsuarioDto().getNombres() + " " +
         * autenticacionWebMB.getUsuarioDto().getApellidos() });
         */

        // Filtros de consulta para encabezado del reporte
        List<Object> listFiltros = new ArrayList<>();
        listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE_HISTORICO_INGRESOS).getString("label_aplicacion"),
                historicoIngresoFL.getNombreAppSel()));
        listFiltros.add(Arrays.asList(
                getBundle(NOMBRE_BUNDLE_HISTORICO_INGRESOS).getString("label_fecha_ingreso_inicial"),
                historicoIngresoFL.getFechaInicial()));
        listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE_HISTORICO_INGRESOS)
                .getString("label_fecha_ingreso_final"), historicoIngresoFL.getFechaFinal()));

        if (StringUtils.isNotBlank(historicoIngresoFL.getRecursoSeleccionado())) {
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE_HISTORICO_INGRESOS).getString("label_recurso"),
                    historicoIngresoFL.getDescripcionRecursoSel()));
        }

        if (StringUtils.isNotBlank(historicoIngresoFL.getUsuario())) {
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE_HISTORICO_INGRESOS).getString("label_login"),
                    historicoIngresoFL.getUsuario()));
        }

        if (StringUtils.isNotBlank(historicoIngresoFL.getEstadoIngresoSeleccionado())) {
            listFiltros.add(Arrays.asList(
                    getBundle(NOMBRE_BUNDLE_HISTORICO_INGRESOS).getString("label_estado_ingreso"),
                    historicoIngresoFL.getNombreEstadoSel()));
        }

        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, listFiltros);
        return contenido;
    }

    public AutenticacionWebMB getAutenticacionWebMB() {
        return autenticacionWebMB;
    }

    public void setAutenticacionWebMB(AutenticacionWebMB autenticacionWebMB) {
        this.autenticacionWebMB = autenticacionWebMB;
    }

}
