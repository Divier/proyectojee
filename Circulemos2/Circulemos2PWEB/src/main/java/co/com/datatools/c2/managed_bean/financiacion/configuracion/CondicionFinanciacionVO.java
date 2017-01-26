package co.com.datatools.c2.managed_bean.financiacion.configuracion;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.dto.CondicionFinanciacionDTO;
import co.com.datatools.c2.dto.EstadoCondicionFinanciacionDTO;

/**
 * Value Object que representa la condicion de una configuración de financiacion.
 * 
 * @author luis.forero
 * 
 */
public class CondicionFinanciacionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Objeto dto que identifica la condicion financiacion.
     */
    private CondicionFinanciacionDTO condicionFinanciacionDTO;

    /**
     * Objeto que determina si la financiacion esta o no esta activa
     */
    private EstadoCondicionFinanciacionDTO estadoCondicionFinanciacionDTO;

    /**
     * Listado represeta los valores que tomaran la configuracion cargada de la financiacion. Cada valor contiene debe contener dentro la variable a
     * la cual pertenece
     */
    private List<ValorCondicionVO> lstValorCondicionVOs;

    public CondicionFinanciacionVO() {
    }

    public CondicionFinanciacionDTO getCondicionFinanciacionDTO() {
        return condicionFinanciacionDTO;
    }

    public void setCondicionFinanciacionDTO(CondicionFinanciacionDTO condicionFinanciacionDTO) {
        this.condicionFinanciacionDTO = condicionFinanciacionDTO;
    }

    public List<ValorCondicionVO> getLstValorCondicionVOs() {
        return lstValorCondicionVOs;
    }

    public void setLstValorCondicionVOs(List<ValorCondicionVO> lstValorCondicionVOs) {
        this.lstValorCondicionVOs = lstValorCondicionVOs;
    }

    public EstadoCondicionFinanciacionDTO getEstadoCondicionFinanciacionDTO() {
        return estadoCondicionFinanciacionDTO;
    }

    public void setEstadoCondicionFinanciacionDTO(EstadoCondicionFinanciacionDTO estadoCondicionFinanciacionDTO) {
        this.estadoCondicionFinanciacionDTO = estadoCondicionFinanciacionDTO;
    }

}
