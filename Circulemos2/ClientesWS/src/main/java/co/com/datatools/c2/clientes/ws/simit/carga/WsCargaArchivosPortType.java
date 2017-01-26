package co.com.datatools.c2.clientes.ws.simit.carga;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.13
 * 2016-03-28T16:24:11.003-05:00
 * Generated source version: 2.7.13
 * 
 */
@WebService(targetNamespace = "http://webservices.qxmultas.quipux.com.co", name = "WsCargaArchivosPortType")
@XmlSeeAlso({ObjectFactory.class, co.com.datatools.c2.clientes.ws.simit.carga.dto.ObjectFactory.class})
public interface WsCargaArchivosPortType {

    @WebResult(name = "return", targetNamespace = "http://webservices.qxmultas.quipux.com.co")
    @Action(input = "urn:cargaRecaudo", output = "urn:cargaRecaudoResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "urn:cargaRecaudoException")})
    @RequestWrapper(localName = "cargaRecaudo", targetNamespace = "http://webservices.qxmultas.quipux.com.co", className = "co.com.datatools.c2.simit.ws.cliente.CargaRecaudo")
    @WebMethod(action = "urn:cargaRecaudo")
    @ResponseWrapper(localName = "cargaRecaudoResponse", targetNamespace = "http://webservices.qxmultas.quipux.com.co", className = "co.com.datatools.c2.simit.ws.cliente.CargaRecaudoResponse")
    public java.lang.String cargaRecaudo(
        @WebParam(name = "recaudo", targetNamespace = "http://webservices.qxmultas.quipux.com.co")
        co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosRecaudo recaudo
    ) throws Exception_Exception;

    @WebResult(name = "return", targetNamespace = "http://webservices.qxmultas.quipux.com.co")
    @Action(input = "urn:cargaArchivoResolucion", output = "urn:cargaArchivoResolucionResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "urn:cargaArchivoResolucionException")})
    @RequestWrapper(localName = "cargaArchivoResolucion", targetNamespace = "http://webservices.qxmultas.quipux.com.co", className = "co.com.datatools.c2.simit.ws.cliente.CargaArchivoResolucion")
    @WebMethod(action = "urn:cargaArchivoResolucion")
    @ResponseWrapper(localName = "cargaArchivoResolucionResponse", targetNamespace = "http://webservices.qxmultas.quipux.com.co", className = "co.com.datatools.c2.simit.ws.cliente.CargaArchivoResolucionResponse")
    public java.lang.String cargaArchivoResolucion(
        @WebParam(name = "resolucion", targetNamespace = "http://webservices.qxmultas.quipux.com.co")
        co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosResolucion resolucion
    ) throws Exception_Exception;

    @WebResult(name = "return", targetNamespace = "http://webservices.qxmultas.quipux.com.co")
    @Action(input = "urn:cargaArchivoComparendos", output = "urn:cargaArchivoComparendosResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "urn:cargaArchivoComparendosException")})
    @RequestWrapper(localName = "cargaArchivoComparendos", targetNamespace = "http://webservices.qxmultas.quipux.com.co", className = "co.com.datatools.c2.simit.ws.cliente.CargaArchivoComparendos")
    @WebMethod(action = "urn:cargaArchivoComparendos")
    @ResponseWrapper(localName = "cargaArchivoComparendosResponse", targetNamespace = "http://webservices.qxmultas.quipux.com.co", className = "co.com.datatools.c2.simit.ws.cliente.CargaArchivoComparendosResponse")
    public java.lang.String cargaArchivoComparendos(
        @WebParam(name = "comparendo", targetNamespace = "http://webservices.qxmultas.quipux.com.co")
        co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosComparendo comparendo
    ) throws Exception_Exception;
}
