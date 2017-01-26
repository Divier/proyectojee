package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author oscar.leon
 */
public class DejarFirmeDTO implements EntidadDtoC2 {

    private static final long serialVersionUID = 1L;

    private Long idFinanciacion;
    private String numeroFirma;

    public Long getIdFinanciacion() {
        return idFinanciacion;
    }

    public void setIdFinanciacion(Long idFinanciacion) {
        this.idFinanciacion = idFinanciacion;
    }

    public String getNumeroFirma() {
        return numeroFirma;
    }

    public void setNumeroFirma(String numeroFirma) {
        this.numeroFirma = numeroFirma;
    }

}
