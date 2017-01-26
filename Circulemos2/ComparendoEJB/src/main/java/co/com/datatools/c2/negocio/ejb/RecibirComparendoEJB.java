package co.com.datatools.c2.negocio.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaRecibirComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.enumeracion.EnumOrigenNotificacionTercero;
import co.com.datatools.c2.enumeracion.EnumPais;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumTipoOrigenComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.ErrorComparendo;
import co.com.datatools.c2.negocio.interfaces.ILRecibirComparendo;
import co.com.datatools.c2.negocio.interfaces.IRRecibirComparendo;
import co.com.datatools.c2.negocio.local.interfaces.IProcesarRecibirComparendo;
import co.com.datatools.c2.negocio.local.interfaces.IRecibirComparendoColombia;
import co.com.datatools.c2.negocio.local.interfaces.IRecibirComparendoEcuador;
import co.com.datatools.c2.negocio.local.interfaces.IRecibirComparendoPeru;

/**
 * Logica asociada al proceso de recibir y procesar comparendo
 * 
 * @author giovanni.velandia
 * @version 28-Mar-2016 2.21
 */
@Stateless(name = "RecibirComparendoEJB")
@LocalBean
public class RecibirComparendoEJB implements IRRecibirComparendo, ILRecibirComparendo {
    private final static Logger logger = Logger.getLogger(RecibirComparendoEJB.class.getName());

    @EJB
    private IProcesarRecibirComparendo iProcesarComparendo;
    @EJB
    private IRecibirComparendoColombia iRecibirComparendoColombia;
    @EJB
    private IRecibirComparendoPeru iRecibirComparendoPeru;
    @EJB
    private IRecibirComparendoEcuador iRecibirComparendoEcuador;

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaValidacionDTO recibirComparendo(ProcesarComparendoDTO procesarComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::recibirComparendo(ProcesarComparendoDTO)");

        if (procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito() == null) {
            throw new CirculemosNegocioException(ErrorComparendo.RecibirComparendo.COM_002001);
        } else {
            if (procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo() == null) {
                throw new CirculemosNegocioException(ErrorComparendo.RecibirComparendo.COM_002001);
            }
        }

        OrganismoTransitoDTO organismoTransitoDTO = iProcesarComparendo.consultarOrganismoTransito(
                procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo());
        if (organismoTransitoDTO == null) {
            throw new CirculemosNegocioException(ErrorComparendo.RecibirComparendo.COM_002003);
        }

        // Fecha Infraccion

        if (procesarComparendoDTO.getProcesaComparendoDTO().getFechaInfraccion() == null) {
            throw new CirculemosNegocioException(ErrorComparendo.RecibirComparendo.COM_002004);
        }
        // Hora Infraccion
        if (procesarComparendoDTO.getProcesaComparendoDTO().getHoraInfraccion() == null) {
            throw new CirculemosNegocioException(ErrorComparendo.RecibirComparendo.COM_002005);
        }

        // Codigo infraccion
        if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoInfraccion() == null) {
            throw new CirculemosNegocioException(ErrorComparendo.RecibirComparendo.COM_002006);
        }

        // Valida que el pais al cual se esta ingresando el comparedno
        ValorParametroDTO valorParametroDTO = iProcesarComparendo.consultarValorParametro(
                EnumParametro.PAIS_INSTALACION,
                procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo());

        boolean notifica = false;
        RespuestaRecibirComparendoDTO respuestaRecibirComparendoDTO = null;
        // Colombia
        if (valorParametroDTO.getValorParamInt() == EnumPais.COLOMBIA.getId()) {

            // Se encarga de realizar el flujo de Negocio
            respuestaRecibirComparendoDTO = iRecibirComparendoColombia.procesarRecibirComparendo(procesarComparendoDTO);
            notifica = true;
        }
        // Peru
        if (valorParametroDTO.getValorParamInt() == EnumPais.PERU.getId()) {

            // Se encarga de realizar el flujo de Negocio
            respuestaRecibirComparendoDTO = iRecibirComparendoPeru.procesarRecibirComparendo(procesarComparendoDTO);
            notifica = false;
        }
        // Ecuador
        if (valorParametroDTO.getValorParamInt() == EnumPais.ECUADOR.getId()) {

            // Este campo se ingresa solo para ecuador ya que solo hay un origen de datos para comparendo en los otros casos este campo debe ser
            // diligenciado desde el ingreso de los datos
            procesarComparendoDTO.getProcesaComparendoDTO().setCodigoOrigen(EnumTipoOrigenComparendo.SIMUR.getCodigo());

            // Se encarga de realizar el flujo de Negocio
            respuestaRecibirComparendoDTO = iRecibirComparendoEcuador.procesarRecibirComparendo(procesarComparendoDTO);
            notifica = false;
        }

        if (notifica) {
            if (respuestaRecibirComparendoDTO.getCodigoResultado()
                    .equals(EnumErrorProcesamiento.REGISTRADO.getCodigo())) {
                // Notifica el comparendo a terceros definidos.
                iProcesarComparendo.notificarComparendoTerceros(respuestaRecibirComparendoDTO.getCicompareno(),
                        respuestaRecibirComparendoDTO.getCodigoOrganismoTransito(),
                        EnumOrigenNotificacionTercero.REGISTRAR);
            }
        }
        return respuestaRecibirComparendoDTO;
    }

    @Override
    public boolean comparendoIngresado(String numeroComparendo, Integer codigoOrganismo) {
        return iProcesarComparendo.comparendoIngresado(numeroComparendo, codigoOrganismo);
    }

    @Override
    public RespuestaValidacionDTO validarRectificaComparendo(Long cicomparendo) {
        return iProcesarComparendo.validarRectificaComparendo(cicomparendo);
    }

    @Override
    public RespuestaValidacionDTO rectificarComparendo(ProcesaComparendoRectificadoDTO comparendoRectificadoDTO)
            throws CirculemosNegocioException {
        return iProcesarComparendo.rectificarComparendo(comparendoRectificadoDTO);
    }
}