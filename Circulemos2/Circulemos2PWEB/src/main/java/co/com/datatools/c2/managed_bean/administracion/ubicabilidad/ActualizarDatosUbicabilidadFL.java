package co.com.datatools.c2.managed_bean.administracion.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.datatools.c2.dt.administracion.PersonaUbicabilidadDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion.vo.RegistroTablaVO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class ActualizarDatosUbicabilidadFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private Integer idTipoIdentificacion; // se usa
    private String numeroDocumento; // se usa
    private PersonaDTO persona;
    private boolean esPersonaJuridica;
    private PersonaJuridicaDTO personaJuridicaDTO;
    private List<PersonaHistoricoDTO> historicoPersonas = new ArrayList<>();

    // correos
    private RegistroTablaVO<CorreoPersonaDTO> correoGuardar;
    private boolean actualizarCorreo;
    private List<RegistroTablaVO<CorreoPersonaDTO>> correosPersona;
    private RegistroTablaVO<CorreoPersonaDTO> correoSeleccionado;
    private String tituloPopUpCorreo;

    // telefonos
    private List<RegistroTablaVO<TelefonoPersonaDTO>> telefonosPersona;
    private RegistroTablaVO<TelefonoPersonaDTO> telefonoSeleccionado;
    private RegistroTablaVO<TelefonoPersonaDTO> telefonoGuardar;
    private boolean actualizarTelefono;
    private String tituloPopUpTelefono;

    // direccion
    private RegistroTablaVO<DireccionPersonaDTO> direccionSeleccionado;
    private List<RegistroTablaVO<DireccionPersonaDTO>> direccionesPersona;
    private boolean actualizarDireccion;

    // validacion
    private Boolean validacion;
    private Integer calificacion;
    private int tipoDatoValidado;

    // PersonaJuridica
    private RepresentanteLegalDTO representante;
    private List<SelectItem> tiposDocumentoRepresentante;
    private boolean existeRepresentante;

    // Creada para validar en la vista el tipo y numero de identificacion
    private boolean editar;

    private boolean confirmarUbicabilidad;
    private boolean verMensajeAdv;
    private String mensajeAdv;

    private PersonaUbicabilidadDTO personaUbicabilidadDTO;
    /**
     * Indica si en la ubicabilidad solo se guarda la persona sin generacion de documento ni captura de firma
     */
    private boolean soloGuardar;

    /**
     * Indica si al actualizar o crear la ubicabilidad el ciudadano esta presente, esto se tiene en cuenta para el tipo de fuente de información
     * "ciudadano" ó "proceso manual" con el que se va guardar la ubicabilidad, este parametro es utilizado desde la funcionalidad UBI_002
     */
    private Boolean ciudadanoPresente;

    public static final String NOMBRE_BEAN = "actualizarDatosUbicabilidadFL";

    public ActualizarDatosUbicabilidadFL() {
        personaUbicabilidadDTO = new PersonaUbicabilidadDTO();
    }

    public Integer getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public boolean isEsPersonaJuridica() {
        return esPersonaJuridica;
    }

    public void setEsPersonaJuridica(boolean esPersonaJuridica) {
        this.esPersonaJuridica = esPersonaJuridica;
    }

    public PersonaJuridicaDTO getPersonaJuridicaDTO() {
        return personaJuridicaDTO;
    }

    public void setPersonaJuridicaDTO(PersonaJuridicaDTO personaJuridicaDTO) {
        this.personaJuridicaDTO = personaJuridicaDTO;
    }

    public List<PersonaHistoricoDTO> getHistoricoPersonas() {
        return historicoPersonas;
    }

    public void setHistoricoPersonas(List<PersonaHistoricoDTO> historicoPersonas) {
        this.historicoPersonas = historicoPersonas;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public boolean isConfirmarUbicabilidad() {
        return confirmarUbicabilidad;
    }

    public void setConfirmarUbicabilidad(boolean confirmarUbicabilidad) {
        this.confirmarUbicabilidad = confirmarUbicabilidad;
    }

    public boolean isVerMensajeAdv() {
        return verMensajeAdv;
    }

    public void setVerMensajeAdv(boolean verMensajeAdv) {
        this.verMensajeAdv = verMensajeAdv;
    }

    public String getMensajeAdv() {
        return mensajeAdv;
    }

    public void setMensajeAdv(String mensajeAdv) {
        this.mensajeAdv = mensajeAdv;
    }

    public PersonaUbicabilidadDTO getPersonaUbicabilidadDTO() {
        return personaUbicabilidadDTO;
    }

    public void setPersonaUbicabilidadDTO(PersonaUbicabilidadDTO personaUbicabilidadDTO) {
        this.personaUbicabilidadDTO = personaUbicabilidadDTO;
    }

    public boolean isSoloGuardar() {
        return soloGuardar;
    }

    public void setSoloGuardar(boolean soloGuardar) {
        this.soloGuardar = soloGuardar;
    }

    public Boolean getCiudadanoPresente() {
        return ciudadanoPresente;
    }

    public void setCiudadanoPresente(Boolean ciudadanoPresente) {
        this.ciudadanoPresente = ciudadanoPresente;
    }

    public boolean isActualizarCorreo() {
        return actualizarCorreo;
    }

    public void setActualizarCorreo(boolean actualizarCorreo) {
        this.actualizarCorreo = actualizarCorreo;
    }

    public RegistroTablaVO<CorreoPersonaDTO> getCorreoGuardar() {
        return correoGuardar;
    }

    public void setCorreoGuardar(RegistroTablaVO<CorreoPersonaDTO> correoGuardar) {
        this.correoGuardar = correoGuardar;
    }

    public List<RegistroTablaVO<CorreoPersonaDTO>> getCorreosPersona() {
        return correosPersona;
    }

    public void setCorreosPersona(List<RegistroTablaVO<CorreoPersonaDTO>> correosPersona) {
        this.correosPersona = correosPersona;
    }

    public RegistroTablaVO<CorreoPersonaDTO> getCorreoSeleccionado() {
        return correoSeleccionado;
    }

    public void setCorreoSeleccionado(RegistroTablaVO<CorreoPersonaDTO> correoSeleccionado) {
        this.correoSeleccionado = correoSeleccionado;
    }

    public List<RegistroTablaVO<TelefonoPersonaDTO>> getTelefonosPersona() {
        return telefonosPersona;
    }

    public void setTelefonosPersona(List<RegistroTablaVO<TelefonoPersonaDTO>> telefonosPersona) {
        this.telefonosPersona = telefonosPersona;
    }

    public RegistroTablaVO<TelefonoPersonaDTO> getTelefonoSeleccionado() {
        return telefonoSeleccionado;
    }

    public void setTelefonoSeleccionado(RegistroTablaVO<TelefonoPersonaDTO> telefonoSeleccionado) {
        this.telefonoSeleccionado = telefonoSeleccionado;
    }

    public RegistroTablaVO<TelefonoPersonaDTO> getTelefonoGuardar() {
        return telefonoGuardar;
    }

    public void setTelefonoGuardar(RegistroTablaVO<TelefonoPersonaDTO> telefonoGuardar) {
        this.telefonoGuardar = telefonoGuardar;
    }

    public boolean isActualizarTelefono() {
        return actualizarTelefono;
    }

    public void setActualizarTelefono(boolean actualizarTelefono) {
        this.actualizarTelefono = actualizarTelefono;
    }

    public String getTituloPopUpCorreo() {
        return tituloPopUpCorreo;
    }

    public void setTituloPopUpCorreo(String tituloPopUpCorreo) {
        this.tituloPopUpCorreo = tituloPopUpCorreo;
    }

    public String getTituloPopUpTelefono() {
        return tituloPopUpTelefono;
    }

    public void setTituloPopUpTelefono(String tituloPopUpTelefono) {
        this.tituloPopUpTelefono = tituloPopUpTelefono;
    }

    public RegistroTablaVO<DireccionPersonaDTO> getDireccionSeleccionado() {
        return direccionSeleccionado;
    }

    public void setDireccionSeleccionado(RegistroTablaVO<DireccionPersonaDTO> direccionSeleccionado) {
        this.direccionSeleccionado = direccionSeleccionado;
    }

    public List<RegistroTablaVO<DireccionPersonaDTO>> getDireccionesPersona() {
        return direccionesPersona;
    }

    public void setDireccionesPersona(List<RegistroTablaVO<DireccionPersonaDTO>> direccionesPersona) {
        this.direccionesPersona = direccionesPersona;
    }

    public Boolean getValidacion() {
        return validacion;
    }

    public void setValidacion(Boolean validacion) {
        this.validacion = validacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public int getTipoDatoValidado() {
        return tipoDatoValidado;
    }

    public void setTipoDatoValidado(int tipoDatoValidado) {
        this.tipoDatoValidado = tipoDatoValidado;
    }

    public boolean isActualizarDireccion() {
        return actualizarDireccion;
    }

    public void setActualizarDireccion(boolean actualizarDireccion) {
        this.actualizarDireccion = actualizarDireccion;
    }

    public RepresentanteLegalDTO getRepresentante() {
        return representante;
    }

    public void setRepresentante(RepresentanteLegalDTO representante) {
        this.representante = representante;
    }

    public List<SelectItem> getTiposDocumentoRepresentante() {
        return tiposDocumentoRepresentante;
    }

    public void setTiposDocumentoRepresentante(List<SelectItem> tiposDocumentoRepresentante) {
        this.tiposDocumentoRepresentante = tiposDocumentoRepresentante;
    }

    public boolean isExisteRepresentante() {
        return existeRepresentante;
    }

    public void setExisteRepresentante(boolean existeRepresentante) {
        this.existeRepresentante = existeRepresentante;
    }

}
