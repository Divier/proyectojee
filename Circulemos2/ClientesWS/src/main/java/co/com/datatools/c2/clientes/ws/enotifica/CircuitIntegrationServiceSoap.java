/**
 * CircuitIntegrationServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public interface CircuitIntegrationServiceSoap extends java.rmi.Remote {

    /**
     * Logon para el uso de las operaciones del servicio web. cifCompany:
     * Opcional. Si el usuario pertenece a varias empresas, el login se realizará
     * con la indicada en este parámetro.
     */
    public java.lang.String logonbyUserName(java.lang.String appId, java.lang.String userName, java.lang.String password, java.lang.String cifCompany) throws java.rmi.RemoteException;

    /**
     * Crea un circuito con la posibilidad de crear adjuntos
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse createCircuitFullDocument(java.lang.String userToken, java.lang.String applicationType, java.lang.String circuitTitle, co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument[] document, boolean publishOnFinish, co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver[] circuitReceivers, boolean hideCreator, boolean alertfinish, boolean encryption, boolean graphics, boolean face, java.lang.String externalAppCircuitId, boolean notReceiversCircuits, java.lang.String batch, co.com.datatools.c2.clientes.ws.enotifica.MetadataBean[] metadataCircuit) throws java.rmi.RemoteException;

    /**
     * Finaliza una operación sobre un circuito y desencada las siguientes
     * (si tiene)
     */
    public boolean finishOperationId(java.lang.String userToken, java.lang.String operationId, int resultCode, java.lang.String obs) throws java.rmi.RemoteException;

    /**
     * Devuelve el listado de circuitos que están activos para el
     * usuario
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CircuitPropertiesResponse getCircuits(java.lang.String userToken) throws java.rmi.RemoteException;

    /**
     * Obtiene la configuración de un circuito
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationResponse getConfigurationCircuit(java.lang.String userToken, java.lang.String idcircuit) throws java.rmi.RemoteException;

    /**
     * Devuelve listado de contactos a los que un usuario puede enviar
     * un circuito
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CircuitSearchContactResponse searchContact(java.lang.String userToken, java.lang.String search, boolean excludeTokenUser) throws java.rmi.RemoteException;

    /**
     * Devuelve el listado de tareas de un circuito a través del ExternalId
     */
    public co.com.datatools.c2.clientes.ws.enotifica.OperationsCircuitResponse getAllOperationsByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException;

    /**
     * Devuelve si un circuito está finalizado y la descripción de
     * su estado actual
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CircuitisfinishResponse circuitIsFinishByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException;

    /**
     * Recupera el documento actual
     */
    public co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse get_documentOfCircuitByExternalId(java.lang.String userToken, java.lang.String externalId, boolean onlyAttach) throws java.rmi.RemoteException;

    /**
     * Cancela un circuito
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CancelCircuitResponse cancelCircuitByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException;

    /**
     * Devuelve el PDF de las evidencias sobre un circuito
     */
    public co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse getEvidenceCircuitByExternalId(java.lang.String userToken, java.lang.String externalId, boolean ispdf, java.lang.String tasks) throws java.rmi.RemoteException;

    /**
     * Recupera el documento actual
     */
    public co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse get_documentByExternalId(java.lang.String userToken, java.lang.String externalIdDocument, java.lang.String externalIdCircuit) throws java.rmi.RemoteException;

    /**
     * Devuelve el XML/PDF de las evidencias sobre un circuito
     */
    public co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse getEvidenceCircuit(java.lang.String userToken, java.lang.String idcircuit, boolean ispdf, java.lang.String tasks) throws java.rmi.RemoteException;

    /**
     * Cambia el estado a todas las operaciones caducadas
     */
    public co.com.datatools.c2.clientes.ws.enotifica.OperationExpiredResponse closeOperationsExpired(java.lang.String pkey, java.lang.String entorno) throws java.rmi.RemoteException;

    /**
     * Crea un circuito con multi-documentos
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse createCircuitMultiDocument(java.lang.String userToken, java.lang.String applicationType, java.lang.String circuitTitle, co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument[] document, boolean publishOnFinish, co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver[] circuitReceivers, boolean hideCreator, boolean alertfinish, boolean encryption, boolean graphics, boolean face, java.lang.String externalAppCircuitId) throws java.rmi.RemoteException;

    /**
     * Reenvía el correo de operación pendiente al usuario
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse reminderOperationByExternalId(java.lang.String userToken, java.lang.String externalIdOperation) throws java.rmi.RemoteException;

    /**
     * Reenvía el correo a todas las operaciones pendientes del circuito
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse reminderCircuitByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException;

    /**
     * Devuelve todos los circuitos que se han modificado desde la
     * última petición. La primera vez los devolverá todos.
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse getAllCircuitsModifiedByDate(java.lang.String userToken, java.lang.String appId) throws java.rmi.RemoteException;

    /**
     * Devuelve todos los circuitos de un lote que se han modificado
     * desde la última petición. La primera vez los devolverá todos.
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse getAllCircuitsBatchIdModifiedByDate(java.lang.String userToken, java.lang.String appId, java.lang.String batchid) throws java.rmi.RemoteException;

    /**
     * Devuelve el listado de operaciones de un lote que se han modificado
     * desde la última petición.
     */
    public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsBatchIdModifiedByDateUsertoken(java.lang.String userToken, java.lang.String appId, java.lang.String batchid) throws java.rmi.RemoteException;

    /**
     * Devuelve el listado de operaciones de un externalid.
     */
    public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsByExternalCircuitUsertoken(java.lang.String userToken, java.lang.String externalid) throws java.rmi.RemoteException;

    /**
     * Devuelve el listado de operaciones de un lote que se han modificado
     * desde la última petición pasándole las credenciales del usuario.
     */
    public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsBatchIdModifiedByDate(java.lang.String appId, java.lang.String userName, java.lang.String password, java.lang.String batchid) throws java.rmi.RemoteException;

    /**
     * Devuelve el listado de operaciones de un lote que se han modificado
     * entre un rango de fechas pasándole las credenciales del usuario.
     */
    public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsBatchIdModifiedBetweenDates(java.lang.String appId, java.lang.String userName, java.lang.String password, java.lang.String batchid, java.lang.String dateFrom, java.lang.String dateTo) throws java.rmi.RemoteException;

    /**
     * Obtiene evidencia en formato PDF a través de ID de evidencia
     */
    public co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse getPDFFromEvidence(java.lang.String userToken, java.lang.String evidenceId) throws java.rmi.RemoteException;

    /**
     * Obtiene todas las evidencias de una operación
     */
    public co.com.datatools.c2.clientes.ws.enotifica.OperationEvidenceResponse getOperationEvidence(java.lang.String userToken, java.lang.String operationId) throws java.rmi.RemoteException;

    /**
     * Obtiene todas las evidencias de un circuito
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CircuitEvidenceResponse getCircuitEvidence(java.lang.String userToken, java.lang.String idcircuit) throws java.rmi.RemoteException;
}
