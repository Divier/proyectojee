package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.cartera.ILCarteraComparendo;
import co.com.datatools.c2.adaptador.dto.CarteraComparendoDTO;
import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.dto.ArchivoNotificacionComparendoDTO;
import co.com.datatools.c2.dto.ArchivoNotificacionSimitDTO;
import co.com.datatools.c2.dto.HomologacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.LineaArchivoNotCompSimitDTO;
import co.com.datatools.c2.dto.LoggerNotificacionCompSIMIT;
import co.com.datatools.c2.dto.LoggerNotificacionCompSIMIT.EstadoTransaccion;
import co.com.datatools.c2.dto.NotificacionCompaSimitLogDTO;
import co.com.datatools.c2.dto.NotificacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.NotificacionSimitDTO;
import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.dto.ValidacionComparendoSimitDTO;
import co.com.datatools.c2.dto.common.RespuestaNotificacionDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoCarteraDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.entidades.ArchivoNotificacionSimit;
import co.com.datatools.c2.entidades.NotificacionSimit;
import co.com.datatools.c2.entidades.TipoDocumentoEnvioSimit;
import co.com.datatools.c2.entidades.TipoResultadoEnvioSimit;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.enumeracion.EnumMedioImposicion;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumRespuestaWS;
import co.com.datatools.c2.enumeracion.EnumTipoDocumentoEnvioSimit;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeracion.EnumTipoResultadoEnvioSimit;
import co.com.datatools.c2.enumeracion.EnumTipoSancion;
import co.com.datatools.c2.enumeraciones.EnumAccionComparendo;
import co.com.datatools.c2.enumeraciones.EnumCarteraComparendo;
import co.com.datatools.c2.enumeraciones.EnumCodigoEstadoComparendoSimit;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendoSimit;
import co.com.datatools.c2.enumeraciones.EnumOpcionGeneracionArchivo;
import co.com.datatools.c2.enumeraciones.EnumServicioEnvioSimit;
import co.com.datatools.c2.enumeraciones.EnumTipoAgenteImpositor;
import co.com.datatools.c2.enumeraciones.EnumTipoServicio;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.error.ErrorSimit;
import co.com.datatools.c2.negocio.error.ErrorSimit.EnumGenerarNotificacionComparendo;
import co.com.datatools.c2.negocio.error.ErrorSimit.EnumNotificacionComparendoSIMIT;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaComparendo;
import co.com.datatools.c2.negocio.helpers.ArchivoNotificacionSimitHelper;
import co.com.datatools.c2.negocio.helpers.NotificacionSimitHelper;
import co.com.datatools.c2.negocio.interfaces.ILNotificacionComparendoSimit;
import co.com.datatools.c2.negocio.interfaces.IRClienteWSSIMIT;
import co.com.datatools.c2.negocio.interfaces.IRNotificacionComparendoSimit;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.util.ArchivoSimitUtil;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

@Stateless(name = "SimitEJB")
@LocalBean
public class SimitEJB implements ILNotificacionComparendoSimit, IRNotificacionComparendoSimit {

