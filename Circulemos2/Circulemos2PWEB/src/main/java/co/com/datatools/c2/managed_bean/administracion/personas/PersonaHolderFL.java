package co.com.datatools.c2.managed_bean.administracion.personas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.ParentescoPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;

/**
 * @author robert.bautista
 * @since 2014-04-03
 */
public class PersonaHolderFL implements Serializable {

    private static final long serialVersionUID = 1L;

    // FILTROS DE BUSQUEDA
    // PERSONA PRINCIPAL
    /**
     * Contiene el id de tipo de identificación seleccionada en la búsqueda inicial de Persona
     */
    private Integer idTipoIdentificacionPersona;

    /**
     * Contiene el número de identificación ingresado en la búsqueda inicial de Persona
     */
    private String numeroIdentificacionPersona;

    /**
     * Contiene el nombre 1 de la persona natural para realizar el filtro
     */
    private String nombre1;

    /**
     * Contiene el nombre 2 de la persona natural para realizar el filtro
     */
    private String nombre2;

    /**
     * Contiene el apellido 1 de la persona natural para realizar el filtro
     */
    private String apellido1;

    /**
     * Contiene el apellido 2 de la persona natural para realizar el filtro
     */
    private String apellido2;

    /**
     * Contiene el digito de verificacion de la persona juridica para realizar el filtro
     */
    private Short digitoVerificacion;

    /**
     * Contiene el nombre comercial de la persona juridica para realizar el filtro
     */
    private String nombreComercial;

    /**
     * Contiene la sigla de la persona juridica para realizar el filtro
     */
    private String sigla;

    // CONYUGUE
    /**
     * Contiene el id del tipo de identificación del Cónyugue
     */
    private Integer idTipoIdentificacionConyuge;

    /**
     * Contiene el número de documento del cónyugue
     */
    private String numeroDocumentoConyuge;

    /**
     * Contiene el id del departamento de expedicion del documento del conyugue
     */
    private Integer idDeptoDocConyugue;

    /**
     * Contiene el id del municipio de expedicion del documento del conyugue
     */
    private Integer idMunDocConyugue;

    // PERSONA JURIDICA
    /**
     * Contiene el id del tipo de identificación del Representante Legal
     */
    private Integer idTipoIdentificacionRepresentante;

    /**
     * Contiene el número de documento del Representante legal
     */
    private String numeroDocumentoRepresentante;

    /**
     * Contiene el id de la seccion de la actividad económica
     */
    private Integer idSeccionActEconomica;

    /**
     * Contiene el id de la división de la actividad económica
     */
    private Integer idDivisionActEconomica;

    /**
     * Contiene el id del grupo de la actividad económica
     */
    private Integer idGrupoActEconomica;

    /**
     * Contiene el id de la clase de la actividad económica
     */
    private Integer idClaseActEconomica;

    /**
     * Contiene el pais de origen de la persona juridica
     */
    private Integer idPaisJuridica;

    /**
     * Contiene el departamento de origen de la persona juridica
     */
    private Integer idDepartamentoJuridica;

    /**
     * Contiene el municipio de origen de la persona juridica
     */
    private Integer idMunicipioJuridica;

    // PERSONA NATURAL
    /**
     * Contiene el id del departamento de expedicion del documento de la persona natural
     */
    private Integer idDepartamentoDoc;

    /**
     * Contiene el id del municipio de expedicion del documento de la persona natural
     */
    private Integer idMunicipioDoc;

    // Atributos internos
    /**
     * Contiene titulos dinámicos
     */
    private String labelOperacion;

    // Flags
    /**
     * Indica si se debe presentar el boton para agregar conyugue
     */
    private boolean presentarOpcionConyugue;

    /**
     * Indica si se debe mostrar el panel natural
     */
    private boolean mostrarPanelNatural;

    /**
     * Indica si se debe mostrar el panel juridico
     */
    private boolean mostrarPanelJuridico;

    /**
     * Indica si la persona principal es natural.
     */
    private boolean personaNatural;

    /**
     * Indica si se debe mostrar el panel del conyugue
     */
    private boolean mostrarConyugue;

