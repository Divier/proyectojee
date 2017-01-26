package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:04:41 COT 2016
 */
public class EmbargoBienDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Long id;
    private EmbargoDTO embargoDTO;
    private BienDTO bienDTO;

    public EmbargoBienDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmbargoDTO getEmbargoDTO() {
        return embargoDTO;
    }

    public void setEmbargoDTO(EmbargoDTO embargoDTO) {
        this.embargoDTO = embargoDTO;
    }

    public BienDTO getBienDTO() {
        return bienDTO;
    }

    public void setBienDTO(BienDTO bienDTO) {
        this.bienDTO = bienDTO;
    }

}