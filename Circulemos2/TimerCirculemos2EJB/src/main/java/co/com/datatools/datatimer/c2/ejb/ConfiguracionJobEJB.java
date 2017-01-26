package co.com.datatools.datatimer.c2.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.datatimer.c2.entidades.Job;
import co.com.datatools.datatimer.c2.interfaces.ILConfiguracionJob;
import co.com.datatools.datatimer.c2.interfaces.IRConfiguracionJob;
import co.com.datatools.datatimer.dto.JobDTO;
import co.com.datatools.datatimer.helpers.JobHelper;
import co.com.datatools.util.GenericDao;

@Stateless(name = "ConfiguracionJobEJB")
@LocalBean
public class ConfiguracionJobEJB implements ILConfiguracionJob, IRConfiguracionJob {

    private final static Logger logger = Logger.getLogger(ConfiguracionJobEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    public ConfiguracionJobEJB() {
        super();
    }

    @Override
    public List<JobDTO> consultarConfiguracionJob(int identificador) {

        logger.debug("ConfiguracionJobEJB::consultarConfiguracionJob");
        List<JobDTO> lJobDTO = null;
        GenericDao<Job> jobDao = new GenericDao<>(Job.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT j FROM Job j");
        jpql.append(" LEFT JOIN j.triggerJobList tl");
        jpql.append(" WHERE 1=1");
        jpql.append(" AND j.id = :identificador");
        filtros.put("identificador", identificador);

        List<Job> resultadoConsulta = jobDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            lJobDTO = JobHelper.toListLevel1DTO(resultadoConsulta);
        }
        return lJobDTO;
    }
}