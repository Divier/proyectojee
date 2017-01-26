/**
 * CircuitIntegrationService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public interface CircuitIntegrationService extends javax.xml.rpc.Service {
    public java.lang.String getCircuitIntegrationServiceSoapAddress();

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap getCircuitIntegrationServiceSoap()
            throws javax.xml.rpc.ServiceException;

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap getCircuitIntegrationServiceSoap(
            java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

    public void setCircuitIntegrationServiceSoapEndpointAddress(String address);
}
