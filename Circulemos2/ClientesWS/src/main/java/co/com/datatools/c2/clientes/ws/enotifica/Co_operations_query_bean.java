/**
 * Co_operations_query_bean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

import java.util.Arrays;

public class Co_operations_query_bean implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String externalId;

    private java.lang.String applicationType;

    private java.util.Calendar expireDate;

    private java.util.Calendar closeDate;

    private int stateCode;

    private boolean isFinish;

    private java.util.Calendar createDate;

    private java.util.Calendar smtpDate;

    private int smtpCode;

    private java.lang.String smtpDesc;

    private co.com.datatools.c2.clientes.ws.enotifica.MetadataBean[] metadataCircuit;

    public Co_operations_query_bean() {
    }

    public Co_operations_query_bean(java.lang.String id, java.lang.String externalId, java.lang.String applicationType,
            java.util.Calendar expireDate, java.util.Calendar closeDate, int stateCode, boolean isFinish,
            java.util.Calendar createDate, java.util.Calendar smtpDate, int smtpCode, java.lang.String smtpDesc,
            co.com.datatools.c2.clientes.ws.enotifica.MetadataBean[] metadataCircuit) {
        this.id = id;
        this.externalId = externalId;
        this.applicationType = applicationType;
        this.expireDate = expireDate;
        this.closeDate = closeDate;
        this.stateCode = stateCode;
        this.isFinish = isFinish;
        this.createDate = createDate;
        this.smtpDate = smtpDate;
        this.smtpCode = smtpCode;
        this.smtpDesc = smtpDesc;
        this.metadataCircuit = metadataCircuit;
    }

    /**
     * Gets the id value for this Co_operations_query_bean.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Sets the id value for this Co_operations_query_bean.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     * Gets the externalId value for this Co_operations_query_bean.
     * 
     * @return externalId
     */
    public java.lang.String getExternalId() {
        return externalId;
    }

    /**
     * Sets the externalId value for this Co_operations_query_bean.
     * 
     * @param externalId
     */
    public void setExternalId(java.lang.String externalId) {
        this.externalId = externalId;
    }

    /**
     * Gets the applicationType value for this Co_operations_query_bean.
     * 
     * @return applicationType
     */
    public java.lang.String getApplicationType() {
        return applicationType;
    }

    /**
     * Sets the applicationType value for this Co_operations_query_bean.
     * 
     * @param applicationType
     */
    public void setApplicationType(java.lang.String applicationType) {
        this.applicationType = applicationType;
    }

    /**
     * Gets the expireDate value for this Co_operations_query_bean.
     * 
     * @return expireDate
     */
    public java.util.Calendar getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the expireDate value for this Co_operations_query_bean.
     * 
     * @param expireDate
     */
    public void setExpireDate(java.util.Calendar expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Gets the closeDate value for this Co_operations_query_bean.
     * 
     * @return closeDate
     */
    public java.util.Calendar getCloseDate() {
        return closeDate;
    }

    /**
     * Sets the closeDate value for this Co_operations_query_bean.
     * 
     * @param closeDate
     */
    public void setCloseDate(java.util.Calendar closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * Gets the stateCode value for this Co_operations_query_bean.
     * 
     * @return stateCode
     */
    public int getStateCode() {
        return stateCode;
    }

    /**
     * Sets the stateCode value for this Co_operations_query_bean.
     * 
     * @param stateCode
     */
    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * Gets the isFinish value for this Co_operations_query_bean.
     * 
     * @return isFinish
     */
    public boolean isIsFinish() {
        return isFinish;
    }

    /**
     * Sets the isFinish value for this Co_operations_query_bean.
     * 
     * @param isFinish
     */
    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    /**
     * Gets the createDate value for this Co_operations_query_bean.
     * 
     * @return createDate
     */
    public java.util.Calendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the createDate value for this Co_operations_query_bean.
     * 
     * @param createDate
     */
    public void setCreateDate(java.util.Calendar createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the smtpDate value for this Co_operations_query_bean.
     * 
     * @return smtpDate
     */
    public java.util.Calendar getSmtpDate() {
        return smtpDate;
    }

    /**
     * Sets the smtpDate value for this Co_operations_query_bean.
     * 
     * @param smtpDate
     */
    public void setSmtpDate(java.util.Calendar smtpDate) {
        this.smtpDate = smtpDate;
    }

    /**
     * Gets the smtpCode value for this Co_operations_query_bean.
     * 
     * @return smtpCode
     */
    public int getSmtpCode() {
        return smtpCode;
    }

    /**
     * Sets the smtpCode value for this Co_operations_query_bean.
     * 
     * @param smtpCode
     */
    public void setSmtpCode(int smtpCode) {
        this.smtpCode = smtpCode;
    }

    /**
     * Gets the smtpDesc value for this Co_operations_query_bean.
     * 
     * @return smtpDesc
     */
    public java.lang.String getSmtpDesc() {
        return smtpDesc;
    }

    /**
     * Sets the smtpDesc value for this Co_operations_query_bean.
     * 
     * @param smtpDesc
     */
    public void setSmtpDesc(java.lang.String smtpDesc) {
        this.smtpDesc = smtpDesc;
    }

    /**
     * Gets the metadataCircuit value for this Co_operations_query_bean.
     * 
     * @return metadataCircuit
     */
    public co.com.datatools.c2.clientes.ws.enotifica.MetadataBean[] getMetadataCircuit() {
        return metadataCircuit;
    }

    /**
     * Sets the metadataCircuit value for this Co_operations_query_bean.
     * 
     * @param metadataCircuit
     */
    public void setMetadataCircuit(co.com.datatools.c2.clientes.ws.enotifica.MetadataBean[] metadataCircuit) {
        this.metadataCircuit = metadataCircuit;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Co_operations_query_bean))
            return false;
        Co_operations_query_bean other = (Co_operations_query_bean) obj;
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
                && ((this.id == null && other.getId() == null) || (this.id != null && this.id.equals(other.getId())))
                && ((this.externalId == null && other.getExternalId() == null)
                        || (this.externalId != null && this.externalId.equals(other.getExternalId())))
                && ((this.applicationType == null && other.getApplicationType() == null)
                        || (this.applicationType != null && this.applicationType.equals(other.getApplicationType())))
                && ((this.expireDate == null && other.getExpireDate() == null)
                        || (this.expireDate != null && this.expireDate.equals(other.getExpireDate())))
                && ((this.closeDate == null && other.getCloseDate() == null)
                        || (this.closeDate != null && this.closeDate.equals(other.getCloseDate())))
                && this.stateCode == other.getStateCode() && this.isFinish == other.isIsFinish()
                && ((this.createDate == null && other.getCreateDate() == null)
                        || (this.createDate != null && this.createDate.equals(other.getCreateDate())))
                && ((this.smtpDate == null && other.getSmtpDate() == null)
                        || (this.smtpDate != null && this.smtpDate.equals(other.getSmtpDate())))
                && this.smtpCode == other.getSmtpCode()
                && ((this.smtpDesc == null && other.getSmtpDesc() == null)
                        || (this.smtpDesc != null && this.smtpDesc.equals(other.getSmtpDesc())))
                && ((this.metadataCircuit == null && other.getMetadataCircuit() == null)
                        || (this.metadataCircuit != null
                                && java.util.Arrays.equals(this.metadataCircuit, other.getMetadataCircuit())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getExternalId() != null) {
            _hashCode += getExternalId().hashCode();
        }
        if (getApplicationType() != null) {
            _hashCode += getApplicationType().hashCode();
        }
        if (getExpireDate() != null) {
            _hashCode += getExpireDate().hashCode();
        }
        if (getCloseDate() != null) {
            _hashCode += getCloseDate().hashCode();
        }
        _hashCode += getStateCode();
        _hashCode += (isIsFinish() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCreateDate() != null) {
            _hashCode += getCreateDate().hashCode();
        }
        if (getSmtpDate() != null) {
            _hashCode += getSmtpDate().hashCode();
        }
        _hashCode += getSmtpCode();
        if (getSmtpDesc() != null) {
            _hashCode += getSmtpDesc().hashCode();
        }
        if (getMetadataCircuit() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(getMetadataCircuit()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMetadataCircuit(), i);
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
            Co_operations_query_bean.class, true);

    static {
        typeDesc.setXmlType(
                new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_operations_query_bean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "externalId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "applicationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expireDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "expireDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("closeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "closeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "stateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isFinish");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "isFinish"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "createDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smtpDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "smtpDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smtpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "smtpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smtpDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "smtpDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metadataCircuit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "metadataCircuit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "MetadataBean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "MetadataBean"));
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
        return "Co_operations_query_bean [id=" + id + ", externalId=" + externalId + ", applicationType="
                + applicationType + ", expireDate=" + expireDate + ", closeDate=" + closeDate + ", stateCode="
                + stateCode + ", isFinish=" + isFinish + ", createDate=" + createDate + ", smtpDate=" + smtpDate
                + ", smtpCode=" + smtpCode + ", smtpDesc=" + smtpDesc + ", metadataCircuit="
                + Arrays.toString(metadataCircuit) + "]";
    }
}