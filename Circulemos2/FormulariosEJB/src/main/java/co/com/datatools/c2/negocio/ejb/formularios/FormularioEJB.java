package co.com.datatools.c2.negocio.ejb.formularios;

import static com.google.common.base.Preconditions.checkNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.AsignacionDTO;
import co.com.datatools.c2.dto.formularios.CambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.CambioEstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.CantidadRangoDTO;
import co.com.datatools.c2.dto.formularios.CifrasControlEstadoFormularioAsignacionDTO;
import co.com.datatools.c2.dto.formularios.ConsultaDetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.ConsultaRangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ConsultaRangoTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ConsultaSeguimientoFormularioDTO;
import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.dto.formularios.DocumentoFormularioDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioAsignacionDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.dto.formularios.RangoDTO;
import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.RelacionEstadosDTO;
import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.SeguimientoFormularioDTO;
import co.com.datatools.c2.dto.formularios.StockTipoResponsableDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.entidades.CausalCambioEstado;
import co.com.datatools.c2.entidades.DetalleCambioEstado;
import co.com.datatools.c2.entidades.DetalleNumeracion;
import co.com.datatools.c2.entidades.DocumentoFormulario;
import co.com.datatools.c2.entidades.EstadoFormulario;
import co.com.datatools.c2.entidades.Formulario;
import co.com.datatools.c2.entidades.NumeracionFormulario;
import co.com.datatools.c2.entidades.RangoFormulario;
import co.com.datatools.c2.entidades.RelacionEstados;
import co.com.datatools.c2.entidades.ResponsableFormulario;
import co.com.datatools.c2.entidades.SeguimientoFormulario;
import co.com.datatools.c2.entidades.StockTipoResponsable;
import co.com.datatools.c2.entidades.TipoFormulario;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;
import co.com.datatools.c2.enumeracion.EnumVariablesCorreo;
import co.com.datatools.c2.enumeraciones.EnumCausalCambioEstado;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.enumeraciones.EnumTipoFormulario;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.ejb.aud.UsuarioSesionEJB;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios.AdministrarRango;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios.AdministrarStockTipoResponsable;
import co.com.datatools.c2.negocio.error.formularios.ErrorFormularios.CambioEstadosFormularios;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.helpers.formularios.CambioEstadosFormularioSQL;
import co.com.datatools.c2.negocio.helpers.formularios.DetalleCambioEstadoHelper;
import co.com.datatools.c2.negocio.helpers.formularios.DetalleCambioEstadoHelperExtend;
import co.com.datatools.c2.negocio.helpers.formularios.DetalleNumeracionHelper;
import co.com.datatools.c2.negocio.helpers.formularios.DocumentoFormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.FormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.FormularioHelperExtend;
import co.com.datatools.c2.negocio.helpers.formularios.NumeracionFormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.RangoFormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.ResponsableFormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.SeguimientoFormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.StockTipoResponsableHelper;
import co.com.datatools.c2.negocio.helpers.formularios.TipoFormularioHelper;
import co.com.datatools.c2.negocio.helpers.formularios.UnificacionResponsableHelper;
import co.com.datatools.c2.negocio.helpers.formularios.UtilFormularios;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.formularios.ILAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.ILFormulario;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.ILRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.EstadosFormulariosUtil;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

@Stateless(name = "FormularioEJB")
@LocalBean
public class FormularioEJB implements ILFormulario, IRFormulario, ILProcesamientoFormularios {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(FormularioEJB.class.getName());

    @EJB
    private ILRepositorioArchivo documentosEjb;
    @EJB
    private ILAdministracionFormularios administracionFormulariosEJB;
    @EJB
    private IRCirculemosMailSender circulemosMailSenderEjb;
    @EJB
    private ILProcesamientoFormularios ilProcesamientoFormularios;
    @EJB
    private UsuarioSesionEJB usuarioSesionEJB;
    @EJB
    private IRFachadaAdminNegocio iRFachadaPersona;
    @EJB
    private ILSeguridadCirculemos iLSeguridadCirculemos;

    public static final int TAMANIO_DIVISION_ARREGLO = 3000;
    public static final int CANTIDAD_FORMULARIOS = 1;

    public static final String SQ_UPD_SEGUIMIENTO_BY_DETALLE_CAMBIO = "UPDATE SeguimientoFormulario seg SET seg.observaciones = :observacion WHERE seg.detalleCambioEstado.id = :idDetalleCambioEstado";
    public static final String SQ_UPD_SEGUIMIENTO_BY_DETALLE_CAMBIO_P1 = "observacion";
    public static final String SQ_UPD_SEGUIMIENTO_BY_DETALLE_CAMBIO_P2 = "idDetalleCambioEstado";
    public static final String SQ_DEL_SEGUIMIENTO_BY_DETALLE_CAMBIO = "DELETE FROM SeguimientoFormulario s WHERE s.detalleCambioEstado.id = :idDetalleCambioEstado";
    public static final String SQ_DEL_SEGUIMIENTO_BY_DETALLE_CAMBIO_P1 = "idDetalleCambioEstado";

    @Resource
    private SessionContext ctx;

    @Override
    public void registrarRangoFormulario(RangoFormularioDTO rangoFormularioDTO) throws CirculemosNegocioException,
            CirculemosAlertaException {
        logger.debug("FormularioEJB::registrarRangoFormulario");

        // Realiza validaciones para creacion de rango de formularios
        if (validarRangoFormulario(rangoFormularioDTO)) {
            rangoFormularioDTO.setAlertaStock(false);

            // Consultar el detalle de la numeracion para poder calcular la cantidad de numeros en el rango
            rangoFormularioDTO.getNumeracion().setDetalleNumeracionList(
                    administracionFormulariosEJB.consultarDetalleNumeracionFormulario(rangoFormularioDTO
                            .getNumeracion().getId()));
            // Calcular la cantidad de formularios
            UtilFormularios util = new UtilFormularios();
            Integer cantidadFormularios = util.calcularCantidadFormularios(rangoFormularioDTO.getNumeroInicial(),
                    rangoFormularioDTO.getNumeroFinal(), rangoFormularioDTO.getNumeracion());
            rangoFormularioDTO.setCantidadTotal(cantidadFormularios);
            rangoFormularioDTO.setCantidadDisponible(cantidadFormularios);

            try {
                if (rangoFormularioDTO.getArchivoAutorizacion() != null) {
                    // Crea documento en el repositorio
                    String idDocumento = documentosEjb.registrarDocumento(
                            EnumCategoriaDocumento.AUTORIZACION_RANGO_FORMULARIO,
                            rangoFormularioDTO.getArchivoAutorizacion());
                    DocumentoFormularioDTO documentoFormularioDTO = new DocumentoFormularioDTO();
                    documentoFormularioDTO.setIdDocumento(idDocumento);
                    documentoFormularioDTO = registrarDocumentoFormulario(documentoFormularioDTO);
                    rangoFormularioDTO.setDocumentoFormulario(documentoFormularioDTO);
                }
            } catch (CirculemosAlertaException e) {
                logger.info(e.getMessage());
            } finally {
                RangoFormulario entidadRango = RangoFormularioHelper.toLevel1Entity(rangoFormularioDTO,
                        new RangoFormulario());
                em.persist(entidadRango);
                logger.debugv(
                        "FormularioEJB::registrarRangoFormulario: Se registra el rango de {0} hasta {1} con ID= {2} ",
                        entidadRango.getNumeroInicial(), entidadRango.getNumeroFinal(), entidadRango.getId());
            }

        }
    }

    /**
     * Se encarga de validar la posibilidad de creación del rango , de acuerdo a las condiciones de numeración y cruce con otros rangos del mismo tipo
     * de formulario
     * 
     * @param idRangoFormulario
     * @return true Si paso la validacion
     * @throws CirculemosNegocioException
     */
    private boolean validarRangoFormulario(RangoFormularioDTO rangoFormularioDTO) throws CirculemosNegocioException {
        // Identificar la numeracion a la cual pertenece el rango y asignarla al DTO
        String numeroInicial = rangoFormularioDTO.getNumeroInicial();
        String numeroFinal = rangoFormularioDTO.getNumeroFinal();
        String tramaNumeroInicial = obtenerTramaNumeroFormulario(numeroInicial);
        List<NumeracionFormulario> numeracionesRelacionadas = consultarNumeracionFormulario(rangoFormularioDTO
                .getTipoFormulario().getId(), rangoFormularioDTO.getFechaAutorizacion(), tramaNumeroInicial);
        if (numeracionesRelacionadas.isEmpty()) {
            // Error no hay numeraciones vigentes para la fecha de autorizacion ingresada
            throw new CirculemosNegocioException(AdministrarRango.ADM_030001);
        }
        // Hay una o varias numeraciones en esa vigencia, verificar a cual pertenece el rango enviado, si llega a pertenecer a varias, se toma la
        // numeracion con vigencia mas antigua(validado con analisis) y asignar el id de esta numeracion al DTO

        for (NumeracionFormulario numeracionFormulario : numeracionesRelacionadas) {
            String patronObtenido = obtenerExpresionRegularNumeracion(numeracionFormulario);
            if (numeroInicial.matches(patronObtenido) && numeroFinal.matches(patronObtenido)) {
                // // El rango pertenece a esta numeracion pero debe validar que numero final sea mayor al inicial
                if (!esNumIniMenorIgualNumFin(numeroInicial, numeroFinal)) {
                    // Error: el numero final debe ser mayor al inicial
                    throw new CirculemosNegocioException(AdministrarRango.ADM_030002);
                }
                rangoFormularioDTO.setNumeracion(NumeracionFormularioHelper.toLevel0DTO(numeracionFormulario));
                break;
            }
        }
        if (rangoFormularioDTO.getNumeracion().getId() == null) {
            // Error: el rango no pertenece a ninguna numeracion
            throw new CirculemosNegocioException(AdministrarRango.ADM_030003);
        }

        List<RangoFormulario> resultado = consultarRangosXNumeros(rangoFormularioDTO.getNumeracion().getId(),
                numeroInicial, numeroFinal);
        if (!resultado.isEmpty()) {
            // Error, el rango ya esta ingresado
            throw new CirculemosNegocioException(AdministrarRango.ADM_030004);
        }
        return true;

    }

    /**
     * Permite identificar si un rango es menor igual que otro rango
     * 
     * @param numeroInicial
     *            primer rango a comparar
     * @param numeroFinal
     *            segundo rango a comparar
     * @return true si el numero inicial es menor o igual al final, false si el numero final es menor al inicial
     * @author luis.forero(2016-02-03)
     */
    private boolean esNumIniMenorIgualNumFin(String numeroInicial, String numeroFinal) {
        return mayorIgualQue(0, (short) 0, numeroInicial, numeroFinal);
    }

    /**
     * Verifica si el numero o rango inicial es menor o igual al inicial
     * 
     * @param index
     *            indice que debe comenzar en 0 para su correcto funcionamiento en el algoritmo
     * @param comparador
     *            0 para cuando es igual o inicia, 1 para cuando el inicial es menor al final y -1 cuando el inicial es mayor al final
     * @param numeroInicial
     *            rango inicio a ser verificado
     * @param numeroFinal
     *            rango final a ser verificado
     * @return true si el numero inicial es menor o igual al final, false si el numero final es menor al inicial
     * @author luis.forero(2016-02-03)
     */
    private boolean mayorIgualQue(int index, short comparador, String numeroInicial, String numeroFinal) {
        if (index == numeroInicial.length() || comparador == (short) 1) {
            return true;
        } else if (comparador == (short) -1) {
            return false;
        }
        if (comparador == (short) 0) {
            if (numeroInicial.codePointAt(index) > numeroFinal.codePointAt(index)) {
                comparador = (short) -1;
            } else if (numeroInicial.codePointAt(index) < numeroFinal.codePointAt(index)) {
                comparador = (short) 1;
            }
            index++;
        }
        return mayorIgualQue(index, comparador, numeroInicial, numeroFinal);
    }

    /**
     * Registra el documento del formulario
     * 
     * @param documentoFormularioDTO
     *            documento del formulario a registrar
     */
    private DocumentoFormularioDTO registrarDocumentoFormulario(DocumentoFormularioDTO documentoFormularioDTO) {
        logger.debug("FormularioEJB::registrarRangoFormulario");
        DocumentoFormulario entidadDocumentoFormulario = DocumentoFormularioHelper.toLevel1Entity(
                documentoFormularioDTO, new DocumentoFormulario());
        em.persist(entidadDocumentoFormulario);
        documentoFormularioDTO.setId(entidadDocumentoFormulario.getId());
        logger.debugv(
                "FormularioEJB::registrarRangoFormulario: Se registra el documento formulario de numero {0} e id repositorio {1} con ID= {2} ",
                entidadDocumentoFormulario.getNumeroDocumento(), entidadDocumentoFormulario.getIdDocumento(),
                entidadDocumentoFormulario.getId());
        return documentoFormularioDTO;
    }

    /**
     * Obtiene una expresion regular para una numeracion basada en la configuracion de cada casilla
     * 
     * @param numeracionFormulario
     *            Dto con los datos de la numeracion
     * @return Expresion regular de la numeracion
     * @author claudia.rodriguez
     */
    private String obtenerExpresionRegularNumeracion(NumeracionFormulario numeracionFormulario) {
        StringBuilder patron = new StringBuilder();
        List<DetalleNumeracion> detalle = numeracionFormulario.getDetalleNumeracionList();
        for (DetalleNumeracion detalleNumeracion : detalle) {
            if (detalleNumeracion.getIncremental())
                patron.append("[" + detalleNumeracion.getCaracterInicio() + "-" + detalleNumeracion.getCaracterFin()
                        + "]");
            else
                patron.append(detalleNumeracion.getCaracterInicio());
        }
        return patron.toString();
    }

    /**
     * Consulta los rangos de formularios que cumplan de la numeracion enviada que contengan total o parcialmente los numeros comprendidos entre el
     * numeroInicial y numeroFinal señalados
     * 
     * @param idNumeracion
     *            Id de la numeracion cuyos rangos seran consultados
     * @param numeroInicial
     *            Numero desde el cual se validara si los numeros ya estan contenidos en algun rango
     * @param numeroFinal
     *            Numero hasta el cual se validara si los numeros ya estan contenidos en algun rango
     * @return Rangos que pertenecen a la numeracion enviada que contienen total o parcialmente los numeros comprendidos entre numeroInicial y
     *         numeroFinal
     * @author claudia.rodriguez
     */
    private List<RangoFormulario> consultarRangosXNumeros(Integer idNumeracion, String numeroInicial, String numeroFinal) {
        TypedQuery<RangoFormulario> q = em
                .createNamedQuery(RangoFormulario.SQ_RANGOS_BY_NUMEROS, RangoFormulario.class);
        q.setParameter("idNumeracion", idNumeracion);
        q.setParameter("numeroInicial", numeroInicial);
        q.setParameter("numeroFinal", numeroFinal);
        return q.getResultList();
    }

