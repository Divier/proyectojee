package co.com.datatools.c2.negocio.helpers.regveh.extencion;

import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;
import co.com.datatools.c2.entidades.EmpresaVehiculo;
import co.com.datatools.c2.negocio.helpers.personas.PersonaJuridicaHelperExtend;
import co.com.datatools.c2.negocio.helpers.regveh.EmpresaVehiculoHelper;

public class EmpresaVehiculoHelperExtend extends EmpresaVehiculoHelper {

    public static EmpresaVehiculoDTO toLevel1DTOExtends(EmpresaVehiculo entidad) {
        EmpresaVehiculoDTO dto = toLevel0DTO(entidad);
        if (entidad.getPersonaJuridica() != null) {
            dto.setPersonaJuridica(PersonaJuridicaHelperExtend.toPersonaJuridicaDTOSegundoNivel(entidad
                    .getPersonaJuridica()));
        }
        return dto;
    }
}