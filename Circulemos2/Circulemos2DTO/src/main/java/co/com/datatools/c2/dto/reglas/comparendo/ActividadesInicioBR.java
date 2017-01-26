package co.com.datatools.c2.dto.reglas.comparendo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.reglas.ReglaOrganismoBR;

/**
 * Clase abstracta que recibe una lista de codigos de actividades
 * 
 * @author robert.bautista - 15/08/2014
 */
public abstract class ActividadesInicioBR extends ReglaOrganismoBR implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Parametro de salida
     */
    private List<Integer> codigosActividades;

    protected ActividadesInicioBR() {
        this.codigosActividades = new ArrayList<Integer>();
    }

    public List<Integer> getCodigosActividades() {
        return this.codigosActividades;
    }

    public void setCodigosActividades(List<Integer> codigosActividades) {
        this.codigosActividades = codigosActividades;
    }

}
