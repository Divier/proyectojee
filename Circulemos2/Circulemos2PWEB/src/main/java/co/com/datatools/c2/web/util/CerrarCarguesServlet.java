package co.com.datatools.c2.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.security.auth.login.LoginContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.datatools.c2.dto.ParametroDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.negocio.fachada.IRFachadaFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRCargueMasivo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
//import co.com.datatools.documentos.negocio.interfaces.documentos.IRVariable;
import co.com.datatools.c2.util.BeanLocatorC2;

/**
 * Servlet implementation class CerrarCarguesServlet
 */
@WebServlet(value="/CerrarCarguesServlet", loadOnStartup=1)
public class CerrarCarguesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String KEY_CERRAR_CARGUES = "CERRAR_CARGUES_ERROR";
	private final static String CIERRA_SI = "S";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CerrarCarguesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		try {
//			final Hashtable<String, String> props = new Hashtable<String, String>();
//			// setup the ejb: namespace URL factory
//         	props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//         	// create the InitialContext
//         	Context context;
//			context = new javax.naming.InitialContext(props);
//			IRCargueMasivo irCargue = (IRCargueMasivo) context.lookup("java:app/AdministracionEJB/CargueMasivoEJB!co.com.datatools.c2.negocio.interfaces.IRCargueMasivo");
//			irCargue.cerrarCarguesErroneos();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void init(){
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("circulemos.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String parametroCierra = properties.getProperty(KEY_CERRAR_CARGUES);
		if(parametroCierra != null && parametroCierra.equals(CIERRA_SI)){
			IRCargueMasivo iRCargue = BeanLocatorC2.locate(IRCargueMasivo.class, BeanLocatorC2.Beans.IRCargueMasivo.toString());
			iRCargue.cerrarCarguesErroneos();
		}
	}

}
