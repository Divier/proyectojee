package co.com.datatools.c2.negocio.interfaces;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.DiaNoHabilDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRDiaNoHabil {

    /**
     * Retorna los d�as no h�biles que actualmente se encuentran configurados en el sistema seg�n par�metros de entrada. Los par�metros de entrada
     * pueden ser nulos, en cuyo caso el filtro de b�squeda cambia. Por ejemplo, si la fecha fin es <code>null</code> se buscan todos los d�as no
     * h�biles desde la fecha de inicio, si la fecha de inicio es <code>null</code> se buscan todos los d�as no h�biles configurados menores a la
     * fecha fin.
     * 
     * @param codigoOrganismo
     *            codigo de organismo de transito sobre el cual se estan consultando los dias no habiles
     * @param fechaInicio
     *            fecha inicial del intervale de la fecha
     * @param fechaFin
     *            fecha final del interfalo definido de la consulta
     * @return listado con los dias no habiles dentro del rango ingresado
     * @author luis.forero
     */
    List<DiaNoHabilDTO> consultarDiasNoHabiles(int codigoOrganismo, Date fechaInicio, Date fechaFin);

    /**
     * Valida si la fecha en el parametro de entrada es un dia no habil.
     * 
     * @param codigoOrganismo
     *            codigo del organismo de transito sobre el cual se esta consultando
     * @param fecha
     *            fecha a consultar si es o no dia no habil
     * @return Retorna verdadero si es parametro de entrada en un dia no habil, falso en caso contrario.
     * @author luis.forero (mod 2014-09-10)
     */
    boolean esDiaNoHabil(int codigoOrganismo, Date fecha);

    /**
     * Permite convertir un dia no habil en un dia habil. Esto se realiza mediante el mecanismo de borrado logico. En la observaci�n se indica el
     * motivo por el cual se desea modificar el d�a no habil.
     * 
     * @param diaNoHabilDTO
     *            objeto con los atributos a modificar
     * @throws CirculemosNegocioException
     * <br>
     *             ADM_019002=Dia no habil ya registrado.<br>
     *             ADM_019003=La fecha a modificar no puede ser menor a la fecha actual.<br>
     * @author luis.forero (mod 2014-09-10)<br>
     *         luis.forero (mod 2015-03-05)<br>
     */
    void modificarDiaNoHabil(DiaNoHabilDTO diaNoHabilDTO) throws CirculemosNegocioException;

    /**
     * Retorna null si todos los dias fueron adicionados correctamente. En caso contrario retorna los dias que no puedieron ser adicionados en el
     * sistema.
     * 
     * @param diasNoHabiles
     *            listado de dias no habiles a registrar en el sistema
     * @return listado ingresado con los ids
     * @throws CirculemosNegocioException
     * <br>
     *             ADM_019001=No puede efectuarse la operacion porque la fecha es menor o igual a la fecha actual.<br>
     *             ADM_019002=Dia no habil ya registrado.<br>
     *             ADM_019003=La fecha a modificar no puede ser menor a la fecha actual.<br>
     * @author luis.forero (mod 2014-09-10)<br>
     *         luis.forero (mod 2015-03-05)
     */
    List<DiaNoHabilDTO> registrarDiasNoHabiles(List<DiaNoHabilDTO> diasNoHabiles) throws CirculemosNegocioException;

    /**
     * Elimina de la base de datos el registro del dia no habil
     * 
     * @param diaNoHabilDTO
     *            dia no habil a borrar por id
     * @throws CirculemosNegocioException
     * <br>
     *             ADM_019001=No puede efectuarse la operacion porque la fecha es menor o igual a la fecha actual.<br>
     * @author luis.forero (mod 2014-09-10)<br>
     *         luis.forero (mod 2015-03-05)
     */
    void eliminarDiaNoHabil(DiaNoHabilDTO diaNoHabilDTO) throws CirculemosNegocioException;

    /**
     * Suma o resta los d�as h�biles teniendo en cuenta las fechas consideradas no h�biles del organismo de tr�nsito. Para esto �ltimo, internamente
     * consulta los d�as no h�biles definidos en el sistema.
     * 
     * @param codigoOrganismo
     *            El c�digo de organismo de tr�nsito.
     * @param fechaInicio
     *            Fecha inicial desde donde empieza a sumar d�as h�biles.
     * @param diasHabiles
     *            El n�mero de d�as h�biles a sumar.
     * @return La fecha que indica la fecha de inicio m�s los d�as h�biles sumados.
     * @author rodrigo.cruz <br>
     *         robert.bautista (mod 2016-04-13)
     * 
     */
    Date sumarDiasHabiles(int codigoOrganismo, Date fechaInicio, int diasHabiles);

    /**
     * Consulta las fechas que son dias no habiles para un organismo de trasito
     * 
     * @param codigoOrganismo
     *            codigo del organismo de trasito
     * @param fechaInicio
     *            fecha a partir de la cual se consultan los dias no habiles para un organismo de transito
     * @return lista de fechas no habiles para un organismo de transito, vacio si no hay dias no habiles
     * @author felipe.martinez
     */
    List<Calendar> consultarDiasNoHabiles(int codigoOrganismo, Date fechaInicio);
}
