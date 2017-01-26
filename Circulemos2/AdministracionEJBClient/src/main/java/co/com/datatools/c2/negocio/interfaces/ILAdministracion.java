package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

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

@Local
public interface ILAdministracion {

    /**
     * @see IRAdministracion#consultarOrganismoTransito(ConsultaOrganismoTransitoDTO)
     */
    List<OrganismoTransitoDTO> consultarOrganismoTransito(ConsultaOrganismoTransitoDTO consultaOrganismoTransito);

    /**
     * @see IRAdministracion#consultarOrganismoTransito(Integer)
     */
    OrganismoTransitoDTO consultarOrganismoTransito(Integer codigoOrganismo);

    /**
     * @see IRAdministracion#consultarOrganismoTransito(String)
     */
    List<OrganismoTransitoDTO> consultarOrganismoTransito(String codigoOrganismo);

    /**
     * @see IRAdministracion#registrarOrganismoTransito(OrganismoTransitoDTO)
     */
    void registrarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO) throws CirculemosNegocioException;

    /**
     * @see IRAdministracion#actualizarOrganismoTransito(OrganismoTransitoDTO)
     */
    void actualizarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO) throws CirculemosNegocioException;

    /**
     * @see IRAdministracion#eliminarOrganismoTransito(Integer)
     */
    void eliminarOrganismoTransito(Integer codigoOrganismo) throws CirculemosNegocioException;

    /**
     * @see IRAdministracion#consultarTipoIdentificacionPersona(PaisDTO, TipoIdentificacionPersonaDTO)
     */
    List<TipoIdentificacionPersonaDTO> consultarTipoIdentificacionPersona(PaisDTO pPais,
            TipoIdentificacionPersonaDTO pTipIdPerDTO);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarEstadoCivil(int)
     */
    List<EstadoCivilDTO> consultarEstadoCivil(int pais);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarTipoSociedad(int)
     */
    List<TipoSociedadDTO> consultarTipoSociedad(int pais);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarTipoVivienda(int)
     */
    List<TipoViviendaDTO> consultarTipoVivienda(int pais);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarNivelEducativo(int)
     */
    List<NivelEducativoDTO> consultarNivelEducativo(int pais);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarDepartamentos(int)
     */
    List<DepartamentoDTO> consultarDepartamentos(int pais);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#obtenerTipoFuenteInformacionModuloAdministracion(int, int)
     */
    int obtenerTipoFuenteInformacionModuloAdministracion(int pais, int proceso);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#obtenerTipoIdentificacionEmpresaJuridicaPais(int)
     */
    TipoIdentificacionPersonaDTO obtenerTipoIdentificacionEmpresaJuridicaPais(int idPais);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#obtenerEstadosCivilesImpliquenConyugue(int)
     */
    List<Integer> obtenerEstadosCivilesImpliquenConyugue(int pais);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarSeccionActividadEconomica(int)
     */
    List<SeccionActividadEconomicaDTO> consultarSeccionActividadEconomica(int pais);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarDivisionActividadEconomica(int)
     */
    List<DivisionActividadEconomicaDTO> consultarDivisionActividadEconomica(int seccion);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarGrupoActividadEconomica(int)
     */
    List<GrupoActividadEconomicaDTO> consultarGrupoActividadEconomica(int division);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarClaseActividadEconomica(int)
     */
    List<ClaseActividadEconomicaDTO> consultarClaseActividadEconomica(int grupo);

    /**
     * @see IRAdministracion#existeActividad
     */
    boolean existeActividad(ActividadDTO actividadDTO, Integer codigoOrganismo);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRAdministracion#consultarProceso(ProcesoDTO)
     */
    List<ProcesoNegocioDTO> consultarProceso(ProcesoNegocioDTO proceso);

    /**
     * @see IRAdministracion#consultarTipoVia(Integer)
     */
    List<TipoViaDTO> consultarTipoVia(Integer idPais);

    /**
     * @see IRAdministracion#consultarNombreVia(int, int)
     */
    List<NombreViaDTO> consultarNombreVia(Integer idMunicipio, Integer codTipoVia);

    /**
     * @see IRAdministracion#consultarCardinalidadDireccion(int)
     */
    List<CardinalidadDireccionDTO> consultarCardinalidadDireccion(Integer idPais);

    /**
     * @see IRAdministracion#consultarPais(PaisDTO)
     */
    List<PaisDTO> consultarPais(PaisDTO paisDTO);

    /**
     * @see IRAdministracion#consultarDepartamento(DepartamentoDTO)
     */
    List<DepartamentoDTO> consultarDepartamento(DepartamentoDTO departamentoDTO);

    /**
     * @see IRAdministracion#consultarMunicipio(MunicipioDTO)
     */
    List<MunicipioDTO> consultarMunicipio(MunicipioDTO municipioDTO);

    /**
     * @see IRAdministracion#consultarLocalidad(LocalidadDTO)
     */
    List<LocalidadDTO> consultarLocalidad(LocalidadDTO localidadDTO);

    /**
     * @see IRAdministracion#consultarOrganismosAsociados(int)
     */
    List<OrganismoTransitoDTO> consultarOrganismosAsociados(int codigoOrganismo);

    /**
     * @see IRAdministracion#validarNumeroDocumento(String, Integer, Date)
     */
    public boolean validarNumeroDocumento(String numeroDocumento, Integer tipoDocumento, Date fechaVigencia)
            throws CirculemosNegocioException;

    /**
     * 
     * @see IRAdministracion#consultarFormatosDocumento()
     */
    public List<ItemCatalogoDTO> consultarFormatosDocumento();

    /**
     * 
     * @see IRAdministracion#consultarOrganismosTransito()
     */
    public List<ItemCatalogoDTO> consultarOrganismosTransito();

    /**
     * @return {@link IRAdministracion#consultarOrganismoTransito(OrganismoTransitoDTO)}
     */
    public List<OrganismoTransitoDTO> consultarOrganismoTransito(OrganismoTransitoDTO organismoTransitoDTO);

    /**
     * @see IRAdministracion#consultarConfiguracionEntidad(ConfiguracionEntidadDTO)
     */
    public List<ConfiguracionEntidadDTO> consultarConfiguracionEntidad(ConfiguracionEntidadDTO configuracionEntidadDTO);

    /**
     * @see IRAdministracion#consultarConfiguracionEntidad(Long)
     */
    public ConfiguracionEntidadDTO consultarConfiguracionEntidad(Long idConfiguracionEntidad);

    /**
     * @see IRAdministracion#registrarConfiguracionEntidad(ConfiguracionEntidadDTO)
     */
    public void registrarConfiguracionEntidad(ConfiguracionEntidadDTO configuracionEntidadDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRAdministracion#actualizarConfiguracionEntidad(ConfiguracionEntidadDTO)
     */
    public void actualizarConfiguracionEntidad(ConfiguracionEntidadDTO configuracionEntidadDTO);

    /**
     * @see IRAdministracion#consultarMunicipio(int)
     */
    public MunicipioDTO consultarMunicipio(int idMunicipio);

    /**
     * @see IRAdministracion#consultarConfiguracionEntidad(EntidadOficioDTO)
     */
    public ConfiguracionEntidadDTO consultarConfiguracionEntidad(EntidadOficioDTO entidadOficioDTO);
}
