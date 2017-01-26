package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ActividadDTO;
import co.com.datatools.c2.dto.ConfiguracionEntidadDTO;
import co.com.datatools.c2.dto.ConsultaOrganismoTransitoDTO;
import co.com.datatools.c2.dto.ProcesoNegocioDTO;
import co.com.datatools.c2.dto.comun.CardinalidadDireccionDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.EntidadOficioDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.NombreViaDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.personas.ClaseActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.DivisionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.EstadoCivilDTO;
import co.com.datatools.c2.dto.personas.GrupoActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.NivelEducativoDTO;
import co.com.datatools.c2.dto.personas.SeccionActividadEconomicaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.personas.TipoSociedadDTO;
import co.com.datatools.c2.dto.personas.TipoViviendaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRAdministracion {

    /**
     * Este metodo consulta los organismos de transito.
     * 
     * @param consultaOrganismoTransito
     * @return Lista de organismos de transito "OrganismosTransito".
     * @author Ivan.Arteaga<br>
     *         Luis.Forero(mod 2015-01-20)
     */
    List<OrganismoTransitoDTO> consultarOrganismoTransito(ConsultaOrganismoTransitoDTO consultaOrganismoTransito);

    /**
     * Se encarga de consultar un organismo de transito con su información detallada de acuerdo al codigo en el sistema
     * 
     * @param codigoOrganismo
     *            codigo del organismo de transito a consultar
     * @return organismo de transito con su informacion detallada
     * @author luis.forero (2015-01-26)
     */
    OrganismoTransitoDTO consultarOrganismoTransito(Integer codigoOrganismo);

    /**
     * Consulta un listado de organismos de transito que contengan un determinado codigo de organismo.
     * 
     * @param codigoOrganismo
     *            cadena con los digitos del codigo del organismo a consultar
     * @return listado de organismos que contienen los digitos ingresados
     * @author luis.forero(2015-09-04)
     */
    List<OrganismoTransitoDTO> consultarOrganismoTransito(String codigoOrganismo);

    /**
     * Se encarga de registrar un nuevo organismo de transito en el sistema
     * 
     * @param organismoTransitoDTO
     *            estructura que contiene todos los datos y relaciones del organismo que se va a crear
     * @throws CirculemosNegocioException
     * @author luis.forero (2015-01-22)
     */
    void registrarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO) throws CirculemosNegocioException;

    /**
     * Se encarga de llevar a cabo la actualizacion de un organismo de transito en del sistema
     * 
     * @param organismoTransitoDTO
     *            estructura que contiene todos los datos y relaciones del organismo que se va a actualizar
     * @throws CirculemosNegocioException
     * @author luis.forero (2015-01-22)
     */
    void actualizarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO) throws CirculemosNegocioException;

    /**
     * Se encarga de eliminar el organismo de transito asignado al codigo recibido.
     * 
     * @param codigoOrganismo
     *            identificador unicio del organismo de transito a eliminar
     * @throws CirculemosNegocioException
     * @author luis.forero (2015-01-22)
     */
    void eliminarOrganismoTransito(Integer codigoOrganismo) throws CirculemosNegocioException;

    /**
     * Consulta los tipos de identificacion de una persona segun el pais.
     * 
     * @param pPais
     *            El pais con el ID (obligatorio).
     * @param pTipIdPerDTO
     *            Consulta por ID si es enviado.
     * @return Los tipos de identificacion que coinciden segun los parametros de busqueda. Null Safe
     * @author Maria.Torres
     */
    List<TipoIdentificacionPersonaDTO> consultarTipoIdentificacionPersona(PaisDTO pPais,
            TipoIdentificacionPersonaDTO pTipIdPerDTO);

    /**
     * Retorna el listado de los estados civiles de las personas en el pais indicado
     * 
     * @author robert.bautista
     * @param pais
     *            contiene el id del pais del cual se cargan los estados civiles.
     * @return Una Lista DTO de los estado civiles @ si se presenta un eror cargando los estados civiles de la base de datos
     */
    List<EstadoCivilDTO> consultarEstadoCivil(int pais);

    /**
     * Retorna la lista de los tipos de sociedad existentes en el pais indicado
     * 
     * @author robert.bautista
     * @param pais
     *            contiene el id del pais del cual se cargan los tipos de sociedades.
     * @return Una Lista DTO de los estado civiles @ si se presenta un eror cargando los tipos de sociedad de la base de datos
     */
    List<TipoSociedadDTO> consultarTipoSociedad(int pais);

    /**
     * Retorna el listado de Tipos de vivienda existentes en un pais
     * 
     * @param pais
     * @return Una Lista DTO de los tipos de vivienda @ si algun problema se presenta cargando los tipos de vivienda de la base de datos
     * @author robert.bautista
     */
    List<TipoViviendaDTO> consultarTipoVivienda(int pais);

    /**
     * Retorna el listado de niveles educativos existentes en un pais
     * 
     * @param pais
     * @return Una Lista DTO de los niveles educativos @ si algun problema se presenta cargando los niveles educativos de la base de datos
     * @author robert.bautista
     */
    List<NivelEducativoDTO> consultarNivelEducativo(int pais);

    /**
     * Retorna el listado de departamentos existentes en un pais
     * 
     * @param pais
     * @return Una Lista DTO de los departamentos @ si algun problema se presenta cargando los departamentos de la base de datos
     * @author robert.bautista
     */
    List<DepartamentoDTO> consultarDepartamentos(int pais);

    /**
     * REGLA DE NEGOCIO Retorna el tipo de fuente de informacion de la persona a registrar desde el formulario de persona del modulo de persona.
     * 
     * @param pais
     *            id del pais
     * 
     * @param proceso
     *            indica el proceso del cual se busca el id del tipo de informacion
     * 
     * @return id del tipo de fuente de informacion a usar con la persona
     */
    int obtenerTipoFuenteInformacionModuloAdministracion(int pais, int proceso);

    /**
     * REGLA DE NEGOCIO Retorna el tipo de identificacion considerado persona juridica para un pais determinado.
     * 
     * @param idPais
     *            id del pais del cual se consulta el tipo de identificacion de la persona juridica
     * 
     * @return Retorna el id del tipo de identificacion de la persona juridica del pais indicado
     */
    TipoIdentificacionPersonaDTO obtenerTipoIdentificacionEmpresaJuridicaPais(int idPais);

    /**
     * REGLA DE NEGOCIO Retorna el listado de estados civiles que implican manejar un conyugue.
     * 
     * @param pais
     *            contiene el id del pais del cual se cargaran los estados civiles que manejen conyugue
     * 
     * @return List<Integer> con los codigos de los estados civiles que implican un posible conyugue
     */
    List<Integer> obtenerEstadosCivilesImpliquenConyugue(int pais);

    /**
     * Retorna el listado de Secciones de actividades economicas para el pais indicado
     * 
     * @param pais
     *            id del pais del cual se cargaran las secciones
     * 
     * @return List<SeccionActividadEconomicaDTO> del pais indicado
     */
    List<SeccionActividadEconomicaDTO> consultarSeccionActividadEconomica(int pais);

    /**
     * Retorna el listado de Divisiones de actividades economicas para la seccion indicada
     * 
     * @param seccion
     *            id de la seccion de la cual se cargaran las divisiones
     * 
     * @return List<DivisionActividadEconomicaDTO> de la seccion indicada
     */
    List<DivisionActividadEconomicaDTO> consultarDivisionActividadEconomica(int seccion);

    /**
     * Retorna el listado de Grupos de actividades economicas para la division indicada
     * 
     * @param division
     *            id de la division de la cual se cargaran los grupos
     * @return List<GrupoActividadEconomicaDTO> de la division indicada
     */
    List<GrupoActividadEconomicaDTO> consultarGrupoActividadEconomica(int division);

    /**
     * Retorna el listado de Clases de actividades economicas para el grupo indicado
     * 
     * @param grupo
     *            id del grupo del cual se cargaran las clases
     * 
     * @return List<ClaseActividadEconomicaDTO> del grupo indicado
     */
    List<ClaseActividadEconomicaDTO> consultarClaseActividadEconomica(int grupo);

    /**
     * Verifica si existe una actividad en particular junto con el organismo de transito. El organismo de transito es obligatorio. La actividad se
     * consulta por id, codigo proceso y/o codigo actividad. Si se consulta el codigo de actividad se debe acompaniar con el codigo de proceso.
     * 
     * @param actividadDTO
     *            Objeto ActividadDTO que contiene los codigos de proceso y de actividad, no realiza busqueda por nombre o descripcion
     * @param codigoOrganismo
     *            Codigo del organismo de transito
     * @return boolean true si existe, false si no existe
     */
    boolean existeActividad(ActividadDTO actividadDTO, Integer codigoOrganismo);

    /**
     * Retorna el listado de Procesos que cumplen con el filtro dado. Si el proceso es nulo o no tiene filtro, retorna todos los procesos existentes.
     * 
     * @param proceso
     *            contiene los filtros para consultar los procesos a retornar
     * 
     * @return List<ProcesoDTO> con todos los procesos que cumplen con el filtro
     */
    List<ProcesoNegocioDTO> consultarProceso(ProcesoNegocioDTO proceso);

    /**
     * Consulta los tipos de via asociados a un pais.
     * 
     * @param idPais
     * @return Catalogo de Tipo Via
     * @author rodrigo.cruz
     */
    List<TipoViaDTO> consultarTipoVia(Integer idPais);

    /**
     * Permite consultar los nombres de via asociados a un municipio.
     * 
     * @param idMunicipio
     * @return Catalogo de Nombre Via
     * @author rodrigo.cruz
     */
    List<NombreViaDTO> consultarNombreVia(Integer idMunicipio, Integer codTipoVia);

    /**
     * consulta las cardinalidades por pais.
     * 
     * @param idPais
     * @return Catalogo de Cardindalidad de Direccion
     * @author rodrigo.cruz
     */
    List<CardinalidadDireccionDTO> consultarCardinalidadDireccion(Integer idPais);

    /**
     * Consulta la lista de paises del sistema. Si el filtro es nulo retorna todos los paisess
     * 
     * @return Catalogo de Paises
     * @author rodrigo.cruz<br>
     *         luis.forero(mod 2015-12-09)
     */
    List<PaisDTO> consultarPais(PaisDTO paisDTO);

    /**
     * Consulta los departamentos de un pais. Si el filtro es nulo, retorna la lista completa de departamentos.
     * 
     * @param departamentoDTO
     *            contiene los filtros incluido el idPais en su DTO correspondiente
     * @return Catalogo de Departamentos
     * @author rodrigo.cruz<br>
     *         luis.forero(mod 2015-12-09)
     */
    List<DepartamentoDTO> consultarDepartamento(DepartamentoDTO departamentoDTO);

    /**
     * Retorna el listado de Municipios existentes segun el filtro indicado,si el filtro es nulo retorna todos los municipios.
     * 
     * @param municipioDTO
     *            contiene los filtros como puede ser el id del departamento del cual se cargan los municipios
     * @return Una Lista DTO de los municipios @ si algun problema se presenta cargando los Municipios de la base de datos
     * @author robert.bautista<br>
     *         luis.forero(mod 2015-12-09)
     */
    List<MunicipioDTO> consultarMunicipio(MunicipioDTO municipioDTO);

    /**
     * Consulta la lista de localidades de un municipio. Si el filtro es nulo retorna todas las localidades.
     * 
     * 
     * @param localidadDTO
     *            contiene los filtros como es el idMunicipio para filtrar sus respectivas localidades
     * @return Retorna el listado de localidades acorde al filtro indicado
     * @author rodrigo.cruz<br>
     *         luis.forero(mod 2015-12-09)
     */
    List<LocalidadDTO> consultarLocalidad(LocalidadDTO localidadDTO);

    /**
     * Consulta un listado de organismos de transito asociados al codigo de organismo enviado como padre.
     * 
     * @param codigoOrganismo
     *            codigo del organismo a consultar
     * @return listado de organismos asociados de acuerdo con el parametro de entrada
     * @author divier.casas(2015-10-06)
     */
    List<OrganismoTransitoDTO> consultarOrganismosAsociados(int codigoOrganismo);

    /**
     * Valida si el número de documento del tipo de documento es válido acorde a formato y longitudes configuradas.
     * 
     * @param numeroDocumento
     * @param tipoDocumento
     * @param fechaVigencia
     * @return Retorna true si el número de documento del tipo de documento es válido acorde a formato y longitudes configuradas.
     * @author julio.pinzon
     * @throws CirculemosNegocioException
     */
    public boolean validarNumeroDocumento(String numeroDocumento, Integer tipoDocumento, Date fechaVigencia)
            throws CirculemosNegocioException;

    /**
     * Listado de formatos permitidos para los documentos de identidad. Estos formatos son un catálogo contenido en EnumFormatoDocumentoIdentidad.
     * 
     * @return
     * @author giovanni.velandia
     */
    public List<ItemCatalogoDTO> consultarFormatosDocumento();

    /**
     * Permite consultar los organismos de transito <strong>configurables</strong> en el sistema
     * 
     * Un organismo de tránisto configurabkles es aquel que pertenece a un padre, o aquel que no tiene padre pero no tiene hijos
     * 
     * @return List<ItemCatalogoDTO> - lista con los organismos de transito que cumplen con la condicion, formateados al <<super objeto>>
     *         ItemCatalogoDTO
     */
    public List<ItemCatalogoDTO> consultarOrganismosTransito();

    /**
     * Permite la consulta de un organismo de transito por medio de sus campos como medio de filtro
     * 
     * @param organismoTransitoDTO
     * @return
     * @author giovanni.velandia
     */
    public List<OrganismoTransitoDTO> consultarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO);

    /**
     * @author giovanni.velandia
     * @param configuracionEntidadDTO
     * @return
     */
    public List<ConfiguracionEntidadDTO> consultarConfiguracionEntidad(ConfiguracionEntidadDTO configuracionEntidadDTO);

    /**
     * @author giovanni.velandia
     * @param idConfiguracionEntidad
     * @return
     */
    public ConfiguracionEntidadDTO consultarConfiguracionEntidad(Long idConfiguracionEntidad);

    /**
     * @author giovanni.velandia
     * @param configuracionEntidadDTO
     */
    public void registrarConfiguracionEntidad(ConfiguracionEntidadDTO configuracionEntidadDTO)
            throws CirculemosNegocioException;

    /**
     * @author giovanni.velandia
     * @param configuracionEntidadDTO
     */
    public void actualizarConfiguracionEntidad(ConfiguracionEntidadDTO configuracionEntidadDTO);

    /**
     * Consulta la ciudad por id
     * 
     * @author giovanni.velandia
     * @return
     */
    public MunicipioDTO consultarMunicipio(int idMunicipio);

    /**
     * @author giovanni.velandia
     * @param configuracionEntidadDTO
     * @return
     */
    public ConfiguracionEntidadDTO consultarConfiguracionEntidad(EntidadOficioDTO entidadOficioDTO);
}
