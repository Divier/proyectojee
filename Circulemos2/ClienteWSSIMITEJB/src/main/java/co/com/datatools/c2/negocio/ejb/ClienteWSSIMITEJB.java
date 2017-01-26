package co.com.datatools.c2.negocio.ejb;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.axis.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.clientes.ws.simit.carga.Exception_Exception;
import co.com.datatools.c2.clientes.ws.simit.carga.WsCargaArchivos;
import co.com.datatools.c2.clientes.ws.simit.carga.WsCargaArchivosPortType;
import co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosComparendo;
import co.com.datatools.c2.clientes.ws.simit.carga.dto.ObjectFactory;
import co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductor;
import co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorLocator;
import co.com.datatools.c2.clientes.ws.simit.historialconductor.WSHistorialConductorSoapBindingStub;
import co.com.datatools.c2.dto.AutenticacionWebServiceDTO;
import co.com.datatools.c2.dto.HomologacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.WebServiceDTO;
import co.com.datatools.c2.dto.common.RespuestaConsultaComparendoDTO;
import co.com.datatools.c2.dto.common.RespuestaNotificacionDTO;
import co.com.datatools.c2.enumeracion.EnumRespuestaWS;
import co.com.datatools.c2.enumeracion.EnumRespuestaWebServices;
import co.com.datatools.c2.enumeracion.EnumWebService;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.interfaces.ILClienteWSSIMIT;
import co.com.datatools.c2.negocio.interfaces.IRClienteWSSIMIT;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.simit.ws.cliente.helpers.ClienteWSSIMITUtil;

/**
 * Implementacion de logica de metodos contra las conexiones de WS de SIMIT
 * 
 * @author luis.forero(2016-03-29)
 * 
 */
@Stateless(name = "ClienteWSSIMITEJB")
@LocalBean
public class ClienteWSSIMITEJB implements IRClienteWSSIMIT, ILClienteWSSIMIT {

    /**
     * Cantidad de caracteres que contiene el campo de secretaria.
     */
    private static final int CANTIDAD_CARACTERES_CODIGO_ORG = 8;

    /**
     * Caracter con el que se completa el numero de caracteres de la secretaria de transito
     */
    private static final String CARACTER_RELLENO = "0";

    private final static Logger logger = Logger.getLogger(ClienteWSSIMITEJB.class.getName());

    private static List<RespuestaWebServiceDTO> lstRespuestasNotificacionSIMIT;

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneralEJB;

    @EJB
    private IRSeguridadCirculemos seguridadCirculemosEJB;

