package co.com.datatools.c2.clientes.ws.simit.historialconductor;

public class WSHistorialConductorImplProxy implements co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorImpl {
  private String _endpoint = null;
  private co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorImpl wSHistorialConductorImpl = null;
  
  public WSHistorialConductorImplProxy() {
    _initWSHistorialConductorImplProxy();
  }
  
  public WSHistorialConductorImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSHistorialConductorImplProxy();
  }
  
  private void _initWSHistorialConductorImplProxy() {
    try {
      wSHistorialConductorImpl = (new co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorLocator()).getWSHistorialConductor();
      if (wSHistorialConductorImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSHistorialConductorImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSHistorialConductorImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSHistorialConductorImpl != null)
      ((javax.xml.rpc.Stub)wSHistorialConductorImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorImpl getWSHistorialConductorImpl() {
    if (wSHistorialConductorImpl == null)
      _initWSHistorialConductorImplProxy();
    return wSHistorialConductorImpl;
  }
  
  public java.lang.String getHistorialConductor(java.lang.String idFuncionario, java.lang.String clave, java.lang.String idSecretaria, int idTipoDocumento, java.lang.String idContraventor) throws java.rmi.RemoteException{
    if (wSHistorialConductorImpl == null)
      _initWSHistorialConductorImplProxy();
    return wSHistorialConductorImpl.getHistorialConductor(idFuncionario, clave, idSecretaria, idTipoDocumento, idContraventor);
  }
  
  
}