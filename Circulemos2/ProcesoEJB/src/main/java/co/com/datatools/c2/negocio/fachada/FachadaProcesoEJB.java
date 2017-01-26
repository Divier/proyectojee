package co.com.datatools.c2.negocio.fachada;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RegistraProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.negocio.interfaces.ILProceso;

@Stateless(name = "FachadaProcesoEJB")
@LocalBean
public class FachadaProcesoEJB implements IRFachadaProceso {

    private static final Logger logger = Logger.getLogger(FachadaProcesoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILProceso iLProceso;

    @Override
    public ProcesoDTO crearProceso(RegistraProcesoDTO registra) {
        logger.debug("FachadaProcesoEJB::crearProceso(RegistraProcesoDTO)");
        return iLProceso.crearProceso(registra);
    }

    @Override
    public TrazabilidadProcesoDTO actualizarEstadoProceso(Long idProceso, EnumEstadoProceso estado,
            Boolean cerrarProceso) {
        logger.debug("FachadaProcesoEJB::actualizarEstadoProceso(Long,EnumEstadoProceso,Boolean)");
        return iLProceso.actualizarEstadoProceso(idProceso, estado, cerrarProceso);
    }

    @Override
    public EstadoProcesoDTO consultarEstadoProceso(Long idProceso) {
        logger.debug("FachadaProcesoEJB::consultarEstadoProceso(Long)");
        return iLProceso.consultarEstadoProceso(idProceso);
    }

    @Override
    public List<ProcesoDTO> consultarProceso(ProcesoDTO proceso) {
        logger.debug("FachadaProcesoEJB::consultarProceso(ProcesoDTO)");
        return iLProceso.consultarProceso(proceso);
    }

    @Override
    public DocumentoProcesoDTO registrarDocumento(DocumentoProcesoDTO documento) {
        logger.debug("FachadaProcesoEJB::registrarDocumento(DocumentoProcesoDTO)");
        return iLProceso.registrarDocumento(documento);
    }

    @Override
    public List<TrazabilidadProcesoDTO> consultarTrazabilidad(TrazabilidadProcesoDTO trazabilidad) {
        logger.debug("FachadaProcesoEJB::consultarTrazabilidad(TrazabilidadProcesoDTO)");
        return iLProceso.consultarTrazabilidad(trazabilidad);
    }

    @Override
    public List<ParticipanteProcesoDTO> consultarParticipantesProceso(ParticipanteProcesoDTO criterios) {
        logger.debug("FachadaProcesoEJB::consultarParticipantesProceso(ParticipanteProcesoDTO)");
        return iLProceso.consultarParticipantesProceso(criterios);
    }

    @Override
    public ProcesoDTO consultarProceso(Long idProceso) {
        logger.debug("FachadaProcesoEJB::consultarProceso(Long)");
        return iLProceso.consultarProceso(idProceso);
    }

}