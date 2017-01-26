package co.com.datatools.c2.negocio.ejb;

import static co.com.datatools.c2.util.Utilidades.safeList;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.c2.entidades.personas.Funcionario;
import co.com.datatools.c2.negocio.helpers.personas.FuncionarioHelper;
import co.com.datatools.c2.negocio.interfaces.ILFuncionario;
import co.com.datatools.c2.negocio.interfaces.IRFuncionario;
import co.com.datatools.util.GenericDao;

/**
 * Funcionario
 * 
 * @author julio.pinzon 2016-10-03
 */
@Stateless(name = "FuncionarioEJB")
@LocalBean
public class FuncionarioEJB implements ILFuncionario, IRFuncionario {

    private final static Logger logger = Logger.getLogger(FuncionarioEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<FuncionarioDTO> consultarFuncionarios(FuncionarioDTO funcionario, Date fechaCorte) {
        logger.debug("FuncionarioEJB::consultarFuncionario(FuncionarioDTO, Date)");

        List<FuncionarioDTO> funcionarios = new ArrayList<FuncionarioDTO>();

        Map<String, Object> params = new HashMap<String, Object>();

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT DISTINCT(f) FROM Funcionario f ");
        sb.append(" JOIN FETCH f.persona p ");

        sb.append(" WHERE 1 = 1");

        if (funcionario != null) {
            if (funcionario.getPersona() != null) {
                if (funcionario.getPersona().getTipoIdentificacion() != null
                        && funcionario.getPersona().getTipoIdentificacion().getId() != null) {
                    sb.append(" AND p.tipoIdentificacion.id = :tipoId ");
                    params.put("tipoId", funcionario.getPersona().getTipoIdentificacion().getId());
                }
                if (StringUtils.isNotBlank(funcionario.getPersona().getNumeroIdentificacion())) {
                    sb.append(" AND p.numeroIdentificacion = :numeroIdentificacion ");
                    params.put("numeroIdentificacion", funcionario.getPersona().getNumeroIdentificacion());
                }
            }
        }
        if (fechaCorte != null) {
            sb.append(" AND f.fechaInicioVigencia <= :fechaCorte ");
            sb.append(" AND (f.fechaFinalVigencia >= :fechaCorte OR f.fechaFinalVigencia IS NULL) ");
            params.put("fechaCorte", fechaCorte);
        }
        if (funcionario.getIdCargo() != null) {
            sb.append(" AND f.idCargo = :idCargo");
            params.put("idCargo", funcionario.getIdCargo());
        }

        GenericDao<Funcionario> dao = new GenericDao<>(Funcionario.class, this.em);
        final List<Funcionario> result = safeList(dao.buildAndExecuteQuery(sb, params));
        if (!result.isEmpty()) {
            funcionarios = FuncionarioHelper.toListLevel1DTO(result);
        }
        return funcionarios;
    }
}