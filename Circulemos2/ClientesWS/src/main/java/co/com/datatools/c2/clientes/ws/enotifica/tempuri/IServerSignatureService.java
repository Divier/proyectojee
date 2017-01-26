/**
 * IServerSignatureService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.com.datatools.c2.clientes.ws.enotifica.tempuri;

public interface IServerSignatureService extends java.rmi.Remote {
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.CertReference[] getUserCertificateRefs(java.lang.String userId, java.lang.String password) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault;
    public byte[] sign(java.lang.String userId, java.lang.String password, java.lang.String idCertificate, java.lang.String certPin, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile signatureProfile, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType signatureType, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm, java.lang.String[] options, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams parameters, byte[] detachedSignature, byte[] signingDocument) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault;
    public byte[] encrypt(java.lang.String userId, java.lang.String password, java.lang.String idCertificate, java.lang.String certPin, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm, byte[] dataToEncrypt) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault;
    public byte[] signByToken(java.lang.String token, java.lang.String idCertificate, java.lang.String certPin, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile signatureProfile, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureType signatureType, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvHashAlgorithm hashAlgorithm, java.lang.String[] options, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureParams parameters, byte[] detachedSignature, byte[] signingDocument) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault;
    public byte[] signProvider(java.lang.Integer idCertificate, java.lang.String uri, java.lang.String providerParameter, byte[] signingDocument) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault;
    public co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.SignatureValidation verify(java.lang.String userId, java.lang.String password, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.IvSignatureEnumsIvSignatureProfile signatureProfile, java.lang.String[] options, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureBeans.VerifyParams parameters, byte[] detachedSignature, byte[] document) throws java.rmi.RemoteException, co.com.datatools.c2.clientes.ws.enotifica.IvSignatureService_ivsignatureservice.IvSignatureFault;
}
