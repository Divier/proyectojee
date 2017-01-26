/**
 * CircuitEvidenceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

import java.util.Arrays;

public class CircuitEvidenceResponse implements java.io.Serializable {
    private int errorCode;

    private java.lang.String errorMessage;

    private co.com.datatools.c2.clientes.ws.enotifica.Operation_evidence_bean[] operations;

    public CircuitEvidenceResponse() {
    }

    public CircuitEvidenceResponse(int errorCode, java.lang.String errorMessage,
            co.com.datatools.c2.clientes.ws.enotifica.Operation_evidence_bean[] operations) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.operations = operations;
    }

    /**
     * Gets the errorCode value for this CircuitEvidenceResponse.
     * 
     * @return errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the errorCode value for this CircuitEvidenceResponse.
     * 
     * @param errorCode
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the errorMessage value for this CircuitEvidenceResponse.
     * 
     * @return errorMessage
     */
    public java.lang.String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the errorMessage value for this CircuitEvidenceResponse.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(java.lang.String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the operations value for this CircuitEvidenceResponse.
     * 
     * @return operations
     */
    public co.com.datatools.c2.clientes.ws.enotifica.Operation_evidence_bean[] getOperations() {
        return operations;
    }

    /**
     * Sets the operations value for this CircuitEvidenceResponse.
     * 
     * @param operations
     */
    public void setOperations(co.com.datatools.c2.clientes.ws.enotifica.Operation_evidence_bean[] operations) {
        this.operations = operations;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CircuitEvidenceResponse))
            return false;
        CircuitEvidenceResponse other = (CircuitEvidenceResponse) obj;
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && this.errorCode == other.getErrorCode()
                && ((this.errorMessage == null && other.getErrorMessage() == null)
                        || (this.errorMessage != null && this.errorMessage.equals(other.getErrorMessage())))
                && ((this.operations == null && other.getOperations() == null) || (this.operations != null
                        && java.util.Arrays.equals(this.operations, other.getOperations())));
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
        _hashCode += getErrorCode();
        if (getErrorMessage() != null) {
            _hashCode += getErrorMessage().hashCode();
        }
        if (getOperations() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(getOperations()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOperations(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
            CircuitEvidenceResponse.class, true);

    static {
        typeDesc.setXmlType(
                new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitEvidenceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "errorMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operations");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operations"));
        elemField.setXmlType(
                new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operation_evidence_bean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(
                new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operation_evidence_bean"));
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
        return "CircuitEvidenceResponse [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", operations="
                + Arrays.toString(operations) + "]";
    }
}