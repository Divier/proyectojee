package co.com.datatools.c2.dto.regveh;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.ColorDTO;
import co.com.datatools.c2.dto.comun.ClaseVehiculoDTO;
import co.com.datatools.c2.dto.comun.ModalidadDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.RadioAccionDTO;
import co.com.datatools.c2.dto.comun.TipoServicioDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Oct 08 09:26:19 COT 2015
 */
public class VehiculoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String licenciaTransito;
    private String numeroMotor;
    private Integer modelo;
    private String placa;
    private Date fechaExpedicionTarjeOpera;
    private Date fechaVencimientoTarjeOpera;
    private Integer numeroTarjetaOpera;
    private ColorDTO color;
    private LineaVehiculoDTO linea;
    private ModalidadDTO modalidad;
    private RadioAccionDTO radioAccion;
    private TipoServicioDTO tipoServicio;
    private ClaseVehiculoDTO claseVehiculo;
    private OrganismoTransitoDTO organismoTransito;
    private List<PropietarioVehiculoDTO> propietarioVehiculoList;
    private List<EmpresaVehiculoDTO> empresaVehiculoList;
    private List<HistoricoVehiculoDTO> historicoVehiculoList;
    // Adicionado por COM_050
    private TipoTransportePasajeroDTO tipoTransportePasajero;

    // --- Constructor
    public VehiculoDTO() {
    }

    public VehiculoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenciaTransito() {
        return this.licenciaTransito;
    }

    public void setLicenciaTransito(String licenciaTransito) {
        this.licenciaTransito = licenciaTransito;
    }

    public String getNumeroMotor() {
        return this.numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public Integer getModelo() {
        return this.modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public Integer getNumeroTarjetaOpera() {
        return this.numeroTarjetaOpera;
    }

    public void setNumeroTarjetaOpera(Integer numeroTarjetaOpera) {
        this.numeroTarjetaOpera = numeroTarjetaOpera;
    }

    public ColorDTO getColor() {
        return this.color;
    }

    public void setColor(ColorDTO color) {
        this.color = color;
    }

    public LineaVehiculoDTO getLinea() {
        return this.linea;
    }

    public void setLinea(LineaVehiculoDTO linea) {
        this.linea = linea;
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

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<PropietarioVehiculoDTO> getPropietarioVehiculoList() {
        if (this.propietarioVehiculoList == null) {
            this.propietarioVehiculoList = new java.util.ArrayList<>(1);
        }
        return this.propietarioVehiculoList;
    }

    public void setPropietarioVehiculoList(List<PropietarioVehiculoDTO> propietarioVehiculoList) {
        this.propietarioVehiculoList = propietarioVehiculoList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<EmpresaVehiculoDTO> getEmpresaVehiculoList() {
        if (this.empresaVehiculoList == null) {
            this.empresaVehiculoList = new java.util.ArrayList<>(1);
        }
        return this.empresaVehiculoList;
    }

    public void setEmpresaVehiculoList(List<EmpresaVehiculoDTO> empresaVehiculoList) {
        this.empresaVehiculoList = empresaVehiculoList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<HistoricoVehiculoDTO> getHistoricoVehiculoList() {
        if (this.historicoVehiculoList == null) {
            this.historicoVehiculoList = new java.util.ArrayList<>(1);
        }
        return this.historicoVehiculoList;
    }

    public void setHistoricoVehiculoList(List<HistoricoVehiculoDTO> historicoVehiculoList) {
        this.historicoVehiculoList = historicoVehiculoList;
    }

    public TipoTransportePasajeroDTO getTipoTransportePasajero() {
        return tipoTransportePasajero;
    }

    public void setTipoTransportePasajero(TipoTransportePasajeroDTO tipoTransportePasajero) {
        this.tipoTransportePasajero = tipoTransportePasajero;
    }

}