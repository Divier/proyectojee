package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:19:26 COT 2014
 */
public class LogAdjuntoEnvioCorreoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private LogEnvioCorreoDTO logEnvioCorreo;
    private String numeroDocumento;
    private byte[] archivo;

    // --- Constructor
    public LogAdjuntoEnvioCorreoDTO() {
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

}
