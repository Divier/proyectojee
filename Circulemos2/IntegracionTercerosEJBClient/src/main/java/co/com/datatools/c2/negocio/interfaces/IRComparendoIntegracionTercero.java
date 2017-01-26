package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.multas.ItMultaDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;

/**
 * Permite llevar a cabo la logica relacionada con las multas o comparendos.
 * 
 * @author luis.forero(2016-05-05)
 * 
 */
@Remote
public interface IRComparendoIntegracionTercero {

    /**
     * Consulta los comparendos que se encuentran en un estado determinado.
     * 
     * @param codigoOrganismo
     *            codigo del organismo de transito sobre el cual se lleva a cabo la consulta
     * @param estadoLectura
     *            estado de los registros que se desea consultar
     * @return listado de multas para un organismo en un determinado estado de lectura
     * @author luis.forero(2016-05-05)
     */
    List<ItMultaDTO> consultarMultas(int codigoOrganismo, EnumEstadoLectura estadoLectura);

    /**
     * Actualiza los datos de multa o comparendo
     * 
     * @param idMulta
     *            identificador del comparendo o multa que se desea cambiar el estado
     * @param estadoLectura
     *            estado al cual se actualiza el registro
     * @author luis.forero(2016-05-05)
     */
    void actualizarEstadoMulta(long idMulta, EnumEstadoLectura estadoLectura);

    /**
     * Lleva a cabo la carga de evidencias desde la BD llamando un procedimiento almacenado.
     */
    void cargarEvidenciasComparendosTerceros();

}
