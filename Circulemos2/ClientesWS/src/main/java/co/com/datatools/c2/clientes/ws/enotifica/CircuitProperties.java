/**
 * CircuitProperties.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class CircuitProperties  implements java.io.Serializable {
    private java.lang.String idApplication;

    private java.lang.String nombre;

    private int cnt_IniciarApp;

    private int cnt_OTPApp;

    private int cnt_DispApp;

    private int cnt_SigApp;

    public CircuitProperties() {
    }

    public CircuitProperties(
           java.lang.String idApplication,
           java.lang.String nombre,
           int cnt_IniciarApp,
           int cnt_OTPApp,
           int cnt_DispApp,
           int cnt_SigApp) {
           this.idApplication = idApplication;
           this.nombre = nombre;
           this.cnt_IniciarApp = cnt_IniciarApp;
           this.cnt_OTPApp = cnt_OTPApp;
           this.cnt_DispApp = cnt_DispApp;
           this.cnt_SigApp = cnt_SigApp;
    }


    /**
     * Gets the idApplication value for this CircuitProperties.
     * 
     * @return idApplication
     */
    public java.lang.String getIdApplication() {
        return idApplication;
    }


    /**
     * Sets the idApplication value for this CircuitProperties.
     * 
     * @param idApplication
     */
    public void setIdApplication(java.lang.String idApplication) {
        this.idApplication = idApplication;
    }


    /**
     * Gets the nombre value for this CircuitProperties.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this CircuitProperties.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the cnt_IniciarApp value for this CircuitProperties.
     * 
     * @return cnt_IniciarApp
     */
    public int getCnt_IniciarApp() {
        return cnt_IniciarApp;
    }


    /**
     * Sets the cnt_IniciarApp value for this CircuitProperties.
     * 
     * @param cnt_IniciarApp
     */
    public void setCnt_IniciarApp(int cnt_IniciarApp) {
        this.cnt_IniciarApp = cnt_IniciarApp;
    }


    /**
     * Gets the cnt_OTPApp value for this CircuitProperties.
     * 
     * @return cnt_OTPApp
     */
    public int getCnt_OTPApp() {
        return cnt_OTPApp;
    }


    /**
     * Sets the cnt_OTPApp value for this CircuitProperties.
     * 
     * @param cnt_OTPApp
     */
    public void setCnt_OTPApp(int cnt_OTPApp) {
        this.cnt_OTPApp = cnt_OTPApp;
    }


    /**
     * Gets the cnt_DispApp value for this CircuitProperties.
     * 
     * @return cnt_DispApp
     */
    public int getCnt_DispApp() {
        return cnt_DispApp;
    }


    /**
     * Sets the cnt_DispApp value for this CircuitProperties.
     * 
     * @param cnt_DispApp
     */
    public void setCnt_DispApp(int cnt_DispApp) {
        this.cnt_DispApp = cnt_DispApp;
    }


    /**
     * Gets the cnt_SigApp value for this CircuitProperties.
     * 
     * @return cnt_SigApp
     */
    public int getCnt_SigApp() {
        return cnt_SigApp;
    }


    /**
     * Sets the cnt_SigApp value for this CircuitProperties.
     * 
     * @param cnt_SigApp
     */
    public void setCnt_SigApp(int cnt_SigApp) {
        this.cnt_SigApp = cnt_SigApp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CircuitProperties)) return false;
        CircuitProperties other = (CircuitProperties) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idApplication==null && other.getIdApplication()==null) || 
             (this.idApplication!=null &&
              this.idApplication.equals(other.getIdApplication()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            this.cnt_IniciarApp == other.getCnt_IniciarApp() &&
            this.cnt_OTPApp == other.getCnt_OTPApp() &&
            this.cnt_DispApp == other.getCnt_DispApp() &&
            this.cnt_SigApp == other.getCnt_SigApp();
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
        if (getIdApplication() != null) {
            _hashCode += getIdApplication().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        _hashCode += getCnt_IniciarApp();
        _hashCode += getCnt_OTPApp();
        _hashCode += getCnt_DispApp();
        _hashCode += getCnt_SigApp();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CircuitProperties.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitProperties"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idApplication");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "idApplication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnt_IniciarApp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "cnt_IniciarApp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnt_OTPApp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "cnt_OTPApp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnt_DispApp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "cnt_DispApp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnt_SigApp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "cnt_SigApp"));
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
