package co.com.datatools.sogit.utilies;

/**
 * Unificacion de expresiones regulares
 * 
 * @author julio.pinzon
 */
public class ExpresionesRegulares {
    public static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Z0-9a-z]{2,})$";
    // anterior
    // (^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Z0-9a-z]{2,})$){0,255}
    public static final String REGEX_NUMERICO_NO_OBLIGATORIO = "[0-9]*";
    public static final String REGEX_SOLO_LETRAS_AL_MENOS_TRES = "[A-Za-z��]{3}[A-Za-z�� ]*";
    public static final String REGEX_SOLO_LETRAS_AL_MENOS_TRES_UBICABILIDAD = "[A-Za-z������������]{3}[A-Za-z������������ ]*";
    public static final String REGEX_SOLO_LETRAS_AL_MENOS_DOS = "[A-Za-z��.]{2}[A-Za-z��. ]*";
    public static final String REGEX_SOLO_LETRAS = "[A-Za-z������������ ]*";
    public static final String REGEX_ALFANUMERICO_NO_OBLIGATORIO = "[A-Za-z0-9\\s]*";
    public static final String REGEX_SOLO_LETRAS_AL_MENOS_TRES_PLUS = "[A-Za-z������������ @-_#$%]{3}[A-Za-z������������ @-_#$%]*";
    public static final String REGEX_NUMERICO_AL_MENOS_SIETE = "[0-9- ]{7}[0-9- ]*";
    public static final String REGEX_TELEFONO = "[A-Za-z0-9\\s\\-]*";
    public static final String REGEX_NOMBRE_COMERCIAL = "[A-Za-z0-9������������ @_#$%.\\[\\]]{2}[A-Za-z0-9������������ @_#$%.\\[\\]]*";

    // expresion regular para alfanumericos
    public static final String REGEX_ALFANUMERICO_OBLIGATORIO = "[A-Za-z0-9]*";
}
