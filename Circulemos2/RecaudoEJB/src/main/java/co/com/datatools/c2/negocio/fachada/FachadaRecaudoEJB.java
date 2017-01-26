package co.com.datatools.c2.negocio.fachada;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.RespuestaConciliarPagoDTO;
import co.com.datatools.c2.dto.RespuestaReplicarPagoDTO;
import co.com.datatools.c2.negocio.interfaces.ILInconsistenciaRecaudo;
import co.com.datatools.c2.negocio.interfaces.ILRecaudo;
import co.com.datatools.c2.negocio.interfaces.ILRechazosRecaudo;

@Stateless(name = "FachadaRecaudoEJB")
@LocalBean
public class FachadaRecaudoEJB implements IRFachadaRecaudo {
    private final static Logger logger = Logger.getLogger(FachadaRecaudoEJB.class.getName());

    @EJB
    private ILInconsistenciaRecaudo iLInconsistenciaRecaudo;

    @EJB
    private ILRechazosRecaudo iLRechazosRecaudo;

    @EJB
    private ILRecaudo iLRecaudo;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer enviarRechazosRecaudos(Integer codigoOrganismo) {
        logger.debug("FachadaRecaudoEJB.enviarRechazosRecaudos(Integer)");
        return iLRechazosRecaudo.enviarRechazosRecaudos(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaReplicarPagoDTO replicarPagoTerceros(Integer codigoOrganismo) {
        logger.debug("FachadaRecaudoEJB.replicarPagoTerceros(Integer)");
        return iLRecaudo.replicarPagoTerceros(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer enviarInconsistencias(Integer codigoOrganismo) {
        logger.debug("FachadaRecaudoEJB.enviarInconsistencias()");
        return iLInconsistenciaRecaudo.enviarInconsistencias(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer enviarInconsistenciasConciliacion(Integer codigoOrganismo) {
        logger.debug("FachadaRecaudoEJB.enviarInconsistenciasConciliacion()");
        return iLInconsistenciaRecaudo.enviarInconsistenciasConciliacion(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaConciliarPagoDTO realizarConciliacionRecaudo() {
        logger.debug("FachadaRecaudoEJB.realizarConciliacionRecaudo()");
        return iLRecaudo.realizarConciliacionRecaudo();
    }

}
