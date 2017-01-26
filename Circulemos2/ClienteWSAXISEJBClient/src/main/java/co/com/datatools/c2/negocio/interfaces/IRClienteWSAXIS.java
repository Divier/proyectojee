package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Interface que ofrece los servicios para consumir WebService de SIMIT
 * 
 * @author julio.pinzon 2016-08-12
 * 
 */
@Remote
public interface IRClienteWSAXIS {

    /**
     * Realiza la invocacion con el WebService para realizar la registrar la financiacion y obtener el id del convenio
     * 
     * @param financiacion
     *            datos de la financiacion
     * @return Financiacion con el Numero de axis para Wla financiacion
     * @author julio.pinzon(2016-08-12)
     * @throws CirculemosNegocioException
     */
    public FinanciacionDTO registrarFinanciacion(FinanciacionDTO financiacion) throws CirculemosNegocioException;

    /**
     * Realiza la invocacion con el WebService para anular la financiacion
     * 
     * @param numeroFinanciacion
     *            numero del convenio
     * @author julio.pinzon(2016-08-17)
     * @throws CirculemosNegocioException
     */
    public void anularFinanciacion(String numeroFinanciacion) throws CirculemosNegocioException;

    /**
     * Realiza la invocacion con el WebService para realizar la impugnacion de un comparendo
     * 
     * @param comparendo
     *            datos del comparendo
     * @param impugnacion
     *            datos de la impugnacion
     * @author julio.pinzon(2016-08-17)
     * @throws CirculemosNegocioException
     */
    public void impugnarComparendo(ComparendoDTO comparendo, ProcesoDTO impugnacion) throws CirculemosNegocioException;

    /**
     * Realiza la invocacion con el WebService para realizar el registro del fallo sobre la impugnacion previa
     * 
     * @param fallo
     *            datos del fallo
     * @return Nuevo numero de factura
     * @author julio.pinzon(2016-08-17)
     * @param idProceso
     * @param comparendoDTO
     * @throws CirculemosNegocioException
     */
    public Long registrarFalloImpugnacion(FalloImpugnacionDTO fallo, ComparendoDTO comparendoDTO, Long idProceso)
            throws CirculemosNegocioException;

    /**
     * Realiza la invocacion con el WebService para realizar el registro del numero de coactivo de axis
     * 
     * @param coactivoDTO
     * @return CoactivoDTO
     * @throws CirculemosNegocioException
     * @author Jeison.Rodriguez (2016-09-21)
     */
    public CoactivoDTO registarCoactivo(CoactivoDTO coactivoDTO) throws CirculemosNegocioException;
}
