
package co.com.datatools.c2.negocio.ejb;

import static co.com.datatools.c2.util.Utilidades.safeList;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.cartera.ILCarteraComparendo;
import co.com.datatools.c2.adaptador.dto.CarteraComparendoDTO;
import co.com.datatools.c2.adaptador.dto.ProcesoComparendoDTO;
import co.com.datatools.c2.adaptador.patio.IRPatioComparendo;
import co.com.datatools.c2.adaptador.proceso.IRProcesoComparendo;
import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.dt.administracion.ConsultaDireccionDTO;
import co.com.datatools.c2.dto.ConsultaResolucionesDTO;
import co.com.datatools.c2.dto.CorreoEnvioDTO;
import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.GeneraEdictoDTO;
import co.com.datatools.c2.dto.GeneraNotificacionCorreoDTO;
import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.dto.NotificacionComparendoTerceroDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RegistraProcesoDTO;
import co.com.datatools.c2.dto.ResolucionComparendoDTO;
import co.com.datatools.c2.dto.ResolucionDTO;
import co.com.datatools.c2.dto.TipoDocumentoProcesoDTO;
import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.dto.common.ConsultaObligacionesDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.AnulacionDTO;
import co.com.datatools.c2.dto.comparendo.AvisoGeneradoDTO;
import co.com.datatools.c2.dto.comparendo.CambioEstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteInconsistenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoCarteraDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoConsultaAnulacionDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionDescuentoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaAvisosNotificacionDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaCantidadComparendosNoNotificadosDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoAnulacionDTO;
//import co.com.datatools.c2.dto.comparendo.ConsultaComparendoCarteraDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ContenidoArchivoNotificacionDTO;
import co.com.datatools.c2.dto.comparendo.DocumentoAvisoGeneradoDTO;
import co.com.datatools.c2.dto.comparendo.EstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.HistoricoNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.InfraccionDTO;
import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.LogSolicitarNumeroComparendo;
import co.com.datatools.c2.dto.comparendo.NotificacionAvisoDTO;
import co.com.datatools.c2.dto.comparendo.NotificacionAvisoLogDTO;
import co.com.datatools.c2.dto.comparendo.NotificacionCompaCorreoLogDTO;
import co.com.datatools.c2.dto.comparendo.NotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.NotificarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.PatioComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ResolucionSancionAutomaticaLogDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaResolucionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoCargueArchivoNotificacionDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SaldoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SeguimientoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SolicitudNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SolicitudOrdenCompaNacioDTO;
import co.com.datatools.c2.dto.comparendo.TarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;
import co.com.datatools.c2.dto.comparendo.TrazabilidadComparendoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.configuracion.AsociaComparendoFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.entidades.Actividad;
import co.com.datatools.c2.entidades.Anulacion;
import co.com.datatools.c2.entidades.ArchivoAcuseNotificacion;
import co.com.datatools.c2.entidades.CampoArchivoAcuse;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoAgente;
import co.com.datatools.c2.entidades.ComparendoArchivoAcuse;
import co.com.datatools.c2.entidades.ComparendoCartera;
import co.com.datatools.c2.entidades.ComparendoPersona;
import co.com.datatools.c2.entidades.ComparendoProceso;
import co.com.datatools.c2.entidades.ComparendoResolucion;
import co.com.datatools.c2.entidades.ComparendoVehiculo;
import co.com.datatools.c2.entidades.ConfiguracionDescuento;
import co.com.datatools.c2.entidades.ErrorCargueAcuse;
import co.com.datatools.c2.entidades.EstadoComparendo;
import co.com.datatools.c2.entidades.EstadoComparendoSimit;
import co.com.datatools.c2.entidades.Evidencia;
import co.com.datatools.c2.entidades.HistoricoNumeroComparendo;
import co.com.datatools.c2.entidades.MensajeErrorCargueAcuse;
import co.com.datatools.c2.entidades.NotificacionAviso;
import co.com.datatools.c2.entidades.NotificacionCorreo;
import co.com.datatools.c2.entidades.NotificacionCorreoComparendo;
import co.com.datatools.c2.entidades.PagoComparendo;
import co.com.datatools.c2.entidades.ParticipanteProceso;
import co.com.datatools.c2.entidades.SolicitudOrdenCompaNacio;
import co.com.datatools.c2.entidades.TipoNotificacionComparendo;
import co.com.datatools.c2.entidades.TipoParticipante;
import co.com.datatools.c2.entidades.TrazabilidadComparendo;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.PersonaJuridica;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumEstadoTransaccionLog;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumMedioImposicion;
import co.com.datatools.c2.enumeracion.EnumOrigenNotificacionTercero;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumProceso;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoDescuento;
import co.com.datatools.c2.enumeracion.EnumTipoDia;
import co.com.datatools.c2.enumeracion.EnumTipoFechaOrigen;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;
import co.com.datatools.c2.enumeracion.EnumTipoSancion;
import co.com.datatools.c2.enumeraciones.EnumAccionComparendo;
import co.com.datatools.c2.enumeraciones.EnumCampoArchivoAcuse;
import co.com.datatools.c2.enumeraciones.EnumConfiguracion;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendoSimit;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumEstadoResolucion;
import co.com.datatools.c2.enumeraciones.EnumMsjErrorArchivoAcuse;
import co.com.datatools.c2.enumeraciones.EnumOpcConsultaCarteraComp;
import co.com.datatools.c2.enumeraciones.EnumRespuestaResolucionSancion;
import co.com.datatools.c2.enumeraciones.EnumTipoComparendo;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoGenerado;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoFormulario;
import co.com.datatools.c2.enumeraciones.EnumTipoInconsistenciaAgente;
import co.com.datatools.c2.enumeraciones.EnumTipoNotificacionComparendo;
import co.com.datatools.c2.enumeraciones.EnumTipoParticipante;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoResolucion;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosIllegalArgumentException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.ejb.utilidades.LoggerGenerarNotificacionAviso;
import co.com.datatools.c2.negocio.error.ErrorComparendo;
import co.com.datatools.c2.negocio.error.ErrorComparendo.ActualizarAgenteComparendo;
import co.com.datatools.c2.negocio.error.ErrorComparendo.AnularMultaTransito;
import co.com.datatools.c2.negocio.error.ErrorComparendo.ConsultarSeguimientoComparendo;
import co.com.datatools.c2.negocio.error.ErrorComparendo.ConsumirReservaOCN;
import co.com.datatools.c2.negocio.error.ErrorComparendo.GenerarNotificacionAviso;
import co.com.datatools.c2.negocio.error.ErrorComparendo.NotificarComparendoSIMIT;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminResolucion;
import co.com.datatools.c2.negocio.fachada.IRFachadaIntegracionTerceros;
import co.com.datatools.c2.negocio.fachada.IRFachadaProceso;
import co.com.datatools.c2.negocio.fachada.IRFachadaRegistroVehicular;
import co.com.datatools.c2.negocio.helpers.ProcesarPersonasHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.AnulacionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoAgenteHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.EstadoComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.EvidenciaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.HistoricoNumeroComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.NotificacionAvisoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TrazabilidadComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelperExtend;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.extencion.ComparendoHelperExtend;
import co.com.datatools.c2.negocio.helpers.extencion.ComparendoPersonaHelperExtend;
import co.com.datatools.c2.negocio.helpers.extencion.ConfiguracionDescuentoHelperExtend;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.LogEnvioCorreoHelper;
import co.com.datatools.c2.negocio.interfaces.ILAgente;
import co.com.datatools.c2.negocio.interfaces.ILComparendo;
import co.com.datatools.c2.negocio.interfaces.ILParametro;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRNotificacionComparendoTercero;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.administracion.ILAdministracionComparendo;
import co.com.datatools.c2.negocio.interfaces.administracion.ILInfraccion;
import co.com.datatools.c2.negocio.interfaces.administracion.ILTarifaInfraccion;
import co.com.datatools.c2.negocio.interfaces.administracion.IRDescuentoComparendo;
import co.com.datatools.c2.negocio.interfaces.formularios.ILAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.ILFormulario;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.util.Validador;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;
import co.com.datatools.util.file.archivo.FilaArchivoDTO;
import co.com.datatools.util.file.archivo.FilaArchivoExcelDTO;

@Stateless(name = "ComparendoEJB")
@LocalBean
public class ComparendoEJB implements ILComparendo, IRComparendo {

    private final static Logger logger = Logger.getLogger(ComparendoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILParametro parametroEJB;
    @EJB
    private ILAdministracionFormularios administracionFormulariosEjb;
    @EJB
    private ILFormulario iLFormulario;
    @EJB
    private ILTarifaInfraccion iLTarifaInfraccion;
    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneral;
    @EJB
    private IRFachadaConfiguracion iRFachadaConfiguracion;
    @EJB
    private IRPatioComparendo iPatioComparendo;
    @EJB
    private IRProcesoComparendo iRProcesoComparendo;
    @EJB
    private ILCarteraComparendo iCarteraComparendo;
    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;
    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;
    @EJB
    private IRRepositorioArchivo iRRepositorioArchivo;
    @EJB
    private ILAdministracionComparendo administracionComparendosEjb;
    @EJB
    private IRFachadaAdminResolucion fachadaAdminResolucionEJB;
    @EJB
    private IRFachadaAdminNegocio fachadaAdminNegocioEJB;
    @EJB
    private IRCirculemosMailSender iRCirculemosMailSender;
    @EJB
    private ILInfraccion infraccionEJB;
    @EJB
    private ILTarifaInfraccion tarifaInfraccionEJB;
    @EJB
    private IRFachadaRegistroVehicular fachadaRegistroVehicular;

    @EJB
    private IRFachadaProceso iRFachadaProceso;

    @EJB
    private ILAgente agenteEJB;
    @EJB
    private IRNotificacionComparendoTercero notificacionComparendoTercero;
    @EJB
    private IRDescuentoComparendo iRDescuentoComparendo;

    @EJB
    private IRFachadaIntegracionTerceros fachadaIntegracionTercerosEJB;

    @EJB
    private ILComparendo iLComparendo;

    @EJB
    private IRPersona iRPersona;

    @Override
    public boolean existeComparendo(String numeroComparendo, int codigoOrganismo) {
        logger.debug("ComparendoEJB.existeComparendo(String,int)");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(c) FROM Comparendo AS c");
        sql.append(" WHERE c.ordenComparendoNacional.numeroComparendo = :numComparendo");
        sql.append(" AND c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codOrganismo");

        final TypedQuery<Long> query = em.createQuery(sql.toString(), Long.class);
        query.setParameter("numComparendo", numeroComparendo).setParameter("codOrganismo", codigoOrganismo);
        long i = (Long) query.getSingleResult();
        return (i == 0) ? false : true;
    }

    @Override
    public ComparendoDTO consultarComparendo(String numeroComparendo, Integer codigoOrganismo) {
        logger.debug(ComparendoEJB.class.getName().concat("::existeComparendo(String,int)"));
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT c FROM Comparendo c");
        jpql.append(" WHERE c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", codigoOrganismo);

        jpql.append(" AND c.ordenComparendoNacional.numeroComparendo = :pNumComp");
        filtros.put("pNumComp", numeroComparendo);

        GenericDao<Comparendo> dao = new GenericDao<Comparendo>(Comparendo.class, em);
        List<Comparendo> resultadoConsulta = dao.buildAndExecuteQuery(jpql, filtros);
        if (resultadoConsulta.isEmpty()) {
            return null;
        }
        return ComparendoHelperExtend.toLevel1DTO(resultadoConsulta.get(0));
    }

    @Override
    public List<ComparendoDTO> consultarComparendos(ConsultaComparendoDTO consultaComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.consultarComparendos(ConsultaComparendoDTO)");
        List<ComparendoDTO> lsResultado = null;

        if (consultaComparendoDTO.getFechaInicioImposicion() != null
                && consultaComparendoDTO.getFechaFinImposicion() != null) {
            if (!consultaComparendoDTO.getFechaInicioImposicion()
                    .after(consultaComparendoDTO.getFechaFinImposicion())) {
                ValorParametroDTO valorParametroDTO = parametroEJB.consultarParametro(
                        EnumParametro.MAX_DIAS_CONSULTA_COMPARENDOS, consultaComparendoDTO.getCodigoOrganismo(), true);

                long diasDiferencia = Math.abs(consultaComparendoDTO.getFechaFinImposicion().getTime()
                        - consultaComparendoDTO.getFechaInicioImposicion().getTime());
                diasDiferencia = diasDiferencia / (24 * 60 * 60 * 1000);

                int diasMax = Integer.valueOf(valorParametroDTO.getValores().get(0));

                if (diasDiferencia > diasMax) {
                    throw new CirculemosNegocioException(ErrorComparendo.ConsultarComparendos.COM_045001);
                }
            }
        }

        Boolean seleccionValida = false;

        GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT c FROM Comparendo c");
        jpql.append(" LEFT JOIN c.personaList plInf");
        jpql.append(" LEFT JOIN c.personaList plPro");
        jpql.append(" LEFT JOIN plInf.tipoPersonaComparendo tpcInf");
        jpql.append(" LEFT JOIN plPro.tipoPersonaComparendo tpcPro");
        jpql.append(" WHERE 1=1");

        if (consultaComparendoDTO.getCodigoOrganismo() != null) {
            jpql.append(" AND c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codigoOrganismo");
            filtros.put("codigoOrganismo", consultaComparendoDTO.getCodigoOrganismo());
        }

        if (consultaComparendoDTO.isEsPolca() != null) {
            jpql.append(" AND c.esPolca = :esPolca");
            filtros.put("esPolca", consultaComparendoDTO.isEsPolca());
        }

        if (consultaComparendoDTO.getIdTipoComparendo() != null) {
            jpql.append(" AND c.tipoComparendo.id = :idTipoComparendo");
            filtros.put("idTipoComparendo", consultaComparendoDTO.getIdTipoComparendo());
        }

        if (consultaComparendoDTO.getNumeroCitacion() != null && consultaComparendoDTO.getAnioCitacion() != null) {
            jpql.append(" AND c.numeroCitacion = :numeroCitacion");
            filtros.put("numeroCitacion", consultaComparendoDTO.getNumeroCitacion());

            jpql.append(" AND c.fechaInfraccion >= :fechaInicioAnio AND c.fechaInfraccion <= :fechaFinAnio");

            Calendar fechaInicio = Calendar.getInstance();
            fechaInicio.set(consultaComparendoDTO.getAnioCitacion(), 0, 1, 0, 0, 0);
            filtros.put("fechaInicioAnio", fechaInicio.getTime());
            Calendar fechaFinal = Calendar.getInstance();
            fechaFinal.set(consultaComparendoDTO.getAnioCitacion(), 11, 31, 23, 59, 59);
            filtros.put("fechaFinAnio", fechaFinal.getTime());

            seleccionValida = true;
        }

        if (consultaComparendoDTO.getNumeroComparendo() != null) {
            jpql.append(" AND c.ordenComparendoNacional.numeroComparendo = :numeroComparendo");
            filtros.put("numeroComparendo", consultaComparendoDTO.getNumeroComparendo());
            seleccionValida = true;
        }

        if (consultaComparendoDTO.getPlacaVehiculo() != null) {
            jpql.append(" AND c.comparendoVehiculo.placaVehiculo = :placaVehiculo");
            filtros.put("placaVehiculo", consultaComparendoDTO.getPlacaVehiculo());
            seleccionValida = true;
        }

        if (consultaComparendoDTO.getFechaInicioImposicion() != null
                && consultaComparendoDTO.getFechaFinImposicion() != null) {
            jpql.append(
                    " AND c.fechaInfraccion >= :fechaImposicionInicial AND c.fechaInfraccion <= :fechaImposicionFinal ");
            filtros.put("fechaImposicionInicial", consultaComparendoDTO.getFechaInicioImposicion());
            filtros.put("fechaImposicionFinal", consultaComparendoDTO.getFechaFinImposicion());
            seleccionValida = true;
        }

        if (consultaComparendoDTO.getTipoDocumentoInfractor() != null
                && consultaComparendoDTO.getNumeroDocumentoInfractor() != null) {
            jpql.append(" AND plInf.tipoIdentificacion.id = :idTipoDocInfractor");
            filtros.put("idTipoDocInfractor", consultaComparendoDTO.getTipoDocumentoInfractor());
            jpql.append(" AND plInf.numeroIdentificacion = :numeroIdentificacionInfractor");
            filtros.put("numeroIdentificacionInfractor", consultaComparendoDTO.getNumeroDocumentoInfractor());
            jpql.append(" AND tpcInf.codigo = :tipoPersonaComparendoInf");
            filtros.put("tipoPersonaComparendoInf", EnumTipoPersonaComparendo.INFRACTOR.getCodigo());
            seleccionValida = true;
        }

        if (consultaComparendoDTO.getTipoDocumentoPropietario() != null
                && consultaComparendoDTO.getNumeroDocumentoPropietario() != null) {
            jpql.append(" AND plPro.tipoIdentificacion.id = :idTipoDocPropietario");
            filtros.put("idTipoDocPropietario", consultaComparendoDTO.getTipoDocumentoPropietario());
            jpql.append(" AND plPro.numeroIdentificacion = :numeroIdentificacionPropietario");
            filtros.put("numeroIdentificacionPropietario", consultaComparendoDTO.getNumeroDocumentoPropietario());
            jpql.append(" AND tpcPro.codigo = :tipoPersonaComparendoProp");
            filtros.put("tipoPersonaComparendoProp", EnumTipoPersonaComparendo.PROPIETARIO.getCodigo());
            seleccionValida = true;
        }

        if (consultaComparendoDTO.getTipoDocumentoAgente() != null
                && consultaComparendoDTO.getNumeroDocumentoAgente() != null) {
            jpql.append(" AND c.comparendoAgente.tipoIdentificacionPersona.id = :idTipoDocAgente");
            filtros.put("idTipoDocAgente", consultaComparendoDTO.getTipoDocumentoAgente());
            jpql.append(" AND c.comparendoAgente.numeroIdentificacion = :numeroIdentificacionAgente");
            filtros.put("numeroIdentificacionAgente", consultaComparendoDTO.getNumeroDocumentoAgente());
            seleccionValida = true;
        }

        if (consultaComparendoDTO.getIdTipoComparendo() == EnumTipoComparendo.COMPARENDO_NACIONAL.getCodigo()
                || consultaComparendoDTO.getIdTipoComparendo() == EnumTipoComparendo.PAPELETA.getCodigo()) {
            jpql.append(" ORDER BY c.fechaInfraccion, c.horaInfraccion DESC");
        }

        if (!seleccionValida
                && consultaComparendoDTO.getIdTipoComparendo() == EnumTipoComparendo.ACTA_CONTROL.getCodigo()) {
            seleccionValida = true;
        }

        if (seleccionValida) {
            List<Comparendo> resultadoConsulta = comparendoDao.buildAndExecuteQuery(jpql, filtros);
            if (!resultadoConsulta.isEmpty()) {
                lsResultado = ComparendoHelperExtend.toListLevel1DTOExtTipoPersona(resultadoConsulta);
            }
        } else {
            throw new CirculemosNegocioException(ErrorComparendo.ConsultarComparendos.COM_045002);
        }
        return lsResultado;
    }

    @Override
    public ComparendoDTO consultarComparendo(Long cicomparendo) {
        logger.debug("ComparendoEJB.consultarComparendo(long)");
        ComparendoDTO compDTO = null;
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT c FROM Comparendo AS c");
        jpql.append(" LEFT JOIN FETCH c.ordenComparendoNacional as ocn");
        jpql.append(" LEFT JOIN FETCH c.tipoOrigen");
        jpql.append(" LEFT JOIN FETCH c.usuarioPersona");
        jpql.append(" LEFT JOIN FETCH c.medioImposicion");
        jpql.append(" LEFT JOIN FETCH c.tipoInfractor");
        jpql.append(" LEFT JOIN FETCH c.tipoNotificacion");
        jpql.append(" LEFT JOIN FETCH c.direccion");
        jpql.append(" LEFT JOIN FETCH c.infraccion");
        jpql.append(" LEFT JOIN FETCH c.ruta");
        jpql.append(" LEFT JOIN FETCH c.tipoComparendo");
        jpql.append(" LEFT JOIN FETCH c.gradoAlcoholemia");
        jpql.append(" LEFT JOIN FETCH c.estadoComparendo");
        jpql.append(" LEFT JOIN FETCH c.comparendoAgente AS ca");
        jpql.append(" LEFT JOIN FETCH ca.agente");
        jpql.append(" LEFT JOIN FETCH c.comparendoPatio cpt");
        jpql.append(" LEFT JOIN FETCH cpt.direccion");
        jpql.append(" LEFT JOIN FETCH c.comparendoVehiculo as cv");
        jpql.append(" LEFT JOIN FETCH cv.claseVehiculo");
        jpql.append(" LEFT JOIN FETCH c.personaList");

        jpql.append(" WHERE c.cicomparendo = :pCiComp");
        filtros.put("pCiComp", cicomparendo);

        GenericDao<Comparendo> dao = new GenericDao<>(Comparendo.class, em);
        List<Comparendo> lstResultado = dao.buildAndExecuteQuery(jpql, filtros);
        if (!lstResultado.isEmpty()) {
            Comparendo comparendo = lstResultado.get(0);
            compDTO = ComparendoHelperExtend.toLevel1DTO(comparendo);

            // Extraccion de la configuracion de la infraccion
            InfraccionDTO infraccion = compDTO.getInfraccion();
            ConfiguracionInfraccionDTO confInf = infraccionEJB.consultarInfraccion(infraccion.getCodigo(),
                    comparendo.getFechaInfraccion());
            infraccion.setConfiguracionInfraccionList(new ArrayList<ConfiguracionInfraccionDTO>(0));
            infraccion.getConfiguracionInfraccionList().add(confInf);

            // Informacion del agente del comparendo
            if (compDTO.getComparendoAgente() != null) {
                if (compDTO.getComparendoAgente().getUnificacionResponsable() != null) {
                    UnificacionResponsableDTO unificacionResponsable = new UnificacionResponsableDTO(
                            compDTO.getComparendoAgente().getUnificacionResponsable().getId());
                    try {
                        unificacionResponsable = administracionFormulariosEjb
                                .consultarResponsableFormularios(unificacionResponsable);
                    } catch (CirculemosNegocioException e) {
                        logger.debugv("Responsable no esta activo [ idUnificacionResponsable:{0} ]",
                                unificacionResponsable.getId());
                    }

                    if (unificacionResponsable != null) {
                        unificacionResponsable.setId(compDTO.getComparendoAgente().getUnificacionResponsable().getId());
                        compDTO.getComparendoAgente().setUnificacionResponsable(unificacionResponsable);
                    }
                }
            }

            // Inmovilizacion
            if (compDTO.getComparendoPatio() != null) {
                if (comparendo.getComparendoPatio().getDireccion() != null) {
                    compDTO.getComparendoPatio().setDireccion(
                            DireccionHelperExtend.toLevel1DTO(comparendo.getComparendoPatio().getDireccion()));
                }
                if (comparendo.getComparendoPatio().getIdPatio() != null) {
                    PatioComparendoDTO patioComparendoDTO = new PatioComparendoDTO();
                    patioComparendoDTO.setId(comparendo.getComparendoPatio().getIdPatio());
                    List<PatioComparendoDTO> patios = iPatioComparendo.consultarPatios(patioComparendoDTO);
                    if (!patios.isEmpty()) {
                        compDTO.getComparendoPatio().setPatio(patios.get(0));
                    }
                }
            }

        }

        return compDTO;
    }

    @Override
    public boolean validarFormatoNumeroComparendo(String numeroComparendo, int tipoComparendo)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.validarFormatoNumeroComparendo(String,int,Date)");

        /*
         * Datos de la configuracion 008 creacion de DTO con campos de entrada (Tipo comparendo) y salida (Tipo formulario)
         */
        // Ingreso el tipo comparendo
        AsociaComparendoFormularioDTO asociaComparendoFormularioDTO = new AsociaComparendoFormularioDTO();
        List<String> tipoComparendos = new ArrayList<String>();
        tipoComparendos.add(String.valueOf(tipoComparendo));
        asociaComparendoFormularioDTO.setTipoComparendo(tipoComparendos);

        // Me devuelve el tipo de formulario
        asociaComparendoFormularioDTO = iRFachadaConfiguracion.consultarValorConfiguracion(
                EnumConfiguracion.ASOCIA_TIPOS_COMPARENDO_FORMULARIO.getCodigo(), asociaComparendoFormularioDTO);

        return iLFormulario.validarFormatoNumeroFormulario(numeroComparendo,
                Integer.parseInt(asociaComparendoFormularioDTO.getTipoFormulario().get(0)));
    }

    @Override
    public List<ResultadoConsultaNotificacionComparendoDTO> consultarComparendosNotificados(
            ConsultaNotificacionComparendoDTO consultaNotificacionComparendoDTO,
            EnumTipoNotificacionComparendo... tipoNotificacion) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.consultarComparendosNotificados(ConsultaNotificacionComparendoDTO,Integer...)");

        List<ResultadoConsultaNotificacionComparendoDTO> lsResultado = null;

