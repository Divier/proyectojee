package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO que me permite obtener los tipos de validación que aplican para un determinado valor de dondicion dependiendo de la variable a la cual
 * pertenece.
 * 
 * @author luis.forero
 * 
 */
public class ValorCondicionTiposValidacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idVariableCondicionValor;
    private List<Integer> listTipoValidaciones;
    private String descripcion;

    public ValorCondicionTiposValidacionDTO() {
        listTipoValidaciones = new ArrayList<Integer>();
    }

    public Integer getIdVariableCondicionValor() {
        return idVariableCondicionValor;
    }

    public void setIdVariableCondicionValor(Integer idVariableCondicionValor) {
        this.idVariableCondicionValor = idVariableCondicionValor;
    }

    public List<Integer> getListTipoValidaciones() {
        return listTipoValidaciones;
    }

    public void setListTipoValidaciones(List<Integer> listTipoValidaciones) {
        this.listTipoValidaciones = listTipoValidaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
