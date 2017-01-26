package co.com.datatools.seguridad.mb.recursos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de consulta de Recursos
 * 
 * @author claudia.rodriguez
 * 
 */
public class RecursosFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(RecursosFL.class.getName());

    public static final String NOMBRE_BEAN = "recursosFL";

    private String nombre;
    private String descripcion;
    private String idAplicacion;
    private String nombrePadreSeleccionado;
    private boolean tieneOperaciones;

    private Map<String, String> lAplicaciones;

    /**
     * Indica si ya se ha oprimido el boton de Consultar recursos
     */
    private boolean consultaRealizada;

    private List<RecursoDto> resultadoConsulta;

    /**
     * Almacena el recurso que es seleccionado en la consulta para ejecutar accion de ver detalle/modificar/eliminar
     */
    private RecursoDto recursoSeleccionado;

    /**
     * Almacena el recurso que ha sido seleccionado como padre de otro recurso
     */
    private RecursoDto recursoPadreDto;

    private RecursoDetalleDto detalleRecurso;

    private String descripcionRecPadreConsulta;
    private String nombreRecPadreConsulta;
    private RecursoDto recursoPadreSeleccionado;
    private List<RecursoDto> resultadoConsultaRecPadre;
    private boolean consultaPadreRealizada;
    private RecursoDetalleDto detalleRecursoPadre;

    /**
     * Indica si la busqueda de recursos se realiza para asignar recursos padre(en creacion o modificacion de recursos) o si es una busqueda para
     * creacion/modificacion de opciones de menu
     */
    private boolean asignacionRecursoPadre = true;
    /**
     * Si es una busqueda para creacion/modificacion de opciones de menu indica si es modificacion o no
     */
    private boolean modificacionMenu;

    public RecursosFL() {
        logger.debug("RecursosFL.RecursosFL");
        consultaRealizada = false;
        resultadoConsulta = new ArrayList<>();

        consultaPadreRealizada = false;
        resultadoConsultaRecPadre = new ArrayList<>();

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

    public String getNombrePadreSeleccionado() {
        return nombrePadreSeleccionado;
    }

    public void setNombrePadreSeleccionado(String nombrePadreSeleccionado) {
        this.nombrePadreSeleccionado = nombrePadreSeleccionado;
    }

    public boolean isTieneOperaciones() {
        return tieneOperaciones;
    }

    public void setTieneOperaciones(boolean tieneOperaciones) {
        this.tieneOperaciones = tieneOperaciones;
    }

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    public List<RecursoDto> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<RecursoDto> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public RecursoDto getRecursoSeleccionado() {
        return recursoSeleccionado;
    }

    public void setRecursoSeleccionado(RecursoDto recursoSeleccionado) {
        this.recursoSeleccionado = recursoSeleccionado;
    }

    public RecursoDetalleDto getDetalleRecurso() {
        return detalleRecurso;
    }

    public void setDetalleRecurso(RecursoDetalleDto detalleRecurso) {
        this.detalleRecurso = detalleRecurso;
    }

    public RecursoDto getRecursoPadreDto() {
        return recursoPadreDto;
    }

    public void setRecursoPadreDto(RecursoDto recursoPadreDto) {
        this.recursoPadreDto = recursoPadreDto;
    }

    public String getDescripcionRecPadreConsulta() {
        return descripcionRecPadreConsulta;
    }

    public void setDescripcionRecPadreConsulta(String descripcionRecPadreConsulta) {
        this.descripcionRecPadreConsulta = descripcionRecPadreConsulta;
    }

    public String getNombreRecPadreConsulta() {
        return nombreRecPadreConsulta;
    }

    public void setNombreRecPadreConsulta(String nombreRecPadreConsulta) {
        this.nombreRecPadreConsulta = nombreRecPadreConsulta;
    }

    public RecursoDto getRecursoPadreSeleccionado() {
        return recursoPadreSeleccionado;
    }

    public void setRecursoPadreSeleccionado(RecursoDto recursoPadreSeleccionado) {
        this.recursoPadreSeleccionado = recursoPadreSeleccionado;
    }

    public List<RecursoDto> getResultadoConsultaRecPadre() {
        return resultadoConsultaRecPadre;
    }

    public void setResultadoConsultaRecPadre(List<RecursoDto> resultadoConsultaRecPadre) {
        this.resultadoConsultaRecPadre = resultadoConsultaRecPadre;
    }

    public boolean isConsultaPadreRealizada() {
        return consultaPadreRealizada;
    }

    public void setConsultaPadreRealizada(boolean consultaPadreRealizada) {
        this.consultaPadreRealizada = consultaPadreRealizada;
    }

    public RecursoDetalleDto getDetalleRecursoPadre() {
        return detalleRecursoPadre;
    }

    public void setDetalleRecursoPadre(RecursoDetalleDto detalleRecursoPadre) {
        this.detalleRecursoPadre = detalleRecursoPadre;
    }

    public Map<String, String> getlAplicaciones() {
        return lAplicaciones;
    }

    public void setlAplicaciones(Map<String, String> lAplicaciones) {
        this.lAplicaciones = lAplicaciones;
    }

    public boolean isAsignacionRecursoPadre() {
        return asignacionRecursoPadre;
    }

    public void setAsignacionRecursoPadre(boolean asignacionRecursoPadre) {
        this.asignacionRecursoPadre = asignacionRecursoPadre;
    }

    public boolean isModificacionMenu() {
        return modificacionMenu;
    }

    public void setModificacionMenu(boolean modificacionMenu) {
        this.modificacionMenu = modificacionMenu;
    }

}
