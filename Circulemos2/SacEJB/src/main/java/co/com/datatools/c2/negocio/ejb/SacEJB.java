package co.com.datatools.c2.negocio.ejb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.dto.DetallePagoDTO;
import co.com.datatools.c2.dto.EvidenciaObligacionSacDTO;
import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.GestionCobroSacDTO;
import co.com.datatools.c2.dto.NovedadImpugnacionSacDTO;
import co.com.datatools.c2.dto.NovedadSacDTO;
import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;
import co.com.datatools.c2.dto.ObligacionSacDTO;
import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.RespuestaProcesoSacDTO;
import co.com.datatools.c2.dto.RespuestaUbicabilidadSacDTO;
import co.com.datatools.c2.dto.UbicabilidadSacDTO;
import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoCarteraDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoProcesoDTO;
import co.com.datatools.c2.dto.comparendo.HistoricoNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.TipoNotificacionComparendoDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.NovedadAnulacionSac;
import co.com.datatools.c2.entidades.NovedadImpugnacionSac;
import co.com.datatools.c2.entidades.ObligacionSacC2;
import co.com.datatools.c2.entidades.PagoNovedadSac;
import co.com.datatools.c2.entidades.PersonaUbicabilidadSac;
import co.com.datatools.c2.enumeracion.EnumEstadoPago;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumEstadoProcesoMultaSac;
import co.com.datatools.c2.enumeraciones.EnumEstadoTransaccionSac;
import co.com.datatools.c2.enumeraciones.EnumOrigenFacturaSac;
import co.com.datatools.c2.enumeraciones.EnumTipoFallo;
import co.com.datatools.c2.enumeraciones.EnumTipoGestionSac;
import co.com.datatools.c2.enumeraciones.EnumTipoNotificacionComparendo;
import co.com.datatools.c2.enumeraciones.EnumTipoNovedadSac;
import co.com.datatools.c2.enumeraciones.EnumTipoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoSaldo;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaCartera;
import co.com.datatools.c2.negocio.fachada.IRFachadaComparendo;
import co.com.datatools.c2.negocio.fachada.IRFachadaFinanciacion;
import co.com.datatools.c2.negocio.fachada.IRFachadaImpugnacion;
import co.com.datatools.c2.negocio.helpers.NovedadImpugnacionSacHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoNotificacionComparendoHelper;
import co.com.datatools.c2.negocio.interfaces.ILRegistroSac;
import co.com.datatools.c2.negocio.interfaces.ILSac;
import co.com.datatools.c2.negocio.interfaces.IRSac;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.util.Utilidades;

/**
 * Session Bean implementation class SacEJB
 */
@Stateless(mappedName = "SacEJB")
@LocalBean
public class SacEJB implements IRSac, ILSac {

