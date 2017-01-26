/**
 * CircuitContactProperties.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class CircuitContactProperties  implements java.io.Serializable {
    private java.lang.String contactid;

    private java.lang.String contactName;

    private java.lang.String contactEmail;

    private boolean contacttelf;

    private boolean hasEncryptCert;

    private co.com.datatools.c2.clientes.ws.enotifica.CircuitCompaniesProperties[] userCompanies;

    public CircuitContactProperties() {
    }

    public CircuitContactProperties(
           java.lang.String contactid,
           java.lang.String contactName,
           java.lang.String contactEmail,
           boolean contacttelf,
           boolean hasEncryptCert,
           co.com.datatools.c2.clientes.ws.enotifica.CircuitCompaniesProperties[] userCompanies) {
           this.contactid = contactid;
           this.contactName = contactName;
           this.contactEmail = contactEmail;
           this.contacttelf = contacttelf;
           this.hasEncryptCert = hasEncryptCert;
           this.userCompanies = userCompanies;
    }


    /**
     * Gets the contactid value for this CircuitContactProperties.
     * 
     * @return contactid
     */
    public java.lang.String getContactid() {
        return contactid;
    }


    /**
     * Sets the contactid value for this CircuitContactProperties.
     * 
     * @param contactid
     */
    public void setContactid(java.lang.String contactid) {
        this.contactid = contactid;
    }


    /**
     * Gets the contactName value for this CircuitContactProperties.
     * 
     * @return contactName
     */
    public java.lang.String getContactName() {
        return contactName;
    }


    /**
     * Sets the contactName value for this CircuitContactProperties.
     * 
     * @param contactName
     */
    public void setContactName(java.lang.String contactName) {
        this.contactName = contactName;
    }


    /**
     * Gets the contactEmail value for this CircuitContactProperties.
     * 
     * @return contactEmail
     */
    public java.lang.String getContactEmail() {
        return contactEmail;
    }


    /**
     * Sets the contactEmail value for this CircuitContactProperties.
     * 
     * @param contactEmail
     */
    public void setContactEmail(java.lang.String contactEmail) {
        this.contactEmail = contactEmail;
    }


    /**
     * Gets the contacttelf value for this CircuitContactProperties.
     * 
     * @return contacttelf
     */
    public boolean isContacttelf() {
        return contacttelf;
    }


    /**
     * Sets the contacttelf value for this CircuitContactProperties.
     * 
     * @param contacttelf
     */
    public void setContacttelf(boolean contacttelf) {
        this.contacttelf = contacttelf;
    }


    /**
     * Gets the hasEncryptCert value for this CircuitContactProperties.
     * 
     * @return hasEncryptCert
     */
    public boolean isHasEncryptCert() {
        return hasEncryptCert;
    }


    /**
     * Sets the hasEncryptCert value for this CircuitContactProperties.
     * 
     * @param hasEncryptCert
     */
    public void setHasEncryptCert(boolean hasEncryptCert) {
        this.hasEncryptCert = hasEncryptCert;
    }


    /**
     * Gets the userCompanies value for this CircuitContactProperties.
     * 
     * @return userCompanies
     */
    public co.com.datatools.c2.clientes.ws.enotifica.CircuitCompaniesProperties[] getUserCompanies() {
        return userCompanies;
    }


    /**
     * Sets the userCompanies value for this CircuitContactProperties.
     * 
     * @param userCompanies
     */
    public void setUserCompanies(co.com.datatools.c2.clientes.ws.enotifica.CircuitCompaniesProperties[] userCompanies) {
        this.userCompanies = userCompanies;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CircuitContactProperties)) return false;
        CircuitContactProperties other = (CircuitContactProperties) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contactid==null && other.getContactid()==null) || 
             (this.contactid!=null &&
              this.contactid.equals(other.getContactid()))) &&
            ((this.contactName==null && other.getContactName()==null) || 
             (this.contactName!=null &&
              this.contactName.equals(other.getContactName()))) &&
            ((this.contactEmail==null && other.getContactEmail()==null) || 
             (this.contactEmail!=null &&
              this.contactEmail.equals(other.getContactEmail()))) &&
            this.contacttelf == other.isContacttelf() &&
            this.hasEncryptCert == other.isHasEncryptCert() &&
            ((this.userCompanies==null && other.getUserCompanies()==null) || 
             (this.userCompanies!=null &&
              java.util.Arrays.equals(this.userCompanies, other.getUserCompanies())));
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
        if (getContactid() != null) {
            _hashCode += getContactid().hashCode();
        }
        if (getContactName() != null) {
            _hashCode += getContactName().hashCode();
        }
        if (getContactEmail() != null) {
            _hashCode += getContactEmail().hashCode();
        }
        _hashCode += (isContacttelf() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isHasEncryptCert() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getUserCompanies() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserCompanies());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUserCompanies(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CircuitContactProperties.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitContactProperties"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "contactid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "contactName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "contactEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contacttelf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "contacttelf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasEncryptCert");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "hasEncryptCert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userCompanies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "userCompanies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitCompaniesProperties"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitCompaniesProperties"));
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
