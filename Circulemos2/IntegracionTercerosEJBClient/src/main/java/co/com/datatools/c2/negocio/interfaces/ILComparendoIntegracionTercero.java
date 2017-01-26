package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.multas.ItMultaDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;

/**
 * Interface que permite llevar a cabo la logica relacionada con las multas o comparendos.
 * 
 * @author luis.forero(2016-05-05)
 */
@Local
public interface ILComparendoIntegracionTercero {

    /**
     * @see IRComparendoIntegracionTercero#consultarMultas(int, EnumEstadoLectura)
     */
    List<ItMultaDTO> consultarMultas(int codigoOrganismo, EnumEstadoLectura estadoLectura);

    /**
     * @see IRComparendoIntegracionTercero#actualizarEstadoMulta(long, EnumEstadoLectura)
     */
    void actualizarEstadoMulta(long idMulta, EnumEstadoLectura estadoLectura);

    /**
     * @see IRComparendoIntegracionTercero#cargarEvidenciasComparendosTerceros()
     */
    void cargarEvidenciasComparendosTerceros();

}
