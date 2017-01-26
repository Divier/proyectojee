package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ArchivoNotificacionComparendoDTO;
import co.com.datatools.c2.dto.HomologacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.NotificacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.NotificacionSimitDTO;
import co.com.datatools.c2.dto.ValidacionComparendoSimitDTO;
import co.com.datatools.c2.enumeraciones.EnumOpcionGeneracionArchivo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRNotificacionComparendoSimit {

    /**
     * Consulta las notificaciones a Simit realizadas con los filtros indicados. Debe validar que las fechas de consulta no sean nulas y esten
     * ordenadas cronologicamente.
     * 
     * @param codigoOrganismo
     * @param fechaInicial
     * @param fechaFinal
     * @param tipoDocumentoEnvioSimit
     * @return Retorna el listado de notificaciones a Simit realizadas con los filtros indicados.
     * @throws CirculemosNegocioException
     *             Cuando se sobrepasa la cantidad de dias parametrizado en el sistema entre las fechas inicial y final de generacion de archivo
     * @author rodrigo.cruz
     */
    public List<NotificacionSimitDTO> consultarNotificacionesSimit(int codigoOrganismo, Date fechaInicial,
            Date fechaFinal, int tipoDocumentoEnvioSimit) throws CirculemosNegocioException;

    /**
     * Consulta los comparendos que se requieran enviar a SIMIT ya sea por primera vez o por corrección. La generación del archivo a enviar depende
     * del parámetro opciones el cual es de tipo var-args. Si no se envía el parámetro opciones el archivo se genera.
     * 
     * @param codigoOrganismo
     * @param opciones
     * @return Retorna el listado de archivos creados, con nombre y cantidad de registros del archivo para enviar a SIMIT.
     * @author julio.pinzon
     * @throws CirculemosNegocioException
     *             : SIM_006001:NO existen comparendo disponibles para enviar
     * 
     */
    public List<ArchivoNotificacionComparendoDTO> generarNotificacionComparendo(int codigoOrganismo,
            EnumOpcionGeneracionArchivo... opciones) throws CirculemosNegocioException;

    /**
     * Genera y registra el archivo en la ruta parametrizada concatenada a la fecha y hora de generacion. Genera una copia identica del archivo en el
     * repositorio de archivos.
     * 
     * @param codigoOrganismo
     * @throws CirculemosNegocioException
     *             Cuando ocurre una excepcion en la generacion del archivo (ver:
     *             {@link IRNotificacionComparendoSimit#generarNotificacionComparendo(int, EnumOpcionGeneracionArchivo...)})
     * @author rodrigo.cruz
     */
    void registrarArchivoNotificacionComparendo(int codigoOrganismo) throws CirculemosNegocioException;

    /**
     * Genera y registra el archivo en la ruta parametrizada concatenada a la fecha y hora de generación. Genera una copia identica del archivo en el
     * repositorio de archivos.
     * 
     * @param codigoOrganismo
     * @throws CirculemosNegocioException
     * @author rodrigo.cruz
     */
    void registrarArchivoNotificacionComparendoAutomatico(int codigoOrganismo) throws CirculemosNegocioException;

    /**
     * Permite validar y homologar la información del comparendo con las reglas definidas por SIMIT
     * 
     * @param validacionComparendoDTO
     * @return
     */
    HomologacionComparendoSIMITDTO validarComparendo(ValidacionComparendoSimitDTO validacionComparendoDTO)
            throws CirculemosNegocioException;

    /**
     * Notifica un comparendo a SIMIT.
     * 
     * @param notificacion
     *            contiene la informacion basica del comparendo a ser notificado
     * @throws CirculemosNegocioException
     *             SIM_001001(Identificador (cicomparendo) Comparendo por notificar a SIMIT vacio)<br>
     * @return Retorna true si el comparendo recibido se notifica satisfactoriamente a SIMIT.
     * @author luis.forero(2013-03-30)
     */
    boolean notificarComparendo(NotificacionComparendoSIMITDTO notificacion) throws CirculemosNegocioException;

}
