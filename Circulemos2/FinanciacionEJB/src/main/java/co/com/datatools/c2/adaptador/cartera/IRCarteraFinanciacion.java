package co.com.datatools.c2.adaptador.cartera;

import java.util.Date;

import javax.ejb.Local;

import co.com.datatools.c2.adaptador.dto.CarteraFinanciacionDTO;
import co.com.datatools.c2.adaptador.dto.RegistroCarteraFinanciacionDTO;
import co.com.datatools.c2.adaptador.dto.TasaInteresDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Interface propia de comparendos que ofrece los servicios de cartera.
 * 
 * @author julio.pinzon
 * 
 */
@Local
public interface IRCarteraFinanciacion {

    /**
     * Activa obligacion
     * 
     * @param idObligacion
     * @param fechaActivacion
     * @throws CirculemosNegocioException
     * @author julio.pinzon (2016-05-18)
     */
    public void activarCarteraFinanciacion(long idObligacion, Date fechaActivacion) throws CirculemosNegocioException;

    /**
     * 
     * @param registroCarteraFinanciacionDTO
     * @return
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    public long registrarCarteraFinanciacion(RegistroCarteraFinanciacionDTO registroCarteraFinanciacionDTO)
            throws CirculemosNegocioException;

    /**
     * Consulta la información de la cartera con el identificador indicado
     * 
     * @param id
     *            identificador de la cartera
     * @return Retorna la información de la cartera con el identificador indicado. En caso de no existir retorna excepción.
     * @author julio.pinzon (2016-05-18)
     */
    public CarteraFinanciacionDTO consultarCartera(long id) throws CirculemosNegocioException;

    /**
     * Consulta la información de la cartera con el nombre y tipo de obligacion
     * 
     * @param nombre
     * @param idTipoObligacion
     * @return Retorna la información de la cartera con el identificador indicado. En caso de no existir retorna nulo el id.
     * @author julio.pinzon (2016-05-18)
     */
    public CarteraFinanciacionDTO consultarCartera(String nombre, Integer idTipoObligacion);

    /**
     * Enumeración encargada de contener los valores fijos que requiere cartera. Por ejemplo, los tipos de obligacion, estados, etc.
     * 
     * @author julio.pinzon
     * 
     */
    enum EnumCarteraFinanciacion implements SearchableEnumeration<Integer> {

        /**
         * Indica que el estado de la obligación es ACTIVO.
         */
        OBLIGACION_ACTIVO(1), //

        /**
         * Indica que el estado de la obligación es PENDIENTE POR ACTIVAR.
         */
        OBLIGACION_PENDIENTE_ACTIVAR(2), //

        /**
         * Indica que el estado de la obligación es PAGADO.
         */
        PAGADO(3), //

        /**
         * Indica que el estado de la obligación es FINANCIADO.
         */
        FINANCIADO(4), //

        ANULADA(5), //

        PREFINANCIADA(6), //

        PAGADO_FINANCIACION(7), //
        ;

        private int codigo;

        EnumCarteraFinanciacion(int codigo) {
            this.codigo = codigo;
        }

        @Override
        public Integer getValue() {
            return codigo;
        }

    }

    /**
     * Cambia estado de cartera a financiado
     * 
     * @param idObligacion
     * @param fechaActivacion
     * @throws CirculemosNegocioException
     */
    public void financiarCarteraFinanciacion(long idObligacion, Date fechaActivacion) throws CirculemosNegocioException;

    /**
     * Consulta la tasa de interes a la fecha indicada.
     * 
     * @param claseInteres
     *            identificador de la clase de interes que se encuentra consultando
     * @param fecha
     *            fecha sobre la cual se desea obtener la tasa.
     * @author luis.forero
     */
    public TasaInteresDTO consultarTasaInteresVigente(int claseInteres, Date fecha);

    /**
     * Registra el movimiento de cartera indicado, asociándolos a la cartera con el identificador indicado.
     * 
     */
    public void registrarMovimientoCartera(FinanciacionDTO financiacionDTO);

    /**
     * Cambia estado de cartera a prefinanciado
     * 
     * @param idObligacion
     * @param fechaActivacion
     * @throws CirculemosNegocioException
     * @author julio.pinzon 2016-08-03
     */
    public void preFinanciarCarteraFinanciacion(long idObligacion, Date fechaActivacion)
            throws CirculemosNegocioException;

    /**
     * Consulta la información de la cartera de la financiacion con el numero de obligacion
     * 
     * @param numeroFinanciacion
     * @return Retorna la información de la cartera con el identificador indicado. En caso de no existir retorna nulo el id.
     * @author julio.pinzon (2016-08-03)
     */
    public CarteraFinanciacionDTO consultarCarteraFinanciacion(String numeroFinanciacion);

    /**
     * Actualiza el estado de la cartera asociada a las obligaciones de la financiacion al estado anterior
     * 
     * @param idProceso
     * @author divier.casas mod-julio.pinzon (2016-08-19)
     */
    void actualizarEstadoAnteriorCartera(Long idProceso) throws CirculemosNegocioException;
}