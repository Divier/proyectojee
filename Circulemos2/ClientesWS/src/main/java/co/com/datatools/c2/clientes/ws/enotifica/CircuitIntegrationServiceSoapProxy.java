package co.com.datatools.c2.clientes.ws.enotifica;

public class CircuitIntegrationServiceSoapProxy implements co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap {
  private String _endpoint = null;
  private co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap circuitIntegrationServiceSoap = null;
  
  public CircuitIntegrationServiceSoapProxy() {
    _initCircuitIntegrationServiceSoapProxy();
  }
  
  public CircuitIntegrationServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initCircuitIntegrationServiceSoapProxy();
  }
  
  private void _initCircuitIntegrationServiceSoapProxy() {
    try {
      circuitIntegrationServiceSoap = (new co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceLocator()).getCircuitIntegrationServiceSoap();
      if (circuitIntegrationServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)circuitIntegrationServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)circuitIntegrationServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (circuitIntegrationServiceSoap != null)
      ((javax.xml.rpc.Stub)circuitIntegrationServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap getCircuitIntegrationServiceSoap() {
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap;
  }
  
  public java.lang.String logonbyUserName(java.lang.String appId, java.lang.String userName, java.lang.String password, java.lang.String cifCompany) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.logonbyUserName(appId, userName, password, cifCompany);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse createCircuitFullDocument(java.lang.String userToken, java.lang.String applicationType, java.lang.String circuitTitle, co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument[] document, boolean publishOnFinish, co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver[] circuitReceivers, boolean hideCreator, boolean alertfinish, boolean encryption, boolean graphics, boolean face, java.lang.String externalAppCircuitId, boolean notReceiversCircuits, java.lang.String batch, co.com.datatools.c2.clientes.ws.enotifica.MetadataBean[] metadataCircuit) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.createCircuitFullDocument(userToken, applicationType, circuitTitle, document, publishOnFinish, circuitReceivers, hideCreator, alertfinish, encryption, graphics, face, externalAppCircuitId, notReceiversCircuits, batch, metadataCircuit);
  }
  
  public boolean finishOperationId(java.lang.String userToken, java.lang.String operationId, int resultCode, java.lang.String obs) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.finishOperationId(userToken, operationId, resultCode, obs);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CircuitPropertiesResponse getCircuits(java.lang.String userToken) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getCircuits(userToken);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationResponse getConfigurationCircuit(java.lang.String userToken, java.lang.String idcircuit) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getConfigurationCircuit(userToken, idcircuit);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CircuitSearchContactResponse searchContact(java.lang.String userToken, java.lang.String search, boolean excludeTokenUser) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.searchContact(userToken, search, excludeTokenUser);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.OperationsCircuitResponse getAllOperationsByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getAllOperationsByExternalId(userToken, externalId);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CircuitisfinishResponse circuitIsFinishByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.circuitIsFinishByExternalId(userToken, externalId);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse get_documentOfCircuitByExternalId(java.lang.String userToken, java.lang.String externalId, boolean onlyAttach) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.get_documentOfCircuitByExternalId(userToken, externalId, onlyAttach);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CancelCircuitResponse cancelCircuitByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.cancelCircuitByExternalId(userToken, externalId);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse getEvidenceCircuitByExternalId(java.lang.String userToken, java.lang.String externalId, boolean ispdf, java.lang.String tasks) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getEvidenceCircuitByExternalId(userToken, externalId, ispdf, tasks);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse get_documentByExternalId(java.lang.String userToken, java.lang.String externalIdDocument, java.lang.String externalIdCircuit) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.get_documentByExternalId(userToken, externalIdDocument, externalIdCircuit);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse getEvidenceCircuit(java.lang.String userToken, java.lang.String idcircuit, boolean ispdf, java.lang.String tasks) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getEvidenceCircuit(userToken, idcircuit, ispdf, tasks);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.OperationExpiredResponse closeOperationsExpired(java.lang.String pkey, java.lang.String entorno) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.closeOperationsExpired(pkey, entorno);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse createCircuitMultiDocument(java.lang.String userToken, java.lang.String applicationType, java.lang.String circuitTitle, co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument[] document, boolean publishOnFinish, co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver[] circuitReceivers, boolean hideCreator, boolean alertfinish, boolean encryption, boolean graphics, boolean face, java.lang.String externalAppCircuitId) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.createCircuitMultiDocument(userToken, applicationType, circuitTitle, document, publishOnFinish, circuitReceivers, hideCreator, alertfinish, encryption, graphics, face, externalAppCircuitId);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse reminderOperationByExternalId(java.lang.String userToken, java.lang.String externalIdOperation) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.reminderOperationByExternalId(userToken, externalIdOperation);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse reminderCircuitByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.reminderCircuitByExternalId(userToken, externalId);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse getAllCircuitsModifiedByDate(java.lang.String userToken, java.lang.String appId) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getAllCircuitsModifiedByDate(userToken, appId);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse getAllCircuitsBatchIdModifiedByDate(java.lang.String userToken, java.lang.String appId, java.lang.String batchid) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getAllCircuitsBatchIdModifiedByDate(userToken, appId, batchid);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsBatchIdModifiedByDateUsertoken(java.lang.String userToken, java.lang.String appId, java.lang.String batchid) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getAllOperationsBatchIdModifiedByDateUsertoken(userToken, appId, batchid);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsByExternalCircuitUsertoken(java.lang.String userToken, java.lang.String externalid) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getAllOperationsByExternalCircuitUsertoken(userToken, externalid);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsBatchIdModifiedByDate(java.lang.String appId, java.lang.String userName, java.lang.String password, java.lang.String batchid) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getAllOperationsBatchIdModifiedByDate(appId, userName, password, batchid);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsBatchIdModifiedBetweenDates(java.lang.String appId, java.lang.String userName, java.lang.String password, java.lang.String batchid, java.lang.String dateFrom, java.lang.String dateTo) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getAllOperationsBatchIdModifiedBetweenDates(appId, userName, password, batchid, dateFrom, dateTo);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse getPDFFromEvidence(java.lang.String userToken, java.lang.String evidenceId) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getPDFFromEvidence(userToken, evidenceId);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.OperationEvidenceResponse getOperationEvidence(java.lang.String userToken, java.lang.String operationId) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getOperationEvidence(userToken, operationId);
  }
  
  public co.com.datatools.c2.clientes.ws.enotifica.CircuitEvidenceResponse getCircuitEvidence(java.lang.String userToken, java.lang.String idcircuit) throws java.rmi.RemoteException{
    if (circuitIntegrationServiceSoap == null)
      _initCircuitIntegrationServiceSoapProxy();
    return circuitIntegrationServiceSoap.getCircuitEvidence(userToken, idcircuit);
  }
  
  
}