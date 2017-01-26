package co.com.datatools.c2.negocio.fachada;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.dto.multas.ItMultaDTO;
import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.dto.ubicabilidad.RespuestaSolicitudUbicabilidadTerceroDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Fachada de servicios de integracion a terceros
 * 
 * @author julio.pinzon (2016-05-03)
 * 
 */
@Remote
public interface IRFachadaIntegracionTerceros {

    /**
     * Metodo encargado de consultar los recaudos nuevos en las tablas intermedias
     * 
     * @param codigoOrganismo
     *            Codigo del organismo
     * @param estadoLectura
     *            Estado de la lectura que se quiere consultar
     * @return Lista de recaudos no leidos
     * @author julio.pinzon (2016-05-04)
     */
    public List<ItRecaudoDTO> consultarRecaudos(int codigoOrganismo, EnumEstadoLectura estadoLectura);

    /**
     * Metodo encargado de actualizar los recaudos enviados a su estado no recibido o recibido
     * 
     * @param idRecaudo
     *            Identificador del recaudo a actualizar
     * @param estadoLectura
     *            Estado de la lectura que se quiere actualizar
     * @author julio.pinzon (2016-05-04)
     */
    public void actualizarEstadoRecaudo(Long idRecaudo, EnumEstadoLectura estadoLectura);

    /**
     * Consulta los comparendos que se encuentran en un estado determinado.
     * 
     * @param codigoOrganismo
     *            codigo del organismo de transito del cual se quiere consultar los datos
     * @param estadoLectura
     *            estado en el cual se desean consultar las multas o comparendos. NUEVO, PROCESADO, NO PROCESADO
     * @return Listado de multas pertenecientes al organismo de transito que se encuentran en un determinado estado de lectura.
     * @author luis.forero(2016-05-06)
     */
    List<ItMultaDTO> consultarMultas(int codigoOrganismo, EnumEstadoLectura estadoLectura);

    /**
     * Actualiza los datos de multa o comparendo
     * 
     * @param idMulta
     *            identificador unico de la multa a la cual se desea cambiar el estado
     * @param estadoLectura
     *            estado en el cual se requiere persistir
     * @author luis.forero(2016-05-06)
     */
    void actualizarEstadoMulta(long idMulta, EnumEstadoLectura estadoLectura);

    /**
     * Se encarga de consultar las financiaciones en las tablas intermedias
     * 
     * @param codigoOrganismo
     *            Codigo del organismo
     * @param estadoLectura
     *            Estado de la lectura por el que se filtrara la consulta
     * @return Lista de financiaciones para el organismo y estado indicados
     * @author dixon.alvarez
     */
    List<ItFinanciacionDTO> consultarFinanciaciones(Integer codigoOrganismo, EnumEstadoLectura estadoLectura);

    /**
     * Se encarga de actualizar las financiaciones al estado enviado como parametro
     * 
     * @param numeroFinanciacion
     *            Numero de la financiacion a actualizar
     * @param estadoLectura
     *            Estado de lectura en el que quedara la financiacion
     * @author dixon.alvarez
     */
    void actualizarEstadoFinanciacion(Long numeroFinanciacion, EnumEstadoLectura estadoLectura);

    /**
     * Consulta de registros de ubicabilidad de un tercero.
     * 
     * @return respuesta con las cantidades de registros leidos, recibidos y no recibidos.
     * @throws CirculemosNegocioException
     *             si alguna regla del negocio no se cumple
     * @author divier.casas(2016-05-17)
     */
    public RespuestaSolicitudUbicabilidadTerceroDTO solicitarUbicabilidadTerceros(int codigoOrganismo);

    /**
     * Lleva a cabo la carga de evidencias desde la BD llamando un procedimiento almacenado.
     */
    void cargarEvidenciasComparendosTerceros();
}
