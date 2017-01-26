package co.com.datatools.c2.clientes.ws.enotifica.tempuri;

public class IServerSignatureServiceProxy implements co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService {
  private String _endpoint = null;
  private co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService iServerSignatureService = null;
  
  public IServerSignatureServiceProxy() {
    _initIServerSignatureServiceProxy();
  }
  
  public IServerSignatureServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIServerSignatureServiceProxy();
  }
  
  private void _initIServerSignatureServiceProxy() {
    try {
      iServerSignatureService = (new co.com.datatools.c2.clientes.ws.enotifica.tempuri.ServerSignatureServiceLocator()).getBasicHttpBinding_IServerSignatureService();
      if (iServerSignatureService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iServerSignatureService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iServerSignatureService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iServerSignatureService != null)
      ((javax.xml.rpc.Stub)iServerSignatureService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService getIServerSignatureService() {
    if (iServerSignatureService == null)
      _initIServerSignatureServiceProxy();
    return iServerSignatureService;
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.CertReference[] getUserCertificateRefs(java.lang.String userId, java.lang.String password) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault{
    if (iServerSignatureService == null)
      _initIServerSignatureServiceProxy();
    return iServerSignatureService.getUserCertificateRefs(userId, password);
  }
  
  public byte[] sign(java.lang.String userId, java.lang.String password, java.lang.String idCertificate, java.lang.String certPin, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile signatureProfile, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType signatureType, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm, java.lang.String[] options, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams parameters, byte[] detachedSignature, byte[] signingDocument) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault{
    if (iServerSignatureService == null)
      _initIServerSignatureServiceProxy();
    return iServerSignatureService.sign(userId, password, idCertificate, certPin, signatureProfile, signatureType, hashAlgorithm, options, parameters, detachedSignature, signingDocument);
  }
  
  public byte[] encrypt(java.lang.String userId, java.lang.String password, java.lang.String idCertificate, java.lang.String certPin, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm, byte[] dataToEncrypt) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault{
    if (iServerSignatureService == null)
      _initIServerSignatureServiceProxy();
    return iServerSignatureService.encrypt(userId, password, idCertificate, certPin, hashAlgorithm, dataToEncrypt);
  }
  
  public byte[] signByToken(java.lang.String token, java.lang.String idCertificate, java.lang.String certPin, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile signatureProfile, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType signatureType, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm, java.lang.String[] options, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams parameters, byte[] detachedSignature, byte[] signingDocument) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault{
    if (iServerSignatureService == null)
      _initIServerSignatureServiceProxy();
    return iServerSignatureService.signByToken(token, idCertificate, certPin, signatureProfile, signatureType, hashAlgorithm, options, parameters, detachedSignature, signingDocument);
  }
  
  public byte[] signProvider(java.lang.Integer idCertificate, java.lang.String uri, java.lang.String providerParameter, byte[] signingDocument) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault{
    if (iServerSignatureService == null)
      _initIServerSignatureServiceProxy();
    return iServerSignatureService.signProvider(idCertificate, uri, providerParameter, signingDocument);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureValidation verify(java.lang.String userId, java.lang.String password, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile signatureProfile, java.lang.String[] options, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.VerifyParams parameters, byte[] detachedSignature, byte[] document) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault{
    if (iServerSignatureService == null)
      _initIServerSignatureServiceProxy();
    return iServerSignatureService.verify(userId, password, signatureProfile, options, parameters, detachedSignature, document);
  }
  
  
}