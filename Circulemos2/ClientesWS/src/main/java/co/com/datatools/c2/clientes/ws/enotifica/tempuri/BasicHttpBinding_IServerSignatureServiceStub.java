/**
 * BasicHttpBinding_IServerSignatureServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.tempuri;

public class BasicHttpBinding_IServerSignatureServiceStub extends org.apache.axis.client.Stub implements co.com.datatools.c2.clientes.ws.enotifica.tempuri.IServerSignatureService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[6];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetUserCertificateRefs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "ArrayOfCertReference"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.CertReference[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetUserCertificateRefsResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "CertReference"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"),
                      "org.datacontract.schemas._2004._07.IvSignatureService_ivsignatureservice.IvSignatureFault",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Sign");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "idCertificate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "certPin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "signatureProfile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureProfile"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "signatureType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureType"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "hashAlgorithm"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvHashAlgorithm"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "options"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureFlags"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "detachedSignature"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "signingDocument"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        oper.setReturnClass(byte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SignResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"),
                      "org.datacontract.schemas._2004._07.IvSignatureService_ivsignatureservice.IvSignatureFault",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Encrypt");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "idCertificate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "certPin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "hashAlgorithm"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvHashAlgorithm"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "dataToEncrypt"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        oper.setReturnClass(byte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "EncryptResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"),
                      "org.datacontract.schemas._2004._07.IvSignatureService_ivsignatureservice.IvSignatureFault",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SignByToken");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "token"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "idCertificate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "certPin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "signatureProfile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureProfile"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "signatureType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureType"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "hashAlgorithm"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvHashAlgorithm"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "options"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureFlags"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "detachedSignature"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "signingDocument"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        oper.setReturnClass(byte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SignByTokenResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"),
                      "org.datacontract.schemas._2004._07.IvSignatureService_ivsignatureservice.IvSignatureFault",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SignProvider");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "idCertificate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "uri"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "providerParameter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "signingDocument"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        oper.setReturnClass(byte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SignProviderResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"),
                      "org.datacontract.schemas._2004._07.IvSignatureService_ivsignatureservice.IvSignatureFault",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Verify");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "signatureProfile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureProfile"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "options"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvVerificationFlags"), java.lang.String[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "VerifyParams"), co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.VerifyParams.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "detachedSignature"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "document"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureValidation"));
        oper.setReturnClass(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureValidation.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "VerifyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"),
                      "org.datacontract.schemas._2004._07.IvSignatureService_ivsignatureservice.IvSignatureFault",
                      new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault"), 
                      true
                     ));
        _operations[5] = oper;

    }

    public BasicHttpBinding_IServerSignatureServiceStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public BasicHttpBinding_IServerSignatureServiceStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public BasicHttpBinding_IServerSignatureServiceStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "ArrayOfCertReference");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.CertReference[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "CertReference");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "CertReference");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "ArrayOfSignatureRef");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureRef");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureRef");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "ArrayOfTimestampRef");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "TimestampRef");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "TimestampRef");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "CertReference");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.CertReference.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvCertificateInfo");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvCertificateInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvPDFSignatureParams");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvPDFSignatureParams.PdfSignatureBackground");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureBackground.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvPDFSignatureParams.PdfSignatureWidgetProps");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureWidgetProps.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvHashAlgorithm");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureFlags");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(simplelistsf);
            cachedDeserFactories.add(simplelistdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureProfile");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureType");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvVerificationFlags");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(simplelistsf);
            cachedDeserFactories.add(simplelistdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.SignatureTemplate");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsSignatureTemplate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.TimestampType");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsTimestampType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.VerifyResult");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsVerifyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.VerifyState");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsVerifyState.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.XadesReferenceTransform");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsXadesReferenceTransform.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.SignaturePolicy");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignaturePolicy.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.SignLocation");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignLocation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.WidgetTextInfo");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsWidgetTextInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.XadesSignedDataObjectProperties");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsXadesSignedDataObjectProperties.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvTimestampServerInfo");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvTimestampServerInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureRef");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureValidation");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureValidation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "TimestampRef");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "VerifyParams");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.VerifyParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureService.ivsignatureservice", "IvSignatureFault");
            cachedSerQNames.add(qName);
            cls = co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault.class;
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

    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.CertReference[] getUserCertificateRefs(java.lang.String userId, java.lang.String password) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IServerSignatureService/GetUserCertificateRefs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetUserCertificateRefs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.CertReference[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.CertReference[]) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.CertReference[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) {
              throw (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public byte[] sign(java.lang.String userId, java.lang.String password, java.lang.String idCertificate, java.lang.String certPin, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile signatureProfile, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType signatureType, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm, java.lang.String[] options, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams parameters, byte[] detachedSignature, byte[] signingDocument) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IServerSignatureService/Sign");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Sign"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId, password, idCertificate, certPin, signatureProfile, signatureType, hashAlgorithm, options, parameters, detachedSignature, signingDocument});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (byte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (byte[]) org.apache.axis.utils.JavaUtils.convert(_resp, byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) {
              throw (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public byte[] encrypt(java.lang.String userId, java.lang.String password, java.lang.String idCertificate, java.lang.String certPin, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm, byte[] dataToEncrypt) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IServerSignatureService/Encrypt");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Encrypt"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId, password, idCertificate, certPin, hashAlgorithm, dataToEncrypt});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (byte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (byte[]) org.apache.axis.utils.JavaUtils.convert(_resp, byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) {
              throw (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public byte[] signByToken(java.lang.String token, java.lang.String idCertificate, java.lang.String certPin, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile signatureProfile, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType signatureType, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm, java.lang.String[] options, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams parameters, byte[] detachedSignature, byte[] signingDocument) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IServerSignatureService/SignByToken");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SignByToken"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {token, idCertificate, certPin, signatureProfile, signatureType, hashAlgorithm, options, parameters, detachedSignature, signingDocument});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (byte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (byte[]) org.apache.axis.utils.JavaUtils.convert(_resp, byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) {
              throw (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public byte[] signProvider(java.lang.Integer idCertificate, java.lang.String uri, java.lang.String providerParameter, byte[] signingDocument) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IServerSignatureService/SignProvider");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SignProvider"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idCertificate, uri, providerParameter, signingDocument});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (byte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (byte[]) org.apache.axis.utils.JavaUtils.convert(_resp, byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) {
              throw (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureValidation verify(java.lang.String userId, java.lang.String password, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile signatureProfile, java.lang.String[] options, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.VerifyParams parameters, byte[] detachedSignature, byte[] document) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IServerSignatureService/Verify");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "Verify"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userId, password, signatureProfile, options, parameters, detachedSignature, document});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureValidation) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureValidation) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureValidation.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) {
              throw (co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
