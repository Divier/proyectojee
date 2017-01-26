/**
 * Co_document_operation_ExtendedProps_bean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class Co_document_operation_ExtendedProps_bean  implements java.io.Serializable {
    private java.lang.String comments;

    private boolean finishFirst;

    private boolean marcasgraficas;

    private java.lang.String body_email;

    private java.lang.String externalId;

    private java.lang.String subject_email;

    private int FExpired;

    public Co_document_operation_ExtendedProps_bean() {
    }

    public Co_document_operation_ExtendedProps_bean(
           java.lang.String comments,
           boolean finishFirst,
           boolean marcasgraficas,
           java.lang.String body_email,
           java.lang.String externalId,
           java.lang.String subject_email,
           int FExpired) {
           this.comments = comments;
           this.finishFirst = finishFirst;
           this.marcasgraficas = marcasgraficas;
           this.body_email = body_email;
           this.externalId = externalId;
           this.subject_email = subject_email;
           this.FExpired = FExpired;
    }


    /**
     * Gets the comments value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @return comments
     */
    public java.lang.String getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @param comments
     */
    public void setComments(java.lang.String comments) {
        this.comments = comments;
    }


    /**
     * Gets the finishFirst value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @return finishFirst
     */
    public boolean isFinishFirst() {
        return finishFirst;
    }


    /**
     * Sets the finishFirst value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @param finishFirst
     */
    public void setFinishFirst(boolean finishFirst) {
        this.finishFirst = finishFirst;
    }


    /**
     * Gets the marcasgraficas value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @return marcasgraficas
     */
    public boolean isMarcasgraficas() {
        return marcasgraficas;
    }


    /**
     * Sets the marcasgraficas value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @param marcasgraficas
     */
    public void setMarcasgraficas(boolean marcasgraficas) {
        this.marcasgraficas = marcasgraficas;
    }


    /**
     * Gets the body_email value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @return body_email
     */
    public java.lang.String getBody_email() {
        return body_email;
    }


    /**
     * Sets the body_email value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @param body_email
     */
    public void setBody_email(java.lang.String body_email) {
        this.body_email = body_email;
    }


    /**
     * Gets the externalId value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @return externalId
     */
    public java.lang.String getExternalId() {
        return externalId;
    }


    /**
     * Sets the externalId value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @param externalId
     */
    public void setExternalId(java.lang.String externalId) {
        this.externalId = externalId;
    }


    /**
     * Gets the subject_email value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @return subject_email
     */
    public java.lang.String getSubject_email() {
        return subject_email;
    }


    /**
     * Sets the subject_email value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @param subject_email
     */
    public void setSubject_email(java.lang.String subject_email) {
        this.subject_email = subject_email;
    }


    /**
     * Gets the FExpired value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @return FExpired
     */
    public int getFExpired() {
        return FExpired;
    }


    /**
     * Sets the FExpired value for this Co_document_operation_ExtendedProps_bean.
     * 
     * @param FExpired
     */
    public void setFExpired(int FExpired) {
        this.FExpired = FExpired;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Co_document_operation_ExtendedProps_bean)) return false;
        Co_document_operation_ExtendedProps_bean other = (Co_document_operation_ExtendedProps_bean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              this.comments.equals(other.getComments()))) &&
            this.finishFirst == other.isFinishFirst() &&
            this.marcasgraficas == other.isMarcasgraficas() &&
            ((this.body_email==null && other.getBody_email()==null) || 
             (this.body_email!=null &&
              this.body_email.equals(other.getBody_email()))) &&
            ((this.externalId==null && other.getExternalId()==null) || 
             (this.externalId!=null &&
              this.externalId.equals(other.getExternalId()))) &&
            ((this.subject_email==null && other.getSubject_email()==null) || 
             (this.subject_email!=null &&
              this.subject_email.equals(other.getSubject_email()))) &&
            this.FExpired == other.getFExpired();
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
        if (getComments() != null) {
            _hashCode += getComments().hashCode();
        }
        _hashCode += (isFinishFirst() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isMarcasgraficas() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getBody_email() != null) {
            _hashCode += getBody_email().hashCode();
        }
        if (getExternalId() != null) {
            _hashCode += getExternalId().hashCode();
        }
        if (getSubject_email() != null) {
            _hashCode += getSubject_email().hashCode();
        }
        _hashCode += getFExpired();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Co_document_operation_ExtendedProps_bean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_document_operation_ExtendedProps_bean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finishFirst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "finishFirst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marcasgraficas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "marcasgraficas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("body_email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "body_email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject_email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "subject_email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FExpired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "FExpired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
