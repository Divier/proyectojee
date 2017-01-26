package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:04:41 COT 2016
 */
public class SolicitudBienEntidadDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Long id;
    private SolicitudOficioBienDTO solicitudOficioBienDTO;
    private ConfiguracionEntidadDTO configuracionEntidadDTO;

    public SolicitudBienEntidadDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SolicitudOficioBienDTO getSolicitudOficioBienDTO() {
        return solicitudOficioBienDTO;
    }

    public void setSolicitudOficioBienDTO(SolicitudOficioBienDTO solicitudOficioBienDTO) {
        this.solicitudOficioBienDTO = solicitudOficioBienDTO;
    }

    public ConfiguracionEntidadDTO getConfiguracionEntidadDTO() {
        return configuracionEntidadDTO;
    }

    public void setConfiguracionEntidadDTO(ConfiguracionEntidadDTO configuracionEntidadDTO) {
        this.configuracionEntidadDTO = configuracionEntidadDTO;
    }

}