    @Override
    public RespuestaNotificacionDTO notificarComparendoSIMIT(HomologacionComparendoSIMITDTO comparendo) {
        logger.debug(ClienteWSSIMITEJB.class.getName().concat(
                "::notificarComparendoSIMIT(HomologacionComparendoSIMITDTO)"));
        // Se lleva a cabo la consulta de los datos del web service para llevar a cabo la conexion
        WebServiceDTO webService = fachadaAdminGeneralEJB.consultarWebService(EnumWebService.NOTIFICAR_COMPARENDO_SIMIT
                .getValue());
        if (webService == null) {
            return newRespuestaNotificacionSIMIT(EnumRespuestaWebServices.FALTAN_DATOS_CONEXION,
                    EnumRespuestaWS.PROB_COM);
        }

        WsCargaArchivosPortType port = null;
        try {
            String url = null;
            String END_POINT = null;

            if (StringUtils.isNotBlank(webService.getUrlPrimaria())) {
                url = webService.getUrlPrimaria();
                END_POINT = "11";
            } else if (StringUtils.isNotBlank(webService.getUrlPrimaria())) {
                url = webService.getUrlSecundaria();
                END_POINT = "12";
            } else {
                return newRespuestaNotificacionSIMIT(EnumRespuestaWebServices.FALTAN_DATOS_CONEXION,
                        EnumRespuestaWS.PROB_COM);
            }
            URL wsdlLocation = new URL(url);

            WsCargaArchivos ws = new WsCargaArchivos(wsdlLocation);
            switch (END_POINT) {
            case "11":
                port = ws.getWsCargaArchivosHttpSoap11Endpoint();
                break;
            case "12":
                port = ws.getWsCargaArchivosHttpSoap12Endpoint();
                break;
            default:
                break;
            }
            if (port == null) {
                return newRespuestaNotificacionSIMIT(EnumRespuestaWebServices.PROBLEMAS_CONEXION,
                        EnumRespuestaWS.PROB_COM);
            }
            // Se lleva a cabo transformacion de los datos a ser enviados al WS de Carga de Comparendos SIMIT
            DatosComparendo comparendoSIMITDTO = ClienteWSSIMITUtil
                    .homologacionCompDTOToDatosComparendoSIMITDTO(comparendo);

            // Se cargan datos del usuario del webservice
            ObjectFactory objectFactory = new ObjectFactory();
            // Se obtiene el usuario para el organismo de transito sobre el cual el esta autenticado.
            Integer codigoOrganismo = seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo();
            AutenticacionWebServiceDTO autenticacionWebServiceDTO = getUsuarioAutenticarOrganismo(
                    webService.getAutenticacionWebServices(), codigoOrganismo);
            if (autenticacionWebServiceDTO != null) {
                comparendoSIMITDTO.setUsuario(objectFactory.createDatosComparendoSecretaria(autenticacionWebServiceDTO
                        .getUsuario()));
                comparendoSIMITDTO.setClave(objectFactory.createDatosComparendoClave(autenticacionWebServiceDTO
                        .getClave()));
                comparendoSIMITDTO.setSecretaria(objectFactory.createDatosResolucionSecretaria(StringUtils.rightPad(
                        String.valueOf(codigoOrganismo), CANTIDAD_CARACTERES_CODIGO_ORG, CARACTER_RELLENO)));
            } else {
                return newRespuestaNotificacionSIMIT(EnumRespuestaWebServices.FALTAN_DATOS_AUTENTICACION,
                        EnumRespuestaWS.PROB_COM);
            }

            String cargaArchivoComparendos = port.cargaArchivoComparendos(comparendoSIMITDTO);

            if (cargaArchivoComparendos.equals("<idTipoError>")) {
                return newRespuestaNotificacionSIMIT(EnumRespuestaWebServices.NOTIFICACION_NO_EXITOSA,
                        EnumRespuestaWS.ERROR, cargaArchivoComparendos);
            } else {
                return newRespuestaNotificacionSIMIT(EnumRespuestaWebServices.NOTIFICACION_EXITOSA,
                        EnumRespuestaWS.SATISFACTORIO, cargaArchivoComparendos);
            }

        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            return newRespuestaNotificacionSIMIT(EnumRespuestaWebServices.PROBLEMAS_URL_CONEXION,
                    EnumRespuestaWS.PROB_COM);
        } catch (Exception_Exception e) {
            logger.error(e.getMessage());
            return newRespuestaNotificacionSIMIT(EnumRespuestaWebServices.PROBLEMAS_WEB_SERVICE, EnumRespuestaWS.ERROR,
                    e.getMessage());
        }
    }

