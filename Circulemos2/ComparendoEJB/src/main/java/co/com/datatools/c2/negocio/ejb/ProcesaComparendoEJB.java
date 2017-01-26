package co.com.datatools.c2.negocio.ejb;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.dto.comparendo.BloqueoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.DetalleBloqueoDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.UnificacionInconsistenciasComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.entidades.BloqueoComparendo;
import co.com.datatools.c2.entidades.ProcesaComparendo;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.helpers.comparendos.BloqueoComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.DetalleBloqueoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.DetalleProcesamientoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaComparendoPersonaHelper;
import co.com.datatools.c2.negocio.helpers.extencion.ProcesaComparendoHelperExtend;
import co.com.datatools.c2.negocio.helpers.extencion.UnificacionInconCompHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILProcesaComparendo;
import co.com.datatools.c2.negocio.interfaces.IRProcesaComparendo;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;

/**
 * Logica asociada al procesar comparendo
 * 
 * @author giovanni.velandia
 */
@Stateless(name = "ProcesaComparendoEJB")
@LocalBean
public class ProcesaComparendoEJB implements IRProcesaComparendo, ILProcesaComparendo {
    private final static Logger LOGGER = Logger.getLogger(ProcesaComparendoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminNegocio iFachadaAdminNegocio;
    @EJB
    private IRCirculemosMailSender iRCirculemosMailSender;

    @Override
    public List<ProcesaComparendoDTO> consultarProcesaComparendo(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO) {
        LOGGER.debug("ProcesaComparendoEJB::consultarProcesaComparendo(ConsultaProcesaComparendoDTO)");
        List<ProcesaComparendoDTO> procesaComparendoDTOs = new ArrayList<ProcesaComparendoDTO>();

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT pc FROM ProcesaComparendo pc");
        jpql.append(" WHERE pc.organismoTransito.codigoOrganismo = :codigoOrganismo");

        if (consultaProcesaComparendoDTO.getReportado() != null && !consultaProcesaComparendoDTO.getReportado()) {
            jpql.append(" AND pc.fechaReporte IS NULL");
        } else if (consultaProcesaComparendoDTO.getReportado() != null && consultaProcesaComparendoDTO.getReportado()) {
            jpql.append(" AND pc.fechaReporte IS NOT NULL");
        }

        // Fecha inicial y Final de imposicion
        if (consultaProcesaComparendoDTO.getFechaImposicionInicial() != null
                && consultaProcesaComparendoDTO.getFechaImposicionFinal() != null) {
            jpql.append(
                    " AND pc.fechaInfraccion >= :fechaImposicionInicial AND pc.fechaInfraccion <= :fechaImposicionFinal");
        }

        // Fecha inicial y Final de registro
        if (consultaProcesaComparendoDTO.getFechaRegistroInicial() != null
                && consultaProcesaComparendoDTO.getFechaRegistroFinal() != null) {
            jpql.append(" AND pc.fechaOperacion >= :fechaRegistroInicial AND pc.fechaOperacion <= :fechaRegistroFinal");
        }

        // Numero de comparendo
        if (consultaProcesaComparendoDTO.getNumeroComparendo() != null) {
            jpql.append(" AND pc.numeroComparendo = :numeroComparendo");
        }

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Organismo de transito
        query.setParameter("codigoOrganismo", consultaProcesaComparendoDTO.getCodigoOrganismo());
        // Numero de comparendo
        if (consultaProcesaComparendoDTO.getNumeroComparendo() != null) {
            query.setParameter("numeroComparendo", consultaProcesaComparendoDTO.getNumeroComparendo());
        }
        // Fecha inicial y Final de imposicion
        if (consultaProcesaComparendoDTO.getFechaImposicionInicial() != null
                && consultaProcesaComparendoDTO.getFechaImposicionFinal() != null) {
            // Fecha Inicial
            query.setParameter("fechaImposicionInicial", consultaProcesaComparendoDTO.getFechaImposicionInicial());
            // Fecha Final
            query.setParameter("fechaImposicionFinal", consultaProcesaComparendoDTO.getFechaImposicionFinal());
        }
        // Fecha inicial y Final de registro
        if (consultaProcesaComparendoDTO.getFechaRegistroInicial() != null
                && consultaProcesaComparendoDTO.getFechaRegistroFinal() != null) {
            // Fecha Inicial
            query.setParameter("fechaRegistroInicial", consultaProcesaComparendoDTO.getFechaRegistroInicial());
            // Fecha Final
            query.setParameter("fechaRegistroFinal", consultaProcesaComparendoDTO.getFechaRegistroFinal());
        }

        @SuppressWarnings("unchecked")
        List<ProcesaComparendo> procesaComparendos = query.getResultList();
        if (procesaComparendos != null && !procesaComparendos.isEmpty()) {
            for (ProcesaComparendo procesaComparendo : procesaComparendos) {
                ProcesaComparendoDTO procesaComparendoDTO = new ProcesaComparendoDTO();
                procesaComparendoDTO = ProcesaComparendoHelper.toLevel1DTO(procesaComparendo);
                if (procesaComparendoDTO.getUsuarioPersona() != null) {
                    procesaComparendoDTO.getUsuarioPersona()
                            .setPersona(iFachadaAdminNegocio
                                    .consultarPersona(
                                            new PersonaDTO(procesaComparendo.getUsuarioPersona().getPersona().getId()))
                                    .get(0));
                }
                // Proceso comparendo persona
                List<ProcesaComparendoPersonaDTO> procesaComparendoPersonaDTOs = ProcesaComparendoPersonaHelper
                        .toListLevel1DTO(procesaComparendo.getProcesaComparendoPersonas());
                procesaComparendoDTO.setProcesaComparendoPersonas(procesaComparendoPersonaDTOs);

                // Detalle procesamiento
                List<DetalleProcesamientoDTO> detalleProcesamientoDTOs = DetalleProcesamientoHelper
                        .toListLevel1DTO(procesaComparendo.getDetalleProcesamientos());
                procesaComparendoDTO.setDetalleProcesamientos(detalleProcesamientoDTOs);
                procesaComparendoDTOs.add(procesaComparendoDTO);
            }
        }
        return procesaComparendoDTOs;
    }

    @Override
    public List<BloqueoComparendoDTO> consultarBloqueoComparendo(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO) {
        LOGGER.debug("ProcesaComparendoEJB::consultarBloqueoComparendo(ConsultaProcesaComparendoDTO)");
        List<BloqueoComparendoDTO> bloqueoComparendoDTOs = new ArrayList<BloqueoComparendoDTO>();

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT bc FROM BloqueoComparendo bc");
        jpql.append(" WHERE 1=1");

        if (consultaProcesaComparendoDTO.getReportado() != null && !consultaProcesaComparendoDTO.getReportado()) {
            jpql.append(" AND bc.fechaReporte IS NULL");
        } else if (consultaProcesaComparendoDTO.getReportado() != null && consultaProcesaComparendoDTO.getReportado()) {
            jpql.append(" AND bc.fechaReporte IS NOT NULL");
        }

        // Fecha inicial y Final de imposicion
        if (consultaProcesaComparendoDTO.getFechaImposicionInicial() != null
                && consultaProcesaComparendoDTO.getFechaImposicionFinal() != null) {
            jpql.append(
                    " AND bc.fechaInfraccion >= :fechaImposicionInicial AND bc.fechaInfraccion <= :fechaImposicionFinal");
        }

        // Numero de comparendo
        if (consultaProcesaComparendoDTO.getNumeroComparendo() != null) {
            jpql.append(" AND bc.numeroComparendo = :numeroComparendo");
        }

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Numero de comparendo
        if (consultaProcesaComparendoDTO.getNumeroComparendo() != null) {
            query.setParameter("numeroComparendo", consultaProcesaComparendoDTO.getNumeroComparendo());
        }
        // Fecha inicial y Final de imposicion
        if (consultaProcesaComparendoDTO.getFechaImposicionInicial() != null
                && consultaProcesaComparendoDTO.getFechaImposicionFinal() != null) {
            // Fecha Inicial
            query.setParameter("fechaImposicionInicial", consultaProcesaComparendoDTO.getFechaImposicionInicial());
            // Fecha Final
            query.setParameter("fechaImposicionFinal", consultaProcesaComparendoDTO.getFechaImposicionFinal());
        }

        @SuppressWarnings("unchecked")
        List<BloqueoComparendo> bloqueoComparendos = query.getResultList();
        if (bloqueoComparendos != null && !bloqueoComparendos.isEmpty()) {
            for (BloqueoComparendo bloqueoComparendo : bloqueoComparendos) {
                BloqueoComparendoDTO bloqueoComparendoDTO = new BloqueoComparendoDTO();
                bloqueoComparendoDTO = BloqueoComparendoHelper.toLevel1DTO(bloqueoComparendo);
                if (bloqueoComparendoDTO.getUsuarioPersona() != null) {
                    bloqueoComparendoDTO.getUsuarioPersona()
                            .setPersona(iFachadaAdminNegocio
                                    .consultarPersona(
                                            new PersonaDTO(bloqueoComparendo.getUsuarioPersona().getPersona().getId()))
                                    .get(0));
                }

                // Detalle procesamiento
                List<DetalleBloqueoDTO> detalleBloqueoDTOs = DetalleBloqueoHelper
                        .toListLevel1DTO(bloqueoComparendo.getDetalleBloqueantes());
                bloqueoComparendoDTO.setDetalleBloqueantes(detalleBloqueoDTOs);
                bloqueoComparendoDTOs.add(bloqueoComparendoDTO);
            }
        }

        return bloqueoComparendoDTOs;
    }

    @Override
    public List<UnificacionInconsistenciasComparendoDTO> unificacionInconsistencias(
            List<ProcesaComparendoDTO> procesaComparendoDTOs, List<BloqueoComparendoDTO> bloqueoComparendoDTOs) {
        LOGGER.debug(
                "ProcesaComparendoEJB.unificacionInconsistencias(List<ProcesaComparendoDTO>,List<BloqueoComparendoDTO>)");

        List<UnificacionInconsistenciasComparendoDTO> unificacionInconsistenciasComparendoDTOs = new ArrayList<UnificacionInconsistenciasComparendoDTO>();

        UnificacionInconsistenciasComparendoDTO unificacionInconsistenciasComparendoDTO = null;
        // Procesa Comparendo
        if (procesaComparendoDTOs != null && !procesaComparendoDTOs.isEmpty()) {
            for (ProcesaComparendoDTO procesaComparendoDTO : procesaComparendoDTOs) {

                // Infractor
                ProcesaComparendoPersonaDTO procesaComparendoPersDTO = null;
                for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                        .getProcesaComparendoPersonas()) {
                    if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                            .equals(EnumTipoPersonaComparendo.INFRACTOR.getValue())) {
                        procesaComparendoPersDTO = procesaComparendoPersonaDTO;
                        break;
                    }
                }

                for (DetalleProcesamientoDTO detalleProcesamientoDTO : procesaComparendoDTO
                        .getDetalleProcesamientos()) {
                    unificacionInconsistenciasComparendoDTO = UnificacionInconCompHelperExtend
                            .toUnificacionInconsistenciasComparendoDTO(procesaComparendoDTO, detalleProcesamientoDTO,
                                    procesaComparendoPersDTO);
                    unificacionInconsistenciasComparendoDTOs.add(unificacionInconsistenciasComparendoDTO);
                }
            }
        }

