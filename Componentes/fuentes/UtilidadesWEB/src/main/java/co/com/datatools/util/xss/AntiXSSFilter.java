package co.com.datatools.util.xss;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

/**
 * Based code AntiSQLFilter<br>
 * <br>
 * AntiSQLFilter is a J2EE Web Application Filter that protects internal componentes from SQL Injection attacks.<br>
 * <br>
 * SQL Injection is a hacker technique that uses regular http/https requests (via URL or HTML forms) to address a security vunerability on SQL handler
 * application components. Any http request parameter has the potential to carry special SQL statements to mislead the underlying database and expose
 * the web application.<br>
 * <br>
 * J2EE filters are the easiest way to intercept http request - they can be configured to intercept all or the most critical requests to a J2EE web
 * application.<br>
 * <br>
 * AntiSQLFilter is a filter that evaluates all request parameters and identifies possible SQL Injection attempts. The filter behavior can be
 * configured to react to an SQL Injection attack: log registration, exception raising, request redirection or parameter re-writing.<br>
 * <br>
 * It is open source (LGPL), written in Java and applies to any J2EE web application based on Servlet API 2.3 or higher and Java Virtual Machines 1.4
 * or higher.<br>
 * <br>
 * AntiSQLFilter is a J2EE Web Application Filter that protects web components from SQL Injection hacker attacks.<br>
 * Must to be configured with web.xml descriptors. <br>
 * <br>
 * Below, the filter initialization parameters to configure: <br>
 * <br>
 * <b>logging</b> - a <i>true</i> value enables output to Servlet Context logging in case of a SQL Injection detection. Defaults to <i>false</i>. <br>
 * <br>
 * <b>behavior</b> - there are three possible behaviors in case of a SQL Injection detection: <li>protect : (default) dangerous SQL keywords are 2nd
 * character supressed / dangerous SQL delimitters are blank space replaced. Afterwards the request flows as expected. <li>throw: a ServletException
 * is thrown - breaking the request flow. <li>forward: thre request is forwarded to a specific resource. <br>
 * <br>
 * <b>forwardTo</b> - the resource to forward when forward behavior is set.<br>
 * 
 * @author rbellia http://antisqlfilter.sourceforge.net/index.html
 * @version 0.1
 * 
 */
public class AntiXSSFilter implements Filter {

    private static final String INIT_PARAM_LOGGING = "logging";
    private static final String INIT_PARAM_BEHAVIOR = "behavior";
    private static final String INIT_PARAM_FORWARDTO = "forwardTo";

    private static final String BEHAVIOR_PROTECT = "protect";
    private static final String BEHAVIOR_FORWARD = "forward";

    private static String[] keyWords = { ";", "\"", "\'", "/*", "*/", "--", "exec", "select", "update", "delete",
            "insert", "alter", "drop", "create", "shutdown" };

