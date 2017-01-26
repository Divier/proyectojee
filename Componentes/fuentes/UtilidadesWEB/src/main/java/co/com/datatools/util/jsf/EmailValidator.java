package co.com.datatools.util.jsf;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 * Validador de correo electronico para faces
 * 
 * @author Felipe Martinez
 */
@FacesValidator("co.com.datatools.util.jsf.EmailValidator")
public class EmailValidator implements javax.faces.validator.Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null) {
            final String inputEmail = value.toString();
            if (!co.com.datatools.util.mail.EmailValidator.validate(inputEmail)) {
                FacesMessage msg = new FacesMessage(ResourceBundle.getBundle(
                        "co.com.datatools.c2.bundle.util.Mensajes", Locale.getDefault()).getString(
                        "msg_err_email_invalido"));
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
    }

}
