package co.com.datatools.c2.negocio.fachada;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.RespuestaReplicarFinanciacionDTO;
import co.com.datatools.c2.dto.ValidaPagoFinanciacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.ILFinanciacion;

@Stateless(mappedName = "FachadaFinanciacionEJB")
@LocalBean
public class FachadaFinanciacionEJB implements IRFachadaFinanciacion {

    private final static Logger logger = Logger.getLogger(FachadaFinanciacionEJB.class.getName());

    @EJB
    private ILFinanciacion iFinanciacion;

    @Override
    public FinanciacionDTO consultarFinanciacion(String numeroFinanciacion) {
        logger.debug("FachadaFinanciacionEJB.consultarFinanciacion(String)");
        return iFinanciacion.consultarFinanciacion(numeroFinanciacion);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaReplicarFinanciacionDTO replicarFinanciacionTerceros(Integer codigoOrganismo) {
        logger.debug("FachadaFinanciacionEJB.replicarFinanciacionTerceros(Integer)");
        return iFinanciacion.replicarFinanciacionTerceros(codigoOrganismo);
    }

    @Override
    public Long registrarCarteraFinanciacion(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException {
        logger.debug("FachadaFinanciacionEJB.registrarCarteraFinanciacion(FinanciacionDTO)");
        return iFinanciacion.registrarCarteraFinanciacion(financiacionDTO);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ValidaPagoFinanciacionDTO validarPagosFinanciaciones(Integer codigoOrganismo) {
        logger.debug("FachadaFinanciacionEJB::validarPagosFinanciaciones(Integer)");
        return iFinanciacion.validarPagosFinanciaciones(codigoOrganismo);
    }
}