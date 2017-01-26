package co.com.datatools.c2.negocio.ejb;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.util.ArrayList;
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
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesDocumentosC2;
import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.ConsultarNotificacionesDTO;
import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.entidades.Evidencia;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoNotificacion;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoGenerado;
import co.com.datatools.c2.negocio.helpers.comparendos.EvidenciaHelper;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRFachadaNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.administracion.ILNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.administracion.IRNotificacionTerceros;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class NotificacionComparendoTerceroEJB
 */
@Stateless(mappedName = "NotificacionTerceroEJB")
@LocalBean
public class NotificacionTercerosEJB implements IRNotificacionTerceros, ILNotificacionTerceros {

    private final static Logger logger = Logger.getLogger(NotificacionTercerosEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILNotificacionTerceros iLNotificacion;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    private IRFachadaNotificacionTerceros iRFachadaNotificaciones;

    @EJB
    private IRParametro iRParametro;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    public static final String NOMBRE_ARCHIVO_NOTIFICACION = "NOTIFICACION_MULTA";
    public static final String EXTENSION_ARCHIVO_NOTIFICACION = ".pdf";
    public static final String GUION_PISO = "_";

    /**
     * Consultar las citaciones electronicas que se pueden notificar por correo electronico
     * 
     */
    @Override
    public EnvioNotificacionDTO consultarCitacionesNotifica(int codOrganismo) {

        logger.debug(NotificacionTercerosEJB.class.getName().concat("::consultarCitacionesNotifica(int)"));
        List<ConsultarNotificacionesDTO> lsResultado = new ArrayList<ConsultarNotificacionesDTO>();
        EnvioNotificacionDTO envioNotificacion = new EnvioNotificacionDTO();
        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT TOP 6 com.numero_citacion, ");
        consulta.append("com.id_factura_axis, ");
        consulta.append("i.codigo_infraccion, ");
        consulta.append("com.fecha_infraccion, ");
        consulta.append("CASE ");
        consulta.append("   WHEN per.id_tipo_identificacion = 1 ");
        consulta.append("       THEN CONCAT (per.nombre1,' ',per.nombre2,' ',per.apellido1,' ',per.apellido2) ");
        consulta.append("   WHEN per.id_tipo_identificacion = 2 ");
        consulta.append("       THEN pj.nombre_comercial ");
        consulta.append("END AS nombreCompleto, ");
        consulta.append("tip.codigo, ");
        consulta.append("per.numero_identificacion, ");
        consulta.append("crp.correo_electronico, ");
        consulta.append("com.cicomparendo, ");
        consulta.append("cv.placa_vehiculo ");
        consulta.append("FROM comparendo com ");
        consulta.append("JOIN comparendo_cartera cc ON cc.cicomparendo = com.cicomparendo ");
        consulta.append("JOIN cartera ca ON ca.id_cartera = cc.id_cartera ");
        consulta.append("JOIN comparendo_persona cp ON cp.cicomparendo = com.cicomparendo ");
        consulta.append("JOIN infraccion i ON i.id_infraccion = com.id_infraccion ");
        consulta.append("JOIN comparendo_vehiculo cv ON cv.id_comparendo_vehiculo = com.cicomparendo ");
        consulta.append("JOIN persona per ON per.id_persona = cp.id_persona ");
        consulta.append("LEFT JOIN persona_juridica pj ON pj.id_persona_juridica = per.id_persona ");
        consulta.append(
                "JOIN tipo_identificacion_persona tip ON tip.id_tipo_identificacion = per.id_tipo_identificacion ");
        consulta.append("JOIN correo_persona crp ON crp.id_persona = per.id_persona ");
        consulta.append("WHERE com.codigo_medio_imposicion = 2 ");
        consulta.append("AND com.fecha_infraccion >= CAST('2016-05-24' AS DATE) ");
        consulta.append("AND com.id_estado_comparendo IN (1, 2) ");
        consulta.append("AND crp.correo_electronico IS NOT NULL ");
        consulta.append("AND ca.saldo_capital > 0.00 ");
        consulta.append("AND (SELECT count(*) FROM evidencia ev WHERE ev.cicomparendo = com.cicomparendo) >= 2 ");
        consulta.append("AND (crp.estado IS NULL OR crp.estado = 1) ");
        // consulta.append("AND crp.autorizado_notificacion = 1 ");
        consulta.append("AND per.id_tipo_identificacion = 1 ");
        consulta.append(
                "AND per.numero_identificacion IN ('1718807371','0908377369','1706728316','0910832534','0906847504') ");

        Query query = em.createNativeQuery(consulta.toString());
        List<Object[]> resultado = Utilidades.safeList(query.getResultList());
        List<EvidenciaDTO> evidencias = null;
        List<Long> lsNumerosCitacion = new ArrayList<>();

        if (!resultado.isEmpty()) {
            ConsultarNotificacionesDTO notificacion = null;
            for (Object[] datos : resultado) {

                if (lsNumerosCitacion.isEmpty()) {
                    notificacion = new ConsultarNotificacionesDTO();
                } else {
                    if (!lsNumerosCitacion.contains(Long.parseLong(datos[0].toString()))) {
                        notificacion = new ConsultarNotificacionesDTO();
                    }
                }
                notificacion.setNumeroCitacion(Long.parseLong(datos[0].toString()));
                notificacion.setExternalId(Long.parseLong(datos[1].toString()));
                notificacion.setCodigoInfraccion((String) datos[2]);
                notificacion.setFechaImposicion((Date) datos[3]);
                notificacion.setNombreInfractor((String) datos[4]);
                notificacion.setTipoIdentificacion((String) datos[5]);
                notificacion.setNumeroIdentificacion((String) datos[6]);
                // Verifica si el correo es valido
                if (datos[7].toString().matches(ExpresionesRegulares.REGEX_EMAIL)) {
                    notificacion.setCorreoValido(true);
                } else {
                    notificacion.setCorreoValido(false);
                }
                notificacion.addCorreoElectronico((String) datos[7]);
                notificacion.setCodSeguimientoInt(Long.parseLong(datos[8].toString()));
                notificacion.setPlaca((String) datos[9].toString());

                evidencias = obtenerEvidencia(notificacion.getCodSeguimientoInt(), codOrganismo);

                try {
                    // Genera el documento por cada infraccion
                    GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
                    generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
                    generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.NOTI_ELECTRONICA);
                    Object[] valoresParametros = { notificacion.getCodSeguimientoInt() };
                    generaDocumento.setValoresParametros(valoresParametros);
                    Map<String, Object> valoresVistaPreliminar = new HashMap<>();
                    valoresVistaPreliminar.put(ConstantesDocumentosC2.EVIDENCIA1, evidencias.get(0).getUrl());
                    valoresVistaPreliminar.put(ConstantesDocumentosC2.EVIDENCIA2, evidencias.get(1).getUrl());
                    // valoresVistaPreliminar.put(ConstantesDocumentosC2.IMAGEN_FIRMA, );
                    generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
                    // TODO Deescomentarear linea snuevamente
                    // Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);
                    // notificacion.setIdDocumento(idDocumento);
                    Long idDocumento = 23570L;
                    ArrayList<Long> documentos = new ArrayList<Long>();
                    documentos.add(idDocumento);
                    ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(
                            NOMBRE_ARCHIVO_NOTIFICACION.concat(GUION_PISO).concat(datos[1].toString())
                                    .concat(EXTENSION_ARCHIVO_NOTIFICACION),
                            iRDocumentosCirculemos.consultarDocumentosPDF(documentos));
                    archivo.setNumeroDocumento(String.valueOf(idDocumento));
                    List<ArchivoTransportableDTO> lsArchivos = new ArrayList<>();
                    lsArchivos.add(archivo);
                    notificacion.setLsArchivos(lsArchivos);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    continue;
                }

                if (!lsNumerosCitacion.contains(Long.parseLong(datos[0].toString()))) {
                    lsResultado.add(notificacion);
                    lsNumerosCitacion.add(Long.parseLong(datos[0].toString()));
                }
            }
        }
        envioNotificacion.setTipoCorreo(EnumTipoCorreo.NOTIFICACION_E_NOTIFICA);
        envioNotificacion.setTipoNotificacion(EnumTipoNotificacion.NOTIFICACION_CITACIONES);
        OrganismoTransitoDTO organismo = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
        PaisDTO pais = iRSeguridadCirculemos.obtenerPais();
        Map<String, Object> variablesMensaje = new HashMap<>();
        variablesMensaje.put("organismo", escapeHtml4(organismo.getNombreOrganismo()));
        variablesMensaje.put("ciudad", escapeHtml4(organismo.getMunicipio().getNombre()));
        variablesMensaje.put("pais", escapeHtml4(pais.getNombre()));
        envioNotificacion.setVariablesMensaje(variablesMensaje);
        envioNotificacion.setLsNotificaciones(lsResultado);
        return envioNotificacion;
    }

