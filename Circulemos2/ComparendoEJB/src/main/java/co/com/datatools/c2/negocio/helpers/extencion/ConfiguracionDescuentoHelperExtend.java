package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ConfiguracionDescuentoDTO;
import co.com.datatools.c2.dto.comparendo.MedioImposicionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.TipoNotificacionComparendoDTO;
import co.com.datatools.c2.entidades.ConfiguracionDescuento;
import co.com.datatools.c2.entidades.MedioImposicionComparendo;
import co.com.datatools.c2.entidades.TipoNotificacionComparendo;
import co.com.datatools.c2.negocio.helpers.comparendos.ConfiguracionDescuentoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.MedioImposicionComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoNotificacionComparendoHelper;

/**
 * @author julio.pinzon
 * @version 3.0 - Wed Jan 27 10:10:00 COT 2016
 */
public class ConfiguracionDescuentoHelperExtend extends ConfiguracionDescuentoHelper {
    // --- to DTO
    public static List<ConfiguracionDescuentoDTO> toListLevel1DTO(List<ConfiguracionDescuento> listEntidad) {
        List<ConfiguracionDescuentoDTO> listDto = new ArrayList<ConfiguracionDescuentoDTO>();
        for (ConfiguracionDescuento entidad : listEntidad) {
            ConfiguracionDescuentoDTO configuracion = toLevel1DTO(entidad);

            if (entidad.getMedioImposicionList() != null) {
                configuracion.setMedioImposicionList(new ArrayList<MedioImposicionComparendoDTO>());
                for (MedioImposicionComparendo medioImposicionComparendo : entidad.getMedioImposicionList()) {
                    configuracion.getMedioImposicionList().add(
                            MedioImposicionComparendoHelper.toLevel0DTO(medioImposicionComparendo));
                }
            }
            if (entidad.getTipoNotificacionList() != null) {
                configuracion.setTipoNotificacionList(new ArrayList<TipoNotificacionComparendoDTO>());
                for (TipoNotificacionComparendo tipoNotificacionComparendo : entidad.getTipoNotificacionList()) {
                    configuracion.getTipoNotificacionList().add(
                            TipoNotificacionComparendoHelper.toLevel0DTO(tipoNotificacionComparendo));
                }
            }

            listDto.add(configuracion);
        }
        return listDto;
    }

    // --- fin to DTO

    /**
     * Encuentra la configuracion adecuada para el medio de imposicion y tipo de notificacion
     * 
     * @param configuraciones
     * @param idMedioImposicionComparendo
     * @param idTipoNotificacion
     * @return Configuracion asociada
     */
    public static ConfiguracionDescuentoDTO obtenerConfiguracion(List<ConfiguracionDescuentoDTO> configuraciones,
            Integer idMedioImposicionComparendo, Integer idTipoNotificacion) {
        ConfiguracionDescuentoDTO configuracionDescuento = null;
        for (ConfiguracionDescuentoDTO configuracionDescuentoDTO : configuraciones) {
            if (configuracionDescuentoDTO.getMedioImposicionList() != null) {
                for (MedioImposicionComparendoDTO medioImposicionComparendoDTO : configuracionDescuentoDTO
                        .getMedioImposicionList()) {
                    if (medioImposicionComparendoDTO.getId().equals(idMedioImposicionComparendo)) {
                        if (configuracionDescuentoDTO.getTipoNotificacionList() != null) {
                            for (TipoNotificacionComparendoDTO tipoNotificacionComparendoDTO : configuracionDescuentoDTO
                                    .getTipoNotificacionList()) {
                                if (tipoNotificacionComparendoDTO.getId().equals(idTipoNotificacion)) {
                                    configuracionDescuento = configuracionDescuentoDTO;
                                }
                            }
                        }
                    }
                }
            } else {
                continue;
            }
        }
        return configuracionDescuento;
    }

}
