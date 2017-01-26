package co.com.datatools.util.mail;

import java.util.regex.Pattern;

/**
 * Clase para validar si una cadena es un correo valido utiliza la expresion regular <br>
 * {@link EmailValidator#EMAIL_PATTERN}: ^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$
 * 
 * <pre>
 * ^                       #start of the line
 *   [_A-Za-z0-9-\\+]+     #  must start with string in the bracket [ ], must contains one or more (+)
 *   (                     #   start of group #1
 *     \\.[_A-Za-z0-9-]+   #     follow by a dot "." and string in the bracket [ ], must contains one or more (+)
 *   )*                    #   end of group #1, this group is optional (*)
 *     {@literal @}                   #     must contains a "@" symbol
 *      [A-Za-z0-9-]+      #       follow by string in the bracket [ ], must contains one or more (+)
 *       (                 #         start of group #2 - first level TLD checking
 *        \\.[A-Za-z0-9]+  #           follow by a dot "." and string in the bracket [ ], must contains one or more (+)
 *       )*                #         end of group #2, this group is optional (*)
 *       (                 #         start of group #3 - second level TLD checking
 *        \\.[A-Za-z]{2,}  #           follow by a dot "." and string in the bracket [ ], with minimum length of 2
 *       )                 #         end of group #3
 * $                       #end of the line
 * </pre>
 * 
 */
public final class EmailValidator {
    /**
     * ^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$
     */
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private EmailValidator() {
    }

    /**
     * Valida si la cadena ingresa cumple con el formato de correo establecido
     * 
     * @param cadena
     *            cadena a validar
     * @return true email valido, false email invalido
     */
    public static boolean validate(final String cadena) {
        return pattern.matcher(cadena).matches();

    }
}
