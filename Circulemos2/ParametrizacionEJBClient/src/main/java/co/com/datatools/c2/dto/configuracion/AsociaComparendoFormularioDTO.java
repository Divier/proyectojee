package co.com.datatools.c2.dto.configuracion;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * DTO que se encarga de representar la configuracion para el codigo 008 - Asocia tipos de comparendo y de formulario
 * 
 * @author giovanni.velandia 2015-10-06
 * 
 */
public class AsociaComparendoFormularioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Entrada
     */
    // <codigo>tipoComparendo</codigo>
    private List<String> tipoComparendo;

    /*
     * Salida
     */
    // <codigo>tipoFormulario</codigo>
    private List<String> tipoFormulario;

    public List<String> getTipoComparendo() {
        return tipoComparendo;
    }

    public void setTipoComparendo(List<String> tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    public List<String> getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(List<String> tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

}
