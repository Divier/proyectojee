package co.com.datatools.c2.negocio.interfaces;

import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Remote;

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

@Remote
public interface IRPersona {

    /**
     * Se encarga de recuperar las direcciones asociadas a una persona
     * 
     * @author luis.forero
     * @param personaDTO
     *            Persona con el id correspondiente a consultar
     * @return listado de direcciones de la persona
     */
    public List<DireccionPersonaDTO> consultarDireccionPersona(PersonaDTO personaDTO);

    /**
     * Retorna la persona con el tipo de identificación y número de identificación indicados. Esta persona debe estar asociada al organismo con el
     * código indicado
     * 
     * @param codigoOrganismo
     *            código del organismo al cual debe estar asociada la persona
     * @param tipoIdentificacion
     *            contiene el id del tipo de identificación de la persona
     * @param numeroIdentificacion
     *            contiene el númedo de identificación de la persona
     * @return Persona asociada a los parámetros recibidos o null si no existe
     * @author robert.bautista<br>
     *         felipe.martinez 26/06/2014<br>
     *         giovanni.velandia (mod 23-02-2016)
     */
    PersonaDTO consultarPersona(int codigoOrganismo, int tipoIdentificacion, String numeroIdentificacion);

    /**
     * Retorna la informacion completa de una persona a partir del id
     * 
     * @param idPersona
     *            id de base de datos de la persona a consultar
     * @return Persona asociada a los parámetros recibidos o null si no existe
     * @author felipe.martinez
     */
    PersonaDTO consultarPersona(long idPersona);

    /**
     * Consulta las personas de acuerdo a la información que llega en el objeto por parámetro. Debe incluir el código del organismo dentro del DTO
     * 
     * @param personaDTO
     *            contiene los filtros para realizar la consulta, si el <b>id</b> es diferente de null trae la persona por id
     * @return lista con los resultados de la busqueda, vacio si no encuentra resultados, la lista puede contener objetos q hereden de
     *         {@link PersonaDTO}
     * @author robert.bautista<br>
     *         felipe.martinez 26/06/2014
     */
    List<PersonaDTO> consultarPersonas(PersonaDTO personaDTO);

    /**
     * Ingresa la persona indicada al sistema retornando el id asociado a esta persona una vez es persistida
     * 
     * @author robert.bautista
     * @param personaDTO
     *            contiene la información de la persona a ingresar al sistema
     * @param sinHistorico
     *            determina si genera historíco o no
     * @return Long con el id asociado a la persona luego de haberla persistido
     * @throws CirculemosNegocioException
     *             si alguna regla del negocio no se cumple
     */
    public Long registrarPersona(PersonaDTO personaDTO, Boolean... sinHistorico) throws CirculemosNegocioException;

    /**
     * Modifica la persona indicada retornando true si la modificación se realizó satisfactoriamente
     * 
     * @param personaDTO
     *            contiene la información de la persona a modificar
     * @return true si la actualización se realizó exitosamente
     * @throws CirculemosNegocioException
     *             si alguna regla del negocio no se cumple al realizar la actualización
     */
    public boolean modificarPersona(PersonaDTO personaDTO, DatosUbicabilidadDTO datosUbicabilidadDTO)
            throws CirculemosNegocioException;

    /**
     * Método que consulta los parentescos de una persona que cumplen con los filtros recibidos por parámetros.
     * 
     * @param parentescoPersonaDTO
     *            contiene los filtros para realizar la consulta, requiere el id de la persona principal y opcional el tipo de parentesco
     * @return lista de parentescos de una persona, vacio si no encuentra parientes de acuerdo a las restricciones
     * @author robert.bautista<br>
     *         felipe.martinez
     */
    List<ParentescoPersonaDTO> consultarParentescoPersona(ParentescoPersonaDTO parentescoPersonaDTO);

    /**
     * Permite validar si una persona existe registrada en el sistema
     * 
     * @param codigoOrganismo
     *            codigo de organismo de la persona a buscar
     * @param IdTipoIdentificacion
     *            id del tipo de identificacion de la persona a buscar
     * @param numeroIdentificacion
     *            numero de identificacion de la persona a buscar
     * @return true: si existe un persona con las tres condiciones, false: no existe
     * @author felipe.martinez
     */
    boolean existePersona(int codigoOrganismo, int IdTipoIdentificacion, String numeroIdentificacion);

    /**
     * Se encarga de consultar la informacion de la persona de acuerdo al tipo de identificacion y numero. Se encarga de llamar a los metodos de
     * consulta de orden de fuente de informacion en los parametros del sistema y obtener el origen de datos que se considera actualizado
     * 
     * @param idTipoIdentificacion
     *            Codigo del catalogo de tipo de identificacion
     * @param numeroIdentificacion
     *            Numero de identificacion de la persona a consultar
     * @param codigoOrganismo
     *            Codigo del organismo de transito desde el cual se hace la consulta para obtener el parametro de 'Prioridad de busqueda persona'
     * @return persona fuente externa representada como una PersonaDTO en dado caso de encontrarla dentro de persona_fuente_externa, null si no fue
     *         encontrada la persona
     * @author luis.forero
     */
    PersonaDTO consultarPersonaFuenteExterna(int idTipoIdentificacion, String numeroIdentificacion,
            int codigoOrganismo);

    /**
     * Consulta los representantes legales asosiados a una personaJuridica
     * 
     * @param idPersonaJuridica
     *            identificador de la persona juridica
     * @return Listado de representantes legales asociados a la persona juridica, vacia si no encuentra datos, null safe
     * @author dixon.alvarez
     */
    List<RepresentanteLegalDTO> consultarRepresentastesLegales(Long idPersonaJuridica);

