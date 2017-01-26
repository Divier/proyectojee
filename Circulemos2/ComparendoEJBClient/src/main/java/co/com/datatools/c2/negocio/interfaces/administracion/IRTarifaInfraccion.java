package co.com.datatools.c2.negocio.interfaces.administracion;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * @author rodrigo.cruz
 * 
 */
@Remote
public interface IRTarifaInfraccion {

    /**
     * Consulta la tarifa confirmada de la infraccion por el id de la infraccion y la fecha de imposicion del comparendo
     * 
     * @param pIdInfraccion
     *            ID de la infraccion
     * @param pFechaImposicion
     *            Fecha de imposicio del comparendo
     * @return Una tarifa de infraccion que cumple con los criterios de busqueda
     * @author pedro.moncada giovanni.velandia(mod 24-11-2015)
     */
    public TarifaInfraccionDTO consultarTarifaInfraccion(Integer pIdInfraccion, Date pFechaImposicion);

    /**
     * Calcula el valor asociado a la infracción en la fecha indicada acorde a los parámetros recibidos en la liquidacion de tarifa.
     * 
     * @param liquidarTarifaInfraccionDTO
     * @return Retorna tarifa liquidacion con el valor acorde a la generación.
     * @author giovanni.velandia
     */
    public TarifaLiquidacionDTO liquidarTarifaInfraccion(LiquidarTarifaInfraccionDTO liquidarTarifaInfraccionDTO)
            throws CirculemosNegocioException;

    /**
     * Consulta la tarifa confirmada de la infraccion por filtros
     * 
     * @param pIdInfraccion
     *            ID de la infraccion
     * @param pPorcentaje
     *            Porcentaje de descuento
     * @param pFechaImposicion
     *            Fecha de imposicion del comparendo
     * @return Una tarifa de infraccion que cumple con los criterios de busqueda.
     * @author pedro.moncada
     */
    public TarifaInfraccionDTO consultarTarifaInfraccion(int pIdInfraccion, BigDecimal pPorcentaje,
            Date pFechaImposicion);
}
