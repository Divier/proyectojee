package co.com.datatools.c2.negocio.interfaces;

import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Local;

import co.com.datatools.c2.dt.administracion.PersonaUbicabilidadDTO;
import co.com.datatools.c2.dto.ConsultaAnalisisUbicResulDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.personas.ArchivoPersonaDTO;
import co.com.datatools.c2.dto.personas.DatosUbicabilidadDTO;
import co.com.datatools.c2.dto.personas.ParentescoPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaHistoricoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Local
public interface ILPersona {

    /**
     * @see IRPersona#consultarDireccionPersona(PersonaDTO)
     */
    public List<DireccionPersonaDTO> consultarDireccionPersona(PersonaDTO personaDTO);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRPersona#consultarPersona(int, int, String)
     */
    PersonaDTO consultarPersona(int codigoOrganismo, int tipoIdentificacion, String numeroIdentificacion);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRPersona#consultarPersona(long)
     */
    PersonaDTO consultarPersona(long idPersona);

    /**
     * @see IRPersona#consultarPersonas(PersonaDTO)
     */
    List<PersonaDTO> consultarPersonas(PersonaDTO personaDTO);

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRPersona#registrarPersona(PersonaDTO, Boolean...)
     */
    public Long registrarPersona(PersonaDTO personaDTO, Boolean... sinHistorico) throws CirculemosNegocioException;

    /**
     * @see co.com.datatools.c2.negocio.interfaces.IRPersona#modificarPersona(PersonaDTO)
     */
    public boolean modificarPersona(PersonaDTO personaDTO, DatosUbicabilidadDTO datosUbicabilidadDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRPersona#consultarParentescoPersona(ParentescoPersonaDTO)
     */
    List<ParentescoPersonaDTO> consultarParentescoPersona(ParentescoPersonaDTO parentescoPersonaDTO);

    /**
     * @see IRPersona#consultarPersonaFuenteExterna(int, String, int)
     */
    PersonaDTO consultarPersonaFuenteExterna(int idTipoIdentificacion, String numeroIdentificacion,
            int codigoOrganismo);

    /**
     * @see IRPersona#consultarRepresentastesLegales(Long)
     */
    List<RepresentanteLegalDTO> consultarRepresentastesLegales(Long idPersonaJuridica);

    /**
     * @see IRPersona#consultarPersonaConPrioridad(int, int, String)
     */
    PersonaDTO consultarPersonaConPrioridad(int codigoOrganismo, int tipoIdentificacion, String numeroIdentificacion)
            throws CirculemosNegocioException;

    /**
     * @see IRPersona#modificarCabeceraPersona(PersonaDTO)
     */
    boolean modificarCabeceraPersona(PersonaDTO personaDTO);

    /**
     * @see IRPersona#consultarHistoricoPersona(PersonaHistoricoDTO)
     */
    public List<PersonaHistoricoDTO> consultarHistoricoPersona(PersonaHistoricoDTO persona);

    /**
     * @see IRPersona#guardarPersonaConDocumento(PersonaDTO)
     */
    public PersonaDTO guardarPersonaConDocumento(PersonaUbicabilidadDTO personaUbicabilidadDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRPersona#consultarPersona(Integer, String)
     */
    public PersonaDTO consultarPersona(Integer tipoIdentificacion, String numeroIdentificacion);

    /**
     * @see IRPersona#validarAnalisisUbicabilidad(ArchivoTransportableDTO)
     */
    public CargueArchivoDTO validarAnalisisUbicabilidad(ArchivoTransportableDTO archivoConsulta)
            throws CirculemosNegocioException;

    /**
     * @see IRPersona#consultarAnalisisUbicabilidad(ArchivoTransportableDTO, CargueArchivoDTO)
     */
    public Future<ConsultaAnalisisUbicResulDTO> consultarAnalisisUbicabilidad(ArchivoTransportableDTO archivoConsulta,
            CargueArchivoDTO cargueArchivo, boolean... camposConsulta) throws CirculemosNegocioException;

    /**
     * @see IRPersona#consultarDireccionPersonaHistorico(DireccionPersonaHistoricoDTO)
     */
    public List<DireccionPersonaHistoricoDTO> consultarDireccionPersonaHistorico(
            DireccionPersonaHistoricoDTO direccionPersonaHistoricoDTO);

    /**
     * @see IRPersona#consultarTelefonoPersonaHistorico(TelefonoPersonaHistoricoDTO)
     */
    public List<TelefonoPersonaHistoricoDTO> consultarTelefonoPersonaHistorico(
            TelefonoPersonaHistoricoDTO telefonoPersonaHistoricoDTO);

    /**
     * @see IRPersona#consultarCorreoPersonaHistorico(CorreoPersonaHistoricoDTO)
     */
    public List<CorreoPersonaHistoricoDTO> consultarCorreoPersonaHistorico(
            CorreoPersonaHistoricoDTO correoPersonaHistoricoDTO);

    /**
     * @see IRPersona#consultarArchivosPersona(ArchivoPersonaDTO, boolean)
     */
    public List<ArchivoPersonaDTO> consultarArchivosPersona(ArchivoPersonaDTO archivoPersonaDTO,
            boolean soloDocumentos);

    /**
     * @see IRPersona#registrarArchivoPersona(ArchivoPersonaDTO, ArchivoTransportableDTO, boolean)
     */
    public ArchivoPersonaDTO registrarArchivoPersona(ArchivoPersonaDTO archivoPersonaDTO,
            ArchivoTransportableDTO archivoTransportableDTO, boolean noUbicabilidad) throws CirculemosNegocioException;

    /**
     * @see IRPersona#enviarCorreoUbicabilidad(PersonaDTO)
     */
    public void enviarCorreoUbicabilidad(PersonaDTO personaDTO) throws CirculemosNegocioException;
}