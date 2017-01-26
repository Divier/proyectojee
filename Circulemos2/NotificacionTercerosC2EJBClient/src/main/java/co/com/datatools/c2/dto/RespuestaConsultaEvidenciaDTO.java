package co.com.datatools.c2.dto;

import java.io.Serializable;

public class RespuestaConsultaEvidenciaDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String idEvidencia;
    private String tipoEvidencia;

    public String getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(String idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public String getTipoEvidencia() {
        return tipoEvidencia;
    }

    public void setTipoEvidencia(String tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }
}