    /**
     * Indica si el cónyugue se agregó o modificó, por lo tanto se asocia a la persona principal.
     */
    private boolean conyugueAsociado;

    /**
     * Indica si se debe mostrar el resultado de una consulta por filtro
     */
    private boolean mostrarResultado;

    /**
     * Contiene el resultado de la consulta de personas naturales
     */
    private List<PersonaDTO> resultadoNatural;

    /**
     * Contiene el resultado de la consulta de personas juridicas
     */
    private List<PersonaJuridicaDTO> resultadoJuridico;

    /**
     * Contiene el tamanio del resultado de la consulta
     */
    private int tamanioConsulta;

    /**
     * Contiene la persona seleccionada
     */
    private PersonaDTO personaSeleccionada;

    /**
     * Contiene la persona seleccionada
     */
    private PersonaJuridicaDTO personaJuridicaSeleccionada;

    /**
     * Contiene la pila de personas vistas por detalle
     */
    private List<PersonaDTO> pilaPersonas;

    /**
     * Contiene el conyugue seleccionado
     */
    private ParentescoPersonaDTO conyugueSeleccionado;

    /**
     * Indica si la persona seleccionada fue editada
     */
    private boolean personaEditada;

    /**
     * Indica si se debe solicitar huella
     */
    private Boolean solicitaHuella;

    /**
     * Indica si se debe solicitar foto
     */
    private Boolean solicitaFoto;

    /**
     * Indica si se puede editar el registro de persona natural
     */
    private boolean personaEditable;

    /**
     * Indica si este MB se comporta como cliente
     */
    private boolean cliente;

    /**
     * Indica si debe mostrar para la interfaz de consulta de persona el numero de documento
     */
    private boolean mostrarNumDocObl;

    /**
     * Indica si es el caso de uso <b>CU_CIR20_DAT_ADM_004<b/> de consulta de personas en fuente externa
     */
    private boolean fuenteExterna;

    /**
     * Contiene el represetante legal actual
     */
    private RepresentanteLegalDTO representanteLegalDTO;

    /**
     * Constructor vacio
     */
    public PersonaHolderFL() {
        this.limpie();
    }

    public Integer getIdTipoIdentificacionPersona() {
        return this.idTipoIdentificacionPersona;
    }

    public void setIdTipoIdentificacionPersona(Integer idTipoIdentificacionPersona) {
        this.idTipoIdentificacionPersona = idTipoIdentificacionPersona;
    }

    public String getNumeroIdentificacionPersona() {
        return this.numeroIdentificacionPersona;
    }

    public void setNumeroIdentificacionPersona(String numeroIdentificacionPersona) {
        this.numeroIdentificacionPersona = numeroIdentificacionPersona;
    }

    public boolean isPresentarOpcionConyugue() {
        return this.presentarOpcionConyugue;
    }

    public void setPresentarOpcionConyugue(boolean presentarOpcionConyugue) {
        this.presentarOpcionConyugue = presentarOpcionConyugue;
    }

    public boolean isMostrarPanelNatural() {
        return this.mostrarPanelNatural;
    }

    public void setMostrarPanelNatural(boolean mostrarPanelNatural) {
        this.mostrarPanelNatural = mostrarPanelNatural;
    }

    public boolean isMostrarPanelJuridico() {
        return this.mostrarPanelJuridico;
    }

    public void setMostrarPanelJuridico(boolean mostrarPanelJuridico) {
        this.mostrarPanelJuridico = mostrarPanelJuridico;
    }

    public boolean isPersonaNatural() {
        return this.personaNatural;
    }

    public void setPersonaNatural(boolean personaNatural) {
        this.personaNatural = personaNatural;
    }

    public boolean isMostrarConyugue() {
        return this.mostrarConyugue;
    }

    public void setMostrarConyugue(boolean mostrarConyugue) {
        this.mostrarConyugue = mostrarConyugue;
    }

    public String getLabelOperacion() {
        return this.labelOperacion;
    }

    public void setLabelOperacion(String labelOperacion) {
        this.labelOperacion = labelOperacion;
    }

