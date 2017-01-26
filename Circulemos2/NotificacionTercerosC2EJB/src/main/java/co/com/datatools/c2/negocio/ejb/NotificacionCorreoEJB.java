package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConfiguracionEmailDTO;
import co.com.datatools.c2.dto.ConsultarEvidenciasNotificacionDTO;
import co.com.datatools.c2.dto.ConsultarNotificacionesDTO;
import co.com.datatools.c2.dto.CreacionCircuitoDTO;
import co.com.datatools.c2.dto.DetalleNotificacionDTO;
import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.EvidenciaNotificacionDTO;
import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.dto.MedioNotificacionDTO;
import co.com.datatools.c2.dto.NotificacionDTO;
import co.com.datatools.c2.dto.RespuestaConsultaCircuitoDTO;
import co.com.datatools.c2.dto.RespuestaConsultaEvidenciaDTO;
import co.com.datatools.c2.dto.RespuestaCreacionCircuitoDTO;
import co.com.datatools.c2.dto.TipoNotificacionDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.entidades.ConfiguracionEmail;
import co.com.datatools.c2.entidades.DetalleNotificacion;
import co.com.datatools.c2.entidades.EvidenciaNotificacion;
import co.com.datatools.c2.entidades.LogAdjuntoEnvioCorreo;
import co.com.datatools.c2.entidades.LogDireccionEnvioCorreo;
import co.com.datatools.c2.entidades.LogEnvioCorreo;
import co.com.datatools.c2.entidades.Notificacion;
import co.com.datatools.c2.entidades.TipoEmail;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumEstadosEnvioNotificacionENotifica;
import co.com.datatools.c2.enumeracion.EnumMedioNotificacion;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoCircuitoENotifica;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoEvidenciaENotifica;
import co.com.datatools.c2.enumeracion.EnumTipoOperacionENotifica;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.NotificacionException;
import co.com.datatools.c2.negocio.fachada.IRFachadaComparendo;
import co.com.datatools.c2.negocio.helpers.DetalleNotificacionHelper;
import co.com.datatools.c2.negocio.helpers.EvidenciaNotificacionHelper;
import co.com.datatools.c2.negocio.helpers.NotificacionHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.ConfiguracionEmailHelper;
import co.com.datatools.c2.negocio.helpers.v2.LogEnvioCorreoHelper;
import co.com.datatools.c2.negocio.interfaces.ILNotificacionCorreo;
import co.com.datatools.c2.negocio.interfaces.IRConfiguracionEmail;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRNotificacionCorreo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRWSENotifica;
import co.com.datatools.c2.negocio.interfaces.administracion.IRNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class NotificacionComparendoTerceroEJB
 */
@Stateless(mappedName = "NotificacionCorreoEJB")
@LocalBean
public class NotificacionCorreoEJB implements IRNotificacionCorreo, ILNotificacionCorreo {

    private final static Logger logger = Logger.getLogger(NotificacionCorreoEJB.class.getName());

    private static final String TMP_PLACER = "@@";
    private static final String PRE_VAR = "\\$\\{";
    private static final String POS_VAR = "\\}";
    private static final String PRE_GRUPO_VAR = "\\#\\{grupo ";
    private static final String POS_GRUPO_VAR = "\\}";
    private static final String ANY = ".*";
    private static final Integer SMTP_CODE_OK = 250;

    public static final String HTML_MEDIA_TYPE = "text/html; charset=ISO-8859-1";

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRNotificacionTerceros IRNotificacion;

    @EJB
    private IRConfiguracionEmail iRConfiguracionEmail;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRWSENotifica iRWSENotifica;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    private ILNotificacionCorreo iLNotificacionCorreo;

    @EJB
    private IRRepositorioArchivo iReposArchivo;

    @EJB
    private IRFachadaComparendo iFachadaComparendo;

