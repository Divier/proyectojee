package co.com.datatools.seguridad.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

/**
 * Filtro que evalua en cada request si tiene una sesion valida para redireccionar a la pagina de inicio
 * 
 * @author Felipe Martinez
 * 
 */
@WebFilter(filterName = "LoginRequestFilter", urlPatterns = { "/app/*" })
public class LoginRequestFilter implements Filter {

    private final static Logger logger = Logger.getLogger(LoginRequestFilter.class.getName());
    private static final String NOMBRE_PAGINA_MAIN = "/protegido/main.xhtml";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (request instanceof HttpServletRequest) {
            final HttpServletRequest rq = (HttpServletRequest) request;
            final HttpServletResponse httpRs = (HttpServletResponse) response;

            String url = rq.getRequestURL().toString();
            String queryString = rq.getQueryString();
            if (!rq.isRequestedSessionIdValid()) {
                if (url.contains(NOMBRE_PAGINA_MAIN)) {
                    return;
                } else if (!url.contains(ConstantesManagedBean.NOMBRE_PAGINA_RESTABLECER_PW)) {
                    logger.infov("Peticion sin sesion valida, se retorna al main, {0}?{1}", url, queryString);
                    rq.getSession().setAttribute(ConstantesManagedBean.NOMBRE_OBJ_URL_INICIAL, url);
                    httpRs.sendRedirect(rq.getContextPath() + ConstantesManagedBean.URL_MAIN);
                    return;
                }

            }
            InicioAppSeguridad initAppSegMB = (InicioAppSeguridad) rq.getServletContext().getAttribute(
                    InicioAppSeguridad.NOMBRE_BEAN);
            if (!initAppSegMB.instaladorEjecutado(rq)) {
                httpRs.sendRedirect(rq.getContextPath() + ConstantesManagedBean.URL_INSTALADOR);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}