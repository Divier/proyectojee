package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author giovanni.velandia
 */
public class RegistroRadicarExcepcionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private RadicarExcepcionDTO radicarExcepcionDTO;
    private List<RegistroArchivoExcepcionDTO> registroArchivoExcepcionDTOs;

    public RadicarExcepcionDTO getRadicarExcepcionDTO() {
        return radicarExcepcionDTO;
    }

    public void setRadicarExcepcionDTO(RadicarExcepcionDTO radicarExcepcionDTO) {
        this.radicarExcepcionDTO = radicarExcepcionDTO;
    }

    public List<RegistroArchivoExcepcionDTO> getRegistroArchivoExcepcionDTOs() {
        return registroArchivoExcepcionDTOs;
    }

    public void setRegistroArchivoExcepcionDTOs(List<RegistroArchivoExcepcionDTO> registroArchivoExcepcionDTOs) {
        this.registroArchivoExcepcionDTOs = registroArchivoExcepcionDTOs;
    }
}