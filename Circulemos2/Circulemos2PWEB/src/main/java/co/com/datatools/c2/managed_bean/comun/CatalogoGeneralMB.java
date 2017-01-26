package co.com.datatools.c2.managed_bean.comun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConsultaOrganismoTransitoDTO;
import co.com.datatools.c2.dto.ProcesoNegocioDTO;
import co.com.datatools.c2.dto.TipoEmailDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.personas.ClaseActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.DivisionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.EstadoCivilDTO;
import co.com.datatools.c2.dto.personas.GrupoActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.NivelEducativoDTO;
import co.com.datatools.c2.dto.personas.SeccionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.personas.TipoSociedadDTO;
import co.com.datatools.c2.dto.personas.TipoViviendaDTO;
import co.com.datatools.c2.enumeracion.EnumGeneroPersona;
import co.com.datatools.c2.enumeracion.EnumMedioIngresoImagenFirma;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRConfiguracionEmail;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

@ManagedBean
@SessionScoped
public class CatalogoGeneralMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(CatalogoGeneralMB.class.getName());

    private final PaisDTO pais;
    private final OrganismoTransitoDTO organismoTransito;

    private int tipoDocumentoEmpresa;

    /**
     * Lista de catalogo de clases de intereses
     */
    // Migrado
    private List<SelectItem> opcClasesInteres;

    /**
     * Lista de catalogos de tipos de email(tipos de comunicacion por correo electronico)
     */
    // Migrado
    private List<SelectItem> opcTiposComunicacionesCorreo;

    /**
     * Lista de catalogo de causales de infraccion
     */
    // Migrado
    private List<SelectItem> opcCausalInfraccion;

    /**
     * Lista de catalogo para normatividad
     */
    // Migrado
    private List<SelectItem> opcNormatividad;

    /**
     * Lista de catalogo para tipos de sanciones
     */
    // Migrado
    private List<SelectItem> opcTipoSancion;

    /**
     * Lista de catalogo para ordenamiento pais
     */
    private List<SelectItem> opcOrdenamientoPais;

    /**
     * lista que almacena los tipos de identificacion
     */
    // Migrado
    private List<SelectItem> opcTipoIdentificacion;

    /**
     * lista que almacena los organismos de transito
     */
    private List<SelectItem> opcOrganismoTransito;

    /**
     * Contiene los generos disponibles
     */
    private Map<String, String> opcGenero;

    /**
     * Contiene todos los paises
     */
    private List<SelectItem> opcPaises;

    /**
     * Contiene los departamentos del pais
     */
    private List<SelectItem> opcDepartamentos;

    /**
     * Contiene los departamentos por pais
     */
    private Map<Integer, List<SelectItem>> opcDepartamentosPorPais;

    /**
     * Contiene todos los municipios del pais
     */
    private List<SelectItem> opcMunicipios;

    /**
     * Contiene los municipios por departamento
     */
    private Map<Integer, List<SelectItem>> opcMunicipiosPorDepartamento;

    /**
     * Contiene todos las localidades de los departamentos
     */
    private List<SelectItem> opcLocalidades;

    /**
     * Contiene las localidades por municipio
     */
    private Map<Integer, List<SelectItem>> opcLocalidadesPorMunicipio;

    /**
     * Contiene los tipos de sociedad
     */
    private List<SelectItem> opcTipoSociedad;

    /**
     * Contiene los estados civiles
     */
    private List<SelectItem> opcEstadoCivil;

    /**
     * Contiene los tipos de vivienda
     */
    private List<SelectItem> opcTipoVivienda;

    /**
     * Contiene los niveles de educacion
     */
    private List<SelectItem> opcNivelEducacion;

    /**
     * Contiene las secciones de actividades economicas del pais
     */
    private List<SelectItem> opcSeccionActEconimica;

    /**
     * Contiene todas las divisiones de actividades economicas
     */
    private List<SelectItem> opcDivisionActEconomica;

    /**
     * Contiene las divisiones de actividades economicas por seccion
     */
    private Map<Integer, List<SelectItem>> opcDivisionPorSeccion;

    /**
     * Contiene todas los grupos de actividades economicas
     */
    private List<SelectItem> opcGrupoActEconomica;

    /**
     * Contiene los grupos de actividades economicas por division
     */
    private Map<Integer, List<SelectItem>> opcGrupoPorDivision;

    /**
     * Contiene todas las clases de actividades economicas
     */
    private List<SelectItem> opcClaseActEconomica;

    /**
     * Contiene las clases de actividades economicas por Grupo
     */
    private Map<Integer, List<SelectItem>> opcClasePorGrupo;

    /**
     * Contiene las areas fisicas existentes por organismo de transito
     */
    private Map<Integer, List<SelectItem>> opcAreaFisicaPorOrganismo;

    /**
     * Contiene los cargos establecidos por organismo de transito
     */
    private Map<Integer, List<SelectItem>> opcCargosPorOrganismo;

    /**
     * Contiene los medios de ingreso posibles de las imagenes de las firmas
     */
    private List<SelectItem> opcMedioIngresoImagenFirma;

    /**
     * almacena el catalogo de codigos de transito disponibles en la BD
     */
    private List<SelectItem> opcCodigosTransito;

    /**
     * Contiene el catalogo de los tipos de concepto cartera
     */
    private List<SelectItem> opcTiposConceptoCartera;

    /**
     * Contiene el catalogo de los tipos de cuenta
     */
    private List<SelectItem> opcTiposCuenta;

    /**
     * Contiene el catalogo de los bancos manejados en el organismo de transito
     */
    // Migrado
    private List<SelectItem> opcBancos;

    /**
     * Contiene los tipos de respues web service disponibles
     */
    private List<SelectItem> opcTiposRespuestaWS;

    /**
     * Contiene los cargos establecidos por organismo de transito
     */
    private Map<Integer, List<SelectItem>> opcRespuestaWSPorTipo;

    /**
     * Contiene las opciones de las entidades a los cuales pertenecen los agentes de transito
     */
    private List<SelectItem> opcEntidadesAgenteTransito;

    /**
     * Contiene el catalogo de los procesos existentes
     */
    private List<SelectItem> opcProcesos;

    @EJB
    private IRAdministracion iRAdministracion;

    @EJB
    private IRConfiguracionEmail iRConfiguracionEmail;

    @EJB
    private IRAdministracionFormularios administracionFormulariosEJB;

    public CatalogoGeneralMB() {
        logger.debug("CatalogoGeneralMB::CatalogoGeneralMB");
        pais = findSessionObject(ConstantesManagedBean.CLASE_OBJ_PAIS, ConstantesManagedBean.NOMBRE_OBJ_PAIS);
        organismoTransito = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
    }

    @PostConstruct
    public void iniciar() {
        final TipoIdentificacionPersonaDTO tipoIdJuridica = this.iRAdministracion
                .obtenerTipoIdentificacionEmpresaJuridicaPais(pais.getId());
        this.tipoDocumentoEmpresa = tipoIdJuridica.getId();

        this.crearOpcionesTipoIdentificacionPersona();
        this.crearOpcionesOrganismoTransito();
        this.crearOpcionesGenero();
        this.crearOpcionesPais();
        this.crearOpcionesDepartamento();
        this.crearOpcionesMunicipio();
        this.crearOpcionesLocalidad();
        this.crearOpcionesTipoSociedad();
        this.crearOpcionesEstadosCiviles();
        this.crearOpcionesTiposVivienda();
        this.crearOpcionesNivelesEducativos();
        this.crearSeccionesActividadEconomica();
        this.crearAreasFisicasOrganismo();
        this.crearCargosOrganismo();
        this.crearMediosIngresoImagenFirma();
        this.crearTiposComunicacionesCorreo();
        this.crearOpcionesProceso();

    }

    /**
     * Inicializa el catalogo de tipos de comunicaciones de correo(tipo_email)
     */
    private void crearTiposComunicacionesCorreo() {
        this.opcTiposComunicacionesCorreo = new ArrayList<>();
        List<TipoEmailDTO> lstTiposComunicacionesCorreo = iRConfiguracionEmail.consultarTiposEmail(organismoTransito
                .getCodigoOrganismo());
        for (TipoEmailDTO tipoEmailDTO : lstTiposComunicacionesCorreo) {
            this.opcTiposComunicacionesCorreo.add(new SelectItem(tipoEmailDTO.getCodigo(), tipoEmailDTO.getNombre()));
        }
    }

    /**
     * Carga las secciones de actividades economicas
     */
    private void crearSeccionesActividadEconomica() {
        List<SeccionActividadEconomicaDTO> lSeccionActividadEconomicaDTO = null;
        this.opcSeccionActEconimica = new ArrayList<>();
        // se inicializan los mapas de division por seccion
        this.opcDivisionActEconomica = new ArrayList<>();
        this.opcDivisionPorSeccion = new HashMap<Integer, List<SelectItem>>();
        // se inicializan los mapas de grupo por division
        this.opcGrupoActEconomica = new ArrayList<>();
        this.opcGrupoPorDivision = new HashMap<Integer, List<SelectItem>>();
        // se inicializan los mapas de clase por grupo
        this.opcClaseActEconomica = new ArrayList<>();
        this.opcClasePorGrupo = new HashMap<Integer, List<SelectItem>>();

        lSeccionActividadEconomicaDTO = this.iRAdministracion.consultarSeccionActividadEconomica(pais.getId());

        if (lSeccionActividadEconomicaDTO != null) {
            for (SeccionActividadEconomicaDTO sae : lSeccionActividadEconomicaDTO) {
                this.opcSeccionActEconimica.add(new SelectItem(sae.getId(), sae.getNombre()));
            }

        }
    }

    /**
     * Carga las opciones de nivel educativo
     */
    public void crearOpcionesNivelesEducativos() {
        this.opcNivelEducacion = new ArrayList<>();
        List<NivelEducativoDTO> lNivelEducativoDTO = this.iRAdministracion.consultarNivelEducativo(this.pais.getId());

        if (lNivelEducativoDTO != null) {
            for (NivelEducativoDTO ne : lNivelEducativoDTO) {
                this.opcNivelEducacion.add(new SelectItem(ne.getCodigo(), ne.getNombre()));
            }

        }

    }

    /**
     * Carga las opciones de Tipos de Vivienda
     */
    public void crearOpcionesTiposVivienda() {
        this.opcTipoVivienda = new ArrayList<>();
        List<TipoViviendaDTO> lTipoViviendaDTO = this.iRAdministracion.consultarTipoVivienda(this.pais.getId());

        if (lTipoViviendaDTO != null) {
            for (TipoViviendaDTO tv : lTipoViviendaDTO) {
                this.opcTipoVivienda.add(new SelectItem(tv.getCodigo(), tv.getNombre()));
            }

        }

    }

    /**
     * Carga los estados civiles disponibles
     */
    public void crearOpcionesEstadosCiviles() {
        this.opcEstadoCivil = new ArrayList<>();
        List<EstadoCivilDTO> lEstadoCivilDTO = this.iRAdministracion.consultarEstadoCivil(this.pais.getId());

        if (lEstadoCivilDTO != null) {
            for (EstadoCivilDTO ec : lEstadoCivilDTO) {
                this.opcEstadoCivil.add(new SelectItem(ec.getId(), ec.getNombre()));
            }

        }

    }

    /**
     * Carga los tipos de sociedades disponibles
     */
    public void crearOpcionesTipoSociedad() {
        this.opcTipoSociedad = new ArrayList<>();
        List<TipoSociedadDTO> lTipoSociedadDTO = this.iRAdministracion.consultarTipoSociedad(pais.getId());

        if (lTipoSociedadDTO != null) {
            for (TipoSociedadDTO ts : lTipoSociedadDTO) {
                this.opcTipoSociedad.add(new SelectItem(ts.getId(), ts.getNombre()));
            }

        }

    }

    /**
     * Inicializa los mapas de municipios en vacio
     */
    private void crearOpcionesMunicipio() {
        this.opcMunicipios = new ArrayList<>();
        this.opcMunicipiosPorDepartamento = new HashMap<Integer, List<SelectItem>>();
    }

    /**
     * Inicializa los mapas de municipios en vacio
     */
    private void crearOpcionesLocalidad() {
        this.opcLocalidades = new ArrayList<>();
        this.opcLocalidadesPorMunicipio = new HashMap<Integer, List<SelectItem>>();
    }

    /**
     * Carga los generos disponibles para las personas
     */
    private void crearOpcionesGenero() {
        this.opcGenero = new HashMap<String, String>();
        for (EnumGeneroPersona egp : EnumGeneroPersona.getGenerosOrdenados()) {
            this.opcGenero.put(egp.getNombre(), egp.getDiminutivo());
        }

    }

    /**
     * Carga todos los paises existentes
     */
    public void crearOpcionesPais() {
        List<PaisDTO> lPaisDTO = iRAdministracion.consultarPais(null);

        this.opcPaises = new ArrayList<>();
        for (PaisDTO paisDTO : lPaisDTO) {
            this.opcPaises.add(new SelectItem(paisDTO.getId(), paisDTO.getNombre()));
        }

    }

    /**
     * Carga los departamentos existentes en el pais
     */
    public void crearOpcionesDepartamento() {
        this.opcDepartamentos = new ArrayList<>();
        this.opcDepartamentosPorPais = new HashMap<Integer, List<SelectItem>>();
    }

    /**
     * Carga todos los procesos como una lista de items
     */
    public void crearOpcionesProceso() {
        List<ProcesoNegocioDTO> lProcesoDTO = this.iRAdministracion.consultarProceso(null);

        this.opcProcesos = new ArrayList<>();
        for (ProcesoNegocioDTO procesoDTO : lProcesoDTO) {
            this.opcProcesos.add(new SelectItem(procesoDTO.getId(), procesoDTO.getNombre()));
        }

    }

    /**
     * se consultan los tipos de identificacion de las personas.
     */
    public void crearOpcionesTipoIdentificacionPersona() {
        List<TipoIdentificacionPersonaDTO> listTipoNotiCompDTO = this.iRAdministracion
                .consultarTipoIdentificacionPersona(pais, null);
        this.opcTipoIdentificacion = new ArrayList<>();

        if (listTipoNotiCompDTO != null) {
            for (TipoIdentificacionPersonaDTO tipDTO : listTipoNotiCompDTO) {
                this.opcTipoIdentificacion.add(new SelectItem(tipDTO.getId(), tipDTO.getNombre()));
            }

        }

    }

    /**
     * se consultan los organismos de transito
     */
    public void crearOpcionesOrganismoTransito() {
        ConsultaOrganismoTransitoDTO consultaOrganismoTransitoDTO = null;
        List<OrganismoTransitoDTO> listTipoNotiCompDTO = this.iRAdministracion
                .consultarOrganismoTransito(consultaOrganismoTransitoDTO);
        this.opcOrganismoTransito = new ArrayList<>();

        for (OrganismoTransitoDTO otDTO : listTipoNotiCompDTO) {
            this.opcOrganismoTransito.add(new SelectItem(otDTO.getCodigoOrganismo(), otDTO.getNombreOrganismo()));
        }

    }

    /**
     * Inicializa las areas fisicas por organismo de transito en vacio
     */
    private void crearAreasFisicasOrganismo() {
        this.opcAreaFisicaPorOrganismo = new HashMap<Integer, List<SelectItem>>();
    }

    /**
     * Inicializa los cargos por organismo de transito en vacio
     */
    private void crearCargosOrganismo() {
        this.opcCargosPorOrganismo = new HashMap<Integer, List<SelectItem>>();
    }

    /**
     * Inicializa los medios de ingreso posibles de las imagenes de las firmas
     */
    private void crearMediosIngresoImagenFirma() {
        this.opcMedioIngresoImagenFirma = new ArrayList<>();

        List<EnumMedioIngresoImagenFirma> medios = EnumMedioIngresoImagenFirma.getMediosIngresoOrdenados();
        for (EnumMedioIngresoImagenFirma medio : medios) {
            this.opcMedioIngresoImagenFirma.add(new SelectItem(medio.getCodigo(), medio.getNombre()));
        }

    }

    public List<SelectItem> getOpcTipoIdentificacion() {
        return this.opcTipoIdentificacion;
    }

    public void setOpcTipoIdentificacion(List<SelectItem> opcTipoIdentificacion) {
        this.opcTipoIdentificacion = opcTipoIdentificacion;
    }

    public List<SelectItem> getOpcOrganismoTransito() {
        return this.opcOrganismoTransito;
    }

    public void setOpcOrganismoTransito(List<SelectItem> opcOrganismoTransito) {
        this.opcOrganismoTransito = opcOrganismoTransito;
    }

    public List<SelectItem> getOpcDepartamentos() {
        return this.opcDepartamentos;
    }

    public List<SelectItem> getOpcPaises() {
        return this.opcPaises;
    }

    /**
     * Carga los departamentos por el pais que se encuentra en sesion
     * 
     * @return Lista departamentos por pais
     */
    public List<SelectItem> cargueDepartamentosPorPaisLocal() {
        return this.cargueDepartamentosPorPais(this.pais.getId());
    }

    /**
     * Carga los departamentos por el pais indicado
     * 
     * @param pais
     *            id del pais al cual pertenecen los municipios a cargar
     * 
     */
    public List<SelectItem> cargueDepartamentosPorPais(Integer pais) {
        List<SelectItem> valor = null;
        List<DepartamentoDTO> lDepartamentosDTO = null;

        if (pais != null) {
            valor = this.opcDepartamentosPorPais.get(pais);

            if (valor == null) {
                lDepartamentosDTO = this.iRAdministracion.consultarDepartamentos(pais);

                if (lDepartamentosDTO != null) {
                    List<SelectItem> deptosPais = new ArrayList<SelectItem>();
                    for (DepartamentoDTO departamentoDTO : lDepartamentosDTO) {
                        // se cargan todos los departamentos
                        this.opcDepartamentos.add(new SelectItem(departamentoDTO.getId(), departamentoDTO.getNombre()));
                        // se agrupan los departamentos por pais
                        deptosPais.add(new SelectItem(departamentoDTO.getId(), departamentoDTO.getNombre()));
                    }

                    this.opcDepartamentosPorPais.put(pais, deptosPais);
                    valor = deptosPais;
                }

            }

        }

        if (valor == null) {
            valor = new ArrayList<SelectItem>();
        }

        return valor;
    }

    /**
     * Carga los municipios por el departamento indicado
     * 
     * @param departamento
     *            id del departamento al cual pertenecen los municipios a cargar
     * 
     */
    public List<SelectItem> cargueMunicipiosPorDepartamento(Integer departamento) {
        List<SelectItem> valor = null;
        List<MunicipioDTO> lMunicipiosDTO = null;

        if (departamento != null) {
            valor = this.opcMunicipiosPorDepartamento.get(departamento);

            if (valor == null) {
                MunicipioDTO munDTO = new MunicipioDTO();
                munDTO.setDepartamento(new DepartamentoDTO(departamento));
                lMunicipiosDTO = this.iRAdministracion.consultarMunicipio(munDTO);

                if (lMunicipiosDTO != null) {
                    List<SelectItem> municipiosDepto = new ArrayList<SelectItem>();
                    for (MunicipioDTO municipioDTO : lMunicipiosDTO) {
                        // se cargan todos los municipios
                        this.opcMunicipios.add(new SelectItem(municipioDTO.getId(), municipioDTO.getNombre()));
                        // se agrupan los municipios por departamento
                        municipiosDepto.add(new SelectItem(municipioDTO.getId(), municipioDTO.getNombre()));
                    }

                    this.opcMunicipiosPorDepartamento.put(departamento, municipiosDepto);
                    valor = municipiosDepto;
                }

            }

        }

        if (valor == null) {
            valor = new ArrayList<SelectItem>();
        }

        return valor;
    }

    /**
     * Carga los municipios por el departamento indicado
     * 
     * @param municipio
     *            id del departamento al cual pertenecen los municipios a cargar
     * 
     */
    public List<SelectItem> cargueLocalidadesPorMunicipio(Integer municipio) {
        List<SelectItem> valor = null;
        List<LocalidadDTO> lLocalidadesDTO = null;

        if (municipio != null) {
            valor = this.opcLocalidadesPorMunicipio.get(municipio);
            if (valor == null) {
                LocalidadDTO locDTO = new LocalidadDTO();
                locDTO.setMunicipio(new MunicipioDTO(municipio));
                lLocalidadesDTO = this.iRAdministracion.consultarLocalidad(locDTO);
                if (lLocalidadesDTO != null) {
                    List<SelectItem> localidadesMunicipio = new ArrayList<>();
                    for (LocalidadDTO localidadDTO : lLocalidadesDTO) {
                        // se cargan todas las localidades
                        this.opcLocalidades.add(new SelectItem(localidadDTO.getId(), localidadDTO.getNombre()));
                        // se agrupan las localidades por municipio
                        localidadesMunicipio.add(new SelectItem(localidadDTO.getId(), localidadDTO.getNombre()));
                    }

                    this.opcLocalidadesPorMunicipio.put(municipio, localidadesMunicipio);
                    valor = localidadesMunicipio;
                }

            }

        }

        if (valor == null) {
            valor = new ArrayList<SelectItem>();
        }

        return valor;
    }

    /**
     * Carga las clases por la seccion indicada
     * 
     * @param seccion
     *            id de la Seccion al cual pertenecen las clases a cargar
     * 
     */
    public List<SelectItem> cargueDivisionPorSeccion(Integer seccion) {
        List<SelectItem> valor = null;
        List<DivisionActividadEconomicaDTO> lDivisionesDTO;

        if (seccion != null) {
            valor = this.opcDivisionPorSeccion.get(seccion);

            if (valor == null) {

                lDivisionesDTO = this.iRAdministracion.consultarDivisionActividadEconomica(seccion);
                if (lDivisionesDTO != null) {
                    List<SelectItem> divisionesSeccion = new ArrayList<>();
                    for (DivisionActividadEconomicaDTO divisionDTO : lDivisionesDTO) {
                        // se cargan todas las clases
                        this.opcDivisionActEconomica.add(new SelectItem(divisionDTO.getId(), divisionDTO.getNombre()));
                        // se agrupan las clases por seccion
                        divisionesSeccion.add(new SelectItem(divisionDTO.getId(), divisionDTO.getNombre()));
                    }

                    this.opcDivisionPorSeccion.put(seccion, divisionesSeccion);
                    valor = divisionesSeccion;
                }

            }

        }

        if (valor == null) {
            valor = new ArrayList<SelectItem>();
        }

        return valor;
    }

    /**
     * Carga los grupos por la division indicada
     * 
     * @param division
     *            id de la Division al cual pertenecen los grupos a cargar
     * 
     */
    public List<SelectItem> cargueGrupoPorDivision(Integer division) {
        List<SelectItem> valor = null;
        List<GrupoActividadEconomicaDTO> lGruposDTO;

        if (division != null) {
            valor = this.opcGrupoPorDivision.get(division);

            if (valor == null) {

                lGruposDTO = this.iRAdministracion.consultarGrupoActividadEconomica(division);
                if (lGruposDTO != null) {
                    List<SelectItem> gruposDivision = new ArrayList<>();
                    for (GrupoActividadEconomicaDTO grupoDTO : lGruposDTO) {
                        // se cargan todos los grupos
                        this.opcGrupoActEconomica.add(new SelectItem(grupoDTO.getId(), grupoDTO.getNombre()));
                        // se agrupan los grupos por division
                        gruposDivision.add(new SelectItem(grupoDTO.getId(), grupoDTO.getNombre()));
                    }

                    this.opcGrupoPorDivision.put(division, gruposDivision);
                    valor = gruposDivision;
                }

            }

        }

        if (valor == null) {
            valor = new ArrayList<SelectItem>();
        }

        return valor;
    }

    /**
     * Carga las clases por el grupo indicado
     * 
     * @param grupo
     *            id del Grupo al cual pertenecen las clases a cargar
     * 
     */
    public List<SelectItem> cargueClasesPorGrupo(Integer grupo) {
        List<SelectItem> valor = null;
        List<ClaseActividadEconomicaDTO> lClasesDTO;

        if (grupo != null) {
            valor = this.opcClasePorGrupo.get(grupo);

            if (valor == null) {

                lClasesDTO = this.iRAdministracion.consultarClaseActividadEconomica(grupo);
                if (lClasesDTO != null) {
                    List<SelectItem> clasesGrupo = new ArrayList<>();
                    for (ClaseActividadEconomicaDTO claseDTO : lClasesDTO) {
                        // se cargan todas las clases
                        this.opcClaseActEconomica.add(new SelectItem(claseDTO.getId(), claseDTO.getNombre()));
                        // se agrupan las clases por grupo
                        clasesGrupo.add(new SelectItem(claseDTO.getId(), claseDTO.getNombre()));
                    }

                    this.opcClasePorGrupo.put(grupo, clasesGrupo);
                    valor = clasesGrupo;
                }

            }

        }

        if (valor == null) {
            valor = new ArrayList<SelectItem>();
        }

        return valor;
    }

    public Map<String, String> getOpcGenero() {
        return this.opcGenero;
    }

    public List<SelectItem> getOpcMunicipios() {
        return this.opcMunicipios;
    }

    public List<SelectItem> getOpcTipoSociedad() {
        return this.opcTipoSociedad;
    }

    public List<SelectItem> getOpcEstadoCivil() {
        return this.opcEstadoCivil;
    }

    public List<SelectItem> getOpcTipoVivienda() {
        return this.opcTipoVivienda;
    }

    public List<SelectItem> getOpcNivelEducacion() {
        return this.opcNivelEducacion;
    }

    /**
     * Retorna todas la secciones de actividades economicas cargadas hasta el momento
     * 
     * @return the opcSeccionActEconimica
     */
    public List<SelectItem> getOpcSeccionActEconimica() {
        return this.opcSeccionActEconimica;
    }

    /**
     * Retorna todas las divisiones de actividades economicas cargadas hasta el momento
     * 
     * @return the opcDivisionActEconomica
     */
    public List<SelectItem> getOpcDivisionActEconomica() {
        return this.opcDivisionActEconomica;
    }

    /**
     * Retorna todos los grupos de actividades economicas cargados hasta el momento
     * 
     * @return the opcGrupoActEconomica
     */
    public List<SelectItem> getOpcGrupoActEconomica() {
        return this.opcGrupoActEconomica;
    }

    /**
     * Retorna todas las clases de actividades economicas cargadas hasta el momento
     * 
     * @return the opcClaseActEconomica
     */
    public List<SelectItem> getOpcClaseActEconomica() {
        return this.opcClaseActEconomica;
    }

    /**
     * Obtiene el nombre de una determianda opcion seleccionada a partir de su id
     * 
     * @param idTipoIdentificacion
     *            id seleccionado desde las opciones de tipo de identificacion (opcTipoIdentificacion)
     * @return nombre del id del tipo de identificacion correspondiente
     * @author luis.forero (15-09-2014)
     */
    public String obtenerNombreOpcTipoIdentificacion(Integer idTipoIdentificacion) {
        String nombreTipo = null;
        for (SelectItem item : opcTipoIdentificacion) {
            if (((Integer) item.getValue()).equals(idTipoIdentificacion)) {
                return item.getLabel();
            }
        }
        return nombreTipo;
    }

    /**
     * Retorna los tipos de documentos disponibles para un persona
     * 
     * @return mapa con las entradas de los documentos disponibles para una persona
     */
    public List<SelectItem> getOpcTipoIdentPersona() {
        List<SelectItem> resp = new ArrayList<>();
        EnumTipoIdentificacion[] tipos = EnumTipoIdentificacion.values();

        for (SelectItem ent : this.opcTipoIdentificacion) {
            boolean valido = false;

            for (int i = 0; i < tipos.length; i++) {
                if ((Integer) ent.getValue() == tipos[i].getValor()) {
                    valido = (tipos[i].isDePersona());
                    break;
                }

            }

            if (valido) {
                resp.add(new SelectItem(ent.getValue(), ent.getLabel()));
            }

        }

        return resp;
    }

    /**
     * Retorna los tipos de documentos disponibles para un conyugue (no puede ser juridico)
     * 
     * @return mapa con las entradas de los documentos disponibles para un conyugue
     */
    public List<SelectItem> getOpcTipoIdentConyugue() {
        List<SelectItem> resp = new ArrayList<>();
        EnumTipoIdentificacion[] tipos = EnumTipoIdentificacion.values();

        for (SelectItem ent : this.opcTipoIdentificacion) {
            if ((Integer) ent.getValue() != this.tipoDocumentoEmpresa) {
                boolean valido = false;

                for (int i = 0; i < tipos.length; i++) {
                    if (((Integer) ent.getValue()).intValue() == tipos[i].getValor()) {
                        valido = (tipos[i].isDePersona());
                        break;
                    }

                }

                if (valido) {
                    resp.add(new SelectItem(ent.getValue(), ent.getLabel()));
                }

            }

        }

        return resp;
    }

    /**
     * Retorna los tipos de documentos disponibles para un representante legal (no puede ser juridico)
     * 
     * @return mapa con las entradas de los documentos disponibles para un representante legal
     */
    public List<SelectItem> getOpcTipoIdentRepresLegal() {
        List<SelectItem> resp = new ArrayList<>();
        EnumTipoIdentificacion[] tipos = EnumTipoIdentificacion.values();

        for (SelectItem ent : this.opcTipoIdentificacion) {
            if ((Integer) ent.getValue() != this.tipoDocumentoEmpresa) {
                boolean valido = false;

                for (int i = 0; i < tipos.length; i++) {
                    if (((Integer) ent.getValue()).intValue() == tipos[i].getValor()) {
                        valido = (tipos[i].isDeRepresentanteLegal());
                        break;
                    }

                }

                if (valido) {
                    resp.add(new SelectItem(ent.getValue(), ent.getLabel()));
                }

            }

        }

        return resp;
    }

    public List<SelectItem> getOpcMedioIngresoImagenFirma() {
        return this.opcMedioIngresoImagenFirma;
    }

    /**
     * Retorna los tipos de documentos disponibles para un representante legal (no puede ser juridico)
     * 
     * @return mapa con las entradas de los documentos disponibles para un representante legal
     */
    public List<SelectItem> getOpcTipoIdentFuncionario() {
        List<SelectItem> resp = new ArrayList<>();
        EnumTipoIdentificacion[] tipos = EnumTipoIdentificacion.values();

        for (SelectItem ent : this.opcTipoIdentificacion) {
            if ((Integer) ent.getValue() != this.tipoDocumentoEmpresa) {
                boolean valido = false;

                for (int i = 0; i < tipos.length; i++) {
                    if (((Integer) ent.getValue()).intValue() == tipos[i].getValor()) {
                        valido = (tipos[i].isDeFuncionario());
                        break;
                    }

                }

                if (valido) {
                    resp.add(new SelectItem(ent.getValue(), ent.getLabel()));
                }

            }

        }

        return resp;
    }

    public List<SelectItem> getOpcCodigosTransito() {
        return opcCodigosTransito;
    }

    public void setOpcCodigosTransito(List<SelectItem> opcCodigosTransito) {
        this.opcCodigosTransito = opcCodigosTransito;
    }

    public List<SelectItem> getOpcTiposConceptoCartera() {
        return opcTiposConceptoCartera;
    }

    public void setOpcTiposConceptoCartera(List<SelectItem> opcTiposConceptoCartera) {
        this.opcTiposConceptoCartera = opcTiposConceptoCartera;
    }

    public List<SelectItem> getOpcTiposCuenta() {
        return opcTiposCuenta;
    }

    public void setOpcTiposCuenta(List<SelectItem> opcTiposCuenta) {
        this.opcTiposCuenta = opcTiposCuenta;
    }

    public List<SelectItem> getOpcBancos() {
        return opcBancos;
    }

    public void setOpcBancos(List<SelectItem> opcBancos) {
        this.opcBancos = opcBancos;
    }

    public List<SelectItem> getOpcTiposRespuestaWS() {
        return this.opcTiposRespuestaWS;
    }

    public void setOpcTiposRespuestaWS(List<SelectItem> opcTiposRespuestaWS) {
        this.opcTiposRespuestaWS = opcTiposRespuestaWS;
    }

    public List<SelectItem> getOpcEntidadesAgenteTransito() {
        return this.opcEntidadesAgenteTransito;
    }

    public void setOpcEntidadesAgenteTransito(List<SelectItem> opcEntidadesAgenteTransito) {
        this.opcEntidadesAgenteTransito = opcEntidadesAgenteTransito;
    }

    public List<SelectItem> getOpcCausalInfraccion() {
        return opcCausalInfraccion;
    }

    public void setOpcCausalInfraccion(List<SelectItem> opcCausalInfraccion) {
        this.opcCausalInfraccion = opcCausalInfraccion;
    }

    public List<SelectItem> getOpcNormatividad() {
        return opcNormatividad;
    }

    public void setOpcNormatividad(List<SelectItem> opcNormatividad) {
        this.opcNormatividad = opcNormatividad;
    }

    public List<SelectItem> getOpcTipoSancion() {
        return opcTipoSancion;
    }

    public void setOpcTipoSancion(List<SelectItem> opcTipoSancion) {
        this.opcTipoSancion = opcTipoSancion;
    }

    public List<SelectItem> getOpcOrdenamientoPais() {
        return opcOrdenamientoPais;
    }

    public void setOpcOrdenamientoPais(List<SelectItem> opcOrdenamientoPais) {
        this.opcOrdenamientoPais = opcOrdenamientoPais;
    }

    public int getTipoDocumentoEmpresa() {
        return this.tipoDocumentoEmpresa;
    }

    public List<SelectItem> getOpcTiposComunicacionesCorreo() {
        return opcTiposComunicacionesCorreo;
    }

    public void setOpcTiposComunicacionesCorreo(List<SelectItem> opcTiposComunicacionesCorreo) {
        this.opcTiposComunicacionesCorreo = opcTiposComunicacionesCorreo;
    }

    public List<SelectItem> getOpcProcesos() {
        return this.opcProcesos;
    }

    public List<SelectItem> getOpcClasesInteres() {
        return opcClasesInteres;
    }

    public void setOpcClasesInteres(List<SelectItem> opcClasesInteres) {
        this.opcClasesInteres = opcClasesInteres;
    }

}
