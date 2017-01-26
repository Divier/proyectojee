/**
 * WSHistorialConductorLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.simit.historialconductor;

public class WSHistorialConductorLocator extends org.apache.axis.client.Service implements
        co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductor {

    public WSHistorialConductorLocator() {
    }

    public WSHistorialConductorLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSHistorialConductorLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
            throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSHistorialConductor
    private java.lang.String WSHistorialConductor_address = "http://181.48.11.2:8383/Simit/services/WSHistorialConductor/";

    public java.lang.String getWSHistorialConductorAddress() {
        return WSHistorialConductor_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSHistorialConductorWSDDServiceName = "WSHistorialConductor";

    public java.lang.String getWSHistorialConductorWSDDServiceName() {
        return WSHistorialConductorWSDDServiceName;
    }

    public void setWSHistorialConductorWSDDServiceName(java.lang.String name) {
        WSHistorialConductorWSDDServiceName = name;
    }

    public co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorImpl getWSHistorialConductor()
            throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSHistorialConductor_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSHistorialConductor(endpoint);
    }

    public co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorImpl getWSHistorialConductor(
            java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorSoapBindingStub _stub = new co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorSoapBindingStub(
                    portAddress, this);
            _stub.setPortName(getWSHistorialConductorWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSHistorialConductorEndpointAddress(java.lang.String address) {
        WSHistorialConductor_address = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorImpl.class
                    .isAssignableFrom(serviceEndpointInterface)) {
                co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorSoapBindingStub _stub = new co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorSoapBindingStub(
                        new java.net.URL(WSHistorialConductor_address), this);
                _stub.setPortName(getWSHistorialConductorWSDDServiceName());
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
        if ("WSHistorialConductor".equals(inputPortName)) {
            return getWSHistorialConductor();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.simit.org.co/Simit/services/WSHistorialConductor",
                "WSHistorialConductor");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.simit.org.co/Simit/services/WSHistorialConductor",
                    "WSHistorialConductor"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address)
            throws javax.xml.rpc.ServiceException {

        if ("WSHistorialConductor".equals(portName)) {
            setWSHistorialConductorEndpointAddress(address);
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
