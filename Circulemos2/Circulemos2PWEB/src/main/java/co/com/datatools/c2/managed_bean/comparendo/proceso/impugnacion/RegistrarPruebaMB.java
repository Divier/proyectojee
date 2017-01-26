package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.ImpulsoPruebaDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.PruebaDTO;
import co.com.datatools.c2.dto.RegistrarPruebaDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion.vo.RegistroTablaVO;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRImpugnacion;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRProceso;
import co.com.datatools.c2.negocio.interfaces.IRPruebas;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.file.FileValidator;

/**
 * Managed Bean para el manejo de registro de pruebas de impugnaciones
 * 
 * @author divier.casas 2016-06-29
 *
 */
@ManagedBean
@SessionScoped
public class RegistrarPruebaMB extends AbstractC2ManagedBean {
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(RegistrarPruebaMB.class.getName());

    private final String NOMBRE_BUNDLE = "labelProcesoImpugnacion";

    /**
     * Extensiones permitidas para carga de pruebas
     */
    private String extensionesPermitidas = "/(\\.|\\/)(rar|zip|7zip)$/";
    private String[] extensionesPermitidasPruebas = { "rar", "zip", "7zip" };
    private int tamanioMaximoArch;

    /**
     * Atributos para visualizar documentos
     */
    private final String NOMBRE_ARCHIVO = "Documento.pdf";

    /**
     * Documento a visualizar
     */
    private StreamedContent documento;

    @EJB
    private IRImpugnacion impugnacionEJB;

    @EJB
    private IRPruebas pruebasEJB;

    @EJB
    private IRProceso procesoEJB;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    private IRRepositorioArchivo repositorioArchivoEJB;

    @EJB
    private IRParametro parametroEjb;

    @ManagedProperty(value = "#{encabezadoImpugnacionMB}")
    private EncabezadoImpugnacionMB encabezadoImpugnacion;

    @ManagedProperty(value = "#{visualizarDocumentoMB}")
    private VisualizarDocumentoMB visualizarDocumentoMB;

    private int LONGITUD_NOMBRE_ARCHIVO = 100;

    @PostConstruct
    public void init() {
        logger.debug("RegistrarPruebaMB::init()");
        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
    }

    /**
     * Inicializa los datos del registro de pruebas
     * 
     * @author divier.casas 2016-06-29
     */
    public void inicializarDatos() {
        logger.debug("RegistrarPruebaMB::inicializarDatos()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);
        if (registrarPruebaFL.getSolicitud() != null) {
            // Llena los impulsos
            registrarPruebaFL.setListaImpulsos(new ArrayList<RegistroTablaVO<ImpulsoPruebaDTO>>());
            for (ImpulsoPruebaDTO impulso : registrarPruebaFL.getSolicitud().getImpulsoPruebas()) {
                registrarPruebaFL.getListaImpulsos().add(new RegistroTablaVO<ImpulsoPruebaDTO>(impulso));
            }
            // Llena las pruebas
            registrarPruebaFL.setListaPruebas(new ArrayList<RegistroTablaVO<PruebaDTO>>());
            for (PruebaDTO prueba : registrarPruebaFL.getSolicitud().getPruebas()) {
                registrarPruebaFL.getListaPruebas().add(new RegistroTablaVO<PruebaDTO>(prueba));
            }
            registrarPruebaFL.setIdProceso(impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());
        }
    }

    /**
     * Guarda el registro de las pruebas
     * 
     * @author julio.pinzon 2016-07-07
     */
    public void guardar() {
        logger.debug("RegistrarPruebaMB::guardar()");
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);

        boolean tienePruebasAdicionadas = false;
        for (RegistroTablaVO<PruebaDTO> prueba : registrarPruebaFL.getListaPruebas()) {
            if (prueba.getDto().getId() == null) {
                tienePruebasAdicionadas = true;
                break;
            }
        }

        boolean tieneImpulsosAdicionados = false;
        for (RegistroTablaVO<ImpulsoPruebaDTO> impulso : registrarPruebaFL.getListaImpulsos()) {
            if (impulso.getDto().getId() == null) {
                tieneImpulsosAdicionados = true;
                break;
            }
        }

        // valida que al menos se ha adicionado un impulso o una prueba en las listas respectivas
        if (tienePruebasAdicionadas || tieneImpulsosAdicionados) {
            try {
                RegistrarPruebaDTO registro = new RegistrarPruebaDTO();
                registro.setIdProceso(registrarPruebaFL.getIdProceso());
                registro.setSolicitud(registrarPruebaFL.getSolicitud());
                List<ImpulsoPruebaDTO> impulsos = new ArrayList<>();
                for (RegistroTablaVO<ImpulsoPruebaDTO> impulso : registrarPruebaFL.getListaImpulsos()) {
                    impulsos.add(impulso.getDto());
                }
                registro.setImpulsos(impulsos);
                List<PruebaDTO> pruebas = new ArrayList<>();
                for (RegistroTablaVO<PruebaDTO> prueba : registrarPruebaFL.getListaPruebas()) {
                    pruebas.add(prueba.getDto());
                }
                registro.setPruebas(pruebas);
                byte[] contenidoDoc = pruebasEJB.registrarPruebas(registro);
                visualizarDocumentoMB.setVisualizar(false);
                visualizarDocumentoMB.visualizarDocumento(contenidoDoc, NOMBRE_ARCHIVO,
                        getBundle(NOMBRE_BUNDLE).getString("msg_creacion_exitosa_prueba"),
                        getBundle(NOMBRE_BUNDLE).getString("label_confirmacion"));
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleError(e.getErrorInfo());
            }
        } else {
            addErrorMessage(NOMBRE_BUNDLE, "msg_error_falta_impulso");
        }
    }

