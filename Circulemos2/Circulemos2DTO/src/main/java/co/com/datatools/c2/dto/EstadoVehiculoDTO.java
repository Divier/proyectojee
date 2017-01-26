package co.com.datatools.c2.dto;

import java.io.Serializable;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class EstadoVehiculoDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer idEstadoVehiculo;
    private Integer codigoEstadoVehiculo;
    private String nombreEstadoVehiculo;
    private OrganismoTransitoDTO organismoTransitoDTO;

    // Constructors Declaration

    public EstadoVehiculoDTO() {

    }

    // Start Methods Declaration

    public Integer getIdEstadoVehiculo() {
        return idEstadoVehiculo;
    }

    public void setIdEstadoVehiculo(Integer idEstadoVehiculo) {
        this.idEstadoVehiculo = idEstadoVehiculo;
    }

    public Integer getCodigoEstadoVehiculo() {
        return codigoEstadoVehiculo;
    }

    public void setCodigoEstadoVehiculo(Integer codigoEstadoVehiculo) {
        this.codigoEstadoVehiculo = codigoEstadoVehiculo;
    }

    public String getNombreEstadoVehiculo() {
        return nombreEstadoVehiculo;
    }

    public void setNombreEstadoVehiculo(String nombreEstadoVehiculo) {
        this.nombreEstadoVehiculo = nombreEstadoVehiculo;
    }

    public OrganismoTransitoDTO getOrganismoTransitoDTO() {
        return organismoTransitoDTO;
    }

    public void setOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
    }

    // Finish the class
}