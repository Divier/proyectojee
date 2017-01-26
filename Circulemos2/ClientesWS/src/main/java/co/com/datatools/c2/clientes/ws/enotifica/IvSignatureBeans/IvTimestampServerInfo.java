/**
 * IvTimestampServerInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvTimestampServerInfo  implements java.io.Serializable {
    private java.lang.String URL;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm;

    private java.lang.Boolean httpAuth;

    private java.lang.Integer id;

    private java.lang.Integer idServerCertificate;

    private java.lang.Boolean includeCertificates;

    private java.lang.String name;

    private java.lang.String password;

    private java.lang.Boolean sslClientAuth;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvCertificateInfo sslClientAuthCertificateReference;

    private java.lang.Boolean useNonce;

    private java.lang.String userName;

    public IvTimestampServerInfo() {
    }

    public IvTimestampServerInfo(
           java.lang.String URL,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm,
           java.lang.Boolean httpAuth,
           java.lang.Integer id,
           java.lang.Integer idServerCertificate,
           java.lang.Boolean includeCertificates,
           java.lang.String name,
           java.lang.String password,
           java.lang.Boolean sslClientAuth,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvCertificateInfo sslClientAuthCertificateReference,
           java.lang.Boolean useNonce,
           java.lang.String userName) {
           this.URL = URL;
           this.hashAlgorithm = hashAlgorithm;
           this.httpAuth = httpAuth;
           this.id = id;
           this.idServerCertificate = idServerCertificate;
           this.includeCertificates = includeCertificates;
           this.name = name;
           this.password = password;
           this.sslClientAuth = sslClientAuth;
           this.sslClientAuthCertificateReference = sslClientAuthCertificateReference;
           this.useNonce = useNonce;
           this.userName = userName;
    }


    /**
     * Gets the URL value for this IvTimestampServerInfo.
     * 
     * @return URL
     */
    public java.lang.String getURL() {
        return URL;
    }


    /**
     * Sets the URL value for this IvTimestampServerInfo.
     * 
     * @param URL
     */
    public void setURL(java.lang.String URL) {
        this.URL = URL;
    }


    /**
     * Gets the hashAlgorithm value for this IvTimestampServerInfo.
     * 
     * @return hashAlgorithm
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm getHashAlgorithm() {
        return hashAlgorithm;
    }


    /**
     * Sets the hashAlgorithm value for this IvTimestampServerInfo.
     * 
     * @param hashAlgorithm
     */
    public void setHashAlgorithm(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }


    /**
     * Gets the httpAuth value for this IvTimestampServerInfo.
     * 
     * @return httpAuth
     */
    public java.lang.Boolean getHttpAuth() {
        return httpAuth;
    }


    /**
     * Sets the httpAuth value for this IvTimestampServerInfo.
     * 
     * @param httpAuth
     */
    public void setHttpAuth(java.lang.Boolean httpAuth) {
        this.httpAuth = httpAuth;
    }


    /**
     * Gets the id value for this IvTimestampServerInfo.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this IvTimestampServerInfo.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the idServerCertificate value for this IvTimestampServerInfo.
     * 
     * @return idServerCertificate
     */
    public java.lang.Integer getIdServerCertificate() {
        return idServerCertificate;
    }


    /**
     * Sets the idServerCertificate value for this IvTimestampServerInfo.
     * 
     * @param idServerCertificate
     */
    public void setIdServerCertificate(java.lang.Integer idServerCertificate) {
        this.idServerCertificate = idServerCertificate;
    }


    /**
     * Gets the includeCertificates value for this IvTimestampServerInfo.
     * 
     * @return includeCertificates
     */
    public java.lang.Boolean getIncludeCertificates() {
        return includeCertificates;
    }


    /**
     * Sets the includeCertificates value for this IvTimestampServerInfo.
     * 
     * @param includeCertificates
     */
    public void setIncludeCertificates(java.lang.Boolean includeCertificates) {
        this.includeCertificates = includeCertificates;
    }


    /**
     * Gets the name value for this IvTimestampServerInfo.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this IvTimestampServerInfo.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the password value for this IvTimestampServerInfo.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this IvTimestampServerInfo.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the sslClientAuth value for this IvTimestampServerInfo.
     * 
     * @return sslClientAuth
     */
    public java.lang.Boolean getSslClientAuth() {
        return sslClientAuth;
    }


    /**
     * Sets the sslClientAuth value for this IvTimestampServerInfo.
     * 
     * @param sslClientAuth
     */
    public void setSslClientAuth(java.lang.Boolean sslClientAuth) {
        this.sslClientAuth = sslClientAuth;
    }


    /**
     * Gets the sslClientAuthCertificateReference value for this IvTimestampServerInfo.
     * 
     * @return sslClientAuthCertificateReference
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvCertificateInfo getSslClientAuthCertificateReference() {
        return sslClientAuthCertificateReference;
    }


    /**
     * Sets the sslClientAuthCertificateReference value for this IvTimestampServerInfo.
     * 
     * @param sslClientAuthCertificateReference
     */
    public void setSslClientAuthCertificateReference(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvCertificateInfo sslClientAuthCertificateReference) {
        this.sslClientAuthCertificateReference = sslClientAuthCertificateReference;
    }


    /**
     * Gets the useNonce value for this IvTimestampServerInfo.
     * 
     * @return useNonce
     */
    public java.lang.Boolean getUseNonce() {
        return useNonce;
    }


    /**
     * Sets the useNonce value for this IvTimestampServerInfo.
     * 
     * @param useNonce
     */
    public void setUseNonce(java.lang.Boolean useNonce) {
        this.useNonce = useNonce;
    }


    /**
     * Gets the userName value for this IvTimestampServerInfo.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this IvTimestampServerInfo.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IvTimestampServerInfo)) return false;
        IvTimestampServerInfo other = (IvTimestampServerInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.URL==null && other.getURL()==null) || 
             (this.URL!=null &&
              this.URL.equals(other.getURL()))) &&
            ((this.hashAlgorithm==null && other.getHashAlgorithm()==null) || 
             (this.hashAlgorithm!=null &&
              this.hashAlgorithm.equals(other.getHashAlgorithm()))) &&
            ((this.httpAuth==null && other.getHttpAuth()==null) || 
             (this.httpAuth!=null &&
              this.httpAuth.equals(other.getHttpAuth()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idServerCertificate==null && other.getIdServerCertificate()==null) || 
             (this.idServerCertificate!=null &&
              this.idServerCertificate.equals(other.getIdServerCertificate()))) &&
            ((this.includeCertificates==null && other.getIncludeCertificates()==null) || 
             (this.includeCertificates!=null &&
              this.includeCertificates.equals(other.getIncludeCertificates()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.sslClientAuth==null && other.getSslClientAuth()==null) || 
             (this.sslClientAuth!=null &&
              this.sslClientAuth.equals(other.getSslClientAuth()))) &&
            ((this.sslClientAuthCertificateReference==null && other.getSslClientAuthCertificateReference()==null) || 
             (this.sslClientAuthCertificateReference!=null &&
              this.sslClientAuthCertificateReference.equals(other.getSslClientAuthCertificateReference()))) &&
            ((this.useNonce==null && other.getUseNonce()==null) || 
             (this.useNonce!=null &&
              this.useNonce.equals(other.getUseNonce()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName())));
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
        if (getURL() != null) {
            _hashCode += getURL().hashCode();
        }
        if (getHashAlgorithm() != null) {
            _hashCode += getHashAlgorithm().hashCode();
        }
        if (getHttpAuth() != null) {
            _hashCode += getHttpAuth().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdServerCertificate() != null) {
            _hashCode += getIdServerCertificate().hashCode();
        }
        if (getIncludeCertificates() != null) {
            _hashCode += getIncludeCertificates().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getSslClientAuth() != null) {
            _hashCode += getSslClientAuth().hashCode();
        }
        if (getSslClientAuthCertificateReference() != null) {
            _hashCode += getSslClientAuthCertificateReference().hashCode();
        }
        if (getUseNonce() != null) {
            _hashCode += getUseNonce().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IvTimestampServerInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvTimestampServerInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "URL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hashAlgorithm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "hashAlgorithm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvHashAlgorithm"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("httpAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "httpAuth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServerCertificate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "idServerCertificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeCertificates");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "includeCertificates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sslClientAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "sslClientAuth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sslClientAuthCertificateReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "sslClientAuthCertificateReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvCertificateInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useNonce");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "useNonce"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "userName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
