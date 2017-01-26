package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesCargaArchivos;
import co.com.datatools.c2.constantes.ConstantesDocumentosC2;
import co.com.datatools.c2.dto.AprobacionPrecoactivoDTO;
import co.com.datatools.c2.dto.CargueCoactivoDTO;
import co.com.datatools.c2.dto.CargueCourierDTO;
import co.com.datatools.c2.dto.CargueNotificaCoactivoResulDTO;
import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.CoactivoOficioBienDTO;
import co.com.datatools.c2.dto.CondicionCoactivoDTO;
import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.dto.ConfiguracionEntidadDTO;
import co.com.datatools.c2.dto.ConsultaSeguimientoCoactivoDTO;
import co.com.datatools.c2.dto.DetalleCargueCourierDTO;
import co.com.datatools.c2.dto.DetalleCargueCourierErrorDTO;
import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.ErrorGeneraCoactivoDTO;
import co.com.datatools.c2.dto.EstadoPrecoactivoDTO;
import co.com.datatools.c2.dto.FiltroCoactivoDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.ObligacionCoactivoDTO;
import co.com.datatools.c2.dto.OficioBienDTO;
import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.dto.PrecoactivoDTO;
import co.com.datatools.c2.dto.PrecoactivoErrorDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RadicarExcepcionDTO;
import co.com.datatools.c2.dto.RegistraProcesoDTO;
import co.com.datatools.c2.dto.RegistroArchivoExcepcionDTO;
import co.com.datatools.c2.dto.RegistroRadicarExcepcionDTO;
import co.com.datatools.c2.dto.RespuestaCoactivoDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaSeguimientoDTO;
import co.com.datatools.c2.dto.RespuestaTrazabilidadDTO;
import co.com.datatools.c2.dto.SolicitudBienEntidadDTO;
import co.com.datatools.c2.dto.SolicitudOficioBienDTO;
import co.com.datatools.c2.dto.SolicitudOficioCoactivoDTO;
import co.com.datatools.c2.dto.TipoDocumentoProcesoDTO;
import co.com.datatools.c2.dto.TipoParticipanteDTO;
import co.com.datatools.c2.dto.TipoProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.dto.ValidacionPrecoactivoDTO;
import co.com.datatools.c2.dto.ValorCondicionCoactivoDTO;
import co.com.datatools.c2.dto.VariableCondicionCoacDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.cargue.EstadoCargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.TipoDocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.ConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.MovimientosCarteraDTO;
import co.com.datatools.c2.dto.cartera.TipoConceptoCarteraDTO;
import co.com.datatools.c2.dto.cartera.TipoObligacionDTO;
import co.com.datatools.c2.dto.cartera.TipoSaldoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.common.cartera.MovimientoCarteraDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoProcesoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comun.EntidadOficioDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.entidades.ArchivoExcepcion;
import co.com.datatools.c2.entidades.CargueCoactivo;
import co.com.datatools.c2.entidades.CargueCourier;
import co.com.datatools.c2.entidades.Coactivo;
import co.com.datatools.c2.entidades.CoactivoOficioBien;
import co.com.datatools.c2.entidades.ComparendoProceso;
import co.com.datatools.c2.entidades.DetalleCargueCourier;
import co.com.datatools.c2.entidades.DetalleCargueCourierError;
import co.com.datatools.c2.entidades.ErrorCargueCourier;
import co.com.datatools.c2.entidades.EstadoPrecoactivo;
import co.com.datatools.c2.entidades.ObligacionCoactivo;
import co.com.datatools.c2.entidades.ObligacionFinanciacion;
import co.com.datatools.c2.entidades.OficioBien;
import co.com.datatools.c2.entidades.Precoactivo;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.entidades.RadicarExcepcion;
import co.com.datatools.c2.entidades.SolicitudBienEntidad;
import co.com.datatools.c2.entidades.SolicitudOficioBien;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumCampoObligacionCoactivo;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;
import co.com.datatools.c2.enumeracion.EnumEstadoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeracion.EnumTipoDocumentoResulCargue;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeraciones.EnumActividadCartera;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumErrorCargueCourier;
import co.com.datatools.c2.enumeraciones.EnumErrorGeneraCoactivo;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoObligacion;
import co.com.datatools.c2.enumeraciones.EnumEstadoPrecoactivo;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoConceptoCartera;
import co.com.datatools.c2.enumeraciones.EnumTipoCostaProcesal;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoGenerado;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoObligacion;
import co.com.datatools.c2.enumeraciones.EnumTipoOficio;
import co.com.datatools.c2.enumeraciones.EnumTipoParticipante;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoSaldo;
import co.com.datatools.c2.enumeraciones.EnumVariableCondicionCoactivo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorCoactivo;
import co.com.datatools.c2.negocio.error.ErrorCoactivo.CargueAnalisisCoactivo;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.fachada.IRFachadaCartera;
import co.com.datatools.c2.negocio.fachada.IRFachadaComparendo;
import co.com.datatools.c2.negocio.fachada.IRFachadaFinanciacion;
import co.com.datatools.c2.negocio.fachada.IRFachadaProceso;
import co.com.datatools.c2.negocio.helpers.ArchivoExcepcionHelper;
import co.com.datatools.c2.negocio.helpers.CargueCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.CargueCourierHelper;
import co.com.datatools.c2.negocio.helpers.CoactivoHelper;
import co.com.datatools.c2.negocio.helpers.CoactivoOficioBienHelper;
import co.com.datatools.c2.negocio.helpers.ConfiguracionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.DetalleCargueCourierErrorHelper;
import co.com.datatools.c2.negocio.helpers.DetalleCargueCourierHelper;
import co.com.datatools.c2.negocio.helpers.ErrorCargueCourierHelper;
import co.com.datatools.c2.negocio.helpers.ObligacionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.OficioBienHelper;
import co.com.datatools.c2.negocio.helpers.ParticipanteProcesoHelper;
import co.com.datatools.c2.negocio.helpers.PrecoactivoErrorHelper;
import co.com.datatools.c2.negocio.helpers.PrecoactivoHelper;
import co.com.datatools.c2.negocio.helpers.ProcesarPersonasHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.RadicarExcepcionHelper;
import co.com.datatools.c2.negocio.helpers.SolicitudBienEntidadHelper;
import co.com.datatools.c2.negocio.helpers.SolicitudOficioBienHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.extend.CoactivoHelperExtend;
import co.com.datatools.c2.negocio.helpers.personas.FuncionarioHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.DireccionPersonaHelper;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionCoactivo;
import co.com.datatools.c2.negocio.interfaces.ILCoactivo;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRClienteWSAXIS;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.negocio.opciones.GestorArchivosOpciones;
import co.com.datatools.c2.negocio.opciones.OpcionGestorFileSystem;
import co.com.datatools.c2.negocio.opciones.ProcesadorArchivoFileSystem;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

@Stateless(mappedName = "CoactivoEJB")
@LocalBean
public class CoactivoEJB implements IRCoactivo, ILCoactivo {

    private static final Logger logger = Logger.getLogger(CoactivoEJB.class);

    private static final String RESPONSABLE_SOGIT = "SOGIT";

    private static final String NOMBRE_ARCHIVO_NOTIFICACION = "NotificacionCoactivo_%s.pdf";

    private static final String RUTA_NOTIFICACIONES = "/c2/coactivo/notificaciones/%s/%s";

    private static String CSV_SEPARADOR = ";";
    private static String FORMATO_FECHA_ARCHIVO = "MM-dd-yyyy_HH-mm-ss";
    private static String NOMBRE_ARCHIVO_NOTIFICACIONES = "NotificacionesCoactivo";
    private static String EXTENSION_CSV = ".csv";
    private static String NOMBRE_ARCHIVO_INCONSISTENCIAS = ".csv";
    private static String LINE_SEPARATOR_PROP = "line.separator";
    private static String COLUMNA_INCONSISTENCIAS = "Inconsistencias";
    private static String NOTIFICACION_SI = "SI";
    private static String NOTIFICACION_NO = "NO";
    private static String SEPARADOR_INCONSISTENCIAS = " - ";

    @EJB
    private IRFachadaProceso iRFachadaProceso;
    @EJB
    private ILSeguridadCirculemos seguridadCirculemosEJB;
    @EJB
    private IRFachadaComparendo iRFachadaComparendo;
    @EJB
    private IRFachadaFinanciacion iRFachadaFinanciacion;
    @EJB
    private IRFachadaAdminGeneral iFachadaAdminGeneral;
    @EJB
    private ILCoactivo iLCoactivo;
    @EJB
    private ILAdministracionCoactivo iLAdministracionCoactivo;
    @EJB
    private IRFachadaAdminNegocio iRFachadaAdminNegocio;
    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;
    @EJB
    private IRClienteWSAXIS iRClienteWSAXIS;
    @EJB
    private IRFachadaCartera iRFachadaCartera;
    @EJB
    private IRFachadaConfiguracion iFachadaConfiguracion;
    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;
    @EJB
    private IRRepositorioArchivo irRepositorioArchivo;
    @EJB
    private IRAdministracion iRAdministracion;