    public Integer getIdTipoIdentificacionConyuge() {
        return this.idTipoIdentificacionConyuge;
    }

    public void setIdTipoIdentificacionConyuge(Integer idTipoIdentificacionConyuge) {
        this.idTipoIdentificacionConyuge = idTipoIdentificacionConyuge;
    }

    public String getNumeroDocumentoConyuge() {
        return this.numeroDocumentoConyuge;
    }

    public void setNumeroDocumentoConyuge(String numeroDocumentoConyuge) {
        this.numeroDocumentoConyuge = numeroDocumentoConyuge;
    }

    public Integer getIdSeccionActEconomica() {
        return this.idSeccionActEconomica;
    }

    public void setIdSeccionActEconomica(Integer idSeccionActEconomica) {
        this.idSeccionActEconomica = idSeccionActEconomica;
        this.setIdDivisionActEconomica(null);
    }

    public Integer getIdDivisionActEconomica() {
        return this.idDivisionActEconomica;
    }

    public void setIdDivisionActEconomica(Integer idDivisionActEconomica) {
        this.idDivisionActEconomica = idDivisionActEconomica;
        this.setIdGrupoActEconomica(null);
    }

    public Integer getIdGrupoActEconomica() {
        return this.idGrupoActEconomica;
    }

    public void setIdGrupoActEconomica(Integer idGrupoActEconomica) {
        this.idGrupoActEconomica = idGrupoActEconomica;
        this.setIdClaseActEconomica(null);
    }

    public Integer getIdClaseActEconomica() {
        return this.idClaseActEconomica;
    }

    public void setIdClaseActEconomica(Integer idClaseActEconomica) {
        this.idClaseActEconomica = idClaseActEconomica;
    }

    public Integer getIdTipoIdentificacionRepresentante() {
        return this.idTipoIdentificacionRepresentante;
    }

    public void setIdTipoIdentificacionRepresentante(Integer idTipoIdentificacionRepresentante) {
        this.idTipoIdentificacionRepresentante = idTipoIdentificacionRepresentante;
    }

    public String getNumeroDocumentoRepresentante() {
        return this.numeroDocumentoRepresentante;
    }

    public void setNumeroDocumentoRepresentante(String numeroDocumentoRepresentante) {
        this.numeroDocumentoRepresentante = numeroDocumentoRepresentante;
    }

    public boolean isConyugueAsociado() {
        return this.conyugueAsociado;
    }

    public void setConyugueAsociado(boolean conyugueAsociado) {
        this.conyugueAsociado = conyugueAsociado;
    }

    /**
     * Inicializa los atributos con valores por defecto
     */
    public void limpie() {
        this.cliente = false;
        this.solicitaFoto = Boolean.FALSE;
        this.solicitaHuella = Boolean.TRUE;
        this.limpieDatosFiltro();
        this.limpieResultadoBusqueda();
    }

