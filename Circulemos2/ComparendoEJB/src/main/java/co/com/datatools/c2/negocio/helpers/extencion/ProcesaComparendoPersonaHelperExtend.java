package co.com.datatools.c2.negocio.helpers.extencion;

import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.entidades.ProcesaComparendoPersona;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaComparendoPersonaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaDireccionHelper;

public class ProcesaComparendoPersonaHelperExtend extends ProcesaComparendoPersonaHelper {

    public static ProcesaComparendoPersona toLevel1Entity(ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO,
            ProcesaComparendoPersona procesaComparendoPersona) {

        ProcesaComparendoPersona resp = ProcesaComparendoPersonaHelper.toLevel0Entity(procesaComparendoPersonaDTO,
                procesaComparendoPersona);

        ProcesaDireccionDTO direccion = procesaComparendoPersonaDTO.getProcesaDireccion();
        if (direccion != null) {
            resp.setProcesaDireccion(ProcesaDireccionHelper.toLevel0Entity(direccion, null));
        }
        return resp;
    }

    /**
     * Metodo que se encarga de transformar una persona en un procesa persona comparendo solo para este caso hay informacion que no debe contener esto
     * es por que solo esta informacion nos sirve de la persona de ubicabilidad
     * 
     * @param personaDTO
     * @return
     * @author giovanni.velandia
     */
    public static ProcesaComparendoPersonaDTO toProcesaComparendoPersonaUbicabiliad(PersonaDTO personaDTO,
            ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO) {

        if (procesaComparendoPersonaDTO == null) {
            procesaComparendoPersonaDTO = new ProcesaComparendoPersonaDTO();
        }

        if (personaDTO != null) {

            if (personaDTO instanceof PersonaJuridicaDTO) {
                PersonaJuridicaDTO personaJuridicaDTO = (PersonaJuridicaDTO) personaDTO;
                procesaComparendoPersonaDTO.setDigitoVerificacionNit(personaJuridicaDTO.getDigitoVerificacion());
                procesaComparendoPersonaDTO.setRazonSocial(personaJuridicaDTO.getNombreComercial());
            }

            // Apellido 1
            procesaComparendoPersonaDTO.setApellido1(personaDTO.getApellido1());
            // Nombre 1
            procesaComparendoPersonaDTO.setNombre1(personaDTO.getNombre1());
            // Apellido 2
            procesaComparendoPersonaDTO.setApellido2(personaDTO.getApellido2());
            // Nombre 2
            procesaComparendoPersonaDTO.setNombre2(personaDTO.getNombre2());
            procesaComparendoPersonaDTO.setPersonaDTO(personaDTO);
        }
        return procesaComparendoPersonaDTO;
    }
}
