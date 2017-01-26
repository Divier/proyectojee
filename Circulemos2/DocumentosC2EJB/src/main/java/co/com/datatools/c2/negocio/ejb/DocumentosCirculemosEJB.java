package co.com.datatools.c2.negocio.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.PlantillaDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorDocumentos;
import co.com.datatools.c2.negocio.error.ErrorDocumentos.Documentos;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.interfaces.ILDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.documentos.constantes.ConstantesGeneral;
import co.com.datatools.documentos.enumeraciones.EnumFormatoDocumento;
import co.com.datatools.documentos.negocio.interfaces.IDocumentosWS;
import co.com.datatools.documentos.ws.DocumentoGeneradoVO;
import co.com.datatools.documentos.ws.DocumentoWSVO;
import co.com.datatools.documentos.ws.PlantillaConfiguracionVO;
import co.com.datatools.documentos.ws.PlantillaVO;
import co.com.datatools.documentos.ws.UsuarioVO;
import co.com.datatools.documentos.ws.exception.DocumentosWebException;

@Stateless(name = "DocumentosCirculemosEJB")
@LocalBean
public class DocumentosCirculemosEJB implements IRDocumentosCirculemos, ILDocumentosCirculemos {

    private final static Logger logger = Logger.getLogger(DocumentosCirculemosEJB.class.getName());

    private static final String INTEGRACION_DOCUMENTOS_JNDI_NAME = "ejb:DocumentosEAR-"
            + ConstantesGeneral.VERSION_DOCUMENTOS
            + "/IntegracionEJB/DocumentosWSEJB!co.com.datatools.documentos.negocio.interfaces.IDocumentosWS";

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    /**
     * Usuario para autenticarse en documentos
     */
    private static final String USUARIO_DOCUMENTOS = "admin";
    private static final String PWD_USUARIO_DOCUMENTOS = "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec";

    /**
     * Separador de valores de configuracion
     */
    private static final String COMA = ",";

    /**
     * Nombres variables que se envian con un valor por defecto
     */
    private static final String FECHA_ACTUAL = "fecha_actual";
    private static final String HORA_ACTUAL = "hora_actual";
    private static final String CARGO_FUNCIONARIO = "cargo";
    private static final String NOMBRE_FUNCIONARIO_APERTURA = "responsable_apertura";
    private static final String NOMBRE_FUNCIONARIO_UBICABILIDAD = "responsable_ubicabilidad";
    private static final String NOMBRE_FUNCIONARIO = "NOMBRE_FUNCIONARIO";
    private static final String DOCUMENTO_FUNCIONARIO = "DOCUMENTO_FUNCIONARIO";
    private static final String T_DOCUMENTO_FUNCIONARIO = "T_DOCUMENTO_FUNCIONARIO";

    private static final String UBICACION_PRELIMINAR = "/c2/preliminar";

    private IDocumentosWS iDocumentosWS;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneral;

    @EJB
    private IRPersona personaEjb;

    public DocumentosCirculemosEJB() {
        try {
            final Hashtable<String, String> props = new Hashtable<String, String>();
            // setup the ejb: namespace URL factory
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            // create the InitialContext
            final Context context = new javax.naming.InitialContext(props);
            // lookup the bean
            iDocumentosWS = (IDocumentosWS) context.lookup(INTEGRACION_DOCUMENTOS_JNDI_NAME);
        } catch (NamingException e) {
            logger.error("Error localizando Jndi EJB", e);
        }
    }

    @Override
    public Long generarDocumento(GeneraDocumentoDTO generaDocumento) throws CirculemosAlertaException {
        logger.debug("DocumentosCirculemosEJB::generarDocumento(GeneraDocumentoDTO)");
        generaDocumento.setPreliminar(false);
        // Generacion de archivo
        DocumentoGeneradoVO dd = procesarGeneracionDocumento(generaDocumento);
        return dd.getCodigoDocumento();
    }

