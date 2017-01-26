package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudComparendosDTO;

/**
 * Contiene los metodos de negocio para llevar a cabo el procesamiento de los datos de un comparendo desde o para un tercero no propiamente del
 * proyecto de C2
 * 
 * @author Luis Forero (2016-06-05)
 */
@Remote
public interface IRComparendoTercero {

    /**
     * Lleva a cabo la consulta de los comparendos a un tercero identificando todos los estados de lectura igual a nuevo y procesandolos para
     * registrarlos en el sistema de C2. La lectura se lleva a cabo desde una estructura intermedia definida de Integración de Terceros.
     * 
     * @return Retorna el numero de registros leidos, registrados y no registrados.
     * @author luis.forero(2016-06-05)
     */
    RespuestaSolicitudComparendosDTO solicitarComparendosTerceros();

}