package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudComparendosDTO;

/**
 * @see IRComparendoTercero
 * @author luis.forero(2016-05-06)
 * 
 */
@Local
public interface ILComparendoTercero {

    /**
     * @see IRComparendoTercero#solicitarComparendosTerceros()
     */
    RespuestaSolicitudComparendosDTO solicitarComparendosTerceros();

}
