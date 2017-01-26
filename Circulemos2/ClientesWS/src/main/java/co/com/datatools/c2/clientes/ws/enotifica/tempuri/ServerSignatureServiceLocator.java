/**
 * ServerSignatureServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.tempuri;

public class ServerSignatureServiceLocator extends org.apache.axis.client.Service
        implements co.com.datatools.c2.clientes.ws.enotifica.tempuri.ServerSignatureService {

    public ServerSignatureServiceLocator() {
    }

    public ServerSignatureServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServerSignatureServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
            throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IServerSignatureService
    private java.lang.String BasicHttpBinding_IServerSignatureService_address = null;

    public java.lang.String getBasicHttpBinding_IServerSignatureServiceAddress() {
        return BasicHttpBinding_IServerSignatureService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IServerSignatureServiceWSDDServiceName = "BasicHttpBinding_IServerSignatureService";

    public java.lang.String getBasicHttpBinding_IServerSignatureServiceWSDDServiceName() {
        return BasicHttpBinding_IServerSignatureServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IServerSignatureServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IServerSignatureServiceWSDDServiceName = name;
    }

    public co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService getBasicHttpBinding_IServerSignatureService()
            throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IServerSignatureService_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IServerSignatureService(endpoint);
    }

    public co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService getBasicHttpBinding_IServerSignatureService(
            java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.com.datatools.c2.clientes.ws.enotifica.tempuri.BasicHttpBinding_IServerSignatureServiceStub _stub = new co.com.datatools.c2.clientes.ws.enotifica.tempuri.BasicHttpBinding_IServerSignatureServiceStub(
                    portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IServerSignatureServiceWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IServerSignatureServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IServerSignatureService_address = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService.class
                    .isAssignableFrom(serviceEndpointInterface)) {
                co.com.datatools.c2.clientes.ws.enotifica.tempuri.BasicHttpBinding_IServerSignatureServiceStub _stub = new co.com.datatools.c2.clientes.ws.enotifica.tempuri.BasicHttpBinding_IServerSignatureServiceStub(
                        new java.net.URL(BasicHttpBinding_IServerSignatureService_address), this);
                _stub.setPortName(getBasicHttpBinding_IServerSignatureServiceWSDDServiceName());
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
        if ("BasicHttpBinding_IServerSignatureService".equals(inputPortName)) {
            return getBasicHttpBinding_IServerSignatureService();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ServerSignatureService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IServerSignatureService"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address)
            throws javax.xml.rpc.ServiceException {

        if ("BasicHttpBinding_IServerSignatureService".equals(portName)) {
            setBasicHttpBinding_IServerSignatureServiceEndpointAddress(address);
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
