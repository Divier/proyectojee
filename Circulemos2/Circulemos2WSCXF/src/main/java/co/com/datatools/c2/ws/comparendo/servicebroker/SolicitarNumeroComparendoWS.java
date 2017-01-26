package co.com.datatools.c2.ws.comparendo.servicebroker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SolicitudNumeroComparendoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.ws.RespuestaSolicitudNumeroComparendoWSDTO;
import co.com.datatools.c2.dto.ws.SolicitudNumeroComparendoWSDTO;
import co.com.datatools.c2.enumeracion.EnumEstadoTransaccionLog;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumRespuestaWebServices;
import co.com.datatools.c2.enumeracion.EnumWebService;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.error.ErrorComparendo.ObtenerOCN;
import co.com.datatools.c2.negocio.interfaces.ILAdministracion;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionWebService;
import co.com.datatools.c2.negocio.interfaces.ILComparendo;
import co.com.datatools.c2.negocio.interfaces.ILParametro;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.ILCatalogo;
import co.com.datatools.c2.ws.comparendo.helper.SolicitarOCNHelper;
import co.com.datatools.c2.ws.comparendo.utilidades.LogSolicitarNumeroComparendo;

/**
 * EJB encargado de procesar la peticion del WS de solicitar OCN <b>COM_001</b>
 * 
 * @author diego.fonseca
 * 
 */
@Stateless(name = "SolicitarNumeroComparendoWS")
@LocalBean
public class SolicitarNumeroComparendoWS implements ILSolicitarNumeroComparendoWS {

    @EJB
    ILComparendo comparendoEJB;
    @EJB
    ILAdministracion administracionEJB;
    @EJB
    ILParametro parametroEJB;
    @EJB
    ILCatalogo catalogoEJB;
    @EJB
    ILAdministracionWebService administracionWebServiceEJB;
    @EJB
    private ILSeguridadCirculemos seguridadCirculemosEJB;

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private static final ILog logger = LoggerC2.getLogger(EnumLogSistema.SOLICITUD_OCN);
    private static final Logger loggerServer = Logger.getLogger(SolicitarNumeroComparendoWS.class.getName());

