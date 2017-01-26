package co.com.datatools.seguridad.dto.autorizacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Informacion detallada de un recurso completa de un recurso
 * 
 * @author Felipe Martinez
 */
public class RecursoDetalleDto extends RecursoDto {
    private static final long serialVersionUID = 1L;

    /**
     * Referencia al recurso padre al q esta asociado este recurso, puede ser null
     */
    private RecursoDto padre;

    /**
     * Lista de hijos del recurso
     */
    private List<RecursoDetalleDto> hijos;

    /**
     * Lista de operaciones del recurso
     */
    private List<OperacionDto> operaciones;

    /**
     * Constructor sin argumentos que invoca al constructor de RecursoDto e instancia la lista de hijos y operaciones RecursoDetalleDto
     */
    public RecursoDetalleDto() {
        super();
        hijos = new ArrayList<>();
        operaciones = new ArrayList<>();
    }

    /**
     * Invoca al constructor sin argumentos y asigna el nombre al recurso
     * 
     * @param nombreRecurso
     */
    public RecursoDetalleDto(String nombreRecurso) {
        this();
        super.setNombreRecurso(nombreRecurso);
    }

    public RecursoDto getPadre() {
        return padre;
    }

    public void setPadre(RecursoDto padre) {
        this.padre = padre;
    }

    public List<RecursoDetalleDto> getHijos() {
        return hijos;
    }

    /**
     * Asigna el parametro enviado a la lista de hijos del RecursoDetalleDto o asigna una nueva instancia si el parametro viene nulo
     * 
     * @param hijos
     *            Lista de RecursoDetalleDto hijos para asignar al RecursoDetalleDto
     */
    public void setHijos(List<RecursoDetalleDto> hijos) {
        if (hijos == null) {
            this.hijos = new ArrayList<>();
        } else {
            this.hijos = hijos;
        }
    }

    public List<OperacionDto> getOperaciones() {
        return operaciones;
    }

    /**
     * Asigna el parametro enviado a la lista de operaciones del RecursoDetalleDto o asigna una nueva instancia si el parametro viene nulo
     * 
     * @param operaciones
     *            Lista de operaciones para asignar al RecursoDetalleDto
     */
    public void setOperaciones(List<OperacionDto> operaciones) {
        if (operaciones == null) {
            this.operaciones = new ArrayList<>();
        } else {
            this.operaciones = operaciones;
        }
    }

    @Override
    public String toString() {
        return "RecursoDetalleDto {nombreRecurso=" + getNombreRecurso() + ", operaciones=" + operaciones + "}";
    }

}
