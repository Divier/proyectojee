package co.com.datatools.c2.negocio.interfaces;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.DiaNoHabilDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * @author luis.forero
 */
@Local
public interface ILDiaNoHabil {

    /**
     * @see IRDiaNoHabil#consultarDiasNoHabiles(int, Date, Date)
     */
    List<DiaNoHabilDTO> consultarDiasNoHabiles(int codigoOrganismo, Date fechaInicio, Date fechaFin);

    /**
     * @see IRDiaNoHabil#esDiaNoHabil(int, Date)
     */
    boolean esDiaNoHabil(int codigoOrganismo, Date fecha);

    /**
     * @see IRDiaNoHabil#modificarDiaNoHabil(DiaNoHabilDTO)
     */
    void modificarDiaNoHabil(DiaNoHabilDTO diaNoHabilDTO) throws CirculemosNegocioException;

    /**
     * @see IRDiaNoHabil#registrarDiasNoHabiles(List)
     */
    List<DiaNoHabilDTO> registrarDiasNoHabiles(List<DiaNoHabilDTO> diasNoHabiles) throws CirculemosNegocioException;

    /**
     * @see IRDiaNoHabil#eliminarDiaNoHabil(DiaNoHabilDTO)
     */
    void eliminarDiaNoHabil(DiaNoHabilDTO diaNoHabilDTO) throws CirculemosNegocioException;

    /**
     * @see IRDiaNoHabil#sumarDiasHabiles(int, Date, int)
     */
    Date sumarDiasHabiles(int codigoOrganismo, Date fechaInicio, int diasHabiles);

    /**
     * @see IRDiaNoHabil#consultarDiasNoHabiles(int, Date)
     */
    List<Calendar> consultarDiasNoHabiles(int codigoOrganismo, Date fechaInicio);
}
