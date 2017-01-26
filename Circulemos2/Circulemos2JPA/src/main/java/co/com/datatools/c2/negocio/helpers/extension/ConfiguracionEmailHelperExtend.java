package co.com.datatools.c2.negocio.helpers.extension;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionEmailDTO;
import co.com.datatools.c2.entidades.ConfiguracionEmail;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.ConfiguracionEmailHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoEmailHelper;

/**
 * Extencion de helper de configuracion de email para creacion de objetos DTO
 * 
 * @author luis.forero
 * 
 */
public class ConfiguracionEmailHelperExtend extends ConfiguracionEmailHelper {

    public static ConfiguracionEmailDTO toLevel2DTO(ConfiguracionEmail entidad) {
        ConfiguracionEmailDTO dto = toLevel1DTO(entidad);
        if (entidad.getTipoEmail() != null)
            dto.setTipoEmail(TipoEmailHelper.toLevel1DTO(entidad.getTipoEmail()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel1DTO(entidad.getUsuario()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<ConfiguracionEmailDTO> toListLevel2DTO(List<ConfiguracionEmail> listEntidad) {
        List<ConfiguracionEmailDTO> listDto = new ArrayList<ConfiguracionEmailDTO>();
        for (ConfiguracionEmail entidad : listEntidad) {
            listDto.add(toLevel2DTO(entidad));
        }
        return listDto;
    }
}
