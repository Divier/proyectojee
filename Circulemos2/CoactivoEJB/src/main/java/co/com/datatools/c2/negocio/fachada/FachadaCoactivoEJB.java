package co.com.datatools.c2.negocio.fachada;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.negocio.interfaces.ILCoactivo;

@Stateless(name = "FachadaCoactivoEJB")
@LocalBean
public class FachadaCoactivoEJB implements IRFachadaCoactivo {

    private static final Logger logger = Logger.getLogger(FachadaCoactivoEJB.class.getName());

    @EJB
    private ILCoactivo iLCoactivo;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer registrarCoactivoJob(Integer codigoOrganismo) {
        logger.debug("FachadaCoactivoEJB::registrarCoactivoJob(Integer)");
        return iLCoactivo.registrarCoactivoJob(codigoOrganismo);
    }

    public CoactivoDTO consultarCoactivo(String numeroCoactivo) {
        logger.debug("FachadaFinanciacionEJB.consultarFinanciacion(String)");
        return iLCoactivo.consultarCoactivo(numeroCoactivo);
    }
}