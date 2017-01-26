package co.com.datatools.sogit.utilies;

import java.io.IOException;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.enumeracion.EnumParametro;

/**
 * Clase de fachada para acceder a los catalogos alojados en el contexto aplicacion
 * 
 * @author felipe.martinez
 */
@ManagedBean
@SessionScoped
public class UtilidadMB extends AbstractPortalManagedBean {

    private final static Logger LOGGER = Logger.getLogger(UtilidadMB.class.getName());
    private static final long serialVersionUID = 1L;

    private static String SERVICIO_REST_PARAMETRO = "/FachadaIntegracionWeb/rest/consultarValorParametro/";
    private static Integer CODIGO_ORGANISMO = 11001;

    private String formatoFecha;
    private String formatoFechaCompleta;
    private String formatoMonedaCompleta;
    private String formatoMoneda;

    /**
     * retorna el formato de fecha corta
     * 
     * @return
     */
    public String modificarFormatoFechaCorta() {
        LOGGER.debug("modificarFormatoFechaCorta()");
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target(getServer() + SERVICIO_REST_PARAMETRO + EnumParametro.FORMATO_FECHA.getValue());

        String valorParametro = target.request(MediaType.TEXT_PLAIN).get(String.class);

        String[] valorParametroDTO = valorParametro.split(" ");
        return valorParametroDTO[0];
    }

    /**
     * retorna el formato de fecha larga
     * 
     * @return
     */
    public String modificarFormatoFechaCompleta() {
        LOGGER.debug("modificarFormatoFechaCompleta()");
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target(getServer() + SERVICIO_REST_PARAMETRO + EnumParametro.FORMATO_FECHA.getValue());
        String valorParametro = target.request(MediaType.TEXT_PLAIN).get(String.class);
        return valorParametro;
    }

    /**
     * retorna el formato de mondeda completo
     * 
     * @return
     */
    public String modificarFormatoMonedaCompleta() {
        LOGGER.debug("modificarFormatoMonedaCompleta()");
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target(getServer() + SERVICIO_REST_PARAMETRO + EnumParametro.FORMATO_MONEDA.getValue());
        String valorParametro = target.request(MediaType.TEXT_PLAIN).get(String.class);
        return valorParametro;
    }

    /**
     * retorna el formato de moneda sin el simbolo solo al inicial
     * 
     * @return
     */
    public String modificaFormatoModenaCorta() {
        LOGGER.debug("modificaFormatoModenaCorta()");
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target(getServer() + SERVICIO_REST_PARAMETRO + EnumParametro.FORMATO_MONEDA.getValue());
        String valorParametro = target.request(MediaType.TEXT_PLAIN).get(String.class);
        return valorParametro.substring(1);
    }

    /**
     * Se encarga de sacar la version del proyecto
     * 
     * @author giovanni.velandia
     * @return
     */
    public static String cargarVersionArtefacto() {
        Properties propiedades = new Properties();
        try {
            propiedades.load(UtilidadMB.class.getResourceAsStream("/portal-artefacto.properties"));
            return propiedades.getProperty("version");
        } catch (IOException e) {
            LOGGER.error("Problemas cargando propiedades. ", e);
        }
        return null;
    }

    /**
     * @author giovanni.velandia
     * @return
     */
    public String rutaServerEvidencias() {
        return consultarValorParametro(EnumParametro.RUTA_EVIDENCIAS.getValue(), CODIGO_ORGANISMO);
    }

    /**
     * retorna el valor de un parametro
     * 
     * @author giovanni.velandia
     * @return
     */
    public String consultarValorParametro(Integer parametro, Integer codigoOrgansimo) {
        LOGGER.debug("consultarValorParametro(Integer, Integer)");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(getServer() + SERVICIO_REST_PARAMETRO + parametro + "/" + codigoOrgansimo);

        String valorParametro = target.request(MediaType.TEXT_PLAIN).get(String.class);
        return valorParametro.trim();
    }

    public String getFormatoFecha() {

        if (formatoFecha == null) {
            formatoFecha = modificarFormatoFechaCorta();
        }
        return formatoFecha;
    }

    public String getFormatoFechaCompleta() {
        if (formatoFechaCompleta == null) {
            formatoFechaCompleta = modificarFormatoFechaCompleta();
        }
        return formatoFechaCompleta;
    }

    public String getFormatoMonedaCompleta() {
        if (formatoMonedaCompleta == null) {
            formatoMonedaCompleta = modificarFormatoMonedaCompleta();
        }
        return formatoMonedaCompleta;
    }

    public String getFormatoMoneda() {
        if (formatoMoneda == null) {
            formatoMoneda = modificaFormatoModenaCorta();
        }
        return formatoMoneda;
    }

}
