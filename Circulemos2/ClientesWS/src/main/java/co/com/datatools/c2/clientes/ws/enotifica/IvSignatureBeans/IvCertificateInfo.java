/**
 * IvCertificateInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvCertificateInfo  implements java.io.Serializable {
    private java.lang.String contactInfo;

    private java.lang.String dllName;

    private byte[] encoded;

    private java.lang.String hsmPin;

    private java.lang.String hsmSlot;

    private java.lang.String id;

    private java.lang.String issuer;

    private java.lang.String owner;

    private java.lang.Boolean ownerOnlyAdmin;

    private java.lang.String serialNumber;

    private java.lang.String subject;

    private java.util.Calendar validFrom;

    private java.util.Calendar validTo;

    public IvCertificateInfo() {
    }

    public IvCertificateInfo(
           java.lang.String contactInfo,
           java.lang.String dllName,
           byte[] encoded,
           java.lang.String hsmPin,
           java.lang.String hsmSlot,
           java.lang.String id,
           java.lang.String issuer,
           java.lang.String owner,
           java.lang.Boolean ownerOnlyAdmin,
           java.lang.String serialNumber,
           java.lang.String subject,
           java.util.Calendar validFrom,
           java.util.Calendar validTo) {
           this.contactInfo = contactInfo;
           this.dllName = dllName;
           this.encoded = encoded;
           this.hsmPin = hsmPin;
           this.hsmSlot = hsmSlot;
           this.id = id;
           this.issuer = issuer;
           this.owner = owner;
           this.ownerOnlyAdmin = ownerOnlyAdmin;
           this.serialNumber = serialNumber;
           this.subject = subject;
           this.validFrom = validFrom;
           this.validTo = validTo;
    }


    /**
     * Gets the contactInfo value for this IvCertificateInfo.
     * 
     * @return contactInfo
     */
    public java.lang.String getContactInfo() {
        return contactInfo;
    }


    /**
     * Sets the contactInfo value for this IvCertificateInfo.
     * 
     * @param contactInfo
     */
    public void setContactInfo(java.lang.String contactInfo) {
        this.contactInfo = contactInfo;
    }


    /**
     * Gets the dllName value for this IvCertificateInfo.
     * 
     * @return dllName
     */
    public java.lang.String getDllName() {
        return dllName;
    }


    /**
     * Sets the dllName value for this IvCertificateInfo.
     * 
     * @param dllName
     */
    public void setDllName(java.lang.String dllName) {
        this.dllName = dllName;
    }


    /**
     * Gets the encoded value for this IvCertificateInfo.
     * 
     * @return encoded
     */
    public byte[] getEncoded() {
        return encoded;
    }


    /**
     * Sets the encoded value for this IvCertificateInfo.
     * 
     * @param encoded
     */
    public void setEncoded(byte[] encoded) {
        this.encoded = encoded;
    }


    /**
     * Gets the hsmPin value for this IvCertificateInfo.
     * 
     * @return hsmPin
     */
    public java.lang.String getHsmPin() {
        return hsmPin;
    }


    /**
     * Sets the hsmPin value for this IvCertificateInfo.
     * 
     * @param hsmPin
     */
    public void setHsmPin(java.lang.String hsmPin) {
        this.hsmPin = hsmPin;
    }


    /**
     * Gets the hsmSlot value for this IvCertificateInfo.
     * 
     * @return hsmSlot
     */
    public java.lang.String getHsmSlot() {
        return hsmSlot;
    }


    /**
     * Sets the hsmSlot value for this IvCertificateInfo.
     * 
     * @param hsmSlot
     */
    public void setHsmSlot(java.lang.String hsmSlot) {
        this.hsmSlot = hsmSlot;
    }


    /**
     * Gets the id value for this IvCertificateInfo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this IvCertificateInfo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the issuer value for this IvCertificateInfo.
     * 
     * @return issuer
     */
    public java.lang.String getIssuer() {
        return issuer;
    }


    /**
     * Sets the issuer value for this IvCertificateInfo.
     * 
     * @param issuer
     */
    public void setIssuer(java.lang.String issuer) {
        this.issuer = issuer;
    }


    /**
     * Gets the owner value for this IvCertificateInfo.
     * 
     * @return owner
     */
    public java.lang.String getOwner() {
        return owner;
    }


    /**
     * Sets the owner value for this IvCertificateInfo.
     * 
     * @param owner
     */
    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }


    /**
     * Gets the ownerOnlyAdmin value for this IvCertificateInfo.
     * 
     * @return ownerOnlyAdmin
     */
    public java.lang.Boolean getOwnerOnlyAdmin() {
        return ownerOnlyAdmin;
    }


    /**
     * Sets the ownerOnlyAdmin value for this IvCertificateInfo.
     * 
     * @param ownerOnlyAdmin
     */
    public void setOwnerOnlyAdmin(java.lang.Boolean ownerOnlyAdmin) {
        this.ownerOnlyAdmin = ownerOnlyAdmin;
    }


    /**
     * Gets the serialNumber value for this IvCertificateInfo.
     * 
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this IvCertificateInfo.
     * 
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }


    /**
     * Gets the subject value for this IvCertificateInfo.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this IvCertificateInfo.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the validFrom value for this IvCertificateInfo.
     * 
     * @return validFrom
     */
    public java.util.Calendar getValidFrom() {
        return validFrom;
    }


    /**
     * Sets the validFrom value for this IvCertificateInfo.
     * 
     * @param validFrom
     */
    public void setValidFrom(java.util.Calendar validFrom) {
        this.validFrom = validFrom;
    }


    /**
     * Gets the validTo value for this IvCertificateInfo.
     * 
     * @return validTo
     */
    public java.util.Calendar getValidTo() {
        return validTo;
    }


    /**
     * Sets the validTo value for this IvCertificateInfo.
     * 
     * @param validTo
     */
    public void setValidTo(java.util.Calendar validTo) {
        this.validTo = validTo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IvCertificateInfo)) return false;
        IvCertificateInfo other = (IvCertificateInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contactInfo==null && other.getContactInfo()==null) || 
             (this.contactInfo!=null &&
              this.contactInfo.equals(other.getContactInfo()))) &&
            ((this.dllName==null && other.getDllName()==null) || 
             (this.dllName!=null &&
              this.dllName.equals(other.getDllName()))) &&
            ((this.encoded==null && other.getEncoded()==null) || 
             (this.encoded!=null &&
              java.util.Arrays.equals(this.encoded, other.getEncoded()))) &&
            ((this.hsmPin==null && other.getHsmPin()==null) || 
             (this.hsmPin!=null &&
              this.hsmPin.equals(other.getHsmPin()))) &&
            ((this.hsmSlot==null && other.getHsmSlot()==null) || 
             (this.hsmSlot!=null &&
              this.hsmSlot.equals(other.getHsmSlot()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.issuer==null && other.getIssuer()==null) || 
             (this.issuer!=null &&
              this.issuer.equals(other.getIssuer()))) &&
            ((this.owner==null && other.getOwner()==null) || 
             (this.owner!=null &&
              this.owner.equals(other.getOwner()))) &&
            ((this.ownerOnlyAdmin==null && other.getOwnerOnlyAdmin()==null) || 
             (this.ownerOnlyAdmin!=null &&
              this.ownerOnlyAdmin.equals(other.getOwnerOnlyAdmin()))) &&
            ((this.serialNumber==null && other.getSerialNumber()==null) || 
             (this.serialNumber!=null &&
              this.serialNumber.equals(other.getSerialNumber()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.validFrom==null && other.getValidFrom()==null) || 
             (this.validFrom!=null &&
              this.validFrom.equals(other.getValidFrom()))) &&
            ((this.validTo==null && other.getValidTo()==null) || 
             (this.validTo!=null &&
              this.validTo.equals(other.getValidTo())));
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
        if (getContactInfo() != null) {
            _hashCode += getContactInfo().hashCode();
        }
        if (getDllName() != null) {
            _hashCode += getDllName().hashCode();
        }
        if (getEncoded() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEncoded());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEncoded(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHsmPin() != null) {
            _hashCode += getHsmPin().hashCode();
        }
        if (getHsmSlot() != null) {
            _hashCode += getHsmSlot().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIssuer() != null) {
            _hashCode += getIssuer().hashCode();
        }
        if (getOwner() != null) {
            _hashCode += getOwner().hashCode();
        }
        if (getOwnerOnlyAdmin() != null) {
            _hashCode += getOwnerOnlyAdmin().hashCode();
        }
        if (getSerialNumber() != null) {
            _hashCode += getSerialNumber().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getValidFrom() != null) {
            _hashCode += getValidFrom().hashCode();
        }
        if (getValidTo() != null) {
            _hashCode += getValidTo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IvCertificateInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvCertificateInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "contactInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dllName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "dllName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encoded");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "encoded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hsmPin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "hsmPin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hsmSlot");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "hsmSlot"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issuer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "issuer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("owner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "owner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ownerOnlyAdmin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "ownerOnlyAdmin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "serialNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "validFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "validTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
