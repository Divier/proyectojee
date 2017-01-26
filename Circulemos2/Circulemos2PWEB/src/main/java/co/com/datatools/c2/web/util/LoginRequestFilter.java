package co.com.datatools.c2.web.util;

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
 */
@WebFilter(filterName = "LoginRequestFilter", urlPatterns = { "/app/*" })
public class LoginRequestFilter implements Filter {

    private final static Logger logger = Logger.getLogger(LoginRequestFilter.class.getName());
    private static final String NOMBRE_PAGINA_RESTABLECER_PW = "recuperacionPw";
    private static final String NOMBRE_PAGINA_MAIN = "/protegido/main.xhtml";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (request instanceof HttpServletRequest) {
            final HttpServletRequest httpRq = (HttpServletRequest) request;
            final HttpServletResponse httpRs = (HttpServletResponse) response;

            String url = httpRq.getRequestURL().toString();
            String queryString = httpRq.getQueryString();
            if (!httpRq.isRequestedSessionIdValid()) {
                if (url.contains(NOMBRE_PAGINA_MAIN)) {
                    return;
                } else if (!url.contains(NOMBRE_PAGINA_RESTABLECER_PW)) {
                    logger.infov("Peticion sin sesion valida, se retorna al main, {0}?{1}", url, queryString);
                    httpRq.getSession().setAttribute(ConstantesManagedBean.URL_INICIAL, url);
                    httpRs.sendRedirect(httpRq.getContextPath() + NOMBRE_PAGINA_MAIN);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}