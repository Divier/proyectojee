package co.com.datatools.util.cifrado;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.jboss.security.auth.spi.Util;

/**
 * Fachada para facilitar la operaciones de Digester sobre un string de entrada
 * 
 * @author claudia.rodriguez
 * 
 */
public final class Digester {

    /**
     * Nombre de los algoritmos soportados para la ejecucion de digester
     * 
     * @author felipe.martinez
     */
    public enum EnumAlgoritmo {
        MD5("MD5"), //
        SHA256("SHA-256"), //
        SHA384("SHA-384"), //
        SHA512("SHA-512");//
        /**
         * Nombre del algoritmo
         */
        public String nombre;

        EnumAlgoritmo(String nombre) {
            this.nombre = nombre;
        }
    }

    /**
     * Procesa una entrada mediante MessageDigest utilizando el algoritmo enviado
     * 
     * @param entrada
     *            Cadena que se desea procesar
     * @param algoritmo
     *            Algoritmo a utilizar
     * @return resultado de la cadena procesada en Base 16
     * @throws NoSuchAlgorithmException
     *             cuando el algoritmo seleccionado no se encuentra en el entorno
     */
    public static String digest(String entrada, EnumAlgoritmo algoritmo) throws NoSuchAlgorithmException {
        byte[] bytesEntrada = entrada.getBytes();

        MessageDigest messageDigest = MessageDigest.getInstance(algoritmo.nombre);
        messageDigest.reset();
        messageDigest.update(bytesEntrada);
        byte[] entradaCifrada = messageDigest.digest();
        return DatatypeConverter.printBase64Binary(entradaCifrada);
    }

    /**
     * Procesa una entrada mediante MessageDigest utilizando el algoritmo enviado
     * 
     * @param entrada
     *            Cadena que se desea procesar
     * @param algoritmo
     *            Algoritmo a utilizar
     * @return resultado de la cadena procesada en Base 16 utilizando el encoder {@link Util#encodeBase16(byte[])}
     * @throws NoSuchAlgorithmException
     *             cuando el algoritmo seleccionado no se encuentra en el entorno
     * @throws IOException
     */
    public static String digestEncoderJboss(String entrada, EnumAlgoritmo algoritmo) throws NoSuchAlgorithmException,
            IOException {
        byte[] bytesEntrada = entrada.getBytes();

        MessageDigest messageDigest = MessageDigest.getInstance(algoritmo.nombre);
        messageDigest.reset();
        messageDigest.update(bytesEntrada);
        byte[] entradaCifrada = messageDigest.digest();
        return Util.encodeBase16(entradaCifrada);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(digest("admin", EnumAlgoritmo.SHA512));
        System.out.println(digestEncoderJboss("admin", EnumAlgoritmo.SHA512));
    }
}
