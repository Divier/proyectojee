package co.com.datatools.c2.negocio.ejb.aud;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.datatools.c2.negocio.interfaces.aud.ILUsuarioSesion;

/**
 * @author rodrigo.cruz
 */
@Stateless(mappedName = "UsuarioSesionEJB")
@LocalBean
public class UsuarioSesionEJB implements ILUsuarioSesion {

    @Resource
    private EJBContext ctx;

    public UsuarioSesionEJB() {
    }

    @Override
    public String getUsuario() {
        if (null == UtilSeguridad.getMapUsuarios(Thread.currentThread().getId())) {
            return ctx.getCallerPrincipal().getName();
        } else {
            return UtilSeguridad.getMapUsuarios(Thread.currentThread().getId());
        }
    }

    @Override
    public String getIP() {
        if (null == UtilSeguridad.getMapIp(Thread.currentThread().getId())) {
            return "127.0.0.1"; // TODO RC - GET SESSION IP (JAAS)
        } else {
            return UtilSeguridad.getMapIp(Thread.currentThread().getId());
        }
    }

    @Override
    public long almacenarUsuario(String loginUsuario, String ip) {
        long idThread = Thread.currentThread().getId();
        UtilSeguridad.setMapUsuarios(idThread, loginUsuario, ip);
        return idThread;
    }

    @Override
    public void removerUsuario() {
        UtilSeguridad.removerUsuario(Thread.currentThread().getId());
    }

}
