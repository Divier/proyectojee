package co.com.datatools.c2.dto.comparendo;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Apr 28 13:48:36 COT 2016
 */
public class BloqueoComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String numeroComparendo;
    private Integer codigoOrigen;
    private String codigoInfraccion;
    private Date fechaRegistro;
    private Date fechaReporte;
    private Date fechaInfraccion;
    private Date horaInfraccion;
    private String placaVehiculo;
    private Integer idTipoIdentificacionInfractor;
    private String numeroIdentificacionInfractor;
    private String nombre1Infractor;
    private String nombre2Infractor;
    private String apellido1Infractor;
    private String apellido2Infractor;
    private List<DetalleBloqueoDTO> detalleBloqueantes;
    private UsuarioPersonaDTO usuarioPersona;
    private String nombreOrigen;

    // --- Constructor
    public BloqueoComparendoDTO() {
    }

    public BloqueoComparendoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroComparendo() {
        return this.numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Integer getCodigoOrigen() {
        return this.codigoOrigen;
    }

    public void setCodigoOrigen(Integer codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    public String getCodigoInfraccion() {
        return this.codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaReporte() {
        return this.fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Date getFechaInfraccion() {
        return this.fechaInfraccion;
    }

    public void setFechaInfraccion(Date fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public Date getHoraInfraccion() {
        return this.horaInfraccion;
    }

    public void setHoraInfraccion(Date horaInfraccion) {
        this.horaInfraccion = horaInfraccion;
    }

    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public Integer getIdTipoIdentificacionInfractor() {
        return this.idTipoIdentificacionInfractor;
    }

    public void setIdTipoIdentificacionInfractor(Integer idTipoIdentificacionInfractor) {
        this.idTipoIdentificacionInfractor = idTipoIdentificacionInfractor;
    }

    public String getNumeroIdentificacionInfractor() {
        return this.numeroIdentificacionInfractor;
    }

    public void setNumeroIdentificacionInfractor(String numeroIdentificacionInfractor) {
        this.numeroIdentificacionInfractor = numeroIdentificacionInfractor;
    }

    public String getNombre1Infractor() {
        return this.nombre1Infractor;
    }

    public void setNombre1Infractor(String nombre1Infractor) {
        this.nombre1Infractor = nombre1Infractor;
    }

    public String getNombre2Infractor() {
        return this.nombre2Infractor;
    }

    public void setNombre2Infractor(String nombre2Infractor) {
        this.nombre2Infractor = nombre2Infractor;
    }

    public String getApellido1Infractor() {
        return this.apellido1Infractor;
    }

    public void setApellido1Infractor(String apellido1Infractor) {
        this.apellido1Infractor = apellido1Infractor;
    }

    public String getApellido2Infractor() {
        return this.apellido2Infractor;
    }

    public void setApellido2Infractor(String apellido2Infractor) {
        this.apellido2Infractor = apellido2Infractor;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleBloqueoDTO> getDetalleBloqueantes() {
        if (this.detalleBloqueantes == null) {
            this.detalleBloqueantes = new java.util.ArrayList<>(1);
        }
        return this.detalleBloqueantes;
    }

    public void setDetalleBloqueantes(List<DetalleBloqueoDTO> detalleBloqueantes) {
        this.detalleBloqueantes = detalleBloqueantes;
    }

    public UsuarioPersonaDTO getUsuarioPersona() {
        return this.usuarioPersona;
    }

    public void setUsuarioPersona(UsuarioPersonaDTO usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }
}