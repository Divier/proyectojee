package co.com.datatools.c2.dto.regveh;

import java.util.Date;

import co.com.datatools.c2.dto.ColorDTO;
import co.com.datatools.c2.dto.comun.ClaseVehiculoDTO;
import co.com.datatools.c2.dto.comun.ModalidadDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.RadioAccionDTO;
import co.com.datatools.c2.dto.comun.TipoServicioDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 08:58:15 COT 2015
 */
public class HistoricoVehiculoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Long numeroMotor;
    private Date fechaExpedicionTarjeOpera;
    private Date fechaVencimientoTarjeOpera;
    private Integer numeroTarjetaOperacion;
    private String campoCambio;
    private Date fechaInicial;
    private Date fechaFinal;
    private ColorDTO color;
    private ModalidadDTO modalidad;
    private RadioAccionDTO radioAccion;
    private TipoServicioDTO tipoServicio;
    private ClaseVehiculoDTO claseVehiculo;
    private OrganismoTransitoDTO organismoTransito;
    private VehiculoDTO vehiculo;

    // --- Constructor
    public HistoricoVehiculoDTO() {
    }

    public HistoricoVehiculoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroMotor() {
        return this.numeroMotor;
    }

    public void setNumeroMotor(Long numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public Date getFechaExpedicionTarjeOpera() {
        return this.fechaExpedicionTarjeOpera;
    }

    public void setFechaExpedicionTarjeOpera(Date fechaExpedicionTarjeOpera) {
        this.fechaExpedicionTarjeOpera = fechaExpedicionTarjeOpera;
    }

    public Date getFechaVencimientoTarjeOpera() {
        return this.fechaVencimientoTarjeOpera;
    }

    public void setFechaVencimientoTarjeOpera(Date fechaVencimientoTarjeOpera) {
        this.fechaVencimientoTarjeOpera = fechaVencimientoTarjeOpera;
    }

    public Integer getNumeroTarjetaOperacion() {
        return this.numeroTarjetaOperacion;
    }

    public void setNumeroTarjetaOperacion(Integer numeroTarjetaOperacion) {
        this.numeroTarjetaOperacion = numeroTarjetaOperacion;
    }

    public String getCampoCambio() {
        return this.campoCambio;
    }

    public void setCampoCambio(String campoCambio) {
        this.campoCambio = campoCambio;
    }

    public Date getFechaInicial() {
        return this.fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return this.fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public ColorDTO getColor() {
        return this.color;
    }

    public void setColor(ColorDTO color) {
        this.color = color;
    }

    public ModalidadDTO getModalidad() {
        return this.modalidad;
    }

    public void setModalidad(ModalidadDTO modalidad) {
        this.modalidad = modalidad;
    }

    public RadioAccionDTO getRadioAccion() {
        return this.radioAccion;
    }

    public void setRadioAccion(RadioAccionDTO radioAccion) {
        this.radioAccion = radioAccion;
    }

    public TipoServicioDTO getTipoServicio() {
        return this.tipoServicio;
    }

    public void setTipoServicio(TipoServicioDTO tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public ClaseVehiculoDTO getClaseVehiculo() {
        return this.claseVehiculo;
    }

    public void setClaseVehiculo(ClaseVehiculoDTO claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public VehiculoDTO getVehiculo() {
        return this.vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

}