    private final static Logger logger = Logger.getLogger(SacEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILRegistroSac registroSacEJB;
    @EJB
    private IRFachadaCartera iRFachadaCartera;
    @EJB
    private IRFachadaConfiguracion fachadaConfiguracionEJB;
    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneralEJB;
    @EJB
    private IRFachadaAdminGeneral administracionComparendoEJB;
    @EJB
    private IRSeguridadCirculemos seguridadCirculemosEJB;
    @EJB
    private ILSac sacEJB;
    @EJB
    private IRFachadaComparendo iRFachadaComparendo;
    @EJB
    private IRFachadaFinanciacion iRFachadaFinanciacion;
    @EJB
    private IRFachadaImpugnacion iRFachadaImpugnacion;

    private int obtenerDiasCobroComparendo(int codigoOrganismo) {
        int diasCobroComparendoPedestre = fachadaAdminGeneralEJB.consultarValorParametro(
                EnumParametro.DIAS_COBRO_COMPARENDO_PEDESTRE, codigoOrganismo, true).getValorParamInt();
        return diasCobroComparendoPedestre;
    }

    private List<ObligacionSacDTO> consultarOblicacionesSacFinanciaciones(int codigoOrganismo, Date fechaConsulta) {
        StringBuilder consulta = new StringBuilder();
        logger.debug("SacEJB.consultarOblicacionesSacFinanciaciones(int, Date)");

        consulta.append("SELECT c.id_cartera, ");
        consulta.append("c.nombre, ");
        consulta.append("p.fecha_inicio, ");
        consulta.append("pe.numero_identificacion, ");
        consulta.append("pe.id_tipo_identificacion, ");
        consulta.append("pe.apellido1, ");
        consulta.append("pe.apellido2, ");
        consulta.append("pe.nombre1, ");
        consulta.append("pe.nombre2, ");
        consulta.append("pj.nombre_comercial, ");
        consulta.append("c.saldo_capital as saldo_capital, ");
        consulta.append("c.codigo_estado_obligacion, ");
        consulta.append("c.saldo_interes ");
        consulta.append("FROM cartera c ");
        consulta.append("LEFT JOIN obligacion_sac_c2 oc2 ON oc2.id_cartera = c.id_cartera ");
        consulta.append("JOIN persona pe ON c.id_deudor = pe.id_persona ");
        consulta.append("LEFT JOIN persona_juridica pj ON pj.id_persona_juridica = pe.id_persona ");
        consulta.append("LEFT JOIN financiacion_cartera fc ON fc.id_cartera = c.id_cartera ");
        consulta.append("JOIN financiacion f ON f.id_financiacion = fc.id_financiacion ");
        consulta.append("JOIN tipo_obligacion tob on tob.codigo_tipo_obligacion = c.codigo_tipo_obligacion ");
        consulta.append("JOIN proceso p ON p.id_proceso = f.id_proceso ");
        consulta.append("WHERE c.codigo_tipo_obligacion = :tipoObligacion ");
        consulta.append("AND oc2.id_obligacion_sac_c2 IS NULL ");
        consulta.append("AND p.fecha_inicio >= :fechaConsulta ");
        consulta.append("AND (p.id_estado_proceso = :estadoFirme ");
        consulta.append("OR p.id_estado_proceso = :estadoIncumplido) ");
        consulta.append("ORDER BY c.nombre ");

        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("tipoObligacion", EnumTipoObligacion.FINANCIACION.getValue());
        query.setParameter("fechaConsulta", fechaConsulta);
        query.setParameter("estadoFirme", EnumEstadoProceso.ECUADOR_FINANCIACION_EN_FIRME.getValue());
        query.setParameter("estadoIncumplido", EnumEstadoProceso.ECUADOR_FINANCIACION_INCUMPLIDO.getValue());

        @SuppressWarnings({ "unchecked" })
        List<Object[]> detalles = Utilidades.safeList(query.getResultList());
        ArrayList<ObligacionSacDTO> respuesta = new ArrayList<ObligacionSacDTO>();
        ObligacionSacDTO obligacionSacDTO = null;

        if (detalles != null) {
            for (Object[] campo : detalles) {
                int i = 0;
                obligacionSacDTO = new ObligacionSacDTO();
                obligacionSacDTO.setIdCartera(Utilidades.convertBigIntegerToLong(campo[i++])); // id_cartera
                obligacionSacDTO.setIdOrigenFacturaSac(EnumOrigenFacturaSac.FINANCIACION.getValue()); // ok
                obligacionSacDTO.setNumeroFactura((String) campo[i++]); // nombre
                obligacionSacDTO.setFechaFactura((Date) campo[i]); // fecha_inicio proceso
                obligacionSacDTO.setAnioFactura(Utilidades.getYearFromDate((Date) campo[i])); // fecha_inicio proceso
                obligacionSacDTO.setHoraFactura((Date) campo[i]); // fecha_inicio proceso
                obligacionSacDTO.setFechaNotificacion((Date) campo[i++]); // fecha_inicio proceso
                obligacionSacDTO.setNumeroDocumentoDeudor((String) campo[i++]); // numero_identificacion
                obligacionSacDTO.setIdTipoDocumentoSac((Integer) campo[i++]); // id_tipo_identificacion
                obligacionSacDTO.setPrimerApellidoDeudor((String) campo[i++]); // apellido1
                obligacionSacDTO.setSegundoApellidoDeudor((String) campo[i++]); // apellido2
                obligacionSacDTO.setPrimerNombreDeudor((String) campo[i++]); // nombre1
                obligacionSacDTO.setSegundoNombreDeudor((String) campo[i++]); // nombre2
                obligacionSacDTO.setRazonSocial((String) campo[i++]); // nombre_comercial
                obligacionSacDTO.setSaldoTotalDeuda((BigDecimal) (campo[i++])); // saldo_capital
                obligacionSacDTO.setValorRecargoGenerado(BigDecimal.ZERO); // ok
                obligacionSacDTO.setValorTasaGenerada(BigDecimal.ZERO); // ok
                obligacionSacDTO.setIdTipoGestionSac(EnumTipoGestionSac.COBRANZA.getValue());// tipo gestion
                obligacionSacDTO.setFechaRegistro(new Date());// calcular
                obligacionSacDTO.setIdEstadoTransaccionSac(EnumEstadoTransaccionSac.TRANSACCION_NULA.getValue());// calcular
                obligacionSacDTO.setIdEstadoProcesoMulta((Integer) campo[i++]); // codigo_estado_obligacion
                obligacionSacDTO.setValorInteresFinanciacion((BigDecimal) campo[i++]); // saldo intereses financiacion
                respuesta.add(obligacionSacDTO);
            }
        }
        return respuesta;
    }

    private List<ObligacionSacDTO> consultarOblicacionesSacComparendos(int codigoOrganismo, Date fechaConsulta) {
        StringBuilder consulta = new StringBuilder();
        int numeroCuotaInicial = 0;
        int diasCobroComparendoPedestre = obtenerDiasCobroComparendo(codigoOrganismo);
        Date diasGestionCobro = fachadaAdminGeneralEJB.sumarDias(codigoOrganismo, new Date(), -1
                * diasCobroComparendoPedestre, true);
        logger.debug("SacEJB.consultarOblicacionesSacComparendos(int, Date)");

        consulta.append("select ");
        consulta.append("cp.codigo_medio_imposicion, "); // 0 id_origen_factura_sac
        consulta.append("c.nombre, "); // 1 numero_factura
        consulta.append("c.fecha_creacion, "); // 2 fecha_factura
        consulta.append("year(c.fecha_creacion) as anio_fecha, "); // 3 anio_factura
        consulta.append("cp.hora_infraccion, "); // 4 hora_factura
        consulta.append("cp.id_tipo_notificacion_compa, "); // 5 id_tipo_notificacion_sac
        consulta.append("cp.fecha_notificacion, "); // 6 fecha_notificacion
        consulta.append("getdate() as fecha_actual, "); // 7 fecha_pago_oportuna
        consulta.append("pe.id_tipo_identificacion, "); // 8 id_tipo_documento_sac
        consulta.append("pe.numero_identificacion, "); // 9 numero_documento_deudor
        consulta.append("pe.nombre1, "); // 10 primer_nombre_deudor
        consulta.append("pe.nombre2, "); // 11 segundo_nombre_deudor
        consulta.append("pe.apellido1, "); // 12 segundo_nombre_deudor
        consulta.append("pe.apellido2, "); // 13 segundo_apellido_deudor
        consulta.append("pj.nombre_comercial, "); // 14 razon_social
        consulta.append("s.saldo, "); // 15 valor_cobrar
        consulta.append("cp.cicomparendo, "); // 16 cicomparendo
        consulta.append("c.id_cartera, "); // 17 id cartera
        consulta.append("cp.numero_citacion, "); // 18 numero_citacion
        consulta.append("(CASE cp.codigo_medio_imposicion ");
        consulta.append("WHEN " + EnumOrigenFacturaSac.PEDESTRE.getValue()
                + " THEN (CASE WHEN c.fecha_creacion <= :fechaParametro THEN " + EnumTipoGestionSac.COBRANZA.getValue()
                + " ELSE NULL END ) ");
        consulta.append(
                "WHEN " + EnumOrigenFacturaSac.ELECTRONICA.getValue()
                        + " THEN (CASE WHEN cp.fecha_notificacion IS NULL THEN "
                        + EnumTipoGestionSac.NOTIFICAR_COBRANZA.getValue() + " ELSE "
                        + EnumTipoGestionSac.COBRANZA.getValue() + " END) ").append("END) as tipo_gestion, "); // 19 tipo gestion
        consulta.append(" cp.id_estado_comparendo as estado_proceso_multa, "); // 20 estado proceso multa
        consulta.append("c.saldo_capital "); // 21 saldo deuda
        consulta.append("from cartera c ").append("left join saldo_cartera s ON s.id_cartera = c.id_cartera ");
        consulta.append("join persona pe ON c.id_deudor = pe.id_persona ");
        consulta.append("left join persona_juridica pj ON pj.id_persona_juridica = pe.id_persona ");
        consulta.append("join orden_comparendo_nacional ocn ON c.nombre = ocn.numero_comparendo ");
        consulta.append("join comparendo cp ON cp.cicomparendo = ocn.cicomparendo ");
        consulta.append("join tipo_obligacion tob on tob.codigo_tipo_obligacion = c.codigo_tipo_obligacion ");
        consulta.append("left join obligacion_sac_c2 ob2 on ob2.id_cartera = c.id_cartera ");
        consulta.append("where c.codigo_tipo_obligacion = :tipoObligacion ");
        consulta.append("and s.id_tipo_saldo = :tipoSaldo ");
        consulta.append("and s.id_saldo_cartera = (select min(id_saldo_cartera) from saldo_cartera sc ");
        consulta.append("where sc.id_cartera = c.id_cartera and sc.id_tipo_saldo = :tipoSaldo) ");
        consulta.append("and c.fecha_registro >= :fechaConsulta ").append("and ob2.id_cartera is null ");
        consulta.append("group by ").append("cp.codigo_medio_imposicion, ").append("c.nombre, ");
        consulta.append("c.fecha_creacion, ").append("cp.hora_infraccion, ").append("cp.id_tipo_notificacion_compa, ");
        consulta.append("cp.fecha_notificacion, ").append("pe.id_tipo_identificacion, ");
        consulta.append("pe.numero_identificacion, ").append("pe.nombre1, ").append("pe.nombre2, ");
        consulta.append("pe.apellido1, ").append("pe.apellido2, ").append("pj.nombre_comercial, ").append("s.saldo, ");
        consulta.append("cp.cicomparendo, ").append("c.id_cartera, ");
        consulta.append("cp.numero_citacion, cp.id_estado_comparendo, ");
        consulta.append("c.saldo_capital ");
        consulta.append("order by c.nombre ");

        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("tipoSaldo", EnumTipoSaldo.CAPITAL.getValue());
        query.setParameter("tipoObligacion", EnumTipoObligacion.COMPARENDO.getValue());
        query.setParameter("fechaConsulta", fechaConsulta);
        query.setParameter("fechaParametro", diasGestionCobro);

        @SuppressWarnings({ "unchecked" })
        List<Object[]> detalles = Utilidades.safeList(query.getResultList());
        ArrayList<ObligacionSacDTO> respuesta = new ArrayList<ObligacionSacDTO>();

        String cadenaFacturaTemporal = "";
        String cadenaFactura = null;

        ObligacionSacDTO obligacionSacDTO = null;
        if (detalles != null) {
            for (Object[] campo : detalles) {
                cadenaFactura = (String) campo[1];
                boolean esMismaFactura = cadenaFacturaTemporal.equals(cadenaFactura);
                if (!esMismaFactura) {
                    obligacionSacDTO = new ObligacionSacDTO();
                    obligacionSacDTO.setIdOrigenFacturaSac((Integer) campo[0]); // ok
                    obligacionSacDTO.setNumeroFactura((String) campo[1]); // ok
                    obligacionSacDTO.setFechaFactura((Date) campo[2]); // ok
                    obligacionSacDTO.setAnioFactura((Integer) campo[3]); // ok
                    obligacionSacDTO.setHoraFactura((Date) campo[4]); // ok
                    obligacionSacDTO.setIdTipoNotificacionSac((Integer) campo[5]); // ok
                    obligacionSacDTO.setFechaNotificacion((Date) campo[6]); // ok
                    obligacionSacDTO.setFechaPagoOportuna((Date) campo[7]); // ok
                    obligacionSacDTO.setIdTipoDocumentoSac((Integer) campo[8]); // ok
                    obligacionSacDTO.setNumeroDocumentoDeudor((String) campo[9]); // ok
                    obligacionSacDTO.setPrimerNombreDeudor((String) campo[10]); // ok
                    obligacionSacDTO.setSegundoNombreDeudor((String) campo[11]); // ok
                    obligacionSacDTO.setPrimerApellidoDeudor((String) campo[12]); // ok
                    obligacionSacDTO.setSegundoApellidoDeudor((String) campo[13]); // ok
                    obligacionSacDTO.setRazonSocial((String) campo[14]); // ok
                    obligacionSacDTO.setValorCobrar((BigDecimal) (campo[15])); // ok
                    obligacionSacDTO.setValorRecargoGenerado(BigDecimal.ZERO); // ok
                    obligacionSacDTO.setValorInteresFinanciacion(BigDecimal.ZERO); // ok
                    obligacionSacDTO.setValorTasaGenerada(BigDecimal.ZERO); // ok
                    obligacionSacDTO.setFechaRegistro((Date) campo[7]); // ok
                    obligacionSacDTO.setIdEstadoTransaccionSac(EnumEstadoTransaccionSac.TRANSACCION_NULA.getValue()); // ok
                    obligacionSacDTO.setNumeroCuota(numeroCuotaInicial); // ok
                    obligacionSacDTO.setIdCartera(Utilidades.convertBigIntegerToLong(campo[17])); // ok
                    obligacionSacDTO.setNumeroCitacion((String) campo[18]); // ok
                    obligacionSacDTO.setIdTipoGestionSac(((Integer) campo[19])); // ok
                    obligacionSacDTO.setIdEstadoProcesoMulta((Integer) campo[20]); // ok
                    obligacionSacDTO.setSaldoTotalDeuda((BigDecimal) (campo[21])); // ok
                    List<EvidenciaObligacionSacDTO> listaEvidencias = obtenerEvidenciaComparendo(
                            Utilidades.convertBigIntegerToLong(campo[16]), obligacionSacDTO);
                    if (listaEvidencias != null && !listaEvidencias.isEmpty()) {
                        obligacionSacDTO.setEvidenciaObligacionSacs(listaEvidencias);
                    }
                }
                respuesta.add(obligacionSacDTO);
                cadenaFacturaTemporal = (String) campo[1];
            }
        }
        return respuesta;
    }

    private List<EvidenciaObligacionSacDTO> obtenerEvidenciaComparendo(Long cicomparendo,
            ObligacionSacDTO obligacionSacDTO) {
        List<EvidenciaObligacionSacDTO> listaEvidencias = new ArrayList<EvidenciaObligacionSacDTO>();
        EvidenciaObligacionSacDTO evidenciaObligacionSacDTO = null;
        StringBuilder consulta = new StringBuilder();
        consulta.append("select url, nombre_evidencia from evidencia where cicomparendo = :cicomparendo ");
        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("cicomparendo", cicomparendo);
        @SuppressWarnings({ "unchecked" })
        List<Object[]> detalles = Utilidades.safeList(query.getResultList());
        for (Object[] campo : detalles) {
            evidenciaObligacionSacDTO = new EvidenciaObligacionSacDTO();
            evidenciaObligacionSacDTO.setNumeroCitacion(obligacionSacDTO.getNumeroCitacion());
            evidenciaObligacionSacDTO.setNumeroFactura(obligacionSacDTO.getNumeroFactura());
            evidenciaObligacionSacDTO.setUrl((String) campo[0]);
            evidenciaObligacionSacDTO.setNombre((String) campo[1]);
            listaEvidencias.add(evidenciaObligacionSacDTO);
        }
        return listaEvidencias;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaUbicabilidadSacDTO consultarUbicabilidadSac(int codigoOrganismo) {
        logger.debug("SacEJB.consultarUbicabilidadSac(int)");

        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT p.id_tipo_identificacion, p.numero_identificacion, p.nombre1, p.nombre2,");
        consulta.append(" p.apellido1, p.apellido2, pj.nombre_comercial, d.complemento, p.numero_telefonico,");
        consulta.append(" p.numero_celular, p.correo_electronico, 1 origen_ubicabilidad,");
        consulta.append(" CURRENT_TIMESTAMP fecha_registro, 1 estado_transaccion, ph.id_persona_historico,");
        consulta.append(" pa.nombre_pais, dpto.nombre_departamento, mun.nombre nombre_municipio, loc.nombre nombre_localidad");
        consulta.append(" FROM persona p");
        consulta.append(" INNER JOIN persona_historico ph ON p.id_persona = ph.id_persona");
        consulta.append(" LEFT JOIN persona_juridica pj ON p.id_persona = pj.id_persona_juridica");
        consulta.append(" LEFT JOIN direccion_persona dp ON p.id_persona = dp.id_persona");
        consulta.append(" LEFT JOIN direccion d ON dp.id_direccion = d.id_direccion");
        consulta.append(" LEFT JOIN pais pa ON pa.id_pais = d.id_pais");
        consulta.append(" LEFT JOIN departamento dpto ON dpto.id_departamento = d.id_departamento");
        consulta.append(" LEFT JOIN municipio mun ON mun.id_municipio = d.id_municipio");
        consulta.append(" LEFT JOIN localidad loc ON loc.id_localidad = d.id_localidad");
        consulta.append(" WHERE p.codigo_organismo = :codigoOrganismo");
        consulta.append(" AND (dp.fecha_fin IS NULL OR dp.fecha_fin >= CURRENT_TIMESTAMP)");
        consulta.append(" AND ph.id_persona_historico =");
        consulta.append(" (SELECT MAX(phIn.id_persona_historico) FROM persona_historico phIn");
        consulta.append(" WHERE phIn.id_persona = ph.id_persona)");
        consulta.append(" AND NOT EXISTS");
        consulta.append(" (SELECT 1 FROM persona_ubicabilidad_sac pu");
        consulta.append(" WHERE pu.id_persona_historico = ph.id_persona_historico)");

        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("codigoOrganismo", codigoOrganismo);

        @SuppressWarnings({ "unchecked" })
        List<Object[]> detalles = Utilidades.safeList(query.getResultList());
        RespuestaUbicabilidadSacDTO respuesta = new RespuestaUbicabilidadSacDTO();

        for (Object[] campo : detalles) {
            UbicabilidadSacDTO ubicabilidad = new UbicabilidadSacDTO();
            respuesta.getUbicabilidadSacList().add(ubicabilidad);
            ubicabilidad.setIdTipoDocumentoSac((Integer) campo[0]);
            ubicabilidad.setNumeroDocumento((String) campo[1]);
            ubicabilidad.setPrimerNombre((String) campo[2]);
            ubicabilidad.setSegundoNombre((String) campo[3]);
            ubicabilidad.setPrimerApellido((String) campo[4]);
            ubicabilidad.setSegundoApellido((String) campo[5]);
            ubicabilidad.setRazonSocial((String) campo[6]);
            ubicabilidad.setDireccion((String) campo[7]);
            ubicabilidad.setTelefonoFijo((String) campo[8]);
            ubicabilidad.setTelefonoMovil((String) campo[9]);
            ubicabilidad.setCorreoElectronico((String) campo[10]);
            ubicabilidad.setIdOrigenUbicabilidadSac((Integer) campo[11]);
            ubicabilidad.setFechaRegistro((Date) campo[12]);
            ubicabilidad.setIdEstadoTransaccionSac((Integer) campo[13]);
            respuesta.getLsIdPersonaHistorico().add(((BigInteger) campo[14]).longValue());
            ubicabilidad.setPais((String) campo[15]);
            ubicabilidad.setDepartamento((String) campo[16]);
            ubicabilidad.setMunicipio((String) campo[17]);
            ubicabilidad.setLocalidad((String) campo[18]);
        }

        return respuesta;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void registrarUbicabilidadReplicada(int codigoOrganismo, UbicabilidadSacDTO ubicabilidadSacDTO,
            Long idPersonaHistorico) {
        logger.debug("SacEJB.registrarUbicabilidadReplicada(int, UbicabilidadSacDTO)");
        try {
            PersonaUbicabilidadSac personaUbicabilidadSac = new PersonaUbicabilidadSac();
            personaUbicabilidadSac.setIdPersonaHistorico(idPersonaHistorico);
            personaUbicabilidadSac.setIdUbicabilidadSac(ubicabilidadSacDTO.getId());
            personaUbicabilidadSac.setFechaHoraReplicaSac(ubicabilidadSacDTO.getFechaRegistro());
            em.persist(personaUbicabilidadSac);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /********************************************************************
     * Replica de novedades, pagos Inicio
     *******************************************************************/
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaProcesoSacDTO replicarNovedadesSac(int codigoOrganismo) {
        logger.debug("SacEJB.replicarNovedadesSac(int)");
        // Fecha actual
        Date ahora = new Date();
        RespuestaProcesoSacDTO respuesta = new RespuestaProcesoSacDTO();
        int numeroExitosos = 0;
        int numeroFallos = 0;
        ValorParametroDTO replicarPagos = fachadaAdminGeneralEJB.consultarValorParametro(
                EnumParametro.REPLICAR_NOVEDADES_PAGOS_SAC, codigoOrganismo, true);
        ValorParametroDTO replicarAnulaciones = fachadaAdminGeneralEJB.consultarValorParametro(
                EnumParametro.REPLICAR_NOVEDADES_ANULACION_SAC, codigoOrganismo, true);
        ValorParametroDTO replicarImpugnaciones = fachadaAdminGeneralEJB.consultarValorParametro(
                EnumParametro.REPLICAR_NOVEDADES_IMPUGNACION_SAC, codigoOrganismo, true);
        // Replicar pagos a SAC
        if (replicarPagos != null && replicarPagos.getValorParamBoolean()) {
            for (DetallePagoDTO detallePago : consultarPagos(codigoOrganismo)) {
                try {
                    if (sacEJB.replicarPagoSac(detallePago, ahora)) {
                        numeroExitosos++;
                    } else {
                        numeroFallos++;
                    }
                } catch (Exception e) {
                    logger.error("Error al replicar novedades de pagos a SAC: " + detallePago.getId(), e);
                    numeroFallos++;
                }
            }
        }

        // Replicar Anulaciones a SAC
        if (replicarAnulaciones != null && replicarAnulaciones.getValorParamBoolean()) {
            for (ComparendoDTO comparendoDTO : consultarComparendosAnulados(codigoOrganismo)) {
                try {
                    if (sacEJB.replicarAnulacionSac(comparendoDTO, ahora)) {
                        numeroExitosos++;
                    } else {
                        numeroFallos++;
                    }

                } catch (Exception e) {
                    logger.error(
                            "Error al replicar novedades de anulaciones a SAC: " + comparendoDTO.getCicomparendo(), e);
                    numeroFallos++;
                }
            }
        }

        // Replicar Impugnaciones a SAC
        if (replicarImpugnaciones != null && replicarImpugnaciones.getValorParamBoolean()) {
            // Aperturas de impugnacion
            for (NovedadImpugnacionSacDTO novedadImpugnacionSacDTO : consultarComparendosImpugnacion(codigoOrganismo,
                    false)) {
                try {
                    if (sacEJB.replicarAperturaImpugnacionSac(novedadImpugnacionSacDTO, ahora)) {
                        numeroExitosos++;
                    } else {
                        numeroFallos++;
                    }
                } catch (Exception e) {
                    logger.error("Error al replicar novedades de apertura impugnacion a SAC: "
                            + novedadImpugnacionSacDTO.getIdCartera(), e);
                    numeroFallos++;
                }
            }

            // Fallos de impugnacion
            for (NovedadImpugnacionSacDTO novedadImpugnacionSacDTO : consultarComparendosImpugnacion(codigoOrganismo,
                    true)) {
                try {
                    if (sacEJB.replicarFalloImpugnacionSac(novedadImpugnacionSacDTO, ahora)) {
                        numeroExitosos++;
                    } else {
                        numeroFallos++;
                    }
                } catch (Exception e) {
                    logger.error("Error al replicar novedades de fallos de impugnacion a SAC: "
                            + novedadImpugnacionSacDTO.getIdCartera(), e);
                    numeroFallos++;
                }
            }
        }

        respuesta.setNumeroExitosos(numeroExitosos);
        respuesta.setNumeroFallidos(numeroFallos);
        return respuesta;
    }

    /**
     * Consulta los pagos que se deben pasar a sAC
     * 
     * @param codigoOrganismo
     * @return Lista de detalles de pago
     * @author julio.pinzon 2016-05-31
     */
    private List<DetallePagoDTO> consultarPagos(int codigoOrganismo) {
        logger.debug("SacEJB.consultarPagos(int)");
        List<DetallePagoDTO> pagos = new ArrayList<>();
        // Consulta de pagos
        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT p.numero_pago, p.fecha_transaccion, dp.id_detalle_pago, dp.numero_obligacion, ");
        consulta.append("dp.valor_obligacion, dp.numero_cuota, dp.id_tipo_recaudo ");
        consulta.append("FROM detalle_pago dp ");
        consulta.append("JOIN pago p ON p.id_pago = dp.id_pago ");
        consulta.append("LEFT JOIN pago_novedad_sac pns ON pns.id_detalle_pago = dp.id_detalle_pago ");
        consulta.append("WHERE p.codigo_organismo = :organismo ");
        consulta.append("AND dp.id_estado_pago = :estadoPago ");
        consulta.append("AND pns.id_pago_novedad_sac IS NULL ");

        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("organismo", codigoOrganismo);
        query.setParameter("estadoPago", EnumEstadoPago.PAGO_APLICADO.getValue());

        @SuppressWarnings({ "unchecked" })
        List<Object[]> detalles = Utilidades.safeList(query.getResultList());

        if (detalles != null) {
            for (Object[] campo : detalles) {
                int i = 0;
                DetallePagoDTO detallePagoDTO = new DetallePagoDTO();
                PagoDTO pago = new PagoDTO();

                pago.setNumeroPago((String) campo[i++]);// numero_pago
                pago.setFechaTransaccion((Date) campo[i++]);// fecha_transaccion
                detallePagoDTO.setPago(pago);

                detallePagoDTO.setId(Utilidades.convertBigIntegerToLong(campo[i++])); // id_detalle_pago
                detallePagoDTO.setNumeroObligacion((String) campo[i++]); // numero_obligacion
                detallePagoDTO.setValorObligacion((BigDecimal) (campo[i++])); // valor_obligacion
                detallePagoDTO.setNumeroCuota((Integer) campo[i++]); // numero_cuota
                detallePagoDTO.setIdTipoRecaudo((Integer) campo[i++]); // id_tipo_recaudo
                pagos.add(detallePagoDTO);
            }
        }
        return pagos;

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean replicarPagoSac(DetallePagoDTO detallePago, Date ahora) {
        logger.debug("SacEJB.replicarPagoSac(DetallePagoDTO,Date)");
        boolean replicado = false;

        ObligacionSacDTO obligacionSac = new ObligacionSacDTO();
        obligacionSac.setNumeroFactura(detallePago.getNumeroObligacion());
        obligacionSac.setNumeroCuota(detallePago.getNumeroCuota());

        // Busca la obligacion asociada al pago
        obligacionSac = registroSacEJB.consultarObligacionSac(obligacionSac);
        if (obligacionSac != null) {
            obligacionSac.setFechaCambio(ahora);
            obligacionSac.setIdTipoGestionSac(null);
            registroSacEJB.modificarObligacionSac(obligacionSac);

            NovedadSacDTO novedad = new NovedadSacDTO();
            novedad.setFechaNovedad(detallePago.getPago().getFechaTransaccion());
            novedad.setFechaRegistro(ahora);
            novedad.setIdTipoNovedadSac(EnumTipoNovedadSac.PAGO.getValue());
            novedad.setObligacionSac(obligacionSac);
            novedad.setSoporteNovedad(detallePago.getNumeroObligacion());
            novedad.setValorNovedad(detallePago.getValorObligacion());
            novedad.setIdEstadoTransaccionSac(EnumEstadoTransaccionSac.TRANSACCION_NULA.getValue());

            // registro de recaudo en sac
            novedad = registroSacEJB.registarNovedadSac(novedad);

            // Guarda el log de la novedad
            PagoNovedadSac logNovedad = new PagoNovedadSac();
            logNovedad.setFechaPagoNovedad(ahora);
            logNovedad.setIdDetallePago(detallePago.getId());
            logNovedad.setIdNovedadSac(novedad.getIdNovedadSac());
            em.persist(logNovedad);

            replicado = true;
        }
        return replicado;

    }

    /********************************************************************
     * Replica de novedades, pagos Final
     *******************************************************************/

    /********************************************************************
     * Replica de novedades, anulaciones Inicio
     *******************************************************************/

    /**
     * Consulta los comparendos anulados que se deben pasar a SAC
     * 
     * @param codigoOrganismo
     * @return Lista de comparendos anulados
     * @author Dixon.Alvarez 23-08-2016
     */
    private List<ComparendoDTO> consultarComparendosAnulados(int codigoOrganismo) {
        logger.debug("SacEJB.consultarComparendosAnulados(int)");
        List<ComparendoDTO> comparendos = new ArrayList<ComparendoDTO>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c.cicomparendo, "); // cicomparendo
        sql.append("c.id_factura_axis, "); // numero factura
        sql.append("car.id_cartera, "); // id_cartera
        sql.append("cp.id_proceso, "); // id_proceso
        sql.append("c.fecha_modificacion "); // fecha_modificacion
        sql.append("FROM cartera car ");
        sql.append("JOIN orden_comparendo_nacional ocn ON car.nombre = ocn.numero_comparendo ");
        sql.append("JOIN comparendo c ON c.cicomparendo = ocn.cicomparendo ");
        sql.append("JOIN comparendo_proceso cp ON cp.cicomparendo = c.cicomparendo ");
        sql.append("LEFT JOIN novedad_anulacion_sac na ON na.id_cartera = car.id_cartera ");
        sql.append("WHERE na.id_cartera IS NULL ");
        sql.append("AND id_estado_comparendo = :estadoAnulado ");
        sql.append("AND ocn.codigo_organismo_transito = :codigoOrganismo");

        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("codigoOrganismo", codigoOrganismo);
        query.setParameter("estadoAnulado", EnumEstadoComparendo.ANULADO.getValue());

        @SuppressWarnings({ "unchecked" })
        List<Object[]> resultado = Utilidades.safeList(query.getResultList());

        for (Object[] campo : resultado) {
            int i = 0;
            ComparendoDTO comparendoDTO = new ComparendoDTO();
            comparendoDTO.setCicomparendo(Utilidades.convertBigIntegerToLong(campo[i++]));
            comparendoDTO.setIdFacturaAxis(Utilidades.convertBigIntegerToLong(campo[i++]));
            ComparendoCarteraDTO comparendoCarteraDTO = new ComparendoCarteraDTO();
            comparendoCarteraDTO.setIdCartera(Utilidades.convertBigIntegerToLong(campo[i++]));
            comparendoDTO.setComparendoCarteraList(new ArrayList<ComparendoCarteraDTO>());
            comparendoDTO.getComparendoCarteraList().add(comparendoCarteraDTO);
            ComparendoProcesoDTO comparendoProcesoDTO = new ComparendoProcesoDTO();
            comparendoProcesoDTO.setIdProceso(Utilidades.convertBigIntegerToLong(campo[i++]));
            comparendoDTO.setComparendoProcesoList(new ArrayList<ComparendoProcesoDTO>());
            comparendoDTO.getComparendoProcesoList().add(comparendoProcesoDTO);
            comparendoDTO.setFechaModificacion((Date) campo[i++]);
            comparendos.add(comparendoDTO);
        }
        return comparendos;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean replicarAnulacionSac(ComparendoDTO comparendoDTO, Date fechaActual) {
        logger.debug("SacEJB.replicarAnulacionSac(ComparendoDTO,Date)");
        boolean registroExitoso = false;

        Long idCartera = comparendoDTO.getComparendoCarteraList().get(0).getIdCartera();
        ObligacionSacDTO obligacionSacDTO = new ObligacionSacDTO();
        obligacionSacDTO.setIdCartera(idCartera);
        obligacionSacDTO.setNumeroCuota(0);
        obligacionSacDTO = registroSacEJB.consultarObligacionSac(obligacionSacDTO);
        if (obligacionSacDTO != null) {
            obligacionSacDTO.setFechaCambio(fechaActual);
            obligacionSacDTO.setIdTipoGestionSac(null);
            registroSacEJB.modificarObligacionSac(obligacionSacDTO);

            Long idProceso = comparendoDTO.getComparendoProcesoList().get(0).getIdProceso();

            NovedadSacDTO novedadSacDTO = new NovedadSacDTO();
            novedadSacDTO.setFechaNovedad(comparendoDTO.getFechaModificacion());
            novedadSacDTO.setFechaRegistro(fechaActual);
            novedadSacDTO.setIdEstadoTransaccionSac(EnumEstadoTransaccionSac.TRANSACCION_NULA.getValue());
            novedadSacDTO.setIdTipoNovedadSac(EnumTipoNovedadSac.ANULACION.getValue());
            novedadSacDTO.setObligacionSac(obligacionSacDTO);
            novedadSacDTO.setValorNovedad(obligacionSacDTO.getValorCobrar());
            novedadSacDTO.setSoporteNovedad(String.valueOf(comparendoDTO.getIdFacturaAxis()));
            registroSacEJB.registarNovedadSac(novedadSacDTO);

            // Registra la anulacion replicada
            NovedadAnulacionSac novedadAnulacionSac = new NovedadAnulacionSac();
            novedadAnulacionSac.setFechaReplicaSac(fechaActual);
            novedadAnulacionSac.setIdCartera(idCartera);
            novedadAnulacionSac.setIdProceso(idProceso);
            novedadAnulacionSac.setIdObligacionSac(obligacionSacDTO.getId());
            em.persist(novedadAnulacionSac);
            registroExitoso = true;
        }
        return registroExitoso;
    }

    /********************************************************************
     * Replica de novedades, anulaciones Fin
     *******************************************************************/

    /********************************************************************
     * Replica de novedades, Impugnaciones Inicio
     *******************************************************************/

    /**
     * Consulta las obligaciones a los cuales se les abrio un proceso de impugnacion y que se deben pasar a SAC
     * 
     * @param codigoOrganismo
     * @param fallos
     *            Indica si se van a counsultar aperturas o fallos de impugnacion (TRUE = fallos)
     * @return Lista de obligaciones con apertura de proceso de impugnacion
     * @author Dixon.Alvarez 23-08-2016
     */
    private List<NovedadImpugnacionSacDTO> consultarComparendosImpugnacion(int codigoOrganismo, boolean fallos) {
        logger.debug("SacEJB.consultarComparendosAprturaImpugnacion(int)");
        List<NovedadImpugnacionSacDTO> novedades = new ArrayList<NovedadImpugnacionSacDTO>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("p.id_proceso, "); // id_proceso
        sql.append("car.id_cartera, "); // id_cartera
        sql.append("ni.id_novedad_impugnacion_sac, "); // id_novedad_impugnacion
        sql.append("ni.fecha_replica_apertura "); // fecha_replica_apertura
        sql.append("FROM proceso p ");
        sql.append("JOIN comparendo_proceso cp ON cp.id_proceso = p.id_proceso ");
        sql.append("JOIN orden_comparendo_nacional ocn ON ocn.cicomparendo = cp.cicomparendo ");
        sql.append("JOIN cartera car ON ocn.numero_comparendo = car.nombre ");
        sql.append("LEFT JOIN novedad_impugnacion_sac ni ON ni.id_proceso = p.id_proceso ");
        sql.append("WHERE ocn.codigo_organismo_transito = :codigoOrganismo ");
        sql.append("AND p.id_tipo_proceso = :tipoImpugnacion ");
        if (fallos) {
            sql.append("AND ni.fecha_replica_fallo IS NULL ");
            sql.append("AND p.id_estado_proceso = :estadoCerrado ");
        } else {
            sql.append("AND p.fecha_fin IS NULL ");
            sql.append("AND ni.fecha_replica_apertura IS NULL ");
        }

        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("codigoOrganismo", codigoOrganismo);
        query.setParameter("tipoImpugnacion", EnumTipoProceso.IMPUGNACION_COMPARENDO.getValue());
        if (fallos) {
            query.setParameter("estadoCerrado", EnumEstadoProceso.ECUADOR_IMPUGNACION_CERRADO.getValue());
        }

        @SuppressWarnings({ "unchecked" })
        List<Object[]> resultado = Utilidades.safeList(query.getResultList());

        for (Object[] campo : resultado) {
            int i = 0;
            NovedadImpugnacionSacDTO novedadImpugnacionSacDTO = new NovedadImpugnacionSacDTO();
            novedadImpugnacionSacDTO.setIdProceso(Utilidades.convertBigIntegerToLong(campo[i++]));
            novedadImpugnacionSacDTO.setIdCartera(Utilidades.convertBigIntegerToLong(campo[i++]));
            novedadImpugnacionSacDTO.setId(Utilidades.convertBigIntegerToLong(campo[i++]));
            novedadImpugnacionSacDTO.setFechaReplicaApertura((Date) campo[i++]);
            novedades.add(novedadImpugnacionSacDTO);
        }
        return novedades;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean replicarAperturaImpugnacionSac(NovedadImpugnacionSacDTO novedadImpugnacionSacDTO, Date fechaActual) {
        logger.debug("SacEJB.replicarAperturaImpugnacionSac(NovedadImpugnacionSacDTO,Date)");
        boolean registroExitoso = false;

        ObligacionSacDTO obligacionSacDTO = new ObligacionSacDTO();
        obligacionSacDTO.setIdCartera(novedadImpugnacionSacDTO.getIdCartera());
        obligacionSacDTO.setNumeroCuota(0);
        obligacionSacDTO = registroSacEJB.consultarObligacionSac(obligacionSacDTO);
        if (obligacionSacDTO != null) {
            obligacionSacDTO.setFechaCambio(fechaActual);
            obligacionSacDTO.setIdTipoGestionSac(null);
            registroSacEJB.modificarObligacionSac(obligacionSacDTO);

            // Registra la apertura del proceso
            NovedadImpugnacionSac novedadImpugnacionSac = new NovedadImpugnacionSac();
            novedadImpugnacionSac.setFechaReplicaApertura(fechaActual);
            novedadImpugnacionSac.setIdCartera(novedadImpugnacionSacDTO.getIdCartera());
            novedadImpugnacionSac.setIdProceso(novedadImpugnacionSacDTO.getIdProceso());
            novedadImpugnacionSac.setIdObligacionSac(obligacionSacDTO.getId());
            em.persist(novedadImpugnacionSac);
            registroExitoso = true;
        }
        return registroExitoso;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean replicarFalloImpugnacionSac(NovedadImpugnacionSacDTO novedadImpugnacionSacDTO, Date fechaActual) {
        logger.debug("SacEJB.replicarFalloImpugnacionSac(NovedadImpugnacionSacDTO,Date)");
        boolean registroExitoso = false;
        boolean registraNovedad = true;
        boolean consultarNumeroComparendoNuevo = true;

        FalloImpugnacionDTO falloImpugnacionDTO = iRFachadaImpugnacion
                .consultarUltimoFalloImpugnacion(novedadImpugnacionSacDTO.getIdProceso());
        if (falloImpugnacionDTO != null) {
            ObligacionSacDTO obligacionSacDTO = new ObligacionSacDTO();
            obligacionSacDTO.setIdCartera(novedadImpugnacionSacDTO.getIdCartera());
            obligacionSacDTO.setNumeroCuota(0);
            obligacionSacDTO = registroSacEJB.consultarObligacionSac(obligacionSacDTO);
            if (falloImpugnacionDTO.getTipoFallo().getId().intValue() == EnumTipoFallo.ABSOLUTORIO.getValue()
                    .intValue()) {
                obligacionSacDTO.setIdTipoGestionSac(null);
                consultarNumeroComparendoNuevo = false;
            } else if (falloImpugnacionDTO.getTipoFallo().getId().intValue() == EnumTipoFallo.CONDENATORIO_PARCIAL
                    .getValue().intValue()) {
                obligacionSacDTO.setIdTipoGestionSac(EnumTipoGestionSac.COBRANZA.getValue());
            } else if (falloImpugnacionDTO.getTipoFallo().getId().intValue() == EnumTipoFallo.CONDENATORIO.getValue()
                    .intValue()) {
                obligacionSacDTO.setIdTipoGestionSac(EnumTipoGestionSac.COBRANZA.getValue());
                registraNovedad = false;
            }

            if (consultarNumeroComparendoNuevo) {
                HistoricoNumeroComparendoDTO historicoNumeroComparendoDTO = new HistoricoNumeroComparendoDTO();
                historicoNumeroComparendoDTO.setNumeroComparendoAntiguo(obligacionSacDTO.getNumeroFactura());
                historicoNumeroComparendoDTO = iRFachadaComparendo
                        .consultarHistoricoNumeroComparendo(historicoNumeroComparendoDTO);
                obligacionSacDTO.setNumeroFactura(historicoNumeroComparendoDTO.getNumeroComparendoNuevo());
            }

            // Modificar la tabla de replicas de obligaciones a SAC
            obligacionSacDTO.setFechaCambio(fechaActual);
            registroSacEJB.modificarObligacionSac(obligacionSacDTO);

            // Registra la novedad en SAC
            if (registraNovedad) {
                NovedadSacDTO novedadSacDTO = new NovedadSacDTO();
                novedadSacDTO.setFechaNovedad(falloImpugnacionDTO.getTrazabilidadProceso().getFechaInicio());
                novedadSacDTO.setFechaRegistro(fechaActual);
                novedadSacDTO.setIdEstadoTransaccionSac(EnumEstadoTransaccionSac.TRANSACCION_NULA.getValue());
                novedadSacDTO.setIdTipoNovedadSac(EnumTipoNovedadSac.EXONERACION.getValue());
                novedadSacDTO.setObligacionSac(obligacionSacDTO);
                novedadSacDTO.setValorNovedad(falloImpugnacionDTO.getValorDescuento());
                novedadSacDTO.setSoporteNovedad(obligacionSacDTO.getNumeroFactura());
                registroSacEJB.registarNovedadSac(novedadSacDTO);
            }

            if (novedadImpugnacionSacDTO.getId() != null) {
                // Actualiza la fecha en que se replico el fallo
                NovedadImpugnacionSac novedadImpugnacionSac = NovedadImpugnacionSacHelper.toLevel0Entity(
                        novedadImpugnacionSacDTO, null);
                novedadImpugnacionSac.setFechaReplicaFallo(fechaActual);
                novedadImpugnacionSac.setIdObligacionSac(obligacionSacDTO.getId());
                em.merge(novedadImpugnacionSac);
            } else {
                // Persiste la fecha en que replico el fallo, en este caso la fecha replica apertura quedara null
                NovedadImpugnacionSac novedadImpugnacionSac = NovedadImpugnacionSacHelper.toLevel0Entity(
                        novedadImpugnacionSacDTO, null);
                novedadImpugnacionSac.setFechaReplicaFallo(fechaActual);
                novedadImpugnacionSac.setIdObligacionSac(obligacionSacDTO.getId());
                em.persist(novedadImpugnacionSac);
            }

            registroExitoso = true;
        }

        return registroExitoso;
    }

    /********************************************************************
     * Replica de novedades, impugnaciones Fin
     *******************************************************************/

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaProcesoSacDTO actualizarObligacionesFinanciadas(int codigoOrganismo) {
        logger.debug("SacEJB.actualizarObligacionesFinanciadas(int)");
        RespuestaProcesoSacDTO respuesta = new RespuestaProcesoSacDTO();
        int numeroExitosos = 0;
        int numeroFallos = 0;

        /*
         * Indica la cantidad de dias atras que debe tenee en cuenta para la consulta de obligaciones
         */
        ValorParametroDTO valDiasConsulta = fachadaAdminGeneralEJB.consultarValorParametro(
                EnumParametro.DIAS_BUSCAR_OBLIGACIONES_SAC, codigoOrganismo, true);
        int diasConsultaObligaciones = valDiasConsulta.getValorParamInt();
        Calendar fechaConsulta = Calendar.getInstance();
        fechaConsulta.add(Calendar.DAY_OF_MONTH, diasConsultaObligaciones * (-1));

        // Buscamos las obligaciones que han sido financiadas

        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT oc2.id_obligacion_sac_c2 ");
        consulta.append("FROM obligacion_sac_c2 oc2 ");
        consulta.append("JOIN cartera c ON oc2.id_cartera = c.id_cartera ");
        consulta.append("WHERE c.codigo_tipo_obligacion = :tipoObligacion ");
        consulta.append("AND c.codigo_estado_obligacion = :estadoObligacion ");
        consulta.append("AND c.fecha_registro >= :fechaConsulta");

        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("tipoObligacion", EnumTipoObligacion.COMPARENDO.getValue());
        query.setParameter("estadoObligacion", EnumEstadoObligacion.FINANCIADO.getValue());
        query.setParameter("fechaConsulta", fechaConsulta.getTime());

        @SuppressWarnings({ "unchecked" })
        List<Long> resultadoConsulta = Utilidades.safeList(query.getResultList());
        for (Long idObligacionSac : resultadoConsulta) {
            try {
                ObligacionSacDTO obligacionSac = registroSacEJB.consultarObligacionSac(idObligacionSac);
                if (obligacionSac != null
                        && (obligacionSac.getIdEstadoProcesoMulta() == null || !EnumEstadoProcesoMultaSac.FINANCIACION_COMPARENDO
                                .getValue().equals(obligacionSac.getIdEstadoProcesoMulta()))) {
                    obligacionSac.setFechaCambio(new Date());
                    obligacionSac.setIdTipoGestionSac(null);
                    obligacionSac.setIdEstadoProcesoMulta(EnumEstadoProcesoMultaSac.FINANCIACION_COMPARENDO.getValue());
                    registroSacEJB.modificarObligacionSac(obligacionSac);
                    numeroExitosos++;
                } else {
                    numeroFallos++;
                }
            } catch (Exception e) {
                numeroFallos++;
                logger.error("Error al modificar obligacion sac", e);
            }
        }
        respuesta.setNumeroExitosos(numeroExitosos);
        respuesta.setNumeroFallidos(numeroFallos);
        return respuesta;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaProcesoSacDTO replicarObligacionesSac(int codigoOrganismo) {
        logger.debug("SacEJB.replicarObligacionesSac(int)");

        RespuestaProcesoSacDTO respuesta = new RespuestaProcesoSacDTO();
        Date ahora = new Date();
        int numeroExitosos = 0;
        int numeroFallos = 0;

        /*
         * Indica la cantidad de dias atras que debe tenee en cuenta para la consulta de obligaciones
         */
        ValorParametroDTO valDiasConsulta = fachadaAdminGeneralEJB.consultarValorParametro(
                EnumParametro.DIAS_BUSCAR_OBLIGACIONES_SAC, codigoOrganismo, true);
        int diasConsultaObligaciones = valDiasConsulta.getValorParamInt();
        Calendar fechaConsulta = Calendar.getInstance();
        fechaConsulta.add(Calendar.DAY_OF_MONTH, diasConsultaObligaciones * (-1));

        List<ObligacionSacDTO> obligaciones = new ArrayList<ObligacionSacDTO>();
        // Valida si debe enviar cada uno de los tipos de obligacion
        ValorParametroDTO replicarComparendos = fachadaAdminGeneralEJB.consultarValorParametro(
                EnumParametro.REPLICAR_COMPARENDOS_SAC, codigoOrganismo, true);
        ValorParametroDTO replicarFinanciaciones = fachadaAdminGeneralEJB.consultarValorParametro(
                EnumParametro.REPLICAR_FINANCIACION_SAC, codigoOrganismo, true);

        if (replicarComparendos != null && replicarComparendos.getValorParamBoolean()) {
            obligaciones.addAll(consultarOblicacionesSacComparendos(codigoOrganismo, fechaConsulta.getTime()));
        }

        if (replicarFinanciaciones != null && replicarFinanciaciones.getValorParamBoolean()) {
            obligaciones.addAll(consultarOblicacionesSacFinanciaciones(codigoOrganismo, fechaConsulta.getTime()));
        }

        for (ObligacionSacDTO obligacion : obligaciones) {
            try {
                sacEJB.replicaObligacion(obligacion, ahora);
                numeroExitosos++;
            } catch (Exception e) {
                numeroFallos++;
                logger.error("Error al procesar obligación: " + e.getMessage());
            }
        }
        respuesta.setNumeroExitosos(numeroExitosos);
        respuesta.setNumeroFallidos(numeroFallos);
        return respuesta;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean replicaObligacion(ObligacionSacDTO obligacion, Date ahora) {
        boolean exitoso = false;
        Long idObligacionSac = null;
        if (obligacion.getIdOrigenFacturaSac().intValue() == EnumOrigenFacturaSac.FINANCIACION.getValue().intValue()) {
            FinanciacionDTO financiacionDTO = iRFachadaFinanciacion
                    .consultarFinanciacion(obligacion.getNumeroFactura());
            // Registra las obligaciones sac de cada detalle de la financiacion
            for (DetalleFinanciacionDTO detalle : financiacionDTO.getDetallesFinanciacion()) {
                obligacion.setFechaPagoOportuna(detalle.getFechaPagoOportuno());
                obligacion.setValorCobrar(detalle.getValorTotal());
                obligacion.setNumeroCuota(detalle.getNumeroCuota());
                idObligacionSac = registroSacEJB.registrarObligacionSac(obligacion).getId();
                obligacion.setId(null);
            }

            /**
             * Consultamos obligaciones incluidas en una financiacion y generamos las novedades a SAC
             **/
            for (ObligacionFinanciacionDTO obligacionFinanciacion : financiacionDTO.getObligacionesFinanciacion()) {
                ObligacionSacDTO obligacionSacFinanciacion = new ObligacionSacDTO();
                obligacionSacFinanciacion.setNumeroFactura(obligacionFinanciacion.getNumeroObligacion());
                obligacionSacFinanciacion.setNumeroCuota(0);
                obligacionSacFinanciacion = registroSacEJB.consultarObligacionSac(obligacionSacFinanciacion);
                obligacionSacFinanciacion.setIdTipoGestionSac(null);
                obligacionSacFinanciacion.setFechaCambio(ahora);
                registroSacEJB.modificarObligacionSac(obligacionSacFinanciacion);
                NovedadSacDTO novedadSacDTO = new NovedadSacDTO();
                novedadSacDTO.setFechaNovedad(obligacion.getFechaFactura());
                novedadSacDTO.setFechaRegistro(ahora);
                novedadSacDTO.setIdEstadoTransaccionSac(EnumEstadoTransaccionSac.TRANSACCION_NULA.getValue());
                novedadSacDTO.setIdTipoNovedadSac(EnumTipoNovedadSac.FINANCIACION.getValue());
                novedadSacDTO.setObligacionSac(obligacionSacFinanciacion);
                novedadSacDTO.setValorNovedad(obligacionFinanciacion.getValorObligacion());
                novedadSacDTO.setSoporteNovedad(obligacion.getNumeroFactura());
                registroSacEJB.registarNovedadSac(novedadSacDTO);
            }
        } else {
            idObligacionSac = registroSacEJB.registrarObligacionSac(obligacion).getId();
        }

        // persiste informacion para no ingresar datos repetidos en SAC
        ObligacionSacC2 obligacionSacC2 = new ObligacionSacC2();
        obligacionSacC2.setIdCartera(obligacion.getIdCartera());
        obligacionSacC2.setFechaHoraReplicaSac(ahora);
        obligacionSacC2.setIdObligacionSac(idObligacionSac);
        em.persist(obligacionSacC2);
        exitoso = true;
        return exitoso;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaProcesoSacDTO recibirGestionCobroSac(int codigoOrganismo) {
        logger.debug("SacEJB.recibirGestionCobroSac(int)");
        RespuestaProcesoSacDTO respuesta = new RespuestaProcesoSacDTO();
        int numeroExitosos = 0;
        int numeroFallos = 0;
        List<GestionCobroSacDTO> gestionesSac = registroSacEJB.consultarGestionCobroSac();
        Date fechaActual = new Date();
        for (GestionCobroSacDTO gestionCobroSacDTO : gestionesSac) {
            try {
                CarteraDTO carteraDTO = new CarteraDTO();
                carteraDTO.setNombre(gestionCobroSacDTO.getMulta());
                carteraDTO = iRFachadaCartera.consultarCartera(carteraDTO);

                if (carteraDTO != null) {
                    gestionCobroSacDTO.setIdCartera(carteraDTO.getId());
                    gestionCobroSacDTO.setFechaRegistro(fechaActual);
                    // verificar si el tipo de gestion es = Notificación y cobranza y el resultado de la accion = SI
                    if (gestionCobroSacDTO.getTipoGestionSac().equalsIgnoreCase(
                            EnumTipoGestionSac.NOTIFICAR_COBRANZA.getNombre())
                            && gestionCobroSacDTO.getResultadoAccion().equalsIgnoreCase("SI")) {
                        if (carteraDTO.getTipoObligacion().getId().intValue() == EnumTipoObligacion.COMPARENDO
                                .getValue().intValue()) {
                            // Consultar comparendo
                            ComparendoDTO comparendoDTO = iRFachadaComparendo.consultarComparendo(
                                    gestionCobroSacDTO.getMulta(), codigoOrganismo);
                            // Actualizar fecha y tipo de notificacion al comparendo si la fecha notificacion = NULL
                            if (comparendoDTO.getFechaNotificacion() == null) {
                                // Verificar con analisis catalogo enviado en campo accion, se deja por defecto Notificacion por aviso
                                TipoNotificacionComparendoDTO tipoNotificacionComparendoDTO = new TipoNotificacionComparendoDTO();
                                tipoNotificacionComparendoDTO
                                        .setId(EnumTipoNotificacionComparendo.NOTIFICACION_POR_AVISO.getValue());
                                comparendoDTO.setTipoNotificacion(tipoNotificacionComparendoDTO);
                                comparendoDTO.setFechaNotificacion(gestionCobroSacDTO.getFechaGestion());
                                sacEJB.actualizarFechaNotificacionComparendo(comparendoDTO);
                            }
                        }
                    }
                    // Registra la replica de gestion de sac
                    registroSacEJB.registarGestionCobroSac(gestionCobroSacDTO);
                    numeroExitosos++;
                } else {
                    // No esta registrado en C2
                    numeroFallos++;
                }
            } catch (Exception e) {
                logger.error("Error recibir gestion cobro " + e.getMessage());
                numeroFallos++;
            }
        }
        respuesta.setNumeroExitosos(numeroExitosos);
        respuesta.setNumeroFallidos(numeroFallos);
        return respuesta;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarFechaNotificacionComparendo(ComparendoDTO comparendoDTO) {
        logger.debug("actualizarFechaNotificacionComparendo(ComparendoDTO)");
        Comparendo comparendo = em.find(Comparendo.class, comparendoDTO.getCicomparendo());
        comparendo.setFechaNotificacion(comparendoDTO.getFechaNotificacion());
        comparendo.setTipoNotificacion(TipoNotificacionComparendoHelper.toLevel0Entity(
                comparendoDTO.getTipoNotificacion(), null));
        em.merge(comparendo);
    }
}
