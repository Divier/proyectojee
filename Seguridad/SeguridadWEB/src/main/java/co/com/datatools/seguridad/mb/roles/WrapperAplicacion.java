package co.com.datatools.seguridad.mb.roles;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;

public class WrapperAplicacion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreAplicacion;
    private List<RecursoDetalleDto> recursos;
    private TreeNode arbolRecursos;

    public WrapperAplicacion(Entry<String, List<RecursoDetalleDto>> app) {
        this.nombreAplicacion = app.getKey();
        this.recursos = app.getValue();
        this.arbolRecursos = procesarAplicacion(this.recursos);
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    public List<RecursoDetalleDto> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoDetalleDto> recursos) {
        this.recursos = recursos;
    }

    public TreeNode getArbolRecursos() {
        return arbolRecursos;
    }

    public void setArbolRecursos(TreeNode arbolRecursos) {
        this.arbolRecursos = arbolRecursos;
    }

    private TreeNode procesarAplicacion(List<RecursoDetalleDto> recursosApp) {
        TreeNode recursos = new CheckboxTreeNode(nombreAplicacion, null);

        for (RecursoDetalleDto detalle : recursosApp) {
            procesarRecursos(detalle, recursos);
        }
        return recursos;
    }

    private void procesarRecursos(RecursoDetalleDto recurso, TreeNode parent) {
        WrapperRecurso infoNodo = null;
        if (!recurso.getOperaciones().isEmpty()) {
            infoNodo = WrapperRecurso.buildRecurso(recurso);
        } else {
            infoNodo = WrapperRecurso.buildPadre(recurso);
        }
        final TreeNode newParent = new CheckboxTreeNode(infoNodo, parent);
        final List<RecursoDetalleDto> hijos = recurso.getHijos();
        for (RecursoDetalleDto hijo : hijos) {
            procesarRecursos(hijo, newParent);
        }
    }

}