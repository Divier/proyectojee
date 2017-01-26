/**
 * IvSignatureParamsXadesSignedDataObjectProperties.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvSignatureParamsXadesSignedDataObjectProperties  implements java.io.Serializable {
    private java.lang.String description;

    private java.lang.String encoding;

    private java.lang.String mimeType;

    private java.lang.String ojectIdentifierValue;

    public IvSignatureParamsXadesSignedDataObjectProperties() {
    }

    public IvSignatureParamsXadesSignedDataObjectProperties(
           java.lang.String description,
           java.lang.String encoding,
           java.lang.String mimeType,
           java.lang.String ojectIdentifierValue) {
           this.description = description;
           this.encoding = encoding;
           this.mimeType = mimeType;
           this.ojectIdentifierValue = ojectIdentifierValue;
    }


    /**
     * Gets the description value for this IvSignatureParamsXadesSignedDataObjectProperties.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this IvSignatureParamsXadesSignedDataObjectProperties.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the encoding value for this IvSignatureParamsXadesSignedDataObjectProperties.
     * 
     * @return encoding
     */
    public java.lang.String getEncoding() {
        return encoding;
    }


    /**
     * Sets the encoding value for this IvSignatureParamsXadesSignedDataObjectProperties.
     * 
     * @param encoding
     */
    public void setEncoding(java.lang.String encoding) {
        this.encoding = encoding;
    }


    /**
     * Gets the mimeType value for this IvSignatureParamsXadesSignedDataObjectProperties.
     * 
     * @return mimeType
     */
    public java.lang.String getMimeType() {
        return mimeType;
    }


    /**
     * Sets the mimeType value for this IvSignatureParamsXadesSignedDataObjectProperties.
     * 
     * @param mimeType
     */
    public void setMimeType(java.lang.String mimeType) {
        this.mimeType = mimeType;
    }


    /**
     * Gets the ojectIdentifierValue value for this IvSignatureParamsXadesSignedDataObjectProperties.
     * 
     * @return ojectIdentifierValue
     */
    public java.lang.String getOjectIdentifierValue() {
        return ojectIdentifierValue;
    }


    /**
     * Sets the ojectIdentifierValue value for this IvSignatureParamsXadesSignedDataObjectProperties.
     * 
     * @param ojectIdentifierValue
     */
    public void setOjectIdentifierValue(java.lang.String ojectIdentifierValue) {
        this.ojectIdentifierValue = ojectIdentifierValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IvSignatureParamsXadesSignedDataObjectProperties)) return false;
        IvSignatureParamsXadesSignedDataObjectProperties other = (IvSignatureParamsXadesSignedDataObjectProperties) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.encoding==null && other.getEncoding()==null) || 
             (this.encoding!=null &&
              this.encoding.equals(other.getEncoding()))) &&
            ((this.mimeType==null && other.getMimeType()==null) || 
             (this.mimeType!=null &&
              this.mimeType.equals(other.getMimeType()))) &&
            ((this.ojectIdentifierValue==null && other.getOjectIdentifierValue()==null) || 
             (this.ojectIdentifierValue!=null &&
              this.ojectIdentifierValue.equals(other.getOjectIdentifierValue())));
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getEncoding() != null) {
            _hashCode += getEncoding().hashCode();
        }
        if (getMimeType() != null) {
            _hashCode += getMimeType().hashCode();
        }
        if (getOjectIdentifierValue() != null) {
            _hashCode += getOjectIdentifierValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IvSignatureParamsXadesSignedDataObjectProperties.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.XadesSignedDataObjectProperties"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encoding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "encoding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mimeType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "mimeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ojectIdentifierValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "ojectIdentifierValue"));
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
