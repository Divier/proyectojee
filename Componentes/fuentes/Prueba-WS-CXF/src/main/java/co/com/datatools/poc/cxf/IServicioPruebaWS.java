package co.com.datatools.poc.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(
        name = "IServicioPruebaWS",
        targetNamespace = "http://suma.ws.poc.c2.datatools.com.co/")
public interface IServicioPruebaWS {
	
	@WebMethod(operationName = "suma", action = "urn:Suma")
	public int suma (@WebParam(name = "x") Integer x, @WebParam(name = "y") Integer y);
	
}
