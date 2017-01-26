package co.com.datatools.seguridad.mb.recursos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de creacion de Recursos
 * 
 * @author claudia.rodriguez
 * 
 */
public class CrearRecursoFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(CrearRecursoFL.class.getName());

    public static final String NOMBRE_BEAN = "crearRecursoFL";

    private String nombre;
    private String descripcion;
    private String idAplicacion;
    private String idRecursoPadre;
    private String recursoPadre;
    private boolean tieneOperaciones;
    private List<SelectItem> lSiNo;

    /**
     * Alimenta la lista de operaciones del recurso que se va a crear
     */
    private DualListModel<OperacionDto> operaciones;

    public CrearRecursoFL() {
        logger.debug("CrearRecursoFL.CrearRecursoFL");
        cargarListaSiNo();
        tieneOperaciones = true;
    }

    /**
     * Carga la lista de valores Si/No para indicar si el recurso tiene o no operaciones
     */
    public void cargarListaSiNo() {
        lSiNo = new ArrayList<>();
        lSiNo.add(new SelectItem(true, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_si")));
        lSiNo.add(new SelectItem(false, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_no")));

    }

    /**
     * Inicializa los valores del formulario cuando se cancela la creacion
     */
    public void reset() {
        nombre = null;
        descripcion = null;
        idAplicacion = null;
        idRecursoPadre = null;
        recursoPadre = null;
        tieneOperaciones = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(String idAplicacion) {
        this.idAplicacion = idAplicacion;
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

    public DualListModel<OperacionDto> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(DualListModel<OperacionDto> operaciones) {
        this.operaciones = operaciones;
    }

}
