
package co.com.datatools.c2.clientes.ws.simit.carga;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosComparendo;
import co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosRecaudo;
import co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosResolucion;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.datatools.c2.simit.ws.cliente package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CargaArchivoResolucionResponseReturn_QNAME = new QName("http://webservices.qxmultas.quipux.com.co", "return");
    private final static QName _CargaRecaudoRecaudo_QNAME = new QName("http://webservices.qxmultas.quipux.com.co", "recaudo");
    private final static QName _CargaArchivoComparendosComparendo_QNAME = new QName("http://webservices.qxmultas.quipux.com.co", "comparendo");
    private final static QName _ExceptionException_QNAME = new QName("http://webservices.qxmultas.quipux.com.co", "Exception");
    private final static QName _CargaArchivoResolucionResolucion_QNAME = new QName("http://webservices.qxmultas.quipux.com.co", "resolucion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.com.datatools.c2.simit.ws.cliente
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Exception2 }
     * 
     */
    public Exception2 createException2() {
        return new Exception2();
    }

    /**
     * Create an instance of {@link CargaRecaudo }
     * 
     */
    public CargaRecaudo createCargaRecaudo() {
        return new CargaRecaudo();
    }

    /**
     * Create an instance of {@link CargaArchivoComparendosResponse }
     * 
     */
    public CargaArchivoComparendosResponse createCargaArchivoComparendosResponse() {
        return new CargaArchivoComparendosResponse();
    }

    /**
     * Create an instance of {@link CargaArchivoResolucion }
     * 
     */
    public CargaArchivoResolucion createCargaArchivoResolucion() {
        return new CargaArchivoResolucion();
    }

    /**
     * Create an instance of {@link CargaArchivoComparendos }
     * 
     */
    public CargaArchivoComparendos createCargaArchivoComparendos() {
        return new CargaArchivoComparendos();
    }

    /**
     * Create an instance of {@link CargaArchivoResolucionResponse }
     * 
     */
    public CargaArchivoResolucionResponse createCargaArchivoResolucionResponse() {
        return new CargaArchivoResolucionResponse();
    }

    /**
     * Create an instance of {@link CargaRecaudoResponse }
     * 
     */
    public CargaRecaudoResponse createCargaRecaudoResponse() {
        return new CargaRecaudoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.qxmultas.quipux.com.co", name = "return", scope = CargaArchivoResolucionResponse.class)
    public JAXBElement<String> createCargaArchivoResolucionResponseReturn(String value) {
        return new JAXBElement<String>(_CargaArchivoResolucionResponseReturn_QNAME, String.class, CargaArchivoResolucionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatosRecaudo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.qxmultas.quipux.com.co", name = "recaudo", scope = CargaRecaudo.class)
    public JAXBElement<DatosRecaudo> createCargaRecaudoRecaudo(DatosRecaudo value) {
        return new JAXBElement<DatosRecaudo>(_CargaRecaudoRecaudo_QNAME, DatosRecaudo.class, CargaRecaudo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatosComparendo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.qxmultas.quipux.com.co", name = "comparendo", scope = CargaArchivoComparendos.class)
    public JAXBElement<DatosComparendo> createCargaArchivoComparendosComparendo(DatosComparendo value) {
        return new JAXBElement<DatosComparendo>(_CargaArchivoComparendosComparendo_QNAME, DatosComparendo.class, CargaArchivoComparendos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.qxmultas.quipux.com.co", name = "Exception", scope = Exception.class)
    public JAXBElement<Exception2> createExceptionException(Exception2 value) {
        return new JAXBElement<Exception2>(_ExceptionException_QNAME, Exception2 .class, Exception.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.qxmultas.quipux.com.co", name = "return", scope = CargaRecaudoResponse.class)
    public JAXBElement<String> createCargaRecaudoResponseReturn(String value) {
        return new JAXBElement<String>(_CargaArchivoResolucionResponseReturn_QNAME, String.class, CargaRecaudoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.qxmultas.quipux.com.co", name = "Exception", scope = Exception2 .class)
    public JAXBElement<Object> createException2Exception(Object value) {
        return new JAXBElement<Object>(_ExceptionException_QNAME, Object.class, Exception2 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatosResolucion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.qxmultas.quipux.com.co", name = "resolucion", scope = CargaArchivoResolucion.class)
    public JAXBElement<DatosResolucion> createCargaArchivoResolucionResolucion(DatosResolucion value) {
        return new JAXBElement<DatosResolucion>(_CargaArchivoResolucionResolucion_QNAME, DatosResolucion.class, CargaArchivoResolucion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.qxmultas.quipux.com.co", name = "return", scope = CargaArchivoComparendosResponse.class)
    public JAXBElement<String> createCargaArchivoComparendosResponseReturn(String value) {
        return new JAXBElement<String>(_CargaArchivoResolucionResponseReturn_QNAME, String.class, CargaArchivoComparendosResponse.class, value);
    }

}
