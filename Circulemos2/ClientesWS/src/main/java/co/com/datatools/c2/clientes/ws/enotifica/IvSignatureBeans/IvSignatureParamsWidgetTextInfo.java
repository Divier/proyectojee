/**
 * IvSignatureParamsWidgetTextInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvSignatureParamsWidgetTextInfo  implements java.io.Serializable {
    private java.lang.String algorithmCaption;

    private java.lang.String algorithmInfo;

    private java.lang.String header;

    private java.lang.String signerCaption;

    private java.lang.String signerInfo;

    public IvSignatureParamsWidgetTextInfo() {
    }

    public IvSignatureParamsWidgetTextInfo(
           java.lang.String algorithmCaption,
           java.lang.String algorithmInfo,
           java.lang.String header,
           java.lang.String signerCaption,
           java.lang.String signerInfo) {
           this.algorithmCaption = algorithmCaption;
           this.algorithmInfo = algorithmInfo;
           this.header = header;
           this.signerCaption = signerCaption;
           this.signerInfo = signerInfo;
    }


    /**
     * Gets the algorithmCaption value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @return algorithmCaption
     */
    public java.lang.String getAlgorithmCaption() {
        return algorithmCaption;
    }


    /**
     * Sets the algorithmCaption value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @param algorithmCaption
     */
    public void setAlgorithmCaption(java.lang.String algorithmCaption) {
        this.algorithmCaption = algorithmCaption;
    }


    /**
     * Gets the algorithmInfo value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @return algorithmInfo
     */
    public java.lang.String getAlgorithmInfo() {
        return algorithmInfo;
    }


    /**
     * Sets the algorithmInfo value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @param algorithmInfo
     */
    public void setAlgorithmInfo(java.lang.String algorithmInfo) {
        this.algorithmInfo = algorithmInfo;
    }


    /**
     * Gets the header value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @return header
     */
    public java.lang.String getHeader() {
        return header;
    }


    /**
     * Sets the header value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @param header
     */
    public void setHeader(java.lang.String header) {
        this.header = header;
    }


    /**
     * Gets the signerCaption value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @return signerCaption
     */
    public java.lang.String getSignerCaption() {
        return signerCaption;
    }


    /**
     * Sets the signerCaption value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @param signerCaption
     */
    public void setSignerCaption(java.lang.String signerCaption) {
        this.signerCaption = signerCaption;
    }


    /**
     * Gets the signerInfo value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @return signerInfo
     */
    public java.lang.String getSignerInfo() {
        return signerInfo;
    }


    /**
     * Sets the signerInfo value for this IvSignatureParamsWidgetTextInfo.
     * 
     * @param signerInfo
     */
    public void setSignerInfo(java.lang.String signerInfo) {
        this.signerInfo = signerInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IvSignatureParamsWidgetTextInfo)) return false;
        IvSignatureParamsWidgetTextInfo other = (IvSignatureParamsWidgetTextInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.algorithmCaption==null && other.getAlgorithmCaption()==null) || 
             (this.algorithmCaption!=null &&
              this.algorithmCaption.equals(other.getAlgorithmCaption()))) &&
            ((this.algorithmInfo==null && other.getAlgorithmInfo()==null) || 
             (this.algorithmInfo!=null &&
              this.algorithmInfo.equals(other.getAlgorithmInfo()))) &&
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.signerCaption==null && other.getSignerCaption()==null) || 
             (this.signerCaption!=null &&
              this.signerCaption.equals(other.getSignerCaption()))) &&
            ((this.signerInfo==null && other.getSignerInfo()==null) || 
             (this.signerInfo!=null &&
              this.signerInfo.equals(other.getSignerInfo())));
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
        if (getAlgorithmCaption() != null) {
            _hashCode += getAlgorithmCaption().hashCode();
        }
        if (getAlgorithmInfo() != null) {
            _hashCode += getAlgorithmInfo().hashCode();
        }
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getSignerCaption() != null) {
            _hashCode += getSignerCaption().hashCode();
        }
        if (getSignerInfo() != null) {
            _hashCode += getSignerInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IvSignatureParamsWidgetTextInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.WidgetTextInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("algorithmCaption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "algorithmCaption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("algorithmInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "algorithmInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signerCaption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signerCaption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signerInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signerInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
