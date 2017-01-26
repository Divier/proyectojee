package co.com.datatools.c2.dto.regveh;

import java.io.Serializable;
import java.util.Date;

/**
 * Objeto que se encarga de encapsular los filtros de consulta desde la interfaz para las empresas de un vehículo
 * 
 * @author dixon.alvarez
 * @version Iteración 6
 * 
 */

public class ConsultaEmpresaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismo;
    private String nit;
    private String placa;
    private Date fechaInicioVigencia;
    private Date fechaFinVigencia;

    public ConsultaEmpresaDTO() {

    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }
}
