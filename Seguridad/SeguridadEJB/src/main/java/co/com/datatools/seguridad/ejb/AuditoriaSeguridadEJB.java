package co.com.datatools.seguridad.ejb;

import javax.ejb.Stateless;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.datatools.auditoria.interfaces.AbstractAuditor;
import co.com.datatools.auditoria.interfaces.ILAuditoria;
import co.com.datatools.auditoria.interfaces.IRAuditoria;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;

/**
 * Session Bean implementation class AuditoriaSeguridadEJB
 */
@Stateless(name = "AuditoriaSeguridadEJB")
@ExcludeDefaultInterceptors
public class AuditoriaSeguridadEJB extends AbstractAuditor implements ILAuditoria, IRAuditoria {

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

}
