package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;

/**
 * Interface de servicios de integracion a terceros
 * 
 * @author julio.pinzon (2016-05-04)
 * 
 */
@Remote
public interface IRRecaudoIntegracion {

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

}