    public String getNombre1() {
        return this.nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return this.nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Short getDigitoVerificacion() {
        return this.digitoVerificacion;
    }

    public void setDigitoVerificacion(Short digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getNombreComercial() {
        return this.nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public boolean isMostrarResultado() {
        return this.mostrarResultado;
    }

    public void setMostrarResultado(boolean mostrarResultado) {
        this.mostrarResultado = mostrarResultado;
    }

    public List<PersonaDTO> getResultadoNatural() {
        return this.resultadoNatural;
    }

    public void setResultadoNatural(List<PersonaDTO> resultadoNatural) {
        this.resultadoNatural = resultadoNatural;
    }

    public List<PersonaJuridicaDTO> getResultadoJuridico() {
        return this.resultadoJuridico;
    }

    public void setResultadoJuridico(List<PersonaJuridicaDTO> resultadoJuridico) {
        this.resultadoJuridico = resultadoJuridico;
    }

    public int getTamanioConsulta() {
        return this.tamanioConsulta;
    }

    public void setTamanioConsulta(int tamanioConsulta) {
        this.tamanioConsulta = tamanioConsulta;
    }

    public PersonaDTO getPersonaSeleccionada() {
        return this.personaSeleccionada;
    }

    public void setPersonaSeleccionada(PersonaDTO personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public PersonaJuridicaDTO getPersonaJuridicaSeleccionada() {
        return this.personaJuridicaSeleccionada;
    }

    public void setPersonaJuridicaSeleccionada(PersonaJuridicaDTO personaJuridicaSeleccionada) {
        this.personaJuridicaSeleccionada = personaJuridicaSeleccionada;
    }

    public ParentescoPersonaDTO getConyugueSeleccionado() {
        return this.conyugueSeleccionado;
    }

    public void setConyugueSeleccionado(ParentescoPersonaDTO conyugueSeleccionado) {
        this.conyugueSeleccionado = conyugueSeleccionado;
    }

    public boolean isPersonaEditada() {
        return personaEditada;
    }

    public void setPersonaEditada(boolean personaEditada) {
        this.personaEditada = personaEditada;
    }

    /**
     * Limpia los datos relacionados con detalle de personas
     */
    public void limpieDatosDetalle() {
        this.personaSeleccionada = null;
        this.personaJuridicaSeleccionada = null;
        this.conyugueSeleccionado = null;
        this.pilaPersonas = null;
        this.personaEditada = false;
        this.personaEditable = true;
        this.representanteLegalDTO = null;
    }

    /**
     * Limpia los datos del filtro principal
     */
    public void limpieDatosFiltro() {
        this.idTipoIdentificacionPersona = null;
        this.numeroIdentificacionPersona = null;
        this.apellido1 = null;
        this.apellido2 = null;
        this.nombre1 = null;
        this.nombre2 = null;
        this.digitoVerificacion = null;
        this.nombreComercial = null;
        this.sigla = null;
        this.mostrarResultado = false;
    }

    /**
     * Limpia los datos del filtro del conyugue
     */
    public void limpieDatosFiltroConyugue() {
        this.idTipoIdentificacionConyuge = null;
        this.numeroDocumentoConyuge = null;
    }

    /**
     * Limpia los datos y flags modificados en busquedas previas
     */
    public void limpieResultadoBusqueda() {
        this.presentarOpcionConyugue = false;
        this.mostrarPanelJuridico = false;
        this.mostrarPanelNatural = false;
        this.personaNatural = false;
        this.mostrarConyugue = false;
        this.labelOperacion = "";
        this.idTipoIdentificacionConyuge = null;
        this.numeroDocumentoConyuge = null;
        this.idSeccionActEconomica = null;
        this.idDivisionActEconomica = null;
        this.idGrupoActEconomica = null;
        this.idClaseActEconomica = null;
        this.idTipoIdentificacionRepresentante = null;
        this.numeroDocumentoRepresentante = null;
        this.conyugueAsociado = false;
        this.resultadoJuridico = null;
        this.resultadoNatural = null;
        this.tamanioConsulta = 0;
        this.personaEditada = false;
        this.idPaisJuridica = null;
        this.idDepartamentoJuridica = null;
        this.idMunicipioJuridica = null;
        this.idDepartamentoDoc = null;
        this.idMunicipioDoc = null;
        this.idDeptoDocConyugue = null;
        this.idMunDocConyugue = null;
    }

    /**
     * Modifica el resultado de la consulta acorde al tipo de persona recibida en la lista indicada
     * 
     * @param resultadoConsulta
     *            contiene el listado de personas resultado de la consulta
     */
    @SuppressWarnings("unchecked")
    public void setResultadoConsulta(List<? extends PersonaDTO> resultadoConsulta) {
        this.setResultadoNatural(new ArrayList<PersonaDTO>());
        this.setResultadoJuridico(new ArrayList<PersonaJuridicaDTO>());
        this.setTamanioConsulta(resultadoConsulta.size());
        if (!resultadoConsulta.isEmpty()) {
            PersonaDTO personaDTO = resultadoConsulta.get(0);
            if (personaDTO instanceof PersonaJuridicaDTO) {
                this.setResultadoJuridico((List<PersonaJuridicaDTO>) resultadoConsulta);
            } else {
                this.setResultadoNatural((List<PersonaDTO>) resultadoConsulta);
            }

        }

    }

    /**
     * Adiciona la persona actual seleccionada a la pila de personas
     */
    public void adicionePila() {
        if (this.pilaPersonas == null) {
            this.pilaPersonas = new ArrayList<PersonaDTO>();
        }

        this.pilaPersonas.add(this.personaSeleccionada);
    }

    /**
     * Remueve el ultimo elemento de la pila
     */
    public void remuevaPila() {
        this.pilaPersonas.remove(this.pilaPersonas.size() - 1);
        this.personaSeleccionada = this.pilaPersonas.get(this.pilaPersonas.size() - 1);
    }

    /**
     * Retorna true si hay mas de un elemento en la pila de personas vistas en detalle
     * 
     * @return true si el tamanio de la pila de personas es mayor a uno
     */
    public boolean hayElementosEnPila() {
        if (this.fuenteExterna) {
            return true;
        }
        return this.pilaPersonas.size() > 1;
    }

    public Boolean getSolicitaHuella() {
        return this.solicitaHuella;
    }

    public void setSolicitaHuella(Boolean solicitaHuella) {
        this.solicitaHuella = solicitaHuella;
    }

    public Boolean getSolicitaFoto() {
        return this.solicitaFoto;
    }

    public void setSolicitaFoto(Boolean solicitaFoto) {
        this.solicitaFoto = solicitaFoto;
    }

    public boolean isPersonaEditable() {
        return this.personaEditable;
    }

    public void setPersonaEditable(boolean personaEditable) {
        this.personaEditable = personaEditable;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public boolean isMostrarNumDocObl() {
        return mostrarNumDocObl;
    }

    public void setMostrarNumDocObl(boolean mostrarNumDocObl) {
        this.mostrarNumDocObl = mostrarNumDocObl;
    }

    public boolean isFuenteExterna() {
        return fuenteExterna;
    }

    public void setFuenteExterna(boolean fuenteExterna) {
        this.fuenteExterna = fuenteExterna;
    }

    public Integer getIdDepartamentoDoc() {
        return this.idDepartamentoDoc;
    }

    public void setIdDepartamentoDoc(Integer idDepartamentoDoc) {
        this.idDepartamentoDoc = idDepartamentoDoc;
        this.setIdMunicipioDoc(null);
    }

    public Integer getIdMunicipioDoc() {
        return this.idMunicipioDoc;
    }

    public void setIdMunicipioDoc(Integer idMunicipioDoc) {
        this.idMunicipioDoc = idMunicipioDoc;
    }

    public Integer getIdDeptoDocConyugue() {
        return this.idDeptoDocConyugue;
    }

    public void setIdDeptoDocConyugue(Integer idDeptoDocConyugue) {
        this.idDeptoDocConyugue = idDeptoDocConyugue;
        this.setIdMunDocConyugue(null);
    }

    public Integer getIdMunDocConyugue() {
        return this.idMunDocConyugue;
    }

    public void setIdMunDocConyugue(Integer idMunDocConyugue) {
        this.idMunDocConyugue = idMunDocConyugue;
    }

    public Integer getIdPaisJuridica() {
        return this.idPaisJuridica;
    }

    public void setIdPaisJuridica(Integer idPaisJuridica) {
        this.idPaisJuridica = idPaisJuridica;
        this.setIdDepartamentoJuridica(null);
    }

    public Integer getIdDepartamentoJuridica() {
        return this.idDepartamentoJuridica;
    }

    public void setIdDepartamentoJuridica(Integer idDepartamentoJuridica) {
        this.idDepartamentoJuridica = idDepartamentoJuridica;
        this.setIdMunicipioJuridica(null);
    }

    public Integer getIdMunicipioJuridica() {
        return this.idMunicipioJuridica;
    }

    public void setIdMunicipioJuridica(Integer idMunicipioJuridica) {
        this.idMunicipioJuridica = idMunicipioJuridica;
    }

    public RepresentanteLegalDTO getRepresentanteLegalDTO() {
        return representanteLegalDTO;
    }

    public void setRepresentanteLegalDTO(RepresentanteLegalDTO representanteLegalDTO) {
        this.representanteLegalDTO = representanteLegalDTO;
    }

}
