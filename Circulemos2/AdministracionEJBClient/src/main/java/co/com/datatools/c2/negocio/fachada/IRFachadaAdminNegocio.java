package co.com.datatools.c2.negocio.fachada;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.CorreoEnvioDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.ILPersona;

@Remote
public interface IRFachadaAdminNegocio {

    /**
     * Retorna el listado de PersonaDTO que satisfagan los filtros indicados. Se debe tener en cuenta que puede ser una PersonaJuridicaDTO la enviada.
     * Tambien se debe tener en cuenta que se puede filtrar solo por numero y tipo de documento o por filtros adicionales y distintos a este. Si es
     * PersonaFacadeDTO (la cual hereda de PersonaJuridicaDTO) se deben tener en cuenta los filtros.
     * 
     * @param personaDTO
     *            contiene los filtros para realizar la consulta, si el <b>id</b> es diferente de null trae la persona por id
     * @return lista con los resultados de la busqueda, vacio si no encuentra resultados, la lista puede contener objetos q hereden de
     *         {@link PersonaDTO}
     * @author luis.forero (2015-08-20)
     */
    List<PersonaDTO> consultarPersona(PersonaDTO personaDTO);

    /**
     * Retorna el listado de organismos de transito acorde al filtro establecido por el parametro que representa un Organismo de Transito. Se debe
     * tener en cuenta que puede llegar una instancia de OrganismoTransitoDTO o de OrganismoTransitoFacadeDTO.
     * 
     * @param organismoTransito
     * @return Lista de organismos de transito "OrganismosTransito".
     * @author luis.forero (2015-08-21)
     */
    List<OrganismoTransitoDTO> consultarOrganismoTransito(OrganismoTransitoDTO organismoTransito);

    /**
     * Permite registrar una persona en el sistema con fuente de informacion Circulemos 2. Invoca el servicio de negocio de
     * {@link ILPersona#registrarPersona(PersonaDTO)}
     * 
     * @param personaDTO
     * @return
     * @throws CirculemosNegocioException
     */
    Long registrarPersona(PersonaDTO personaDTO) throws CirculemosNegocioException;

    /**
     * Retorna la persona (natural o juridica) perteneciente al organismo de transito indicado, con el tipo y numero de identificacion indicados.
     * 
     * @param organismoTransito
     * @param tipoDocumento
     * @param numeroDocumento
     * @return {@link PersonaDTO}
     * @throws CirculemosNegocioException
     */
    PersonaDTO consultarPersona(int organismoTransito, int tipoDocumento, String numeroDocumento)
            throws CirculemosNegocioException;

    /**
     * Se encarga de consultar los tipos de documento juridicos por pais
     * 
     * @param idPais
     * @return
     * @author giovanni.velandia
     */
    public TipoIdentificacionPersonaDTO consultarTipoIdentificacionPersonaJuridica(int idPais);

    /**
     * Consulta el listado de correos electrónicos asociados a las configuraciones con las características recibidas en los filtros.
     * 
     * @param codigoOrganismo
     * @param activo
     * @param tipoEmail
     * @return Retorna el listado de correos electrónicos asociados a las configuraciones
     * @author julio.pinzon
     */
    public List<CorreoEnvioDTO> consultarEmailsConfigurados(int codigoOrganismo, Boolean activo,
            EnumTipoCorreo tipoEmail);

    /**
     * Modifica la persona indicada retornando true si la modificación se realizó satisfactoriamente
     * 
     * @param personaDTO
     *            contiene la información de la persona a modificar
     * @return true si la actualización se realizó exitosamente
     * @throws CirculemosNegocioException
     *             si alguna regla del negocio no se cumple al realizar la actualización
     */
    boolean modificarPersona(PersonaDTO personaDTO) throws CirculemosNegocioException;

    /**
     * Permite modificar la informacion de cabecera de la persona indicada
     * 
     * @param personaDTO
     *            contiene la información de la persona a modificar
     * @return true si la actualización se realizó exitosamente
     * @author divier.casas
     */
    boolean modificarCabeceraPersona(PersonaDTO personaDTO);

    /**
     * Retorna la informacion completa de una persona a partir del id
     * 
     * @param idPersona
     *            id de base de datos de la persona a consultar
     * @return Persona asociada a los parámetros recibidos o null si no existe
     * @author divier.casas
     */
    PersonaDTO consultarPersona(long idPersona);

    /**
     * Retorna la persona (natural o juridica) con el tipo y numero de identificacion indicados.
     * 
     * @author giovanni.velandia
     * @param tipoIdentificacion
     * @param numeroIdentificacion
     * @return
     */
    public PersonaDTO consultarPersona(Integer tipoIdentificacion, String numeroIdentificacion);

    /**
     * Permite consultar todos los correos asociados a una persona que son notificables
     * 
     * @param idPersona
     * @return
     * @author divier.casas
     */
    List<CorreoPersonaDTO> consultarCorreosNotificables(Long idPersona);

    /**
     * Se encarga de traer el listado de las direcciones de una persona
     * 
     * @author giovanni.velandia
     * @param direccionPersonaDTO
     * @return
     */
    public List<DireccionPersonaDTO> consultarDireccionPersona(DireccionPersonaDTO direccionPersonaDTO);

    /**
     * Cierra el cargue activo de un archivo al sistema
     * 
     * @param cargueArchivoDTO
     * @author manuel.chavarro 2016-11-25
     */
    public void cerrarCargueArchivo(CargueArchivoDTO cargueArchivoDTO);

    /**
     * Registra avance de cargue masivo
     * 
     * @param cargueArchivo
     * @author manuel.chavarro 2016-11-25
     */
    public void registrarRegistroProcesado(CargueArchivoDTO cargueArchivo);

    /**
     * Registra un documento resultado del cargue de un archivo
     * 
     * @param documentoResulDTO
     * @author manuel.chavarro 2016-11-25
     */
    public DocumentoResultadoCargueDTO registrarDocumentoResulCargue(DocumentoResultadoCargueDTO documentoResulDTO);

    /**
     * consulta los correos asociados a una persona
     * 
     * @param correoPersonaDTO
     * @return
     */
    public abstract List<CorreoPersonaDTO> consultarCorreoPersona(CorreoPersonaDTO correoPersonaDTO);

    /**
     * consulta los telefonos asociados a una persona
     * 
     * @param telefonoPersonaDTO
     * @return
     */
    public abstract List<TelefonoPersonaDTO> consultarTelefonoPersona(TelefonoPersonaDTO telefonoPersonaDTO);
}