    @EJB
    private IRParametro parametroEJB;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer[] enviarNotificaciones(EnvioNotificacionDTO notificaciones) {

        logger.debug(NotificacionCorreoEJB.class.getName().concat("::enviaNotificaciones(EnvioNotificacionDTO)"));
        checkNotNull(notificaciones, "Debe recibirse las notificaciones");

        // Se consulta el Organismo de tránsito en sesión
        OrganismoTransitoDTO organismo = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

        // Se consulta la configuración de correo para E-Notifica
        List<ConfiguracionEmailDTO> configuraciones = iRConfiguracionEmail
                .consultarConfiguracionEmail(organismo.getCodigoOrganismo(), notificaciones.getTipoCorreo(), true);

        final ConfiguracionEmailDTO confEmail = configuraciones.get(0);
        final String contenido = procesarContenidoCorreo(
                "<p>" + confEmail.getCuerpoEmail() + "</p> <p>" + confEmail.getPieEmail() + "</p>",
                notificaciones.getVariablesMensaje());
        final String asunto = procesarContenidoCorreo(confEmail.getAsuntoEmail(), notificaciones.getVariablesMensaje());

        // Se transforma el correo a Base64
        String correo = DatatypeConverter.printBase64Binary(contenido.getBytes());
        confEmail.setCuerpoEmail(correo);
        confEmail.setAsuntoEmail(asunto);

        Integer[] cantidadNotificados = new Integer[2];
        cantidadNotificados[0] = 0;
        cantidadNotificados[1] = 0;

        NotificacionDTO notificacionDTO = new NotificacionDTO();
        TipoNotificacionDTO tipoNotificacionDTO = new TipoNotificacionDTO();
        tipoNotificacionDTO.setId(notificaciones.getTipoNotificacion().getId());
        notificacionDTO.setTipoNotificacion(tipoNotificacionDTO);

        MedioNotificacionDTO medioNotificacion = new MedioNotificacionDTO();
        medioNotificacion.setId(EnumMedioNotificacion.CORREO_ELECTRONICO_WS.getValue());
        notificacionDTO.setMedioNotificacion(medioNotificacion);

        notificacionDTO.setFechaSolicitud(UtilFecha.buildCalendar().getTime());
        notificacionDTO.setUsuario(iRSeguridadCirculemos.obtenerUsuarioDto());

        // Se persiste la notificación
        NotificacionDTO notificacion = iLNotificacionCorreo.guardarNotificacion(notificacionDTO);

        for (ConsultarNotificacionesDTO consultarNotificacionesDTO : notificaciones.getLsNotificaciones()) {
            RespuestaCreacionCircuitoDTO respuestaCreacionCircuitoDTO = null;
            DetalleNotificacionDTO detalleNotificacion = null;
            try {
                List<ArchivoTransportableDTO> lsArchivos = consultarNotificacionesDTO.getLsArchivos();
                List<ArchivoTransportableDTO> lsArchivosF = new ArrayList<>();
                for (ArchivoTransportableDTO archivo : lsArchivos) {
                    byte[] archivoFirmado = iRWSENotifica.firmarDocumento(archivo.getContenido());
                    if (archivoFirmado != null) {
                        ArchivoTransportableDTO archivoF = new ArchivoTransportableDTO();
                        archivoF.setContenido(archivoFirmado);
                        archivoF.setNombre(archivo.getNombre());
                        archivoF.setNumeroDocumento(archivo.getNumeroDocumento());
                        lsArchivosF.add(archivoF);
                    } else {
                        lsArchivosF.clear();
                        break;
                    }
                }

                PersonaDTO personaDTO = new PersonaDTO();
                personaDTO.setCorreoPersonaList(new ArrayList<CorreoPersonaDTO>());

                for (String correoElectronico : consultarNotificacionesDTO.getLsCorreoElectronico()) {
                    CorreoPersonaDTO correoPersona = new CorreoPersonaDTO();
                    correoPersona.setCorreoElectronico(correoElectronico);
                    personaDTO.getCorreoPersonaList().add(correoPersona);
                }

                List<PersonaDTO> personas = new ArrayList<>();
                personas.add(personaDTO);

                List<ConsultarNotificacionesDTO> lsConsultaNotificaciones = new ArrayList<>();
                lsConsultaNotificaciones.add(consultarNotificacionesDTO);

                CreacionCircuitoDTO creacionCircuitoDTO = new CreacionCircuitoDTO();
                creacionCircuitoDTO.setlDocumentos(lsArchivosF);
                creacionCircuitoDTO.setlDestinatarios(personas);
                creacionCircuitoDTO.setConfigMail(confEmail);
                creacionCircuitoDTO.setlMetadata(lsConsultaNotificaciones);
                creacionCircuitoDTO.setTipoCircuito(EnumTipoCircuitoENotifica.NOTIFICACION);
                creacionCircuitoDTO.setTipoOperacion(EnumTipoOperacionENotifica.DESCARGA_DOCUMENTO);
                creacionCircuitoDTO.setPublicacionCerti(false);
                creacionCircuitoDTO.setOculCreadorCircuito(true);
                creacionCircuitoDTO.setRecibirAviso(false);
                creacionCircuitoDTO.setTituloCircuito(notificaciones.getTipoCorreo().toString());

                String[] correos = consultarNotificacionesDTO.getLsCorreoElectronico()
                        .toArray(new String[consultarNotificacionesDTO.getLsCorreoElectronico().size()]);
                LogEnvioCorreoDTO logEnvioCorreoDTO = iLNotificacionCorreo.guardarTraza(confEmail.getAsuntoEmail(),
                        confEmail.getCuerpoEmail(), organismo.getCodigoOrganismo(), confEmail,
                        notificaciones.getTipoCorreo(), lsArchivosF, correos);

                DetalleNotificacionDTO detalleNotificacionDTO = new DetalleNotificacionDTO();
                detalleNotificacionDTO.setLogEnvioCorreo(logEnvioCorreoDTO);
                detalleNotificacionDTO.setNotificacion(notificacion);
                detalleNotificacionDTO
                        .setCodigoSeguimientoInt(String.valueOf(consultarNotificacionesDTO.getCodSeguimientoInt()));
                detalleNotificacionDTO.setFechaEnvioTercero(UtilFecha.buildCalendar().getTime());
                detalleNotificacionDTO.setFechaModificacion(UtilFecha.buildCalendar().getTime());
                detalleNotificacionDTO.setEnviadoExt(BigInteger.ONE.intValue());
                detalleNotificacionDTO.setUsuario(iRSeguridadCirculemos.obtenerUsuarioDto());

                // Guarda detalle de notificación sin log de web service
                detalleNotificacion = iLNotificacionCorreo.guardarDetalleNotificacion(detalleNotificacionDTO);

                creacionCircuitoDTO.setExternalAppId(detalleNotificacion.getId().toString());
                creacionCircuitoDTO.setExternalId(consultarNotificacionesDTO.getExternalId().toString());

                respuestaCreacionCircuitoDTO = iRWSENotifica.crearCircuito(creacionCircuitoDTO);

                if (respuestaCreacionCircuitoDTO.getIdCircuito() != null
                        && !respuestaCreacionCircuitoDTO.getIdCircuito().trim().isEmpty()) {
                    cantidadNotificados[1]++;

                    LogEjecucionWSDTO logEjecucionWSDTO = new LogEjecucionWSDTO();
                    logEjecucionWSDTO.setId(respuestaCreacionCircuitoDTO.getIdLogEjecucionWS());
                    detalleNotificacion.setLogEjecucionWS(logEjecucionWSDTO);
                    detalleNotificacion.setCodigoSeguimientoExt(respuestaCreacionCircuitoDTO.getIdCircuito());

                    iLNotificacionCorreo.actualizarDetalleNotificacion(detalleNotificacion);
                } else {
                    LogEjecucionWSDTO logEjecucionWSDTO = new LogEjecucionWSDTO();
                    logEjecucionWSDTO.setId(respuestaCreacionCircuitoDTO.getIdLogEjecucionWS());
                    detalleNotificacion.setLogEjecucionWS(logEjecucionWSDTO);
                    detalleNotificacion.setCodigoSeguimientoExt(respuestaCreacionCircuitoDTO.getIdCircuito());

                    iLNotificacionCorreo.actualizarDetalleNotificacion(detalleNotificacion);
                    cantidadNotificados[0]++;
                }
            } catch (NotificacionException e) {
                cantidadNotificados[0]++;
                continue;
            }
        }
        return cantidadNotificados;
    }

