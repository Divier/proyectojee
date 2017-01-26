package co.com.datatools.seguridad.mb.roles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;

public class WrapperRecurso implements Serializable {
    private static final long serialVersionUID = 1L;

    private String clase;
    private String idRecurso;
    private RecursoDetalleDto recurso;
    private List<OperacionDto> seleccionOperacion;
    private boolean seleccionado;

    public WrapperRecurso(RecursoDetalleDto recurso, String clase) {
        super();
        this.clase = clase;
        this.recurso = recurso;
        this.idRecurso = recurso.getNombreRecurso().hashCode() + RandomStringUtils.randomAlphanumeric(4);
        seleccionOperacion = new ArrayList<>();
        final List<OperacionDto> backupOperaciones = recurso.getOperaciones();
        recurso.setOperaciones(null);
        for (OperacionDto op : backupOperaciones) {
            recurso.getOperaciones().add(new WrapperOperacion(op));
        }
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public static WrapperRecurso buildRecurso(RecursoDetalleDto recurso) {
        return new WrapperRecurso(recurso, "ui-seg-rol-recurso");
    }

    public static WrapperRecurso buildPadre(RecursoDetalleDto recurso) {
        return new WrapperRecurso(recurso, "ui-seg-rol-padre");
    }

    public RecursoDetalleDto getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoDetalleDto recurso) {
        this.recurso = recurso;
    }

    public List<OperacionDto> getSeleccionOperacion() {
        return seleccionOperacion;
    }

    public void setSeleccionOperacion(List<OperacionDto> seleccionOperacion) {
        this.seleccionOperacion = seleccionOperacion;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        /*
         * List<String> seleccion = new ArrayList<>(); if (seleccionado) { List<OperacionDto> operacionesRecurso = recurso.getOperaciones(); for
         * (OperacionDto operacionDto : operacionesRecurso) { seleccion.add(String.valueOf(operacionDto.getId())); } } else
         * setSeleccionOperacion(seleccion);
         */
        this.seleccionado = seleccionado;
    }
}