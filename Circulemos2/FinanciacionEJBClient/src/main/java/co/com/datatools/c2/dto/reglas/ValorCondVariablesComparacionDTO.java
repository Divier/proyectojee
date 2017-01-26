package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;

/**
 * DTO que permite consultar las variables con las cuales es comparado un determinado valor de una determinada variable.
 * 
 * @author luis.forero
 * 
 */
public class ValorCondVariablesComparacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idVariableCondicionFinan;
    private List<Integer> lstIdsVariablesCondFinan;

    public ValorCondVariablesComparacionDTO() {
        lstIdsVariablesCondFinan = new ArrayList<Integer>();
    }

    /**
     * Constructor para recepcion de valores definidos.
     * 
     * <pre>
     * VariableCondicionFinanDTO y su ID no nulos
     * </pre>
     * 
     * @param valorCondicionFinanciacionDTO
     */
    public ValorCondVariablesComparacionDTO(ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO) {
        idVariableCondicionFinan = valorCondicionFinanciacionDTO.getVariableCondicionFinan().getId();
        lstIdsVariablesCondFinan = new ArrayList<Integer>();
    }

    public Integer getIdVariableCondicionFinan() {
        return idVariableCondicionFinan;
    }

    public void setIdVariableCondicionFinan(Integer idVariableCondicionFinan) {
        this.idVariableCondicionFinan = idVariableCondicionFinan;
    }

    public List<Integer> getLstIdsVariablesCondFinan() {
        return lstIdsVariablesCondFinan;
    }

    public void setLstIdsVariablesCondFinan(List<Integer> lstIdsVariablesCondFinan) {
        this.lstIdsVariablesCondFinan = lstIdsVariablesCondFinan;
    }

}
