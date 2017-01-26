package co.com.datatools.c2.adaptador.comparendo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.dto.ComparendoProcesoTrazabilidadDTO;
import co.com.datatools.c2.adaptador.dto.ProcesoComparendoDTO;
import co.com.datatools.c2.adaptador.proceso.IRProcesoComparendo;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.TipoProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.entidades.ComparendoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.negocio.interfaces.IRProceso;
import co.com.datatools.c2.util.Utilidades;

/**
 * Clase que se encarga de implementar el adaptador entre los modulos de proceso y comparendos
 */
@Stateless(name = "ProcesoComparendoEJB")
@LocalBean
public class ProcesoComparendoEJB implements IRProcesoComparendo {

    private final static Logger logger = Logger.getLogger(ProcesoComparendoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRProceso procesoEJB;

    @Override
    public boolean existeProceso(ProcesoComparendoDTO procesoComparendoDTO) {
        logger.debug("ProcesoComparendoEJB.existeProceso(ProcesoComparendoDTO)");
        ProcesoDTO proceso = new ProcesoDTO();
        proceso.setTipoProceso(new TipoProcesoDTO(procesoComparendoDTO.getTipoProceso().getValue()));
        return procesoEJB.existeProceso(proceso);
    }

    @Override
    public List<ComparendoProcesoTrazabilidadDTO> consultarTrazabilidadProcesos(Long cicomparendo) {
        logger.debug("consultarTrazabilidadProcesos(Long)");
        List<ComparendoProcesoTrazabilidadDTO> comparendoProcesoTrazabilidadDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT cp FROM ComparendoProceso cp");
        jpql.append(" WHERE cp.comparendo.cicomparendo = :cicomparendo");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("cicomparendo", cicomparendo);

        @SuppressWarnings("unchecked")
        List<ComparendoProceso> comparendoProceso = query.getResultList();
        if (comparendoProceso != null && !comparendoProceso.isEmpty()) {
            comparendoProcesoTrazabilidadDTOs = new ArrayList<ComparendoProcesoTrazabilidadDTO>();

            for (ComparendoProceso compaProceso : comparendoProceso) {
                // Proceso comparendo
                ProcesoComparendoDTO procesoComparendoDTO = new ProcesoComparendoDTO();
                // Trazabilidad proceso
                TrazabilidadProcesoDTO trazabilidadProcesoDTO = new TrazabilidadProcesoDTO();
                ProcesoDTO procesoDTO = new ProcesoDTO();
                procesoDTO.setId(compaProceso.getIdProceso());
                trazabilidadProcesoDTO.setProceso(procesoDTO);
                List<TrazabilidadProcesoDTO> trazabProcesoDTOs = procesoEJB
                        .consultarTrazabilidad(trazabilidadProcesoDTO);
                if (trazabProcesoDTOs != null && !trazabProcesoDTOs.isEmpty()) {
                    // Ingreso tipo de proceso
                    EnumTipoProceso enumTipoProceso = Utilidades.buscarElemEnum(EnumTipoProceso.class,
                            trazabProcesoDTOs.get(0).getProceso().getTipoProceso().getId());
                    procesoComparendoDTO.setTipoProceso(enumTipoProceso);

                    procesoComparendoDTO.setTrazabilidadProcesoDTOs(new ArrayList<TrazabilidadProcesoDTO>());
                    for (TrazabilidadProcesoDTO trazabilidadPrDTO : trazabProcesoDTOs) {
                        procesoComparendoDTO.getTrazabilidadProcesoDTOs().add(trazabilidadPrDTO);
                    }
                }
            }
        }

        return comparendoProcesoTrazabilidadDTOs;
    }
}
