package co.com.datatools.c2.managed_bean.administracion.calendario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import co.com.datatools.c2.bundle.comun.EnumParametrosWeb;
import co.com.datatools.c2.dto.DiaNoHabilDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRDiaNoHabil;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral.MensajesGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.util.date.UtilFecha;

/**
 * Managed Bean para manipulacion de dias no habiles <b>CU_CIR20_DAT_ADM_019</b>
 * 
 * @author luis.forero
 * 
 */
@ManagedBean
@SessionScoped
public class CalendarioMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(CalendarioMB.class.getName());

    private final static String BUNDLE_DIAS_NO_HABILES = "labelCalendario";

    private static final String DIA_NO_HABIL_FL = "diaNoHabilFL";

    @EJB
    private IRDiaNoHabil iRDiaNoHabil;

    public CalendarioMB() {
    }

    @PostConstruct
    public void calendarioMBPost() {
        logger.debug(CalendarioMB.class.getName() + "::calendarioMBPost");
    }

    /**
     * 
     */
    public void cambioFechaConsultada() {
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        int anioSeleccionado = diaNoHabilFL.getAnioSeleccionado();
        int mesSeleccionado = diaNoHabilFL.getMesSeleccionado();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(diaNoHabilFL.getdFecIniFiltro());
        calendar.set(Calendar.YEAR, anioSeleccionado);
        calendar.set(Calendar.MONTH, mesSeleccionado - 1);
        diaNoHabilFL.setdFecIniFiltro(calendar.getTime());

        diaNoHabilFL.calcAnios();
        return;
    }

    /**
     * Lleva a cabo la consulta de los dias no habiles entre las fechas ingresadas.
     */
    public void consultarDiasNoHabiles() {
        logger.info(CalendarioMB.class.getName().concat("::consultarDiasNoHabiles()"));
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        LazyScheduleModel smCalendario = new LazyScheduleModel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void loadEvents(Date start, Date end) {
                List<DiaNoHabilDTO> lstDiaNoHabilMes = (List<DiaNoHabilDTO>) iRDiaNoHabil.consultarDiasNoHabiles(
                        getOrganismoTransito().getCodigoOrganismo(), start, end);

                for (DiaNoHabilDTO diaNoHabilDTO : lstDiaNoHabilMes) {
                    ScheduleEvent event = new ScheduleEventDiaNoHabil(diaNoHabilDTO);
                    addEvent(event);
                }
            }
        };
        if (!smCalendario.getEvents().isEmpty()) {
            ScheduleEventDiaNoHabil event = (ScheduleEventDiaNoHabil) smCalendario.getEvents().get(0);
            diaNoHabilFL.setdFecIniFiltro(event.getStartDate());
        }
        diaNoHabilFL.setSmCalendario(smCalendario);

    }

    /**
     * Permite inicialisar los atriabutos para cargar el detalle de un dia no habil.
     * 
     * @param selectEvent
     */
    public void verDiaNoHabil(SelectEvent selectEvent) {
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        if (selectEvent.getObject() != null) {
            ScheduleEventDiaNoHabil seDiaNoHabil = (ScheduleEventDiaNoHabil) selectEvent.getObject();
            diaNoHabilFL.setSelDiaNoHabil((DiaNoHabilDTO) seDiaNoHabil.getData());
            diaNoHabilFL.setdFecIniFiltro(diaNoHabilFL.getSelDiaNoHabil().getFecha());
            RequestContext context = RequestContext.getCurrentInstance();

            context.execute("PF('popUpDetail').show();");
        }
    }

    /**
     * Permite visualisar los atributos para registrar un nuevo dia no habil.
     * 
     * @param selectEvent
     */
    public void verRegDiaNoHabil(SelectEvent selectEvent) {
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);

        Date base = (Date) selectEvent.getObject();
        Calendar fecSel = UtilFecha.resetTime(base);
        // TODO Agregacion de un dia por componente con Bug. Borrar cuando Schedule no tenga bug.
        fecSel.add(Calendar.DAY_OF_MONTH, 1);
        diaNoHabilFL.setSelDiaNoHabil(new DiaNoHabilDTO());
        diaNoHabilFL.getSelDiaNoHabil().setFecha(fecSel.getTime());

        // Se agrega la fecha actual sobre la cual se esta trabajando
        diaNoHabilFL.setdFecIniFiltro(fecSel.getTime());

        if (validarDiaNoHabil(diaNoHabilFL.getSelDiaNoHabil())) {
            diaNoHabilFL.setFechaHasta(fecSel.getTime());
            diaNoHabilFL.getSelDiaNoHabil().setObservacion(null);
            diaNoHabilFL.getSelDiaNoHabil().setOrganismoTransito(diaNoHabilFL.getOrganismoTransito());

            diaNoHabilFL.setSeDiaNoHabil(new ScheduleEventDiaNoHabil(diaNoHabilFL.getSelDiaNoHabil()));
            RequestContext context = RequestContext.getCurrentInstance();

            context.execute("PF('popUpRegDNH').show();");
        } else {
            ScheduleModel smCalendario = diaNoHabilFL.getSmCalendario();

            for (ScheduleEvent se : smCalendario.getEvents()) {
                DiaNoHabilDTO diaNoHabilDTO = (DiaNoHabilDTO) ((ScheduleEventDiaNoHabil) se).getData();
                if (diaNoHabilDTO.getFecha().compareTo(fecSel.getTime()) == 0) {
                    diaNoHabilFL.setSelDiaNoHabil(diaNoHabilDTO);
                    diaNoHabilFL.setSeDiaNoHabil((ScheduleEventDiaNoHabil) se);
                    RequestContext context = RequestContext.getCurrentInstance();

                    context.execute("PF('popUpDetail').show();");
                    break;
                }
            }
        }
    }

    /**
     * Lleva a cabo las siguientes validaciones: <br>
     * <br>
     * 1) Fecha ingresada es mayor a la actual.<br>
     * 2) Fecha no Habil ingresada al sistema.
     * 
     * @param diaNoHabil
     * @return boolean
     */
    private boolean validarDiaNoHabil(DiaNoHabilDTO pDiaNoHabil) {
        /**
         * Verifico si la fecha es mayor a la actual.
         */
        if (!esMayorIgualDiaActualSel()) {
            super.addWarningMessage(BUNDLE_DIAS_NO_HABILES, "wrn_fecha_mayor_igual_fecha_actual");
            return false;
        } else
        /**
         * Verifico si la fecha ya fue ingresada
         */
        if (iRDiaNoHabil
                .esDiaNoHabil(
                        findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO).getCodigoOrganismo(),
                        pDiaNoHabil.getFecha())) {
            super.addWarningMessage(BUNDLE_DIAS_NO_HABILES, "wrn_fecha_ya_ingrasa");
            return false;
        } else {
            return true;
        }

    }

    /**
     * Verifico si la fecha ya fue ingresada
     * 
     * @return
     */
    public boolean esMayorIgualDiaActualSel() {
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        if (diaNoHabilFL.getSelDiaNoHabil().getFecha().compareTo(UtilFecha.currentZeroTimeDate()) < 0) {
            return false;
        }
        return true;
    }

    /**
     * Elimina de la lista a ingresar un dia no habil
     */
    public void eliminarListaRegistrar() {
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        diaNoHabilFL.getLstDiasNoHabiles().remove(diaNoHabilFL.getSelDiaNoHabil());
        diaNoHabilFL.setSelDiaNoHabil(new DiaNoHabilDTO());
    }

    /**
     * limpia todos los dias no habiles a ser ingresados.
     */
    public void limpiarRegistroDiasNHabiles() {
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        diaNoHabilFL.getLstDiasNoHabiles().clear();
        diaNoHabilFL.setSelDiaNoHabil(new DiaNoHabilDTO());
    }

    /**
     * Determina si ya se encuantra ingresado en la lista y formatea la fecha ingresada borrando la hora de la misma.
     * 
     * @return Verdadero no ingresado
     */
    private boolean noIngresado(DiaNoHabilDTO selDiaNoHabil) {
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        Date fecSel = UtilFecha.resetTime(selDiaNoHabil.getFecha()).getTime();
        /**
         * Recorro la lista para verificar si la fecha a ingresar no este ya dentro de la lista.
         */
        ScheduleModel smCalendario = diaNoHabilFL.getSmCalendario();

        for (ScheduleEvent se : smCalendario.getEvents()) {
            DiaNoHabilDTO diaNoHabilDTO = (DiaNoHabilDTO) ((ScheduleEventDiaNoHabil) se).getData();
            if (diaNoHabilDTO.getFecha().compareTo(fecSel) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula el numero de dias entre dos determinadas fechas
     * 
     * <pre>
     * d1 > = d2
     * </pre>
     * 
     * @param d1
     *            = date 1 desde
     * @param d2
     *            = date 2 hasta
     * @return numero de dias entre las dos fechas
     */
    private int diasEntre(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * Retorna un listado de fechas de un determinado rango.
     * 
     * @author luis.forero
     * @param desde
     * @param hasta
     * @return
     */
    private List<Date> retornarFechasAgregadas(Date desde, Date hasta) {
        int diasEntre = diasEntre(desde, hasta);
        List<Date> listaFechas = new ArrayList<>();
        for (int i = 0; i <= diasEntre; i++) {
            Calendar calendar = UtilFecha.resetTime(desde);
            calendar.setTime(desde);
            calendar = UtilFecha.resetTime(calendar);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            listaFechas.add(calendar.getTime());
        }
        return listaFechas;
    }

    /**
     * Registra dias habiles
     */
    public void registrarDiaNoHabil() {
        logger.info(CalendarioMB.class.getName().concat("::registrarDiaNoHabil()"));
        FacesContext fc = FacesContext.getCurrentInstance();

        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);

        List<Date> retornarFechasAgregadas = retornarFechasAgregadas(diaNoHabilFL.getSelDiaNoHabil().getFecha(),
                diaNoHabilFL.getFechaHasta());
        List<DiaNoHabilDTO> lstDiaNoHabilDTO = new ArrayList<>();
        boolean validado = true;
        for (Date fechDiaNoHabil : retornarFechasAgregadas) {
            DiaNoHabilDTO diaNoHabil = new DiaNoHabilDTO();
            diaNoHabil.setOrganismoTransito(diaNoHabilFL.getSelDiaNoHabil().getOrganismoTransito());
            diaNoHabil.setObservacion(diaNoHabilFL.getSelDiaNoHabil().getObservacion());
            diaNoHabil.setFecha(fechDiaNoHabil);
            if (noIngresado(diaNoHabil)) {
                lstDiaNoHabilDTO.add(diaNoHabil);
            } else {
                validado = false;
                addWarningMessage(BUNDLE_DIAS_NO_HABILES, "wrn_fecha_rango_ya_agragada");
                return;
            }
        }
        if (validado) {

            try {
                List<DiaNoHabilDTO> response = iRDiaNoHabil.registrarDiasNoHabiles(lstDiaNoHabilDTO);

                fc.addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, CirculemosAccesoBundleGeneral
                                .getStringGeneral(MensajesGeneral.msj_registro_ok),
                                getResponseMessageRegistro(response)));

                RequestContext.getCurrentInstance().execute("PF('popUpRegDNH').hide();");

            } catch (CirculemosNegocioException e) {
                logger.error("ERROR - CalendarioMB - METODO registrarDiaNoHabil: " + e.getMessage());
                CirculemosErrorHandler.handleException(e);
            }
        }

    }

    /**
     * Construye un mensaje resultado del registro de un dia no habil ingresando en el calendario los dias exitosos.
     * 
     * @param response
     * @return String
     */
    private String getResponseMessageRegistro(List<DiaNoHabilDTO> response) {
        StringBuilder responseMessage = new StringBuilder();
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        for (DiaNoHabilDTO diaNoHabilDTO : response) {
            String patron = getBundle(EnumParametrosWeb.getBundleName()).getString(
                    EnumParametrosWeb.lab_calendar_pattern.toString());
            SimpleDateFormat formato = new SimpleDateFormat(patron);
            // formateo
            responseMessage.append("- ").append(formato.format(diaNoHabilDTO.getFecha())).append("\n");
            ScheduleEvent newDiaNoHabil = new ScheduleEventDiaNoHabil(diaNoHabilDTO);
            diaNoHabilFL.getSmCalendario().addEvent(newDiaNoHabil);
        }
        return responseMessage.toString();
    }

    /**
     * Lleva a cabo la modificación del día no habil
     */
    public void modificarDiaNoHabil() {
        logger.info(CalendarioMB.class.getName().concat("::modificarDiaNoHabil()"));
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        try {
            iRDiaNoHabil.modificarDiaNoHabil(diaNoHabilFL.getSelDiaNoHabil());

            CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();

            RequestContext.getCurrentInstance().execute("PF('popUpModDNH').hide();");
        } catch (CirculemosNegocioException e) {
            logger.error("ERROR - CalendarioMB - METODO modificarDiaNoHabil: " + e.getMessage());
            CirculemosErrorHandler.handleException(e);
        }

    }

    /**
     * Lleva a cabo la eliminación de un dia no habil en el sistema
     */
    public void eliminarDiaNoHabil() {
        logger.info(CalendarioMB.class.getName().concat("::eliminarDiaNoHabil()"));
        DiaNoHabilFL diaNoHabilFL = findFlowObject(DiaNoHabilFL.class, DIA_NO_HABIL_FL);
        try {
            iRDiaNoHabil.eliminarDiaNoHabil(diaNoHabilFL.getSelDiaNoHabil());

            CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();

        } catch (CirculemosNegocioException e) {
            logger.error("ERROR - CalendarioMB - METODO eliminarDiaNoHabil(): " + e.getMessage());
            CirculemosErrorHandler.handleException(e);
        }
    }

}