    @PersistenceContext(name = "Circulemos2JPA")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void registrarCoactivo(List<ObligacionCoactivoDTO> obligaciones,
            ConfiguracionCoactivoDTO configuracionCoactivo,
            Map<EnumVariableCondicionCoactivo, List<String>> valoresConfiguracion) throws CirculemosNegocioException {
        logger.debug("CoactivoEJB::registrarCoactivo(List<ObligacionCoactivoDTO>)");

        List<ObligacionCoactivoDTO> obligacionesValidadas = new ArrayList<ObligacionCoactivoDTO>();

        // Verifica si se enviaron los valores de configuracion de coactivo
        if (valoresConfiguracion == null) {

            // Consulta la configuracion
            List<ConfiguracionCoactivoDTO> pConfiguracionCoactivoDTO = iLAdministracionCoactivo
                    .consultarConfiguracionCoactivo(null, true);
            if (pConfiguracionCoactivoDTO != null && !pConfiguracionCoactivoDTO.isEmpty()) {
                configuracionCoactivo = pConfiguracionCoactivoDTO.get(0);

                // Obtiene valores de la configuracion
                valoresConfiguracion = obtenerValoresConfiguracion(configuracionCoactivo);

            } else {
                throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002006);
            }
        }
        if (valoresConfiguracion == null || valoresConfiguracion.isEmpty()) {
            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002007);
        }

        // Consulta si es agrupado
        List<String> valor = valoresConfiguracion.get(EnumVariableCondicionCoactivo.AGRUPAR_OBLIGACIONES);
        if (valor == null || valor.isEmpty()) {
            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002007);
        }
        Boolean coactivoAgrupado = Boolean.valueOf(valor.get(0));

        // Calcula metodo de costas procesales
        // Tipo de costa
        valor = valoresConfiguracion.get(EnumVariableCondicionCoactivo.TIPO_COSTA_PROCESAL);
        if (valor == null || valor.isEmpty()) {
            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002007);
        }
        Integer tipoCosta = Integer.valueOf(valor.get(0));

        // Valor de costa
        valor = valoresConfiguracion.get(EnumVariableCondicionCoactivo.VALOR_TIPO_COSTA_PROCESAL);
        if (valor == null || valor.isEmpty()) {
            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002007);
        }
        BigDecimal valorAbsolutoCP = null;
        BigDecimal porcentajeCP = null;
        if (tipoCosta == EnumTipoCostaProcesal.PROCENTAJE_DEUDA.getValue()) {
            porcentajeCP = new BigDecimal(valor.get(0));
        } else if (tipoCosta == EnumTipoCostaProcesal.VALOR_ABSOLUTO.getValue()) {
            valorAbsolutoCP = new BigDecimal(valor.get(0));
        }

        List<PrecoactivoDTO> precoactivoErrores = new ArrayList<>();
        // Valida si se calcula el coactivo agrupado
        if (coactivoAgrupado) {
            // Valida la configuracion contra las obligaciones agrupadas
            obligacionesValidadas = validarObligacionesCoactivo(obligaciones, configuracionCoactivo,
                    valoresConfiguracion, precoactivoErrores);

            if (obligacionesValidadas != null && !obligacionesValidadas.isEmpty()) {
                // Calcula costas procesales por obligacion
                obligacionesValidadas = calcularCostasProcesales(obligacionesValidadas, valorAbsolutoCP, porcentajeCP);

                // Genera el coactivo y dependencias
                generaProcesoCoactivo(obligacionesValidadas, configuracionCoactivo);
            }

        } else {
            // Valida la configuracion contra las obligaciones no agrupadas
            List<ObligacionCoactivoDTO> tmplistObligaciones;
            for (ObligacionCoactivoDTO obligacion : obligaciones) {
                tmplistObligaciones = new ArrayList<ObligacionCoactivoDTO>();
                tmplistObligaciones.add(obligacion);
                obligacionesValidadas = validarObligacionesCoactivo(tmplistObligaciones, configuracionCoactivo,
                        valoresConfiguracion, precoactivoErrores);

                if (obligacionesValidadas != null && !obligacionesValidadas.isEmpty()) {
                    // Calcula costas procesales por obligacion
                    obligacionesValidadas = calcularCostasProcesales(obligacionesValidadas, valorAbsolutoCP,
                            porcentajeCP);

                    // Genera el coactivo y dependencias
                    generaProcesoCoactivo(obligacionesValidadas, configuracionCoactivo);
                }
            }
        }

        /**
         * @author giovanni.velandia
         */
        /*
         * Generar valor de Gastos administrativos: valor de acuerdo al parámetro “Generar gastos administrativos” en la “HU_CIR20_DAT_COAC_001-
         * Administrar las condiciones generales de cobro coactivo”.
         */
        // Consulta si es agrupado
        // valor = valoresConfiguracion.get(EnumVariableCondicionCoactivo.GENERAR_GASTOS_ADM);
        // if (valor == null || valor.isEmpty()) {
        // throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002007);
        // }
        // Boolean coactivoAgrupado = Boolean.valueOf(valor.get(0));

        // Guarda errores del precoactivo
        if (!precoactivoErrores.isEmpty()) {
            for (PrecoactivoDTO precoactivoDTO : precoactivoErrores) {
                for (PrecoactivoErrorDTO error : precoactivoDTO.getPrecoactivoErrors()) {
                    error.setPrecoactivo(precoactivoDTO);
                    em.persist(PrecoactivoErrorHelper.toLevel1Entity(error, null));
                    Precoactivo precoactivo = em.find(Precoactivo.class, precoactivoDTO.getId());
                    precoactivo.setEstadoPrecoactivo(
                            new EstadoPrecoactivo(EnumEstadoPrecoactivo.RECHAZADO_ERROR.getValue()));
                    em.merge(precoactivo);
                }
            }
        }

    }

    /**
     * Valida las obligaciones del coactivo
     * 
     * @param obligaciones
     * @param configuracionCoactivo
     * @param valoresConfiguracion
     * @param precoactivoErrores
     * @return Lista de obligaciones que pasaron las validaciones
     * @author julio.pinzon 2016-08-04
     */
    private List<ObligacionCoactivoDTO> validarObligacionesCoactivo(List<ObligacionCoactivoDTO> obligaciones,
            ConfiguracionCoactivoDTO configuracionCoactivo,
            Map<EnumVariableCondicionCoactivo, List<String>> valoresConfiguracion,
            List<PrecoactivoDTO> precoactivoErrores) {
        logger.debug("CoactivoEJB::validarObligacionesCoactivo(List<ObligacionCoactivoDTO,ConfiguracionCoactivoDTO>)");
        List<ObligacionCoactivoDTO> obligacionesValidas = new ArrayList<ObligacionCoactivoDTO>();
        if (configuracionCoactivo != null) {
            if (valoresConfiguracion == null) {
                valoresConfiguracion = obtenerValoresConfiguracion(configuracionCoactivo);
            }
            BigDecimal valorTotalObligaciones = BigDecimal.ZERO;
            BigDecimal valorMinimo = BigDecimal
                    .valueOf(Long.valueOf(valoresConfiguracion.get(EnumVariableCondicionCoactivo.VALOR_MINIMO).get(0)));

            for (ObligacionCoactivoDTO obligacionCoactivoDTO : obligaciones) {
                // Valida que los datos de la factura esten correctos
                Map<ValidacionPrecoactivoDTO, RespuestaDTO<EnumCampoObligacionCoactivo, EnumErrorGeneraCoactivo>> validacion = validarFacturaCoactivo(
                        obligacionCoactivoDTO.getNumeroObligacion(), configuracionCoactivo, valoresConfiguracion,
                        false);
                ValidacionPrecoactivoDTO validacionPre = validacion.keySet().iterator().next();
                PrecoactivoDTO precoactivoDTO = validacionPre.getPrecoactivo();
                RespuestaDTO<EnumCampoObligacionCoactivo, EnumErrorGeneraCoactivo> respuesta = validacion.values()
                        .iterator().next();
                if (respuesta.getRespuesta().equals(EnumRespuestaSistema.OK)) {
                    obligacionCoactivoDTO.setDireccionValida(precoactivoDTO.getDireccion());
                    obligacionesValidas.add(obligacionCoactivoDTO);
                    valorTotalObligaciones = valorTotalObligaciones.add(obligacionCoactivoDTO.getValorObligacion());
                } else {
                    precoactivoDTO.setId(obligacionCoactivoDTO.getIdPrecoativo());
                    List<PrecoactivoErrorDTO> precoactivoErrors = new ArrayList<>();
                    PrecoactivoErrorDTO error = new PrecoactivoErrorDTO();
                    error.setErrorGeneraCoactivo(new ErrorGeneraCoactivoDTO(respuesta.getErrorRespuesta().getValue()));
                    precoactivoErrors.add(error);
                    precoactivoDTO.setPrecoactivoErrors(precoactivoErrors);
                    precoactivoErrores.add(precoactivoDTO);
                }
            }
            // Validar valor minimo de total de obligaciones
            if (valorMinimo == null || valorTotalObligaciones.compareTo(valorMinimo) < 0) {
                obligacionesValidas.clear();

                for (ObligacionCoactivoDTO obligacionCoactivoDTO : obligaciones) {
                    PrecoactivoDTO precoactivoDTO = new PrecoactivoDTO(obligacionCoactivoDTO.getIdPrecoativo());
                    List<PrecoactivoErrorDTO> precoactivoErrors = new ArrayList<>();
                    PrecoactivoErrorDTO error = new PrecoactivoErrorDTO();
                    error.setErrorGeneraCoactivo(
                            new ErrorGeneraCoactivoDTO(EnumErrorGeneraCoactivo.VALOR_MINIMO_COACTIVO.getValue()));
                    precoactivoErrors.add(error);
                    precoactivoDTO.setPrecoactivoErrors(precoactivoErrors);
                    precoactivoErrores.add(precoactivoDTO);
                }
            }
        }
        return obligacionesValidas;
    }

    @Override
    public Integer registrarCoactivoJob(Integer codigoOrganismo) {
        logger.debug("CoactivoEJB::registrarCoactivoJob(Integer)");
        int cantidadProcesosCoactivo = 0;

        // COnsultar configuracion de coactivo
        List<ConfiguracionCoactivoDTO> pConfiguracionCoactivoDTOList = iLAdministracionCoactivo
                .consultarConfiguracionCoactivo(null, true);
        // Verificacion de configuracion existe
        if (!pConfiguracionCoactivoDTOList.isEmpty()) {
            ConfiguracionCoactivoDTO configuracionCoactivoDTO = pConfiguracionCoactivoDTOList.get(0);

            // Obtiene valores de la configuracion
            Map<EnumVariableCondicionCoactivo, List<String>> valoresConfiguracion = obtenerValoresConfiguracion(
                    configuracionCoactivoDTO);

            // Consulta de obligaciones comparendo por deudor
            Map<Long, List<ObligacionCoactivoDTO>> lsObligacionesComparendo = consultaObligacionesComparendo();
            if (lsObligacionesComparendo != null && !lsObligacionesComparendo.isEmpty()) {
                for (Entry<Long, List<ObligacionCoactivoDTO>> obligacionesDeudor : lsObligacionesComparendo
                        .entrySet()) {
                    try {
                        // Registra coactivo de comparendos
                        iLCoactivo.registrarCoactivo(obligacionesDeudor.getValue(), configuracionCoactivoDTO,
                                valoresConfiguracion);
                        cantidadProcesosCoactivo += obligacionesDeudor.getValue().size();
                    } catch (Exception e) {
                        logger.error("Error al registrar coactivo comparendos", e);
                    }
                }
            }

            try {
                generarOficioBien();
            } catch (Exception e) {
                logger.error("Error al registrar coactivo comparendos", e);
            }
        } else {
            logger.info("No existe configuracion de coactivo vigente");
            cantidadProcesosCoactivo = -1;
        }

        return cantidadProcesosCoactivo;
    }

    /**
     * Consulta las obligaciones de comparendo que pueden pasar a coactivo con las validaciones iniciales
     * 
     * @return Lista de obligaciones de tipo Comparendo por deudor
     * @author julio.pinzon 2016-08-04
     * @param numeroDiasCoactivo
     * @param tipoIdentificacionPermitidos
     * @param coactivoNoNotificadas
     */
    private Map<Long, List<ObligacionCoactivoDTO>> consultaObligacionesComparendo() {
        logger.debug("CoactivoEJB::consultaObligacionesComparendo()");
        Map<Long, List<ObligacionCoactivoDTO>> obligacionesDeudor = new HashMap<>();
        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT ");
        consulta.append("p.id_cartera, ");
        consulta.append("ca.saldo_capital, ");
        consulta.append("ca.saldo_interes, ");
        consulta.append("p.numero_obligacion, ");
        consulta.append("p.id_deudor, ");
        consulta.append("p.fecha_obligacion, ");
        consulta.append("p.id_funcionario, ");
        consulta.append("p.id_cargue_coactivo, ");
        consulta.append("p.id_precoactivo ");
        consulta.append("FROM precoactivo p ");
        consulta.append("JOIN cartera ca on ca.id_cartera = p.id_cartera ");
        consulta.append("WHERE p.id_estado_precoactivo = :estadoAprobado ");
        consulta.append("AND p.codigo_tipo_obligacion = :tipoComparendo ");

        Query query = em.createNativeQuery(consulta.toString());
        query.setParameter("estadoAprobado", EnumEstadoPrecoactivo.AUTORIZADO.getValue());
        query.setParameter("tipoComparendo", EnumTipoObligacion.COMPARENDO.getValue());
        @SuppressWarnings({ "unchecked" })
        List<Object[]> lsObligacionesComparendos = Utilidades.safeList(query.getResultList());

        for (Object[] obligacionComparendo : lsObligacionesComparendos) {
            int i = 0;
            BigInteger idCartera = (BigInteger) obligacionComparendo[i++];
            BigDecimal saldoCapital = (BigDecimal) obligacionComparendo[i++];
            BigDecimal saldoInteres = (BigDecimal) obligacionComparendo[i++];
            String numObligacion = (String) obligacionComparendo[i++];
            Long idDeudor = ((BigInteger) obligacionComparendo[i++]).longValue();
            Date fechaObligacion = (Date) obligacionComparendo[i++];
            Integer idFuncionario = (Integer) obligacionComparendo[i++];
            Long idCargue = ((BigInteger) obligacionComparendo[i++]).longValue();
            Long idPrecoactivo = ((BigInteger) obligacionComparendo[i++]).longValue();

            CoactivoDTO coactivo = new CoactivoDTO();
            coactivo.setCargueCoactivo(new CargueCoactivoDTO(idCargue));
            coactivo.setFuncionario(new FuncionarioDTO(idFuncionario));
            coactivo.setPersona(new PersonaDTO(idDeudor));
            // Arma obligacion que va a entrar a coactivo
            ObligacionCoactivoDTO obligacion = new ObligacionCoactivoDTO();
            obligacion.setCodigoTipoObligacion(EnumTipoObligacion.COMPARENDO.getValue());
            obligacion.setCartera(new CarteraDTO(idCartera.longValue()));
            obligacion.setNumeroObligacion(numObligacion);
            obligacion.setCoactivo(coactivo);
            obligacion.setFechaObligacion(fechaObligacion);
            obligacion.setValorObligacion(saldoCapital);
            obligacion.setValorInteresMoratorios(saldoInteres);
            obligacion.setIdPrecoativo(idPrecoactivo);
            if (!obligacionesDeudor.containsKey(idDeudor)) {
                obligacionesDeudor.put(idDeudor, new ArrayList<ObligacionCoactivoDTO>());
            }
            obligacionesDeudor.get(idDeudor).add(obligacion);
        }
        return obligacionesDeudor;
    }

    /**
     * Consulta y devuelve un mapa con los valores de una configuracion
     * 
     * @param configuracionCoactivoDTO
     * @return Mapa con los valores de la configuracion
     * @author julio.pinzon 2016-08-04
     */
    private Map<EnumVariableCondicionCoactivo, List<String>> obtenerValoresConfiguracion(
            ConfiguracionCoactivoDTO configuracionCoactivoDTO) {
        List<CondicionCoactivoDTO> condiciones = iLAdministracionCoactivo
                .consultarCondicionesConfiguracionCoactivo(configuracionCoactivoDTO, true);
        // Valores
        Map<EnumVariableCondicionCoactivo, List<String>> valores = new HashMap<>();
        for (CondicionCoactivoDTO condicionFinanciacionDTO : condiciones) {
            for (VariableCondicionCoacDTO variableCondicionDTO : condicionFinanciacionDTO
                    .getLstVariablesCondicionCoac()) {
                EnumVariableCondicionCoactivo variable = EnumVariableCondicionCoactivo
                        .getEnum(variableCondicionDTO.getId());
                List<String> valoresCondicion = null;
                if (variableCondicionDTO.getLstValorCondicionCoactivo() != null
                        && !variableCondicionDTO.getLstValorCondicionCoactivo().isEmpty()) {
                    valoresCondicion = new ArrayList<>();

                    for (ValorCondicionCoactivoDTO valor : variableCondicionDTO.getLstValorCondicionCoactivo()) {
                        valoresCondicion.add(valor.getValor());
                    }
                }
                valores.put(variable, valoresCondicion);
            }
        }
        return valores;
    }

    @Override
    public List<RespuestaSeguimientoDTO> consultarSeguimientosCoactivo(ConsultaSeguimientoCoactivoDTO consulta)
            throws CirculemosNegocioException {
        logger.debug("CoactivoEJB::consultarSeguimiento(ConsultaSeguimientoCoactivoDTO)");
        List<RespuestaSeguimientoDTO> lsResSeguimiento = new ArrayList<>(0);

        checkNotNull(consulta, "Debe ingresar algun criterio de busqueda");

        GenericDao<Coactivo> coactivoDao = new GenericDao<>(Coactivo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT c FROM Coactivo c ");
        jpql.append(" JOIN FETCH c.persona p");
        // jpql.append(" LEFT JOIN c.obligacionCoactivo lsOblig");
        jpql.append(" WHERE 1=1");

        if (consulta.getFechaIniProceso() != null && consulta.getFechaFinProceso() != null) {
            jpql.append(
                    " AND c.proceso.fechaInicio >= :fechaInicioAperturaProceso AND c.proceso.fechaInicio <= :fechaFinAperturaProceso ");

            Calendar fechaInicio = Calendar.getInstance();
            fechaInicio.setTime(consulta.getFechaIniProceso());
            fechaInicio.set(Calendar.HOUR, 0);
            fechaInicio.set(Calendar.MINUTE, 0);
            fechaInicio.set(Calendar.SECOND, 0);
            filtros.put("fechaInicioAperturaProceso", fechaInicio.getTime());
            Calendar fechaFinal = Calendar.getInstance();
            fechaFinal.setTime(consulta.getFechaFinProceso());
            fechaFinal.set(Calendar.HOUR, 23);
            fechaFinal.set(Calendar.MINUTE, 59);
            fechaFinal.set(Calendar.SECOND, 59);
            filtros.put("fechaFinAperturaProceso", fechaFinal.getTime());
        }

        if (consulta.getNumeroProceso() != null) {
            jpql.append(" AND c.proceso.numeroProceso = :numeroProceso");
            filtros.put("numeroProceso", consulta.getNumeroProceso());
        }

        if (consulta.getTipoDocDeudor() != null && StringUtils.isNotBlank(consulta.getNumeroDocDeudor())) {
            jpql.append(" AND p.numeroIdentificacion = :numeroIden");
            filtros.put("numeroIden", consulta.getNumeroDocDeudor());
            jpql.append(" AND p.tipoIdentificacion.id = :tipoIden");
            filtros.put("tipoIden", consulta.getTipoDocDeudor());
        }

        // jpql.append(" GROUP BY c");
        jpql.append(" ORDER BY c.proceso.fechaInicio DESC");

        List<Coactivo> resultadoConsulta = coactivoDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            for (Coactivo coactivo : resultadoConsulta) {
                RespuestaSeguimientoDTO resSeguimiento = new RespuestaSeguimientoDTO();
                resSeguimiento.setIdProceso(coactivo.getProceso().getId());
                resSeguimiento.setFechaGeneraCoactivo(coactivo.getProceso().getFechaInicio());
                resSeguimiento.setNombreDeudor(
                        ProcesarPersonasHelper.toPersonaBasicaDTO(coactivo.getPersona()).getNombreCompleto());
                resSeguimiento.setNumeroCoactivo(coactivo.getNumeroCoactivo());
                resSeguimiento.setNumeroProceso(coactivo.getProceso().getNumeroProceso());
                resSeguimiento.setFechaProceso(coactivo.getProceso().getFechaInicio());
                resSeguimiento.setTipoDocDeudor(coactivo.getPersona().getTipoIdentificacion().getNombre());
                resSeguimiento.setNumeroDocDeudor(coactivo.getPersona().getNumeroIdentificacion());
                resSeguimiento.setIdTramite(coactivo.getIdTramite());
                resSeguimiento.setIdCoactivo(coactivo.getId());
                resSeguimiento.setValorCoactivo(coactivo.getValorTotalObligaciones());
                resSeguimiento.setValorCostas(coactivo.getValorTotalCostasProcesales());
                lsResSeguimiento.add(resSeguimiento);
            }
        }
        return lsResSeguimiento;
    }

    @Override
    public List<RespuestaTrazabilidadDTO> consultarTrazaSeguimiento(Long idProceso) {
        logger.debug("CoactivoEJB::consultarTrazaSeguimiento(Long)");
        List<RespuestaTrazabilidadDTO> lsResTrzSeguimiento = new ArrayList<>(0);
        if (idProceso != null) {
            TrazabilidadProcesoDTO trzProcesoDTO = new TrazabilidadProcesoDTO();
            ProcesoDTO procesoDTO = new ProcesoDTO();
            procesoDTO.setId(idProceso);
            trzProcesoDTO.setProceso(procesoDTO);
            List<TrazabilidadProcesoDTO> lsTrzProceso = iRFachadaProceso.consultarTrazabilidad(trzProcesoDTO);
            if (lsTrzProceso != null) {
                Collections.sort(lsTrzProceso, new Comparator<TrazabilidadProcesoDTO>() {
                    @Override
                    public int compare(TrazabilidadProcesoDTO obj1, TrazabilidadProcesoDTO obj2) {
                        return obj1.getFechaInicio().compareTo(obj2.getFechaInicio());
                    }
                });
                Collections.reverse(lsTrzProceso);
                RespuestaTrazabilidadDTO resTrzSeguimiento = null;
                for (TrazabilidadProcesoDTO trzProceso : lsTrzProceso) {
                    resTrzSeguimiento = new RespuestaTrazabilidadDTO();
                    resTrzSeguimiento.setIdTrazabilidadPro(trzProceso.getId());
                    resTrzSeguimiento.setActividad(trzProceso.getEstadoProceso().getNombre());
                    resTrzSeguimiento.setFechaIniActividad(trzProceso.getFechaInicio());
                    if (trzProceso.getDocumentos() != null && !trzProceso.getDocumentos().isEmpty()) {
                        resTrzSeguimiento.setNumActoAdmin(trzProceso.getProceso().getNumeroProceso());
                    }
                    UsuarioDetalleDto usuarioDetDto = seguridadCirculemosEJB
                            .consultarUsuario(trzProceso.getUsuario().getLogin());
                    StringBuilder sb = new StringBuilder();
                    sb.append(usuarioDetDto.getNombres());
                    sb.append(" ");
                    sb.append(usuarioDetDto.getApellidos());
                    resTrzSeguimiento.setUsuario(sb.toString());
                    resTrzSeguimiento.setLsDocumentosProc(trzProceso.getDocumentos());
                    lsResTrzSeguimiento.add(resTrzSeguimiento);
                }
            }
        }
        return lsResTrzSeguimiento;
    }

    /**
     * Calcula las costas procesales
     * 
     * @param obligaciones
     * @param valorAbsoluto
     * @param porcentaje
     * @return
     * @throws CirculemosNegocioException
     */
    private List<ObligacionCoactivoDTO> calcularCostasProcesales(List<ObligacionCoactivoDTO> obligaciones,
            BigDecimal valorAbsoluto, BigDecimal porcentaje) throws CirculemosNegocioException {
        // No aplica costas
        if (valorAbsoluto == null && porcentaje == null) {
            return obligaciones;
        } else if (valorAbsoluto != null) {
            // Aplica valor absoluto
            if (obligaciones.size() == 1) {
                obligaciones.get(0).setValorCostasProcesales(valorAbsoluto);
                return obligaciones;
            } else {
                BigDecimal porcentajeDeuda;
                BigDecimal totalDeuda = new BigDecimal(0);

                // Calculo de deuda
                for (ObligacionCoactivoDTO obligacion : obligaciones) {
                    totalDeuda = totalDeuda
                            .add(obligacion.getValorObligacion().add(obligacion.getValorInteresMoratorios()));

                }

                // Calculo de porcentaje y valor
                for (ObligacionCoactivoDTO obligacion : obligaciones) {
                    porcentajeDeuda = ((obligacion.getValorCostasProcesales()
                            .add(obligacion.getValorInteresMoratorios())).multiply(new BigDecimal(100)))
                                    .divide(totalDeuda);

                    obligacion
                            .setValorCostasProcesales(totalDeuda.multiply(porcentajeDeuda).divide(new BigDecimal(100)));

                }
                return obligaciones;

            }
        } else if (porcentaje != null) {
            // Aplica porcentaje de la deuda
            for (ObligacionCoactivoDTO obligacion : obligaciones) {
                obligacion.setValorCostasProcesales(
                        (obligacion.getValorObligacion().add(obligacion.getValorInteresMoratorios()))
                                .multiply(porcentaje).divide(new BigDecimal(100)).setScale(2,
                                        BigDecimal.ROUND_HALF_UP));

            }
            return obligaciones;
        } else {
            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002005);
        }
    }

    /**
     * Registra un proceso de tipo coactivo
     * 
     * @author giovanni.velandia
     * @return
     */
    private ProcesoDTO registrarProcesoCoactivo() {
        RegistraProcesoDTO registra = new RegistraProcesoDTO();
        registra.setObservacion(EnumTipoProceso.COACTIVO.name());
        registra.setTipoProceso(EnumTipoProceso.COACTIVO);
        registra.setEstado(EnumEstadoProceso.ECUADOR_COACTIVO_RADICACION);
        registra.setConsecutivo(EnumConsecutivo.NUMERO_COACTIVO_ECUADOR);
        return iRFachadaProceso.crearProceso(registra);
    }

    /**
     * Registrar coactivo
     * 
     * @author giovanni.velandia
     * @param obligaciones
     * @param procesoDTO
     * @param configuracionCoactivoDTO
     */
    private Coactivo registrarCoactivo(List<ObligacionCoactivoDTO> obligaciones, ProcesoDTO procesoDTO,
            ConfiguracionCoactivoDTO configuracionCoactivoDTO) {
        Calendar fecha = new GregorianCalendar();
        Coactivo coactivo = CoactivoHelper.toLevel1Entity(obligaciones.get(0).getCoactivo(), null);
        coactivo.setAnio(fecha.get(Calendar.YEAR));
        coactivo.setCantidadObligaciones(obligaciones.size());
        coactivo.setConfiguracionCoactivo(ConfiguracionCoactivoHelper.toLevel0Entity(configuracionCoactivoDTO, null));
        coactivo.setOrganismoTransito(
                OrganismoTransitoHelper.toLevel0Entity(seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario(), null));
        coactivo.setProceso(ProcesoHelper.toLevel0Entity(procesoDTO, null));
        // Direccio
        if (obligaciones.get(0).getDireccionValida() != null) {
            coactivo.setDireccion(DireccionHelper.toLevel0Entity(obligaciones.get(0).getDireccionValida(), null));
        }
        // Pone valores por defecto hasta que se envie a axis
        coactivo.setIdTramite(0l);
        coactivo.setNumeroCoactivo(procesoDTO.getNumeroProceso());

        // Calcula las sumas de las obligaciones
        BigDecimal sumaObligaciones = BigDecimal.ZERO;
        BigDecimal sumaCostas = BigDecimal.ZERO;
        for (ObligacionCoactivoDTO obligacionDTO : obligaciones) {
            sumaObligaciones = sumaObligaciones.add(obligacionDTO.getValorObligacion());
            sumaCostas = sumaCostas.add(obligacionDTO.getValorCostasProcesales());
        }
        coactivo.setValorTotalCostasProcesales(sumaCostas);
        coactivo.setValorTotalObligaciones(sumaObligaciones);

        em.persist(coactivo);
        return coactivo;
    }

    /**
     * Reagistrar obligaciones
     * 
     * @author giovanni.velandia
     * @param obligacionCoactivoDTOs
     * @param
     */
    private void registrarObligaciones(List<ObligacionCoactivoDTO> obligacionCoactivoDTOs, Coactivo coactivo,
            ProcesoDTO procesoDTO) throws CirculemosNegocioException {
        ObligacionCoactivo obligacion;
        ComparendoDTO comparendoDTO;
        for (ObligacionCoactivoDTO obligacionDTO : obligacionCoactivoDTOs) {
            // Registra las obligaciones
            obligacion = ObligacionCoactivoHelper.toLevel1Entity(obligacionDTO, null);
            obligacion.setCoactivo(coactivo);
            em.persist(obligacion);

            // Actualiza estado precoactivo
            Precoactivo precoactivo = em.find(Precoactivo.class, obligacionDTO.getIdPrecoativo());
            precoactivo.setEstadoPrecoactivo(new EstadoPrecoactivo(EnumEstadoPrecoactivo.EN_COACTIVO.getValue()));
            em.merge(precoactivo);

            // Registra las tablas intermedias
            if (obligacionDTO.getCodigoTipoObligacion() == EnumTipoObligacion.COMPARENDO.getValue()) {
                // Crea ComparendoProceso
                ComparendoProceso comparendoProcesoEntidad = new ComparendoProceso();
                comparendoDTO = iRFachadaComparendo.consultarComparendo(obligacionDTO.getNumeroObligacion(),
                        seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());
                comparendoProcesoEntidad.setComparendo(ComparendoHelper.toLevel0Entity(comparendoDTO, null));
                comparendoProcesoEntidad.setIdProceso(procesoDTO.getId());
                em.persist(comparendoProcesoEntidad);

                // Registrar movimiento de costas procesales
                MovimientoCarteraDTO movimiento = new MovimientoCarteraDTO();
                movimiento.setSaldoCapital(obligacionDTO.getValorObligacion());
                movimiento.setSaldoIntereses(obligacionDTO.getValorInteresMoratorios());
                movimiento.setSaldoCostasProcesales(obligacionDTO.getValorCostasProcesales());
                movimiento.setIdActividad(EnumActividadCartera.INICIAR_COACTIVO.getValue());
                MovimientosCarteraDTO movimientosCartera = new MovimientosCarteraDTO();
                movimientosCartera.setCartera(iRFachadaCartera.consultarCartera(obligacionDTO.getCartera().getId()));
                movimientosCartera.setValorMovimiento(obligacionDTO.getValorCostasProcesales());

                ConceptoCarteraDTO conceptoCarteraDTO = new ConceptoCarteraDTO();
                TipoConceptoCarteraDTO tipoConceptoCarteraDTO = new TipoConceptoCarteraDTO();
                tipoConceptoCarteraDTO.setId(EnumTipoConceptoCartera.DEBITO.getCodigo());
                conceptoCarteraDTO.setTipoConceptoCartera(tipoConceptoCarteraDTO);
                conceptoCarteraDTO.setId(EnumConceptoCartera.CREAR_COSTAS_PROCESALES.getValue());
                conceptoCarteraDTO.setCodigo(EnumConceptoCartera.CREAR_COSTAS_PROCESALES.getCodigo());
                TipoSaldoDTO tipoSaldoDTO = new TipoSaldoDTO();
                tipoSaldoDTO.setId(EnumTipoSaldo.COSTAS_PROCESALES.getId());
                conceptoCarteraDTO.setTipoSaldo(tipoSaldoDTO);
                movimientosCartera.setConceptoCartera(conceptoCarteraDTO);
                movimientosCartera.setFechaCreacion(UtilFecha.buildCalendar().getTime());
                movimientosCartera.setFechaMovimiento(UtilFecha.buildCalendar().getTime());
                movimientosCartera.setLoginUsuario(seguridadCirculemosEJB.obtenerUsuarioDto().getLogin());
                movimientosCartera.setObservaciones(EnumConceptoCartera.EXONERACION.name());
                movimiento.setMovimientosCartera(movimientosCartera);
                iRFachadaCartera.registrarMovimiento(movimiento);
            } else {
                throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002004);
            }
        }
    }

    /**
     * Genera documento notificacion
     * 
     * @author giovanni.velandia
     * @param coactivo
     * @param trazabilidadProcesoDTO
     * @param procesoDTO
     */
    private void generaDocumentoNotificacion(Coactivo coactivo, TrazabilidadProcesoDTO trazabilidadProcesoDTO,
            ProcesoDTO procesoDTO) throws CirculemosNegocioException {
        try {

            DocumentoProcesoDTO documentoProceso = registrarDocumentoCoactivo(
                    EnumTipoDocumentoGenerado.NOTIFICACION_COACTIVO, trazabilidadProcesoDTO, coactivo,
                    EnumTipoDocumentoProceso.COACTIVO_NOTIFICACION, RESPONSABLE_SOGIT);

            // Guardar en FileSystem
            List<Long> idsDocumento = new ArrayList<>();
            idsDocumento.add(documentoProceso.getNumeroDocumento());
            byte[] contenido = iRDocumentosCirculemos.consultarDocumentosPDF(idsDocumento);
            ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(
                    String.format(NOMBRE_ARCHIVO_NOTIFICACION, procesoDTO.getNumeroProceso()), contenido);
            archivo.setRuta(String.format(RUTA_NOTIFICACIONES, Calendar.getInstance().get(Calendar.YEAR) + "",
                    (Calendar.getInstance().get(Calendar.MONTH) + 1) + ""));
            ProcesadorArchivoFileSystem procesa = new ProcesadorArchivoFileSystem();
            procesa.procesarArchivo(archivo);
        } catch (CirculemosAlertaException e) {
            logger.error("Error en generación de documento de envio a notificacion", e);
            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002008);
        }
    }

    /**
     * 
     * Genera el proceso de coactivo
     * 
     * @param obligaciones
     * @param configuracion
     * @throws CirculemosNegocioException
     * @throws CirculemosAlertaException
     */
    private void generaProcesoCoactivo(List<ObligacionCoactivoDTO> obligaciones, ConfiguracionCoactivoDTO configuracion)
            throws CirculemosNegocioException {

        if (obligaciones != null && !obligaciones.isEmpty() && configuracion != null) {

            // Crea proceso coactivo
            ProcesoDTO procesoDTO = registrarProcesoCoactivo();

            // Crea Participantes
            PersonaDTO persona = iRFachadaAdminNegocio
                    .consultarPersona(obligaciones.get(0).getCoactivo().getPersona().getId());
            registrarParticipante(persona, procesoDTO);

            // Se crea el coactivo
            Coactivo coactivo = registrarCoactivo(obligaciones, procesoDTO, configuracion);

            // Se crean las obligaciones
            registrarObligaciones(obligaciones, coactivo, procesoDTO);

            // Actualizacion de estado a mandamiento de pago
            TrazabilidadProcesoDTO trazabilidadMandamiento = iRFachadaProceso
                    .actualizarEstadoProceso(procesoDTO.getId(), EnumEstadoProceso.ECUADOR_COACTIVO_MANDAMIENTO, false);
            try {
                em.flush();

                boolean financiacionIncumplida = false;
                for (ObligacionCoactivoDTO obligacionCoactivoDTO : obligaciones) {
                    financiacionIncumplida = validarProcesoFianciacionIncumplido(
                            obligacionCoactivoDTO.getNumeroObligacion());
                    if (financiacionIncumplida) {
                        financiacionIncumplida = true;
                        break;
                    }
                }

                if (financiacionIncumplida) {
                    // TODO genera auto de pago especial
                } else {
                    // Genera documento apertura
                    if (validarDireccionPersona(persona)) {
                        registrarDocumentoCoactivo(EnumTipoDocumentoGenerado.AUTO_PAGO, trazabilidadMandamiento,
                                coactivo, EnumTipoDocumentoProceso.COACTIVO_AUTO_PAGO, null);
                    } else {
                        registrarDocumentoCoactivo(EnumTipoDocumentoGenerado.AUTO_PAGO_SIN_DIRECCION,
                                trazabilidadMandamiento, coactivo,
                                EnumTipoDocumentoProceso.COACTIVO_AUTO_PAGO_SIN_DIRECCION, null);
                    }
                }

                // Genera documento posesion
                registrarDocumentoCoactivo(EnumTipoDocumentoGenerado.ACTA_DE_POSESION, trazabilidadMandamiento,
                        coactivo, EnumTipoDocumentoProceso.COACTIVO_ACTA_DE_POSESION, null);

            } catch (CirculemosAlertaException e) {
                logger.error("Error en generación de documentos de mandamiento de pago", e);
                throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002001);
            }

            // Verifica si se debe generar los documentos con el siguiente parametro : 225 Generar documentos de notificacion
            generarNotificacion(procesoDTO, coactivo);

            // Generacion de oficio de solicitud de bienes
            generarSolicitudBien(configuracion, procesoDTO, coactivo);

            // Valida si debe registrar el coactivo en axis
            registrarCoactivoAxis(coactivo, persona);

        } else {
            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002002);
        }
    }

    /**
     * Valida si una financiacion es incumplida
     * 
     * @author giovanni.velandia
     * @param numeroObligacion
     * @return
     */
    private boolean validarProcesoFianciacionIncumplido(String numeroObligacion) {

        boolean proceso = false;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT o FROM ObligacionFinanciacion o");
        jpql.append(" JOIN o.financiacion f");
        jpql.append(" JOIN f.proceso p");
        jpql.append(" WHERE o.numeroObligacion = :numeroObligacion");
        jpql.append(" AND p.estadoProceso.id = :estadoProceso");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("numeroObligacion", numeroObligacion);
        query.setParameter("estadoProceso", EnumEstadoProceso.ECUADOR_FINANCIACION_INCUMPLIDO.getId());

        @SuppressWarnings("unchecked")
        List<ObligacionFinanciacion> procesos = query.getResultList();
        if (procesos != null && !procesos.isEmpty()) {
            proceso = true;
        }
        return proceso;
    }

    /**
     * registro de participante
     * 
     * @author giovanni.velandia
     * @param obligaciones
     */
    private void registrarParticipante(PersonaDTO personaDTO, ProcesoDTO procesoDTO) throws CirculemosNegocioException {
        if (personaDTO != null) {
            ParticipanteProcesoDTO participante = new ParticipanteProcesoDTO();
            participante.setTipoParticipante(new TipoParticipanteDTO(EnumTipoParticipante.INFRACTOR.getValue()));
            participante.setProceso(procesoDTO);
            participante.setPersona(personaDTO);
            em.persist(ParticipanteProcesoHelper.toLevel1Entity(participante, null));
        } else {
            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002003);
        }
    }

    /**
     * Validar direccion persona
     * 
     * @author giovanni.velandia
     * @param personaDTO
     */
    private boolean validarDireccionPersona(PersonaDTO personaDTO) {
        DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
        direccionPersonaDTO.setPersona(personaDTO);
        List<DireccionPersonaDTO> direccionPersonaDTOs = iRFachadaAdminNegocio
                .consultarDireccionPersona(direccionPersonaDTO);

        if (direccionPersonaDTOs != null && !direccionPersonaDTOs.isEmpty()) {
            // reversa la lista para que recorra de la mas actualizada a la menos
            Collections.reverse(direccionPersonaDTOs);

            RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> respuestaDTO;
            // recorre la lista de direcciones
            for (DireccionPersonaDTO direccion : direccionPersonaDTOs) {
                respuestaDTO = new RespuestaDTO<>();
                // valida si la direccion es correcta
                respuestaDTO = iFachadaAdminGeneral.validarDireccion(direccion.getDireccion(),
                        EnumTipoValidacionDireccion.INFRACTOR);
                // si la direccion es correcta no recorra mas direcciones
                if (respuestaDTO.getRespuesta().equals(EnumRespuestaSistema.OK)) {
                    return true;
                } else {
                    if (respuestaDTO.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * Registro informacion en Axis
     * 
     * @author giovanni.velandia
     * @param coactivo
     * @param personaDTO
     */
    private void registrarCoactivoAxis(Coactivo coactivo, PersonaDTO personaDTO) throws CirculemosNegocioException {
        // Valida si debe registrar el coactivo en axis
        ValorParametroDTO parametroCoactivoTerceros = iFachadaAdminGeneral.consultarValorParametro(
                EnumParametro.REGISTRAR_COACTIVO_TERCEROS, coactivo.getOrganismoTransito().getCodigoOrganismo(), true);

        if (parametroCoactivoTerceros != null && parametroCoactivoTerceros.getValorParamBoolean()) {
            // Envia el coactivo a Axis
            CoactivoDTO coactivoDTO = CoactivoHelper.toLevel1DTO(coactivo);
            coactivoDTO.setPersona(personaDTO);
            coactivoDTO = iRClienteWSAXIS.registarCoactivo(coactivoDTO);
            coactivo.setIdTramite(coactivoDTO.getIdTramite());
            coactivo.setNumeroCoactivo(coactivoDTO.getNumeroCoactivo());
            em.merge(coactivo);
        }
    }

    /**
     * Generar notificacion
     * 
     * @author giovanni.velandia
     * @param procesoDTO
     */
    private void generarNotificacion(ProcesoDTO procesoDTO, Coactivo coactivo) throws CirculemosNegocioException {
        ValorParametroDTO valorParametroDTO = iFachadaAdminGeneral.consultarValorParametro(
                EnumParametro.GENERAR_DOC_NOTIFI_PROCESO_COACTIVO,
                seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(), true);
        if (valorParametroDTO != null) {
            if (valorParametroDTO.getValorParamBoolean()) {
                // Actualizacion de estado a envio notificacion
                TrazabilidadProcesoDTO trazabilidadEnvio = iRFachadaProceso.actualizarEstadoProceso(procesoDTO.getId(),
                        EnumEstadoProceso.ECUADOR_COACTIVO_ENVIO_NOTIFICACION, false);

                // Genera documento notificacion
                generaDocumentoNotificacion(coactivo, trazabilidadEnvio, procesoDTO);
            }
        }
    }

    /**
     * @author giovanni.velandia
     * @param configuracionCoactivoDTO
     */
    private void generarSolicitudBien(ConfiguracionCoactivoDTO configuracionCoactivoDTO, ProcesoDTO procesoDTO,
            Coactivo coactivo) throws CirculemosNegocioException {
        // Obtiene valores de la configuracion
        Map<EnumVariableCondicionCoactivo, List<String>> valoresConfiguracion = obtenerValoresConfiguracion(
                configuracionCoactivoDTO);
        List<String> valor = valoresConfiguracion.get(EnumVariableCondicionCoactivo.GENERAR_OFICIO_SOLICITUD_BIENES);
        if (valor == null || valor.isEmpty()) {
            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002007);
        }
        Boolean oficioSolicitud = Boolean.valueOf(valor.get(0));

        if (oficioSolicitud) {
            valor = valoresConfiguracion.get(EnumVariableCondicionCoactivo.ENTIDAD);
            if (valor == null || valor.isEmpty()) {
                throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002007);
            }
            List<String> entidades = valor;
            SolicitudOficioBienDTO solicitudOficioBienDTO = new SolicitudOficioBienDTO();
            // Entidades
            solicitudOficioBienDTO.setSolicitudBienEntidadDTOs(new ArrayList<SolicitudBienEntidadDTO>());
            for (String entidad : entidades) {

                // Entidad oficio
                SolicitudBienEntidadDTO solicitudBienEntidadDTO = new SolicitudBienEntidadDTO();
                ConfiguracionEntidadDTO configuracionEntidadDTO = new ConfiguracionEntidadDTO();
                EntidadOficioDTO entidadOficioDTO = new EntidadOficioDTO();
                entidadOficioDTO.setId(Integer.parseInt(entidad));
                configuracionEntidadDTO = iRAdministracion.consultarConfiguracionEntidad(entidadOficioDTO);
                solicitudBienEntidadDTO.setConfiguracionEntidadDTO(configuracionEntidadDTO);
                solicitudOficioBienDTO.getSolicitudBienEntidadDTOs().add(solicitudBienEntidadDTO);
            }

            // Coactivo
            solicitudOficioBienDTO.setCoactivoDTO(CoactivoHelper.toLevel0DTO(coactivo));
            registrarSolicitudOficioBien(solicitudOficioBienDTO);
        }
    }

    /**
     * @author giovanni.velandia
     * @param configuracionCoactivoDTO
     */
    public void generarOficioBien() throws CirculemosNegocioException {

        // COnsultar configuracion de coactivo
        List<ConfiguracionCoactivoDTO> pConfiguracionCoactivoDTOList = iLAdministracionCoactivo
                .consultarConfiguracionCoactivo(null, true);

        ConfiguracionCoactivoDTO configuracionCoactivoDTO = null;
        if (!pConfiguracionCoactivoDTOList.isEmpty()) {
            configuracionCoactivoDTO = pConfiguracionCoactivoDTOList.get(0);
        }

        // Consulta la generacion de oficio
        List<SolicitudOficioBienDTO> solicitudOficioBienDTOs = consultarSolicitudOficioBien();
        if (solicitudOficioBienDTOs != null && !solicitudOficioBienDTOs.isEmpty()) {

            // Obtiene valores de la configuracion
            Map<EnumVariableCondicionCoactivo, List<String>> valoresConfiguracion = obtenerValoresConfiguracion(
                    configuracionCoactivoDTO);
            List<String> valor = valoresConfiguracion.get(EnumVariableCondicionCoactivo.TIPO_OFICIO);
            if (valor == null || valor.isEmpty()) {
                throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002007);
            }
            Integer tipoOficio = Integer.valueOf(valor.get(0));

            if (tipoOficio.equals(EnumTipoOficio.INDIVIDUAL.getValue())) {
                // Individuales
                for (SolicitudOficioBienDTO solicitudOficioBienDTO : solicitudOficioBienDTOs) {
                    // Proceso
                    ProcesoDTO procesoDTO = consultarProceso(solicitudOficioBienDTO.getCoactivoDTO().getId());

                    // Actualizacion de estado a solicitud bienes
                    TrazabilidadProcesoDTO trazabilidadSolicitudBien = iRFachadaProceso.actualizarEstadoProceso(
                            procesoDTO.getId(), EnumEstadoProceso.SOLICITUD_BUSQUEDA_DE_BIENES, false);
                    for (SolicitudBienEntidadDTO solicitudBienEntidadDTO : solicitudOficioBienDTO
                            .getSolicitudBienEntidadDTOs()) {
                        OficioBienDTO oficioBienDTO = new OficioBienDTO();
                        // Lista de coactios asociados a el oficio
                        oficioBienDTO.setCoactivoOficioBiens(new ArrayList<CoactivoOficioBienDTO>());
                        // Solicitud bien
                        oficioBienDTO.setSolicitudBienEntidadDTO(solicitudBienEntidadDTO);

                        // Agregamso los coactivos para este oficio en este caso es uno
                        CoactivoOficioBienDTO coactivoOficioBienDTO = new CoactivoOficioBienDTO();
                        coactivoOficioBienDTO.setCoactivoDTO(solicitudOficioBienDTO.getCoactivoDTO());
                        oficioBienDTO.getCoactivoOficioBiens().add(coactivoOficioBienDTO);

                        // Registrar oficio bien
                        registrarOficioBien(oficioBienDTO);

                        try {
                            // Registrar documento solicitud bien
                            registrarDocumentoSolicitudBien(EnumTipoDocumentoGenerado.SOLICITUD_OFICIO_BIEN,
                                    trazabilidadSolicitudBien, oficioBienDTO,
                                    EnumTipoDocumentoProceso.SOLICITUD_OFICIO_BIEN, null);

                        } catch (CirculemosAlertaException e) {
                            logger.error("Error en generación de documento solicitud oficio bien", e);
                            throw new CirculemosNegocioException(ErrorCoactivo.GenerarCoactivo.COAC_002001);
                        }

                        solicitudOficioBienDTO.setGeneraOficio(true);
                        actualizarSolicitudOficioBien(solicitudOficioBienDTO);
                    }
                }
            } else {
                // Masivos
            }
        }

    }

    /**
     * @author giovanni.velandia
     * @param idCOactivo
     * @return
     */
    private ProcesoDTO consultarProceso(Long idCoactivo) {
        ProcesoDTO procesoDTO = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM Coactivo c");
        jpql.append(" INNER JOIN c.proceso p");
        jpql.append(" WHERE c.id = :idCoactivo");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("idCoactivo", idCoactivo);

        @SuppressWarnings("unchecked")
        List<Proceso> procesos = query.getResultList();
        if (procesos != null && !procesos.isEmpty()) {
            procesoDTO = ProcesoHelper.toLevel1DTO(procesos.get(0));
        }

        return procesoDTO;
    }

    /**
     * Registro oficio bien
     * 
     * @author giovanni.velandia
     */
    private void registrarOficioBien(OficioBienDTO oficioBienDTO) {
        oficioBienDTO.setNumeroOficio(consecutivoOficioBien());
        oficioBienDTO.setFechaGeneracion(Calendar.getInstance().getTime());
        OficioBien oficioBien = OficioBienHelper.toLevel1Entity(oficioBienDTO, null);
        em.persist(oficioBien);
        em.flush();
        oficioBienDTO.setId(oficioBien.getId());
        for (CoactivoOficioBienDTO coactivoOficioBienDTO : oficioBienDTO.getCoactivoOficioBiens()) {
            coactivoOficioBienDTO.setOficioBienDTO(oficioBienDTO);
            registrarCoactivoOficioBien(coactivoOficioBienDTO);
        }
    }

    /**
     * Registro coactivo oficio bien
     * 
     * @author giovanni.velandia
     */
    private void registrarCoactivoOficioBien(CoactivoOficioBienDTO coactivoOficioBienDTO) {
        CoactivoOficioBien coactivoOficioBien = CoactivoOficioBienHelper.toLevel1Entity(coactivoOficioBienDTO, null);
        em.persist(coactivoOficioBien);
    }

    /**
     * @author giovanni.velandia
     * @return
     */
    private List<SolicitudOficioBienDTO> consultarSolicitudOficioBien() {
        List<SolicitudOficioBienDTO> solicitudOficioBienDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT sob FROM SolicitudOficioBien sob");
        jpql.append(" WHERE sob.generaOficio IS NULL");

        Query query = em.createQuery(jpql.toString());

        @SuppressWarnings("unchecked")
        List<SolicitudOficioBien> solicitudOficioBiens = query.getResultList();
        if (solicitudOficioBiens != null && !solicitudOficioBiens.isEmpty()) {
            solicitudOficioBienDTOs = new ArrayList<>();
            for (SolicitudOficioBien solicitudOficioBien : solicitudOficioBiens) {
                SolicitudOficioBienDTO solicitudOficioBienDTO = SolicitudOficioBienHelper
                        .toLevel1DTO(solicitudOficioBien);
                solicitudOficioBienDTO.setSolicitudBienEntidadDTOs(
                        SolicitudBienEntidadHelper.toListLevel1DTO(solicitudOficioBien.getSolicitudBienEntidads()));
                solicitudOficioBienDTOs.add(solicitudOficioBienDTO);
            }
        }
        return solicitudOficioBienDTOs;
    }

    /**
     * Actualizar de la solicitud de bien
     * 
     * @author giovanni.velandia
     */
    private void actualizarSolicitudOficioBien(SolicitudOficioBienDTO solicitudOficioBienDTO) {
        em.merge(SolicitudOficioBienHelper.toLevel1Entity(solicitudOficioBienDTO, null));
    }

    /**
     * Registro de la solicitud de bien
     * 
     * @author giovanni.velandia
     */
    private void registrarSolicitudOficioBien(SolicitudOficioBienDTO solicitudOficioBienDTO) {

        // Fecha solicitud
        solicitudOficioBienDTO.setFechaSolicitud(Calendar.getInstance().getTime());
        SolicitudOficioBien solicitudOficioBien = SolicitudOficioBienHelper.toLevel1Entity(solicitudOficioBienDTO,
                null);
        em.persist(solicitudOficioBien);
        solicitudOficioBienDTO.setId(solicitudOficioBien.getId());
        // Solicitud bien entidad
        for (SolicitudBienEntidadDTO solicitudBienEntidadDTO : solicitudOficioBienDTO.getSolicitudBienEntidadDTOs()) {
            solicitudBienEntidadDTO.setSolicitudOficioBienDTO(solicitudOficioBienDTO);
            registrarSolicitudBienEntidad(solicitudBienEntidadDTO);
        }
    }

    /**
     * Registro coactivo oficio bien
     * 
     * @author giovanni.velandia
     */
    private void registrarSolicitudBienEntidad(SolicitudBienEntidadDTO SolicitudBienEntidadDTO) {
        SolicitudBienEntidad solicitudBienEntidad = SolicitudBienEntidadHelper.toLevel1Entity(SolicitudBienEntidadDTO,
                null);
        em.persist(solicitudBienEntidad);
    }

    /**
     * Consecutivo oficio bien
     * 
     * @author giovanni.velandia
     * @return
     */
    private String consecutivoOficioBien() {
        return iFachadaAdminGeneral.generarConsecutivo(
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                EnumConsecutivo.NUMERO_IMPUGNACION_ECUADOR);
    }

    /**
     * Crea un documento y lo agrega al proceso
     * 
     * @param tipoDocGenerado
     * @param traza
     * @param idCoactivo
     * @param tipoDocProceso
     * @param suma
     * @throws CirculemosAlertaException
     * @author julio.pinzon 2016-10-05
     */
    private DocumentoProcesoDTO registrarDocumentoCoactivo(EnumTipoDocumentoGenerado tipoDocGenerado,
            TrazabilidadProcesoDTO traza, Coactivo coactivo, EnumTipoDocumentoProceso tipoDocProceso,
            String responsableGeneracion) throws CirculemosAlertaException {
        // Genera documento
        GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
        generaDocumento.setFechaGeneracion(coactivo.getProceso().getFechaInicio());
        generaDocumento.setIdTipoDocumentoGenerado(tipoDocGenerado);
        Object[] valoresParametros = { coactivo.getId() };
        generaDocumento.setValoresParametros(valoresParametros);
        Map<String, Object> valoresVistaPreliminar = new HashMap<>();
        valoresVistaPreliminar.put(ConstantesDocumentosC2.TOTAL_LETRAS, coactivo.getValorTotalObligaciones());
        valoresVistaPreliminar.put(ConstantesDocumentosC2.VALOR_TOTAL, coactivo.getValorTotalObligaciones());
        generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
        Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);

        // Guarda el documento generado
        DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
        documentoProceso.setNumeroDocumento(idDocumento);
        documentoProceso.setTrazabilidadProceso(traza);
        TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
        tipoDocumento.setId(tipoDocProceso.getValue());
        documentoProceso.setTipoDocumento(tipoDocumento);
        // Responsable de la generacion de los documentos
        documentoProceso.setResponsableGeneracion(responsableGeneracion);
        documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);
        return documentoProceso;
    }

    /**
     * Crea un documento y lo agrega al proceso
     * 
     * @param tipoDocGenerado
     * @param traza
     * @param idCoactivo
     * @param tipoDocProceso
     * @param suma
     * @throws CirculemosAlertaException
     * @author julio.pinzon 2016-10-05
     */
    private DocumentoProcesoDTO registrarDocumentoSolicitudBien(EnumTipoDocumentoGenerado tipoDocGenerado,
            TrazabilidadProcesoDTO traza, OficioBienDTO oficioBienDTO, EnumTipoDocumentoProceso tipoDocProceso,
            String responsableGeneracion) throws CirculemosAlertaException {
        // Genera documento
        GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
        generaDocumento.setFechaGeneracion(oficioBienDTO.getFechaGeneracion());
        generaDocumento.setIdTipoDocumentoGenerado(tipoDocGenerado);
        Object[] valoresParametros = { oficioBienDTO.getId() };
        generaDocumento.setValoresParametros(valoresParametros);
        Long idDocumento = iRDocumentosCirculemos.generarDocumento(generaDocumento);

        // Guarda el documento generado
        DocumentoProcesoDTO documentoProceso = new DocumentoProcesoDTO();
        documentoProceso.setNumeroDocumento(idDocumento);
        documentoProceso.setTrazabilidadProceso(traza);
        TipoDocumentoProcesoDTO tipoDocumento = new TipoDocumentoProcesoDTO();
        tipoDocumento.setId(tipoDocProceso.getValue());
        documentoProceso.setTipoDocumento(tipoDocumento);
        // Responsable de la generacion de los documentos
        documentoProceso.setResponsableGeneracion(responsableGeneracion);
        documentoProceso = iRFachadaProceso.registrarDocumento(documentoProceso);
        return documentoProceso;
    }

    @Override
    public List<ObligacionCoactivoDTO> consultarObligacionesCoactivo(ObligacionCoactivoDTO obligacionCoactivoDTO) {
        logger.debug("CoactivoEJB.consultarObligacionesCoactivo(ObligacionCoactivoDTO)");
        List<ObligacionCoactivoDTO> lsResultado = null;

        GenericDao<ObligacionCoactivo> obligacionDao = new GenericDao<>(ObligacionCoactivo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT o FROM ObligacionCoactivo o");
        jpql.append(" WHERE 1=1");

        if (obligacionCoactivoDTO != null) {
            if (obligacionCoactivoDTO.getCoactivo() != null && obligacionCoactivoDTO.getCoactivo().getId() != null) {
                jpql.append(" AND o.coactivo.id = :idCoactivo");
                filtros.put("idCoactivo", obligacionCoactivoDTO.getCoactivo().getId());
            }

            if (obligacionCoactivoDTO.getNumeroObligacion() != null) {
                jpql.append(" AND o.numeroObligacion = :numeroObligacion");
                filtros.put("numeroObligacion", obligacionCoactivoDTO.getNumeroObligacion());
            }

            if (obligacionCoactivoDTO.getCodigoTipoObligacion() != null) {
                jpql.append(" AND o.codigoTipoObligacion = :codigoTipoObligacion");
                filtros.put("codigoTipoObligacion", obligacionCoactivoDTO.getCodigoTipoObligacion());
            }

            if (obligacionCoactivoDTO.getCartera() != null) {
                jpql.append(" AND o.cartera.id = :idCartera");
                filtros.put("idCartera", obligacionCoactivoDTO.getCartera().getId());
            }
        }

        List<ObligacionCoactivo> resultadoConsulta = obligacionDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            lsResultado = ObligacionCoactivoHelper.toListLevel1DTO(resultadoConsulta);
        }

        return lsResultado;
    }

    /**
     * genera el documento metodo provicional no cargar al repositorio
     * 
     * @param obligacionCoactivoDTO
     * @param coactivo
     * @return
     * @author Jeison.Rodriguez
     */
    public void generarDocumentosCoactivosAnteriores() {

        try {

            StringBuilder sql = new StringBuilder();

            sql.append("Set Language 'Español'");

            Query update = em.createNativeQuery(sql.toString());
            update.executeUpdate();

            // Consulta de coactivos
            sql = new StringBuilder();
            sql.append(
                    "SELECT c.id_proceso, c.id_coactivo, p.fecha_inicio, c.valor_total_obligaciones, p.numero_proceso, tp.id_trazabilidad_proceso , tp2.id_trazabilidad_proceso as traza2 ");
            sql.append("FROM coactivo c ");
            sql.append("JOIN proceso p ON p.id_proceso = c.id_proceso ");
            sql.append("JOIN trazabilidad_proceso tp ON p.id_proceso = tp.id_proceso ");
            sql.append("AND tp.id_estado_proceso = 22 ");
            sql.append("JOIN trazabilidad_proceso tp2 ON p.id_proceso = tp2.id_proceso ");
            sql.append("AND tp2.id_estado_proceso = 24 ");
            sql.append("WHERE NOT EXISTS (SELECT 1 FROM trazabilidad_proceso tp ");
            sql.append(
                    "WHERE EXISTS (select 1 from documento_proceso dp where dp.id_trazabilidad_proceso = tp.id_trazabilidad_proceso) ");
            sql.append("AND tp.id_proceso = p.id_proceso) ");
            sql.append("AND CAST(p.numero_proceso AS bigint) <= 6179  ");
            sql.append("ORDER BY c.id_coactivo");

            Query query = em.createNativeQuery(sql.toString());

            @SuppressWarnings("unchecked")
            List<Object[]> listaResultados = Utilidades.safeList(query.getResultList());

            for (Object[] filaCoactivo : listaResultados) {
                iLCoactivo.generarDocumentosAnteriores(filaCoactivo);
            }
        } catch (Exception e) {
            logger.error("Error en generación de documento de apertura de coactivo", e);

        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void generarDocumentosAnteriores(Object[] filaCoactivo)
            throws CirculemosNegocioException, CirculemosAlertaException {
        // Calendar fecha = new GregorianCalendar();
        ProcesoDTO proceso;

        // Consulta de traza
        proceso = new ProcesoDTO();
        proceso.setId(((BigInteger) filaCoactivo[0]).longValue());
        proceso.setFechaInicio((Date) filaCoactivo[2]);
        proceso.setNumeroProceso(String.valueOf(filaCoactivo[4]));
        TrazabilidadProcesoDTO trazaMand = new TrazabilidadProcesoDTO(((BigInteger) filaCoactivo[5]).longValue());
        TrazabilidadProcesoDTO trazaNotifica = new TrazabilidadProcesoDTO(((BigInteger) filaCoactivo[6]).longValue());

        Coactivo coactivo = new Coactivo();
        coactivo.setId(((BigInteger) filaCoactivo[1]).longValue());
        coactivo.setValorTotalObligaciones((BigDecimal) filaCoactivo[3]);
        coactivo.setProceso(ProcesoHelper.toLevel0Entity(proceso, null));

        // Genera documento apertura
        registrarDocumentoCoactivo(EnumTipoDocumentoGenerado.AUTO_PAGO, trazaMand, coactivo,
                EnumTipoDocumentoProceso.COACTIVO_AUTO_PAGO, null);
        // Genera documento posesion
        registrarDocumentoCoactivo(EnumTipoDocumentoGenerado.ACTA_DE_POSESION, trazaMand, coactivo,
                EnumTipoDocumentoProceso.COACTIVO_ACTA_DE_POSESION, null);

        // Genera documento notificacion
        // DocumentoProcesoDTO documentoProceso =
        registrarDocumentoCoactivo(EnumTipoDocumentoGenerado.NOTIFICACION_COACTIVO, trazaNotifica, coactivo,
                EnumTipoDocumentoProceso.COACTIVO_NOTIFICACION, null);

        // Guardar en FileSystem
        // List<Long> idsDocumento = new ArrayList<>();
        // idsDocumento.add(documentoProceso.getNumeroDocumento());
        // byte[] contenido = iRDocumentosCirculemos.consultarDocumentosPDF(idsDocumento);
        // ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(
        // String.format(NOMBRE_ARCHIVO_NOTIFICACION, proceso.getNumeroProceso()), contenido);
        // archivo.setRuta(String.format(RUTA_NOTIFICACIONES, fecha.get(Calendar.YEAR) + "",
        // (fecha.get(Calendar.MONTH) + 1) + ""));
        // ProcesadorArchivoFileSystem procesa = new ProcesadorArchivoFileSystem();
        // procesa.procesarArchivo(archivo);
    }

    /**
     * Consulta los coactivos y los envia al sevicio de axis
     * 
     * @throws CirculemosNegocioException
     */
    @SuppressWarnings("unchecked")
    public void consultarCoactivosXLS() throws CirculemosNegocioException {

        // consulta la tabla temporal de los 6000 coactivos
        StringBuilder sql = new StringBuilder();

        sql.append("select [TIPO DOC], [NUMERO DE IDENTIFICACIÓN], [valor multas], [titulo valor], proceso ");
        sql.append("from coactivos_xls ");
        sql.append("where id_tramite is null and numero_axis is null AND archivo is not null ");
        sql.append("AND [titulo valor] IN ('2186721','2187250','2187580','2186845')");

        Query query = em.createNativeQuery(sql.toString());

        List<Object[]> listaResultados = Utilidades.safeList(query.getResultList());

        Date fechaCoactivo = UtilFecha.buildCalendar().getTime();
        CoactivoDTO coactivoDTOs;
        TipoIdentificacionPersonaDTO tipoIdentificacion;
        List<ObligacionCoactivoDTO> obligacionesCoactivoDTO;
        PersonaDTO personaDTO;
        ProcesoDTO proceso;
        // persiste los resultados en coactivoDTO
        for (Object[] coactivo : listaResultados) {
            coactivoDTOs = new CoactivoDTO();
            // persiste el tipo de indetificacion en TipoIdentificacionPersonaDTO
            personaDTO = new PersonaDTO();
            proceso = new ProcesoDTO();
            tipoIdentificacion = new TipoIdentificacionPersonaDTO();
            obligacionesCoactivoDTO = new ArrayList<>();

            tipoIdentificacion.setCodigo((String) coactivo[0]);
            personaDTO.setTipoIdentificacion(tipoIdentificacion);
            coactivoDTOs.setPersona(personaDTO);
            coactivoDTOs.getPersona().setNumeroIdentificacion((String) coactivo[1]);

            proceso.setFechaInicio(fechaCoactivo);
            coactivoDTOs.setProceso(proceso);
            coactivoDTOs.setValorTotalObligaciones(BigDecimal
                    .valueOf(Double.valueOf(coactivo[2].toString().replaceAll("\\.", "").replaceAll(",", "."))));
            coactivoDTOs.setValorTotalCostasProcesales(
                    coactivoDTOs.getValorTotalObligaciones().multiply(BigDecimal.valueOf(0.05)));
            coactivoDTOs.getProceso().setObservacion("COACTIVO");
            String[] numeroFactura = coactivo[3].toString().split(",");
            // separa los numeros de factura cuando viene mas de uno.
            for (String nFactura : numeroFactura) {
                // consulta por numero de factura el el saldo a capital por cada factura
                sql = new StringBuilder();
                sql.append("select nombre, saldo_capital, saldo_interes ");
                sql.append("from cartera_coactivos_xls ");
                sql.append("where nombre = :nFactura");

                Query quer = em.createNativeQuery(sql.toString());

                quer.setParameter("nFactura", nFactura);

                List<Object[]> result = Utilidades.safeList(quer.getResultList());
                // persiste las obligaciones consultadas y las inserta en ObligacionCoactivoDTO
                ObligacionCoactivoDTO obligaciones;
                for (Object[] obligacion : result) {
                    obligaciones = new ObligacionCoactivoDTO();
                    obligaciones.setNumeroObligacion((String) obligacion[0]);
                    obligaciones.setValorObligacion(BigDecimal.valueOf(
                            Double.valueOf(obligacion[1].toString().replaceAll("\\.", "").replaceAll(",", "."))));
                    obligaciones.setValorInteresMoratorios(BigDecimal.valueOf(
                            Double.valueOf(obligacion[2].toString().replaceAll("\\.", "").replaceAll(",", "."))));
                    // Adiciona las obligaciones a una lista
                    obligacionesCoactivoDTO.add(obligaciones);
                }

            }
            coactivoDTOs.setObligacionCoactivos(obligacionesCoactivoDTO);
            try {
                iLCoactivo.registrarCoactivoAxis(coactivoDTOs, coactivo[4].toString());
            } catch (Exception e) {
                logger.error("Error al registrar coactivo 6000 :", e);
            }
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void registrarCoactivoAxis(CoactivoDTO coactivoDTOs, String proceso) throws CirculemosNegocioException {

        // Envia el coactivo al servicio de coactivo de axis
        coactivoDTOs = iRClienteWSAXIS.registarCoactivo(coactivoDTOs);

        // coactivoDTOs.setNumeroCoactivo("0001");
        // coactivoDTOs.setIdTramite(11111l);

        // Actualizar la tabla temporal coactivo xls
        StringBuilder sql = new StringBuilder();

        sql.append("update coactivos_xls set numero_axis = :numeroCoactivo , id_tramite = :idTramite ");
        sql.append("where proceso = :proceso ");

        Query update = em.createNativeQuery(sql.toString());
        update.setParameter("numeroCoactivo", coactivoDTOs.getNumeroCoactivo());
        update.setParameter("idTramite", coactivoDTOs.getIdTramite());
        update.setParameter("proceso", proceso);

        update.executeUpdate();
    }

    @Override
    @Asynchronous
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public Future<CargueArchivoDTO> cargarArchivoCoactivos(CargueArchivoDTO respuestaCargue,
            ArchivoTransportableDTO archivoTransportableDTO, ConfiguracionCoactivoDTO configuracionCoactivoDTO)
            throws CirculemosNegocioException {
        int totalLeidas = 0;
        int totalValidas = 0;
        int totalNoValidas = 0;
        Map<EnumVariableCondicionCoactivo, List<String>> valoresConfiguracion = null;
        if (configuracionCoactivoDTO != null) {
            if (valoresConfiguracion == null) {
                valoresConfiguracion = obtenerValoresConfiguracion(configuracionCoactivoDTO);
            }
        }
        // Genera cache de errores
        Map<Integer, String> cacheErrores = new HashMap<Integer, String>();
        List<ItemCatalogoDTO> erroresCoactivo = iFachadaConfiguracion
                .consultarItemsCatalogo(EnumCatalogo.ErrorGeneraCoactivo.name(), null);
        for (ItemCatalogoDTO errorCoactivo : erroresCoactivo) {
            cacheErrores.put(errorCoactivo.getId(), errorCoactivo.getDescripcion());
        }

        Boolean agruparObligaciones = Boolean
                .valueOf(valoresConfiguracion.get(EnumVariableCondicionCoactivo.AGRUPAR_OBLIGACIONES).get(0));
        BigDecimal valorMinimo = BigDecimal
                .valueOf(Long.valueOf(valoresConfiguracion.get(EnumVariableCondicionCoactivo.VALOR_MINIMO).get(0)));
        try (ByteArrayInputStream fis = new ByteArrayInputStream(archivoTransportableDTO.getContenido());
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr)) {
            String linea = br.readLine();

            Map<Long, List<ValidacionPrecoactivoDTO>> multasValidas = new LinkedHashMap<Long, List<ValidacionPrecoactivoDTO>>();
            Map<String, String> multasNoValidas = new LinkedHashMap<String, String>();
            Set<String> facturas = new HashSet<String>();
            while ((linea = br.readLine()) != null) {
                try {
                    totalLeidas++;
                    if (StringUtils.isNotBlank(linea)) {
                        if (!facturas.contains(linea)) {
                            facturas.add(linea);
                            // Valida que los datos de la factura esten correctos
                            Map<ValidacionPrecoactivoDTO, RespuestaDTO<EnumCampoObligacionCoactivo, EnumErrorGeneraCoactivo>> validacion = validarFacturaCoactivo(
                                    linea.trim(), configuracionCoactivoDTO, valoresConfiguracion, true);
                            ValidacionPrecoactivoDTO validacionPre = validacion.keySet().iterator().next();
                            PrecoactivoDTO precoactivoDTO = validacionPre.getPrecoactivo();
                            RespuestaDTO<EnumCampoObligacionCoactivo, EnumErrorGeneraCoactivo> respuesta = validacion
                                    .values().iterator().next();
                            if (agruparObligaciones == null) {
                                agruparObligaciones = Boolean.valueOf(valoresConfiguracion
                                        .get(EnumVariableCondicionCoactivo.AGRUPAR_OBLIGACIONES).get(0));
                            }
                            if (respuesta.getRespuesta().equals(EnumRespuestaSistema.OK)) {
                                if (agruparObligaciones) {
                                    if (multasValidas.containsKey(precoactivoDTO.getPersona().getId())) {
                                        multasValidas.get(precoactivoDTO.getPersona().getId()).add(validacionPre);
                                    } else {
                                        List<ValidacionPrecoactivoDTO> obligaciones = new ArrayList<ValidacionPrecoactivoDTO>();
                                        obligaciones.add(validacionPre);
                                        multasValidas.put(precoactivoDTO.getPersona().getId(), obligaciones);
                                    }
                                } else {
                                    List<ValidacionPrecoactivoDTO> obligaciones = new ArrayList<ValidacionPrecoactivoDTO>();
                                    obligaciones.add(validacionPre);
                                    multasValidas.put(Long.valueOf(totalLeidas), obligaciones);
                                }
                            } else {
                                // Adicion de multas que tienen errores
                                multasNoValidas.put(precoactivoDTO.getNumeroObligacion(),
                                        cacheErrores.get(respuesta.getErrorRespuesta().getValue()));
                                totalNoValidas++;
                            }
                        } else {
                            // Adicion de multas que tienen errores
                            multasNoValidas.put("'" + linea + "'",
                                    cacheErrores.get(EnumErrorGeneraCoactivo.FACTURA_REPETIDA_ARCHIVO.getValue()));
                            totalNoValidas++;
                        }
                    } else {
                        // Adicion de multas que tienen errores
                        multasNoValidas.put("'" + linea + "'", EnumErrorGeneraCoactivo.CAMPOS_VACIOS.getDescripcion());
                        totalNoValidas++;
                    }
                } catch (Exception e) {
                    // Adicion de multas que tienen errores
                    multasNoValidas.put("'" + linea + "'", EnumErrorGeneraCoactivo.ERROR_INESPERADO.getDescripcion());
                    totalNoValidas++;
                    logger.error("Error en validacion de coactivos", e);
                }

                respuestaCargue.setTotalInconsistencias(totalNoValidas);
                respuestaCargue.setTotalLeidos(totalValidas);
                // Actualiza el cargue cada 10 registros
                if (totalLeidas % 10 == 0) {
                    iRFachadaAdminNegocio.registrarRegistroProcesado(respuestaCargue);
                }
            }

            if (valorMinimo == null) {
                valorMinimo = BigDecimal.valueOf(
                        Long.valueOf(valoresConfiguracion.get(EnumVariableCondicionCoactivo.VALOR_MINIMO).get(0)));
            }
            List<ValidacionPrecoactivoDTO> precoactivosValidados = new ArrayList<ValidacionPrecoactivoDTO>();
            List<PrecoactivoDTO> precoactivosRegistrar = new ArrayList<PrecoactivoDTO>();
            int index = 0;
            // Validar contra configuracion de coactivo
            for (Map.Entry<Long, List<ValidacionPrecoactivoDTO>> entry : multasValidas.entrySet()) {
                BigDecimal valorTotalObligaciones = BigDecimal.ZERO;
                for (ValidacionPrecoactivoDTO obligacion : entry.getValue()) {
                    valorTotalObligaciones = valorTotalObligaciones
                            .add(obligacion.getPrecoactivo().getValorObligacion());
                }
                if (valorMinimo.compareTo(valorTotalObligaciones) < 0) {
                    for (ValidacionPrecoactivoDTO obligacion : entry.getValue()) {
                        precoactivosValidados.add(obligacion);
                        precoactivosRegistrar.add(obligacion.getPrecoactivo());
                        totalValidas++;
                    }
                } else {
                    for (ValidacionPrecoactivoDTO obligacion : entry.getValue()) {
                        multasNoValidas.put(obligacion.getPrecoactivo().getNumeroObligacion(),
                                cacheErrores.get(EnumErrorGeneraCoactivo.VALOR_MINIMO_COACTIVO.getValue()));
                        totalNoValidas++;
                    }
                }

                respuestaCargue.setTotalInconsistencias(totalNoValidas);
                respuestaCargue.setTotalLeidos(totalValidas);
                // Actualiza el cargue cada 10 registros
                if (index % 10 == 0) {
                    iRFachadaAdminNegocio.registrarRegistroProcesado(respuestaCargue);
                }
                index++;
            }

            // Genera archivo de multas validas, si las hay
            if (!precoactivosValidados.isEmpty()) {
                ArchivoTransportableDTO archivoValidas = new ArchivoTransportableDTO();
                archivoValidas
                        .setNombre(ConstantesCargaArchivos.NOMBRE_MULTAS_VALIDAS + archivoTransportableDTO.getNombre());
                StringBuilder sbValidos = new StringBuilder();

                for (ValidacionPrecoactivoDTO precoactivo : precoactivosValidados) {
                    sbValidos.append(precoactivo.toString());
                    sbValidos.append(ConstantesCargaArchivos.getSeparadorLineaSistema());
                }
                archivoValidas.setContenido(sbValidos.toString().getBytes(StandardCharsets.UTF_8));
                if (archivoValidas != null) {
                    String idDocumentoCargueDir = irRepositorioArchivo.registrarDocumento(
                            EnumCategoriaDocumento.ARCHIVO_CARGUE_COACTIVOS_VALIDOS, archivoValidas);
                    DocumentoResultadoCargueDTO docResulDTO = new DocumentoResultadoCargueDTO();
                    docResulDTO.setCargueArchivo(respuestaCargue);
                    docResulDTO.setIdDocumentoResultado(Long.parseLong(idDocumentoCargueDir));
                    docResulDTO.setTipoDocumentoResultadoCargue(new TipoDocumentoResultadoCargueDTO(
                            EnumTipoDocumentoResulCargue.SIN_IDENTIFICAR.getValue()));
                    docResulDTO = iRFachadaAdminNegocio.registrarDocumentoResulCargue(docResulDTO);
                }
                // Registra los precoactivos con estado validados
                CargueCoactivoDTO cargueCoactivoDTO = new CargueCoactivoDTO();
                cargueCoactivoDTO.setConfiguracionCoactivo(configuracionCoactivoDTO);
                cargueCoactivoDTO.setCargueArchivo(respuestaCargue);
                cargueCoactivoDTO.setPrecoactivos(precoactivosRegistrar);
                iLCoactivo.registrarCarguePrecoactivos(cargueCoactivoDTO);
            }

            // Genera archivo de inconsistencias, si las hay
            if (!multasNoValidas.isEmpty()) {
                ArchivoTransportableDTO archivoInconsistencias = new ArchivoTransportableDTO();
                archivoInconsistencias.setNombre(
                        ConstantesCargaArchivos.NOMBRE_INCONSISTENCIAS + archivoTransportableDTO.getNombre());
                StringBuilder sbInconsistencias = new StringBuilder();

                for (Map.Entry<String, String> entry : multasNoValidas.entrySet()) {
                    sbInconsistencias.append(entry.getKey());
                    sbInconsistencias.append(ConstantesCargaArchivos.CSV_SEPARADOR);
                    sbInconsistencias.append(entry.getValue());
                    sbInconsistencias.append(ConstantesCargaArchivos.getSeparadorLineaSistema());
                }
                archivoInconsistencias.setContenido(sbInconsistencias.toString().getBytes(StandardCharsets.UTF_8));

                String codigoDoc = irRepositorioArchivo.registrarDocumento(
                        EnumCategoriaDocumento.ARCHIVO_CARGUE_COACTIVOS_INCONSISTENCIAS, archivoInconsistencias);
                respuestaCargue.setIdDocumentoInconsistencias(Long.valueOf(codigoDoc));
            }
            respuestaCargue.setTotalInconsistencias(totalNoValidas);
            respuestaCargue.setTotalLeidos(totalValidas);
            respuestaCargue
                    .setEstadoCargueArchivo(new EstadoCargueArchivoDTO(EnumEstadoCargueArchivo.PROCESADO.getValue()));
            respuestaCargue
                    .setTotalRegistros(respuestaCargue.getTotalLeidos() + respuestaCargue.getTotalInconsistencias());
            iRFachadaAdminNegocio.cerrarCargueArchivo(respuestaCargue);

        } catch (IOException | CirculemosAlertaException e) {
            logger.error("Error al procesar el cargue analisis cartera", e);
            throw new CirculemosNegocioException(CargueAnalisisCoactivo.COAC_009003);
        }
        return new AsyncResult<CargueArchivoDTO>(respuestaCargue);
    }

    /**
     * Valida informacion basica de la factura para determinar si es posible ingresarla en coactivo
     * 
     * @param numeroFactura
     * @param configuracionCoactivoDTO
     * @param valoresConfiguracion
     * @param esCargue
     * @return Map con un PrecoactivoDTO y la respuesta de la validacion
     * @author Dixon.Alvarez
     */
    private Map<ValidacionPrecoactivoDTO, RespuestaDTO<EnumCampoObligacionCoactivo, EnumErrorGeneraCoactivo>> validarFacturaCoactivo(
            String numeroFactura, ConfiguracionCoactivoDTO configuracionCoactivoDTO,
            Map<EnumVariableCondicionCoactivo, List<String>> valoresConfiguracion, boolean esCargue) {
        logger.debug(
                "CoactivoEJB.validarFacturaCoactivo(String, ConfiguracionCoactivoDTO, Map<EnumVariableCondicionCoactivo, List<String>>,boolean)");
        Map<ValidacionPrecoactivoDTO, RespuestaDTO<EnumCampoObligacionCoactivo, EnumErrorGeneraCoactivo>> resultado = new HashMap<ValidacionPrecoactivoDTO, RespuestaDTO<EnumCampoObligacionCoactivo, EnumErrorGeneraCoactivo>>();
        RespuestaDTO<EnumCampoObligacionCoactivo, EnumErrorGeneraCoactivo> respuesta = new RespuestaDTO<EnumCampoObligacionCoactivo, EnumErrorGeneraCoactivo>(
                false);
        PrecoactivoDTO precoactivoDTO = new PrecoactivoDTO();
        ValidacionPrecoactivoDTO validacion = new ValidacionPrecoactivoDTO();
        precoactivoDTO.setNumeroObligacion(numeroFactura);
        CarteraDTO carteraDTO = null;
        TipoObligacionDTO tipoObligacionDTO = new TipoObligacionDTO();

        if (configuracionCoactivoDTO != null) {
            if (valoresConfiguracion == null) {
                valoresConfiguracion = obtenerValoresConfiguracion(configuracionCoactivoDTO);
            }
        }

        ValorParametroDTO formatoFecha = iFachadaAdminGeneral.consultarValorParametro(EnumParametro.FORMATO_FECHA,
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(), true);
        SimpleDateFormat formatoLargo = new SimpleDateFormat(formatoFecha.getValorParam());
        formatoLargo.setLenient(false);

        Boolean obligacionesNoNotificadas = Boolean.valueOf(valoresConfiguracion
                .get(EnumVariableCondicionCoactivo.GENERAR_COACTIVO_OBLIGACIONES_NO_NOTIFICADAS).get(0));

        // Consulta si el la factura existe en comparendo
        ComparendoDTO comparendoDTO = iRFachadaComparendo.consultarComparendo(numeroFactura,
                seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());

        if (comparendoDTO != null) {
            // Si la condicion "Generar coactivos a obligaciones no notificadas" tiene como valor FALSE
            // Valida si el comparendo ya fue notificado de lo contrario genera error
            if (obligacionesNoNotificadas
                    || (!obligacionesNoNotificadas && comparendoDTO.getFechaNotificacion() != null)) {
                // Valida si el comparendo tiene un proceso abierto de coactivo
                boolean enCoactivo = false;
                if (comparendoDTO.getComparendoProcesoList() != null
                        && !comparendoDTO.getComparendoProcesoList().isEmpty()) {
                    cicloProceso: for (ComparendoProcesoDTO comparendoProcesoDTO : comparendoDTO
                            .getComparendoProcesoList()) {
                        ProcesoDTO procesoDTO = new ProcesoDTO();
                        procesoDTO.setId(comparendoProcesoDTO.getIdProceso());
                        procesoDTO.setTipoProceso(new TipoProcesoDTO(EnumTipoProceso.COACTIVO.getValue()));
                        List<ProcesoDTO> lsProcesoDTO = iRFachadaProceso.consultarProceso(procesoDTO);
                        if (lsProcesoDTO != null && !lsProcesoDTO.isEmpty()) {
                            for (ProcesoDTO procesoDTO2 : lsProcesoDTO) {
                                if (procesoDTO2.getFechaFin() == null) {
                                    enCoactivo = true;
                                    break cicloProceso;
                                }
                            }
                        }
                    }
                }
                if (!enCoactivo) {
                    // valida si el comparendo se encuentra en estado vigente
                    if (comparendoDTO.getEstadoComparendo().getId().equals(EnumEstadoComparendo.VIGENTE.getValue())) {
                        carteraDTO = new CarteraDTO();
                        tipoObligacionDTO.setId(EnumTipoObligacion.COMPARENDO.getValue());
                        carteraDTO.setNombre(numeroFactura);
                        carteraDTO.setTipoObligacion(tipoObligacionDTO);
                        // Consulta la cartera
                        carteraDTO = iRFachadaCartera.consultarCartera(carteraDTO);
                        // Valida si existen datos en cartera
                        if (carteraDTO != null) {
                            // valida si la cartera se encuntra activa
                            if (carteraDTO.getEstadoObligacion().getId()
                                    .equals(EnumEstadoObligacion.ACTIVO.getValue())) {
                                // Consultamos si esta registrado como precoactivo
                                PrecoactivoDTO filtroPrecoactivo = new PrecoactivoDTO();
                                filtroPrecoactivo.setNumeroObligacion(numeroFactura);
                                List<PrecoactivoDTO> precoactivos = iLCoactivo.consultarPrecoactivo(filtroPrecoactivo,
                                        null);
                                boolean estaPrecoactivo = false;
                                if (precoactivos != null) {
                                    for (PrecoactivoDTO precoactivoDTO2 : precoactivos) {
                                        if (!precoactivoDTO2.getEstadoPrecoactivo().getId()
                                                .equals(EnumEstadoPrecoactivo.VALIDADO.getValue())) {
                                            estaPrecoactivo = true;
                                            break;
                                        } else if (esCargue) {
                                            // En caso que un cargue haya fallado
                                            iLCoactivo.modificarCarguePrecoactivos(
                                                    new CargueArchivoDTO(precoactivoDTO2.getCargueCoactivo().getId()),
                                                    false);
                                        }
                                    }
                                }
                                if (!esCargue || !estaPrecoactivo) {
                                    // consulta la persona
                                    PersonaDTO personaDTO = iRFachadaAdminNegocio
                                            .consultarPersona(carteraDTO.getIdDeudor());
                                    precoactivoDTO.setPersona(personaDTO);

                                    DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
                                    direccionPersonaDTO.setPersona(personaDTO);
                                    List<DireccionPersonaDTO> direccionPersonaDTOs = iRFachadaAdminNegocio
                                            .consultarDireccionPersona(direccionPersonaDTO);
                                    DireccionPersonaDTO direccion = null;
                                    if (direccionPersonaDTOs != null && !direccionPersonaDTOs.isEmpty()) {
                                        // reversa la lista para que recorra de la mas actualizada a la menos
                                        Collections.reverse(direccionPersonaDTOs);
                                        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> respuestaDTO;
                                        // recorre la lista de direcciones
                                        for (DireccionPersonaDTO direccionPerDTO : direccionPersonaDTOs) {
                                            respuestaDTO = new RespuestaDTO<>();
                                            // valida si la direccion es correcta
                                            respuestaDTO = iFachadaAdminGeneral.validarDireccion(
                                                    direccionPerDTO.getDireccion(),
                                                    EnumTipoValidacionDireccion.INFRACTOR);
                                            // si la direccion es correcta no recorra mas direcciones
                                            if (respuestaDTO.getRespuesta().equals(EnumRespuestaSistema.OK)) {
                                                direccion = direccionPerDTO;
                                            }
                                        }
                                    }

                                    precoactivoDTO.setCodigoTipoObligacion(EnumTipoObligacion.COMPARENDO.getValue());
                                    precoactivoDTO.setValorObligacion(carteraDTO.getSaldoCapital());
                                    precoactivoDTO.setValorInteresMoratorios(carteraDTO.getSaldoInteres());

                                    precoactivoDTO.setPersona(personaDTO);
                                    precoactivoDTO.setFechaObligacion(comparendoDTO.getFechaHoraImposicion());
                                    precoactivoDTO.setCartera(carteraDTO);
                                    precoactivoDTO.setEstadoPrecoactivo(
                                            new EstadoPrecoactivoDTO(EnumEstadoPrecoactivo.VALIDADO.getValue()));
                                    if (direccion != null) {
                                        precoactivoDTO.setDireccion(direccion.getDireccion());
                                    }

                                    // Completa el objeto de validacion
                                    if (esCargue) {
                                        validacion.setNumeroCitacion(comparendoDTO.getNumeroCitacion());
                                        validacion.setIdFacturaAxis(comparendoDTO.getIdFacturaAxis());
                                        validacion.setFechaInfraccion(
                                                formatoLargo.format(comparendoDTO.getFechaHoraImposicion()));
                                        if (comparendoDTO.getFechaNotificacion() != null) {
                                            validacion.setFechaNotificacion(
                                                    formatoLargo.format(comparendoDTO.getFechaNotificacion()));
                                        }
                                        validacion.setMedioImposicion(comparendoDTO.getMedioImposicion().getNombre());
                                        validacion.setCodigoInfraccion(comparendoDTO.getInfraccion().getCodigo());
                                        ConfiguracionInfraccionDTO configInf = iRFachadaComparendo
                                                .consultarConfiguracionInfraccion(
                                                        comparendoDTO.getInfraccion().getCodigo(),
                                                        comparendoDTO.getFechaHoraImposicion());
                                        validacion.setDescripcionInfraccion(configInf.getDescripcion());
                                        validacion.setValorMulta(carteraDTO.getSaldoCapital());
                                        if (direccion != null) {
                                            validacion.setDireccion(direccion.getDireccion().getComplemento());
                                        }
                                        validacion.setNombreCompleto(personaDTO.getNombreCompleto());
                                        validacion.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
                                        validacion
                                                .setTipoIdentificacion(personaDTO.getTipoIdentificacion().getCodigo());
                                    }

                                    respuesta.setRespuesta(EnumRespuestaSistema.OK);
                                } else {
                                    respuesta.setErrorRespuesta(EnumErrorGeneraCoactivo.EXISTE_PRECOACTIVO);
                                    respuesta.setDescripcionRespuesta(
                                            EnumErrorGeneraCoactivo.EXISTE_PRECOACTIVO.getDescripcion());
                                }
                            } else {
                                respuesta.setErrorRespuesta(EnumErrorGeneraCoactivo.ESTADO_NO_ACTIVO);
                                respuesta.setDescripcionRespuesta(
                                        EnumErrorGeneraCoactivo.ESTADO_NO_ACTIVO.getDescripcion());
                            }
                        } else {
                            respuesta.setErrorRespuesta(EnumErrorGeneraCoactivo.CARTERA_NO_EXISTE);
                            respuesta.setDescripcionRespuesta(
                                    EnumErrorGeneraCoactivo.CARTERA_NO_EXISTE.getDescripcion());
                        }
                    } else {
                        respuesta.setErrorRespuesta(EnumErrorGeneraCoactivo.ESTADO_NO_VIGENTE);
                        respuesta.setDescripcionRespuesta(EnumErrorGeneraCoactivo.ESTADO_NO_VIGENTE.getDescripcion());
                    }
                } else {
                    respuesta.setErrorRespuesta(EnumErrorGeneraCoactivo.EXISTE_COACTIVO);
                    respuesta.setDescripcionRespuesta(EnumErrorGeneraCoactivo.EXISTE_COACTIVO.getDescripcion());
                }
            } else {
                respuesta.setErrorRespuesta(EnumErrorGeneraCoactivo.OBLIGACION_NO_NOTIFICADA);
                respuesta.setDescripcionRespuesta(EnumErrorGeneraCoactivo.OBLIGACION_NO_NOTIFICADA.getDescripcion());
            }
        } else {
            respuesta.setErrorRespuesta(EnumErrorGeneraCoactivo.NUMERO_FACTURA_NO_EXISTE);
            respuesta.setDescripcionRespuesta(EnumErrorGeneraCoactivo.NUMERO_FACTURA_NO_EXISTE.getDescripcion());
        }
        validacion.setPrecoactivo(precoactivoDTO);

        resultado.put(validacion, respuesta);

        return resultado;
    }

    @Override
    public List<PrecoactivoDTO> consultarPrecoactivo(PrecoactivoDTO precoactivo, List<EnumEstadoPrecoactivo> estados) {
        logger.debug("CoactivoEJB.consultarPrecoactivo(PrecoactivoDTO, List<EnumEstadoPrecoactivo>)");
        List<PrecoactivoDTO> lsResultado = null;

        GenericDao<Precoactivo> obligacionDao = new GenericDao<>(Precoactivo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT p FROM Precoactivo p ");
        jpql.append("WHERE 1=1");

        if (precoactivo != null) {
            if (precoactivo.getId() != null) {
                jpql.append(" AND p.id = :idPrecoactivo");
                filtros.put("idPrecoactivo", precoactivo.getId());
            }

            if (precoactivo.getPersona() != null) {
                jpql.append(" AND p.persona.id = :idDeudor");
                filtros.put("idDeudor", precoactivo.getPersona().getId());
            }

            if (StringUtils.isNotBlank(precoactivo.getNumeroObligacion())) {
                jpql.append(" AND p.numeroObligacion = :numeroObligacion");
                filtros.put("numeroObligacion", precoactivo.getNumeroObligacion());
            }
        }
        if (estados != null && !estados.isEmpty()) {
            List<Integer> idEstados = new ArrayList<>();
            for (EnumEstadoPrecoactivo enumEstadoPrecoactivo : estados) {
                idEstados.add(enumEstadoPrecoactivo.getValue());
            }
            jpql.append(" AND p.estadoPrecoactivo.id IN (:estados)");
            filtros.put("estados", idEstados);
        }

        List<Precoactivo> resultadoConsulta = obligacionDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            lsResultado = PrecoactivoHelper.toListLevel1DTO(resultadoConsulta);
        }

        return lsResultado;
    }

    @Override
    public List<AprobacionPrecoactivoDTO> consultarPrecoactivoAprobacion() {
        logger.debug("CoactivoEJB.consultarPrecoactivoAprobacion()");
        List<AprobacionPrecoactivoDTO> lsResultado = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        sql.append(" SELECT p.id_precoactivo, c.numero_citacion, c.id_factura_axis,");
        sql.append(" c.fecha_infraccion, c.fecha_notificacion,");
        sql.append(" mic.nombre,");
        sql.append(" ifr.codigo_infraccion,");
        sql.append(" ci.descripcion, car.saldo_capital,");
        sql.append(" d.complemento AS direccion,");
        sql.append(" tip.id_tipo_identificacion, per.nombre1, per.nombre2,");
        sql.append(" per.apellido1, per.apellido2, perj.nombre_comercial,");
        sql.append(" per.numero_identificacion, tip.codigo");
        sql.append(" FROM precoactivo p");
        sql.append(" LEFT JOIN direccion d ON d.id_direccion = p.id_direccion");
        sql.append(" JOIN comparendo c ON c.id_factura_axis = p.numero_obligacion");
        sql.append(" JOIN medio_imposicion_comparendo mic ON mic.codigo_medio_imposicion = c.codigo_medio_imposicion");
        sql.append(" JOIN infraccion ifr ON ifr.id_infraccion = c.id_infraccion");
        sql.append(" JOIN configuracion_infraccion ci ON ci.id_infraccion = ifr.id_infraccion");
        sql.append(" JOIN cartera car ON p.id_cartera=car.id_cartera");
        sql.append(" JOIN persona per ON per.id_persona=p.id_deudor");
        sql.append(" LEFT JOIN persona_juridica perj ON perj.id_persona_juridica = per.id_persona");
        sql.append(" JOIN tipo_identificacion_persona tip ON tip.id_tipo_identificacion=per.id_tipo_identificacion");
        sql.append(" WHERE p.id_estado_precoactivo = :idEstado");

        filtros.put("idEstado", EnumEstadoPrecoactivo.CARGADO.getValue());

        Query query = em.createNativeQuery(sql.toString());

        if (!filtros.isEmpty()) {
            for (Map.Entry<String, Object> param : filtros.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        @SuppressWarnings("unchecked")
        List<Object[]> objects = query.getResultList();

        if (objects != null && !objects.isEmpty()) {
            for (Object[] object : objects) {
                AprobacionPrecoactivoDTO aprobacion = new AprobacionPrecoactivoDTO();
                aprobacion.setIdPrecoactivo(Long.parseLong(object[0].toString()));
                aprobacion.setNumeroCitacion((object[1] == null ? null : object[1].toString()));
                aprobacion.setIdFacturaAxis(Long.parseLong(object[2].toString()));
                aprobacion.setFechaInfraccion((Date) object[3]);
                aprobacion.setFechaNotificacion((Date) object[4]);
                aprobacion.setMedioImposicion(object[5].toString());
                aprobacion.setCodigoInfraccion(object[6].toString());
                aprobacion.setDescripcionInfraccion(object[7].toString());
                aprobacion.setValorMulta(new BigDecimal(object[8].toString()));
                aprobacion.setDireccion((object[9] == null ? null : object[9].toString()));

                // Tipo de identificacion

                // Nombre completo
                StringBuilder nombreCompleto = new StringBuilder();
                // Tipo de identificacion, validacion
                Integer tipoIdentificacion = (Integer.parseInt(object[10].toString()));
                if (!tipoIdentificacion.equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor())) {
                    // Nombre 1
                    if ((object[11] == null ? null : object[11].toString()) != null) {
                        nombreCompleto.append(object[11].toString());
                    }
                    // Nombre 2
                    if ((object[12] == null ? null : object[12].toString()) != null) {
                        nombreCompleto.append(" ");
                        nombreCompleto.append(object[12].toString());
                    }
                    // Apellido 1
                    if ((object[13] == null ? null : object[13].toString()) != null) {
                        nombreCompleto.append(" ");
                        nombreCompleto.append(object[13].toString());
                    }
                    // Apellido 2
                    if ((object[14] == null ? null : object[14].toString()) != null) {
                        nombreCompleto.append(" ");
                        nombreCompleto.append(object[14].toString());
                    }
                } else {
                    // Razon Social
                    if ((object[15] == null ? null : object[15].toString()) != null) {
                        nombreCompleto.append(object[15].toString());
                    }
                }
                aprobacion.setNombreCompleto(nombreCompleto.toString());

                aprobacion.setNumeroIdentificacion(object[16].toString());
                aprobacion.setTipoIdentificacion(object[17].toString());

                lsResultado.add(aprobacion);
            }
        }

        return lsResultado;
    }

    @Override
    public void autorizarPrecoactivos(List<AprobacionPrecoactivoDTO> precoactivos, FuncionarioDTO secretario) {
        logger.debug("CoactivoEJB.cambiarEstadoPrecoactivos(List<AprobacionPrecoactivoDTO>)");
        UsuarioPersona usuarioPersona = null;
        UsuarioPersonaDTO usuarioPersonaDTO = seguridadCirculemosEJB.obtenerUsuarioDto();
        if (usuarioPersonaDTO.getLogin() != null || usuarioPersonaDTO.getPersona() != null) {
            usuarioPersona = UsuarioPersonaHelper.toLevel1Entity(usuarioPersonaDTO, null);
        }
        Date fechaActual = new Date();
        for (AprobacionPrecoactivoDTO aprobacionPrecoactivoDTO : precoactivos) {
            // Actualiza estado del precoactivo
            Precoactivo precoactivo = em.find(Precoactivo.class, aprobacionPrecoactivoDTO.getIdPrecoactivo());
            precoactivo.setEstadoPrecoactivo(new EstadoPrecoactivo(EnumEstadoPrecoactivo.AUTORIZADO.getValue()));
            precoactivo.setFuncionario(FuncionarioHelper.toLevel0Entity(secretario, null));
            em.merge(precoactivo);

            // Actualiza datos del cargue
            if (precoactivo.getCargueCoactivo().getFechaAprobacion() == null) {
                precoactivo.getCargueCoactivo().setFechaAprobacion(fechaActual);
                precoactivo.getCargueCoactivo().setUsuarioAprobacion(usuarioPersona);
                em.merge(precoactivo.getCargueCoactivo());
            }
        }

    }

    @Override
    public CargueNotificaCoactivoResulDTO procesarArchivoNotificacionesCoactivos(ArchivoTransportableDTO archivoDTO)
            throws CirculemosNegocioException {
        logger.debug("CoactivoEJB.procesarArchivoNotificacionesCoactivos(ArchivoTransportableDTO)");
        try {
            DateFormat df = new SimpleDateFormat(FORMATO_FECHA_ARCHIVO);
            String dateStr = df.format(Utilidades.getFechaActual());

            File xlsFile = new File(NOMBRE_ARCHIVO_NOTIFICACIONES + dateStr + EXTENSION_CSV);
            FileUtils.writeByteArrayToFile(xlsFile, archivoDTO.getContenido());

            validarEstructuraArchivoNotif(xlsFile);

            CargueCourierDTO cargueCourier = new CargueCourierDTO();
            cargueCourier.setFechaCargue(Utilidades.getFechaActual());
            UsuarioPersonaDTO usuario = iRSeguridadCirculemos.obtenerUsuarioDto();
            cargueCourier.setUsuarioCargue(usuario);
            try {
                GestorArchivosOpciones[] opciones = new GestorArchivosOpciones[0];
                String idDocumentoCargue = irRepositorioArchivo
                        .registrarDocumento(EnumCategoriaDocumento.NOTIFICACION_COACTIVO_COURIER, archivoDTO, opciones);
                cargueCourier.setIdDocumentoCargue(Long.parseLong(idDocumentoCargue));
            } catch (CirculemosAlertaException e1) {
                throw new CirculemosNegocioException(ErrorCoactivo.NotificarCoactivoCourier.COAC_003003);
            }

            String consecutivo = iFachadaAdminGeneral.generarConsecutivo(
                    iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                    EnumConsecutivo.NOTIFICACION_COURIER_COACTIVO_ECUADOR);
            cargueCourier.setConsecutivo(consecutivo);
            CargueCourier cargueCourierEnt = CargueCourierHelper.toLevel0Entity(cargueCourier, null);
            em.persist(cargueCourierEnt);
            cargueCourier = CargueCourierHelper.toLevel0DTO(cargueCourierEnt);

            StringBuilder sbArchivoIncosistencias = new StringBuilder();
            CargueNotificaCoactivoResulDTO respuestaCargue = new CargueNotificaCoactivoResulDTO();
            validarDatosArchivoNotif(xlsFile, cargueCourier, sbArchivoIncosistencias, respuestaCargue);

            ArchivoTransportableDTO reporteInconsistencias = new ArchivoTransportableDTO();
            reporteInconsistencias.setNombre(NOMBRE_ARCHIVO_INCONSISTENCIAS + dateStr + EXTENSION_CSV);
            reporteInconsistencias.setContenido(sbArchivoIncosistencias.toString().getBytes());
            respuestaCargue.setReporteInconsistencias(reporteInconsistencias);

            return respuestaCargue;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Procesa y valida los datos del archivo de notificaciones de coactivos
     * 
     * @param xlsFile
     *            archivo a procesar
     * @param cargueCourier
     *            información general del cargue
     * @param sbArchivoIncosistencias
     *            buffer del archivo de inconsistencias
     * @param respuestaCargue
     *            objeto de respuesta
     * @throws CirculemosNegocioException
     */
    private void validarDatosArchivoNotif(File xlsFile, CargueCourierDTO cargueCourier,
            StringBuilder sbArchivoIncosistencias, CargueNotificaCoactivoResulDTO respuestaCargue)
            throws CirculemosNegocioException {
        logger.debug(
                "CoactivoEJB.validarDatosArchivoNotif(File,CargueCourierDTO,StringBuilder,CargueNotificaCoactivoResulDTO)");
        Map<CoactivoDTO, DireccionPersonaDTO> coactivosDireccion = new HashMap<CoactivoDTO, DireccionPersonaDTO>();

        ValorParametroDTO fomatoFecha = iFachadaAdminGeneral.consultarValorParametro(EnumParametro.FORMATO_FECHA,
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(), true);
        SimpleDateFormat formatter = new SimpleDateFormat(fomatoFecha.getValorParam().split(" ")[0]);
        formatter.setLenient(false);
        final String lineSep = System.getProperty(LINE_SEPARATOR_PROP);

        int numColumna = 0;
        int numFila = 0;
        int totalNotificados = 0;
        int totalNoNotificados = 0;
        int totalInconsistencias = 0;
        String linea = null;
        String[] celdas = null;
        try (BufferedReader br = new BufferedReader(new FileReader(xlsFile))) {
            while ((linea = br.readLine()) != null) {
                switch (numFila) {
                case 0: // fila de encabezados
                    sbArchivoIncosistencias.append(linea + CSV_SEPARADOR + COLUMNA_INCONSISTENCIAS + lineSep);
                    break;
                default: // filas de datos
                    celdas = linea.split(CSV_SEPARADOR, -1);
                    numColumna = 0;
                    String numeroProceso = null;
                    CoactivoDTO coactivo = null;
                    DireccionPersonaDTO direccion = null;
                    DetalleCargueCourierDTO detalleCargue = new DetalleCargueCourierDTO();
                    List<DetalleCargueCourierErrorDTO> erroresDetalle = new ArrayList<DetalleCargueCourierErrorDTO>();
                    detalleCargue.setCargueCourier(cargueCourier);
                    detalleCargue.setDetalleCargueCourierErrors(erroresDetalle);
                    for (String celda : celdas) {
                        celda = celda.trim();
                        switch (numColumna) {
                        case 8:// numero de proceso
                            numeroProceso = celda;
                            break;
                        case 9: // fecha de proceso
                            try {
                                ProcesoDTO proceso = new ProcesoDTO();
                                proceso.setNumeroProceso(numeroProceso);
                                proceso.setTipoProceso(new TipoProcesoDTO(EnumTipoProceso.COACTIVO.getValue()));
                                // proceso.setFechaInicio(formatter.parse(celda));
                                List<ProcesoDTO> procesos = iRFachadaProceso.consultarProceso(proceso);
                                if (procesos == null || procesos.isEmpty()) {
                                    // throw new CirculemosNegocioException(ErrorCoactivo.NotificarCoactivoCourier.COAC_003002);
                                    DetalleCargueCourierErrorDTO detalleCargueError = new DetalleCargueCourierErrorDTO();
                                    detalleCargueError.setDetalleCargueCourier(detalleCargue);
                                    ErrorCargueCourier error = em.find(ErrorCargueCourier.class,
                                            EnumErrorCargueCourier.NUMERO_PROCESO_NO_EXISTE_EN_SISTEMA.getValue());
                                    detalleCargueError
                                            .setErrorCargueCourier(ErrorCargueCourierHelper.toLevel0DTO(error));
                                    erroresDetalle.add(detalleCargueError);
                                    break;
                                }
                                coactivo = new CoactivoDTO();
                                coactivo.setProceso(procesos.get(0));
                                List<CoactivoDTO> coactivos = consultarCoactivos(coactivo);
                                if (coactivos.isEmpty()) {
                                    DetalleCargueCourierErrorDTO detalleCargueError = new DetalleCargueCourierErrorDTO();
                                    detalleCargueError.setDetalleCargueCourier(detalleCargue);
                                    ErrorCargueCourier error = em.find(ErrorCargueCourier.class,
                                            EnumErrorCargueCourier.PROCESO_NO_ES_COACTIVO.getValue());
                                    detalleCargueError
                                            .setErrorCargueCourier(ErrorCargueCourierHelper.toLevel0DTO(error));
                                    erroresDetalle.add(detalleCargueError);
                                    break;
                                }
                                coactivo = coactivos.get(0);
                                detalleCargue.setNumeroProceso(coactivo.getProceso().getNumeroProceso());
                                detalleCargue.setFechaProceso(coactivo.getProceso().getFechaInicio());
                            } catch (NumberFormatException ex) {
                                DetalleCargueCourierErrorDTO detalleCargueError = new DetalleCargueCourierErrorDTO();
                                detalleCargueError.setDetalleCargueCourier(detalleCargue);
                                ErrorCargueCourier error = em.find(ErrorCargueCourier.class,
                                        EnumErrorCargueCourier.DATOS_DE_PROCESO_INCOSISTENTE.getValue());
                                detalleCargueError.setErrorCargueCourier(ErrorCargueCourierHelper.toLevel0DTO(error));
                                erroresDetalle.add(detalleCargueError);
                                break;
                            }
                            break;

                        case 10: // id direccion persona
                            DireccionPersona direccionEntity = em.find(DireccionPersona.class, Long.parseLong(celda));
                            if (direccionEntity == null) {
                                DetalleCargueCourierErrorDTO detalleCargueError = new DetalleCargueCourierErrorDTO();
                                detalleCargueError.setDetalleCargueCourier(detalleCargue);
                                ErrorCargueCourier error = em.find(ErrorCargueCourier.class,
                                        EnumErrorCargueCourier.DIRECCION_NO_EXISTE_EN_SISTEMA.getValue());
                                detalleCargueError.setErrorCargueCourier(ErrorCargueCourierHelper.toLevel0DTO(error));
                                erroresDetalle.add(detalleCargueError);
                                break;
                            }
                            direccion = DireccionPersonaHelper.toLevel1DTO(direccionEntity);
                            break;

                        case 14:// respuesta notificacion
                            String notificacionExitosa = celda.toUpperCase();
                            detalleCargue.setRespuesta(notificacionExitosa);
                            if (notificacionExitosa != null && notificacionExitosa.length() == 2
                                    && (notificacionExitosa.equals(NOTIFICACION_SI)
                                            || notificacionExitosa.equals(NOTIFICACION_NO))
                                    && coactivo != null) {
                                if (notificacionExitosa.equals(NOTIFICACION_SI)) {
                                    totalNotificados++;
                                } else {
                                    totalNoNotificados++;
                                }
                            } else {
                                DetalleCargueCourierErrorDTO detalleCargueError = new DetalleCargueCourierErrorDTO();
                                detalleCargueError.setDetalleCargueCourier(detalleCargue);
                                ErrorCargueCourier error = em.find(ErrorCargueCourier.class,
                                        EnumErrorCargueCourier.REGISTRO_NO_TIENE_NOTIFICACION.getValue());
                                detalleCargueError.setErrorCargueCourier(ErrorCargueCourierHelper.toLevel0DTO(error));
                                erroresDetalle.add(detalleCargueError);
                            }
                            break;

                        case 15: // fecha de notificacion
                            try {
                                Date fechaNotificacion = formatter.parse(celda);
                                if (coactivo != null && detalleCargue.getRespuesta().equals(NOTIFICACION_SI)) {
                                    if (coactivo.getFechaNotificacion() == null
                                            || (coactivo.getFechaNotificacion() != null
                                                    && fechaNotificacion.after(coactivo.getFechaNotificacion()))) {
                                        coactivo.setFechaNotificacion(fechaNotificacion);
                                        coactivosDireccion.put(coactivo, direccion);
                                    } else {
                                        totalNotificados--;
                                        DetalleCargueCourierErrorDTO detalleCargueError = new DetalleCargueCourierErrorDTO();
                                        detalleCargueError.setDetalleCargueCourier(detalleCargue);
                                        ErrorCargueCourier error = em.find(ErrorCargueCourier.class,
                                                EnumErrorCargueCourier.FECHA_NOTIFICACION_MENOR_A_LA_DE_COACTIVO
                                                        .getValue());
                                        detalleCargueError
                                                .setErrorCargueCourier(ErrorCargueCourierHelper.toLevel0DTO(error));
                                        erroresDetalle.add(detalleCargueError);
                                    }
                                }
                            } catch (ParseException e) {
                                if (detalleCargue.getRespuesta().equals(NOTIFICACION_SI)) {
                                    totalNotificados--;
                                    DetalleCargueCourierErrorDTO detalleCargueError = new DetalleCargueCourierErrorDTO();
                                    detalleCargueError.setDetalleCargueCourier(detalleCargue);
                                    ErrorCargueCourier error = em.find(ErrorCargueCourier.class,
                                            EnumErrorCargueCourier.REGISTRO_NO_TIENE_FECHA_NOTIFICACION.getValue());
                                    detalleCargueError
                                            .setErrorCargueCourier(ErrorCargueCourierHelper.toLevel0DTO(error));
                                    erroresDetalle.add(detalleCargueError);
                                }
                            }
                            break;
                        }
                        numColumna++;
                    }
                    agregarIncosistenciasArchivoCargue(linea, erroresDetalle, sbArchivoIncosistencias, lineSep);
                    detalleCargue.setCoactivo(coactivo);
                    detalleCargue.setDireccion((direccion == null ? null : direccion.getDireccion()));
                    DetalleCargueCourier entidadDetalle = DetalleCargueCourierHelper.toLevel1Entity(detalleCargue,
                            null);
                    em.persist(entidadDetalle);
                    if (erroresDetalle.isEmpty()) {
                        if (coactivo != null && detalleCargue.getRespuesta().equals(NOTIFICACION_SI)) {
                            Coactivo entidadCoactivo = em.find(Coactivo.class, coactivo.getId());
                            entidadCoactivo.setFechaNotificacion(coactivo.getFechaNotificacion());
                            entidadCoactivo
                                    .setDireccion(DireccionHelper.toLevel0Entity(direccion.getDireccion(), null));
                            em.merge(entidadCoactivo);
                        }
                    } else {
                        for (DetalleCargueCourierErrorDTO error : erroresDetalle) {
                            DetalleCargueCourierError entidadError = DetalleCargueCourierErrorHelper
                                    .toLevel1Entity(error, null);
                            entidadError.setDetalleCargueCourier(entidadDetalle);
                            em.persist(entidadError);
                        }
                    }
                    totalInconsistencias = totalInconsistencias + erroresDetalle.size();
                    break;
                }
                numFila++;
            }
            respuestaCargue.setTotalCoactivos(numFila - 1);
            respuestaCargue.setNotificacionesExitosas(totalNotificados);
            respuestaCargue.setNotificacionesNoExitosas(totalNoNotificados);
            respuestaCargue.setTotalInconsistencias(totalInconsistencias);
            respuestaCargue.setCoactivoDireccionNotif(coactivosDireccion);
        } catch (IOException e) {
            throw new CirculemosNegocioException(ErrorCoactivo.NotificarCoactivoCourier.COAC_003001);
        }
    }

    /**
     * Agrega a la linea del archivo csv las inconsistencias presentadas en el procesamiento del coactivo
     * 
     * @param linea
     *            linea del archivo
     * @param erroresDetalle
     *            errores presentados con el coactivo
     * @param sbArchivoIncosistencias
     *            buffer del archivo de inconsistencias
     * @param lineSep
     *            separador de linea
     * @throws IOException
     */
    private void agregarIncosistenciasArchivoCargue(String linea, List<DetalleCargueCourierErrorDTO> erroresDetalle,
            StringBuilder sbArchivoIncosistencias, String lineSep) throws IOException {
        logger.debug(
                "CoactivoEJB.agregarIncosistenciasArchivoCargue(String,List<DetalleCargueCourierErrorDTO>,StringBuilder,String)");
        String inconsistenciasCol = "";
        if (erroresDetalle != null && !erroresDetalle.isEmpty()) {
            String[] arrStrInonsistencias = new String[erroresDetalle.size()];
            int i = 0;
            for (DetalleCargueCourierErrorDTO error : erroresDetalle) {
                arrStrInonsistencias[i] = error.getErrorCargueCourier().getDescripcion();
                i++;
            }
            for (int j = 0; j < arrStrInonsistencias.length; j++) {
                inconsistenciasCol += (j == arrStrInonsistencias.length - 1 ? arrStrInonsistencias[j]
                        : arrStrInonsistencias[j] + SEPARADOR_INCONSISTENCIAS);
            }
        }
        linea = linea + CSV_SEPARADOR + inconsistenciasCol + lineSep;
        sbArchivoIncosistencias.append(linea);
    }

    /**
     * Valida la estructura de una archivo de cargue de notificaciones de coativos
     * 
     * @param xlsFile
     *            archivo con la información de las notificaciones
     * @throws CirculemosNegocioException
     */
    private void validarEstructuraArchivoNotif(File xlsFile) throws CirculemosNegocioException {
        logger.debug("CoactivoEJB.validarEstructuraArchivoNotif(File)");
        int numColumnas = 0;
        int numFilas = 0;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(xlsFile))) {
            while ((line = br.readLine()) != null) {
                numFilas++;
                if (numFilas > 1) {
                    String[] informacion = line.split(CoactivoEJB.CSV_SEPARADOR);
                    numColumnas = informacion.length;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (numColumnas == 0 || numColumnas > 16 || numFilas <= 1) {
            throw new CirculemosNegocioException(ErrorCoactivo.NotificarCoactivoCourier.COAC_003001);
        }
    }

    @Override
    public List<CoactivoDTO> consultarCoactivos(CoactivoDTO coactivo) {
        logger.debug("ProcesoEJB::consultarProceso(CoactivoDTO)");

        List<CoactivoDTO> lsProcesoDTO = new ArrayList<>();

        if (coactivo != null) {
            GenericDao<Coactivo> coactivoDao = new GenericDao<>(Coactivo.class, em);
            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();
            jpql.append("SELECT c FROM Coactivo c");
            jpql.append(" WHERE 1=1");

            if (coactivo.getId() != null) {
                jpql.append(" AND c.id = :idCoactivo");
                filtros.put("idCoactivo", coactivo.getId());
            }

            if (coactivo.getProceso() != null && StringUtils.isNotBlank(coactivo.getProceso().getNumeroProceso())) {
                jpql.append(" AND c.proceso.numeroProceso = :numeroProceso");
                filtros.put("numeroProceso", coactivo.getProceso().getNumeroProceso());
            }
            List<Coactivo> resultadoConsulta = coactivoDao.buildAndExecuteQuery(jpql, filtros);
            if (!resultadoConsulta.isEmpty()) {
                lsProcesoDTO = CoactivoHelperExtend.toListLevel1DTO(resultadoConsulta);
            }
        }
        return lsProcesoDTO;
    }

    @Override
    public CargueCoactivoDTO registrarCarguePrecoactivos(CargueCoactivoDTO cargueCoactivoDTO) {
        logger.debug("CoactivoEJB.registrarCarguePrecoactivos(CargueCoactivoDTO)");
        cargueCoactivoDTO.setId(cargueCoactivoDTO.getCargueArchivo().getId());
        CargueCoactivo cargueCoactivo = CargueCoactivoHelper.toLevel1Entity(cargueCoactivoDTO, null);
        cargueCoactivo.setId(cargueCoactivoDTO.getCargueArchivo().getId());
        em.persist(cargueCoactivo);
        if (cargueCoactivoDTO.getPrecoactivos() != null) {
            for (PrecoactivoDTO precoactivoDTO : cargueCoactivoDTO.getPrecoactivos()) {
                precoactivoDTO.setCargueCoactivo(cargueCoactivoDTO);
                Precoactivo precoactivo = PrecoactivoHelper.toLevel1Entity(precoactivoDTO, null);
                em.persist(precoactivo);
            }
        }
        return cargueCoactivoDTO;
    }

    @Override
    public void modificarCarguePrecoactivos(CargueArchivoDTO cargueArchivo, boolean aceptar) {
        logger.debug("CoactivoEJB.modificarCarguePrecoactivos(CargueCoactivoDTO, boolean)");

        final StringBuilder sql = new StringBuilder();
        if (aceptar) {
            // Actualiza el estado de los precoactivos guardados
            sql.append("UPDATE precoactivo ");
            sql.append("SET id_estado_precoactivo = :estado ");
            sql.append("WHERE id_cargue_coactivo = :idCargue ");

            Query query = em.createNativeQuery(sql.toString());
            query.setParameter("estado", EnumEstadoPrecoactivo.CARGADO.getValue());
            query.setParameter("idCargue", cargueArchivo.getId());
            query.executeUpdate();
        } else {
            // Borra los precoactivos guardados en el cargue
            sql.append("DELETE FROM precoactivo ");
            sql.append("WHERE id_cargue_coactivo = :idCargue ");
            sql.append("AND id_estado_precoactivo = :estado ");

            Query query = em.createNativeQuery(sql.toString());
            query.setParameter("estado", EnumEstadoPrecoactivo.VALIDADO.getValue());
            query.setParameter("idCargue", cargueArchivo.getId());
            query.executeUpdate();

            CargueCoactivo cargueCoactivo = em.find(CargueCoactivo.class, cargueArchivo.getId());
            if (cargueCoactivo != null) {
                em.remove(cargueCoactivo);
            }
        }

    }

    @Override
    public void radicarExcepcion(RegistroRadicarExcepcionDTO registroRadicarExcepcionDTO)
            throws CirculemosNegocioException {
        logger.debug("CoactivoEJB.radicarExcepcion(RegistroRadicarExcepcionDTO)");

        validarRadicacionExcepciones(registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getCoactivoDTO().getId());

        RadicarExcepcion radicarExcepcion = RadicarExcepcionHelper
                .toLevel1Entity(registroRadicarExcepcionDTO.getRadicarExcepcionDTO(), null);

        radicarExcepcion.setFechaExcepcion(Calendar.getInstance().getTime());
        em.persist(radicarExcepcion);

        // Actualizacion de estado
        iRFachadaProceso.actualizarEstadoProceso(
                registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getCoactivoDTO().getProceso().getId(),
                EnumEstadoProceso.ECUADOR_COACTIVO_RADICAR_EXCEPCION, false);

        registroRadicarExcepcionDTO.getRadicarExcepcionDTO()
                .setIdRadicarExcepcion(radicarExcepcion.getIdRadicarExcepcion());
        try {
            registrarAnexoExcepcion(registroRadicarExcepcionDTO);
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorCoactivo.RadicarExcepciones.COAC_015004);
        }
    }

    @Override
    public void falloExcepcion(RegistroRadicarExcepcionDTO registroRadicarExcepcionDTO)
            throws CirculemosNegocioException {
        logger.debug("CoactivoEJB.falloExcepcion(RegistroRadicarExcepcionDTO)");

        validarFalloExcepciones(registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getCoactivoDTO().getId());

        RadicarExcepcion radicarExcepcion = RadicarExcepcionHelper
                .toLevel1Entity(registroRadicarExcepcionDTO.getRadicarExcepcionDTO(), null);

        radicarExcepcion
                .setFechaFalloExcepcion(registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getFechaFalloExcepcion());
        em.merge(radicarExcepcion);

        // Actualizacion de estado
        iRFachadaProceso.actualizarEstadoProceso(
                registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getCoactivoDTO().getProceso().getId(),
                EnumEstadoProceso.ECUADOR_COACTIVO_REGISTRO_FALLO, false);
        // Cierre de coactivo cuando el fallo es SI
        if (registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getFalloAFavor()) {
            // Actualizacion de estado
            iRFachadaProceso.actualizarEstadoProceso(
                    registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getCoactivoDTO().getProceso().getId(),
                    EnumEstadoProceso.ECUADOR_COACTIVO_CERRADO_POR_FALLO, false);

            // Actualizar multas a anuladas
            anularComparendosCoactivo(registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getCoactivoDTO().getId());

            // TODO
            // Generar resolucion
        }

        try {
            for (RegistroArchivoExcepcionDTO registroArchivoExcepcionDTO : registroRadicarExcepcionDTO
                    .getRegistroArchivoExcepcionDTOs()) {
                registroArchivoExcepcionDTO.setFalloExcepcion(true);
            }
            registrarAnexoExcepcion(registroRadicarExcepcionDTO);
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorCoactivo.RadicarExcepciones.COAC_015004);
        }
    }

    /**
     * 
     * @param idCoactivo
     */
    private void anularComparendosCoactivo(Long idCoactivo) throws CirculemosNegocioException {

        ObligacionCoactivoDTO obligacionCoactivoDTO = new ObligacionCoactivoDTO();

        // Coactivo
        CoactivoDTO coactivoDTO = new CoactivoDTO();
        coactivoDTO.setId(idCoactivo);
        obligacionCoactivoDTO.setCoactivo(coactivoDTO);
        obligacionCoactivoDTO.setCodigoTipoObligacion(EnumTipoObligacion.COMPARENDO.getValue());
        obligacionCoactivoDTO.setCodigoTipoObligacion(EnumTipoObligacion.COMPARENDO.getValue());
        List<ObligacionCoactivoDTO> obligacionCoactivoDTOs = consultarObligacionesCoactivo(obligacionCoactivoDTO);

        // id comparendos
        List<Long> idComparendos = new ArrayList<>();
        for (ObligacionCoactivoDTO obligacionCoactivoDTOBD : obligacionCoactivoDTOs) {
            idComparendos.add(obligacionCoactivoDTOBD.getId());
        }

        iRFachadaComparendo.registrarAnulacion(idComparendos);

    }

    /**
     * Registro de anexos de una radicacion de excepcion
     * 
     * @author giovanni.velandia
     * @throws CirculemosAlertaException
     */
    private void registrarAnexoExcepcion(RegistroRadicarExcepcionDTO registroRadicarExcepcionDTO)
            throws CirculemosAlertaException {
        logger.debug("CoactivoEJB.registrarAnexoExcepcion(RegistroRadicarExcepcionDTO)");
        // Calculo de ruta: anno/mes/proceso
        String ruta = "c2/anexosExcepcion/" + Calendar.getInstance().get(Calendar.YEAR) + "/"
                + Calendar.getInstance().get(Calendar.MONTH) + "/Proceso-"
                + registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getCoactivoDTO().getProceso().getId();

        for (RegistroArchivoExcepcionDTO registroArchivoExcepcionDTO : registroRadicarExcepcionDTO
                .getRegistroArchivoExcepcionDTOs()) {
            ArchivoExcepcion archivoExcepcion = new ArchivoExcepcion();
            archivoExcepcion.setFechaRegistro(Calendar.getInstance().getTime());
            archivoExcepcion.setNombreArchivo(registroArchivoExcepcionDTO.getArchivoTransportableDTO().getNombre());
            archivoExcepcion.setFalloExcepcion(registroArchivoExcepcionDTO.isFalloExcepcion());

            registroArchivoExcepcionDTO.getArchivoTransportableDTO().setRuta(ruta);
            OpcionGestorFileSystem ogfs = new OpcionGestorFileSystem();
            ogfs.setUbicacion(ruta);

            archivoExcepcion.setNumeroArchivo(irRepositorioArchivo.registrarDocumento(null,
                    registroArchivoExcepcionDTO.getArchivoTransportableDTO(), ogfs));

            RadicarExcepcion radicarExcepcion = new RadicarExcepcion();
            radicarExcepcion.setIdRadicarExcepcion(
                    registroRadicarExcepcionDTO.getRadicarExcepcionDTO().getIdRadicarExcepcion());
            archivoExcepcion.setRadicarExcepcion(radicarExcepcion);
            em.persist(archivoExcepcion);
        }

    }

    @Override
    public void validarRadicacionExcepciones(Long idCoactivo) throws CirculemosNegocioException {
        logger.debug("CoactivoEJB.validarRadicacionExcepciones(Long)");
        Coactivo coactivo = em.find(Coactivo.class, idCoactivo);

        // El coactivo se encuentra cerrado
        if (coactivo.getProceso().getEstadoProceso().getId()
                .equals(EnumEstadoProceso.ECUADOR_COACTIVO_CERRADO_POR_FALLO.getId())) {
            throw new CirculemosNegocioException(ErrorCoactivo.RadicarExcepciones.COAC_015001);
        }

        // Verificar si ya tiene excepciones
        if (coactivo.getRadicarExcepcions() != null && !coactivo.getRadicarExcepcions().isEmpty()) {
            throw new CirculemosNegocioException(ErrorCoactivo.RadicarExcepciones.COAC_015002);
        }

        // Verificamos el parametro
        // Verifica si se debe generar los documentos con el siguiente parametro : 228 Plazo para radicar excepciones
        ValorParametroDTO valorParametroDTO = iFachadaAdminGeneral.consultarValorParametro(
                EnumParametro.PLAZO_RADICAR_EXCEPCIONES,
                seguridadCirculemosEJB.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(), true);

        Date calFecha = UtilFecha.sumarDias(coactivo.getProceso().getFechaInicio(),
                valorParametroDTO.getValorParamInt());
        if (calFecha.before(Calendar.getInstance().getTime())) {
            throw new CirculemosNegocioException(ErrorCoactivo.RadicarExcepciones.COAC_015003);
        }

    }

    @Override
    public void validarFalloExcepciones(Long idCoactivo) throws CirculemosNegocioException {
        logger.debug("CoactivoEJB.validarRadicacionExcepciones(Long)");
        Coactivo coactivo = em.find(Coactivo.class, idCoactivo);

        // El coactivo se encuentra cerrado
        if (coactivo.getProceso().getEstadoProceso().getId()
                .equals(EnumEstadoProceso.ECUADOR_COACTIVO_CERRADO_POR_FALLO.getId())) {
            throw new CirculemosNegocioException(ErrorCoactivo.RadicarExcepciones.COAC_015005);
        }

        // No tiene una excepcion radicada
        if (coactivo.getRadicarExcepcions() == null || coactivo.getRadicarExcepcions().isEmpty()) {
            throw new CirculemosNegocioException(ErrorCoactivo.RadicarExcepciones.COAC_015006);
        } else {
            // El coactivo ya tiene el fallo de la excepción
            if (coactivo.getRadicarExcepcions().get(0).getFechaFalloExcepcion() != null) {
                throw new CirculemosNegocioException(ErrorCoactivo.RadicarExcepciones.COAC_015007);
            }
        }

    }

    @Override
    public RadicarExcepcionDTO consultarRadicarExcepcion(Long idCoactivo) {
        logger.debug("CoactivoEJB.consultarRadicarExcepcion(Long)");
        RadicarExcepcionDTO radicarExcepcionDTO = null;
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT r FROM RadicarExcepcion r");
        jpql.append(" WHERE r.coactivo.id = :idCoactivo");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("idCoactivo", idCoactivo);

        @SuppressWarnings("unchecked")
        List<RadicarExcepcion> radicarExcepcions = query.getResultList();
        if (radicarExcepcions != null && !radicarExcepcions.isEmpty()) {
            for (RadicarExcepcion radicarExcepcion : radicarExcepcions) {
                radicarExcepcionDTO = RadicarExcepcionHelper.toLevel1DTO(radicarExcepcion);
                List<ArchivoExcepcion> excepcions = radicarExcepcion.getArchivoExcepcions();
                // Excepciones
                if (excepcions != null && !excepcions.isEmpty()) {
                    radicarExcepcionDTO.setArchivoExcepcionDTOs(ArchivoExcepcionHelper.toListLevel1DTO(excepcions));
                }
                break;
            }
        }

        return radicarExcepcionDTO;
    }

    @Override
    public CoactivoDTO consultarCoactivo(String numeroCoactivo) {
        logger.debug("CoactivoEJB.consultarCoactivo(String)");

        checkNotNull(numeroCoactivo, "Se requiere parametro de numero de coactivo");
        CoactivoDTO coactivoDTO = null;

        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT c FROM Coactivo c");
        jpql.append(" JOIN c.proceso p");
        jpql.append(" LEFT JOIN c.obligacionCoactivos o");
        jpql.append(" WHERE c.numeroCoactivo = :numeroCoactivo");

        final Map<String, Object> params = new HashMap<>();
        params.put("numeroCoactivo", numeroCoactivo);

        final GenericDao<Coactivo> dao = new GenericDao<>(Coactivo.class, em);
        final List<Coactivo> coactivos = dao.buildAndExecuteQuery(jpql.toString(), params);
        if (!coactivos.isEmpty()) {
            List<CoactivoDTO> coactivoDTOs = CoactivoHelperExtend.toLevel1DTOExtendObligaciones(coactivos);

            if (coactivoDTOs != null && !coactivoDTOs.isEmpty()) {
                coactivoDTO = coactivoDTOs.get(0);
            }
        }

        return coactivoDTO;
    }

    @Override
    public List<RespuestaCoactivoDTO> consultarCoactivo(FiltroCoactivoDTO filtroCoactivoDTO) {
        logger.debug("CoactivoEJB.consultarCoactivo(ConsultaSolicitudBienDTO)");
        List<RespuestaCoactivoDTO> respuestaCoactivoDTOs = new ArrayList<>();

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT c FROM Coactivo c");
        jpql.append(" JOIN c.proceso pro");
        jpql.append(" JOIN c.persona per");
        jpql.append(" JOIN per.tipoIdentificacion ti");
        jpql.append(" WHERE");
        jpql.append(" 1=1");

        if (filtroCoactivoDTO.getIdtipoIdentificacion() != null
                && filtroCoactivoDTO.getNumeroIdentificacion() != null) {
            jpql.append(" AND per.numeroIdentificacion=:numeroIdentificacion");
            jpql.append(" AND ti.id=:idTipoIdentificacionPersona");

            filtros.put("numeroIdentificacion", filtroCoactivoDTO.getNumeroIdentificacion());
            filtros.put("idTipoIdentificacionPersona", filtroCoactivoDTO.getIdtipoIdentificacion());
        }

        if (filtroCoactivoDTO.getNumeroProcesoInicio() != null && filtroCoactivoDTO.getNumeroProcesoFin() != null) {
            jpql.append(" AND pro.numeroProceso BETWEEN :numProcesoInicio AND :numProcesoFin");

            filtros.put("numProcesoInicio", filtroCoactivoDTO.getNumeroProcesoInicio());
            filtros.put("numProcesoFin", filtroCoactivoDTO.getNumeroProcesoFin());
        }

        if (filtroCoactivoDTO.getFechaIncialProceso() != null && filtroCoactivoDTO.getFechaFinProceso() != null) {
            jpql.append(" AND pro.fechaInicio BETWEEN :fechaInicioProceso AND :fechaFinProceso");

            filtros.put("fechaInicioProceso", filtroCoactivoDTO.getFechaIncialProceso());
            filtros.put("fechaFinProceso", filtroCoactivoDTO.getFechaFinProceso());
        }

        final GenericDao<Coactivo> dao = new GenericDao<>(Coactivo.class, em);
        final List<Coactivo> coactivos = dao.buildAndExecuteQuery(jpql.toString(), filtros);

        if (coactivos != null && !coactivos.isEmpty()) {
            for (Coactivo coactivo : coactivos) {
                RespuestaCoactivoDTO respuestaCoactivoDTO = new RespuestaCoactivoDTO();
                respuestaCoactivoDTO.setIdCoactivo(coactivo.getId());
                if (coactivo.getPersona() != null) {
                    respuestaCoactivoDTO.setNumeroDocumento(coactivo.getPersona().getNumeroIdentificacion());

                    if (coactivo.getPersona().getTipoIdentificacion() != null) {
                        respuestaCoactivoDTO
                                .setCodigoTipoDocumento(coactivo.getPersona().getTipoIdentificacion().getCodigo());
                    }

                    PersonaDTO persona = ProcesarPersonasHelper.toPersonaBasicaDTO(coactivo.getPersona());
                    respuestaCoactivoDTO.setNombreDeudor(persona.getNombreCompleto());

                }

                // idCoactivo
                respuestaCoactivoDTO.setIdCoactivo(coactivo.getId());

                if (coactivo.getProceso() != null) {
                    respuestaCoactivoDTO.setNumeroProceso(coactivo.getProceso().getNumeroProceso());
                    respuestaCoactivoDTO.setFechaProceso(coactivo.getProceso().getFechaInicio());
                }

                respuestaCoactivoDTO.setTieneBien(Boolean.FALSE);

                respuestaCoactivoDTOs.add(respuestaCoactivoDTO);

            }
        }

        return respuestaCoactivoDTOs;
    }

    @Override
    public List<SolicitudOficioCoactivoDTO> consultarSolicitudOficioCoactivo(Long idCoactivo) {
        List<SolicitudOficioCoactivoDTO> solicitudOficioCoactivoDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT cob FROM CoactivoOficioBien cob");
        jpql.append(" JOIN FETCH cob.oficioBien fcb");
        jpql.append(" JOIN FETCH fcb.solicitudBienEntidad sbe");
        jpql.append(" JOIN FETCH sbe.configuracionEntidad cone");
        jpql.append(" JOIN FETCH cone.entidadOficio ef");
        jpql.append(" WHERE cob.coactivo.id = :idCoactivo");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("idCoactivo", idCoactivo);

        @SuppressWarnings("unchecked")
        List<CoactivoOficioBien> coactivoOficioBiens = query.getResultList();
        if (coactivoOficioBiens != null && !coactivoOficioBiens.isEmpty()) {
            solicitudOficioCoactivoDTOs = new ArrayList<SolicitudOficioCoactivoDTO>();
            for (CoactivoOficioBien coactivoOficioBien : coactivoOficioBiens) {
                SolicitudOficioCoactivoDTO solicitudOficioCoactivoDTO = new SolicitudOficioCoactivoDTO();
                // Entidad
                solicitudOficioCoactivoDTO.setEntidad(coactivoOficioBien.getOficioBien().getSolicitudBienEntidad()
                        .getConfiguracionEntidad().getEntidadOficio().getNombre());
                // Id coactivo oficio bien
                solicitudOficioCoactivoDTO.setIdCoactivoOficioBien(coactivoOficioBien.getId());
                // Id coactivo
                solicitudOficioCoactivoDTO.setIdCoactivo(coactivoOficioBien.getCoactivo().getId());
                // Numero de oficio
                solicitudOficioCoactivoDTO.setNumeroOficio(coactivoOficioBien.getOficioBien().getNumeroOficio());
                solicitudOficioCoactivoDTOs.add(solicitudOficioCoactivoDTO);
            }
        }

        return solicitudOficioCoactivoDTOs;
    }

    @Override
    public SolicitudOficioCoactivoDTO consultarSolicitudOficioCoactivo(Long idCoactivo, String numeroOficio) {
        SolicitudOficioCoactivoDTO solicitudOficioCoactivoDTO = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT cob FROM CoactivoOficioBien cob");
        jpql.append(" JOIN FETCH cob.oficioBien fcb");
        jpql.append(" JOIN FETCH fcb.solicitudBienEntidad sbe");
        jpql.append(" JOIN FETCH sbe.configuracionEntidad cone");
        jpql.append(" JOIN FETCH cone.entidadOficio ef");
        jpql.append(" JOIN FETCH cob.coactivo c");
        jpql.append(" JOIN FETCH c.persona per");
        jpql.append(" JOIN FETCH per.tipoIdentificacion tip");
        jpql.append(" JOIN FETCH c.proceso p");
        jpql.append(" WHERE c.id = :idCoactivo");
        jpql.append(" AND fcb.numeroOficio = :numeroOficio");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("idCoactivo", idCoactivo);
        query.setParameter("numeroOficio", numeroOficio);

        @SuppressWarnings("unchecked")
        List<CoactivoOficioBien> coactivoOficioBiens = query.getResultList();
        if (coactivoOficioBiens != null && !coactivoOficioBiens.isEmpty()) {
            for (CoactivoOficioBien coactivoOficioBien : coactivoOficioBiens) {
                solicitudOficioCoactivoDTO = new SolicitudOficioCoactivoDTO();
                // Entidad
                solicitudOficioCoactivoDTO.setEntidad(coactivoOficioBien.getOficioBien().getSolicitudBienEntidad()
                        .getConfiguracionEntidad().getEntidadOficio().getNombre());
                // Fecha proceso
                solicitudOficioCoactivoDTO
                        .setFechaProceso(coactivoOficioBien.getCoactivo().getProceso().getFechaInicio());
                // Id coactivo oficio bien
                solicitudOficioCoactivoDTO.setIdCoactivoOficioBien(coactivoOficioBien.getId());
                // Nombres de persona
                solicitudOficioCoactivoDTO.setNombres(
                        PersonaHelper.toLevel1DTO(coactivoOficioBien.getCoactivo().getPersona()).getNombreCompleto());
                // Numero documento identificacion
                solicitudOficioCoactivoDTO.setNumeroDocIdentificacion(
                        coactivoOficioBien.getCoactivo().getPersona().getNumeroIdentificacion());
                // Numero de oficio
                solicitudOficioCoactivoDTO.setNumeroOficio(coactivoOficioBien.getOficioBien().getNumeroOficio());
                // Numero de proceso
                solicitudOficioCoactivoDTO
                        .setNumeroProceso(coactivoOficioBien.getCoactivo().getProceso().getNumeroProceso());
                // Tipo de identificacion proceso
                solicitudOficioCoactivoDTO.setTipoDocIdentificacion(
                        coactivoOficioBien.getCoactivo().getPersona().getTipoIdentificacion().getNombre());
            }
        }

        return solicitudOficioCoactivoDTO;
    }

}