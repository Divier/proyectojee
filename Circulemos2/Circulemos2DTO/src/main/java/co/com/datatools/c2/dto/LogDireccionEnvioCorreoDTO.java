package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:19:26 COT 2014
 */
public class LogDireccionEnvioCorreoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private LogEnvioCorreoDTO logEnvioCorreo;
    private String direccionDestino;
    private String tipoDestinatario;

    // --- Constructor
    public LogDireccionEnvioCorreoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogEnvioCorreoDTO getLogEnvioCorreo() {
        return logEnvioCorreo;
    }

    public void setLogEnvioCorreo(LogEnvioCorreoDTO logEnvioCorreo) {
        this.logEnvioCorreo = logEnvioCorreo;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getTipoDestinatario() {
        return tipoDestinatario;
    }

    public void setTipoDestinatario(String tipoDestinatario) {
        this.tipoDestinatario = tipoDestinatario;
    }

}
