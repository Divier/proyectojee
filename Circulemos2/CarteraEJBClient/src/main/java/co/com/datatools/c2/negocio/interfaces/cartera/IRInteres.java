package co.com.datatools.c2.negocio.interfaces.cartera;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.dto.common.ConsultaTasaInteresDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRInteres {

    /**
     * Permite consultar la tasa de interes vigente para una determinada fecha
     * 
     * @param consultaTasaInteresDTO
     * @return InteresDTO
     * @author divier.casas
     */
    public InteresDTO consultarTasasInteres(ConsultaTasaInteresDTO consultaTasaInteresDTO);

    /**
     * Registra el interes que se envia como parametro
     * 
     * @param interesDTO
     *            Contiene la informacion del interes a persistir
     * @return InteresDTO persistido
     * @author Dixon.Alvarez 2016-09-30
     */
    InteresDTO registrarInteres(InteresDTO interesDTO) throws CirculemosNegocioException;

    /**
     * Actualiza la información de un interes
     * 
     * @param interesDTO
     *            Contiene la información del interes a actualizar
     * @author Dixon.Alvarez 2016-09-30
     */
    void modificarInteres(InteresDTO interesDTO) throws CirculemosNegocioException;

    /**
     * Consulta los intereses que existen en el sistema teniendo en cuenta los filtros ingresados
     * 
     * @param interesDTO
     *            Contiene la información por la cual se va filtrar la consulta
     * @param validarEstado
     *            Indica si se debe tener en cuenta el estado del interes
     * @return Lista vacia en caso de que no se encuentren resultados
     * @author Dixon.Alvarez 2016-09-30
     */
    List<InteresDTO> consultarIntereses(InteresDTO interesDTO, boolean validarEstado);

}
