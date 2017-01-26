package co.com.datatools.c2.maganed_bean.persona.datos_demograficos;

import java.util.List;

import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.personas.ArchivoPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaHistoricoDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Objeto que permite manipular objetos para el flujo administracion-persona-flow CU_CIR20_DAT_UBI_003
 * 
 * @author Dixon.Alvarez
 * 
 */
public class DatosDemograficosHolderFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "datosDemograficosHolderFL";

    private PersonaDTO personaFiltro;
    private PersonaDTO personaConsultada;
    private boolean consultado;
    private boolean procesoManual;
    private Boolean subflujo;
    private boolean pupupUsuarioNoExisteVisible;
    private boolean popupVerAutorizacionVisible;
    private boolean popupVerDocumentosVisible;
    private boolean popupVerDetalleVisible;
    private List<PersonaHistoricoDTO> historicosPersona;
    private PersonaHistoricoDTO historicoSeleccionado;
    private List<DireccionPersonaHistoricoDTO> direcciones;
    private List<TelefonoPersonaHistoricoDTO> telefonos;
    private List<CorreoPersonaHistoricoDTO> correos;
    private List<ArchivoPersonaDTO> documentos;
    private ArchivoPersonaDTO documentoSeleccionado;
    private List<ArchivoPersonaDTO> archivosNoUbicabilidad;
    private ArchivoPersonaDTO archivoNoUbiSeleccionado;
    private ArchivoPersonaDTO archivoNoUbiRegistrar;
    private UploadedFile archivoCargado;

    public DatosDemograficosHolderFL() {
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

    public PersonaDTO getPersonaConsultada() {
        return personaConsultada;
    }

    public void setPersonaConsultada(PersonaDTO personaConsultada) {
        this.personaConsultada = personaConsultada;
    }

    public boolean isConsultado() {
        return consultado;
    }

    public void setConsultado(boolean consultado) {
        this.consultado = consultado;
    }

    public boolean isProcesoManual() {
        return procesoManual;
    }

    public void setProcesoManual(boolean procesoManual) {
        this.procesoManual = procesoManual;
    }

    public Boolean getSubflujo() {
        return subflujo;
    }

    public void setSubflujo(Boolean subflujo) {
        this.subflujo = subflujo;
    }

    public boolean isPupupUsuarioNoExisteVisible() {
        return pupupUsuarioNoExisteVisible;
    }

    public void setPupupUsuarioNoExisteVisible(boolean pupupUsuarioNoExisteVisible) {
        this.pupupUsuarioNoExisteVisible = pupupUsuarioNoExisteVisible;
    }

    public boolean isPopupVerAutorizacionVisible() {
        return popupVerAutorizacionVisible;
    }

    public void setPopupVerAutorizacionVisible(boolean popupVerAutorizacionVisible) {
        this.popupVerAutorizacionVisible = popupVerAutorizacionVisible;
    }

    public boolean isPopupVerDocumentosVisible() {
        return popupVerDocumentosVisible;
    }

    public void setPopupVerDocumentosVisible(boolean popupVerDocumentosVisible) {
        this.popupVerDocumentosVisible = popupVerDocumentosVisible;
    }

    public boolean isPopupVerDetalleVisible() {
        return popupVerDetalleVisible;
    }

    public void setPopupVerDetalleVisible(boolean popupVerDetalleVisible) {
        this.popupVerDetalleVisible = popupVerDetalleVisible;
    }

    public List<PersonaHistoricoDTO> getHistoricosPersona() {
        return historicosPersona;
    }

    public void setHistoricosPersona(List<PersonaHistoricoDTO> historicosPersona) {
        this.historicosPersona = historicosPersona;
    }

    public PersonaHistoricoDTO getHistoricoSeleccionado() {
        return historicoSeleccionado;
    }

    public void setHistoricoSeleccionado(PersonaHistoricoDTO historicoSeleccionado) {
        this.historicoSeleccionado = historicoSeleccionado;
    }

    public List<DireccionPersonaHistoricoDTO> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionPersonaHistoricoDTO> direcciones) {
        this.direcciones = direcciones;
    }

    public List<TelefonoPersonaHistoricoDTO> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoPersonaHistoricoDTO> telefonos) {
        this.telefonos = telefonos;
    }

    public List<CorreoPersonaHistoricoDTO> getCorreos() {
        return correos;
    }

    public void setCorreos(List<CorreoPersonaHistoricoDTO> correos) {
        this.correos = correos;
    }

    public List<ArchivoPersonaDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ArchivoPersonaDTO> documentos) {
        this.documentos = documentos;
    }

    public ArchivoPersonaDTO getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionado(ArchivoPersonaDTO documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public List<ArchivoPersonaDTO> getArchivosNoUbicabilidad() {
        return archivosNoUbicabilidad;
    }

    public void setArchivosNoUbicabilidad(List<ArchivoPersonaDTO> archivosNoUbicabilidad) {
        this.archivosNoUbicabilidad = archivosNoUbicabilidad;
    }

    public ArchivoPersonaDTO getArchivoNoUbiSeleccionado() {
        return archivoNoUbiSeleccionado;
    }

    public void setArchivoNoUbiSeleccionado(ArchivoPersonaDTO archivoNoUbiSeleccionado) {
        this.archivoNoUbiSeleccionado = archivoNoUbiSeleccionado;
    }

    public ArchivoPersonaDTO getArchivoNoUbiRegistrar() {
        return archivoNoUbiRegistrar;
    }

    public void setArchivoNoUbiRegistrar(ArchivoPersonaDTO archivoNoUbiRegistrar) {
        this.archivoNoUbiRegistrar = archivoNoUbiRegistrar;
    }

    public UploadedFile getArchivoCargado() {
        return archivoCargado;
    }

    public void setArchivoCargado(UploadedFile archivoCargado) {
        this.archivoCargado = archivoCargado;
    }

}
