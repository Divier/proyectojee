/**
 * CircuitReceiver.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class CircuitReceiver  implements java.io.Serializable {
    private java.lang.String email;

    private java.lang.String cifCompany;

    private java.lang.String operationType;

    private java.lang.String body_email;

    private java.lang.String name;

    private java.lang.String tlf;

    private java.lang.String dni;

    private java.lang.String externalId;

    private java.lang.String subject_email;

    private int FExpired;

    public CircuitReceiver() {
    }

    public CircuitReceiver(
           java.lang.String email,
           java.lang.String cifCompany,
           java.lang.String operationType,
           java.lang.String body_email,
           java.lang.String name,
           java.lang.String tlf,
           java.lang.String dni,
           java.lang.String externalId,
           java.lang.String subject_email,
           int FExpired) {
           this.email = email;
           this.cifCompany = cifCompany;
           this.operationType = operationType;
           this.body_email = body_email;
           this.name = name;
           this.tlf = tlf;
           this.dni = dni;
           this.externalId = externalId;
           this.subject_email = subject_email;
           this.FExpired = FExpired;
    }


    /**
     * Gets the email value for this CircuitReceiver.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this CircuitReceiver.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the cifCompany value for this CircuitReceiver.
     * 
     * @return cifCompany
     */
    public java.lang.String getCifCompany() {
        return cifCompany;
    }


    /**
     * Sets the cifCompany value for this CircuitReceiver.
     * 
     * @param cifCompany
     */
    public void setCifCompany(java.lang.String cifCompany) {
        this.cifCompany = cifCompany;
    }


    /**
     * Gets the operationType value for this CircuitReceiver.
     * 
     * @return operationType
     */
    public java.lang.String getOperationType() {
        return operationType;
    }


    /**
     * Sets the operationType value for this CircuitReceiver.
     * 
     * @param operationType
     */
    public void setOperationType(java.lang.String operationType) {
        this.operationType = operationType;
    }


    /**
     * Gets the body_email value for this CircuitReceiver.
     * 
     * @return body_email
     */
    public java.lang.String getBody_email() {
        return body_email;
    }


    /**
     * Sets the body_email value for this CircuitReceiver.
     * 
     * @param body_email
     */
    public void setBody_email(java.lang.String body_email) {
        this.body_email = body_email;
    }


    /**
     * Gets the name value for this CircuitReceiver.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this CircuitReceiver.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the tlf value for this CircuitReceiver.
     * 
     * @return tlf
     */
    public java.lang.String getTlf() {
        return tlf;
    }


    /**
     * Sets the tlf value for this CircuitReceiver.
     * 
     * @param tlf
     */
    public void setTlf(java.lang.String tlf) {
        this.tlf = tlf;
    }


    /**
     * Gets the dni value for this CircuitReceiver.
     * 
     * @return dni
     */
    public java.lang.String getDni() {
        return dni;
    }


    /**
     * Sets the dni value for this CircuitReceiver.
     * 
     * @param dni
     */
    public void setDni(java.lang.String dni) {
        this.dni = dni;
    }


    /**
     * Gets the externalId value for this CircuitReceiver.
     * 
     * @return externalId
     */
    public java.lang.String getExternalId() {
        return externalId;
    }


    /**
     * Sets the externalId value for this CircuitReceiver.
     * 
     * @param externalId
     */
    public void setExternalId(java.lang.String externalId) {
        this.externalId = externalId;
    }


    /**
     * Gets the subject_email value for this CircuitReceiver.
     * 
     * @return subject_email
     */
    public java.lang.String getSubject_email() {
        return subject_email;
    }


    /**
     * Sets the subject_email value for this CircuitReceiver.
     * 
     * @param subject_email
     */
    public void setSubject_email(java.lang.String subject_email) {
        this.subject_email = subject_email;
    }


    /**
     * Gets the FExpired value for this CircuitReceiver.
     * 
     * @return FExpired
     */
    public int getFExpired() {
        return FExpired;
    }


    /**
     * Sets the FExpired value for this CircuitReceiver.
     * 
     * @param FExpired
     */
    public void setFExpired(int FExpired) {
        this.FExpired = FExpired;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CircuitReceiver)) return false;
        CircuitReceiver other = (CircuitReceiver) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.cifCompany==null && other.getCifCompany()==null) || 
             (this.cifCompany!=null &&
              this.cifCompany.equals(other.getCifCompany()))) &&
            ((this.operationType==null && other.getOperationType()==null) || 
             (this.operationType!=null &&
              this.operationType.equals(other.getOperationType()))) &&
            ((this.body_email==null && other.getBody_email()==null) || 
             (this.body_email!=null &&
              this.body_email.equals(other.getBody_email()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.tlf==null && other.getTlf()==null) || 
             (this.tlf!=null &&
              this.tlf.equals(other.getTlf()))) &&
            ((this.dni==null && other.getDni()==null) || 
             (this.dni!=null &&
              this.dni.equals(other.getDni()))) &&
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
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getCifCompany() != null) {
            _hashCode += getCifCompany().hashCode();
        }
        if (getOperationType() != null) {
            _hashCode += getOperationType().hashCode();
        }
        if (getBody_email() != null) {
            _hashCode += getBody_email().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getTlf() != null) {
            _hashCode += getTlf().hashCode();
        }
        if (getDni() != null) {
            _hashCode += getDni().hashCode();
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
        new org.apache.axis.description.TypeDesc(CircuitReceiver.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitReceiver"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cifCompany");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "cifCompany"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "OperationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "tlf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dni");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "dni"));
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
