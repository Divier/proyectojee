/**
 * IvSignatureEnumsIvSignatureProfile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans;

public class IvSignatureEnumsIvSignatureProfile implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected IvSignatureEnumsIvSignatureProfile(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Default_ = "Default_";
    public static final java.lang.String _CMS = "CMS";
    public static final java.lang.String _CAdESBES = "CAdESBES";
    public static final java.lang.String _CAdEST = "CAdEST";
    public static final java.lang.String _CAdESC = "CAdESC";
    public static final java.lang.String _CAdESX = "CAdESX";
    public static final java.lang.String _CAdESXL = "CAdESXL";
    public static final java.lang.String _CAdESA = "CAdESA";
    public static final java.lang.String _XMLDigSig = "XMLDigSig";
    public static final java.lang.String _XAdESBES = "XAdESBES";
    public static final java.lang.String _XAdEST = "XAdEST";
    public static final java.lang.String _XAdESC = "XAdESC";
    public static final java.lang.String _XAdESX = "XAdESX";
    public static final java.lang.String _XAdESXL = "XAdESXL";
    public static final java.lang.String _XAdESA = "XAdESA";
    public static final java.lang.String _PDF = "PDF";
    public static final java.lang.String _PAdESBasic = "PAdESBasic";
    public static final java.lang.String _PAdESBES = "PAdESBES";
    public static final java.lang.String _PAdESLTV = "PAdESLTV";
    public static final java.lang.String _PAdESXML = "PAdESXML";
    public static final java.lang.String _Office = "Office";
    public static final java.lang.String _RSA = "RSA";
    public static final java.lang.String _BasicHash = "BasicHash";
    public static final IvSignatureEnumsIvSignatureProfile Default_ = new IvSignatureEnumsIvSignatureProfile(_Default_);
    public static final IvSignatureEnumsIvSignatureProfile CMS = new IvSignatureEnumsIvSignatureProfile(_CMS);
    public static final IvSignatureEnumsIvSignatureProfile CAdESBES = new IvSignatureEnumsIvSignatureProfile(_CAdESBES);
    public static final IvSignatureEnumsIvSignatureProfile CAdEST = new IvSignatureEnumsIvSignatureProfile(_CAdEST);
    public static final IvSignatureEnumsIvSignatureProfile CAdESC = new IvSignatureEnumsIvSignatureProfile(_CAdESC);
    public static final IvSignatureEnumsIvSignatureProfile CAdESX = new IvSignatureEnumsIvSignatureProfile(_CAdESX);
    public static final IvSignatureEnumsIvSignatureProfile CAdESXL = new IvSignatureEnumsIvSignatureProfile(_CAdESXL);
    public static final IvSignatureEnumsIvSignatureProfile CAdESA = new IvSignatureEnumsIvSignatureProfile(_CAdESA);
    public static final IvSignatureEnumsIvSignatureProfile XMLDigSig = new IvSignatureEnumsIvSignatureProfile(_XMLDigSig);
    public static final IvSignatureEnumsIvSignatureProfile XAdESBES = new IvSignatureEnumsIvSignatureProfile(_XAdESBES);
    public static final IvSignatureEnumsIvSignatureProfile XAdEST = new IvSignatureEnumsIvSignatureProfile(_XAdEST);
    public static final IvSignatureEnumsIvSignatureProfile XAdESC = new IvSignatureEnumsIvSignatureProfile(_XAdESC);
    public static final IvSignatureEnumsIvSignatureProfile XAdESX = new IvSignatureEnumsIvSignatureProfile(_XAdESX);
    public static final IvSignatureEnumsIvSignatureProfile XAdESXL = new IvSignatureEnumsIvSignatureProfile(_XAdESXL);
    public static final IvSignatureEnumsIvSignatureProfile XAdESA = new IvSignatureEnumsIvSignatureProfile(_XAdESA);
    public static final IvSignatureEnumsIvSignatureProfile PDF = new IvSignatureEnumsIvSignatureProfile(_PDF);
    public static final IvSignatureEnumsIvSignatureProfile PAdESBasic = new IvSignatureEnumsIvSignatureProfile(_PAdESBasic);
    public static final IvSignatureEnumsIvSignatureProfile PAdESBES = new IvSignatureEnumsIvSignatureProfile(_PAdESBES);
    public static final IvSignatureEnumsIvSignatureProfile PAdESLTV = new IvSignatureEnumsIvSignatureProfile(_PAdESLTV);
    public static final IvSignatureEnumsIvSignatureProfile PAdESXML = new IvSignatureEnumsIvSignatureProfile(_PAdESXML);
    public static final IvSignatureEnumsIvSignatureProfile Office = new IvSignatureEnumsIvSignatureProfile(_Office);
    public static final IvSignatureEnumsIvSignatureProfile RSA = new IvSignatureEnumsIvSignatureProfile(_RSA);
    public static final IvSignatureEnumsIvSignatureProfile BasicHash = new IvSignatureEnumsIvSignatureProfile(_BasicHash);
    public java.lang.String getValue() { return _value_;}
    public static IvSignatureEnumsIvSignatureProfile fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        IvSignatureEnumsIvSignatureProfile enumeration = (IvSignatureEnumsIvSignatureProfile)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static IvSignatureEnumsIvSignatureProfile fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(IvSignatureEnumsIvSignatureProfile.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/IvSignatureBeans", "IvSignatureEnums.IvSignatureProfile"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
