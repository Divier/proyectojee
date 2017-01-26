/**
 * IvSignatureParams.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvSignatureParams  implements java.io.Serializable {
    private java.lang.String cause;

    private java.lang.String docPassword;

    private java.lang.Boolean isXadesCounterSignature;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignLocation location;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParams pdfParameters;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignaturePolicy policy;

    private java.lang.String reference;

    private java.lang.String signerRole;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsSignatureTemplate template;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvTimestampServerInfo tstampBackupServer;

    private java.lang.Integer tstampBackupServerId;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvTimestampServerInfo tstampServer;

    private java.lang.Integer tstampServerId;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsWidgetTextInfo wdTextInfo;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsXadesReferenceTransform xadesPrimaryRefTransform;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsXadesSignedDataObjectProperties xadesSignedDataObject;

    public IvSignatureParams() {
    }

    public IvSignatureParams(
           java.lang.String cause,
           java.lang.String docPassword,
           java.lang.Boolean isXadesCounterSignature,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignLocation location,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParams pdfParameters,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignaturePolicy policy,
           java.lang.String reference,
           java.lang.String signerRole,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsSignatureTemplate template,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvTimestampServerInfo tstampBackupServer,
           java.lang.Integer tstampBackupServerId,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvTimestampServerInfo tstampServer,
           java.lang.Integer tstampServerId,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsWidgetTextInfo wdTextInfo,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsXadesReferenceTransform xadesPrimaryRefTransform,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsXadesSignedDataObjectProperties xadesSignedDataObject) {
           this.cause = cause;
           this.docPassword = docPassword;
           this.isXadesCounterSignature = isXadesCounterSignature;
           this.location = location;
           this.pdfParameters = pdfParameters;
           this.policy = policy;
           this.reference = reference;
           this.signerRole = signerRole;
           this.template = template;
           this.tstampBackupServer = tstampBackupServer;
           this.tstampBackupServerId = tstampBackupServerId;
           this.tstampServer = tstampServer;
           this.tstampServerId = tstampServerId;
           this.wdTextInfo = wdTextInfo;
           this.xadesPrimaryRefTransform = xadesPrimaryRefTransform;
           this.xadesSignedDataObject = xadesSignedDataObject;
    }


    /**
     * Gets the cause value for this IvSignatureParams.
     * 
     * @return cause
     */
    public java.lang.String getCause() {
        return cause;
    }


    /**
     * Sets the cause value for this IvSignatureParams.
     * 
     * @param cause
     */
    public void setCause(java.lang.String cause) {
        this.cause = cause;
    }


    /**
     * Gets the docPassword value for this IvSignatureParams.
     * 
     * @return docPassword
     */
    public java.lang.String getDocPassword() {
        return docPassword;
    }


    /**
     * Sets the docPassword value for this IvSignatureParams.
     * 
     * @param docPassword
     */
    public void setDocPassword(java.lang.String docPassword) {
        this.docPassword = docPassword;
    }


    /**
     * Gets the isXadesCounterSignature value for this IvSignatureParams.
     * 
     * @return isXadesCounterSignature
     */
    public java.lang.Boolean getIsXadesCounterSignature() {
        return isXadesCounterSignature;
    }


    /**
     * Sets the isXadesCounterSignature value for this IvSignatureParams.
     * 
     * @param isXadesCounterSignature
     */
    public void setIsXadesCounterSignature(java.lang.Boolean isXadesCounterSignature) {
        this.isXadesCounterSignature = isXadesCounterSignature;
    }


    /**
     * Gets the location value for this IvSignatureParams.
     * 
     * @return location
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignLocation getLocation() {
        return location;
    }


    /**
     * Sets the location value for this IvSignatureParams.
     * 
     * @param location
     */
    public void setLocation(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignLocation location) {
        this.location = location;
    }


    /**
     * Gets the pdfParameters value for this IvSignatureParams.
     * 
     * @return pdfParameters
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParams getPdfParameters() {
        return pdfParameters;
    }


    /**
     * Sets the pdfParameters value for this IvSignatureParams.
     * 
     * @param pdfParameters
     */
    public void setPdfParameters(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParams pdfParameters) {
        this.pdfParameters = pdfParameters;
    }


    /**
     * Gets the policy value for this IvSignatureParams.
     * 
     * @return policy
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignaturePolicy getPolicy() {
        return policy;
    }


    /**
     * Sets the policy value for this IvSignatureParams.
     * 
     * @param policy
     */
    public void setPolicy(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsSignaturePolicy policy) {
        this.policy = policy;
    }


    /**
     * Gets the reference value for this IvSignatureParams.
     * 
     * @return reference
     */
    public java.lang.String getReference() {
        return reference;
    }


    /**
     * Sets the reference value for this IvSignatureParams.
     * 
     * @param reference
     */
    public void setReference(java.lang.String reference) {
        this.reference = reference;
    }


    /**
     * Gets the signerRole value for this IvSignatureParams.
     * 
     * @return signerRole
     */
    public java.lang.String getSignerRole() {
        return signerRole;
    }


    /**
     * Sets the signerRole value for this IvSignatureParams.
     * 
     * @param signerRole
     */
    public void setSignerRole(java.lang.String signerRole) {
        this.signerRole = signerRole;
    }


    /**
     * Gets the template value for this IvSignatureParams.
     * 
     * @return template
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsSignatureTemplate getTemplate() {
        return template;
    }


    /**
     * Sets the template value for this IvSignatureParams.
     * 
     * @param template
     */
    public void setTemplate(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsSignatureTemplate template) {
        this.template = template;
    }


    /**
     * Gets the tstampBackupServer value for this IvSignatureParams.
     * 
     * @return tstampBackupServer
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvTimestampServerInfo getTstampBackupServer() {
        return tstampBackupServer;
    }


    /**
     * Sets the tstampBackupServer value for this IvSignatureParams.
     * 
     * @param tstampBackupServer
     */
    public void setTstampBackupServer(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvTimestampServerInfo tstampBackupServer) {
        this.tstampBackupServer = tstampBackupServer;
    }


    /**
     * Gets the tstampBackupServerId value for this IvSignatureParams.
     * 
     * @return tstampBackupServerId
     */
    public java.lang.Integer getTstampBackupServerId() {
        return tstampBackupServerId;
    }


    /**
     * Sets the tstampBackupServerId value for this IvSignatureParams.
     * 
     * @param tstampBackupServerId
     */
    public void setTstampBackupServerId(java.lang.Integer tstampBackupServerId) {
        this.tstampBackupServerId = tstampBackupServerId;
    }


    /**
     * Gets the tstampServer value for this IvSignatureParams.
     * 
     * @return tstampServer
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvTimestampServerInfo getTstampServer() {
        return tstampServer;
    }


    /**
     * Sets the tstampServer value for this IvSignatureParams.
     * 
     * @param tstampServer
     */
    public void setTstampServer(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvTimestampServerInfo tstampServer) {
        this.tstampServer = tstampServer;
    }


    /**
     * Gets the tstampServerId value for this IvSignatureParams.
     * 
     * @return tstampServerId
     */
    public java.lang.Integer getTstampServerId() {
        return tstampServerId;
    }


    /**
     * Sets the tstampServerId value for this IvSignatureParams.
     * 
     * @param tstampServerId
     */
    public void setTstampServerId(java.lang.Integer tstampServerId) {
        this.tstampServerId = tstampServerId;
    }


    /**
     * Gets the wdTextInfo value for this IvSignatureParams.
     * 
     * @return wdTextInfo
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsWidgetTextInfo getWdTextInfo() {
        return wdTextInfo;
    }


    /**
     * Sets the wdTextInfo value for this IvSignatureParams.
     * 
     * @param wdTextInfo
     */
    public void setWdTextInfo(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsWidgetTextInfo wdTextInfo) {
        this.wdTextInfo = wdTextInfo;
    }


    /**
     * Gets the xadesPrimaryRefTransform value for this IvSignatureParams.
     * 
     * @return xadesPrimaryRefTransform
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsXadesReferenceTransform getXadesPrimaryRefTransform() {
        return xadesPrimaryRefTransform;
    }


    /**
     * Sets the xadesPrimaryRefTransform value for this IvSignatureParams.
     * 
     * @param xadesPrimaryRefTransform
     */
    public void setXadesPrimaryRefTransform(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsXadesReferenceTransform xadesPrimaryRefTransform) {
        this.xadesPrimaryRefTransform = xadesPrimaryRefTransform;
    }


    /**
     * Gets the xadesSignedDataObject value for this IvSignatureParams.
     * 
     * @return xadesSignedDataObject
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsXadesSignedDataObjectProperties getXadesSignedDataObject() {
        return xadesSignedDataObject;
    }


    /**
     * Sets the xadesSignedDataObject value for this IvSignatureParams.
     * 
     * @param xadesSignedDataObject
     */
    public void setXadesSignedDataObject(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParamsXadesSignedDataObjectProperties xadesSignedDataObject) {
        this.xadesSignedDataObject = xadesSignedDataObject;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IvSignatureParams)) return false;
        IvSignatureParams other = (IvSignatureParams) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cause==null && other.getCause()==null) || 
             (this.cause!=null &&
              this.cause.equals(other.getCause()))) &&
            ((this.docPassword==null && other.getDocPassword()==null) || 
             (this.docPassword!=null &&
              this.docPassword.equals(other.getDocPassword()))) &&
            ((this.isXadesCounterSignature==null && other.getIsXadesCounterSignature()==null) || 
             (this.isXadesCounterSignature!=null &&
              this.isXadesCounterSignature.equals(other.getIsXadesCounterSignature()))) &&
            ((this.location==null && other.getLocation()==null) || 
             (this.location!=null &&
              this.location.equals(other.getLocation()))) &&
            ((this.pdfParameters==null && other.getPdfParameters()==null) || 
             (this.pdfParameters!=null &&
              this.pdfParameters.equals(other.getPdfParameters()))) &&
            ((this.policy==null && other.getPolicy()==null) || 
             (this.policy!=null &&
              this.policy.equals(other.getPolicy()))) &&
            ((this.reference==null && other.getReference()==null) || 
             (this.reference!=null &&
              this.reference.equals(other.getReference()))) &&
            ((this.signerRole==null && other.getSignerRole()==null) || 
             (this.signerRole!=null &&
              this.signerRole.equals(other.getSignerRole()))) &&
            ((this.template==null && other.getTemplate()==null) || 
             (this.template!=null &&
              this.template.equals(other.getTemplate()))) &&
            ((this.tstampBackupServer==null && other.getTstampBackupServer()==null) || 
             (this.tstampBackupServer!=null &&
              this.tstampBackupServer.equals(other.getTstampBackupServer()))) &&
            ((this.tstampBackupServerId==null && other.getTstampBackupServerId()==null) || 
             (this.tstampBackupServerId!=null &&
              this.tstampBackupServerId.equals(other.getTstampBackupServerId()))) &&
            ((this.tstampServer==null && other.getTstampServer()==null) || 
             (this.tstampServer!=null &&
              this.tstampServer.equals(other.getTstampServer()))) &&
            ((this.tstampServerId==null && other.getTstampServerId()==null) || 
             (this.tstampServerId!=null &&
              this.tstampServerId.equals(other.getTstampServerId()))) &&
            ((this.wdTextInfo==null && other.getWdTextInfo()==null) || 
             (this.wdTextInfo!=null &&
              this.wdTextInfo.equals(other.getWdTextInfo()))) &&
            ((this.xadesPrimaryRefTransform==null && other.getXadesPrimaryRefTransform()==null) || 
             (this.xadesPrimaryRefTransform!=null &&
              this.xadesPrimaryRefTransform.equals(other.getXadesPrimaryRefTransform()))) &&
            ((this.xadesSignedDataObject==null && other.getXadesSignedDataObject()==null) || 
             (this.xadesSignedDataObject!=null &&
              this.xadesSignedDataObject.equals(other.getXadesSignedDataObject())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCause() != null) {
            _hashCode += getCause().hashCode();
        }
        if (getDocPassword() != null) {
            _hashCode += getDocPassword().hashCode();
        }
        if (getIsXadesCounterSignature() != null) {
            _hashCode += getIsXadesCounterSignature().hashCode();
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getPdfParameters() != null) {
            _hashCode += getPdfParameters().hashCode();
        }
        if (getPolicy() != null) {
            _hashCode += getPolicy().hashCode();
        }
        if (getReference() != null) {
            _hashCode += getReference().hashCode();
        }
        if (getSignerRole() != null) {
            _hashCode += getSignerRole().hashCode();
        }
        if (getTemplate() != null) {
            _hashCode += getTemplate().hashCode();
        }
        if (getTstampBackupServer() != null) {
            _hashCode += getTstampBackupServer().hashCode();
        }
        if (getTstampBackupServerId() != null) {
            _hashCode += getTstampBackupServerId().hashCode();
        }
        if (getTstampServer() != null) {
            _hashCode += getTstampServer().hashCode();
        }
        if (getTstampServerId() != null) {
            _hashCode += getTstampServerId().hashCode();
        }
        if (getWdTextInfo() != null) {
            _hashCode += getWdTextInfo().hashCode();
        }
        if (getXadesPrimaryRefTransform() != null) {
            _hashCode += getXadesPrimaryRefTransform().hashCode();
        }
        if (getXadesSignedDataObject() != null) {
            _hashCode += getXadesSignedDataObject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IvSignatureParams.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cause");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "cause"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "docPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isXadesCounterSignature");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "isXadesCounterSignature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "location"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.SignLocation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pdfParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "pdfParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvPDFSignatureParams"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "policy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.SignaturePolicy"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "reference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signerRole");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signerRole"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("template");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "template"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.SignatureTemplate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tstampBackupServer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "tstampBackupServer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvTimestampServerInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tstampBackupServerId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "tstampBackupServerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tstampServer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "tstampServer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvTimestampServerInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tstampServerId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "tstampServerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wdTextInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "wdTextInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.WidgetTextInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xadesPrimaryRefTransform");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "xadesPrimaryRefTransform"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.XadesReferenceTransform"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xadesSignedDataObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "xadesSignedDataObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.XadesSignedDataObjectProperties"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
