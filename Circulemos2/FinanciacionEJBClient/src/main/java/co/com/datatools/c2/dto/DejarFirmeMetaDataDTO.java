package co.com.datatools.c2.dto;

import java.io.Serializable;

public class DejarFirmeMetaDataDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long idDocumento;
    private FinanciacionDTO financiacionDTO;

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public FinanciacionDTO getFinanciacionDTO() {
        return financiacionDTO;
    }

    public void setFinanciacionDTO(FinanciacionDTO financiacionDTO) {
        this.financiacionDTO = financiacionDTO;
    }
}