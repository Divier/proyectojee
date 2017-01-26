package co.com.datatools.c2.managed_bean.coactivo.radicarExcepciones;

import java.io.Serializable;
import java.util.ArrayList;

import co.com.datatools.c2.dto.RadicarExcepcionDTO;
import co.com.datatools.c2.dto.RegistroArchivoExcepcionDTO;
import co.com.datatools.c2.dto.RegistroRadicarExcepcionDTO;

/**
 * 
 * @author giovanni.velandia
 *
 */
public class RadicarExcepcionFL implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "radicarExcepcionFL";

    private RegistroRadicarExcepcionDTO registroRadicarExcepcionDTO;
    private RegistroArchivoExcepcionDTO registroArchivoExcepcionSelDTO;

    public RadicarExcepcionFL() {
        registroRadicarExcepcionDTO = new RegistroRadicarExcepcionDTO();
        registroRadicarExcepcionDTO.setRadicarExcepcionDTO(new RadicarExcepcionDTO());
        registroRadicarExcepcionDTO.setRegistroArchivoExcepcionDTOs(new ArrayList<RegistroArchivoExcepcionDTO>());
    }

    public RegistroRadicarExcepcionDTO getRegistroRadicarExcepcionDTO() {
        return registroRadicarExcepcionDTO;
    }

    public void setRegistroRadicarExcepcionDTO(RegistroRadicarExcepcionDTO registroRadicarExcepcionDTO) {
        this.registroRadicarExcepcionDTO = registroRadicarExcepcionDTO;
    }

    public RegistroArchivoExcepcionDTO getRegistroArchivoExcepcionSelDTO() {
        return registroArchivoExcepcionSelDTO;
    }

    public void setRegistroArchivoExcepcionSelDTO(RegistroArchivoExcepcionDTO registroArchivoExcepcionSelDTO) {
        this.registroArchivoExcepcionSelDTO = registroArchivoExcepcionSelDTO;
    }

}
