package co.com.datatools.c2.managed_bean.administracion.personas;

import java.io.Serializable;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;

/**
 * @author robert.bautista - 2/04/2014
 */
public class PersonaFL implements Serializable {

    private static final long serialVersionUID = 1L;

    // DATOS PERSONA NATURAL PRINCIPAL
    /**
     * Contiene los datos de una persona natural
     */
    private PersonaDTO personaNaturalDTO;

    /**
     * Contiene la direccion de la residencia de la persona principal
     */
    private DireccionDTO direccionResidenciaPersonaNatural;

    /**
     * Contiene la direccion del trabajo de la persona principal
     */
    private DireccionDTO direccionTrabajoPersonaNatural;

    /**
     * Contiene la informacion del conyugue
     */
    private PersonaDTO conyugueDTO;

    /**
     * Contiene la direccion de la residencia del conyugue
     */
    private DireccionDTO direccionResidenciaConyugue;

    /**
     * Contiene la direccion del trabajo del conyugue
     */
    private DireccionDTO direccionTrabajoConyugue;

    /**
     * Contiene la copia de la información del conyugue
     */
    private PersonaDTO copiaConyugueDTO;

    // DATOS PERSONA JURIDICA PRINCIPAL
    /**
     * Contiene los datos de una persona jurídica
     */
    private PersonaJuridicaDTO personaJuridicaDTO;

    /**
     * Contiene la direccion de la residencia de la persona principal
     */
    private DireccionDTO direccionResidenciaPersonaJuridica;

    /**
     * Contiene el representante legal de la persona jurídica
     */
    private RepresentanteLegalDTO representanteLegalDTO;

    /**
     * Contiene el Representante Legal actual de la Persona Juridica
     */
    private RepresentanteLegalDTO representanteLegalActualDTO;

    /**
     * Manejo del digito de verificacion porque no es obligatorio
     */
    private String digitoVerificacion;

    /**
     * Constructor vacio
     */
    public PersonaFL() {
        this.limpie();
    }

    public PersonaDTO getPersonaNaturalDTO() {
        return this.personaNaturalDTO;
    }

    public void setPersonaNaturalDTO(PersonaDTO personaNaturalDTO) {
        this.personaNaturalDTO = personaNaturalDTO;
    }

    public PersonaJuridicaDTO getPersonaJuridicaDTO() {
        return this.personaJuridicaDTO;
    }

    public void setPersonaJuridicaDTO(PersonaJuridicaDTO personaJuridicaDTO) {
        this.personaJuridicaDTO = personaJuridicaDTO;
    }

    public RepresentanteLegalDTO getRepresentanteLegalDTO() {
        return this.representanteLegalDTO;
    }

    public void setRepresentanteLegalDTO(RepresentanteLegalDTO representanteLegalDTO) {
        this.representanteLegalDTO = representanteLegalDTO;
    }

    public PersonaDTO getCopiaConyugueDTO() {
        return this.copiaConyugueDTO;
    }

    public void setCopiaConyugueDTO(PersonaDTO copiaConyugueDTO) {
        this.copiaConyugueDTO = copiaConyugueDTO;
    }

    public RepresentanteLegalDTO getRepresentanteLegalActualDTO() {
        return this.representanteLegalActualDTO;
    }

    public void setRepresentanteLegalActualDTO(RepresentanteLegalDTO representanteLegalActualDTO) {
        this.representanteLegalActualDTO = representanteLegalActualDTO;
    }

    public DireccionDTO getDireccionResidenciaPersonaNatural() {
        return this.direccionResidenciaPersonaNatural;
    }

    public void setDireccionResidenciaPersonaNatural(DireccionDTO direccionResidenciaPersonaNatural) {
        this.direccionResidenciaPersonaNatural = direccionResidenciaPersonaNatural;
    }

    public DireccionDTO getDireccionTrabajoPersonaNatural() {
        return this.direccionTrabajoPersonaNatural;
    }

    public void setDireccionTrabajoPersonaNatural(DireccionDTO direccionTrabajoPersonaNatural) {
        this.direccionTrabajoPersonaNatural = direccionTrabajoPersonaNatural;
    }

    public DireccionDTO getDireccionResidenciaPersonaJuridica() {
        return this.direccionResidenciaPersonaJuridica;
    }

    public void setDireccionResidenciaPersonaJuridica(DireccionDTO direccionResidenciaPersonaJuridica) {
        this.direccionResidenciaPersonaJuridica = direccionResidenciaPersonaJuridica;
    }

    public PersonaDTO getConyugueDTO() {
        return this.conyugueDTO;
    }

    public void setConyugueDTO(PersonaDTO conyugueDTO) {
        this.conyugueDTO = conyugueDTO;
    }

    public DireccionDTO getDireccionResidenciaConyugue() {
        return this.direccionResidenciaConyugue;
    }

    public void setDireccionResidenciaConyugue(DireccionDTO direccionResidenciaConyugue) {
        this.direccionResidenciaConyugue = direccionResidenciaConyugue;
    }

    public DireccionDTO getDireccionTrabajoConyugue() {
        return this.direccionTrabajoConyugue;
    }

    public void setDireccionTrabajoConyugue(DireccionDTO direccionTrabajoConyugue) {
        this.direccionTrabajoConyugue = direccionTrabajoConyugue;
    }

    public String getDigitoVerificacion() {
        return this.digitoVerificacion;
    }

    public void setDigitoVerificacion(String digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    /**
     * Inicializa todos los atributos de la clase
     */
    public void limpie() {
        this.limpieDatosBusqueda();
    }

    /**
     * Limpia los datos y flags modificados en busquedas previas
     */
    public void limpieDatosBusqueda() {
        this.personaNaturalDTO = null;
        this.direccionResidenciaPersonaNatural = null;
        this.direccionTrabajoPersonaNatural = null;
        this.conyugueDTO = null;
        this.direccionResidenciaConyugue = null;
        this.direccionTrabajoConyugue = null;
        this.personaJuridicaDTO = null;
        this.direccionResidenciaPersonaJuridica = null;
        this.representanteLegalDTO = null;
        this.copiaConyugueDTO = null;
        this.representanteLegalActualDTO = null;
        this.digitoVerificacion = null;
    }

}