    @Override
    public RespuestaSolicitudNumeroComparendoWSDTO solicitarNumeroComparendo(
            SolicitudNumeroComparendoWSDTO solicitudNumeroComparendoWSDTO) {
        loggerServer.debug("SolicitarNumeroComparendoWS:solicitarNumeroComparendo(SolicitudNumeroCOmparendoWSDTO)");
        final String CATALOGO_TIPO_RESPONSABLE_FORMULARIO = "TipoResponsableFormulario";
        final String CATALOGO_TIPO_IDENTIFICACION_PERSONA = "TipoIdentificacionPersona";
        LogSolicitarNumeroComparendo logSolicitarNumeroComparendo = new LogSolicitarNumeroComparendo();
        RespuestaSolicitudNumeroComparendoWSDTO respuestaWSDTO = new RespuestaSolicitudNumeroComparendoWSDTO();

        String login = seguridadCirculemosEJB.obtenerUsuarioDto().getLogin();

        List<RespuestaWebServiceDTO> respuestasWebServiceList = administracionWebServiceEJB
                .consultarRespuestasWebService(EnumWebService.SOLICITAR_OCN.getValue());

        Map<Integer, List<String>> respuestaWebServiceMap = new HashMap<>();

        for (RespuestaWebServiceDTO auxResp : respuestasWebServiceList) {
            List<String> codigoRespuesta = new ArrayList<>();
            codigoRespuesta.add(auxResp.getCodigo());
            codigoRespuesta.add(auxResp.getDescripcion());
            respuestaWebServiceMap.put(auxResp.getId(), codigoRespuesta);
        }

        List<ItemCatalogoDTO> tipoResponsableList = catalogoEJB.consultarItemsCatalogo(
                CATALOGO_TIPO_RESPONSABLE_FORMULARIO, null);

        Integer tipoResponsableFormulario = null;

        for (ItemCatalogoDTO item : tipoResponsableList)
            if (item.getCodigo().equals(solicitudNumeroComparendoWSDTO.getTipoResponsable()))
                tipoResponsableFormulario = item.getId();

        if (tipoResponsableFormulario == null) {
            respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                    EnumRespuestaWebServices.TIPO_RESPONSABLE_INVALIDO.getValue()).get(0));
            respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                    EnumRespuestaWebServices.TIPO_RESPONSABLE_INVALIDO.getValue()).get(1));
            logSolicitarNumeroComparendo.setEstadoTransaccion(EnumEstadoTransaccionLog.FALLIDA.toString());
            logSolicitarNumeroComparendo.setOrganismoTransito(solicitudNumeroComparendoWSDTO.getCodigoOrganismo());// Organismo de transito lo obtengo
                                                                                                                   // o pongo el que llega
            logger.escribir(login, logSolicitarNumeroComparendo);

            return respuestaWSDTO;
        }

        List<ItemCatalogoDTO> tipoIdentificacionList = catalogoEJB.consultarItemsCatalogo(
                CATALOGO_TIPO_IDENTIFICACION_PERSONA, null);

        Integer tipoDocumento = null;

        for (ItemCatalogoDTO item : tipoIdentificacionList)
            if (item.getCodigo().equals(solicitudNumeroComparendoWSDTO.getTipoDocumento()))
                tipoDocumento = item.getId();

        if (tipoDocumento == null) {
            respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                    EnumRespuestaWebServices.TIPO_DOCUMENTO_INVALIDO.getValue()).get(0));
            respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                    EnumRespuestaWebServices.TIPO_DOCUMENTO_INVALIDO.getValue()).get(1));

            logSolicitarNumeroComparendo.setEstadoTransaccion(EnumEstadoTransaccionLog.FALLIDA.toString());
            logSolicitarNumeroComparendo.setOrganismoTransito(solicitudNumeroComparendoWSDTO.getCodigoOrganismo());// Organismo de transito lo obtengo
                                                                                                                   // o pongo el que llega
            logger.escribir(login, logSolicitarNumeroComparendo);

            return respuestaWSDTO;
        }

        SolicitudNumeroComparendoDTO solicitudNumeroComparendoDTO = SolicitarOCNHelper
                .toSolicitudNumeroComparendoDTO(solicitudNumeroComparendoWSDTO);
        solicitudNumeroComparendoDTO.setTipoDocumento(tipoDocumento);
        solicitudNumeroComparendoDTO.setTipoResponsable(tipoResponsableFormulario);

        RespuestaSolicitudNumeroComparendoDTO respuestaSolicitud = null;
        try {
            respuestaSolicitud = comparendoEJB.solicitarNumeroComparendo(solicitudNumeroComparendoDTO);
            respuestaWSDTO = SolicitarOCNHelper.tolevel1DTO(respuestaSolicitud,
                    solicitudNumeroComparendoWSDTO.getIdentificadorVehiculo());

            respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(EnumRespuestaWebServices.SOLICITUD_EXITOSA.getValue())
                    .get(0));
            respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                    EnumRespuestaWebServices.SOLICITUD_EXITOSA.getValue()).get(1));
        } catch (CirculemosNegocioException e) {

            ObtenerOCN ocn = (ObtenerOCN) e.getErrorInfo();

            switch (ocn) {

            case COM_050001:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.PLACA_AGENTE_INVALIDA.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.PLACA_AGENTE_INVALIDA.getValue()).get(1));
                break;
            case COM_050002:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.RESPONSABLE_NO_EXISTE.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.RESPONSABLE_NO_EXISTE.getValue()).get(1));
                break;
            case COM_050006:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.FALTA_TIPO_DOCUMENTO_RESPONSABLE.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.FALTA_TIPO_DOCUMENTO_RESPONSABLE.getValue()).get(1));
                break;
            case COM_050008:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.VEHICULO_SIN_IDENTIFICAR.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.VEHICULO_SIN_IDENTIFICAR.getValue()).get(1));
                break;
            case COM_050009:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.FECHA_IMPOSICION_INVALIDA.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.FECHA_IMPOSICION_INVALIDA.getValue()).get(1));
                break;
            case COM_050010:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.HORA_IMPOSICION_INVALIDA.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.HORA_IMPOSICION_INVALIDA.getValue()).get(1));
                break;
            case COM_050012:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.COMPARENDOS_NO_DISPONIBLES.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.COMPARENDOS_NO_DISPONIBLES.getValue()).get(1));
                break;
            case COM_050013:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap
                        .get(EnumRespuestaWebServices.AGENTE_INVALIDO.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.AGENTE_INVALIDO.getValue()).get(1));
                break;
            case COM_050014:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.IDENTIFICACION_VEHICULO_IMPRECISO.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.IDENTIFICACION_VEHICULO_IMPRECISO.getValue()).get(1));
                break;
            case COM_050015:
                respuestaWSDTO.setCodigo(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.ORGANISMO_TRANSITO_INVALIDO.getValue()).get(0));
                respuestaWSDTO.setDescripcion(respuestaWebServiceMap.get(
                        EnumRespuestaWebServices.ORGANISMO_TRANSITO_INVALIDO.getValue()).get(1));
                break;
            }

        }
        return respuestaWSDTO;
    }
}
