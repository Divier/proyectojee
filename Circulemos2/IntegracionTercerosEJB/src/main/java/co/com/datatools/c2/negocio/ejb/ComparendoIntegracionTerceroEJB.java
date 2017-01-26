package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.multas.ItMultaDTO;
import co.com.datatools.c2.entidades.multas.ItMulta;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.negocio.helpers.ItMultaHelper;
import co.com.datatools.c2.negocio.interfaces.ILComparendoIntegracionTercero;
import co.com.datatools.c2.negocio.interfaces.IRComparendoIntegracionTercero;

/**
 * @see IRComparendoIntegracionTercero
 * @author luis.forero(2016-05-05)
 */
@Stateless(name = "ComparendoIntegracionTerceroEJB")
@LocalBean
public class ComparendoIntegracionTerceroEJB implements IRComparendoIntegracionTercero, ILComparendoIntegracionTercero {

    private final static Logger logger = Logger.getLogger(RecaudoIntegracionEJB.class.getName());

    @PersistenceContext(unitName = "IntegracionTercerosC2JPA")
    private EntityManager em;

    @Override
    public List<ItMultaDTO> consultarMultas(int codigoOrganismo, EnumEstadoLectura estadoLectura) {
        logger.debug(
                ComparendoIntegracionTerceroEJB.class.getName().concat("::consultarMultas(int,EnumEstadoLectura)"));
        List<ItMultaDTO> lstMultas = new ArrayList<ItMultaDTO>(1);

        TypedQuery<ItMulta> query = em.createNamedQuery(ItMulta.SQ_FIND_BY_COD_ORG_AND_EST_LECT, ItMulta.class);
        query.setParameter("pCodOrg", codigoOrganismo);
        query.setParameter("pEstLec", estadoLectura.getValue());

        List<ItMulta> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            lstMultas = ItMultaHelper.toListLevel1DTO(resultList);
        }

        return lstMultas;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarEstadoMulta(long idMulta, EnumEstadoLectura estadoLectura) {
        logger.debug(ComparendoIntegracionTerceroEJB.class.getName()
                .concat("::actualizarEstadoMulta(long,EnumEstadoLectura)"));
        ItMulta itMulta = em.find(ItMulta.class, idMulta);
        if (itMulta != null) {
            itMulta.setEstadoLectura(estadoLectura.getValue());
            em.merge(itMulta);
        }
    }

    @Override
    public void cargarEvidenciasComparendosTerceros() {
        logger.debug(ComparendoIntegracionTerceroEJB.class.getName().concat("::cargarEvidenciasComparnedosTerceros()"));
        Query query = em.createNativeQuery("EXEC [dbo].cargaEvidenciasComparendos");
        query.executeUpdate();
    }

}
