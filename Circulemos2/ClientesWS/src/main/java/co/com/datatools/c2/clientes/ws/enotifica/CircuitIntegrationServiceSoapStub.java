/**
 * CircuitIntegrationServiceSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class CircuitIntegrationServiceSoapStub extends org.apache.axis.client.Stub implements co.com.datatools.c2.clientes.ws.enotifica.CircuitIntegrationServiceSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[26];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LogonbyUserName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "appId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "cifCompany"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "LogonbyUserNameResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateCircuitFullDocument");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "applicationType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "circuitTitle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "document"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitDocument"), co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitDocument"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "publishOnFinish"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "circuitReceivers"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitReceiver"), co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitReceiver"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "hideCreator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "alertfinish"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "encryption"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "graphics"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "face"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "externalAppCircuitId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "notReceiversCircuits"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "batch"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "metadataCircuit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfMetadataBean"), co.com.datatools.c2.clientes.ws.enotifica.MetadataBean[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "MetadataBean"));
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CreationCircuitResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CreateCircuitFullDocumentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("FinishOperationId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "resultCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "obs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "FinishOperationIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetCircuits");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitPropertiesResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CircuitPropertiesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetCircuitsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetConfigurationCircuit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "idcircuit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitConfigurationResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetConfigurationCircuitResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchContact");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "search"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "excludeTokenUser"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitSearchContactResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CircuitSearchContactResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "SearchContactResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllOperationsByExternalId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationsCircuitResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.OperationsCircuitResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsByExternalIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CircuitIsFinishByExternalId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitisfinishResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CircuitisfinishResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitIsFinishByExternalIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("get_documentOfCircuitByExternalId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "onlyAttach"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "DocumentOfCircuitResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "get_documentOfCircuitByExternalIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelCircuitByExternalId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CancelCircuitResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CancelCircuitResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "cancelCircuitByExternalIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getEvidenceCircuitByExternalId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ispdf"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "tasks"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "EvidenceResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getEvidenceCircuitByExternalIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("get_documentByExternalId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalIdDocument"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalIdCircuit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "DocumentOfCircuitResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "get_documentByExternalIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getEvidenceCircuit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "idcircuit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ispdf"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "tasks"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "EvidenceResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getEvidenceCircuitResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("closeOperationsExpired");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "pkey"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "entorno"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationExpiredResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.OperationExpiredResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "closeOperationsExpiredResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateCircuitMultiDocument");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "applicationType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "circuitTitle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "document"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitDocument"), co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitDocument"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "publishOnFinish"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "circuitReceivers"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitReceiver"), co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitReceiver"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "hideCreator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "alertfinish"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "encryption"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "graphics"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "face"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "externalAppCircuitId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CreationCircuitResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CreateCircuitMultiDocumentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReminderOperationByExternalId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalIdOperation"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CommonResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ReminderOperationByExternalIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReminderCircuitByExternalId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CommonResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ReminderCircuitByExternalIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllCircuitsModifiedByDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "appId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitQueryResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllCircuitsModifiedByDateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllCircuitsBatchIdModifiedByDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "appId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "batchid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitQueryResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllCircuitsBatchIdModifiedByDateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllOperationsBatchIdModifiedByDateUsertoken");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "appId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "batchid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationsBatchIdQueryResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsBatchIdModifiedByDateUsertokenResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllOperationsByExternalCircuitUsertoken");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "externalid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationsBatchIdQueryResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsByExternalCircuitUsertokenResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllOperationsBatchIdModifiedByDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "appId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "batchid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationsBatchIdQueryResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsBatchIdModifiedByDateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllOperationsBatchIdModifiedBetweenDates");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "appId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "batchid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "dateFrom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "dateTo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationsBatchIdQueryResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsBatchIdModifiedBetweenDatesResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetPDFFromEvidence");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "evidenceId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "EvidenceResultResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetPDFFromEvidenceResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetOperationEvidence");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operationId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationEvidenceResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.OperationEvidenceResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetOperationEvidenceResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetCircuitEvidence");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "idcircuit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitEvidenceResponse"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.CircuitEvidenceResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetCircuitEvidenceResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

    }

    public CircuitIntegrationServiceSoapStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public CircuitIntegrationServiceSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public CircuitIntegrationServiceSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitCompaniesProperties");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitCompaniesProperties[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitCompaniesProperties");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitCompaniesProperties");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitConfigurationProperties");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationProperties[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitConfigurationProperties");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitConfigurationProperties");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitContactProperties");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitContactProperties[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitContactProperties");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitContactProperties");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitDocument");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitDocument");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitDocument");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitProperties");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitProperties[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitProperties");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitProperties");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCircuitReceiver");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitReceiver");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitReceiver");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCo_circuit_query_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Co_circuit_query_bean[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_circuit_query_bean");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_circuit_query_bean");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCo_document_operation_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Co_document_operation_bean[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_document_operation_bean");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_document_operation_bean");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfCo_operations_query_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Co_operations_query_bean[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_operations_query_bean");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_operations_query_bean");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfDocumentCircuit");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.DocumentCircuit[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "DocumentCircuit");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "DocumentCircuit");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfEvidence_operation_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Evidence_operation_bean[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "evidence_operation_bean");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "evidence_operation_bean");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfMetadataBean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.MetadataBean[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "MetadataBean");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "MetadataBean");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ArrayOfOperation_evidence_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Operation_evidence_bean[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operation_evidence_bean");
            qName2 = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operation_evidence_bean");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CancelCircuitResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CancelCircuitResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitCompaniesProperties");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitCompaniesProperties.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitConfigurationProperties");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationProperties.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitConfigurationResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitContactProperties");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitContactProperties.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitDocument");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitEvidenceResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitEvidenceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitisfinishResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitisfinishResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitProperties");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitProperties.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitPropertiesResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitPropertiesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitQueryResultResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitReceiver");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitSearchContactResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CircuitSearchContactResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_circuit_query_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Co_circuit_query_bean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_document_operation_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Co_document_operation_bean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_document_operation_ExtendedProps_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Co_document_operation_ExtendedProps_bean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_operations_query_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Co_operations_query_bean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CommonResultResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CreationCircuitResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "DocumentCircuit");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.DocumentCircuit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "DocumentOfCircuitResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "DocumentType");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.DocumentType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "evidence_operation_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Evidence_operation_bean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "EvidenceResultResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "MetadataBean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.MetadataBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operation_evidence_bean");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.Operation_evidence_bean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationEvidenceResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.OperationEvidenceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationExpiredResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.OperationExpiredResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationsBatchIdQueryResultResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationsCircuitResponse");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.OperationsCircuitResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.String logonbyUserName(java.lang.String appId, java.lang.String userName, java.lang.String password, java.lang.String cifCompany) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/LogonbyUserName");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "LogonbyUserName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {appId, userName, password, cifCompany});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse createCircuitFullDocument(java.lang.String userToken, java.lang.String applicationType, java.lang.String circuitTitle, co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument[] document, boolean publishOnFinish, co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver[] circuitReceivers, boolean hideCreator, boolean alertfinish, boolean encryption, boolean graphics, boolean face, java.lang.String externalAppCircuitId, boolean notReceiversCircuits, java.lang.String batch, co.com.datatools.c2.clientes.ws.enotifica.MetadataBean[] metadataCircuit) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/CreateCircuitFullDocument");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CreateCircuitFullDocument"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, applicationType, circuitTitle, document, new java.lang.Boolean(publishOnFinish), circuitReceivers, new java.lang.Boolean(hideCreator), new java.lang.Boolean(alertfinish), new java.lang.Boolean(encryption), new java.lang.Boolean(graphics), new java.lang.Boolean(face), externalAppCircuitId, new java.lang.Boolean(notReceiversCircuits), batch, metadataCircuit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public boolean finishOperationId(java.lang.String userToken, java.lang.String operationId, int resultCode, java.lang.String obs) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/FinishOperationId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "FinishOperationId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, operationId, new java.lang.Integer(resultCode), obs});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitPropertiesResponse getCircuits(java.lang.String userToken) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/GetCircuits");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetCircuits"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitPropertiesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitPropertiesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CircuitPropertiesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationResponse getConfigurationCircuit(java.lang.String userToken, java.lang.String idcircuit) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/GetConfigurationCircuit");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetConfigurationCircuit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, idcircuit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CircuitConfigurationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitSearchContactResponse searchContact(java.lang.String userToken, java.lang.String search, boolean excludeTokenUser) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/SearchContact");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "SearchContact"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, search, new java.lang.Boolean(excludeTokenUser)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitSearchContactResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitSearchContactResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CircuitSearchContactResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.OperationsCircuitResponse getAllOperationsByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/getAllOperationsByExternalId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsByExternalId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, externalId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsCircuitResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsCircuitResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.OperationsCircuitResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitisfinishResponse circuitIsFinishByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/CircuitIsFinishByExternalId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitIsFinishByExternalId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, externalId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitisfinishResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitisfinishResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CircuitisfinishResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse get_documentOfCircuitByExternalId(java.lang.String userToken, java.lang.String externalId, boolean onlyAttach) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/get_documentOfCircuitByExternalId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "get_documentOfCircuitByExternalId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, externalId, new java.lang.Boolean(onlyAttach)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CancelCircuitResponse cancelCircuitByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/cancelCircuitByExternalId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "cancelCircuitByExternalId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, externalId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CancelCircuitResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CancelCircuitResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CancelCircuitResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse getEvidenceCircuitByExternalId(java.lang.String userToken, java.lang.String externalId, boolean ispdf, java.lang.String tasks) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/getEvidenceCircuitByExternalId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getEvidenceCircuitByExternalId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, externalId, new java.lang.Boolean(ispdf), tasks});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse get_documentByExternalId(java.lang.String userToken, java.lang.String externalIdDocument, java.lang.String externalIdCircuit) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/get_documentByExternalId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "get_documentByExternalId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, externalIdDocument, externalIdCircuit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.DocumentOfCircuitResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse getEvidenceCircuit(java.lang.String userToken, java.lang.String idcircuit, boolean ispdf, java.lang.String tasks) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/getEvidenceCircuit");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getEvidenceCircuit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, idcircuit, new java.lang.Boolean(ispdf), tasks});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.OperationExpiredResponse closeOperationsExpired(java.lang.String pkey, java.lang.String entorno) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/closeOperationsExpired");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "closeOperationsExpired"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pkey, entorno});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationExpiredResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationExpiredResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.OperationExpiredResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse createCircuitMultiDocument(java.lang.String userToken, java.lang.String applicationType, java.lang.String circuitTitle, co.com.datatools.c2.clientes.ws.enotifica.CircuitDocument[] document, boolean publishOnFinish, co.com.datatools.c2.clientes.ws.enotifica.CircuitReceiver[] circuitReceivers, boolean hideCreator, boolean alertfinish, boolean encryption, boolean graphics, boolean face, java.lang.String externalAppCircuitId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/CreateCircuitMultiDocument");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CreateCircuitMultiDocument"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, applicationType, circuitTitle, document, new java.lang.Boolean(publishOnFinish), circuitReceivers, new java.lang.Boolean(hideCreator), new java.lang.Boolean(alertfinish), new java.lang.Boolean(encryption), new java.lang.Boolean(graphics), new java.lang.Boolean(face), externalAppCircuitId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CreationCircuitResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse reminderOperationByExternalId(java.lang.String userToken, java.lang.String externalIdOperation) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/ReminderOperationByExternalId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ReminderOperationByExternalId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, externalIdOperation});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse reminderCircuitByExternalId(java.lang.String userToken, java.lang.String externalId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/ReminderCircuitByExternalId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ReminderCircuitByExternalId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, externalId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CommonResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse getAllCircuitsModifiedByDate(java.lang.String userToken, java.lang.String appId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/getAllCircuitsModifiedByDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllCircuitsModifiedByDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, appId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse getAllCircuitsBatchIdModifiedByDate(java.lang.String userToken, java.lang.String appId, java.lang.String batchid) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/getAllCircuitsBatchIdModifiedByDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllCircuitsBatchIdModifiedByDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, appId, batchid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CircuitQueryResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsBatchIdModifiedByDateUsertoken(java.lang.String userToken, java.lang.String appId, java.lang.String batchid) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/getAllOperationsBatchIdModifiedByDateUsertoken");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsBatchIdModifiedByDateUsertoken"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, appId, batchid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsByExternalCircuitUsertoken(java.lang.String userToken, java.lang.String externalid) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/getAllOperationsByExternalCircuitUsertoken");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsByExternalCircuitUsertoken"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, externalid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsBatchIdModifiedByDate(java.lang.String appId, java.lang.String userName, java.lang.String password, java.lang.String batchid) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/getAllOperationsBatchIdModifiedByDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsBatchIdModifiedByDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {appId, userName, password, batchid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse getAllOperationsBatchIdModifiedBetweenDates(java.lang.String appId, java.lang.String userName, java.lang.String password, java.lang.String batchid, java.lang.String dateFrom, java.lang.String dateTo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/getAllOperationsBatchIdModifiedBetweenDates");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "getAllOperationsBatchIdModifiedBetweenDates"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {appId, userName, password, batchid, dateFrom, dateTo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.OperationsBatchIdQueryResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse getPDFFromEvidence(java.lang.String userToken, java.lang.String evidenceId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/GetPDFFromEvidence");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetPDFFromEvidence"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, evidenceId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.EvidenceResultResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.OperationEvidenceResponse getOperationEvidence(java.lang.String userToken, java.lang.String operationId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/GetOperationEvidence");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetOperationEvidence"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, operationId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationEvidenceResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.OperationEvidenceResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.OperationEvidenceResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.CircuitEvidenceResponse getCircuitEvidence(java.lang.String userToken, java.lang.String idcircuit) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://coffice.net/cOfficeWebServices/GetCircuitEvidence");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "GetCircuitEvidence"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userToken, idcircuit});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitEvidenceResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.CircuitEvidenceResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.CircuitEvidenceResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