    /**
     * Obtiene la trama(el formato) que tiene un numero de formulario dado
     * 
     * @param numeroFormulario
     *            Numero de formulario para el cual se obtiene la trama
     * @return Trama del numero de formulario enviado
     * @author claudia.rodriguez
     */
    private String obtenerTramaNumeroFormulario(String numeroFormulario) {
        StringBuilder trama = new StringBuilder();
        for (int i = 0; i < numeroFormulario.length(); i++) {
            char caracter = numeroFormulario.charAt(i);
            if (Character.isDigit(caracter)) {
                trama.append(AdministracionFormulariosEJB.TRAMA_NUMERACION_NUMERO);
            } else if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                trama.append(AdministracionFormulariosEJB.TRAMA_NUMERACION_LETRA_MINUSCULA);
            } else if (Character.isLetter(caracter) && Character.isUpperCase(caracter))
                trama.append(AdministracionFormulariosEJB.TRAMA_NUMERACION_LETRA_MAYUSCULA);
        }
        // logger.debugv("AdministracionFormulariosEJB::obtenerTramaNumeroFormulario: la trama obtenida para el numero {0} es = {1}",numeroFormulario,
        // trama.toString());
        return trama.toString();
    }

    /**
     * Consulta las numeraciones de formulario para el tipo de formulario enviado,cuya vigencia cubra la fecha indicada
     * 
     * @param idTipoFormulario
     *            Id del tipo de formulario para el cual se consultaran las numeraciones
     * @param fechaAutorizacion
     *            Fecha que debe estar contenida dentro de la vigencia de las numeraciones a consultar
     * @return Listado de NumeracionFormulario que cumplen con los parametros enviados
     * @author claudia.rodriguez
     */
    private List<NumeracionFormulario> consultarNumeracionFormulario(Integer idTipoFormulario, Date fechaAutorizacion,
            String tramaNumeroInicial) {
        TypedQuery<NumeracionFormulario> q = em.createNamedQuery(NumeracionFormulario.SQ_NUMERACION_BY_FECHA,
                NumeracionFormulario.class);
        q.setParameter("idTipoFormulario", idTipoFormulario);
        q.setParameter("fecha", fechaAutorizacion);
        q.setParameter("trama", tramaNumeroInicial);
        return q.getResultList();

    }

    @Override
    public List<RangoFormularioDTO> consultarRangoFormulario(ConsultaRangoFormularioDTO consultaRangoFormularioDTO)
            throws CirculemosNegocioException {
        String tramaNumeroInicial = StringUtils.EMPTY, tramaNumeroFinal = StringUtils.EMPTY;

        if (StringUtils.isNotBlank(consultaRangoFormularioDTO.getNumeroInicial())) {
            tramaNumeroInicial = obtenerTramaNumeroFormulario(consultaRangoFormularioDTO.getNumeroInicial());
            if (StringUtils.isNotBlank(consultaRangoFormularioDTO.getNumeroFinal())) {
                tramaNumeroFinal = obtenerTramaNumeroFormulario(consultaRangoFormularioDTO.getNumeroFinal());
                // Validar que ambos filtros correspondan a numeraciones del mismo formato
                if (!tramaNumeroInicial.equals(tramaNumeroFinal)) {
                    throw new CirculemosNegocioException(AdministrarRango.ADM_030005);
                }
            }
        } else if (StringUtils.isNotBlank(consultaRangoFormularioDTO.getNumeroFinal())) {
            tramaNumeroFinal = obtenerTramaNumeroFormulario(consultaRangoFormularioDTO.getNumeroFinal());
        }

        StringBuilder sql = new StringBuilder();

        Map<String, Object> params = new HashMap<>(5);

        sql.append("SELECT r FROM RangoFormulario r JOIN r.numeracion n WHERE 1=1");

        if (StringUtils.isNotBlank(tramaNumeroInicial) || StringUtils.isNotBlank(tramaNumeroFinal)) {
            // Si viene al menos uno de los numeros hay q filtrar las numeraciones por esa trama
            sql.append(" AND n.trama = :trama");
        }
        if (StringUtils.isNotBlank(tramaNumeroInicial)) {
            params.put("trama", tramaNumeroInicial);
        } else if (StringUtils.isNotBlank(tramaNumeroFinal)) {
            params.put("trama", tramaNumeroFinal);
        }
        if (consultaRangoFormularioDTO.getIdTipoFormulario() != null) {
            sql.append(" AND r.tipoFormulario.id = :idTipoFormulario");
            sql.append(" AND r.tipoFormulario.estado = TRUE");
            params.put("idTipoFormulario", consultaRangoFormularioDTO.getIdTipoFormulario());
        }
        if (StringUtils.isNotBlank(consultaRangoFormularioDTO.getNumeroInicial())) {
            sql.append(" AND r.numeroFinal >= :numeroInicial");
            params.put("numeroInicial", consultaRangoFormularioDTO.getNumeroInicial());
        }
        if (StringUtils.isNotBlank(consultaRangoFormularioDTO.getNumeroFinal())) {
            sql.append(" AND r.numeroInicial <= :numeroFinal");
            params.put("numeroFinal", consultaRangoFormularioDTO.getNumeroFinal());
        }
        if (consultaRangoFormularioDTO.getFechaAutorizacionInicial() != null) {
            sql.append(" AND r.fechaAutorizacion >= :fechaAutInicial");
            params.put("fechaAutInicial", consultaRangoFormularioDTO.getFechaAutorizacionInicial());
        }
        if (consultaRangoFormularioDTO.getFechaAutorizacionFinal() != null) {
            sql.append(" AND r.fechaAutorizacion <= :fechaAutFinal");
            params.put("fechaAutFinal", consultaRangoFormularioDTO.getFechaAutorizacionFinal());
        }
        if (consultaRangoFormularioDTO.getCodigoOrganismo() != null) {
            sql.append(" AND r.codigoOrganismo.codigoOrganismo = :codigoOrganismo");
            params.put("codigoOrganismo", consultaRangoFormularioDTO.getCodigoOrganismo());
        }
        if (consultaRangoFormularioDTO.getIdRango() != null) {
            sql.append(" AND r.id = :id");
            params.put("id", consultaRangoFormularioDTO.getIdRango());
        }

        sql.append(" ORDER BY r.fechaAutorizacion, r.numeroInicial, r.numeroFinal");

        GenericDao<RangoFormulario> rangoFormularioDao = new GenericDao<>(RangoFormulario.class, em);
        List<RangoFormulario> resultadoConsulta = rangoFormularioDao.buildAndExecuteQuery(sql.toString(), params);
        List<RangoFormularioDTO> resultado = RangoFormularioHelper.toListLevel1DTO(resultadoConsulta);

        // Consulta el archivo en el repositorio
        for (RangoFormularioDTO rangoFormularioDTO : resultado) {
            if (rangoFormularioDTO.getDocumentoFormulario() != null) {
                try {
                    rangoFormularioDTO.setArchivoAutorizacion(documentosEjb.consultarDocumento(rangoFormularioDTO
                            .getDocumentoFormulario().getIdDocumento()));
                } catch (CirculemosAlertaException e) {
                    logger.info(e.getMessage());
                }
            }

        }
        logger.debugv(
                "FormularioEJB::consultarRangoFormulario: Se consultan {0} rangos de formularios con los parametros {1} ",
                resultado.size(), params);

        return resultado;
    }

    @Override
    public List<RangoFormularioDTO> consultarRangosFormularioDisponibles(RangoFormularioDTO rangoFormularioDTO)
            throws CirculemosNegocioException {
        ConsultaRangoFormularioDTO consultaRangoFormularioDTO = new ConsultaRangoFormularioDTO();

        consultaRangoFormularioDTO.setCodigoOrganismo(rangoFormularioDTO.getCodigoOrganismo().getCodigoOrganismo());
        consultaRangoFormularioDTO.setIdTipoFormulario(rangoFormularioDTO.getTipoFormulario().getId());
        consultaRangoFormularioDTO.setNumeroFinal(rangoFormularioDTO.getNumeroFinal());
        consultaRangoFormularioDTO.setNumeroInicial(rangoFormularioDTO.getNumeroInicial());

        List<RangoFormularioDTO> rangoFormularioDTOList = consultarRangoFormulario(consultaRangoFormularioDTO);

        // Remover rangos sin disponibilidad

        for (Iterator<RangoFormularioDTO> iterator = rangoFormularioDTOList.iterator(); iterator.hasNext();) {
            RangoFormularioDTO rangoFormularioDispDTO = (RangoFormularioDTO) iterator.next();
            if (rangoFormularioDispDTO.getCantidadDisponible() <= 0)
                iterator.remove();
        }

        return rangoFormularioDTOList;
    }

    @Override
    public void eliminarRangoFormulario(Long idRangoFormulario) throws CirculemosNegocioException {
        // Revisar que no tenga detalle
        if (!validarDependenciasRangoFormulario(idRangoFormulario)) {
            throw new CirculemosNegocioException(AdministrarRango.ADM_030006);
        }
        RangoFormulario rango = em.find(RangoFormulario.class, idRangoFormulario);
        logger.debugv("FormularioEJB::eliminarRangoFormulario: Se elimina el rango de ID= {1} ", idRangoFormulario);
        em.remove(rango);
    }

    @Override
    public void actualizarRangoFormulario(RangoFormularioDTO rangoFormularioDTO) throws CirculemosNegocioException,
            CirculemosAlertaException {

        // Identificar la numeracion a la cual pertenece el rango y asignarla al DTO
        RangoFormulario rango = em.find(RangoFormulario.class, rangoFormularioDTO.getId());

        // Revisar que no tenga detalle
        if (!validarDependenciasRangoFormulario(rangoFormularioDTO.getId())) {
            throw new CirculemosNegocioException(AdministrarRango.ADM_030006);
        }

        String tramaNumeroInicial = obtenerTramaNumeroFormulario(rangoFormularioDTO.getNumeroInicial());
        List<NumeracionFormulario> numeracionesRelacionadas = consultarNumeracionFormulario(rangoFormularioDTO
                .getTipoFormulario().getId(), rangoFormularioDTO.getFechaAutorizacion(), tramaNumeroInicial);
        if (numeracionesRelacionadas.isEmpty()) {
            // Error no hay numeraciones vigentes para la fecha de autorizacion ingresada
            throw new CirculemosNegocioException(AdministrarRango.ADM_030001);
        }
        // Hay una o varias numeraciones en esa vigencia, verificar a cual pertenece el rango enviado, si llega a pertenecer a varias, se toma la
        // numeracion con vigencia mas antigua(validado con analisis) y asignar el id de esta numeracion al DTO
        rangoFormularioDTO.setNumeracion(null);
        for (NumeracionFormulario numeracionFormulario : numeracionesRelacionadas) {
            String patronObtenido = obtenerExpresionRegularNumeracion(numeracionFormulario);
            if (rangoFormularioDTO.getNumeroInicial().matches(patronObtenido)
                    && rangoFormularioDTO.getNumeroFinal().matches(patronObtenido)) {
                // // El rango pertenece a esta numeracion pero debe validar que numero final sea mayor al inicial
                if (!esNumIniMenorIgualNumFin(rangoFormularioDTO.getNumeroInicial(),
                        rangoFormularioDTO.getNumeroFinal())) {
                    // Error: el numero final debe ser mayor al inicial
                    throw new CirculemosNegocioException(AdministrarRango.ADM_030002);
                }
                rangoFormularioDTO.setNumeracion(NumeracionFormularioHelper.toLevel0DTO(numeracionFormulario));
                break;
            }
        }
        if (rangoFormularioDTO.getNumeracion() == null || rangoFormularioDTO.getNumeracion().getId() == null) {
            // Error: el rango no pertenece a ninguna numeracion
            throw new CirculemosNegocioException(AdministrarRango.ADM_030003);
        }

        List<RangoFormulario> resultado = consultarRangosXNumeros(rangoFormularioDTO.getNumeracion().getId(),
                rangoFormularioDTO.getNumeroInicial(), rangoFormularioDTO.getNumeroFinal());
        // De los rangos existentes,eliminar el mismo rango que se esta editando
        int indexRemover = 0;
        boolean rangoContenido = false;
        for (RangoFormulario rangoFormulario : resultado) {
            if (rangoFormulario.getId().equals(rangoFormularioDTO.getId())) {
                rangoContenido = true;
                indexRemover = resultado.indexOf(rangoFormulario);
                break;
            }
        }
        if (rangoContenido) {
            resultado.remove(indexRemover);
        }
        if (!resultado.isEmpty()) {
            // Error, el rango ya esta ingresado
            throw new CirculemosNegocioException(AdministrarRango.ADM_030004);
        }

        // Consultar el detalle de la numeracion para poder calcular la cantidad de numeros en el rango
        rangoFormularioDTO.getNumeracion().setDetalleNumeracionList(
                administracionFormulariosEJB.consultarDetalleNumeracionFormulario(rangoFormularioDTO.getNumeracion()
                        .getId()));
        // Calcular la cantidad de formularios
        UtilFormularios util = new UtilFormularios();
        Integer cantidadFormularios = util.calcularCantidadFormularios(rangoFormularioDTO.getNumeroInicial(),
                rangoFormularioDTO.getNumeroFinal(), rangoFormularioDTO.getNumeracion());
        rangoFormularioDTO.setCantidadTotal(cantidadFormularios);
        rangoFormularioDTO.setCantidadDisponible(cantidadFormularios);

        try {
            if (rangoFormularioDTO.getArchivoAutorizacion() != null
                    && rangoFormularioDTO.getDocumentoFormulario() != null) {
                // Obtiene archivo para verificar que tenga la misma extension, si no tiene la misma extension crea un nuevo archivo
                ArchivoTransportableDTO archivo = documentosEjb.consultarDocumento(rangoFormularioDTO
                        .getDocumentoFormulario().getIdDocumento());
                if (archivo != null
                        && !FilenameUtils.getExtension(rangoFormularioDTO.getArchivoAutorizacion().getNombre()).equals(
                                FilenameUtils.getExtension(archivo.getNombre()))) {
                    // Crea documento en el repositorio si no lo guardo en el registro
                    String idDocumento = documentosEjb.registrarDocumento(
                            EnumCategoriaDocumento.AUTORIZACION_RANGO_FORMULARIO,
                            rangoFormularioDTO.getArchivoAutorizacion());

                    rangoFormularioDTO.getDocumentoFormulario().setIdDocumento(idDocumento);

                    DocumentoFormulario entidadDocumentoFormulario = DocumentoFormularioHelper.toLevel1Entity(
                            rangoFormularioDTO.getDocumentoFormulario(), new DocumentoFormulario());
                    em.merge(entidadDocumentoFormulario);
                } else {
                    // Actualiza documento en el repositorio
                    documentosEjb.actualizarDocumento(rangoFormularioDTO.getDocumentoFormulario().getIdDocumento(),
                            rangoFormularioDTO.getArchivoAutorizacion());
                }
            } else if (rangoFormularioDTO.getArchivoAutorizacion() != null) {
                // Crea documento en el repositorio si no lo guardo en el registro
                String idDocumento = documentosEjb.registrarDocumento(
                        EnumCategoriaDocumento.AUTORIZACION_RANGO_FORMULARIO,
                        rangoFormularioDTO.getArchivoAutorizacion());

                // Registrar documento
                DocumentoFormularioDTO documentoFormularioDTO = new DocumentoFormularioDTO();
                documentoFormularioDTO.setIdDocumento(idDocumento);
                documentoFormularioDTO = registrarDocumentoFormulario(documentoFormularioDTO);

                rangoFormularioDTO.setDocumentoFormulario(documentoFormularioDTO);
            }
        } finally {
            RangoFormulario entidadRango = RangoFormularioHelper.toLevel1Entity(rangoFormularioDTO, rango);
            logger.debugv("FormularioEJB::actualizarRangoFormulario: Se actualiza el rango de ID= {1} ",
                    entidadRango.getId());
            em.merge(entidadRango);
        }

    }

    /**
     * Se encarga de validar si el rango recibido como parámetro tiene dependencias en el sistema que no le permitan ser editado o eliminado
     * 
     * @param idRangoFormulario
     * @return true Si paso la validacion
     * @author julio.pinzon
     */
    private boolean validarDependenciasRangoFormulario(long idRangoFormulario) {
        List<DetalleCambioEstado> resultado = new ArrayList<DetalleCambioEstado>();
        TypedQuery<DetalleCambioEstado> q = em.createNamedQuery(DetalleCambioEstado.SQ_FIND_BY_RANGO,
                DetalleCambioEstado.class);
        q.setParameter("idRango", idRangoFormulario);
        resultado = q.getResultList();
        logger.debugv(
                "FormularioEJB::consultarDetalleRangoFormulario: Se consulta el detalle del rango con ID= {1}, se encontraron {1} formularios en el rango ",
                idRangoFormulario, resultado.size());
        return resultado.isEmpty();

    }

    @Override
    public RangoFormularioDTO consultarDetalleRangoFormulario(Long idRangoFormulario) {
        List<Formulario> resultado = new ArrayList<Formulario>();
        TypedQuery<Formulario> q = em.createNamedQuery(Formulario.SQ_FIND_BY_RANGO, Formulario.class);
        q.setParameter("idRango", idRangoFormulario);
        resultado = q.getResultList();
        RangoFormularioDTO rangoDTO = RangoFormularioHelper.toLevel1DTO(em.find(RangoFormulario.class,
                idRangoFormulario));
        rangoDTO.setFormularioList(FormularioHelper.toListLevel1DTO(resultado));
        logger.debugv(
                "FormularioEJB::consultarDetalleRangoFormulario: Se consulta el detalle del rango con ID= {1}, se encontraron {1} formularios en el rango ",
                idRangoFormulario, resultado.size());
        return rangoDTO;
    }

    @Override
    public List<String> listarNumerosRango(String numeroInicial, String numeroFinal,
            NumeracionFormularioDTO numeracionDTO) {
        UtilFormularios util = new UtilFormularios();
        return util.listarNumerosDeRango(numeroInicial, numeroFinal, numeracionDTO, false, null);
    }

    /**
     * Genera los posibles numeros de formulario comprendidos entre un numero inicial y final para un tipo de formulario determinado, validando que
     * los numero inicial y final se encuentren registrados dentro de un rango de la misma numeracion
     * 
     * @param numeroInicial
     *            Numero desde el cual se van a generar los numeros
     * @param numeroFinal
     *            Numero hasta el cual se van a generar los numeros, debe ser mayor al inicial
     * @param idTipoFormulario
     *            Id del tipo de formulario de los numeros
     * @return Lista con los numeros generados
     * 
     * @throws CirculemosNegocioException
     *             NUM_001004: El número inicial ingresado no esta asignado al organismo de tránsito<br>
     *             NUM_001005: El número final ingresado no esta asignado al organismo de tránsito<br>
     *             NUM_001006: Los números inicial y final deben tener el mismo formato de numeración<br>
     *             NUM_001007: El número final debe ser mayor o igual al número inicial
     */
    private List<String> listarNumerosRango(String numeroInicial, String numeroFinal, Integer idTipoFormulario)
            throws CirculemosNegocioException {

        RangoFormulario rangoNumInicial = consultarRangoNumeroFormulario(numeroInicial, idTipoFormulario, null);
        if (rangoNumInicial != null) {
            RangoFormulario rangoNumFinal = consultarRangoNumeroFormulario(numeroFinal, idTipoFormulario, null);
            if (rangoNumFinal != null) {
                if (rangoNumInicial.getNumeracion().getId().equals(rangoNumFinal.getNumeracion().getId())) {
                    if (esNumIniMenorIgualNumFin(numeroInicial, numeroFinal)) {
                        // OK puede realizar el calculo de los numeros correctamente
                        List<DetalleNumeracionDTO> detalleNumeracion = administracionFormulariosEJB
                                .consultarDetalleNumeracionFormulario(rangoNumFinal.getNumeracion().getId());
                        UtilFormularios util = new UtilFormularios();
                        NumeracionFormularioDTO numeracionDTO = NumeracionFormularioHelper.toLevel0DTO(rangoNumFinal
                                .getNumeracion());
                        numeracionDTO.setDetalleNumeracionList(detalleNumeracion);
                        return util.listarNumerosDeRango(numeroInicial, numeroFinal, numeracionDTO, false, null);
                    } else {
                        // lanzar error el numero final debe ser mayor al inicial
                        throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001004);
                    }
                } else {
                    // Lanzar error, los numeros no pertenecen a la misma numeracion
                    throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001003);
                }
            } else {
                // lanzar error el numero final ingresado no esta asignado al organismo de transito
                throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001002);
            }
        } else {
            // lanzar error el numero inicial ingresado no esta asignado al organismo de transito
            throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001001);
        }
    }

    // Commented unused Sergio torres 17-Dic-2015
    // @Override
    // public List<String> listarNumerosFormularioAsignacion(String numeroInicial, Integer codigoOrganismo,
    // Integer idTipoFormulario, Integer idTipoAsignacion) throws CirculemosNegocioException {
    //
    // RangoFormulario rango = consultarRangoNumeroFormulario(numeroInicial, idTipoFormulario, null);
    // List<String> numeros = null;
    // if (rango != null) {
    //
    // List<DetalleNumeracionDTO> detalle = administracionFormulariosEJB
    // .consultarDetalleNumeracionFormulario(rango.getNumeracion().getId());
    // UtilFormularios util = new UtilFormularios();
    // NumeracionFormularioDTO numeracionDTO = NumeracionFormularioHelper.toLevel0DTO(rango.getNumeracion());
    // numeracionDTO.setDetalleNumeracionList(detalle);
    //
    // /*
    // * List<StockTipoAsignacionDTO> stockTipoAsignacion = consultarStockTipoAsignacion(idTipoFormulario, idTipoAsignacion, true); if
    // * (!stockTipoAsignacion.isEmpty()) {
    // */
    // // OJO REVISAR TODO: LA CANTIDAD MAXIMA SE DEBE SACAR ES DE LA CONFIGURACIÓN:
    // // "Cantidad de formularios máximos a Asignar por tipo de formulario"
    // Integer cantidadFormularios = 50;
    // numeros = util.generarNumeros(numeroInicial, numeracionDTO, cantidadFormularios);
    // /*
    // * } else { // lanzar error no se puede calcular el numero final porque no esta configurado el stock por tipo de asignacion throw new
    // * CirculemosNegocioException(CambioEstadosFormularios.NUM_001003); }
    // */
    // } else {
    // // lanzar error el numero inicial ingresado no esta asignado al organismo de transito
    // throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001004);
    // }
    //
    // return numeros;
    //
    // }

    @Override
    public List<StockTipoResponsableDTO> consultarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s FROM StockTipoResponsable s WHERE 1 = 1");
        Map<String, Object> filtros = new HashMap<String, Object>();

        if (stockTipoResponsableDTO.getId() != null) {
            sql.append(" AND s.id = :id");
            filtros.put("id", stockTipoResponsableDTO.getId());
        }
        if (stockTipoResponsableDTO.getEstadoStock() != null) {
            sql.append(" AND s.estadoStock = :estadoStock");
            filtros.put("estadoStock", stockTipoResponsableDTO.getEstadoStock());
        }
        if (stockTipoResponsableDTO.getCodigoOrganismo() != null
                && stockTipoResponsableDTO.getCodigoOrganismo().getCodigoOrganismo() != null) {
            sql.append(" AND s.codigoOrganismo.codigoOrganismo = :codigoOrganismo");
            filtros.put("codigoOrganismo", stockTipoResponsableDTO.getCodigoOrganismo().getCodigoOrganismo());
        }
        if (stockTipoResponsableDTO.getTipoResponsable() != null
                && stockTipoResponsableDTO.getTipoResponsable().getId() != null) {
            sql.append(" AND s.tipoResponsable.id = :idTipoResponsable");
            filtros.put("idTipoResponsable", stockTipoResponsableDTO.getTipoResponsable().getId());
        }
        if (stockTipoResponsableDTO.getTipoFormulario() != null
                && stockTipoResponsableDTO.getTipoFormulario().getId() != null) {
            sql.append(" AND s.tipoFormulario.id = :idTipoFormulario");
            filtros.put("idTipoFormulario", stockTipoResponsableDTO.getTipoFormulario().getId());
        }
        if (stockTipoResponsableDTO.getTipoFormulario() != null
                && stockTipoResponsableDTO.getTipoFormulario().getActivo() != null) {
            sql.append(" AND s.tipoFormulario.estado = :activo");
            filtros.put("activo", stockTipoResponsableDTO.getTipoFormulario().getActivo());
        }

        GenericDao<StockTipoResponsable> stockTipoAsignacionDao = new GenericDao<>(StockTipoResponsable.class, em);
        List<StockTipoResponsable> resultadoConsulta = stockTipoAsignacionDao.buildAndExecuteQuery(sql.toString(),
                filtros);
        List<StockTipoResponsableDTO> resultado = StockTipoResponsableHelper.toListLevel1DTO(resultadoConsulta);

        return resultado;
    }

    @Override
    public int consultarCantidadFormularios(FormularioDTO formularioDTO) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT f FROM Formulario f").append(" ").append("WHERE 1 = 1");

        if (formularioDTO.getId() != null) {
            sql.append(" AND f.id = :id");
            parametros.put("id", formularioDTO.getId());
        }
        if (formularioDTO.getCodigoOrganismo() != null
                && formularioDTO.getCodigoOrganismo().getCodigoOrganismo() != null) {
            sql.append(" AND f.codigoOrganismo.codigoOrganismo = :codigoOrganismo");
            parametros.put("codigoOrganismo", formularioDTO.getCodigoOrganismo().getCodigoOrganismo());
        }
        if (formularioDTO.getRangoFormulario() != null && formularioDTO.getRangoFormulario().getId() != null) {
            sql.append(" AND f.rangoFormulario.id = :idRangoFormulario");
            parametros.put("idRangoFormulario", formularioDTO.getRangoFormulario().getId());
        }
        if (formularioDTO.getTipoFormulario() != null && formularioDTO.getTipoFormulario().getId() != null) {
            sql.append(" AND f.tipoFormulario.id = :idTipoFormulario");
            parametros.put("idTipoFormulario", formularioDTO.getTipoFormulario().getId());
        }
        if (formularioDTO.getEstadoFormulario() != null && formularioDTO.getEstadoFormulario().getId() != null) {
            sql.append(" AND f.estadoFormulario.id = :idEstadoFormulario");
            parametros.put("idEstadoFormulario", formularioDTO.getEstadoFormulario().getId());
        }
        if (formularioDTO.getResponsableFormulario() != null) {
            if (formularioDTO.getResponsableFormulario().getId() != null) {
                sql.append(" AND f.responsableFormulario.id = :idResponsable");
                parametros.put("idResponsable", formularioDTO.getResponsableFormulario().getId());
            }
            if (formularioDTO.getResponsableFormulario().getTipoResponsable() != null
                    && formularioDTO.getResponsableFormulario().getTipoResponsable().getId() != null) {
                sql.append(" AND f.responsableFormulario.tipoResponsable.id = :idTipoResponsable");
                parametros.put("idTipoResponsable", formularioDTO.getResponsableFormulario().getTipoResponsable()
                        .getId());
            }
        }
        if (formularioDTO.getDetalleCambioEstado() != null && formularioDTO.getDetalleCambioEstado().getId() != null) {
            sql.append(" AND f.detalleCambioEstado.id = :idDetalleCambioEstado");
            parametros.put("idDetalleCambioEstado", formularioDTO.getDetalleCambioEstado().getId());
        }

        GenericDao<Formulario> genericDao = new GenericDao<>(Formulario.class, em);
        return genericDao.buildAndExecuteQuery(sql, parametros).size();
    }

    @Override
    public int asignarFormularios(AsignacionDTO asignacionDTO) throws CirculemosAlertaException {
        GenericDao<Formulario> formularioDao = new GenericDao<>(Formulario.class, em);
        GenericDao<NumeracionFormulario> numeracionDao = new GenericDao<>(NumeracionFormulario.class, em);

        Integer codigoOrganismo = 0;
        Integer idTipoFormulario = 0;
        final EstadoFormulario estadoAsignado = em.getReference(EstadoFormulario.class,
                EnumEstadoFomulario.ASIGNADO.getValue());

        // Documento soporte
        DocumentoFormularioDTO documentoFormularioDTO = null;
        if (asignacionDTO.getArchivoSoporte() != null) {
            // Crea documento en el repositorio
            String idDocumento = documentosEjb.registrarDocumento(
                    EnumCategoriaDocumento.SOPORTE_CAMBIO_ESTADO_FORMULARIOS, asignacionDTO.getArchivoSoporte());
            documentoFormularioDTO = new DocumentoFormularioDTO();
            documentoFormularioDTO.setIdDocumento(idDocumento);
            if (asignacionDTO.getDetalleCambioEstado().getDocumentoFormulario() != null)
                documentoFormularioDTO.setNumeroDocumento(asignacionDTO.getDetalleCambioEstado()
                        .getDocumentoFormulario().getNumeroDocumento());
            documentoFormularioDTO = registrarDocumentoFormulario(documentoFormularioDTO);
        }
        asignacionDTO.getDetalleCambioEstado().setDocumentoFormulario(documentoFormularioDTO);

        // Consultar rangos
        Map<Long, Integer> cantidadRangoMap = new HashMap<>();
        List<Long> idRangoList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r FROM RangoFormulario r WHERE r.id IN (:idRangoList)");

        for (CantidadRangoDTO ctdRangoDTO : asignacionDTO.getCantidadRango()) {
            idRangoList.add(ctdRangoDTO.getIdRango());
            cantidadRangoMap.put(ctdRangoDTO.getIdRango(), ctdRangoDTO.getCantidad());
        }

        GenericDao<RangoFormulario> rangoDao = new GenericDao<>(RangoFormulario.class, em);
        List<RangoFormulario> rangoList = rangoDao.buildAndExecuteQuery(sql,
                GenericDao.buildMap("idRangoList", idRangoList));

        // Recorrer rangos
        int cantidadAsignadaTotal = 0;
        List<String> intervalosList = new ArrayList<>();

        for (RangoFormulario rango : rangoList) {
            codigoOrganismo = rango.getCodigoOrganismo().getCodigoOrganismo();
            idTipoFormulario = rango.getTipoFormulario().getId();

            // Cantidades mayores a cero
            if (cantidadRangoMap.get(rango.getId()) == null || cantidadRangoMap.get(rango.getId()) != null
                    && cantidadRangoMap.get(rango.getId()).intValue() <= 0)
                continue;

            // 1 - RECONSTRUIR NUMERACION
            sql = new StringBuilder();
            sql.append("SELECT n FROM NumeracionFormulario n LEFT JOIN FETCH n.detalleNumeracionList d WHERE n.id = :idNumeracion");
            List<NumeracionFormulario> numeracionList = numeracionDao.buildAndExecuteQuery(sql,
                    GenericDao.buildMap("idNumeracion", rango.getNumeracion().getId()));

            NumeracionFormularioDTO numeracionDTO = NumeracionFormularioHelper.toLevel1DTO(numeracionList.get(0));
            List<DetalleNumeracionDTO> detalleNumeracionDTOList = DetalleNumeracionHelper
                    .toListLevel1DTO(numeracionList.get(0).getDetalleNumeracionList());
            numeracionDTO.setDetalleNumeracionList(detalleNumeracionDTOList);
            asignacionDTO.getDetalleCambioEstado().setTrama(numeracionDTO.getTrama());

            List<String> numerosFormularios = listarNumerosRango(rango.getNumeroInicial(), rango.getNumeroFinal(),
                    numeracionDTO);

            // 2 - CONSULTAR TODOS LOS FORMULARIOS
            sql = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            Map<String, Formulario> formulariosMap = new HashMap<>();

            sql.append("SELECT f FROM Formulario f WHERE f.rangoFormulario.id = :idRangoFormulario")
                    .append(" AND f.tipoFormulario.id = :idTipoFormulario")
                    .append(" AND f.codigoOrganismo.codigoOrganismo = :codigoOrganismo")
                    .append(" AND f.numeroFormulario BETWEEN :numeroInicial AND :numeroFinal")
                    .append(" ORDER BY f.numeroFormulario");

            params.put("idRangoFormulario", rango.getId());
            params.put("idTipoFormulario", idTipoFormulario);
            params.put("codigoOrganismo", codigoOrganismo);
            params.put("numeroInicial", rango.getNumeroInicial());
            params.put("numeroFinal", rango.getNumeroFinal());

            // Si no existen formularios los debe crear todos
            List<Formulario> formularioList = formularioDao.buildAndExecuteQuery(sql, params);

            // Crear mapa de formularios
            for (Formulario formulario : formularioList)
                formulariosMap.put(formulario.getNumeroFormulario(), formulario);

            // 3 - RECORRER NUMERACION
            String numeroFinal = StringUtils.EMPTY;
            List<DetalleCambioEstado> detalleCambioEstadoList = new ArrayList<>();
            DetalleCambioEstado detalleCambioEstado = iniciarDetalleCambioEstado(asignacionDTO.getDetalleCambioEstado());

            for (String numeroFormulario : numerosFormularios) {
                // Numero inicial
                if (detalleCambioEstado.getFormularioList().isEmpty())
                    detalleCambioEstado.setNumeroInicial(numeroFormulario);
                else
                // Validar cantidad maxima
                if (detalleCambioEstado.getFormularioList().size() == cantidadRangoMap.get(rango.getId()).intValue())
                    break;

                // Numero final
                numeroFinal = numeroFormulario;

                // No existe formulario: asignar, existe: validar devuelto
                if (formulariosMap.containsKey(numeroFormulario)) {
                    Formulario formulario = formulariosMap.get(numeroFormulario);
                    Integer idEstadoFormulario = formulario.getEstadoFormulario().getId();

                    // Devuelto: asignar, no devuelto: finalizar detalle
                    if (EnumEstadoFomulario.DEVUELTO.getValue().equals(idEstadoFormulario)) {
                        formulario.setEstadoFormulario(estadoAsignado);
                        // Asignar
                        asignarDetalleCambioEstado(detalleCambioEstado, formulario);
                    } else {
                        finalizarDetalleCambioEstado(rango, numeroFinal, detalleCambioEstadoList, detalleCambioEstado,
                                intervalosList);
                        // Iniciar nuevo detalle
                        detalleCambioEstado = iniciarDetalleCambioEstado(asignacionDTO.getDetalleCambioEstado());
                    }
                } else {
                    Formulario formulario = new Formulario();
                    formulario.setCodigoOrganismo(rango.getCodigoOrganismo());
                    formulario.setEstadoFormulario(estadoAsignado);
                    formulario.setNumeroFormulario(numeroFormulario);
                    formulario.setRangoFormulario(rango);
                    formulario.setTipoFormulario(rango.getTipoFormulario());
                    // Asignar
                    asignarDetalleCambioEstado(detalleCambioEstado, formulario);
                }
            }

            finalizarDetalleCambioEstado(rango, numeroFinal, detalleCambioEstadoList, detalleCambioEstado,
                    intervalosList);

            // REGISTRAR ASIGNACIONES DEL RANGO
            cantidadAsignadaTotal += registrarDetalleCambioEstado(detalleCambioEstadoList);

            detalleCambioEstado = null;
            detalleCambioEstadoList = null;
        }

        if (cantidadAsignadaTotal > 0) {
            // ENVIAR CORREO DE NOTIFICACION AL RESPONSABLE
            crearCorreoNotificacionCambioEstado(EnumEstadoFomulario.ASIGNADO, codigoOrganismo, idTipoFormulario,
                    asignacionDTO.getDetalleCambioEstado(), intervalosList);
        }

        return cantidadAsignadaTotal;
    }

    /**
     * Asignacion: iniciar nuevo detalle con formularios consecutivos
     * 
     * @param detalleCambioEstadoDTO
     *            Datos generales de la asignacion
     * @return Un {@link DetalleCambioEstado} con listas vacias de {@link Formulario} y {@link SeguimientoFormulario}
     * @author rodrigo.cruz
     */
    private DetalleCambioEstado iniciarDetalleCambioEstado(DetalleCambioEstadoDTO detalleCambioEstadoDTO) {
        DetalleCambioEstado detalleCambioEstado = DetalleCambioEstadoHelper
                .toLevel1Entity(detalleCambioEstadoDTO, null);
        detalleCambioEstado.setFormularioList(new ArrayList<Formulario>());
        detalleCambioEstado.setSeguimientoFormularioList(new ArrayList<SeguimientoFormulario>());
        return detalleCambioEstado;
    }

    /**
     * Asignacion: asignar formulario y seguimiento de formulario a detalle
     * 
     * @param detalleCambioEstado
     *            Detalle inicializado con listas de formulario y seguimiento de formulario
     * @param formulario
     *            El {@link Formulario} que se asigna al detalle
     * @author rodrigo.cruz
     */
    private void asignarDetalleCambioEstado(DetalleCambioEstado detalleCambioEstado, Formulario formulario) {
        formulario.setDetalleCambioEstado(detalleCambioEstado);
        formulario.setResponsableFormulario(detalleCambioEstado.getResponsableFormulario());

        SeguimientoFormulario seguimientoFormulario = new SeguimientoFormulario();
        seguimientoFormulario.setDetalleCambioEstado(detalleCambioEstado);
        seguimientoFormulario.setEstadoFormulario(detalleCambioEstado.getEstadoFormulario());
        seguimientoFormulario.setFechaAplicacionEstado(detalleCambioEstado.getFechaAplicacionEstado());
        seguimientoFormulario.setFormulario(formulario);
        seguimientoFormulario.setResponsableFormulario(detalleCambioEstado.getResponsableFormulario());
        seguimientoFormulario.setUsuarioRegistro(usuarioSesionEJB.getUsuario());

        detalleCambioEstado.getFormularioList().add(formulario);
        detalleCambioEstado.getSeguimientoFormularioList().add(seguimientoFormulario);
    }

    /**
     * Asignacion: finalizar detalle con nuevos formularios consecutivos (intervalo)
     * 
     * @param rango
     *            El {@link Rango} que se asocia al {@link DetalleCambioEstado}
     * @param numeroFinal
     *            El numero final del intervalo de formularios
     * @param detalleCambioEstadoList
     *            La referencia a la lista de detalles que se actualiza en {@link #asignarFormularios(AsignacionDTO)}
     * @param detalleCambioEstado
     *            El detalle que se finaliza
     * @param intervalos
     *            Referencia de lista de numeros de formularios que se enviara por correo
     * @author rodrigo.cruz
     */
    private void finalizarDetalleCambioEstado(RangoFormulario rango, String numeroFinal,
            List<DetalleCambioEstado> detalleCambioEstadoList, DetalleCambioEstado detalleCambioEstado,
            List<String> intervalos) {
        // Validar detalle finalizado
        // Casos: todos forms nuevos, ultimo detalle todos forms nuevos
        if (!detalleCambioEstado.getFormularioList().isEmpty() && detalleCambioEstado.getNumeroFinal() == null) {
            // Finalizar detalle
            detalleCambioEstado.setCantidadFormularios(detalleCambioEstado.getFormularioList().size());
            detalleCambioEstado.setRangoFormulario(rango);
            detalleCambioEstado.setNumeroFinal(numeroFinal);
            detalleCambioEstado.setEsEvento(true);
            detalleCambioEstadoList.add(detalleCambioEstado);

            if (intervalos != null)
                intervalos.add("<tr><td>" + detalleCambioEstado.getNumeroInicial() + "</td><td>"
                        + detalleCambioEstado.getNumeroFinal() + "</td></tr>");
        }
    }

    /**
     * Asignacion: registrar detalle cambio estado con formularios respectivos y seguimiento de formulario por cada formulario
     * 
     * @param detalleCambioEstadoList
     *            La lista de {@link DetalleCambioEstado} con los intervalos a registrar
     * @return La cantidad de formularios asignados
     * @author rodrigo.cruz
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private int registrarDetalleCambioEstado(List<DetalleCambioEstado> detalleCambioEstadoList) {
        int cantidadAsignada = 0;

        if (detalleCambioEstadoList != null && !detalleCambioEstadoList.isEmpty()) {
            GenericDao<RangoFormulario> rangoDao = new GenericDao<>(RangoFormulario.class, em);
            GenericDao<SeguimientoFormulario> seguimientoDao = new GenericDao<>(SeguimientoFormulario.class, em);
            GenericDao<DetalleCambioEstado> detalleCambioEstadoDao = new GenericDao<>(DetalleCambioEstado.class, em);

            RangoFormulario rangoFormulario = detalleCambioEstadoList.get(0).getRangoFormulario();
            Date ahora = UtilFecha.buildCalendar(null).getTime();

            for (DetalleCambioEstado detalleCambioEstado : detalleCambioEstadoList) {
                detalleCambioEstado.setFechaMovimiento(ahora);
                detalleCambioEstadoDao.saveOrUpdate(detalleCambioEstado);

                cantidadAsignada += detalleCambioEstado.getFormularioList().size();

                for (SeguimientoFormulario seguimientoFormulario : detalleCambioEstado.getSeguimientoFormularioList()) {
                    seguimientoFormulario.setFechaMovimiento(ahora);
                    seguimientoDao.saveOrUpdate(seguimientoFormulario);
                }
            }

            // Actualizar cantidad disponible en rango
            rangoFormulario.setCantidadDisponible(rangoFormulario.getCantidadDisponible() - cantidadAsignada);
            rangoDao.saveOrUpdate(rangoFormulario);
        }

        return cantidadAsignada;
    }

    // Commented unused Sergio Torres 17/dic/2015
    // /**
    // * 2. Validar que todos los formularios esten registrados en el sistema mediante algun rango de registro: <br>
    // * Se deben validar que todos los formularios esten registrados en algun rango, para ello: consultar el rango del numero inicial y del numero
    // * final, Si el numero inicial o el final no esta dentro de algun rango entonces ya se tiene que hay formularios no registrados Si el rango de
    // * registro del numero inicial y final es uno solo, OK los formularios estan registrados Si hay varios rangos de registro q involucran al numero
    // * inicial y final, es probable que todos los formularios esten registrados. Consultar todos los posibles rangos que incluyan el rango en
    // cuestion
    // * y evaluar la cantidad de formularios contra la suma de las cantidades en los rangos registrados, si la cantidad en los rangos registrados
    // * (desde numero inicial hasta final) es menor a la realmente existente entre numero inicial y final, quiere decir que falta formularios por
    // * registrar <br>
    // *
    // * 3. Validar que exista una configuracion de estados valida que permita pasar del estado actual del formulario a asignado y que el formulario
    // no
    // * tenga un responsable asignado: <br>
    // * Para lo anterior hay que consultar cada uno de los formulario, si no existe es porque esta "Pendiente por asignar" si existe validar que del
    // * estado actual por alguna configuracion de estados le permita pasar al estado deseado y que no tenga ya asignado algun responsable, el cual
    // esta
    // * en el campo estado del DTO del formulario <br>
    // *
    // * 5. Insertar/Actualizar cada formulario con su respectivo seguimiento (Si es una asignacion debe insertar los formularios): <br>
    // * Cada seguimiento de formulario va asociado al detalle cambio estado persistido, para todos los formularios se tiene ese mismo detalle cambio
    // * estado,el detalle me da el rango como tal con el q se hizo el cambio de estado Hay que guardar la relacion del detalle cambio estado con el
    // * rango de formulario con el que se hizo el registro de los numeros de formulario
    // *
    // * @param formularios
    // * @param seguimientoFormulario
    // * @throws CirculemosNegocioException
    // */
    // @Deprecated
    // private void cambiarEstadoFormularioCompleto(List<Formulario> formularios,
    // SeguimientoFormulario seguimientoFormulario) throws CirculemosNegocioException {
    // // Tomar el tipo de formulario del primer formulario
    // TipoFormulario tipoFormulario = formularios.get(0).getTipoFormulario();
    // DetalleCambioEstado detalleCambioEstado = seguimientoFormulario.getDetalleCambioEstado();
    // ResponsableFormulario responsableFormulario = detalleCambioEstado.getResponsableFormulario();
    // EstadoFormulario estadoFormulario = detalleCambioEstado.getEstadoFormulario();
    //
    // // 1. Validar que la cantidad de formularios a asignar no superen el maximo de asignacion para el responsable
    // // teniendo en cuenta los formularios que dicho responsable ya tenga asignados a la fecha
    // Long formularioAsignados = contarFormulariosAsignados(responsableFormulario.getId(), tipoFormulario.getId());
    //
    // StockTipoResponsableDTO consultaStockDTO = new StockTipoResponsableDTO();
    // consultaStockDTO.setCodigoOrganismo(new OrganismoTransitoDTO(formularios.get(0).getCodigoOrganismo()
    // .getCodigoOrganismo()));
    // consultaStockDTO.setEstadoStock(true);
    // consultaStockDTO.setTipoFormulario(new TipoFormularioDTO(tipoFormulario.getId()));
    // consultaStockDTO.setTipoResponsable(new TipoResponsableFormularioDTO(responsableFormulario.getTipoResponsable()
    // .getId()));
    // List<StockTipoResponsableDTO> configuracionStock = consultarStockTipoResponsable(consultaStockDTO);
    // StockTipoResponsableDTO stock = configuracionStock.get(0);
    // if ((formularioAsignados + formularios.size()) > stock.getStockMaximo()) {
    // // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001009);
    // }
    //
    // // 2. Validar que todos los formularios esten registrados en el sistema mediante algun rango de registro
    // String trama = obtenerTramaNumeroFormulario(detalleCambioEstado.getNumeroInicial());
    //
    // RangoFormulario rangoNumInicial = consultarRangoNumeroFormulario(detalleCambioEstado.getNumeroInicial(),
    // tipoFormulario.getId(), trama);
    // if (rangoNumInicial == null) {
    // // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001001);
    // }
    //
    // RangoFormulario rangoNumFinal = consultarRangoNumeroFormulario(detalleCambioEstado.getNumeroFinal(),
    // tipoFormulario.getId(), trama);
    // if (rangoNumFinal == null) {
    // // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001001);
    // }
    //
    // RangoFormulario rangoRegistro = null;
    // List<RangoFormulario> rangosRegistro = null;
    // if (rangoNumInicial.getId() != rangoNumFinal.getId()) {
    // // Hay varios rangos de registro (por lo minimo dos)
    // rangosRegistro = consultarRangosRelacionadosNumeros(detalleCambioEstado.getNumeroInicial(),
    // detalleCambioEstado.getNumeroFinal(), tipoFormulario.getId(), trama);
    // rangoNumInicial = rangosRegistro.get(0);
    // rangoNumFinal = rangosRegistro.get(rangosRegistro.size() - 1);
    //
    // NumeracionFormularioDTO numeracionFormularioInicio = NumeracionFormularioHelper.toLevel0DTO(rangoNumInicial
    // .getNumeracion());
    // numeracionFormularioInicio.setDetalleNumeracionList(administracionFormulariosEJB
    // .consultarDetalleNumeracionFormulario(rangoNumInicial.getNumeracion().getId()));
    //
    // NumeracionFormularioDTO numeracionFormularioFinal = NumeracionFormularioHelper.toLevel0DTO(rangoNumFinal
    // .getNumeracion());
    // numeracionFormularioFinal.setDetalleNumeracionList(administracionFormulariosEJB
    // .consultarDetalleNumeracionFormulario(rangoNumFinal.getNumeracion().getId()));
    //
    // UtilFormularios utilFormularios = new UtilFormularios();
    // Integer cantidadRegistradosPrimerRango = utilFormularios.calcularCantidadFormularios(
    // detalleCambioEstado.getNumeroInicial(), rangoNumInicial.getNumeroFinal(),
    // numeracionFormularioInicio);
    // Integer cantidadRegistradosUltimoRango = utilFormularios.calcularCantidadFormularios(
    // rangoNumFinal.getNumeroInicial(), detalleCambioEstado.getNumeroFinal(), numeracionFormularioFinal);
    // Integer cantidadTotalFormRegistrados = cantidadRegistradosPrimerRango + cantidadRegistradosUltimoRango;
    //
    // if (rangosRegistro.size() > 2) {
    // // Aparte del rango de registro del numero inicial y del rango del numero final
    // // hay otros rangos intermedios involucrados y se debe validar la cantidad registrada en los mismo
    // for (int i = 1; i < rangosRegistro.size() - 1; i++) {
    // cantidadTotalFormRegistrados += rangosRegistro.get(i).getCantidadTotal();
    // }
    // }
    //
    // if (cantidadTotalFormRegistrados < detalleCambioEstado.getCantidadFormularios()) {
    // // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001001);
    // }
    // } else {
    // rangoRegistro = rangoNumInicial;
    // }
    //
    // // 3. Validar que exista una configuracion de estados valida que permita pasar del estado actual del formulario a asignado
    // // y que el formulario no tenga un responsable asignado
    // List<Formulario> formulariosEntidades = new ArrayList<Formulario>();
    // Map<Integer, List<Integer>> confEstados = obtenerConfiguracionEstadosTipoForm(tipoFormulario.getId());
    // Integer idEstadoActual;
    // TypedQuery<Formulario> query = em.createNamedQuery(Formulario.SQ_FORM_BY_NUMERO_TIPO_FORM, Formulario.class);
    // query.setParameter("idTipoFormulario", tipoFormulario.getId());
    //
    // for (Formulario formulario : formularios) {
    // query.setParameter("numeroFormulario", formulario.getNumeroFormulario());
    // Formulario formularioEntidad = null;
    // try {
    // formularioEntidad = query.getSingleResult();
    // idEstadoActual = formularioEntidad.getEstadoFormulario().getId();
    // if (formularioEntidad.getResponsableFormulario() != null) {
    // // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001008);
    // }
    // } catch (NoResultException e) {
    // idEstadoActual = EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR.getValue();
    // }
    //
    // boolean existeConfiguracion = validarConfiguracionEstados(confEstados, idEstadoActual,
    // EnumEstadoFomulario.ASIGNADO.getValue());
    // if (!existeConfiguracion) {
    // // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001002);
    // }
    //
    // if (formularioEntidad == null) {
    // formularioEntidad = new Formulario();
    // formularioEntidad.setNumeroFormulario(formulario.getNumeroFormulario());
    // formularioEntidad.setTipoFormulario(new TipoFormulario(tipoFormulario.getId()));
    // }
    // formulariosEntidades.add(formularioEntidad);
    // }
    //
    // // 4. Insertar el detalle cambio estado
    // Date fechaActual = UtilFecha.buildCalendar(null).getTime();
    // detalleCambioEstado.setFechaMovimiento(fechaActual);
    // detalleCambioEstado.setTrama(trama);
    // em.persist(detalleCambioEstado);
    //
    // GenericDao<Formulario> formularioDao = new GenericDao<Formulario>(Formulario.class, em);
    //
    // // 5. Insertar/actualizar cada formulario con su respectivo seguimiento
    // for (Formulario formulario : formulariosEntidades) {
    // formulario.setEstadoFormulario(estadoFormulario);
    // if (rangoRegistro != null)
    // // Todos los formularios se registraron con el mismo rango
    // formulario.setRangoFormulario(rangoRegistro);
    // else {
    // // Hay varios rangos de registro, se debe identificar en que rango esta cada formulario
    // formulario.setRangoFormulario(obtenerRango(rangosRegistro, formulario.getNumeroFormulario()));
    // }
    // formulario.setResponsableFormulario(responsableFormulario);
    // formularioDao.saveOrUpdate(formulario);
    //
    // seguimientoFormulario.setEstadoFormulario(estadoFormulario);
    // seguimientoFormulario.setFechaMovimiento(fechaActual);
    // seguimientoFormulario.setFormulario(formulario);
    // em.persist(seguimientoFormulario);
    //
    // // FIXME
    // detalleCambioEstado.setRangoFormulario(rangoRegistro);
    // em.merge(detalleCambioEstado);
    // }
    //
    // // 6. Guardar la relacion del detalle cambio estado con los rangos de registro involucrados en el rango de asignacion
    // if (rangoRegistro != null) {
    // rangosRegistro = new ArrayList<RangoFormulario>();
    // rangosRegistro.add(rangoRegistro);
    // }
    //
    // // 7. Notificar al responsables por correo
    // // Envio del correo de notificacion de asignacion
    // // ctx.getBusinessObject(FormularioEJB.class).enviarCorreoNotificacionCambioEstado(
    // // DetalleCambioEstadoHelper.toLevel1DTO(detalleCambioEstado), tipoFormulario.getNombre(),
    // // responsableFormulario.getCorreoResponsableFormulario(), EnumEstadoFomulario.ASIGNADO, null);
    // }

    @Override
    public int calcularFormularios(ConsultaRangoTipoFormularioDTO consultaRangoTipoFormularioDTO)
            throws CirculemosNegocioException {

        DetalleCambioEstado detalleCambioEstado = em.find(DetalleCambioEstado.class,
                consultaRangoTipoFormularioDTO.getIdDetalleCambioEstado());

        int cantidadFormularios = 0;

        NumeracionFormulario numeracion = detalleCambioEstado.getRangoFormulario().getNumeracion();
        NumeracionFormularioDTO numeracionDTO = NumeracionFormularioHelper.toLevel1DTO(numeracion);
        List<DetalleNumeracionDTO> detalleNumeracionDTOList = DetalleNumeracionHelper.toListLevel1DTO(numeracion
                .getDetalleNumeracionList());
        numeracionDTO.setDetalleNumeracionList(detalleNumeracionDTOList);

        List<String> numerosDetalleFormularios = listarNumerosRango(detalleCambioEstado.getNumeroInicial(),
                detalleCambioEstado.getNumeroFinal(), numeracionDTO);

        for (RangoDTO rangoDTO : consultaRangoTipoFormularioDTO.getRangoDTOs()) {
            if (!numerosDetalleFormularios.contains(rangoDTO.getNumeroInicial())
                    || !numerosDetalleFormularios.contains(rangoDTO.getNumeroFinal())) {
                throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001005);
            }
        }

        /*
         * Llamamos el metodo calcular cantidad formularios de la clase Util
         */
        for (RangoDTO rangoDTO : consultaRangoTipoFormularioDTO.getRangoDTOs()) {

            List<String> strings = listarNumerosRango(rangoDTO.getNumeroInicial(), rangoDTO.getNumeroFinal(),
                    consultaRangoTipoFormularioDTO.getIdTipoFormulario());
            cantidadFormularios += strings.size();
        }
        return cantidadFormularios;
    }

    @Override
    public void registrarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO)
            throws CirculemosNegocioException {
        validarStockTipoResponsable(stockTipoResponsableDTO);
        StockTipoResponsable stockTipoResponsable = StockTipoResponsableHelper.toLevel1Entity(stockTipoResponsableDTO,
                null);
        stockTipoResponsable.setEstadoStock(true);
        em.persist(stockTipoResponsable);
    }

    /**
     * Permite llevar a cabo las validaciones del stock que esta siendo ingresado
     * 
     * @param stockTipoResponsableDTO
     *            estructura con los datos a validar
     * @throws CirculemosNegocioException
     * <br>
     *             NUM_017001=Tipo de Formulario es obligatorio no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017002=Tipo de Asignacion de formulario es obligatorio no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017003=Rango Minimo es obligatorio, no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017004=Rango Maximo es obligatorio, no puede estar vacio. No se completo la operacion.<br>
     *             NUM_017005=Valor maximo de stock NO acepta valores negativos Debe ser MAYOR o IGUAL a el rango minimo. No se completo la operacion<br>
     *             NUM_017006=No es posible realizar la operacion debido a que los datos ya se encuentran registrados para ese tipo de formulario y
     *             tipo de asignacion seleccionados<br>
     * @author luis.forero(2015-01-30) giovanni.velandia(mod 2015-08-20)
     */
    private void validarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO)
            throws CirculemosNegocioException {
        if (stockTipoResponsableDTO.getTipoFormulario() == null
                || stockTipoResponsableDTO.getTipoFormulario().getId() == null) {
            throw new CirculemosNegocioException(AdministrarStockTipoResponsable.NUM_017001);
        }
        if (stockTipoResponsableDTO.getTipoResponsable() == null
                || stockTipoResponsableDTO.getTipoResponsable().getId() == null) {
            throw new CirculemosNegocioException(AdministrarStockTipoResponsable.NUM_017002);
        }
        /*
         * if (stockTipoResponsableDTO.getStockMinimo() == 0) { throw new CirculemosNegocioException(AdministrarStockTipoResponsable.NUM_017003); } if
         * (stockTipoResponsableDTO.getStockMaximo() == 0) { throw new CirculemosNegocioException(AdministrarStockTipoResponsable.NUM_017004); }
         */
        if (stockTipoResponsableDTO.getStockMinimo() < 1) {
            throw new CirculemosNegocioException(AdministrarStockTipoResponsable.NUM_017007);
        }
        if (stockTipoResponsableDTO.getStockMaximo() < stockTipoResponsableDTO.getStockMinimo()) {
            throw new CirculemosNegocioException(AdministrarStockTipoResponsable.NUM_017005);
        }

        if (stockTipoResponsableDTO.getId() == null) {
            TypedQuery<Long> query = em
                    .createNamedQuery(StockTipoResponsable.SQ_COUNT_BY_TIP_FORM_TIP_RESP, Long.class);
            query.setParameter("pIdTipForm", stockTipoResponsableDTO.getTipoFormulario().getId());
            query.setParameter("pIdTipResp", stockTipoResponsableDTO.getTipoResponsable().getId());

            Long count = query.getSingleResult();
            if (count > 0) {
                throw new CirculemosNegocioException(AdministrarStockTipoResponsable.NUM_017006);
            }
        }
    }

    @Override
    public void actualizarStockTipoResponsable(StockTipoResponsableDTO stockTipoResponsableDTO)
            throws CirculemosNegocioException {
        validarStockTipoResponsable(stockTipoResponsableDTO);

        StockTipoResponsable stockTipoResponsable = em
                .find(StockTipoResponsable.class, stockTipoResponsableDTO.getId());
        stockTipoResponsable = StockTipoResponsableHelper.toLevel1Entity(stockTipoResponsableDTO, stockTipoResponsable);
        em.merge(stockTipoResponsable);

    }

    @Override
    public void eliminarStockTipoResponsable(Integer idStockTipoResponsable) {
        StockTipoResponsable stockTipoResponsable = em.find(StockTipoResponsable.class, idStockTipoResponsable);
        em.remove(stockTipoResponsable);
    }

    // Commented unused Sergio Torres 17/dic/2015
    // /**
    // * Cuenta la cantidad de formularios de un tipo de formulario puntual que tenga asignados un responsable
    // *
    // * @param idResponsable
    // * Id del responsable cuyos formularios seran contados
    // * @param idTipoFormulario
    // * Tipo de formulario de los formularios que seran contados
    // * @return Cantidad de formularios asignados que tiene el responsable
    // */
    // private Long contarFormulariosAsignados(Long idResponsable, Integer idTipoFormulario) {
    // TypedQuery<Long> consulta = em.createNamedQuery(Formulario.SQ_COUNT_BY_RESP_TIPO_FORMULARIO, Long.class);
    // consulta.setParameter("idResponsable", idResponsable);
    //
    // List<Integer> estadosAsig = new ArrayList<Integer>();
    // estadosAsig.add(EnumEstadoFomulario.ASIGNADO.getValue());
    // consulta.setParameter("estados", estadosAsig);
    // consulta.setParameter("idTipoFormulario", idTipoFormulario);
    //
    // try {
    // Long cantidadFormularios = consulta.getSingleResult();
    // return cantidadFormularios;
    // } catch (NoResultException e) {
    // return 0L;
    // }
    // }

    // @Override
    // @Deprecated
    // public void cambiarEstadoFormulario(List<SeguimientoFormularioDTO> seguimientoFormularios,
    // boolean validarResponsable, EnumEstadoFomulario estadoSiguiente, boolean validarConfiguracionEstados,
    // boolean registraRango) throws CirculemosNegocioException, CirculemosAlertaException {
    // checkNotNull(seguimientoFormularios, "No hay formularios para cambiar de estado");
    // // Tomar el tipo de formulario del primer formulario
    // TipoFormularioDTO tipoFormularioDTO = seguimientoFormularios.get(0).getFormulario().getTipoFormulario();
    // SeguimientoFormularioDTO seguimientoFormularioDTO = seguimientoFormularios.get(0);
    // // Validar si el responsable esta activo para el cambio de estado
    // ResponsableFormulario responsable = em.find(ResponsableFormulario.class, seguimientoFormularioDTO
    // .getResponsableFormulario().getId());
    // SeguimientoFormulario seguimientoFormulario = SeguimientoFormularioHelper.toLevel1Entity(
    // seguimientoFormularioDTO, null);
    // seguimientoFormulario.setResponsableFormulario(responsable);
    // // validarResponsable(seguimientoFormulario);
    // // 1. Validar que la cantidad de formularios a asignar no superen el maximo de asignacion para el responsable, teniendo en cuenta los
    // // formularios que dicho responsable ya tenga asignados a la fecha
    // /*
    // * List<StockTipoAsignacionDTO> configuracionStock = consultarStockTipoAsignacion(tipoFormularioDTO.getId(),
    // * seguimientoFormularioDTO.getTipoAsignacion().getId(), true); if (!configuracionStock.isEmpty()) { StockTipoAsignacionDTO stock =
    // * configuracionStock.get(0); Long formularioAsignados = contarFormulariosAsignados(seguimientoFormularioDTO.getResponsableFormulario()
    // * .getId(), tipoFormularioDTO.getId()); if ((formularioAsignados + seguimientoFormularios.size()) > stock.getStockMaximo()) { throw new
    // * CirculemosNegocioException(CambioEstadosFormularios.NUM_001009); } } else { throw new
    // * CirculemosNegocioException(CambioEstadosFormularios.NUM_001014); }
    // */
    //
    // // 2. Validar que todos los formularios esten registrados en el sistema mediante algun rango de registro
    // DetalleCambioEstadoDTO detalleCambioEstadoDto = seguimientoFormularioDTO.getDetalleCambioEstado();
    // String trama = obtenerTramaNumeroFormulario(seguimientoFormularioDTO.getDetalleCambioEstado()
    // .getNumeroInicial());
    // if (!registraRango) {
    // seguimientoFormularioDTO.getDetalleCambioEstado().setNumeroInicial(
    // seguimientoFormularios.get(0).getFormulario().getNumeroFormulario());
    // seguimientoFormularioDTO.getDetalleCambioEstado()
    // .setNumeroFinal(
    // seguimientoFormularios.get(seguimientoFormularios.size() - 1).getFormulario()
    // .getNumeroFormulario());
    // }
    // seguimientoFormularioDTO.getDetalleCambioEstado().setTrama(trama);
    // List<RangoFormulario> rangosRegistro = obtenerRangosRegistro(seguimientoFormularioDTO, tipoFormularioDTO);
    //
    // // 3. Validar la configuracion de estados (boolean validarConfiguracionEstados) y que el formulario no tenga responsable asignado (boolean
    // // validarResponsable), se hace dividiendo la lista de formularios en sublistas de 3000 registros y con la invocacion asincrona del metodo que
    // // hace la validacion para cada una de estas sublistas
    // List<List<SeguimientoFormularioDTO>> listasDivididas = dividirLista(seguimientoFormularios);
    // List<Future<List<SeguimientoFormulario>>> ejecucionesLanzadas = new ArrayList<Future<List<SeguimientoFormulario>>>();
    //
    // for (List<SeguimientoFormularioDTO> divisionLista : listasDivididas) {
    // int i = listasDivididas.indexOf(divisionLista);
    // Future<List<SeguimientoFormulario>> ejecucionValidaEstados = null;
    // ejecucionValidaEstados = ilProcesamientoFormularios.validarConfiguracionEstados(divisionLista,
    // tipoFormularioDTO, i, validarResponsable, estadoSiguiente.getValue(), validarConfiguracionEstados);
    // ejecucionesLanzadas.add(ejecucionValidaEstados);
    // }
    //
    // boolean enEjecucion = true;
    // int finalizadas = 0;
    // while (enEjecucion) {
    // finalizadas = 0;
    // for (Future<List<SeguimientoFormulario>> future : ejecucionesLanzadas) {
    // if (!future.isDone()) {
    // continue;
    // } else {
    // finalizadas++;
    // try {
    // future.get();
    // } catch (InterruptedException e) {
    // // e.printStackTrace();
    // } catch (ExecutionException e) {
    // for (Future<List<SeguimientoFormulario>> ejecuciones : ejecucionesLanzadas) {
    // ejecuciones.cancel(true);
    // }
    // Throwable causa = e.getCause();
    // if (causa instanceof CirculemosNegocioException) {
    // CirculemosNegocioException cne = (CirculemosNegocioException) e.getCause();
    // throw cne;
    // } else {
    // throw new CirculemosRuntimeException("Error al tratar de realizar la operacion: "
    // + e.getCause());
    // }
    // } catch (Exception e) {
    // throw new CirculemosRuntimeException("Error al tratar de realizar la operacion: "
    // + e.getCause());
    // }
    // }
    // }
    // if (finalizadas == ejecucionesLanzadas.size()) {
    // enEjecucion = false;
    // }
    // }
    // if (!enEjecucion) {
    // // 4. Insertar el detalle cambio estado y Guardar la relacion del detalle cambio estado con los rangos de registro involucrados para todo
    // // el rango a cambiar de estado
    // DetalleCambioEstado detalleCambioEstadoPersistir = DetalleCambioEstadoHelper.toLevel1Entity(
    // detalleCambioEstadoDto, new DetalleCambioEstado());
    // // TODO REFACTOR ENTIDADES detalleCambioEstadoPersistir.setRangoFormularioList(rangosRegistro);
    // if (registraRango) {
    // em.persist(detalleCambioEstadoPersistir);
    // }
    // // 5. Insertar/Actualizar cada formulario con su respectivo seguimiento
    // // (Si es una asignacion debe insertar los formularios)
    // // Cada seguimiento de formulario va asociado al detalle cambio estado persistido, para todos los formularios se tiene ese mismo
    // // detalle cambio estado,el detalle me da el rango como tal con el q se hizo el cambio de estado
    // // Hay que guardar la relacion del detalle cambio estado con el rango de formulario con el que se hizo el registro de los numeros de
    // // formulario
    // List<List<SeguimientoFormulario>> listaFormulariosValidados = new ArrayList<List<SeguimientoFormulario>>();
    // try {
    // for (Future<List<SeguimientoFormulario>> ejecucionValidacion : ejecucionesLanzadas) {
    // listaFormulariosValidados.add(ejecucionValidacion.get());
    // }
    //
    // for (List<SeguimientoFormulario> list : listaFormulariosValidados) {
    // registrarCambioEstadoFormulario(list, rangosRegistro, detalleCambioEstadoPersistir,
    // listaFormulariosValidados.indexOf(list), registraRango);
    // }
    // } catch (InterruptedException e) {
    // logger.error("No debe ocurrir ya que el metodo get de cada ejecucion lanzada para la validacion: en este punto ha terminado y fue exitosa");
    // } catch (ExecutionException e) {
    // logger.error("No debe ocurrir ya que el metodo get de cada ejecucion lanzada para la validacion: en este punto ha terminado y fue exitosa");
    // } catch (Exception e) {
    // if (registraRango) {
    // reversarTransaccionCambioEstado(detalleCambioEstadoPersistir.getId());
    // }
    // throw new CirculemosRuntimeException("Error al tratar de realizar la operacion: ");
    // }
    // try {
    // // 6. Notificar al responsables por correo
    // // Envio del correo de notificacion de asignacion
    // TipoFormulario tipoFormulario = em.find(TipoFormulario.class, tipoFormularioDTO.getId());
    // String numerosFormulario = "";
    // if (registraRango) {
    // numerosFormulario = detalleCambioEstadoDto.getNumeroInicial() + " a "
    // + detalleCambioEstadoDto.getNumeroFinal();
    // } else {
    // for (SeguimientoFormularioDTO seguimiento : seguimientoFormularios) {
    // if (StringUtils.isEmpty(numerosFormulario)) {
    // numerosFormulario = seguimiento.getFormulario().getNumeroFormulario();
    // } else {
    // numerosFormulario = ", " + seguimiento.getFormulario().getNumeroFormulario();
    // }
    // }
    // }
    // // enviarCorreoNotificacionCambioEstado(detalleCambioEstadoDto, tipoFormulario.getNombre(),responsable.getCorreo(), estadoSiguiente,
    // // numerosFormulario);
    // } catch (Exception e) {
    // // TODO REFACTOR CODIGOS ERROR throw new CirculemosAlertaException(CambioEstadosFormularios.NUM_001018);
    // }
    // }
    //
    // }

    // Commented unused Sergio Torres 17/dic/2015
    // @Deprecated
    // public boolean validarEstadoActualFormularios(Long idDetalleCambioEstadoDTO, Integer idEstadoActualFormulario)
    // throws CirculemosNegocioException {
    // logger.debug("FormularioEJB::validarEstadoActualFormularios");
    // Boolean valido = false;
    // TypedQuery<SeguimientoFormulario> query = em.createNamedQuery(
    // SeguimientoFormulario.SQ_SEGUIMIENTO_BY_ESTADO_ACTUAL_FORMULARIO, SeguimientoFormulario.class);
    // query.setParameter(SeguimientoFormulario.SQ_SEGUIMIENTO_BY_ESTADO_ACTUAL_FORMULARIO_P1,
    // idDetalleCambioEstadoDTO);
    // query.setParameter(SeguimientoFormulario.SQ_SEGUIMIENTO_BY_ESTADO_ACTUAL_FORMULARIO_P2,
    // idEstadoActualFormulario);
    // List<SeguimientoFormulario> resultado = query.getResultList();
    // if (resultado != null && !resultado.isEmpty()) {
    // // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001011);
    // } else {
    // valido = true;
    // }
    // return valido;
    // }

    /**
     * Validar que todos los fromularios esten registrados en el sistema mediante algun rango de registro.<br>
     * Para ello: consultar el rango del numero inicial y del numero final, Si el numero inicial o el final no esta dentro de algun rango entonces ya
     * se tiene que hay formularios no registrados.Si el rango de registro del numero inicial y final es uno solo, OK los formularios estan
     * registrados.Si hay varios rangos de registro q involucran al numero inicial y final, es probable que todos los formularios esten registrados.
     * Consultar todos los posibles rangos que incluyan el rango en cuestion y evaluar la cantidad de formularios contra la suma de las cantidades en
     * los rangos registrados, si la cantidad en los rangos registrados (desde numero inicial hasta final) es menor a la realmente existente entre
     * numero inicial y final, quiere decir que falta formularios por registrar
     * 
     * @param seguimientoFormularioDTO
     * @param tipoFormularioDTO
     * @return Lista de rangos formulario donde quedaron registrados los formularios
     * @throws CirculemosNegocioException
     */
    @Deprecated
    public List<RangoFormulario> obtenerRangosRegistro(SeguimientoFormularioDTO seguimientoFormularioDTO,
            TipoFormularioDTO tipoFormularioDTO) throws CirculemosNegocioException {
        String trama = seguimientoFormularioDTO.getDetalleCambioEstado().getTrama();
        RangoFormulario rangoNumInicial = consultarRangoNumeroFormulario(seguimientoFormularioDTO
                .getDetalleCambioEstado().getNumeroInicial(), tipoFormularioDTO.getId(), trama);
        if (rangoNumInicial == null) {
            // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001001);
        }
        RangoFormulario rangoNumFinal = consultarRangoNumeroFormulario(seguimientoFormularioDTO
                .getDetalleCambioEstado().getNumeroFinal(), tipoFormularioDTO.getId(), trama);
        if (rangoNumFinal == null) {
            // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001001);
        }

        List<RangoFormulario> rangosRegistro = new ArrayList<RangoFormulario>();
        if (rangoNumInicial.getId() != rangoNumFinal.getId()) {
            // Hay varios rangos de registro(por lo minimo dos)
            rangosRegistro = consultarRangosRelacionadosNumeros(seguimientoFormularioDTO.getDetalleCambioEstado()
                    .getNumeroInicial(), seguimientoFormularioDTO.getDetalleCambioEstado().getNumeroFinal(),
                    tipoFormularioDTO.getId(), trama);
            rangoNumInicial = rangosRegistro.get(0);
            rangoNumFinal = rangosRegistro.get(rangosRegistro.size() - 1);

            NumeracionFormularioDTO numeracionFormularioInicio = NumeracionFormularioHelper.toLevel0DTO(rangoNumInicial
                    .getNumeracion());
            numeracionFormularioInicio.setDetalleNumeracionList(administracionFormulariosEJB
                    .consultarDetalleNumeracionFormulario(rangoNumInicial.getNumeracion().getId()));

            NumeracionFormularioDTO numeracionFormularioFinal = NumeracionFormularioHelper.toLevel0DTO(rangoNumFinal
                    .getNumeracion());
            numeracionFormularioFinal.setDetalleNumeracionList(administracionFormulariosEJB
                    .consultarDetalleNumeracionFormulario(rangoNumFinal.getNumeracion().getId()));

            UtilFormularios utilFormularios = new UtilFormularios();
            Integer cantidadRegistradosPrimerRango = utilFormularios.calcularCantidadFormularios(
                    seguimientoFormularioDTO.getDetalleCambioEstado().getNumeroInicial(),
                    rangoNumInicial.getNumeroFinal(), numeracionFormularioInicio);
            Integer cantidadRegistradosUltimoRango = utilFormularios.calcularCantidadFormularios(rangoNumFinal
                    .getNumeroInicial(), seguimientoFormularioDTO.getDetalleCambioEstado().getNumeroFinal(),
                    numeracionFormularioFinal);
            Integer cantidadTotalFormRegistrados = cantidadRegistradosPrimerRango + cantidadRegistradosUltimoRango;

            if (rangosRegistro.size() > 2) {
                // Aparte del rango de registro del numero inicial y del rango del numero final hay otros rangos intermedios involucrados y se debe
                // validar la cantidad registrada en los mismos
                /*
                 * TODO REFACTOR DE ENTIDADES for (int i = 1; i < rangosRegistro.size() - 1; i++) { cantidadTotalFormRegistrados +=
                 * rangosRegistro.get(i).getCantidadFormularios(); }
                 */
            }
            if (cantidadTotalFormRegistrados < seguimientoFormularioDTO.getDetalleCambioEstado()
                    .getCantidadFormularios()) {
                // Lanzar error
                // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001001);
            }
        } else {
            rangosRegistro.add(rangoNumInicial);
        }

        return rangosRegistro;

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void reversarTransaccionCambioEstado(Long idDetalleCambioEstado) throws CirculemosNegocioException {
        TypedQuery<SeguimientoFormulario> query = em.createNamedQuery(
                SeguimientoFormulario.SQ_SEGUIMIENTO_BY_DETALLE_CAMBIO, SeguimientoFormulario.class);
        query.setParameter("idDetalleCambioEstado", idDetalleCambioEstado);
        List<SeguimientoFormulario> seguimientosReversar = query.getResultList();
        for (SeguimientoFormulario seguimientoFormulario : seguimientosReversar) {
            em.remove(seguimientoFormulario);
            // actualizarFormularioUltimoSeguimiento(seguimientoFormulario.getFormulario(), false);
        }

        GenericDao<DetalleCambioEstado> detalleCambioDao = new GenericDao<DetalleCambioEstado>(
                DetalleCambioEstado.class, em);
        DetalleCambioEstado detalle = detalleCambioDao.findUniqueByAttribute("id", idDetalleCambioEstado);
        if (detalle != null) {
            em.remove(detalle);
        }
    }

    /**
     * Valida que exista una configuracion de estados valida que permita pasar del estado actual del formulario a asignado y que el formulario // no
     * tenga un responsable asignado
     * 
     * @param formularios
     * @param tipoFormularioDTO
     * @param i
     * @return
     * @throws CirculemosNegocioException
     */

    @Override
    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Deprecated
    public Future<List<SeguimientoFormulario>> validarConfiguracionEstados(
            List<SeguimientoFormularioDTO> seguimientoFormularios, TipoFormularioDTO tipoFormularioDTO, int i,
            boolean validarResponsable, Integer idEstadoSiguiente, boolean validarConfiguracionEstados)
            throws CirculemosNegocioException {
        // Hay que consultar cada uno de los formulario, si no existe es porque esta
        // "Pendiente por asignar" si existe validar que del estado actual por alguna configuracion de estados le permita pasar al estado deseado y
        // que no tenga ya asignado algun responsable, el cual esta en el campo estado del DTO del formulario
        List<SeguimientoFormulario> seguimientosEntidades = new ArrayList<SeguimientoFormulario>();
        Map<Integer, List<Integer>> confEstados = obtenerConfiguracionEstadosTipoForm(tipoFormularioDTO.getId());
        Integer idEstadoActual;
        TypedQuery<Formulario> query = em.createNamedQuery(Formulario.SQ_FORM_BY_NUMERO_TIPO_FORM, Formulario.class);
        query.setParameter("idTipoFormulario", tipoFormularioDTO.getId());

        for (SeguimientoFormularioDTO seguimientoDTO : seguimientoFormularios) {
            query.setParameter("numeroFormulario", seguimientoDTO.getFormulario().getNumeroFormulario());
            Formulario formulario = null;
            SeguimientoFormulario seguimientoFormulario = SeguimientoFormularioHelper.toLevel1Entity(seguimientoDTO,
                    null);
            try {
                formulario = query.getSingleResult();
                idEstadoActual = formulario.getEstadoFormulario().getId();
                if (validarResponsable && formulario.getResponsableFormulario() != null) {
                    // Lanza error por responsable
                    // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001008);
                }
            } catch (NoResultException e) {
                idEstadoActual = EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR.getValue();
            }
            boolean existeConfiguracion = true;
            // Verificar si es necesario validar la configuracion de estados para el cambio de estado
            if (validarConfiguracionEstados) {
                existeConfiguracion = validarConfiguracionEstados(confEstados, idEstadoActual, idEstadoSiguiente);
            }
            if (!existeConfiguracion) {
                // TODO REFACTOR CODIGOS ERROR throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001002);
            }

            if (formulario == null) {
                formulario = new Formulario();
                formulario.setNumeroFormulario(seguimientoDTO.getFormulario().getNumeroFormulario());
                formulario.setTipoFormulario(new TipoFormulario(tipoFormularioDTO.getId()));
            }
            seguimientoFormulario.setFormulario(formulario);
            seguimientosEntidades.add(seguimientoFormulario);
        }
        return new AsyncResult<>(seguimientosEntidades);
    }

    // Commented unused Sergio Torres 17/dic/2015
    // private <T> List<List<T>> dividirLista(List<T> lista) {
    // int tamanio = TAMANIO_DIVISION_ARREGLO;
    // List<List<T>> parts = new ArrayList<List<T>>();
    // final int n = lista.size();
    // if (n <= tamanio)
    // parts.add(new ArrayList<T>(lista));
    // else {
    // for (int i = 0; i < n; i += tamanio) {
    // parts.add(new ArrayList<T>(lista.subList(i, Math.min(n, i + tamanio))));
    // }
    // }
    // return parts;
    // }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void registrarCambioEstadoFormulario(List<SeguimientoFormulario> seguimientosEntidades,
            List<RangoFormulario> rangosRegistro, DetalleCambioEstado detalleCambioEstado, int i, boolean registraRango)
            throws CirculemosNegocioException {
        EstadoFormulario estadoFormulario = new EstadoFormulario(seguimientosEntidades.get(0).getEstadoFormulario()
                .getId());
        GenericDao<Formulario> formularioDao = new GenericDao<Formulario>(Formulario.class, em);
        ResponsableFormulario responsableFormularios = new ResponsableFormulario(seguimientosEntidades.get(0)
                .getResponsableFormulario().getId());
        // TipoAsignacionFormulario tipoAsignacion = new TipoAsignacionFormulario(seguimientosEntidades.get(0) .getTipoAsignacion().getId());
        try {
            // Si se debe registrar por formularios especificos
            if (!registraRango) {
                for (SeguimientoFormulario seguimiento : seguimientosEntidades) {
                    seguimiento.setId(null);
                    seguimiento.getFormulario().setEstadoFormulario(estadoFormulario);
                    if (rangosRegistro.size() == 1) {// Todos los formularios se registraron con el mismo rango
                        seguimiento.getFormulario().setRangoFormulario(rangosRegistro.get(0));
                    } else {
                        // Hay varios rangos de registro, se debe identificar en que rango esta cada formulario
                        List<RangoFormulario> rangosFormulario = new ArrayList<>();
                        rangosFormulario.add(obtenerRango(rangosRegistro, seguimiento.getFormulario()
                                .getNumeroFormulario()));
                        seguimiento.getFormulario().setRangoFormulario(rangosFormulario.get(0));
                        // TODO REFACTOR ENTIDADESdetalleCambioEstado.setRangoFormularioList(rangosFormulario);
                    }
                    seguimiento.getFormulario().setResponsableFormulario(responsableFormularios);
                    // seguimiento.getFormulario().setTipoAsignacion(tipoAsignacion);
                    formularioDao.saveOrUpdate(seguimiento.getFormulario());

                    detalleCambioEstado.setId(null);
                    detalleCambioEstado.setNumeroInicial(seguimiento.getFormulario().getNumeroFormulario());
                    detalleCambioEstado.setNumeroFinal(seguimiento.getFormulario().getNumeroFormulario());
                    em.persist(detalleCambioEstado);

                    seguimiento.setDetalleCambioEstado(detalleCambioEstado);
                    em.persist(seguimiento);
                }
            } else {
                for (SeguimientoFormulario seguimiento : seguimientosEntidades) {
                    seguimiento.getFormulario().setEstadoFormulario(estadoFormulario);
                    if (rangosRegistro.size() == 1)// Todos los formularios se registraron con el mismo rango
                        seguimiento.getFormulario().setRangoFormulario(rangosRegistro.get(0));
                    else {
                        // Hay varios rangos de registro, se debe identificar en que rango esta cada formulario
                        seguimiento.getFormulario().setRangoFormulario(
                                obtenerRango(rangosRegistro, seguimiento.getFormulario().getNumeroFormulario()));
                    }
                    seguimiento.getFormulario().setResponsableFormulario(responsableFormularios);
                    // seguimiento.getFormulario().setTipoAsignacion(tipoAsignacion);
                    formularioDao.saveOrUpdate(seguimiento.getFormulario());

                    seguimiento.setDetalleCambioEstado(detalleCambioEstado);
                    em.persist(seguimiento);
                }
            }
        } catch (Exception e) {
            if (!registraRango) {
                reversarTransaccionCambioEstado(detalleCambioEstado.getId());
            }
        }
    }

    /**
     * De un listado de rangos obtiene aquel que contenga el numero de formulario enviado, de lo contrario null
     * 
     * @param rangosRegistro
     *            Rangos de registro de formularios que fueron previamente consultados y pertenecen a un mismo tipo de formulario y numeracion
     * @param numeroFormulario
     *            Numero de formulario con el mismo tipo de formulario y numeracion de los rangos enviados en la lista
     * @return RangoFormulario Rango que contiene al numero enviado
     */
    private RangoFormulario obtenerRango(List<RangoFormulario> rangosRegistro, String numeroFormulario) {
        for (RangoFormulario rangoFormulario : rangosRegistro) {
            if (esNumIniMenorIgualNumFin(rangoFormulario.getNumeroInicial(), numeroFormulario)
                    && esNumIniMenorIgualNumFin(numeroFormulario, rangoFormulario.getNumeroFinal())) {
                return rangoFormulario;
            }
        }
        return null;
    }

    private List<RangoFormulario> consultarRangosRelacionadosNumeros(String numeroInicial, String numeroFinal,
            Integer idTipoFormulario, String trama) {
        TypedQuery<RangoFormulario> query = em.createNamedQuery(RangoFormulario.SQ_RANGOS_RELACIONADOS_NUMEROS,
                RangoFormulario.class);
        query.setParameter("idTipoFormulario", idTipoFormulario);
        query.setParameter("trama", trama);
        query.setParameter("numeroInicial", numeroInicial);
        query.setParameter("numeroFinal", numeroFinal);
        return query.getResultList();

    }

    /**
     * Retorna el rango de formulario con el cual esta registrado un numero de formulario puntual, sino lo encuentra retorna null Validado con
     * analisis: Para el mismo tipo de formulario: un numero de formulario dado es unico en el sistema;no se repiten
     * 
     * @param numeroFormulario
     *            Numero para el cual se va a consultar el rango registrado que lo contiene
     * @return Rango con el cual fue registrado el numero enviado,o null si el numero enviado no esta registrado en ningun rango existente
     */
    public RangoFormulario consultarRangoNumeroFormulario(String numeroFormulario, Integer idTipoFormulario,
            String trama) {
        if (StringUtils.isBlank(trama))
            trama = obtenerTramaNumeroFormulario(numeroFormulario);
        TypedQuery<RangoFormulario> query = em.createNamedQuery(RangoFormulario.SQ_RANGOS_BY_NUMERO_TRAMA,
                RangoFormulario.class);
        query.setParameter("idTipoFormulario", idTipoFormulario);
        query.setParameter("numeroFormulario", numeroFormulario);
        query.setParameter("trama", trama);

        List<RangoFormulario> resultado = query.getResultList();

        if (resultado != null && !resultado.isEmpty()) {
            return resultado.get(0);
        } else {
            return null;
        }
    }

    /**
     * Permite
     * 
     * @param codigoOrganismo
     * @param numeroFormulario
     * @param idTipoFormulario
     * @param trama
     * @return
     */
    private RangoFormulario consultarRangoNumeroFormulario(Integer codigoOrganismo, String numeroFormulario,
            Integer idTipoFormulario, String trama) {
        if (StringUtils.isBlank(trama)) {
            trama = obtenerTramaNumeroFormulario(numeroFormulario);
        }
        TypedQuery<RangoFormulario> query = em.createNamedQuery(RangoFormulario.SQ_RANGOS_BY_NUMERO_ORGANISMO,
                RangoFormulario.class);
        query.setParameter("codigoOrganismo", codigoOrganismo);
        query.setParameter("idTipoFormulario", idTipoFormulario);
        query.setParameter("numeroFormulario", numeroFormulario);
        query.setParameter("trama", trama);

        List<RangoFormulario> resultado = query.getResultList();

        if (resultado != null && !resultado.isEmpty()) {
            return resultado.get(0);
        } else {
            return null;
        }
    }

    private void crearCorreoNotificacionCambioEstado(EnumEstadoFomulario estadoFormulario, Integer codigoOrganismo,
            Integer idTipoFormulario, DetalleCambioEstadoDTO detalleCambioEstadoDTO, List<String> intervalosList) {
        // ENVIAR CORREO DE NOTIFICACION AL RESPONSABLE
        String nombreOrganismo = StringUtils.EMPTY, nombreTipoFormulario = StringUtils.EMPTY, correo = StringUtils.EMPTY;
        OrganismoTransito organismoTransito = em.find(OrganismoTransito.class, codigoOrganismo);
        TipoFormulario tipoFormulario = em.find(TipoFormulario.class, idTipoFormulario);

        if (organismoTransito != null) {
            nombreOrganismo = organismoTransito.getNombreOrganismo();
            organismoTransito = null;
        }
        if (tipoFormulario != null) {
            nombreTipoFormulario = tipoFormulario.getNombre();
            tipoFormulario = null;
        }
        if (detalleCambioEstadoDTO.getResponsableFormulario().getCorreoResponsableFormulario() == null) {
            ResponsableFormulario responsableFormulario = em.find(ResponsableFormulario.class, detalleCambioEstadoDTO
                    .getResponsableFormulario().getId());
            correo = responsableFormulario.getCorreoResponsableFormulario();
            responsableFormulario = null;
        } else
            correo = detalleCambioEstadoDTO.getResponsableFormulario().getCorreoResponsableFormulario();

        StringBuilder intervalos = new StringBuilder();
        for (String intervalo : intervalosList)
            intervalos.append(intervalo);

        detalleCambioEstadoDTO.setFechaMovimiento(UtilFecha.buildCalendar(null).getTime());

        enviarCorreoNotificacionCambioEstado(detalleCambioEstadoDTO, nombreOrganismo, nombreTipoFormulario, correo,
                estadoFormulario, intervalos.toString(), codigoOrganismo);
    }

    /**
     * Envia el correo de notificacion de un cambio de estado de formularios (Asignacion, Devolucion)
     * 
     * @param detalleCambioDto
     *            Contiene los datos de fecha de movimiento, rango de numero incial y numero final de formulario
     * @param tipoFormularioDTO
     *            Contiene el nombre del tipo de formulario asignado y debe contener el organismo de transito de este tipo de asignacion con su
     *            respectivo codigo de organismo
     * @param numerosFormulario
     *            Contiene los números de formularios seleccionados y que cambiaron de estado (Solo cuando es por seleccion de formularios)
     * @param correoDestino
     *            Cuenta de correo electronico a la cual se enviara el correo
     */
    private void enviarCorreoNotificacionCambioEstado(DetalleCambioEstadoDTO detalleCambioDto, String nombreOrganismo,
            String nombreTipoFormulario, String correoDestino, EnumEstadoFomulario enumEstadoFomulario,
            String numerosFormulario, Integer codigoOrganismo) {
        String[] correos = { correoDestino };
        Map<String, Object> variablesMensaje = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(detalleCambioDto.getFechaMovimiento());
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        variablesMensaje.put(EnumVariablesCorreo.tipo_formulario.toString(), nombreTipoFormulario);
        variablesMensaje.put(EnumVariablesCorreo.intervalos.toString(), numerosFormulario);
        variablesMensaje.put(EnumVariablesCorreo.organismo.toString(), nombreOrganismo);

        EnumVariablesCorreo fecha = EnumVariablesCorreo.fecha_asignacion;
        EnumTipoCorreo tipoCorreo = EnumTipoCorreo.NOTIFICACION_ASIGNACION_FORMULARIOS;

        switch (enumEstadoFomulario) {
        case ANULADO:
            fecha = EnumVariablesCorreo.fecha_anulacion;
            tipoCorreo = EnumTipoCorreo.NOTIFICACION_ANULACION_FORMULARIOS;
            break;
        case DEVUELTO:
            fecha = EnumVariablesCorreo.fecha_devolucion;
            tipoCorreo = EnumTipoCorreo.NOTIFICACION_DEVOLUCION_FORMULARIOS;
            break;
        default:
            break;
        }

        variablesMensaje.put(fecha.toString(), formateador.format(detalleCambioDto.getFechaMovimiento()));
        circulemosMailSenderEjb.enviarCorreo(codigoOrganismo, tipoCorreo, correos, variablesMensaje);
    }

    /*
     * private Formulario consultarFormulario(String numeroFormulario, Integer IdTipoFormulario) { TypedQuery<Formulario> query =
     * em.createNamedQuery(Formulario.SQ_FORM_BY_NUMERO_TIPO_FORM, Formulario.class); query.setParameter("idTipoFormulario", IdTipoFormulario);
     * query.setParameter("numeroFormulario", numeroFormulario); try { return query.getSingleResult(); } catch (NoResultException e) { return null; }
     * }
     */

    @Override
    public List<DetalleCambioEstadoDTO> consultarDetalleCambioEstado(
            ConsultaDetalleCambioEstadoDTO consultaDetalleCambioEstadoDTO) throws CirculemosNegocioException {
        List<DetalleCambioEstadoDTO> resultados = new ArrayList<DetalleCambioEstadoDTO>();

        // Validacion de campo obligatorio
        checkNotNull(consultaDetalleCambioEstadoDTO.getCodigoOrganismo(),
                "Codigo organismo de transito a consultar vacio.");
        checkNotNull(consultaDetalleCambioEstadoDTO.getIdTipoFormulario(), "Tipo de formulario a consultar vacio.");
        checkNotNull(consultaDetalleCambioEstadoDTO.getIdTipoResponsable(), "Tipo de responsable a consultar vacio.");

        StringBuilder sql = new StringBuilder(CambioEstadosFormularioSQL.SQL_BASE);
        Map<String, Object> parametros = new HashMap<String, Object>();

        if (consultaDetalleCambioEstadoDTO.getIdTipoFormulario() != null) {
            sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_TIPO_FORMULARIO);
            parametros.put(CambioEstadosFormularioSQL.ParametrosConsulta.id_tipo_formulario.toString(),
                    consultaDetalleCambioEstadoDTO.getIdTipoFormulario());
        }

        if (consultaDetalleCambioEstadoDTO.getCodigoOrganismo() != null) {
            sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_CODIGO_ORGANISMO);
            parametros.put(CambioEstadosFormularioSQL.ParametrosConsulta.codigo_organismo.toString(),
                    consultaDetalleCambioEstadoDTO.getCodigoOrganismo());
        }

        if (consultaDetalleCambioEstadoDTO.getIdEstado() != null) {
            sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_ESTADO_FORMULARIO);
            parametros.put(CambioEstadosFormularioSQL.ParametrosConsulta.id_estado_formulario.toString(),
                    consultaDetalleCambioEstadoDTO.getIdEstado());
        }

        if (consultaDetalleCambioEstadoDTO.getFechaDesde() != null
                && consultaDetalleCambioEstadoDTO.getFechaHasta() != null) {
            sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_BETWEEN_FECHA_APLICACION);
            parametros.put(CambioEstadosFormularioSQL.ParametrosConsulta.fecha_aplicacion_desde.toString(),
                    consultaDetalleCambioEstadoDTO.getFechaDesde());
            parametros.put(CambioEstadosFormularioSQL.ParametrosConsulta.fecha_aplicacion_hasta.toString(),
                    consultaDetalleCambioEstadoDTO.getFechaHasta());
        }
        if (consultaDetalleCambioEstadoDTO.getIdTipoResponsable() != null
                && consultaDetalleCambioEstadoDTO.getIdTipoResponsable().equals(
                        EnumTipoResponsableFormulario.EMPRESA.getValue())) {
            if (consultaDetalleCambioEstadoDTO.getIdTipoIdentificacion() != null) {
                sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_DOCUMENTO_IDENTIFICACION);
                parametros.put(CambioEstadosFormularioSQL.ParametrosConsulta.id_tipo_identificacion.toString(),
                        consultaDetalleCambioEstadoDTO.getIdTipoIdentificacion());
                parametros.put(CambioEstadosFormularioSQL.ParametrosConsulta.numero_identificacion.toString(),
                        consultaDetalleCambioEstadoDTO.getNumeroIdentificacion());
            }
        }
        if (consultaDetalleCambioEstadoDTO.getIdTipoResponsable() != null
                && consultaDetalleCambioEstadoDTO.getIdTipoResponsable().equals(
                        EnumTipoResponsableFormulario.ORGANISMO_TRANSITO.getValue())) {
            if (consultaDetalleCambioEstadoDTO.getOrganismoResponsable() != null) {
                sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_CODIGO_ORGANISMO_RESPONSABLE);
                parametros.put(CambioEstadosFormularioSQL.ParametrosConsulta.codigo_organismo_responsable.toString(),
                        consultaDetalleCambioEstadoDTO.getOrganismoResponsable());
            }
        }

        sql.append(CambioEstadosFormularioSQL.SQL_GROUP_BY);
        sql.append(CambioEstadosFormularioSQL.SQL_ORDER_BY);
        Query query = em.createNativeQuery(sql.toString());
        Iterator<String> itParams = parametros.keySet().iterator();
        while (itParams.hasNext()) {
            String key = itParams.next();
            Object valor = parametros.get(key);
            query.setParameter(key, valor);
        }
        @SuppressWarnings("unchecked")
        List<Object[]> resultado = query.getResultList();
        for (Object[] registro : resultado) {
            DetalleCambioEstadoDTO detalleCambioEstadoDTO = new DetalleCambioEstadoDTO();
            int col = 0;

            // RangoFormulario
            RangoFormularioDTO rangoFormularioDTO = new RangoFormularioDTO();

            // Tipo de formulario
            TipoFormularioDTO tipoFormularioDTO = new TipoFormularioDTO();
            tipoFormularioDTO.setId(Integer.valueOf(registro[col++].toString()));
            tipoFormularioDTO.setNombre(String.valueOf(registro[col++]));
            rangoFormularioDTO.setTipoFormulario(tipoFormularioDTO);

            detalleCambioEstadoDTO.setRangoFormulario(rangoFormularioDTO);

            // Estado Formulario
            EstadoFormularioDTO estadoFormulario = new EstadoFormularioDTO();
            estadoFormulario.setNombre(String.valueOf(registro[col++]));
            estadoFormulario.setId((Integer) registro[col++]);
            detalleCambioEstadoDTO.setEstadoFormulario(estadoFormulario);

            detalleCambioEstadoDTO.setId(Long.valueOf(registro[col++].toString()));
            detalleCambioEstadoDTO.setNumeroInicial(String.valueOf(registro[col++]));
            detalleCambioEstadoDTO.setNumeroFinal(String.valueOf(registro[col++]));
            detalleCambioEstadoDTO.setCantidadFormularios(Integer.valueOf(registro[col++].toString()));
            detalleCambioEstadoDTO.setFechaAplicacionEstado((Date) registro[col++]);
            resultados.add(detalleCambioEstadoDTO);
        }
        return resultados;
    }

    @Override
    public DetalleCambioEstadoDTO consultarDetalleCambioEstado(Long codDetalleCambioEstado)
            throws CirculemosNegocioException {
        FormularioEJB.logger.debug("FormularioEJB.consultarDetalleCambioEstado");
        DetalleCambioEstadoDTO detalleCambioEstadoDTO = null;
        DetalleCambioEstado detalleCambioEstado = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT dce FROM DetalleCambioEstado dce");
        jpql.append(" WHERE dce.id = :idDetalleCambioEstado");

        Query query = em.createQuery(jpql.toString());

        query.setParameter("idDetalleCambioEstado", codDetalleCambioEstado);

        detalleCambioEstado = (DetalleCambioEstado) query.getSingleResult();
        detalleCambioEstadoDTO = DetalleCambioEstadoHelper.toLevel1DTO(detalleCambioEstado);

        // Informacion del tipo formulario
        detalleCambioEstadoDTO.getRangoFormulario().setTipoFormulario(
                TipoFormularioHelper.toLevel0DTO(detalleCambioEstado.getRangoFormulario().getTipoFormulario()));

        // Tipo de responsable
        detalleCambioEstadoDTO.setResponsableFormulario(ResponsableFormularioHelper.toLevel1DTO(detalleCambioEstado
                .getResponsableFormulario()));

        // Informacion del unificacion responsable el cual contiene la informacion de una persona y de un organismo de transito
        detalleCambioEstadoDTO.getResponsableFormulario().setUnificacionResponsableDTO(
                UnificacionResponsableHelper.toLevel1DTO(detalleCambioEstado.getResponsableFormulario()
                        .getUnificacionResponsable()));

        // Infromacion persona
        if (detalleCambioEstado.getResponsableFormulario().getUnificacionResponsable().getPersona() != null) {

            detalleCambioEstadoDTO
                    .getResponsableFormulario()
                    .getUnificacionResponsableDTO()
                    .setPersona(
                            iRFachadaPersona.consultarPersona(
                                    PersonaHelper.toLevel0DTO(detalleCambioEstado.getResponsableFormulario()
                                            .getUnificacionResponsable().getPersona())).get(0));
        }

        return detalleCambioEstadoDTO;
    }

    // Commented unused Sergio Torres 17/dic/2015
    // @Override
    // public List<SeguimientoFormularioDTO> consultarFormulariosDetalleCambio(Long idDetalleCambioEstado) {
    // List<SeguimientoFormulario> resultados = consultarSeguimientosCambioEstado(idDetalleCambioEstado);
    // List<SeguimientoFormularioDTO> listDto = SeguimientoFormularioHelper.toListLevel1DTO(resultados);
    // return listDto;
    // }

    // Commented unused Sergio Torres 17/dic/2015
    // /**
    // * Consulta los seguimientos de formulario asociados a un detalle cambio estado
    // *
    // * @param idDetalleCambioEstado
    // * Id de detalle cambio estado para el filtro
    // * @return Lista de SeguimientoFormulario relacionados a un detalle cambio estado
    // */
    // private List<SeguimientoFormulario> consultarSeguimientosCambioEstado(Long idDetalleCambioEstado) {
    // StringBuilder sql = new StringBuilder(
    // "SELECT segForm FROM SeguimientoFormulario as segForm JOIN FETCH segForm.formulario f WHERE segForm.detalleCambioEstado.id=:idDetalleCambioEstado");
    // TypedQuery<SeguimientoFormulario> query = em.createQuery(sql.toString(), SeguimientoFormulario.class);
    // query.setParameter(SeguimientoFormulario.SQ_SEGUIMIENTO_BY_ESTADO_ACTUAL_FORMULARIO_P1, idDetalleCambioEstado);
    // List<SeguimientoFormulario> resultados = query.getResultList();
    // return resultados;
    // }

    // Commented unused Sergio Torres 17/dic/2015
    // /**
    // * Consulta los formulario asociados a un detalle de cambio de estado que no se encuentre en un estado particular
    // *
    // * @param idDetalleCambioEstado
    // * Id del detalle cambio estado para el cual se consultaran los formularios
    // * @param idEstadoFormulario
    // * Id del estado de formulario en el cual no deben estar los formulario a consultar
    // * @return Formularios del cambio estado enviado que no se encuentren en el estado indicado
    // */
    // private List<Formulario> consultarFormularioCambioEstado(Long idDetalleCambioEstado, Integer idEstadoFormulario) {
    // StringBuilder sql = new StringBuilder(
    // "SELECT f FROM SeguimientoFormulario s JOIN s.formulario f WHERE s.detalleCambioEstado.id = :idDetalleCambio AND f.estadoFormulario.id != :idEstado");
    // TypedQuery<Formulario> query = em.createQuery(sql.toString(), Formulario.class);
    // query.setParameter("idDetalleCambio", idDetalleCambioEstado);
    // query.setParameter("idEstado", idEstadoFormulario);
    // List<Formulario> resultados = query.getResultList();
    // return resultados;
    // }

    // Commented unused Sergio Torres 17/dic/2015
    // private SeguimientoFormulario consultarUltimoSeguimientoFormulario(Long idFormulario) {
    // TypedQuery<SeguimientoFormulario> query = em.createNamedQuery(SeguimientoFormulario.SQ_MAX_BY_FORMULARIO,
    // SeguimientoFormulario.class);
    // query.setParameter("idFormulario", idFormulario);
    // List<SeguimientoFormulario> resultado = query.getResultList();
    //
    // if (resultado != null && !resultado.isEmpty()) {
    // return resultado.get(0);
    // } else {
    // return null;
    // }
    //
    // }

    // Commented unused Sergio Torres 17/dic/2015
    // @Override
    // public void eliminarCambioEstadoFormulario(Long idDetalleCambioEstado) throws CirculemosNegocioException {
    // // TODO revisar si este servicio es requerido en la nueva version del módulo
    // /*
    // * DetalleCambioEstado entidadDetalle = em.find(DetalleCambioEstado.class, idDetalleCambioEstado); EstadoFormulario estadoEvaluar =
    // * entidadDetalle.getEstadoFormulario(); List<Formulario> formulariosEstadosDiferente = consultarFormularioCambioEstado(idDetalleCambioEstado,
    // * estadoEvaluar.getId()); if (formulariosEstadosDiferente != null && !formulariosEstadosDiferente.isEmpty()) { // Lanzar error NO se puede
    // * eliminar porque hay formularios que han sufrido otros cambios de estado throw new
    // * CirculemosNegocioException(CambioEstadosFormularios.NUM_001010); } // Todos los formularios estan en el estado indicado y se puede eliminar
    // * el cambio de estado List<SeguimientoFormulario> seguimientos = consultarSeguimientosCambioEstado(idDetalleCambioEstado);// OJO Traer el
    // * formulario en el fetch // OJO Hacer delete de todos los seguimientos donde is detalle sea el de eliminar ANTES DEL CICLO final
    // * GenericDao<SeguimientoFormulario> seguimientoDao = new GenericDao<>(SeguimientoFormulario.class, em); Map<String, Object> parametros = new
    // * HashMap<>(); parametros.put(SQ_DEL_SEGUIMIENTO_BY_DETALLE_CAMBIO_P1, idDetalleCambioEstado); final Query updateSeguimiento =
    // * seguimientoDao.buildQuery(SQ_DEL_SEGUIMIENTO_BY_DETALLE_CAMBIO, parametros); updateSeguimiento.executeUpdate(); for (SeguimientoFormulario
    // * seguimientoFormulario : seguimientos) {
    // *
    // * // actualizarFormularioUltimoSeguimiento(seguimientoFormulario.getFormulario(), true); } // Eliminar el detalle_cambio_estado con sus
    // * detalle_cambio_estado_rango em.remove(entidadDetalle);
    // */
    // }

    /**
     * Actualiza el estado, tipo de asignación y responsable del formulario, al del ultimo seguimiento
     * 
     * @param formularioActualizar
     *            Formulario a actualizar
     * @param validarResponsable
     *            Indica si se deben hacer validaciones con respecto al responsable que tiene el ultimo seguimiento del formulario
     * @throws CirculemosNegocioException
     *             NUM_001015(Uno de los responsables de formularios asignados al estado anterior, no cumple con los datos necesarios según el \"Tipo
     *             de Asignación\")<br>
     *             NUM_001016(Uno de los responsables de formularios asignados al estado anterior, NO esta activo)<br>
     */
    /*
     * public void actualizarFormularioUltimoSeguimiento(Formulario formularioActualizar, boolean validarResponsable) throws
     * CirculemosNegocioException { SeguimientoFormulario seguimientoAnterior = consultarUltimoSeguimientoFormulario(formularioActualizar.getId()); //
     * Formulario formularioActualizar = em.find(Formulario.class, idFormulario);// OJO No hacer esta consulta ya que el formulario viene en el //
     * fetch del seguimiento if (validarResponsable && seguimientoAnterior != null) { validarResponsable(seguimientoAnterior); } if
     * (seguimientoAnterior != null) { // Hay q dejar el formulario con los datos de ese seguimiento anterior
     * formularioActualizar.setTipoAsignacion(seguimientoAnterior.getTipoAsignacion());
     * formularioActualizar.setEstadoFormulario(seguimientoAnterior.getEstadoFormulario());
     * formularioActualizar.setResponsableFormulario(seguimientoAnterior.getResponsableFormulario()); } else { // Hay q dejar el formulario en
     * pendiente por asignar formularioActualizar.setEstadoFormulario(new EstadoFormulario(EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR .getValue()));
     * formularioActualizar.setResponsableFormulario(null); formularioActualizar.setTipoAsignacion(null); } em.merge(formularioActualizar);
     * 
     * }
     */

    /*
     * private void validarResponsable(SeguimientoFormulario seguimiento) throws CirculemosNegocioException { ResponsableFormulario responsable =
     * seguimiento.getResponsableFormulario(); if (responsable != null && responsable.getId() != null) { if
     * (!responsable.getEstadoResponsable().getId().equals(EnumEstadoResponsable.ACTIVO.getValue()) || (responsable.getFechaInicioServicio() != null
     * && responsable.getFechaFinServicio() == null && UtilFecha .resetTime(responsable.getFechaInicioServicio()).compareTo(
     * UtilFecha.resetTime(seguimiento.getFechaMovimiento())) >= 0) || (responsable.getFechaInicioServicio() != null &&
     * responsable.getFechaFinServicio() != null && seguimiento.getFechaMovimiento() != null && UtilFecha.betweenDate(
     * responsable.getFechaInicioServicio(), responsable.getFechaFinServicio(), seguimiento.getFechaMovimiento()))) { throw new
     * CirculemosNegocioException(CambioEstadosFormularios.NUM_001016); } else if (seguimiento.getTipoAsignacion() != null &&
     * seguimiento.getTipoAsignacion().getId() != null) { // validar tipo de asignacion if
     * (seguimiento.getTipoAsignacion().getId().equals(EnumTipoAsignacion.AGENTE.getValue()) && responsable.getPlaca() == null) { throw new
     * CirculemosNegocioException(CambioEstadosFormularios.NUM_001015); } else if (responsable.getPlaca() != null &&
     * (seguimiento.getTipoAsignacion().getId().equals(EnumTipoAsignacion.EMPRESA.getValue()) || seguimiento
     * .getTipoAsignacion().getId().equals(EnumTipoAsignacion.RADICADOR.getValue()))) { throw new
     * CirculemosNegocioException(CambioEstadosFormularios.NUM_001015); } } } else { throw new
     * CirculemosNegocioException(CambioEstadosFormularios.NUM_001017); } }
     */

    /**
     * Obtiene la configuracion de estados para un tipo de formulario particular
     * 
     * @param idTipoFormulario
     *            Id del tipo de formulario para el cual se desea obtener la configuracion
     * @return Mapa K:Id del estado formulario origen, V: Listado de Ids de estados destino, configurados para el estado origen
     */
    private Map<Integer, List<Integer>> obtenerConfiguracionEstadosTipoForm(Integer idTipoFormulario) {
        TypedQuery<RelacionEstados> query = em.createNamedQuery(RelacionEstados.SQ_FIND_BY_TIPO_FORMULARIO,
                RelacionEstados.class);
        Map<Integer, List<Integer>> configuracionEstados = new TreeMap<Integer, List<Integer>>();
        query.setParameter("idTipoFormulario", idTipoFormulario);
        List<RelacionEstados> resultados = query.getResultList();
        if (resultados.size() > 0) {
            for (RelacionEstados relacionEstados : resultados) {
                Integer idEstadoActual = relacionEstados.getEstadoFormularioActual().getId();
                Integer idEstadoSiguiente = relacionEstados.getEstadoFormularioSiguiente().getId();
                if (configuracionEstados.containsKey(idEstadoActual)) {
                    configuracionEstados.get(idEstadoActual).add(idEstadoSiguiente);
                } else {
                    List<Integer> estadosSiguientes = new ArrayList<Integer>();
                    estadosSiguientes.add(idEstadoSiguiente);
                    configuracionEstados.put(idEstadoActual, estadosSiguientes);
                }
            }
        }
        return configuracionEstados;
    }

    private boolean validarConfiguracionEstados(Map<Integer, List<Integer>> configuracionEstados,
            Integer idEstadoActual, Integer idEstadoSiguiente) {
        List<Integer> estadosSiguientes = configuracionEstados.get(idEstadoActual);
        if (estadosSiguientes != null && estadosSiguientes.contains(idEstadoSiguiente)) {
            return true;
        }
        return false;
    }

    /**
     * Valida si el grupo de formularios enviados como parametro, estan en el estado enviado como parametro "idEstadoActual"
     * 
     * @param formularios
     *            Numero de los formularios a buscar
     * @param tipoFormularioDTO
     *            Tipo de formulario de los formularios
     * @return Lista de formularios, con la información almacenada para cada uno
     * @throws CirculemosNegocioException
     */
    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private Future<List<Formulario>> consultarFormularios(List<FormularioDTO> formularios,
            TipoFormularioDTO tipoFormularioDTO, int i) throws CirculemosNegocioException {
        // Hay que consultar cada uno de los formulario, si no existe es porque esta "Pendiente por asignar"
        List<Formulario> formulariosEntidades = new ArrayList<Formulario>();
        TypedQuery<Formulario> query = em.createNamedQuery(Formulario.SQ_FORM_BY_NUMERO_TIPO_FORM, Formulario.class);
        query.setParameter("idTipoFormulario", tipoFormularioDTO.getId());

        for (FormularioDTO formularioDTO : formularios) {
            query.setParameter("numeroFormulario", formularioDTO.getNumeroFormulario());
            Formulario formulario = null;
            formulario = query.getSingleResult();
            if (formulario == null) {
                formulario = new Formulario();
                formulario.setNumeroFormulario(formularioDTO.getNumeroFormulario());
                formulario.setTipoFormulario(new TipoFormulario(tipoFormularioDTO.getId()));
            }
            formulariosEntidades.add(formulario);
        }

        return new AsyncResult<>(formulariosEntidades);
    }

    @Override
    public void actualizarDetalleCambioEstado(DetalleCambioEstadoDTO detalleCambioEstadoDTO,
            ArchivoTransportableDTO archivoTransportableDTO) throws CirculemosNegocioException {
        logger.debug("FormularioEJB::actualizarDetalleCambioEstado");

        checkNotNull(detalleCambioEstadoDTO, "Objeto detalleCambioEstadoDTO es vacio");

        DocumentoFormularioDTO documentoFormularioDTO = null;
        DetalleCambioEstado detalleActualizar = em.find(DetalleCambioEstado.class, detalleCambioEstadoDTO.getId());
        detalleActualizar = DetalleCambioEstadoHelperExtend.toLevel1Entity(detalleCambioEstadoDTO, detalleActualizar);

        try {
            if (archivoTransportableDTO != null) {
                if (detalleActualizar.getDocumentoFormulario() != null
                        && detalleActualizar.getDocumentoFormulario().getIdDocumento() != null) {
                    documentosEjb.actualizarDocumento(detalleActualizar.getDocumentoFormulario().getIdDocumento(),
                            archivoTransportableDTO);
                } else {
                    documentoFormularioDTO = new DocumentoFormularioDTO();
                    documentoFormularioDTO.setIdDocumento(documentosEjb.registrarDocumento(
                            EnumCategoriaDocumento.SOPORTE_CAMBIO_ESTADO_FORMULARIOS, archivoTransportableDTO));
                    detalleCambioEstadoDTO.setDocumentoFormulario(documentoFormularioDTO);
                    detalleActualizar = DetalleCambioEstadoHelperExtend.toLevel1Entity(detalleCambioEstadoDTO,
                            detalleActualizar);
                }
            }
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(e.getErrorInfo());
        }
        em.merge(detalleActualizar);
    }

    // Commented unused Sergio Torres 17/dic/2015
    // @Override
    // public List<SeguimientoFormularioDTO> consultarFormulario(ConsultaFormularioDTO consultaFormulario) {
    // Integer idTipoFormulario = checkNotNull(consultaFormulario.getIdTipoFormulario(),
    // "Es necesario el tipo de formulario para llevar a cabo la consulta");
    // List<Integer> lstIdEstadoFormulario = checkNotNull(consultaFormulario.getLstIdEstadoFormulario(),
    // "Es necesario seleccionar al menos un estado del formulario para llevar a cabo la consulta");
    // Boolean empty = lstIdEstadoFormulario.isEmpty();
    // if (empty) {
    // empty = null;
    // checkNotNull(empty,
    // "Es necesario seleccionar al menos un estado del formulario para llevar a cabo la consulta");
    // }
    //
    // Date fechaInicial = checkNotNull(consultaFormulario.getFechaInicial(),
    // "Es necesaria la fecha incial del rango para llevar a cabo la consulta");
    // Date fechaFinal = checkNotNull(consultaFormulario.getFechaFinal(),
    // "Es necesaria la fecha final del rango para llevar a cabo la consulta");
    // StringBuilder sql = new StringBuilder(CambioEstadosFormularioSQL.SQL_BASE_FORMULARIO);
    // Map<String, Object> filtros = new HashMap<>();
    // filtros.put("id_tipo_formulario", idTipoFormulario);
    //
    // if (!empty) {
    // sql.append(" AND (");
    // for (int i = 0; i < lstIdEstadoFormulario.size(); i++) {
    // sql.append(" ef.id_estado_formulario = :id_est_form" + i + " OR");
    // filtros.put("id_est_form" + i, lstIdEstadoFormulario.get(i));
    // }
    // sql.replace(sql.lastIndexOf("OR"), sql.lastIndexOf("OR") + 2, ")");
    // }
    //
    // sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_RANGO_FECHA_INICIAL_FINAL);
    // filtros.put("fecha_inicio", fechaInicial);
    // filtros.put("fecha_fin", fechaFinal);
    //
    // if (consultaFormulario.getIdTipoDocumento() != null
    // && StringUtils.isNotBlank(consultaFormulario.getNumeroDocumentoIdentidad())) {
    // sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_DOCUMENTO_IDENTIFICACION);
    // filtros.put("id_tipo_identificacion", consultaFormulario.getIdTipoDocumento());
    // filtros.put("numero_identificacion", consultaFormulario.getNumeroDocumentoIdentidad());
    // }
    //
    // if (StringUtils.isNotBlank(consultaFormulario.getNumeroPlacaResponsable())) {
    // sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_PLACA_RESPONSABLE);
    // filtros.put("placa", consultaFormulario.getNumeroPlacaResponsable());
    // }
    //
    // Query nativeQuery = em.createNativeQuery(sql.toString());
    // for (Iterator<String> iterator = filtros.keySet().iterator(); iterator.hasNext();) {
    // String filtro = iterator.next();
    // nativeQuery.setParameter(filtro, filtros.get(filtro));
    // }
    //
    // nativeQuery.setFirstResult(consultaFormulario.getFilaInicial());
    // nativeQuery.setMaxResults(consultaFormulario.getMaxResultados());
    //
    // List<SeguimientoFormularioDTO> formuariosConsultados = new ArrayList<>(0);
    // @SuppressWarnings("unchecked")
    // List<Object[]> resultado = nativeQuery.getResultList();
    //
    // for (Object[] cursor : resultado) {
    // FormularioDTO formularioDTO = new FormularioDTO();
    // SeguimientoFormularioDTO seguimientoFormularioDTO = new SeguimientoFormularioDTO();
    // // seguimientoFormularioDTO.setTipoAsignacion(new TipoAsignacionFormularioDTO((Integer) cursor[0]));
    // // seguimientoFormularioDTO.getTipoAsignacion().setNombre((String) cursor[1]);
    // formularioDTO.setTipoFormulario(new TipoFormularioDTO((Integer) cursor[2]));
    // formularioDTO.getTipoFormulario().setNombre((String) cursor[3]);
    // ResponsableFormularioDTO responsableFormulario = new ResponsableFormularioDTO(
    // ((BigInteger) cursor[4]).longValue());
    // seguimientoFormularioDTO.setResponsableFormulario(responsableFormulario);
    //
    // TipoIdentificacionPersonaDTO tipoDocumento = new TipoIdentificacionPersonaDTO((Integer) cursor[6]);
    // tipoDocumento.setNombre((String) cursor[5]);
    // PersonaDTO persona = null;
    // if (tipoDocumento.getId().equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor())) {
    // PersonaJuridicaDTO personaJuridicaDTO = new PersonaJuridicaDTO();
    // personaJuridicaDTO.setNombreComercial((String) cursor[11]);
    // persona = personaJuridicaDTO;
    // } else {
    // persona = new PersonaDTO();
    // }
    // persona.setTipoIdentificacion(tipoDocumento);
    // persona.setNombre1((String) cursor[7]);
    // persona.setNombre2((String) cursor[8]);
    // persona.setApellido1((String) cursor[9]);
    // persona.setApellido2((String) cursor[10]);
    // persona.setNombreCompleto(PersonaHelperExtend.construirNombrePersona(persona));
    // // seguimientoFormularioDTO.getResponsableFormulario().setAreaResponsable(
    // // new AreaResponsableDTO((Integer) cursor[12]));
    // // seguimientoFormularioDTO.getResponsableFormulario().getAreaResponsable().setNombre((String) cursor[13]);
    //
    // persona.setNumeroIdentificacion((String) cursor[14]);
    //
    // // seguimientoFormularioDTO.getResponsableFormulario().setPlaca((String) cursor[15]);
    // //
    // // seguimientoFormularioDTO.getResponsableFormulario().setPersona(persona);
    //
    // seguimientoFormularioDTO.setFechaMovimiento((Date) cursor[16]);
    //
    // formularioDTO.setNumeroFormulario((String) cursor[17]);
    //
    // seguimientoFormularioDTO.setEstadoFormulario(new EstadoFormularioDTO((Integer) cursor[18]));
    //
    // seguimientoFormularioDTO.getEstadoFormulario().setNombre((String) cursor[19]);
    //
    // formularioDTO.setId(((BigInteger) cursor[20]).longValue());
    //
    // seguimientoFormularioDTO.setFormulario(formularioDTO);
    //
    // formuariosConsultados.add(seguimientoFormularioDTO);
    //
    // }
    //
    // return formuariosConsultados;
    // }

    // Commented unused Sergio Torres 17/dic/2015
    // @Override
    // public int contarConsultaFormulario(ConsultaFormularioDTO consultaFormulario) {
    // Integer idTipoFormulario = checkNotNull(consultaFormulario.getIdTipoFormulario(),
    // "Es necesario el tipo de formulario para llevar a cabo la consulta");
    // List<Integer> lstIdEstadoFormulario = checkNotNull(consultaFormulario.getLstIdEstadoFormulario(),
    // "Es necesario seleccionar al menos un estado del formulario para llevar a cabo la consulta");
    // Boolean empty = lstIdEstadoFormulario.isEmpty();
    // if (empty) {
    // empty = null;
    // checkNotNull(empty,
    // "Es necesario seleccionar al menos un estado del formulario para llevar a cabo la consulta");
    // }
    //
    // Date fechaInicial = checkNotNull(consultaFormulario.getFechaInicial(),
    // "Es necesaria la fecha incial del rango para llevar a cabo la consulta");
    // Date fechaFinal = checkNotNull(consultaFormulario.getFechaFinal(),
    // "Es necesaria la fecha final del rango para llevar a cabo la consulta");
    // StringBuilder sql = new StringBuilder(CambioEstadosFormularioSQL.SQL_BASE_FORMULARIO_COUNT);
    // Map<String, Object> filtros = new HashMap<>();
    // filtros.put("id_tipo_formulario", idTipoFormulario);
    //
    // if (!empty) {
    // sql.append(" AND (");
    // for (int i = 0; i < lstIdEstadoFormulario.size(); i++) {
    // sql.append(" ef.id_estado_formulario = :id_est_form" + i + " OR");
    // filtros.put("id_est_form" + i, lstIdEstadoFormulario.get(i));
    // }
    // sql.replace(sql.lastIndexOf("OR"), sql.lastIndexOf("OR") + 2, ")");
    // }
    //
    // sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_RANGO_FECHA_INICIAL_FINAL);
    // filtros.put("fecha_inicio", fechaInicial);
    // filtros.put("fecha_fin", fechaFinal);
    //
    // if (consultaFormulario.getIdTipoDocumento() != null
    // && StringUtils.isNotBlank(consultaFormulario.getNumeroDocumentoIdentidad())) {
    // sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_DOCUMENTO_IDENTIFICACION);
    // filtros.put("id_tipo_identificacion", consultaFormulario.getIdTipoDocumento());
    // filtros.put("numero_identificacion", consultaFormulario.getNumeroDocumentoIdentidad());
    // }
    //
    // if (StringUtils.isNotBlank(consultaFormulario.getNumeroPlacaResponsable())) {
    // sql.append(CambioEstadosFormularioSQL.SQL_CONDICION_PLACA_RESPONSABLE);
    // filtros.put("placa", consultaFormulario.getNumeroPlacaResponsable());
    // }
    //
    // Query nativeQuery = em.createNativeQuery(sql.toString());
    // for (Iterator<String> iterator = filtros.keySet().iterator(); iterator.hasNext();) {
    // String filtro = iterator.next();
    // nativeQuery.setParameter(filtro, filtros.get(filtro));
    // }
    //
    // Integer countFormularios = ((BigInteger) nativeQuery.getSingleResult()).intValue();
    // return countFormularios;
    // }

    @Override
    public FormularioDTO consultarSeguimientoFormulario(ConsultaSeguimientoFormularioDTO consultaSeguimientoFormulario) {
        Integer idTipoFormulario = consultaSeguimientoFormulario.getTipoFormularioDTO().getId();
        String numeroFormulario = consultaSeguimientoFormulario.getNumeroFormulario();

        checkNotNull(idTipoFormulario, "Filtro identificador del tipo de formulario (idTipoFormulario) es vacio");
        checkNotNull(numeroFormulario, "Filtro identificador del numero de formulario (numeroFormulario) es vacio");

        FormularioDTO formularioDTO = null;

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT sf FROM SeguimientoFormulario AS sf");
        jpql.append(" JOIN FETCH sf.formulario AS fm");
        jpql.append(" JOIN FETCH fm.tipoFormulario AS tf");
        jpql.append(" JOIN FETCH fm.codigoOrganismo AS co");
        jpql.append(" JOIN FETCH sf.detalleCambioEstado AS dce");
        jpql.append(" LEFT JOIN FETCH dce.documentoFormulario AS doc");
        jpql.append(" JOIN FETCH dce.causalCambioEstado");
        jpql.append(" JOIN dce.responsableFormulario AS rf");
        jpql.append(" JOIN FETCH dce.estadoFormulario AS ef");
        jpql.append(" WHERE 1=1");
        if (consultaSeguimientoFormulario != null) {
            if (consultaSeguimientoFormulario.getTipoFormularioDTO() != null
                    && consultaSeguimientoFormulario.getTipoFormularioDTO().getId() != null) {
                jpql.append(" AND tf.id = :pIdTipFor");
                filtros.put("pIdTipFor", consultaSeguimientoFormulario.getTipoFormularioDTO().getId());
            }

            if (consultaSeguimientoFormulario.getNumeroFormulario() != null) {
                jpql.append(" AND fm.numeroFormulario = :pNumeroFormulario");
                filtros.put("pNumeroFormulario", consultaSeguimientoFormulario.getNumeroFormulario());
            }

            if (consultaSeguimientoFormulario.getOrganismoTransito() != null
                    && consultaSeguimientoFormulario.getOrganismoTransito().getCodigoOrganismo() != null) {
                jpql.append(" AND co.codigoOrganismo = :pIdCodOrg");
                filtros.put("pIdCodOrg", consultaSeguimientoFormulario.getOrganismoTransito().getCodigoOrganismo());
            }
        }

        jpql.append(" ORDER BY sf.fechaAplicacionEstado DESC");

        GenericDao<SeguimientoFormulario> genericDao = new GenericDao<>(SeguimientoFormulario.class, em);
        List<SeguimientoFormulario> result = genericDao.buildAndExecuteQuery(jpql, filtros);

        List<DetalleCambioEstadoDTO> lstSeguimiento = new ArrayList<DetalleCambioEstadoDTO>(1);

        RangoFormulario rangoFormulario = null;
        if (result != null && !result.isEmpty()) {
            Formulario formulario = result.get(0).getFormulario();
            formularioDTO = FormularioHelperExtend.toLevel1DTO(formulario);

            for (SeguimientoFormulario seguimientoFormulario : result) {
                DetalleCambioEstadoDTO detalleCambioEstadoDTO = DetalleCambioEstadoHelperExtend
                        .toLevel1DTO(seguimientoFormulario.getDetalleCambioEstado());

                UnificacionResponsableDTO unificacionResponsable = new UnificacionResponsableDTO();
                unificacionResponsable.setResponsableFormulario(new ResponsableFormularioDTO(seguimientoFormulario
                        .getDetalleCambioEstado().getResponsableFormulario().getId()));
                List<UnificacionResponsableDTO> responsableFormulario = administracionFormulariosEJB
                        .consultarResponsablesFormularios(unificacionResponsable);
                detalleCambioEstadoDTO.getResponsableFormulario().setUnificacionResponsableDTO(
                        responsableFormulario.get(0));

                lstSeguimiento.add(detalleCambioEstadoDTO);
            }

        } else {
            rangoFormulario = consultarRangoNumeroFormulario(consultaSeguimientoFormulario.getOrganismoTransito()
                    .getCodigoOrganismo(), consultaSeguimientoFormulario.getNumeroFormulario(),
                    consultaSeguimientoFormulario.getTipoFormularioDTO().getId(), null);
        }
        if (rangoFormulario != null && formularioDTO == null) {
            formularioDTO = new FormularioDTO();
            formularioDTO.setTipoFormulario(TipoFormularioHelper.toLevel0DTO(rangoFormulario.getTipoFormulario()));
            formularioDTO.setNumeroFormulario(consultaSeguimientoFormulario.getNumeroFormulario());
            formularioDTO.setEstadoFormulario(new EstadoFormularioDTO(EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR
                    .getValue()));
            formularioDTO.getEstadoFormulario().setNombre(EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR.getDescripcion());

        } else if (formularioDTO == null) {
            return formularioDTO;
        }

        DetalleCambioEstadoDTO seguimientoFormularioDTO = new DetalleCambioEstadoDTO();
        seguimientoFormularioDTO.setId(-1L);
        EstadoFormularioDTO estadoFormularioDTO = new EstadoFormularioDTO();
        estadoFormularioDTO.setId(EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR.getValue());
        estadoFormularioDTO.setNombre(EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR.getDescripcion());
        seguimientoFormularioDTO.setEstadoFormulario(estadoFormularioDTO);
        lstSeguimiento.add(seguimientoFormularioDTO);

        formularioDTO.setLstDetalleCambiosEstados(lstSeguimiento);

        return formularioDTO;
    }

    // Commented unused Sergio Torres 17/dic/2015
    // @Override
    // public List<FormularioDTO> consultarSeguimientoFormulario(
    // ConsultaSeguimientoAgrupadoFormularioDTO consultaSeguimientoAgrupadoFormularioDTO)
    // throws CirculemosNegocioException {
    // FormularioEJB.logger.debug("FormularioEJB.consultarSeguimientoFormulario");
    //
    // List<FormularioDTO> formularioDTOs = null;
    //
    // /*
    // * Validacion filtros obligatorios
    // */
    // // if (consultaSeguimientoAgrupadoFormularioDTO.getEstadoFormulario() == null
    // // && consultaSeguimientoAgrupadoFormularioDTO.getEstadoFormulario().isEmpty()) {
    // // throw new CirculemosNegocioException(ErrorFormularios.FormulariosEstados.NUM_008001);
    // // }
    //
    // StringBuilder jpql = new StringBuilder();
    //
    // jpql.append("SELECT f FROM Formulario f");
    // jpql.append(" WHERE 1=1");
    //
    // /*
    // * Validacion filtros obligatorios
    // */
    // // Tipo de formulario
    // if (consultaSeguimientoAgrupadoFormularioDTO.getTipoResponsableFormularioDTO() != null) {
    // if (consultaSeguimientoAgrupadoFormularioDTO.getTipoResponsableFormularioDTO().getId() != null) {
    // jpql.append("AND f.responsableFormulario.tipoResponsable.id = :idTipoResponsable");
    // }
    // }
    //
    // // Tipo formulario
    // if (consultaSeguimientoAgrupadoFormularioDTO.getTipoFormularioDTO().getId() != null) {
    // jpql.append(" AND f.tipoFormulario.id = :idFormulario");
    // }
    //
    // // Estados formulario
    // // if (consultaSeguimientoAgrupadoFormularioDTO.getEstadoFormulario() != null
    // // && !consultaSeguimientoAgrupadoFormularioDTO.getEstadoFormulario().isEmpty()) {
    // // StringBuilder estados = new StringBuilder();
    // // for (Object integers : consultaSeguimientoAgrupadoFormularioDTO.getEstadoFormulario()) {
    // // estados.append(integers);
    // // estados.append(",");
    // // }
    // // jpql.append(" AND f.estadoFormulario.id IN(" + estados.substring(0, estados.length() - 1) + ")");
    // // }
    //
    // Query query = em.createQuery(jpql.toString());
    //
    // /*
    // * Parametros
    // */
    // // Tipo de formulario
    // if (consultaSeguimientoAgrupadoFormularioDTO.getTipoResponsableFormularioDTO() != null) {
    // if (consultaSeguimientoAgrupadoFormularioDTO.getTipoResponsableFormularioDTO().getId() != null) {
    // query.setParameter("idTipoResponsable", consultaSeguimientoAgrupadoFormularioDTO
    // .getTipoResponsableFormularioDTO().getId());
    // }
    // }
    // if (consultaSeguimientoAgrupadoFormularioDTO.getTipoFormularioDTO().getId() != null) {
    // query.setParameter("idFormulario", consultaSeguimientoAgrupadoFormularioDTO.getTipoFormularioDTO().getId());
    // }
    //
    // @SuppressWarnings("unchecked")
    // List<Formulario> formularios = query.getResultList();
    // if (formularios != null && !formularios.isEmpty()) {
    // formularioDTOs = new ArrayList<FormularioDTO>();
    // for (Formulario formulario : formularios) {
    // FormularioDTO formularioDTO = new FormularioDTO();
    // formularioDTO = FormularioHelper.toLevel1DTO(formulario);
    // formularioDTO.setResponsableFormulario(ResponsableFormularioHelper.toLevel1DTO(formulario
    // .getResponsableFormulario()));
    // formularioDTOs.add(formularioDTO);
    // }
    // }
    // return formularioDTOs;
    // }

    @Override
    public int cambiarEstadoFormularios(CambioEstadoFormularioDTO cambioEstadoFormularioDTO)
            throws CirculemosNegocioException {
        checkNotNull(cambioEstadoFormularioDTO, "Se esperaba dto de entrada");

        Long idDetalleCambioEstado = cambioEstadoFormularioDTO.getIdDetalleCambioEstado();
        DetalleCambioEstado detalleCambioEstado = em.find(DetalleCambioEstado.class, idDetalleCambioEstado);

        RangoFormulario rangoFormulario = detalleCambioEstado.getRangoFormulario();
        RangoFormularioDTO objRangoFormularioDTO = RangoFormularioHelper.toLevel1DTO(rangoFormulario);
        DetalleCambioEstadoDTO detalleCambioEstadoDTO = DetalleCambioEstadoHelper.toLevel1DTO(detalleCambioEstado);
        String tramaNumeracion = rangoFormulario.getNumeracion().getTrama();

        // Validar que los rangos cuyos formularios se van a cambiar de estado tengan formato valido con respecto al rango general del
        // DetalleCambioEstadoDTO y que el numero final de cada rango sea mayor o igual al inicial
        List<RangoDTO> rangosDTO = cambioEstadoFormularioDTO.getListRangoDTO();
        for (RangoDTO rangoDTO : rangosDTO) {
            if (!obtenerTramaNumeroFormulario(rangoDTO.getNumeroInicial()).equals(tramaNumeracion)
                    || !obtenerTramaNumeroFormulario(rangoDTO.getNumeroFinal()).equals(tramaNumeracion)) {
                throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001003);
            }
        }
        for (RangoDTO rangoDTO : rangosDTO) {
            if (!esNumIniMenorIgualNumFin(rangoDTO.getNumeroInicial(), rangoDTO.getNumeroFinal())) {
                throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001004);
            }
        }

        // Validar que los rangos esten contenidos en el rango del DetalleCambioEstadoDTO si no Presenta un mensaje informando que los intervalos
        // digitados NO se encuentran dentro del intervalo seleccionado.

        // 2. Generar todos los numeros del rango
        NumeracionFormulario numeracion = rangoFormulario.getNumeracion();
        NumeracionFormularioDTO numeracionDTO = NumeracionFormularioHelper.toLevel1DTO(numeracion);
        List<DetalleNumeracionDTO> detalleNumeracionDTOList = DetalleNumeracionHelper.toListLevel1DTO(numeracion
                .getDetalleNumeracionList());
        numeracionDTO.setDetalleNumeracionList(detalleNumeracionDTOList);

        List<String> numerosDetalleFormularios = listarNumerosRango(detalleCambioEstado.getNumeroInicial(),
                detalleCambioEstado.getNumeroFinal(), numeracionDTO);

        for (RangoDTO rangoDTO : rangosDTO) {
            if (!numerosDetalleFormularios.contains(rangoDTO.getNumeroInicial())
                    || !numerosDetalleFormularios.contains(rangoDTO.getNumeroFinal())) {
                throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001005);
            }
        }

        // Validar que todos los formularios que vienen en los rangos tengan el mismo responsable que viene en el DetalleCambioEstadoDTO sino Presenta
        // un mensaje informando que el responsable de los rangos digitados NO corresponde al responsable cargado
        for (RangoDTO rangoDTO : rangosDTO) {
            TypedQuery<Long> q = em.createNamedQuery(Formulario.SQ_FORM_BY_INTERVALO_RESPONSABLE, Long.class);
            q.setParameter("idRango", rangoFormulario.getId());
            q.setParameter("numeroInicial", rangoDTO.getNumeroInicial());
            q.setParameter("numeroFinal", rangoDTO.getNumeroFinal());
            q.setParameter("idResponsable", detalleCambioEstado.getResponsableFormulario().getId());

            Long cantidad = q.getSingleResult();
            if (cantidad > 0) {
                throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001006);
            }
        }

        // Para todos los formularios de los rangos: Valida que haya una configuracion de estados que permita pasar al estado final enviado en
        // CambioEstadoFormularioDTO, Si existe la configuracion se puede cambiar de estado si no, ese formulario no se afecta
        // Por cada Rango:
        // 1. Consultar los formularios (en tabla formularios) que pertenezcan a ese rango y cuyos estados actuales le permitan pasar al estado final
        // enviado en CambioEstadoFormularioDTO
        RelacionEstadosDTO relacionEstadoConsulta = new RelacionEstadosDTO();
        EstadoFormularioDTO estadoSiguiente = new EstadoFormularioDTO();
        estadoSiguiente.setId(cambioEstadoFormularioDTO.getIdEstadoFinal());
        relacionEstadoConsulta.setEstadoFormularioSiguiente(estadoSiguiente);
        // Tipo de formualrio
        TipoFormularioDTO tipoFormularioDTO = new TipoFormularioDTO();
        tipoFormularioDTO.setId(detalleCambioEstado.getRangoFormulario().getTipoFormulario().getId());
        relacionEstadoConsulta.setTipoFormulario(tipoFormularioDTO);
        // Consultar estados configurados que permiten pasar al estado final
        List<RelacionEstadosDTO> estadosIniciales = administracionFormulariosEJB
                .consultarRelacionesEstados(relacionEstadoConsulta);
        List<Integer> estados = new ArrayList<Integer>();
        if (!estadosIniciales.isEmpty()) {
            for (RelacionEstadosDTO relacionEstadosDTO : estadosIniciales) {
                estados.add(relacionEstadosDTO.getEstadoFormularioActual().getId());
            }
        } else {
            throw new CirculemosNegocioException(CambioEstadosFormularios.NUM_001007);
        }

        List<String> intervalosList = new ArrayList<>();
        List<List<Formulario>> gruposFormularios = new ArrayList<List<Formulario>>();
        for (RangoDTO rangoDTO : rangosDTO) {
            TypedQuery<Formulario> queryForms = em.createNamedQuery(Formulario.SQ_FORM_BY_RANGO_ESTADO,
                    Formulario.class);
            queryForms.setParameter("idRango", rangoFormulario.getId());
            queryForms.setParameter("numeroInicial", rangoDTO.getNumeroInicial());
            queryForms.setParameter("numeroFinal", rangoDTO.getNumeroFinal());
            queryForms.setParameter("idEstados", estados);

            List<Formulario> resultado = queryForms.getResultList();
            Map<String, Formulario> mapFormularios = new HashMap<String, Formulario>();

            for (Formulario formulario : resultado) {
                mapFormularios.put(formulario.getNumeroFormulario(), formulario);
            }

            // 2. Generar todos los numeros del rango
            List<String> numerosFormularios = listarNumerosRango(rangoDTO.getNumeroInicial(),
                    rangoDTO.getNumeroFinal(), numeracionDTO);

            // 3. Construir los grupos de DetalleCambioEstado por consecutividad
            // Por cada numero de formulario generado
            // . validar si esta en la consulta de formularios que pueden pasar al estado final
            // . . si esta
            // . . . Generar/Agregar una entidad DetalleCambioEstado y asociarle a dicho detalle el formulario
            // . . si no esta
            // . . . Crear una nueva entidad DetalleCambioEstado y asociar el formulario a esa nueva entidad
            List<Formulario> listaFormularios = new ArrayList<Formulario>();

            for (String num : numerosFormularios) {
                if (mapFormularios.containsKey(num)) {
                    listaFormularios.add(mapFormularios.get(num));
                } else {
                    if (!listaFormularios.isEmpty()) {
                        gruposFormularios.add(listaFormularios);
                        listaFormularios = new ArrayList<Formulario>();
                    }
                }
            }

            if (!listaFormularios.isEmpty()) {
                gruposFormularios.add(listaFormularios);
            }
        }

        Map<Long, Formulario> cantidadFormularios = new HashMap<Long, Formulario>();
        for (List<Formulario> grupoForm : gruposFormularios) {
            DetalleCambioEstado detallePersistir = new DetalleCambioEstado();

            detallePersistir.setCantidadFormularios(grupoForm.size());
            detallePersistir.setCausalCambioEstado(new CausalCambioEstado(cambioEstadoFormularioDTO
                    .getIdCausalCambioEstado()));
            detallePersistir.setEstadoFormulario(new EstadoFormulario(cambioEstadoFormularioDTO.getIdEstadoFinal()));
            detallePersistir.setFechaAplicacionEstado(cambioEstadoFormularioDTO.getFechaAplicacionEstado());
            detallePersistir.setFechaMovimiento(new Date());
            detallePersistir.setFolio(cambioEstadoFormularioDTO.getFolio());
            detallePersistir.setNumeroFinal(grupoForm.get(grupoForm.size() - 1).getNumeroFormulario());
            detallePersistir.setNumeroInicial(grupoForm.get(0).getNumeroFormulario());
            detallePersistir.setObservaciones(cambioEstadoFormularioDTO.getObservaciones());
            detallePersistir.setRangoFormulario(detalleCambioEstado.getRangoFormulario());
            detallePersistir.setResponsableFormulario(detalleCambioEstado.getResponsableFormulario());
            detallePersistir.setTrama(detalleCambioEstado.getTrama());
            detallePersistir.setEstadoFormulario(new EstadoFormulario(cambioEstadoFormularioDTO.getIdEstadoFinal()));
            detallePersistir.setEsEvento(true);

            /*
             * Registro documento soporte
             */
            if (cambioEstadoFormularioDTO.getArchivoTransportableDTO() != null) {
                DocumentoFormulario documentoFormulario = registrarDocumentoSoporteFormulario(
                        cambioEstadoFormularioDTO.getArchivoTransportableDTO(),
                        cambioEstadoFormularioDTO.getNumeroDocumentoSoporte());
                detallePersistir.setDocumentoFormulario(documentoFormulario);
            }

            em.persist(detallePersistir);
            intervalosList.add("<tr><td>" + detallePersistir.getNumeroInicial() + "</td><td>"
                    + detallePersistir.getNumeroFinal() + "</td></tr>");

            // Insertar los seguimientos de los formularios
            for (Formulario formulario : grupoForm) {

                /*
                 * Contamos la cantidad de formularios a realizar la operacion de cambio de estado seleccionada, sea anulacion o devolucion
                 */
                cantidadFormularios.put(formulario.getId(), formulario);

                SeguimientoFormulario seguimiento = new SeguimientoFormulario();
                seguimiento.setDetalleCambioEstado(detallePersistir);
                seguimiento.setEstadoFormulario(detallePersistir.getEstadoFormulario());
                seguimiento.setFechaAplicacionEstado(detallePersistir.getFechaAplicacionEstado());
                seguimiento.setFechaMovimiento(detallePersistir.getFechaMovimiento());
                seguimiento.setFormulario(formulario);
                seguimiento.setResponsableFormulario(detallePersistir.getResponsableFormulario());
                seguimiento.setUsuarioRegistro(usuarioSesionEJB.getUsuario());
                em.persist(seguimiento);
                formulario.setEstadoFormulario(detallePersistir.getEstadoFormulario());
                em.merge(formulario);
                // TODO HACER PRUEBA DE RENDIMIENTO Y EVALUAR TIEMPO DE HACER ESTE MERGE UNO A UNO O HACER UN MERGE
                // "MASIVO": UPDATE Formulario f SET f.estadoFormulario.id=:idEstadoFinal WHERE f.rangoFormulario.id=
                // :idRango AND
                // f.numeroFormulario>=:numeroInicial AND f.numeroFormulario<=:numeroFinal AND f.estadoFormulario.id IN
                // :idEstados
            }
        }

        /*
         * Actualizamos el rango en el caso de los devueltos para que la disponibilidad de asigancion este siempre con sus cantidades correctas
         */
        if (cambioEstadoFormularioDTO.getIdEstadoFinal().equals(EnumEstadoFomulario.DEVUELTO.getValue())) {
            rangoFormulario.setCantidadDisponible(rangoFormulario.getCantidadDisponible() + cantidadFormularios.size());
            em.merge(rangoFormulario);
        }

        /*
         * Envio de correo informativo
         */
        if (!intervalosList.isEmpty()) {
            EnumEstadoFomulario estadoFinal = Utilidades.buscarElemEnum(EnumEstadoFomulario.class,
                    cambioEstadoFormularioDTO.getIdEstadoFinal());
            OrganismoTransito organismoTransito = em.find(OrganismoTransito.class, objRangoFormularioDTO
                    .getCodigoOrganismo().getCodigoOrganismo());
            TipoFormulario tipoFormulario = em.find(TipoFormulario.class, objRangoFormularioDTO.getTipoFormulario()
                    .getId());
            crearCorreoNotificacionCambioEstado(estadoFinal, organismoTransito.getCodigoOrganismo(),
                    tipoFormulario.getId(), detalleCambioEstadoDTO, intervalosList);
        }
        return cantidadFormularios.size();

    }

    /**
     * Registro documento soporte para un formulario
     * 
     * @return
     * @throws CirculemosNegocioException
     */
    private DocumentoFormulario registrarDocumentoSoporteFormulario(ArchivoTransportableDTO archivoTransportableDTO,
            String numeroDocumentoSoporte) throws CirculemosNegocioException {

        DocumentoFormulario documentoFormulario = new DocumentoFormulario();
        if (archivoTransportableDTO != null) {
            String idDocumento;
            try {
                idDocumento = documentosEjb.registrarDocumento(
                        EnumCategoriaDocumento.SOPORTE_CAMBIO_ESTADO_FORMULARIOS, archivoTransportableDTO);

                documentoFormulario.setIdDocumento(idDocumento);
                if (numeroDocumentoSoporte != null) {
                    documentoFormulario.setNumeroDocumento(numeroDocumentoSoporte);
                }
                em.persist(documentoFormulario);
            } catch (CirculemosAlertaException e) {
                throw new CirculemosNegocioException(e.getErrorInfo());
            }
        }
        return documentoFormulario;
    }

    @Override
    public boolean existeEstadoFormularioOrganismo(String numeroFormulario, Integer idTipoFormulario,
            Integer idCodigoOrganismo, EnumEstadoFomulario... enumEstadoFomulario) {
        FormularioEJB.logger.debug("FormularioEJB.existeAsignacionOrganismo");

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT f FROM Formulario f");
        jpql.append(" WHERE f.numeroFormulario = :numeroFormulario");
        jpql.append(" AND f.estadoFormulario.id IN (:estadosFormulario)");
        jpql.append(" AND f.tipoFormulario.id = :idTipoFormulario");
        jpql.append(" AND f.codigoOrganismo.codigoOrganismo = :idCodigoOrganismo");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Numero de formulario
        query.setParameter("numeroFormulario", numeroFormulario);
        // Estado Formualrio
        List<Integer> idEstadoFomularios = new ArrayList<Integer>();
        for (EnumEstadoFomulario enumEstadoForm : enumEstadoFomulario) {
            idEstadoFomularios.add(enumEstadoForm.getValue());
        }
        query.setParameter("estadosFormulario", idEstadoFomularios);
        // Tipo formulario
        query.setParameter("idTipoFormulario", idTipoFormulario);
        // Organismo de transito
        query.setParameter("idCodigoOrganismo", idCodigoOrganismo);

        @SuppressWarnings("unchecked")
        List<Formulario> formularios = query.getResultList();
        boolean existe = false;
        if (formularios != null && !formularios.isEmpty()) {
            existe = true;
        }

        return existe;
    }

    // Commented unused Sergio torres 17-Dic-2015
    // @Override
    // public boolean existeAsignacion(String numeroFormulario, Integer idTipoFormulario) {
    // FormularioEJB.logger.debug("FormularioEJB.existeAsignacion");
    //
    // StringBuilder jpql = new StringBuilder();
    //
    // jpql.append("SELECT f FROM Formulario f");
    // jpql.append(" WHERE f.numeroFormulario = :numeroFormulario");
    // jpql.append(" AND f.estadoFormulario.id = :estadoFormulario");
    // jpql.append(" AND f.tipoFormulario.id = :idTipoFormulario");
    //
    // Query query = em.createQuery(jpql.toString());
    //
    // /*
    // * Parametros
    // */
    // // Numero de formulario
    // query.setParameter("numeroFormulario", numeroFormulario);
    // // Estado Formualrio
    // query.setParameter("estadoFormulario", EnumEstadoFomulario.ASIGNADO.getValue());
    // // Tipo formulario
    // query.setParameter("idTipoFormulario", idTipoFormulario);
    //
    // @SuppressWarnings("unchecked")
    // List<Formulario> formularios = query.getResultList();
    // boolean existe = false;
    // if (formularios != null && !formularios.isEmpty()) {
    // existe = true;
    // }
    //
    // return existe;
    // }

    @Override
    public boolean existeFormulario(String numeroFormulario, int idTipoFormulario) {
        FormularioEJB.logger.debug("FormularioEJB.existeFormulario(String,int)");
        checkNotNull(idTipoFormulario, "Filtro identificador del tipo de formulario (idTipoFormulario) es vacio");
        checkNotNull(numeroFormulario, "Filtro identificador del numero de formulario (numeroFormulario) es vacio");

        RangoFormulario rangoFormulario = null;
        rangoFormulario = consultarRangoNumeroFormulario(numeroFormulario, idTipoFormulario, null);
        return rangoFormulario != null;
    }

    @Override
    public void cambiarEstadoFormulario(CambioEstadoDTO cambioEstadoDTO) {
        FormularioEJB.logger.debug("FormularioEJB.cambiarEstadoFormulario(CambioEstadoDTO)");

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT f FROM Formulario f");
        jpql.append(" WHERE f.numeroFormulario = :numeroFormulario");
        jpql.append(" AND f.tipoFormulario.id = :idTipoFormulario");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Numero de formulario
        query.setParameter("numeroFormulario", cambioEstadoDTO.getNumeroFormulario());
        // Tipo formulario
        query.setParameter("idTipoFormulario", cambioEstadoDTO.getTipoFormulario());

        Formulario formulario = (Formulario) query.getSingleResult();

        /*
         * llenamos la informacion del detalle cambio estado
         */
        DetalleCambioEstado detalleCambioEstado = new DetalleCambioEstado();
        // Causal cambio estado
        CausalCambioEstado causalCambioEstado = new CausalCambioEstado();
        causalCambioEstado.setId(cambioEstadoDTO.getEnumCausalCambioEstado().getValue());
        detalleCambioEstado.setCausalCambioEstado(causalCambioEstado);
        // Estado formulario
        EstadoFormulario estadoFormulario = new EstadoFormulario();
        estadoFormulario.setId(cambioEstadoDTO.getEnumCausalCambioEstado().getIdEstadoFormulario());
        detalleCambioEstado.setEstadoFormulario(estadoFormulario);
        // Fecha Aplicacion
        detalleCambioEstado.setFechaAplicacionEstado(cambioEstadoDTO.getFechaCambio());
        // fecha movimiento
        detalleCambioEstado.setFechaMovimiento(Calendar.getInstance().getTime());
        // Numero Inicial
        detalleCambioEstado.setNumeroInicial(cambioEstadoDTO.getNumeroFormulario());
        // Numero final
        detalleCambioEstado.setNumeroFinal(cambioEstadoDTO.getNumeroFormulario());
        // Cantidad de formularios
        detalleCambioEstado.setCantidadFormularios(CANTIDAD_FORMULARIOS);
        // Trama
        detalleCambioEstado.setTrama(formulario.getDetalleCambioEstado().getTrama());
        // Responsable formulario
        detalleCambioEstado.setResponsableFormulario(formulario.getDetalleCambioEstado().getResponsableFormulario());
        // Rango formulario
        detalleCambioEstado.setRangoFormulario(formulario.getDetalleCambioEstado().getRangoFormulario());
        // Evento
        detalleCambioEstado.setEsEvento(false);

        em.persist(detalleCambioEstado);

        /*
         * Llenamos la informacion de seguimiento formulario
         */
        SeguimientoFormulario seguimientoFormulario = new SeguimientoFormulario();
        // Formulario
        seguimientoFormulario.setFormulario(formulario);
        // Detalle cambio estado
        seguimientoFormulario.setDetalleCambioEstado(detalleCambioEstado);
        // Estado formulario
        seguimientoFormulario.setEstadoFormulario(detalleCambioEstado.getEstadoFormulario());
        // Fecha Aplicacion estado
        seguimientoFormulario.setFechaAplicacionEstado(detalleCambioEstado.getFechaAplicacionEstado());
        // Fecha movimiento
        seguimientoFormulario.setFechaMovimiento(detalleCambioEstado.getFechaMovimiento());
        // Responsable formulario
        seguimientoFormulario.setResponsableFormulario(detalleCambioEstado.getResponsableFormulario());
        // Usuario registro
        seguimientoFormulario.setUsuarioRegistro(iLSeguridadCirculemos.obtenerUsuarioDto().getUsuario().getLogin());
        em.persist(seguimientoFormulario);

        /*
         * Actualizamos los datos del formulario
         */
        // Estado formulario
        formulario.setEstadoFormulario(estadoFormulario);
        // Detalle cambio estado
        formulario.setDetalleCambioEstado(detalleCambioEstado);
        em.merge(formulario);
    }

    @Override
    public boolean validarFormatoNumeroFormulario(String numeroFormulario, int tipoFormulario)
            throws CirculemosNegocioException {
        FormularioEJB.logger.debug("FormularioEJB.validarFormatoNumeroFormulario(String,int,Date)");
        // Identificar la numeracion a la cual pertenece el rango y asignarla al DTO
        String tramaNumero = obtenerTramaNumeroFormulario(numeroFormulario);
        RangoFormulario rangoFormulario = consultarRangoNumeroFormulario(numeroFormulario, tipoFormulario, tramaNumero);
        if (rangoFormulario == null) {
            // Error no hay numeraciones vigentes para la fecha de autorizacion ingresada
            throw new CirculemosNegocioException(AdministrarRango.ADM_030001);
        }
        if (rangoFormulario.getNumeracion() == null) {
            // Error no hay numeraciones vigentes para la fecha de autorizacion ingresada
            throw new CirculemosNegocioException(AdministrarRango.ADM_030001);
        }

        // Hay una o varias numeraciones en esa vigencia, verificar a cual pertenece el rango enviado, si llega a pertenecer a varias, se toma la
        // numeracion con vigencia mas antigua(validado con analisis) y asignar el id de esta numeracion al DTO
        boolean formatoValido = false;
        String patronObtenido = obtenerExpresionRegularNumeracion(rangoFormulario.getNumeracion());
        if (numeroFormulario.matches(patronObtenido)) {
            // Devolvemos que el numero cumple con el formato de numero de formulario
            formatoValido = true;
        }
        return formatoValido;
    }

    @Override
    public EstadoFormularioAsignacionDTO consultarEstadosFormulariosAsignacion(int tipoFormulario, Long idResponsable)
            throws CirculemosNegocioException {
        FormularioEJB.logger.debug("FormularioEJB.consultarEstadosFormulariosAsignacion");

        // Se obtiene el unificacionRespohsable
        UnificacionResponsableDTO unificacionResponsableDTO = new UnificacionResponsableDTO();
        ResponsableFormularioDTO res = new ResponsableFormularioDTO();
        res.setId(idResponsable);
        unificacionResponsableDTO.setResponsableFormulario(res);

        List<UnificacionResponsableDTO> respuesta = administracionFormulariosEJB
                .consultarResponsablesFormularios(unificacionResponsableDTO);

        // En caso de que el responsable no exista se genera un CirculemosNegocioException

        if (respuesta == null || respuesta.isEmpty()) {
            throw new CirculemosNegocioException(ErrorFormularios.FormulariosEstados.NUM_008003);
        }

        unificacionResponsableDTO = respuesta.get(0);

        // Se obtiene el organismo de transito
        OrganismoTransitoDTO organismoTransitoDTO = unificacionResponsableDTO.getResponsableFormulario()
                .getOrganismoTransito();

        // En caso de que el tipo de formulario no exista se genera un CirculemosNegocioException

        TipoFormulario tipFormulario = em.find(TipoFormulario.class, new Integer(tipoFormulario));

        if (tipFormulario == null) {
            throw new CirculemosNegocioException(ErrorFormularios.FormulariosEstados.NUM_008004);
        }

        TipoFormularioDTO tipoFormularioDTO = new TipoFormularioDTO();
        tipoFormularioDTO = TipoFormularioHelper.toLevel0DTO(tipFormulario);

        // Se obtiene los estados de formulario
        TypedQuery<EstadoFormulario> queryEstados = em.createNamedQuery("EstadoFormulario.findAll",
                EstadoFormulario.class);
        List<EstadoFormulario> resultEstados = queryEstados.getResultList();
        Map<Integer, String> estados = new HashMap<>();

        for (EstadoFormulario estadoAux : resultEstados) {
            estados.put(estadoAux.getId(), estadoAux.getNombre());
        }

        StringBuilder sql = new StringBuilder();

        sql.append("select dce,f.estadoFormulario,count(DISTINCT f.id) as cuenta from DetalleCambioEstado dce");
        sql.append(" JOIN dce.seguimientoFormularioList sf");
        sql.append(" JOIN sf.formulario f");
        sql.append(" JOIN f.tipoFormulario tf");
        sql.append(" WHERE dce.estadoFormulario.id=:estadoFormulario");
        sql.append(" AND dce.responsableFormulario.id=:responsable");
        sql.append(" AND tf.id=:tipoForm");
        sql.append(" AND dce.responsableFormulario.organismoTransito.codigoOrganismo=:organismo");
        sql.append(" GROUP BY dce,f.estadoFormulario");
        sql.append(" ORDER BY dce.fechaAplicacionEstado DESC");

        Map<String, Object> params = new HashMap<>(4);

        params.put("estadoFormulario", EnumEstadoFomulario.ASIGNADO.getValue());
        params.put("responsable", idResponsable);
        params.put("organismo", organismoTransitoDTO.getCodigoOrganismo());
        params.put("tipoForm", tipoFormulario);

        // Query query = em.createQuery(sql.toString());
        //
        // query.setParameter("estadoFormulario", EnumEstadoFomulario.ASIGNADO.getValue());
        // query.setParameter("responsable", new Long(idResponsable));
        // query.setParameter("organismo", organismoTransitoDTO.getCodigoOrganismo());
        // query.setParameter("tipoForm", tipoFormulario);

        GenericDao<Object[]> resultado = new GenericDao<>(Object[].class, em);

        // List<Object[]> results = query.getResultList();
        List<Object[]> results = resultado.buildAndExecuteQuery(sql, params);

        EstadoFormularioAsignacionDTO estadoFormularioAsignadoDTO = new EstadoFormularioAsignacionDTO();
        List<CifrasControlEstadoFormularioAsignacionDTO> ccefAsignacionDTOList = new ArrayList<>();

        int[][] matrizAux = { { 0, EnumEstadoFomulario.ASIGNADO.getValue(), 0 },
                { 0, EnumEstadoFomulario.DEVUELTO.getValue(), 0 }, { 0, EnumEstadoFomulario.ANULADO.getValue(), 0 },
                { 0, EnumEstadoFomulario.CONSUMIDO.getValue(), 0 } };
        CifrasControlEstadoFormularioAsignacionDTO ccefAsignacionDTOAnt = new CifrasControlEstadoFormularioAsignacionDTO();
        CifrasControlEstadoFormularioAsignacionDTO ccefAsignacionDTOAct = new CifrasControlEstadoFormularioAsignacionDTO();

        // Metodo Auxiliar que se encarga de tomar el resultado y adicionar la informacion complementaria
        EstadosFormulariosUtil.generarLista(matrizAux, ccefAsignacionDTOAnt, ccefAsignacionDTOAct,
                ccefAsignacionDTOList, estados, results);

        estadoFormularioAsignadoDTO.setCodigoOrganismo(organismoTransitoDTO.getCodigoOrganismo());
        estadoFormularioAsignadoDTO.setNombreOrganismo(organismoTransitoDTO.getNombreOrganismo());
        estadoFormularioAsignadoDTO.setIdTipoFormulario(tipoFormulario);
        estadoFormularioAsignadoDTO.setResponsable(unificacionResponsableDTO);
        estadoFormularioAsignadoDTO.setCifrasControl(ccefAsignacionDTOList);
        estadoFormularioAsignadoDTO.setNombreTipoFormulario(tipoFormularioDTO.getNombre());
        return estadoFormularioAsignadoDTO;

    }

    @Override
    public String seleccionarFormulario(UnificacionResponsableDTO unificacionResponsableDTO,
            EnumTipoFormulario enumTipoFormulario) throws CirculemosNegocioException {

        UnificacionResponsableDTO unificacionResponsableDTOAux = null;
        unificacionResponsableDTOAux = administracionFormulariosEJB
                .consultarResponsableFormularios(unificacionResponsableDTO);

        if (unificacionResponsableDTOAux == null)
            throw new CirculemosNegocioException(ErrorFormularios.SolicitarOCN.COM_050002);

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT f FROM Formulario f");
        jpql.append(" JOIN f.tipoFormulario tf");
        jpql.append(" JOIN f.estadoFormulario e");
        jpql.append(" JOIN f.responsableFormulario re");
        jpql.append(" WHERE");
        jpql.append(" tf.id=:tipoForm");
        jpql.append(" AND e.id=:estado");
        jpql.append(" AND re.id=:responsable");
        jpql.append(" ORDER BY f.id");

        filtros.put("tipoForm", enumTipoFormulario.getValue());
        filtros.put("estado", EnumEstadoFomulario.ASIGNADO.getValue());
        filtros.put("responsable", unificacionResponsableDTOAux.getResponsableFormulario().getId());

        GenericDao<Formulario> dao = new GenericDao<Formulario>(Formulario.class, em);
        List<Formulario> lstComparendos = dao.buildAndExecuteQuery(jpql, filtros);

        String numeroFormulario = null;

        if (!lstComparendos.isEmpty()) {

            Formulario formulario = lstComparendos.get(0);
            numeroFormulario = formulario.getNumeroFormulario();
            CambioEstadoDTO cambioEstadoDTO = new CambioEstadoDTO();
            cambioEstadoDTO.setNumeroFormulario(formulario.getNumeroFormulario());
            cambioEstadoDTO.setTipoFormulario(formulario.getTipoFormulario().getId());
            cambioEstadoDTO.setEnumCausalCambioEstado(EnumCausalCambioEstado.DISPOSITIVO_ELECTRONICO);
            cambioEstadoDTO.setFechaCambio(new Date());

            cambiarEstadoFormulario(cambioEstadoDTO);
        }
        return numeroFormulario;
    }
}
