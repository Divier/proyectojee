//package co.com.datatools.seguridad.entidades.aud;
//
//import javax.ejb.SessionContext;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import org.hibernate.envers.RevisionListener;
//
//import co.com.datatools.seguridad.excepciones.SeguridadRuntimeException;
//
///**
// * 
// * @author rodrigo.cruz
// */
//public class RevisionSeguridadListener implements RevisionListener {
//
//    @Override
//    public void newRevision(Object revisionEntity) {
//
//        try {
//            InitialContext ic = new InitialContext();
//            SessionContext sessionContext = (SessionContext) ic.lookup("java:comp/EJBContext");
//
//            RevisionSeguridad entity = (RevisionSeguridad) revisionEntity;
//            entity.setUsuario(sessionContext.getCallerPrincipal().getName()
//                    .substring(sessionContext.getCallerPrincipal().getName().indexOf("#") + 1));
//            entity.setIp(sessionContext.getCallerPrincipal().getName()
//                    .substring(0, sessionContext.getCallerPrincipal().getName().indexOf("#")));
//
//        } catch (NamingException ex) {
//            throw new SeguridadRuntimeException(ex);
//        }
//
//    }
// }
