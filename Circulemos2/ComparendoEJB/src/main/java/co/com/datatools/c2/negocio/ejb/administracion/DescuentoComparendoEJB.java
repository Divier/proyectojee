package co.com.datatools.c2.negocio.ejb.administracion;

import static co.com.datatools.c2.util.Utilidades.safeList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comparendo.ConfiguracionDescuentoDTO;
import co.com.datatools.c2.entidades.ConfiguracionDescuento;
import co.com.datatools.c2.entidades.DetalleDescuento;
import co.com.datatools.c2.enumeracion.EnumTipoDescuento;
import co.com.datatools.c2.negocio.helpers.comparendos.ConfiguracionDescuentoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.DetalleDescuentoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoFechaOrigenHelper;
import co.com.datatools.c2.negocio.interfaces.administracion.ILDescuentoComparendo;
import co.com.datatools.c2.negocio.interfaces.administracion.IRDescuentoComparendo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;

/**
 * Session Bean implementation class DescuentoComparendoEJB
 * 
 * @author divier.casas
 */
@Stateless(name = "DescuentoComparendoEJB")
@LocalBean
public class DescuentoComparendoEJB implements IRDescuentoComparendo, ILDescuentoComparendo {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRCatalogo iRCatalogo;

    private static final Logger logger = Logger.getLogger(DescuentoComparendoEJB.class.getName());

    @Override
    public ConfiguracionDescuentoDTO consultarConfiguracionDescuento(Integer codigoOrganismo,
            Integer codigoMedioImposicion, Integer codigoTipoNotificacion, Date fechaImposicionComparendo) {

        logger.debug("DescuentoComparendoEJB.consultarConfiguracionDescuento(Integer,Integer,Integer,Date)");

        ConfiguracionDescuentoDTO configuracionDescuentoDTO = null;
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT dd FROM ConfiguracionDescuento AS cd");
        final Map<String, Object> mapParam = new HashMap<>();

        if (codigoMedioImposicion != null && fechaImposicionComparendo != null && codigoTipoNotificacion != null) {
            sql.append(" JOIN cd.organismoTransito AS ot");
            sql.append(" JOIN cd.medioImposicionList AS mi");
            sql.append(" JOIN cd.detalleDescuentoList AS dd");
            sql.append(" JOIN cd.tipoNotificacionList AS tn");
        } else {
            return null;
        }

        sql.append(" WHERE");
        sql.append(" cd.tipoDescuento.id = :tipoDescuento");
        sql.append(" AND cd.tipoDescuento.estado = TRUE");
        mapParam.put("tipoDescuento", EnumTipoDescuento.CONFIG_POR_NOTIFICACION_COMPARENDO.getValue());

        sql.append(" AND ot.codigoOrganismo = :orgTransito");
        mapParam.put("orgTransito", codigoOrganismo);

        sql.append(" AND mi.id = :codigoMedioImposicion");
        mapParam.put("codigoMedioImposicion", codigoMedioImposicion);

        sql.append(" AND tn.id = :idTipoNotificacion");
        mapParam.put("idTipoNotificacion", codigoTipoNotificacion);

        sql.append(" AND (:fechaImposicion BETWEEN cd.fechaVigenciaInicio AND cd.fechaVigenciaFin");
        sql.append(" OR :fechaImposicion >= cd.fechaVigenciaInicio AND cd.fechaVigenciaFin IS NULL)");
        mapParam.put("fechaImposicion", fechaImposicionComparendo);

        final TypedQuery<DetalleDescuento> query = em.createQuery(sql.toString(), DetalleDescuento.class);

        for (Entry<String, Object> entry : mapParam.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        final List<DetalleDescuento> lDetalleDescuento = safeList(query.getResultList());

        if (!lDetalleDescuento.isEmpty()) {
            DetalleDescuento detalleDescuento = lDetalleDescuento.get(0);
            ConfiguracionDescuento configuracionDescuento = detalleDescuento.getConfiguracionDescuento();
            configuracionDescuentoDTO = ConfiguracionDescuentoHelper.toLevel1DTO(configuracionDescuento);
            configuracionDescuentoDTO
                    .setDetalleDescuentoList(DetalleDescuentoHelper.toListLevel1DTO(lDetalleDescuento));
            configuracionDescuentoDTO
                    .setTipoFechaOrigen(TipoFechaOrigenHelper.toLevel1DTO(configuracionDescuento.getTipoFechaOrigen()));
        }
        return configuracionDescuentoDTO;
    }
}