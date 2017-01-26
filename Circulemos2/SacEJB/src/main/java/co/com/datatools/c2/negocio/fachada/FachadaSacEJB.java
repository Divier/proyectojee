package co.com.datatools.c2.negocio.fachada;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.RespuestaProcesoSacDTO;
import co.com.datatools.c2.dto.RespuestaUbicabilidadSacDTO;
import co.com.datatools.c2.dto.UbicabilidadSacDTO;
import co.com.datatools.c2.negocio.fachada.interfaces.IRFachadaSac;
import co.com.datatools.c2.negocio.interfaces.IRGestionCobro;
import co.com.datatools.c2.negocio.interfaces.IRRegistroSac;
import co.com.datatools.c2.negocio.interfaces.IRSac;

/**
 * Session Bean implementation class FachadaSacEJB
 */
@Stateless(mappedName = "FachadaSacEJB")
@LocalBean
public class FachadaSacEJB implements IRFachadaSac {

    private final static Logger logger = Logger.getLogger(FachadaSacEJB.class.getName());

    @EJB
    private IRSac iSAC;
    @EJB
    private IRRegistroSac iRegistroSAC;
    @EJB
    private IRGestionCobro iGestionCobro;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaUbicabilidadSacDTO consultarUbicabilidadSac(int codigoOrganismo) {
        logger.debug("FachadaSacEJB.consultarUbicabilidadSac(int)");
        return iSAC.consultarUbicabilidadSac(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaProcesoSacDTO replicarNovedadesSac(int codigoOrganismo) {
        logger.debug("FachadaSacEJB.replicarNovedadesSac(int)");
        return iSAC.replicarNovedadesSac(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public UbicabilidadSacDTO replicarUbicabilidadSac(UbicabilidadSacDTO ubicabilidadSacDTO) {
        logger.debug("FachadaSacEJB.replicarUbicabilidadSac(UbicabilidadSacDTO)");
        return iRegistroSAC.replicarUbicabilidadSac(ubicabilidadSacDTO);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void registrarUbicabilidadReplicada(int codigoOrganismo, UbicabilidadSacDTO ubicabilidadSacDTO,
            Long idPersonaHistorico) {
        logger.debug("FachadaSacEJB.registrarUbicabilidadReplicada(int, UbicabilidadSacDTO)");
        iSAC.registrarUbicabilidadReplicada(codigoOrganismo, ubicabilidadSacDTO, idPersonaHistorico);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaProcesoSacDTO replicarObligacionesSac(int codigoOrganismo) {
        logger.debug("FachadaSacEJB.replicarObligacionSac(ObligacionSacDTO)");
        return iSAC.replicarObligacionesSac(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void actualizarGestionCobroTimer(Integer codigoOrganismo) {
        logger.debug("FachadaSacEJB.actualizarGestionCobroTimer()");
        iGestionCobro.actualizarGestionCobroTimer(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaProcesoSacDTO actualizarObligacionesFinanciadas(int codigoOrganismo) {
        logger.debug("FachadaSacEJB.actualizarObligacionesFinanciadas(int)");
        return iSAC.actualizarObligacionesFinanciadas(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void replicarEvidenciasSac() {
        logger.debug("FachadaSacEJB.replicarEvidenciasSac()");
        iRegistroSAC.replicarEvidenciasSac();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaProcesoSacDTO recibirGestionCobroSac(int codigoOrganismo) {
        logger.debug("FachadaSacEJB.recibirGestionCobroSac(int)");
        return iSAC.recibirGestionCobroSac(codigoOrganismo);
    }

}
