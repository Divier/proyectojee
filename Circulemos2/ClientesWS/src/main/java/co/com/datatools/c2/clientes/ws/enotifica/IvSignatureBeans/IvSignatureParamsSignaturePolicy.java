/**
 * IvSignatureParamsSignaturePolicy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvSignatureParamsSignaturePolicy  implements java.io.Serializable {
    private java.lang.String policyDescription;

    private java.lang.String policyDigest;

    private java.lang.String policyIdentifier;

    private java.lang.Boolean policyIdentifierAddQualifier;

    private java.lang.String policyQualifierURI;

    public IvSignatureParamsSignaturePolicy() {
    }

    public IvSignatureParamsSignaturePolicy(
           java.lang.String policyDescription,
           java.lang.String policyDigest,
           java.lang.String policyIdentifier,
           java.lang.Boolean policyIdentifierAddQualifier,
           java.lang.String policyQualifierURI) {
           this.policyDescription = policyDescription;
           this.policyDigest = policyDigest;
           this.policyIdentifier = policyIdentifier;
           this.policyIdentifierAddQualifier = policyIdentifierAddQualifier;
           this.policyQualifierURI = policyQualifierURI;
    }


    /**
     * Gets the policyDescription value for this IvSignatureParamsSignaturePolicy.
     * 
     * @return policyDescription
     */
    public java.lang.String getPolicyDescription() {
        return policyDescription;
    }


    /**
     * Sets the policyDescription value for this IvSignatureParamsSignaturePolicy.
     * 
     * @param policyDescription
     */
    public void setPolicyDescription(java.lang.String policyDescription) {
        this.policyDescription = policyDescription;
    }


    /**
     * Gets the policyDigest value for this IvSignatureParamsSignaturePolicy.
     * 
     * @return policyDigest
     */
    public java.lang.String getPolicyDigest() {
        return policyDigest;
    }


    /**
     * Sets the policyDigest value for this IvSignatureParamsSignaturePolicy.
     * 
     * @param policyDigest
     */
    public void setPolicyDigest(java.lang.String policyDigest) {
        this.policyDigest = policyDigest;
    }


    /**
     * Gets the policyIdentifier value for this IvSignatureParamsSignaturePolicy.
     * 
     * @return policyIdentifier
     */
    public java.lang.String getPolicyIdentifier() {
        return policyIdentifier;
    }


    /**
     * Sets the policyIdentifier value for this IvSignatureParamsSignaturePolicy.
     * 
     * @param policyIdentifier
     */
    public void setPolicyIdentifier(java.lang.String policyIdentifier) {
        this.policyIdentifier = policyIdentifier;
    }


    /**
     * Gets the policyIdentifierAddQualifier value for this IvSignatureParamsSignaturePolicy.
     * 
     * @return policyIdentifierAddQualifier
     */
    public java.lang.Boolean getPolicyIdentifierAddQualifier() {
        return policyIdentifierAddQualifier;
    }


    /**
     * Sets the policyIdentifierAddQualifier value for this IvSignatureParamsSignaturePolicy.
     * 
     * @param policyIdentifierAddQualifier
     */
    public void setPolicyIdentifierAddQualifier(java.lang.Boolean policyIdentifierAddQualifier) {
        this.policyIdentifierAddQualifier = policyIdentifierAddQualifier;
    }


    /**
     * Gets the policyQualifierURI value for this IvSignatureParamsSignaturePolicy.
     * 
     * @return policyQualifierURI
     */
    public java.lang.String getPolicyQualifierURI() {
        return policyQualifierURI;
    }


    /**
     * Sets the policyQualifierURI value for this IvSignatureParamsSignaturePolicy.
     * 
     * @param policyQualifierURI
     */
    public void setPolicyQualifierURI(java.lang.String policyQualifierURI) {
        this.policyQualifierURI = policyQualifierURI;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IvSignatureParamsSignaturePolicy)) return false;
        IvSignatureParamsSignaturePolicy other = (IvSignatureParamsSignaturePolicy) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.policyDescription==null && other.getPolicyDescription()==null) || 
             (this.policyDescription!=null &&
              this.policyDescription.equals(other.getPolicyDescription()))) &&
            ((this.policyDigest==null && other.getPolicyDigest()==null) || 
             (this.policyDigest!=null &&
              this.policyDigest.equals(other.getPolicyDigest()))) &&
            ((this.policyIdentifier==null && other.getPolicyIdentifier()==null) || 
             (this.policyIdentifier!=null &&
              this.policyIdentifier.equals(other.getPolicyIdentifier()))) &&
            ((this.policyIdentifierAddQualifier==null && other.getPolicyIdentifierAddQualifier()==null) || 
             (this.policyIdentifierAddQualifier!=null &&
              this.policyIdentifierAddQualifier.equals(other.getPolicyIdentifierAddQualifier()))) &&
            ((this.policyQualifierURI==null && other.getPolicyQualifierURI()==null) || 
             (this.policyQualifierURI!=null &&
              this.policyQualifierURI.equals(other.getPolicyQualifierURI())));
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
        if (getPolicyDescription() != null) {
            _hashCode += getPolicyDescription().hashCode();
        }
        if (getPolicyDigest() != null) {
            _hashCode += getPolicyDigest().hashCode();
        }
        if (getPolicyIdentifier() != null) {
            _hashCode += getPolicyIdentifier().hashCode();
        }
        if (getPolicyIdentifierAddQualifier() != null) {
            _hashCode += getPolicyIdentifierAddQualifier().hashCode();
        }
        if (getPolicyQualifierURI() != null) {
            _hashCode += getPolicyQualifierURI().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IvSignatureParamsSignaturePolicy.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureParams.SignaturePolicy"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "policyDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyDigest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "policyDigest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "policyIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyIdentifierAddQualifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "policyIdentifierAddQualifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("policyQualifierURI");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "policyQualifierURI"));
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