    @Override
    public RespuestaConsultaComparendoDTO consultarComparendos(Integer idTipoIdentificacion, String numeroIdentificacion) {
        logger.debug(ClienteWSSIMITEJB.class.getName().concat("::consultarComparendos(Integer, String)"));
        // Se lleva a cabo la consulta de los datos del web service para llevar a cabo la conexion
        WebServiceDTO webService = fachadaAdminGeneralEJB.consultarWebService(EnumWebService.CONSULTAR_REINCIDENCIAS
                .getValue());
        if (webService == null) {
            // TODO error en datos de web Services
            return null;
        }
        RespuestaConsultaComparendoDTO rstCnsltaCompDTO = null;
        WSHistorialConductor locator = new WSHistorialConductorLocator();
        try {
            String address = webService.getUrlPrimaria();
            if (address == null) {
                address = webService.getUrlSecundaria();
            }
            ((WSHistorialConductorLocator) locator).setWSHistorialConductorEndpointAddress(address);
            WSHistorialConductorSoapBindingStub srvStub = new WSHistorialConductorSoapBindingStub(new URL(address),
                    locator);
            if (srvStub == null) {
                return null;
            }

            // Se obtiene el usuario para el organismo de transito sobre el cual el esta autenticado.
            Integer codigoOrganismo = seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo();
            AutenticacionWebServiceDTO autenticacionWebServiceDTO = getUsuarioAutenticarOrganismo(
                    webService.getAutenticacionWebServices(), codigoOrganismo);
            String idFuncionario = null;
            String clave = null;
            if (autenticacionWebServiceDTO != null) {
                idFuncionario = autenticacionWebServiceDTO.getUsuario();
                clave = autenticacionWebServiceDTO.getClave();
            }
            if (StringUtils.isBlank(idFuncionario) || StringUtils.isBlank(clave)) {
                // TODO RESPUESTA DE FALTA DE DATOS DEL USUARIO DE CONEXION
                return null;
            }

            String historialConductor = srvStub.getHistorialConductor(idFuncionario, clave, StringUtils.rightPad(
                    String.valueOf(codigoOrganismo), CANTIDAD_CARACTERES_CODIGO_ORG, CARACTER_RELLENO),
                    idTipoIdentificacion, numeroIdentificacion);

            rstCnsltaCompDTO = new RespuestaConsultaComparendoDTO();
            try {
                // TODO Tratamiento de WebServiceClient
                if (historialConductor.contains("<error>") && historialConductor.contains("</error>")) {
                    // TODO
                    return null;
                }
                ClienteWSSIMITUtil.responseToRespuesataConsultaComparendoDTO(rstCnsltaCompDTO, historialConductor);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                return null;
            }

        } catch (AxisFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // TODO
        return rstCnsltaCompDTO;
    }

    /**
     * Obtiene el usuario utilizado para el organismo de transito en el que se encuentra autenticado
     * 
     * @param autenticacionWebServices
     *            listado de posibles datos de autenticacion del webservice de SIMIT
     * @param codigoOrganismo
     *            codigo del organismo sobre el que se encuentra autenticado
     * @return retorna datos de autenticacion del web service a ser utilizados, null en caso de no encontrarlos
     * @author luis.forero(2016-04-07)
     */
    private AutenticacionWebServiceDTO getUsuarioAutenticarOrganismo(
            List<AutenticacionWebServiceDTO> autenticacionWebServices, Integer codigoOrganismo) {
        for (AutenticacionWebServiceDTO autenticacionWebServiceDTO : autenticacionWebServices) {
            if (autenticacionWebServiceDTO.getOrganismoTransito().getCodigoOrganismo().equals(codigoOrganismo)) {
                return autenticacionWebServiceDTO;
            }
        }
        return null;
    }

    /**
     * Construye insntancia de la respuesta procesada.
     * 
     * @param respuestaWebServices
     *            respuesta de web service a cololocar.
     * @param respuestaWS
     *            respuesta presentada
     * @return respuesta con sus respectivos valores
     * @author luis.forero(2016-04-08)
     */
    private RespuestaNotificacionDTO newRespuestaNotificacionSIMIT(EnumRespuestaWebServices respuestaWebServices,
            EnumRespuestaWS respuestaWS) {
        return newRespuestaNotificacionSIMIT(respuestaWebServices, respuestaWS, null);
    }

    /**
     * Construye insntancia de la respuesta procesada.
     * 
     * @param respuestaWebServices
     *            respuesta de web service a cololocar.
     * @param respuestaWS
     *            respuesta presentada
     * @param detalle
     *            detalle del mensaje recibido a marcar
     * @return respuesta con sus respectivos valores
     * @author luis.forero(2016-04-08)
     */
    private RespuestaNotificacionDTO newRespuestaNotificacionSIMIT(EnumRespuestaWebServices respuestaWebServices,
            EnumRespuestaWS respuestaWS, String detalle) {
        RespuestaNotificacionDTO respuestaNotificacionSIMIT = new RespuestaNotificacionDTO();
        RespuestaWebServiceDTO rstWS = consultarRespuestaWS(respuestaWebServices);
        respuestaNotificacionSIMIT.setCodigoRespuesta(rstWS.getCodigo());
        respuestaNotificacionSIMIT.setMensaje(rstWS.getNombre());
        respuestaNotificacionSIMIT.setDetalle(detalle);
        respuestaNotificacionSIMIT.setResultado(respuestaWS);
        return respuestaNotificacionSIMIT;
    }

    /**
     * Construye insntancia de la respuesta procesada.
     * 
     * @param respuestaWebServices
     *            respuesta de web service a cololocar.
     * @param respuestaWS
     *            respuesta presentada
     * @return respuesta con sus respectivos valores
     * @author luis.forero(2016-04-19)
     */
    private RespuestaConsultaComparendoDTO newRespuestaConsultaComparendoDTO(
            EnumRespuestaWebServices respuestaWebServices, EnumRespuestaWS respuestaWS) {
        return newRespuestaConsultaComparendoDTO(respuestaWebServices, respuestaWS, null);
    }

    /**
     * Construye insntancia de la respuesta procesada.
     * 
     * @param respuestaWebServices
     *            respuesta de web service a cololocar.
     * @param respuestaWS
     *            respuesta presentada
     * @param detalle
     *            detalle del mensaje recibido a marcar
     * @return respuesta con sus respectivos valores
     * @author luis.forero(2016-04-19)
     */
    private RespuestaConsultaComparendoDTO newRespuestaConsultaComparendoDTO(
            EnumRespuestaWebServices respuestaWebServices, EnumRespuestaWS respuestaWS, String detalle) {
        RespuestaConsultaComparendoDTO respuestaNotificacionSIMIT = new RespuestaConsultaComparendoDTO();
        RespuestaWebServiceDTO rstWS = consultarRespuestaWS(respuestaWebServices);
        respuestaNotificacionSIMIT.setCodigoRespuesta(rstWS.getCodigo());
        respuestaNotificacionSIMIT.setMensaje(rstWS.getNombre());
        respuestaNotificacionSIMIT.setDetalle(detalle);
        respuestaNotificacionSIMIT.setResultado(respuestaWS);
        return respuestaNotificacionSIMIT;
    }

    /**
     * Consulta las respuestas del web service de notificacion comparendo SIMIT y obtiene una determinada del listado
     * 
     * @param respuestaWebServices
     *            enumeracion de entrada para consultar la respuesta deseada
     * @return respuesta definida en la enumeracion junto con sus datos correspondientes, null en caso de no encontrarlo
     * @author luis.forero(2016-04-08)
     */
    private RespuestaWebServiceDTO consultarRespuestaWS(EnumRespuestaWebServices respuestaWebServices) {
        if (lstRespuestasNotificacionSIMIT == null) {
            lstRespuestasNotificacionSIMIT = fachadaAdminGeneralEJB
                    .consultarRespuestasWebService(EnumWebService.NOTIFICAR_COMPARENDO_SIMIT.getValue());
        }
        for (RespuestaWebServiceDTO respuestaWebServiceDTO : lstRespuestasNotificacionSIMIT) {
            if (respuestaWebServiceDTO.getId().equals(respuestaWebServices.getValue()))
                return respuestaWebServiceDTO;
        }
        return null;
    }

}
