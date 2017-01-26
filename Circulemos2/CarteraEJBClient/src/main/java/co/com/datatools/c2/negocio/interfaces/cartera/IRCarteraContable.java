package co.com.datatools.c2.negocio.interfaces.cartera;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.IngresoNotaCarteraDTO;
import co.com.datatools.c2.dto.cartera.SaldoCarteraDTO;
import co.com.datatools.c2.dto.common.ConsultaValorInteresesDTO;
import co.com.datatools.c2.dto.common.cartera.CambioEstadoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.MovimientoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.RegistroCarteraDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRCarteraContable {

    /**
     * Cambia la obligacion indicada al estado indicado. En caso de ser activacion de cartera, asigna la fecha de inicio como la fecha indicada.
     * Utiliza la actividad indicada para realizar la trazabilidad.
     * 
     * @param cambioEstadoCartera
     * @author rodrigo.cruz
     * @throws CirculemosNegocioException
     */
    void actualizarEstadoCartera(CambioEstadoCarteraDTO cambioEstadoCartera) throws CirculemosNegocioException;

    /**
     * Crea un registro en cartera basado en la informacion recibida como parametro. Este registro se relaciona al movimiento que se crea con el valor
     * del movimiento. Retorna el identificador de la cartera generada.
     * 
     * @param registroCartera
     * @return
     * @author rodrigo.cruz
     * @throws CirculemosNegocioException
     */
    Long registrarCartera(RegistroCarteraDTO registroCartera) throws CirculemosNegocioException;

    /**
     * Registra el movimiento de cartera indicado, asociándolos a la cartera con el identificador indicado.
     * 
     * @param movimiento
     *            Datos: <br>
     *            <ul>
     *            <li>idCartera: {@code MovimientoCarteraDTO.MovimientosCarteraDTO.CarteraDTO.id}
     *            <li>codEstadoObligacion: {@code MovimientoCarteraDTO.MovimientosCarteraDTO.CarteraDTO.EstadoObligacionDTO.codigo}
     *            <li>idActividad: {@code MovimientoCarteraDTO.idActividad}
     *            <li>valorMovimiento: {@code MovimientoCarteraDTO.MovimientosCarteraDTO.valorMovimiento}
     *            <li>codConceptoCartera: {@code MovimientoCarteraDTO.MovimientosCarteraDTO.ConceptoCarteraDTO.codigo}
     *            <li>valorSaldoCapital: {@code MovimientoCarteraDTO.saldoCapital}
     *            <li>valorSaldoInteres: {@code MovimientoCarteraDTO.saldoInteres}
     *            <li>fechaMovimiento: {@code MovimientoCarteraDTO.MovimientosCarteraDTO.fechaMovimiento}
     *            <li>loginUsuario: {@code MovimientoCarteraDTO.MovimientosCarteraDTO.loginUsuario}
     *            </ul>
     * @return
     * @author rodrigo.cruz
     */
    Long registrarMovimiento(MovimientoCarteraDTO movimiento);

    /**
     * Permite consultar el valor acumulado de interes de acuerdo a un rango de fechas
     * 
     * @param consultaValorInteresesDTO
     * @return BigDecimal
     * @author divier.casas
     * @throws CirculemosNegocioException
     */
    public BigDecimal consultarValorIntereses(ConsultaValorInteresesDTO consultaValorInteresesDTO)
            throws CirculemosNegocioException;

    /**
     * Consulta la obligación asociada al identificador indicado. Ordena los saldos por su fecha de registro de forma descendente, es decir, de la mas
     * reciente a la mas antigua
     * 
     * @param id
     * @return Retorna la obligación asociada al identificador indicado.
     * @author luis.forero(mod 2016-02-25)
     */
    public CarteraDTO consultarCartera(long id);

    /**
     * Registra la nota a la obligación indicada. Si es necesario cierra la cartera y la reinicia actualizando datos de la obligación.
     * 
     * @param ingresoNotaCarteraDTO
     * @author giovanni.velandia
     */
    public void registrarNotaCartera(IngresoNotaCarteraDTO ingresoNotaCarteraDTO);

    /**
     * Calcula los intereses causados a un comparendo de acuerdo a las fechas indicadas.
     * 
     * @param idObligacion
     *            identificador de la obligacion en cartera
     * @param idClaseInteres
     *            identificador de la clase de interes
     * @param fechaInicioInteres
     *            fecha inicial para calcular el interes.
     * @param fechaCalculo
     *            fecha de realizacion del calculo del interes.
     * @return Retorna el saldo de intereses causados
     * @author divier.casas(2016-04-11)
     */
    public BigDecimal calcularIntereses(Long idObligacion, Integer idClaseInteres, Date fechaInicioInteres,
            Date fechaCalculo) throws CirculemosNegocioException;

    /**
     * Consulta un concepto de cartera
     * 
     * @param conceptoCarteraDTO
     * @return
     */
    List<ConceptoCarteraDTO> consultarConceptoCartera(ConceptoCarteraDTO conceptoCarteraDTO);

    /**
     * Consulta la obligación asociada al filtro indicado. Ordena los saldos por su fecha de registro de forma descendente, es decir, de la mas
     * reciente a la mas antigua
     * 
     * @param cartera
     * @return Retorna la obligación asociada al filtro indicado.
     * @author julio.pinzon(2016-04-27)
     */
    public CarteraDTO consultarCartera(CarteraDTO cartera);

    /**
     * consulta los saldos de una cartera
     * 
     * 
     * @param saldoCartera
     * @return Retorna una lista de saldos cartera asociadas a la cartera
     */
    public List<SaldoCarteraDTO> consultarSaldoCartera(SaldoCarteraDTO saldoCartera);

}
