package co.com.datatools.c2.dto.formularios;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Manejo de consultas para fomularios cifras seguimiento
 * 
 * @author giovanni.velandia
 * 
 */
public class ConsultaCifrasSeguimientoFormularioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Date fechaInicialCambioEstado;
    private Date fechaFinalCambioEstado;
    private EstadoFormularioDTO estadoFormularioDTO;
    private Integer codigoOrganismo;

    public ConsultaCifrasSeguimientoFormularioDTO() {
        // TODO Auto-generated constructor stub
    }

    public Date getFechaInicialCambioEstado() {
        return fechaInicialCambioEstado;
    }

    public void setFechaInicialCambioEstado(Date fechaInicialCambioEstado) {
        this.fechaInicialCambioEstado = fechaInicialCambioEstado;
    }

    public Date getFechaFinalCambioEstado() {
        return fechaFinalCambioEstado;
    }

    public void setFechaFinalCambioEstado(Date fechaFinalCambioEstado) {
        this.fechaFinalCambioEstado = fechaFinalCambioEstado;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public EstadoFormularioDTO getEstadoFormularioDTO() {
        return estadoFormularioDTO;
    }

    public void setEstadoFormularioDTO(EstadoFormularioDTO estadoFormularioDTO) {
        this.estadoFormularioDTO = estadoFormularioDTO;
    }
}
