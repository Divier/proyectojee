package co.com.datatools.c2.adaptador.cartera;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Local;

import co.com.datatools.c2.adaptador.dto.CarteraComparendoDTO;
import co.com.datatools.c2.adaptador.dto.RegistroCarteraComparendoDTO;
import co.com.datatools.c2.adaptador.dto.RegistroNotaCarteraDTO;
import co.com.datatools.c2.enumeraciones.EnumOpcConsultaCarteraComp;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Interface propia de comparendos que ofrece los servicios de cartera.
 * 
 * @author julio.pinzon
 * 
 */
@Local
public interface ILCarteraComparendo {

    /**
     * 
     * @param idObligacion
     * @param fechaActivacion
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    public void activarCarteraComparendo(long idObligacion, Date fechaActivacion) throws CirculemosNegocioException;

    /**
     * 
     * @param registroCarteraComparendoDTO
     * @return
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    public long registrarCarteraComparendo(RegistroCarteraComparendoDTO registroCarteraComparendoDTO)
            throws CirculemosNegocioException;

    /**
     * Registra la nota de cartera indicada, si es necesario cierra la cartera y la reinicia actualizando datos de la obligación.
     * 
     * @param registroNotaCarteraDTO
     */
    public void registrarNotaCartera(RegistroNotaCarteraDTO registroNotaCarteraDTO);

    /**
     * Consulta la información de la cartera con el identificador indicado
     * 
     * @param id
     *            identificador de la cartera
     * @param opcConsultaCarteraComp
     *            opciones posibles de consulta para saldos
     * @return Retorna la información de la cartera con el identificador indicado. En caso de no existir retorna excepción.
     * @author julio.pinzon<br>
     *         luis.forero (mod 2016-02-29)
     */
    public CarteraComparendoDTO consultarCartera(long id, EnumOpcConsultaCarteraComp... opcConsultaCarteraComp)
            throws CirculemosNegocioException;

    /**
     * Enumeración encargada de contener los valores fijos que requiere cartera. Por ejemplo, los tipos de obligacion, estados, etc.
     * 
     * @author julio.pinzon
     * 
     */
    enum EnumCarteraComparendo implements SearchableEnumeration<Integer> {

        /**
         * Indica que el estado de la obligación es ACTIVO.
         */
        OBLIGACION_ACTIVO(1), //

        /**
         * Indica que el estado de la obligación es PENDIENTE POR ACTIVAR.
         */
        OBLIGACION_PENDIENTE_ACTIVAR(2), //
        ;

        private int codigo;

        EnumCarteraComparendo(int codigo) {
            this.codigo = codigo;
        }

        @Override
        public Integer getValue() {
            return codigo;
        }

    }

    /**
     * Calcula los intereses causados a un comparendo de acuerdo a las fechas indicadas.
     * 
     * @param cicomparendo
     *            identificador del comparendo a calcular intereses
     * @param fechaGeneracionInteres
     *            fecha inicial para calcular el interes.
     * @param fechaCalculo
     *            fecha de realizacion del calculo del interes.
     * @return Retorna el saldo de intereses causados
     * @author divier.casas(2016-04-11)
     */
    public BigDecimal calcularInteresesComparendo(Long cicomparendo, Date fechaGeneracionInteres, Date fechaCalculo)
            throws CirculemosNegocioException;

    /**
     * Actualiza el deudor de la cartera asociada a un comparendo
     * 
     * @author giovanni.velandia
     * @param cicomparendo
     * @param idDeudor
     */
    public void actualizarDeudorCartera(Long cicomparendo, Long idDeudor);

    /**
     * Anula la cartera de un comparendo por el id de la cartera
     * 
     * @author diego.fonseca
     * @param idCartera
     *            cartera a anular
     */
    public void anularCartera(Long idCartera);

    /**
     * Cambia el nombre de la cartera por uno nuevo
     * 
     * @param idCartera
     * @param nuevoNombre
     * @author julio.pinzon 2016-08-24
     */
    public void cambiarNombreCartera(Long idCartera, String nuevoNombre);

    /**
     * Retorna el valor de la infracción
     * 
     * @param id
     * @return
     */
    public BigDecimal consultarValorDeInfraccion(Long idCartera);
}