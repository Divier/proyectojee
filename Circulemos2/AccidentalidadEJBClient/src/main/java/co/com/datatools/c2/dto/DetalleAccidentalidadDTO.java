package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.comun.ClaseVehiculoDTO;
import co.com.datatools.c2.dto.comun.TipoCategLicenciaConduccionDTO;
import co.com.datatools.c2.dto.comun.TipoServicioDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 16:10:15 COT 2016
 */
public class DetalleAccidentalidadDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private AccidentalidadDTO accidentalidad;
    private String breveRelacion;
    private String nombrePersona;
    private TipoPersonaIPATDTO tipoPersonaIPAT;
    private String licencia;
    private TipoCategLicenciaConduccionDTO tipoLicencia;
    private String origen;
    private EstadoFisicoDTO estadoFisico;
    private Integer edad;
    private Boolean embriaguez;
    private Boolean aprehendido;
    private ClaseVehiculoDTO claseVehiculo;
    private String tipoVehiculo;
    private String placa;
    private TipoServicioDTO tipoServicio;
    private String calleCarretera;
    private SentidoDTO sentido;
    private CarrilDTO carril;
    private Boolean retenido;
    private String version;
    private String danosVehiculo;
    private String circustancias;
    private String observaciones;
    private String zona_impacto;
    private String datos_adjuntos;

    // --- Constructor
    public DetalleAccidentalidadDTO() {
    }

    public DetalleAccidentalidadDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccidentalidadDTO getAccidentalidad() {
        return this.accidentalidad;
    }

    public void setAccidentalidad(AccidentalidadDTO accidentalidad) {
        this.accidentalidad = accidentalidad;
    }

    public String getBreveRelacion() {
        return this.breveRelacion;
    }

    public void setBreveRelacion(String breveRelacion) {
        this.breveRelacion = breveRelacion;
    }

    public String getNombrePersona() {
        return this.nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public TipoPersonaIPATDTO getTipoPersonaIPAT() {
        return this.tipoPersonaIPAT;
    }

    public void setTipoPersonaIPAT(TipoPersonaIPATDTO tipoPersonaIPAT) {
        this.tipoPersonaIPAT = tipoPersonaIPAT;
    }

    public String getLicencia() {
        return this.licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public TipoCategLicenciaConduccionDTO getTipoLicencia() {
        return this.tipoLicencia;
    }

    public void setTipoLicencia(TipoCategLicenciaConduccionDTO tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public String getOrigen() {
        return this.origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public EstadoFisicoDTO getEstadoFisico() {
        return this.estadoFisico;
    }

    public void setEstadoFisico(EstadoFisicoDTO estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public Integer getEdad() {
        return this.edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getEmbriaguez() {
        return this.embriaguez;
    }

    public void setEmbriaguez(Boolean embriaguez) {
        this.embriaguez = embriaguez;
    }

    public Boolean getAprehendido() {
        return this.aprehendido;
    }

    public void setAprehendido(Boolean aprehendido) {
        this.aprehendido = aprehendido;
    }

    public ClaseVehiculoDTO getClaseVehiculo() {
        return this.claseVehiculo;
    }

    public void setClaseVehiculo(ClaseVehiculoDTO claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    public String getTipoVehiculo() {
        return this.tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoServicioDTO getTipoServicio() {
        return this.tipoServicio;
    }

    public void setTipoServicio(TipoServicioDTO tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getCalleCarretera() {
        return this.calleCarretera;
    }

    public void setCalleCarretera(String calleCarretera) {
        this.calleCarretera = calleCarretera;
    }

    public SentidoDTO getSentido() {
        return this.sentido;
    }

    public void setSentido(SentidoDTO sentido) {
        this.sentido = sentido;
    }

    public CarrilDTO getCarril() {
        return this.carril;
    }

    public void setCarril(CarrilDTO carril) {
        this.carril = carril;
    }

    public Boolean getRetenido() {
        return this.retenido;
    }

    public void setRetenido(Boolean retenido) {
        this.retenido = retenido;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDanosVehiculo() {
        return this.danosVehiculo;
    }

    public void setDanosVehiculo(String danosVehiculo) {
        this.danosVehiculo = danosVehiculo;
    }

    public String getCircustancias() {
        return this.circustancias;
    }

    public void setCircustancias(String circustancias) {
        this.circustancias = circustancias;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getZona_impacto() {
        return this.zona_impacto;
    }

    public void setZona_impacto(String zona_impacto) {
        this.zona_impacto = zona_impacto;
    }

    public String getDatos_adjuntos() {
        return this.datos_adjuntos;
    }

    public void setDatos_adjuntos(String datos_adjuntos) {
        this.datos_adjuntos = datos_adjuntos;
    }

}
