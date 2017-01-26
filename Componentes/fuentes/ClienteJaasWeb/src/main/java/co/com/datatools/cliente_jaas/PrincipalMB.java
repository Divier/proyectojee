/**
 * 
 */
package co.com.datatools.cliente_jaas;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import co.com.datatools.ejb.ClienteJaasLocal;

/**
 * @author sergio.torres
 * 
 */
@ManagedBean
@SessionScoped
public class PrincipalMB {

    private String usuario;
    @EJB
    private ClienteJaasLocal clienteJaasLocal;

    /**
     * 
     */
    public PrincipalMB() {
        // TODO Auto-generated constructor stub
    }

    @PostConstruct
    public void inicializar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        usuario = request.getUserPrincipal().getName();
        clienteJaasLocal.metodoNegocio();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
