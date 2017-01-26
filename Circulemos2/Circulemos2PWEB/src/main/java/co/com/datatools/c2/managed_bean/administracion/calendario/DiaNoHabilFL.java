package co.com.datatools.c2.managed_bean.administracion.calendario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;

import co.com.datatools.c2.dto.DiaNoHabilDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.util.date.UtilFecha;

public class DiaNoHabilFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "diaNoHabilFL";

    /**
     * Modelo en el cual se agregan los dias no habiles registrados
     */
    private LazyScheduleModel smCalendario;

    /**
     * Objeto para manipular los dias no habiles ingresados en el calendario
     */
    private ScheduleEvent seDiaNoHabil;
    /**
     * Dia Seleccionado
     */
    private DiaNoHabilDTO selDiaNoHabil;
    /**
     * Variable para fecha hasta y determinar un rango de dias no habiles
     */
    private Date fechaHasta;
    /**
     * Organismo de transito por el cual se esta consultando los dias habiles
     */
    private OrganismoTransitoDTO organismoTransito;

    /**
     * Listado de días no hábiles a registrar
     */
    private List<DiaNoHabilDTO> lstDiasNoHabiles;

    /**
     * Variable que marca la fecha desde la cual se esta haciendo la consulta.
     */
    private Date dFecIniFiltro;

    /**
     * Atributo que permite identificar el anio seleccionado
     */
    private int anioSeleccionado;
    private List<SelectItem> anios;

    /**
     * Atributo que permite identificar el mes seleccionado
     */
    private int mesSeleccionado;
    private List<SelectItem> meses;

    public DiaNoHabilFL() {
        init();
    }

    public void init() {
        seDiaNoHabil = new ScheduleEventDiaNoHabil();
        selDiaNoHabil = new DiaNoHabilDTO();

        lstDiasNoHabiles = new ArrayList<DiaNoHabilDTO>();

        /**
         * Extraccion de fecha
         */
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar = UtilFecha.resetTime(calendar);
        /**
         * inicializacion desde el primer dia del anio
         */
        dFecIniFiltro = calendar.getTime();

        organismoTransito = (OrganismoTransitoDTO) super.findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);

        mesSeleccionado = calendar.get(Calendar.MONTH) + 1;
        anioSeleccionado = calendar.get(Calendar.YEAR);

        calcAnios();
        cargarMeses();
    }

    public void cargarMeses() {
        String[] mesesStr = this.getBundle("labelCalendario").getString("lst_meses").split(",");
        int i = 1;
        meses = new ArrayList<SelectItem>(12);
        for (String mes : mesesStr) {
            meses.add(new SelectItem(i, mes));
            i++;
        }
    }

    /**
     * 
     */
    public void calcAnios() {
        anios = new ArrayList<>();
        for (int i = anioSeleccionado - 10; i < (anioSeleccionado + 10); i++) {
            anios.add(new SelectItem(i, String.valueOf(i)));
        }
    }

    public LazyScheduleModel getSmCalendario() {
        return smCalendario;
    }

    public void setSmCalendario(LazyScheduleModel smCalendario) {
        this.smCalendario = smCalendario;
    }

    public ScheduleEvent getSeDiaNoHabil() {
        return seDiaNoHabil;
    }

    public void setSeDiaNoHabil(ScheduleEvent seDiaNoHabil) {
        this.seDiaNoHabil = seDiaNoHabil;
    }

    public DiaNoHabilDTO getSelDiaNoHabil() {
        return selDiaNoHabil;
    }

    public void setSelDiaNoHabil(DiaNoHabilDTO selDiaNoHabil) {
        this.selDiaNoHabil = selDiaNoHabil;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public List<DiaNoHabilDTO> getLstDiasNoHabiles() {
        return lstDiasNoHabiles;
    }

    public void setLstDiasNoHabiles(List<DiaNoHabilDTO> lstDiasNoHabiles) {
        this.lstDiasNoHabiles = lstDiasNoHabiles;
    }

    public Date getdFecIniFiltro() {
        return dFecIniFiltro;
    }

    public void setdFecIniFiltro(Date dFecIniFiltro) {
        this.dFecIniFiltro = dFecIniFiltro;
    }

    public int getAnioSeleccionado() {
        return anioSeleccionado;
    }

    public void setAnioSeleccionado(int anioSeleccionado) {
        this.anioSeleccionado = anioSeleccionado;
    }

    public List<SelectItem> getAnios() {
        return anios;
    }

    public void setAnios(List<SelectItem> anios) {
        this.anios = anios;
    }

    public int getMesSeleccionado() {
        return mesSeleccionado;
    }

    public void setMesSeleccionado(int mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    public List<SelectItem> getMeses() {
        return meses;
    }

    public void setMeses(List<SelectItem> meses) {
        this.meses = meses;
    }

}
