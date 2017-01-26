package co.com.datatools.c2.managed_bean.coactivo.administracion.condiciones;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.dto.CondicionCoactivoDTO;
import co.com.datatools.c2.dto.EstadoCondicionCoactivoDTO;

/**
 * Value Object que representa la condicion de una configuración de coactivo.
 * 
 * @author Dixon.Alvarez
 * 
 */
public class CondicionCoactivoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Objeto dto que identifica la condicion coactivo.
     */
    private CondicionCoactivoDTO condicionCoactivoDTO;

    /**
     * Objeto que determina si la condicion esta o no esta activa
     */
    private EstadoCondicionCoactivoDTO estadoCondicionCoactivoDTO;

    /**
     * Listado represeta los valores que tomaran la configuracion cargada. Cada valor debe contener dentro la variable a la cual pertenece
     */
    private List<ValorCondicionCoactivoVO> lstValorCondicionVOs;

    public CondicionCoactivoVO() {
    }

    public CondicionCoactivoDTO getCondicionCoactivoDTO() {
        return condicionCoactivoDTO;
    }

    public void setCondicionCoactivoDTO(CondicionCoactivoDTO condicionCoactivoDTO) {
        this.condicionCoactivoDTO = condicionCoactivoDTO;
    }

    public List<ValorCondicionCoactivoVO> getLstValorCondicionVOs() {
        return lstValorCondicionVOs;
    }

    public void setLstValorCondicionVOs(List<ValorCondicionCoactivoVO> lstValorCondicionVOs) {
        this.lstValorCondicionVOs = lstValorCondicionVOs;
    }

    public EstadoCondicionCoactivoDTO getEstadoCondicionCoactivoDTO() {
        return estadoCondicionCoactivoDTO;
    }

    public void setEstadoCondicionCoactivoDTO(EstadoCondicionCoactivoDTO estadoCondicionCoactivoDTO) {
        this.estadoCondicionCoactivoDTO = estadoCondicionCoactivoDTO;
    }

}
