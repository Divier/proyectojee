package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ConfiguracionEmailDTO;
import co.com.datatools.c2.dto.CorreoEnvioDTO;
import co.com.datatools.c2.dto.TipoEmailDTO;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRConfiguracionEmail {

    /**
     * Permite consultar una configuracion por estado de la configuracion para un organismo de transito. Llama el metodo consultarConfiguracionEmail
     * (tipoCorreo: EnumTipoCorreo, estadoConfiguracion: Boolean, codigoOrganismo: int) con tipoCorreo en null.
     * 
     * @param estadoConfiguracion
     *            Estado True para activos, false para inactivos
     * @return listado de configuraciones de email
     * @author luis.forero
     */
    public List<ConfiguracionEmailDTO> consultarConfiguracionEmail(Boolean estadoConfiguracion);

    /**
     * Permite consultar una configuracion por tipo de mail para un organismo de transito.
     * 
     * <pre>
     * El unico dato obligatorio es el codigo de organismo de
     * transito, los demas pueden ser nulos.
     * </pre>
     * 
     * @param codigoOrganismo
     *            Codigo del organismo a consultar
     * @param enumTipoCorreo
     * @param estadoConfiguracion
     * @return Retorna la lista de configuraciones de email para un organismo de transito y/o los filtros ingresados.
     * @author luis.forero
     */
    public List<ConfiguracionEmailDTO> consultarConfiguracionEmail(int codigoOrganismo, EnumTipoCorreo enumTipoCorreo,
            Boolean estadoConfiguracion);

    /**
     * Permite consultar los tipos de email asociados a un organismo de transito.
     * 
     * <pre>
     * Codigo de organismo obligatorio
     * </pre>
     * 
     * @param codigoOrganismo
     *            Codigo del organismo a consultar
     * @return Listado de tipos de email configurados para el organismo de transito
     * @author luis.forero
     */
    public List<TipoEmailDTO> consultarTiposEmail(int codigoOrganismo);

    /**
     * Permite eliminar la configuracion de mail. Elimina la entidad segun el id encontrado en el objeto configuracionEmail
     * 
     * @param configuracionEmailDTO
     *            Objeto con el id del registro a eliminar
     * @throws CirculemosNegocioException
     * @author luis.forero
     */
    public void eliminarConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO)
            throws CirculemosNegocioException;

    /**
     * Permite modificar la configuracion de mail. Modifica la entidad segun el id encontrado en el objeto configuracionEmail
     * 
     * @param configuracionEmailDTO
     *            Objeto que contiene los datos de modificacion y su respectivo id
     * @throws CirculemosNegocioException
     * @author luis.forero
     */
    public void modificarConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO)
            throws CirculemosNegocioException;

    /**
     * Permite registrar una configuracion para un organismo de transito.
     * 
     * <pre>
     * El organismo de transito esta interno en el objeto configuracioEmail. Si el
     * organismo no esta definido debe lanzar una excepcion de argumentos invalidos.
     * </pre>
     * 
     * @param configuracionEmailDTO
     *            Objeto con los datos de la configuracion de email a ingresar
     * @return Retorna el id del registro ingresado
     * @throws CirculemosNegocioException
     * @author luis.forero
     */
    public int registrarConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO)
            throws CirculemosNegocioException;

    /**
     * Consulta los emails existentes para configuraciones que contengan la cadena recibida por parametro.
     * 
     * @param email
     *            Cadena para encontrar email.
     * @param codOrganismo
     *            Organismo para el que esta registrado el correo
     * 
     * @return Si el parametro es null o vacio retorna todos los emails
     * @author luis.forero
     */
    public List<CorreoEnvioDTO> consultarEmails(String email, int codOrganismo);

    /**
     * Consulta el listado de CorreoEnvioDTO asociados al organismo y la configuración indicada.
     * 
     * @param codigoOrganismo
     * @param idConfiguracion
     * @return Retorna el listado de CorreoEnvioDTO asociados al organismo y la configuración indicada.
     * @author julio.pinzon
     */
    public CorreoEnvioDTO consultarEmailsConfigurados(int codigoOrganismo, Integer idConfiguracion);
}
