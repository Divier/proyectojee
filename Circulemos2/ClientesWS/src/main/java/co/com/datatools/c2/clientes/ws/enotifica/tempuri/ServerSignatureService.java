/**
 * ServerSignatureService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.tempuri;

public interface ServerSignatureService extends javax.xml.rpc.Service {
    public java.lang.String getBasicHttpBinding_IServerSignatureServiceAddress();

    public co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService getBasicHttpBinding_IServerSignatureService()
            throws javax.xml.rpc.ServiceException;

    public co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService getBasicHttpBinding_IServerSignatureService(
            java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

    public void setBasicHttpBinding_IServerSignatureServiceEndpointAddress(java.lang.String address);
}