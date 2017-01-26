package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConsultarNotificacionesDTO;
import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.ImpulsoPruebaDTO;
import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.ProrrogaPruebaDTO;
import co.com.datatools.c2.dto.PruebaDTO;
import co.com.datatools.c2.dto.RegistrarPruebaDTO;
import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;
import co.com.datatools.c2.dto.TipoDocumentoProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.entidades.ImpulsoPrueba;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.entidades.ProrrogaPrueba;
import co.com.datatools.c2.entidades.Prueba;
import co.com.datatools.c2.entidades.SolicitudPruebasBack;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoNotificacion;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumOrigenPrueba;
import co.com.datatools.c2.enumeraciones.EnumTipoDestinoPruebaImpug;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoGenerado;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorImpugnacion;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.fachada.IRFachadaProceso;
import co.com.datatools.c2.negocio.helpers.ImpulsoPruebaHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.PruebaHelper;
import co.com.datatools.c2.negocio.helpers.SolicitudPruebasBackHelper;
import co.com.datatools.c2.negocio.helpers.extencion.SolicitudPruebasBackHelperExtend;
import co.com.datatools.c2.negocio.helpers.v2.LogEnvioCorreoHelper;
import co.com.datatools.c2.negocio.interfaces.ILPruebas;
import co.com.datatools.c2.negocio.interfaces.IRConsecutivo;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRFachadaNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRPruebas;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.negocio.opciones.OpcionGestorFileSystem;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

@Stateless(name = "PruebasEJB")
@LocalBean
public class PruebasEJB implements ILPruebas, IRPruebas {

