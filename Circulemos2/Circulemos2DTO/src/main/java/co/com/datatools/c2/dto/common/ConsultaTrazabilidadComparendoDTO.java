package co.com.datatools.c2.dto.common;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO utilizado para el caso de uso CU_CIR20_DAT_COM_016 - Consultar trazabilidad del comparendo, para facilitar la construccion de los parametros de
 * entrada
 * 
 * @author rodrigo.cruz
 * 
 */
public class ConsultaTrazabilidadComparendoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numeroComparendo;
    private Integer codigoOrganismo;
    private Integer tipoIdentificacion;
    private String numeroDocumento;
    private String placaVehiculo;
    private Integer codigoMedioImposicion;
    private Date fechaInicial;
    private Date fechaFinal;

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Integer tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public Integer getCodigoMedioImposicion() {
        return codigoMedioImposicion;
    }

    public void setCodigoMedioImposicion(Integer codigoMedioImposicion) {
        this.codigoMedioImposicion = codigoMedioImposicion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

}
