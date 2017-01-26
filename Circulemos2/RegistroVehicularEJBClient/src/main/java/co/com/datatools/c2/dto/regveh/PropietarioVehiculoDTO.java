package co.com.datatools.c2.dto.regveh;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 08:59:17 COT 2015
 */
public class PropietarioVehiculoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal porcentaje;
    private VehiculoDTO vehiculo;
    private PersonaDTO persona;
    private TipoPropietarioDTO tipoPropietario;

    // --- Constructor
    public PropietarioVehiculoDTO() {
    }

    public PropietarioVehiculoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getPorcentaje() {
        return this.porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public VehiculoDTO getVehiculo() {
        return this.vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public TipoPropietarioDTO getTipoPropietario() {
        return this.tipoPropietario;
    }

    public void setTipoPropietario(TipoPropietarioDTO tipoPropietario) {
        this.tipoPropietario = tipoPropietario;
    }

}