    private final static Logger logger = Logger.getLogger(PruebasEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaProceso iRFachadaProceso;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    private IRRepositorioArchivo iRRepositorioArchivo;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneral;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRCirculemosMailSender iRCirculemosMailSender;

    @EJB
    private IRFachadaNotificacionTerceros iRFachadaNotificaciones;

    @EJB
    private IRFachadaAdminNegocio fachadaAdminNegocio;

    @EJB
    private IRConsecutivo iRConsecutivo;

    @EJB
    private ILPruebas iLPruebas;

    private final String NOMBRE_ARCHIVO_CIERRE_PRUEBAS = "Cierre_pruebas.pdf";
    private int DIA_CALENDARIO = 1;

    @Override
    public byte[] registrarSolicitudPrueba(SolicitudPruebasBackDTO solicitud, Long idProceso)
            throws CirculemosNegocioException {
        logger.debug("PruebasEJB::registrarSolicitudPrueba(SolicitudPruebasBackDTO)");
        boolean diaHabil = true;
        try {
            // Validar Datos
            checkNotNull(idProceso, ErrorImpugnacion.SolicitarPruebas.JUR_020003);
            if (solicitud.getFechaSolicitud() != null
                    && solicitud.getFechaSolicitud().before(UtilFecha.currentZeroTimeDate())) {
                throw new CirculemosNegocioException(ErrorImpugnacion.SolicitarPruebas.JUR_020004);
            }
            // Calcular fecha limite
            OrganismoTransitoDTO organismoTransito = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
            ValorParametroDTO diasEsperaPruebas = fachadaAdminGeneral.consultarValorParametro(
                    EnumParametro.DIAS_HABILES_ESPERA_PRUEBAS, organismoTransito.getCodigoOrganismo(), true);
            // checkNotNull(solicitud.getFechaSolicitud(), ErrorImpugnacion.SolicitarPruebas.JUR_020002);

            TrazabilidadProcesoDTO trazabilidadDTO = new TrazabilidadProcesoDTO();

            ProcesoDTO procesoDTO = new ProcesoDTO();
            procesoDTO.setId(idProceso);

            EstadoProcesoDTO estadoProcDTO = new EstadoProcesoDTO();
            estadoProcDTO.setId(EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION.getValue());

            trazabilidadDTO.setProceso(procesoDTO);
            trazabilidadDTO.setEstadoProceso(estadoProcDTO);

            List<TrazabilidadProcesoDTO> trazabilidadList = iRFachadaProceso.consultarTrazabilidad(trazabilidadDTO);

            if (trazabilidadList == null || trazabilidadList.size() == 0) {
                throw new CirculemosNegocioException(ErrorImpugnacion.SolicitarPruebas.JUR_020005);
            }

            TrazabilidadProcesoDTO trazabilidadEvaluado = trazabilidadList.get(0);

            // Se valida el tipo de dia con el que se calcula la fecha limite de pruebas segun parametro
            ValorParametroDTO tipoDia = fachadaAdminGeneral.consultarValorParametro(EnumParametro.TIPO_DIA,
                    organismoTransito.getCodigoOrganismo(), true);
            if (tipoDia.getValorParamInt() == DIA_CALENDARIO) {
                diaHabil = false;
            }
            Date fechaLimite = fachadaAdminGeneral.sumarDias(organismoTransito.getCodigoOrganismo(),
                    trazabilidadEvaluado.getFechaInicio(), diasEsperaPruebas.getValorParamInt(), diaHabil);
            solicitud.setFechaLimite(fechaLimite);

            // Generar Traza
            TrazabilidadProcesoDTO traza = iRFachadaProceso.actualizarEstadoProceso(idProceso,
                    EnumEstadoProceso.ECUADOR_IMPUGNACION_SOLICITUD_PRUEBAS, false);

            // Guardar Solicitud
            solicitud.setTrazabilidadProceso(traza);
            SolicitudPruebasBack sol = SolicitudPruebasBackHelper.toLevel1Entity(solicitud, null);
            em.persist(sol);
            em.flush();
            solicitud.setId(sol.getId());

            // Genera documento
            GeneraDocumentoDTO generaSolicitud = new GeneraDocumentoDTO();
            generaSolicitud.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
            Object[] valoresParametros = { idProceso, sol.getId() };
            generaSolicitud.setValoresParametros(valoresParametros);

            String consecutivo = null;
            if (!solicitud.getTipoDestinoPruebaImpug().getId().equals(EnumTipoDestinoPruebaImpug.OTRAS.getValue())) {
                generaSolicitud.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.SOLICITUD_PRUEBAS);
                consecutivo = iRConsecutivo.generarConsecutivo(
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                        EnumConsecutivo.NUMERO_MEMORANDO_SOLIC_PRUEBAS);
            } else {
                generaSolicitud.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.OFICIO_SOLICITUD_PRUEBAS);
                consecutivo = iRConsecutivo.generarConsecutivo(
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                        EnumConsecutivo.NUMERO_OFICIO_SOLIC_PRUEBAS);
            }
            consecutivo = StringUtils.leftPad(String.valueOf(consecutivo), 4, "0");
            sol.setConsecutivoDocumento(consecutivo);
            em.merge(sol);
            em.flush();
            Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaSolicitud);
            // Guardar Documento
            DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
            documentoProceso.setNumeroDocumento(idDocumento);
            documentoProceso.setTrazabilidadProceso(traza);
            TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
            tipoDocumento.setId(EnumTipoDocumentoProceso.IMPUGNACION_SOLICITUD_PRUEBAS.getValue());
            documentoProceso.setTipoDocumento(tipoDocumento);
            iRFachadaProceso.registrarDocumento(documentoProceso);
            List<Long> lista = new ArrayList<Long>();
            lista.add(idDocumento);
            return iRDocumentosCirculemos.consultarDocumentosPDF(lista);
        } catch (CirculemosAlertaException e) {
            logger.error(ErrorImpugnacion.SolicitarPruebas.JUR_020001, e);
            throw new CirculemosNegocioException(ErrorImpugnacion.SolicitarPruebas.JUR_020001);
        }
    }

    @Override
    public void registrarProrrogaPrueba(ProrrogaPruebaDTO prorroga, Long idProceso) throws CirculemosNegocioException {
        logger.debug("PruebasEJB::registrarProrrogaPrueba(ProrrogaPruebaDTO)");

        // Debe validar que el proceso NO supere la cantidad de prorrogas solicitadas, este límite de solicitud de prorrogas está configurado en el
        // parámetro Número de veces permitidas para prórroga

        OrganismoTransitoDTO organismoTransitoDTO = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

        // Calculo de parametros
        ValorParametroDTO cantidadVecesProrroga = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.CANT_VECES_PERMITIR_PRORROGA, organismoTransitoDTO.getCodigoOrganismo(), true);
        ValorParametroDTO diasMaximoCierreImpugnacion = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.DIAS_MAX_CIERRE_PROCESO_IMPUGNA, organismoTransitoDTO.getCodigoOrganismo(), true);

        if (prorroga.getSolicitudPruebasBack() != null && prorroga.getSolicitudPruebasBack().getId() != null) {
            SolicitudPruebasBack solicitud = em.find(SolicitudPruebasBack.class,
                    prorroga.getSolicitudPruebasBack().getId());

            // cantidad de prorrogas solicitadas superan el parametro Número de veces permitidas para prórroga
            if (solicitud.getProrrogaPruebas() != null && !solicitud.getProrrogaPruebas().isEmpty()
                    && solicitud.getProrrogaPruebas().size() >= cantidadVecesProrroga.getValorParamInt()) {
                throw new CirculemosNegocioException(ErrorImpugnacion.GenerarProrroga.JUR_021001);
            }
            // Debe validar que la Fecha límite de entrega de pruebas sea mayor o igual a la Fecha actual.
            if (solicitud.getFechaLimite().before(UtilFecha.currentZeroTimeDate())) {
                throw new CirculemosNegocioException(ErrorImpugnacion.GenerarProrroga.JUR_021002);
            }
            // Debe validar que el origen de pruebas sea diferente a CIUDADANO
            if (solicitud.getOrigenPrueba().getId().equals(EnumOrigenPrueba.CIUDADANO.getValue())) {
                throw new CirculemosNegocioException(ErrorImpugnacion.GenerarProrroga.JUR_021003);
            }
            // Debe validar que la solicitud de pruebas no tenga ningún impulso Definitivo = SI acorde al campo Este impulso es definitivo.
            // Calcula los impulsos definitivos
            List<ImpulsoPrueba> impulsos = solicitud.getImpulsoPruebas();
            if (impulsos != null && !impulsos.isEmpty()) {
                for (ImpulsoPrueba impulso : impulsos) {
                    if (impulso.getDefinitivo()) {
                        throw new CirculemosNegocioException(ErrorImpugnacion.GenerarProrroga.JUR_021004);
                    }
                }
            }

            // Calcula el nuevo el valor Fecha límite de entrega de pruebas
            Date fechaLimiteEntregaPruebas = null;
            if (prorroga.getDiasProrroga() != null) {
                fechaLimiteEntregaPruebas = fachadaAdminGeneral.sumarDias(organismoTransitoDTO.getCodigoOrganismo(),
                        solicitud.getFechaLimite(), prorroga.getDiasProrroga(), true);

                // validar que la nueva "Fecha límite de entrega de pruebas" NO supere la fecha máxima para cerrar el proceso de Impugnación
                Date fechaMaximaParaCerrarProceso = null;
                ProcesoDTO proceso = new ProcesoDTO();
                proceso.setId(idProceso);

                List<ProcesoDTO> procesoDTOList = iRFachadaProceso.consultarProceso(proceso);
                if (procesoDTOList != null && !procesoDTOList.isEmpty()) {
                    fechaMaximaParaCerrarProceso = fachadaAdminGeneral.sumarDias(
                            organismoTransitoDTO.getCodigoOrganismo(), procesoDTOList.get(0).getFechaInicio(),
                            diasMaximoCierreImpugnacion.getValorParamInt(), true);

                    if (fechaLimiteEntregaPruebas.after(fechaMaximaParaCerrarProceso)) {
                        throw new CirculemosNegocioException(ErrorImpugnacion.GenerarProrroga.JUR_021005);
                    }

                    ProrrogaPrueba prorrogaPrueba = new ProrrogaPrueba();
                    prorrogaPrueba.setSolicitudPruebasBack(solicitud);
                    prorrogaPrueba
                            .setNumeroProrroga(prorroga.getSolicitudPruebasBack().getProrrogaPruebas().size() + 1);
                    prorrogaPrueba.setDiasProrroga(prorroga.getDiasProrroga());
                    prorrogaPrueba.setFechaProrroga(UtilFecha.currentZeroTimeDate());
                    em.persist(prorrogaPrueba);

                    solicitud.setFechaLimite(fechaLimiteEntregaPruebas);
                    em.merge(solicitud);
                } else {
                    throw new CirculemosNegocioException(ErrorImpugnacion.GenerarProrroga.JUR_021006);
                }
            } else {
                throw new CirculemosNegocioException(ErrorImpugnacion.GenerarProrroga.JUR_021007);
            }

        } else {
            throw new CirculemosNegocioException(ErrorImpugnacion.GenerarProrroga.JUR_021008);
        }

    }

    @Override
    public byte[] cerrarPruebas(Long idProceso, Boolean devolverDocumento) throws CirculemosNegocioException {
        logger.debug("PruebasEJB::cerrarPruebas(Long)");
        try {
            // Validacion del proceso
            checkNotNull(idProceso, "Proceso es obligatorio");
            EstadoProcesoDTO estadoProcesoDTO = iRFachadaProceso.consultarEstadoProceso(idProceso);
            Map<String, Object> variables = new HashMap<>();
            String[] aDireccionesDestino = null;

            if (!estadoProcesoDTO.getId().equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_SOLICITUD_PRUEBAS.getId())) {
                throw new CirculemosNegocioException(ErrorImpugnacion.CierrePruebas.JUR_016004);
            }

            // Validaciones
            // Consulta
            SolicitudPruebasBackDTO solicitudTmp = new SolicitudPruebasBackDTO();
            TrazabilidadProcesoDTO trazaTmp = new TrazabilidadProcesoDTO();
            ProcesoDTO procesoTmp = new ProcesoDTO();
            procesoTmp.setId(idProceso);

            Proceso proceso = em.find(Proceso.class, idProceso);
            variables.put("numero_proceso", escapeHtml4(proceso.getNumeroProceso()));
            ParticipanteProcesoDTO participante = new ParticipanteProcesoDTO();
            participante.setProceso(new ProcesoDTO(idProceso));
            List<ParticipanteProcesoDTO> participantes = iRFachadaProceso.consultarParticipantesProceso(participante);

            PersonaDTO persona = null;
            if (!participantes.isEmpty()) {
                List<String> correos = new ArrayList<>();
                for (ParticipanteProcesoDTO part : participantes) {
                    persona = part.getPersona();
                    List<CorreoPersonaDTO> lsCorreoPersona = fachadaAdminNegocio
                            .consultarCorreosNotificables(persona.getId());
                    for (CorreoPersonaDTO correoPersona : lsCorreoPersona) {
                        correos.add(correoPersona.getCorreoElectronico());
                    }
                }
                aDireccionesDestino = correos.toArray(new String[correos.size()]);
            }
            variables.put("organismo",
                    escapeHtml4(iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getNombreOrganismo()));
            trazaTmp.setProceso(procesoTmp);
            solicitudTmp.setTrazabilidadProceso(trazaTmp);
            List<SolicitudPruebasBackDTO> solicitudes = consultarSolicitudesProceso(solicitudTmp);
            Date fechaActual = new Date();

            if (!solicitudes.isEmpty()) {
                for (SolicitudPruebasBackDTO solicitud : solicitudes) {
                    // Valida la fecha limite
                    if (!solicitud.getFechaLimite().before(fechaActual)) {
                        throw new CirculemosNegocioException(ErrorImpugnacion.CierrePruebas.JUR_016002);
                    }
                }

                // Genera la traza
                TrazabilidadProcesoDTO trazabilidad = iRFachadaProceso.actualizarEstadoProceso(idProceso,
                        EnumEstadoProceso.ECUADOR_IMPUGNACION_CIERRE_PRUEBAS, false);

                // Genera documento
                GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
                generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
                generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.CIERRE_PRUEBAS);
                Object[] valoresParametros = { idProceso };
                generaDocumento.setValoresParametros(valoresParametros);
                Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);

                // Guarda el documento generado
                DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
                documentoProceso.setNumeroDocumento(idDocumento);
                documentoProceso.setTrazabilidadProceso(trazabilidad);
                TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
                tipoDocumento.setId(EnumTipoDocumentoProceso.IMPUGNACION_CIERRE_PRUEBAS.getValue());
                documentoProceso.setTipoDocumento(tipoDocumento);
                documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);

                ValorParametroDTO parametro = iRFachadaNotificaciones.consultarParametroEnvioNotificaciones(
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());

                // Adiciona el documento al DTO de pantalla
                ArrayList<ArchivoTransportableDTO> archivos = new ArrayList<ArchivoTransportableDTO>();
                ArchivoTransportableDTO archivo = null;
                if (idDocumento != null) {
                    ArrayList<Long> documentos = new ArrayList<Long>();
                    documentos.add(idDocumento);
                    archivo = new ArchivoTransportableDTO(NOMBRE_ARCHIVO_CIERRE_PRUEBAS,
                            iRDocumentosCirculemos.consultarDocumentosPDF(documentos));
                    archivo.setNumeroDocumento(String.valueOf(idDocumento));
                    archivos.add(archivo);
                }

                if (aDireccionesDestino != null && aDireccionesDestino.length > BigInteger.ZERO.intValue()) {

                    if (parametro != null && parametro.getValorParamBoolean()) {
                        EnvioNotificacionDTO envioNotificacion = new EnvioNotificacionDTO();
                        List<ConsultarNotificacionesDTO> lsNotificacion = new ArrayList<>();
                        ConsultarNotificacionesDTO notificacion = new ConsultarNotificacionesDTO();
                        notificacion.setLsCorreoElectronico(Arrays.asList(aDireccionesDestino));
                        notificacion.setNombreInfractor(persona.getNombreCompleto());
                        notificacion.setCodSeguimientoInt(idProceso);
                        notificacion.setExternalId(trazabilidad.getId());
                        notificacion.setLsArchivos(archivos);
                        lsNotificacion.add(notificacion);
                        envioNotificacion.setLsNotificaciones(lsNotificacion);
                        envioNotificacion.setTipoCorreo(EnumTipoCorreo.NOTIFICACION_CIERRE_PRUEBAS_ENOTIFICA);
                        envioNotificacion.setTipoNotificacion(EnumTipoNotificacion.NOTIFICACION_IMPUGNACIONES);
                        envioNotificacion.setVariablesMensaje(variables);
                        iRFachadaNotificaciones.enviaNotificaciones(envioNotificacion);
                    } else {
                        LogEnvioCorreoDTO log = iRCirculemosMailSender.enviarCorreo(
                                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                                EnumTipoCorreo.NOTIFICACION_CIERRE_PRUEBAS, aDireccionesDestino, variables, archivos);
                        // Actualizacion de auditoria de correo
                        log.setTablaSolicitud("trazabilidad_proceso");
                        log.setIdTablaSolicitud(solicitudTmp.getTrazabilidadProceso().getId());
                        em.merge(LogEnvioCorreoHelper.toLevel1Entity(log, null));
                    }

                    if (devolverDocumento) {
                        return archivo.getContenido();
                    }
                }
            } else {
                throw new CirculemosNegocioException(ErrorImpugnacion.CierrePruebas.JUR_016003);
            }
        } catch (CirculemosAlertaException e) {
            logger.error("Error al generar documento de cierre de pruebas", e);
            throw new CirculemosNegocioException(ErrorImpugnacion.CierrePruebas.JUR_016001);
        }
        return null;
    }

    @Override
    public List<SolicitudPruebasBackDTO> consultarSolicitudesProceso(SolicitudPruebasBackDTO criterios) {
        logger.debug("PruebasEJB::consultarSolicitudesProceso(ProcesoDTO)");

        List<SolicitudPruebasBackDTO> lsSolicitudPruebasDTO = new ArrayList<>();

        if (criterios != null) {
            GenericDao<SolicitudPruebasBack> solicitudPruebasDAO = new GenericDao<>(SolicitudPruebasBack.class, em);
            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();
            jpql.append("SELECT DISTINCT s FROM SolicitudPruebasBack s ");
            jpql.append("LEFT JOIN s.impulsoPruebas ip ");
            jpql.append("LEFT JOIN s.pruebas pru ");
            jpql.append("JOIN s.trazabilidadProceso tp ");
            jpql.append("JOIN tp.proceso p ");
            jpql.append("WHERE 1=1 ");

            if (criterios.getId() != null) {
                jpql.append("AND s.id = :idSolicitud ");
                filtros.put("idSolicitud", criterios.getId());
            }

            if (criterios.getTrazabilidadProceso() != null && criterios.getTrazabilidadProceso().getId() != null) {
                jpql.append("AND tp.id = :idTrazabilidad ");
                filtros.put("idTrazabilidad", criterios.getTrazabilidadProceso().getId());
            }

            if (criterios.getTrazabilidadProceso() != null && criterios.getTrazabilidadProceso().getProceso() != null
                    && criterios.getTrazabilidadProceso().getProceso().getId() != null) {
                jpql.append("AND p.id = :idProceso ");
                filtros.put("idProceso", criterios.getTrazabilidadProceso().getProceso().getId());
            }

            jpql.append("ORDER BY s.fechaSolicitud ");

            List<SolicitudPruebasBack> resultadoConsulta = solicitudPruebasDAO.buildAndExecuteQuery(jpql, filtros);
            if (!resultadoConsulta.isEmpty()) {
                lsSolicitudPruebasDTO = SolicitudPruebasBackHelperExtend.toListLevel2DTO(resultadoConsulta);
            }
        }
        return lsSolicitudPruebasDTO;
    }

    @Override
    public byte[] registrarPruebas(RegistrarPruebaDTO registro) throws CirculemosNegocioException {
        logger.debug("PruebasEJB::registrarPruebas(RegistrarPruebaDTO)");
        try {
            if (registro != null) {
                // Validacion del proceso
                checkNotNull(registro.getIdProceso(), "Proceso es obligatorio");

                // Valida que al menos exista un impulso o una validacion
                if ((registro.getImpulsos() == null && registro.getPruebas() == null)
                        || (registro.getImpulsos() != null && registro.getImpulsos().isEmpty()
                                && registro.getPruebas() != null && registro.getPruebas().isEmpty())) {
                    throw new CirculemosNegocioException(ErrorImpugnacion.RegistroPruebas.JUR_011002);
                }

                List<Long> documentosImpulsos = new ArrayList<Long>();

                // Guarda impulsos
                if (registro.getImpulsos() != null && !registro.getImpulsos().isEmpty()) {
                    for (ImpulsoPruebaDTO impulsoDTO : registro.getImpulsos()) {

                        if (impulsoDTO.getId() == null) {
                            // Crea impulso
                            impulsoDTO.setSolicitudPruebasBack(registro.getSolicitud());
                            ImpulsoPrueba impulso = new ImpulsoPrueba();
                            impulso = ImpulsoPruebaHelper.toLevel1Entity(impulsoDTO, impulso);
                            em.persist(impulso);
                            em.flush();

                            // Genera documento
                            GeneraDocumentoDTO generaImpulso = new GeneraDocumentoDTO();
                            generaImpulso.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
                            generaImpulso.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.IMPULSO_PRUEBAS);
                            Object[] valoresParametros = { registro.getIdProceso(), impulso.getId() };
                            generaImpulso.setValoresParametros(valoresParametros);
                            Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaImpulso);

                            // Guarda el documento generado
                            impulso.setNumeroDocumento(idDocumento);
                            em.merge(impulso);
                            documentosImpulsos.add(idDocumento);
                        }
                    }
                }

                // Guarda pruebas
                if (registro.getPruebas() != null && !registro.getPruebas().isEmpty()) {
                    for (PruebaDTO pruebaDTO : registro.getPruebas()) {

                        if (pruebaDTO.getId() == null) {
                            // Calculo de ruta: anno/mes/proceso/solicitud
                            String ruta = "c2/pruebasImpugnacion/" + Calendar.getInstance().get(Calendar.YEAR) + "/"
                                    + Calendar.getInstance().get(Calendar.MONTH) + "/Proceso-" + registro.getIdProceso()
                                    + "/Solicitud-" + registro.getSolicitud().getId();

                            // Guarda archivo
                            ArchivoTransportableDTO archivo = new ArchivoTransportableDTO();
                            archivo.setContenido(pruebaDTO.getArchivo());
                            archivo.setNombre(pruebaDTO.getNombreArchivo());
                            archivo.setRuta(ruta);
                            OpcionGestorFileSystem ogfs = new OpcionGestorFileSystem();
                            ogfs.setUbicacion(ruta);
                            pruebaDTO.setUrlPrueba(ruta);
                            pruebaDTO.setNumeroArchivo(iRRepositorioArchivo.registrarDocumento(null, archivo, ogfs));

                            // Crea prueba
                            pruebaDTO.setSolicitudPruebasBack(registro.getSolicitud());
                            Prueba prueba = new Prueba();
                            prueba = PruebaHelper.toLevel1Entity(pruebaDTO, prueba);
                            em.persist(prueba);
                        }

                    }
                }

                // Retorna los documentos
                return documentosImpulsos.size() > 0 ? iRDocumentosCirculemos.consultarDocumentosPDF(documentosImpulsos)
                        : null;
            } else {
                throw new CirculemosNegocioException(ErrorImpugnacion.RegistroPruebas.JUR_011003);
            }

        } catch (CirculemosAlertaException e) {
            logger.error("Error al generar documento de registro de pruebas", e);
            throw new CirculemosNegocioException(ErrorImpugnacion.RegistroPruebas.JUR_011001);
        }
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public void cerrarPruebasJob() {
        logger.debug("PruebasEJB::cerrarPruebasJob()");

        List<ProcesoDTO> procesoDTOs = iLPruebas.consultarSolicitudPruebasJob();

        if (procesoDTOs != null && !procesoDTOs.isEmpty()) {
            for (ProcesoDTO proceso : procesoDTOs) {
                try {
                    iLPruebas.cerrarPruebas(proceso.getId(), false);
                } catch (CirculemosNegocioException e) {
                    logger.debug("Error al cerrar la etapa de pruebas del proceso: " + proceso.getId(), e);
                }
            }
        }

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ProcesoDTO> consultarSolicitudPruebasJob() {
        logger.debug("PruebasEJB::consultarSolicitudPruebasJob()");
        List<ProcesoDTO> procesoDTOs = null;

        GenericDao<Proceso> solicitudPruebasDAO = new GenericDao<>(Proceso.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT p FROM SolicitudPruebasBack s ");
        jpql.append("JOIN s.trazabilidadProceso tp ");
        jpql.append("JOIN tp.proceso p ");
        jpql.append("WHERE tp.estadoProceso.id = :idEstadoProceso ");
        jpql.append("AND tp.fechaFin IS NULL ");
        jpql.append("AND p.tipoProceso.id =  :idTipoProceso ");

        filtros.put("idEstadoProceso", EnumEstadoProceso.ECUADOR_IMPUGNACION_SOLICITUD_PRUEBAS.getId());
        filtros.put("idTipoProceso", EnumTipoProceso.IMPUGNACION_COMPARENDO.getValue());

        List<Proceso> procesos = solicitudPruebasDAO.buildAndExecuteQuery(jpql, filtros);
        if (procesos != null && !procesos.isEmpty()) {
            procesoDTOs = ProcesoHelper.toListLevel1DTO(procesos);
        }

        return procesoDTOs;
    }

}