    /**
     * Guarda los archivos de pruebas
     * 
     * @author divier.casas 2016-06-29
     */
    public void cargarArchivoPrueba(FileUploadEvent event) {
        logger.debug("RegistrarPruebaMB::cargarArchivoPrueba()");
        final UploadedFile file = event.getFile();

        try {
            if (FileValidator.checkFileType(file.getInputstream(), file.getFileName(), extensionesPermitidasPruebas)) {
                RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class,
                        RegistrarPruebaFL.NOMBRE_BEAN);

                boolean nombresIguales = false;
                for (RegistroTablaVO<PruebaDTO> pruebaTemp : registrarPruebaFL.getListaPruebas()) {
                    if (pruebaTemp.getDto().getNombreArchivo().equals(file.getFileName())) {
                        nombresIguales = true;
                        break;
                    }
                }
                if (!nombresIguales) {
                    if (file.getFileName().length() <= LONGITUD_NOMBRE_ARCHIVO) {
                        final PruebaDTO prueba = new PruebaDTO();
                        prueba.setArchivo(file.getContents());
                        prueba.setFechaCarga(new Date());
                        prueba.setNombreArchivo(file.getFileName());

                        List<RegistroTablaVO<PruebaDTO>> lstPrueba = registrarPruebaFL.getListaPruebas();
                        if (lstPrueba == null) {
                            lstPrueba = new ArrayList<>();
                        }
                        lstPrueba.add(new RegistroTablaVO<PruebaDTO>(prueba));

                        registrarPruebaFL.setListaPruebas(lstPrueba);

                    } else {
                        String mensajeError = MessageFormat.format(
                                getBundle(NOMBRE_BUNDLE).getString("msg_error_longitud_nombre_archivo"),
                                LONGITUD_NOMBRE_ARCHIVO);
                        getFacesContext().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensajeError));
                    }
                } else {
                    addErrorMessage(NOMBRE_BUNDLE, "msg_error_nombre_archivo");
                }
            } else {
                addErrorMessage(NOMBRE_BUNDLE, "msg_error_tipo_dato");
            }
        } catch (IOException e) {
            addErrorMessage(NOMBRE_BUNDLE, "msg_error_tipo_dato");
        }
    }

    /**
     * Verifica si contiene un impulso definitivo
     * 
     * @return true si es definitivo
     * @author julio.pinzon 2016-07-07
     */
    public boolean esImpulsoDefinitivo(boolean soloPrevios) {
        logger.debug("RegistrarPruebaMB::esImpulsoDefinitivo()");
        boolean definitivo = false;
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);
        if (registrarPruebaFL.getListaImpulsos() != null) {
            for (RegistroTablaVO<ImpulsoPruebaDTO> impulso : registrarPruebaFL.getListaImpulsos()) {
                if ((!soloPrevios || impulso.getDto().getId() != null) && impulso.getDto().getDefinitivo()) {
                    definitivo = true;
                    break;
                }
            }
        }
        return definitivo;
    }

    /**
     * Consulta la prueba para descargarla
     * 
     * @return Contenido del archivo
     * @author julio.pinzon 2016-07-07
     */
    public StreamedContent verPrueba() {
        logger.debug("RegistrarPruebaMB::verPrueba()");
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);
        StreamedContent streamedContent = null;
        ArchivoTransportableDTO archivoTransportableDTO;
        try {

            if (registrarPruebaFL.getPruebaSeleccionada() != null
                    && registrarPruebaFL.getPruebaSeleccionada().getDto().getNumeroArchivo() != null) {
                archivoTransportableDTO = repositorioArchivoEJB
                        .consultarDocumento(registrarPruebaFL.getPruebaSeleccionada().getDto().getNumeroArchivo());

                streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), null,
                        archivoTransportableDTO.getNombre());
            } else if (registrarPruebaFL.getPruebaSeleccionada() != null) {
                archivoTransportableDTO = new ArchivoTransportableDTO(
                        registrarPruebaFL.getPruebaSeleccionada().getDto().getNombreArchivo(),
                        registrarPruebaFL.getPruebaSeleccionada().getDto().getArchivo());

                streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), null,
                        archivoTransportableDTO.getNombre());
            }

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        }
        return streamedContent;
    }

    /**
     * Elimina pruebas que se hayan creado
     * 
     * @author julio.pinzon 2016-07-07
     */
    public void eliminarPrueba() {
        logger.debug("RegistrarPruebaMB::eliminarPrueba()");
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);

        if (registrarPruebaFL.getPruebaSeleccionada() != null) {
            registrarPruebaFL.getListaPruebas().remove(registrarPruebaFL.getPruebaSeleccionada());
            registrarPruebaFL.setPruebaSeleccionada(null);
        }
    }

    /**
     * Consulta el impulso para visualizarlo
     * 
     * @author julio.pinzon 2016-07-07
     */
    public void verImpulso() {
        logger.debug("RegistrarPruebaMB::verImpulso()");
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);

        if (registrarPruebaFL.getImpulsoSeleccionado() != null
                && registrarPruebaFL.getImpulsoSeleccionado().getDto().getNumeroDocumento() != null) {
            List<Long> documentoList = new ArrayList<>();
            documentoList.add(registrarPruebaFL.getImpulsoSeleccionado().getDto().getNumeroDocumento());
            consultarDocumento(documentoList);
        }
    }

    /**
     * Elimina impulsos que se hayan creado
     * 
     * @author julio.pinzon 2016-07-07
     */
    public void eliminarImpulso() {
        logger.debug("RegistrarPruebaMB::eliminarImpulso()");
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);

        if (registrarPruebaFL.getImpulsoSeleccionado() != null) {
            registrarPruebaFL.getListaImpulsos().remove(registrarPruebaFL.getImpulsoSeleccionado());
            registrarPruebaFL.setImpulsoSeleccionado(null);
        }
    }

    /**
     * Consulta el documento de solicitud para visualizarlo
     * 
     * @author julio.pinzon 2016-07-07
     */
    public void verSolicitudPrueba() {
        logger.debug("RegistrarPruebaMB::verSolicitudPrueba()");
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);

        if (registrarPruebaFL.getSolicitud() != null) {
            TrazabilidadProcesoDTO traza = new TrazabilidadProcesoDTO(
                    registrarPruebaFL.getSolicitud().getTrazabilidadProceso().getId());
            traza.setProceso(new ProcesoDTO(registrarPruebaFL.getIdProceso()));
            List<TrazabilidadProcesoDTO> trazas = procesoEJB.consultarTrazabilidad(traza);
            if (!trazas.isEmpty() && trazas.get(0).getDocumentos() != null) {
                List<Long> documentoList = new ArrayList<>();
                for (DocumentoProcesoDTO doc : trazas.get(0).getDocumentos()) {
                    documentoList.add(doc.getNumeroDocumento());
                }
                consultarDocumento(documentoList);
            }
        }
    }

    /**
     * Consulta el documento del proceso
     * 
     * @author julio.pinzon 2016-06-27
     */
    private void consultarDocumento(List<Long> documentoList) {
        logger.debug("RegistrarPruebaMB::consultarDocumento(List<Long>)");
        try {
            byte[] contenido = iRDocumentosCirculemos.consultarDocumentosPDF(documentoList);
            visualizarDocumentoMB.setVisualizar(true);
            visualizarDocumentoMB.visualizarDocumento(contenido, NOMBRE_ARCHIVO, null, null);
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public String getMesErrLimitSize() {
        return MessageFormat.format(getBundle(NOMBRE_BUNDLE).getString("msg_error_tamanio_archivo"), tamanioMaximoArch);
    }

    public EncabezadoImpugnacionMB getEncabezadoImpugnacion() {
        return encabezadoImpugnacion;
    }

    public void setEncabezadoImpugnacion(EncabezadoImpugnacionMB encabezadoImpugnacion) {
        this.encabezadoImpugnacion = encabezadoImpugnacion;
    }

    public String getExtensionesPermitidas() {
        return extensionesPermitidas;
    }

    public int getTamanioMaximoArch() {
        return tamanioMaximoArch;
    }

    public StreamedContent getDocumento() {
        return documento;
    }

    public void setDocumento(StreamedContent documento) {
        this.documento = documento;
    }

    public VisualizarDocumentoMB getVisualizarDocumentoMB() {
        return visualizarDocumentoMB;
    }

    public void setVisualizarDocumentoMB(VisualizarDocumentoMB visualizarDocumentoMB) {
        this.visualizarDocumentoMB = visualizarDocumentoMB;
    }

}