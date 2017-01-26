/**
 * TimestampRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class TimestampRef  implements java.io.Serializable {
    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef[] timestampSignatures;

    private java.util.Calendar timestampTime;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsTimestampType timestampType;

    private java.lang.Boolean timestampValid;

    public TimestampRef() {
    }

    public TimestampRef(
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef[] timestampSignatures,
           java.util.Calendar timestampTime,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsTimestampType timestampType,
           java.lang.Boolean timestampValid) {
           this.timestampSignatures = timestampSignatures;
           this.timestampTime = timestampTime;
           this.timestampType = timestampType;
           this.timestampValid = timestampValid;
    }


    /**
     * Gets the timestampSignatures value for this TimestampRef.
     * 
     * @return timestampSignatures
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef[] getTimestampSignatures() {
        return timestampSignatures;
    }


    /**
     * Sets the timestampSignatures value for this TimestampRef.
     * 
     * @param timestampSignatures
     */
    public void setTimestampSignatures(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef[] timestampSignatures) {
        this.timestampSignatures = timestampSignatures;
    }


    /**
     * Gets the timestampTime value for this TimestampRef.
     * 
     * @return timestampTime
     */
    public java.util.Calendar getTimestampTime() {
        return timestampTime;
    }


    /**
     * Sets the timestampTime value for this TimestampRef.
     * 
     * @param timestampTime
     */
    public void setTimestampTime(java.util.Calendar timestampTime) {
        this.timestampTime = timestampTime;
    }


    /**
     * Gets the timestampType value for this TimestampRef.
     * 
     * @return timestampType
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsTimestampType getTimestampType() {
        return timestampType;
    }


    /**
     * Sets the timestampType value for this TimestampRef.
     * 
     * @param timestampType
     */
    public void setTimestampType(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsTimestampType timestampType) {
        this.timestampType = timestampType;
    }


    /**
     * Gets the timestampValid value for this TimestampRef.
     * 
     * @return timestampValid
     */
    public java.lang.Boolean getTimestampValid() {
        return timestampValid;
    }


    /**
     * Sets the timestampValid value for this TimestampRef.
     * 
     * @param timestampValid
     */
    public void setTimestampValid(java.lang.Boolean timestampValid) {
        this.timestampValid = timestampValid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TimestampRef)) return false;
        TimestampRef other = (TimestampRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.timestampSignatures==null && other.getTimestampSignatures()==null) || 
             (this.timestampSignatures!=null &&
              java.util.Arrays.equals(this.timestampSignatures, other.getTimestampSignatures()))) &&
            ((this.timestampTime==null && other.getTimestampTime()==null) || 
             (this.timestampTime!=null &&
              this.timestampTime.equals(other.getTimestampTime()))) &&
            ((this.timestampType==null && other.getTimestampType()==null) || 
             (this.timestampType!=null &&
              this.timestampType.equals(other.getTimestampType()))) &&
            ((this.timestampValid==null && other.getTimestampValid()==null) || 
             (this.timestampValid!=null &&
              this.timestampValid.equals(other.getTimestampValid())));
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
        if (getTimestampSignatures() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimestampSignatures());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTimestampSignatures(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTimestampTime() != null) {
            _hashCode += getTimestampTime().hashCode();
        }
        if (getTimestampType() != null) {
            _hashCode += getTimestampType().hashCode();
        }
        if (getTimestampValid() != null) {
            _hashCode += getTimestampValid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TimestampRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "TimestampRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestampSignatures");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "timestampSignatures"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureRef"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestampTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "timestampTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestampType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "timestampType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.TimestampType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestampValid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "timestampValid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
