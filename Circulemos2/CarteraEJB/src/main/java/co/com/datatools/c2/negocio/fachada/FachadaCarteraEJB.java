package co.com.datatools.c2.negocio.fachada;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.CambioEstadoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.MovimientoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.RegistroCarteraDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.cartera.ILCarteraContable;

@Stateless(name = "FachadaCarteraEJB")
@LocalBean
public class FachadaCarteraEJB implements IRFachadaCartera {

    private final static Logger logger = Logger.getLogger(FachadaCarteraEJB.class.getName());

    @EJB
    private ILCarteraContable iLCarteraContable;

    @Override
    public List<ConceptoCarteraDTO> consultarConceptoCartera(ConceptoCarteraDTO conceptoCarteraDTO) {
        logger.debug(FachadaCarteraEJB.class.getName().concat("::consultarConceptoCartera(ConceptoCarteraDTO)"));
        return iLCarteraContable.consultarConceptoCartera(conceptoCarteraDTO);
    }

    @Override
    public Long registrarMovimiento(MovimientoCarteraDTO movimiento) {
        logger.debug("FachadaCarteraEJB::registrarMovimiento(MovimientoCarteraDTO)");
        return iLCarteraContable.registrarMovimiento(movimiento);
    }

    @Override
    public CarteraDTO consultarCartera(CarteraDTO cartera) {
        logger.debug("FachadaCarteraEJB::consultarCartera(CarteraDTO)");
        return iLCarteraContable.consultarCartera(cartera);
    }

    @Override
    public CarteraDTO consultarCartera(long id) {
        logger.debug("FachadaCarteraEJB::consultarCartera(long)");
        return iLCarteraContable.consultarCartera(id);
    }

    @Override
    public Long registrarCartera(RegistroCarteraDTO registroCartera) throws CirculemosNegocioException {
        logger.debug("FachadaCarteraEJB::registrarCartera(RegistroCarteraDTO)");
        return iLCarteraContable.registrarCartera(registroCartera);
    }

    @Override
    public void actualizarEstadoCartera(CambioEstadoCarteraDTO cambioEstadoCartera) throws CirculemosNegocioException {
        logger.debug("FachadaCarteraEJB::actualizarEstadoCartera(CambioEstadoCarteraDTO)");
        iLCarteraContable.actualizarEstadoCartera(cambioEstadoCartera);
    }
}
