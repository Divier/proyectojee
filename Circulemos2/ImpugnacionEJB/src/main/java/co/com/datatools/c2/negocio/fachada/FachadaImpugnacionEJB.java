package co.com.datatools.c2.negocio.fachada;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.negocio.interfaces.ILImpugnacion;
import co.com.datatools.c2.negocio.interfaces.ILPruebas;

@Stateless(name = "FachadaImpugnacionEJB")
@LocalBean
public class FachadaImpugnacionEJB implements IRFachadaImpugnacion {

    private static final Logger logger = Logger.getLogger(FachadaProcesoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILImpugnacion iLImpugnacion;

    @EJB
    private ILPruebas iLPruebas;

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public void cerrarPruebasJob() {
        logger.debug("FachadaImpugnacionEJB::cerrarPruebasJob()");
        iLPruebas.cerrarPruebasJob();
    }

    public FalloImpugnacionDTO consultarUltimoFalloImpugnacion(Long idProceso) {
        logger.debug("FachadaImpugnacionEJB::consultarUltimoFalloImpugnacion(Long)");
        return iLImpugnacion.consultarUltimoFalloImpugnacion(idProceso);
    }
}