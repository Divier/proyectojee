/**
 * Evidence_operation_bean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class Evidence_operation_bean  implements java.io.Serializable {
    private java.lang.String tipoEvidencia;

    private java.lang.String subTipo;

    private java.lang.String idEvidencia;

    private java.lang.String contenido;

    public Evidence_operation_bean() {
    }

    public Evidence_operation_bean(
           java.lang.String tipoEvidencia,
           java.lang.String subTipo,
           java.lang.String idEvidencia,
           java.lang.String contenido) {
           this.tipoEvidencia = tipoEvidencia;
           this.subTipo = subTipo;
           this.idEvidencia = idEvidencia;
           this.contenido = contenido;
    }


    /**
     * Gets the tipoEvidencia value for this Evidence_operation_bean.
     * 
     * @return tipoEvidencia
     */
    public java.lang.String getTipoEvidencia() {
        return tipoEvidencia;
    }


    /**
     * Sets the tipoEvidencia value for this Evidence_operation_bean.
     * 
     * @param tipoEvidencia
     */
    public void setTipoEvidencia(java.lang.String tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }


    /**
     * Gets the subTipo value for this Evidence_operation_bean.
     * 
     * @return subTipo
     */
    public java.lang.String getSubTipo() {
        return subTipo;
    }


    /**
     * Sets the subTipo value for this Evidence_operation_bean.
     * 
     * @param subTipo
     */
    public void setSubTipo(java.lang.String subTipo) {
        this.subTipo = subTipo;
    }


    /**
     * Gets the idEvidencia value for this Evidence_operation_bean.
     * 
     * @return idEvidencia
     */
    public java.lang.String getIdEvidencia() {
        return idEvidencia;
    }


    /**
     * Sets the idEvidencia value for this Evidence_operation_bean.
     * 
     * @param idEvidencia
     */
    public void setIdEvidencia(java.lang.String idEvidencia) {
        this.idEvidencia = idEvidencia;
    }


    /**
     * Gets the contenido value for this Evidence_operation_bean.
     * 
     * @return contenido
     */
    public java.lang.String getContenido() {
        return contenido;
    }


    /**
     * Sets the contenido value for this Evidence_operation_bean.
     * 
     * @param contenido
     */
    public void setContenido(java.lang.String contenido) {
        this.contenido = contenido;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Evidence_operation_bean)) return false;
        Evidence_operation_bean other = (Evidence_operation_bean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoEvidencia==null && other.getTipoEvidencia()==null) || 
             (this.tipoEvidencia!=null &&
              this.tipoEvidencia.equals(other.getTipoEvidencia()))) &&
            ((this.subTipo==null && other.getSubTipo()==null) || 
             (this.subTipo!=null &&
              this.subTipo.equals(other.getSubTipo()))) &&
            ((this.idEvidencia==null && other.getIdEvidencia()==null) || 
             (this.idEvidencia!=null &&
              this.idEvidencia.equals(other.getIdEvidencia()))) &&
            ((this.contenido==null && other.getContenido()==null) || 
             (this.contenido!=null &&
              this.contenido.equals(other.getContenido())));
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
        if (getTipoEvidencia() != null) {
            _hashCode += getTipoEvidencia().hashCode();
        }
        if (getSubTipo() != null) {
            _hashCode += getSubTipo().hashCode();
        }
        if (getIdEvidencia() != null) {
            _hashCode += getIdEvidencia().hashCode();
        }
        if (getContenido() != null) {
            _hashCode += getContenido().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Evidence_operation_bean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "evidence_operation_bean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEvidencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "tipoEvidencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subTipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "subTipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idEvidencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "idEvidencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contenido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "contenido"));
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
