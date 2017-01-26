/**
 * IvSignatureEnumsVerifyState.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvSignatureEnumsVerifyState implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected IvSignatureEnumsVerifyState(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Valid = "Valid";
    public static final java.lang.String _SignatureCorrupted = "SignatureCorrupted";
    public static final java.lang.String _SignerNotFound = "SignerNotFound";
    public static final java.lang.String _IncompleteChain = "IncompleteChain";
    public static final java.lang.String _BadCountersignature = "BadCountersignature";
    public static final java.lang.String _BadTimestamp = "BadTimestamp";
    public static final java.lang.String _CertificateExpired = "CertificateExpired";
    public static final java.lang.String _CertificateRevoked = "CertificateRevoked";
    public static final java.lang.String _CertificateCorrupted = "CertificateCorrupted";
    public static final java.lang.String _UntrustedCA = "UntrustedCA";
    public static final java.lang.String _RevInfoNotFound = "RevInfoNotFound";
    public static final java.lang.String _TimestampInfoNotFound = "TimestampInfoNotFound";
    public static final java.lang.String _Failure = "Failure";
    public static final java.lang.String _CertificateMalformed = "CertificateMalformed";
    public static final java.lang.String _Unknown = "Unknown";
    public static final java.lang.String _InvalidPolicy = "InvalidPolicy";
    public static final java.lang.String _NotValidForUsage = "NotValidForUsage";
    public static final IvSignatureEnumsVerifyState Valid = new IvSignatureEnumsVerifyState(_Valid);
    public static final IvSignatureEnumsVerifyState SignatureCorrupted = new IvSignatureEnumsVerifyState(_SignatureCorrupted);
    public static final IvSignatureEnumsVerifyState SignerNotFound = new IvSignatureEnumsVerifyState(_SignerNotFound);
    public static final IvSignatureEnumsVerifyState IncompleteChain = new IvSignatureEnumsVerifyState(_IncompleteChain);
    public static final IvSignatureEnumsVerifyState BadCountersignature = new IvSignatureEnumsVerifyState(_BadCountersignature);
    public static final IvSignatureEnumsVerifyState BadTimestamp = new IvSignatureEnumsVerifyState(_BadTimestamp);
    public static final IvSignatureEnumsVerifyState CertificateExpired = new IvSignatureEnumsVerifyState(_CertificateExpired);
    public static final IvSignatureEnumsVerifyState CertificateRevoked = new IvSignatureEnumsVerifyState(_CertificateRevoked);
    public static final IvSignatureEnumsVerifyState CertificateCorrupted = new IvSignatureEnumsVerifyState(_CertificateCorrupted);
    public static final IvSignatureEnumsVerifyState UntrustedCA = new IvSignatureEnumsVerifyState(_UntrustedCA);
    public static final IvSignatureEnumsVerifyState RevInfoNotFound = new IvSignatureEnumsVerifyState(_RevInfoNotFound);
    public static final IvSignatureEnumsVerifyState TimestampInfoNotFound = new IvSignatureEnumsVerifyState(_TimestampInfoNotFound);
    public static final IvSignatureEnumsVerifyState Failure = new IvSignatureEnumsVerifyState(_Failure);
    public static final IvSignatureEnumsVerifyState CertificateMalformed = new IvSignatureEnumsVerifyState(_CertificateMalformed);
    public static final IvSignatureEnumsVerifyState Unknown = new IvSignatureEnumsVerifyState(_Unknown);
    public static final IvSignatureEnumsVerifyState InvalidPolicy = new IvSignatureEnumsVerifyState(_InvalidPolicy);
    public static final IvSignatureEnumsVerifyState NotValidForUsage = new IvSignatureEnumsVerifyState(_NotValidForUsage);
    public java.lang.String getValue() { return _value_;}
    public static IvSignatureEnumsVerifyState fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        IvSignatureEnumsVerifyState enumeration = (IvSignatureEnumsVerifyState)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static IvSignatureEnumsVerifyState fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IvSignatureEnumsVerifyState.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.VerifyState"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