        Boolean seleccionValida = false;

        GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT c ");
        jpql.append(" FROM Comparendo c");
        jpql.append(" LEFT JOIN c.personaList plInf");
        jpql.append(" LEFT JOIN plInf.tipoPersonaComparendo tpcInf");
        jpql.append(" LEFT JOIN c.comparendoCarteraList cc");
        jpql.append(" WHERE");

        jpql.append(" c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", consultaNotificacionComparendoDTO.getCodigoOrganismo());

        if (consultaNotificacionComparendoDTO.getEsPolca() != null) {
            jpql.append(" AND c.esPolca = :esPolca");
            filtros.put("esPolca", consultaNotificacionComparendoDTO.getEsPolca());
        }

        if (StringUtils.isNotBlank(consultaNotificacionComparendoDTO.getNumeroComparendo())) {
            jpql.append(" AND c.ordenComparendoNacional.numeroComparendo = :numeroComparendo");
            filtros.put("numeroComparendo", consultaNotificacionComparendoDTO.getNumeroComparendo());
            seleccionValida = true;
        }
        List<Integer> tipoNotificacionList = new ArrayList<>();
        if (tipoNotificacion != null && tipoNotificacion.length > 0) {
            //
            for (EnumTipoNotificacionComparendo tipoNotificacionAux : tipoNotificacion) {
                if (tipoNotificacionAux.equals(EnumTipoNotificacionComparendo.VACIO)) {
                    jpql.append(" AND ");
                    if (tipoNotificacion.length > 1)
                        jpql.append("(");
                    jpql.append("c.tipoNotificacion IS NULL");
                } else {
                    tipoNotificacionList.add(tipoNotificacionAux.getValue());
                }
            }
            //
            if (!tipoNotificacionList.isEmpty()) {
                jpql.append(" OR c.tipoNotificacion.id IN (:idsTipoNotificacion)");
                if (tipoNotificacion.length > 1)
                    jpql.append(")");
                filtros.put("idsTipoNotificacion", tipoNotificacionList);// Arrays.asList(tipoNotificacionList
            }

        }

        if (consultaNotificacionComparendoDTO.getTipoDocumentoInfractor() != null
                && StringUtils.isNotBlank(consultaNotificacionComparendoDTO.getNumeroDocumentoInfractor())) {
            jpql.append(" AND plInf.tipoIdentificacion.id = :idTipoDocInfractor");
            filtros.put("idTipoDocInfractor", consultaNotificacionComparendoDTO.getTipoDocumentoInfractor());
            jpql.append(" AND plInf.numeroIdentificacion = :numeroIdentificacionInfractor");
            filtros.put("numeroIdentificacionInfractor",
                    consultaNotificacionComparendoDTO.getNumeroDocumentoInfractor());
            jpql.append(" AND tpcInf.codigo = :tipoPersonaComparendoInf");
            filtros.put("tipoPersonaComparendoInf", EnumTipoPersonaComparendo.INFRACTOR.getValue());
            seleccionValida = true;
        }

