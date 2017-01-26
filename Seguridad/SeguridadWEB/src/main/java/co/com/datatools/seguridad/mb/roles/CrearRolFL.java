package co.com.datatools.seguridad.mb.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de creacion de Roles
 * 
 * @author claudia.rodriguez
 * 
 */
public class CrearRolFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String descripcion;
    private String idRolPadre;
    private String nombreRolPadre;
    private String idAplicacion;

    private List<SelectItem> lSiNo;

    // Lista de aplicaciones de seguridad
    private Map<String, String> lAplicaciones;

    /**
     * Contiene toda la infromacion de recursos y operaciones
     */
    List<WrapperAplicacion> recursosApp = new ArrayList<>();

    public static final String NOMBRE_BEAN = "crearRolFL";

    private DualListModel<GrupoDto> grupos;

    public CrearRolFL() {
        cargarListaSiNo();
    }

    public void reset() {
        nombre = null;
        descripcion = null;
        idRolPadre = null;
        nombreRolPadre = null;
        idAplicacion = null;
    }

    /**
     * Carga la lista de valores Si/No para indicar si el recurso tiene o no operaciones
     */
    public void cargarListaSiNo() {
        lSiNo = new ArrayList<>();
        lSiNo.add(new SelectItem(true, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_si")));
        lSiNo.add(new SelectItem(false, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_no")));

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

    public String getIdRolPadre() {
        return idRolPadre;
    }

    public void setIdRolPadre(String idRolPadre) {
        this.idRolPadre = idRolPadre;
    }

    public String getNombreRolPadre() {
        return nombreRolPadre;
    }

    public void setNombreRolPadre(String nombreRolPadre) {
        this.nombreRolPadre = nombreRolPadre;
    }

    public List<SelectItem> getlSiNo() {
        return lSiNo;
    }

    public void setlSiNo(List<SelectItem> lSiNo) {
        this.lSiNo = lSiNo;
    }

    public List<WrapperAplicacion> getRecursosApp() {
        return recursosApp;
    }

    public void setRecursosApp(List<WrapperAplicacion> recursosApp) {
        this.recursosApp = recursosApp;
    }

    public DualListModel<GrupoDto> getGrupos() {
        return grupos;
    }

    public void setGrupos(DualListModel<GrupoDto> grupos) {
        this.grupos = grupos;
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
