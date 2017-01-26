package co.com.datatools.c2.dto.eventos;

import java.io.Serializable;
import java.util.List;

/**
 * filtros para consulta de configuracion horario
 * 
 * @author giovanni.velandia
 *
 */
public class ConfiguracionHorarioFiltrosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreCargo;
    private Integer idCargo;
    private List<Integer> dias;

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public List<Integer> getDias() {
        return dias;
    }

    public void setDias(List<Integer> dias) {
        this.dias = dias;
    }

}
