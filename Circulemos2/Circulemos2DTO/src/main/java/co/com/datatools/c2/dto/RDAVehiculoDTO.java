package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;

/**
 * DTO
 * 
 * @author pedro.moncada, mod
 */
public class RDAVehiculoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idTipoServicio;
    private Integer idNivelServicio;
    private Integer idClaseVehiculo;

    private PersonaJuridicaDTO empresa;
    private List<PersonaDTO> propietarioList;

    public RDAVehiculoDTO() {

    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Integer getIdNivelServicio() {
        return idNivelServicio;
    }

    public void setIdNivelServicio(Integer idNivelServicio) {
        this.idNivelServicio = idNivelServicio;
    }

    public Integer getIdClaseVehiculo() {
        return idClaseVehiculo;
    }

    public void setIdClaseVehiculo(Integer idClaseVehiculo) {
        this.idClaseVehiculo = idClaseVehiculo;
    }

    public PersonaJuridicaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PersonaJuridicaDTO empresa) {
        this.empresa = empresa;
    }

    public List<PersonaDTO> getPropietarioList() {
        return propietarioList;
    }

    public void setPropietarioList(List<PersonaDTO> propietarioList) {
        this.propietarioList = propietarioList;
    }

}
