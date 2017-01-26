package co.com.datatools.c2.negocio.interfaces.cartera;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.dto.common.ConsultaTasaInteresDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILInteres {

    /**
     * @see IRInteres#consultarTasasInteres()
     */
    public InteresDTO consultarTasasInteres(ConsultaTasaInteresDTO consultaTasaInteresDTO);

    /**
     * @see IRInteres#registrarInteres(InteresDTO)
     */
    InteresDTO registrarInteres(InteresDTO interesDTO) throws CirculemosNegocioException;

    /**
     * @see IRInteres#modificarInteres(InteresDTO)
     */
    void modificarInteres(InteresDTO interesDTO) throws CirculemosNegocioException;

    /**
     * @see IRInteres#consultarIntereses(InteresDTO, boolean)
     */
    List<InteresDTO> consultarIntereses(InteresDTO interesDTO, boolean validarEstado);
}
