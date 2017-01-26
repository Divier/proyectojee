package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class LogLiquidarComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Date fechaLog;
    private String codigoExcepcion;
    private String descripcionExcepcion;
    private String detalleExcepcion;
    private UsuarioPersonaDTO usuario;

    // --- Constructor
    public LogLiquidarComparendoDTO() {
    }

    public LogLiquidarComparendoDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public String getDetalleExcepcion() {
        return this.detalleExcepcion;
    }

    public void setDetalleExcepcion(String detalleExcepcion) {
        this.detalleExcepcion = detalleExcepcion;
    }

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

}