        if (seleccionValida) {
            List<Comparendo> resultadoConsulta = comparendoDao.buildAndExecuteQuery(jpql, filtros);
            lsResultado = new ArrayList<ResultadoConsultaNotificacionComparendoDTO>();
            for (Comparendo comparendo : resultadoConsulta) {
                ResultadoConsultaNotificacionComparendoDTO resultado = ComparendoHelperExtend
                        .toLevel1DTONotificacion(comparendo);
                // Consulta la tarifa del comparendo
                TarifaInfraccionDTO tarifa = iLTarifaInfraccion.consultarTarifaInfraccion(
                        comparendo.getInfraccion().getId(), new BigDecimal(0), comparendo.getFechaInfraccion());
                if (tarifa != null) {
                    resultado.setValorInfraccion(tarifa.getValorInfraccion());

                }
                lsResultado.add(resultado);
            }
        } else {
            throw new CirculemosNegocioException(ErrorComparendo.NotificarComparendosPresencialmente.COM_049001);
        }
        return lsResultado;
    }

    @Override
    public byte[] notificarComparendos(NotificacionComparendoDTO notificacionComparendo)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.notificarComparendos(NotificacionComparendoDTO)");
        byte[] archivoResultado = null;

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT c ");
        jpql.append(" FROM Comparendo c");
        jpql.append(" LEFT JOIN c.comparendoCarteraList cc");
        jpql.append(" WHERE 1=1");

        if (notificacionComparendo.getComparendos() != null && !notificacionComparendo.getComparendos().isEmpty()) {
            jpql.append(" AND c.ordenComparendoNacional.numeroComparendo IN (:numerosComparendo)");
            filtros.put("numerosComparendo", notificacionComparendo.getComparendos());
        } else {
            throw new CirculemosNegocioException(ErrorComparendo.NotificarComparendosPresencialmente.COM_049005);
        }

        GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
        List<Comparendo> resultadoConsulta = comparendoDao.buildAndExecuteQuery(jpql, filtros);
        if (resultadoConsulta.isEmpty()) {
            throw new CirculemosNegocioException(ErrorComparendo.NotificarComparendosPresencialmente.COM_049004);
        }
        List<Long> idsDocumentosGenerados = new ArrayList<Long>();
        for (Comparendo comparendo : resultadoConsulta) {
            // Valida que la 'Fecha Notificacion' es MAYOR O IGUAL a la 'Fecha de imposicion comparendo'
            if (comparendo.getFechaInfraccion().after(notificacionComparendo.getFechaNotificacion())) {
                throw new CirculemosNegocioException(ErrorComparendo.NotificarComparendosPresencialmente.COM_049002);
            }
            /*
             * Verifica si el 'Codigo de la infraccion' del 'Numero de Comparendo' bajo la configuracion del caso de uso 'Administrar infracciones'
             * tiene el campo 'Genera cartera?' es IGUAL A 'SI'
             */
            ConfiguracionInfraccionDTO configInfraccion = infraccionEJB
                    .consultarInfraccion(comparendo.getInfraccion().getCodigo(), comparendo.getFechaInfraccion());
            // if (configInfraccion.getGeneraCartera()) {
            /*
             * Verifica si el 'Numero de Comparendo' TIENE UNA CARTERA REGISTRADA consultandolo en Cartera
             */
            // if (!comparendo.getComparendoCarteraList().isEmpty()) {
            // for (ComparendoCartera comparendoCartera : comparendo.getComparendoCarteraList()) {
            /*
             * Se activa el registro de la cartera asociada a la obligacion y se registra la trazabilidad incluyendo el caso de uso 'Cambiar estado de
             * cartera'
             */
            // iCarteraComparendo.activarCarteraComparendo(comparendoCartera.getIdCartera(),
            // notificacionComparendo.getFechaNotificacion());
            // }
            /*
             * Cambia el estado del (los) comparendo (s) de la siguiente manera: - Estado de Comparendo = Vigente
             */

            // EstadoComparendo estadoComparendoVigente = new EstadoComparendo();
            // estadoComparendoVigente.setId(EnumEstadoComparendo.VIGENTE.getValue());
            // comparendo.setEstadoComparendo(estadoComparendoVigente);

            // comparendo.setEstadoComparendo(em.getReference(EstadoComparendo.class,
            // EnumEstadoComparendo.VIGENTE.getCodigo()));
            // }
            // } else {
            /*
             * Cambia el estado del (los) comparendo (s) de la siguiente manera: - Estado de Comparendo = Vigente
             */

            // EstadoComparendo estadoComparendoVigente = new EstadoComparendo();
            // estadoComparendoVigente.setId(EnumEstadoComparendo.VIGENTE.getValue());
            // comparendo.setEstadoComparendo(estadoComparendoVigente);

            // comparendo.setEstadoComparendo(em.getReference(EstadoComparendo.class,
            // EnumEstadoComparendo.VIGENTE.getCodigo()));
            // }

            // comparendo.setEstadoComparendo(em.getReference(EstadoComparendo.class,
            // EnumEstadoComparendo.VIGENTE.getCodigo()));

            // Guarda datos de la notificacion
            // comparendo.setFechaNotificacion(notificacionComparendo.getFechaNotificacion());
            // comparendo.setFechaModificacion(new Date());

            // TipoNotificacionComparendo tipoNotificacion = new TipoNotificacionComparendo();
            // tipoNotificacion.setId(notificacionComparendo.getIdTipoNotificacion());
            // comparendo.setTipoNotificacion(tipoNotificacion);

            // comparendo.setTipoNotificacion(em.getReference(TipoNotificacionComparendo.class,
            // notificacionComparendo.getIdTipoNotificacion()));

            // Completa datos de trazabilidad
            // TrazabilidadComparendo trazabilidad = null;
            // if (EnumTipoNotificacionComparendo.NOTIFICACION_PERSONAL.getValue().equals(
            // notificacionComparendo.getIdTipoNotificacion())) {
            // trazabilidad = new TrazabilidadComparendo();
            //
            // // Actividad
            // Actividad actividad = new Actividad();
            // actividad.setId(EnumActividad.NOTIFICACION_PERSONAL_COMPARENDO.getValue());
            // trazabilidad.setActividad(actividad);
            // } else if (EnumTipoNotificacionComparendo.NOTIFICACION_POR_CONDUCTA_CONCLUYENTE.getValue().equals(
            // notificacionComparendo.getIdTipoNotificacion())) {
            // trazabilidad = new TrazabilidadComparendo();
            //
            // // Actividad
            // Actividad actividad = new Actividad();
            // actividad.setId(EnumActividad.NOTIFICACION_POR_CONDUCTA_CONCLUYENTE_COMPARENDO.getValue());
            // trazabilidad.setActividad(actividad);
            // } else {
            // throw new CirculemosNegocioException(ErrorComparendo.NotificarComparendosPresencialmente.COM_049003);
            // }
            // trazabilidad.setEstadoComparendo(comparendo.getEstadoComparendo());
            // trazabilidad.setFechaRegistro(comparendo.getFechaModificacion());
            //
            // UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            // if (usuarioPersonaDTO.getLogin() != null || usuarioPersonaDTO.getPersona() != null) {
            // trazabilidad.setUsuario(UsuarioPersonaHelper.toLevel1Entity(usuarioPersonaDTO, null));
            // }
            // trazabilidad.setFechaEjecucion(comparendo.getFechaNotificacion());
            // trazabilidad.setComparendo(comparendo);
            // // Agrega trazabilidad al comparendo
            // comparendo.getTrazabilidadComparendoList().add(trazabilidad);

            int idActividad;
            if (EnumTipoNotificacionComparendo.NOTIFICACION_PERSONAL.getValue()
                    .equals(notificacionComparendo.getIdTipoNotificacion())) {
                idActividad = EnumActividad.NOTIFICACION_PERSONAL_COMPARENDO.getValue();
            } else if (EnumTipoNotificacionComparendo.NOTIFICACION_POR_CONDUCTA_CONCLUYENTE.getValue()
                    .equals(notificacionComparendo.getIdTipoNotificacion())) {
                idActividad = EnumActividad.NOTIFICACION_POR_CONDUCTA_CONCLUYENTE_COMPARENDO.getValue();
            } else {
                throw new CirculemosNegocioException(ErrorComparendo.NotificarComparendosPresencialmente.COM_049003);
            }

            // this.generarTrazabilidad(comparendo, idActividad, comparendo.getFechaNotificacion());

            NotificarComparendoDTO notificacion = new NotificarComparendoDTO();
            notificacion.setFechaNotificacion(notificacionComparendo.getFechaNotificacion());
            notificacion.setIdActividadTrazabilidad(idActividad);
            notificacion.setIdTipoNotificacion(notificacionComparendo.getIdTipoNotificacion());
            notificacion.setActivaCartera(configInfraccion.getGeneraCartera());
            //
            this.notificarComparendo(comparendo, notificacion);

            this.notificarComparendoTerceros(comparendo);
            /*
             * Genera el documento de uno o varios documentos asociado(s) a la plantilla a la notificacion bajo la inclusion del caso de uso 'Generar
             * Documento circulemos 2.0',
             */
            // GeneraDocumentoNotificacionPresencialDTO generaDocumento = new GeneraDocumentoNotificacionPresencialDTO();
            GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
            if (EnumTipoNotificacionComparendo.NOTIFICACION_PERSONAL.getValue()
                    .equals(notificacionComparendo.getIdTipoNotificacion())) {
                // Nombre del documento Circulemos 2 = Notificacion personal del comparendo.
                generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.NOTIFICACION_PERSONAL);
            } else if (EnumTipoNotificacionComparendo.NOTIFICACION_POR_CONDUCTA_CONCLUYENTE.getValue()
                    .equals(notificacionComparendo.getIdTipoNotificacion())) {
                // Nombre del documento Circulemos 2 = Notificacion por conducta concluyente del comparendo.
                generaDocumento
                        .setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.NOTIFICACION_POR_CONDUCTA_CONCLUYENTE);
            }
            generaDocumento.setCodigoOrganismo(
                    comparendo.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
            // generaDocumento.setNumeroComparendo(comparendo.getOrdenComparendoNacional().getNumeroComparendo());
            generaDocumento.setCicomparendo(comparendo.getCicomparendo());
            generaDocumento.setFechaGeneracion(new Date());

            Long idDocumento;
            try {
                idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);
            } catch (CirculemosAlertaException e) {
                throw new CirculemosNegocioException(e.getErrorInfo());
            }

            comparendo.setIdDocumentoNotificacion(idDocumento);

            // Adiciona a lista de documentos generados para posteriormente unirlos en un solo pdf
            idsDocumentosGenerados.add(idDocumento);

            // Realiza actualizacion del comparendo
            em.merge(comparendo);
        }

        /*
         * Realiza la union de los documentos y genera un solo PDF con los documentos generados bajo la inclusion del caso de uso 'Obtener documento'
         */
        try {
            archivoResultado = iRDocumentosCirculemos.consultarDocumentosPDF(idsDocumentosGenerados);
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(e.getErrorInfo());
        }
        return archivoResultado;
    }

    @Override
    public String obtenerNombreEvidencia(EvidenciaDTO evidencia) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.obtenerNombreEvidencia(EvidenciaDTO)");
        Date ahora = UtilFecha.buildCalendar().getTime();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMddHHmmss");
        String extensiones[] = new String[] { ".tiff", ".pdf", ".png", ".jpg" };

        if (evidencia == null)
            throw new CirculemosNegocioException(ErrorComparendo.ConsultarComparendos.COM_045003);
        if (evidencia.getTipoEvidencia() == null && evidencia.getTipoEvidencia().getId() == null)
            throw new CirculemosNegocioException(ErrorComparendo.ConsultarComparendos.COM_045004);
        if (evidencia.getArchivoTransportable() == null && evidencia.getArchivoTransportable().getNombre() == null)
            throw new CirculemosNegocioException(ErrorComparendo.ConsultarComparendos.COM_045005);
        if (FilenameUtils.isExtension(evidencia.getArchivoTransportable().getNombre(), extensiones))
            throw new CirculemosNegocioException(ErrorComparendo.ConsultarComparendos.COM_045006);

        StringBuilder nombreArchivoEvidencia = new StringBuilder();
        nombreArchivoEvidencia.append(evidencia.getTipoEvidencia().getId()).append("-")
                .append(formatoFecha.format(ahora)).append(FilenameUtils.EXTENSION_SEPARATOR)
                .append(evidencia.getArchivoTransportable().getExtension());

        return nombreArchivoEvidencia.toString();
    }

    @Override
    public List<EvidenciaDTO> registrarEvidencias(long cicomparendo, List<EvidenciaDTO> evidenciasDTO)
            throws CirculemosAlertaException, CirculemosNegocioException {
        logger.debug("ComparendoEJB.registrarEvidencias(Long, List<EvidenciaDTO>)");
        List<Integer> idEvidenciasComparendo = new ArrayList<>();
        List<EvidenciaDTO> evidenciasRegistradas = new ArrayList<>();
        ComparendoDTO comparendo = consultarComparendo(cicomparendo);

        // Evidencias actuales
        for (EvidenciaDTO evidenciaComparendo : comparendo.getEvidenciaList())
            idEvidenciasComparendo.add(evidenciaComparendo.getId());

        // Consultar y validar parametro
        ValorParametroDTO valorParam = fachadaAdminGeneral.consultarValorParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                comparendo.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(), true);
        int maxTamano = Integer.valueOf(valorParam.getValorParam());
        valorParam = fachadaAdminGeneral.consultarValorParametro(EnumParametro.RAIZ_RUTA_ARCHIVOS,
                comparendo.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(), true);
        String raizRutaArchivo = valorParam.getValorParam();

        // Crear ruta
        StringBuilder rutaEvidencia = new StringBuilder();
        rutaEvidencia.append(raizRutaArchivo).append(File.separatorChar)
                .append(comparendo.getOrdenComparendoNacional().getNumeroComparendo())
                .append(EnumProceso.INGRESAR_COMPARENDO.getRuta()).append(File.separatorChar);

        for (EvidenciaDTO evidenciaDTO : evidenciasDTO) {
            // Validar si existe evidencia
            boolean existeEvidencia = false;

            if (evidenciaDTO.getId() != null)
                for (Integer id : idEvidenciasComparendo)
                    if (id.equals(evidenciaDTO.getId())) {
                        existeEvidencia = true;
                        break;
                    }

            if (existeEvidencia)
                continue;

            // Validar tamano
            if (evidenciaDTO.getArchivoTransportable().getContenido().length > maxTamano)
                throw new CirculemosNegocioException(ErrorComparendo.ConsultarComparendos.COM_045007);

            // Registrar archivo en repositorio
            evidenciaDTO.getArchivoTransportable().setRuta(rutaEvidencia.toString());
            String idDocumento = iRRepositorioArchivo.registrarDocumento(EnumCategoriaDocumento.COMPARENDO_EVIDENCIA,
                    evidenciaDTO.getArchivoTransportable());

            // Registrar evidencia de comparendo
            evidenciaDTO.setId(null);
            evidenciaDTO.setFechaEvidencia(UtilFecha.buildCalendar().getTime());
            evidenciaDTO.setComparendo(comparendo);
            evidenciaDTO.setIdDocumento(idDocumento);

            Evidencia evidencia = new Evidencia();
            em.persist(EvidenciaHelper.toLevel1Entity(evidenciaDTO, evidencia));

            evidenciaDTO.setId(evidencia.getId());
            evidenciaDTO.setArchivoTransportable(null);
            evidenciaDTO.setComparendo(new ComparendoDTO(comparendo.getCicomparendo()));
            evidenciasRegistradas.add(evidenciaDTO);
        }

        return evidenciasRegistradas;
    }

    @Override
    public void eliminarEvidenciasComparendo(long cicomparendo, List<Integer> idEvidencias)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.eliminarEvidenciasComparendo(long, List<Integer>)");
        List<Integer> idEvidenciasComparendo = new ArrayList<>();
        ComparendoDTO comparendo = consultarComparendo(cicomparendo);

        // Evidencias actuales
        for (EvidenciaDTO evidenciaComparendo : comparendo.getEvidenciaList())
            idEvidenciasComparendo.add(evidenciaComparendo.getId());

        for (int idEvidencia : idEvidencias) {
            // Validar si existe evidencia
            boolean existeEvidencia = false;

            for (Integer id : idEvidenciasComparendo)
                if (id == idEvidencia) {
                    existeEvidencia = true;
                    break;
                }

            if (existeEvidencia) {
                // Eliminar evidencia de comparendo
                eliminarEvidenciaComparendo(idEvidencia);
            } else
                throw new CirculemosNegocioException(ErrorComparendo.ConsultarComparendos.COM_045008);
        }
    }

    private void eliminarEvidenciaComparendo(int idEvidencia) {
        Query query = em.createQuery("DELETE FROM Evidencia WHERE id = " + idEvidencia);
        query.executeUpdate();
    }

    @Override
    public Integer consultarCantidadComparendosNoNotificados(
            ConsultaCantidadComparendosNoNotificadosDTO consultarCantidadComparendosNoNotificadosDTO)
            throws CirculemosNegocioException {
        logger.debug(
                "ComparendoEJB::consultarCantidadComparendosNoNotificados(ConsultaCantidadComparendosNoNotificadosDTO)");

        // Realiza validaciones de fechas
        validaNotificacionCorreoCertificado(consultarCantidadComparendosNoNotificadosDTO);

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("organismo", consultarCantidadComparendosNoNotificadosDTO.getCodigoOrganismo());
        StringBuilder sql = new StringBuilder();

        // Calcula la cantidad de comparendos en donde los campos Tipo de Notificacion del Comparendo y Fecha Notificacion NO
        // estan
        // registrados
        sql.append("SELECT COUNT(DISTINCT c) FROM Comparendo c");
        sql.append(" JOIN c.ordenComparendoNacional o");
        sql.append(" JOIN c.personaList p");
        sql.append(" JOIN p.tipoPersonaComparendo tpc");
        sql.append(" JOIN o.organismoTransito ot");
        sql.append(" WHERE c.fechaNotificacion IS NULL");
        sql.append(" AND c.tipoNotificacion IS NULL");
        sql.append(" AND ot.codigoOrganismo=:organismo");
        sql.append(" AND tpc.codigo=:tipoPersonaComparendo");
        sql.append(" AND p.tipoIdentificacion IS NOT NULL");
        sql.append(" AND p.numeroIdentificacion IS NOT NULL");

        filtros.put("tipoPersonaComparendo", EnumTipoPersonaComparendo.INFRACTOR.getValue());

        if (consultarCantidadComparendosNoNotificadosDTO.getNumeroComparendo() != null) {
            sql.append(" AND o.numeroComparendo=:numComparendo");
            filtros.put("numComparendo", consultarCantidadComparendosNoNotificadosDTO.getNumeroComparendo());
        }

        if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion() != null) {
            if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro() != null) {
                sql.append(" AND ");
                sql.append("((c.fechaInfraccion BETWEEN :fechaInfraccionInicial AND :fechaInfraccionFinal)");
                sql.append(" OR ");
                sql.append("(c.fechaRegistro BETWEEN :fechaRegistroIncial AND :fechaRegistroFinal))");
                filtros.put("fechaInfraccionInicial",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion());
                filtros.put("fechaInfraccionFinal",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion());
                filtros.put("fechaRegistroIncial",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro());
                filtros.put("fechaRegistroFinal", consultarCantidadComparendosNoNotificadosDTO.getFechaFinalRegistro());
            } else {
                sql.append(" AND ");
                sql.append("c.fechaInfraccion BETWEEN :fechaInfraccionInicial AND :fechaInfraccionFinal");
                filtros.put("fechaInfraccionInicial",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion());
                filtros.put("fechaInfraccionFinal",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion());

            }
        } else {
            if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro() != null) {
                sql.append(" AND ");
                sql.append("c.fechaRegistro BETWEEN :fechaRegistroIncial AND :fechaRegistroFinal");
                filtros.put("fechaRegistroIncial",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro());
                filtros.put("fechaRegistroFinal", consultarCantidadComparendosNoNotificadosDTO.getFechaFinalRegistro());

            }
        }

        GenericDao<Long> resultado = new GenericDao<>(Long.class, em);
        List<Long> results = resultado.buildAndExecuteQuery(sql, filtros);

        return results.get(0).intValue();
    }

    @Override
    public Integer consultarCantidadRectificaciones(long cicomparendo) {
        logger.debug("ComparendoEJB.consultarCantidadRectificaciones(long)");

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT COUNT(rc) FROM RectificaComparendo rc");
        jpql.append(" WHERE rc.comparendo.id = :cicomparendo");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Cicomparendo
        query.setParameter("cicomparendo", cicomparendo);

        Long cantidad = (Long) query.getSingleResult();
        return cantidad.intValue();
    }

    @Override
    public boolean existePago(long cicomparendo) {
        logger.debug("ComparendoEJB::existePago(Long)");
        boolean existePagos = false;

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT pc FROM PagoComparendo pc");
        jpql.append(" WHERE pc.comparendo.cicomparendo = :cicomparendo");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Cicomparendo
        query.setParameter("cicomparendo", cicomparendo);

        @SuppressWarnings("unchecked")
        List<PagoComparendo> pagoComparendos = query.getResultList();
        if (pagoComparendos != null && !pagoComparendos.isEmpty()) {
            existePagos = true;
        }
        return existePagos;
    }

    @Override
    public long generarNotificacionCorreo(
            ConsultaCantidadComparendosNoNotificadosDTO consultarCantidadComparendosNoNotificadosDTO,
            boolean generarDocumentos, Date fecha) throws CirculemosNegocioException, CirculemosAlertaException {

        logger.debug("ComparendoEJB::generaNotificacionCorreo(ConsultaCantidadComparendosNoNotificadosDTO)");
        Date fechaGeneracion = new Date();

        // Realiza validaciones de fechas
        validaNotificacionCorreoCertificado(consultarCantidadComparendosNoNotificadosDTO);

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("organismo", consultarCantidadComparendosNoNotificadosDTO.getCodigoOrganismo());
        StringBuilder sql = new StringBuilder();

        // Calcula la cantidad de comparendos en donde los campos Tipo de Notificacion del Comparendo y Fecha Notificacion NO
        // estan
        // registrados
        sql.append("SELECT DISTINCT c FROM Comparendo c");
        sql.append(" JOIN c.ordenComparendoNacional o");
        sql.append(" JOIN c.personaList p");
        sql.append(" JOIN p.tipoPersonaComparendo tpc");
        sql.append(" JOIN o.organismoTransito ot");
        sql.append(" WHERE c.fechaNotificacion IS NULL");
        sql.append(" AND c.tipoNotificacion IS NULL");
        sql.append(" AND ot.codigoOrganismo=:organismo");
        sql.append(" AND tpc.codigo=:tipoPersonaComparendo");
        sql.append(" AND p.tipoIdentificacion IS NOT NULL");
        sql.append(" AND p.numeroIdentificacion IS NOT NULL");
        sql.append(" AND c.esPolca=:polca");

        filtros.put("polca", consultarCantidadComparendosNoNotificadosDTO.isEsPolca());
        filtros.put("tipoPersonaComparendo", EnumTipoPersonaComparendo.INFRACTOR.getValue());

        if (consultarCantidadComparendosNoNotificadosDTO.getNumeroComparendo() != null) {
            sql.append(" AND o.numeroComparendo=:numComparendo");
            filtros.put("numComparendo", consultarCantidadComparendosNoNotificadosDTO.getNumeroComparendo());
        }

        if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion() != null) {
            if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro() != null) {
                sql.append(" AND ");
                sql.append("((c.fechaInfraccion BETWEEN :fechaInfraccionInicial AND :fechaInfraccionFinal)");
                sql.append(" OR ");
                sql.append("(c.fechaRegistro BETWEEN :fechaRegistroIncial AND :fechaRegistroFinal))");
                filtros.put("fechaInfraccionInicial",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion());
                filtros.put("fechaInfraccionFinal",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion());
                filtros.put("fechaRegistroIncial",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro());
                filtros.put("fechaRegistroFinal", consultarCantidadComparendosNoNotificadosDTO.getFechaFinalRegistro());
            } else {
                sql.append(" AND ");
                sql.append("c.fechaInfraccion BETWEEN :fechaInfraccionInicial AND :fechaInfraccionFinal");
                filtros.put("fechaInfraccionInicial",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion());
                filtros.put("fechaInfraccionFinal",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion());

            }
        } else {
            if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro() != null) {
                sql.append(" AND ");
                sql.append("c.fechaRegistro BETWEEN :fechaRegistroIncial AND :fechaRegistroFinal");
                filtros.put("fechaRegistroIncial",
                        consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro());
                filtros.put("fechaRegistroFinal", consultarCantidadComparendosNoNotificadosDTO.getFechaFinalRegistro());

            }
        }

        GenericDao<Comparendo> resultado = new GenericDao<>(Comparendo.class, em);
        List<Comparendo> results = resultado.buildAndExecuteQuery(sql, filtros);

        // Genera archivo excel
        List<ContenidoArchivoNotificacionDTO> contenido = obtenerDatosArchivo(results);
        byte[] archivoGenerado = ComparendoHelperExtend.generaArchivo(contenido,
                consultarCantidadComparendosNoNotificadosDTO);

        // Se obtiene el numero de consecutivo para el archivo plano
        String consecutivo = fachadaAdminGeneral.generarConsecutivo(
                consultarCantidadComparendosNoNotificadosDTO.getCodigoOrganismo(), EnumConsecutivo.NOTIF_COMPA_CORREO);

        ValorParametroDTO valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.RAIZ_RUTA_ARCHIVOS, consultarCantidadComparendosNoNotificadosDTO.getCodigoOrganismo(),
                true);

        ArchivoTransportableDTO archivo = new ArchivoTransportableDTO();

        archivo.setRuta(valorParametroDTO.getValorParam() + "/"
                + consultarCantidadComparendosNoNotificadosDTO.getCodigoOrganismo()
                + EnumProceso.NOTIFICACION_COMPARENDO.getRuta() + "/" + new Date());
        archivo.setNombre(consecutivo + "." + EnumTipoArchivo.XLS.name());
        archivo.setContenido(archivoGenerado);
        String idArchivo = null;
        try {
            idArchivo = iRRepositorioArchivo.registrarDocumento(EnumCategoriaDocumento.ARCHIVO_PLANO_NOTIF_CORREO,
                    archivo);
        } catch (CirculemosRuntimeException e) {
            ILog iLog = LoggerC2.getLogger(EnumLogSistema.NOTIF_COMPA_CORREO);
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            NotificacionCompaCorreoLogDTO notificacionCompaCorreoLogDTO = new NotificacionCompaCorreoLogDTO();
            notificacionCompaCorreoLogDTO.setFechaHoraGeneracionArchivo(fechaGeneracion);
            notificacionCompaCorreoLogDTO
                    .setActividadComparendo(EnumActividad.NOTIFICACION_CORREO_CERTIFICACO.getValue());
            notificacionCompaCorreoLogDTO
                    .setOrganismoTransito(consultarCantidadComparendosNoNotificadosDTO.getCodigoOrganismo());
            iLog.escribir(usuarioPersonaDTO.getLogin(), notificacionCompaCorreoLogDTO);
        }
        NotificacionCorreo notificacionCorreo = new NotificacionCorreo();
        notificacionCorreo.setFechaGeneracion(new Date());
        notificacionCorreo.setIdArchivoGenerado(idArchivo);
        notificacionCorreo.setConsecutivo(consecutivo);
        notificacionCorreo.setNotificacionGenerada(generarDocumentos);

        List<NotificacionCorreoComparendo> notificacionCorreoComparendoList = new ArrayList<>();
        TipoNotificacionComparendo tipoNotificacion = em.find(TipoNotificacionComparendo.class,
                new Integer(EnumTipoNotificacionComparendo.EN_PROCESO_CORREO_CERTIFICADO.getValue()));

        for (Comparendo comparendo : results) {
            comparendo.setTipoNotificacion(tipoNotificacion);
            comparendo.setFechaModificacion(new Date());
            NotificacionCorreoComparendo notificacionCorreoComparendo = new NotificacionCorreoComparendo();
            notificacionCorreoComparendo.setComparendo(comparendo);
            notificacionCorreoComparendo.setNotificacionCorreo(notificacionCorreo);
            notificacionCorreoComparendoList.add(notificacionCorreoComparendo);
            // Genera la trazabilidad del comparendo
            generarTrazabilidad(comparendo, EnumActividad.NOTIFICACION_CORREO_CERTIFICACO.getValue(), new Date());
        }
        notificacionCorreo.setNotificacionCorreoComparendos(notificacionCorreoComparendoList);

        for (Comparendo comparendo : results) {
            em.merge(comparendo);
        }
        em.persist(notificacionCorreo);

        if (generarDocumentos) {
            Long idDocumento;
            List<Long> idDocumentos = new ArrayList<Long>();
            GeneraNotificacionCorreoDTO generaNotificacionCorreoDTO = new GeneraNotificacionCorreoDTO();
            for (NotificacionCorreoComparendo ncc : notificacionCorreo.getNotificacionCorreoComparendos()) {
                generaNotificacionCorreoDTO
                        .setCodigoOrganismo(consultarCantidadComparendosNoNotificadosDTO.getCodigoOrganismo());
                generaNotificacionCorreoDTO.setFecha(fecha);
                // generaNotificacionCorreoDTO.setNumeroComparendo(ncc.getComparendo().getOrdenComparendoNacional()
                // .getNumeroComparendo());
                generaNotificacionCorreoDTO.setCicomparendo(ncc.getComparendo().getCicomparendo());
                generaNotificacionCorreoDTO.setFechaGeneracion(ncc.getNotificacionCorreo().getFechaGeneracion());
                generaNotificacionCorreoDTO.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.NOTIF_CORREO_CERTIF);
                idDocumento = iRDocumentosCirculemos.generarDocumento(generaNotificacionCorreoDTO);
                ncc.setIdDocumentoNotificacion(idDocumento);
                consecutivo = fachadaAdminGeneral.generarConsecutivo(
                        consultarCantidadComparendosNoNotificadosDTO.getCodigoOrganismo(),
                        EnumConsecutivo.NOTIF_COMPA_CORREO);
                idDocumentos.add(idDocumento);
                ncc.setConsecutivo(consecutivo);
            }

            byte[] documentosGenerados = iRDocumentosCirculemos.consultarDocumentosPDF(idDocumentos);

            archivo.setContenido(documentosGenerados);
            idArchivo = iRRepositorioArchivo.registrarDocumento(EnumCategoriaDocumento.ARCHIVO_PLANO_NOTIF_CORREO,
                    archivo);

            em.merge(notificacionCorreo);

        }

        return notificacionCorreo.getId();
    }

    List<ContenidoArchivoNotificacionDTO> obtenerDatosArchivo(List<Comparendo> comparendos)
            throws CirculemosNegocioException {

        logger.debug("ComparendoEJB::obtenerDatosArchivos(List<Comparendo>)");

        List<ContenidoArchivoNotificacionDTO> registros = new ArrayList<>();
        LiquidarTarifaInfraccionDTO liquidarTarifaInfraccionDTO = new LiquidarTarifaInfraccionDTO();
        List<DireccionDTO> direcciones;
        ConsultaDireccionDTO consultaDireccionDTO = new ConsultaDireccionDTO();
        ConfiguracionInfraccionDTO configuracionInfraccionDTO;
        TarifaLiquidacionDTO tarifaLiquidacionDTO;

        for (Comparendo comparendo : comparendos) {
            liquidarTarifaInfraccionDTO.setCodigoInfraccion(comparendo.getInfraccion().getCodigo());
            liquidarTarifaInfraccionDTO.setFechaLiquidacion(comparendo.getFechaInfraccion());
            ContenidoArchivoNotificacionDTO contenidoArchivoNotificacionDTO = new ContenidoArchivoNotificacionDTO();
            if (comparendo.getGradoAlcoholemia() != null) {
                liquidarTarifaInfraccionDTO.setGradoAlcoholemia(comparendo.getGradoAlcoholemia().getId());
            }
            if (comparendo.getComparendoVehiculo() != null) {
                if (comparendo.getComparendoVehiculo().getTipoServicio() != null) {
                    liquidarTarifaInfraccionDTO
                            .setIdClaseServicio(comparendo.getComparendoVehiculo().getTipoServicio().getId());
                }

                contenidoArchivoNotificacionDTO.setPlaca(comparendo.getComparendoVehiculo().getPlacaVehiculo());

            }
            liquidarTarifaInfraccionDTO.setNiegaPruebaAlcoholemia(comparendo.getNiegaPruebaAlcoholemia());
            liquidarTarifaInfraccionDTO.setNumeroReincidencias(comparendo.getNumeroReincidencia());
            tarifaLiquidacionDTO = iLTarifaInfraccion.liquidarTarifaInfraccion(liquidarTarifaInfraccionDTO);

            contenidoArchivoNotificacionDTO
                    .setNumeroComparendo(comparendo.getOrdenComparendoNacional().getNumeroComparendo());
            contenidoArchivoNotificacionDTO.setFechaImposicion(comparendo.getFechaInfraccion());

            // Infractor
            for (ComparendoPersona comparendoPersona : comparendo.getPersonaList()) {
                if (comparendoPersona.getTipoPersonaComparendo().getCodigo() == EnumTipoPersonaComparendo.INFRACTOR
                        .getValue()) {
                    contenidoArchivoNotificacionDTO
                            .setTipoDocumento(comparendoPersona.getTipoIdentificacion().getSigla());
                    contenidoArchivoNotificacionDTO.setNumeroDocumento(comparendoPersona.getNumeroIdentificacion());
                    contenidoArchivoNotificacionDTO.setPrimerNombre(comparendoPersona.getNombre1());
                    contenidoArchivoNotificacionDTO.setSegundoNombre(comparendoPersona.getNombre2());
                    contenidoArchivoNotificacionDTO.setPrimerApellido(comparendoPersona.getApellido1());
                    contenidoArchivoNotificacionDTO.setSegundoApellido(comparendoPersona.getApellido2());
                    contenidoArchivoNotificacionDTO.setNumeroCelular(comparendoPersona.getTelefonoMovil());
                    if (comparendoPersona.getDireccion() != null) {
                        consultaDireccionDTO.setIdDireccion(comparendoPersona.getDireccion().getId());
                        direcciones = fachadaAdminGeneral.consultarDireccion(consultaDireccionDTO);
                        if (direcciones.size() > 0) {
                            contenidoArchivoNotificacionDTO.setDireccion(direcciones.get(0).toString());
                        } else {
                            contenidoArchivoNotificacionDTO.setDireccion(null);
                        }
                    }
                }
            }

            contenidoArchivoNotificacionDTO.setCodigoInfraccion(comparendo.getInfraccion().getCodigo());
            configuracionInfraccionDTO = infraccionEJB.consultarInfraccion(comparendo.getInfraccion().getCodigo(),
                    comparendo.getFechaInfraccion());
            contenidoArchivoNotificacionDTO.setDescripcionInfraccion(configuracionInfraccionDTO.getDescripcion());
            if (tarifaLiquidacionDTO != null) {
                contenidoArchivoNotificacionDTO.setValorComparendo(tarifaLiquidacionDTO.getValorLiquidado());
            }

            registros.add(contenidoArchivoNotificacionDTO);
        }
        return registros;

    }

    /*
     * CARGUE ARCHIVO ACUSE RECIBO NOTIFICACION COMPARENDO
     */

    @Override
    public ResultadoCargueArchivoNotificacionDTO cargarArchivoNotificacion(int codigoOrganismo,
            ArchivoTransportableDTO archivo) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB::cargarArchivoNotificacion(int, ArchivoTransportableDTO)");
        logger.debugv("[ codigoOrganismo: {0}, archivo.nombre: {1} ]", codigoOrganismo, archivo.getNombre());

        // Generar arreglo de filas de archivo
        ArchivoAcuseNotificacion archivoAcuseNotificacion = procesarArchivoAcuseRecibo(codigoOrganismo, archivo);
        int comparendosInvalidos = 0;

        // Notificar, activar cartera, generar trazabilidad de comparendo
        for (ComparendoArchivoAcuse comparendoArchivo : archivoAcuseNotificacion.getComparendoArchivoAcuses()) {
            if (!comparendoArchivo.getProcesado())
                comparendosInvalidos++;
            else {
                NotificarComparendoDTO notificacion = new NotificarComparendoDTO();
                notificacion.setFechaNotificacion(comparendoArchivo.getComparendo().getFechaNotificacion());
                notificacion.setIdActividadTrazabilidad(EnumActividad.NOTIFICACION_CORREO_CERTIFICADO.getValue());
                notificacion.setIdTipoNotificacion(EnumTipoNotificacionComparendo.NOTIFICACION_CORREO.getValue());
                notificacion.setActivaCartera(true);

                this.notificarComparendoTerceros(comparendoArchivo.getComparendo());

                notificarComparendo(comparendoArchivo.getComparendo(), notificacion);

                GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
                comparendoDao.saveOrUpdate(comparendoArchivo.getComparendo());
            }
        }

        GenericDao<ArchivoAcuseNotificacion> archivoAcuseDao = new GenericDao<>(ArchivoAcuseNotificacion.class, em);
        archivoAcuseDao.saveOrUpdate(archivoAcuseNotificacion);

        ResultadoCargueArchivoNotificacionDTO resultado = new ResultadoCargueArchivoNotificacionDTO();
        resultado.setArchivoRespuesta(archivo);
        resultado.setCantidadRegistros(archivoAcuseNotificacion.getComparendoArchivoAcuses().size());
        resultado.setComparendosInvalidos(comparendosInvalidos);

        return resultado;
    }

    /**
     * Recibe archivo de acuse de recibo, realiza validaciones de forma y contenido de datos por cada fila, y actualiza un archivo por referencia con
     * el mismo contenido y una nueva columna donde registra las observaciones por cada comparendo si estas existen.
     * 
     * @param codigoOrganismo
     *            Organismo de transito de los comparendos
     * @param archivo
     *            El archivo Excel con los registros de los comparendos
     * @return El resultado de las validaciones por cada comparendo los cuales deben ser registrados por el cliente que invoca
     * @throws CirculemosNegocioException
     *             <ul>
     *             <li>Cuando el formato del archivo es diferente de los indicados
     *             <li>Cuando el archivo no cumple la estructura (el encabezado es incorrecto)
     *             </ul>
     * @author rodrigo.cruz
     */
    public ArchivoAcuseNotificacion procesarArchivoAcuseRecibo(int codigoOrganismo, ArchivoTransportableDTO archivo)
            throws CirculemosNegocioException {
        final String OBSERVACIONES_SEPARADOR = " | ", OBSERVACIONES = "OBSERVACIONES";
        // Crear archivo de entrada
        EnumTipoArchivo tipoArchivo = null;
        if (StringUtils.equalsIgnoreCase(archivo.getExtension(), EnumTipoArchivo.XLS.toString()))
            tipoArchivo = EnumTipoArchivo.XLS;
        else if (StringUtils.equalsIgnoreCase(archivo.getExtension(), EnumTipoArchivo.XLSX.toString()))
            tipoArchivo = EnumTipoArchivo.XLSX;
        else
            throw new CirculemosNegocioException(ErrorComparendo.CargarAcuseReciboCorreo.COM_054003);

        List<FilaArchivoDTO> filasArchivo = ComparendoHelperExtend.leerArchivo(archivo.getContenido(), tipoArchivo);

        // Crea entidad que almacena los resultados del proceso
        Date ahora = UtilFecha.buildCalendar().getTime();
        ArchivoAcuseNotificacion archivoAcuseNotificacion = new ArchivoAcuseNotificacion();
        archivoAcuseNotificacion.setFechaCargue(ahora);

        // Validar registros (filas)
        for (FilaArchivoDTO filaArchivo : filasArchivo) {
            FilaArchivoExcelDTO filaArchivoExcel = (FilaArchivoExcelDTO) filaArchivo;
            // Solo se examina la primera hoja
            if (filaArchivoExcel.getHoja() == 0 && filaArchivoExcel.getCeldas() != null
                    && !filaArchivoExcel.getCeldas().isEmpty()) {
                int indice = 0;
                if (filaArchivoExcel.isEncabezado()) {
                    for (Object celda : filaArchivoExcel.getCeldas()) {
                        EnumCampoArchivoAcuse columna = Utilidades.buscarElemEnum(EnumCampoArchivoAcuse.class,
                                indice++);
                        // Validar estructura
                        if (columna == null)
                            break;
                        if (celda == null)
                            throw new CirculemosNegocioException(ErrorComparendo.CargarAcuseReciboCorreo.COM_054001,
                                    Integer.toString(indice));
                        Matcher m = Pattern.compile(columna.getRegexp()).matcher(celda.toString().trim());
                        boolean corresponde = false;
                        while (m.find())
                            corresponde = true;
                        if (!corresponde)
                            throw new CirculemosNegocioException(ErrorComparendo.CargarAcuseReciboCorreo.COM_054002,
                                    celda.toString());
                    }
                    // Columna observacion
                    filaArchivoExcel.getCeldas().add(OBSERVACIONES);
                } else {
                    ComparendoArchivoAcuse comparendoArchivoAcuse = new ComparendoArchivoAcuse();
                    comparendoArchivoAcuse.setArchivoAcuseNotificacion(archivoAcuseNotificacion);

                    Date fechaNotif = null, fechaImpos = null;
                    String observaNotif = null, numComparendo = null;
                    StringBuilder observaciones = new StringBuilder();

                    for (Object celda : filaArchivoExcel.getCeldas()) {
                        EnumCampoArchivoAcuse columna = Utilidades.buscarElemEnum(EnumCampoArchivoAcuse.class,
                                indice++);
                        // Validar tipo y longitud
                        if (columna == null)
                            break;
                        if (columna == EnumCampoArchivoAcuse.FECHA_NOTIFICACION && celda != null)
                            fechaNotif = validarCeldaTipoFecha(comparendoArchivoAcuse, observaciones, celda, columna);
                        if (columna == EnumCampoArchivoAcuse.FECHA_DE_IMPOSICIO && celda != null)
                            fechaImpos = validarCeldaTipoFecha(comparendoArchivoAcuse, observaciones, celda, columna);
                        if (columna == EnumCampoArchivoAcuse.OBSERVACIONES_NOTI && celda != null)
                            observaNotif = validarCeldaTipoTexto(comparendoArchivoAcuse, observaciones, celda, columna);
                        if (columna == EnumCampoArchivoAcuse.NUMERO_DE_COMPAREN && celda != null)
                            numComparendo = validarCeldaTipoTexto(comparendoArchivoAcuse, observaciones, celda,
                                    columna);
                    }

                    // Obligatorio
                    if (!(fechaNotif == null ^ observaNotif == null)) {
                        crearErrorCargueAcuse(comparendoArchivoAcuse, EnumCampoArchivoAcuse.OBSERVACIONES_NOTI,
                                EnumMsjErrorArchivoAcuse.FALTA_VALOR);
                        observaciones.append(ErrorComparendo.CargarAcuseReciboCorreo.COM_054004.getDescError());
                    }
                    if (numComparendo == null) {
                        crearErrorCargueAcuse(comparendoArchivoAcuse, EnumCampoArchivoAcuse.NUMERO_DE_COMPAREN,
                                EnumMsjErrorArchivoAcuse.FALTA_VALOR);
                        observaciones.append(ErrorComparendo.CargarAcuseReciboCorreo.COM_054005.getDescError());
                    }

                    // Reglas
                    if (fechaNotif != null && fechaNotif.compareTo(ahora) > 0) {
                        crearErrorCargueAcuse(comparendoArchivoAcuse, EnumCampoArchivoAcuse.FECHA_NOTIFICACION,
                                EnumMsjErrorArchivoAcuse.FECHA_NOTIF_MAYOR_FECHA_ACTUAL);
                        observaciones.append(ErrorComparendo.CargarAcuseReciboCorreo.COM_054006.getDescError());
                    }
                    if (fechaNotif != null && fechaImpos != null && fechaNotif.compareTo(fechaImpos) < 0) {
                        crearErrorCargueAcuse(comparendoArchivoAcuse, EnumCampoArchivoAcuse.FECHA_NOTIFICACION,
                                EnumMsjErrorArchivoAcuse.FECHA_NOTIF_ANTERIOR_FECHA_IMPOS);
                        observaciones.append(ErrorComparendo.CargarAcuseReciboCorreo.COM_054007.getDescError());
                    }
                    if (fechaImpos != null && fechaImpos.compareTo(ahora) >= 0) {
                        crearErrorCargueAcuse(comparendoArchivoAcuse, EnumCampoArchivoAcuse.FECHA_DE_IMPOSICIO,
                                EnumMsjErrorArchivoAcuse.FECHA_IMPOS_MAYOR_FECHA_ACTUAL);
                        observaciones.append(ErrorComparendo.CargarAcuseReciboCorreo.COM_054008.getDescError());
                    }

                    if (!StringUtils.isBlank(observaciones.toString())) {
                        observaciones.deleteCharAt(observaciones.length() - 1).deleteCharAt(observaciones.length() - 1);
                        observaciones.insert(0, OBSERVACIONES_SEPARADOR
                                + ErrorComparendo.CargarAcuseReciboCorreo.COM_054009.getDescError());
                    }

                    // Consultar numero de comparendo
                    StringBuilder consulta = new StringBuilder();
                    consulta.append("SELECT c FROM Comparendo c")
                            .append(" LEFT JOIN FETCH c.trazabilidadComparendoList tcl")
                            .append(" LEFT JOIN c.comparendoCarteraList ccl")
                            .append(" WHERE c.ordenComparendoNacional.numeroComparendo = :numeroComparendo")
                            .append(" AND c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codigoOrganismo");
                    GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
                    List<Comparendo> comparendoList = comparendoDao.buildAndExecuteQuery(consulta.toString(), GenericDao
                            .buildMap("numeroComparendo", numComparendo).addEntry("codigoOrganismo", codigoOrganismo));

                    if (comparendoList.isEmpty()) {
                        crearErrorCargueAcuse(comparendoArchivoAcuse, EnumCampoArchivoAcuse.NUMERO_DE_COMPAREN,
                                EnumMsjErrorArchivoAcuse.COMPARENDO_NO_REGISTRADO);
                        observaciones.append(OBSERVACIONES_SEPARADOR)
                                .append(EnumMsjErrorArchivoAcuse.COMPARENDO_NO_REGISTRADO.getDescripcion());
                    } else {
                        Comparendo comparendo = comparendoList.get(0);
                        // Validar comparendo notificado
                        if (comparendo.getFechaNotificacion() != null) {
                            crearErrorCargueAcuse(comparendoArchivoAcuse, EnumCampoArchivoAcuse.FECHA_NOTIFICACION,
                                    EnumMsjErrorArchivoAcuse.COMPARENDO_NOTIFICADO);
                            observaciones.append(OBSERVACIONES_SEPARADOR)
                                    .append(EnumMsjErrorArchivoAcuse.COMPARENDO_NOTIFICADO.getDescripcion());
                        } else
                            comparendoArchivoAcuse.setComparendo(comparendo);
                    }

                    // Agregar comparendo procesado
                    if (!comparendoArchivoAcuse.getErrorCargueAcuses().isEmpty()) {
                        comparendoArchivoAcuse.setProcesado(Boolean.FALSE);
                    } else {
                        comparendoArchivoAcuse.getComparendo().setFechaNotificacion(fechaNotif);
                        comparendoArchivoAcuse.setProcesado(Boolean.TRUE);
                    }

                    comparendoArchivoAcuse.setNumeroComparendo(numComparendo);
                    archivoAcuseNotificacion.getComparendoArchivoAcuses().add(comparendoArchivoAcuse);

                    // Observaciones en archivo
                    filaArchivoExcel.getCeldas().add(observaciones.toString());
                }
            }
        }

        // Crear archivo de salida
        byte[] contenido = ComparendoHelperExtend.escribirArchivo(filasArchivo, tipoArchivo);
        archivo.setContenido(contenido);

        return archivoAcuseNotificacion;
    }

    /**
     * Crea por referencia una entidad que indica error en el procesamiento de un registro del archivo de acuse de notificacion
     * 
     * @param comparendoArchivoAcuse
     *            Entidad por referencia que almacena la lista de errores al procesar un comparendo
     * @param campoArchivoAcuse
     *            Campo (celda) de la fila que no cumplio la validacion
     * @param msjErrorArchivoAcuse
     *            Mensaje asociado al campo que indica el tipo de error
     */
    private void crearErrorCargueAcuse(ComparendoArchivoAcuse comparendoArchivoAcuse,
            EnumCampoArchivoAcuse campoArchivoAcuse, EnumMsjErrorArchivoAcuse msjErrorArchivoAcuse) {
        ErrorCargueAcuse errorCargueAcuse = new ErrorCargueAcuse();
        errorCargueAcuse.setCampoArchivoAcuse(em.getReference(CampoArchivoAcuse.class, campoArchivoAcuse.getId()));
        errorCargueAcuse.setComparendoArchivoAcuse(comparendoArchivoAcuse);
        errorCargueAcuse.setMensajeErrorCargueAcuse(
                em.getReference(MensajeErrorCargueAcuse.class, msjErrorArchivoAcuse.getId()));
        comparendoArchivoAcuse.getErrorCargueAcuses().add(errorCargueAcuse);
    }

    /**
     * Valida el tipo de dato y la longitud de una celda de un archivo. Actualiza un texto con el mensaje del error.
     * 
     * @param comparendoArchivoAcuse
     *            Entidad por referencia que almacena la lista de errores al procesar un comparendo
     * @param observaciones
     *            Cadena con la lista de observaciones de la validacion que no cumple
     * @param celda
     *            La celda a validar
     * @param columna
     *            La columna (campo) correspondiente a la celda
     * @return La cadena validada, null si no cumple la validacion
     */
    private String validarCeldaTipoTexto(ComparendoArchivoAcuse comparendoArchivoAcuse, StringBuilder observaciones,
            Object celda, EnumCampoArchivoAcuse columna) {
        String textoCelda = null;

        if (celda instanceof String) {
            textoCelda = (String) celda;
            if (textoCelda.length() > columna.getLongitud()) {
                crearErrorCargueAcuse(comparendoArchivoAcuse, columna, EnumMsjErrorArchivoAcuse.LONGITUD_INVALIDA);
                observaciones.append("Longitud incorrecta ").append(columna.getNombre()).append(", ");
            }
        } else {
            crearErrorCargueAcuse(comparendoArchivoAcuse, columna, EnumMsjErrorArchivoAcuse.FORMATO_INVALIDO);
            observaciones.append("Tipo dato incorrecto ").append(columna.getNombre()).append(", ");
        }

        return textoCelda;
    }

    /**
     * Valida el tipo de dato y el formato de fecha de una celda en un archivo. Actualiza un texto con el mensaje del error.
     * 
     * @param comparendoArchivoAcuse
     *            Entidad por referencia que almacena la lista de errores al procesar un comparendo
     * @param observaciones
     *            Cadena con la lista de observaciones de la validacion que no cumple
     * @param celda
     *            La celda a validar
     * @param columna
     *            La columna (campo) correspondiente a la celda
     * @return La fecha validada, null si no cumple la validacion
     */
    private Date validarCeldaTipoFecha(ComparendoArchivoAcuse comparendoArchivoAcuse, StringBuilder observaciones,
            Object celda, EnumCampoArchivoAcuse columna) {
        Date fechaCelda = null;

        if (celda instanceof Date) {
            fechaCelda = (Date) celda;
        } else if (celda instanceof String) {
            String fechaCeldaS = (String) celda;
            if ((fechaCelda = UtilFecha.validarFormatoFecha(fechaCeldaS, columna.getFormatoFecha())) != null)
                fechaCelda = UtilFecha.stringToDate(columna.getFormatoFecha(), fechaCeldaS);
            else {
                crearErrorCargueAcuse(comparendoArchivoAcuse, columna, EnumMsjErrorArchivoAcuse.FORMATO_INVALIDO);
                observaciones.append("Formato incorrecto ").append(columna.getNombre()).append(", ");
            }
        } else {
            crearErrorCargueAcuse(comparendoArchivoAcuse, columna, EnumMsjErrorArchivoAcuse.FORMATO_INVALIDO);
            observaciones.append("Tipo dato incorrecto ").append(columna.getNombre()).append(", ");
        }

        return fechaCelda;
    }

    /**
     * Actualiza los datos de notificacion y genera la trazabilidad del compareno
     * 
     * @param comparendo
     *            El comparendo a notificar
     * @param notificacion
     *            Datos de notificacion obligatorios (estado de comparendo, fecha de notificacion, tipo de notificacion)
     * @throws CirculemosNegocioException
     *             Cuando el estado de la cartera del comparendo ya estaba activa
     */
    private void notificarComparendo(Comparendo comparendo, NotificarComparendoDTO notificacion)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB::notificarComparendo(Comparendo, NotificarComparendoDTO)");

        comparendo
                .setEstadoComparendo(em.getReference(EstadoComparendo.class, EnumEstadoComparendo.VIGENTE.getCodigo()));
        comparendo.setFechaNotificacion(notificacion.getFechaNotificacion());
        comparendo.setTipoNotificacion(
                em.getReference(TipoNotificacionComparendo.class, notificacion.getIdTipoNotificacion()));

        if (notificacion.isActivaCartera()) {
            iCarteraComparendo.activarCarteraComparendo(comparendo.getComparendoCarteraList().get(0).getIdCartera(),
                    comparendo.getFechaNotificacion());
        }

        generarTrazabilidad(comparendo, notificacion.getIdActividadTrazabilidad(), notificacion.getFechaNotificacion());
    }

    /*
     * CARGUE ARCHIVO ACUSE RECIBO NOTIFICACION COMPARENDO (FIN)
     */

    /**
     * Actualiza la trazabilidad de un comparendo, desactivando los anteriores estados y dejando activo el nuevo estado.
     * 
     * @param comparendo
     *            El comparendo con la lista de {@link TrazabilidadComparendo}
     * @param idActividad
     *            ID de actividad (ver {@link EnumActividad})
     * @param fechaEjecucion
     *            La fecha de ejecucion de la actividad
     */
    private void generarTrazabilidad(Comparendo comparendo, int idActividad, Date fechaEjecucion) {
        logger.debug("ComparendoEJB::generarTrazabilidad(Comparendo, int, Date)");

        if (comparendo.getTrazabilidadComparendoList() == null || comparendo.getTrazabilidadComparendoList().isEmpty())
            comparendo.setTrazabilidadComparendoList(new ArrayList<TrazabilidadComparendo>());

        TrazabilidadComparendo trazabilidadComparendo = new TrazabilidadComparendo();
        trazabilidadComparendo.setActividad(em.getReference(Actividad.class, idActividad));
        trazabilidadComparendo.setComparendo(comparendo);
        trazabilidadComparendo.setEstadoComparendo(comparendo.getEstadoComparendo());
        trazabilidadComparendo.setFechaEjecucion(fechaEjecucion);
        trazabilidadComparendo.setFechaRegistro(UtilFecha.buildCalendar().getTime());
        UsuarioPersonaDTO usuarioPersona = iRSeguridadCirculemos.obtenerUsuarioDto();
        if (usuarioPersona != null && usuarioPersona.getUsuario() != null
                && usuarioPersona.getUsuario().getId() != null) {
            trazabilidadComparendo.setUsuario(UsuarioPersonaHelper.toLevel1Entity(usuarioPersona, null));
        }
        comparendo.getTrazabilidadComparendoList().add(trazabilidadComparendo);
        comparendo.setFechaModificacion(UtilFecha.buildCalendar().getTime());
    }

    private void validaNotificacionCorreoCertificado(
            ConsultaCantidadComparendosNoNotificadosDTO consultarCantidadComparendosNoNotificadosDTO)
            throws CirculemosNegocioException {

        logger.debug("ComparendoEJB::validaNotificacionCorreoCertificado(ConsultaCantidadComparendosNoNotificadosDTO)");

        // valida que venga las dos fechas o ninguna
        if ((consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion() != null
                && consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion() == null)
                || (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion() == null
                        && consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion() != null)) {
            throw new CirculemosNegocioException(ErrorComparendo.NotificarComparendoPorCorreoCertificado.COM_052002);
        }

        if ((consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro() != null
                && consultarCantidadComparendosNoNotificadosDTO.getFechaFinalRegistro() == null)
                || (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro() == null
                        && consultarCantidadComparendosNoNotificadosDTO.getFechaFinalRegistro() != null)) {
            throw new CirculemosNegocioException(ErrorComparendo.NotificarComparendoPorCorreoCertificado.COM_052002);
        }

        // Consulta el parametro
        ValorParametroDTO valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.MIN_DIAS_CONSULTA_GENERAL,
                consultarCantidadComparendosNoNotificadosDTO.getCodigoOrganismo(), true);

        // valida que el rango de dias entre las dos fechas NO supere el parametro "Cantidad media de dias a realizar la consulta"
        if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion() != null
                && consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion() != null) {
            long fechaInicialImposicion = consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion()
                    .getTime();
            long fechaFinalImposicion = consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion()
                    .getTime();
            long diferencia = fechaFinalImposicion - fechaInicialImposicion;
            long dias = diferencia / (1000 * 60 * 60 * 24);

            if (dias > Long.parseLong(valorParametroDTO.getValorParam())) {
                throw new CirculemosNegocioException(
                        ErrorComparendo.NotificarComparendoPorCorreoCertificado.COM_052001);
            }
        }

        if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro() != null) {
            long fechaFinalRegistro = consultarCantidadComparendosNoNotificadosDTO.getFechaFinalRegistro().getTime();
            long fechaIncialRegistro = consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro().getTime();
            long diferenciaRegistro = fechaFinalRegistro - fechaIncialRegistro;
            long diasRegistro = diferenciaRegistro / (1000 * 60 * 60 * 24);

            if (diasRegistro > Long.parseLong(valorParametroDTO.getValorParam())) {
                throw new CirculemosNegocioException(
                        ErrorComparendo.NotificarComparendoPorCorreoCertificado.COM_052001);
            }
        }

        // valida que la fecha Inicial de imposicion no sea mayor a la fecha actual
        // valida que la fecha final de imposicion no sea menor a la fecha inicial de imposicion
        if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion() != null
                && consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion() != null) {
            if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion().after(new Date())) {
                throw new CirculemosNegocioException(
                        ErrorComparendo.NotificarComparendoPorCorreoCertificado.COM_052002);
            } else {
                if (consultarCantidadComparendosNoNotificadosDTO.getFechaFinalImposicion()
                        .before(consultarCantidadComparendosNoNotificadosDTO.getFechaInicialImposicion())) {
                    throw new CirculemosNegocioException(
                            ErrorComparendo.NotificarComparendoPorCorreoCertificado.COM_052002);
                }
            }
        }

        // valida que la fecha Inicial de registro no sea mayor a la fecha actual
        // valida que la fecha final de registro no sea menor a la fecha inicial de registro
        if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro() != null
                && consultarCantidadComparendosNoNotificadosDTO.getFechaFinalRegistro() != null) {
            if (consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro().after(new Date())) {
                throw new CirculemosNegocioException(
                        ErrorComparendo.NotificarComparendoPorCorreoCertificado.COM_052002);
            } else {
                if (consultarCantidadComparendosNoNotificadosDTO.getFechaFinalRegistro()
                        .before(consultarCantidadComparendosNoNotificadosDTO.getFechaInicialRegistro())) {
                    throw new CirculemosNegocioException(
                            ErrorComparendo.NotificarComparendoPorCorreoCertificado.COM_052002);
                }
            }
        }

    }

    @Override
    public RespuestaResolucionComparendoDTO generarResolucionesSancion(Date fechaGeneracion,
            int codigoOrganismoTransito) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB::generarResolucionesSancion(Date, int)");
        RespuestaResolucionComparendoDTO respuesta = new RespuestaResolucionComparendoDTO();
        if (fechaGeneracion == null) {
            throw new CirculemosNegocioException(ErrorComparendo.GenerarResolucionesSancion.COM_009001);
        }

        List<ConfiguracionDescuentoDTO> configuracionesDescuento = obtenerConfiguraciones(fechaGeneracion,
                codigoOrganismoTransito);

        // Realiza la busqueda de los comparendo a los cuales se les debe generar resolucion de Sancion
        GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT c ");
        jpql.append(" FROM Comparendo c");
        jpql.append(" JOIN c.personaList plInf");
        jpql.append(" JOIN plInf.tipoPersonaComparendo tpcInf");
        jpql.append(" JOIN FETCH c.infraccion inf");
        jpql.append(" JOIN FETCH inf.configuracionInfraccionList ci");
        jpql.append(" LEFT JOIN c.comparendoCarteraList cc");
        jpql.append(" LEFT JOIN c.cursoPedagogicoList cp ");
        jpql.append(" WHERE");

        jpql.append(" c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", codigoOrganismoTransito);

        /*
         * Valida que el 'Numero del comparendo' tenga datos registrados en los datos del Infractor: 'Tipo de Documento del infractor' y 'Numero
         * Documento del infractor'
         */
        jpql.append(" AND tpcInf.codigo = :tipoPersonaComparendoInf");
        filtros.put("tipoPersonaComparendoInf", EnumTipoPersonaComparendo.INFRACTOR.getValue());

        /*
         * Valida que el 'Estado del Comparendo' debe ser IGUAL a VIGENTE
         */
        jpql.append(" AND c.estadoComparendo.id = :estadoComparendoVigente");
        filtros.put("estadoComparendoVigente", EnumEstadoComparendo.VIGENTE.getValue());

        /*
         * Consulta UNICAMENTE los comparendos que tengan las infracciones configuradas como SI en el campo 'Genera resolucion automatica?'
         */
        jpql.append(" AND ci.generaResolucionAutomatica = :generaResolucionAutomatica");
        filtros.put("generaResolucionAutomatica", true);

        List<Comparendo> lstComparendo = comparendoDao.buildAndExecuteQuery(jpql, filtros);

        List<ComparendoDTO> lstComparendoDTO = new ArrayList<ComparendoDTO>();

        comparendoLoop: for (Comparendo comparendo : lstComparendo) {
            // Obtiene configuracion adecuada
            ConfiguracionDescuentoDTO configuracionDescuentoDTO = ConfiguracionDescuentoHelperExtend
                    .obtenerConfiguracion(configuracionesDescuento, comparendo.getMedioImposicion().getId(),
                            comparendo.getTipoNotificacion().getId());
            if (configuracionDescuentoDTO != null) {
                Date fechaOrigen = comparendo.getFechaInfraccion();
                if (configuracionDescuentoDTO.getTipoFechaOrigen().getCodigo()
                        .equals(EnumTipoFechaOrigen.FECHA_NOTIFICACION.getValue())) {
                    fechaOrigen = comparendo.getFechaNotificacion();
                }
                // Valida que la 'Fecha origen' del comparendo sea MENOR o IGUAL a la Fecha de consulta
                if (fechaOrigen.compareTo(configuracionDescuentoDTO.getFechaConsulta()) <= 0) {
                    /*
                     * Valida que el 'Valor del saldo capital' del 'Numero del comparendo' sea IGUAL o SUPERIOR al valor definido en el parametro
                     * general 'Monto minimo en cartera considerado como cartera vigente', excluyendo de esta validacion las siguientes infracciones:
                     */
                    /*
                     * - Aquellas infracciones que en su Configuracion mediante el caso de uso 'Administrar infracciones' son consideradas con 'Tipos
                     * de sanciones' IGUAL a Amonestacion y que NO tengan curso pedagogico asociado.
                     */
                    /*
                     * - Aquellas infracciones que en su Configuracion mediante el caso de uso 'Administrar infracciones' indiquen que no generan
                     * cartera al parametrizar 'Genera cartera?' IGUAL a NO y que NO tengan curso pedagogico asociado.
                     */
                    ConfiguracionInfraccionDTO configInfraccion = infraccionEJB.consultarInfraccion(
                            comparendo.getInfraccion().getCodigo(), comparendo.getFechaInfraccion());
                    boolean amonestacion = false;
                    for (TipoSancionDTO tipoSancion : configInfraccion.getTipoSancionList()) {
                        if (EnumTipoSancion.AMONESTACION.getValue().equals(tipoSancion.getId())) {
                            amonestacion = true;
                        }
                    }
                    if ((!amonestacion && configInfraccion.getGeneraCartera())
                            || (comparendo.getCursoPedagogicoList() != null
                                    && !comparendo.getCursoPedagogicoList().isEmpty())) {
                        for (ComparendoCartera comparendoCartera : comparendo.getComparendoCarteraList()) {
                            CarteraComparendoDTO cartera = iCarteraComparendo
                                    .consultarCartera(comparendoCartera.getIdCartera());

                            // Obtener el parametro de sistema que consulta el monto minimo en cartera considerado como cartera vigente
                            ValorParametroDTO valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(
                                    EnumParametro.MONTO_MIN_CARTERA_COMPA_VIGENTE, codigoOrganismoTransito, true);
                            int montoMinCarteraComparendoVigente = valorParametroDTO.getValorParamInt();
                            if (cartera.getValorSaldoCapital().intValue() < montoMinCarteraComparendoVigente) {
                                // Si no cumple con la condicion pasa al siguiente comparendo
                                continue comparendoLoop;
                            }
                        }
                    }

                    /*
                     * Valida que el 'Numero del comparendo' NO tenga una proceso de impugnacion abierto
                     */
                    ProcesoComparendoDTO procesoComparendo = new ProcesoComparendoDTO();
                    procesoComparendo.setCodigoOrganismo(codigoOrganismoTransito);
                    procesoComparendo.setEstadoProceso(EnumEstadoProceso.COLOMBIA_IMPUGNACION_ABIERTO);
                    procesoComparendo.setTipoProceso(EnumTipoProceso.IMPUGNACION_COMPARENDO);
                    procesoComparendo
                            .setNumeroComparendo(comparendo.getOrdenComparendoNacional().getNumeroComparendo());

                    if (iRProcesoComparendo.existeProceso(procesoComparendo)) {
                        // Si existe un proceso de impugnacion abierto pasa al siguiente comparendo
                        continue comparendoLoop;
                    }

                    // Valida que el 'Numero del comparendo' NO tenga una resolucion de sancion generada.
                    String numeroResolucion = null;
                    ComparendoResolucionDTO comparendoResolucionDTO = administracionComparendosEjb
                            .consultarResolucionSancionActual(comparendo.getCicomparendo());
                    if (comparendoResolucionDTO != null && comparendoResolucionDTO.getResolucionExitosa()) {
                        if (comparendoResolucionDTO.getEstadoResolucion().getId()
                                .equals(EnumEstadoResolucion.VIGENTE.getValue())) {
                            // El comparendo SI tiene una resolucion de sancion generada con estado VIGENTE
                            // Continua con el siguiente comparendo de la lista de comparendos que cumplieron las validaciones indicadas
                            continue comparendoLoop;
                        } else if (comparendoResolucionDTO.getEstadoResolucion().getId()
                                .equals(EnumEstadoResolucion.ANULADA.getValue())) {
                            // Si tiene una resolucion de sancion generada en estado ANULADO se mantiene el 'Numero del consecutivo' de resolucion
                            // de sancion generado anteriormente para la resolucion de sancion que tiene el estado anulado.
                            numeroResolucion = comparendoResolucionDTO.getNumeroResolucion();
                        }
                    }

                    /*
                     * A cada 'Numero del comparendo' listado en el paso 3 del Flujo basico se les asocian los siguientes datos de la resolucion de
                     * sancion generada:
                     * 
                     * - Tipo de Resolucion=Resolucion de Sancion - Numero de resolucion de sancion - Anio de resolucion de sancion - Fecha de
                     * generacion de la resolucion de sancion - Estado de la resolucion = Vigente - Documento de resolucion de sancion generado
                     */
                    ResolucionComparendoDTO resolucionComparendo = new ResolucionComparendoDTO();
                    resolucionComparendo.setCodigoOrganismo(codigoOrganismoTransito);
                    resolucionComparendo.setEnumConsecutivo(EnumConsecutivo.RESOL_SANCION);
                    resolucionComparendo.setFechaGeneracion(new Date());
                    try {
                        resolucionComparendo.setIdTipoDocumentoGenerado(
                                EnumTipoDocumentoGenerado.valueOf(ConstantesComparendo.RESOLUCION_SANCION
                                        + comparendo.getInfraccion().getCodigo().toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        logger.error("No existe el tipo de documento para esta infraccion :"
                                + comparendo.getInfraccion().getCodigo().toUpperCase());
                    }
                    resolucionComparendo.setIdTipoResolucion(EnumTipoResolucion.SANCION.getValue());
                    resolucionComparendo
                            .setNumeroComparendo(comparendo.getOrdenComparendoNacional().getNumeroComparendo());
                    resolucionComparendo.setEsPolca(comparendo.getEsPolca());
                    resolucionComparendo.setIdTipoComparendo(comparendo.getTipoComparendo().getId());
                    resolucionComparendo.setNumeroConsecutivo(numeroResolucion);
                    long idResolucion = fachadaAdminResolucionEJB.registrarResolucion(resolucionComparendo);

                    // Guarda el resultado de la 'Generacion de Resoluciones de Sancion' por comparendo
                    if (comparendo.getComparendoResolucions() == null) {
                        comparendo.setComparendoResolucions(new ArrayList<ComparendoResolucion>());
                    }
                    ComparendoResolucion comparendoResolucion = new ComparendoResolucion();
                    comparendoResolucion.setIdResolucion(idResolucion);
                    comparendoResolucion.setComparendo(comparendo);
                    comparendo.getComparendoResolucions().add(comparendoResolucion);

                    /*
                     * TODO: Usuario de tareas programadas. Registra en la trazabilidad de cada comparendo listado en el paso 3 del Flujo basico que
                     * la resolucion de sancion fue correctamente generada teniendo en cuenta los siguientes campos:
                     * 
                     * - Actividad ='Generacion de Resolucion de sancion' (Generar resolucion de sancion de comparendos) - Estado del
                     * comparendo=Vigente - Fecha de Registro - Usuario
                     */
                    generarTrazabilidad(comparendo, EnumActividad.GENERACION_RESOLUCION_SANCION.getValue(),
                            resolucionComparendo.getFechaGeneracion());
                    em.merge(comparendo);

                    // Pasa las validaciones, indica que existe al menos un comparendo para generar resolucion
                    ComparendoDTO comparendoDTO = ComparendoHelperExtend.toLevel1DTOOrden(comparendo);
                    comparendoDTO.setIdResolucionSancionGenerada(idResolucion);
                    lstComparendoDTO.add(comparendoDTO);
                }
            }
        }
        if (lstComparendoDTO.isEmpty()) {
            // No existen comparendos aptos para la generacion de la resolucion.
            // Retorna:
            // - Fecha de procesamiento
            // - Codigo de respuesta = No se encontraron comparendos para generarles una resolucion de sancion.
            respuesta.setFechaHoraProcesamiento(new Date());
            respuesta.setRespuesta(EnumRespuestaResolucionSancion.NO_COMPARENDOS_RESOLUCION);
        } else {
            respuesta.setFechaHoraProcesamiento(new Date());
            respuesta.setRespuesta(EnumRespuestaResolucionSancion.RESOLUCION_GENERADA);
            respuesta.setComparendosResolucion(lstComparendoDTO);
        }
        return respuesta;
    }

    /**
     * Busca las configuraciones de descuento existentes
     * 
     * @param fechaGeneracion
     * @param codigoOrganismoTransito
     * @return Lista de configuraciones
     * @throws CirculemosNegocioException
     * @author julio.pinzon
     */
    private List<ConfiguracionDescuentoDTO> obtenerConfiguraciones(Date fechaGeneracion, int codigoOrganismoTransito)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB::obtenerConfiguraciones(Date, int)");

        /*
         * Consulta el 'Tipo de configuracion de descuento' parametrizada mediante el caso de uso 'Administrar condiciones de descuentos de
         * comparendos'. IGUAL a 'Tipo 1: Por Tipo de notificacion del comparendo' que este ACTIVA,
         */
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT cd ");
        jpql.append(" FROM ConfiguracionDescuento cd");
        jpql.append(" LEFT JOIN cd.tipoNotificacionList tn");
        jpql.append(" LEFT JOIN cd.medioImposicionList mi");
        jpql.append(" WHERE cd.tipoDescuento.id = :tipoDescuento");
        jpql.append(" AND cd.organismoTransito.codigoOrganismo = :codigoOrganismo");
        jpql.append(" AND cd.fechaVigenciaInicio <= :fechaGeneracion");
        jpql.append(" AND (cd.fechaVigenciaFin >= :fechaGeneracion OR cd.fechaVigenciaFin IS NULL) ");
        filtros.put("codigoOrganismo", codigoOrganismoTransito);
        filtros.put("tipoDescuento", EnumTipoDescuento.CONFIG_POR_NOTIFICACION_COMPARENDO.getValue());
        filtros.put("fechaGeneracion", fechaGeneracion);

        GenericDao<ConfiguracionDescuento> configDescuentoDao = new GenericDao<>(ConfiguracionDescuento.class, em);
        List<ConfiguracionDescuento> resultadoConsulta = configDescuentoDao.buildAndExecuteQuery(jpql, filtros);
        if (resultadoConsulta.isEmpty()) {
            throw new CirculemosNegocioException(ErrorComparendo.GenerarResolucionesSancion.COM_009002);
        }

        List<ConfiguracionDescuentoDTO> configuracionesDescuento = ConfiguracionDescuentoHelperExtend
                .toListLevel1DTO(resultadoConsulta);

        for (ConfiguracionDescuentoDTO configuracionDescuentoDTO : configuracionesDescuento) {
            // Fecha de consulta: La cual se obtiene de la siguiente manera:
            // Operacion 1: Fecha de generacion de la resolucion de sancion - Dias habiles para el inicio de intereses de comparendos (IMPORTANTE:
            // Aca
            // se deben contar UNICAMENTE los dias HABILES)
            int cantidadDiasHabiles = 0;
            if (configuracionDescuentoDTO.getCantidadDiasHabiles() != null) {
                cantidadDiasHabiles = configuracionDescuentoDTO.getCantidadDiasHabiles();
            }
            Date fechaConsultaOp1 = fachadaAdminGeneral.sumarDias(codigoOrganismoTransito, fechaGeneracion,
                    (cantidadDiasHabiles * -1), true);
            // Operacion 2: Fecha de generacion de la resolucion de sancion - Dias calendario para el inicio de intereses de comparendos
            // (IMPORTANTE:
            // Aca se deben contar los dias CALENDARIO)
            int cantidadDiasCalendario = 0;
            if (configuracionDescuentoDTO.getCantidadDiasCalendario() != null) {
                cantidadDiasCalendario = configuracionDescuentoDTO.getCantidadDiasCalendario();
            }
            Date fechaConsultaOp2 = fachadaAdminGeneral.sumarDias(codigoOrganismoTransito, fechaGeneracion,
                    (cantidadDiasCalendario * -1), false);
            // Se realiza una comparacion de las fechas calculadas en ambas operaciones (Operacion 1 y Operacion 2 ) y se selecciona como 'Fecha de
            // consulta' la fecha mas antigua
            Date fechaConsulta = fechaConsultaOp1;
            if (fechaConsultaOp1.after(fechaConsultaOp2)) {
                fechaConsulta = fechaConsultaOp2;
            }
            configuracionDescuentoDTO.setFechaConsulta(fechaConsulta);
        }
        return configuracionesDescuento;
    }

    @Override
    public void generarResolucionSancionAutomatico(int codigoOrganismo, Date fechaGeneracion)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB::generarResolucionSancionAutomatico(int, Date)");

        /*
         * Realiza el proceso de seleccion de comparendo a los cuales se les debe generar las resoluciones de Sancion por medio de la inclusion del
         * caso de uso 'Generar Resoluciones de Sancion'
         */
        RespuestaResolucionComparendoDTO respuestaResolucionComparendoDTO = generarResolucionesSancion(fechaGeneracion,
                codigoOrganismo);

        // Objeto para escribir en el log
        ResolucionSancionAutomaticaLogDTO resolucionSancionAutomaticaLogDTO = new ResolucionSancionAutomaticaLogDTO();
        resolucionSancionAutomaticaLogDTO.setFechaInicioProceso(fechaGeneracion);
        resolucionSancionAutomaticaLogDTO.setCodigoRespuesta(respuestaResolucionComparendoDTO.getRespuesta().getId()
                + "-" + respuestaResolucionComparendoDTO.getRespuesta().name());
        List<String> numerosComparendo = new ArrayList<String>();
        if (respuestaResolucionComparendoDTO.getComparendosResolucion() != null) {
            for (ComparendoDTO comparendo : respuestaResolucionComparendoDTO.getComparendosResolucion()) {
                // Agrega el numero de comparendo para registrarlo en el log
                numerosComparendo.add(comparendo.getOrdenComparendoNacional().getNumeroComparendo());
            }
        }
        resolucionSancionAutomaticaLogDTO.setNumerosComparendo(numerosComparendo);

        // Busca el 'Correo electronico para envoo de la operacion' o el listado 'Correo electronico para envoo de la operacion' configurados mediante
        // el caso de uso "Administrar correos electronicos" teniendo en cuenta lo siguiente :
        // - El 'Tipo de comunicacion por correo electronico' debe ser IGUAL a 'Envio de rta de proceso de resoluciones de sancion'
        // - El 'Estado de la configuracion de correos electronicos' debe ser IGUAL a 'Activo'
        List<CorreoEnvioDTO> correos = fachadaAdminNegocioEJB.consultarEmailsConfigurados(codigoOrganismo, true,
                EnumTipoCorreo.ENVIO_RTA_RESOLUCIONES_SANCION);
        if (!correos.isEmpty()) {
            // Consulta el organismo
            OrganismoTransitoDTO organismo = new OrganismoTransitoDTO(codigoOrganismo);
            List<OrganismoTransitoDTO> organismos = fachadaAdminNegocioEJB
                    .consultarOrganismoTransito(new OrganismoTransitoDTO(codigoOrganismo));
            if (!organismos.isEmpty()) {
                organismo = organismos.get(0);
            }
            /*
             * Consulta el resultado de la 'Generacion de Resoluciones de Sancion' por 'Fecha de generacion de la resolucion de sancion', registrado
             * mediante el caso de uso 'Generar Resoluciones de Sancion'
             */
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantesComparendo.FORMATO_FECHA_CORREO_RESOLUCION);
            StringBuilder datosResoluciones = new StringBuilder();
            if (respuestaResolucionComparendoDTO.getComparendosResolucion() != null) {
                for (ComparendoDTO comparendo : respuestaResolucionComparendoDTO.getComparendosResolucion()) {

                    // Consulta la resolucion de sancion generada
                    ConsultaResolucionesDTO consulta = new ConsultaResolucionesDTO();
                    List<Long> idsResolucion = new ArrayList<Long>(1);
                    idsResolucion.add(comparendo.getIdResolucionSancionGenerada());
                    consulta.setIdResolucion(idsResolucion);
                    List<ResolucionDTO> resoluciones = fachadaAdminResolucionEJB.consultarResoluciones(consulta);
                    String respuesta = ConstantesComparendo.VALOR_NO_CORREO;
                    if (resoluciones.get(0).getResolucionExitosa()) {
                        respuesta = ConstantesComparendo.VALOR_SI_CORREO;
                    }

                    // Asumimos que la resolucion existe
                    datosResoluciones.append(ConstantesComparendo.TAG_TR_INICIO);
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_INICIO);
                    datosResoluciones.append(comparendo.getOrdenComparendoNacional().getNumeroComparendo());
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_FIN);
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_INICIO);
                    datosResoluciones.append(resoluciones.get(0).getNumeroResolucion());
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_FIN);
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_INICIO);
                    datosResoluciones.append(sdf.format(resoluciones.get(0).getFechaResolucion()));
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_FIN);
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_INICIO);
                    datosResoluciones.append(resoluciones.get(0).getAnoResolucion());
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_FIN);
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_INICIO);
                    datosResoluciones.append(respuesta);
                    datosResoluciones.append(ConstantesComparendo.TAG_TD_FIN);
                    datosResoluciones.append(ConstantesComparendo.TAG_TR_FIN);
                }
            }
            // Lena los datos variables que van en el mensaje del correo
            Calendar fechaResoluciones = Calendar.getInstance();
            fechaResoluciones.setTime(fechaGeneracion);
            Map<String, Object> variablesMensaje = new HashMap<String, Object>();
            variablesMensaje.put(ConstantesComparendo.DIA_FECHA, fechaResoluciones.get(Calendar.DAY_OF_MONTH));
            variablesMensaje.put(ConstantesComparendo.MES_FECHA, fechaResoluciones.get(Calendar.MONTH));
            variablesMensaje.put(ConstantesComparendo.ANIO_FECHA, fechaResoluciones.get(Calendar.YEAR));
            variablesMensaje.put(ConstantesComparendo.CANTIDAD_RESOLUCIONES, numerosComparendo.size());
            variablesMensaje.put(ConstantesComparendo.DATOS_RESOLUCIONES, datosResoluciones.toString());
            variablesMensaje.put(ConstantesComparendo.ORGANISMO, organismo.getNombreOrganismo());
            // Envia el mensaje a los correos
            List<String> emails = new ArrayList<String>();
            for (CorreoEnvioDTO correoEnvioDTO : correos) {
                emails.add(correoEnvioDTO.getEmail());
            }
            iRCirculemosMailSender.enviarCorreo(codigoOrganismo, EnumTipoCorreo.ENVIO_RTA_RESOLUCIONES_SANCION,
                    new String[0], variablesMensaje);

            // Asigna valor de envio de correo
            resolucionSancionAutomaticaLogDTO.setCorreoEnviado(ConstantesComparendo.VALOR_SI_CORREO);
        } else {
            // NO existe una configuracion de correo electronico con el 'Tipo de comunicacion por correo electronico' IGUAL a
            // 'Envio de rta
            // de proceso
            // de resoluciones de sancion' y que este activa.
            // Asigna valor de envo de correo
            resolucionSancionAutomaticaLogDTO.setCorreoEnviado(ConstantesComparendo.VALOR_NO_CORREO);
        }
        // Se registra en el log la respuesta del proceso de generaci脙炉脗驴脗陆n de resoluciones
        resolucionSancionAutomaticaLogDTO.setFechaFinalProceso(new Date());
        resolucionSancionAutomaticaLogDTO
                .setFechaProcesamiento(resolucionSancionAutomaticaLogDTO.getFechaFinalProceso());
        LoggerC2.getLogger(EnumLogSistema.RESOL_SANCION_AUTO).escribir(
                iRSeguridadCirculemos.obtenerUsuarioDto().getUsuario().getLogin(), resolucionSancionAutomaticaLogDTO);
    }

    @Override
    public AvisoGeneradoDTO generarAvisoNotificacion(int codigoOrganismo)
            throws CirculemosNegocioException, CirculemosAlertaException {
        logger.debug("ComparendoEJB::generarAvisoNotificacion(int)");
        return this.generarAvisoNotificacion(codigoOrganismo, false);
    }

    @Override
    public int notificarComparendosAviso(int codigoOrganismo) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB::notificarComparendoAviso(int)");

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT na FROM NotificacionAviso na");
        jpql.append(" JOIN na.organismoTransito ot");
        jpql.append(" WHERE");
        jpql.append(" na.fechaNotificacion <= :fechaActual");
        jpql.append(" AND na.notificado=:notificado");
        jpql.append(" AND ot.codigoOrganismo=:organismoTransito");

        Map<String, Object> filtros = new HashMap<String, Object>();

        filtros.put("fechaActual", new Date());
        filtros.put("notificado", Boolean.FALSE);
        filtros.put("organismoTransito", codigoOrganismo);

        GenericDao<NotificacionAviso> dao = new GenericDao<NotificacionAviso>(NotificacionAviso.class, em);
        List<NotificacionAviso> lstNotificaciones = dao.buildAndExecuteQuery(jpql, filtros);

        int cantidadComparendosNotificados = 0;

        for (NotificacionAviso notificacionAviso : lstNotificaciones) {
            NotificarComparendoDTO notificarComparendoDTO = new NotificarComparendoDTO();
            for (Comparendo comparendo : notificacionAviso.getComparendos()) {
                if (comparendo.getFechaNotificacion() == null && comparendo.getTipoNotificacion()
                        .getId() == EnumTipoNotificacionComparendo.EN_PROCESO_NOTIFICACION_POR_AVISO.getValue()) {
                    notificarComparendoDTO.setFechaNotificacion(notificacionAviso.getFechaNotificacion());
                    notificarComparendoDTO
                            .setIdActividadTrazabilidad(EnumActividad.NOTIFICACION_POR_AVISO_COMPARENDO.getValue());
                    notificarComparendoDTO
                            .setIdTipoNotificacion(EnumTipoNotificacionComparendo.NOTIFICACION_POR_AVISO.getValue());
                    notificarComparendoDTO.setActivaCartera(true);

                    this.notificarComparendoTerceros(comparendo);
                    // Cambia el Comparendo a vigente, genera trazabilidad, genera cartera
                    notificarComparendo(comparendo, notificarComparendoDTO);
                    em.merge(comparendo);

                    // Calcula el campo Cantidad de comparendos notificados por aviso
                    cantidadComparendosNotificados++;
                }

            }
            notificacionAviso.setNotificado(true);
            notificacionAviso.setFechaEjecucionNotificacion(new Date());
            em.merge(notificacionAviso);
        }

        if (cantidadComparendosNotificados == 0) {
            // Escribe en el log
            ILog iLog = LoggerC2.getLogger(EnumLogSistema.NOTIF_COMPA_AVISO);
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            NotificacionAvisoLogDTO notificacionAvisoLogDTO = new NotificacionAvisoLogDTO();
            notificacionAvisoLogDTO.setOrganismoTransito(codigoOrganismo);
            notificacionAvisoLogDTO.setFechaActualDelSistema(new Date());
            notificacionAvisoLogDTO.setCantidadDeComparendosNotificados(cantidadComparendosNotificados);
            iLog.escribir(usuarioPersonaDTO.getLogin(), notificacionAvisoLogDTO);
        }

        return cantidadComparendosNotificados;
    }

    @Override
    public DocumentoAvisoGeneradoDTO generarAvisoNotificacion(int codigoOrganismo, boolean cargarContenido)
            throws CirculemosNegocioException, CirculemosAlertaException {
        logger.debug("ComparendoEJB::generarAvisoNotificacion(int,boolean)");

        //
        // Realiza una consulta sobre la informacion de los comparendos asignados al "Organismo de Transito"
        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT c FROM Comparendo c JOIN c.personaList p");
        jpql.append(" WHERE c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :pCodOrg");
        final Map<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("pCodOrg", codigoOrganismo);
        // con las siguientes condiciones:
        // * El "Numero de Comparendo" NO ha sido notificado exitosamente dado que el campo "Fecha Notificacion" NO esta registrado.
        jpql.append(" AND c.fechaNotificacion IS NULL");
        // * El tipo de "Tipo de Notificacion del Comparendo" es DIFERENTE a "En proceso notificacion por aviso".
        jpql.append(" AND (c.tipoNotificacion IS NULL OR c.tipoNotificacion.id <> :pTipNot )");
        filtros.put("pTipNot", EnumTipoNotificacionComparendo.EN_PROCESO_NOTIFICACION_POR_AVISO.getValue());
        // * La fecha actual es MAYOR O IGUAL a la "Fecha de imposicion comparendo" + "el parametro: Cantidad de dias para generar aviso de
        // notificacion comparendo"
        jpql.append(" AND c.fechaInfraccion <= :pFecGenAvi");
        final int diasGeneracionAviso = fachadaAdminGeneral
                .consultarValorParametro(EnumParametro.DIAS_GENERACION_AVISO_NOTIF, codigoOrganismo, true)
                .getValorParamInt();
        final Date fechaGenAviso = fachadaAdminGeneral.sumarDias(codigoOrganismo, UtilFecha.currentZeroTimeDate(),
                -diasGeneracionAviso, false);
        filtros.put("pFecGenAvi", fechaGenAviso);
        // * NO incluye los comparendos donde el "Tipo de Documento del infractor" y "Numero Documento del infractor" NO estan registrados.
        jpql.append(" AND p.tipoPersonaComparendo.codigo = :pTipPerCom");
        jpql.append(" AND p.tipoIdentificacion IS NOT NULL");
        jpql.append(" AND p.numeroIdentificacion IS NOT NULL");
        filtros.put("pTipPerCom", EnumTipoPersonaComparendo.INFRACTOR.getValue());

        final GenericDao<Comparendo> dao = new GenericDao<Comparendo>(Comparendo.class, em);
        List<Comparendo> lstComparendos = dao.buildAndExecuteQuery(jpql, filtros);

        // NO existen comparendos que cumplan las condiciones para incluirlos en una resolucion de Notificacion por aviso.
        ILog loggerC2 = LoggerC2.getLogger(EnumLogSistema.GENERAR_EDICTO);
        if (lstComparendos.isEmpty()) {
            GenerarNotificacionAviso com055001 = ErrorComparendo.GenerarNotificacionAviso.COM_055001;
            loggerC2.escribir(iRSeguridadCirculemos.obtenerUsuarioDto().getLogin(), new LoggerGenerarNotificacionAviso(
                    codigoOrganismo, lstComparendos.size(), com055001.getDescError()));
            throw new CirculemosNegocioException(com055001);
        }

        // Genero notificacion de aviso a enviar para generacion de documento
        NotificacionAviso notificacionAviso = new NotificacionAviso();
        // Se obtiene un "Numero del consecutivo" mediante la inclusion del caso de uso "Obtener consecutivos" enviando los siguientes datos:
        // * Nombre del Consecutivo= "Notificacion de comparendos por aviso"
        // * Organismo de Transito
        final String conNotifCompAviso = fachadaAdminGeneral.generarConsecutivo(codigoOrganismo,
                EnumConsecutivo.NOTIF_COMPA_AVISO);
        notificacionAviso.setConsecutivo(conNotifCompAviso);

        // * Fecha generacion del aviso = Fecha actual del sistema
        java.util.Date fechaActual = new java.util.Date();
        notificacionAviso.setFechaGeneracion(fechaActual);
        // Calculo Fecha para Notificar comparendo por aviso = "Fecha generacion del aviso" +
        // "Cantidad de dias para activar cartera despues del aviso"; para el calculo con el valor
        // "Cantidad de dias para activar cartera despues del aviso" tener en cuenta el valor del parametro "Tipo de dia para notificar".
        int diasActCartAviso = fachadaAdminGeneral
                .consultarValorParametro(EnumParametro.DIAS_ACTIVAR_CARTE_AVISO, codigoOrganismo, true)
                .getValorParamInt();
        int valTipDia = fachadaAdminGeneral.consultarValorParametro(EnumParametro.TIPO_DIA_NOTIF, codigoOrganismo, true)
                .getValorParamInt();
        EnumTipoDia tipoDia = Utilidades.buscarElemEnum(EnumTipoDia.class, valTipDia);
        Date fechaNotifComparendo = fachadaAdminGeneral.sumarDias(codigoOrganismo, fechaActual, diasActCartAviso,
                tipoDia.equals(EnumTipoDia.DIA_HABIL));
        notificacionAviso.setFechaNotificacion(fechaNotifComparendo);
        notificacionAviso.setNotificado(false);
        notificacionAviso.setOrganismoTransito(new OrganismoTransito());
        notificacionAviso.getOrganismoTransito().setCodigoOrganismo(codigoOrganismo);

        notificacionAviso.setComparendos(lstComparendos);
        notificacionAviso.setCantidadComparendos(lstComparendos.size());
        em.persist(notificacionAviso);

        // Con los "Numero de Comparendo" obtenidos en la consulta se genera el documento segun la plantilla definida bajo la inclusion del caso
        // de uso "Generar Documento circulemos 2.0", enviando los siguientes datos:
        // * Nombre del documento Circulemos 2 = "Notificacion aviso del comparendo"
        // * Organismo de Transito
        // * Usuario que genera el documento = Usuario en sesion
        // * Fecha de generacion del documento = Fecha del sistema
        // * Consecutivo del Documento
        GeneraEdictoDTO generaNotificacionAvisoDTO = new GeneraEdictoDTO();
        generaNotificacionAvisoDTO.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.NOTIF_AVISO_COMPA);
        generaNotificacionAvisoDTO.setCodigoOrganismo(codigoOrganismo);
        generaNotificacionAvisoDTO.setFechaGeneracion(fechaActual);
        generaNotificacionAvisoDTO.setIdAviso(notificacionAviso.getId());
        Long idDocumento = null;
        try {
            idDocumento = iRDocumentosCirculemos.generarDocumento(generaNotificacionAvisoDTO);
        } catch (CirculemosAlertaException e) {
            loggerC2.escribir(iRSeguridadCirculemos.obtenerUsuarioDto().getLogin(),
                    new LoggerGenerarNotificacionAviso(codigoOrganismo, lstComparendos.size(), e.getMessage()));
            throw new CirculemosNegocioException(GenerarNotificacionAviso.COM_055002);
        }

        // Se actualiza la notificacion por aviso
        notificacionAviso.setIdArchivoGenerado(idDocumento);
        em.merge(notificacionAviso);

        // Modifica los siguientes campos para cada comparendo:
        // * Tipo de Notificacion del Comparendo = En proceso notificacion por aviso
        for (Comparendo comparendo : lstComparendos) {
            comparendo.setTipoNotificacion(new TipoNotificacionComparendo());
            comparendo.getTipoNotificacion()
                    .setId(EnumTipoNotificacionComparendo.EN_PROCESO_NOTIFICACION_POR_AVISO.getValue());
            em.merge(comparendo);
        }

        // Fin antiguo generar aviso
        DocumentoAvisoGeneradoDTO avisoGeneradoDTO = new DocumentoAvisoGeneradoDTO();
        avisoGeneradoDTO.setCantidadComparendos(lstComparendos.size());
        avisoGeneradoDTO.setIdAviso(notificacionAviso.getId());

        // seteo el id doc
        avisoGeneradoDTO.setIdDocumento(notificacionAviso.getIdArchivoGenerado());
        List<Long> documento = new ArrayList<>();
        documento.add(avisoGeneradoDTO.getIdDocumento());

        byte[] contenido = iRDocumentosCirculemos.consultarDocumentosPDF(documento);

        ArchivoTransportableDTO archivoTransportableDTO = new ArchivoTransportableDTO();
        archivoTransportableDTO.setContenido(contenido);

        if (!cargarContenido) {
            archivoTransportableDTO.setContenido(null);
        }

        avisoGeneradoDTO.setArchivoTransportableDTO(archivoTransportableDTO);

        return avisoGeneradoDTO;
    }

    @Override
    public List<NotificacionAvisoDTO> consultarAvisosNotificacion(
            ConsultaAvisosNotificacionDTO consultaAvisosNotificacionDTO) throws CirculemosNegocioException {
        logger.debugv(
                "ComparendoEJB::consultarAvisosNotificacion(ConsultaAvisosNotificacionDTO) codigoOrganismo {0} fecha Inicial Generacion {1} fecha final generacion {2}",
                consultaAvisosNotificacionDTO.getCodigoOrganismo(),
                consultaAvisosNotificacionDTO.getFechaInicialGeneracion(),
                consultaAvisosNotificacionDTO.getFechaFinalGeneracion());

        if ((consultaAvisosNotificacionDTO.getFechaInicialGeneracion() != null
                && consultaAvisosNotificacionDTO.getFechaFinalGeneracion() == null)
                || (consultaAvisosNotificacionDTO.getFechaInicialGeneracion() == null
                        && consultaAvisosNotificacionDTO.getFechaFinalGeneracion() != null)) {
            throw new CirculemosNegocioException(ErrorComparendo.NotificarEdicto.COM_053002);
        }

        ValorParametroDTO valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.CANT_MEDIA_DIAS_REALIZAR_CONSULTA, consultaAvisosNotificacionDTO.getCodigoOrganismo(),
                true);

        if (consultaAvisosNotificacionDTO.getFechaInicialGeneracion() != null
                && consultaAvisosNotificacionDTO.getFechaFinalGeneracion() != null) {
            int dias = Utilidades.diasDiferencia(consultaAvisosNotificacionDTO.getFechaInicialGeneracion(),
                    consultaAvisosNotificacionDTO.getFechaFinalGeneracion());
            if (dias > Long.parseLong(valorParametroDTO.getValorParam())) {
                throw new CirculemosNegocioException(ErrorComparendo.NotificarEdicto.COM_053001);
            }
        }

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT na FROM NotificacionAviso na");
        jpql.append(" JOIN na.organismoTransito ot");
        jpql.append(" WHERE ot.codigoOrganismo=:codOrganismo");

        Map<String, Object> filtros = new HashMap<String, Object>();

        filtros.put("codOrganismo", consultaAvisosNotificacionDTO.getCodigoOrganismo());

        if (consultaAvisosNotificacionDTO.getFechaInicialGeneracion() != null
                && consultaAvisosNotificacionDTO.getFechaFinalGeneracion() != null) {
            jpql.append(" AND na.fechaGeneracion BETWEEN :fechaInicial AND :fechaFinal");
            filtros.put("fechaInicial", consultaAvisosNotificacionDTO.getFechaInicialGeneracion());
            filtros.put("fechaFinal", consultaAvisosNotificacionDTO.getFechaFinalGeneracion());
        }

        GenericDao<NotificacionAviso> dao = new GenericDao<NotificacionAviso>(NotificacionAviso.class, em);
        List<NotificacionAviso> lstNotificaciones = dao.buildAndExecuteQuery(jpql, filtros);

        List<NotificacionAvisoDTO> notificacionAvisoDTOList = NotificacionAvisoHelper
                .toListLevel1DTO(lstNotificaciones);

        return notificacionAvisoDTOList;
    }

    @Override
    public SeguimientoComparendoDTO consultarSeguimientoComparendo(long cicomparendo)
            throws CirculemosNegocioException {
        logger.debug(ComparendoEJB.class.getName().concat("consultarSeguimientoComparendo(long)"));

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT c FROM Comparendo AS c");
        jpql.append(" LEFT JOIN FETCH c.ordenComparendoNacional AS ocn");
        jpql.append(" LEFT JOIN FETCH ocn.organismoTransito");
        jpql.append(" LEFT JOIN FETCH c.infraccion");
        jpql.append(" LEFT JOIN FETCH c.tipoComparendo");
        jpql.append(" LEFT JOIN FETCH c.estadoComparendo");
        jpql.append(" LEFT JOIN FETCH c.trazabilidadComparendoList AS tc");
        jpql.append(" LEFT JOIN FETCH tc.actividad");
        jpql.append(" LEFT JOIN FETCH tc.usuario");

        jpql.append(" WHERE c.cicomparendo = :pCicomp");

        jpql.append(" ORDER BY tc.fechaRegistro DESC");
        Map<String, Object> filtros = new HashMap<String, Object>(1);
        filtros.put("pCicomp", cicomparendo);

        GenericDao<Comparendo> query = new GenericDao<Comparendo>(Comparendo.class, em);
        List<Comparendo> result = query.buildAndExecuteQuery(jpql, filtros);
        if (result.isEmpty()) {
            throw new CirculemosNegocioException(ConsultarSeguimientoComparendo.COM_016005);
        }
        Comparendo comparendo = result.get(0);
        ComparendoDTO comparendoDTO = null;
        SeguimientoComparendoDTO seguimientoComparendoDTO = new SeguimientoComparendoDTO();
        comparendoDTO = ComparendoHelperExtend.toLevel1DTOSeguimientoTrazabilidad(comparendo);

        // Consulta la configuracion de infraccion correspondiente
        comparendoDTO.getInfraccion().setConfiguracionInfraccionList(new ArrayList<ConfiguracionInfraccionDTO>(1));
        ConfiguracionInfraccionDTO configuracionInfraccionDTO = infraccionEJB
                .consultarInfraccion(comparendo.getInfraccion().getCodigo(), comparendo.getFechaInfraccion());
        seguimientoComparendoDTO.setDescripcionInfraccion(configuracionInfraccionDTO.getDescripcion());

        // ingresa los datos de la trazabilidad
        comparendoDTO.setTrazabilidadComparendoList(
                new ArrayList<TrazabilidadComparendoDTO>(comparendo.getTrazabilidadComparendoList().size()));
        for (TrazabilidadComparendo trazaComparendo : comparendo.getTrazabilidadComparendoList()) {
            TrazabilidadComparendoDTO trazabilidadComparendoDTO = TrazabilidadComparendoHelper
                    .toLevel1DTO(trazaComparendo);

            UsuarioPersona usuario = trazaComparendo.getUsuario();
            if (usuario != null) {
                List<PersonaDTO> resuList = fachadaAdminNegocioEJB
                        .consultarPersona(new PersonaDTO(usuario.getPersona().getId()));
                if (!resuList.isEmpty()) {
                    trazabilidadComparendoDTO.getUsuario().setPersona(resuList.get(0));
                }
            }
            comparendoDTO.getTrazabilidadComparendoList().add(trazabilidadComparendoDTO);
        }

        // Consulta del infractor del comparendo
        TypedQuery<ComparendoPersona> query2 = em.createNamedQuery(ComparendoPersona.SQ_FIND_BY_TIPO_PERSONA_COMP,
                ComparendoPersona.class);
        query2.setParameter("pCodTipPerCom", EnumTipoPersonaComparendo.INFRACTOR.getValue());
        query2.setParameter("pCiComp", comparendo.getCicomparendo());
        List<ComparendoPersona> compPers = query2.getResultList();
        if (!compPers.isEmpty()) {
            comparendoDTO.setInfractor(ComparendoPersonaHelperExtend.toLevel1DTO(compPers.get(0)));
        }

        seguimientoComparendoDTO.setComparendoDTO(comparendoDTO);

        // Consulta de saldos de cartera
        TypedQuery<ComparendoCartera> queryCartera = em.createNamedQuery(ComparendoCartera.SQ_FIND_BY_COMPARENDO,
                ComparendoCartera.class);
        queryCartera.setParameter("pCicomp", comparendo.getCicomparendo());
        List<ComparendoCartera> listCartera = queryCartera.getResultList();
        SaldoComparendoDTO saldoComparendoDTO = new SaldoComparendoDTO();
        if (!listCartera.isEmpty()) {
            CarteraComparendoDTO carteraComparendoDTO = iCarteraComparendo.consultarCartera(
                    listCartera.get(0).getIdCartera(), EnumOpcConsultaCarteraComp.CONSULTA_SALDO_INTERES);
            if (carteraComparendoDTO != null) {
                saldoComparendoDTO.setSaldoCapital(carteraComparendoDTO.getValorSaldoCapital());
                saldoComparendoDTO.setSaldoInteres(carteraComparendoDTO.getValorSaldoInteres());
            }
        }
        if (saldoComparendoDTO.getSaldoCapital() == null) {
            saldoComparendoDTO.setSaldoCapital(BigDecimal.ZERO);
        }
        if (saldoComparendoDTO.getSaldoInteres() == null) {
            saldoComparendoDTO.setSaldoInteres(BigDecimal.ZERO);
        }
        seguimientoComparendoDTO.setSaldoComparendoDTO(saldoComparendoDTO);

        // Consulta valor de la infracci贸n

        seguimientoComparendoDTO
                .setValorInfraccion(iCarteraComparendo.consultarValorDeInfraccion(listCartera.get(0).getIdCartera()));
        return seguimientoComparendoDTO;
    }

    @Override
    public ComparendoDTO consultarComparendoTrazabilidad(String numeroComparendo, Integer codigoOrganismo) {
        logger.debug(ComparendoEJB.class.getName().concat("consultarTrazabilidadComparendo(String, Integer)"));

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT c FROM Comparendo AS c");
        Map<String, Object> filtros = new HashMap<String, Object>();
        jpql.append(" WHERE c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", codigoOrganismo);
        jpql.append(" AND c.ordenComparendoNacional.numeroComparendo = :pNumComp");
        filtros.put("pNumComp", numeroComparendo);

        GenericDao<Comparendo> query = new GenericDao<Comparendo>(Comparendo.class, em);
        List<Comparendo> resultado = query.buildAndExecuteQuery(jpql, filtros);
        if (resultado != null) {
            return ComparendoHelperExtend.toLevel1DTOTrazaExtend(resultado.get(0));
        }
        return null;
    }

    @Override
    public SaldoComparendoDTO calcularSaldoComparendo(int codigoOrganismo, Long cicomparendo, Date fechaLiquidacion)
            throws CirculemosNegocioException {
        logger.debug(ComparendoEJB.class.getName().concat("calcularSaldoComparendo(int, Long, Date)"));
        SaldoComparendoDTO saldoComparendoDTO = new SaldoComparendoDTO();
        ComparendoDTO comparendoDTO = consultarComparendo(cicomparendo);
        validarComparendoPerteneceOrganismo(comparendoDTO, codigoOrganismo);

        ComparendoCarteraDTO comparendoCarteraDTO;
        CarteraComparendoDTO carteraComparendoDTO;

        if (comparendoDTO != null) {
            List<ComparendoCarteraDTO> lComparendoCartera = comparendoDTO.getComparendoCarteraList();
            ConfiguracionInfraccionDTO configuracionInfraccionDTO = infraccionEJB
                    .consultarInfraccion(comparendoDTO.getInfraccion().getCodigo(), comparendoDTO.getFechaInfraccion());
            if (configuracionInfraccionDTO != null && configuracionInfraccionDTO.getGeneraCartera()
                    && lComparendoCartera != null && !lComparendoCartera.isEmpty()) {
                comparendoCarteraDTO = lComparendoCartera.get(0);
                carteraComparendoDTO = iCarteraComparendo.consultarCartera(comparendoCarteraDTO.getIdCartera(), null);
                saldoComparendoDTO = calcularSaldos(carteraComparendoDTO, comparendoDTO, fechaLiquidacion);
            }
        }
        return saldoComparendoDTO;
    }

    private void validarComparendoPerteneceOrganismo(ComparendoDTO comparendoDTO, int codigoOrganismo)
            throws CirculemosNegocioException {
        if (!existeComparendo(comparendoDTO.getOrdenComparendoNacional().getNumeroComparendo(), codigoOrganismo)) {
            throw new CirculemosNegocioException(ErrorComparendo.CalcularSaldoComparendo.COM_064001);
        }
    }

    private SaldoComparendoDTO calcularSaldos(CarteraComparendoDTO carteraComparendoDTO, ComparendoDTO comparendoDTO,
            Date fechaLiquidacion) throws CirculemosNegocioException {

        SaldoComparendoDTO saldoComparendoDTO = new SaldoComparendoDTO();
        ConfiguracionDescuentoDTO configuracionDescuentoDTO;

        if (carteraComparendoDTO != null) {
            saldoComparendoDTO.setSaldoCapital(carteraComparendoDTO.getValorSaldoCapital());
            configuracionDescuentoDTO = iRDescuentoComparendo.consultarConfiguracionDescuento(
                    comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(),
                    comparendoDTO.getMedioImposicion().getId(), comparendoDTO.getTipoNotificacion().getId(),
                    comparendoDTO.getFechaInfraccion());
            if (configuracionDescuentoDTO != null && configuracionDescuentoDTO.getInteresComparendo()) {
                saldoComparendoDTO.setSaldoInteres(
                        calcularSaldoInteres(configuracionDescuentoDTO, comparendoDTO, fechaLiquidacion));
            } else if (configuracionDescuentoDTO == null) {
                // En dado caso de que no exista una conf. de descuento para la infraccion no se calula los intereses y quedan en cero
                logger.info(ErrorComparendo.CalcularSaldoComparendo.COM_064002.getDescError());
                saldoComparendoDTO.setSaldoInteres(BigDecimal.ZERO);
            }
        }
        return saldoComparendoDTO;
    }

    private BigDecimal calcularSaldoInteres(ConfiguracionDescuentoDTO configuracionDescuentoDTO,
            ComparendoDTO comparendoDTO, Date fechaLiquidacion) throws CirculemosNegocioException {

        BigDecimal saldoInteres = null;
        Date fechaInicialCalculo;

        if (configuracionDescuentoDTO != null && comparendoDTO != null) {
            Date fechaHabil = fachadaAdminGeneral.sumarDias(
                    comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(),
                    comparendoDTO.getFechaInfraccion(), -configuracionDescuentoDTO.getCantidadDiasHabiles(), true);

            Date fechaCalendario = fachadaAdminGeneral.sumarDias(
                    comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo(),
                    comparendoDTO.getFechaInfraccion(), -configuracionDescuentoDTO.getCantidadDiasCalendario(), false);

            if (fechaHabil.compareTo(fechaCalendario) <= 0) {
                fechaInicialCalculo = fechaHabil;
            } else {
                fechaInicialCalculo = fechaCalendario;
            }

            if (fechaInicialCalculo.compareTo(fechaLiquidacion) <= 0) {
                saldoInteres = iCarteraComparendo.calcularInteresesComparendo(comparendoDTO.getCicomparendo(),
                        fechaInicialCalculo, fechaLiquidacion);
            }
        }
        return saldoInteres;
    }

    @Override
    public RespuestaSolicitudNumeroComparendoDTO solicitarNumeroComparendo(
            SolicitudNumeroComparendoDTO solicitudNumeroComparendoDTO) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.solicitarNumeroComparendo(SolicitudNumeroComparendoDTO)");

        final String FORMATO_HORA = "HH:mm";

        OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
        organismoTransitoDTO.setCodigoOrganismo(solicitudNumeroComparendoDTO.getCodigoOrganismo());

        List<OrganismoTransitoDTO> organismos = fachadaAdminNegocioEJB.consultarOrganismoTransito(organismoTransitoDTO);

        if (organismos.isEmpty()) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);
            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050015);
        }

        if (solicitudNumeroComparendoDTO.getFechaImposicion()
                .compareTo(UtilFecha.resetTime(new Date()).getTime()) != 0) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);

            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050009);
        }

        Date horaImposicion = solicitudNumeroComparendoDTO.getHoraImposicion();
        Date fechaActual = null;
        String horaActual = UtilFecha.dateToString(new Date(), FORMATO_HORA);
        String horaimp = UtilFecha.dateToString(solicitudNumeroComparendoDTO.getHoraImposicion(), FORMATO_HORA);
        horaImposicion = UtilFecha.stringToDate(FORMATO_HORA, horaimp);
        fechaActual = UtilFecha.stringToDate(FORMATO_HORA, horaActual);

        if ((horaImposicion.compareTo(fechaActual) > 0) || (horaImposicion.compareTo(fechaActual) == 0)) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);

            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050010);
        }

        if (solicitudNumeroComparendoDTO.getTipoResponsable() == null) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);
            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050004);
        }

        if (solicitudNumeroComparendoDTO.getCodigoOrganismo() == null) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);
            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050007);
        }

        if (StringUtils.isBlank(solicitudNumeroComparendoDTO.getPlacaVehiculo())
                && StringUtils.isBlank(solicitudNumeroComparendoDTO.getIdentificadorVehiculo())) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);

            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050008);
        }

        if (StringUtils.isNotBlank(solicitudNumeroComparendoDTO.getPlacaVehiculo())
                && StringUtils.isNotBlank(solicitudNumeroComparendoDTO.getIdentificadorVehiculo())) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);

            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050014);
        }

        if (StringUtils.isNumeric(solicitudNumeroComparendoDTO.getPlacaAgente()) == false) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);

            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050001);
        }

        if (StringUtils.isNotBlank(solicitudNumeroComparendoDTO.getPlacaVehiculo()))
            if (!Validador.esPlacaValida(solicitudNumeroComparendoDTO.getPlacaVehiculo())) {
                escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);

                throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050011);
            }

        UnificacionResponsableDTO unificacionResponsableDTO = new UnificacionResponsableDTO();

        if (solicitudNumeroComparendoDTO.getTipoResponsable() == EnumTipoResponsableFormulario.ORGANISMO_TRANSITO
                .getValue()) {

            OrganismoTransitoDTO organismoTransito = new OrganismoTransitoDTO();
            organismoTransito.setCodigoOrganismo(solicitudNumeroComparendoDTO.getCodigoOrganismo());
            unificacionResponsableDTO.setOrganismoTransito(organismoTransito);

        } else {

            if (solicitudNumeroComparendoDTO.getTipoDocumento() == null) {
                escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);

                throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050005);
            }

            if (StringUtils.isBlank(solicitudNumeroComparendoDTO.getNumeroDocumento())) {
                escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);

                throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050006);
            }
            ValorParametroDTO valorParametro = fachadaAdminGeneral.consultarValorParametro(
                    EnumParametro.PAIS_INSTALACION, solicitudNumeroComparendoDTO.getCodigoOrganismo(), true);

            TipoIdentificacionPersonaDTO tipoIdentificacionPersona = null;
            tipoIdentificacionPersona = fachadaAdminNegocioEJB
                    .consultarTipoIdentificacionPersonaJuridica(valorParametro.getValorParamInt());

            if (tipoIdentificacionPersona == null
                    || (tipoIdentificacionPersona.getId() != solicitudNumeroComparendoDTO.getTipoDocumento())) {
                escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(), null);
                throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050003);
            }

            PersonaDTO personaDTO = new PersonaDTO();
            personaDTO.setTipoIdentificacion(tipoIdentificacionPersona);
            personaDTO.setNumeroIdentificacion(solicitudNumeroComparendoDTO.getNumeroDocumento());
            unificacionResponsableDTO.setPersona(personaDTO);
        }

        UnificacionResponsableDTO unificacionResponsable = null;
        unificacionResponsable = administracionFormulariosEjb
                .consultarResponsableFormularios(unificacionResponsableDTO);

        if (unificacionResponsable == null) {

            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(),
                    ErrorComparendo.ObtenerOCN.COM_050002.getDescError());

            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050002);
        }

        RespuestaSolicitudNumeroComparendoDTO respuesta = new RespuestaSolicitudNumeroComparendoDTO();

        String numeroFormulario = null;
        numeroFormulario = iLFormulario.seleccionarFormulario(unificacionResponsableDTO,
                EnumTipoFormulario.COMPARENDO_UNICO_NACIONAL);

        if (numeroFormulario == null) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(),
                    ErrorComparendo.ObtenerOCN.COM_050012.getDescError());
            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050012);
        }

        respuesta.setNumeroComparendo(numeroFormulario);

        AgenteDTO agenteDTO = new AgenteDTO();
        OrganismoTransitoDTO organismo = new OrganismoTransitoDTO();
        organismo.setCodigoOrganismo(solicitudNumeroComparendoDTO.getCodigoOrganismo());
        agenteDTO.setOrganismoTransito(organismo);
        agenteDTO.setPlaca(solicitudNumeroComparendoDTO.getPlacaAgente());

        agenteDTO = agenteEJB.consultarAgente(agenteDTO).get(0);

        Date fechaImposicion = solicitudNumeroComparendoDTO.getFechaImposicion();

        if (agenteDTO == null) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(),
                    ErrorComparendo.ObtenerOCN.COM_050001.getDescError());

            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050001);
        }

        if (fechaImposicion.compareTo(agenteDTO.getFechaInicioVigencia()) < 0
                || (agenteDTO.getFechaFinVigencia() != null
                        && fechaImposicion.compareTo(agenteDTO.getFechaFinVigencia()) > 0)) {
            escribirLogOCN(solicitudNumeroComparendoDTO.getCodigoOrganismo(),
                    ErrorComparendo.ObtenerOCN.COM_050013.getDescError());
            throw new CirculemosNegocioException(ErrorComparendo.ObtenerOCN.COM_050013);
        }

        VehiculoDTO vehiculo = null;

        if (StringUtils.isNotBlank(solicitudNumeroComparendoDTO.getPlacaVehiculo())) {

            vehiculo = fachadaRegistroVehicular.consultarVehiculo(solicitudNumeroComparendoDTO.getPlacaVehiculo());

            if (vehiculo == null) {
                vehiculo = new VehiculoDTO();
                vehiculo.setPlaca(solicitudNumeroComparendoDTO.getPlacaVehiculo());
            }

            EmpresaVehiculoDTO empresaVehiculo = null;
            empresaVehiculo = fachadaRegistroVehicular.consultarEmpresa(solicitudNumeroComparendoDTO.getPlacaVehiculo(),
                    solicitudNumeroComparendoDTO.getCodigoOrganismo(),
                    solicitudNumeroComparendoDTO.getFechaImposicion());

            if (empresaVehiculo != null) {
                List<EmpresaVehiculoDTO> empresaVehiculoList = new ArrayList<>();
                empresaVehiculoList.add(empresaVehiculo);
                vehiculo.setEmpresaVehiculoList(empresaVehiculoList);
            }

            ConsultaRegistroVehicularDTO consultaRegistroVehicular = new ConsultaRegistroVehicularDTO();
            consultaRegistroVehicular.setCodigoOrganismo(solicitudNumeroComparendoDTO.getCodigoOrganismo());
            consultaRegistroVehicular.setPlaca(solicitudNumeroComparendoDTO.getPlacaVehiculo());

            PropietarioVehiculoDTO propietarioVehiculo = null;
            propietarioVehiculo = fachadaRegistroVehicular.consultarPropietarioVehiculo(consultaRegistroVehicular);

            if (propietarioVehiculo != null) {
                List<PropietarioVehiculoDTO> propietarioList = new ArrayList<>();
                propietarioList.add(propietarioVehiculo);
                vehiculo.setPropietarioVehiculoList(propietarioList);
            }
            respuesta.setVehiculo(vehiculo);
        }

        UsuarioPersonaDTO usuario = iRSeguridadCirculemos.obtenerUsuarioDto();

        if (numeroFormulario != null) {
            SolicitudOrdenCompaNacio solicitudOrdenComparendo = new SolicitudOrdenCompaNacio();
            solicitudOrdenComparendo.setNumeroComparendo(numeroFormulario);
            solicitudOrdenComparendo.setPlacaVehiculo(solicitudNumeroComparendoDTO.getPlacaVehiculo());
            solicitudOrdenComparendo.setIdentificacionVehiculo(solicitudNumeroComparendoDTO.getIdentificadorVehiculo());
            solicitudOrdenComparendo.setPlacaAgente(solicitudNumeroComparendoDTO.getPlacaAgente());
            solicitudOrdenComparendo.setFechaImposicion(solicitudNumeroComparendoDTO.getFechaImposicion());
            solicitudOrdenComparendo.setHoraImposicion(solicitudNumeroComparendoDTO.getHoraImposicion());
            solicitudOrdenComparendo.setIdAgente(agenteDTO.getId());
            solicitudOrdenComparendo.setIdUsuario(usuario.getUsuario().getId());
            solicitudOrdenComparendo.setFechaRegistro(new Date());

            em.persist(solicitudOrdenComparendo);
        }

        return respuesta;
    }

    private void escribirLogOCN(Integer codigoOrganismoTransito, String mensaje) {
        final String SEPARADOR = "-";
        logger.debug("ComparendoEJB.escribirLogOCN(Integer,String)");
        ILog iLog = LoggerC2.getLogger(EnumLogSistema.NOTIF_COMPA_CORREO);
        UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
        LogSolicitarNumeroComparendo logSolicitarNumeroComparendo = new LogSolicitarNumeroComparendo();
        logSolicitarNumeroComparendo.setOrganismoTransito(codigoOrganismoTransito);
        if (mensaje != null)
            logSolicitarNumeroComparendo
                    .setEstadoTransaccion(EnumEstadoTransaccionLog.FALLIDA.toString() + SEPARADOR + mensaje);
        else
            logSolicitarNumeroComparendo.setEstadoTransaccion(EnumEstadoTransaccionLog.FALLIDA.toString());
        iLog.escribir(usuarioPersonaDTO.getLogin(), logSolicitarNumeroComparendo);
    }

    @Override
    public void notificarComparendoSIMIT(Integer codigoOrganismo, Long cicomparendo, EnumAccionComparendo accion)
            throws CirculemosNegocioException {
        if (codigoOrganismo == null) {
            throw new CirculemosNegocioException(NotificarComparendoSIMIT.SIM_001103);
        }
        if (cicomparendo == null) {
            throw new CirculemosNegocioException(NotificarComparendoSIMIT.SIM_001102);
        }
        if (accion == null) {
            throw new CirculemosNegocioException(NotificarComparendoSIMIT.SIM_001104);
        }

        TypedQuery<Comparendo> query = em.createNamedQuery(Comparendo.SQ_COMPARENDO_BY_CICOM_ORG, Comparendo.class);
        query.setParameter("pCodOrg", codigoOrganismo);
        query.setParameter("pCiComp", cicomparendo);

        List<Comparendo> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            throw new CirculemosNegocioException(NotificarComparendoSIMIT.SIM_001101);
        }
        Comparendo comparendo = resultList.get(0);
        comparendo.setEstadoComparendoSimit(new EstadoComparendoSimit());
        comparendo.getEstadoComparendoSimit().setId(EnumEstadoComparendoSimit.GENERADO.getValue());

        Date fechaModificacion = new Date();
        comparendo.setFechaModificacion(fechaModificacion);

        EnumActividad actividadTrazabilidad = null;
        switch (accion) {
        case NOTIFICACION:
            actividadTrazabilidad = EnumActividad.NOTIFICACION_COMPARENDO_ENVIADA_SIMIT;
            break;
        case RECTIFICACION:
            actividadTrazabilidad = EnumActividad.RECTIFICACION_COMPARENDO_ENVIADA_SIMIT;
            break;
        case REGISTRO:
            actividadTrazabilidad = EnumActividad.COMPARENDO_ENVIADO_SIMIT;
            break;
        }
        if (comparendo.getTrazabilidadComparendoList().isEmpty()) {
            comparendo.setTrazabilidadComparendoList(new ArrayList<TrazabilidadComparendo>(1));
        }
        generarTrazabilidad(comparendo, actividadTrazabilidad.getValue(), fechaModificacion);

        em.merge(comparendo);
    }

    /**
     * Envia la notificaci贸n de la actualizaci贸n del comparendo a los terceros necesarios.
     * 
     * @param comparendo
     *            comparendo a notificar a los terceros
     * @author robert.bautista
     * @throws CirculemosNegocioException
     */
    private void notificarComparendoTerceros(Comparendo comparendo) throws CirculemosNegocioException {
        NotificacionComparendoTerceroDTO notificacion = new NotificacionComparendoTerceroDTO();

        notificacion.setCicomparendo(comparendo.getCicomparendo());
        notificacion.setCodigoOrganismo(
                comparendo.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
        notificacion.setOrigenNotificacionTercero(EnumOrigenNotificacionTercero.ACTUALIZAR);

        this.notificacionComparendoTercero.notificarComparendo(notificacion);
    }

    @Override
    public boolean existeSolicitudOrdenComparendoNacional(SolicitudOrdenCompaNacioDTO solicitudOrdenCompaNacioDTO) {
        logger.debug(ComparendoEJB.class.getName()
                .concat("existeSolicitudOrdenComparendoNacional(SolicitudOrdenCompaNacioDTO)"));

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT socn FROM SolicitudOrdenCompaNacio socn");
        jpql.append(" WHERE 1=1");
        // Fecha consumo
        if (solicitudOrdenCompaNacioDTO.getFechaConsumo() != null) {
            jpql.append(" AND socn.fechaConsumo = :fechaConsumo");
        }
        // Feha imposicion
        if (solicitudOrdenCompaNacioDTO.getFechaImposicion() != null) {
            jpql.append(" AND socn.fechaImposicion = :fechaImposicion");
        }
        // Fecha registro
        if (solicitudOrdenCompaNacioDTO.getFechaRegistro() != null) {
            jpql.append(" AND socn.fechaRegistro = :fechaRegistro");
        }
        // Id agente
        if (solicitudOrdenCompaNacioDTO.getIdAgente() != null) {
            jpql.append(" AND idAgente = :idAgente");
        }
        // Id Usuario
        if (solicitudOrdenCompaNacioDTO.getIdUsuario() != null) {
            jpql.append(" AND idUsuario = :idUsuario");
        }
        // Identificacion vehiculo
        if (solicitudOrdenCompaNacioDTO.getIdentificacionVehiculo() != null) {
            jpql.append(" AND identificacionVehiculo = :identificacionVehiculo");
        }
        // Numero comparendo
        if (solicitudOrdenCompaNacioDTO.getNumeroComparendo() != null) {
            jpql.append(" AND numeroComparendo = :numeroComparendo");
        }
        // Placa agente
        if (solicitudOrdenCompaNacioDTO.getPlacaAgente() != null) {
            jpql.append(" AND placaAgente = :placaAgente");
        }
        // Placa vehiculo
        if (solicitudOrdenCompaNacioDTO.getPlacaVehiculo() != null) {
            jpql.append(" AND placaVehiculo = :placaVehiculo");
        }

        Query query = em.createQuery(jpql.toString());

        // Fecha consumo
        if (solicitudOrdenCompaNacioDTO.getFechaConsumo() != null) {
            query.setParameter("fechaConsumo", solicitudOrdenCompaNacioDTO.getFechaConsumo());
        }
        // Feha imposicion
        if (solicitudOrdenCompaNacioDTO.getFechaImposicion() != null) {
            query.setParameter("fechaImposicion", solicitudOrdenCompaNacioDTO.getFechaImposicion());
        }
        // Fecha registro
        if (solicitudOrdenCompaNacioDTO.getFechaRegistro() != null) {
            query.setParameter("fechaRegistro", solicitudOrdenCompaNacioDTO.getFechaRegistro());
        }
        // Id agente
        if (solicitudOrdenCompaNacioDTO.getIdAgente() != null) {
            query.setParameter("idAgente", solicitudOrdenCompaNacioDTO.getIdAgente());
        }
        // Id Usuario
        if (solicitudOrdenCompaNacioDTO.getIdUsuario() != null) {
            query.setParameter("idUsuario", solicitudOrdenCompaNacioDTO.getIdUsuario());
        }
        // Identificacion vehiculo
        if (solicitudOrdenCompaNacioDTO.getIdentificacionVehiculo() != null) {
            query.setParameter("identificacionVehiculo", solicitudOrdenCompaNacioDTO.getIdentificacionVehiculo());
        }
        // Numero comparendo
        if (solicitudOrdenCompaNacioDTO.getNumeroComparendo() != null) {
            query.setParameter("numeroComparendo", solicitudOrdenCompaNacioDTO.getNumeroComparendo());
        }
        // Placa agente
        if (solicitudOrdenCompaNacioDTO.getPlacaAgente() != null) {
            query.setParameter("placaAgente", solicitudOrdenCompaNacioDTO.getPlacaAgente());
        }
        // Placa vehiculo
        if (solicitudOrdenCompaNacioDTO.getPlacaVehiculo() != null) {
            query.setParameter("placaVehiculo", solicitudOrdenCompaNacioDTO.getPlacaVehiculo());
        }

        boolean existe = false;
        @SuppressWarnings("unchecked")
        List<SolicitudOrdenCompaNacio> solicitudOrdenCompaNacios = query.getResultList();
        if (solicitudOrdenCompaNacios != null && !solicitudOrdenCompaNacios.isEmpty()) {
            existe = true;
        }

        return existe;
    }

    @Override
    public void consumirReservaOCN(String numeroComparendo, Date fechaConsumo) throws CirculemosNegocioException {
        logger.debug(ComparendoEJB.class.getName().concat("consumirReservaOCN(String, Date)"));

        checkNotNull(fechaConsumo, "Filtro fecha consumo es vacio");
        checkNotNull(numeroComparendo, "Filtro numero de comparendo es vacio");

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT socn FROM SolicitudOrdenCompaNacio socn");
        jpql.append(" WHERE numeroComparendo = :numeroComparendo");

        Query query = em.createQuery(jpql.toString());

        // Numero comparendo
        query.setParameter("numeroComparendo", numeroComparendo);

        SolicitudOrdenCompaNacio solicitudOrdenCompaNacio = (SolicitudOrdenCompaNacio) query.getSingleResult();
        // Validacion reserva
        if (solicitudOrdenCompaNacio == null) {
            throw new CirculemosNegocioException(ConsumirReservaOCN.COM_002001);
        }
        // Validacion fecha de consumo
        if (solicitudOrdenCompaNacio.getFechaConsumo() != null) {
            throw new CirculemosNegocioException(ConsumirReservaOCN.COM_002002);
        }

        // Actualizacion
        solicitudOrdenCompaNacio.setFechaConsumo(fechaConsumo);
        em.merge(solicitudOrdenCompaNacio);
    }

    @Override
    public void actualizarEstadoComparendo(CambioEstadoComparendoDTO cambioEstadoComparendo) {
        logger.debug(ComparendoEJB.class.getName().concat("actualizarEstadoComparendo(CambioEstadoComparendoDTO)"));
        Comparendo comparendo = null;
        if (cambioEstadoComparendo != null) {
            if (!cambioEstadoComparendo.isActualizarToAnterior()) {
                ComparendoDTO comparendoDTO = consultarComparendo(cambioEstadoComparendo.getNumeroComparendo(),
                        cambioEstadoComparendo.getCodigoOrganismo());
                if (comparendoDTO != null) {
                    EstadoComparendo estado = new EstadoComparendo();
                    estado.setId(cambioEstadoComparendo.getEstadoComparendo().getValue());
                    comparendo = em.find(Comparendo.class, comparendoDTO.getCicomparendo());
                    comparendo.setEstadoComparendo(estado);
                }
            } else {
                ComparendoDTO comparendoDTO = consultarComparendoTrazabilidad(
                        cambioEstadoComparendo.getNumeroComparendo(), cambioEstadoComparendo.getCodigoOrganismo());
                if (comparendoDTO != null) {
                    List<TrazabilidadComparendoDTO> lsTrazabilidad = comparendoDTO.getTrazabilidadComparendoList();
                    Collections.sort(lsTrazabilidad, new Comparator<TrazabilidadComparendoDTO>() {
                        @Override
                        public int compare(TrazabilidadComparendoDTO obj1, TrazabilidadComparendoDTO obj2) {
                            return obj2.getFechaRegistro().compareTo(obj1.getFechaRegistro());
                        }
                    });

                    if (lsTrazabilidad != null) {
                        EstadoComparendoDTO ulEstadoComparendoDTO = lsTrazabilidad.get(0).getEstadoComparendo();
                        for (TrazabilidadComparendoDTO trazabilidadComparendo : lsTrazabilidad) {
                            if (!trazabilidadComparendo.getEstadoComparendo().getId()
                                    .equals(ulEstadoComparendoDTO.getId())) {

                                EstadoComparendo estado = EstadoComparendoHelper
                                        .toLevel1Entity(trazabilidadComparendo.getEstadoComparendo(), null);
                                comparendo = em.find(Comparendo.class, comparendoDTO.getCicomparendo());
                                comparendo.setEstadoComparendo(estado);
                                break;
                            }
                        }
                    }
                }
            }
            generarTrazabilidad(comparendo, cambioEstadoComparendo.getActividad().getValue(),
                    cambioEstadoComparendo.getFechaCambio());
            em.merge(comparendo);
        }
    }

    @Override
    public PersonaDTO consultarInfractorComparendo(Long idComparendo) {
        checkNotNull(idComparendo, "Se esperaba id del comparendo");

        PersonaDTO personaDTO = null;

        Map<String, Object> params = new HashMap<String, Object>();

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT cp.persona FROM ComparendoPersona cp ");
        sb.append(" WHERE cp.comparendo.cicomparendo = :idComparendo ");
        params.put("idComparendo", idComparendo);

        GenericDao<Persona> personaDao = new GenericDao<>(Persona.class, this.em);
        final List<Persona> result = safeList(personaDao.buildAndExecuteQuery(sb, params));
        if (!result.isEmpty()) {
            personaDTO = ProcesarPersonasHelper.toPersonaLevel1DTO(result.get(0));
        }
        return personaDTO;
    }

    @Override
    public List<ConsultaObligacionesDTO> consultarComparendosSimulacionFinanciacion(PersonaDTO consultaInfractorDTO,
            List<Integer> estadosComparendo) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.consultarObligacionComparendoInfractor(PersonaDTO, List<Integer>)");
        Map<String, Object> filtros = new HashMap<String, Object>();

        GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT c FROM Comparendo c");
        jpql.append(" LEFT JOIN c.personaList plInf");
        jpql.append(" LEFT JOIN c.comparendoCarteraList cc");
        jpql.append(" LEFT JOIN c.comparendoProcesos cp");
        jpql.append(" WHERE plInf.tipoIdentificacion.id = :idTipoDocInfractor ");
        jpql.append(" AND plInf.numeroIdentificacion = :numeroIdentificacionInfractor");
        if (estadosComparendo != null && !estadosComparendo.isEmpty()) {
            jpql.append(" AND c.estadoComparendo.id IN (:estComparendo)");
            filtros.put("estComparendo", estadosComparendo);
        }
        filtros.put("numeroIdentificacionInfractor", consultaInfractorDTO.getNumeroIdentificacion());
        filtros.put("idTipoDocInfractor", consultaInfractorDTO.getTipoIdentificacion().getId());

        List<Comparendo> resultadoConsulta = comparendoDao.buildAndExecuteQuery(jpql, filtros);
        List<ConsultaObligacionesDTO> listaResultadosInfraccion = new ArrayList<ConsultaObligacionesDTO>();
        if (!resultadoConsulta.isEmpty()) {
            for (Comparendo comparendo : resultadoConsulta) {
                ConsultaObligacionesDTO infraccionDTO = new ConsultaObligacionesDTO();

                // Verifica si existe un proceso activo de coactivo
                boolean insertar = true;
                infraccionDTO.setEstaEnCoactivo(Boolean.FALSE);
                for (ComparendoProceso comparendoProceso : comparendo.getComparendoProcesos()) {
                    ProcesoDTO proceso = iRFachadaProceso.consultarProceso(comparendoProceso.getIdProceso());
                    if (proceso != null && proceso.getFechaFin() == null
                            && proceso.getTipoProceso().getId().equals(EnumTipoProceso.COACTIVO.getValue())) {
                        infraccionDTO.setEstaEnCoactivo(Boolean.TRUE);
                    }

                    // No debe insertar si tiene algun proceso abierto de financiaciones o impugnaciones
                    if (proceso != null && proceso.getFechaFin() == null
                            && (proceso.getTipoProceso().getId()
                                    .equals(EnumTipoProceso.FINANCIACION_COMPARENDO.getValue())
                                    || proceso.getTipoProceso().getId()
                                            .equals(EnumTipoProceso.IMPUGNACION_COMPARENDO.getValue()))) {
                        insertar = false;
                    }
                }

                if (insertar) {

                    infraccionDTO.setCodigoInfraccion(comparendo.getInfraccion().getCodigo());
                    infraccionDTO.setNumeroObligacion(comparendo.getOrdenComparendoNacional().getNumeroComparendo());
                    infraccionDTO.setFechaObligacion(comparendo.getFechaInfraccion());
                    infraccionDTO.setFechaNotificacion(comparendo.getFechaNotificacion());
                    infraccionDTO.setMedioImposicion(comparendo.getMedioImposicion().getNombre());
                    ComparendoVehiculo comparendoVehiculo = comparendo.getComparendoVehiculo();
                    if (comparendoVehiculo != null) {
                        infraccionDTO.setPlaca(comparendoVehiculo.getPlacaVehiculo());
                    }
                    //
                    infraccionDTO.setPersona(consultaInfractorDTO);
                    if (comparendo.getComparendoCarteraList() != null
                            && !comparendo.getComparendoCarteraList().isEmpty()) {
                        ComparendoCartera comparendoCartera = comparendo.getComparendoCarteraList().get(0);
                        CarteraComparendoDTO carteraComparendoDTO = iCarteraComparendo
                                .consultarCartera(comparendoCartera.getIdCartera());
                        infraccionDTO.setTipoObligacion(carteraComparendoDTO.getNomTipoObligacion());
                        infraccionDTO.setIdTipoObligacion(carteraComparendoDTO.getIdTipoObligacion());
                        infraccionDTO.setIdTipoObligacion(carteraComparendoDTO.getIdTipoObligacion());
                        infraccionDTO.setSaldoObligacion(carteraComparendoDTO.getValorSaldoCapital() == null
                                ? BigDecimal.ZERO : carteraComparendoDTO.getValorSaldoCapital());
                        infraccionDTO.setValorRecargo(calcularRecargoObligacion(
                                comparendo.getOrdenComparendoNacional().getNumeroComparendo()));
                        infraccionDTO.setIdCartera(comparendoCartera.getIdCartera());
                        infraccionDTO.setValorCostaProcesal(carteraComparendoDTO.getValorSaldoCostasProcesales() == null
                                ? BigDecimal.ZERO : carteraComparendoDTO.getValorSaldoCostasProcesales());
                    } else {
                        infraccionDTO.setSaldoObligacion(BigDecimal.ZERO);
                        infraccionDTO.setValorRecargo(BigDecimal.ZERO);
                        infraccionDTO.setValorCostaProcesal(BigDecimal.ZERO);
                    }

                    if (infraccionDTO.getSaldoObligacion().compareTo(BigDecimal.ZERO) == 1) {
                        listaResultadosInfraccion.add(infraccionDTO);
                    }

                    //
                }
            }
        }
        return listaResultadosInfraccion;
    }

    /**
     * Calcula los intereses de una obligacion ejecutando un procedimiento almacenado en AXIS
     * 
     * @param numeroObligacion
     *            Numero de la obligacion a la cual se le van a calcular los intereses
     * @return valor del recargo de la obligacion
     * @author dixon.alvarez
     */
    private BigDecimal calcularRecargoObligacion(String numeroObligacion) {
        StringBuilder sql = new StringBuilder();
        sql.append("declare ");
        sql.append("@liquidacion   float ");
        sql.append("exec dbo.sp_LiquidacionAxis ");
        sql.append("@factura = :factura, ");
        sql.append("@liquidacion = @liquidacion OUTPUT ");
        sql.append("select @liquidacion");

        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("factura", numeroObligacion);

        Object resultado = query.getSingleResult();
        if (resultado == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf((Double) resultado);
    }

    @Override
    public List<ComparendoConsultaAnulacionDTO> consultarComparendoAnulacion(ConsultaComparendoAnulacionDTO filtros)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.consultarComparendoAnulacion(ConsultaComparendoAnulacionDTO)");

        if (filtros == null) {
            throw new CirculemosIllegalArgumentException("Argumentos insuficientes { filtros: null}");

        } else if (filtros.getCodigoOrganismo() == null || filtros.getFechaInicial() == null
                || filtros.getFechaFinal() == null || StringUtils.isEmpty(filtros.getDireccion())) {
            throw new CirculemosIllegalArgumentException(
                    "Argumentos insuficientes { filtros.codigoOrganismo:" + filtros.getCodigoOrganismo()
                            + ",filtros.fechaInicial:" + filtros.getFechaInicial() + ",filtros.fechaFinal:"
                            + filtros.getFechaFinal() + ",filtros.direccion:" + filtros.getDireccion() + "}");
        }

        if (filtros.getFechaInicial().after(UtilFecha.currentZeroTimeDate())) {
            throw new CirculemosNegocioException(AnularMultaTransito.COM_086001);
        }

        if (filtros.getFechaFinal().before(filtros.getFechaInicial())) {
            throw new CirculemosNegocioException(AnularMultaTransito.COM_086002);
        }

        ValorParametroDTO cantMaximoDiasConsultaAnulacion = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.NUM_MAX_DIAS_CONSULTA_ANULACION, filtros.getCodigoOrganismo(), true);

        Date fechaLimiteConsulta = fachadaAdminGeneral.sumarDias(filtros.getCodigoOrganismo(),
                filtros.getFechaInicial(), cantMaximoDiasConsultaAnulacion.getValorParamInt(), false);

        if (filtros.getFechaFinal().after(fechaLimiteConsulta)) {
            throw new CirculemosNegocioException(AnularMultaTransito.COM_086003);
        }

        List<ComparendoConsultaAnulacionDTO> comparendoConsultaAnulacionDTOList = new ArrayList<>();
        StringBuilder sql = new StringBuilder().append("SELECT ocn.numero_comparendo,").append(" c.cicomparendo,")
                .append(" inf.codigo_infraccion,").append(" c.fecha_infraccion,").append(" c.hora_infraccion,")
                .append(" c.fecha_notificacion,").append(" ec.codigo as est_comparendo,").append(" dir.complemento,")
                .append(" cv.placa_vehiculo,").append(" tip.nombre,").append(" cp.numero_identificacion,")
                .append(" CASE WHEN tip.id_tipo_identificacion=2 THEN pj.nombre_comercial ELSE cp.nombre1+' '+ ISNULL(cp.nombre2,'')+' '+cp.apellido1+' '+ISNULL(cp.apellido2,'') END,")
                .append(" c.fecha_registro,").append(" per.correo_electronico ").append(" FROM comparendo c")
                .append(" JOIN orden_comparendo_nacional ocn ON(c.cicomparendo=ocn.cicomparendo)")
                .append(" JOIN infraccion inf ON(inf.id_infraccion=c.id_infraccion)")
                .append(" JOIN estado_comparendo ec ON(ec.id_estado_comparendo=c.id_estado_comparendo)")
                .append(" JOIN comparendo_persona cp ON(c.cicomparendo=cp.cicomparendo)")
                .append(" JOIN tipo_identificacion_persona tip ON(tip.id_tipo_identificacion=cp.id_tipo_identificacion)")
                .append(" JOIN comparendo_cartera cc ON(cc.cicomparendo=c.cicomparendo)")
                .append(" JOIN persona per ON(per.id_persona=cp.id_persona)")
                .append(" LEFT JOIN comparendo_vehiculo cv ON(cv.id_comparendo_vehiculo=c.cicomparendo)")
                .append(" LEFT JOIN direccion dir ON(dir.id_direccion=c.id_direccion_comparendo)")
                .append(" LEFT JOIN persona_juridica pj ON(cp.id_persona=pj.id_persona_juridica)")
                .append(" WHERE ocn.codigo_organismo_transito=:codOrganismo")
                .append(" AND c.codigo_medio_imposicion=:medImposicion")
                .append(" AND ec.id_estado_comparendo IN :estComparendo")
                .append(" AND cp.codigo_tipo_persona_comparendo=:tipoPersona")
                .append(" AND CAST(c.fecha_infraccion AS DATETIME) + CAST(c.hora_infraccion AS DATETIME) BETWEEN CAST(:fechaInicio AS DATETIME) AND CAST(:fechaFin AS DATETIME)")
                .append(" AND dir.complemento  LIKE :complemento").append(" AND NOT EXISTS(")
                .append("SELECT cpro.id_proceso FROM comparendo_proceso cpro")
                .append(" JOIN proceso pro ON cpro.id_proceso=pro.id_proceso")
                .append(" WHERE pro.id_tipo_proceso IN :tipoProceso").append(" AND cpro.cicomparendo=c.cicomparendo)");

        Query query = em.createNativeQuery(sql.toString());

        query.setParameter("fechaInicio", filtros.getFechaInicial());
        query.setParameter("fechaFin", filtros.getFechaFinal());
        query.setParameter("codOrganismo", filtros.getCodigoOrganismo());
        query.setParameter("medImposicion", EnumMedioImposicion.FOTOMULTA.getPk());
        List<Integer> estadosComparendo = new ArrayList<>();
        estadosComparendo.add(EnumEstadoComparendo.REGISTRADO.getCodigo());
        estadosComparendo.add(EnumEstadoComparendo.VIGENTE.getCodigo());
        query.setParameter("estComparendo", estadosComparendo);
        query.setParameter("tipoPersona", EnumTipoPersonaComparendo.INFRACTOR.getCodigo());
        List<Integer> tipoProceso = new ArrayList<>();
        tipoProceso.add(EnumTipoProceso.IMPUGNACION_COMPARENDO.getValue());
        tipoProceso.add(EnumTipoProceso.FINANCIACION_COMPARENDO.getValue());
        tipoProceso.add(EnumTipoProceso.ANULACION_COMPARENDO.getValue());
        tipoProceso.add(EnumTipoProceso.COACTIVO.getValue());
        query.setParameter("tipoProceso", tipoProceso);
        if (StringUtils.isNotBlank(filtros.getDireccion())) {
            query.setParameter("complemento", "%" + filtros.getDireccion() + "%");
        }

        @SuppressWarnings("unchecked")
        List<Object[]> resultado = query.getResultList();

        for (Object[] comparendo : resultado) {
            ComparendoConsultaAnulacionDTO comparendoConsultaAnulacionDTO = new ComparendoConsultaAnulacionDTO();
            comparendoConsultaAnulacionDTO.setCicomparendo(((BigInteger) comparendo[1]).longValue());
            comparendoConsultaAnulacionDTO.setNumeroMulta((String) comparendo[0]);
            comparendoConsultaAnulacionDTO.setCodigoInfraccion((String) comparendo[2]);
            if (comparendo[3] != null) {
                comparendoConsultaAnulacionDTO
                        .setFechaImposicion(Utilidades.dateToStringFormatApp((Date) comparendo[3], false));
            }
            if (comparendo[4] != null) {
                comparendoConsultaAnulacionDTO
                        .setHoraImposicion(Utilidades.hourToStringFormatApp((Date) comparendo[4]));
            }
            if (comparendo[5] != null) {
                comparendoConsultaAnulacionDTO
                        .setFechaNotificacion(Utilidades.dateToStringFormatApp((Date) comparendo[5], false));
            }
            comparendoConsultaAnulacionDTO.setEstadoMulta((String) comparendo[6]);
            comparendoConsultaAnulacionDTO.setDireccionInfraccion((String) comparendo[7]);
            comparendoConsultaAnulacionDTO.setPlaca((String) comparendo[8]);
            comparendoConsultaAnulacionDTO.setTipoDocumentoInfractor((String) comparendo[9]);
            comparendoConsultaAnulacionDTO.setNumeroDocumentoInfractor((String) comparendo[10]);
            comparendoConsultaAnulacionDTO.setNombreInfractor((String) comparendo[11]);
            if (comparendo[12] != null) {
                comparendoConsultaAnulacionDTO
                        .setFechaRegistro(Utilidades.dateToStringFormatApp((Date) comparendo[12], false));
            }
            if (comparendo[13] != null) {
                comparendoConsultaAnulacionDTO.setCorreoElectronico((String) comparendo[13]);
            }
            comparendoConsultaAnulacionDTOList.add(comparendoConsultaAnulacionDTO);
        }

        return comparendoConsultaAnulacionDTOList;
    }

    @Override
    public void registrarAnulacion(AnulacionDTO anulacion, List<Long> comparendos) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.registrarAnulacion(AnulacionComparendoDTO, List<Long>)");

        // Validar que los datos ingresados cumplen la obligatoriedad, reglas y tipo de dato definidos
        validarAnularComparendo(anulacion, comparendos);

        // Crea proceso de anulacion
        RegistraProcesoDTO registra = new RegistraProcesoDTO();
        registra.setObservacion(EnumTipoProceso.ANULACION_COMPARENDO.name());
        registra.setTipoProceso(EnumTipoProceso.ANULACION_COMPARENDO);
        registra.setEstado(EnumEstadoProceso.ECUADOR_ANULACION_ANULADO);
        registra.setConsecutivo(EnumConsecutivo.NUMERO_ANULACION_ECUADOR);
        ProcesoDTO proceso = iRFachadaProceso.crearProceso(registra);

        // Crea la anulacion
        Anulacion anulacionComparendo = AnulacionHelper.toLevel0Entity(anulacion, null);
        anulacionComparendo.setProceso(ProcesoHelper.toLevel0Entity(proceso, null));
        em.persist(anulacionComparendo);

        // Consulta trazabilidad
        TrazabilidadProcesoDTO trazabilidad = new TrazabilidadProcesoDTO();
        trazabilidad.setProceso(proceso);
        List<TrazabilidadProcesoDTO> trazas = iRFachadaProceso.consultarTrazabilidad(trazabilidad);

        List<Persona> participantes = new ArrayList<Persona>();
        for (Long cicomparendo : comparendos) {
            participantes = procesoAnularComparendo(cicomparendo, trazas.get(0), proceso, false);
        }

        // Crea los participantes
        registrarParticipantesPorceso(participantes, proceso);

    }

    @Override
    public void registrarAnulacion(List<Long> comparendos) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.registrarAnulacion(AnulacionComparendoDTO, List<Long>)");

        // Crea proceso de anulacion
        RegistraProcesoDTO registra = new RegistraProcesoDTO();
        registra.setObservacion(EnumTipoProceso.ANULACION_COMPARENDO.name());
        registra.setTipoProceso(EnumTipoProceso.ANULACION_COMPARENDO);
        registra.setEstado(EnumEstadoProceso.ECUADOR_ANULACION_ANULADO);
        registra.setConsecutivo(EnumConsecutivo.NUMERO_ANULACION_ECUADOR);
        ProcesoDTO proceso = iRFachadaProceso.crearProceso(registra);

        // Consulta trazabilidad
        TrazabilidadProcesoDTO trazabilidad = new TrazabilidadProcesoDTO();
        trazabilidad.setProceso(proceso);
        List<TrazabilidadProcesoDTO> trazas = iRFachadaProceso.consultarTrazabilidad(trazabilidad);

        List<Persona> participantes = new ArrayList<Persona>();
        for (Long cicomparendo : comparendos) {
            participantes = procesoAnularComparendo(cicomparendo, trazas.get(0), proceso, true);
        }

        // Crea los participantes
        registrarParticipantesPorceso(participantes, proceso);
    }

    /**
     * 
     * @param anulacion
     */
    private void validarAnularComparendo(AnulacionDTO anulacion, List<Long> comparendos)
            throws CirculemosNegocioException {
        if (anulacion == null) {
            throw new CirculemosIllegalArgumentException("Argumentos insuficientes { anulacion: null}");

        } else if (anulacion.getCorreoSupervisor() == null || anulacion.getFechaCorreo() == null
                || anulacion.getJustificacionCorreo() == null || anulacion.getPrimerNombreFuncionario() == null
                || anulacion.getPrimerApellidoFuncionario() == null || comparendos == null) {
            throw new CirculemosIllegalArgumentException(
                    "Argumentos insuficientes { anulacion.correoSupervisor:" + anulacion.getCorreoSupervisor()
                            + ",anulacion.fechaCorreo:" + anulacion.getFechaCorreo() + ",anulacion.justificacionCorreo:"
                            + anulacion.getJustificacionCorreo() + ",anulacion.primerNombreFuncionario:"
                            + anulacion.getPrimerNombreFuncionario() + ",anulacion.primerApellidoFuncionario:"
                            + anulacion.getPrimerApellidoFuncionario() + ",anulacion.comparendos:" + comparendos + "}");
        } else if (comparendos.isEmpty()) {
            throw new CirculemosRuntimeException(
                    "Se necesita por lo menos un comparendo para realizar el proceso de Anulaci贸n");
        }

        Date fechaSistema = UtilFecha.buildCalendar().getTime();

        if (anulacion.getFechaCorreo().after(fechaSistema)) {
            throw new CirculemosNegocioException(AnularMultaTransito.COM_086005);
        }

    }

    /**
     * 
     */
    private List<Persona> procesoAnularComparendo(Long cicomparendo, TrazabilidadProcesoDTO trazabilidadProcesoDTO,
            ProcesoDTO procesoDTO, boolean coactivo) throws CirculemosNegocioException {
        final String NOMBRE_ARCHIVO_CORREO = "Notificaci髇 de Anulaci髇 de Citaci髇.pdf";
        OrganismoTransitoDTO organismoTransito = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));

        List<Persona> participantes = new ArrayList<Persona>();
        try {

            Long idDocumento = null;
            Comparendo comparendoConsulta = em.find(Comparendo.class, cicomparendo);
            Persona persona = null;
            PersonaJuridica personaJuridica = null;

            // Valida la existencia de la cartera
            boolean cartera = false;
            if (comparendoConsulta.getPersonaList() == null || comparendoConsulta.getPersonaList().isEmpty()
                    || comparendoConsulta.getComparendoCarteraList() == null
                    || comparendoConsulta.getComparendoCarteraList().isEmpty()) {
                cartera = true;
            }

            if (!cartera) {
                // Obtiene el infractor para envio del correo
                for (ComparendoPersona comparendoPersona : comparendoConsulta.getPersonaList()) {
                    if (comparendoPersona.getTipoPersonaComparendo().getCodigo()
                            .equals(EnumTipoPersonaComparendo.INFRACTOR.getValue())) {
                        if (comparendoPersona.getPersona().getPersonaJuridica() != null) {
                            personaJuridica = comparendoPersona.getPersona().getPersonaJuridica();
                        } else {
                            persona = comparendoPersona.getPersona();
                        }
                    }
                }

                // Valida la existencia del infractor
                if (persona == null && personaJuridica == null) {
                    throw new CirculemosNegocioException(AnularMultaTransito.COM_086004);

                }

                if (!participantes.contains(persona)) {
                    participantes.add(persona);
                }
                // Crea ComparendoProceso
                ComparendoProceso comparendoProceso = new ComparendoProceso();
                comparendoProceso.setComparendo(comparendoConsulta);
                comparendoProceso.setIdProceso(procesoDTO.getId());
                em.persist(comparendoProceso);

                // Cambia de estado el comparendo
                CambioEstadoComparendoDTO cambio = new CambioEstadoComparendoDTO();
                cambio.setNumeroComparendo(comparendoConsulta.getOrdenComparendoNacional().getNumeroComparendo());
                cambio.setCodigoOrganismo(
                        comparendoConsulta.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
                cambio.setEstadoComparendo(EnumEstadoComparendo.ANULADO);
                cambio.setActividad(EnumActividad.CITACION_ANULADA);
                cambio.setFechaCambio(new Date());
                actualizarEstadoComparendo(cambio);

                em.flush();

                // Anula la cartera
                iCarteraComparendo.anularCartera(comparendoConsulta.getComparendoCarteraList().get(0).getIdCartera());

                if (comparendoConsulta.getFechaNotificacion() != null) {

                    // Generacion de documento
                    // este sisteme es provicional esperar plntilla de anulacion de coactivo
                    if (!coactivo) {
                        GeneraDocumentoDTO notificacionAnulacionDTO = new GeneraDocumentoDTO();
                        notificacionAnulacionDTO.setFechaGeneracion(Calendar.getInstance().getTime());
                        Object[] valoresParametros = { procesoDTO.getId(), cicomparendo };
                        notificacionAnulacionDTO.setValoresParametros(valoresParametros);
                        notificacionAnulacionDTO
                                .setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.NOTIFICACION_ANULACION);
                        idDocumento = iRDocumentosCirculemos.generarDocumento(notificacionAnulacionDTO);

                        // Guarda el documento generado
                        DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
                        documentoProceso.setNumeroDocumento(idDocumento);
                        documentoProceso.setTrazabilidadProceso(trazabilidadProcesoDTO);
                        documentoProceso.setIdComparendoProceso(comparendoProceso.getId());
                        TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
                        tipoDocumento.setId(EnumTipoDocumentoProceso.ANULACION_NOTIFICACION_ANULACION.getValue());
                        documentoProceso.setTipoDocumento(tipoDocumento);
                        documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);

                    }
                    if (comparendoConsulta.getPersonaList() != null) {
                        ComparendoPersona comparendoPersona = null;
                        for (ComparendoPersona comparendoPersonaDB : comparendoConsulta.getPersonaList()) {
                            if (comparendoPersonaDB.getTipoPersonaComparendo().getCodigo()
                                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                                comparendoPersona = comparendoPersonaDB;
                                break;
                            }
                        }

                        CorreoPersonaDTO correoPersonaDTO = new CorreoPersonaDTO();
                        correoPersonaDTO.setPersona(PersonaHelper.toLevel0DTO(comparendoPersona.getPersona()));
                        List<CorreoPersonaDTO> correoPersonaDTOs = fachadaAdminNegocioEJB
                                .consultarCorreoPersona(correoPersonaDTO);

                        // Construye el correo
                        ArrayList<ArchivoTransportableDTO> adjuntos = new ArrayList<ArchivoTransportableDTO>();
                        if (correoPersonaDTOs != null && !correoPersonaDTOs.isEmpty()) {
                            if (!coactivo) {
                                List<Long> documentos = new ArrayList<>();
                                documentos.add(idDocumento);
                                byte[] archivo = iRDocumentosCirculemos.consultarDocumentosPDF(documentos);
                                ArchivoTransportableDTO adjunto = new ArchivoTransportableDTO();

                                adjunto.setContenido(archivo);
                                adjunto.setNombre(NOMBRE_ARCHIVO_CORREO);
                                adjuntos.add(adjunto);
                            }

                            List<String> correos = new ArrayList<>();
                            for (CorreoPersonaDTO correoPersoDTO : correoPersonaDTOs) {
                                correos.add(correoPersoDTO.getCorreoElectronico());
                            }

                            Map<String, Object> variables = new HashMap<>();
                            variables.put("ciudad", organismoTransito.getMunicipio().getNombre());
                            if (personaJuridica != null) {
                                variables.put("nombre", personaJuridica.getNombreComercial().toUpperCase());
                            } else {
                                variables.put("nombre",
                                        (persona.getNombre1() + " "
                                                + ((persona.getNombre2() == null) ? " " : persona.getNombre2() + " ")
                                                + persona.getApellido1() + " "
                                                + ((persona.getApellido2() == null) ? "" : persona.getApellido2()))
                                                        .toUpperCase());
                            }
                            variables.put("fecha_actual", formateador.format(Calendar.getInstance().getTime()));
                            variables.put("consecutivo", procesoDTO.getNumeroProceso());
                            variables.put("numero_multa", comparendoConsulta.getIdFacturaAxis());
                            variables.put("fecha_imposicion",
                                    formateador.format(comparendoConsulta.getFechaInfraccion()));
                            if (comparendoConsulta.getComparendoVehiculo() != null) {
                                variables.put("placa",
                                        (comparendoConsulta.getComparendoVehiculo().getPlacaVehiculo() == null ? " "
                                                : comparendoConsulta.getComparendoVehiculo().getPlacaVehiculo()));
                            }

                            String[] correosArray = new String[correos.size()];
                            correosArray = correos.toArray(correosArray);

                            // Envia correo
                            LogEnvioCorreoDTO log = iRCirculemosMailSender.enviarCorreo(
                                    organismoTransito.getCodigoOrganismo(), EnumTipoCorreo.ENVIO_ANULACION_MULTA,
                                    correosArray, variables, adjuntos);
                            // Actualizacion de auditoria de correo
                            log.setTablaSolicitud("trazabilidad_proceso");
                            log.setIdTablaSolicitud(trazabilidadProcesoDTO.getId());
                            em.merge(LogEnvioCorreoHelper.toLevel1Entity(log, null));

                        }

                    }
                }
            }
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(AnularMultaTransito.COM_086006);
        }

        return participantes;
    }

    /**
     * 
     * @param personas
     */
    private void registrarParticipantesPorceso(List<Persona> personas, ProcesoDTO procesoDTO) {
        ParticipanteProceso participante;
        for (Persona persona : personas) {

            participante = new ParticipanteProceso();
            participante.setPersona(persona);
            participante
                    .setTipoParticipante(em.find(TipoParticipante.class, EnumTipoParticipante.INFRACTOR.getValue()));
            participante.setProceso(ProcesoHelper.toLevel0Entity(procesoDTO, null));
            em.persist(participante);
        }
    }

    @Override
    public void cambiarNumeroFactura(Long cicomparendo, Long numeroFacturaNuevo) throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.cambiarNumeroFactura(Long, Long)");

        // Actualiza numero de factura
        Comparendo comparendo = em.find(Comparendo.class, cicomparendo);
        String numeroComparendoAntiguo = comparendo.getOrdenComparendoNacional().getNumeroComparendo();
        comparendo.setIdFacturaAxis(numeroFacturaNuevo);
        comparendo.setFechaModificacion(UtilFecha.buildCalendar().getTime());
        em.merge(comparendo);

        // Actualiza numero de comparendo
        comparendo.getOrdenComparendoNacional().setNumeroComparendo(String.valueOf(numeroFacturaNuevo));
        em.merge(comparendo.getOrdenComparendoNacional());

        // Cambia el nombre de la cartera asociada al comparendo
        for (ComparendoCartera comparendoCartera : comparendo.getComparendoCarteraList()) {
            iCarteraComparendo.cambiarNombreCartera(comparendoCartera.getIdCartera(),
                    String.valueOf(numeroFacturaNuevo));
            break;
        }

        // Inserta registro en tabla de cambio de numero de factura
        HistoricoNumeroComparendo historico = new HistoricoNumeroComparendo();
        historico.setComparendo(comparendo);
        historico.setFechaCambio(UtilFecha.buildCalendar().getTime());
        historico.setNumeroComparendoAntiguo(numeroComparendoAntiguo);
        historico.setNumeroComparendoNuevo(String.valueOf(numeroFacturaNuevo));
        em.persist(historico);
    }

    @Override
    public HistoricoNumeroComparendoDTO consultarHistoricoNumeroComparendo(
            HistoricoNumeroComparendoDTO historicoNumeroComparendoDTO) {
        logger.debug("ComparendoEJB.consultarHistoricoNumeroComparendo(HistoricoNumeroComparendoDTO)");
        HistoricoNumeroComparendoDTO historico = null;
        Map<String, Object> filtros = new HashMap<>();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT h FROM HistoricoNumeroComparendo h ");
        jpql.append("JOIN h.comparendo c ");
        jpql.append("WHERE 1=1 ");

        if (historicoNumeroComparendoDTO != null) {
            if (historicoNumeroComparendoDTO.getComparendo() != null
                    && historicoNumeroComparendoDTO.getComparendo().getCicomparendo() != null) {
                jpql.append("AND c.cicomparendo = :cicomparendo ");
                filtros.put("cicomparendo", historicoNumeroComparendoDTO.getComparendo().getCicomparendo());
            }
            if (StringUtils.isNotBlank(historicoNumeroComparendoDTO.getNumeroComparendoAntiguo())) {
                jpql.append("AND h.numeroComparendoAntiguo = :numeroAntiguo ");
                filtros.put("numeroAntiguo", historicoNumeroComparendoDTO.getNumeroComparendoAntiguo());
            }
            if (StringUtils.isNotBlank(historicoNumeroComparendoDTO.getNumeroComparendoNuevo())) {
                jpql.append("AND h.numeroComparendoNuevo = :numeroNuevo ");
                filtros.put("numeroNuevo", historicoNumeroComparendoDTO.getNumeroComparendoNuevo());
            }
        }
        jpql.append("ORDER BY h.fechaCambio DESC ");

        GenericDao<HistoricoNumeroComparendo> historicoDao = new GenericDao<HistoricoNumeroComparendo>(
                HistoricoNumeroComparendo.class, em);
        List<HistoricoNumeroComparendo> resultado = historicoDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultado.isEmpty()) {
            historico = HistoricoNumeroComparendoHelper.toLevel1DTO(resultado.get(0));
        }
        return historico;
    }

    @Override
    public List<ComparendoAgenteInconsistenteDTO> consultarComparendosIncosistenteAgente(
            EnumTipoInconsistenciaAgente inconsistencia, Date fechaInicialImposicion, Date fechaFinalImposicion)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.consultarComparendosIncosistenteAgente(Integer)");
        List<ComparendoAgenteInconsistenteDTO> comparendos = new ArrayList<>();

        if (inconsistencia == null) {
            throw new CirculemosNegocioException(ActualizarAgenteComparendo.COM_091001);
        }

        OrganismoTransitoDTO organismoTransito = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();

        ValorParametroDTO cantMaximoDiasConsultaAnulacion = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.DIAS_CONSULTA_COMPARENDOS_SIN_AGENTE_VALIDO, organismoTransito.getCodigoOrganismo(),
                true);

        Date fechaLimiteConsulta = fachadaAdminGeneral.sumarDias(organismoTransito.getCodigoOrganismo(),
                fechaInicialImposicion, cantMaximoDiasConsultaAnulacion.getValorParamInt(), false);

        if (fechaFinalImposicion.after(fechaLimiteConsulta)) {
            throw new CirculemosNegocioException(ActualizarAgenteComparendo.COM_091008);
        }

        Map<String, Object> filtros = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        switch (inconsistencia) {
        case MULTA_NO_TIENE_AGENTE:
            sql.append(
                    "SELECT c.cicomparendo, c.id_factura_axis, c.fecha_infraccion, c.hora_infraccion, c.placa_agente_transito, t.nombre inconsistencia FROM comparendo c, tipo_inconsistencia_agente t where c.placa_agente_transito is null AND t.codigo = :codigoIncosistencia AND c.codigo_medio_imposicion=:medImposicion");
            filtros.put("codigoIncosistencia", EnumTipoInconsistenciaAgente.MULTA_NO_TIENE_AGENTE.getValue());
            break;
        case AGENTE_NO_REGISTRADO:
            sql.append(
                    "SELECT c.cicomparendo, c.id_factura_axis, c.fecha_infraccion, c.hora_infraccion, c.placa_agente_transito, t.nombre inconsistencia FROM comparendo c, tipo_inconsistencia_agente t WHERE c.placa_agente_transito IS NOT NULL AND c.placa_agente_transito NOT IN (SELECT a.placa_agente_transito FROM agente a) AND t.codigo = :codigoIncosistencia AND c.codigo_medio_imposicion=:medImposicion");
            filtros.put("codigoIncosistencia", EnumTipoInconsistenciaAgente.AGENTE_NO_REGISTRADO.getValue());
            break;
        case AGENTE_NO_VIGENTE:
            sql.append(
                    "SELECT c.cicomparendo, c.id_factura_axis, c.fecha_infraccion, c.hora_infraccion, c.placa_agente_transito, t.nombre inconsistencia FROM comparendo c, tipo_inconsistencia_agente t WHERE c.placa_agente_transito IS NOT NULL AND c.placa_agente_transito IN (SELECT a.placa_agente_transito FROM agente a WHERE a.estado =:estadoAgente) AND t.codigo = :codigoIncosistencia AND c.codigo_medio_imposicion=:medImposicion");
            filtros.put("estadoAgente", Boolean.FALSE);
            filtros.put("codigoIncosistencia", EnumTipoInconsistenciaAgente.AGENTE_NO_VIGENTE.getValue());
            break;
        default:
            throw new CirculemosNegocioException(ActualizarAgenteComparendo.COM_091002);
        }

        sql.append(
                " AND CAST(c.fecha_infraccion AS DATETIME) + CAST(c.hora_infraccion AS DATETIME) BETWEEN CAST(:fechaInicio AS DATETIME) AND CAST(:fechaFin AS DATETIME) ");

        filtros.put("fechaInicio", fechaInicialImposicion);
        filtros.put("fechaFin", fechaFinalImposicion);
        filtros.put("medImposicion", EnumMedioImposicion.FOTOMULTA.getPk());
        sql.append(" ORDER BY cicomparendo ");

        Query query = em.createNativeQuery(sql.toString());
        if (!filtros.isEmpty()) {
            for (Map.Entry<String, Object> param : filtros.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        @SuppressWarnings("unchecked")
        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {
                ComparendoAgenteInconsistenteDTO comparendo = new ComparendoAgenteInconsistenteDTO();
                comparendo.setCicomparendo(((BigInteger) objects2[0]).longValue());
                comparendo.setIdFacturaAxis(((BigInteger) objects2[1]).longValue());
                comparendo.setFechaInfraccion((Date) objects2[2]);
                comparendo.setHoraInfraccion((Date) objects2[3]);
                comparendo.setPlacaAgenteTransito((objects2[4] == null ? null : objects2[4].toString()));
                comparendo.setInconsistencia(objects2[5].toString());
                comparendos.add(comparendo);
            }
        }

        return comparendos;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ArrayList<Integer> actualizarAgenteComparendoMasivo(List<ComparendoDTO> comparendos, AgenteDTO agente)
            throws CirculemosNegocioException {
        logger.debug("ComparendoEJB.actualizarAgenteComparendoMasivo(List<ComparendoDTO>, AgenteDTO)");
        ArrayList<Integer> resultados = new ArrayList<Integer>();
        if (comparendos == null || comparendos.size() <= 0) {
            throw new CirculemosNegocioException(ActualizarAgenteComparendo.COM_091003);
        }

        if (agente == null || agente.getId() == null) {
            throw new CirculemosNegocioException(ActualizarAgenteComparendo.COM_091004);
        }

        ComparendoAgenteDTO comparendoAgente = new ComparendoAgenteDTO();
        comparendoAgente.setAgente(agente);
        comparendoAgente.setTipoIdentificacionPersona(agente.getPersona().getTipoIdentificacion());
        comparendoAgente.setNumeroIdentificacion(agente.getPersona().getNumeroIdentificacion());
        comparendoAgente.setPlaca(agente.getPlaca());
        comparendoAgente.setApellido1(agente.getPersona().getApellido1());
        comparendoAgente.setApellido2(agente.getPersona().getApellido2());
        comparendoAgente.setNombre1(agente.getPersona().getNombre1());
        comparendoAgente.setNombre2(agente.getPersona().getNombre2());

        Integer cantRegistros = 0;
        Integer cantRegistrosNoValidos = 0;

        for (ComparendoDTO comparendoDto : comparendos) {

            if (comparendoValidoAgente(comparendoDto, agente)) {
                comparendoAgente.setComparendo(comparendoDto);
                iLComparendo.actualizaciarAgenteComparendo(comparendoAgente);
                cantRegistros++;
            } else {
                cantRegistrosNoValidos++;
            }
        }
        resultados.add(cantRegistros);
        resultados.add(cantRegistrosNoValidos);

        return resultados;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizaciarAgenteComparendo(ComparendoAgenteDTO comparendoAgente) {
        logger.debug("ComparendoEJB.actualizarAgenteComparendo(ComparendoAgenteDTO)");

        ComparendoAgente comparendoAgenteEntity = ComparendoAgenteHelper.toLevel1Entity(comparendoAgente, null);
        em.persist(comparendoAgenteEntity);

        Query query = em.createQuery(
                "UPDATE Comparendo SET placa_agente_transito = :placaAgente WHERE cicomparendo = :ciComparendo");
        query.setParameter("placaAgente", comparendoAgente.getPlaca());
        query.setParameter("ciComparendo", comparendoAgente.getComparendo().getCicomparendo());
        query.executeUpdate();
    }

    @Override
    public boolean comparendoValidoAgente(ComparendoDTO comparendoDto, AgenteDTO agente) {
        boolean valido = false;

        Date fechaInicio = agente.getFechaInicioVigencia();
        fechaInicio = UtilFecha.sumarDias(fechaInicio, -1);

        if (agente.getFechaFinVigencia() == null) {
            if (comparendoDto.getFechaInfraccion().after(fechaInicio)) {
                valido = true;
            } else {
                valido = false;
            }
        } else {
            Date fechaFin = agente.getFechaFinVigencia();
            fechaFin = UtilFecha.sumarDias(fechaFin, 1);
            if (comparendoDto.getFechaInfraccion().after(fechaInicio)
                    && comparendoDto.getFechaInfraccion().before(fechaFin)) {
                valido = true;
            } else {
                valido = false;
            }
        }
        return valido;
    }

    @Override
    public void actualizarFechaNotificacion(Long ciComparendo, int codigoOrganismo, Date fechaNotificacion) {
        logger.debug(ComparendoEJB.class.getName().concat("actualizarFechaNotificacion(Long, int, Date)"));
        Comparendo comparendo = null;
        if (ciComparendo != null && fechaNotificacion != null) {
            comparendo = em.find(Comparendo.class, ciComparendo);
            comparendo.setFechaNotificacion(fechaNotificacion);
            em.merge(comparendo);
        }
    }

}