package co.com.datatools.c2.dt.administracion;

import java.io.Serializable;

import co.com.datatools.c2.dto.personas.PersonaDTO;

/**
 * 
 * @author diego.fonseca DTO que se utliza para consultar la dirección basado en el id de DireccionDTO
 */
public class PersonaUbicabilidadDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private PersonaDTO personaDTO;
    private String numeroFirma;
    /**
     * Indica si en la ubicabilidad solo se guarda la persona sin generacion de documento ni captura de firma
     */
    private boolean soloGuardar;

    public String getNumeroFirma() {
        return numeroFirma;
    }

    public void setNumeroFirma(String numeroFirma) {
        this.numeroFirma = numeroFirma;
    }

    public PersonaDTO getPersonaDTO() {
        return personaDTO;
    }

    public void setPersonaDTO(PersonaDTO personaDTO) {
        this.personaDTO = personaDTO;
    }

    public boolean isSoloGuardar() {
        return soloGuardar;
    }

    public void setSoloGuardar(boolean soloGuardar) {
        this.soloGuardar = soloGuardar;
    }

}
