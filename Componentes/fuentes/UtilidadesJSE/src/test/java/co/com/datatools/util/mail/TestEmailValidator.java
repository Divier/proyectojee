package co.com.datatools.util.mail;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Clase de prueba para {@link EmailValidator}
 * 
 * @author Felipe Martinez
 */
public class TestEmailValidator {

    @Test
    public void ValidEmailTest() {

        String[] emails = new String[] { "nombre@yahoo.com", "nombre-100@yahoo.com", "nombre.100@yahoo.com",
                "nombre111@nombre.com", "nombre-100@nombre.net", "nombre.100@nombre.com.au", "nombre@1.com",
                "nombre@gmail.com.com", "nombre+100@gmail.com", "nombre-100@yahoo-test.com",
                "nombre.apellido@datatools.com.co" };

        for (String temp : emails) {
            boolean valid = EmailValidator.validate(temp);
            assertEquals(temp, valid, true);
        }

    }

    @Test
    public void InValidEmailTest() {
        String[] emails = new String[] { "nombre", "nombre@.com.my", "nombre123@gmail.a", "nombre123@.com",
                "nombre123@.com.com", ".nombre@nombre.com", "nombre()*@gmail.com", "nombre@%*.com",
                "nombre..2002@gmail.com", "nombre.@gmail.com", "nombre@nombre@gmail.com", "nombre@gmail.com.1a" };
        for (String temp : emails) {
            boolean valid = EmailValidator.validate(temp);
            assertEquals(temp, valid, false);
        }
    }
}
