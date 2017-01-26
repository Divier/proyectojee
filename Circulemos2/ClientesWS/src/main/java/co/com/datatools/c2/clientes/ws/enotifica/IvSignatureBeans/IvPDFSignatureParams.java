/**
 * IvPDFSignatureParams.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvPDFSignatureParams  implements java.io.Serializable {
    private java.lang.String pwd;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureBackground signatureBackGroundConfig;

    private java.lang.String signatureFieldName;

    private java.lang.Boolean signatureFilterOnlyDocSignatures;

    private java.lang.Boolean signatureVisible;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureWidgetProps widgetProps;

    public IvPDFSignatureParams() {
    }

    public IvPDFSignatureParams(
           java.lang.String pwd,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureBackground signatureBackGroundConfig,
           java.lang.String signatureFieldName,
           java.lang.Boolean signatureFilterOnlyDocSignatures,
           java.lang.Boolean signatureVisible,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureWidgetProps widgetProps) {
           this.pwd = pwd;
           this.signatureBackGroundConfig = signatureBackGroundConfig;
           this.signatureFieldName = signatureFieldName;
           this.signatureFilterOnlyDocSignatures = signatureFilterOnlyDocSignatures;
           this.signatureVisible = signatureVisible;
           this.widgetProps = widgetProps;
    }


    /**
     * Gets the pwd value for this IvPDFSignatureParams.
     * 
     * @return pwd
     */
    public java.lang.String getPwd() {
        return pwd;
    }


    /**
     * Sets the pwd value for this IvPDFSignatureParams.
     * 
     * @param pwd
     */
    public void setPwd(java.lang.String pwd) {
        this.pwd = pwd;
    }


    /**
     * Gets the signatureBackGroundConfig value for this IvPDFSignatureParams.
     * 
     * @return signatureBackGroundConfig
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureBackground getSignatureBackGroundConfig() {
        return signatureBackGroundConfig;
    }


    /**
     * Sets the signatureBackGroundConfig value for this IvPDFSignatureParams.
     * 
     * @param signatureBackGroundConfig
     */
    public void setSignatureBackGroundConfig(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureBackground signatureBackGroundConfig) {
        this.signatureBackGroundConfig = signatureBackGroundConfig;
    }


    /**
     * Gets the signatureFieldName value for this IvPDFSignatureParams.
     * 
     * @return signatureFieldName
     */
    public java.lang.String getSignatureFieldName() {
        return signatureFieldName;
    }


    /**
     * Sets the signatureFieldName value for this IvPDFSignatureParams.
     * 
     * @param signatureFieldName
     */
    public void setSignatureFieldName(java.lang.String signatureFieldName) {
        this.signatureFieldName = signatureFieldName;
    }


    /**
     * Gets the signatureFilterOnlyDocSignatures value for this IvPDFSignatureParams.
     * 
     * @return signatureFilterOnlyDocSignatures
     */
    public java.lang.Boolean getSignatureFilterOnlyDocSignatures() {
        return signatureFilterOnlyDocSignatures;
    }


    /**
     * Sets the signatureFilterOnlyDocSignatures value for this IvPDFSignatureParams.
     * 
     * @param signatureFilterOnlyDocSignatures
     */
    public void setSignatureFilterOnlyDocSignatures(java.lang.Boolean signatureFilterOnlyDocSignatures) {
        this.signatureFilterOnlyDocSignatures = signatureFilterOnlyDocSignatures;
    }


    /**
     * Gets the signatureVisible value for this IvPDFSignatureParams.
     * 
     * @return signatureVisible
     */
    public java.lang.Boolean getSignatureVisible() {
        return signatureVisible;
    }


    /**
     * Sets the signatureVisible value for this IvPDFSignatureParams.
     * 
     * @param signatureVisible
     */
    public void setSignatureVisible(java.lang.Boolean signatureVisible) {
        this.signatureVisible = signatureVisible;
    }


    /**
     * Gets the widgetProps value for this IvPDFSignatureParams.
     * 
     * @return widgetProps
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureWidgetProps getWidgetProps() {
        return widgetProps;
    }


    /**
     * Sets the widgetProps value for this IvPDFSignatureParams.
     * 
     * @param widgetProps
     */
    public void setWidgetProps(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvPDFSignatureParamsPdfSignatureWidgetProps widgetProps) {
        this.widgetProps = widgetProps;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IvPDFSignatureParams)) return false;
        IvPDFSignatureParams other = (IvPDFSignatureParams) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pwd==null && other.getPwd()==null) || 
             (this.pwd!=null &&
              this.pwd.equals(other.getPwd()))) &&
            ((this.signatureBackGroundConfig==null && other.getSignatureBackGroundConfig()==null) || 
             (this.signatureBackGroundConfig!=null &&
              this.signatureBackGroundConfig.equals(other.getSignatureBackGroundConfig()))) &&
            ((this.signatureFieldName==null && other.getSignatureFieldName()==null) || 
             (this.signatureFieldName!=null &&
              this.signatureFieldName.equals(other.getSignatureFieldName()))) &&
            ((this.signatureFilterOnlyDocSignatures==null && other.getSignatureFilterOnlyDocSignatures()==null) || 
             (this.signatureFilterOnlyDocSignatures!=null &&
              this.signatureFilterOnlyDocSignatures.equals(other.getSignatureFilterOnlyDocSignatures()))) &&
            ((this.signatureVisible==null && other.getSignatureVisible()==null) || 
             (this.signatureVisible!=null &&
              this.signatureVisible.equals(other.getSignatureVisible()))) &&
            ((this.widgetProps==null && other.getWidgetProps()==null) || 
             (this.widgetProps!=null &&
              this.widgetProps.equals(other.getWidgetProps())));
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
        if (getPwd() != null) {
            _hashCode += getPwd().hashCode();
        }
        if (getSignatureBackGroundConfig() != null) {
            _hashCode += getSignatureBackGroundConfig().hashCode();
        }
        if (getSignatureFieldName() != null) {
            _hashCode += getSignatureFieldName().hashCode();
        }
        if (getSignatureFilterOnlyDocSignatures() != null) {
            _hashCode += getSignatureFilterOnlyDocSignatures().hashCode();
        }
        if (getSignatureVisible() != null) {
            _hashCode += getSignatureVisible().hashCode();
        }
        if (getWidgetProps() != null) {
            _hashCode += getWidgetProps().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IvPDFSignatureParams.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvPDFSignatureParams"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pwd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "pwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureBackGroundConfig");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureBackGroundConfig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvPDFSignatureParams.PdfSignatureBackground"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureFieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureFieldName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureFilterOnlyDocSignatures");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureFilterOnlyDocSignatures"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureVisible");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureVisible"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("widgetProps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "widgetProps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvPDFSignatureParams.PdfSignatureWidgetProps"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
