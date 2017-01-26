package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.entidades.personas.RepresentanteLegal;

/**
 * @author robert.bautista
 * @since 2013-08-11
 */
public class RepresentanteLegalHelperExtend extends RepresentanteLegalHelper {

    // TO DTO LEVEL 1
    public static RepresentanteLegalDTO toLevel1DTO(RepresentanteLegal representanteLegal) {
        RepresentanteLegalDTO representanteLegalDTO = RepresentanteLegalHelper.toLevel0DTO(representanteLegal);

        if (representanteLegal.getPersonaJuridica() != null) {
            representanteLegalDTO.setPersonaJuridica(PersonaJuridicaHelper.toLevel0DTO(representanteLegal
                    .getPersonaJuridica()));
        }

        representanteLegalDTO.setId(representanteLegal.getPersona().getId());
        representanteLegalDTO.setNumeroIdentificacion(representanteLegal.getPersona().getNumeroIdentificacion());

        TipoIdentificacionPersonaDTO tipoIdentificacion = new TipoIdentificacionPersonaDTO();
        tipoIdentificacion.setId(representanteLegal.getPersona().getTipoIdentificacion().getId());
        tipoIdentificacion.setNombre(representanteLegal.getPersona().getTipoIdentificacion().getNombre());
        representanteLegalDTO.setTipoIdentificacion(tipoIdentificacion);

        return representanteLegalDTO;
    }

    // TO LIST<DTO> LEVEL 1
    public static List<RepresentanteLegalDTO> toListLevel1DTO(List<RepresentanteLegal> listRepresentanteLegal) {
        List<RepresentanteLegalDTO> listRepresentanteLegalDTO = new ArrayList<RepresentanteLegalDTO>();
        for (RepresentanteLegal representanteLegal : listRepresentanteLegal) {
            listRepresentanteLegalDTO.add(RepresentanteLegalHelperExtend.toLevel1DTO(representanteLegal));
        }
        return listRepresentanteLegalDTO;
    }

    // TO LIST<DTO> LEVEL 2
    public static List<RepresentanteLegalDTO> toListLevel2DTO(List<RepresentanteLegal> listRepresentanteLegal) {
        List<RepresentanteLegalDTO> listRepresentanteLegalDTO = new ArrayList<RepresentanteLegalDTO>();
        for (RepresentanteLegal representanteLegal : listRepresentanteLegal) {
            listRepresentanteLegalDTO.add(RepresentanteLegalHelperExtend.toLevel2DTO(representanteLegal));
        }
        return listRepresentanteLegalDTO;
    }

    // TO DTO LEVEL 1 - 2
    public static RepresentanteLegalDTO toLevel2DTO(RepresentanteLegal representanteLegal) {
        RepresentanteLegalDTO representanteLegalDTO = RepresentanteLegalHelper.toLevel0DTO(representanteLegal);

        representanteLegalDTO.setPersonaJuridica(PersonaJuridicaHelper.toLevel0DTO(representanteLegal
                .getPersonaJuridica()));
        // TODO FIX Helper
        // representanteLegalDTO.setPersona(PersonaHelper.toLevel1DTO(representanteLegal.getPersona()));
        return representanteLegalDTO;
    }

}
