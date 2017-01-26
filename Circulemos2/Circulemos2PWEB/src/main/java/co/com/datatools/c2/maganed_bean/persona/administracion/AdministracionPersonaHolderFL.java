package co.com.datatools.c2.maganed_bean.persona.administracion;

import java.util.List;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Objeto que permite manipular objetos para el flujo administracion-persona-flow CU_CIR20_DAT_UBI_002
 * 
 * @author Dixon.Alvarez
 * 
 */
public class AdministracionPersonaHolderFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "administracionPersonaHolderFL";

    private PersonaDTO personaFiltro;
    private PersonaDTO personaSeleccionada;
    private List<PersonaDTO> personas;
    private boolean actualizar;
    private boolean popupVisible;

    public AdministracionPersonaHolderFL() {
        super();
        personaFiltro = new PersonaDTO();
        personaFiltro.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
    }

    public PersonaDTO getPersonaFiltro() {
        return personaFiltro;
    }

    public void setPersonaFiltro(PersonaDTO personaFiltro) {
        this.personaFiltro = personaFiltro;
    }

    public PersonaDTO getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(PersonaDTO personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public List<PersonaDTO> getPersonas() {
        return personas;
    }

    public void setPersonas(List<PersonaDTO> personas) {
        this.personas = personas;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public boolean isPopupVisible() {
        return popupVisible;
    }

    public void setPopupVisible(boolean popupVisible) {
        this.popupVisible = popupVisible;
    }

}
