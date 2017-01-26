/**
 * Co_circuit_query_bean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class Co_circuit_query_bean  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String title;

    private java.lang.String externalId;

    private int stateCode;

    private boolean isFinish;

    private java.lang.String reasonReject;

    private java.util.Calendar dateReg;

    private java.lang.String batchid;

    public Co_circuit_query_bean() {
    }

    public Co_circuit_query_bean(
           java.lang.String id,
           java.lang.String title,
           java.lang.String externalId,
           int stateCode,
           boolean isFinish,
           java.lang.String reasonReject,
           java.util.Calendar dateReg,
           java.lang.String batchid) {
           this.id = id;
           this.title = title;
           this.externalId = externalId;
           this.stateCode = stateCode;
           this.isFinish = isFinish;
           this.reasonReject = reasonReject;
           this.dateReg = dateReg;
           this.batchid = batchid;
    }


    /**
     * Gets the id value for this Co_circuit_query_bean.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Co_circuit_query_bean.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the title value for this Co_circuit_query_bean.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this Co_circuit_query_bean.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the externalId value for this Co_circuit_query_bean.
     * 
     * @return externalId
     */
    public java.lang.String getExternalId() {
        return externalId;
    }


    /**
     * Sets the externalId value for this Co_circuit_query_bean.
     * 
     * @param externalId
     */
    public void setExternalId(java.lang.String externalId) {
        this.externalId = externalId;
    }


    /**
     * Gets the stateCode value for this Co_circuit_query_bean.
     * 
     * @return stateCode
     */
    public int getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode value for this Co_circuit_query_bean.
     * 
     * @param stateCode
     */
    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * Gets the isFinish value for this Co_circuit_query_bean.
     * 
     * @return isFinish
     */
    public boolean isIsFinish() {
        return isFinish;
    }


    /**
     * Sets the isFinish value for this Co_circuit_query_bean.
     * 
     * @param isFinish
     */
    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }


    /**
     * Gets the reasonReject value for this Co_circuit_query_bean.
     * 
     * @return reasonReject
     */
    public java.lang.String getReasonReject() {
        return reasonReject;
    }


    /**
     * Sets the reasonReject value for this Co_circuit_query_bean.
     * 
     * @param reasonReject
     */
    public void setReasonReject(java.lang.String reasonReject) {
        this.reasonReject = reasonReject;
    }


    /**
     * Gets the dateReg value for this Co_circuit_query_bean.
     * 
     * @return dateReg
     */
    public java.util.Calendar getDateReg() {
        return dateReg;
    }


    /**
     * Sets the dateReg value for this Co_circuit_query_bean.
     * 
     * @param dateReg
     */
    public void setDateReg(java.util.Calendar dateReg) {
        this.dateReg = dateReg;
    }


    /**
     * Gets the batchid value for this Co_circuit_query_bean.
     * 
     * @return batchid
     */
    public java.lang.String getBatchid() {
        return batchid;
    }


    /**
     * Sets the batchid value for this Co_circuit_query_bean.
     * 
     * @param batchid
     */
    public void setBatchid(java.lang.String batchid) {
        this.batchid = batchid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Co_circuit_query_bean)) return false;
        Co_circuit_query_bean other = (Co_circuit_query_bean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.externalId==null && other.getExternalId()==null) || 
             (this.externalId!=null &&
              this.externalId.equals(other.getExternalId()))) &&
            this.stateCode == other.getStateCode() &&
            this.isFinish == other.isIsFinish() &&
            ((this.reasonReject==null && other.getReasonReject()==null) || 
             (this.reasonReject!=null &&
              this.reasonReject.equals(other.getReasonReject()))) &&
            ((this.dateReg==null && other.getDateReg()==null) || 
             (this.dateReg!=null &&
              this.dateReg.equals(other.getDateReg()))) &&
            ((this.batchid==null && other.getBatchid()==null) || 
             (this.batchid!=null &&
              this.batchid.equals(other.getBatchid())));
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
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getExternalId() != null) {
            _hashCode += getExternalId().hashCode();
        }
        _hashCode += getStateCode();
        _hashCode += (isIsFinish() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getReasonReject() != null) {
            _hashCode += getReasonReject().hashCode();
        }
        if (getDateReg() != null) {
            _hashCode += getDateReg().hashCode();
        }
        if (getBatchid() != null) {
            _hashCode += getBatchid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Co_circuit_query_bean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_circuit_query_bean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "title"));
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
        elemField.setFieldName("reasonReject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "reasonReject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateReg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "dateReg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "batchid"));
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
