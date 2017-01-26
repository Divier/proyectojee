package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.dto.ColorDTO;
import co.com.datatools.c2.dto.comun.ClaseVehiculoDTO;
import co.com.datatools.c2.dto.comun.ModalidadDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.RadioAccionDTO;
import co.com.datatools.c2.dto.comun.TipoServicioDTO;
import co.com.datatools.c2.dto.regveh.LineaVehiculoDTO;
import co.com.datatools.c2.dto.regveh.MarcaVehiculoDTO;
import co.com.datatools.c2.dto.regveh.TipoTransportePasajeroDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 20 15:23:40 COT 2015
 */
public class ComparendoVehiculoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private OrganismoTransitoDTO organismoMatriVehic;
    private OrganismoTransitoDTO organismoLicenciaTransito;
    private String identificacionVehiculo;
    private String modelo;
    private String numeroLicenciaTransito;
    private String numeroMotor;
    private String numeroTarjetaOperacion;
    private Integer pesoNeto;
    private Integer pesoSeco;
    private String placaVehiculo;
    private ClaseVehiculoDTO claseVehiculo;
    private ColorDTO color;
    private ComparendoDTO comparendo;
    private LineaVehiculoDTO lineaVehiculo;
    private MarcaVehiculoDTO marcaVehiculo;
    private ModalidadDTO modalidad;
    private NivelServicioDTO nivelServicio;
    private RadioAccionDTO radioAccion;
    private TipoServicioDTO tipoServicio;
    private TipoTransportePasajeroDTO tipoTransPasajero;

    // --- Constructor
    public ComparendoVehiculoDTO() {
    }

    public ComparendoVehiculoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacionVehiculo() {
        return this.identificacionVehiculo;
    }

    public void setIdentificacionVehiculo(String identificacionVehiculo) {
        this.identificacionVehiculo = identificacionVehiculo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroLicenciaTransito() {
        return this.numeroLicenciaTransito;
    }

    public void setNumeroLicenciaTransito(String numeroLicenciaTransito) {
        this.numeroLicenciaTransito = numeroLicenciaTransito;
    }

    public String getNumeroMotor() {
        return this.numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroTarjetaOperacion() {
        return this.numeroTarjetaOperacion;
    }

    public void setNumeroTarjetaOperacion(String numeroTarjetaOperacion) {
        this.numeroTarjetaOperacion = numeroTarjetaOperacion;
    }

    public Integer getPesoNeto() {
        return this.pesoNeto;
    }

    public void setPesoNeto(Integer pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public Integer getPesoSeco() {
        return this.pesoSeco;
    }

    public void setPesoSeco(Integer pesoSeco) {
        this.pesoSeco = pesoSeco;
    }

    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public ClaseVehiculoDTO getClaseVehiculo() {
        return this.claseVehiculo;
    }

    public void setClaseVehiculo(ClaseVehiculoDTO claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    public ColorDTO getColor() {
        return this.color;
    }

    public void setColor(ColorDTO color) {
        this.color = color;
    }

    public ComparendoDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public LineaVehiculoDTO getLineaVehiculo() {
        return this.lineaVehiculo;
    }

    public void setLineaVehiculo(LineaVehiculoDTO lineaVehiculo) {
        this.lineaVehiculo = lineaVehiculo;
    }

    public MarcaVehiculoDTO getMarcaVehiculo() {
        return this.marcaVehiculo;
    }

    public void setMarcaVehiculo(MarcaVehiculoDTO marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public ModalidadDTO getModalidad() {
        return this.modalidad;
    }

    public void setModalidad(ModalidadDTO modalidad) {
        this.modalidad = modalidad;
    }

    public NivelServicioDTO getNivelServicio() {
        return this.nivelServicio;
    }

    public void setNivelServicio(NivelServicioDTO nivelServicio) {
        this.nivelServicio = nivelServicio;
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

    public TipoTransportePasajeroDTO getTipoTransPasajero() {
        return this.tipoTransPasajero;
    }

    public void setTipoTransPasajero(TipoTransportePasajeroDTO tipoTransPasajero) {
        this.tipoTransPasajero = tipoTransPasajero;
    }

    public OrganismoTransitoDTO getOrganismoMatriVehic() {
        return organismoMatriVehic;
    }

    public void setOrganismoMatriVehic(OrganismoTransitoDTO organMatriVehic) {
        this.organismoMatriVehic = organMatriVehic;
    }

    public OrganismoTransitoDTO getOrganismoLicenciaTransito() {
        return organismoLicenciaTransito;
    }

    public void setOrganismoLicenciaTransito(OrganismoTransitoDTO organismoLicenciaTransito) {
        this.organismoLicenciaTransito = organismoLicenciaTransito;
    }

    /**
     * Crea una copia partiendo de la informacion del presente comparendoVehiculoDTO
     * 
     * @author luis.forero(2016-02-02)
     */
    public ComparendoVehiculoDTO clone() {
        ComparendoVehiculoDTO clone = new ComparendoVehiculoDTO(new Long(this.id));
        if (this.organismoMatriVehic != null) {
            clone.setOrganismoMatriVehic(this.organismoMatriVehic.clone());
        }
        if (this.organismoLicenciaTransito != null) {
            clone.setOrganismoLicenciaTransito(this.organismoLicenciaTransito.clone());
        }
        clone.setIdentificacionVehiculo(this.identificacionVehiculo != null ? new String(this.identificacionVehiculo)
                : null);

        clone.setModelo(this.modelo != null ? new String(this.modelo) : null);
        clone.setNumeroLicenciaTransito(this.numeroLicenciaTransito != null ? new String(this.numeroLicenciaTransito)
                : null);
        clone.setNumeroMotor(this.numeroMotor != null ? new String(this.numeroMotor) : null);
        clone.setNumeroTarjetaOperacion(this.numeroTarjetaOperacion != null ? new String(this.numeroTarjetaOperacion)
                : null);
        clone.setPesoNeto(this.pesoNeto != null ? new Integer(this.pesoNeto) : null);
        clone.setPesoSeco(this.pesoSeco != null ? new Integer(this.pesoSeco) : null);
        clone.setPlacaVehiculo(this.placaVehiculo != null ? new String(this.placaVehiculo) : null);
        if (this.claseVehiculo != null) {
            clone.setClaseVehiculo(this.claseVehiculo.clone());
        }
        if (color != null) {
            ColorDTO colorDTO = new ColorDTO(this.color.getId() != null ? new Integer(this.color.getId()) : null);
            colorDTO.setNombre(this.color.getNombre() != null ? new String(this.color.getNombre()) : null);
            colorDTO.setCodigo(this.color.getCodigo() != null ? new String(this.color.getCodigo()) : null);
            colorDTO.setSigla(this.color.getSigla() != null ? new String(this.color.getSigla()) : null);
            clone.setColor(colorDTO);

        }

        if (lineaVehiculo != null) {
            LineaVehiculoDTO lineaVehiculoDTO = new LineaVehiculoDTO(this.lineaVehiculo.getId() != null ? new Integer(
                    this.lineaVehiculo.getId()) : null);
            lineaVehiculoDTO.setNombre(this.lineaVehiculo.getNombre() != null ? new String(this.lineaVehiculo
                    .getNombre()) : null);
            lineaVehiculoDTO.setCodigo(this.lineaVehiculo.getCodigo() != null ? new String(this.lineaVehiculo
                    .getCodigo()) : null);
            lineaVehiculoDTO.setDescripcion(this.lineaVehiculo.getDescripcion() != null ? new String(this.lineaVehiculo
                    .getDescripcion()) : null);
            clone.setLineaVehiculo(lineaVehiculoDTO);
        }

        if (this.marcaVehiculo != null) {
            MarcaVehiculoDTO marcaVehiculoDTO = new MarcaVehiculoDTO(this.marcaVehiculo.getId() != null ? new Integer(
                    this.marcaVehiculo.getId()) : null);
            marcaVehiculoDTO.setCodigo(this.marcaVehiculo.getCodigo() != null ? new String(this.marcaVehiculo
                    .getCodigo()) : null);
            marcaVehiculoDTO.setNombre(this.marcaVehiculo.getNombre() != null ? new String(this.marcaVehiculo
                    .getNombre()) : null);
            marcaVehiculoDTO.setSigla(this.marcaVehiculo.getSigla() != null ? new String(this.marcaVehiculo.getSigla())
                    : null);
            clone.setMarcaVehiculo(marcaVehiculoDTO);
        }
        if (this.modalidad != null) {
            ModalidadDTO modalidadDTO = new ModalidadDTO(this.modalidad.getId() != null ? new Integer(
                    this.modalidad.getId()) : null);
            modalidadDTO.setNombre(this.modalidad.getNombre() != null ? new String(this.modalidad.getNombre()) : null);
            modalidadDTO.setSigla(this.modalidad.getSigla() != null ? new String(this.modalidad.getSigla()) : null);
            modalidadDTO.setCodigo(this.modalidad.getCodigo() != null ? new String(this.modalidad.getCodigo()) : null);
            clone.setModalidad(modalidadDTO);
        }
        if (this.nivelServicio != null) {
            NivelServicioDTO nivelServicioDTO = new NivelServicioDTO(this.nivelServicio.getId() != null ? new Integer(
                    this.nivelServicio.getId()) : null);
            nivelServicioDTO.setCodigo(this.nivelServicio.getCodigo() != null ? new String(this.nivelServicio
                    .getCodigo()) : null);
            nivelServicioDTO.setDescripcion(this.nivelServicio.getDescripcion() != null ? new String(this.nivelServicio
                    .getDescripcion()) : null);
            nivelServicioDTO.setNombre(this.nivelServicio.getNombre() != null ? new String(this.nivelServicio
                    .getNombre()) : null);
            clone.setNivelServicio(nivelServicioDTO);
        }
        if (this.radioAccion != null) {
            RadioAccionDTO radioAccionDTO = new RadioAccionDTO(this.radioAccion.getId() != null ? new Integer(
                    this.radioAccion.getId()) : null);
            radioAccionDTO.setCodigo(this.radioAccion.getCodigo() != null ? new String(this.radioAccion.getCodigo())
                    : null);
            radioAccionDTO.setDescripcion(this.radioAccion.getDescripcion() != null ? new String(this.radioAccion
                    .getDescripcion()) : null);
            radioAccionDTO.setNombre(this.radioAccion.getNombre() != null ? new String(this.radioAccion.getNombre())
                    : null);
            radioAccionDTO.setSigla(this.radioAccion.getSigla() != null ? new String(this.radioAccion.getSigla())
                    : null);
            clone.setRadioAccion(radioAccionDTO);
        }
        if (this.tipoServicio != null) {
            TipoServicioDTO tipoServicioDTO = new TipoServicioDTO(this.tipoServicio.getId() != null ? new Integer(
                    this.tipoServicio.getId()) : null);
            tipoServicioDTO.setCodigo(this.tipoServicio.getCodigo() != null ? new String(this.tipoServicio.getCodigo())
                    : null);
            tipoServicioDTO.setNombre(this.tipoServicio.getNombre() != null ? new String(this.tipoServicio.getNombre())
                    : null);
            tipoServicioDTO.setSigla(this.tipoServicio.getSigla() != null ? new String(this.tipoServicio.getSigla())
                    : null);
            clone.setTipoServicio(tipoServicioDTO);
        }
        if (this.tipoTransPasajero != null) {
            TipoTransportePasajeroDTO tipoTransportePasajeroDTO = new TipoTransportePasajeroDTO(
                    this.tipoTransPasajero.getId() != null ? new Integer(this.tipoTransPasajero.getId()) : null);
            tipoTransportePasajeroDTO.setCodigo(this.tipoTransPasajero.getCodigo() != null ? new String(
                    this.tipoTransPasajero.getCodigo()) : null);
            tipoTransportePasajeroDTO.setDescripcion(this.tipoTransPasajero.getDescripcion() != null ? new String(
                    this.tipoTransPasajero.getDescripcion()) : null);
            tipoTransportePasajeroDTO.setNombre(this.tipoTransPasajero.getNombre() != null ? new String(
                    this.tipoTransPasajero.getNombre()) : null);
            tipoTransportePasajeroDTO.setSigla(this.tipoTransPasajero.getSigla() != null ? new String(
                    this.tipoTransPasajero.getSigla()) : null);
            clone.setTipoTransPasajero(tipoTransportePasajeroDTO);
        }

        return clone;
    }
}