    private String procesarContenidoCorreo(String contenido, Map<String, Object> variablesMensaje) {
        logger.debug("CirculemosMailSenderEJB::procesarContenidoCorreo(String, Map<String, Object>)");
        if (variablesMensaje == null) {
            return contenido;
        }
        Set<Entry<String, Object>> entrySet = variablesMensaje.entrySet();
        for (Entry<String, Object> entry : entrySet) {
            contenido = procesaParteMensaje(contenido, entry);
        }
        // Remover si quedaron grupos sin procesar
        contenido = Pattern.compile(PRE_GRUPO_VAR + ANY + POS_GRUPO_VAR + ANY + PRE_GRUPO_VAR + ANY + POS_GRUPO_VAR,
                Pattern.DOTALL).matcher(contenido).replaceAll("");
        // Remover si quedaron variables sin procesar
        contenido = contenido.replaceAll(PRE_VAR + ANY + POS_VAR, "");
        return contenido;
    }

    /**
     * 
     * @param contenido
     * @param entry
     * @return
     */
    private String procesaParteMensaje(String contenido, Entry<String, Object> entry) {
        logger.debug("CirculemosMailSenderEJB::procesaParteMensaje(String, Entry<String, Object>)");
        String key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof Collection) {
            // Reemplazar bloque N veces
            String regex = PRE_GRUPO_VAR + key + POS_GRUPO_VAR + ANY + PRE_GRUPO_VAR + key + POS_GRUPO_VAR;
            String tempPlacer = TMP_PLACER + key + TMP_PLACER;
            Matcher m = Pattern.compile(regex, Pattern.DOTALL).matcher(contenido);
            if (m.find()) {
                // Obtener bloque
                String bloqueRepetir = m.group().replaceAll(PRE_GRUPO_VAR + key + POS_GRUPO_VAR, "");
                contenido = Pattern.compile(regex, Pattern.DOTALL).matcher(contenido).replaceFirst(tempPlacer);
                @SuppressWarnings("unchecked")
                Collection<Map<String, Object>> valoresBloque = (Collection<Map<String, Object>>) value;
                StringBuilder repeticiones = new StringBuilder();
                for (Map<String, Object> valoresGrupo : valoresBloque) {
                    repeticiones.append(procesarContenidoCorreo(bloqueRepetir, valoresGrupo));
                }
                contenido = Pattern.compile(tempPlacer, Pattern.DOTALL).matcher(contenido)
                        .replaceFirst(repeticiones.toString());
            }
        } else if (value != null) {
            // Reemplazar una variable
            contenido = contenido.replaceAll(PRE_VAR + key + POS_VAR, value.toString());
        }
        return contenido;
    }

    @Override
    public LogEnvioCorreoDTO guardarTraza(String asunto, String contenido, Integer codigoOrganismo,
            ConfiguracionEmailDTO configuracion, EnumTipoCorreo tipoCorreo, List<ArchivoTransportableDTO> adjuntos,
            String[] direccionesEnvio) {

        Date fecha = new Date();
        ConfiguracionEmail configuracionP = null;
        TipoEmail tipo = null;

        if (asunto == null) {
            asunto = "";
        }
        if (contenido == null) {
            contenido = "";
        }
        if (codigoOrganismo == null) {
            codigoOrganismo = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo();
        }
        if (configuracion != null) {
            configuracionP = ConfiguracionEmailHelper.toLevel0Entity(configuracion, null);
        }
        if (tipoCorreo != null) {
            tipo = em.find(TipoEmail.class, tipoCorreo.getCodigo());
        }

        OrganismoTransito organismo = em.find(OrganismoTransito.class, codigoOrganismo);

        // Almacena el log
        LogEnvioCorreo log = new LogEnvioCorreo();
        log.setAsuntoEmail(asunto);
        log.setConfiguracionEmail(configuracionP);
        log.setCuerpoEmail(contenido);
        log.setFechaEnvio(fecha);
        log.setOrganismoTransito(organismo);
        log.setTipoEmail(tipo);
        log.setUsuario(UsuarioPersonaHelper.toLevel0Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), null));
        log.setAdjunto(adjuntos != null && !adjuntos.isEmpty());
        em.persist(log);

        // Guardar direcciones
        LogDireccionEnvioCorreo logDir;
        for (int i = 0; i < direccionesEnvio.length; i++) {
            logDir = new LogDireccionEnvioCorreo();
            logDir.setDireccionDestino(direccionesEnvio[i]);
            logDir.setLogEnvioCorreo(log);
            logDir.setTipoDestinatario("PARA");
            em.persist(logDir);
        }

        // Guardar adjuntos
        if (adjuntos != null && !adjuntos.isEmpty()) {
            LogAdjuntoEnvioCorreo logAdj;
            for (ArchivoTransportableDTO adjunto : adjuntos) {
                logAdj = new LogAdjuntoEnvioCorreo();
                logAdj.setNumeroDocumento(adjunto.getNumeroDocumento());
                logAdj.setArchivo(adjunto.getNumeroDocumento() == null ? adjunto.getContenido() : null);
                logAdj.setLogEnvioCorreo(log);
                em.persist(logAdj);
            }

        }
        return LogEnvioCorreoHelper.toLevel1DTO(log);
    }

    @Override
    public NotificacionDTO guardarNotificacion(NotificacionDTO notificacion) {
        logger.debug("CirculemosMailSenderEJB::guardarNotificacion(NotificacionDTO)");

        Notificacion notificacionEntity = NotificacionHelper.toLevel1Entity(notificacion, null);
        em.persist(notificacionEntity);

        return NotificacionHelper.toLevel0DTO(notificacionEntity);
    }

    @Override
    public DetalleNotificacionDTO guardarDetalleNotificacion(DetalleNotificacionDTO detalleNotificacion) {
        logger.debug("CirculemosMailSenderEJB::guardarNotificacion(NotificacionDTO)");

        DetalleNotificacion detalleNotificacionEntity = DetalleNotificacionHelper.toLevel1Entity(detalleNotificacion,
                null);
        em.persist(detalleNotificacionEntity);

        return DetalleNotificacionHelper.toLevel1DTO(detalleNotificacionEntity);
    }

    @Override
    public DetalleNotificacionDTO actualizarDetalleNotificacion(DetalleNotificacionDTO detalleNotificacion) {
        logger.debug("CirculemosMailSenderEJB::guardarNotificacion(NotificacionDTO)");

        DetalleNotificacion detalleNotificacionEntity = DetalleNotificacionHelper.toLevel1Entity(detalleNotificacion,
                null);

        em.merge(detalleNotificacionEntity);

        return DetalleNotificacionHelper.toLevel1DTO(detalleNotificacionEntity);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer[] consultarNotificaciones() {

        logger.debug(NotificacionCorreoEJB.class.getName().concat("::consultarNotificaciones()"));
        List<DetalleNotificacionDTO> detNotifDTO = iLNotificacionCorreo.consultarDetalleNotificacion();
        Integer[] registrosProcesados = new Integer[2];
        registrosProcesados[0] = 0;
        registrosProcesados[1] = 0;

        for (DetalleNotificacionDTO detNotif : detNotifDTO) {
            try {
                RespuestaConsultaCircuitoDTO consultaCircuito = iRWSENotifica
                        .consultarCircuito(detNotif.getId().toString());
                iLNotificacionCorreo.procesarNotificacion(detNotif, consultaCircuito);
                registrosProcesados[1]++;
            } catch (CirculemosAlertaException | NotificacionException e) {
                registrosProcesados[0]++;
                continue;
            }
        }
        return registrosProcesados;
    }

    @Override
    public List<DetalleNotificacionDTO> consultarDetalleNotificacion() {

        logger.debug(NotificacionCorreoEJB.class.getName().concat("::consultarDetalleNotificacion()"));
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT dn FROM DetalleNotificacion dn ");
        jpql.append("WHERE dn.enviadoExt = 1 ");
        jpql.append("AND (dn.estadoExt IS NULL OR dn.estadoExt = 1)");
        jpql.append("AND dn.fechaNotificacion IS NULL");

        GenericDao<DetalleNotificacion> dao = new GenericDao<>(DetalleNotificacion.class, em);
        List<DetalleNotificacion> resultado = dao.buildAndExecuteQuery(jpql, filtros);
        return DetalleNotificacionHelper.toListLevel1DTO(resultado);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void procesarNotificacion(DetalleNotificacionDTO detNotifDTO, RespuestaConsultaCircuitoDTO consultaCircuito)
            throws NotificacionException, CirculemosAlertaException {

        logger.debug(NotificacionCorreoEJB.class.getName()
                .concat("::procesarNotificacion(DetalleNotificacionDTO, RespuestaConsultaCircuitoDTO)"));

        if (consultaCircuito != null) {
            boolean estadoFinal = false;
            if (detNotifDTO.getEstadoExt() == null) {
                detNotifDTO.setEstadoExt(consultaCircuito.getCodigoEstado().toString());
                actualizarDetalleNotificacion(detNotifDTO);
            }

            if (consultaCircuito.getCodigoEstado().equals(EnumEstadosEnvioNotificacionENotifica.CREADO.getValue())
                    && consultaCircuito.getSmtpCodigo().equals(SMTP_CODE_OK)) {
                detNotifDTO.setFechaNotificacion(consultaCircuito.getCreateDate());
                actualizarDetalleNotificacion(detNotifDTO);
                iFachadaComparendo.actualizarFechaNotificacion(Long.valueOf(detNotifDTO.getCodigoSeguimientoInt()),
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                        consultaCircuito.getCreateDate());
                estadoFinal = true;
            } else if (consultaCircuito.getCodigoEstado()
                    .equals(EnumEstadosEnvioNotificacionENotifica.DESCARGADO.getValue())
                    && consultaCircuito.getSmtpCodigo().equals(SMTP_CODE_OK)) {
                detNotifDTO.setFechaNotificacion(consultaCircuito.getCreateDate());
                actualizarDetalleNotificacion(detNotifDTO);
                iFachadaComparendo.actualizarFechaNotificacion(Long.valueOf(detNotifDTO.getCodigoSeguimientoInt()),
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                        consultaCircuito.getCreateDate());
                estadoFinal = true;
            } else if (consultaCircuito.getCodigoEstado()
                    .equals(EnumEstadosEnvioNotificacionENotifica.CADUCADO.getValue())
                    && consultaCircuito.getSmtpCodigo().equals(SMTP_CODE_OK)) {
                detNotifDTO.setFechaNotificacion(consultaCircuito.getCreateDate());
                actualizarDetalleNotificacion(detNotifDTO);
                iFachadaComparendo.actualizarFechaNotificacion(Long.valueOf(detNotifDTO.getCodigoSeguimientoInt()),
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                        consultaCircuito.getCreateDate());
                estadoFinal = true;
            }

            if (estadoFinal) {
                List<RespuestaConsultaEvidenciaDTO> lRConsultaEv = iRWSENotifica.consultarEvidencias(detNotifDTO);
                if (lRConsultaEv != null && !lRConsultaEv.isEmpty()) {
                    for (RespuestaConsultaEvidenciaDTO rConsultaEv : lRConsultaEv) {
                        List<EvidenciaNotificacionDTO> lEvNotif = null;
                        ConsultarEvidenciasNotificacionDTO consulta = new ConsultarEvidenciasNotificacionDTO();
                        consulta.setIdDetalleNotificacion(detNotifDTO.getId());
                        consulta.setTipoEvidencia(EnumTipoEvidenciaENotifica
                                .encontrarPorNombre(rConsultaEv.getTipoEvidencia()).getValue());
                        consulta.setIdEvidenciaExterno(rConsultaEv.getIdEvidencia());
                        lEvNotif = iLNotificacionCorreo.consultarEvidencias(consulta);

                        if (lEvNotif == null) {
                            byte[] evidencia = iRWSENotifica.obtenerEvidencias(rConsultaEv);
                            String numeroArchivo = null;
                            ArchivoTransportableDTO archivoDTO = new ArchivoTransportableDTO();
                            archivoDTO.setContenido(evidencia);
                            archivoDTO.setNombre(null);
                            numeroArchivo = iReposArchivo
                                    .registrarDocumento(EnumCategoriaDocumento.COMPARENDO_EVIDENCIA, archivoDTO);

                            EvidenciaNotificacionDTO evNotifDTO = new EvidenciaNotificacionDTO();
                            evNotifDTO.setFechaModificacion(UtilFecha.buildCalendar().getTime());
                            evNotifDTO.setUsuario(iRSeguridadCirculemos.obtenerUsuarioDto());
                            evNotifDTO.setDetalleNotificacion(detNotifDTO);
                            evNotifDTO.setCodigoEvidencia(numeroArchivo);
                            evNotifDTO.setIdEvidenciaExterno(rConsultaEv.getIdEvidencia());
                            evNotifDTO.setTipoEvidencia(EnumTipoEvidenciaENotifica
                                    .encontrarPorNombre(rConsultaEv.getTipoEvidencia()).getValue());

                            EvidenciaNotificacion evidenciaNotif = new EvidenciaNotificacion();
                            em.persist(EvidenciaNotificacionHelper.toLevel1Entity(evNotifDTO, evidenciaNotif));
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<EvidenciaNotificacionDTO> consultarEvidencias(ConsultarEvidenciasNotificacionDTO consulta) {

        logger.debug(NotificacionCorreoEJB.class.getName()
                .concat("::consultarEvidencias(ConsultarEvidenciasNotificacionDTO)"));
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT en FROM EvidenciaNotificacion en ");
        jpql.append("WHERE 1=1 ");

        if (consulta.getIdDetalleNotificacion() != null) {
            jpql.append("AND en.detalleNotificacion.id = :idDetalleNotificacion ");
            filtros.put("idDetalleNotificacion", consulta.getIdDetalleNotificacion());
        }

        if (consulta.getCodigoEvidencia() != null) {
            jpql.append("AND en.codigoEvidencia = :codigoEvidencia ");
            filtros.put("codigoEvidencia", consulta.getCodigoEvidencia());
        }

        if (consulta.getTipoEvidencia() != null) {
            jpql.append("AND en.tipoEvidencia = :tipoEvidencia ");
            filtros.put("tipoEvidencia", consulta.getTipoEvidencia());
        }

        GenericDao<EvidenciaNotificacion> dao = new GenericDao<>(EvidenciaNotificacion.class, em);
        List<EvidenciaNotificacion> resultado = dao.buildAndExecuteQuery(jpql, filtros);

        if (resultado != null && !resultado.isEmpty()) {
            return EvidenciaNotificacionHelper.toListLevel1DTO(resultado);
        }
        return null;
    }

    @Override
    public ValorParametroDTO consultarParametroEnvioNotificaciones(int codigoOrganismo) {

        logger.debug(NotificacionCorreoEJB.class.getName().concat("::consultarParametroEnvioNotificaciones(int)"));
        ValorParametroDTO valorParametroDTO = parametroEJB
                .consultarParametro(EnumParametro.ENVIO_NOTIFICACION_POR_TERCERO, codigoOrganismo, true);
        return valorParametroDTO;
    }
}