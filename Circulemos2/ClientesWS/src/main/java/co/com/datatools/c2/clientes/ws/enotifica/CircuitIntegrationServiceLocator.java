/**
 * CircuitIntegrationServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class CircuitIntegrationServiceLocator extends org.apache.axis.client.Service
        implements co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationService {

    public CircuitIntegrationServiceLocator() {
    }

    public CircuitIntegrationServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CircuitIntegrationServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
            throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CircuitIntegrationServiceSoap
    private java.lang.String CircuitIntegrationServiceSoap_address = null;

    public java.lang.String getCircuitIntegrationServiceSoapAddress() {
        return CircuitIntegrationServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CircuitIntegrationServiceSoapWSDDServiceName = "CircuitIntegrationServiceSoap";

    public java.lang.String getCircuitIntegrationServiceSoapWSDDServiceName() {
        return CircuitIntegrationServiceSoapWSDDServiceName;
    }

    public void setCircuitIntegrationServiceSoapWSDDServiceName(java.lang.String name) {
        CircuitIntegrationServiceSoapWSDDServiceName = name;
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap getCircuitIntegrationServiceSoap()
            throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CircuitIntegrationServiceSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCircuitIntegrationServiceSoap(endpoint);
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap getCircuitIntegrationServiceSoap(
            java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoapStub _stub = new co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoapStub(
                    portAddress, this);
            _stub.setPortName(getCircuitIntegrationServiceSoapWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCircuitIntegrationServiceSoapEndpointAddress(java.lang.String address) {
        CircuitIntegrationServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap.class
                    .isAssignableFrom(serviceEndpointInterface)) {
                co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoapStub _stub = new co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoapStub(
                        new java.net.URL(CircuitIntegrationServiceSoap_address), this);
                _stub.setPortName(getCircuitIntegrationServiceSoapWSDDServiceName());
                return _stub;
            }
        } catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
                + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation. If this service has no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
            throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CircuitIntegrationServiceSoap".equals(inputPortName)) {
            return getCircuitIntegrationServiceSoap();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitIntegrationService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices",
                    "CircuitIntegrationServiceSoap"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address)
            throws javax.xml.rpc.ServiceException {

        if ("CircuitIntegrationServiceSoap".equals(portName)) {
            setCircuitIntegrationServiceSoapEndpointAddress(address);
        } else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address)
            throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
