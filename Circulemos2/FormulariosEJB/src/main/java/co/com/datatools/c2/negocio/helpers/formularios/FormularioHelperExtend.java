package co.com.datatools.c2.negocio.helpers.formularios;

import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.entidades.Formulario;

public class FormularioHelperExtend extends FormularioHelper {

    /**
     * Convierte a nivel uno el responsable del formulario
     * 
     * @param formulario
     *            con los datos extraidos en la consulta
     * @return formulario con todos los datos del responsable a nivel 1
     * @author luis.forero(mod 2015-09-21)
     */
    public static FormularioDTO toLevel1DTO(Formulario formulario) {
        FormularioDTO formularioDTO = FormularioHelper.toLevel1DTO(formulario);
        formularioDTO.setResponsableFormulario(ResponsableFormularioHelper.toLevel1DTO(formulario
                .getResponsableFormulario()));
        return formularioDTO;
    }
}
