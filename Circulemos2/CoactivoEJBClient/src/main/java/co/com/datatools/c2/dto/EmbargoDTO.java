package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:04:41 COT 2016
 */
public class EmbargoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String valorEmbargo;

    public EmbargoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValorEmbargo() {
        return valorEmbargo;
    }

    public void setValorEmbargo(String valorEmbargo) {
        this.valorEmbargo = valorEmbargo;
    }

}