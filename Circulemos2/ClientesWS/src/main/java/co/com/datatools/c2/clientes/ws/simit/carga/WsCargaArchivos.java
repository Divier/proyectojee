package co.com.datatools.c2.clientes.ws.simit.carga;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.13
 * 2016-03-28T16:24:11.011-05:00
 * Generated source version: 2.7.13
 * 
 */
@WebServiceClient(name = "WsCargaArchivos", 
                  wsdlLocation = "http://181.48.11.2:8282/simit-pruebas/services/WsCargaArchivos?wsdl",
                  targetNamespace = "http://webservices.qxmultas.quipux.com.co") 
public class WsCargaArchivos extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webservices.qxmultas.quipux.com.co", "WsCargaArchivos");
    public final static QName WsCargaArchivosHttpSoap12Endpoint = new QName("http://webservices.qxmultas.quipux.com.co", "WsCargaArchivosHttpSoap12Endpoint");
    public final static QName WsCargaArchivosHttpSoap11Endpoint = new QName("http://webservices.qxmultas.quipux.com.co", "WsCargaArchivosHttpSoap11Endpoint");
    public final static QName WsCargaArchivosHttpEndpoint = new QName("http://webservices.qxmultas.quipux.com.co", "WsCargaArchivosHttpEndpoint");
    static {
        URL url = null;
        try {
            url = new URL("http://181.48.11.2:8282/simit-pruebas/services/WsCargaArchivos?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WsCargaArchivos.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://181.48.11.2:8282/simit-pruebas/services/WsCargaArchivos?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WsCargaArchivos(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WsCargaArchivos(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WsCargaArchivos() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WsCargaArchivos(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WsCargaArchivos(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WsCargaArchivos(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns WsCargaArchivosPortType
     */
    @WebEndpoint(name = "WsCargaArchivosHttpSoap12Endpoint")
    public WsCargaArchivosPortType getWsCargaArchivosHttpSoap12Endpoint() {
        return super.getPort(WsCargaArchivosHttpSoap12Endpoint, WsCargaArchivosPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WsCargaArchivosPortType
     */
    @WebEndpoint(name = "WsCargaArchivosHttpSoap12Endpoint")
    public WsCargaArchivosPortType getWsCargaArchivosHttpSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(WsCargaArchivosHttpSoap12Endpoint, WsCargaArchivosPortType.class, features);
    }
    /**
     *
     * @return
     *     returns WsCargaArchivosPortType
     */
    @WebEndpoint(name = "WsCargaArchivosHttpSoap11Endpoint")
    public WsCargaArchivosPortType getWsCargaArchivosHttpSoap11Endpoint() {
        return super.getPort(WsCargaArchivosHttpSoap11Endpoint, WsCargaArchivosPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WsCargaArchivosPortType
     */
    @WebEndpoint(name = "WsCargaArchivosHttpSoap11Endpoint")
    public WsCargaArchivosPortType getWsCargaArchivosHttpSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(WsCargaArchivosHttpSoap11Endpoint, WsCargaArchivosPortType.class, features);
    }
    /**
     *
     * @return
     *     returns WsCargaArchivosPortType
     */
    @WebEndpoint(name = "WsCargaArchivosHttpEndpoint")
    public WsCargaArchivosPortType getWsCargaArchivosHttpEndpoint() {
        return super.getPort(WsCargaArchivosHttpEndpoint, WsCargaArchivosPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WsCargaArchivosPortType
     */
    @WebEndpoint(name = "WsCargaArchivosHttpEndpoint")
    public WsCargaArchivosPortType getWsCargaArchivosHttpEndpoint(WebServiceFeature... features) {
        return super.getPort(WsCargaArchivosHttpEndpoint, WsCargaArchivosPortType.class, features);
    }

}
