package co.com.datatools.c2.seguridad;

/**
 * Clase encargada de cifrar y descifrar informaci�n utilizando un key interno.<br>
 * TODO Esta clase debe ser deprecada y el codigo debe generalizarse en UtilidadesJSE
 * 
 * @author robert.bautista
 * @since 2013-10-31
 */
public class TripleDes {

    /**
     * Retorna el texto indicado cifrado acorde a la librer�a de cifrado manejada por la aplicaci�n
     * 
     * @param unencryptedString
     *            texto a cifrar
     * 
     * @return texto cifrado
     */
    public static String encrypt(String unencryptedString) {
        return unencryptedString;

    }

    /**
     * Retorna el texto cifrado descifrado acorde a la librer�a de desciframiento manejada por la aplicaci�n
     * 
     * @param encryptedString
     *            el texto a descifrar
     * 
     * @return textoCifrado descifrado
     */
    public static String decrypt(String encryptedString) {
        return encryptedString;
    }
}
