/**
 * IvPDFSignatureParamsPdfSignatureBackground.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvPDFSignatureParamsPdfSignatureBackground  implements java.io.Serializable {
    private java.lang.Boolean includeSignatureBack;

    private byte[] signatureBack;

    private java.lang.Boolean signatureBackAutoStretch;

    private java.lang.Integer signatureBackHeight;

    private java.lang.Integer signatureBackWidth;

    public IvPDFSignatureParamsPdfSignatureBackground() {
    }

    public IvPDFSignatureParamsPdfSignatureBackground(
           java.lang.Boolean includeSignatureBack,
           byte[] signatureBack,
           java.lang.Boolean signatureBackAutoStretch,
           java.lang.Integer signatureBackHeight,
           java.lang.Integer signatureBackWidth) {
           this.includeSignatureBack = includeSignatureBack;
           this.signatureBack = signatureBack;
           this.signatureBackAutoStretch = signatureBackAutoStretch;
           this.signatureBackHeight = signatureBackHeight;
           this.signatureBackWidth = signatureBackWidth;
    }


    /**
     * Gets the includeSignatureBack value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @return includeSignatureBack
     */
    public java.lang.Boolean getIncludeSignatureBack() {
        return includeSignatureBack;
    }


    /**
     * Sets the includeSignatureBack value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @param includeSignatureBack
     */
    public void setIncludeSignatureBack(java.lang.Boolean includeSignatureBack) {
        this.includeSignatureBack = includeSignatureBack;
    }


    /**
     * Gets the signatureBack value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @return signatureBack
     */
    public byte[] getSignatureBack() {
        return signatureBack;
    }


    /**
     * Sets the signatureBack value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @param signatureBack
     */
    public void setSignatureBack(byte[] signatureBack) {
        this.signatureBack = signatureBack;
    }


    /**
     * Gets the signatureBackAutoStretch value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @return signatureBackAutoStretch
     */
    public java.lang.Boolean getSignatureBackAutoStretch() {
        return signatureBackAutoStretch;
    }


    /**
     * Sets the signatureBackAutoStretch value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @param signatureBackAutoStretch
     */
    public void setSignatureBackAutoStretch(java.lang.Boolean signatureBackAutoStretch) {
        this.signatureBackAutoStretch = signatureBackAutoStretch;
    }


    /**
     * Gets the signatureBackHeight value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @return signatureBackHeight
     */
    public java.lang.Integer getSignatureBackHeight() {
        return signatureBackHeight;
    }


    /**
     * Sets the signatureBackHeight value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @param signatureBackHeight
     */
    public void setSignatureBackHeight(java.lang.Integer signatureBackHeight) {
        this.signatureBackHeight = signatureBackHeight;
    }


    /**
     * Gets the signatureBackWidth value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @return signatureBackWidth
     */
    public java.lang.Integer getSignatureBackWidth() {
        return signatureBackWidth;
    }


    /**
     * Sets the signatureBackWidth value for this IvPDFSignatureParamsPdfSignatureBackground.
     * 
     * @param signatureBackWidth
     */
    public void setSignatureBackWidth(java.lang.Integer signatureBackWidth) {
        this.signatureBackWidth = signatureBackWidth;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IvPDFSignatureParamsPdfSignatureBackground)) return false;
        IvPDFSignatureParamsPdfSignatureBackground other = (IvPDFSignatureParamsPdfSignatureBackground) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.includeSignatureBack==null && other.getIncludeSignatureBack()==null) || 
             (this.includeSignatureBack!=null &&
              this.includeSignatureBack.equals(other.getIncludeSignatureBack()))) &&
            ((this.signatureBack==null && other.getSignatureBack()==null) || 
             (this.signatureBack!=null &&
              java.util.Arrays.equals(this.signatureBack, other.getSignatureBack()))) &&
            ((this.signatureBackAutoStretch==null && other.getSignatureBackAutoStretch()==null) || 
             (this.signatureBackAutoStretch!=null &&
              this.signatureBackAutoStretch.equals(other.getSignatureBackAutoStretch()))) &&
            ((this.signatureBackHeight==null && other.getSignatureBackHeight()==null) || 
             (this.signatureBackHeight!=null &&
              this.signatureBackHeight.equals(other.getSignatureBackHeight()))) &&
            ((this.signatureBackWidth==null && other.getSignatureBackWidth()==null) || 
             (this.signatureBackWidth!=null &&
              this.signatureBackWidth.equals(other.getSignatureBackWidth())));
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
        if (getIncludeSignatureBack() != null) {
            _hashCode += getIncludeSignatureBack().hashCode();
        }
        if (getSignatureBack() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSignatureBack());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSignatureBack(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSignatureBackAutoStretch() != null) {
            _hashCode += getSignatureBackAutoStretch().hashCode();
        }
        if (getSignatureBackHeight() != null) {
            _hashCode += getSignatureBackHeight().hashCode();
        }
        if (getSignatureBackWidth() != null) {
            _hashCode += getSignatureBackWidth().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IvPDFSignatureParamsPdfSignatureBackground.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvPDFSignatureParams.PdfSignatureBackground"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeSignatureBack");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "includeSignatureBack"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureBack");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureBack"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureBackAutoStretch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureBackAutoStretch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureBackHeight");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureBackHeight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureBackWidth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureBackWidth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
