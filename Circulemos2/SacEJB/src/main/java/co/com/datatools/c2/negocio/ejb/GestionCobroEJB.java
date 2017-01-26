package co.com.datatools.c2.negocio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.GestionCobroSacDTO;
import co.com.datatools.c2.dto.RespuestaGestionCobroSacDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.entidades.ObligacionSac;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumEstadoTransaccionSac;
import co.com.datatools.c2.enumeraciones.EnumOrigenFacturaSac;
import co.com.datatools.c2.enumeraciones.EnumTipoGestionSac;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.interfaces.ILGestionCobro;
import co.com.datatools.c2.negocio.interfaces.IRGestionCobro;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class GestionCobroEJB
 */
@Stateless(mappedName = "GestionCobroEJB")
@LocalBean
public class GestionCobroEJB implements IRGestionCobro, ILGestionCobro {

    private final static Logger logger = Logger.getLogger(GestionCobroEJB.class.getName());

    @PersistenceContext(unitName = "IntegracionSacC2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminGeneral iFachadaAdminGeneralEJB;

    @Override
    public RespuestaGestionCobroSacDTO registrarGestionCobro(GestionCobroSacDTO gestionCobroSac) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarGestionCobroTimer(Integer codigoOrganismo) {
        logger.debug("GestionCobroEJB.actualizarGestionCobroTimer()");
        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT os from ObligacionSac os");
        jpql.append(" WHERE os.idOrigenFacturaSac = :origenFactura");
        jpql.append(" AND (os.idTipoGestionSac is null OR os.idTipoGestionSac <> :cobranza) ");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("origenFactura", EnumOrigenFacturaSac.PEDESTRE.getValue());
        query.setParameter("cobranza", EnumTipoGestionSac.COBRANZA.getValue());

        List<ObligacionSac> obligaciones = query.getResultList();
        Date fechaCalculada = null;
        Date fechaActual = UtilFecha.resetTime(new Date()).getTime();
        ValorParametroDTO valorParametroDTO = iFachadaAdminGeneralEJB.consultarValorParametro(
                EnumParametro.DIAS_COBRO_COMPARENDO_PEDESTRE, codigoOrganismo, true);
        if (obligaciones != null && !obligaciones.isEmpty()) {
            for (ObligacionSac obligacion : obligaciones) {
                if (obligacion.getFechaFactura() != null) {

                    fechaCalculada = iFachadaAdminGeneralEJB.sumarDias(codigoOrganismo, obligacion.getFechaFactura(),
                            valorParametroDTO.getValorParamInt(), true);
                    if (fechaCalculada.before(fechaActual)) {
                        obligacion.setFechaCambio(new Date());
                        obligacion.setIdTipoGestionSac(EnumTipoGestionSac.COBRANZA.getValue());
                        obligacion.setIdEstadoTransaccionSac(EnumEstadoTransaccionSac.TRANSACCION_NULA.getValue());
                        em.persist(obligacion);
                    }
                }
            }
        }

    }

}
