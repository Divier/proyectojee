/**
 * 
 */
package co.com.datatools.cliente_jaas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author sergio.torres
 * 
 */
@ManagedBean
@SessionScoped
public class AutenticacionMB {

    private String ip;

    /**
     * 
     */
    @PostConstruct
    public void inicializar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        ip = request.getRemoteAddr();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