    private final static Logger logger = Logger.getLogger(SimitEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILCarteraComparendo iCarteraComparendo;
    @EJB
    private IRFachadaComparendo iFachadaComparendo;
    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneral;
    @EJB
    private IRRepositorioArchivo iRepositorioArchivo;
    @EJB
    private IRFachadaConfiguracion iFachadaConfiguracion;
    @EJB
    private IRSeguridadCirculemos iSeguridadC2;
    @EJB
    private IRClienteWSSIMIT clienteWSSIMITEJB;

    @Override
    public List<NotificacionSimitDTO> consultarNotificacionesSimit(int codigoOrganismo, Date fechaInicial,
            Date fechaFinal, int tipoDocumentoEnvioSimit) throws CirculemosNegocioException {
        logger.debug("SimitEJB::consultarNotificacionesSimit(int, Date, Date, int)");

        fechaInicial = checkNotNull(fechaInicial, "La fecha inicial es obligatoria");
        fechaFinal = checkNotNull(fechaFinal, "La fecha final es obligatoria");
        checkArgument(fechaInicial.compareTo(fechaFinal) <= 0, "La fecha inicial debe ser menor a la fecha final");
        checkArgument(fechaInicial.compareTo(UtilFecha.resetTime(UtilFecha.buildCalendar()).getTime()) <= 0,
                "La fecha inicial debe ser menor a hoy");

        // Consultar y validar parametro: rango dias de diferencia
        ValorParametroDTO valorParam = fachadaAdminGeneral.consultarValorParametro(EnumParametro.MAX_DIAS_CONSULTA,
                codigoOrganismo, true);
        int maxDias = Integer.valueOf(valorParam.getValorParam());
        long diasDiferencia = fechaFinal.getTime() - fechaInicial.getTime();
        diasDiferencia = TimeUnit.DAYS.convert(diasDiferencia, TimeUnit.MILLISECONDS);

        if (diasDiferencia > maxDias)
            throw new CirculemosNegocioException(ErrorSimit.EnumRegistrarNotificacionComparendo.SIM_008001);

        GenericDao<NotificacionSimit> notSimitDao = new GenericDao<>(NotificacionSimit.class, em);
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT nf FROM NotificacionSimit nf LEFT JOIN FETCH nf.archivoNotificacionSimits")
                .append(" WHERE nf.tipoDocumentoEnvioSimit.id = :idTipoDoc")
                .append(" AND nf.codigoOrganismo.codigoOrganismo = :codigoOrganismo")
                .append(" AND nf.fechaGeneracion BETWEEN :fechaInicial AND :fechaFinal")
                .append(" ORDER BY nf.fechaGeneracion DESC");

        List<NotificacionSimitDTO> notSimitDTOList = new ArrayList<NotificacionSimitDTO>();
        List<NotificacionSimit> notSimitList = notSimitDao.buildAndExecuteQuery(jpql,
                GenericDao.buildMap("idTipoDoc", tipoDocumentoEnvioSimit).addEntry("codigoOrganismo", codigoOrganismo)
                        .addEntry("fechaInicial", fechaInicial).addEntry("fechaFinal", fechaFinal));

        for (NotificacionSimit notificacionSimit : notSimitList) {
            List<ArchivoNotificacionSimitDTO> archivoNotSimitList = ArchivoNotificacionSimitHelper
                    .toListLevel1DTO(notificacionSimit.getArchivoNotificacionSimits());
            NotificacionSimitDTO notificacionSimitDTO = NotificacionSimitHelper.toLevel1DTO(notificacionSimit);
            notificacionSimitDTO.setArchivoNotificacionSimits(archivoNotSimitList);
            notSimitDTOList.add(notificacionSimitDTO);
        }

        return notSimitDTOList;
    }

    @Override
    public List<ArchivoNotificacionComparendoDTO> generarNotificacionComparendo(int codigoOrganismo,
            EnumOpcionGeneracionArchivo... opciones) throws CirculemosNegocioException {
        logger.debug("SimitEJB.generarNotificacionComparendo(int, EnumOpcionGeneracionArchivo...)");

        // Si no se envía el parámetro opciones el archivo se genera
        boolean generarArchivo = true;
        if (opciones != null) {
            for (EnumOpcionGeneracionArchivo enumOpcionGeneracionArchivo : opciones) {
                if (EnumOpcionGeneracionArchivo.NO_GENERAR_ARCHIVO.equals(enumOpcionGeneracionArchivo)) {
                    generarArchivo = false;
                    break;
                }
            }
        }

        List<ComparendoDTO> lsComparendoDTO = iFachadaComparendo.consultarComparendos(codigoOrganismo, null);
        List<ArchivoNotificacionComparendoDTO> archivos = generarArchivos(lsComparendoDTO, generarArchivo,
                codigoOrganismo);

        return archivos;
    }

    /**
     * Genera archivos
     * 
     * @param resultadoConsulta
     * @param generarArchivo
     * @param codigoOrganismo
     * @return listado de archivos
     * @author julio.pinzon
     * @throws CirculemosNegocioException
     */
    private List<ArchivoNotificacionComparendoDTO> generarArchivos(List<ComparendoDTO> resultadoConsulta,
            boolean generarArchivo, int codigoOrganismo) throws CirculemosNegocioException {
        logger.debug("SimitEJB.generarNotificacionComparendo(List<Comparendo>, boolean, int)");

        List<ArchivoNotificacionComparendoDTO> archivos = new ArrayList<ArchivoNotificacionComparendoDTO>();

        // Separador del archivo plano comparendos SIMIT
        ValorParametroDTO valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.SEP_ARCH_COMPA_SIMIT, codigoOrganismo, true);
        String separadorColumnasArchivo = valorParametroDTO.getValorParam();

        // Para saber el 'Número máximo de registros para Plano SIMIT' usar el parámetro: MAX_REGS_COMP_SIMIT
        valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(EnumParametro.MAX_REGS_COMP_SIMIT,
                codigoOrganismo, true);
        int maxRegsCompSimit = valorParametroDTO.getValorParamInt();

        // Para saber si 'El plano para SIMIT incluye comparendos con valor 0' usar el parámetro: ARCH_SIMIT_INCLUYE_CERO
        valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(EnumParametro.ARCH_SIMIT_INCLUYE_CERO,
                codigoOrganismo, true);
        boolean archSimitIncluyeCero = valorParametroDTO.getValorParamBoolean();

        // Separador archivo Simit
        valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(EnumParametro.SEP_ARCH_COMPA_SIMIT,
                codigoOrganismo, true);
        String separadorArchivoSImit = valorParametroDTO.getValorParam();

        // Inicializa valor de cache de homologacion
        ArchivoSimitUtil.inicializarCacheHomologacion();
        // Sumatoria de los registros
        BigDecimal sumatoriaRegistros = null;
        // Sumatoria ascii
        BigInteger sumatoriaAscii = null;
        // Contenido archivo
        StringBuilder contenidoArchivo = null;
        int numLinea = 0;
        ArchivoNotificacionComparendoDTO archivo = null;
        comparendoLoop: for (ComparendoDTO comparendo : resultadoConsulta) {
            /*
             * Cuando el número de ítems del resultado es MAYOR al valor definido en el parámetro "Número máximo de registros para Plano SIMIT", se
             * DEBE segmentar el resultado en grupos de ítems donde su tamaño NO supere el valor del parámetro.
             */
            if (maxRegsCompSimit == numLinea) {
                if (generarArchivo) {
                    // obtener el número de oficio
                    archivo.setNumeroOficio(fachadaAdminGeneral.generarConsecutivo(codigoOrganismo,
                            EnumConsecutivo.GENERAL));
                    /*
                     * El resultado del cálculo de la cifras de control, se adicionan en la ÚLTIMA línea del archivo plano, con los siguientes valores
                     * :
                     * 
                     * - Número de registros contenidos - Sumatoria de los registros - Número de oficio con el que se entrega - Código de chequeo de
                     * la información
                     */
                    BigInteger codigoChequeo = sumatoriaAscii.mod(new BigInteger("10000"));
                    String ultimaLinea = numLinea + separadorColumnasArchivo + sumatoriaRegistros
                            + separadorColumnasArchivo + archivo.getNumeroOficio() + separadorColumnasArchivo
                            + codigoChequeo.longValue();
                    contenidoArchivo.append(ultimaLinea);
                    archivo.setContenido(contenidoArchivo.toString().getBytes());
                }

                archivo.setCantidadRegistros(numLinea);

                // Agrega archivo donde se generó la ultima linea
                archivos.add(archivo);
                numLinea = 0;
            }
            if (numLinea == 0) {
                archivo = new ArchivoNotificacionComparendoDTO();
                contenidoArchivo = new StringBuilder();
                sumatoriaAscii = BigInteger.ZERO;
                sumatoriaRegistros = BigDecimal.ZERO;
            }
            for (ComparendoCarteraDTO comparendoCartera : comparendo.getComparendoCarteraList()) {
                CarteraComparendoDTO cartera = iCarteraComparendo.consultarCartera(comparendoCartera.getIdCartera());
                /*
                 * Cuando el comparendo NO ha sido enviado previamente a SIMIT
                 * 
                 * - Los comparendos con "Estado del comparendo" DEBE ser "Registrado" y el "Estado de la obligación" DEBE ser "Pendiente por activar"
                 * - Los comparendos con "Estado del comparendo" DEBE ser "Vigente" y el "Estado de la obligación" DEBE ser "Activa" - No tiene
                 * asignado el campo "Estado del comparendo para envío a SIMIT".
                 */
                if (comparendo.getEstadoComparendoSimit() == null
                        && ((comparendo.getEstadoComparendo().getId()
                                .equals(EnumEstadoComparendo.REGISTRADO.getValue()) && cartera.getEstado().getValue()
                                .equals(EnumCarteraComparendo.OBLIGACION_PENDIENTE_ACTIVAR.getValue())) || (comparendo
                                .getEstadoComparendo().getId().equals(EnumEstadoComparendo.VIGENTE.getValue()) && cartera
                                .getEstado().getValue().equals(EnumCarteraComparendo.OBLIGACION_ACTIVO.getValue())))) {
                    if (!archSimitIncluyeCero) {
                        ConfiguracionInfraccionDTO configInfraccion = iFachadaComparendo
                                .consultarConfiguracionInfraccion(comparendo.getInfraccion().getCodigo(),
                                        comparendo.getFechaInfraccion());

                        /*
                         * Cuando el valor del parámetro "El plano para SIMIT incluye comparendos con valor 0" es IGUAL A "NO", NO se incluye:
                         * 
                         * ­ Los comparendos que tienen el "Código de infracción" clasificado como "Tipos de sanciones" IGUAL A "Amonestación", bajo
                         * la configuración del caso de uso "Administrar Infracciones" y su "Estado de la obligación" IGUAL A "Pendiente por activar"
                         */
                        for (TipoSancionDTO tipoSancion : configInfraccion.getTipoSancionList()) {
                            if (EnumTipoSancion.AMONESTACION.getValue().equals(tipoSancion.getId())
                                    && cartera.getEstado().equals(EnumCarteraComparendo.OBLIGACION_PENDIENTE_ACTIVAR)) {
                                continue comparendoLoop;
                            }
                        }
                    }

                    // Arma la linea del archivo con los datos del comparendo
                    if (generarArchivo) {
                        ValidacionComparendoSimitDTO validacionComparendoSimit = new ValidacionComparendoSimitDTO();
                        validacionComparendoSimit.setCicomparendo(comparendo.getCicomparendo());
                        validacionComparendoSimit.setCodigoOrganismo(codigoOrganismo);
                        validacionComparendoSimit.setOrigenEnvioSimit(EnumAccionComparendo.REGISTRO);
                        try {
                            HomologacionComparendoSIMITDTO homologacionComparendo = validarComparendo(validacionComparendoSimit);
                            iFachadaComparendo.notificarComparendoSIMIT(codigoOrganismo, comparendo.getCicomparendo(),
                                    EnumAccionComparendo.REGISTRO);
                            homologacionComparendo.setConsecutivo(String.valueOf(numLinea + 1));
                            String contenido = homologacionComparendo.toString(separadorArchivoSImit);

                            // Sumatoria de valores de comparendo
                            sumatoriaRegistros = sumatoriaRegistros.add(new BigDecimal(homologacionComparendo
                                    .getValorComparendo()));
                            sumatoriaAscii = sumatoriaAscii.add(ArchivoSimitUtil.obtenerSumatoriaAscii(contenido));
                            contenidoArchivo.append(contenido);
                        } catch (CirculemosNegocioException e) {
                            logger.debugv("SimitEJB.generarNotificacionComparendo(List<Comparendo>, boolean, int) {0}",
                                    e.toString());
                            continue comparendoLoop;
                        }
                    }
                    numLinea++;
                }

                /*
                 * Cuando el comparendo SI ha sido enviado previamente a SIMIT
                 * 
                 * - El "Estado del comparendo para envío a SIMIT" DEBE ser "Corregido"
                 */
                else if (comparendo.getEstadoComparendoSimit() != null
                        && comparendo.getEstadoComparendoSimit().getId()
                                .equals(EnumEstadoComparendoSimit.CORREGIDO.getValue())) {
                    // Arma la linea del archivo con los datos del comparendo
                    if (generarArchivo) {
                        ValidacionComparendoSimitDTO validacionComparendoSimit = new ValidacionComparendoSimitDTO();
                        validacionComparendoSimit.setCicomparendo(comparendo.getCicomparendo());
                        validacionComparendoSimit.setCodigoOrganismo(codigoOrganismo);
                        validacionComparendoSimit.setOrigenEnvioSimit(EnumAccionComparendo.REGISTRO);
                        try {
                            HomologacionComparendoSIMITDTO homologacionComparendo = validarComparendo(validacionComparendoSimit);
                            iFachadaComparendo.notificarComparendoSIMIT(codigoOrganismo, comparendo.getCicomparendo(),
                                    EnumAccionComparendo.REGISTRO);
                            homologacionComparendo.setConsecutivo(String.valueOf(numLinea + 1));
                            String contenido = homologacionComparendo.toString(separadorArchivoSImit);

                            // Sumatoria de valores de comparendo
                            sumatoriaRegistros = sumatoriaRegistros.add(new BigDecimal(homologacionComparendo
                                    .getValorComparendo()));

                            sumatoriaAscii = sumatoriaAscii.add(ArchivoSimitUtil.obtenerSumatoriaAscii(contenido));
                            contenidoArchivo.append(contenido);
                        } catch (CirculemosNegocioException e) {
                            logger.debugv("SimitEJB.generarNotificacionComparendo(List<Comparendo>, boolean, int) {0}",
                                    e.toString());
                            continue comparendoLoop;
                        }
                    }
                    numLinea++;
                }
                break;
            }
        }
        if (numLinea > 0) {
            if (generarArchivo) {
                // obtener el número de oficio
                archivo.setNumeroOficio(fachadaAdminGeneral
                        .generarConsecutivo(codigoOrganismo, EnumConsecutivo.GENERAL));
                /*
                 * El resultado del cálculo de la cifras de control, se adicionan en la ÚLTIMA línea del archivo plano, con los siguientes valores :
                 * 
                 * - Número de registros contenidos - Sumatoria de los registros - Número de oficio con el que se entrega - Código de chequeo de la
                 * información
                 */
                BigInteger codigoChequeo = sumatoriaAscii.mod(new BigInteger("10000"));
                String ultimaLinea = numLinea + separadorColumnasArchivo + sumatoriaRegistros
                        + separadorColumnasArchivo + archivo.getNumeroOficio() + separadorColumnasArchivo
                        + codigoChequeo.longValue();
                contenidoArchivo.append(ultimaLinea);
                archivo.setContenido(contenidoArchivo.toString().getBytes());
            }

            archivo.setCantidadRegistros(numLinea);

            // Agrega archivo donde se generó la ultima linea
            archivos.add(archivo);
            // Si se debe generar el archivo se le pone el nombre
            if (generarArchivo) {
                // Nombre archivo plano comparendos SIMIT
                valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(EnumParametro.NOMBRE_ARCH_COMPA_SIMIT,
                        codigoOrganismo, true);
                // parametro nombre y extension
                String nombreArchivo = codigoOrganismo + valorParametroDTO.getValorParam();

                // Extensión archivo plano comparendos SIMIT
                valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(EnumParametro.EXT_ARCH_COMPA_SIMIT,
                        codigoOrganismo, true);
                String extension = valorParametroDTO.getValorParam();
                // Si es solo un archivo, le añade la extension al nombre
                if (archivos.size() == 1) {
                    // parametro nombre y extension
                    archivos.get(0).setNombre(nombreArchivo + "." + extension);
                } else {
                    // parametro nombre y extension
                    int numArchivos = 1;
                    for (ArchivoNotificacionComparendoDTO archivoNotificacionComparendoDTO : archivos) {
                        archivoNotificacionComparendoDTO.setNombre(nombreArchivo + "_" + numArchivos + "." + extension);
                        numArchivos++;
                    }
                }

            }
        }

        // Limpia valor de cache de homologacion
        ArchivoSimitUtil.limpiarCacheHomologacion();
        if (archivos.isEmpty()) {
            throw new CirculemosNegocioException(EnumGenerarNotificacionComparendo.SIM_006001);
        }

        return archivos;
    }

