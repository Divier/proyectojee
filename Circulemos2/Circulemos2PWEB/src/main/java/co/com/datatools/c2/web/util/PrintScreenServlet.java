package co.com.datatools.c2.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.codec.binary.Base64;
import org.jboss.logging.Logger;

import co.com.datatools.c2.managed_bean.accidentalidad.AdminAccidentalidadMB;

/**
 * Servlet implementation class PrintScreenServlet
 * 
 * @author julio.pinzon
 */
@MultipartConfig
public class PrintScreenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Logger
     */
    private final static Logger LOGGER = Logger.getLogger(PrintScreenServlet.class.getName());

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader bufferedReader = null;
        try {
            String infoImagen = "data:image/jpeg;base64,";
            Part part = request.getPart("printScreen");
            bufferedReader = new BufferedReader(new InputStreamReader(part.getInputStream(), Charset.forName("utf-8")));
            String sImg = bufferedReader.readLine();
            if (sImg != null) {
                sImg = sImg.substring(sImg.indexOf(infoImagen) + infoImagen.length());
                byte[] bImg64 = sImg.getBytes();
                byte[] bImg = Base64.decodeBase64(bImg64); // apache-commons-codec
                AdminAccidentalidadMB adminAccidentalidadMB = (AdminAccidentalidadMB) request.getSession()
                        .getAttribute("adminAccidentalidadMB");
                if (adminAccidentalidadMB != null) {
                    adminAccidentalidadMB.guardarInforme(bImg);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error al guardar imagen", e);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

}
