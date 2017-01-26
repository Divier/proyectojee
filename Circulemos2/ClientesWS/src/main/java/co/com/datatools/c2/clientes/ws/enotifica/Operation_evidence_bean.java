/**
 * Operation_evidence_bean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

import java.util.Arrays;

public class Operation_evidence_bean implements java.io.Serializable {
    private java.lang.String idOperation;

    private java.lang.String idUser;

    private java.lang.String operationType;

    private int state;

    private java.lang.String fclose;

    private co.com.datatools.c2.clientes.ws.enotifica.Evidence_operation_bean[] evidencias;

    public Operation_evidence_bean() {
    }

    public Operation_evidence_bean(java.lang.String idOperation, java.lang.String idUser,
            java.lang.String operationType, int state, java.lang.String fclose,
            co.com.datatools.c2.clientes.ws.enotifica.Evidence_operation_bean[] evidencias) {
        this.idOperation = idOperation;
        this.idUser = idUser;
        this.operationType = operationType;
        this.state = state;
        this.fclose = fclose;
        this.evidencias = evidencias;
    }

    /**
     * Gets the idOperation value for this Operation_evidence_bean.
     * 
     * @return idOperation
     */
    public java.lang.String getIdOperation() {
        return idOperation;
    }

    /**
     * Sets the idOperation value for this Operation_evidence_bean.
     * 
     * @param idOperation
     */
    public void setIdOperation(java.lang.String idOperation) {
        this.idOperation = idOperation;
    }

    /**
     * Gets the idUser value for this Operation_evidence_bean.
     * 
     * @return idUser
     */
    public java.lang.String getIdUser() {
        return idUser;
    }

    /**
     * Sets the idUser value for this Operation_evidence_bean.
     * 
     * @param idUser
     */
    public void setIdUser(java.lang.String idUser) {
        this.idUser = idUser;
    }

    /**
     * Gets the operationType value for this Operation_evidence_bean.
     * 
     * @return operationType
     */
    public java.lang.String getOperationType() {
        return operationType;
    }

    /**
     * Sets the operationType value for this Operation_evidence_bean.
     * 
     * @param operationType
     */
    public void setOperationType(java.lang.String operationType) {
        this.operationType = operationType;
    }

    /**
     * Gets the state value for this Operation_evidence_bean.
     * 
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * Sets the state value for this Operation_evidence_bean.
     * 
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Gets the fclose value for this Operation_evidence_bean.
     * 
     * @return fclose
     */
    public java.lang.String getFclose() {
        return fclose;
    }

    /**
     * Sets the fclose value for this Operation_evidence_bean.
     * 
     * @param fclose
     */
    public void setFclose(java.lang.String fclose) {
        this.fclose = fclose;
    }

    /**
     * Gets the evidencias value for this Operation_evidence_bean.
     * 
     * @return evidencias
     */
    public co.com.datatools.c2.clientes.ws.enotifica.Evidence_operation_bean[] getEvidencias() {
        return evidencias;
    }

    /**
     * Sets the evidencias value for this Operation_evidence_bean.
     * 
     * @param evidencias
     */
    public void setEvidencias(co.com.datatools.c2.clientes.ws.enotifica.Evidence_operation_bean[] evidencias) {
        this.evidencias = evidencias;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Operation_evidence_bean))
            return false;
        Operation_evidence_bean other = (Operation_evidence_bean) obj;
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
                && ((this.idOperation == null && other.getIdOperation() == null)
                        || (this.idOperation != null && this.idOperation.equals(other.getIdOperation())))
                && ((this.idUser == null && other.getIdUser() == null)
                        || (this.idUser != null && this.idUser.equals(other.getIdUser())))
                && ((this.operationType == null && other.getOperationType() == null)
                        || (this.operationType != null && this.operationType.equals(other.getOperationType())))
                && this.state == other.getState()
                && ((this.fclose == null && other.getFclose() == null)
                        || (this.fclose != null && this.fclose.equals(other.getFclose())))
                && ((this.evidencias == null && other.getEvidencias() == null) || (this.evidencias != null
                        && java.util.Arrays.equals(this.evidencias, other.getEvidencias())));
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
        if (getIdOperation() != null) {
            _hashCode += getIdOperation().hashCode();
        }
        if (getIdUser() != null) {
            _hashCode += getIdUser().hashCode();
        }
        if (getOperationType() != null) {
            _hashCode += getOperationType().hashCode();
        }
        _hashCode += getState();
        if (getFclose() != null) {
            _hashCode += getFclose().hashCode();
        }
        if (getEvidencias() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(getEvidencias()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEvidencias(), i);
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
            Operation_evidence_bean.class, true);

    static {
        typeDesc.setXmlType(
                new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operation_evidence_bean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOperation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "idOperation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "idUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fclose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "fclose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("evidencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "evidencias"));
        elemField.setXmlType(
                new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "evidence_operation_bean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(
                new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "evidence_operation_bean"));
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
        return "Operation_evidence_bean [idOperation=" + idOperation + ", idUser=" + idUser + ", operationType="
                + operationType + ", state=" + state + ", fclose=" + fclose + ", evidencias="
                + Arrays.toString(evidencias) + "]";
    }
}