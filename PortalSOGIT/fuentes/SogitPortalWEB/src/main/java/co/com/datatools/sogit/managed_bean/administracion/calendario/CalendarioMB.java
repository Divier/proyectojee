package co.com.datatools.sogit.managed_bean.administracion.calendario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.sogit.utilies.AbstractPortalManagedBean;

/**
 * S eencarga del manejo de las fechas
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class CalendarioMB extends AbstractPortalManagedBean {
    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(CalendarioMB.class.getName());

    /**
     * Atributo que permite identificar el anio seleccionado
     */
    private List<SelectItem> anios;
    private int anioSeleccionado;

    public CalendarioMB() {
    }

    @PostConstruct
    public void calendarioMBPost() {
        LOGGER.debug("calendarioMBPost()");

        /**
         * Extraccion de fecha
         */
        Calendar calendar = Calendar.getInstance();
        anioSeleccionado = calendar.get(Calendar.YEAR);

        calcAnios();
    }

    /**
     * Se encarga de escuchar el cambio de la fecha
     * 
     * @author giovanni.velandia
     */
    public void cambioFechaConsultada(int anioSeleccionado) {
        LOGGER.debug("cambioFechaConsultada(int)");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, anioSeleccionado);
        calcAnios();
        return;
    }

    /**
     * LLena la lista de años
     * 
     * @author giovanni.velandia
     */
    public void calcAnios() {
        LOGGER.debug("calcAnios(int)");
        anios = new ArrayList<>();
        for (int i = anioSeleccionado - 1; i < (anioSeleccionado + 1); i++) {
            anios.add(new SelectItem(i, String.valueOf(i)));
        }
    }

    public List<SelectItem> getAnios() {
        return anios;
    }

    public void setAnios(List<SelectItem> anios) {
        this.anios = anios;
    }

    public int getAnioSeleccionado() {
        return anioSeleccionado;
    }

    public void setAnioSeleccionado(int anioSeleccionado) {
        this.anioSeleccionado = anioSeleccionado;
    }

}