    /**
     * Consulta la persona realizando la busqueda teniendo en cuent alas prioridades de las fuentes de informacio
     * 
     * @param codigoOrganismo
     *            codigo del organismo de transito sobre el cual se consulta
     * @param tipoIdentificacion
     *            tipo de identificacion de la persona
     * @param numeroIdentificacion
     *            numero de identificacion de la persona
     * @return Retorna la persona del organismo de transito indicado, que tenga el tipo y numero de identificacion indicados. La busqueda la realiza
     *         teniendo en cuenta Prioridades de fuentes de informacion.
     * @throws CirculemosNegocioException
     * @author luis.forero(2015-10-08)
     */
    PersonaDTO consultarPersonaConPrioridad(int codigoOrganismo, int tipoIdentificacion, String numeroIdentificacion)
            throws CirculemosNegocioException;

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
     * Permite consultar el historico de una persona
     * 
     * @param persona
     * @return
     */
    public List<PersonaHistoricoDTO> consultarHistoricoPersona(PersonaHistoricoDTO persona);

    /**
     * Guarda la informacion de persona
     * 
     * @param persona
     * @return
     * @throws CirculemosNegocioException
     * @autor diego.fonseca
     */
    public PersonaDTO guardarPersonaConDocumento(PersonaUbicabilidadDTO personaUbicabilidadDTO)
            throws CirculemosNegocioException;

    /**
     * cosnulta la persona solo sus datos basicos
     * 
     * @param tipoIdentificacion
     * @param numeroIdentificacion
     * @return
     * @author giovanni.velandia
     */
    public PersonaDTO consultarPersona(Integer tipoIdentificacion, String numeroIdentificacion);

    /**
     * Consulta los datos de ubicabilidad de una o varias personas a partir de un archivo CSV con los documentos a consultar (HU_CIR20_DAT_UBI_001)
     * 
     * @param archivoConsulta
     *            archivo CSV cargado con los documentos a consultar
     * @param camposConsulta
     *            informacion a conultar (direccion, telefono, correos)
     */
    public CargueArchivoDTO validarAnalisisUbicabilidad(ArchivoTransportableDTO archivoConsulta)
            throws CirculemosNegocioException;

    /**
     * Realiza la consulta de los documentos contenidos en un archivo CSV con datos de personas (HU_CIR20_DAT_UBI_006)
     * 
     * @param archivoConsulta
     * @param cargueArchivo
     * @return archivo con la informacion de la consulta
     * @throws CirculemosNegocioException
     */
    public Future<ConsultaAnalisisUbicResulDTO> consultarAnalisisUbicabilidad(ArchivoTransportableDTO archivoConsulta,
            CargueArchivoDTO cargueArchivo, boolean... camposConsulta) throws CirculemosNegocioException;

    /**
     * Consultas las direcciones adicionadas o actualizadas en un historico de persona
     * 
     * @param direccionPersonaHistoricoDTO
     *            Contiene los filtros de busqueda
     * @return Lista vacia en caso de que no hayan registros
     */
    public List<DireccionPersonaHistoricoDTO> consultarDireccionPersonaHistorico(
            DireccionPersonaHistoricoDTO direccionPersonaHistoricoDTO);

    /**
     * Consultas los telefonos adicionados o actualizados en un historico de persona
     * 
     * @param telefonoPersonaHistoricoDTO
     *            Contiene los filtros de busqueda
     * @return Lista vacia en caso de que no hayan registros
     */
    public List<TelefonoPersonaHistoricoDTO> consultarTelefonoPersonaHistorico(
            TelefonoPersonaHistoricoDTO telefonoPersonaHistoricoDTO);

    /**
     * Consultas las correos adicionados o actualizados en un historico de persona
     * 
     * @param correoPersonaHistoricoDTO
     *            Contiene los filtros de busqueda
     * @return Lista vacia en caso de que no hayan registros
     */
    public List<CorreoPersonaHistoricoDTO> consultarCorreoPersonaHistorico(
            CorreoPersonaHistoricoDTO correoPersonaHistoricoDTO);

    /**
     * Consulta los archivos relacionados a una persona
     * 
     * @param archivoPersonaDTO
     *            Contiene los filtros de busqueda a aplicar
     * @param soloDocumentos
     *            Indica si se deben consultar solamente los documentos que no son de tipo archivo no ubicabilidad
     * @return Lista vacia en caso de que no encuentre archivos relacionados
     */
    public List<ArchivoPersonaDTO> consultarArchivosPersona(ArchivoPersonaDTO archivoPersonaDTO,
            boolean soloDocumentos);

    /**
     * Guarda un archivo en el repositorio de documentos y lo relaciona en archivo persona
     * 
     * @param archivoPersonaDTO
     *            Contiene la informacion relacionada a la persona y datos de quien lo guarda
     * @param archivoTransportableDTO
     *            Contiene el archivo que se guardara en el respositorio de documentos
     * @param noUbicabilidad
     *            Indica si lo que se va guardar es un archivo de no ubicabilidad
     * @return ArchivoPersonaDTO registrado
     */
    public ArchivoPersonaDTO registrarArchivoPersona(ArchivoPersonaDTO archivoPersonaDTO,
            ArchivoTransportableDTO archivoTransportableDTO, boolean noUbicabilidad) throws CirculemosNegocioException;

    /**
     * Permite llamar al servicio de envio de correos
     * 
     * @param personaDTO
     * @throws CirculemosNegocioException
     */
    public void enviarCorreoUbicabilidad(PersonaDTO personaDTO) throws CirculemosNegocioException;
}