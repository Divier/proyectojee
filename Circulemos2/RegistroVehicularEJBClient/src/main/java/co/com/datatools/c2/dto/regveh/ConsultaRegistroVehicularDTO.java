package co.com.datatools.c2.dto.regveh;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que se encarga de encapsular los filtros de consulta para los datos de registro distrital vehícular
 * 
 * @author dixon.alvarez
 * @version Iteración 6 07/01/2015 12:26:00
 * 
 */

public class ConsultaRegistroVehicularDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismo;
    private String placa;
    private Integer idTipoIdentificacion;
    private String numeroIdentificacion;
    private Date fechaConsulta;
    private String numeroLicenciaConduccion;

    public ConsultaRegistroVehicularDTO() {

    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getNumeroLicenciaConduccion() {
        return numeroLicenciaConduccion;
    }

    public void setNumeroLicenciaConduccion(String numeroLicenciaConduccion) {
        this.numeroLicenciaConduccion = numeroLicenciaConduccion;
    }

}
