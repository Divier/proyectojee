package co.com.datatools.seguridad.mb.recursos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de modificacion de Recursos
 * 
 * @author claudia.rodriguez
 * 
 */
public class ModificarRecursoFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ModificarRecursoFL.class.getName());

    public static final String NOMBRE_BEAN = "modificarRecursoFL";

    private RecursoDto recursoModificar;
    private String recursoPadre;
    private String idRecursoPadre;
    private boolean tieneOperaciones;
    /**
     * Indica si es la primera vez que se carga el detalle del recurso, para evitar volver a consultarlo cuando se recarga la misma pagina
     */
    private boolean primerIngreso = true;
    private List<SelectItem> lSiNo;

    /**
     * Alimenta la lista de operaciones disponibles y asignadas para el recurso que se esta modificando
     */
    private DualListModel<OperacionDto> operacionesRecurso;

    public ModificarRecursoFL() {
        logger.debug("ModificarRecursoFL.ModificarRecursoFL");
        cargarListaSiNo();
    }

    /**
     * Carga la lista de valores Si/No para indicar si el recurso tiene o no operaciones
     */
    public void cargarListaSiNo() {
        lSiNo = new ArrayList<>();
        lSiNo.add(new SelectItem(true, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_si")));
        lSiNo.add(new SelectItem(false, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_no")));

    }

    public RecursoDto getRecursoModificar() {
        return recursoModificar;
    }

    public void setRecursoModificar(RecursoDto recursoModificar) {
        this.recursoModificar = recursoModificar;
    }

    public String getRecursoPadre() {
        return recursoPadre;
    }

    public void setRecursoPadre(String recursoPadre) {
        this.recursoPadre = recursoPadre;
    }

    public boolean isTieneOperaciones() {
        return tieneOperaciones;
    }

    public void setTieneOperaciones(boolean tieneOperaciones) {
        this.tieneOperaciones = tieneOperaciones;
    }

    public boolean isPrimerIngreso() {
        return primerIngreso;
    }

    public void setPrimerIngreso(boolean primerIngreso) {
        this.primerIngreso = primerIngreso;
    }

    public List<SelectItem> getlSiNo() {
        return lSiNo;
    }

    public void setlSiNo(List<SelectItem> lSiNo) {
        this.lSiNo = lSiNo;
    }

    public String getIdRecursoPadre() {
        return idRecursoPadre;
    }

    public void setIdRecursoPadre(String idRecursoPadre) {
        this.idRecursoPadre = idRecursoPadre;
    }

    public DualListModel<OperacionDto> getOperacionesRecurso() {
        return operacionesRecurso;
    }

    public void setOperacionesRecurso(DualListModel<OperacionDto> operacionesRecurso) {
        this.operacionesRecurso = operacionesRecurso;
    }

}