    /**
     * Realiza el llamado al modulo de documentos para la generacion
     * 
     * @param generaDocumento
     *            Objeto de generacion de documentos
     * @param data
     *            Mapa con los valores del documento a generar
     * @return Codigo del documento generado
     * @throws CirculemosNegocioException
     * @author julio.pinzon
     * @param preliminar
     */
    private DocumentoGeneradoVO completarGeneracionDocumento(GeneraDocumentoDTO generaDocumento,
            Map<String, Object> data) throws CirculemosAlertaException {
        logger.debug("DocumentosCirculemosEJB::generarDocumento(GeneraDocumentoDTO)");

        // Objeto para conversion JSON
        Gson gson = new GsonBuilder().setDateFormat(ConstantesGeneral.DATE_FORMAT).create();
        // Formato de fechas
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantesGeneral.DATE_FORMAT);

        UsuarioVO usuario = new UsuarioVO();
        usuario.setUsuario(USUARIO_DOCUMENTOS);
        usuario.setClave(PWD_USUARIO_DOCUMENTOS);
        DocumentoWSVO documentoWSVO = new DocumentoWSVO();
        documentoWSVO.setCodigoPlantilla(generaDocumento.getTipoDocumentoGenerado().getCodigoPlantilla());
        documentoWSVO.setDescripcion(generaDocumento.getTipoDocumentoGenerado().getDescripcion());
        documentoWSVO.setFechaGeneracion(sdf.format(generaDocumento.getFechaGeneracion()));
        if (generaDocumento.isPreliminar()) {
            documentoWSVO.setFormato(EnumFormatoDocumento.PDF_PREVIEW.name());
        } else {
            documentoWSVO.setFormato(EnumFormatoDocumento.PDF.name());
        }
        documentoWSVO.setUbicacion(generaDocumento.getUbicacion());
        documentoWSVO.setUsuario(iRSeguridadCirculemos.obtenerUsuarioDto().getUsuario().getLogin());
        documentoWSVO.setValoresPlantilla(gson.toJson(data));
        try {
            return iDocumentosWS.generarDocumento(documentoWSVO, usuario);
        } catch (DocumentosWebException e) {
            logger.error("Error al generar documento", e);
            throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073001);
        }
    }

    @Override
    public byte[] consultarDocumentosPDF(List<Long> idDocumentos) throws CirculemosAlertaException {
        logger.debug("DocumentosCirculemosEJB::consultarDocumentosPDF(List<Long>)");
        try {
            UsuarioVO usuario = new UsuarioVO();
            usuario.setUsuario(USUARIO_DOCUMENTOS);
            usuario.setClave(PWD_USUARIO_DOCUMENTOS);
            return iDocumentosWS.consultarDocumentosPDF(idDocumentos, usuario);
        } catch (DocumentosWebException e) {
            logger.error("Error al consultar documentos de notificacion de comparendos", e);
            throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073002);
        }
    }

    /**
     * Procesa la generacion de documentos
     * 
     * @param generaDocumento
     * @return Documento generado
     * @throws CirculemosAlertaException
     * @author julio.pinzon 2016-08-03
     */
    private DocumentoGeneradoVO procesarGeneracionDocumento(GeneraDocumentoDTO generaDocumento)
            throws CirculemosAlertaException {
        logger.debug("DocumentosCirculemosEJB::procesarGeneracionDocumento(GeneraDocumentoDTO, boolean)");

        // Valida tipo de documento
        if (generaDocumento.getTipoDocumentoGenerado() == null) {
            throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073005);
        }
        Map<String, Object> data = obtenerDatosConfiguracion(generaDocumento);
        try {
            return completarGeneracionDocumento(generaDocumento, data);
        } catch (Exception e) {
            if (e instanceof CirculemosAlertaException) {
                throw e;
            }
            logger.error(e.getMessage());
            throw new CirculemosAlertaException(Documentos.ADM_073001);
        }

    }

    @Override
    public ArchivoTransportableDTO generarDocumentoPreliminar(GeneraDocumentoDTO generaDocumento)
            throws CirculemosAlertaException {
        logger.debug("DocumentosCirculemosEJB::generarDocumento(GeneraDocumentoDTO)");
        generaDocumento.setPreliminar(true);
        // Generacion de archivo preliminar
        DocumentoGeneradoVO dd = procesarGeneracionDocumento(generaDocumento);
        ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(dd.getNombre(), dd.getContenido());
        return archivo;
    }

    @Override
    public List<PlantillaDTO> consultarPlantillasPorProceso(String codigoProceso) throws CirculemosAlertaException {
        logger.debug("DocumentosCirculemosEJB::consultarPlantillasPorProceso(String)");

        List<PlantillaDTO> plantillas = new ArrayList<>();
        UsuarioVO usuario = new UsuarioVO();
        usuario.setUsuario(USUARIO_DOCUMENTOS);
        usuario.setClave(PWD_USUARIO_DOCUMENTOS);
        try {
            List<PlantillaVO> plantillasVO = iDocumentosWS.consultarPlantillas(codigoProceso, usuario);
            for (PlantillaVO plantillaVO : plantillasVO) {
                PlantillaDTO plantilla = new PlantillaDTO();
                plantilla.setCodigoPlantilla(plantillaVO.getCodigoPlantilla());
                plantilla.setNombrePlantilla(plantillaVO.getNombrePlantilla());
                plantillas.add(plantilla);
            }
        } catch (DocumentosWebException e) {
            logger.error("Error al generar documento", e);
            throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073006);
        }
        return plantillas;
    }

    /**
     * Obtiene los valores de las variables a partir de la consulta
     * 
     * @param generaDocumento
     * @param configuracion
     * @param valoresParametros
     * @return
     * @throws CirculemosAlertaException
     * @author julio.pinzon 2016-09-08
     */
    private List<Map<String, Object>> obtenerValoresVariables(GeneraDocumentoDTO generaDocumento,
            PlantillaConfiguracionVO configuracion, Object[] valoresParametros) throws CirculemosAlertaException {
        logger.debug("DocumentosCirculemosEJB::generarDocumentoImpugnacion(GeneraDocumentoDTO)");

        List<Map<String, Object>> datosDocumento = new ArrayList<>();

        try {
            
            //TODO pendiente probar lenguaje fecha 
            //StringBuilder sql = new StringBuilder();

            //sql.append("Set Language 'Español'");

            //Query update = em.createNativeQuery(sql.toString());
            //update.executeUpdate();

            Query query = em.createNativeQuery(configuracion.getConsulta());
            String[] parametros = configuracion.getParametros().split(COMA);
            int index = 0;
            for (String parametro : parametros) {
                if (StringUtils.isNotBlank(parametro)) {
                    // Atributo en generar documento que trae esos parametros
                    query.setParameter(parametro.trim(), valoresParametros[index]);
                }
                index++;
            }
            // Indica el orden en que vienen las variables en la consulta
            String[] ordenVariables = configuracion.getOrdenVariables().split(COMA);

            @SuppressWarnings({ "unchecked" })
            List<Object[]> detalles = Utilidades.safeList(query.getResultList());
            if (detalles != null && !detalles.isEmpty()) {
                for (Object[] campos : detalles) {
                    Map<String, Object> datosDetalle = new HashMap<String, Object>();
                    int i = 0;
                    // Asigna los valores a las variables
                    for (Object campo : campos) {
                        if (campo != null) {
                            if (campo instanceof Date) {
                                datosDetalle.put(ordenVariables[i].trim(), campo);
                            } else {
                                datosDetalle.put(ordenVariables[i].trim(), String.valueOf(campo));
                            }
                        }
                        i++;
                    }

                    // Llena los datos de los grupos por cada detalle
                    if (configuracion.getConfiguracionesPlantilla() != null) {
                        for (PlantillaConfiguracionVO config : configuracion.getConfiguracionesPlantilla()) {
                            datosDetalle.put(config.getNombreGrupo(),
                                    obtenerValoresVariables(generaDocumento, config, campos));
                        }
                    }
                    datosDocumento.add(datosDetalle);

                    // Si no es un grupo solo lee el primer registro
                    if (StringUtils.isBlank(configuracion.getNombreGrupo())) {
                        if (StringUtils.isNotBlank(configuracion.getParametrosUbicacion())) {
                            // Indica los campos de la consulta inicial que se ponen en la ubicacion
                            String[] indexParametrosUbicacion = configuracion.getParametrosUbicacion().split(COMA);
                            // Adiciona parametros para la ubicacion del documento
                            Object[] parametrosUbicacion = new Object[indexParametrosUbicacion.length];
                            int indexUbicacion = 0;
                            for (String indexParametro : indexParametrosUbicacion) {
                                parametrosUbicacion[indexUbicacion] = campos[Integer.valueOf(indexParametro.trim())];
                            }

                            String ubicacion = generaDocumento.getUbicacion();
                            if (parametrosUbicacion != null) {
                                ubicacion = String.format(ubicacion, parametrosUbicacion);
                            }
                            generaDocumento.setUbicacion(ubicacion);
                        }
                        break;
                    }
                }
            } else if (!generaDocumento.isPreliminar()) {
                throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073001);
            } else {
                datosDocumento.add(new HashMap<String, Object>());
                generaDocumento.setUbicacion(UBICACION_PRELIMINAR);
            }
        } catch (Exception e) {
            logger.error("Error en la configuracion de la plantilla para generar documento", e);
            throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073001);
        }

        return datosDocumento;
    }

    /**
     * Consulta la configuracion de la plantilla enviada y pone los datos por defecto
     * 
     * @param generaDocumento
     * @return Mapa de las variables con su valor
     * @throws CirculemosAlertaException
     * @author julio.pinzon 2016-09-09
     */
    private Map<String, Object> obtenerDatosConfiguracion(GeneraDocumentoDTO generaDocumento)
            throws CirculemosAlertaException {
        logger.debug("DocumentosCirculemosEJB::obtenerDatosConfiguracion(GeneraDocumentoDTO)");

        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setUsuario(USUARIO_DOCUMENTOS);
        usuarioVO.setClave(PWD_USUARIO_DOCUMENTOS);

        // Realiza la consulta del reporte principal
        PlantillaConfiguracionVO configuracion = null;
        try {
            configuracion = iDocumentosWS.consultarConfiguracionPlantilla(
                    generaDocumento.getTipoDocumentoGenerado().getCodigoPlantilla(),
                    generaDocumento.getFechaGeneracion(), usuarioVO);
            // Colocar ubicacion consultada
            generaDocumento.setUbicacion(configuracion.getUbicacion());
        } catch (DocumentosWebException e) {
            logger.error("Se presento un error al consultar configuracion de la plantilla", e);
            throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073007);
        }

        // Obtiene los valores de las variables a partir de la consulta
        List<Map<String, Object>> datosDocumento = obtenerValoresVariables(generaDocumento, configuracion,
                generaDocumento.getValoresParametros());

        Map<String, Object> data = datosDocumento.get(0);

        // Pone valores por defecto que siempre se envian
        // Fecha actual
        Calendar fechaActual = Calendar.getInstance();
        data.put(FECHA_ACTUAL, fechaActual.getTime());
        data.put(HORA_ACTUAL, fechaActual.getTime());
        // Obtiene el usuario de sesion
        UsuarioPersonaDTO usuario = iRSeguridadCirculemos.obtenerUsuarioDto();
        // Obtiene el Rol del usuario
        List<String> rolesUsuario = iRSeguridadCirculemos.consultarRolesUsuario(usuario.getLogin());
        if (rolesUsuario != null && !rolesUsuario.isEmpty()) {
            data.put(CARGO_FUNCIONARIO, rolesUsuario.get(0));
        }
        // Consulta la persona asociada al usuario en sesion
        PersonaDTO persona = personaEjb.consultarPersona(usuario.getPersona().getId());
        data.put(NOMBRE_FUNCIONARIO_UBICABILIDAD, persona.getNombreCompleto());
        data.put(NOMBRE_FUNCIONARIO_APERTURA, persona.getNombreCompleto());
        data.put(NOMBRE_FUNCIONARIO, persona.getNombreCompleto());
        data.put(DOCUMENTO_FUNCIONARIO, persona.getNumeroIdentificacion());
        data.put(T_DOCUMENTO_FUNCIONARIO, persona.getTipoIdentificacion().getCodigo());

        // En caso de que sea vista preliminar debe enviar los valores como parametro
        if (generaDocumento.getValoresVistaPreliminar() != null) {
            for (Entry<String, Object> valor : generaDocumento.getValoresVistaPreliminar().entrySet()) {
                data.put(valor.getKey(), valor.getValue());
            }
        }
        return data;
    }

    // TODO: METODOS SOLO APLICAN PARA COLOMBIA, SE DEBEN PASAR A LA CONFIGURACION DE DOCUMENTOS
    // /**
    // * Genera documentos de notificacion personal y de conducta concluyente
    // *
    // * @author julio.pinzon
    // * @param generaDocumento
    // * @return Mapa de valores de las variables
    // * @throws CirculemosNegocioException
    // */
    // private Map<String, Object> generarDocumentoNotificacionPersonal(GeneraDocumentoDTO generaDocumento)
    // throws CirculemosAlertaException {
    // logger.debug("DocumentosCirculemosEJB::generarDocumentoNotificacionPersonal(GeneraDocumentoDTO)");
    //
    // // GeneraDocumentoNotificacionPresencialDTO generaDocumentoNotificacion = (GeneraDocumentoNotificacionPresencialDTO) generaDocumento;
    //
    // ComparendoDTO comparendo = comparendoEjb.consultarComparendo(generaDocumento.getCicomparendo());
    // if (comparendo == null) {
    // throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073003);
    // }
    // Map<String, Object> data = new HashMap<String, Object>();
    // data.put(EnumVariablePlantilla.FECHA_ACTUAL_SISTEMA.name(), new Date());
    // if (comparendo.getInfractor() != null) {
    // data.put(EnumVariablePlantilla.TIPO_DOCUMENTO_INFRACTOR.name(),
    // comparendo.getInfractor().getTipoIdentificacion().getNombre());
    // data.put(EnumVariablePlantilla.NUMERO_DOCUMENTO_INFRACTOR.name(),
    // comparendo.getInfractor().getNumeroIdentificacion());
    // if (comparendo.getTipoInfractor() != null) {
    // data.put(EnumVariablePlantilla.TIPO_INFRACTOR.name(), comparendo.getTipoInfractor().getNombre());
    // }
    // data.put(EnumVariablePlantilla.NOMBRE_INFRACTOR.name(), comparendo.getInfractor().getNombreCompleto());
    // if (comparendo.getInfractor().getDireccion() != null) {
    // data.put(EnumVariablePlantilla.DIRECCION_INFRACTOR.name(),
    // comparendo.getInfractor().getDireccion().toString());
    // }
    // return data;
    // } else {
    // throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073004);
    // }
    //
    // }
    //
    // /**
    // * Genera documentos de resolucion de comparendos
    // *
    // * @author julio.pinzon
    // * @param generaDocumento
    // * @return Mapa de valores de las variables
    // * @throws CirculemosNegocioException
    // */
    // private Map<String, Object> generarDocumentoResolucion(GeneraDocumentoDTO generaDocumento)
    // throws CirculemosAlertaException {
    // logger.debug("DocumentosCirculemosEJB::generarDocumentoResolucion(GeneraDocumentoDTO)");
    //
    // GeneraResolucionComparendoDTO generaResolucionComparendoDTO = (GeneraResolucionComparendoDTO) generaDocumento;
    // ConsultaComparendoDTO consultaComparendoDTO = new ConsultaComparendoDTO();
    // consultaComparendoDTO.setCodigoOrganismo(generaResolucionComparendoDTO.getCodigoOrganismo());
    // consultaComparendoDTO.setNumeroComparendo(generaResolucionComparendoDTO.getNumeroComparendo());
    // consultaComparendoDTO.setEsPolca(generaResolucionComparendoDTO.isEsPolca());
    // consultaComparendoDTO.setIdTipoComparendo(generaResolucionComparendoDTO.getIdTipoComparendo());
    // try {
    // List<ComparendoDTO> comparendos = comparendoEjb.consultarComparendos(consultaComparendoDTO);
    // if (!comparendos.isEmpty()) {
    // ComparendoDTO comparendo = comparendos.get(0);
    // Map<String, Object> data = new HashMap<String, Object>();
    // data.put(EnumVariablePlantilla.CODIGO_INFRACCION.name(), comparendo.getInfraccion().getCodigo());
    // ConfiguracionInfraccionDTO configInfraccion = infraccionEjb
    // .consultarInfraccion(comparendo.getInfraccion().getCodigo(), comparendo.getFechaInfraccion());
    // data.put(EnumVariablePlantilla.Descripcion_Infractor.name(), configInfraccion.getDescripcion());
    // data.put(EnumVariablePlantilla.Fecha_Imposicion_Comparendo.name(), comparendo.getFechaInfraccion());
    // data.put(EnumVariablePlantilla.RESOLUCION_SANCION.name(),
    // generaResolucionComparendoDTO.getNumeroConsecutivo());
    // data.put(EnumVariablePlantilla.Numero_Comparendo.name(),
    // generaResolucionComparendoDTO.getNumeroComparendo());
    // // data.put(EnumVariablePlantilla.Organismo_Transito.name(),
    // // generaResolucionComparendoDTO.getCodigoOrganismo() + "-"
    // // + comparendo.getOrdenComparendoNacional().getOrganismoTransito().getNombreOrganismo());
    // // "Unidad_Monetaria":"Pesos",
    // // "CANTIDAD_TIPO_UNIDAD_CONVERSION_UNITARIA":"0000000",
    // data.put(EnumVariablePlantilla.FECHA_ACTUAL_SISTEMA.name(), new Date());
    // // Consulta la tarifa del comparendo
    // TarifaInfraccionDTO tarifa = iRTarifaInfraccion.consultarTarifaInfraccion(
    // comparendo.getInfraccion().getId(), new BigDecimal(0), comparendo.getFechaInfraccion());
    // if (tarifa != null) {
    // data.put(EnumVariablePlantilla.Tarifa_Infraccion.name(),
    // tarifa.getValorInfraccion().toPlainString());
    // }
    // if (comparendo.getComparendoVehiculo() != null) {
    // data.put(EnumVariablePlantilla.Placa_Vehiculo.name(),
    // comparendo.getComparendoVehiculo().getPlacaVehiculo());
    // }
    // if (comparendo.getPropietario() != null) {
    // data.put(EnumVariablePlantilla.NOMBRE_COMPLETO_PROPETARIO.name(),
    // comparendo.getPropietario().getNombreCompleto());
    // }
    // if (comparendo.getInfractor() != null) {
    // data.put(EnumVariablePlantilla.TIPO_DOCUMENTO_INFRACTOR.name(),
    // comparendo.getInfractor().getTipoIdentificacion().getNombre());
    // data.put(EnumVariablePlantilla.NUMERO_DOCUMENTO_INFRACTOR.name(),
    // comparendo.getInfractor().getNumeroIdentificacion());
    // if (comparendo.getTipoInfractor() != null) {
    // data.put(EnumVariablePlantilla.TIPO_INFRACTOR.name(),
    // comparendo.getTipoInfractor().getNombre());
    // }
    // data.put(EnumVariablePlantilla.Nombre_Completo_Infractor.name(),
    // comparendo.getInfractor().getNombreCompleto());
    // if (comparendo.getInfractor().getDireccion() != null) {
    // data.put(EnumVariablePlantilla.DIRECCION_INFRACTOR.name(),
    // comparendo.getInfractor().getDireccion().toString());
    // }
    // return data;
    // } else {
    // throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073004);
    // }
    // } else {
    // throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073003);
    // }
    // } catch (CirculemosNegocioException e) {
    // throw new CirculemosAlertaException(ErrorDocumentos.Documentos.ADM_073001);
    // }
    // }
    //
    // private enum VariablesPlantillaNotifAviso {
    // CONSECUTIVO_DOCUMENTO, //
    // FECHA_GENERACION_AVISO, //
    // NUM_COMPARENDO, //
    // FECHA_IMPOSICION_COMPARENDO, //
    // HORA_IMPOSICION_COMPARENDO, //
    // TIPO_DOCUMENTO_INFRACTOR, //
    // NUMERO_DOCUMENTO_INFRACTOR, //
    // PRIMER_NOMBRE_INFRACTOR, //
    // SEGUNDO_NOMBRE_INFRACTOR, //
    // PRIMER_APELLIDO_INFRACTOR, //
    // SEGUNDO_APELLIDO_INFRACTOR, //
    // DIRECCION_INFRACTOR, //
    // CELULAR_INFRACTOR, //
    // CODIGO_INFRACCION, //
    // PLACA_VEHICULO, //
    // grupo_1, //
    // ;
    //
    // }
    //
    // /**
    // * Permite retornar los datos del respectivo documento de notificacion aviso encontrados en el sistema.
    // *
    // * @param generaDocumento
    // * genera documento con los datos de entrada para consultar los valores a generar en el documento
    // * @return datos a generar en el documento de notificacion aviso.
    // * @throws CirculemosAlertaException
    // * @author luis.forero(2016-02-15)
    // */
    // private Map<String, Object> generarDocumentoNotificacionAviso(GeneraDocumentoDTO generaDocumento)
    // throws CirculemosAlertaException {
    // logger.debug("DocumentosCirculemosEJB::generarDocumentoNotificacionAviso(GeneraDocumentoDTO)");
    //
    // GeneraEdictoDTO generaNotifAvisoDTO = (GeneraEdictoDTO) generaDocumento;
    //
    // String jpql = "SELECT na FROM NotificacionAviso na LEFT JOIN FETCH na.comparendos c LEFT JOIN FETCH c.infraccion LEFT JOIN FETCH
    // c.ordenComparendoNacional LEFT JOIN FETCH c.comparendoVehiculo WHERE na.id = :pIdAviso";
    // Map<String, Object> filtro = new HashMap<String, Object>();
    // filtro.put("pIdAviso", generaNotifAvisoDTO.getIdAviso());
    //
    // GenericDao<NotificacionAviso> genericDao = new GenericDao<NotificacionAviso>(NotificacionAviso.class, em);
    // List<NotificacionAviso> resultList = genericDao.buildAndExecuteQuery(jpql, filtro);
    // if (!resultList.isEmpty()) {
    // Map<String, Object> datosNotificacionAviso = new HashMap<String, Object>();
    // NotificacionAviso notificacionAviso = resultList.get(0);
    // // ************ Ingreso de campos de la notificacion *************
    // // CONSECUTIVO_DOCUMENTO
    // datosNotificacionAviso.put(VariablesPlantillaNotifAviso.CONSECUTIVO_DOCUMENTO.toString(),
    // notificacionAviso.getConsecutivo());
    // datosNotificacionAviso.put(VariablesPlantillaNotifAviso.FECHA_GENERACION_AVISO.toString(),
    // notificacionAviso.getFechaGeneracion());
    //
    // // DATOS DE LOS COMPARENDOS A SER NOTIFICADOS.
    // List<Map<String, Object>> lstValrsComparendos = new ArrayList<Map<String, Object>>();
    // for (Comparendo comparendo : notificacionAviso.getComparendos()) {
    // Map<String, Object> datosComparendo = new HashMap<String, Object>();
    // // NUMERO DE COMPARENDO
    // datosComparendo.put(VariablesPlantillaNotifAviso.NUM_COMPARENDO.toString(),
    // comparendo.getOrdenComparendoNacional().getNumeroComparendo());
    // // FECHA DE IMPOSICION DEL COMPARENDO
    // datosComparendo.put(VariablesPlantillaNotifAviso.FECHA_IMPOSICION_COMPARENDO.toString(),
    // comparendo.getFechaInfraccion());
    // // HORA DE IMPOSICION DEL COMPARENDO
    // datosComparendo.put(VariablesPlantillaNotifAviso.HORA_IMPOSICION_COMPARENDO.toString(),
    // comparendo.getFechaInfraccion());
    //
    // // DATOS INFRACTOR
    // String jpqlCompPers = "SELECT cp FROM ComparendoPersona cp LEFT JOIN FETCH cp.tipoIdentificacion LEFT JOIN FETCH cp.direccion WHERE
    // cp.comparendo.cicomparendo = :pIdComp AND cp.tipoPersonaComparendo.codigo = :pCodTipPerCom";
    // filtro.clear();
    // filtro.put("pIdComp", comparendo.getCicomparendo());
    // filtro.put("pCodTipPerCom", EnumTipoPersonaComparendo.INFRACTOR.getValue());
    // GenericDao<ComparendoPersona> daoCP = new GenericDao<ComparendoPersona>(ComparendoPersona.class, em);
    // List<ComparendoPersona> result = daoCP.buildAndExecuteQuery(jpqlCompPers, filtro);
    // if (!result.isEmpty()) {
    // ComparendoPersona comparendoPersona = result.get(0);
    // // TIPO DOCUMENTO DEL INFRACTOR
    // datosComparendo.put(VariablesPlantillaNotifAviso.TIPO_DOCUMENTO_INFRACTOR.toString(),
    // comparendoPersona.getTipoIdentificacion().getNombre());
    // // NUMERO DE DOCUMENTO DEL INFRACTOR
    // datosComparendo.put(VariablesPlantillaNotifAviso.NUMERO_DOCUMENTO_INFRACTOR.toString(),
    // comparendoPersona.getNumeroIdentificacion());
    // // NOMBRE 1
    // datosComparendo.put(VariablesPlantillaNotifAviso.PRIMER_NOMBRE_INFRACTOR.toString(),
    // comparendoPersona.getNombre1());
    // // NOMBRE 2
    // datosComparendo.put(VariablesPlantillaNotifAviso.SEGUNDO_NOMBRE_INFRACTOR.toString(),
    // comparendoPersona.getNombre2());
    // // APELLIDO 1
    // datosComparendo.put(VariablesPlantillaNotifAviso.PRIMER_APELLIDO_INFRACTOR.toString(),
    // comparendoPersona.getApellido1());
    // // APELLIDO 2
    // datosComparendo.put(VariablesPlantillaNotifAviso.SEGUNDO_APELLIDO_INFRACTOR.toString(),
    // comparendoPersona.getApellido2());
    // // DIRECCION
    // if (comparendoPersona.getDireccion() != null) {
    // DireccionDTO direccionDTO = DireccionHelperExtend.toLevel1DTO(comparendoPersona.getDireccion());
    // datosComparendo.put(VariablesPlantillaNotifAviso.DIRECCION_INFRACTOR.toString(),
    // direccionDTO.toString());
    // }
    // // NUMERO DE CELULAR
    // datosComparendo.put(VariablesPlantillaNotifAviso.CELULAR_INFRACTOR.toString(),
    // comparendoPersona.getTelefonoMovil());
    // // CODIGO DE INFRACCION
    // datosComparendo.put(VariablesPlantillaNotifAviso.CODIGO_INFRACCION.toString(),
    // comparendo.getInfraccion().getCodigo());
    // // PLACA VEHICULO
    // if (comparendo.getComparendoVehiculo() != null) {
    // datosComparendo.put(VariablesPlantillaNotifAviso.PLACA_VEHICULO.toString(),
    // comparendo.getComparendoVehiculo().getPlacaVehiculo());
    // }
    //
    // } else {
    // throw new CirculemosAlertaException(Documentos.COM_055001);
    // }
    // lstValrsComparendos.add(datosComparendo);
    // }
    //
    // datosNotificacionAviso.put(VariablesPlantillaNotifAviso.grupo_1.toString(), lstValrsComparendos);
    // return datosNotificacionAviso;
    // }
    //
    // return null;
    // }

}
