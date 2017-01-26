package co.com.datatools.c2.managed_bean.administracion.personas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.CardinalidadDireccionDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.NombreViaDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.TipoUbicabilidadDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.dto.personas.ClaseActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.EstadoCivilDTO;
import co.com.datatools.c2.dto.personas.NivelEducativoDTO;
import co.com.datatools.c2.dto.personas.ParentescoPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.personas.TipoParentescoPersonaDTO;
import co.com.datatools.c2.dto.personas.TipoSociedadDTO;
import co.com.datatools.c2.dto.personas.TipoViviendaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumGeneroPersona;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeracion.EnumTipoParentescoPersona;
import co.com.datatools.c2.enumeracion.EnumTipoUbicabilidad;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.administracion.direccion.DireccionFL;
import co.com.datatools.c2.managed_bean.administracion.direccion.DireccionMB;
import co.com.datatools.c2.managed_bean.comun.CatalogoGeneralMB;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRDireccion;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral.MensajesGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.c2.web.util.ConstantesManagedBean.C2Bundles;
import co.com.datatools.c2.web.util.ConstantesManagedBean.C2FlowObjects;
import co.com.datatools.util.date.UtilFecha;

/**
 * @author robert.bautista - 2/04/2014
 */
@ManagedBean
@SessionScoped
public class PersonaMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(PersonaMB.class.getName());

    /**
     * Contiene el valor que toman por defecto los combos al no seleccionar nada.
     */
    private final static int VALOR_DEFECTO_COMBOS = 0;

    /**
     * Manejo de operaciones dentro del bean
     * 
     * @author robert.bautista
     */
    private enum EnumTipoOperacion {
        ACTUALIZAR_PERSONA_NATURAL, INGRESAR_PERSONA_NATURAL, ACTUALIZAR_PERSONA_JURIDICA, INGRESAR_PERSONA_JURIDICA;

        EnumTipoOperacion() {
        }

    };

    @EJB
    private IRPersona irPersona;

    @EJB
    private IRAdministracion irAdministracion;

    @EJB
    private IRParametro iRParametro;

    @ManagedProperty(value = "#{direccionMB}")
    private DireccionMB direccionMB;

    @ManagedProperty(value = "#{catalogoGeneralMB}")
    private CatalogoGeneralMB catalogoGeneralMB;

    /**
     * Contiene los estados civiles que pueden manejar cónyugue
     */
    private List<Integer> estadosCivilesConyugue;

    /**
     * Contiene los tipos de identificacion de las personas
     */
    private List<TipoIdentificacionPersonaDTO> tiposIdentificacion;

    /**
     * Contiene los codigos de los tipos de documento que pueden tener asociada la huella
     */
    private List<String> tiposDocConHuella;

    /**
     * Contiene los codigos de los tipos de documento que pueden tener asociada la foto
     */
    private List<String> tiposDocConFoto;

    /**
     * Contiene el código de la fuente de información a usar en la creación de usuarios.
     */
    private int codigoFuenteInformacion;

    /**
     * Retorna el FuncionarioFL encargado del registro-edicion de funcionarios
     * 
     * @return FuncionarioFL
     */
    private PersonaFL cargarPersonaFL() {
        final PersonaFL personaFL = this.findFlowObject(PersonaFL.class, C2FlowObjects.PERSONA_FL);

        return personaFL;
    }

    /**
     * Retorna el PersonaHolderFL encargado del registro-edicion de personas
     * 
     * @return PersonaHolderFL
     */
    private PersonaHolderFL cargarPersonaHolderFL() {
        final PersonaHolderFL personaHolderFL = this.findFlowObject(PersonaHolderFL.class,
                C2FlowObjects.PERSONA_HOLDER_FL);

        return personaHolderFL;
    }

    @PostConstruct
    private void iniciar() {
        this.cargueParametros();
        this.cargarEstadosCiviles();
    }

    /**
     * Carga el listado de tipos de identificacion disponibles para las personas
     */
    private void cargarTiposIdentificacion() {
        List<SelectItem> lista = this.catalogoGeneralMB.getOpcTipoIdentPersona();
        this.tiposIdentificacion = new ArrayList<TipoIdentificacionPersonaDTO>();
        for (SelectItem item : lista) {
            TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO = new TipoIdentificacionPersonaDTO();
            tipoIdentificacionPersonaDTO.setId((Integer) item.getValue());
            tipoIdentificacionPersonaDTO.setNombre(item.getLabel());
            this.tiposIdentificacion.add(tipoIdentificacionPersonaDTO);
        }

    }

    /**
     * Carga los estados civiles que implican un conyugue acorde al pais
     */
    private void cargarEstadosCiviles() {
        List<Integer> codigosEstadosCivilesConyugue = this.irAdministracion
                .obtenerEstadosCivilesImpliquenConyugue(this.cargarPaisDTO().getId());
        List<EstadoCivilDTO> estadosCiviles = this.irAdministracion.consultarEstadoCivil(this.cargarPaisDTO().getId());
        List<Integer> estadosCivilesConyugue = new ArrayList<Integer>();

        for (Integer codigo : codigosEstadosCivilesConyugue) {
            for (EstadoCivilDTO estadoCivil : estadosCiviles) {
                if (estadoCivil.getCodigo().equals(codigo)) {
                    estadosCivilesConyugue.add(estadoCivil.getId());
                    break;
                }

            }

        }

        this.setEstadosCivilesConyugue(estadosCivilesConyugue);
    }

    /**
     * Modifica el estado de los campos para ingresar una persona nueva.
     */
    private void cargarPersonaNueva() {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        final PersonaFL personaFL = this.cargarPersonaFL();
        personaHolderFL.setPresentarOpcionConyugue(false);
        personaHolderFL.setMostrarPanelJuridico(false);
        personaHolderFL.setMostrarPanelNatural(false);

        if (personaHolderFL.getIdTipoIdentificacionPersona().equals(this.getIdTipoDocumentoEmpresa())) {
            // Es persona Jurídica
            personaHolderFL.setPersonaNatural(false);
            personaHolderFL.setMostrarPanelJuridico(true);
            personaFL.setPersonaJuridicaDTO(this.crearPersonaJuridicaDTO(personaFL));
            personaFL.setDireccionResidenciaPersonaJuridica(this.crearDireccion());
            personaHolderFL.setLabelOperacion(
                    this.getBundle(C2Bundles.NOMBRE_BUNDLE_ADMIN).getString("titulo_ingresar_persona_juridica"));
        } else { // No es persona jurídica
            personaHolderFL.setPersonaNatural(true);
            personaHolderFL.setMostrarPanelNatural(true);
            personaHolderFL.setMostrarConyugue(false);
            personaFL.setPersonaNaturalDTO(this.crearPersonaDTO());
            personaFL.setDireccionResidenciaPersonaNatural(this.crearDireccion());
            personaFL.setDireccionTrabajoPersonaNatural(this.crearDireccion());
            personaHolderFL.setLabelOperacion(
                    this.getBundle(C2Bundles.NOMBRE_BUNDLE_ADMIN).getString("titulo_ingresar_persona"));
        }

    }

    /**
     * Retorna una direccionDTO nueva
     * 
     * @return DireccionDTO nueva
     */
    private DireccionDTO crearDireccion() {
        DireccionDTO direccionDTO = new DireccionDTO();

        TipoViaDTO tipoViaPrincipalDTO = new TipoViaDTO();
        NombreViaDTO nombreViaPrincipalDTO = new NombreViaDTO();
        CardinalidadDireccionDTO cardinalidadViaPrincipal = new CardinalidadDireccionDTO();

        TipoViaDTO tipoViaSecundariaDTO = new TipoViaDTO();
        NombreViaDTO nombreViaSecundariaDTO = new NombreViaDTO();
        CardinalidadDireccionDTO cardinalidadViaSecundaria = new CardinalidadDireccionDTO();

        PaisDTO paisDTO = new PaisDTO();
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setPais(paisDTO);
        MunicipioDTO municipioDTO = new MunicipioDTO();
        municipioDTO.setDepartamento(departamentoDTO);
        LocalidadDTO localidadDTO = new LocalidadDTO();
        localidadDTO.setMunicipio(municipioDTO);

        direccionDTO.setTipoViaPrincipal(tipoViaPrincipalDTO);
        direccionDTO.setNombreViaPrincipal(nombreViaPrincipalDTO);
        direccionDTO.setCardinalidadViaPrincipal(cardinalidadViaPrincipal);
        direccionDTO.setTipoViaSecundaria(tipoViaSecundariaDTO);
        direccionDTO.setNombreViaSecundaria(nombreViaSecundariaDTO);
        direccionDTO.setCardinalidadViaSecundaria(cardinalidadViaSecundaria);
        direccionDTO.setMunicipio(municipioDTO);
        direccionDTO.setLocalidad(localidadDTO);

        return direccionDTO;
    }

    private PersonaJuridicaDTO crearPersonaJuridicaDTO() {
        PersonaJuridicaDTO personaJuridicaDTO = new PersonaJuridicaDTO();

        this.asignarCamposBasicosNuevos(personaJuridicaDTO);

        PaisDTO paisDTO = new PaisDTO();
        paisDTO.setId(null);

        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(null);
        departamentoDTO.setPais(paisDTO);

        MunicipioDTO municipioDTO = new MunicipioDTO();
        municipioDTO.setId(null);
        municipioDTO.setDepartamento(departamentoDTO);

        personaJuridicaDTO.setMunicipio(municipioDTO);

        TipoSociedadDTO tipoSociedad = new TipoSociedadDTO();
        tipoSociedad.setId(null);
        personaJuridicaDTO.setTipoSociedad(tipoSociedad);

        ClaseActividadEconomicaDTO claseActividad = new ClaseActividadEconomicaDTO();
        claseActividad.setId(null);
        personaJuridicaDTO.setClaseActividadEconomica(claseActividad);

        return personaJuridicaDTO;
    }

    /**
     * Retorna una PersonaJuridicaDTO nueva con el código de organismo y código de fuente de información manejados en el bean.
     * 
     * @param personaFL
     * 
     * @param personaHolderFL
     * 
     * @return PersonaJuridicaDTO con los campos básicos
     */
    private PersonaJuridicaDTO crearPersonaJuridicaDTO(PersonaFL personaFL) {
        PersonaJuridicaDTO personaJuridicaDTO = this.crearPersonaJuridicaDTO();
        personaFL.setRepresentanteLegalDTO(this.crearRepresentanteLegalDTO());
        return personaJuridicaDTO;
    }

    /**
     * Asigna los campos DTO de TODA PERSONA DTO a la persona indicada
     * 
     * @param persona
     *            a quien se asignan los nuevos campos
     */
    private void asignarCamposBasicosNuevos(PersonaDTO persona) {
        TipoIdentificacionPersonaDTO tipoIdentificacion = new TipoIdentificacionPersonaDTO();
        persona.setTipoIdentificacion(tipoIdentificacion);

        OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
        organismoTransitoDTO.setCodigoOrganismo(Integer.valueOf(getCodigoOrganismoTransito()));
        persona.setOrganismoTransito(organismoTransitoDTO);

        TipoFuenteInformacionDTO tipoFuenteInformacionDTO = new TipoFuenteInformacionDTO();
        persona.setFuenteInformacion(tipoFuenteInformacionDTO);
    }

    /**
     * Retorna un RepresentanteLegalDTO nuevo
     * 
     * @return RepresentanteLegalDTO nuevo
     */
    private RepresentanteLegalDTO crearRepresentanteLegalDTO() {
        RepresentanteLegalDTO representanteDTO = new RepresentanteLegalDTO();

        TipoIdentificacionPersonaDTO tipoIdentificacion = new TipoIdentificacionPersonaDTO();
        representanteDTO.setTipoIdentificacion(tipoIdentificacion);
        return representanteDTO;
    }

    /**
     * Retorna una PersonaNaturalDTO nueva con el codigo de organismo y codigo de fuente de informacion manejados en el bean.
     * 
     * @param personaHolderFL
     *            contiene los parametros para crear la persona
     * @return PersonaNaturalDTO con los campos basicos
     */
    private PersonaDTO crearPersonaDTO() {
        PersonaDTO persona = new PersonaDTO();

        this.asignarCamposBasicosNuevos(persona);
        persona.setMunicipioExpedicionDocumento(this.crearMunicipio());
        persona.getMunicipioExpedicionDocumento().setDepartamento(this.crearDepartamento());

        EstadoCivilDTO estadoCivilDTO = new EstadoCivilDTO();
        persona.setEstadoCivil(estadoCivilDTO);

        TipoViviendaDTO tipoVivienda = new TipoViviendaDTO();
        persona.setTipoVivienda(tipoVivienda);

        NivelEducativoDTO nivelEducativo = new NivelEducativoDTO();
        persona.setNivelEducativo(nivelEducativo);

        return persona;
    }

    /**
     * Crea un municipio vacio
     * 
     * @return MunicipioDTO
     */
    private MunicipioDTO crearMunicipio() {
        MunicipioDTO municipioDTO = new MunicipioDTO();
        municipioDTO.setId(null);
        return municipioDTO;
    }

    /**
     * Crea un departamento vacio
     * 
     * @return DepartamentoDTO
     */
    private DepartamentoDTO crearDepartamento() {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(null);
        return departamentoDTO;
    }

    /**
     * Carga la información en el bean acorde a la Persona Jurídica indicada
     * 
     * @param personaDTO
     *            contiene la Persona Jurídica a cargar en el bean
     */
    private void cargarPersonaJuridica(PersonaJuridicaDTO personaDTO) {
        final PersonaFL personaFL = this.cargarPersonaFL();
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        personaFL.setPersonaJuridicaDTO(personaDTO);
        String digVerficacion = (personaDTO.getDigitoVerificacion() == null) ? null
                : personaDTO.getDigitoVerificacion().toString();
        personaFL.setDigitoVerificacion(digVerficacion);
        personaHolderFL.setIdSeccionActEconomica(null);

        if ((personaDTO.getClaseActividadEconomica() != null)
                && (personaDTO.getClaseActividadEconomica().getId() != null)) {
            personaHolderFL
                    .setIdSeccionActEconomica(personaDTO.getClaseActividadEconomica().getGrupoActividadEconomica()
                            .getDivisionActividadEconomica().getSeccionActividadEconomica().getId());
            personaHolderFL.setIdDivisionActEconomica(personaDTO.getClaseActividadEconomica()
                    .getGrupoActividadEconomica().getDivisionActividadEconomica().getId());
            personaHolderFL.setIdGrupoActEconomica(
                    personaDTO.getClaseActividadEconomica().getGrupoActividadEconomica().getId());
            personaHolderFL.setIdClaseActEconomica(personaDTO.getClaseActividadEconomica().getId());
        } else {
            personaDTO.setClaseActividadEconomica(new ClaseActividadEconomicaDTO());
        }

        if (personaDTO.getTipoSociedad() == null) {
            TipoSociedadDTO tipoSociedad = new TipoSociedadDTO();
            personaDTO.setTipoSociedad(tipoSociedad);
        }

        personaHolderFL.setIdPaisJuridica(null);
        if ((personaDTO.getMunicipio() != null) && (personaDTO.getMunicipio().getId() != null)) {
            personaHolderFL.setIdPaisJuridica(personaDTO.getMunicipio().getDepartamento().getPais().getId());
            personaHolderFL.setIdDepartamentoJuridica(personaDTO.getMunicipio().getDepartamento().getId());
            personaHolderFL.setIdMunicipioJuridica(personaDTO.getMunicipio().getId());
        } else {
            personaDTO.setMunicipio(this.crearMunicipio());
        }

        personaFL.setRepresentanteLegalDTO(this.crearRepresentanteLegalDTO());
        this.cargarRepresentanteLegalActual(personaFL, personaHolderFL);

        DireccionDTO direccionDTO = this.obtenerMayorDireccion(EnumTipoUbicabilidad.RESIDENCIA,
                personaDTO.getDireccionPersonaList());
        personaFL.setDireccionResidenciaPersonaJuridica(direccionDTO);
        personaHolderFL.setLabelOperacion(
                this.getBundle(C2Bundles.NOMBRE_BUNDLE_ADMIN).getString("titulo_actualizar_persona_juridica"));
    }

    /**
     * Carga la informacion del Representante Legal actual vigente acorde al representante indicado
     * 
     * @param personaFL
     *            contiene el representante a manejar
     * @param personaHolderFL
     *            contiene la informacion del representante
     * @param representante
     *            contiene la informacion para cargar el conjunto de representantes de la persona juridica y seleccionar el vigente.
     * 
     */
    private void cargarRepresentanteLegalActual(PersonaFL personaFL, PersonaHolderFL personaHolderFL) {
        personaFL.setRepresentanteLegalActualDTO(null);
        personaHolderFL.setNumeroDocumentoRepresentante(null);
        personaHolderFL.setIdTipoIdentificacionRepresentante(null);

        if (!personaFL.getPersonaJuridicaDTO().getRepresentanteLegalList().isEmpty()) {
            personaFL.setRepresentanteLegalActualDTO(personaHolderFL.getRepresentanteLegalDTO());
        }

        if (personaFL.getRepresentanteLegalActualDTO() != null) {
            personaFL.getRepresentanteLegalDTO()
                    .setFechaInicio(personaFL.getRepresentanteLegalActualDTO().getFechaInicio());
            personaFL.getRepresentanteLegalDTO().setFechaFin(personaFL.getRepresentanteLegalActualDTO().getFechaFin());
            personaFL.getRepresentanteLegalDTO()
                    .setCorreoElectronico(personaFL.getRepresentanteLegalActualDTO().getCorreoElectronico());
            personaHolderFL.setNumeroDocumentoRepresentante(
                    personaFL.getRepresentanteLegalActualDTO().getNumeroIdentificacion());
            personaHolderFL.setIdTipoIdentificacionRepresentante(
                    personaFL.getRepresentanteLegalActualDTO().getTipoIdentificacion().getId());
        }

    }

    /**
     * Carga la informacion en el bean acorde a la Persona Natural indicada
     * 
     * @param personaFL
     *            maneja la informacion de la persona a presentar
     * @param personaHolderFL
     *            contiene la información de flags y presentación
     * @param personaDTO
     *            contiene la información de la Persona Natural a cargar
     */
    private void cargarPersonaNatural(PersonaFL personaFL, PersonaHolderFL personaHolderFL, PersonaDTO personaDTO) {
        personaFL.setPersonaNaturalDTO(personaDTO);
        personaHolderFL.setMostrarConyugue(false);
        PersonaDTO conyugueDTO = this.obtenerConyugueActual();
        if (conyugueDTO != null) {
            personaHolderFL.setIdTipoIdentificacionConyuge(conyugueDTO.getTipoIdentificacion().getId());
            personaHolderFL.setNumeroDocumentoConyuge(conyugueDTO.getNumeroIdentificacion());
            if ((conyugueDTO.getMunicipioExpedicionDocumento() != null) && (conyugueDTO
                    .getMunicipioExpedicionDocumento().getId().intValue() != PersonaMB.VALOR_DEFECTO_COMBOS)) {
                personaHolderFL
                        .setIdDeptoDocConyugue(conyugueDTO.getMunicipioExpedicionDocumento().getDepartamento().getId());
                personaHolderFL.setIdMunDocConyugue(conyugueDTO.getMunicipioExpedicionDocumento().getId());
            }

            personaFL.setCopiaConyugueDTO(new PersonaDTO());
            this.copiarConyugue(conyugueDTO, personaFL.getCopiaConyugueDTO());
            personaHolderFL.setMostrarConyugue(true);
        }

        this.presentarConyugue();
        if ((personaDTO.getMunicipioExpedicionDocumento() != null)
                && (personaDTO.getMunicipioExpedicionDocumento().getId() != null)) {
            personaHolderFL
                    .setIdDepartamentoDoc(personaDTO.getMunicipioExpedicionDocumento().getDepartamento().getId());
            personaHolderFL.setIdMunicipioDoc(personaDTO.getMunicipioExpedicionDocumento().getId());
        }
        personaHolderFL.setPersonaEditable(personaFL.getPersonaNaturalDTO().getFechaFallecimiento() == null);
        personaHolderFL.setLabelOperacion(
                this.getBundle(C2Bundles.NOMBRE_BUNDLE_ADMIN).getString("titulo_actualizar_persona"));
    }

    /**
     * Copia la información del cónyugue original en el cónyugue copia indicados
     * 
     * @param conyugueOriginal
     *            quien contiene la información a copiar
     * 
     * @param conyugueCopia
     *            sobre quien se copiará la información
     * 
     */
    private void copiarConyugue(PersonaDTO conyugueOriginal, PersonaDTO conyugueCopia) {
        conyugueCopia.setId(conyugueOriginal.getId());
        conyugueCopia.setNumeroIdentificacion(conyugueOriginal.getNumeroIdentificacion());
        TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO = new TipoIdentificacionPersonaDTO();
        tipoIdentificacionPersonaDTO.setId(conyugueOriginal.getTipoIdentificacion().getId());
        conyugueCopia.setTipoIdentificacion(tipoIdentificacionPersonaDTO);
        conyugueCopia.setApellido1(conyugueOriginal.getApellido1());
        conyugueCopia.setApellido2(conyugueOriginal.getApellido2());
        conyugueCopia.setNombre1(conyugueOriginal.getNombre1());
        conyugueCopia.setNombre2(conyugueOriginal.getNombre2());
        conyugueCopia.setGenero(conyugueOriginal.getGenero());
        conyugueCopia.setFechaNacimiento(conyugueOriginal.getFechaNacimiento());
        MunicipioDTO municipioDTO = new MunicipioDTO();
        if (conyugueOriginal.getMunicipioExpedicionDocumento() != null
                && conyugueOriginal.getMunicipioExpedicionDocumento().getId() != null) {
            municipioDTO.setId(conyugueOriginal.getMunicipioExpedicionDocumento().getId());
        }
        conyugueCopia.setMunicipioExpedicionDocumento(municipioDTO);
        conyugueCopia.setFechaExpedicionDocumento(conyugueOriginal.getFechaExpedicionDocumento());
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // conyugueCopia.setNumeroTelefonico(conyugueOriginal.getNumeroTelefonico());
        // conyugueCopia.setNumeroCelular(conyugueOriginal.getNumeroCelular());
        // conyugueCopia.setCorreoElectronico(conyugueOriginal.getCorreoElectronico());
        conyugueCopia.setNombreEmpresaLabora(conyugueOriginal.getNombreEmpresaLabora());
        conyugueCopia.setCargoEmpresaLabora(conyugueOriginal.getCargoEmpresaLabora());
        // conyugueCopia.setFaxEmpresaLabora(conyugueOriginal.getFaxEmpresaLabora());
        // conyugueCopia.setTelefonoEmpresaLabora(conyugueOriginal.getTelefonoEmpresaLabora());
        TipoViviendaDTO tipoViviendaDTO = new TipoViviendaDTO();
        if (conyugueOriginal.getTipoVivienda() != null && conyugueOriginal.getTipoVivienda().getCodigo() != null) {
            tipoViviendaDTO.setCodigo(conyugueOriginal.getTipoVivienda().getCodigo());
        }
        conyugueCopia.setTipoVivienda(tipoViviendaDTO);
        NivelEducativoDTO nivelEducativoDTO = new NivelEducativoDTO();
        nivelEducativoDTO.setCodigo(conyugueOriginal.getNivelEducativo().getCodigo());
        conyugueCopia.setNivelEducativo(nivelEducativoDTO);
    }

    /**
     * Indica si se debe solicitar huella
     */
    private void solicitarHuella() {
        PersonaFL personaFL = this.cargarPersonaFL();
        PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if ((this.tiposDocConHuella == null) || (this.tiposDocConHuella.isEmpty())) {
            ValorParametroDTO valorParametroDTO = this.iRParametro.consultarParametro(EnumParametro.TIPO_DOC_REQ_HUELLA,
                    getCodigoOrganismoTransito(), true);
            if ((valorParametroDTO.getValores().size() == 1)
                    && (!StringUtils.isBlank(valorParametroDTO.getValorParam()))) {
                this.tiposDocConHuella = valorParametroDTO.getValores();
            } else {
                this.tiposDocConHuella = new ArrayList<String>();
            }

        }

        personaHolderFL.setSolicitaHuella(Boolean.FALSE);
        if (!this.tiposDocConHuella.isEmpty()) {
            List<TipoIdentificacionPersonaDTO> tipoIdentificacion = this.irAdministracion
                    .consultarTipoIdentificacionPersona(this.cargarPaisDTO(), null);
            for (String codigo : this.tiposDocConHuella) {
                for (TipoIdentificacionPersonaDTO tipoId : tipoIdentificacion) {
                    if (tipoId.getCodigo().equals(codigo)) {
                        personaHolderFL.setSolicitaHuella(Boolean.valueOf(true));
                        break;
                    }

                }

                if (personaHolderFL.getSolicitaHuella().booleanValue()) {
                    break;
                }

            }

            if ((personaHolderFL.getSolicitaHuella().booleanValue())
                    && (personaFL.getPersonaNaturalDTO().getRutaFoto() == null)) {
                this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_persona_ingresar_huella");
                // TODO RB https://redmine.datatools.com.co:8008/issues/12835
            }

        }

    }

    /**
     * Indica si se debe solicitar foto
     */
    private void solicitarFoto() {
        PersonaFL personaFL = this.cargarPersonaFL();
        PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if ((this.tiposDocConFoto == null) || (this.tiposDocConFoto.isEmpty())) {
            ValorParametroDTO valorParametroDTO = this.iRParametro.consultarParametro(EnumParametro.TIPO_DOC_REQ_FOTO,
                    getCodigoOrganismoTransito(), true);
            if ((valorParametroDTO.getValores().size() == 1)
                    && (!StringUtils.isBlank(valorParametroDTO.getValorParam()))) {
                this.tiposDocConFoto = valorParametroDTO.getValores();
            } else {
                this.tiposDocConFoto = new ArrayList<String>();
            }

        }

        personaHolderFL.setSolicitaFoto(Boolean.FALSE);
        if (!this.tiposDocConFoto.isEmpty()) {
            List<TipoIdentificacionPersonaDTO> tipoIdentificacion = this.irAdministracion
                    .consultarTipoIdentificacionPersona(this.cargarPaisDTO(), null);
            for (String codigo : this.tiposDocConFoto) {
                for (TipoIdentificacionPersonaDTO tipoId : tipoIdentificacion) {
                    if (tipoId.getCodigo().equals(codigo)) {
                        personaHolderFL.setSolicitaFoto(Boolean.TRUE);
                        break;
                    }

                }

                if (personaHolderFL.getSolicitaFoto().booleanValue()) {
                    break;
                }

            }

            if (personaHolderFL.getSolicitaFoto().booleanValue()) {
                if (personaFL.getPersonaNaturalDTO().getRutaFoto() == null) {
                    this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_persona_ingresar_foto");
                    // TODO RB https://redmine.datatools.com.co:8008/issues/12836 - solicitud y asociación de la foto
                    // se carga la foto y se asigna la fecha actual
                    personaFL.getPersonaNaturalDTO().setFechaFoto(new Date());
                } else {
                    // se verifica que no haya caducado la foto
                    ValorParametroDTO valorParametroDTO = this.iRParametro
                            .consultarParametro(EnumParametro.DIAS_VIGENCIA_FOTO, getCodigoOrganismoTransito(), false);

                    int dias = -1;
                    String valor = null;
                    if ((valorParametroDTO.getValores() != null) && (!valorParametroDTO.getValores().isEmpty())) {
                        valor = valorParametroDTO.getValorParam();
                    }

                    if (!StringUtils.isEmpty(valor)) {
                        dias = Integer.parseInt(valor);
                    }

                    Date fechaActual = Utilidades.getFechaActual();
                    Date fechaResultado = Utilidades.getFechaActual();
                    if (dias != -1) {
                        fechaResultado = Utilidades.sumarDias(personaFL.getPersonaNaturalDTO().getFechaFoto(), dias);
                    }

                    if ((dias == -1) || (fechaActual.after(fechaResultado))) {
                        this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_persona_ingresar_foto");
                        // TODO RB https://redmine.datatools.com.co:8008/issues/12837 solicitud y asociación de la foto por actualización
                        // se carga la foto y se asigna la fecha actual
                        personaFL.getPersonaNaturalDTO().setFechaFoto(new Date());
                    }

                }

            }

        }

    }

    /**
     * Retorna el pais de la sesion
     * 
     * @return PaisDTO cargado en la sesion
     */
    private PaisDTO cargarPaisDTO() {
        return this.findSessionObject(ConstantesManagedBean.CLASE_OBJ_PAIS, ConstantesManagedBean.NOMBRE_OBJ_PAIS);
    }

    /**
     * Carga la información del cónyugue que ya existe.
     * 
     * @param personaFL
     *            contiene la informacion de la persona
     * @param personaHolderFL
     *            contiene los atributos para presentacion
     * @param personaDTO
     *            conyugue a asociar
     */
    private void cargarConyugueExistente(PersonaFL personaFL, PersonaHolderFL personaHolderFL, PersonaDTO personaDTO) {
        personaFL.setConyugueDTO(personaDTO);
        DireccionDTO direccionDTO = this.obtenerMayorDireccion(EnumTipoUbicabilidad.RESIDENCIA,
                personaDTO.getDireccionPersonaList());
        if (direccionDTO == null) {
            direccionDTO = this.crearDireccion();
        }
        personaFL.setDireccionResidenciaConyugue(direccionDTO);

        direccionDTO = this.obtenerMayorDireccion(EnumTipoUbicabilidad.EMPRESA, personaDTO.getDireccionPersonaList());
        if (direccionDTO == null) {
            direccionDTO = this.crearDireccion();
        }
        personaFL.setDireccionTrabajoConyugue(direccionDTO);

        personaHolderFL.setLabelOperacion(
                this.getBundle(C2Bundles.NOMBRE_BUNDLE_ADMIN).getString("titulo_actualizar_persona"));
    }

    /**
     * Adiciona la direccion indicada a la lista de direcciones de la persona indicada
     * 
     * @param direccionDTO
     * @param personaDTO
     */
    private void adicionarDireccionPersona(DireccionDTO direccionDTO, PersonaDTO personaDTO) {
        DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
        direccionPersonaDTO.setFechaRegistro(new Date());
        direccionPersonaDTO.setPersona(personaDTO);
        direccionPersonaDTO.setDireccion(direccionDTO);
        TipoFuenteInformacionDTO tipoFuenteInformacionDTO = new TipoFuenteInformacionDTO();
        tipoFuenteInformacionDTO.setId(Integer.valueOf(this.getCodigoFuenteInformacion()));
        direccionPersonaDTO.setTipoFuenteInformacion(tipoFuenteInformacionDTO);
        personaDTO.getDireccionPersonaList().add(direccionPersonaDTO);
    }

    /**
     * Limpia los datos del filtro del conyugue
     */
    public void limpiarFiltroConyugue() {
        this.cargarPersonaHolderFL().limpieDatosFiltroConyugue();

    }

    /**
     * Adiciona el conyugue actual a la lista de conyugues de la persona natural principal
     */
    private void adicionarConyugue() {
        final PersonaFL personaFL = this.cargarPersonaFL();
        ParentescoPersonaDTO parentescoPersonaNuevoDTO = this.crearParentescoConyugue();
        personaFL.getPersonaNaturalDTO().getParentescoPersonaList().add(parentescoPersonaNuevoDTO);
    }

    /**
     * Retorna el parentesco relacionando a la persona natural principal con el conyugue como conyugues
     * 
     * @return Conyuge
     */
    private ParentescoPersonaDTO crearParentescoConyugue() {
        final PersonaFL personaFL = this.cargarPersonaFL();
        ParentescoPersonaDTO parentescoPersonaNuevoDTO = new ParentescoPersonaDTO();
        parentescoPersonaNuevoDTO.setPrincipal(personaFL.getPersonaNaturalDTO());
        parentescoPersonaNuevoDTO.setPariente(personaFL.getConyugueDTO());
        parentescoPersonaNuevoDTO.setFechaInicio(new Date());

        TipoParentescoPersonaDTO tipoParentescoPersonaDTO = new TipoParentescoPersonaDTO();
        tipoParentescoPersonaDTO.setId(EnumTipoParentescoPersona.CONYUGUE.getId());
        parentescoPersonaNuevoDTO.setTipoParentesco(tipoParentescoPersonaDTO);
        return parentescoPersonaNuevoDTO;
    }

    /**
     * Asigna valores por defecto y si los valores de la persona no son validos presenta mensaje de error
     * 
     * @param esNatural
     *            indica si es natural o juridica
     * 
     * @param tipoOperacion
     *            indica el tipo de operacion a realizar con la persona
     * 
     * @return true si es valida
     */
    private boolean esPersonaValida(boolean esNatural, EnumTipoOperacion tipoOperacion) {
        logger.debug("PersonaMB::esPersonaValida");
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        final PersonaFL personaFL = this.cargarPersonaFL();
        boolean personaValida = true;

        if (esNatural) {
            this.asignarValoresCombosPersonaNatural(personaFL.getPersonaNaturalDTO(), true, true);
            if (tipoOperacion == EnumTipoOperacion.INGRESAR_PERSONA_NATURAL) {

                if ((personaFL.getDireccionResidenciaPersonaNatural() != null)
                        && (personaFL.getDireccionResidenciaPersonaNatural().getMunicipio().getId() != null)) {
                    this.adicionarDireccionPersona(personaFL.getDireccionResidenciaPersonaNatural(),
                            personaFL.getPersonaNaturalDTO());
                }

                if ((personaFL.getDireccionTrabajoPersonaNatural() != null)
                        && (personaFL.getDireccionTrabajoPersonaNatural().getMunicipio().getId() != null)) {
                    this.adicionarDireccionPersona(personaFL.getDireccionTrabajoPersonaNatural(),
                            personaFL.getPersonaNaturalDTO());
                }

                if (!personaHolderFL.isPresentarOpcionConyugue()) {
                    // No tiene conyugue
                    personaFL.getPersonaNaturalDTO().setParentescoPersonaList(null);
                } else if (personaHolderFL.isConyugueAsociado()) {
                    // El conyugue queda en memoria, debe tener el mismo estado civil de la persona principal.
                    if (personaFL.getConyugueDTO().getId() != null) {
                        // se asocio un conyugue existente
                        this.adicionarConyugue();
                    }

                    personaFL.getPersonaNaturalDTO().getParentescoPersonaList()
                            .get(personaFL.getPersonaNaturalDTO().getParentescoPersonaList().size() - 1).getPariente()
                            .setEstadoCivil(personaFL.getPersonaNaturalDTO().getEstadoCivil());
                } else {
                    // La persona tiene estado civil que implica un conyugue, pero no inserto un conyugue...
                    // no debiera hacerse nada...
                    logger.debug(
                            "personaMB::ingresePersonaNatural LA PERSONA PRINCIPAL TIENE ESTADO CIVIL CON CONYUGUE PERO NO TIENE CONYUGUE ");
                }

                personaFL.getPersonaNaturalDTO().getTipoIdentificacion()
                        .setId(personaHolderFL.getIdTipoIdentificacionPersona());
                personaFL.getPersonaNaturalDTO()
                        .setNumeroIdentificacion(personaHolderFL.getNumeroIdentificacionPersona().trim());
            } else {
                this.adicionarDireccionPersonaPorTipo(personaFL.getPersonaNaturalDTO(),
                        personaFL.getDireccionResidenciaPersonaNatural(), EnumTipoUbicabilidad.RESIDENCIA);
                this.adicionarDireccionPersonaPorTipo(personaFL.getPersonaNaturalDTO(),
                        personaFL.getDireccionTrabajoPersonaNatural(), EnumTipoUbicabilidad.EMPRESA);
                if (!personaHolderFL.isPresentarOpcionConyugue()) {
                    // No tiene conyugue
                    logger.debug("PersonaMB::modificarPersona Natural LA PERSONA PRINCIPAL NO TIENE CONYUGUE ");
                    // pudo tener conyugue y lo dejaron sin conyugue, se debe cerrar el anterior
                    ParentescoPersonaDTO parentescoConyugue = this.obtenerParentescoPersonaActual();
                    if (parentescoConyugue != null) {
                        parentescoConyugue.setFechaFin(new Date());
                    }

                } else if (!personaHolderFL.isConyugueAsociado()) {
                    // pudo modificar data del conyugue pero no la asocio...
                    if (personaFL.getCopiaConyugueDTO() != null) {
                        for (ParentescoPersonaDTO parentescoPersonaDTO : personaFL.getPersonaNaturalDTO()
                                .getParentescoPersonaList()) {
                            if ((parentescoPersonaDTO.getFechaFin() == null) && (parentescoPersonaDTO.getPariente()
                                    .getId().equals(personaFL.getCopiaConyugueDTO().getId()))) {
                                this.copiarConyugue(personaFL.getCopiaConyugueDTO(),
                                        parentescoPersonaDTO.getPariente());
                            }

                        }

                    }

                } else {
                    // El conyugue queda en memoria, debe tener el mismo estado civil de la persona principal.
                    if ((personaFL.getConyugueDTO().getId() == null)
                            && (personaFL.getConyugueDTO().getTipoIdentificacion() != null)
                            && (personaFL.getConyugueDTO().getTipoIdentificacion().getId() != null)) {
                        // se asocio un conyugue nuevo
                        // no se hace nada porque ya se asocio anteriormente...
                        personaFL.getPersonaNaturalDTO().getParentescoPersonaList()
                                .get(personaFL.getPersonaNaturalDTO().getParentescoPersonaList().size() - 1)
                                .getPariente().setEstadoCivil(personaFL.getPersonaNaturalDTO().getEstadoCivil());
                        logger.debug(
                                "PersonaMB::modificarPersona Natural LA PERSONA PRINCIPAL TIENE UN CONYUGUE NUEVO ");
                    } else if (personaFL.getConyugueDTO().getId() != null) {
                        ParentescoPersonaDTO parentescoPersonaDTO = personaFL.getPersonaNaturalDTO()
                                .getParentescoPersonaList()
                                .get(personaFL.getPersonaNaturalDTO().getParentescoPersonaList().size() - 1);
                        if (parentescoPersonaDTO.getPariente().getId().equals(personaFL.getConyugueDTO().getId())) {
                            // pudo cambiar data
                            parentescoPersonaDTO.setPariente(personaFL.getConyugueDTO());
                        } else {
                            // cambio de conyugue por otro existente
                            this.adicionarConyugue();
                        }

                    }

                    PersonaDTO conyugueDTO = personaFL.getPersonaNaturalDTO().getParentescoPersonaList()
                            .get(personaFL.getPersonaNaturalDTO().getParentescoPersonaList().size() - 1).getPariente();
                    conyugueDTO.setEstadoCivil(personaFL.getPersonaNaturalDTO().getEstadoCivil());

                }

            }

            personaFL.getPersonaNaturalDTO().getFuenteInformacion().setId(this.getCodigoFuenteInformacion());
            logger.info("PersonaMB::esPersonaValida   -->  se validó ingreso persona natural y el resultado es "
                    + personaValida);
            return personaValida;

        } else {
            // Es persona Juridica
            int value;
            if (tipoOperacion == EnumTipoOperacion.INGRESAR_PERSONA_JURIDICA) {
                value = Utilidades.digitoVerificacion(personaHolderFL.getNumeroIdentificacionPersona());
            } else {
                value = Utilidades
                        .digitoVerificacion(personaHolderFL.getPersonaJuridicaSeleccionada().getNumeroIdentificacion());
            }

            logger.info("PersonaMB::esPersonaValida   -->  dígito validado " + value);
            if (!StringUtils.isBlank(personaFL.getDigitoVerificacion())) {
                if (Short.valueOf(personaFL.getDigitoVerificacion()).intValue() != value) {
                    ResourceBundle rb = this.getBundle(C2Bundles.NOMBRE_BUNDLE_ADMIN);
                    FacesContext.getCurrentInstance().addMessage("form-ingreso:spinDigitoverificacion",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                    rb.getString("msg_error_digito_verificacion")));
                    personaValida = false;
                    return personaValida;
                }

            }

            this.asignarValoresCombosPersonaJuridica(personaFL.getPersonaJuridicaDTO());
            if ((personaHolderFL.getIdTipoIdentificacionRepresentante() != null) && (personaHolderFL
                    .getIdTipoIdentificacionRepresentante().intValue() != PersonaMB.VALOR_DEFECTO_COMBOS)) {
                // Se tiene un representante legal en pantalla
                if (personaFL.getRepresentanteLegalActualDTO() != null) {
                    // Ya posee un Representante legal actual
                    if ((personaHolderFL.getIdTipoIdentificacionRepresentante()
                            .equals(personaFL.getRepresentanteLegalActualDTO().getTipoIdentificacion().getId()))
                            && (personaHolderFL.getNumeroDocumentoRepresentante()
                                    .equals(personaFL.getRepresentanteLegalActualDTO().getNumeroIdentificacion()))) {
                        // no se modificó la persona del representante legal... es válido
                        return personaValida;
                    } else {
                        // se modificó la persona del representante legal...
                        personaValida = this.existePersonaRepresentanteLegal(personaHolderFL, personaFL);
                    }

                } else {
                    personaValida = this.existePersonaRepresentanteLegal(personaHolderFL, personaFL);
                }

            }

            if (personaValida) {
                if (personaFL.getDireccionResidenciaPersonaJuridica() != null
                        && personaFL.getDireccionResidenciaPersonaJuridica().getId() == null) {
                    if (!StringUtils.isBlank(personaFL.getDireccionResidenciaPersonaJuridica().toString())) {
                        DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
                        direccionPersonaDTO.setFechaRegistro(new Date());
                        direccionPersonaDTO.setPersona(personaFL.getPersonaJuridicaDTO());
                        direccionPersonaDTO.setDireccion(personaFL.getDireccionResidenciaPersonaJuridica());
                        TipoFuenteInformacionDTO tipoFuenteInformacionDTO = new TipoFuenteInformacionDTO();
                        tipoFuenteInformacionDTO.setId(Integer.valueOf(this.getCodigoFuenteInformacion()));
                        direccionPersonaDTO.setTipoFuenteInformacion(tipoFuenteInformacionDTO);
                        personaFL.getPersonaJuridicaDTO().getDireccionPersonaList().add(direccionPersonaDTO);
                    }

                }

                Short digVerificacion = StringUtils.isBlank(personaFL.getDigitoVerificacion()) ? null
                        : Short.valueOf(personaFL.getDigitoVerificacion());
                personaFL.getPersonaJuridicaDTO().setDigitoVerificacion(digVerificacion);
                personaFL.getPersonaJuridicaDTO().getFuenteInformacion().setId(this.getCodigoFuenteInformacion());
                personaFL.getPersonaJuridicaDTO().getTipoIdentificacion()
                        .setId(personaHolderFL.getIdTipoIdentificacionPersona());
                if (personaHolderFL.getPersonaJuridicaSeleccionada() != null) {
                    personaFL.getPersonaJuridicaDTO().setNumeroIdentificacion(
                            personaHolderFL.getPersonaJuridicaSeleccionada().getNumeroIdentificacion().trim());
                } else {
                    personaFL.getPersonaJuridicaDTO()
                            .setNumeroIdentificacion(personaHolderFL.getNumeroIdentificacionPersona().trim());
                }

                if (tipoOperacion == EnumTipoOperacion.INGRESAR_PERSONA_JURIDICA) {
                    if ((personaFL.getRepresentanteLegalDTO() != null)
                            && (personaFL.getRepresentanteLegalDTO().getId() != null)) {
                        // Tiene un representante legal
                        this.adicionarUnicoRepresentanteLegal();
                    }

                }

            }

        }

        return personaValida;
    }

    private void adicionarDireccionPersonaPorTipo(PersonaDTO personaDTO, DireccionDTO direccionPersonaNatural,
            EnumTipoUbicabilidad tipoDireccion) {
        if ((direccionPersonaNatural != null) && (direccionPersonaNatural.getId() == null)) {
            if (!StringUtils.isBlank(direccionPersonaNatural.toString())) {
                // direccion nueva
                this.adicionarDireccionPersona(direccionPersonaNatural, personaDTO);
            } else {
                // limpiaron la direccion
                // busco la anterior q no este cerrada y la cierro...
                for (DireccionPersonaDTO direccionPersonaDTO : personaDTO.getDireccionPersonaList()) {
                    if ((direccionPersonaDTO.getDireccion().getTipoUbicabilidad().getId().intValue() == tipoDireccion
                            .getPk()) && (direccionPersonaDTO.getDireccion().getId() != null)
                            && (direccionPersonaDTO.getFechaActualizacion() == null)) {
                        direccionPersonaDTO.setFechaActualizacion(new Date());
                        break;
                    }

                }

            }

        }

    }

    /**
     * Confirma que exista una persona con el número y tipo de identificación del representante legal, modificando la persona del representante legal.
     * De no existir presenta mensaje de error y retorna false.
     * 
     * @return true si la persona existe
     */
    private boolean existePersonaRepresentanteLegal(PersonaHolderFL personaHolderFL, PersonaFL personaFL) {
        logger.debug("PersonaMB::existePersonaRepresentanteLegal");
        boolean existe = true;

        PersonaDTO personaTemp = this.irPersona.consultarPersona(getCodigoOrganismoTransito(),
                personaHolderFL.getIdTipoIdentificacionRepresentante(),
                personaHolderFL.getNumeroDocumentoRepresentante());
        existe = (personaTemp != null);
        if (existe) {
            personaFL.getRepresentanteLegalDTO().setId(personaTemp.getId());
        } else {
            this.addErrorMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_error_buscar_representante");
        }

        return existe;
    }

    /**
     * Modifica el nivel educativo por null en caso de no haberse seleccionado alguno
     * 
     * @param personaDTO
     *            elemento sobre el cual se modifica el nivel educativo
     */
    private void asignarNivelEducativo(PersonaDTO personaDTO) {
        if ((personaDTO.getNivelEducativo() != null) && (personaDTO.getNivelEducativo().getCodigo() != null)
                && (personaDTO.getNivelEducativo().getCodigo() == PersonaMB.VALOR_DEFECTO_COMBOS)) {
            personaDTO.getNivelEducativo().setCodigo(null);
        }

    }

    private void asignarGenero(PersonaDTO personaDTO) {
        if ((personaDTO.getGenero() != null)
                && (personaDTO.getGenero().charValue() == PersonaMB.VALOR_DEFECTO_COMBOS)) {
            personaDTO.setGenero(null);
        }

    }

    /**
     * Asigna el municipio seleccionado
     * 
     * @param personaDTO
     * @param esPrincipal
     */
    private void asignarMunicipioExpedicionDocumento(PersonaDTO personaDTO, boolean esPrincipal) {
        boolean limpiar = false;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (personaDTO.getMunicipioExpedicionDocumento() == null) {
            personaDTO.setMunicipioExpedicionDocumento(this.crearMunicipio());
        }

        if (esPrincipal) {
            if ((personaHolderFL.getIdMunicipioDoc() != null)
                    && (personaHolderFL.getIdMunicipioDoc() != PersonaMB.VALOR_DEFECTO_COMBOS)) {
                personaDTO.getMunicipioExpedicionDocumento().setId(personaHolderFL.getIdMunicipioDoc());
            } else {
                limpiar = true;
            }
        } else {
            if ((personaHolderFL.getIdMunDocConyugue() != null)
                    && (personaHolderFL.getIdMunDocConyugue() != PersonaMB.VALOR_DEFECTO_COMBOS)) {
                personaDTO.getMunicipioExpedicionDocumento().setId(personaHolderFL.getIdMunDocConyugue());
            } else {
                limpiar = true;
            }

        }

        if (limpiar) {
            personaDTO.setMunicipioExpedicionDocumento(this.crearMunicipio());
            personaDTO.getMunicipioExpedicionDocumento().setDepartamento(this.crearDepartamento());
        }

    }

    private void asignarTipoVivienda(PersonaDTO personaDTO) {
        if (personaDTO.getTipoVivienda() != null && personaDTO.getTipoVivienda().getCodigo() != null
                && personaDTO.getTipoVivienda().getCodigo() == PersonaMB.VALOR_DEFECTO_COMBOS) {
            personaDTO.getTipoVivienda().setCodigo(null);
        }

    }

    private void asignarEstadoCivil(PersonaDTO personaDTO) {
        if (personaDTO.getEstadoCivil() != null && personaDTO.getEstadoCivil().getId() != null
                && personaDTO.getEstadoCivil().getId() == PersonaMB.VALOR_DEFECTO_COMBOS) {
            personaDTO.getEstadoCivil().setId(null);
        }

    }

    /**
     * Asigna valores nulos a los campos que no son obligatorios tipo combo
     * 
     * @param personaDTO
     */
    private void asignarValoresCombosPersonaNatural(PersonaDTO personaDTO, boolean esPrincipal,
            boolean valideEstadoCivil) {
        this.asignarGenero(personaDTO);
        this.asignarMunicipioExpedicionDocumento(personaDTO, esPrincipal);
        this.asignarTipoVivienda(personaDTO);
        this.asignarNivelEducativo(personaDTO);
        if (valideEstadoCivil) {
            this.asignarEstadoCivil(personaDTO);
        }

    }

    /**
     * Asigna valores nulos a los campos que no son obligatorios tipo combo
     * 
     * @param personaDTO
     */
    private void asignarValoresCombosPersonaJuridica(PersonaJuridicaDTO personaDTO) {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if ((personaDTO.getTipoSociedad().getId() != null)
                && (personaDTO.getTipoSociedad().getId().intValue() == PersonaMB.VALOR_DEFECTO_COMBOS)) {
            personaDTO.getTipoSociedad().setId(null);
        }

        if ((personaHolderFL.getIdClaseActEconomica() == null) || ((personaHolderFL.getIdClaseActEconomica() != null)
                && (personaHolderFL.getIdClaseActEconomica().intValue() == PersonaMB.VALOR_DEFECTO_COMBOS))) {
            personaDTO.getClaseActividadEconomica().setId(null);
        } else {
            personaDTO.getClaseActividadEconomica().setId(personaHolderFL.getIdClaseActEconomica());
        }

        if ((personaHolderFL.getIdMunicipioJuridica() == null) || ((personaHolderFL.getIdMunicipioJuridica() != null)
                && (personaHolderFL.getIdMunicipioJuridica().intValue() == PersonaMB.VALOR_DEFECTO_COMBOS))) {
            personaDTO.getMunicipio().setId(null);
        } else {
            personaDTO.getMunicipio().setId(personaHolderFL.getIdMunicipioJuridica());
        }

    }

    /**
     * Adiciona el representante legal a la persona juridica.
     */
    private void adicionarUnicoRepresentanteLegal() {
        final PersonaFL personaFL = this.cargarPersonaFL();
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        List<RepresentanteLegalDTO> list = new ArrayList<RepresentanteLegalDTO>();
        list.add(personaFL.getRepresentanteLegalDTO());
        personaFL.getRepresentanteLegalDTO().setNumeroIdentificacion(personaHolderFL.getNumeroDocumentoRepresentante());
        personaFL.getRepresentanteLegalDTO().getTipoIdentificacion()
                .setId(personaHolderFL.getIdTipoIdentificacionRepresentante());
        personaFL.getPersonaJuridicaDTO().setRepresentanteLegalList(list);
    }

    /**
     * Realiza las modificaciones necesarias al representante legal de la persona jurídica
     */
    private void actualizarRepresentanteLegal() {
        logger.debug("PersonaMB::valideRepresentanteLegal ");
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        final PersonaFL personaFL = this.cargarPersonaFL();

        // SE MANEJA EL REPRESENTANTE LEGAL ACTUAL...
        if (personaFL.getRepresentanteLegalActualDTO() != null) {
            // Tenía representante legal
            if (personaHolderFL.getIdTipoIdentificacionRepresentante().intValue() == PersonaMB.VALOR_DEFECTO_COMBOS) {
                // Ya no tiene representante legal
                personaFL.getRepresentanteLegalActualDTO().setFechaFin(new Date());
            } else if ((!personaHolderFL.getIdTipoIdentificacionRepresentante()
                    .equals(personaFL.getRepresentanteLegalActualDTO().getTipoIdentificacion().getId()))
                    || (!personaHolderFL.getNumeroDocumentoRepresentante().trim()
                            .equals(personaFL.getRepresentanteLegalActualDTO().getNumeroIdentificacion()))) {
                // Cambio de Representante legal
                if (personaFL.getRepresentanteLegalActualDTO().getFechaFin() == null) {
                    personaFL.getRepresentanteLegalActualDTO().setFechaFin(new Date());
                }

                personaFL.getRepresentanteLegalDTO()
                        .setNumeroIdentificacion(personaHolderFL.getNumeroDocumentoRepresentante());
                personaFL.getRepresentanteLegalDTO().getTipoIdentificacion()
                        .setId(personaHolderFL.getIdTipoIdentificacionRepresentante());
                personaFL.getPersonaJuridicaDTO().getRepresentanteLegalList().add(personaFL.getRepresentanteLegalDTO());
            } else {
                // no se modificó la persona del representante legal... es posible que hayan cambiado sus campos básicos
                personaFL.getRepresentanteLegalActualDTO()
                        .setCorreoElectronico(personaFL.getRepresentanteLegalDTO().getCorreoElectronico());
                personaFL.getRepresentanteLegalActualDTO()
                        .setFechaInicio(personaFL.getRepresentanteLegalDTO().getFechaInicio());
                personaFL.getRepresentanteLegalActualDTO()
                        .setFechaFin(personaFL.getRepresentanteLegalDTO().getFechaFin());
            }

        } else {
            // No tenía representante legal
            if (personaHolderFL.getIdTipoIdentificacionRepresentante() != null && personaHolderFL
                    .getIdTipoIdentificacionRepresentante().intValue() != PersonaMB.VALOR_DEFECTO_COMBOS) {
                // Selección un nuevo representante legal
                List<RepresentanteLegalDTO> list = personaFL.getPersonaJuridicaDTO().getRepresentanteLegalList();
                if ((list == null) || (list.isEmpty())) {
                    // No tenía representantes
                    this.adicionarUnicoRepresentanteLegal();
                } else {
                    // ya tenía representantes
                    personaFL.getRepresentanteLegalDTO()
                            .setNumeroIdentificacion(personaHolderFL.getNumeroDocumentoRepresentante());
                    personaFL.getRepresentanteLegalDTO().getTipoIdentificacion()
                            .setId(personaHolderFL.getIdTipoIdentificacionRepresentante());
                    list.add(personaFL.getRepresentanteLegalDTO());
                }

            }

        }

    }

    /**
     * Constructor vacio
     */
    public PersonaMB() {
    }

    /**
     * Carga los parametros necesarios para el correcto funcionamiento
     */
    public void cargueParametros() {
        this.cargarTiposIdentificacion();
        this.setCodigoFuenteInformacion(EnumTipoFuenteInformacion.ADMINISTRACION.getValue());
    }

    /**
     * Evalua si debe o no pedirse huella, foto y opción de presentar el conyugue
     */
    public void presentarOpcionesPorEstadoCivil() {
        this.solicitarHuella();
        this.solicitarFoto();
        this.presentarConyugue();
    }

    /**
     * Evalua si debe o no mostrarse la opcion del conyugue.
     */
    public void presentarConyugue() {
        final PersonaFL personaFL = this.cargarPersonaFL();
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (personaFL.getPersonaNaturalDTO().getEstadoCivil().getId() != null) {
            personaHolderFL.setPresentarOpcionConyugue(false);
            for (Integer id : this.estadosCivilesConyugue) {
                if (personaFL.getPersonaNaturalDTO().getEstadoCivil().getId().equals(id)) {
                    personaHolderFL.setPresentarOpcionConyugue(true);
                    break;
                }

            }

        }

    }

    /**
     * Realiza la consulta de la persona principal al intentar registrar
     */
    public void buscarPrincipalNuevo() {
        logger.info("PersonaMB::buscarPrincipal ");
        personaPrincipalExistente();

    }

    /**
     * Indica si la persona que se esta buscando existe o no
     * 
     * @return true si la persona existe, false de lo contrario
     * @author luis.forero
     */
    private boolean personaPrincipalExistente() {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        final PersonaFL personaFL = this.cargarPersonaFL();

        personaHolderFL.limpieResultadoBusqueda();
        personaFL.limpieDatosBusqueda();
        PersonaDTO personaDTO = this.irPersona.consultarPersona(getCodigoOrganismoTransito(),
                personaHolderFL.getIdTipoIdentificacionPersona(), personaHolderFL.getNumeroIdentificacionPersona());
        if (personaDTO != null) {
            // la persona existe
            this.addWarningMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_persona_existente");
            return true;
        } else {
            // la persona no existe...
            this.addWarningMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_persona_inexistente");
            this.cargarPersonaNueva();
            return false;
        }
    }

    /**
     * Método que realiza la consulta del conyugue
     * 
     */
    public void buscarConyugue() {
        logger.debug("PersonaMB::buscarConyugue ");
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        final PersonaFL personaFL = this.cargarPersonaFL();

        this.prepareConyugue();
        personaHolderFL.setMostrarConyugue(false);
        if ((personaHolderFL.getIdTipoIdentificacionConyuge().equals(personaHolderFL.getIdTipoIdentificacionPersona()))
                && (personaHolderFL.getNumeroIdentificacionPersona()
                        .equals(personaHolderFL.getNumeroDocumentoConyuge()))) {
            this.addErrorMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_error_buscar_conyugue");
        } else {
            PersonaDTO personaDTO = this.irPersona.consultarPersona(getCodigoOrganismoTransito(),
                    personaHolderFL.getIdTipoIdentificacionConyuge(), personaHolderFL.getNumeroDocumentoConyuge());
            personaHolderFL.setMostrarConyugue(true);
            if (personaDTO != null) {
                // Conyugue existe
                this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_conyugue_existente");
                this.cargarConyugueExistente(personaFL, personaHolderFL, personaDTO);
            } else {
                // Conyugue no existe
                this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_persona_inexistente");
                personaHolderFL.setLabelOperacion(
                        this.getBundle(C2Bundles.NOMBRE_BUNDLE_ADMIN).getString("titulo_ingresar_persona"));
            }

        }

    }

    /**
     * Inserta la nueva persona natural al sistema
     */
    public boolean ingresarPersona() {
        logger.debug("personaMB::ingresarPersona ");
        boolean valida = false;
        final PersonaFL personaFL = this.cargarPersonaFL();
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (this.esPersonaValida(true, EnumTipoOperacion.INGRESAR_PERSONA_NATURAL)) {
            this.solicitarHuella();
            if (!personaHolderFL.getSolicitaHuella().booleanValue()) {
                this.solicitarFoto();
                if (!personaHolderFL.getSolicitaFoto().booleanValue()) {
                    personaFL.getPersonaNaturalDTO().setValidarCamposMinimos(false);
                    try {
                        this.irPersona.registrarPersona(personaFL.getPersonaNaturalDTO());
                        this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_exito_ingresar_persona");
                        if (!isCliente()) {
                            this.limpiar();
                        }
                        valida = true;
                    } catch (CirculemosNegocioException pe) {
                        CirculemosErrorHandler.handleException(pe);
                    }

                }

            }

        }

        return valida;
    }

    /**
     * Realiza el proceso de ingreso de la persona Jurídica
     */
    public boolean ingresarJuridico() {
        logger.debug("PersonaMB::ingresarJuridico ");
        boolean valida = false;
        final PersonaFL personaFL = this.cargarPersonaFL();
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (this.esPersonaValida(false, EnumTipoOperacion.INGRESAR_PERSONA_JURIDICA)) {
            this.solicitarHuella();
            if (!personaHolderFL.getSolicitaHuella().booleanValue()) {
                this.solicitarFoto();
                if (!personaHolderFL.getSolicitaFoto().booleanValue()) {
                    personaFL.getPersonaJuridicaDTO().setValidarCamposMinimos(false);
                    try {
                        personaFL.getPersonaJuridicaDTO()
                                .setId(this.irPersona.registrarPersona(personaFL.getPersonaJuridicaDTO()));
                        CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();
                        if (!isCliente()) {
                            this.limpiar();
                        }
                        valida = true;
                    } catch (CirculemosNegocioException pe) {
                        CirculemosErrorHandler.handleException(pe);
                    }

                }

            }

        }

        return valida;
    }

    /**
     * Realiza la actualización de la persona natural actual
     */
    public void modificarPersona() {
        logger.debug("PersonaMB::modificarPersona ");
        final PersonaFL personaFL = this.cargarPersonaFL();
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (this.esPersonaValida(true, EnumTipoOperacion.ACTUALIZAR_PERSONA_NATURAL)) {
            this.solicitarHuella();
            if (!personaHolderFL.getSolicitaHuella().booleanValue()) {
                this.solicitarFoto();
                if (!personaHolderFL.getSolicitaFoto().booleanValue()) {
                    personaFL.getPersonaNaturalDTO().setValidarCamposMinimos(false);
                    try {
                        this.irPersona.modificarPersona(personaFL.getPersonaNaturalDTO(), null);
                        this.cargarPersonaHolderFL().setPersonaEditada(true);
                        this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_exito_actualizar_persona");
                        this.prepareEdicionPersonaNatural();
                    } catch (CirculemosNegocioException pe) {
                        CirculemosErrorHandler.handleException(pe);
                    }

                }

            }

        }

    }

    /**
     * Ingresa el conyugue digitado. Este solo queda en memoria, no es persistido.
     */
    public void ingresarConyugue() {
        logger.debug("PersonaMB::ingresarConyugue ");
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        final PersonaFL personaFL = this.cargarPersonaFL();

        this.asignarValoresCombosPersonaNatural(personaFL.getConyugueDTO(), false, false);
        this.adicionarDireccionPersonaPorTipo(personaFL.getConyugueDTO(), personaFL.getDireccionResidenciaConyugue(),
                EnumTipoUbicabilidad.RESIDENCIA);
        this.adicionarDireccionPersonaPorTipo(personaFL.getConyugueDTO(), personaFL.getDireccionTrabajoConyugue(),
                EnumTipoUbicabilidad.EMPRESA);
        if (personaFL.getConyugueDTO().getId() == null) {
            // Se asignan los datos de documentos de identificacion
            PersonaDTO conyugue = personaFL.getConyugueDTO();
            conyugue.getTipoIdentificacion().setId(personaHolderFL.getIdTipoIdentificacionConyuge());
            conyugue.setNumeroIdentificacion(personaHolderFL.getNumeroDocumentoConyuge());
            conyugue.getFuenteInformacion().setId(this.getCodigoFuenteInformacion());

            ParentescoPersonaDTO parentescoPersonaDTO = this.crearParentescoConyugue();
            if (!personaFL.getPersonaNaturalDTO().getParentescoPersonaList().isEmpty()) {
                ParentescoPersonaDTO parentescoPersonaAnteriorDTO = personaFL.getPersonaNaturalDTO()
                        .getParentescoPersonaList()
                        .get(personaFL.getPersonaNaturalDTO().getParentescoPersonaList().size() - 1);
                if ((parentescoPersonaAnteriorDTO.getFechaFin() == null)
                        && (parentescoPersonaAnteriorDTO.getPariente().getTipoIdentificacion().getId()
                                .intValue() == parentescoPersonaDTO.getPariente().getTipoIdentificacion().getId()
                                        .intValue())
                        && (parentescoPersonaAnteriorDTO.getPariente().getNumeroIdentificacion()
                                .equals(parentescoPersonaDTO.getPariente().getNumeroIdentificacion()))) {
                    personaFL.getPersonaNaturalDTO().getParentescoPersonaList()
                            .remove(personaFL.getPersonaNaturalDTO().getParentescoPersonaList().size() - 1);
                }

            }

            personaFL.getPersonaNaturalDTO().getParentescoPersonaList().add(parentescoPersonaDTO);
            personaHolderFL.setConyugueAsociado(true);
            this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_exito_ingresar_conyuge");
        } else {
            PersonaDTO conyugue = personaFL.getConyugueDTO();
            conyugue.getFuenteInformacion().setId(this.getCodigoFuenteInformacion());
            personaHolderFL.setConyugueAsociado(true);
            this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_exito_actualizar_conyuge");
        }

    }

    /**
     * Modifica la informacion del conyugue.
     */
    public void modificarConyugue() {
        logger.debug("personaMB::modificarConyugue ");
        this.cargarPersonaHolderFL().setConyugueAsociado(true);
        this.addInfoMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_exito_actualizar_conyuge");
    }

    /**
     * Realiza la actualización de la persona jurídica actual
     */
    public void modificarJuridico() {
        logger.debug("PersonaMB::modificarJuridico ");
        final PersonaFL personaFL = this.cargarPersonaFL();
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (this.esPersonaValida(false, EnumTipoOperacion.ACTUALIZAR_PERSONA_JURIDICA)) {
            this.actualizarRepresentanteLegal();

            this.solicitarHuella();
            if (!personaHolderFL.getSolicitaHuella().booleanValue()) {
                this.solicitarFoto();
                if (!personaHolderFL.getSolicitaFoto().booleanValue()) {
                    personaFL.getPersonaJuridicaDTO().setValidarCamposMinimos(false);
                    try {
                        this.irPersona.modificarPersona(personaFL.getPersonaJuridicaDTO(), null);
                        CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
                        this.prepareEdicionPersonaJuridica();
                        personaHolderFL.setPersonaEditada(true);
                    } catch (CirculemosNegocioException e) {
                        CirculemosErrorHandler.handleException(e);
                    }

                }

            }

        }

    }

    /**
     * TODO RC - Validar en capa de negocio (ver {@link IRDireccion#validarDireccion(DireccionDTO)} Almacena en memoria la direccion ingresada por el
     * usuario, si no es valida muestra mensaje de error
     * 
     * @param esNatural
     *            indica si se ingresa la direccion de una persona natural
     * @param enumUbicabilidad
     *            indica el tipo de direccion que se esta ingresando
     */
    private boolean ingresarDireccion(boolean esNatural, boolean esConyugue, EnumTipoUbicabilidad enumUbicabilidad) {
        final DireccionFL direccionFL = findFlowObject(DireccionFL.class, C2FlowObjects.DIRECCION_FL);
        boolean valida = true;

        if (!StringUtils.isBlank(direccionFL.getDireccionDTO().toString())) {
            // valida = this.direccionMB.esDireccionValida();
        }

        if (valida) {
            if (direccionFL.getDireccionDTO().getTipoUbicabilidad() == null) {
                TipoUbicabilidadDTO tipoUbicabilidadDTO = new TipoUbicabilidadDTO();
                tipoUbicabilidadDTO.setId(enumUbicabilidad.getPk());
                direccionFL.getDireccionDTO().setTipoUbicabilidad(tipoUbicabilidadDTO);
            }

            switch (enumUbicabilidad) {
            case RESIDENCIA:
                if (esNatural) {
                    if (esConyugue) {
                        if (!this.cargarPersonaFL().getDireccionResidenciaConyugue().toString()
                                .equals(direccionFL.getDireccionDTO().toString())) {
                            direccionFL.getDireccionDTO().setId(null);
                        }

                        this.cargarPersonaFL().setDireccionResidenciaConyugue(direccionFL.getDireccionDTO());
                    } else {
                        if (!this.cargarPersonaFL().getDireccionResidenciaPersonaNatural().toString()
                                .equals(direccionFL.getDireccionDTO().toString())) {
                            direccionFL.getDireccionDTO().setId(null);
                        }

                        this.cargarPersonaFL().setDireccionResidenciaPersonaNatural(direccionFL.getDireccionDTO());
                    }

                } else {
                    if (!this.cargarPersonaFL().getDireccionResidenciaPersonaJuridica().toString()
                            .equals(direccionFL.getDireccionDTO().toString())) {
                        direccionFL.getDireccionDTO().setId(null);
                    }

                    this.cargarPersonaFL().setDireccionResidenciaPersonaJuridica(direccionFL.getDireccionDTO());
                }
                break;
            case EMPRESA:
                if (esNatural) {
                    if (esConyugue) {
                        if (!this.cargarPersonaFL().getDireccionTrabajoConyugue().toString()
                                .equals(direccionFL.getDireccionDTO().toString())) {
                            direccionFL.getDireccionDTO().setId(null);
                        }

                        this.cargarPersonaFL().setDireccionTrabajoConyugue(direccionFL.getDireccionDTO());
                    } else {
                        if (!this.cargarPersonaFL().getDireccionTrabajoPersonaNatural().toString()
                                .equals(direccionFL.getDireccionDTO().toString())) {
                            direccionFL.getDireccionDTO().setId(null);
                        }

                        this.cargarPersonaFL().setDireccionTrabajoPersonaNatural(direccionFL.getDireccionDTO());
                    }

                }
                break;
            default: // nada por hacer...
                break;
            }

        }

        return valida;
    }

    /**
     * Realiza la asociacion de la direccion de residencia a la persona natural
     * 
     * @return true si se asocio la direccion correctamente
     */
    public boolean ingresarDireccionResidenciaNatural() {
        return this.ingresarDireccion(true, false, EnumTipoUbicabilidad.RESIDENCIA);
    }

    /**
     * Realiza la asociacion de la direccion de residencia a la persona juridica
     * 
     * @return true si se asocio la direccion correctamente
     */
    public boolean ingresarDireccionResidenciaJuridica() {
        return this.ingresarDireccion(false, false, EnumTipoUbicabilidad.RESIDENCIA);
    }

    /**
     * Realiza la asociacion de la direccion de la empresa a la persona natural
     * 
     * @return true si se asocio la direccion correctamente
     */
    public boolean ingresarDireccionEmpresaNatural() {
        return this.ingresarDireccion(true, false, EnumTipoUbicabilidad.EMPRESA);
    }

    /**
     * Realiza la asociacion de la direccion de residencia al conyugue
     * 
     * @return true si se asocio la direccion correctamente
     */
    public boolean ingresarDireccionResidenciaConyugue() {
        return this.ingresarDireccion(true, true, EnumTipoUbicabilidad.RESIDENCIA);
    }

    /**
     * Realiza la asociacion de la direccion de la empresa al conyugue
     * 
     * @return true si se asocio la direccion correctamente
     */
    public boolean ingresarDireccionEmpresaConyugue() {
        return this.ingresarDireccion(true, true, EnumTipoUbicabilidad.EMPRESA);
    }

    /**
     * TODO RC - Reemplazar Spring Web Flow por ventana emergente Alista la direccion de la residencia de la persona natural para ser mostrada
     */
    public void prepareDireccionResidencia() {
        final DireccionFL direccionFL = findFlowObject(DireccionFL.class, C2FlowObjects.DIRECCION_FL);
        final PersonaFL personaFL = this.cargarPersonaFL();
        // this.direccionMB.setCliente(true);

        if (personaFL.getDireccionResidenciaPersonaNatural() == null) {
            direccionFL.setDireccionDTO(this.crearDireccion());
        } else if (personaFL.getDireccionResidenciaPersonaNatural().getId() == null) {
            direccionFL.setDireccionDTO(this.cargarPersonaFL().getDireccionResidenciaPersonaNatural());
        } else {
            direccionFL.setDireccionDTO(personaFL.getDireccionResidenciaPersonaNatural().clone());
        }

    }

    /**
     * TODO RC - Reemplazar Spring Web Flow por ventana emergente Alista la direccion de la residencia de la persona juridica para ser mostrada
     */
    public void prepareDireccionResidenciaJuridica() {
        final DireccionFL direccionFL = findFlowObject(DireccionFL.class, C2FlowObjects.DIRECCION_FL);
        final PersonaFL personaFL = this.cargarPersonaFL();
        // this.direccionMB.setCliente(true);

        if (personaFL.getDireccionResidenciaPersonaJuridica() == null) {
            direccionFL.setDireccionDTO(this.crearDireccion());
        } else if (personaFL.getDireccionResidenciaPersonaJuridica().getId() == null) {
            direccionFL.setDireccionDTO(this.cargarPersonaFL().getDireccionResidenciaPersonaJuridica());
        } else {
            direccionFL.setDireccionDTO(this.cargarPersonaFL().getDireccionResidenciaPersonaJuridica().clone());
        }

    }

    /**
     * TODO RC - Reemplazar Spring Web Flow por ventana emergente Alista la direccion de la empresa de la persona natural para ser mostrada
     */
    public void prepareDireccionEmpresa() {
        final DireccionFL direccionFL = findFlowObject(DireccionFL.class, C2FlowObjects.DIRECCION_FL);
        final PersonaFL personaFL = this.cargarPersonaFL();
        // this.direccionMB.setCliente(true);

        if (personaFL.getDireccionTrabajoPersonaNatural() == null) {
            direccionFL.setDireccionDTO(this.crearDireccion());
        } else if (personaFL.getDireccionTrabajoPersonaNatural().getId() == null) {
            direccionFL.setDireccionDTO(this.cargarPersonaFL().getDireccionTrabajoPersonaNatural());
        } else {
            direccionFL.setDireccionDTO(personaFL.getDireccionTrabajoPersonaNatural().clone());
        }

    }

    /**
     * TODO RC - Reemplazar Spring Web Flow por ventana emergente Alista la direccion de la residencia del conyugue para ser mostrada
     */
    public void prepareDireccionResidenciaConyugue() {
        final DireccionFL direccionFL = findFlowObject(DireccionFL.class, C2FlowObjects.DIRECCION_FL);
        // this.direccionMB.setCliente(true);
        if (this.cargarPersonaFL().getDireccionResidenciaConyugue().getId() == null) {
            direccionFL.setDireccionDTO(this.cargarPersonaFL().getDireccionResidenciaConyugue());
        } else {
            direccionFL.setDireccionDTO(this.cargarPersonaFL().getDireccionResidenciaConyugue().clone());
        }

    }

    /**
     * TODO RC - Reemplazar Spring Web Flow por ventana emergente Alista la direccion de la empresa de la persona natural para ser mostrada
     */
    public void prepareDireccionEmpresaConyugue() {
        final DireccionFL direccionFL = findFlowObject(DireccionFL.class, C2FlowObjects.DIRECCION_FL);
        // this.direccionMB.setCliente(true);
        if (this.cargarPersonaFL().getDireccionTrabajoConyugue().getId() == null) {
            direccionFL.setDireccionDTO(this.cargarPersonaFL().getDireccionTrabajoConyugue());
        } else {
            direccionFL.setDireccionDTO(this.cargarPersonaFL().getDireccionTrabajoConyugue().clone());
        }

    }

    /**
     * Retorna la direccion del tipo de ubicabilidad indicado, con la fecha final nula y con fecha inicio mayor a todas las de la lista indicada
     * 
     * @return DireccionDTO
     */
    private DireccionDTO obtenerMayorDireccion(EnumTipoUbicabilidad tipoUbicabilidad,
            List<DireccionPersonaDTO> direccionPersonaLista) {
        DireccionDTO direccionDTO = null;
        Date fechaIni = null;

        for (DireccionPersonaDTO direccionPersonaDTO : direccionPersonaLista) {
            if ((direccionPersonaDTO.getFechaActualizacion() == null) && (direccionPersonaDTO.getDireccion()
                    .getTipoUbicabilidad().getCodigo().equals(tipoUbicabilidad.getPk()))) {
                if (direccionDTO == null) {
                    fechaIni = direccionPersonaDTO.getFechaRegistro();
                    direccionDTO = direccionPersonaDTO.getDireccion();
                } else if (fechaIni.after(direccionPersonaDTO.getFechaRegistro())) {
                    fechaIni = direccionPersonaDTO.getFechaRegistro();
                    direccionDTO = direccionPersonaDTO.getDireccion();
                }

            }

        }

        return direccionDTO;
    }

    /**
     * Inicializa el contenido del conyugue con la informacion de una persona nueva
     */
    public void prepareConyugue() {
        PersonaFL personaFL = this.cargarPersonaFL();

        if (personaFL.getPersonaNaturalDTO().getParentescoPersonaList().isEmpty()) {
            this.cargarPersonaFL().setConyugueDTO(this.crearPersonaDTO());
            personaFL.setDireccionResidenciaConyugue(this.crearDireccion());
            personaFL.setDireccionTrabajoConyugue(this.crearDireccion());
        } else {
            if (personaFL.getPersonaNaturalDTO().getId() != null) {
                this.copiarConyugue(this.obtenerConyugueActual(), personaFL.getCopiaConyugueDTO());
            }

            personaFL.setConyugueDTO(this.obtenerConyugueActual());
            List<DireccionPersonaDTO> direccionPersonaLista = personaFL.getPersonaNaturalDTO()
                    .getParentescoPersonaList()
                    .get(personaFL.getPersonaNaturalDTO().getParentescoPersonaList().size() - 1).getPariente()
                    .getDireccionPersonaList();
            DireccionDTO direccionDTO = this.obtenerMayorDireccion(EnumTipoUbicabilidad.RESIDENCIA,
                    direccionPersonaLista);
            personaFL.setDireccionResidenciaConyugue(direccionDTO);
            direccionDTO = this.obtenerMayorDireccion(EnumTipoUbicabilidad.EMPRESA, direccionPersonaLista);
            if (direccionDTO != null) {
                personaFL.setDireccionTrabajoConyugue(direccionDTO);
            } else {
                personaFL.setDireccionTrabajoConyugue(this.crearDireccion());
            }

        }

    }

    /**
     * @return true si el conyugue no existe
     */
    public boolean isConyugueNuevo() {
        final PersonaFL personaFL = this.cargarPersonaFL();
        boolean resp = true;

        if (personaFL.getConyugueDTO() != null) {
            resp = (personaFL.getConyugueDTO().getId() == null);
        }

        return resp;
    }

    /**
     * @return true si la division de la actividad debe estar desabilitada
     */
    public boolean isDivisionDesabilitada() {
        boolean resp = true;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (personaHolderFL.getIdSeccionActEconomica() != null) {
            resp = (personaHolderFL.getIdSeccionActEconomica().intValue() == PersonaMB.VALOR_DEFECTO_COMBOS);
            if (resp) {
                personaHolderFL.setIdDivisionActEconomica(Integer.valueOf(PersonaMB.VALOR_DEFECTO_COMBOS));
            }

        }

        return resp;
    }

    /**
     * @return true si el grupo de la actividad debe estar desabilitada
     */
    public boolean isGrupoDesabilitado() {
        boolean resp = true;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (personaHolderFL.getIdDivisionActEconomica() != null) {
            resp = ((this.isDivisionDesabilitada())
                    || (personaHolderFL.getIdDivisionActEconomica().intValue() == PersonaMB.VALOR_DEFECTO_COMBOS));
            if (resp) {
                personaHolderFL.setIdGrupoActEconomica(PersonaMB.VALOR_DEFECTO_COMBOS);
            }

        }

        return resp;
    }

    /**
     * @return true si la clase de actividad debe estar desabilitada
     */
    public boolean isClaseDesabilitada() {
        boolean resp = true;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        final PersonaFL personaFL = this.cargarPersonaFL();

        if (personaHolderFL.getIdGrupoActEconomica() != null) {
            resp = ((this.isGrupoDesabilitado())
                    || (personaHolderFL.getIdGrupoActEconomica().intValue() == PersonaMB.VALOR_DEFECTO_COMBOS));
            if (resp) {
                personaFL.getPersonaJuridicaDTO().getClaseActividadEconomica().setId(null);
            }

        }

        return resp;
    }

    /**
     * indica si el listado de departamentos debe estar desabilitado
     * 
     * @return true si no se han seleccionado paises
     */
    public boolean isDepartamentoDesabilitado() {
        boolean resp = true;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        PersonaJuridicaDTO personaJuridicaDTO = this.cargarPersonaFL().getPersonaJuridicaDTO();

        if ((personaJuridicaDTO != null) && (personaHolderFL.getIdPaisJuridica() != null)
                && (personaHolderFL.getIdPaisJuridica().intValue() != PersonaMB.VALOR_DEFECTO_COMBOS)) {
            resp = false;
        }

        return resp;
    }

    /**
     * indica si el municipio debe estar desabilitado
     * 
     * @return si no se han seleccionado departamentos
     */
    public boolean isMunicipioDesabilitado() {
        boolean resp = true;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        PersonaDTO personaNaturalDTO = this.cargarPersonaFL().getPersonaNaturalDTO();

        if (personaNaturalDTO != null) {
            if ((personaHolderFL.getIdDepartamentoDoc() != null)
                    && (personaHolderFL.getIdDepartamentoDoc().intValue() != PersonaMB.VALOR_DEFECTO_COMBOS)) {
                resp = false;
            }

        } else {
            PersonaJuridicaDTO personaJuridicaDTO = this.cargarPersonaFL().getPersonaJuridicaDTO();
            if ((personaJuridicaDTO != null) && (personaHolderFL.getIdDepartamentoJuridica() != null)
                    && (personaHolderFL.getIdDepartamentoJuridica().intValue() != PersonaMB.VALOR_DEFECTO_COMBOS)) {
                resp = false;
            }

        }

        return resp;
    }

    /**
     * @return true si el municipio del conyugue debe estar desabilitado
     */
    public boolean isMunicipioDesabilitadoConyugue() {
        boolean resp = true;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        PersonaDTO conyugueDTO = this.cargarPersonaFL().getConyugueDTO();

        if ((conyugueDTO != null) && (personaHolderFL.getIdDeptoDocConyugue() != null)
                && (personaHolderFL.getIdDeptoDocConyugue().intValue() != PersonaMB.VALOR_DEFECTO_COMBOS)) {
            resp = false;
        }

        return resp;
    }

    /**
     * @return true si el representante legal es obligatorio
     */
    public boolean isRepresentanteObligatorio() {
        boolean resp = false;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        final PersonaFL personaFL = this.cargarPersonaFL();

        if ((personaHolderFL.getIdTipoIdentificacionRepresentante() != null) && (personaHolderFL
                .getIdTipoIdentificacionRepresentante().intValue() != PersonaMB.VALOR_DEFECTO_COMBOS)) {
            resp = true;
        }

        if (!resp) {
            personaFL.getRepresentanteLegalDTO().setCorreoElectronico(null);
            personaFL.getRepresentanteLegalDTO().setFechaInicio(null);
            personaFL.getRepresentanteLegalDTO().setNumeroIdentificacion(null);
            personaHolderFL.setNumeroDocumentoRepresentante(null);
        }

        return resp;
    }

    public List<Integer> getEstadosCivilesConyugue() {
        return this.estadosCivilesConyugue;
    }

    public void setEstadosCivilesConyugue(List<Integer> estadosCivilesConyugue) {
        this.estadosCivilesConyugue = estadosCivilesConyugue;
    }

    public DireccionMB getDireccionMB() {
        return this.direccionMB;
    }

    public void setDireccionMB(DireccionMB direccionMB) {
        this.direccionMB = direccionMB;
    }

    public CatalogoGeneralMB getCatalogoGeneralMB() {
        return this.catalogoGeneralMB;
    }

    public void setCatalogoGeneralMB(CatalogoGeneralMB catalogoGeneralMB) {
        this.catalogoGeneralMB = catalogoGeneralMB;
    }

    public int getCodigoFuenteInformacion() {
        return this.codigoFuenteInformacion;
    }

    public void setCodigoFuenteInformacion(int codigoFuenteInformacion) {
        this.codigoFuenteInformacion = codigoFuenteInformacion;
    }

    public int getIdTipoDocumentoEmpresa() {
        return this.catalogoGeneralMB.getTipoDocumentoEmpresa();
    }

    /**
     * Indica si se deben mostrar los filtros para persona natural
     * 
     * @return true si el documento seleccionado no es de persona juridica
     */
    public boolean mostrarFiltroNatural() {
        boolean mostrar = false;
        PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        if (!personaHolderFL.isFuenteExterna()) {
            Integer idTipoDoc = personaHolderFL.getIdTipoIdentificacionPersona();

            if (idTipoDoc != null) {
                mostrar = (idTipoDoc.intValue() != this.getIdTipoDocumentoEmpresa());
            }
        }

        return mostrar;
    }

    /**
     * Indica si se deben mostrar los filtros para persona juridica
     * 
     * @return true si el documento seleccionado es de persona juridica
     */
    public boolean mostrarFiltroJuridico() {
        boolean mostrar = false;
        PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        if (!personaHolderFL.isFuenteExterna()) {
            Integer idTipoDoc = personaHolderFL.getIdTipoIdentificacionPersona();

            if (idTipoDoc != null) {
                mostrar = (idTipoDoc.intValue() == this.getIdTipoDocumentoEmpresa());
            }
        }

        return mostrar;
    }

    /**
     * Indica si se debe mostrar el resultado de la consulta de persona Juridica
     * 
     * @return true si el tamanio del resultado es mayor de cero
     */
    public boolean mostrarResultadoJuridica() {
        PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        return ((personaHolderFL.isMostrarResultado()) && (!personaHolderFL.getResultadoJuridico().isEmpty()));
    }

    /**
     * Indica si se debe mostrar el resultado de la consulta de persona natural
     * 
     * @return true si el tamanio del resultado es mayor de cero
     */
    public boolean mostrarResultadoNatural() {
        PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        return ((personaHolderFL.isMostrarResultado()) && (!personaHolderFL.getResultadoNatural().isEmpty()));
    }

    /**
     * Limpia los campos del formulario de la persona principal
     */
    public void limpiar() {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        personaHolderFL.limpieDatosFiltro();
        personaHolderFL.limpieResultadoBusqueda();
        this.cargarPersonaFL().limpieDatosBusqueda();
    }

    /**
     * Realiza la consulta de los funcionarios acorde a los filtros propios de un funcionario
     */
    public void filtrarPersona() {
        if (this.esFiltroValido()) {
            PersonaDTO personaFiltro = this.corregirFiltro();
            final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
            personaHolderFL.setMostrarResultado(false);
            personaHolderFL.setResultadoConsulta(this.irPersona.consultarPersonas(personaFiltro));
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(personaHolderFL.getTamanioConsulta());
            personaHolderFL.setMostrarResultado(true);
        }

    }

    /**
     * Indica si el filtro realizado es valido. En caso de no serlo muestra mensaje de error
     * 
     * @return true si el filtro es valido
     */
    private boolean esFiltroValido() {
        boolean filtroNormal = true;
        boolean valido = true;
        int contador = 0;
        PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        if (personaHolderFL.getIdTipoIdentificacionPersona().intValue() != this.getIdTipoDocumentoEmpresa()) {
            if (StringUtils.isBlank(personaHolderFL.getNumeroIdentificacionPersona())) {
                filtroNormal = false;
                if (!StringUtils.isBlank(personaHolderFL.getNombre1())) {
                    contador++;
                }

                if (!StringUtils.isBlank(personaHolderFL.getNombre2())) {
                    contador++;
                }

                if (!StringUtils.isBlank(personaHolderFL.getApellido1())) {
                    contador++;
                }

                if (!StringUtils.isBlank(personaHolderFL.getApellido2())) {
                    contador++;
                }

            }

        } else {
            if (StringUtils.isBlank(personaHolderFL.getNumeroIdentificacionPersona())) {
                filtroNormal = false;
                if ((personaHolderFL.getDigitoVerificacion() != null)) {
                    contador++;
                }

                if (!StringUtils.isBlank(personaHolderFL.getNombreComercial())) {
                    contador++;
                }

                if ((!StringUtils.isBlank(personaHolderFL.getSigla()))) {
                    contador++;
                }

            }

        }

        if ((!filtroNormal) && (contador < 2)) {
            valido = false;
            this.addErrorMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_error_filtro_busqueda");
        }

        return valido;
    }

    /**
     * Asigna los valores para el filtro de la consulta corrigiendo sus contenidos
     * 
     * @return PersonaDTO con los valores para realizar el filtro
     */
    private PersonaDTO corregirFiltro() {
        PersonaDTO personaFiltro;
        PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (personaHolderFL.getIdTipoIdentificacionPersona() == this.getIdTipoDocumentoEmpresa()) {
            PersonaJuridicaDTO personaFiltroTemporal = this.crearPersonaJuridicaDTO();
            personaFiltro = personaFiltroTemporal;
            if (personaHolderFL.getDigitoVerificacion() != null) {
                personaFiltroTemporal.setDigitoVerificacion(personaHolderFL.getDigitoVerificacion());
            }

            if (personaHolderFL.getSigla() != null) {
                personaHolderFL.setSigla(personaHolderFL.getSigla().trim());
                if (!StringUtils.isBlank(personaHolderFL.getSigla())) {
                    personaFiltroTemporal.setSigla(personaHolderFL.getSigla());
                }

            }

            if (personaHolderFL.getNombreComercial() != null) {
                personaHolderFL.setNombreComercial(personaHolderFL.getNombreComercial().trim());
                if (!StringUtils.isBlank(personaHolderFL.getNombreComercial())) {
                    personaFiltroTemporal.setNombreComercial(personaHolderFL.getNombreComercial());
                }
            }

        } else {
            personaFiltro = this.crearPersonaDTO();
            if (personaHolderFL.getNombre1() != null) {
                personaHolderFL.setNombre1(personaHolderFL.getNombre1().trim());
                if (!StringUtils.isBlank(personaHolderFL.getNombre1())) {
                    personaFiltro.setNombre1(personaHolderFL.getNombre1());
                }

            }

            if (personaHolderFL.getNombre2() != null) {
                personaHolderFL.setNombre2(personaHolderFL.getNombre2().trim());
                if (!StringUtils.isBlank(personaHolderFL.getNombre2())) {
                    personaFiltro.setNombre2(personaHolderFL.getNombre2());
                }

            }

            if (personaHolderFL.getApellido1() != null) {
                personaHolderFL.setApellido1(personaHolderFL.getApellido1().trim());
                if (!StringUtils.isBlank(personaHolderFL.getApellido1())) {
                    personaFiltro.setApellido1(personaHolderFL.getApellido1());
                }

            }

            if (personaHolderFL.getApellido2() != null) {
                personaHolderFL.setApellido2(personaHolderFL.getApellido2().trim());
                if (!StringUtils.isBlank(personaHolderFL.getApellido2())) {
                    personaFiltro.setApellido2(personaHolderFL.getApellido2());
                }

            }

        }

        personaFiltro.getTipoIdentificacion().setId(personaHolderFL.getIdTipoIdentificacionPersona());
        if (personaHolderFL.getNumeroIdentificacionPersona() != null) {
            personaHolderFL.setNumeroIdentificacionPersona(personaHolderFL.getNumeroIdentificacionPersona().trim());
            if (!StringUtils.isBlank(personaHolderFL.getNumeroIdentificacionPersona())) {
                personaFiltro.setNumeroIdentificacion(personaHolderFL.getNumeroIdentificacionPersona());
            }

        }

        return personaFiltro;
    }

    /**
     * Retorna el genero indicado como un String largo
     * 
     * @param genero
     *            contiene la letra del genero a retornar como String largo
     * 
     * @return Masculino o Femenino
     */
    public String nombreGenero(String genero) {
        String nombre = "";
        if (genero.equalsIgnoreCase(EnumGeneroPersona.MASCULINO.getDiminutivo())) {
            nombre = CirculemosAccesoBundleGeneral.getStringGeneral(MensajesGeneral.lab_genero_masculino);
        } else if (genero.equalsIgnoreCase(EnumGeneroPersona.FEMENINO.getDiminutivo())) {
            nombre = CirculemosAccesoBundleGeneral.getStringGeneral(MensajesGeneral.lab_genero_femenino);
        }

        return nombre;
    }

    /**
     * Retorna la direccion de la residencia actual como un String
     * 
     * @param lista
     *            contiene la lista de direcciones
     * 
     * @return direccion de la residencia como un String
     */
    public String direccionResidencia(List<DireccionPersonaDTO> lista) {
        return this.direccionUbicabilidad(lista, EnumTipoUbicabilidad.RESIDENCIA);
    }

    /**
     * Retorna la direccion de la residencia actual como un String
     * 
     * @param lista
     *            contiene la lista de direcciones
     * 
     * @return direccion de la residencia como un String
     */
    public String direccionEmpresa(List<DireccionPersonaDTO> lista) {
        return this.direccionUbicabilidad(lista, EnumTipoUbicabilidad.EMPRESA);
    }

    /**
     * Retorna la direccion actual acorde al tipo de ubicabilidad
     * 
     * @param lista
     *            lista de direcciones persona
     * @param ubicabilidad
     *            ubicabilidad
     * @return DireccionDTO con la ubicabilidad indicada
     */
    private DireccionDTO getDireccionUbicabilidad(List<DireccionPersonaDTO> lista, EnumTipoUbicabilidad ubicabilidad) {
        DireccionDTO direccion = null;
        for (DireccionPersonaDTO direccionPersonaDTO : lista) {
            if ((direccionPersonaDTO.getDireccion().getTipoUbicabilidad().getId().intValue() == ubicabilidad.getPk())
                    && (direccionPersonaDTO.getFechaActualizacion() == null)) {
                direccion = direccionPersonaDTO.getDireccion();
                break;
            }

        }

        return direccion;
    }

    /**
     * Retorna la direccion actual con el tipo de ubicabilidad indicado de la lista indicada
     * 
     * @param lista
     *            contiene el listado de direcciones sobre las cuales buscar
     * 
     * @param ubicabilidad
     *            indica el tipo de ubicabilidad a buscar
     * 
     * @return la direccion actual con el tipo de ubicabilidad indicado como un String
     */
    private String direccionUbicabilidad(List<DireccionPersonaDTO> lista, EnumTipoUbicabilidad ubicabilidad) {
        String direccion = "";
        if (lista != null) {
            DireccionDTO direccionDTO = this.getDireccionUbicabilidad(lista, ubicabilidad);
            if (direccionDTO != null) {
                direccion = direccionDTO.toString();
            }

        }

        return direccion;
    }

    /**
     * Adiciona la persona seleccionada a la pila y la prepara para ser mostrada
     */
    public void prepareDetallePersonaNatural() {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(personaHolderFL.getPersonaSeleccionada().getId());
        if (personaHolderFL.getConyugueSeleccionado() != null) {
            personaDTO.setId(personaHolderFL.getConyugueSeleccionado().getPariente().getId());
        }

        List<PersonaDTO> resultado = this.irPersona.consultarPersonas(personaDTO);
        personaHolderFL.setPersonaSeleccionada(resultado.get(0));
        personaHolderFL.adicionePila();

        ParentescoPersonaDTO parentescoPersonaDTO = new ParentescoPersonaDTO();
        parentescoPersonaDTO.setPrincipal(personaDTO);
        TipoParentescoPersonaDTO tipoParentescoPersonaDTO = new TipoParentescoPersonaDTO();
        tipoParentescoPersonaDTO.setId(EnumTipoParentescoPersona.CONYUGUE.getId());
        parentescoPersonaDTO.setTipoParentesco(tipoParentescoPersonaDTO);
        List<ParentescoPersonaDTO> parentescos = this.irPersona.consultarParentescoPersona(parentescoPersonaDTO);
        personaHolderFL.getPersonaSeleccionada().setParentescoPersonaList(parentescos);
        if (personaHolderFL.getConyugueSeleccionado() != null) {
            personaHolderFL.setConyugueSeleccionado(null);
        }

    }

    /**
     * Elimina la ultima persona de la pila y muestra la anterior
     */
    public void prepareDetalleConyugue() {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        personaHolderFL.remuevaPila();
        personaHolderFL.setConyugueSeleccionado(null);
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(personaHolderFL.getPersonaSeleccionada().getId());
        ParentescoPersonaDTO parentescoPersonaDTO = new ParentescoPersonaDTO();
        parentescoPersonaDTO.setPrincipal(personaDTO);
        TipoParentescoPersonaDTO tipoParentescoPersonaDTO = new TipoParentescoPersonaDTO();
        tipoParentescoPersonaDTO.setId(EnumTipoParentescoPersona.CONYUGUE.getId());
        parentescoPersonaDTO.setTipoParentesco(tipoParentescoPersonaDTO);
        List<ParentescoPersonaDTO> parentescos = this.irPersona.consultarParentescoPersona(parentescoPersonaDTO);
        personaHolderFL.getPersonaSeleccionada().setParentescoPersonaList(parentescos);
    }

    /**
     * Prepara la persona juridica seleccionada para ser consultada
     */
    public void prepareDetallePersonaJuridica() {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        List<RepresentanteLegalDTO> repsDTO = this.irPersona
                .consultarRepresentastesLegales(personaHolderFL.getPersonaJuridicaSeleccionada().getId());

        personaHolderFL.getPersonaJuridicaSeleccionada().setRepresentanteLegalList(repsDTO);
        personaHolderFL.setRepresentanteLegalDTO(this.obtenerUltimoRepresentanteLegal(repsDTO));
    }

    /**
     * Inicializa los datos de detalle
     */
    public void limpieDatosDetalle() {
        this.cargarPersonaHolderFL().limpieDatosDetalle();
    }

    /**
     * Indica si se debe mostrar el detalle de la persona natural
     * 
     * @return Verdadero muestra detalle persona natural
     */
    public boolean mostrarDetalleNatural() {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        if (!personaHolderFL.isFuenteExterna()) {
            return personaHolderFL.getIdTipoIdentificacionPersona().intValue() != this.getIdTipoDocumentoEmpresa();
        } else {
            return true;
        }
    }

    /**
     * Indica si se debe mostrar el detalle de la persona juridica
     * 
     * @return Verdadero muestra detalle persona juridica
     */
    public boolean mostrarDetalleJuridica() {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        if (!personaHolderFL.isFuenteExterna()) {
            return personaHolderFL.getIdTipoIdentificacionPersona().intValue() == this.getIdTipoDocumentoEmpresa();
        } else {
            return false;
        }
    }

    /**
     * Retorna el nombre del tipo de documento del representante legal
     * 
     * @param lista
     *            contiene la lista de representantes legales sobres los cuales se busca el ultimo
     * 
     * @return nombre del documento del representante legal
     */
    public String tipoDocumentoRepresentante() {
        String tipoDocumento = "";
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (personaHolderFL.getRepresentanteLegalDTO() != null) {
            tipoDocumento = personaHolderFL.getRepresentanteLegalDTO().getTipoIdentificacion().getNombre();
        }

        return tipoDocumento;
    }

    /**
     * Retorna el numero de documento del representante legal
     * 
     * @param lista
     *            contiene la lista de representantes legales sobres los cuales se busca el ultimo
     * 
     * @return numero del documento del representante legal
     */
    public String numeroDocumentoRepresentante() {
        String numeroDocumento = "";
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (personaHolderFL.getRepresentanteLegalDTO() != null) {
            numeroDocumento = personaHolderFL.getRepresentanteLegalDTO().getNumeroIdentificacion();
        }

        return numeroDocumento;
    }

    /**
     * Retorna el numero de documento del representante legal
     * 
     * @param lista
     *            contiene la lista de representantes legales sobres los cuales se busca el ultimo
     * 
     * @return numero del documento del representante legal
     */
    public String emailRepresentante() {
        String email = "";
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (personaHolderFL.getRepresentanteLegalDTO() != null) {
            email = personaHolderFL.getRepresentanteLegalDTO().getCorreoElectronico();
        }

        return email;
    }

    /**
     * Retorna la fecha de inicio de vigencia del representante legal
     * 
     * @param lista
     *            contiene la lista de representantes legales sobres los cuales se busca el ultimo
     * 
     * @return fecha de inicio de vigencia del representante legal
     */
    public Date inicioVigenciaRepresentante() {
        Date fechaInicio = null;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (personaHolderFL.getRepresentanteLegalDTO() != null) {
            fechaInicio = personaHolderFL.getRepresentanteLegalDTO().getFechaInicio();
        }

        return fechaInicio;
    }

    /**
     * Retorna el representante legal actual
     * 
     * @param lista
     *            contiene la lista de representantes sobre la cual se busca el representante legal actual
     * @return RepresentanteLegalDTO con la información del representante legal actual
     */
    private RepresentanteLegalDTO obtenerUltimoRepresentanteLegal(List<RepresentanteLegalDTO> lista) {
        // TODO RB https://redmine.datatools.com.co:8008/issues/12844
        Date fechaActual, fechaInicio;
        RepresentanteLegalDTO representanteLegalDTO = null;
        if (lista != null && !lista.isEmpty()) {
            fechaActual = UtilFecha.currentZeroTimeDate();
            for (RepresentanteLegalDTO representanteDTO : lista) {
                if (representanteDTO.getFechaFin() != null) {
                    if (UtilFecha.betweenDate(representanteDTO.getFechaInicio(), representanteDTO.getFechaFin(),
                            fechaActual)) {
                        representanteLegalDTO = representanteDTO;
                        break;
                    }

                } else {
                    fechaInicio = UtilFecha.resetTime(representanteDTO.getFechaInicio()).getTime();
                    int valor = fechaInicio.compareTo(fechaActual);
                    if (valor <= 0) {
                        representanteLegalDTO = representanteDTO;
                        break;
                    }

                }

            }

        }

        return representanteLegalDTO;
    }

    /**
     * Prepara la informacion para editar una persona natural
     */
    public void prepareEdicionPersonaNatural() {
        final PersonaFL personaFL = this.cargarPersonaFL();
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(personaHolderFL.getPersonaSeleccionada().getId());
        if (personaHolderFL.getConyugueSeleccionado() != null) {
            personaDTO.setId(personaHolderFL.getConyugueSeleccionado().getPariente().getId());
        }

        List<PersonaDTO> resultado = this.irPersona.consultarPersonas(personaDTO);
        personaDTO = resultado.get(0);
        personaHolderFL.setPersonaNatural(true);
        personaHolderFL.setMostrarPanelNatural(true);
        personaHolderFL.setPresentarOpcionConyugue(false);

        ParentescoPersonaDTO parentescoPersonaDTO = new ParentescoPersonaDTO();
        parentescoPersonaDTO.setPrincipal(personaDTO);
        TipoParentescoPersonaDTO tipoParentescoPersonaDTO = new TipoParentescoPersonaDTO();
        tipoParentescoPersonaDTO.setId(EnumTipoParentescoPersona.CONYUGUE.getId());
        parentescoPersonaDTO.setTipoParentesco(tipoParentescoPersonaDTO);
        List<ParentescoPersonaDTO> parentescos = this.irPersona.consultarParentescoPersona(parentescoPersonaDTO);
        personaDTO.setParentescoPersonaList(parentescos);

        this.cargarPersonaNatural(personaFL, personaHolderFL, personaDTO);

        DireccionDTO direccionDTO = this.obtenerMayorDireccion(EnumTipoUbicabilidad.RESIDENCIA,
                personaDTO.getDireccionPersonaList());
        personaFL.setDireccionResidenciaPersonaNatural(direccionDTO);
        direccionDTO = this.obtenerMayorDireccion(EnumTipoUbicabilidad.EMPRESA, personaDTO.getDireccionPersonaList());
        if (direccionDTO != null) {
            personaFL.setDireccionTrabajoPersonaNatural(direccionDTO);
        } else {
            personaFL.setDireccionTrabajoPersonaNatural(this.crearDireccion());
        }

    }

    /**
     * Prepara los datos de la persona juridica a ser editada
     */
    public void prepareEdicionPersonaJuridica() {
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        PersonaJuridicaDTO personaDTO = new PersonaJuridicaDTO();
        personaDTO.setId(personaHolderFL.getPersonaJuridicaSeleccionada().getId());
        List<PersonaDTO> resultado = this.irPersona.consultarPersonas(personaDTO);
        personaDTO = (PersonaJuridicaDTO) resultado.get(0);

        personaHolderFL.setPersonaNatural(false);
        personaHolderFL.setMostrarPanelJuridico(true);

        this.prepareDetallePersonaJuridica();
        personaDTO.setRepresentanteLegalList(
                personaHolderFL.getPersonaJuridicaSeleccionada().getRepresentanteLegalList());
        this.cargarPersonaJuridica(personaDTO);
    }

    /**
     * Actualiza los datos del FL posterior a salir de la pantalla de edicion
     */
    public void actualiceEstadosFL() {
        PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();
        if (personaHolderFL.isPersonaEditada()) {
            this.filtrarPersona();
        }

        personaHolderFL.limpieDatosDetalle();
        this.cargarPersonaFL().setPersonaNaturalDTO(null);
    }

    /**
     * Obtiene el pariente actual
     * 
     * @return conyugue con fecha fin no definida
     */
    private PersonaDTO obtenerConyugueActual() {
        PersonaDTO conyugueDTO = null;

        for (ParentescoPersonaDTO parentescoPersonaDTO : this.cargarPersonaFL().getPersonaNaturalDTO()
                .getParentescoPersonaList()) {
            if ((parentescoPersonaDTO.getTipoParentesco().getId().equals(EnumTipoParentescoPersona.CONYUGUE.getId()))
                    && (parentescoPersonaDTO.getFechaFin() == null)) {
                conyugueDTO = parentescoPersonaDTO.getPariente();
                break;
            }

        }

        return conyugueDTO;
    }

    /**
     * Retorna el parentesco persona asociado al conyugue actual
     * 
     * @return ParentescoPersonaDTO asociado al conyugue actual con id distinto de null
     */
    private ParentescoPersonaDTO obtenerParentescoPersonaActual() {
        ParentescoPersonaDTO conyugueDTO = null;

        for (ParentescoPersonaDTO parentescoPersonaDTO : this.cargarPersonaFL().getPersonaNaturalDTO()
                .getParentescoPersonaList()) {
            if ((parentescoPersonaDTO.getTipoParentesco().getId().equals(EnumTipoParentescoPersona.CONYUGUE.getId()))
                    && (parentescoPersonaDTO.getFechaFin() == null)
                    && (parentescoPersonaDTO.getPariente().getId() != null)) {
                conyugueDTO = parentescoPersonaDTO;
                break;
            }

        }

        return conyugueDTO;
    }

    /**
     * Elimina la direccion de la empresa de la persona natural que haya sido ingresada sin salvar
     */
    public void limpiarDireccionEmpresaNatural() {
        PersonaFL personaFL = this.cargarPersonaFL();
        personaFL.setDireccionTrabajoPersonaNatural(this.crearDireccion());
    }

    /**
     * Elimina la direccion de la residencia de la persona natural que haya sido ingresada sin salvar
     */
    public void limpiarDireccionResidenciaNatural() {
        PersonaFL personaFL = this.cargarPersonaFL();
        personaFL.setDireccionResidenciaPersonaNatural(this.crearDireccion());
    }

    /**
     * Elimina la direccion de la residencia de la persona juridica que haya sido ingresada sin salvar
     */
    public void limpiarDireccionResidenciaJuridica() {
        PersonaFL personaFL = this.cargarPersonaFL();
        personaFL.setDireccionResidenciaPersonaJuridica(this.crearDireccion());
    }

    /**
     * Elimina la direccion de la empresa de la persona natural que haya sido ingresada sin salvar
     */
    public void limpiarDireccionEmpresaConyugue() {
        PersonaFL personaFL = this.cargarPersonaFL();
        this.eliminarDireccionPersonaPorTipo(personaFL.getConyugueDTO(), EnumTipoUbicabilidad.EMPRESA);
        personaFL.setDireccionTrabajoConyugue(this.crearDireccion());
    }

    /**
     * Elimina la direccion de la residencia de la persona natural que haya sido ingresada sin salvar
     */
    public void limpiarDireccionResidenciaConyugue() {
        PersonaFL personaFL = this.cargarPersonaFL();
        this.eliminarDireccionPersonaPorTipo(personaFL.getConyugueDTO(), EnumTipoUbicabilidad.RESIDENCIA);
        personaFL.setDireccionResidenciaConyugue(this.crearDireccion());
    }

    /**
     * Elimina la direccion del tipo de ubicabilidad indicado de la persona natural que haya sido ingresada sin salvar
     * 
     * @param tipoUbicabilidad
     */
    private void eliminarDireccionPersonaPorTipo(PersonaDTO personaDTO, EnumTipoUbicabilidad tipoUbicabilidad) {
        int pos = -1, indice = -1;

        for (DireccionPersonaDTO direccionPersonaDTO : personaDTO.getDireccionPersonaList()) {
            indice++;
            if ((direccionPersonaDTO.getDireccion().getTipoUbicabilidad().getId().intValue() == tipoUbicabilidad
                    .getPk()) && (direccionPersonaDTO.getId() == null)) {
                pos = indice;
                break;
            }

        }

        if (pos != -1) {
            personaDTO.getDireccionPersonaList().remove(pos);
        }

    }

    /**
     * Permite saber si el conyugue esta en edicion o ingreso
     * 
     * @return true si el conyugue cargado no es nuevo
     */
    public boolean conyugueEnEdicion() {
        return this.cargarPersonaFL().getConyugueDTO().getId() != null;
    }

    /**
     * Retorna la minima fecha de fallecimiento a seleccionar
     * 
     * @return Minima fecha fallecimiento
     */
    public Date minimaFechaFallecimiento() {
        Date minima = null;
        Date nacimiento = this.cargarPersonaFL().getPersonaNaturalDTO().getFechaNacimiento();
        Date expedicion = this.cargarPersonaFL().getPersonaNaturalDTO().getFechaExpedicionDocumento();

        if (expedicion != null) {
            minima = expedicion;
        } else if (nacimiento != null) {
            minima = Utilidades.sumarDias(nacimiento, 1);
        }

        return minima;
    }

    /**
     * Indica si se tiene fecha de fallecimiento
     * 
     * @return true si tiene asociada una fecha de fallecimiento
     */
    public boolean tieneFechaFallecimiento() {
        return this.cargarPersonaFL().getPersonaNaturalDTO().getFechaFallecimiento() != null;
    }

    /**
     * Indica si el registro de la persona esta habilitado o no para editar
     * 
     * @return true si no se puede
     */
    public boolean personaDesabilitada() {
        return !this.cargarPersonaHolderFL().isPersonaEditable();
    }

    /**
     * Retorna la minima fecha de fin de vigencia del representante legal
     * 
     * @return fecha inicio mas un dia
     */
    public Date minimaFechaFinVigenciaRepresentante() {
        Date fecha = null;
        Date fechaInicio = this.cargarPersonaFL().getRepresentanteLegalDTO().getFechaInicio();
        if (fechaInicio != null) {
            fecha = Utilidades.sumarDias(fechaInicio, 1);
        }

        return fecha;
    }

    /**
     * Método para clientes del caso de uso para validar si la persona natural a ingresar es valida. La persona natural queda almacenada en
     * personaFL.getPersonaNaturalDTO()
     */
    public boolean esPersonaNaturalValida() {
        boolean valida = false;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (this.esPersonaValida(true, EnumTipoOperacion.INGRESAR_PERSONA_NATURAL)) {
            this.solicitarHuella();
            if (!personaHolderFL.getSolicitaHuella().booleanValue()) {
                this.solicitarFoto();
                if (!personaHolderFL.getSolicitaFoto().booleanValue()) {
                    valida = true;
                }

            }

        }

        return valida;
    }

    /**
     * Método para clientes del caso de uso para validar si la persona juridica a ingresar es valida. La persona Juridica queda almacenada en
     * personaFL.getPersonaJuridicaDTO()
     */
    public boolean esPersonaJuridicaValida() {
        boolean valida = false;
        final PersonaHolderFL personaHolderFL = this.cargarPersonaHolderFL();

        if (this.esPersonaValida(false, EnumTipoOperacion.INGRESAR_PERSONA_JURIDICA)) {
            this.solicitarHuella();
            if (!personaHolderFL.getSolicitaHuella().booleanValue()) {
                this.solicitarFoto();
                if (!personaHolderFL.getSolicitaFoto().booleanValue()) {
                    valida = true;
                }

            }

        }

        return valida;
    }

    public boolean isCliente() {
        return this.findFlowObject(PersonaHolderFL.class, C2FlowObjects.PERSONA_HOLDER_FL).isCliente();
    }

    public void setCliente(boolean cliente) {
        this.findFlowObject(PersonaHolderFL.class, C2FlowObjects.PERSONA_HOLDER_FL).setCliente(cliente);
    }

    /**
     * Retorna la maxima fecha de nacimiento a seleccionar
     * 
     * @return Maxima fecha nacimiento
     */
    public Date obtenerMaxFechaNacimiento() {
        Date fecha = new Date();
        PersonaDTO personaDTO = this.cargarPersonaFL().getPersonaNaturalDTO();

        if (personaDTO.getFechaExpedicionDocumento() != null) {
            fecha = personaDTO.getFechaExpedicionDocumento();
        }

        return Utilidades.sumarDias(fecha, -1);
    }

    /**
     * Retorna la minima fecha de expedicion del documento
     * 
     * @return Minima fecha expedicion documento
     */
    public Date obtenerMinFechaExpDocumento() {
        Date fecha = null;
        PersonaDTO personaDTO = this.cargarPersonaFL().getPersonaNaturalDTO();

        if (personaDTO.getFechaNacimiento() != null) {
            fecha = Utilidades.sumarDias(personaDTO.getFechaNacimiento(), 1);
        }

        if (fecha == null) {
            fecha = this.obtenerFechaMinima();
        }

        return fecha;
    }

    /**
     * Consulta el detalle de una persona en la Persona Fuente Informacion Externa. <b>CU_CIR20_DAT_ADM_004</b>
     * 
     * @return true si encontro la persona en la consulta.
     * @author luis.forero (mod 2014-11-20)
     */
    public boolean consultarPersonaFuenteInfExterna() {
        logger.debug(PersonaMB.class.getName().concat("::consultarPersonaFuenteInfExterna()"));
        final PersonaHolderFL personaHolderFL = findFlowObject(PersonaHolderFL.class, C2FlowObjects.PERSONA_HOLDER_FL);
        Integer idTipoIdentificacionPersona = personaHolderFL.getIdTipoIdentificacionPersona();
        String numeroIdentificacionPersona = personaHolderFL.getNumeroIdentificacionPersona();
        if (idTipoIdentificacionPersona != null && StringUtils.isNotBlank(numeroIdentificacionPersona)) {
            PersonaDTO personaFuenteExterna = irPersona.consultarPersonaFuenteExterna(idTipoIdentificacionPersona,
                    numeroIdentificacionPersona, findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                            ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO).getCodigoOrganismo());

            if (personaFuenteExterna == null) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
                return false;
            }
            personaHolderFL.setPersonaSeleccionada(personaFuenteExterna);
        } else {
            this.addErrorMessage(C2Bundles.NOMBRE_BUNDLE_ADMIN, "msg_error_numero_tipo_doc_persona");
            return false;
        }

        return true;
    }

    /**
     * Cliente para determinar si la persona ya existe o debe ser ingresada.
     * 
     * @return true si existe la persona, false de lo contrario
     * @author luis.forero
     */
    public boolean existePersonaPrincipal() {
        return this.personaPrincipalExistente();
    }

    /**********************
     * Expresiones regulares
     **********************/
    public String getExpresionCorreoE() {
        return ExpresionesRegulares.REGEX_EMAIL;
    }
    /**********************
     * Fin Expresiones regulares
     **********************/
}