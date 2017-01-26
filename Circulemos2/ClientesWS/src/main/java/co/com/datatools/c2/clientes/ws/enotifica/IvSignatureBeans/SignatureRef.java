/**
 * SignatureRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class SignatureRef  implements java.io.Serializable {
    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef[] counterSignatures;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm;

    private java.lang.String[] sFlags;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile sProfile;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType sType;

    private byte[] signatureCertificate;

    private java.lang.String signatureID;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsVerifyState signatureStatus;

    private java.util.Calendar signingTime;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef[] timestamps;

    private co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef[] validationTimestamps;

    public SignatureRef() {
    }

    public SignatureRef(
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef[] counterSignatures,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm,
           java.lang.String[] sFlags,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile sProfile,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType sType,
           byte[] signatureCertificate,
           java.lang.String signatureID,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsVerifyState signatureStatus,
           java.util.Calendar signingTime,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef[] timestamps,
           co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef[] validationTimestamps) {
           this.counterSignatures = counterSignatures;
           this.hashAlgorithm = hashAlgorithm;
           this.sFlags = sFlags;
           this.sProfile = sProfile;
           this.sType = sType;
           this.signatureCertificate = signatureCertificate;
           this.signatureID = signatureID;
           this.signatureStatus = signatureStatus;
           this.signingTime = signingTime;
           this.timestamps = timestamps;
           this.validationTimestamps = validationTimestamps;
    }


    /**
     * Gets the counterSignatures value for this SignatureRef.
     * 
     * @return counterSignatures
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef[] getCounterSignatures() {
        return counterSignatures;
    }


    /**
     * Sets the counterSignatures value for this SignatureRef.
     * 
     * @param counterSignatures
     */
    public void setCounterSignatures(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureRef[] counterSignatures) {
        this.counterSignatures = counterSignatures;
    }


    /**
     * Gets the hashAlgorithm value for this SignatureRef.
     * 
     * @return hashAlgorithm
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm getHashAlgorithm() {
        return hashAlgorithm;
    }


    /**
     * Sets the hashAlgorithm value for this SignatureRef.
     * 
     * @param hashAlgorithm
     */
    public void setHashAlgorithm(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }


    /**
     * Gets the sFlags value for this SignatureRef.
     * 
     * @return sFlags
     */
    public java.lang.String[] getSFlags() {
        return sFlags;
    }


    /**
     * Sets the sFlags value for this SignatureRef.
     * 
     * @param sFlags
     */
    public void setSFlags(java.lang.String[] sFlags) {
        this.sFlags = sFlags;
    }


    /**
     * Gets the sProfile value for this SignatureRef.
     * 
     * @return sProfile
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile getSProfile() {
        return sProfile;
    }


    /**
     * Sets the sProfile value for this SignatureRef.
     * 
     * @param sProfile
     */
    public void setSProfile(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile sProfile) {
        this.sProfile = sProfile;
    }


    /**
     * Gets the sType value for this SignatureRef.
     * 
     * @return sType
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType getSType() {
        return sType;
    }


    /**
     * Sets the sType value for this SignatureRef.
     * 
     * @param sType
     */
    public void setSType(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType sType) {
        this.sType = sType;
    }


    /**
     * Gets the signatureCertificate value for this SignatureRef.
     * 
     * @return signatureCertificate
     */
    public byte[] getSignatureCertificate() {
        return signatureCertificate;
    }


    /**
     * Sets the signatureCertificate value for this SignatureRef.
     * 
     * @param signatureCertificate
     */
    public void setSignatureCertificate(byte[] signatureCertificate) {
        this.signatureCertificate = signatureCertificate;
    }


    /**
     * Gets the signatureID value for this SignatureRef.
     * 
     * @return signatureID
     */
    public java.lang.String getSignatureID() {
        return signatureID;
    }


    /**
     * Sets the signatureID value for this SignatureRef.
     * 
     * @param signatureID
     */
    public void setSignatureID(java.lang.String signatureID) {
        this.signatureID = signatureID;
    }


    /**
     * Gets the signatureStatus value for this SignatureRef.
     * 
     * @return signatureStatus
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsVerifyState getSignatureStatus() {
        return signatureStatus;
    }


    /**
     * Sets the signatureStatus value for this SignatureRef.
     * 
     * @param signatureStatus
     */
    public void setSignatureStatus(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsVerifyState signatureStatus) {
        this.signatureStatus = signatureStatus;
    }


    /**
     * Gets the signingTime value for this SignatureRef.
     * 
     * @return signingTime
     */
    public java.util.Calendar getSigningTime() {
        return signingTime;
    }


    /**
     * Sets the signingTime value for this SignatureRef.
     * 
     * @param signingTime
     */
    public void setSigningTime(java.util.Calendar signingTime) {
        this.signingTime = signingTime;
    }


    /**
     * Gets the timestamps value for this SignatureRef.
     * 
     * @return timestamps
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef[] getTimestamps() {
        return timestamps;
    }


    /**
     * Sets the timestamps value for this SignatureRef.
     * 
     * @param timestamps
     */
    public void setTimestamps(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef[] timestamps) {
        this.timestamps = timestamps;
    }


    /**
     * Gets the validationTimestamps value for this SignatureRef.
     * 
     * @return validationTimestamps
     */
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef[] getValidationTimestamps() {
        return validationTimestamps;
    }


    /**
     * Sets the validationTimestamps value for this SignatureRef.
     * 
     * @param validationTimestamps
     */
    public void setValidationTimestamps(co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.TimestampRef[] validationTimestamps) {
        this.validationTimestamps = validationTimestamps;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SignatureRef)) return false;
        SignatureRef other = (SignatureRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.counterSignatures==null && other.getCounterSignatures()==null) || 
             (this.counterSignatures!=null &&
              java.util.Arrays.equals(this.counterSignatures, other.getCounterSignatures()))) &&
            ((this.hashAlgorithm==null && other.getHashAlgorithm()==null) || 
             (this.hashAlgorithm!=null &&
              this.hashAlgorithm.equals(other.getHashAlgorithm()))) &&
            ((this.sFlags==null && other.getSFlags()==null) || 
             (this.sFlags!=null &&
              java.util.Arrays.equals(this.sFlags, other.getSFlags()))) &&
            ((this.sProfile==null && other.getSProfile()==null) || 
             (this.sProfile!=null &&
              this.sProfile.equals(other.getSProfile()))) &&
            ((this.sType==null && other.getSType()==null) || 
             (this.sType!=null &&
              this.sType.equals(other.getSType()))) &&
            ((this.signatureCertificate==null && other.getSignatureCertificate()==null) || 
             (this.signatureCertificate!=null &&
              java.util.Arrays.equals(this.signatureCertificate, other.getSignatureCertificate()))) &&
            ((this.signatureID==null && other.getSignatureID()==null) || 
             (this.signatureID!=null &&
              this.signatureID.equals(other.getSignatureID()))) &&
            ((this.signatureStatus==null && other.getSignatureStatus()==null) || 
             (this.signatureStatus!=null &&
              this.signatureStatus.equals(other.getSignatureStatus()))) &&
            ((this.signingTime==null && other.getSigningTime()==null) || 
             (this.signingTime!=null &&
              this.signingTime.equals(other.getSigningTime()))) &&
            ((this.timestamps==null && other.getTimestamps()==null) || 
             (this.timestamps!=null &&
              java.util.Arrays.equals(this.timestamps, other.getTimestamps()))) &&
            ((this.validationTimestamps==null && other.getValidationTimestamps()==null) || 
             (this.validationTimestamps!=null &&
              java.util.Arrays.equals(this.validationTimestamps, other.getValidationTimestamps())));
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
        if (getCounterSignatures() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCounterSignatures());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCounterSignatures(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHashAlgorithm() != null) {
            _hashCode += getHashAlgorithm().hashCode();
        }
        if (getSFlags() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSFlags());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSFlags(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSProfile() != null) {
            _hashCode += getSProfile().hashCode();
        }
        if (getSType() != null) {
            _hashCode += getSType().hashCode();
        }
        if (getSignatureCertificate() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSignatureCertificate());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSignatureCertificate(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSignatureID() != null) {
            _hashCode += getSignatureID().hashCode();
        }
        if (getSignatureStatus() != null) {
            _hashCode += getSignatureStatus().hashCode();
        }
        if (getSigningTime() != null) {
            _hashCode += getSigningTime().hashCode();
        }
        if (getTimestamps() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimestamps());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTimestamps(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getValidationTimestamps() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValidationTimestamps());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValidationTimestamps(), i);
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
        new org.apache.axis.description.TypeDesc(SignatureRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("counterSignatures");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "counterSignatures"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "SignatureRef"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hashAlgorithm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "hashAlgorithm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvHashAlgorithm"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SFlags");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "sFlags"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureFlags"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SProfile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "sProfile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureProfile"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "sType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureCertificate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureCertificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signatureStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signatureStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.VerifyState"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signingTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "signingTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestamps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "timestamps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "TimestampRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "TimestampRef"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validationTimestamps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "validationTimestamps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "TimestampRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "TimestampRef"));
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
