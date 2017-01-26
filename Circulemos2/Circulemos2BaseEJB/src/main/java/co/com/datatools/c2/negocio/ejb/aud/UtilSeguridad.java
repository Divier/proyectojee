package co.com.datatools.c2.negocio.ejb.aud;

import java.util.HashMap;
import java.util.Map;

public class UtilSeguridad {

    private static String loginUsuario;
    private static String ipUsuario;
    private static Map<Long, String> mapaUsuarios;
    private static Map<Long, String> mapaIps;

    public static String getIpUsuario() {
        return ipUsuario;
    }

    public static void setIpUsuario(String ipUsuario) {
        UtilSeguridad.ipUsuario = ipUsuario;
    }

    public static String getLoginUsuario() {
        return loginUsuario;
    }

    public static void setLoginUsuario(String loginUsuario) {
        UtilSeguridad.loginUsuario = loginUsuario;
    }

    public static void setMapUsuarios(Long idThread, String usuario, String ip) {
        if (null == mapaUsuarios) {
            mapaUsuarios = new HashMap<Long, String>();
        }
        if (null == mapaIps)
            mapaIps = new HashMap<Long, String>();
        mapaUsuarios.put(idThread, usuario);
        mapaIps.put(idThread, ip);
    }

    public static String getMapUsuarios(Long idThread) {
        String login = "";
        try {
            login = mapaUsuarios.get(idThread);
        } catch (Exception e) {
            return null;
        }
        return login;
    }

    public static String getMapIp(Long idThread) {
        String ip = "";
        try {
            ip = mapaIps.get(idThread);
        } catch (Exception e) {
            return null;
        }
        return ip;
    }

    /**
     * Quita el usuario del mapa estatico de usuarios
     * 
     * @param idThread
     * @author julio.pinzon
     */
    public static void removerUsuario(Long idThread) {
        if (null != mapaUsuarios) {
            mapaUsuarios.remove(idThread);
        }
        if (null != mapaIps) {
            mapaIps.remove(idThread);
        }
    }
}
