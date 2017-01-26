/**
 * CircuitDocument.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class CircuitDocument  implements java.io.Serializable {
    private java.lang.String fileNameWithExtension;

    private co.com.datatools.c2.clientes.ws.enotifica.DocumentType type;

    private byte[] content;

    private java.lang.String title;

    private java.lang.String notes;

    private java.lang.String externalId;

    private java.lang.String contentString;

    private java.lang.String fileNameTempFac;

    private int facturae;

    public CircuitDocument() {
    }

    public CircuitDocument(
           java.lang.String fileNameWithExtension,
           co.com.datatools.c2.clientes.ws.enotifica.DocumentType type,
           byte[] content,
           java.lang.String title,
           java.lang.String notes,
           java.lang.String externalId,
           java.lang.String contentString,
           java.lang.String fileNameTempFac,
           int facturae) {
           this.fileNameWithExtension = fileNameWithExtension;
           this.type = type;
           this.content = content;
           this.title = title;
           this.notes = notes;
           this.externalId = externalId;
           this.contentString = contentString;
           this.fileNameTempFac = fileNameTempFac;
           this.facturae = facturae;
    }


    /**
     * Gets the fileNameWithExtension value for this CircuitDocument.
     * 
     * @return fileNameWithExtension
     */
    public java.lang.String getFileNameWithExtension() {
        return fileNameWithExtension;
    }


    /**
     * Sets the fileNameWithExtension value for this CircuitDocument.
     * 
     * @param fileNameWithExtension
     */
    public void setFileNameWithExtension(java.lang.String fileNameWithExtension) {
        this.fileNameWithExtension = fileNameWithExtension;
    }


    /**
     * Gets the type value for this CircuitDocument.
     * 
     * @return type
     */
    public co.com.datatools.c2.clientes.ws.enotifica.DocumentType getType() {
        return type;
    }


    /**
     * Sets the type value for this CircuitDocument.
     * 
     * @param type
     */
    public void setType(co.com.datatools.c2.clientes.ws.enotifica.DocumentType type) {
        this.type = type;
    }


    /**
     * Gets the content value for this CircuitDocument.
     * 
     * @return content
     */
    public byte[] getContent() {
        return content;
    }


    /**
     * Sets the content value for this CircuitDocument.
     * 
     * @param content
     */
    public void setContent(byte[] content) {
        this.content = content;
    }


    /**
     * Gets the title value for this CircuitDocument.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this CircuitDocument.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the notes value for this CircuitDocument.
     * 
     * @return notes
     */
    public java.lang.String getNotes() {
        return notes;
    }


    /**
     * Sets the notes value for this CircuitDocument.
     * 
     * @param notes
     */
    public void setNotes(java.lang.String notes) {
        this.notes = notes;
    }


    /**
     * Gets the externalId value for this CircuitDocument.
     * 
     * @return externalId
     */
    public java.lang.String getExternalId() {
        return externalId;
    }


    /**
     * Sets the externalId value for this CircuitDocument.
     * 
     * @param externalId
     */
    public void setExternalId(java.lang.String externalId) {
        this.externalId = externalId;
    }


    /**
     * Gets the contentString value for this CircuitDocument.
     * 
     * @return contentString
     */
    public java.lang.String getContentString() {
        return contentString;
    }


    /**
     * Sets the contentString value for this CircuitDocument.
     * 
     * @param contentString
     */
    public void setContentString(java.lang.String contentString) {
        this.contentString = contentString;
    }


    /**
     * Gets the fileNameTempFac value for this CircuitDocument.
     * 
     * @return fileNameTempFac
     */
    public java.lang.String getFileNameTempFac() {
        return fileNameTempFac;
    }


    /**
     * Sets the fileNameTempFac value for this CircuitDocument.
     * 
     * @param fileNameTempFac
     */
    public void setFileNameTempFac(java.lang.String fileNameTempFac) {
        this.fileNameTempFac = fileNameTempFac;
    }


    /**
     * Gets the facturae value for this CircuitDocument.
     * 
     * @return facturae
     */
    public int getFacturae() {
        return facturae;
    }


    /**
     * Sets the facturae value for this CircuitDocument.
     * 
     * @param facturae
     */
    public void setFacturae(int facturae) {
        this.facturae = facturae;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CircuitDocument)) return false;
        CircuitDocument other = (CircuitDocument) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fileNameWithExtension==null && other.getFileNameWithExtension()==null) || 
             (this.fileNameWithExtension!=null &&
              this.fileNameWithExtension.equals(other.getFileNameWithExtension()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              java.util.Arrays.equals(this.content, other.getContent()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.notes==null && other.getNotes()==null) || 
             (this.notes!=null &&
              this.notes.equals(other.getNotes()))) &&
            ((this.externalId==null && other.getExternalId()==null) || 
             (this.externalId!=null &&
              this.externalId.equals(other.getExternalId()))) &&
            ((this.contentString==null && other.getContentString()==null) || 
             (this.contentString!=null &&
              this.contentString.equals(other.getContentString()))) &&
            ((this.fileNameTempFac==null && other.getFileNameTempFac()==null) || 
             (this.fileNameTempFac!=null &&
              this.fileNameTempFac.equals(other.getFileNameTempFac()))) &&
            this.facturae == other.getFacturae();
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
        if (getFileNameWithExtension() != null) {
            _hashCode += getFileNameWithExtension().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getContent() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContent());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContent(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getNotes() != null) {
            _hashCode += getNotes().hashCode();
        }
        if (getExternalId() != null) {
            _hashCode += getExternalId().hashCode();
        }
        if (getContentString() != null) {
            _hashCode += getContentString().hashCode();
        }
        if (getFileNameTempFac() != null) {
            _hashCode += getFileNameTempFac().hashCode();
        }
        _hashCode += getFacturae();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CircuitDocument.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "CircuitDocument"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileNameWithExtension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "fileNameWithExtension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "DocumentType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "notes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "ExternalId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentString");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "contentString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileNameTempFac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "fileNameTempFac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("facturae");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "facturae"));
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