    /**
     * Envia los comparendo para ser notificados
     * 
     */
    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public Integer[] enviarNotificaciones(int codOrganismo) {

        logger.debug(NotificacionTercerosEJB.class.getName().concat("::enviarNotificaciones(int)"));
        // Consulta de comparendo a notificar
        EnvioNotificacionDTO comparendos = iLNotificacion.consultarCitacionesNotifica(codOrganismo);
        // Envio notificaciones
        Integer[] resultado = iRFachadaNotificaciones.enviaNotificaciones(comparendos);
        return resultado;
    }

    @Override
    public List<EvidenciaDTO> obtenerEvidencia(Long cicomparendo, int codOrganismo) {

        logger.debug(NotificacionTercerosEJB.class.getName().concat("::obtenerEvidencia(Long, int)"));
        String urlEVidencia = null;
        List<EvidenciaDTO> evidenciasDTO = new ArrayList<>();

        // Consulta las evidencias por comparendo
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT e FROM Evidencia e ");
        jpql.append("WHERE e.comparendo.cicomparendo = :cicomparendo");
        filtros.put("cicomparendo", cicomparendo);

        GenericDao<Evidencia> dao = new GenericDao<>(Evidencia.class, em);
        List<Evidencia> resultado = dao.buildAndExecuteQuery(jpql, filtros);
        // Obtiene la URL de la evidencia
        String url = iRParametro.consultarValorParametro(EnumParametro.URL_EVIDENCIAS, codOrganismo);

        EvidenciaDTO evidenciaDTO = null;

        for (Evidencia evi : resultado) {
            evidenciaDTO = EvidenciaHelper.toLevel1DTO(evi);
            // Se completa la URL se reemplaza los /
            urlEVidencia = url + evidenciaDTO.getUrl();
            evidenciaDTO.setUrl(urlEVidencia);
            evidenciasDTO.add(evidenciaDTO);
        }
        return evidenciasDTO;
    }
}