package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.dto.ActividadDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Mar 01 11:56:35 COT 2016
 */
public class TrazabilidadComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaEjecucion;
    private Date fechaRegistro;
    private String usuarioExterno;
    private String numeroActoAdministrativo;
    private UsuarioPersonaDTO usuario;
    private ActividadDTO actividad;
    private ComparendoDTO comparendo;
    private EstadoComparendoDTO estadoComparendo;

    // --- Constructor
    public TrazabilidadComparendoDTO() {
    }

    public TrazabilidadComparendoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaEjecucion() {
        return this.fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuarioExterno() {
        return this.usuarioExterno;
    }

    public void setUsuarioExterno(String usuarioExterno) {
        this.usuarioExterno = usuarioExterno;
    }

    public String getNumeroActoAdministrativo() {
        return this.numeroActoAdministrativo;
    }

    public void setNumeroActoAdministrativo(String numeroActoAdministrativo) {
        this.numeroActoAdministrativo = numeroActoAdministrativo;
    }

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public ActividadDTO getActividad() {
        return this.actividad;
    }

    public void setActividad(ActividadDTO actividad) {
        this.actividad = actividad;
    }

    public ComparendoDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public EstadoComparendoDTO getEstadoComparendo() {
        return this.estadoComparendo;
    }

    public void setEstadoComparendo(EstadoComparendoDTO estadoComparendo) {
        this.estadoComparendo = estadoComparendo;
    }

}
