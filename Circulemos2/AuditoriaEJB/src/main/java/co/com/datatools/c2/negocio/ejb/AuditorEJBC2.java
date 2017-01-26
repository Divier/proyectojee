package co.com.datatools.c2.negocio.ejb;

import javax.ejb.Stateless;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.datatools.auditoria.interfaces.AbstractAuditor;

/**
 * Session Bean implementation class AuditorEJBC2
 */
@Stateless
@ExcludeDefaultInterceptors
public class AuditorEJBC2 extends AbstractAuditor implements ILAuditoriaC2 {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
