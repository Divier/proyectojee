package co.com.datatools.c2.adaptador.comparendo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.datatools.c2.adaptador.patio.IRPatioComparendo;
import co.com.datatools.c2.dto.comparendo.PatioComparendoDTO;
import co.com.datatools.c2.dto.patios.PatioDTO;
import co.com.datatools.c2.negocio.interfaces.patios.IRPatio;

/**
 * Clase que se encarga de implementar el adpatador entre los modulos de patios y comparendos
 */
@Stateless(name = "PatioComparendoEJB")
@LocalBean
public class PatioComparendoEJB implements IRPatioComparendo {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRPatio patioEJB;

    /**
     * Default constructor.
     */
    public PatioComparendoEJB() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<PatioComparendoDTO> consultarPatios(PatioComparendoDTO patioComparendoDTO) {
        List<PatioComparendoDTO> patiosComparendo = new ArrayList<PatioComparendoDTO>();
        PatioDTO patioDTO = new PatioDTO();
        patioDTO.setId(patioComparendoDTO.getId());
        List<PatioDTO> patios = patioEJB.consultarPatios(patioDTO);
        for (PatioDTO patio : patios) {
            PatioComparendoDTO patioComparendo = new PatioComparendoDTO();
            patioComparendo.setId(patio.getId());
            patioComparendo.setCodigo(patio.getCodigo());
            patioComparendo.setNombre(patio.getNombre());
            patiosComparendo.add(patioComparendo);
        }
        return patiosComparendo;
    }
}
