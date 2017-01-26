/**
 * CreationCircuitResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class CreationCircuitResponse implements java.io.Serializable {
    private java.lang.String creationCode;

    private java.lang.String errorDescription;

    private java.lang.String idcircuit;

    public CreationCircuitResponse() {
    }

    public CreationCircuitResponse(java.lang.String creationCode, java.lang.String errorDescription,
            java.lang.String idcircuit) {
        this.creationCode = creationCode;
        this.errorDescription = errorDescription;
        this.idcircuit = idcircuit;
    }

    /**
     * Gets the creationCode value for this CreationCircuitResponse.
     * 
     * @return creationCode
     */
    public java.lang.String getCreationCode() {
        return creationCode;
    }

    /**
     * Sets the creationCode value for this CreationCircuitResponse.
     * 
     * @param creationCode
     */
    public void setCreationCode(java.lang.String creationCode) {
        this.creationCode = creationCode;
    }

    /**
     * Gets the errorDescription value for this CreationCircuitResponse.
     * 
     * @return errorDescription
     */
    public java.lang.String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Sets the errorDescription value for this CreationCircuitResponse.
     * 
     * @param errorDescription
     */
    public void setErrorDescription(java.lang.String errorDescription) {
        this.errorDescription = errorDescription;
    }

    /**
     * Gets the idcircuit value for this CreationCircuitResponse.
     * 
     * @return idcircuit
     */
    public java.lang.String getIdcircuit() {
        return idcircuit;
    }

    /**
     * Sets the idcircuit value for this CreationCircuitResponse.
     * 
     * @param idcircuit
     */
    public void setIdcircuit(java.lang.String idcircuit) {
        this.idcircuit = idcircuit;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreationCircuitResponse))
            return false;
        CreationCircuitResponse other = (CreationCircuitResponse) obj;
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true
                && ((this.creationCode == null && other.getCreationCode() == null)
                        || (this.creationCode != null && this.creationCode.equals(other.getCreationCode())))
                && ((this.errorDescription == null && other.getErrorDescription() == null)
                        || (this.errorDescription != null && this.errorDescription.equals(other.getErrorDescription())))
                && ((this.idcircuit == null && other.getIdcircuit() == null)
                        || (this.idcircuit != null && this.idcircuit.equals(other.getIdcircuit())));
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
        if (getCreationCode() != null) {
            _hashCode += getCreationCode().hashCode();
        }
        if (getErrorDescription() != null) {
            _hashCode += getErrorDescription().hashCode();
        }
        if (getIdcircuit() != null) {
            _hashCode += getIdcircuit().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
            CreationCircuitResponse.class, true);

    static {
        typeDesc.setXmlType(
                new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CreationCircuitResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "creationCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDescription");
        elemField
                .setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "errorDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idcircuit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "idcircuit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
    public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
            java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
            java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

    @Override
    public String toString() {
        return "CreationCircuitResponse [creationCode=" + creationCode + ", errorDescription=" + errorDescription
                + ", idcircuit=" + idcircuit + "]";
    }
}