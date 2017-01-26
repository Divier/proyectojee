package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 14 15:31:14 COT 2016
 */
public class EventoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String nombreEvento;
    private String lugar;
    private String numeroReferencia;
    private Date fecha;
    private Date horaInicio;
    private Date horaFin;
    private Date fechaRegistro;
    private TipoProcesoEventoDTO tipoProcesoEvento;
    private TipoEventoDTO tipoEvento;
    private UsuarioPersonaDTO usuario;
    private EstadoEventoDTO estadoEvento;
    private ConfiguracionHorarioDTO configuracionHorario;
    private String observacion;

    // --- Constructor
    public EventoDTO() {
    }

    public EventoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return this.nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNumeroReferencia() {
        return this.numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TipoProcesoEventoDTO getTipoProcesoEvento() {
        return this.tipoProcesoEvento;
    }

    public void setTipoProcesoEvento(TipoProcesoEventoDTO tipoProceso) {
        this.tipoProcesoEvento = tipoProceso;
    }

    public TipoEventoDTO getTipoEvento() {
        return this.tipoEvento;
    }

    public void setTipoEvento(TipoEventoDTO tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public EstadoEventoDTO getEstadoEvento() {
        return this.estadoEvento;
    }

    public void setEstadoEvento(EstadoEventoDTO estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public ConfiguracionHorarioDTO getConfiguracionHorario() {
        return this.configuracionHorario;
    }

    public void setConfiguracionHorario(ConfiguracionHorarioDTO configuracionHorario) {
        this.configuracionHorario = configuracionHorario;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
