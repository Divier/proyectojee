package co.com.datatools.ingreso.utilidades;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.jboss.security.Base64Encoder;
import org.jboss.security.auth.spi.Util;

public class Cifrador {

    /**
     * permite codificar el mensaje utilizando un algoritmo y un encoder dado
     * 
     * @param mensaje
     *            - texto que debe ser cifrado
     * @param algoritmo
     *            - algoritmo que se va a utilizar para realizar la encriptacion
     * @param codificador
     *            - Encoder utilizado para tyransformar el texto encriptado al conjunto de caracteres solicitado
     * @return cadena correspondiente al mensaje encriptado
     * 
     * @author sergio.torres (21/05/2015)
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String cifrarMensaje(String mensaje, EnumAlgoritmo algoritmo, EnumCodificador codificador,
            EnumMetodo metodo) throws NoSuchAlgorithmException, IOException {
        byte[] bytesEntrada = mensaje.getBytes();

        MessageDigest messageDigest = MessageDigest.getInstance(algoritmo.getCodigo());
        messageDigest.reset();
        messageDigest.update(bytesEntrada);
        byte[] entradaCifrada = messageDigest.digest();
        String salida = null;
        if (metodo.equals(EnumMetodo.bind)) {
            salida = transformarXml(entradaCifrada, codificador);
        } else {
            salida = transformarJboss(entradaCifrada, codificador);
        }
        return salida;
    }

    /**
     * Utiliza la libreria de sguridad de jboss para codificar la salida encriptada
     * 
     * @param entrada
     * @return salida codificada
     * @throws IOException
     */
    private static String transformarJboss(byte[] entrada, EnumCodificador codificador) throws IOException {
        switch (codificador) {
        case base64: {
            return Base64Encoder.encode(entrada);
        }
        case hex: {
            return Util.encodeBase16(entrada);
        }
        }
        return null;
    }

    /**
     * Utiliza la libreria dejavax.xml.bind para codificar la salida encriptada
     * 
     * @param entrada
     * @return salida codificada
     * @throws IOException
     */
    private static String transformarXml(byte[] entrada, EnumCodificador codificador) throws IOException {
        switch (codificador) {
        case base64: {
            return DatatypeConverter.printBase64Binary(entrada);
        }
        case hex: {
            return DatatypeConverter.printHexBinary(entrada);
        }
        }
        return null;
    }

}
