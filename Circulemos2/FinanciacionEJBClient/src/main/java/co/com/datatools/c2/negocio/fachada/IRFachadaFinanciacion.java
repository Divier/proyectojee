package co.com.datatools.c2.negocio.fachada;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.RespuestaReplicarFinanciacionDTO;
import co.com.datatools.c2.dto.ValidaPagoFinanciacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRFachadaFinanciacion {

    /**
     * @param financiacionDTO
     * @return
     * @author Rodrigo.Cruz
     */
    FinanciacionDTO consultarFinanciacion(String numeroFinanciacion);

    /**
     * Recibe el codigo de organismo para realizar la tarea de replicar financiaciones utilizando funcionalidad de la integracion con terceros
     * 
     * @param codigoOrganismo
     * @return Respuesta de la ejecucion de la replica
     * @author julio.pinzon (2016-05-04)
     */
    public RespuestaReplicarFinanciacionDTO replicarFinanciacionTerceros(Integer codigoOrganismo);

    /**
     * Registra y activa la cartera para una financiacion
     * 
     * @param financiacionDTO
     * @return Id de la cartera generada
     * @throws CirculemosNegocioException
     * @author julio.pinzon(2016-07-26)
     */
    public Long registrarCarteraFinanciacion(FinanciacionDTO financiacionDTO) throws CirculemosNegocioException;

    /**
     * Lleva a cabo la validacion del pago de cuotas de financiaciones.
     * 
     * @param Integer
     *            Codigo del organismo de transito
     * @return ValidaPagoFinanciacionDTO
     * @author divier.casas(2016-08-03)
     */
    public ValidaPagoFinanciacionDTO validarPagosFinanciaciones(Integer codigoOrganismo);
}