        // Bloqueo comparendo
        if (bloqueoComparendoDTOs != null && !bloqueoComparendoDTOs.isEmpty()) {
            for (BloqueoComparendoDTO bloqueoComparendoDTO : bloqueoComparendoDTOs) {
                for (DetalleBloqueoDTO detalleBloqueoDTO : bloqueoComparendoDTO.getDetalleBloqueantes()) {
                    unificacionInconsistenciasComparendoDTO = UnificacionInconCompHelperExtend
                            .toUnificacionInconsistenciasComparendoDTO(bloqueoComparendoDTO, detalleBloqueoDTO);
                    unificacionInconsistenciasComparendoDTOs.add(unificacionInconsistenciasComparendoDTO);
                }
            }
        }

        return unificacionInconsistenciasComparendoDTOs;
    }

    @Override
    public List<UnificacionInconsistenciasComparendoDTO> consularErrorComparendo(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO) {
        LOGGER.debug("ProcesaComparendoEJB.consularErrorComparendo(ConsultaProcesaComparendoDTO)");

        List<ProcesaComparendoDTO> procesaComparendoDTOs = consultarProcesaComparendo(consultaProcesaComparendoDTO);
        List<BloqueoComparendoDTO> bloqueoComparendoDTOs = consultarBloqueoComparendo(consultaProcesaComparendoDTO);

        List<UnificacionInconsistenciasComparendoDTO> unificacionInconsistenciasComparendoDTOs = unificacionInconsistencias(
                procesaComparendoDTOs, bloqueoComparendoDTOs);
        return unificacionInconsistenciasComparendoDTOs;
    }

    @Override
    public boolean existeProcesaComparendo(String numeroComparendo, int codigoOrganismo) {
        LOGGER.debug("ProcesaComparendoEJB.existeProcesaComparendo(String,int)");

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT pc FROM ProcesaComparendo pc");
        jpql.append(" WHERE pc.numeroComparendo = :numeroComparendo");
        jpql.append(" AND pc.organismoTransito.codigoOrganismo = :codigoOrganismo");
        jpql.append(" AND pc.comparendo IS NULL");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Numero de comparendo
        query.setParameter("numeroComparendo", numeroComparendo);
        // Codigo Organismo de transito
        query.setParameter("codigoOrganismo", codigoOrganismo);

        @SuppressWarnings("unchecked")
        List<ProcesaComparendo> procesaComparendos = query.getResultList();
        boolean existe = false;
        if (procesaComparendos != null && !procesaComparendos.isEmpty()) {
            existe = true;
        }

        return existe;
    }

    @Override
    public ProcesaComparendoDTO consultarProcesaComparendo(Long idProcesaComparendo) {
        LOGGER.debug("ProcesaComparendoEJB::consultarProcesaComparendo(Long)");
        ProcesaComparendoDTO procesaComparendoDTO = null;

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT pc FROM ProcesaComparendo pc");
        jpql.append(" WHERE pc.id = :idProcesaComparendo");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Procesa comparendo
        query.setParameter("idProcesaComparendo", idProcesaComparendo);

        @SuppressWarnings("unchecked")
        List<ProcesaComparendo> procesaComparendos = query.getResultList();
        if (procesaComparendos != null && !procesaComparendos.isEmpty()) {
            ProcesaComparendo procesaComparendo = procesaComparendos.get(0);
            procesaComparendoDTO = ProcesaComparendoHelper.toLevel1DTO(procesaComparendo);
        }
        return procesaComparendoDTO;
    }

    @Override
    public Integer enviarInconsistenciasComparendos(OrganismoTransitoDTO organismoDTO) {

        Integer totalInconsistencias = 0;
        if (organismoDTO != null) {

            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO = new ConsultaProcesaComparendoDTO();
            consultaProcesaComparendoDTO.setCodigoOrganismo(organismoDTO.getCodigoOrganismo());
            consultaProcesaComparendoDTO.setReportado(false);

            List<UnificacionInconsistenciasComparendoDTO> unificacionInconsistenciasComparendoDTO = consularErrorComparendoReporte(
                    consultaProcesaComparendoDTO);

            if (unificacionInconsistenciasComparendoDTO != null && !unificacionInconsistenciasComparendoDTO.isEmpty()) {

                totalInconsistencias = unificacionInconsistenciasComparendoDTO.size();
                Date fechaSistema = UtilFecha.buildCalendar().getTime();

                Query query;

                for (UnificacionInconsistenciasComparendoDTO unificacion : unificacionInconsistenciasComparendoDTO) {
                    unificacion.setFechaReporte(fechaSistema);
                    // TODO actualizar ProcesaComparendo y BloqueaComparendo

                    if (unificacion.getTabla().equalsIgnoreCase("PROCESA_COMPARENDO")) {
                        query = em
                                .createNativeQuery(
                                        "UPDATE procesa_comparendo set fecha_reporte = ? WHERE id_procesamiento = ? ")
                                .setParameter(1, unificacion.getFechaReporte()).setParameter(2, unificacion.getId());
                        query.executeUpdate();
                    } else if (unificacion.getTabla().equalsIgnoreCase("BLOQUEO_COMPARENDO")) {
                        query = em
                                .createNativeQuery(
                                        "UPDATE bloqueo_comparendo set fecha_reporte = ? WHERE id_bloqueo_comparendo = ? ")
                                .setParameter(1, unificacion.getFechaReporte()).setParameter(2, unificacion.getId());
                        query.executeUpdate();
                    }

                }

                // Creacion de archivo
                byte[] archivoAdjunto = ProcesaComparendoHelperExtend
                        .generaArchivo(unificacionInconsistenciasComparendoDTO);

                ArchivoTransportableDTO archivoTransportableDTO = new ArchivoTransportableDTO();
                archivoTransportableDTO.setNombre(
                        ConstantesComparendo.NOMBRE_REPORTE_INCONSISTENCIAS + "." + EnumTipoArchivo.XLS.name());
                archivoTransportableDTO.setContenido(archivoAdjunto);

                Map<String, Object> variablesMensaje = new HashMap<>();
                SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
                variablesMensaje.put(ConstantesComparendo.FECHA_REPORTE_INCONSISTENCIAS,
                        formateador.format(fechaSistema));
                variablesMensaje.put(ConstantesComparendo.TOTAL_INCONSISTENCIAS, totalInconsistencias);
                variablesMensaje.put(ConstantesComparendo.ORGANISMO, organismoDTO.getNombreOrganismo());
                ArrayList<ArchivoTransportableDTO> archivos = new ArrayList<ArchivoTransportableDTO>();
                archivos.add(archivoTransportableDTO);

                // Envio de correo
                iRCirculemosMailSender.enviarCorreo(organismoDTO.getCodigoOrganismo(),
                        EnumTipoCorreo.NOTIFICACION_INCONSISTENCIAS_COMPARENDOS, null, variablesMensaje, archivos);
            }
        }
        return totalInconsistencias;
    }

    @Override
    public void actualizarProcesaComparendo(ProcesaComparendoDTO procesaComparendoDTO) {
        ProcesaComparendo procesaComparendo = em.find(ProcesaComparendo.class, procesaComparendoDTO.getId());
        if (procesaComparendo != null) {
            procesaComparendo = ProcesaComparendoHelper.toLevel1Entity(procesaComparendoDTO, null);
            em.merge(procesaComparendo);
        }
    }

    @Override
    public void actualizarBloqueoComparendo(BloqueoComparendoDTO bloqueoComparendoDTO) {
        BloqueoComparendo procesaComparendo = em.find(BloqueoComparendo.class, bloqueoComparendoDTO.getId());
        if (procesaComparendo != null) {
            procesaComparendo = BloqueoComparendoHelper.toLevel1Entity(bloqueoComparendoDTO, null);
            em.merge(procesaComparendo);
        }
    }

    @Override
    public List<UnificacionInconsistenciasComparendoDTO> consularErrorComparendoReporte(
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO) {
        LOGGER.debug("ProcesaComparendoEJB.consularErrorComparendoReporte(ConsultaProcesaComparendoDTO)");

        List<UnificacionInconsistenciasComparendoDTO> unificacionInconsistenciasComparendoDTOs = new ArrayList<>();

        StringBuilder consulta1 = new StringBuilder();
        StringBuilder consulta2 = new StringBuilder();

        // Procesa comparendo
        consulta1.append("SELECT pc.id_procesamiento id, pc.numero_comparendo numeroComparendo,  ");
        consulta1.append("toc.nombre codigoOrigen, pc.codigo_infraccion codigoInfraccion,  ");
        consulta1.append("pc.fecha_recepcion fechaRegistro, pc.fecha_reporte fechaReporte,  ");
        consulta1.append("pc.fecha_infraccion fechaInfraccion, pc.hora_infraccion horaInfraccion,  ");
        consulta1.append("tip.nombre tipoIdentificacionInfractor,  ");
        consulta1.append("pcp.numero_identificacion numeroIdentificacionInfractor,  ");
        consulta1.append("pcp.nombre1 nombre1Infractor, pcp.nombre2 nombre2Infractor,  ");
        consulta1.append("pcp.apellido1 apellido1Infractor, pcp.apellido2 apellido2Infractor,  ");
        consulta1.append(
                "CONCAT(up.login,' (',p.nombre1,' ',p.apellido1,')') usuarioPersona, ce.nombre_campo nombreCampoInconsistencias,  ");
        consulta1.append("ep.nombre errorProcesamiento, pc.placa_vehiculo placaVehiculo, ");
        consulta1.append("'PROCESA_COMPARENDO' tabla ");
        consulta1.append("FROM procesa_comparendo pc ");
        consulta1.append("INNER JOIN procesa_comparendo_persona pcp ON pc.id_procesamiento = pcp.id_procesamiento ");
        consulta1.append("INNER JOIN tipo_origen_comparendo toc ON pc.codigo_origen = toc.codigo_origen ");
        consulta1.append(
                "INNER JOIN tipo_identificacion_persona tip ON pcp.id_tipo_identificacion = tip.id_tipo_identificacion ");
        consulta1.append("INNER JOIN usuario_persona up ON pc.id_usuario = up.id_usuario ");
        consulta1.append("INNER JOIN persona p ON up.id_persona = p.id_persona ");
        consulta1.append("INNER JOIN detalle_procesamiento dp ON pc.id_procesamiento = dp.id_procesamiento ");
        consulta1.append("INNER JOIN campo_entidad ce ON dp.codigo_campo = ce.codigo_campo ");
        consulta1.append("INNER JOIN error_procesamiento ep ON dp.id_error_procesamiento = ep.id_error_procesamiento ");
        consulta1.append("WHERE 1 = 1 ");

        // Bloquea comparendo
        consulta2.append("SELECT bc.id_bloqueo_comparendo id, bc.numero_comparendo numeroComparendo,  ");
        consulta2.append("toc.nombre codigoOrigen, bc.codigo_infraccion codigoInfraccion,  ");
        consulta2.append("bc.fecha_registro fechaRegistro, bc.fecha_reporte fechaReporte,  ");
        consulta2.append("bc.fecha_infraccion fechaInfraccion, bc.hora_infraccion horaInfraccion,  ");
        consulta2.append("tip.nombre tipoIdentificacionInfractor, ");
        consulta2.append("bc.numero_identificacion_infract numeroIdentificacionInfractor,  ");
        consulta2.append("bc.nombre1_infractor nombre1Infractor, bc.nombre2_infractor nombre2Infractor,  ");
        consulta2.append("bc.apellido1_infractor apellido1Infractor, bc.apellido2_infractor apellido2Infractor, ");
        consulta2.append(
                "CONCAT(up.login,' (',p.nombre1,' ',p.apellido1,')') usuarioPersona, ce.nombre_campo nombreCampoInconsistencias,  ");
        consulta2.append("ep.nombre errorProcesamiento, bc.placa_vehiculo placaVehiculo, ");
        consulta2.append("'BLOQUEO_COMPARENDO' tabla ");
        consulta2.append("FROM bloqueo_comparendo bc ");
        consulta2.append("INNER JOIN tipo_origen_comparendo toc ON bc.codigo_origen = toc.codigo_origen ");
        consulta2.append("INNER JOIN detalle_bloqueo db ON bc.id_bloqueo_comparendo = db.id_bloqueo_comparendo ");
        consulta2.append(
                "INNER JOIN tipo_identificacion_persona tip ON bc.id_tipo_identificacion_infract = tip.id_tipo_identificacion ");
        consulta2.append("INNER JOIN usuario_persona up ON bc.id_usuario = up.id_usuario ");
        consulta2.append("INNER JOIN persona p ON up.id_persona = p.id_persona ");
        consulta2.append("INNER JOIN campo_entidad ce ON db.id_campo_entidad = ce.codigo_campo ");
        consulta2
                .append("INNER JOIN  error_procesamiento ep ON db.id_error_procesamiento = ep.id_error_procesamiento ");
        consulta2.append("WHERE 1 = 1 ");

        // Reportado
        if (consultaProcesaComparendoDTO.getReportado() != null && !consultaProcesaComparendoDTO.getReportado()) {
            consulta1.append("AND pc.fecha_reporte IS NULL ");
            consulta2.append("AND bc.fecha_reporte IS NULL ");
        } else if (consultaProcesaComparendoDTO.getReportado() != null && consultaProcesaComparendoDTO.getReportado()) {
            consulta1.append("AND pc.fecha_reporte IS NOT NULL ");
            consulta2.append("AND bc.fecha_reporte IS NOT NULL ");
        }

        // Fecha inicial y Final de imposicion
        if (consultaProcesaComparendoDTO.getFechaImposicionInicial() != null
                && consultaProcesaComparendoDTO.getFechaImposicionFinal() != null) {
            consulta1.append(
                    "AND pc.fecha_infraccion >= :fechaImposicionInicial AND pc.fecha_infraccion <= :fechaImposicionFinal ");
            consulta2.append(
                    "AND bc.fecha_infraccion >= :fechaImposicionInicial AND bc.fecha_infraccion <= :fechaImposicionFinal ");
        }

        // Fecha inicial y Final de registro
        if (consultaProcesaComparendoDTO.getFechaRegistroInicial() != null
                && consultaProcesaComparendoDTO.getFechaRegistroFinal() != null) {
            consulta1.append(
                    "AND pc.fecha_recepcion >= :fechaRegistroInicial AND pc.fecha_recepcion <= :fechaRegistroFinal ");
            consulta2.append(
                    "AND bc.fecha_registro >= :fechaRegistroInicial AND bc.fecha_registro <= :fechaRegistroFinal ");
        }

        // Numero de comparendo
        if (consultaProcesaComparendoDTO.getNumeroComparendo() != null) {
            consulta1.append("AND pc.numero_comparendo = :numeroComparendo ");
            consulta2.append("AND bc.numero_comparendo = :numeroComparendo ");
        }

        consulta1.append("UNION ");

        Query query = em.createNativeQuery(consulta1.toString() + consulta2.toString());

        /*
         * Parametros
         */

        // Numero de comparendo
        if (consultaProcesaComparendoDTO.getNumeroComparendo() != null) {
            query.setParameter("numeroComparendo", consultaProcesaComparendoDTO.getNumeroComparendo());
        }
        // Fecha inicial y Final de imposicion
        if (consultaProcesaComparendoDTO.getFechaImposicionInicial() != null
                && consultaProcesaComparendoDTO.getFechaImposicionFinal() != null) {
            // Fecha Inicial
            query.setParameter("fechaImposicionInicial", consultaProcesaComparendoDTO.getFechaImposicionInicial());
            // Fecha Final
            query.setParameter("fechaImposicionFinal", consultaProcesaComparendoDTO.getFechaImposicionFinal());
        }
        // Fecha inicial y Final de registro
        if (consultaProcesaComparendoDTO.getFechaRegistroInicial() != null
                && consultaProcesaComparendoDTO.getFechaRegistroFinal() != null) {
            // Fecha Inicial
            query.setParameter("fechaRegistroInicial", consultaProcesaComparendoDTO.getFechaRegistroInicial());
            // Fecha Final
            query.setParameter("fechaRegistroFinal", consultaProcesaComparendoDTO.getFechaRegistroFinal());
        }

        List<Object[]> inconsistencias = Utilidades.safeList(query.getResultList());

        if (inconsistencias != null) {
            UnificacionInconsistenciasComparendoDTO unificacion;
            for (Object[] campo : inconsistencias) {
                unificacion = new UnificacionInconsistenciasComparendoDTO();
                int i = 0;
                unificacion.setId(((BigInteger) campo[i++]).longValue());
                unificacion.setNumeroComparendo((String) campo[i++]);
                unificacion.setNombreOrigen((String) campo[i++]);
                unificacion.setCodigoInfraccion((String) campo[i++]);
                unificacion.setFechaRegistro((Date) campo[i++]);
                unificacion.setFechaReporte((Date) campo[i++]);
                unificacion.setFechaInfraccion((Date) campo[i++]);
                unificacion.setHoraInfraccion((Date) campo[i++]);
                unificacion.setNombreTipoIdentificacionInfractor((String) campo[i++]);
                unificacion.setNumeroIdentificacionInfractor((String) campo[i++]);
                unificacion.setNombre1Infractor((String) campo[i++]);
                unificacion.setNombre2Infractor((String) campo[i++]);
                unificacion.setApellido1Infractor((String) campo[i++]);
                unificacion.setApellido2Infractor((String) campo[i++]);
                unificacion.setLoginUsuario((String) campo[i++]);
                unificacion.setNombreCampoInconsistencias((String) campo[i++]);
                unificacion.setErrorProcesamiento((String) campo[i++]);
                unificacion.setPlacaVehiculo((String) campo[i++]);
                unificacion.setTabla((String) campo[i++]);
                unificacion.setFechaHoraInfraccion((String) campo[6].toString() + " " + campo[7].toString());
                unificacionInconsistenciasComparendoDTOs.add(unificacion);
            }
        }

        return unificacionInconsistenciasComparendoDTOs;
    }
}