    /**
     * Obtiene los campos para formar la linea del archivo
     * 
     * @param cicomparendo
     * @param separadorColumnasArchivo
     * @param consecutivo
     * @return Cadena con una linea del archivo
     * @author julio.pinzon
     * @throws CirculemosNegocioException
     */
    private LineaArchivoNotCompSimitDTO obtenerLineaArchivo(Long cicomparendo, String separadorColumnasArchivo,
            int consecutivo, int codigoOrganismo) throws CirculemosNegocioException {
        logger.debug("SimitEJB.obtenerLineaArchivo(Long, String, int)");
        List<ComparendoDTO> lsComparendoDTO = iFachadaComparendo.consultarComparendos(codigoOrganismo, cicomparendo);
        ComparendoDTO comparendoDTO = null;

        if (!lsComparendoDTO.isEmpty()) {
            comparendoDTO = lsComparendoDTO.get(0);
        }

        // Arma la linea del archivo con los datos del comparendo
        LineaArchivoNotCompSimitDTO lineaArchivo = new LineaArchivoNotCompSimitDTO();
        // Formato fecha constante
        SimpleDateFormat sdfFecha = new SimpleDateFormat(ConstantesComparendo.FORMATO_FECHA);
        SimpleDateFormat sdfHora = new SimpleDateFormat(ConstantesComparendo.FORMATO_HORA);

        // Asigna el separador
        lineaArchivo.setSeparador(separadorColumnasArchivo);
        lineaArchivo.setConsecutivo(String.valueOf(consecutivo));
        lineaArchivo.setNumeroComparendo(comparendoDTO.getOrdenComparendoNacional().getNumeroComparendo());
        lineaArchivo.setFechaImposicionComparendo(sdfFecha.format(comparendoDTO.getFechaInfraccion()));
        lineaArchivo.setHoraImposicionComparendo(sdfHora.format(comparendoDTO.getHoraInfraccion()));
        lineaArchivo.setDireccionLugarInfraccion(comparendoDTO.getDireccion().toStringSinMunicipio());
        if (comparendoDTO.getDireccion().getMunicipio() != null) {
            lineaArchivo.setMunicipio(comparendoDTO.getDireccion().getMunicipio().getCodigo());
        }
        if (comparendoDTO.getDireccion().getLocalidad() != null) {
            lineaArchivo.setLocalidad(comparendoDTO.getDireccion().getLocalidad().getCodigo());
        }
        if (comparendoDTO.getComparendoVehiculo() != null) {
            lineaArchivo.setPlacaVehiculo(comparendoDTO.getComparendoVehiculo().getPlacaVehiculo());
            if (comparendoDTO.getComparendoVehiculo().getOrganismoMatriVehic() != null
                    && comparendoDTO.getComparendoVehiculo().getOrganismoMatriVehic().getMunicipio() != null) {
                lineaArchivo.setMatriculadoEn(comparendoDTO.getComparendoVehiculo().getOrganismoMatriVehic()
                        .getMunicipio().getCodigo());
            }
            // Catalogos de SIMIT
            if (comparendoDTO.getComparendoVehiculo().getClaseVehiculo() != null) {
                String valorHomologado = ArchivoSimitUtil.obtenerValorHomologacionCache(
                        EnumServicioEnvioSimit.TIPO_VEHICULO, comparendoDTO.getComparendoVehiculo().getClaseVehiculo()
                                .getCodigo());
                if (valorHomologado == null) {
                    valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TIPO_VEHICULO,
                            comparendoDTO.getComparendoVehiculo().getClaseVehiculo().getCodigo());
                    ArchivoSimitUtil.agregarValorHomologacionCache(EnumServicioEnvioSimit.TIPO_VEHICULO, comparendoDTO
                            .getEstadoComparendo().getCodigo(), valorHomologado);
                }
                lineaArchivo.setTipoVehiculo(valorHomologado);
            }
            if (comparendoDTO.getComparendoVehiculo().getTipoServicio() != null) {
                String valorHomologado = ArchivoSimitUtil.obtenerValorHomologacionCache(
                        EnumServicioEnvioSimit.CLASE_SERVICIO, comparendoDTO.getComparendoVehiculo().getTipoServicio()
                                .getCodigo());
                if (valorHomologado == null) {
                    valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.CLASE_SERVICIO,
                            comparendoDTO.getComparendoVehiculo().getTipoServicio().getCodigo());
                    ArchivoSimitUtil.agregarValorHomologacionCache(EnumServicioEnvioSimit.CLASE_SERVICIO, comparendoDTO
                            .getEstadoComparendo().getCodigo(), valorHomologado);
                }
                lineaArchivo.setClaseServicio(valorHomologado);
            }
            if (comparendoDTO.getComparendoVehiculo().getRadioAccion() != null) {
                String valorHomologado = ArchivoSimitUtil.obtenerValorHomologacionCache(
                        EnumServicioEnvioSimit.RADIO_ACCION, comparendoDTO.getComparendoVehiculo().getRadioAccion()
                                .getCodigo());
                if (valorHomologado == null) {
                    valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.RADIO_ACCION,
                            comparendoDTO.getComparendoVehiculo().getRadioAccion().getCodigo());
                    ArchivoSimitUtil.agregarValorHomologacionCache(EnumServicioEnvioSimit.RADIO_ACCION, comparendoDTO
                            .getEstadoComparendo().getCodigo(), valorHomologado);
                }
                lineaArchivo.setRadioAccion(valorHomologado);
            }
            if (comparendoDTO.getComparendoVehiculo().getModalidad() != null) {
                String valorHomologado = ArchivoSimitUtil.obtenerValorHomologacionCache(
                        EnumServicioEnvioSimit.MODALIDAD_TRANSPORTE, comparendoDTO.getComparendoVehiculo()
                                .getModalidad().getCodigo());
                if (valorHomologado == null) {
                    valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.MODALIDAD_TRANSPORTE,
                            comparendoDTO.getComparendoVehiculo().getModalidad().getCodigo());
                    ArchivoSimitUtil.agregarValorHomologacionCache(EnumServicioEnvioSimit.MODALIDAD_TRANSPORTE,
                            comparendoDTO.getEstadoComparendo().getCodigo(), valorHomologado);
                }
                lineaArchivo.setModalidadTransporte(valorHomologado);
            }
            if (comparendoDTO.getComparendoVehiculo().getTipoTransPasajero() != null) {
                String valorHomologado = ArchivoSimitUtil.obtenerValorHomologacionCache(
                        EnumServicioEnvioSimit.TRANSPORTE_PASAJEROS, comparendoDTO.getComparendoVehiculo()
                                .getTipoTransPasajero().getCodigo());
                if (valorHomologado == null) {
                    valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TRANSPORTE_PASAJEROS,
                            comparendoDTO.getComparendoVehiculo().getTipoTransPasajero().getCodigo());
                    ArchivoSimitUtil.agregarValorHomologacionCache(EnumServicioEnvioSimit.TRANSPORTE_PASAJEROS,
                            comparendoDTO.getEstadoComparendo().getCodigo(), valorHomologado);
                }
                lineaArchivo.setTransportePasajeros(valorHomologado);
            }
            // Catalogos de SIMIT Fin
            lineaArchivo.setNumeroLicenciaTransito(comparendoDTO.getComparendoVehiculo().getNumeroLicenciaTransito());
            if (comparendoDTO.getComparendoVehiculo().getOrganismoLicenciaTransito() != null) {
                lineaArchivo.setOrganismoLicenciaTransito(comparendoDTO.getComparendoVehiculo()
                        .getOrganismoLicenciaTransito().getMunicipio().getCodigo());
            }
            lineaArchivo.setNumeroTarjetaOperacion(comparendoDTO.getComparendoVehiculo().getNumeroTarjetaOperacion());
        }
        // Catalogos de SIMIT
        if (comparendoDTO.getTipoInfractor() != null) {
            String valorHomologado = ArchivoSimitUtil.obtenerValorHomologacionCache(
                    EnumServicioEnvioSimit.TIPO_INFRACTOR, comparendoDTO.getTipoInfractor().getCodigo());
            if (valorHomologado == null) {
                valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TIPO_INFRACTOR,
                        comparendoDTO.getTipoInfractor().getCodigo());
                ArchivoSimitUtil.agregarValorHomologacionCache(EnumServicioEnvioSimit.TIPO_INFRACTOR, comparendoDTO
                        .getEstadoComparendo().getCodigo(), valorHomologado);
            }
            lineaArchivo.setTipoInfractor(valorHomologado);
        }
        // Catalogos de SIMIT Fin
        if (comparendoDTO.getPersonaList() != null) {
            for (ComparendoPersonaDTO comparendoPersona : comparendoDTO.getPersonaList()) {
                if (EnumTipoPersonaComparendo.INFRACTOR.getValue().equals(
                        comparendoPersona.getTipoPersonaComparendo().getCodigo())) {
                    lineaArchivo.setNumeroDocumentoInfractor(comparendoPersona.getNumeroIdentificacion());
                    // Catalogo de SIMIT
                    String valorHomologado = ArchivoSimitUtil.obtenerValorHomologacionCache(
                            EnumServicioEnvioSimit.TIPO_DOCUMENTO, comparendoPersona.getTipoIdentificacion()
                                    .getCodigo());
                    if (valorHomologado == null) {
                        valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TIPO_DOCUMENTO,
                                comparendoPersona.getTipoIdentificacion().getCodigo());
                        ArchivoSimitUtil.agregarValorHomologacionCache(EnumServicioEnvioSimit.TIPO_DOCUMENTO,
                                comparendoDTO.getEstadoComparendo().getCodigo(), valorHomologado);
                    }
                    lineaArchivo.setTipoDocumentoInfractor(valorHomologado);
                    // Catalogos de SIMIT Fin
                    lineaArchivo.setNombresInfractor(comparendoPersona.getNombres());
                    lineaArchivo.setApellidosInfractor(comparendoPersona.getApellidos());
                    if (comparendoPersona.getEdad() != null) {
                        lineaArchivo.setEdadInfractor(String.valueOf(comparendoPersona.getEdad()));
                    }
                    if (comparendoPersona.getDireccion() != null) {
                        lineaArchivo.setDireccionInfractor(comparendoPersona.getDireccion().toStringSinMunicipio());
                        lineaArchivo.setCiudadResidenciaInfractor(comparendoPersona.getDireccion().getMunicipio()
                                .getCodigo());
                    }
                    lineaArchivo.setCorreoElectronicoInfractor(comparendoPersona.getEmail());
                    lineaArchivo.setTelefonoInfractor(comparendoPersona.getTelefonoFijo());
                    lineaArchivo.setNumeroLicenciaConduccion(comparendoPersona.getNumeroLicencia());
                    if (comparendoPersona.getCategoriaLicencia() != null) {
                        lineaArchivo.setCategoriaLicenciaConduccion(comparendoPersona.getCategoriaLicencia()
                                .getNombre());
                    }
                    if (comparendoPersona.getOrganismoTransito() != null) {
                        lineaArchivo.setOrganismoExpideLicencia(comparendoPersona.getOrganismoTransito().getMunicipio()
                                .getCodigo());
                    }
                    if (comparendoPersona.getFechaVencimientoLicenCondu() != null) {
                        lineaArchivo.setFechaVencimientoLicenciaConduccionInfractor(sdfFecha.format(comparendoPersona
                                .getFechaVencimientoLicenCondu()));
                    }
                    if (comparendoDTO.getTipoInfractor() != null) {
                        lineaArchivo.setTipoInfractor(comparendoDTO.getTipoInfractor().getCodigo());
                    }
                } else if (EnumTipoPersonaComparendo.PROPIETARIO.getValue().equals(
                        comparendoPersona.getTipoPersonaComparendo().getCodigo())) {
                    lineaArchivo.setNumeroDocumentoPropietario(comparendoPersona.getNumeroIdentificacion());
                    // Catalogo de SIMIT
                    String valorHomologado = ArchivoSimitUtil.obtenerValorHomologacionCache(
                            EnumServicioEnvioSimit.TIPO_DOCUMENTO, comparendoPersona.getTipoIdentificacion()
                                    .getCodigo());
                    if (valorHomologado == null) {
                        valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TIPO_DOCUMENTO,
                                comparendoPersona.getTipoIdentificacion().getCodigo());
                        ArchivoSimitUtil.agregarValorHomologacionCache(EnumServicioEnvioSimit.TIPO_DOCUMENTO,
                                comparendoDTO.getEstadoComparendo().getCodigo(), valorHomologado);
                    }
                    lineaArchivo.setTipoDocumentoPropietario(valorHomologado);
                    // Catalogos de SIMIT Fin
                    lineaArchivo.setNombresApellidosPropietario(comparendoPersona.getNombreCompleto());
                } else if (EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getValue().equals(
                        comparendoPersona.getTipoPersonaComparendo().getCodigo())) {
                    lineaArchivo.setRazonSocial(comparendoPersona.getRazonSocial());
                    lineaArchivo.setNumeroDocumentoEmpresa(comparendoPersona.getNumeroIdentificacion());
                } else if (EnumTipoPersonaComparendo.TESTIGO.getValue().equals(
                        comparendoPersona.getTipoPersonaComparendo().getCodigo())) {
                    lineaArchivo.setNumeroDocumentoTestigo(comparendoPersona.getNumeroIdentificacion());
                    lineaArchivo.setNombresApellidosTestigo(comparendoPersona.getNombreCompleto());
                    if (comparendoPersona.getDireccion() != null) {
                        lineaArchivo.setDireccionTestigo(comparendoPersona.getDireccion().toStringSinMunicipio());
                    }
                    lineaArchivo.setNumeroCelularTestigo(comparendoPersona.getTelefonoMovil());
                }
            }
        }
        if (comparendoDTO.getComparendoAgente() != null) {
            lineaArchivo.setPlacaAgenteTransito(comparendoDTO.getComparendoAgente().getPlaca());
            lineaArchivo.setObservacionesAgente(comparendoDTO.getComparendoAgente().getObservacionesAgente());
        }
        lineaArchivo.setExisteFugaInfractor(ArchivoSimitUtil.reemplazaBoleano(comparendoDTO.getExisteFugaInfractor()));
        lineaArchivo.setReportaAccidente(ConstantesComparendo.VALOR_NO);

        ConfiguracionInfraccionDTO configInfraccion = iFachadaComparendo.consultarConfiguracionInfraccion(comparendoDTO
                .getInfraccion().getCodigo(), comparendoDTO.getFechaInfraccion());
        lineaArchivo.setReportaInmovilizacion(ConstantesComparendo.VALOR_NO);
        for (TipoSancionDTO tipoSancion : configInfraccion.getTipoSancionList()) {
            if (EnumTipoSancion.INMOVILIZACION_VEHICULO.getValue().equals(tipoSancion.getId())) {
                lineaArchivo.setReportaInmovilizacion(ConstantesComparendo.VALOR_SI);
            }
        }
        if (comparendoDTO.getComparendoPatio() != null) {
            lineaArchivo.setNombrePatio(comparendoDTO.getComparendoPatio().getNombre());
            if (comparendoDTO.getComparendoPatio().getDireccion() != null) {
                lineaArchivo.setDireccionPatioInmovilizacion(comparendoDTO.getComparendoPatio().getDireccion()
                        .toStringSinMunicipio());
            }
            if (comparendoDTO.getComparendoPatio().getNumeroGrua() != null) {
                lineaArchivo.setNumeroGrua(String.valueOf(comparendoDTO.getComparendoPatio().getNumeroGrua()));
            }
            lineaArchivo.setPlacaGrua(comparendoDTO.getComparendoPatio().getPlacaGrua());
            lineaArchivo.setConsecutivoInmovilizacion(comparendoDTO.getComparendoPatio().getConsecutivoAsignadoGrua());
        }

        // Para consultar los valores del comparendo y la tarifa de infracción utilizar el servicio (COM_048)
        LiquidarTarifaInfraccionDTO liquidarComparendoDTO = new LiquidarTarifaInfraccionDTO();
        liquidarComparendoDTO.setCodigoInfraccion(comparendoDTO.getInfraccion().getCodigo());
        liquidarComparendoDTO.setFechaLiquidacion(comparendoDTO.getFechaInfraccion());
        if (comparendoDTO.getGradoAlcoholemia() != null) {
            liquidarComparendoDTO.setGradoAlcoholemia(comparendoDTO.getGradoAlcoholemia().getValor());
        }
        if (comparendoDTO.getComparendoVehiculo() != null
                && comparendoDTO.getComparendoVehiculo().getTipoServicio() != null) {
            liquidarComparendoDTO.setIdClaseServicio(comparendoDTO.getComparendoVehiculo().getTipoServicio().getId());
        }
        if (comparendoDTO.getNiegaPruebaAlcoholemia() != null) {
            liquidarComparendoDTO.setNiegaPruebaAlcoholemia(comparendoDTO.getNiegaPruebaAlcoholemia());
        }
        if (comparendoDTO.getNumeroReincidencia() != null) {
            liquidarComparendoDTO.setNumeroReincidencias(comparendoDTO.getNumeroReincidencia());
        }

        BigDecimal valorTarifa = iFachadaComparendo.consultarTarifaInfraccion(liquidarComparendoDTO);
        lineaArchivo.setValorComparendo(valorTarifa.toPlainString());
        lineaArchivo.setValoresAdicionales(ConstantesComparendo.VALOR_CERO);
        lineaArchivo.setOrganismoReporta(comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito()
                .getMunicipio().getCodigo());
        // Catalogos de SIMIT
        String valorHomologado = ArchivoSimitUtil.obtenerValorHomologacionCache(
                EnumServicioEnvioSimit.ESTADO_COMPARENDO, comparendoDTO.getEstadoComparendo().getCodigo());
        if (valorHomologado == null) {
            valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.ESTADO_COMPARENDO,
                    comparendoDTO.getEstadoComparendo().getCodigo());
            ArchivoSimitUtil.agregarValorHomologacionCache(EnumServicioEnvioSimit.ESTADO_COMPARENDO, comparendoDTO
                    .getEstadoComparendo().getCodigo(), valorHomologado);
        }
        lineaArchivo.setEstadoComparendo(valorHomologado);
        // Catalogos de SIMIT Fin
        // Revisar polca
        // lineaArchivo.setPolca(ArchivoSimitUtil.reemplazaBoleano(comparendoDTO.getEsPolca()));
        lineaArchivo.setCodigoInfraccion(comparendoDTO.getInfraccion().getCodigo());
        lineaArchivo.setValorTarifaInfraccion(valorTarifa.toPlainString());
        if (comparendoDTO.getGradoAlcoholemia() != null) {
            lineaArchivo.setGradoAlcoholemia(String.valueOf(comparendoDTO.getGradoAlcoholemia().getValor()));
        }
        String tipoComparendo = ConstantesComparendo.VALOR_NO;
        if (EnumMedioImposicion.ELECTRONICO_DEAP.getValue().equals(comparendoDTO.getTipoComparendo().getId())) {
            tipoComparendo = ConstantesComparendo.VALOR_SI;
        }
        lineaArchivo.setTipoComparendo(tipoComparendo);
        if (comparendoDTO.getFechaNotificacion() != null) {
            lineaArchivo.setFechaNotificacion(sdfFecha.format(comparendoDTO.getFechaNotificacion()));
        }

        // iFachadaComparendo.actualizarEstadoComparendoSimit(comparendoDTO);
        iFachadaComparendo.notificarComparendoSIMIT(comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito()
                .getCodigoOrganismo(), comparendoDTO.getCicomparendo(), EnumAccionComparendo.REGISTRO);
        return lineaArchivo;
    }

    @Override
    public void registrarArchivoNotificacionComparendo(int codigoOrganismo) throws CirculemosNegocioException {
        logger.debug("SimitEJB::registrarArchivoNotificacionComparendo(int)");
        NotificacionCompaSimitLogDTO log = new NotificacionCompaSimitLogDTO();
        String loginUsuario = iSeguridadC2.obtenerUsuarioDto().getLogin();

        log.setCodigoOrganismo(codigoOrganismo);

        // GENERAR ARCHVO(S)
        NotificacionSimit notificacionSimit = null;
        try {
            notificacionSimit = crearRegistroArchivoNotificacionComparendo(codigoOrganismo);

            log.setFechaHoraGeneracion(notificacionSimit.getFechaGeneracion());
            log.setNumeroRegistros(notificacionSimit.getCantidadRegistros());
            log.setResultadoEnvio(notificacionSimit.getTipoResultadoEnvioSimit().getNombre());
            log.setTipoDocumentoEnvio(EnumTipoDocumentoEnvioSimit.COMPARENDO.getNombre());
        } catch (CirculemosNegocioException e) {
            // REGISTRAR EN LOG
            log.setFechaHoraGeneracion(new java.util.Date());
            log.setNumeroRegistros(0);
            log.setResultadoEnvio(EnumTipoResultadoEnvioSimit.ARCHIVO_GENERADO.getNombre());
            log.setTipoDocumentoEnvio(EnumTipoDocumentoEnvioSimit.COMPARENDO.getNombre());
            throw e;
        } finally {
            LoggerC2.getLogger(EnumLogSistema.NOTIFICACION_SIMIT).escribir(loginUsuario, log);
        }

    }

    @Override
    public void registrarArchivoNotificacionComparendoAutomatico(int codigoOrganismo) throws CirculemosNegocioException {
        logger.debug("SimitEJB::registrarArchivoNotificacionComparendoAutomatico(int)");

        // GENERAR ARCHVO(S)
        NotificacionSimit notificacionSimit = crearRegistroArchivoNotificacionComparendo(codigoOrganismo);

        // REGISTRAR EN LOG
        NotificacionCompaSimitLogDTO log = new NotificacionCompaSimitLogDTO();

        log.setCodigoOrganismo(codigoOrganismo);
        log.setFechaHoraGeneracion(notificacionSimit.getFechaGeneracion());
        log.setNumeroRegistros(notificacionSimit.getCantidadRegistros());
        log.setResultadoEnvio(notificacionSimit.getTipoResultadoEnvioSimit().getNombre());
        log.setTipoDocumentoEnvio(EnumTipoDocumentoEnvioSimit.COMPARENDO.getNombre());

        LoggerC2.getLogger(EnumLogSistema.NOTIFI_AUTO_COMPA_SIMIT).escribir(
                iSeguridadC2.obtenerUsuarioDto().getLogin(), log);
    }

    /**
     * Crea los registros de notificacion de archivo Simit, documento y archivo fisico
     * 
     * @param codigoOrganismo
     * @return El registro de la notificacion
     * @throws CirculemosNegocioException
     *             (Ver {@link IRNotificacionComparendoSimit#generarNotificacionComparendo(int, EnumOpcionGeneracionArchivo...)})
     */
    private NotificacionSimit crearRegistroArchivoNotificacionComparendo(int codigoOrganismo)
            throws CirculemosNegocioException {
        // 1 - GENERAR ARCHIVO(S)
        List<ArchivoNotificacionComparendoDTO> archivoNotificaCompaList = generarNotificacionComparendo(
                codigoOrganismo, EnumOpcionGeneracionArchivo.GENERAR_ARCHIVO);

        // Crear envio
        NotificacionSimit notificacionSimit = new NotificacionSimit();
        notificacionSimit.setArchivoNotificacionSimits(new ArrayList<ArchivoNotificacionSimit>());
        notificacionSimit.setCantidadRegistros(0);
        notificacionSimit.setCodigoOrganismo(new OrganismoTransito());
        notificacionSimit.getCodigoOrganismo().setCodigoOrganismo(codigoOrganismo);
        notificacionSimit.setTipoDocumentoEnvioSimit(new TipoDocumentoEnvioSimit());
        notificacionSimit.getTipoDocumentoEnvioSimit().setId(EnumTipoDocumentoEnvioSimit.COMPARENDO.getCodigo());
        notificacionSimit.setTipoResultadoEnvioSimit(new TipoResultadoEnvioSimit());

        // Crear carpeta envio de archivo(s)
        Date ahora = UtilFecha.buildCalendar().getTime();
        notificacionSimit.setFechaGeneracion(ahora);
        String formato = EnumCategoriaDocumento.ENVIO_COMPA_SIMIT.getFormato();
        String carpeta = String.format(formato, Integer.toString(codigoOrganismo),
                DateFormatUtils.format(ahora, "yyyyMMdd-HHmm"));

        for (ArchivoNotificacionComparendoDTO archivoNotificaCompa : archivoNotificaCompaList) {
            try {
                // 2 - REGISTRAR DOCUMENTO EN SERVIDOR GESTOR ARCHIVOS
                ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(archivoNotificaCompa.getNombre(),
                        archivoNotificaCompa.getContenido());
                archivo.setRuta(carpeta);
                String idDocumento = iRepositorioArchivo.registrarDocumento(EnumCategoriaDocumento.ENVIO_COMPA_SIMIT,
                        archivo);

                ArchivoNotificacionSimit archivoNotificaSimit = new ArchivoNotificacionSimit();
                archivoNotificaSimit.setCantidadRegistros(archivoNotificaCompa.getCantidadRegistros());
                archivoNotificaSimit.setIdDocumento(idDocumento);
                archivoNotificaSimit.setNotificacionSimit(notificacionSimit);
                archivoNotificaSimit.setNumeroOficio(archivoNotificaCompa.getNumeroOficio());
                archivoNotificaSimit.setRutaDocumento(carpeta);

                notificacionSimit.getArchivoNotificacionSimits().add(archivoNotificaSimit);
                notificacionSimit.setCantidadRegistros(notificacionSimit.getCantidadRegistros()
                        + archivoNotificaSimit.getCantidadRegistros());

                notificacionSimit.getTipoResultadoEnvioSimit().setId(
                        EnumTipoResultadoEnvioSimit.ARCHIVO_GENERADO.getCodigo());
                notificacionSimit.getTipoResultadoEnvioSimit().setNombre(
                        EnumTipoResultadoEnvioSimit.ARCHIVO_GENERADO.getNombre());
            } catch (CirculemosAlertaException e) {
                logger.debugv("Error crear archivo [ nombreCarpeta:{0}, nombreArchivo:{1} ]", carpeta,
                        archivoNotificaCompa.getNombre());
                logger.debugv("Exception: {0}", e.getMessage());

                notificacionSimit.setCantidadRegistros(0);
                notificacionSimit.getTipoResultadoEnvioSimit().setId(
                        EnumTipoResultadoEnvioSimit.FALLO_ERROR_CONEXION_REPO.getCodigo());
                notificacionSimit.getTipoResultadoEnvioSimit().setNombre(
                        EnumTipoResultadoEnvioSimit.FALLO_ERROR_CONEXION_REPO.getNombre());
            }
        }

        // 3 - PERSISTIR NOTIFICACION EN BD
        em.persist(notificacionSimit);

        return notificacionSimit;
    }

    @Override
    public HomologacionComparendoSIMITDTO validarComparendo(ValidacionComparendoSimitDTO validacionComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug("SimitEJB::validarComparendo(ValidacionComparendoSimitDTO)");

        final Integer ID_INFRACCION_F = 242;
        final String CONSECUTIVO_REGISTRO = "1";
        final String TIPO_VEHICULO_POR_DEFECTO = "0";
        final String TIPO_SERVICIO_POR_DEFECTO = "0";
        final String CODIGO_TIPO_INFRACTOR_POR_DEFECTO = "1";
        final String CERO_SIMIT = "0";

        // Valor máximo de comparendo para enviar a SIMIT
        ValorParametroDTO valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(
                EnumParametro.VALOR_MAX_COMPA_SIMIT, validacionComparendoDTO.getCodigoOrganismo(), true);
        int valorMaximoComparendo = valorParametroDTO.getValorParamInt();

        // Para saber si 'El plano para SIMIT incluye comparendos con valor 0' usar el parámetro: ARCH_SIMIT_INCLUYE_CERO
        valorParametroDTO = fachadaAdminGeneral.consultarValorParametro(EnumParametro.ARCH_SIMIT_INCLUYE_CERO,
                validacionComparendoDTO.getCodigoOrganismo(), true);
        boolean archSimitIncluyeCero = valorParametroDTO.getValorParamBoolean();

        HomologacionComparendoSIMITDTO homologacionComparendoSIMITDTO = new HomologacionComparendoSIMITDTO();

        // Consulta del comparendo
        List<ComparendoDTO> comparendoDTOList = iFachadaComparendo.consultarComparendos(0,
                validacionComparendoDTO.getCicomparendo());

        ComparendoDTO comparendoDTO = null;

        if (comparendoDTOList.size() > 0) {
            comparendoDTO = comparendoDTOList.get(0);
        }

        SimpleDateFormat sdfFecha = new SimpleDateFormat(ConstantesComparendo.FORMATO_FECHA);
        SimpleDateFormat sdfHora = new SimpleDateFormat(ConstantesComparendo.FORMATO_HORA);

        // Inicio de homologación
        // Información del comparendo
        homologacionComparendoSIMITDTO.setConsecutivo(CONSECUTIVO_REGISTRO);

        homologacionComparendoSIMITDTO.setNumeroComparendo(comparendoDTO.getOrdenComparendoNacional()
                .getNumeroComparendo());

        if (comparendoDTO.getFechaInfraccion() == null
                || (comparendoDTO.getFechaInfraccion().compareTo(Utilidades.getFechaActual())) > 0) {
            throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009001);
        }
        homologacionComparendoSIMITDTO
                .setFechaImposicionComparendo(sdfFecha.format(comparendoDTO.getFechaInfraccion()));

        if (comparendoDTO.getHoraInfraccion() == null) {
            throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009002);
        }
        homologacionComparendoSIMITDTO.setHoraImposicionComparendo(sdfHora.format(comparendoDTO.getHoraInfraccion()));

        if (comparendoDTO.getDireccion() == null) {
            throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009003);
        }
        String direccion = comparendoDTO.getDireccion().toStringSinMunicipio();

        if (direccion.length() > 50) {
            homologacionComparendoSIMITDTO.setDireccionLugarInfraccion(direccion.substring(0, 50));
        } else {
            homologacionComparendoSIMITDTO.setDireccionLugarInfraccion(direccion);
        }

        if (comparendoDTO.getDireccion().getMunicipio() == null) {
            throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009004);
        }

        homologacionComparendoSIMITDTO.setMunicipio(comparendoDTO.getDireccion().getMunicipio().getCodigo());

        if (comparendoDTO.getDireccion().getLocalidad() != null) {
            homologacionComparendoSIMITDTO.setLocalidad(comparendoDTO.getDireccion().getLocalidad().getCodigo());
        }

        // Datos del vehículo
        if (comparendoDTO.getComparendoVehiculo() != null) {
            homologacionComparendoSIMITDTO.setPlacaVehiculo(comparendoDTO.getComparendoVehiculo().getPlacaVehiculo());
            if (comparendoDTO.getComparendoVehiculo().getOrganismoMatriVehic() != null
                    && comparendoDTO.getComparendoVehiculo().getOrganismoMatriVehic().getMunicipio() != null) {
                homologacionComparendoSIMITDTO.setMatriculadoEn(comparendoDTO.getComparendoVehiculo()
                        .getOrganismoMatriVehic().getMunicipio().getCodigo());
            }

            if (comparendoDTO.getComparendoVehiculo().getClaseVehiculo() == null) {
                homologacionComparendoSIMITDTO.setTipoVehiculo(TIPO_VEHICULO_POR_DEFECTO);
            } else {
                String valorHomologado = null;
                valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TIPO_VEHICULO,
                        comparendoDTO.getComparendoVehiculo().getClaseVehiculo().getCodigo());
                homologacionComparendoSIMITDTO.setTipoVehiculo(valorHomologado);
            }
            if (comparendoDTO.getComparendoVehiculo().getTipoServicio() != null) {
                String valorHomologado = null;
                valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.CLASE_SERVICIO,
                        comparendoDTO.getComparendoVehiculo().getTipoServicio().getCodigo());
                homologacionComparendoSIMITDTO.setClaseServicio(valorHomologado);
                if (comparendoDTO.getComparendoVehiculo().getTipoServicio().getId() == EnumTipoServicio.PUBLICO
                        .getValue()) {
                    if (comparendoDTO.getComparendoVehiculo().getRadioAccion() == null) {
                        throw new CirculemosNegocioException(
                                ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009005);
                    }
                }
            } else {
                homologacionComparendoSIMITDTO.setClaseServicio(TIPO_SERVICIO_POR_DEFECTO);
            }

            if (comparendoDTO.getComparendoVehiculo().getRadioAccion() == null) {
                homologacionComparendoSIMITDTO.setRadioAccion(null);
            } else {
                String valorHomologado = null;
                valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.RADIO_ACCION, comparendoDTO
                        .getComparendoVehiculo().getRadioAccion().getCodigo());
                homologacionComparendoSIMITDTO.setRadioAccion(valorHomologado);
            }

            if (comparendoDTO.getComparendoVehiculo().getModalidad() != null) {
                String valorHomologado = null;
                valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.MODALIDAD_TRANSPORTE,
                        comparendoDTO.getComparendoVehiculo().getModalidad().getCodigo());
                homologacionComparendoSIMITDTO.setModalidadTransporte(valorHomologado);
            }

            if (comparendoDTO.getComparendoVehiculo().getTipoTransPasajero() != null) {
                String valorHomologado = null;
                valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TRANSPORTE_PASAJEROS,
                        comparendoDTO.getComparendoVehiculo().getTipoTransPasajero().getCodigo());
                homologacionComparendoSIMITDTO.setTransportePasajeros(valorHomologado);
            }

            // Datos del infractor
            homologacionComparendoSIMITDTO.setNumeroLicenciaTransito(comparendoDTO.getComparendoVehiculo()
                    .getNumeroLicenciaTransito());
            if (comparendoDTO.getComparendoVehiculo().getOrganismoLicenciaTransito() != null) {
                homologacionComparendoSIMITDTO.setOrganismoLicenciaTransito(comparendoDTO.getComparendoVehiculo()
                        .getOrganismoLicenciaTransito().getMunicipio().getCodigo());
            }

            // Numero tarjeta de operación
            homologacionComparendoSIMITDTO.setNumeroTarjetaOperacion(comparendoDTO.getComparendoVehiculo()
                    .getNumeroTarjetaOperacion());
        }

        if (comparendoDTO.getTipoInfractor() == null) {
            homologacionComparendoSIMITDTO.setTipoInfractor(CODIGO_TIPO_INFRACTOR_POR_DEFECTO);
        } else {
            String valorHomologado = null;
            valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TIPO_INFRACTOR, comparendoDTO
                    .getTipoInfractor().getCodigo());
            homologacionComparendoSIMITDTO.setTipoInfractor(valorHomologado);
        }

        // Catalogos de SIMIT Fin
        if (comparendoDTO.getPersonaList() != null) {
            for (ComparendoPersonaDTO comparendoPersona : comparendoDTO.getPersonaList()) {
                /*
                 * 
                 * Homologación datos del infractor
                 */
                if (EnumTipoPersonaComparendo.INFRACTOR.getValue().equals(
                        comparendoPersona.getTipoPersonaComparendo().getCodigo())) {
                    // Numero de documento del infractor Obligatorio
                    if (comparendoPersona.getNumeroIdentificacion() == null) {
                        throw new CirculemosNegocioException(
                                ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009006);
                    }
                    homologacionComparendoSIMITDTO.setNumeroDocumentoInfractor(comparendoPersona
                            .getNumeroIdentificacion());
                    // Catalogo de SIMIT Obligatorio
                    if (comparendoPersona.getTipoIdentificacion() == null) {
                        throw new CirculemosNegocioException(
                                ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009007);
                    } else {
                        String valorHomologado = null;
                        valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TIPO_DOCUMENTO,
                                comparendoPersona.getTipoIdentificacion().getCodigo());
                        homologacionComparendoSIMITDTO.setTipoDocumentoInfractor(valorHomologado);
                    }

                    homologacionComparendoSIMITDTO.setNombresInfractor(ArchivoSimitUtil.validaLongitud(
                            comparendoPersona.getNombres(), 18));

                    homologacionComparendoSIMITDTO.setApellidosInfractor(ArchivoSimitUtil.validaLongitud(
                            comparendoPersona.getApellidos(), 20));

                    if (comparendoPersona.getEdad() != null) {
                        homologacionComparendoSIMITDTO.setEdadInfractor(String.valueOf(comparendoPersona.getEdad()));
                    }

                    if (comparendoPersona.getDireccion() != null) {
                        homologacionComparendoSIMITDTO.setDireccionInfractor(comparendoPersona.getDireccion()
                                .toStringSinMunicipio());
                        homologacionComparendoSIMITDTO.setCiudadResidenciaInfractor(comparendoPersona.getDireccion()
                                .getMunicipio().getCodigo());
                    }

                    homologacionComparendoSIMITDTO.setCorreoElectronicoInfractor(comparendoPersona.getEmail());

                    homologacionComparendoSIMITDTO.setTelefonoInfractor(ArchivoSimitUtil.validaLongitud(
                            comparendoPersona.getTelefonoFijo(), 15));

                    homologacionComparendoSIMITDTO.setNumeroLicenciaConduccion(comparendoPersona.getNumeroLicencia());

                    if (comparendoPersona.getCategoriaLicencia() != null) {
                        homologacionComparendoSIMITDTO.setCategoriaLicenciaConduccion(comparendoPersona
                                .getCategoriaLicencia().getNombre());
                    }

                    if (comparendoPersona.getOrganismoTransito() != null) {
                        homologacionComparendoSIMITDTO.setOrganismoExpideLicencia(comparendoPersona
                                .getOrganismoTransito().getMunicipio().getCodigo());
                    }

                    if (comparendoPersona.getFechaVencimientoLicenCondu() != null) {
                        homologacionComparendoSIMITDTO.setFechaVencimientoLicenciaConduccionInfractor(sdfFecha
                                .format(comparendoPersona.getFechaVencimientoLicenCondu()));
                    }

                    /*
                     * 
                     * Homologación datos del propietario
                     */
                } else if (EnumTipoPersonaComparendo.PROPIETARIO.getValue().equals(
                        comparendoPersona.getTipoPersonaComparendo().getCodigo())) {

                    // Número Documento del propietario
                    homologacionComparendoSIMITDTO.setNumeroDocumentoPropietario(comparendoPersona
                            .getNumeroIdentificacion());

                    String valorHomologado = null;
                    valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.TIPO_DOCUMENTO,
                            comparendoPersona.getTipoIdentificacion().getCodigo());
                    // Tipo de documento del propietario
                    homologacionComparendoSIMITDTO.setTipoDocumentoPropietario(valorHomologado);

                    homologacionComparendoSIMITDTO.setNombresApellidosPropietario(ArchivoSimitUtil.validaLongitud(
                            comparendoPersona.getNombreCompleto(), 50));

                } else if (EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getValue().equals(
                        comparendoPersona.getTipoPersonaComparendo().getCodigo())) {

                    homologacionComparendoSIMITDTO.setRazonSocial(ArchivoSimitUtil.validaLongitud(
                            comparendoPersona.getRazonSocial(), 30));

                    homologacionComparendoSIMITDTO.setNumeroDocumentoEmpresa(comparendoPersona
                            .getNumeroIdentificacion());
                    /*
                     * 
                     * Homologación datos del testigo
                     */
                } else if (EnumTipoPersonaComparendo.TESTIGO.getValue().equals(
                        comparendoPersona.getTipoPersonaComparendo().getCodigo())) {

                    homologacionComparendoSIMITDTO.setNumeroDocumentoTestigo(comparendoPersona
                            .getNumeroIdentificacion());

                    homologacionComparendoSIMITDTO.setNombresApellidosTestigo(ArchivoSimitUtil.validaLongitud(
                            comparendoPersona.getNombreCompleto(), 50));

                    if (comparendoPersona.getDireccion() != null) {
                        homologacionComparendoSIMITDTO.setDireccionTestigo(ArchivoSimitUtil.validaLongitud(
                                comparendoPersona.getDireccion().toStringSinMunicipio(), 40));
                    }

                    homologacionComparendoSIMITDTO.setNumeroCelularTestigo(ArchivoSimitUtil.validaLongitud(
                            comparendoPersona.getTelefonoMovil(), 15));
                }
            }
        }

        /*
         * 
         * Homologación datos del agente
         */
        if (comparendoDTO.getComparendoAgente() != null) {
            if (comparendoDTO.getComparendoAgente().getPlaca() == null) {
                throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009008);
            }
            homologacionComparendoSIMITDTO.setPlacaAgenteTransito(comparendoDTO.getComparendoAgente().getPlaca());

            homologacionComparendoSIMITDTO.setObservacionesAgente(ArchivoSimitUtil.validaLongitud(comparendoDTO
                    .getComparendoAgente().getObservacionesAgente(), 50));

        }
        if (comparendoDTO.getExisteFugaInfractor() != null) {
            homologacionComparendoSIMITDTO.setExisteFugaInfractor(ArchivoSimitUtil.reemplazaBoleano(comparendoDTO
                    .getExisteFugaInfractor()));
        } else {
            homologacionComparendoSIMITDTO.setExisteFugaInfractor(ArchivoSimitUtil.reemplazaBoleano(Boolean.FALSE));
        }

        homologacionComparendoSIMITDTO.setReportaAccidente(ConstantesComparendo.VALOR_NO);

        ConfiguracionInfraccionDTO configInfraccion = iFachadaComparendo.consultarConfiguracionInfraccion(comparendoDTO
                .getInfraccion().getCodigo(), comparendoDTO.getFechaInfraccion());

        homologacionComparendoSIMITDTO.setReportaInmovilizacion(ConstantesComparendo.VALOR_NO);

        if (configInfraccion != null && configInfraccion.getTipoSancionList() != null) {
            for (TipoSancionDTO tipoSancion : configInfraccion.getTipoSancionList()) {
                if (EnumTipoSancion.INMOVILIZACION_VEHICULO.getValue().equals(tipoSancion.getId())) {
                    homologacionComparendoSIMITDTO.setReportaInmovilizacion(ConstantesComparendo.VALOR_SI);
                }
            }
        } else {
            homologacionComparendoSIMITDTO.setReportaInmovilizacion(ConstantesComparendo.VALOR_NO);
        }

        if (comparendoDTO.getComparendoPatio() != null) {
            homologacionComparendoSIMITDTO.setNombrePatio(ArchivoSimitUtil.validaLongitud(comparendoDTO
                    .getComparendoPatio().getNombre(), 30));

            if (comparendoDTO.getComparendoPatio().getDireccion() != null) {
                homologacionComparendoSIMITDTO.setDireccionPatioInmovilizacion(ArchivoSimitUtil.validaLongitud(
                        comparendoDTO.getComparendoPatio().getDireccion().toStringSinMunicipio(), 30));
            }

            homologacionComparendoSIMITDTO.setNumeroGrua(ArchivoSimitUtil.validaLongitud(
                    String.valueOf(comparendoDTO.getComparendoPatio().getNumeroGrua()), 10));

            homologacionComparendoSIMITDTO.setPlacaGrua(comparendoDTO.getComparendoPatio().getPlacaGrua());
            homologacionComparendoSIMITDTO.setConsecutivoInmovilizacion(comparendoDTO.getComparendoPatio()
                    .getConsecutivoAsignadoGrua());
        }

        /*
         * 
         * Información de infracción
         */
        LiquidarTarifaInfraccionDTO liquidarComparendoDTO = new LiquidarTarifaInfraccionDTO();
        liquidarComparendoDTO.setCodigoInfraccion(comparendoDTO.getInfraccion().getCodigo());
        liquidarComparendoDTO.setFechaLiquidacion(comparendoDTO.getFechaInfraccion());
        if (comparendoDTO.getGradoAlcoholemia() != null) {
            liquidarComparendoDTO.setGradoAlcoholemia(comparendoDTO.getGradoAlcoholemia().getValor());
        }
        if (comparendoDTO.getComparendoVehiculo() != null
                && comparendoDTO.getComparendoVehiculo().getTipoServicio() != null) {
            liquidarComparendoDTO.setIdClaseServicio(comparendoDTO.getComparendoVehiculo().getTipoServicio().getId());
        }
        if (comparendoDTO.getNiegaPruebaAlcoholemia() != null) {
            liquidarComparendoDTO.setNiegaPruebaAlcoholemia(comparendoDTO.getNiegaPruebaAlcoholemia());
        }
        if (comparendoDTO.getNumeroReincidencia() != null) {
            liquidarComparendoDTO.setNumeroReincidencias(comparendoDTO.getNumeroReincidencia());
        }

        BigDecimal valorTarifa = iFachadaComparendo.consultarTarifaInfraccion(liquidarComparendoDTO);

        if (archSimitIncluyeCero && validacionComparendoDTO.getOrigenEnvioSimit().equals(EnumAccionComparendo.REGISTRO)) {
            homologacionComparendoSIMITDTO.setValorComparendo(CERO_SIMIT);
        } else {
            if (valorTarifa.intValue() > valorMaximoComparendo) {
                throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009009);
            }
            homologacionComparendoSIMITDTO.setValorComparendo(valorTarifa.toPlainString());
        }

        homologacionComparendoSIMITDTO.setValoresAdicionales(ConstantesComparendo.VALOR_CERO);
        if (comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getMunicipio() == null) {
            throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009010);
        }

        homologacionComparendoSIMITDTO.setOrganismoReporta(comparendoDTO.getOrdenComparendoNacional()
                .getOrganismoTransito().getMunicipio().getCodigo());

        if (comparendoDTO.getEstadoComparendo() == null) {
            throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009011);
        }
        // Catalogos de SIMIT
        String valorHomologado = null;
        valorHomologado = iFachadaConfiguracion.obtenerValor(EnumServicioEnvioSimit.ESTADO_COMPARENDO, comparendoDTO
                .getEstadoComparendo().getCodigo());

        homologacionComparendoSIMITDTO.setEstadoComparendo(valorHomologado);
        // Catalogos de SIMIT Fin

        // es polca
        if (comparendoDTO.getTipoAgenteImpositorDTO().getId().equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
            homologacionComparendoSIMITDTO.setPolca(ArchivoSimitUtil.reemplazaBoleano(true));
        } else {
            homologacionComparendoSIMITDTO.setPolca(ConstantesComparendo.VALOR_NO);
        }
        if (comparendoDTO.getInfraccion() == null) {
            throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009012);
        }

        homologacionComparendoSIMITDTO.setCodigoInfraccion(comparendoDTO.getInfraccion().getCodigo());

        homologacionComparendoSIMITDTO.setValorTarifaInfraccion(valorTarifa.toPlainString());

        if (comparendoDTO.getInfraccion().getId() == ID_INFRACCION_F && comparendoDTO.getGradoAlcoholemia() == null) {
            throw new CirculemosNegocioException(ErrorSimit.EnumValidarComparendosParaNotificarASimit.SIM_009013);
        }
        if (comparendoDTO.getGradoAlcoholemia() != null) {
            homologacionComparendoSIMITDTO.setGradoAlcoholemia(String.valueOf(comparendoDTO.getGradoAlcoholemia()
                    .getValor()));
        } else {
            homologacionComparendoSIMITDTO.setGradoAlcoholemia(null);
        }
        String tipoComparendo = ConstantesComparendo.VALOR_NO;

        if (EnumMedioImposicion.FOTOMULTA.getValue().equals(comparendoDTO.getMedioImposicion().getId())) {
            tipoComparendo = ConstantesComparendo.VALOR_SI;
        }
        homologacionComparendoSIMITDTO.setTipoComparendo(tipoComparendo);

        if (homologacionComparendoSIMITDTO.getTipoComparendo().equals(ConstantesComparendo.VALOR_SI)) {
            homologacionComparendoSIMITDTO.setFechaNotificacion(sdfFecha.format(comparendoDTO.getFechaInfraccion()));
        }

        if (!(validacionComparendoDTO.getOrigenEnvioSimit().equals(EnumAccionComparendo.RECTIFICACION) || validacionComparendoDTO
                .getOrigenEnvioSimit().equals(EnumAccionComparendo.REGISTRO))) {
            if (!(comparendoDTO.getEstadoComparendo().getId() == EnumEstadoComparendoSimit.GENERADO.getValue())) {
                homologacionComparendoSIMITDTO.setEstadoComparendo(EnumCodigoEstadoComparendoSimit.PENDIENTE
                        .getCodigo().toString());
            } else {
                homologacionComparendoSIMITDTO
                        .setEstadoComparendo(EnumCodigoEstadoComparendoSimit.ACTUALIZACION_COMPARENDO.getCodigo()
                                .toString());
            }
        } else {
            homologacionComparendoSIMITDTO.setEstadoComparendo(EnumCodigoEstadoComparendoSimit.PENDIENTE.getCodigo()
                    .toString());
        }

        return homologacionComparendoSIMITDTO;
    }

    @Override
    public boolean notificarComparendo(NotificacionComparendoSIMITDTO notificacion) throws CirculemosNegocioException {

        if (notificacion.getCicomparendo() == null) {
            throw new CirculemosNegocioException(EnumNotificacionComparendoSIMIT.SIM_001001);
        }
        if (notificacion.getOrigenSIMIT() == null) {
            throw new CirculemosNegocioException(EnumNotificacionComparendoSIMIT.SIM_001002);
        }

        // Verifica que las reglas de cargue de informacion a SIMIT
        ValidacionComparendoSimitDTO validacionComparendoDTO = new ValidacionComparendoSimitDTO();
        validacionComparendoDTO.setCicomparendo(notificacion.getCicomparendo());
        validacionComparendoDTO.setCodigoOrganismo(notificacion.getCodigoOrganismo());
        validacionComparendoDTO.setOrigenEnvioSimit(notificacion.getOrigenSIMIT());
        HomologacionComparendoSIMITDTO homologacionComparendoSIMITDTO = null;

        ComparendoDTO comparendoDTO = iFachadaComparendo.consultarComparendos(notificacion.getCodigoOrganismo(),
                notificacion.getCicomparendo()).get(0);
        try {
            homologacionComparendoSIMITDTO = validarComparendo(validacionComparendoDTO);
        } catch (CirculemosNegocioException e) {
            ILog logger = LoggerC2.getLogger(EnumLogSistema.NOTIF_COMPA_SIMIT_WS);
            logger.escribir(iSeguridadC2.obtenerUsuarioDto().getLogin(),
                    new LoggerNotificacionCompSIMIT(comparendoDTO.getOrdenComparendoNacional().getNumeroComparendo(),
                            EstadoTransaccion.RECHAZADO.toString(), e.getMessage()));
            return false;
        }
        if (homologacionComparendoSIMITDTO != null) {
            // Envia mediante el web service SIMIT la informacion recibida y validada del comparendo
            RespuestaNotificacionDTO respuestaWS = clienteWSSIMITEJB
                    .notificarComparendoSIMIT(homologacionComparendoSIMITDTO);

            // Recibe por parte del web service SIMIT la respuesta de CONFIRMACION de cargue de la informacion enviada.
            if (respuestaWS.getResultado().equals(EnumRespuestaWS.SATISFACTORIO)) {
                iFachadaComparendo.notificarComparendoSIMIT(notificacion.getCodigoOrganismo(),
                        notificacion.getCicomparendo(), notificacion.getOrigenSIMIT());
            } else {
                ILog loggerC2 = LoggerC2.getLogger(EnumLogSistema.NOTIF_COMPA_SIMIT_WS);
                String pattern = "\n COD{0} - {1}\nDescripcion:\n {2}\nDetalle:{3}";
                String mensaje = MessageFormat.format(pattern, respuestaWS.getCodigoRespuesta(), respuestaWS
                        .getResultado().toString(), respuestaWS.getMensaje(), respuestaWS.getDetalle());

                loggerC2.escribir(iSeguridadC2.obtenerUsuarioDto().getLogin(),
                        new LoggerNotificacionCompSIMIT(comparendoDTO.getOrdenComparendoNacional()
                                .getNumeroComparendo(), EstadoTransaccion.RECHAZADO.toString(), mensaje));
                return false;
            }
        }

        return true;
    }
}