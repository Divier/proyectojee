package co.com.datatools.c2.dto.common.cartera;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jul 31 15:47:48 COT 2014
 */
public class LogRegistrarCarteraDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaLog;
    private String codigoExcepcion;
    private String descripcionExcepcion;
    private UsuarioPersonaDTO usuario;

    // private ComparendoDTO comparendo;

    // --- Constructor
    public LogRegistrarCarteraDTO() {
    }

    public LogRegistrarCarteraDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaLog() {
        return this.fechaLog;
    }

    public void setFechaLog(Date fechaLog) {
        this.fechaLog = fechaLog;
    }

    public String getCodigoExcepcion() {
        return this.codigoExcepcion;
    }

    public void setCodigoExcepcion(String codigoExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
    }

    public String getDescripcionExcepcion() {
        return this.descripcionExcepcion;
    }

    public void setDescripcionExcepcion(String descripcionExcepcion) {
        this.descripcionExcepcion = descripcionExcepcion;
    }

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }
    /*
     * public ComparendoDTO getComparendo() { return this.comparendo; }
     * 
     * public void setComparendo(ComparendoDTO comparendo) { this.comparendo = comparendo; }
     */
}
