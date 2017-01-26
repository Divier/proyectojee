package co.com.datatools.c2.negocio.ejb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.DetallePagoConciliacionErrorDTO;
import co.com.datatools.c2.dto.DetallePagoDTO;
import co.com.datatools.c2.dto.DetallePagoInconsistenciaDTO;
import co.com.datatools.c2.dto.DetallePagoInconsistenciaResDTO;
import co.com.datatools.c2.dto.ErrorInconsistenciaPagoDTO;
import co.com.datatools.c2.dto.FiltroConsultaInconsistenciasDTO;
import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.PagoInconsistenciaDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.entidades.DetallePago;
import co.com.datatools.c2.entidades.DetallePagoInconsistencia;
import co.com.datatools.c2.entidades.ErrorInconsistenciaPago;
import co.com.datatools.c2.entidades.Pago;
import co.com.datatools.c2.entidades.PagoInconsistencia;
import co.com.datatools.c2.enumeracion.EnumEstadoPago;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.helpers.DetallePagoConciliacionErrorHelper;
import co.com.datatools.c2.negocio.helpers.DetallePagoHelper;
import co.com.datatools.c2.negocio.helpers.DetallePagoInconsistenciaHelper;
import co.com.datatools.c2.negocio.helpers.ErrorInconsistenciaPagoHelper;
import co.com.datatools.c2.negocio.helpers.PagoHelper;
import co.com.datatools.c2.negocio.helpers.PagoInconsistenciaHelper;
import co.com.datatools.c2.negocio.interfaces.ILInconsistenciaRecaudo;
import co.com.datatools.c2.negocio.interfaces.IRInconsistenciaRecaudo;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.cartera.IRCarteraContable;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.InconsistenciaRecaudoUtil;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;

@Stateless(name = "InconsistenciaRecaudoEJB")
@LocalBean
public class InconsistenciaRecaudoEJB implements IRInconsistenciaRecaudo, ILInconsistenciaRecaudo {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    IRCirculemosMailSender circulemosMailSenderEJB;
    @EJB
    IRFachadaAdminNegocio fachadaAdminNegocioEJB;
    @EJB
    IRSeguridadCirculemos seguridadCirculemosEJB;
    @EJB
    IRCatalogo catalogoEJB;
    @EJB
    IRCarteraContable cartera;
    @EJB
    ILInconsistenciaRecaudo inconsistenciaRecaudoEJB;

    private final static Logger logger = Logger.getLogger(InconsistenciaRecaudoEJB.class.getName());

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer enviarInconsistencias(Integer codigoOrganismo) { // agregar int codigoOrganismo
        logger.debug("InconsistenciaRecaudoEJB.enviarInconsistencias()");

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" pi.codigo_organismo,");
        sb.append("pi.fecha_transaccion,");
        sb.append("pi.numero_pago,");
        sb.append("pi.numero_cuenta,");
        sb.append("tcb.codigo as codigo_cuenta,");
        sb.append("b.codigo as codigo_banco,");
        sb.append("pi.total_efectivo,");
        sb.append("pi.total_cheque,");
        sb.append("pi.total_recaudo,");
        sb.append("tip.codigo as codigo_tipo_ident,");
        sb.append("pi.numero_documento,");
        sb.append("dpi.numero_obligacion,");
        sb.append("dpi.valor_obligacion,");
        sb.append("tob.codigo as codigo_recaudo,");
        sb.append("dpi.numero_cuota,");
        sb.append("pi.id_pago_inconsistencia,");
        sb.append("CONVERT(VARCHAR(100),STUFF((SELECT '|'+eip.descripcion from detalle_pago_incon_error dpie");
        sb.append(
                " INNER JOIN error_inconsistencia_pago eip ON(dpie.id_error_inconsistencia=eip.id_error_inconsistencia)");
        sb.append(
                " where dpie.id_detalle_pago_inconsistencia=dpi.id_detalle_pago_inconsistencia FOR XML PATH('')),1,1,'')) descripcion");
        sb.append(" FROM pago_inconsistencia pi");
        sb.append(
                " LEFT JOIN tipo_identificacion_persona tip ON(tip.id_tipo_identificacion=pi.id_tipo_identificacion)");
        sb.append(" LEFT JOIN banco b ON(b.id_banco=pi.id_banco)");
        sb.append(" LEFT JOIN tipo_cuenta_bancaria tcb ON(pi.id_tipo_cuenta=tcb.id_tipo_cuenta_banca)");
        sb.append(
                " INNER JOIN detalle_pago_inconsistencia dpi ON(dpi.id_pago_inconsistencia=pi.id_pago_inconsistencia)");
        sb.append(" LEFT JOIN tipo_obligacion tob ON(tob.codigo_tipo_obligacion=dpi.id_tipo_recaudo)");
        sb.append(" WHERE pi.fecha_generacion_reporte IS NULL");
        sb.append(" AND pi.codigo_organismo=:organismo");
        logger.info(sb.toString());
        Query query = em.createNativeQuery(sb.toString());
        query.setParameter("organismo", codigoOrganismo);
        List<Object[]> resultado = query.getResultList();

