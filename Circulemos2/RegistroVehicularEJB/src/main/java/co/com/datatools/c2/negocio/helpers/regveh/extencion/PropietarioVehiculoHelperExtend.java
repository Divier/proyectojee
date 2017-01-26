package co.com.datatools.c2.negocio.helpers.regveh.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.entidades.PropietarioVehiculo;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.regveh.PropietarioVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.regveh.TipoPropietarioHelper;

public class PropietarioVehiculoHelperExtend extends PropietarioVehiculoHelper {

    public static List<PropietarioVehiculoDTO> toListLevel1DTOExtends(List<PropietarioVehiculo> listEntidad) {
        List<PropietarioVehiculoDTO> listDto = new ArrayList<PropietarioVehiculoDTO>();
        for (PropietarioVehiculo entidad : listEntidad) {
            listDto.add(toLevel1DTOExtends(entidad));
        }
        return listDto;
    }

    public static PropietarioVehiculoDTO toLevel1DTOExtends(PropietarioVehiculo entidad) {
        PropietarioVehiculoDTO dto = toLevel0DTO(entidad);
        if (entidad.getPersona() != null) {
            dto.setPersona(PersonaHelper.toLevel1DTO(entidad.getPersona()));
        }
        if (entidad.getTipoPropietario() != null) {
            dto.setTipoPropietario(TipoPropietarioHelper.toLevel0DTO(entidad.getTipoPropietario()));
        }

        return dto;
    }
}
