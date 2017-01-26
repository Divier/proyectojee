package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:36:38 COT 2016
 */
public class AccidentalidadDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String consecutivo;
    private TipoAccidenteDTO tipoAccidente;
    private String consecuencias;
    private String ubicacionGps;
    private Date parteFinalizado;
    private String lugarAccidente;
    private Date fechaAccidente;
    private Date fechaAviso;
    private PrevencionDTO prevencion;
    private DelegacionDTO delegacion;
    private String referenciaHechos;
    private String agente;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private UsuarioPersonaDTO usuario;
    private List<ArchivoAccidentalidadDTO> archivoAccidentalidad;
    private List<DetalleAccidentalidadDTO> detalleAccidentalidad;
    private EstadoAccidentalidadDTO estadoAccidentalidad;

    // --- Constructor
    public AccidentalidadDTO() {
    }

    public AccidentalidadDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public TipoAccidenteDTO getTipoAccidente() {
        return this.tipoAccidente;
    }

    public void setTipoAccidente(TipoAccidenteDTO tipoAccidente) {
        this.tipoAccidente = tipoAccidente;
    }

    public String getConsecuencias() {
        return this.consecuencias;
    }

    public void setConsecuencias(String consecuencias) {
        this.consecuencias = consecuencias;
    }

    public String getUbicacionGps() {
        return this.ubicacionGps;
    }

    public void setUbicacionGps(String ubicacionGps) {
        this.ubicacionGps = ubicacionGps;
    }

    public Date getParteFinalizado() {
        return this.parteFinalizado;
    }

    public void setParteFinalizado(Date parteFinalizado) {
        this.parteFinalizado = parteFinalizado;
    }

    public String getLugarAccidente() {
        return this.lugarAccidente;
    }

    public void setLugarAccidente(String lugarAccidente) {
        this.lugarAccidente = lugarAccidente;
    }

    public Date getFechaAccidente() {
        return this.fechaAccidente;
    }

    public void setFechaAccidente(Date fechaAccidente) {
        this.fechaAccidente = fechaAccidente;
    }

    public Date getFechaAviso() {
        return this.fechaAviso;
    }

    public void setFechaAviso(Date fechaAviso) {
        this.fechaAviso = fechaAviso;
    }

    public PrevencionDTO getPrevencion() {
        return this.prevencion;
    }

    public void setPrevencion(PrevencionDTO prevencion) {
        this.prevencion = prevencion;
    }

    public DelegacionDTO getDelegacion() {
        return this.delegacion;
    }

    public void setDelegacion(DelegacionDTO delegacion) {
        this.delegacion = delegacion;
    }

    public String getReferenciaHechos() {
        return this.referenciaHechos;
    }

    public void setReferenciaHechos(String referenciaHechos) {
        this.referenciaHechos = referenciaHechos;
    }

    public String getAgente() {
        return this.agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public List<ArchivoAccidentalidadDTO> getArchivoAccidentalidad() {
        return archivoAccidentalidad;
    }

    public void setArchivoAccidentalidad(List<ArchivoAccidentalidadDTO> archivoAccidentalidad) {
        this.archivoAccidentalidad = archivoAccidentalidad;
    }

    public List<DetalleAccidentalidadDTO> getDetalleAccidentalidad() {
        return detalleAccidentalidad;
    }

    public void setDetalleAccidentalidad(List<DetalleAccidentalidadDTO> detalleAccidentalidad) {
        this.detalleAccidentalidad = detalleAccidentalidad;
    }

    public EstadoAccidentalidadDTO getEstadoAccidentalidad() {
        return estadoAccidentalidad;
    }

    public void setEstadoAccidentalidad(EstadoAccidentalidadDTO estadoAccidentalidad) {
        this.estadoAccidentalidad = estadoAccidentalidad;
    }

}
