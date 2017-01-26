package co.com.datatools.ejb;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ClienteJaas
 */
@Stateless
@LocalBean
public class ClienteJaas implements ClienteJaasLocal {

    @Resource
    private EJBContext context;

    /**
     * Default constructor.
     */
    public ClienteJaas() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void metodoNegocio() {
        System.out.println(context.getCallerPrincipal().getName());
    }

}