    private static long attempts;

    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException,
            ServletException {

        HttpServletRequest originalRequest = (HttpServletRequest) req;

        if (isUnsafe(originalRequest.getParameterMap())) {
            String pLogging = filterConfig.getInitParameter(INIT_PARAM_LOGGING);
            if (pLogging != null && pLogging.equalsIgnoreCase("true")) {
                StringBuffer sb = new StringBuffer();
                sb.append("\nPossible Injection attempt #").append((++attempts)).append(" at ")
                        .append(new java.util.Date());
                sb.append("\nRemote Address: ").append(originalRequest.getRemoteAddr());
                sb.append("\nRemote User: ").append(originalRequest.getRemoteUser());
                sb.append("\nSession Id: ").append(originalRequest.getRequestedSessionId());
                sb.append("\nURI: ").append(originalRequest.getContextPath()).append(originalRequest.getRequestURI());
                sb.append("\nParameters via ").append(originalRequest.getMethod());
                Map paramMap = originalRequest.getParameterMap();
                for (Iterator iter = paramMap.keySet().iterator(); iter.hasNext();) {
                    String paramName = (String) iter.next();
                    String[] paramValues = originalRequest.getParameterValues(paramName);
                    sb.append("\n\t").append(paramName).append(" = ");
                    for (int j = 0; j < paramValues.length; j++) {
                        sb.append(paramValues[j]);
                        if (j < paramValues.length - 1) {
                            sb.append(" , ");
                        }
                    }
                }
                filterConfig.getServletContext().log(sb.toString());
            }

            String behavior = filterConfig.getInitParameter(INIT_PARAM_BEHAVIOR);
            String forwardTo = filterConfig.getInitParameter(INIT_PARAM_FORWARDTO);
            if (behavior == null || behavior.equalsIgnoreCase(BEHAVIOR_PROTECT)) {
                HttpServletRequest wrapper = new AntiXSSRequest(originalRequest);
                filterChain.doFilter(wrapper, resp);
            } else if (behavior.equalsIgnoreCase(BEHAVIOR_FORWARD) && forwardTo != null) {
                req.getRequestDispatcher(forwardTo).forward(req, resp);
            } else {
                RequestContext.getCurrentInstance().execute("PF('dlgError').show();");
                // final HttpServletResponse httpRs = (HttpServletResponse) resp;
                // httpRs.sendRedirect(originalRequest.getContextPath() + "/app/global?stateId=errorCaracteres");

                // StringBuilder response = new StringBuilder();
                // response.append("<html><head>");
                // response.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js\"></script>");
                // response.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/jquery-ui.min.js\"></script>");
                // response.append("<script type=\"text/javascript\">");
                // response.append("$(document).ready(function(){");
                // response.append("$(dialogMessage).dialog();});");
                // response.append(");</script></head><body>");
                // response.append("<div aria-live=\"polite\" aria-hidden=\"false\" aria-labelledby=\"dialogMessage_title\" role=\"dialog\" ");
                // response.append("style=\"width: auto; height: auto; left: 256px; top: 10.5px; display: block; z-index: 1002;\" id=\"dialogMessage\" ");
                // response.append("class=\"ui-dialog ui-widget ui-widget-content ui-corner-all ui-shadow ui-hidden-container dialog-top ui-draggable\">");
                // response.append("<div class=\"ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top ui-draggable-handle\">");
                // response.append("<span id=\"dialogMessage_title\" class=\"ui-dialog-title\">Error</span>");
                // response.append("<a role=\"button\" href=\"#\" class=\"ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all\">");
                // response.append("<span class=\"ui-icon ui-icon-closethick\"></span>");
                // response.append("</a>");
                // response.append("</div>");
                // response.append("<div id=\"messages\" class=\"ui-messages ui-widget\" aria-live=\"polite\"><div class=\"ui-messages-error ui-corner-all\">");
                // response.append("<span class=\"ui-messages-error-icon\"></span>" + "<ul>" + "<li>");
                // response.append("<span class=\"ui-messages-error-summary\"></span>");
                // response.append("<span class=\"ui-messages-error-detail\">Caracteres no validos para el campo</span>");
                // response.append("</li>" + "</ul>" + "</div>" + "</div>" + "</div></body></html>");
                // PrintWriter writer = resp.getWriter();
                // resp.reset();
                // resp.setContentType("text/html");
                // writer.print(response.toString());
                // filterChain.doFilter(req, resp);
            }
        } else {
            filterChain.doFilter(req, resp);
        }

    }

    public void destroy() {
    }

    protected static boolean isUnsafe(Map parameterMap) {
        Iterator iter = parameterMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            String[] param = (String[]) parameterMap.get(key);
            for (int i = 0; i < param.length; i++) {
                if (XSSValidator.isUnsafe(param[i])) {
                    return true;
                }
                if (key.contains("_injComi") && XSSValidator.tieneComillaSencilla(param[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Map getMessageParameter(Map parameterMap, String key) {
        String[] oldValues = (String[]) parameterMap.get(key);
        String[] newValues = new String[oldValues.length];
        for (int i = 0; i < oldValues.length; i++) {
            newValues[i] = getSafeValue(oldValues[i]);
        }

        return Collections.unmodifiableMap(parameterMap);
    }

    static Map getSafeParameterMap(Map parameterMap) {
        Map newMap = new HashMap();
        Iterator iter = parameterMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            String[] oldValues = (String[]) parameterMap.get(key);
            String[] newValues = new String[oldValues.length];
            for (int i = 0; i < oldValues.length; i++) {
                newValues[i] = getSafeValue(oldValues[i]);
            }
            newMap.put(key, newValues);
        }
        return Collections.unmodifiableMap(newMap);
    }

    public static String getSafeValue(String oldValue) {
        StringBuffer sb = new StringBuffer(oldValue);
        String lowerCase = oldValue.toLowerCase();
        for (int i = 0; i < keyWords.length; i++) {
            int x = -1;
            while ((x = lowerCase.indexOf(keyWords[i])) >= 0) {
                if (keyWords[i].length() == 1) {
                    sb.replace(x, x + 1, " ");
                    lowerCase = sb.toString().toLowerCase();
                    continue;
                }
                sb.deleteCharAt(x + 1);
                lowerCase = sb.toString().toLowerCase();
            }
        }
        return sb.toString();
    }

}
