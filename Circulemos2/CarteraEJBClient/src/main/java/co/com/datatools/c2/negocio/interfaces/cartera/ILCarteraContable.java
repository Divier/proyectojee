package co.com.datatools.c2.negocio.interfaces.cartera;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.IngresoNotaCarteraDTO;
import co.com.datatools.c2.dto.cartera.SaldoCarteraDTO;
import co.com.datatools.c2.dto.common.ConsultaValorInteresesDTO;
import co.com.datatools.c2.dto.common.cartera.CambioEstadoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.MovimientoCarteraDTO;
import co.com.datatools.c2.dto.common.cartera.RegistroCarteraDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILCarteraContable {

    /**
     * @see IRCarteraContable#actualizarEstadoCartera(CambioEstadoCarteraDTO)
     */
    void actualizarEstadoCartera(CambioEstadoCarteraDTO cambioEstadoCartera) throws CirculemosNegocioException;;

    /**
     * @see IRCarteraContable#registrarCartera(RegistroCarteraDTO)
     */
    Long registrarCartera(RegistroCarteraDTO registroCartera) throws CirculemosNegocioException;

    /**
     * @see IRCarteraContable#registrarMovimiento(MovimientoCarteraDTO)
     */
    Long registrarMovimiento(MovimientoCarteraDTO movimiento);

    /**
     * @see IRCarteraContable#consultarValorIntereses()
     */
    public BigDecimal consultarValorIntereses(ConsultaValorInteresesDTO consultaValorInteresesDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRCarteraContable#consultarCartera(long)
     */
    public CarteraDTO consultarCartera(long id);

    /**
     * @see IRCarteraContable#registrarNotaCartera(IngresoNotaCarteraDTO)
     */
    public void registrarNotaCartera(IngresoNotaCarteraDTO ingresoNotaCarteraDTO);

    /**
     * @see IRCarteraContable#calcularIntereses(Long, Integer, Date, Date)
     */
    public BigDecimal calcularIntereses(Long idObligacion, Integer idClaseInteres, Date fechaInicioInteres,
            Date fechaCalculo) throws CirculemosNegocioException;

    /**
     * @see IRCarteraContable#consultarConceptoCarteraConceptoCarteraDTO)
     */
    List<ConceptoCarteraDTO> consultarConceptoCartera(ConceptoCarteraDTO conceptoCarteraDTO);

    /**
     * @see IRCarteraContable#consultarCartera(CarteraDTO)
     */
    public CarteraDTO consultarCartera(CarteraDTO cartera);

    /**
     * @see IRCarteraContable#consultarSaldoCartera(SaldoCarteraDTO)
     */
    public List<SaldoCarteraDTO> consultarSaldoCartera(SaldoCarteraDTO saldoCartera);

}