        List<DetallePagoInconsistenciaResDTO> detallePagoInconsistenciaResDTOList = new ArrayList<>();

        for (Object[] campo : resultado) {

            DetallePagoInconsistenciaResDTO detallePagoInconsistenciaResDTO = new DetallePagoInconsistenciaResDTO();
            detallePagoInconsistenciaResDTO.setOrganismoTransito((Integer) campo[0]);
            detallePagoInconsistenciaResDTO
                    .setFechaTransaccion(Utilidades.dateToStringFormatApp((Date) campo[1], false));
            detallePagoInconsistenciaResDTO.setHoraTransaccion(Utilidades.hourToStringFormatApp((Date) campo[1]));
            detallePagoInconsistenciaResDTO.setNumeroRecaudo(campo[2].toString());
            if (campo[3] != null) {
                detallePagoInconsistenciaResDTO.setCuenta(campo[3].toString());
            }
            if (campo[4] != null) {
                detallePagoInconsistenciaResDTO.setCodigoTipoCuenta(campo[4].toString());
            }
            if (campo[5] != null) {
                detallePagoInconsistenciaResDTO.setCodigoBanco(campo[5].toString());
            }
            detallePagoInconsistenciaResDTO.setTotalEfectivo((BigDecimal) (campo[6]));
            detallePagoInconsistenciaResDTO.setTotalCheque((BigDecimal) (campo[7]));
            detallePagoInconsistenciaResDTO.setTotalRecaudo((BigDecimal) (campo[8]));
            if (campo[9] != null) {
                detallePagoInconsistenciaResDTO.setCodigoTipoDocumento(campo[9].toString());
            }
            if (campo[10] != null) {
                detallePagoInconsistenciaResDTO.setNumeroIdentificacion(campo[10].toString());
            }
            detallePagoInconsistenciaResDTO.setObligacionPagada(campo[11].toString());
            detallePagoInconsistenciaResDTO.setValorObligacion((BigDecimal) (campo[12]));
            if (campo[13] != null) {
                detallePagoInconsistenciaResDTO.setCodigoTipoRecaudo(campo[13].toString());
            }
            detallePagoInconsistenciaResDTO.setNumeroCuota((int) campo[14]);
            detallePagoInconsistenciaResDTO.setInconsistencias(campo[16].toString());
            detallePagoInconsistenciaResDTO.setIdPagoInconsistencia((BigInteger) (campo[15]));

            detallePagoInconsistenciaResDTOList.add(detallePagoInconsistenciaResDTO);
        }
        return enviarInconsistenciasAux(detallePagoInconsistenciaResDTOList, codigoOrganismo);
    }

    @Override
    public void registrarInconsistencia(PagoInconsistenciaDTO pagoInconsistenciaDTO) {
        logger.debug("InconsistenciaRecaudoEJB.registrarInconsistencia()");
        PagoInconsistencia pagoInconsistencia = new PagoInconsistencia();
        pagoInconsistencia = PagoInconsistenciaHelper.toLevel0Entity(pagoInconsistenciaDTO, pagoInconsistencia);
        if (pagoInconsistenciaDTO.getErrorInconsistenciaPagos() != null
                && !pagoInconsistenciaDTO.getErrorInconsistenciaPagos().isEmpty()) {
            List<ErrorInconsistenciaPago> errorInconsistenciaPagoList = ErrorInconsistenciaPagoHelper
                    .toListLevel0Entity(pagoInconsistenciaDTO.getErrorInconsistenciaPagos());
            pagoInconsistencia.setErrorInconsistenciaPagos(errorInconsistenciaPagoList);
        }
        em.persist(pagoInconsistencia);

        if (pagoInconsistenciaDTO.getDetallePagoInconsistencias() != null
                && !pagoInconsistenciaDTO.getDetallePagoInconsistencias().isEmpty()) {
            for (DetallePagoInconsistenciaDTO detallePagoInconsistenciaDTO : pagoInconsistenciaDTO
                    .getDetallePagoInconsistencias()) {
                DetallePagoInconsistencia detallePagoInconsistencia = new DetallePagoInconsistencia();
                detallePagoInconsistencia = DetallePagoInconsistenciaHelper.toLevel0Entity(detallePagoInconsistenciaDTO,
                        detallePagoInconsistencia);
                List<ErrorInconsistenciaPago> error = ErrorInconsistenciaPagoHelper
                        .toListLevel0Entity(detallePagoInconsistenciaDTO.getErrorInconsistenciaPagos());
                detallePagoInconsistencia.setErrorInconsistenciaPagos(error);
                detallePagoInconsistencia.setPagoInconsistencia(pagoInconsistencia);
                em.persist(detallePagoInconsistencia);
            }
        }
    }

    private List<PagoInconsistenciaDTO> consultarPagoInconsistencias(int organismoTransito, Date fechaInicial,
            Date fechaFinal) {
        logger.debug("InconsistenciaRecaudoEJB.consultarPagoInconsistencias(int,Date,Date)");
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT pi from PagoInconsistencia pi");
        jpql.append(" LEFT JOIN FETCH pi.detallePagoInconsistencias dpi");
        jpql.append(" LEFT JOIN pi.errorInconsistenciaPagos eip");
        jpql.append(" WHERE pi.codigoOrganismo=:codOrganismo");

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("codOrganismo", organismoTransito);

        if (fechaInicial != null && fechaFinal != null) { // Validar contra parametro de consulta
            jpql.append(" AND pi.fechaGeneracionReporte BETWEEN :fechaInicial AND :fechaFinal");
            filtros.put("fechaInicial", fechaInicial);
            filtros.put("fechaFinal", fechaFinal);
        } else {
            jpql.append(" AND pi.fechaGeneracionReporte IS NULL");
        }

        GenericDao<PagoInconsistencia> dao = new GenericDao<PagoInconsistencia>(PagoInconsistencia.class, em);
        List<PagoInconsistencia> resultadoConsulta = dao.buildAndExecuteQuery(jpql, filtros);

        List<PagoInconsistenciaDTO> pagoInconsistenciaDTOList = new ArrayList<>();
        if (resultadoConsulta != null && !resultadoConsulta.isEmpty()) {
            for (PagoInconsistencia pagoInconsistencia : resultadoConsulta) {
                List<ErrorInconsistenciaPagoDTO> errorInconsistenciaPagoDTOList = new ArrayList<>();
                if (pagoInconsistencia.getErrorInconsistenciaPagos() != null
                        && !pagoInconsistencia.getErrorInconsistenciaPagos().isEmpty()) {
                    errorInconsistenciaPagoDTOList = ErrorInconsistenciaPagoHelper
                            .toListLevel0DTO(pagoInconsistencia.getErrorInconsistenciaPagos());
                }
                List<DetallePagoInconsistenciaDTO> detallePagoInconsistenciaDTOList = new ArrayList<>();
                if (pagoInconsistencia.getDetallePagoInconsistencias() != null
                        && !pagoInconsistencia.getDetallePagoInconsistencias().isEmpty()) {
                    for (DetallePagoInconsistencia detallePagoInconsistencia : pagoInconsistencia
                            .getDetallePagoInconsistencias()) {
                        List<ErrorInconsistenciaPagoDTO> errorInconsistenciaPagoDTOListAux = null;
                        errorInconsistenciaPagoDTOListAux = ErrorInconsistenciaPagoHelper
                                .toListLevel0DTO(detallePagoInconsistencia.getErrorInconsistenciaPagos());
                        DetallePagoInconsistenciaDTO detallePagoInconsistenciaDTO = DetallePagoInconsistenciaHelper
                                .toLevel0DTO(detallePagoInconsistencia);
                        detallePagoInconsistenciaDTO.setErrorInconsistenciaPagos(errorInconsistenciaPagoDTOListAux);
                        detallePagoInconsistenciaDTOList.add(detallePagoInconsistenciaDTO);
                    }

                }
                PagoInconsistenciaDTO pagoInconsistenciaDTO = PagoInconsistenciaHelper.toLevel0DTO(pagoInconsistencia);
                pagoInconsistenciaDTO.setErrorInconsistenciaPagos(errorInconsistenciaPagoDTOList);
                pagoInconsistenciaDTO.setDetallePagoInconsistencias(detallePagoInconsistenciaDTOList);
                pagoInconsistenciaDTOList.add(pagoInconsistenciaDTO);
            }

        }
        return pagoInconsistenciaDTOList;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void actualizarFechaRegistroInconsistenciaRecaudo(Long idPagoInconsistencia) {
        logger.debug("InconsistenciaRecaudoEJB.actualizarFechaRegistroInconsistenciaRecaudo(Long)");
        PagoInconsistencia pago = em.find(PagoInconsistencia.class, idPagoInconsistencia);
        pago.setFechaGeneracionReporte(new Date());
        em.merge(pago);

    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    private Integer enviarInconsistenciasAux(List<DetallePagoInconsistenciaResDTO> pagoInconsistenciaRespuestaDTOList,
            Integer codigoOrganismo) {
        logger.debug("InconsistenciaRecaudoEJB.enviarInconsistenciasAux(List<PagoInconsistenciaRespuestaDTO>)");
        Integer totalInconsistencias = 0;
        final String NOMBRE_ARCHIVO = "NOTIFICACIÓN DE LAS INCONSISTENCIAS DE RECAUDO";

        if (!pagoInconsistenciaRespuestaDTOList.isEmpty()) {
            Object[] respuesta = InconsistenciaRecaudoUtil.generaArchivo(pagoInconsistenciaRespuestaDTOList);

            byte[] archivo = (byte[]) respuesta[0];
            totalInconsistencias = (Integer) respuesta[1];
            ArrayList<ArchivoTransportableDTO> archivosTransportables = new ArrayList<ArchivoTransportableDTO>();
            ArchivoTransportableDTO archivoTransportable = new ArchivoTransportableDTO();
            archivoTransportable.setContenido(archivo);
            archivoTransportable.setNombre(NOMBRE_ARCHIVO + "." + EnumTipoArchivo.XLSX.name());
            archivosTransportables.add(archivoTransportable);
            Map<String, Object> variables = new HashMap<>();
            OrganismoTransitoDTO organismo = new OrganismoTransitoDTO();
            organismo.setCodigoOrganismo(codigoOrganismo);
            List<OrganismoTransitoDTO> organismoTransitoList = fachadaAdminNegocioEJB
                    .consultarOrganismoTransito(organismo);
            Calendar rightNow = Calendar.getInstance();
            variables.put("dia_fecha", rightNow.get(Calendar.DAY_OF_MONTH));
            variables.put("mes_fecha", rightNow.get(Calendar.MONTH));
            variables.put("anio_fecha", rightNow.get(Calendar.YEAR));
            variables.put("cantidad_inconsistencias", totalInconsistencias);
            variables.put("organismo", organismoTransitoList.get(0).getNombreOrganismo());

            circulemosMailSenderEJB.enviarCorreo(codigoOrganismo, EnumTipoCorreo.ENVIO_INCONSISTENCIAS_RECAUDO, null,
                    variables, archivosTransportables);

            for (DetallePagoInconsistenciaResDTO detallePagoInconsistencia : pagoInconsistenciaRespuestaDTOList) {
                inconsistenciaRecaudoEJB.actualizarFechaRegistroInconsistenciaRecaudo(
                        detallePagoInconsistencia.getIdPagoInconsistencia().longValue());
            }
        }
        return totalInconsistencias;

    }

    @Override
    public List<PagoDTO> consultarInconsistenciasConciliacion(int organismoTransito, Date fechaInicial,
            Date fechaFinal) {
        logger.debug("InconsistenciaRecaudoEJB.consultarInconsistenciasConciliacion(int,Date,Date)");
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT DISTINCT p from Pago p");
        jpql.append(" LEFT JOIN FETCH p.detallesPago dp");
        jpql.append(" LEFT JOIN p.pagoConciliacionErrores pce");
        jpql.append(" JOIN p.organismoTransito ot");
        jpql.append(" JOIN dp.estadoPago ep");
        jpql.append(" WHERE ep.id=:estPago");
        jpql.append(" AND ot.codigoOrganismo=:codOrganismo");

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("estPago", EnumEstadoPago.PARA_NOTIFICAR.getValue());
        filtros.put("codOrganismo", organismoTransito);

        if (fechaInicial != null && fechaFinal != null) { // Validar contra parametro de consulta
            jpql.append(" AND dp.fechaGeneracionReporte BETWEEN :fechaInicial AND :fechaFinal");
            filtros.put("fechaInicial", fechaInicial);
            filtros.put("fechaFinal", fechaFinal);
        } else {
            jpql.append(" AND dp.fechaGeneracionReporte IS NULL");
        }

        GenericDao<Pago> dao = new GenericDao<Pago>(Pago.class, em);
        List<Pago> resultadoConsulta = dao.buildAndExecuteQuery(jpql, filtros);
        List<PagoDTO> pagoDTOList = new ArrayList<>();
        if (resultadoConsulta != null && !resultadoConsulta.isEmpty()) {
            for (Pago pago : resultadoConsulta) {
                PagoDTO pagoDTO = PagoHelper.toLevel1DTO(pago);
                if (pago.getDetallesPago() != null && !pago.getDetallesPago().isEmpty()) {
                    List<DetallePagoDTO> detallePagoDTOList = new ArrayList<>();
                    for (DetallePago detallePago : pago.getDetallesPago()) {
                        DetallePagoDTO detallePagoDTO = DetallePagoHelper.toLevel1DTO(detallePago);
                        if (detallePago.getDetallePagoConciliacionErrores() != null
                                && !detallePago.getDetallePagoConciliacionErrores().isEmpty()) {
                            List<DetallePagoConciliacionErrorDTO> detallePagoConciliacionErrorDTOList = null;
                            detallePagoConciliacionErrorDTOList = DetallePagoConciliacionErrorHelper
                                    .toListLevel1DTO(detallePago.getDetallePagoConciliacionErrores());
                            detallePagoDTO.setDetallePagoConciliacionErrores(detallePagoConciliacionErrorDTOList);
                        }
                        detallePagoDTOList.add(detallePagoDTO);
                    }
                    pagoDTO.setDetallesPago(detallePagoDTOList);
                }
                pagoDTOList.add(pagoDTO);
            }
        }

        return pagoDTOList;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer enviarInconsistenciasConciliacion(Integer organismoTransito) {
        logger.debug("InconsistenciaRecaudoEJB.enviarInconsistenciasConciliacion()");
        final String NOMBRE_ARCHIVO = "REPORTE DE CONCILIACIÓN DE RECAUDOS";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("pi.codigo_organismo, ");
        sb.append("pi.fecha_transaccion, ");
        sb.append("pi.numero_pago, ");
        sb.append("pi.numero_cuenta, ");
        sb.append("tcb.codigo as codigo_cuenta, ");
        sb.append("b.codigo as codigo_banco, ");
        sb.append("pi.total_efectivo, ");
        sb.append("pi.total_cheque, ");
        sb.append("pi.total_recaudo, ");
        sb.append("tip.codigo as codigo_tipo_ident, ");
        sb.append("pi.numero_documento, ");
        sb.append("dpi.numero_obligacion, ");
        sb.append("dpi.valor_obligacion, ");
        sb.append("tob.codigo as codigo_tipo_recaudo, ");
        sb.append("dpi.numero_cuota, ");
        sb.append("dpi.id_detalle_pago, ");
        sb.append("CONVERT(VARCHAR(100),STUFF((SELECT '|'+ecp.descripcion from detalle_pago_conci_error dpce ");
        sb.append(
                "INNER JOIN error_conciliacion_pago ecp ON(ecp.id_error_conciliacion_pago=dpce.id_error_conciliacion_pago) ");
        sb.append("where dpce.id_detalle_pago=dpi.id_detalle_pago FOR XML PATH('')),1,1,'')) descripcion ");
        sb.append("FROM pago pi ");
        sb.append(
                "LEFT JOIN tipo_identificacion_persona tip ON(tip.id_tipo_identificacion=pi.id_tipo_identificacion) ");
        sb.append("LEFT JOIN banco b ON(b.id_banco=pi.id_banco) ");
        sb.append("LEFT JOIN tipo_cuenta_bancaria tcb ON(pi.id_tipo_cuenta=tcb.id_tipo_cuenta_banca) ");
        sb.append("INNER JOIN detalle_pago dpi ON(dpi.id_pago=pi.id_pago) ");
        sb.append("LEFT JOIN tipo_obligacion tob ON(tob.codigo_tipo_obligacion=dpi.id_tipo_recaudo) ");
        sb.append("WHERE dpi.fecha_generacion_reporte IS NULL ");
        sb.append("AND pi.codigo_organismo=:organismo ");
        sb.append("AND dpi.id_estado_pago=:estadoPago ");

        Query query = em.createNativeQuery(sb.toString());
        query.setParameter("organismo", organismoTransito);
        query.setParameter("estadoPago", EnumEstadoPago.PARA_NOTIFICAR.getValue());
        List<Object[]> resultado = query.getResultList();

        List<DetallePagoInconsistenciaResDTO> detallePagoInconsistenciaResDTOList = new ArrayList<>();

        for (Object[] campo : resultado) {

            DetallePagoInconsistenciaResDTO detallePagoInconsistenciaResDTO = new DetallePagoInconsistenciaResDTO();
            detallePagoInconsistenciaResDTO.setOrganismoTransito((Integer) campo[0]);
            detallePagoInconsistenciaResDTO
                    .setFechaTransaccion(Utilidades.dateToStringFormatApp((Date) campo[1], false));
            detallePagoInconsistenciaResDTO.setHoraTransaccion(Utilidades.hourToStringFormatApp((Date) campo[1]));
            detallePagoInconsistenciaResDTO.setNumeroRecaudo(campo[2].toString());
            if (campo[3] != null) {
                detallePagoInconsistenciaResDTO.setCuenta(campo[3].toString());
            }
            if (campo[4] != null) {
                detallePagoInconsistenciaResDTO.setCodigoTipoCuenta(campo[4].toString());
            }
            if (campo[5] != null) {
                detallePagoInconsistenciaResDTO.setCodigoBanco(campo[5].toString());
            }
            detallePagoInconsistenciaResDTO.setTotalEfectivo((BigDecimal) (campo[6]));
            detallePagoInconsistenciaResDTO.setTotalCheque((BigDecimal) (campo[7]));
            detallePagoInconsistenciaResDTO.setTotalRecaudo((BigDecimal) (campo[8]));
            if (campo[9] != null) {
                detallePagoInconsistenciaResDTO.setCodigoTipoDocumento(campo[9].toString());
            }
            if (campo[10] != null) {
                detallePagoInconsistenciaResDTO.setNumeroIdentificacion(campo[10].toString());
            }
            detallePagoInconsistenciaResDTO.setObligacionPagada(campo[11].toString());
            detallePagoInconsistenciaResDTO.setValorObligacion((BigDecimal) (campo[12]));
            if (campo[13] != null) {
                detallePagoInconsistenciaResDTO.setCodigoTipoRecaudo(campo[13].toString());
            }
            detallePagoInconsistenciaResDTO.setNumeroCuota((int) campo[14]);
            detallePagoInconsistenciaResDTO.setInconsistencias(campo[16].toString());
            detallePagoInconsistenciaResDTO.setIdPagoInconsistencia((BigInteger) (campo[15]));

            detallePagoInconsistenciaResDTOList.add(detallePagoInconsistenciaResDTO);
        }

        int cantidadInconsistencias = 0;
        if (!detallePagoInconsistenciaResDTOList.isEmpty()) {
            Object[] respuesta = InconsistenciaRecaudoUtil.generaArchivo(detallePagoInconsistenciaResDTOList);
            byte[] archivo = (byte[]) respuesta[0];
            cantidadInconsistencias = (int) respuesta[1];
            ArrayList<ArchivoTransportableDTO> archivosTransportables = new ArrayList<ArchivoTransportableDTO>();
            ArchivoTransportableDTO archivoTransportable = new ArchivoTransportableDTO();
            archivoTransportable.setContenido(archivo);
            archivoTransportable.setNombre(NOMBRE_ARCHIVO + "." + EnumTipoArchivo.XLSX.name());
            archivosTransportables.add(archivoTransportable);

            Map<String, Object> variables = new HashMap<>();
            Calendar rightNow = Calendar.getInstance();
            variables.put("dia_fecha", rightNow.get(Calendar.DAY_OF_MONTH));
            variables.put("mes_fecha", rightNow.get(Calendar.MONTH));
            variables.put("anio_fecha", rightNow.get(Calendar.YEAR));
            variables.put("cantidad_inconsistencias", cantidadInconsistencias);
            variables.put("organismo", organismoTransito);

            circulemosMailSenderEJB.enviarCorreo(organismoTransito, EnumTipoCorreo.ENVIO_CONCILIACION_RECAUDO, null,
                    variables, archivosTransportables);

            for (DetallePagoInconsistenciaResDTO detallePagoInconsistenciaResDTO : detallePagoInconsistenciaResDTOList) {
                inconsistenciaRecaudoEJB.actualizarFechaRegistroInconsistenciaConciliacionRecaudo(
                        detallePagoInconsistenciaResDTO.getIdPagoInconsistencia().longValue());
            }

        }
        return Integer.valueOf(cantidadInconsistencias);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarFechaRegistroInconsistenciaConciliacionRecaudo(Long idDetallePago) {
        logger.debug("InconsistenciaRecaudoEJB.actualizarFechaRegistroInconsistenciaConciliacionRecaudo(Long)");
        DetallePago detallePago = em.find(DetallePago.class, idDetallePago);
        detallePago.setFechaGeneracionReporte(new Date());
        em.merge(detallePago);
    }

    public List<DetallePagoInconsistenciaResDTO> consultarPagoInconsistenciasEnviadas(
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO) {// TODO
        logger.debug("InconsistenciaRecaudoEJB.consultarPagoInconsistencias(FiltroConsultaInconsistenciasDTO)");

        List<DetallePagoInconsistenciaResDTO> detallePagoInconsistenciaDTOList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        sql.append(
                "SELECT ot.codigo_organismo, ot.nombre_organismo, pi.fecha_transaccion, pi.numero_pago, pi.numero_cuenta, ");
        sql.append("tcb.codigo tipo_cuenta, b.codigo banco, pi.total_efectivo, pi.total_cheque, pi.total_recaudo, ");
        sql.append(
                "tip.codigo tipo_identificacion, pi.numero_documento, dpi.numero_obligacion, dpi.valor_obligacion, ");
        sql.append("tio.codigo tipo_onligacion, dpi.numero_cuota, ");
        sql.append("CONVERT(VARCHAR(MAX),STUFF((SELECT '|'+eip.descripcion from detalle_pago_incon_error dpie ");
        sql.append(
                "    INNER JOIN error_inconsistencia_pago eip ON dpie.id_error_inconsistencia = eip.id_error_inconsistencia  ");
        sql.append(
                "    WHERE dpi.id_detalle_pago_inconsistencia = dpie.id_detalle_pago_inconsistencia FOR XML PATH('')),1,1,'')) inconsistencias ");
        sql.append("FROM pago_inconsistencia pi ");
        sql.append("INNER JOIN organismo_transito ot ON pi.codigo_organismo = ot.codigo_organismo ");
        sql.append("LEFT OUTER JOIN tipo_cuenta_bancaria tcb ON pi.id_tipo_cuenta = tcb.id_tipo_cuenta_banca ");
        sql.append("LEFT OUTER JOIN banco b ON pi.id_banco = b.id_banco ");
        sql.append(
                "LEFT OUTER JOIN tipo_identificacion_persona tip ON pi.id_tipo_identificacion = tip.id_tipo_identificacion ");
        sql.append(
                "INNER JOIN detalle_pago_inconsistencia dpi ON dpi.id_pago_inconsistencia = pi.id_pago_inconsistencia ");
        sql.append("INNER JOIN tipo_obligacion tio ON dpi.id_tipo_recaudo = tio.codigo_tipo_obligacion ");
        sql.append("WHERE ot.codigo_organismo = :codOrganismo ");

        if (filtroConsultaInconsistenciasDTO.getNumeroRecaudo() != null) {
            sql.append("AND pi.numero_pago = :numPago ");

        }
        if (filtroConsultaInconsistenciasDTO.getObligacionPagada() != null) {
            sql.append("AND dpi.numero_obligacion = :numObligacion ");
        }

        sql.append("AND pi.fecha_generacion_reporte BETWEEN :fechaInicial AND :fechaFinal ");

        Query query = em.createNativeQuery(sql.toString());

        query.setParameter("codOrganismo", filtroConsultaInconsistenciasDTO.getOrganismoTransito());
        if (filtroConsultaInconsistenciasDTO.getNumeroRecaudo() != null) {
            query.setParameter("numPago", filtroConsultaInconsistenciasDTO.getNumeroRecaudo());

        }
        if (filtroConsultaInconsistenciasDTO.getObligacionPagada() != null) {
            query.setParameter("numObligacion", filtroConsultaInconsistenciasDTO.getObligacionPagada());

        }
        query.setParameter("fechaInicial", filtroConsultaInconsistenciasDTO.getFechaInicial());
        query.setParameter("fechaFinal", filtroConsultaInconsistenciasDTO.getFechaFinal());

        List<Object[]> inconsistencias = Utilidades.safeList(query.getResultList());
        DetallePagoInconsistenciaResDTO detallePagoInconsistenciaResDTO;
        if (inconsistencias != null) {
            for (Object[] inconsistencia : inconsistencias) {
                detallePagoInconsistenciaResDTO = new DetallePagoInconsistenciaResDTO();
                int i = 0;

                detallePagoInconsistenciaResDTO.setOrganismoTransito((Integer) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setNombreOrganismo((String) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO
                        .setFechaTransaccion(Utilidades.dateToStringFormatApp((Date) inconsistencia[i], false));
                detallePagoInconsistenciaResDTO
                        .setHoraTransaccion(Utilidades.hourToStringFormatApp((Date) inconsistencia[i++]));
                detallePagoInconsistenciaResDTO.setNumeroRecaudo((String) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setCuenta((String) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setCodigoTipoCuenta((String) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setCodigoBanco((String) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setTotalEfectivo((BigDecimal) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setTotalCheque((BigDecimal) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setTotalRecaudo((BigDecimal) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setCodigoTipoDocumento((String) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setNumeroIdentificacion((String) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setObligacionPagada((String) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setValorObligacion((BigDecimal) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setCodigoTipoRecaudo((String) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setNumeroCuota((Integer) inconsistencia[i++]);
                detallePagoInconsistenciaResDTO.setInconsistencias((String) inconsistencia[i]);
                detallePagoInconsistenciaDTOList.add(detallePagoInconsistenciaResDTO);
            }
        }
        return detallePagoInconsistenciaDTOList;
    }

    public List<DetallePagoInconsistenciaResDTO> consultarPagosInconsistenciasConciliacionEnviadas(
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO) {
        logger.debug("InconsistenciaRecaudoEJB.consultarPagoInconsistencias(int,Date,Date)");

        List<DetallePagoInconsistenciaResDTO> detallePagoInconsistenciaDTOList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        sql.append(
                "SELECT ot.codigo_organismo, ot.nombre_organismo, p.fecha_transaccion, p.numero_pago, p.numero_cuenta, ");
        sql.append(
                "tcb.codigo tipo_cuenta_bancaria, b.codigo banco, p.total_efectivo, p.total_cheque,p.total_recaudo, ");
        sql.append("tip.codigo tipo_identificacion, p.numero_documento, dp.numero_obligacion, dp.valor_obligacion, ");
        sql.append("tio.codigo tipo_recaudo, dp.numero_cuota, ");
        sql.append("CONVERT(VARCHAR(MAX),STUFF((SELECT '|'+ecp.descripcion FROM detalle_pago_conci_error dpce ");
        sql.append(
                "    INNER JOIN error_conciliacion_pago ecp ON dpce.id_error_conciliacion_pago = ecp.id_error_conciliacion_pago  ");
        sql.append("    WHERE dpce.id_detalle_pago = dp.id_detalle_pago FOR XML PATH('')),1,1,'')) inconsistencias ");
        sql.append("FROM pago p ");
        sql.append("INNER JOIN organismo_transito ot ON p.codigo_organismo = ot.codigo_organismo ");
        sql.append("LEFT OUTER JOIN tipo_cuenta_bancaria tcb ON p.id_tipo_cuenta = tcb.id_tipo_cuenta_banca ");
        sql.append("LEFT OUTER JOIN banco b ON p.id_banco = b.id_banco ");
        sql.append(
                "LEFT OUTER JOIN tipo_identificacion_persona tip ON p.id_tipo_identificacion = tip.id_tipo_identificacion ");
        sql.append("INNER JOIN detalle_pago dp ON dp.id_pago = p.id_pago ");
        sql.append("INNER JOIN tipo_obligacion tio ON dp.id_tipo_recaudo = tio.codigo_tipo_obligacion ");
        sql.append("WHERE OT.codigo_organismo = :codOrganismo ");
        sql.append("AND dp.fecha_generacion_reporte BETWEEN :fechaInicial AND :fechaFinal ");
        sql.append("AND dp.id_estado_pago = :estadoPago ");

        if (filtroConsultaInconsistenciasDTO.getNumeroRecaudo() != null) {
            sql.append("AND p.numero_pago = :numPago ");
        }
        if (filtroConsultaInconsistenciasDTO.getObligacionPagada() != null) {
            sql.append("AND dp.numero_obligacion = :numObligacion ");
        }

        Query query = em.createNativeQuery(sql.toString());

        query.setParameter("codOrganismo", filtroConsultaInconsistenciasDTO.getOrganismoTransito());
        if (filtroConsultaInconsistenciasDTO.getNumeroRecaudo() != null) {
            query.setParameter("numPago", filtroConsultaInconsistenciasDTO.getNumeroRecaudo());
        }
        if (filtroConsultaInconsistenciasDTO.getObligacionPagada() != null) {
            query.setParameter("numObligacion", filtroConsultaInconsistenciasDTO.getObligacionPagada());
        }
        query.setParameter("fechaInicial", filtroConsultaInconsistenciasDTO.getFechaInicial());
        query.setParameter("fechaFinal", filtroConsultaInconsistenciasDTO.getFechaFinal());
        query.setParameter("estadoPago", EnumEstadoPago.PARA_NOTIFICAR.getValue());

        @SuppressWarnings("unchecked")
        List<Object[]> detallesPago = query.getResultList();
        DetallePagoInconsistenciaResDTO detallePagoInconsistenciaResDTO;

        if (detallesPago != null) {
            for (Object[] detallePago : detallesPago) {
                detallePagoInconsistenciaResDTO = new DetallePagoInconsistenciaResDTO();
                int i = 0;

                detallePagoInconsistenciaResDTO.setOrganismoTransito((int) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setNombreOrganismo((String) detallePago[i++]);
                detallePagoInconsistenciaResDTO
                        .setFechaTransaccion(Utilidades.dateToStringFormatApp((Date) detallePago[i], false));
                detallePagoInconsistenciaResDTO
                        .setHoraTransaccion(Utilidades.hourToStringFormatApp((Date) detallePago[i++]));
                detallePagoInconsistenciaResDTO.setNumeroRecaudo((String) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setCuenta((String) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setCodigoTipoCuenta((String) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setCodigoBanco((String) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setTotalEfectivo((BigDecimal) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setTotalCheque((BigDecimal) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setTotalRecaudo((BigDecimal) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setCodigoTipoDocumento((String) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setNumeroIdentificacion((String) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setObligacionPagada((String) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setValorObligacion((BigDecimal) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setCodigoTipoRecaudo((String) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setNumeroCuota((int) detallePago[i++]);
                detallePagoInconsistenciaResDTO.setInconsistencias((String) detallePago[i++]);
                detallePagoInconsistenciaDTOList.add(detallePagoInconsistenciaResDTO);
            }
        }
        return detallePagoInconsistenciaDTOList;
    }
}
