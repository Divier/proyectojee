package co.com.datatools.seguridad.helper;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DatosSesionHelper {

    @Resource
    private EJBContext context;

    private SessionContext sessionContext;

    public DatosSesionHelper() {
        System.out.println("DatosSesionHelper");
        try {
            InitialContext ic = new InitialContext();
            sessionContext = (SessionContext) ic.lookup("java:comp/EJBContext");
            System.out.println("principal= " + sessionContext.getCallerPrincipal());
        } catch (NamingException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public String getUsuarioSesion() {
        return sessionContext.getCallerPrincipal().getName().split("#")[1];
    }

    public String getIPSesion() {
        return sessionContext.getCallerPrincipal().getName().split("#")[0];
    }

}
