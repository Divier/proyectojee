package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed May 28 11:08:32 COT 2014
 */
public class GacetaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaPublicacion;
    private UsuarioPersonaDTO usuario;
    private String numero;
    private Date fechaPeriodoInicio;
    private Date fechaPeriodoFin;

    // --- Constructor
    public GacetaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public UsuarioPersonaDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaPeriodoInicio() {
        return fechaPeriodoInicio;
    }

    public void setFechaPeriodoInicio(Date fechaPeriodoInicio) {
        this.fechaPeriodoInicio = fechaPeriodoInicio;
    }

    public Date getFechaPeriodoFin() {
        return fechaPeriodoFin;
    }

    public void setFechaPeriodoFin(Date fechaPeriodoFin) {
        this.fechaPeriodoFin = fechaPeriodoFin;
    }

}
