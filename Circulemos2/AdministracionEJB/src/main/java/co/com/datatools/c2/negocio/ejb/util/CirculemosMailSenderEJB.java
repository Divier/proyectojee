package co.com.datatools.c2.negocio.ejb.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.ArrayUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConfiguracionEmailDTO;
import co.com.datatools.c2.dto.CorreoEnvioDTO;
import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.entidades.ConfiguracionEmail;
import co.com.datatools.c2.entidades.LogAdjuntoEnvioCorreo;
import co.com.datatools.c2.entidades.LogDireccionEnvioCorreo;
import co.com.datatools.c2.entidades.LogEnvioCorreo;
import co.com.datatools.c2.entidades.TipoEmail;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.mail.dto.DocAdjuntoDTO;
import co.com.datatools.c2.mail.dto.MensajeCorreoDTO;
import co.com.datatools.c2.mail.util.PublicarMensajeCorreo;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.ConfiguracionEmailHelper;
import co.com.datatools.c2.negocio.helpers.v2.LogEnvioCorreoHelper;
import co.com.datatools.c2.negocio.interfaces.ILParametro;
import co.com.datatools.c2.negocio.interfaces.IRConfiguracionEmail;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * Esta clase se encarga de exponer servicios donde se procese el contenido de un correo electronica dependiendo del tipo de correo y el codigo de
 * organismo
 * 
 * @author felipe.martinez
 * 
 */
@Stateless(name = "CirculemosMailSenderEJB")
@LocalBean
public class CirculemosMailSenderEJB implements IRCirculemosMailSender {

    private final static Logger logger = Logger.getLogger(CirculemosMailSenderEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    private static final String TMP_PLACER = "@@";
    private static final String PRE_VAR = "\\$\\{";
    private static final String POS_VAR = "\\}";
    private static final String PRE_GRUPO_VAR = "\\#\\{grupo ";
    private static final String POS_GRUPO_VAR = "\\}";
    private static final String ANY = ".*";

    public static final String HTML_MEDIA_TYPE = "text/html; charset=ISO-8859-1";

    @EJB
    private ILParametro parametroEjb;
    @EJB
    private IRConfiguracionEmail confCorreoEjb;

    private PublicarMensajeCorreo publicador;

    public CirculemosMailSenderEJB() {

    }

    @PostConstruct
    public void init() {
        publicador = new PublicarMensajeCorreo();
    }

    @PreDestroy
    public void destroy() {
        publicador.close();
    }

    @Override
    public LogEnvioCorreoDTO enviarCorreo(int codigoOrganismo,
            co.com.datatools.c2.enumeracion.EnumTipoCorreo tipoCorreo, String[] direccionDestino,
            Map<String, Object> variablesMensaje) {
        logger.debug(
                "CirculemosMailSenderEJB::enviarCorreo(int, co.com.datatools.c2.enumeracion.EnumTipoCorreo, String[], Map<String, Object>)");
        return enviarCorreo(codigoOrganismo, tipoCorreo, direccionDestino, variablesMensaje, null);
    }

    @Override
    public LogEnvioCorreoDTO publicarCorreo(String[] direccionDestino, String asunto, String contenido,
            List<ArchivoTransportableDTO> adjuntos) {
        logger.debug("CirculemosMailSenderEJB::publicarCorreo(String[], String, String, ArchivoTransportableDTO)");
        MensajeCorreoDTO contenidoMensaje = new MensajeCorreoDTO(direccionDestino, asunto, contenido, HTML_MEDIA_TYPE);

        if (adjuntos != null && !adjuntos.isEmpty()) {
            for (ArchivoTransportableDTO adjunto : adjuntos) {
                DocAdjuntoDTO doc = new DocAdjuntoDTO();
                doc.setByteArray(adjunto.getContenido());
                doc.setNombre(adjunto.getNombre());
                contenidoMensaje.addDocAdjunto(doc);
            }
        }

        publicador.publicarMensaje(contenidoMensaje);

        // Guarda el log
        LogEnvioCorreoDTO log = guardarTraza(asunto, contenido, null, null, null, adjuntos, direccionDestino);

        logger.infov("Peticion de correo publicada correctamente [direccionDestino:{0}, asunto:{1}]",
                Arrays.toString(direccionDestino), asunto);
        return log;
    }

    /**
     * 
     * @param contenido
     * @param variablesMensaje
     * @return
     */
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
    public LogEnvioCorreoDTO enviarCorreo(int codigoOrganismo, EnumTipoCorreo tipoCorreo, String[] direccionesEnvio,
            Map<String, Object> variablesMensaje, List<ArchivoTransportableDTO> adjuntos) {

        logger.debug(
                "CirculemosMailSenderEJB::enviarCorreo(int, co.com.datatools.c2.enumeracion.EnumTipoCorreo, String[], ArchivoTransportableDTO)");
        checkNotNull(codigoOrganismo, "Codigo Organismo es requerido");
        checkNotNull(tipoCorreo, "Tipo Correo es requerido");

        final List<ConfiguracionEmailDTO> confEmailList = confCorreoEjb.consultarConfiguracionEmail(codigoOrganismo,
                tipoCorreo, true);
        if (confEmailList.isEmpty()) {
            logger.errorv("No se encuentra la configuracion de correo del tipo {0} [id:{1}]", tipoCorreo,
                    tipoCorreo.getValue());
            return null;
        }

        final ConfiguracionEmailDTO confEmail = confEmailList.get(0);
        final String contenido = procesarContenidoCorreo(
                "<p>" + confEmail.getCuerpoEmail() + "</p> <p>" + confEmail.getPieEmail() + "</p>", variablesMensaje);
        final String asunto = procesarContenidoCorreo(confEmail.getAsuntoEmail(), variablesMensaje);

        final List<CorreoEnvioDTO> emailList = confEmail.getEmailList();
        for (CorreoEnvioDTO correoEnvioDTO : emailList) {
            direccionesEnvio = ArrayUtils.add(direccionesEnvio, correoEnvioDTO.getEmail());
        }

        if (direccionesEnvio == null || direccionesEnvio.length == 0) {
            logger.errorv("No se encuentran correos configurados ni adicionales del tipo {0} [id:{1}]", tipoCorreo,
                    tipoCorreo.getValue());
            return null;
        }

        MensajeCorreoDTO contenidoMensaje = new MensajeCorreoDTO(direccionesEnvio, asunto, contenido, HTML_MEDIA_TYPE);

        if (adjuntos != null && !adjuntos.isEmpty()) {
            for (ArchivoTransportableDTO adjunto : adjuntos) {
                DocAdjuntoDTO doc = new DocAdjuntoDTO();
                doc.setByteArray(adjunto.getContenido());
                doc.setNombre(adjunto.getNombre());
                contenidoMensaje.addDocAdjunto(doc);
            }
        }

        publicador.publicarMensaje(contenidoMensaje);

        // Guarda el log
        LogEnvioCorreoDTO log = guardarTraza(asunto, contenido, codigoOrganismo, confEmailList.get(0), tipoCorreo,
                adjuntos, direccionesEnvio);

        logger.infov("Peticion de correo publicada correctamente [tipo:{0}, id:{1}]", tipoCorreo,
                tipoCorreo.getValue());
        return log;
    }

    /**
     * Guarda el log en BD
     * 
     * @param asunto
     * @param contenido
     * @param codigoOrganismo
     * @param configuracion
     * @param tipoCorreo
     * @param adjunto
     * @param direccionesEnvio
     * @return
     */
    private LogEnvioCorreoDTO guardarTraza(String asunto, String contenido, Integer codigoOrganismo,
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
}