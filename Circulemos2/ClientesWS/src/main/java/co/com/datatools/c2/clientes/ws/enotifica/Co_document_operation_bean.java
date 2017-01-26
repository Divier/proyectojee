/**
 * Co_document_operation_bean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica;

public class Co_document_operation_bean  implements java.io.Serializable {
    private co.com.datatools.c2.clientes.ws.enotifica.Co_document_operation_ExtendedProps_bean extendedProps;

    private int iduser_encryptioncertificate;

    private java.lang.String symmetricKeyEncryptedWithkeyPairPublicKey;

    private java.lang.String observation;

    private boolean reprocess;

    private boolean delete;

    private java.util.Calendar fClose;

    private java.util.Calendar fdelete;

    private java.util.Calendar fAcess;

    private java.util.Calendar freg;

    private int idEmpresa;

    private int FExpired;

    private boolean extern;

    private java.lang.String operationId;

    private java.lang.String externalId;

    private java.lang.String documentId;

    private java.lang.String assignedUser;

    private java.lang.String opType;

    private int state;

    private java.lang.String parentOpId;

    private java.lang.String circuitId;

    public Co_document_operation_bean() {
    }

    public Co_document_operation_bean(
           co.com.datatools.c2.clientes.ws.enotifica.Co_document_operation_ExtendedProps_bean extendedProps,
           int iduser_encryptioncertificate,
           java.lang.String symmetricKeyEncryptedWithkeyPairPublicKey,
           java.lang.String observation,
           boolean reprocess,
           boolean delete,
           java.util.Calendar fClose,
           java.util.Calendar fdelete,
           java.util.Calendar fAcess,
           java.util.Calendar freg,
           int idEmpresa,
           int FExpired,
           boolean extern,
           java.lang.String operationId,
           java.lang.String externalId,
           java.lang.String documentId,
           java.lang.String assignedUser,
           java.lang.String opType,
           int state,
           java.lang.String parentOpId,
           java.lang.String circuitId) {
           this.extendedProps = extendedProps;
           this.iduser_encryptioncertificate = iduser_encryptioncertificate;
           this.symmetricKeyEncryptedWithkeyPairPublicKey = symmetricKeyEncryptedWithkeyPairPublicKey;
           this.observation = observation;
           this.reprocess = reprocess;
           this.delete = delete;
           this.fClose = fClose;
           this.fdelete = fdelete;
           this.fAcess = fAcess;
           this.freg = freg;
           this.idEmpresa = idEmpresa;
           this.FExpired = FExpired;
           this.extern = extern;
           this.operationId = operationId;
           this.externalId = externalId;
           this.documentId = documentId;
           this.assignedUser = assignedUser;
           this.opType = opType;
           this.state = state;
           this.parentOpId = parentOpId;
           this.circuitId = circuitId;
    }


    /**
     * Gets the extendedProps value for this Co_document_operation_bean.
     * 
     * @return extendedProps
     */
    public co.com.datatools.c2.clientes.ws.enotifica.Co_document_operation_ExtendedProps_bean getExtendedProps() {
        return extendedProps;
    }


    /**
     * Sets the extendedProps value for this Co_document_operation_bean.
     * 
     * @param extendedProps
     */
    public void setExtendedProps(co.com.datatools.c2.clientes.ws.enotifica.Co_document_operation_ExtendedProps_bean extendedProps) {
        this.extendedProps = extendedProps;
    }


    /**
     * Gets the iduser_encryptioncertificate value for this Co_document_operation_bean.
     * 
     * @return iduser_encryptioncertificate
     */
    public int getIduser_encryptioncertificate() {
        return iduser_encryptioncertificate;
    }


    /**
     * Sets the iduser_encryptioncertificate value for this Co_document_operation_bean.
     * 
     * @param iduser_encryptioncertificate
     */
    public void setIduser_encryptioncertificate(int iduser_encryptioncertificate) {
        this.iduser_encryptioncertificate = iduser_encryptioncertificate;
    }


    /**
     * Gets the symmetricKeyEncryptedWithkeyPairPublicKey value for this Co_document_operation_bean.
     * 
     * @return symmetricKeyEncryptedWithkeyPairPublicKey
     */
    public java.lang.String getSymmetricKeyEncryptedWithkeyPairPublicKey() {
        return symmetricKeyEncryptedWithkeyPairPublicKey;
    }


    /**
     * Sets the symmetricKeyEncryptedWithkeyPairPublicKey value for this Co_document_operation_bean.
     * 
     * @param symmetricKeyEncryptedWithkeyPairPublicKey
     */
    public void setSymmetricKeyEncryptedWithkeyPairPublicKey(java.lang.String symmetricKeyEncryptedWithkeyPairPublicKey) {
        this.symmetricKeyEncryptedWithkeyPairPublicKey = symmetricKeyEncryptedWithkeyPairPublicKey;
    }


    /**
     * Gets the observation value for this Co_document_operation_bean.
     * 
     * @return observation
     */
    public java.lang.String getObservation() {
        return observation;
    }


    /**
     * Sets the observation value for this Co_document_operation_bean.
     * 
     * @param observation
     */
    public void setObservation(java.lang.String observation) {
        this.observation = observation;
    }


    /**
     * Gets the reprocess value for this Co_document_operation_bean.
     * 
     * @return reprocess
     */
    public boolean isReprocess() {
        return reprocess;
    }


    /**
     * Sets the reprocess value for this Co_document_operation_bean.
     * 
     * @param reprocess
     */
    public void setReprocess(boolean reprocess) {
        this.reprocess = reprocess;
    }


    /**
     * Gets the delete value for this Co_document_operation_bean.
     * 
     * @return delete
     */
    public boolean isDelete() {
        return delete;
    }


    /**
     * Sets the delete value for this Co_document_operation_bean.
     * 
     * @param delete
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }


    /**
     * Gets the fClose value for this Co_document_operation_bean.
     * 
     * @return fClose
     */
    public java.util.Calendar getFClose() {
        return fClose;
    }


    /**
     * Sets the fClose value for this Co_document_operation_bean.
     * 
     * @param fClose
     */
    public void setFClose(java.util.Calendar fClose) {
        this.fClose = fClose;
    }


    /**
     * Gets the fdelete value for this Co_document_operation_bean.
     * 
     * @return fdelete
     */
    public java.util.Calendar getFdelete() {
        return fdelete;
    }


    /**
     * Sets the fdelete value for this Co_document_operation_bean.
     * 
     * @param fdelete
     */
    public void setFdelete(java.util.Calendar fdelete) {
        this.fdelete = fdelete;
    }


    /**
     * Gets the fAcess value for this Co_document_operation_bean.
     * 
     * @return fAcess
     */
    public java.util.Calendar getFAcess() {
        return fAcess;
    }


    /**
     * Sets the fAcess value for this Co_document_operation_bean.
     * 
     * @param fAcess
     */
    public void setFAcess(java.util.Calendar fAcess) {
        this.fAcess = fAcess;
    }


    /**
     * Gets the freg value for this Co_document_operation_bean.
     * 
     * @return freg
     */
    public java.util.Calendar getFreg() {
        return freg;
    }


    /**
     * Sets the freg value for this Co_document_operation_bean.
     * 
     * @param freg
     */
    public void setFreg(java.util.Calendar freg) {
        this.freg = freg;
    }


    /**
     * Gets the idEmpresa value for this Co_document_operation_bean.
     * 
     * @return idEmpresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }


    /**
     * Sets the idEmpresa value for this Co_document_operation_bean.
     * 
     * @param idEmpresa
     */
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }


    /**
     * Gets the FExpired value for this Co_document_operation_bean.
     * 
     * @return FExpired
     */
    public int getFExpired() {
        return FExpired;
    }


    /**
     * Sets the FExpired value for this Co_document_operation_bean.
     * 
     * @param FExpired
     */
    public void setFExpired(int FExpired) {
        this.FExpired = FExpired;
    }


    /**
     * Gets the extern value for this Co_document_operation_bean.
     * 
     * @return extern
     */
    public boolean isExtern() {
        return extern;
    }


    /**
     * Sets the extern value for this Co_document_operation_bean.
     * 
     * @param extern
     */
    public void setExtern(boolean extern) {
        this.extern = extern;
    }


    /**
     * Gets the operationId value for this Co_document_operation_bean.
     * 
     * @return operationId
     */
    public java.lang.String getOperationId() {
        return operationId;
    }


    /**
     * Sets the operationId value for this Co_document_operation_bean.
     * 
     * @param operationId
     */
    public void setOperationId(java.lang.String operationId) {
        this.operationId = operationId;
    }


    /**
     * Gets the externalId value for this Co_document_operation_bean.
     * 
     * @return externalId
     */
    public java.lang.String getExternalId() {
        return externalId;
    }


    /**
     * Sets the externalId value for this Co_document_operation_bean.
     * 
     * @param externalId
     */
    public void setExternalId(java.lang.String externalId) {
        this.externalId = externalId;
    }


    /**
     * Gets the documentId value for this Co_document_operation_bean.
     * 
     * @return documentId
     */
    public java.lang.String getDocumentId() {
        return documentId;
    }


    /**
     * Sets the documentId value for this Co_document_operation_bean.
     * 
     * @param documentId
     */
    public void setDocumentId(java.lang.String documentId) {
        this.documentId = documentId;
    }


    /**
     * Gets the assignedUser value for this Co_document_operation_bean.
     * 
     * @return assignedUser
     */
    public java.lang.String getAssignedUser() {
        return assignedUser;
    }


    /**
     * Sets the assignedUser value for this Co_document_operation_bean.
     * 
     * @param assignedUser
     */
    public void setAssignedUser(java.lang.String assignedUser) {
        this.assignedUser = assignedUser;
    }


    /**
     * Gets the opType value for this Co_document_operation_bean.
     * 
     * @return opType
     */
    public java.lang.String getOpType() {
        return opType;
    }


    /**
     * Sets the opType value for this Co_document_operation_bean.
     * 
     * @param opType
     */
    public void setOpType(java.lang.String opType) {
        this.opType = opType;
    }


    /**
     * Gets the state value for this Co_document_operation_bean.
     * 
     * @return state
     */
    public int getState() {
        return state;
    }


    /**
     * Sets the state value for this Co_document_operation_bean.
     * 
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }


    /**
     * Gets the parentOpId value for this Co_document_operation_bean.
     * 
     * @return parentOpId
     */
    public java.lang.String getParentOpId() {
        return parentOpId;
    }


    /**
     * Sets the parentOpId value for this Co_document_operation_bean.
     * 
     * @param parentOpId
     */
    public void setParentOpId(java.lang.String parentOpId) {
        this.parentOpId = parentOpId;
    }


    /**
     * Gets the circuitId value for this Co_document_operation_bean.
     * 
     * @return circuitId
     */
    public java.lang.String getCircuitId() {
        return circuitId;
    }


    /**
     * Sets the circuitId value for this Co_document_operation_bean.
     * 
     * @param circuitId
     */
    public void setCircuitId(java.lang.String circuitId) {
        this.circuitId = circuitId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Co_document_operation_bean)) return false;
        Co_document_operation_bean other = (Co_document_operation_bean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.extendedProps==null && other.getExtendedProps()==null) || 
             (this.extendedProps!=null &&
              this.extendedProps.equals(other.getExtendedProps()))) &&
            this.iduser_encryptioncertificate == other.getIduser_encryptioncertificate() &&
            ((this.symmetricKeyEncryptedWithkeyPairPublicKey==null && other.getSymmetricKeyEncryptedWithkeyPairPublicKey()==null) || 
             (this.symmetricKeyEncryptedWithkeyPairPublicKey!=null &&
              this.symmetricKeyEncryptedWithkeyPairPublicKey.equals(other.getSymmetricKeyEncryptedWithkeyPairPublicKey()))) &&
            ((this.observation==null && other.getObservation()==null) || 
             (this.observation!=null &&
              this.observation.equals(other.getObservation()))) &&
            this.reprocess == other.isReprocess() &&
            this.delete == other.isDelete() &&
            ((this.fClose==null && other.getFClose()==null) || 
             (this.fClose!=null &&
              this.fClose.equals(other.getFClose()))) &&
            ((this.fdelete==null && other.getFdelete()==null) || 
             (this.fdelete!=null &&
              this.fdelete.equals(other.getFdelete()))) &&
            ((this.fAcess==null && other.getFAcess()==null) || 
             (this.fAcess!=null &&
              this.fAcess.equals(other.getFAcess()))) &&
            ((this.freg==null && other.getFreg()==null) || 
             (this.freg!=null &&
              this.freg.equals(other.getFreg()))) &&
            this.idEmpresa == other.getIdEmpresa() &&
            this.FExpired == other.getFExpired() &&
            this.extern == other.isExtern() &&
            ((this.operationId==null && other.getOperationId()==null) || 
             (this.operationId!=null &&
              this.operationId.equals(other.getOperationId()))) &&
            ((this.externalId==null && other.getExternalId()==null) || 
             (this.externalId!=null &&
              this.externalId.equals(other.getExternalId()))) &&
            ((this.documentId==null && other.getDocumentId()==null) || 
             (this.documentId!=null &&
              this.documentId.equals(other.getDocumentId()))) &&
            ((this.assignedUser==null && other.getAssignedUser()==null) || 
             (this.assignedUser!=null &&
              this.assignedUser.equals(other.getAssignedUser()))) &&
            ((this.opType==null && other.getOpType()==null) || 
             (this.opType!=null &&
              this.opType.equals(other.getOpType()))) &&
            this.state == other.getState() &&
            ((this.parentOpId==null && other.getParentOpId()==null) || 
             (this.parentOpId!=null &&
              this.parentOpId.equals(other.getParentOpId()))) &&
            ((this.circuitId==null && other.getCircuitId()==null) || 
             (this.circuitId!=null &&
              this.circuitId.equals(other.getCircuitId())));
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
        if (getExtendedProps() != null) {
            _hashCode += getExtendedProps().hashCode();
        }
        _hashCode += getIduser_encryptioncertificate();
        if (getSymmetricKeyEncryptedWithkeyPairPublicKey() != null) {
            _hashCode += getSymmetricKeyEncryptedWithkeyPairPublicKey().hashCode();
        }
        if (getObservation() != null) {
            _hashCode += getObservation().hashCode();
        }
        _hashCode += (isReprocess() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isDelete() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFClose() != null) {
            _hashCode += getFClose().hashCode();
        }
        if (getFdelete() != null) {
            _hashCode += getFdelete().hashCode();
        }
        if (getFAcess() != null) {
            _hashCode += getFAcess().hashCode();
        }
        if (getFreg() != null) {
            _hashCode += getFreg().hashCode();
        }
        _hashCode += getIdEmpresa();
        _hashCode += getFExpired();
        _hashCode += (isExtern() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getOperationId() != null) {
            _hashCode += getOperationId().hashCode();
        }
        if (getExternalId() != null) {
            _hashCode += getExternalId().hashCode();
        }
        if (getDocumentId() != null) {
            _hashCode += getDocumentId().hashCode();
        }
        if (getAssignedUser() != null) {
            _hashCode += getAssignedUser().hashCode();
        }
        if (getOpType() != null) {
            _hashCode += getOpType().hashCode();
        }
        _hashCode += getState();
        if (getParentOpId() != null) {
            _hashCode += getParentOpId().hashCode();
        }
        if (getCircuitId() != null) {
            _hashCode += getCircuitId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Co_document_operation_bean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_document_operation_bean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extendedProps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "extendedProps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "co_document_operation_ExtendedProps_bean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iduser_encryptioncertificate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "iduser_encryptioncertificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("symmetricKeyEncryptedWithkeyPairPublicKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "symmetricKeyEncryptedWithkeyPairPublicKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "observation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reprocess");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "reprocess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "delete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FClose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "fClose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fdelete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "fdelete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FAcess");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "fAcess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("freg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "freg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "idEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FExpired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "FExpired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extern");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "extern"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "operationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "externalId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "documentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assignedUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "assignedUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "opType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentOpId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "parentOpId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("circuitId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://coffice.net/cOfficeWebServices", "circuitId"));
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
