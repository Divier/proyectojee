package co.com.datatools.c2.negocio.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import co.com.datatools.c2.dto.InconsistenciaDetalleCuotasFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaFinanciacionDTO;
import co.com.datatools.c2.enumeracion.financiacion.EnumErrorInconsistenciaFinanciacion;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILInconsistenciaFinanciacion {

    /**
     * @see IRInconsistenciaFinanciacion#registrarInconsistenciaFinanciacion(InconsistenciaFinanciacionDTO,List<EnumErrorInconsistenciaFinanciacion>)
     */
    public InconsistenciaFinanciacionDTO registrarInconsistenciaFinanciacion(
            InconsistenciaFinanciacionDTO financiacionInconsistente,
            List<EnumErrorInconsistenciaFinanciacion> inconsistenciasFinanciacion);

    /**
     * 
     * @see IRInconsistenciaFinanciacion#registrarInconsistenciaDetalleFinanciacion(Map<InconsistenciaDetalleFinanciacionDTO,
     *      List<EnumErrorInconsistenciaFinanciacion>>)
     */
    public void registrarInconsistenciaDetalleFinanciacion(
            Map<InconsistenciaDetalleFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> inconsistenciasDetalles,
            InconsistenciaFinanciacionDTO inconsistenciaFinanciacionDTO) throws CirculemosNegocioException;

    /**
     * 
     * @see IRInconsistenciaFinanciacion#registrarInconsistenciaDetalleCuotasFinanciacion(InconsistenciaDetalleCuotasFinanciacionDTO,List<
     *      ErrorFinanciacionDTO>)
     */
    public void registrarInconsistenciaDetalleCuotasFinanciacion(
            Map<InconsistenciaDetalleCuotasFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> inconsistenciasDetalleCuotas,
            InconsistenciaFinanciacionDTO financiacionInconsistente) throws CirculemosNegocioException;

}
