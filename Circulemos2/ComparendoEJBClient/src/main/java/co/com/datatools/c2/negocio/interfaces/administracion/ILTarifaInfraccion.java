package co.com.datatools.c2.negocio.interfaces.administracion;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILTarifaInfraccion {

    /**
     * @see IRTarifaInfraccion#consultarTarifaInfraccion
     */
    public TarifaInfraccionDTO consultarTarifaInfraccion(Integer pIdInfraccion, Date pFechaImposicion);

    /**
     * 
     * @see IRTarifaInfraccion#liquidarTarifaInfraccion(LiquidarTarifaInfraccionDTO)
     */
    public TarifaLiquidacionDTO liquidarTarifaInfraccion(LiquidarTarifaInfraccionDTO liquidarTarifaInfraccionDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRTarifaInfraccion#consultarTarifaInfraccion(int, BigDecimal, Date)
     */
    public TarifaInfraccionDTO consultarTarifaInfraccion(int pIdInfraccion, BigDecimal pPorcentaje,
            Date pFechaImposicion);
}
