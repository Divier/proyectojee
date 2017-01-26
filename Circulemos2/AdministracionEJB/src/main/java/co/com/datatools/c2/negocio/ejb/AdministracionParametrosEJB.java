package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.AdminParametrosDTO;
import co.com.datatools.c2.entidades.ParametroOrganismo;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionParametrosEJB;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionParametrosEJB;
import co.com.datatools.util.GenericDao;

/**
 * Session Bean implementation class AdministracionEJB
 */
@Stateless(name = "AdministracionParametrosEJB")
@LocalBean
public class AdministracionParametrosEJB implements IRAdministracionParametrosEJB, ILAdministracionParametrosEJB {

    private final static Logger logger = Logger.getLogger(AdministracionParametrosEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<AdminParametrosDTO> consultarParametros() {
        logger.debug("AdministracionParametrosEJB::consultarParametros()");
        List<AdminParametrosDTO> lsRespuesta = new ArrayList<AdminParametrosDTO>();

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT po ");
        jpql.append("FROM ParametroOrganismo as po ");
        jpql.append("JOIN FETCH po.parametro as p ");
        jpql.append("JOIN FETCH po.organismoTransito ");
        jpql.append("JOIN FETCH p.tipoVariable ");
        jpql.append("JOIN FETCH p.modulo ");
        jpql.append("WHERE p.editableOrganismo = :editableParametro ");

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("editableParametro", true);

        GenericDao<ParametroOrganismo> daParametroOrganismo = new GenericDao<ParametroOrganismo>(
                ParametroOrganismo.class, em);

        List<ParametroOrganismo> resultado = daParametroOrganismo.buildAndExecuteQuery(jpql, filtros);

        AdminParametrosDTO parametroDTO = null;
        for (ParametroOrganismo parametro : resultado) {
            parametroDTO = new AdminParametrosDTO();
            parametroDTO.setIdParametro(parametro.getId());
            parametroDTO.setOrganismo(parametro.getOrganismoTransito().getNombreOrganismo());
            parametroDTO.setModulo(parametro.getParametro().getModulo().getNombre());
            parametroDTO.setNombre(parametro.getParametro().getNombre());
            parametroDTO.setTipo(parametro.getParametro().getTipoVariable().getNombre());
            parametroDTO.setValor(parametro.getValor());
            parametroDTO.setFechaIni(parametro.getFechaInicio());
            parametroDTO.setFechaFin(parametro.getFechaFin());
            // Se valida el estado del parametro
            if (parametro.getFechaFin() == null) {
                parametroDTO.setEstado("Vigente");
            } else {
                parametroDTO.setEstado("No vigente");
            }

            lsRespuesta.add(parametroDTO);
        }

        return lsRespuesta;
    }

}