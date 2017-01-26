package co.com.datatools.c2.negocio.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.InconsistenciaDetalleCuotasFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaFinanciacionDTO;
import co.com.datatools.c2.enumeracion.financiacion.EnumErrorInconsistenciaFinanciacion;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRInconsistenciaFinanciacion {

    /**
     * registra la financiacion inconsistente, recibe una lista vacia de Errores de financiacion si los errores no estan en la financiación si no en
     * algunos de sus detalles
     * 
     * @param financiacionInconsistente
     * @param inconsistenciasFinanciacion
     * @return
     */
    public InconsistenciaFinanciacionDTO registrarInconsistenciaFinanciacion(
            InconsistenciaFinanciacionDTO financiacionInconsistente,
            List<EnumErrorInconsistenciaFinanciacion> inconsistenciasFinanciacion);

    /**
     * registra los detalles inconsistentes de una financiación, y sus respectivas inconsistencias, en caso de no recibir los parametros necesarios
     * genera CirculemosIllegalArgumentException, en caso de no recibir una financiación asociada al detalle inconsistente genera
     * CirculemosNegocioException, en caso de recibir una lista vacía de errores genera CirculemosNegocioException
     * 
     * @param inconsistenciasDetalles
     * @throws CirculemosNegocioException
     */
    public void registrarInconsistenciaDetalleFinanciacion(
            Map<InconsistenciaDetalleFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> inconsistenciasDetalles,
            InconsistenciaFinanciacionDTO inconsistenciaFinanciacionDTO) throws CirculemosNegocioException;

    /**
     * registra un detalle de la cuota de la financiación inconsistente, y sus respectivas inconsistencias, en caso de no recivir los parametros
     * necesarios genera IllegalArgumentException,en caso de no recibir una financiación asociada al detalle de la cuota inconsistente genera
     * CirculemosNegocioException, en caso de recibir una lista vacía de errores genera CirculemosNegocioException
     * 
     * @param inconsistenciaDetalleCuotasFinanciacionDTO
     * @param inconsistencias
     * @throws CirculemosNegocioException
     */
    public void registrarInconsistenciaDetalleCuotasFinanciacion(
            Map<InconsistenciaDetalleCuotasFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> inconsistenciasDetalleCuotas,
            InconsistenciaFinanciacionDTO financiacionInconsistente) throws CirculemosNegocioException;
}
