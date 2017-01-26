package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;

/**
 * Interface de servicios de integracion a terceros para financiaciones
 * 
 * @author dixon.alvarez
 * 
 */
@Local
public interface IRFinanciacionIntegracion {

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
}
