package co.com.datatools.c2.negocio.ejb.administracion;

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;
import co.com.datatools.c2.dto.configuracion.EmbriaguezTarifasDTO;
import co.com.datatools.c2.entidades.TarifaInfraccion;
import co.com.datatools.c2.enumeraciones.EnumConfiguracion;
import co.com.datatools.c2.enumeraciones.EnumTipoServicio;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorComparendo;
import co.com.datatools.c2.negocio.helpers.extencion.TarifaInfraccionHelperExtend;
import co.com.datatools.c2.negocio.interfaces.administracion.ILInfraccion;
import co.com.datatools.c2.negocio.interfaces.administracion.ILTarifaInfraccion;
import co.com.datatools.c2.negocio.interfaces.administracion.IRTarifaInfraccion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;

/**
 * Session Bean implementation class TarifaInfraccionEJB
 * 
 * @author rodrigo.cruz
 */
@Stateless(mappedName = "TarifaInfraccionEJB")
@LocalBean
public class TarifaInfraccionEJB implements IRTarifaInfraccion, ILTarifaInfraccion {

    private final static Logger logger = Logger.getLogger(TarifaInfraccionEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;
    private static BigDecimal GRADO_NIEGA_PRUEBA_ALCOHOLEMIA = new BigDecimal(4);
    private static BigDecimal MAXIMO_NUMERO_REINCIDENCIAS = new BigDecimal(3);
    private static int GRADO_ALCOHOLEMIA_MINIMO = 0;
    private static int GRADO_ALCOHOLEMIA_MAXIMO = 3;

    @EJB
    private IRFachadaConfiguracion iRFachadaConfiguracion;
    @EJB
    private ILInfraccion iLInfraccion;

    @Override
    public TarifaInfraccionDTO consultarTarifaInfraccion(Integer idInfraccion, Date fechaImposicion) {
        logger.debug("TarifaInfraccionEJB::consultarTarifaInfraccion(Integer,Date)");
        TarifaInfraccionDTO tiDTO = null;
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT DISTINCT ti FROM TarifaInfraccion AS ti");
        sql.append(" WHERE ti.infraccion.id = :idInfraccion");
        sql.append(" AND ti.porcentajeDescuento = 0");
        sql.append(" AND ti.fechaInicioVigencia <= :pFechaImposicion");
        sql.append(" AND ( ti.fechaFinalVigencia IS NULL OR ti.fechaFinalVigencia >= :pFechaImposicion )");
        sql.append(" AND ti.tarifaConfirmada = true");

        TypedQuery<TarifaInfraccion> query = em.createQuery(sql.toString(), TarifaInfraccion.class);
        query.setParameter("idInfraccion", idInfraccion);
        query.setParameter("pFechaImposicion", fechaImposicion);

        List<TarifaInfraccion> listTiDTO = query.getResultList();
        if (!listTiDTO.isEmpty()) {
            tiDTO = TarifaInfraccionHelperExtend.toLevel1DTO(listTiDTO.get(0));
        }
        return tiDTO;
    }

    @Override
    public TarifaLiquidacionDTO liquidarTarifaInfraccion(LiquidarTarifaInfraccionDTO liquidarTarifaInfraccionDTO)
            throws CirculemosNegocioException {
        logger.debug("TarifaInfraccionEJB::liquidarTarifaInfraccion(LiquidarTarifaInfraccionDTO)");

        TarifaLiquidacionDTO tarifaLiquidacionDTO = null;

        // Liquidar Tarifa Infraccion
        checkNotNull(liquidarTarifaInfraccionDTO, "LiquidarTarifaInfraccionDTO no puede ser nulo");
        // Fecha liquidacion
        checkNotNull(liquidarTarifaInfraccionDTO.getFechaLiquidacion(), "La fecha de liquidacion no puede ser nula");
        // Codigo de infraccion
        checkNotNull(liquidarTarifaInfraccionDTO.getCodigoInfraccion(), "El código de infracción no puede ser nulo");

        ConfiguracionInfraccionDTO configuracionInfraccionDTO = iLInfraccion.consultarInfraccion(
                liquidarTarifaInfraccionDTO.getCodigoInfraccion(), liquidarTarifaInfraccionDTO.getFechaLiquidacion());

        if (configuracionInfraccionDTO == null) {
            throw new CirculemosNegocioException(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048002);
        }

        // Fecha liquidacion - validacion
        if (liquidarTarifaInfraccionDTO.getFechaLiquidacion().after(Calendar.getInstance().getTime())) {
            throw new CirculemosNegocioException(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048001);
        }

        // Requiere datos de embriaguez
        if (configuracionInfraccionDTO.getAplicaEmbriaguez()) {
            // Niega Prueba Alcoholemia
            if (liquidarTarifaInfraccionDTO.getNiegaPruebaAlcoholemia() == null) {
                throw new CirculemosNegocioException(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048007);
            }
            // Clase de Servicio
            if (liquidarTarifaInfraccionDTO.getIdClaseServicio() == null) {
                throw new CirculemosNegocioException(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048003);
            }
            // Grado de alcoholemia
            if (liquidarTarifaInfraccionDTO.getGradoAlcoholemia() == null) {
                if (!liquidarTarifaInfraccionDTO.getNiegaPruebaAlcoholemia()) {
                    throw new CirculemosNegocioException(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048004);
                }
            } else {
                if (liquidarTarifaInfraccionDTO.getGradoAlcoholemia() < GRADO_ALCOHOLEMIA_MINIMO
                        || liquidarTarifaInfraccionDTO.getGradoAlcoholemia() > GRADO_ALCOHOLEMIA_MAXIMO) {
                    throw new CirculemosNegocioException(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048005);
                }
            }
            // Nro. de veces reincidente
            if (liquidarTarifaInfraccionDTO.getNumeroReincidencias() == null) {
                if (!liquidarTarifaInfraccionDTO.getNiegaPruebaAlcoholemia()) {
                    throw new CirculemosNegocioException(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048006);
                }
            }
        }

        // FLUJO BASICO
        if (configuracionInfraccionDTO.getAplicaEmbriaguez()) {
            EmbriaguezTarifasDTO embriaguezTarifasDTO = new EmbriaguezTarifasDTO();
            // Codigo de infraccion
            embriaguezTarifasDTO.setCodigoInfraccion(liquidarTarifaInfraccionDTO.getCodigoInfraccion());
            // Clase del servicio
            List<String> tipoServicio = new ArrayList<String>();

            // Para valores recibidos diferentes a Publico se envia como particular
            if (!liquidarTarifaInfraccionDTO.getIdClaseServicio().equals(EnumTipoServicio.PUBLICO.getValue())) {
                tipoServicio.add(EnumTipoServicio.PARTICULAR.getValue().toString());
            } else {
                tipoServicio.add(liquidarTarifaInfraccionDTO.getIdClaseServicio().toString());
            }
            embriaguezTarifasDTO.setTipoServicio(tipoServicio);
            // Grado de Alcoholemia
            if (liquidarTarifaInfraccionDTO.getNiegaPruebaAlcoholemia()) {
                embriaguezTarifasDTO.setGradoAlcohol(GRADO_NIEGA_PRUEBA_ALCOHOLEMIA);
                embriaguezTarifasDTO.setNumeroReincidencia(MAXIMO_NUMERO_REINCIDENCIAS);
            } else {
                embriaguezTarifasDTO.setGradoAlcohol(new BigDecimal(liquidarTarifaInfraccionDTO.getGradoAlcoholemia()));
                // Nro. de veces reincidente
                if (liquidarTarifaInfraccionDTO.getNumeroReincidencias() > MAXIMO_NUMERO_REINCIDENCIAS.intValue()) {
                    embriaguezTarifasDTO.setNumeroReincidencia(MAXIMO_NUMERO_REINCIDENCIAS);
                } else {
                    embriaguezTarifasDTO.setNumeroReincidencia(new BigDecimal(liquidarTarifaInfraccionDTO
                            .getNumeroReincidencias()));
                }
            }
            // inicioPeriodoFecha
            embriaguezTarifasDTO.setInicioPeriodoFecha(liquidarTarifaInfraccionDTO.getFechaLiquidacion());
            // finPeriodoFecha
            embriaguezTarifasDTO.setFinPeriodoFecha(null);
            embriaguezTarifasDTO = iRFachadaConfiguracion.consultarValorConfiguracion(
                    EnumConfiguracion.EMBRIAGUEZ_TARIFAS.getCodigo(), embriaguezTarifasDTO);
            if (embriaguezTarifasDTO != null) {
                tarifaLiquidacionDTO = new TarifaLiquidacionDTO();
                tarifaLiquidacionDTO.setValorLiquidado(embriaguezTarifasDTO.getTarifa());
            }
        } else {
            TarifaInfraccionDTO tarifaInfraccionDTO = consultarTarifaInfraccion(configuracionInfraccionDTO
                    .getInfraccion().getId(), liquidarTarifaInfraccionDTO.getFechaLiquidacion());
            if (tarifaInfraccionDTO != null) {
                tarifaLiquidacionDTO = new TarifaLiquidacionDTO();
                tarifaLiquidacionDTO.setValorLiquidado(tarifaInfraccionDTO.getValorInfraccion());
            }
        }

        if (tarifaLiquidacionDTO == null) {
            throw new CirculemosNegocioException(ErrorComparendo.EnumLiquidarTarifaComparendo.COM_048008);
        }
        return tarifaLiquidacionDTO;
    }

    @Override
    public TarifaInfraccionDTO consultarTarifaInfraccion(int pIdInfraccion, BigDecimal pPorcentaje,
            Date pFechaImposicion) {
        TarifaInfraccionDTO tiDTO = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT tinf FROM TarifaInfraccion AS tinf");
        sql.append(" WHERE tinf.infraccion.id = :idInfraccion");
        sql.append(" AND tinf.porcentajeDescuento = :porcentajeDescuento");
        sql.append(" AND tinf.fechaInicioVigencia <= :pFechaImposicion");
        sql.append(" AND ( tinf.fechaFinalVigencia IS NULL OR tinf.fechaFinalVigencia >= :pFechaImposicion )");
        sql.append(" AND tinf.tarifaConfirmada = true");

        TypedQuery<TarifaInfraccion> query = em.createQuery(sql.toString(), TarifaInfraccion.class);
        query.setParameter("idInfraccion", pIdInfraccion);
        query.setParameter("porcentajeDescuento", pPorcentaje);
        query.setParameter("pFechaImposicion", pFechaImposicion);

        List<TarifaInfraccion> listTiDTO = query.getResultList();
        if (!listTiDTO.isEmpty()) {
            tiDTO = TarifaInfraccionHelperExtend.toLevel1DTO(listTiDTO.get(0));
        }
        return tiDTO;
    }
}
