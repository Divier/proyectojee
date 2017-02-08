package co.com.datatools.c2.maganed_bean.persona.datos_demograficos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.dto.personas.ArchivoPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.TipoArchivoPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoArchivoPersona;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comun.FachadaCatalogosMB;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Managed Bean para manipulación administración de personas CU_CIR20_UBI_003
 * 
 * @author Dixon.Alvarez
 * 
 */
@ManagedBean
@SessionScoped
public class DatosDemograficosMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(DatosDemograficosMB.class.getName());

    private static final String BUNDLE_PERSONA = "labelAdminPersona";
    private static final String CONTENT_TYPE = "application/pdf";
    private final String NOMBRE_ARCHIVO_AUTORIZACION = "Documento ubicabilidad.pdf";
    private static final int TAMANIO_MAXIMO_ARCHIVO_NO_UBICABILIDAD = 1000000000;

    private StreamedContent documentoAutorizacion;
    private StreamedContent documentoDescargar;
    private StreamedContent archivoNoUbicabilidad;
    private Map<Integer, String> tiposFuenteInformacion;

    private int tamanioMaximoArchivoNoUbi;
    private String mensajeErrorTamanioArchivo;

    @EJB
    private IRPersona personaEJB;
    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;
    @EJB
    private IRRepositorioArchivo repositorioArchivoEJB;

    @ManagedProperty(value = "#{fachadaCatalogosMB}")
    private FachadaCatalogosMB fachadaCatalogosMB;

    public void inicializarDatos(Integer idTipoDocumento, String numeroDocumento, Boolean subflujo) {
        logger.debug(DatosDemograficosMB.class.getName().concat("inicializarDatos()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        tamanioMaximoArchivoNoUbi = TAMANIO_MAXIMO_ARCHIVO_NO_UBICABILIDAD;
        mensajeErrorTamanioArchivo = MessageFormat.format(
                getBundle(BUNDLE_PERSONA).getString("msg_error_excede_tamanio_maximo"), tamanioMaximoArchivoNoUbi);
        if (!datosDemograficosHolderFL.isConsultado()) {
            datosDemograficosHolderFL.setSubflujo(false);
            if (subflujo != null && subflujo) {
                datosDemograficosHolderFL.setSubflujo(true);
            }
            if (idTipoDocumento != null && numeroDocumento != null) {
                PersonaDTO personaFiltro = new PersonaDTO();
                personaFiltro.setTipoIdentificacion(new TipoIdentificacionPersonaDTO(idTipoDocumento));
                personaFiltro.setNumeroIdentificacion(numeroDocumento);
                datosDemograficosHolderFL.setPersonaFiltro(personaFiltro);
                consultar();
            }
            // Obtenemos el nombre de los tipos fuente informacion
            tiposFuenteInformacion = new HashMap<Integer, String>();
            for (SelectItem item : fachadaCatalogosMB.catTipoFuenteInformacion()) {
                tiposFuenteInformacion.put((Integer) item.getValue(), item.getLabel());
            }
        }
    }

    public void consultar() {
        logger.debug(DatosDemograficosMB.class.getName().concat("consultar()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        datosDemograficosHolderFL.setConsultado(false);
        datosDemograficosHolderFL.setPersonaConsultada(personaEJB.consultarPersona(getCodigoOrganismoTransito(),
                datosDemograficosHolderFL.getPersonaFiltro().getTipoIdentificacion().getId(),
                datosDemograficosHolderFL.getPersonaFiltro().getNumeroIdentificacion()));
        if (datosDemograficosHolderFL.getPersonaConsultada() != null) {
            PersonaHistoricoDTO personaHistoricoDTO = new PersonaHistoricoDTO();
            personaHistoricoDTO.setPersona(new PersonaDTO(datosDemograficosHolderFL.getPersonaConsultada().getId()));
            datosDemograficosHolderFL.setHistoricosPersona(personaEJB.consultarHistoricoPersona(personaHistoricoDTO));
            datosDemograficosHolderFL.setConsultado(true);
            CirculemosAccesoBundleGeneral
                    .addMensajeResultadoConsulta(datosDemograficosHolderFL.getHistoricosPersona().size());
        } else {
            datosDemograficosHolderFL.setPupupUsuarioNoExisteVisible(true);
            RequestContext request = RequestContext.getCurrentInstance();
            request.update("frmUsuarioNoExiste");
            request.execute("PF('popupUsuarioNoExiste').show();");
        }
    }

    public void validarTipoFuente() {
        logger.debug(DatosDemograficosMB.class.getName().concat("validarTipoFuente()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        datosDemograficosHolderFL.setProcesoManual(false);
        if (datosDemograficosHolderFL.getHistoricoSeleccionado() != null
                && datosDemograficosHolderFL.getHistoricoSeleccionado().getFuenteInformacion().getId()
                        .intValue() == EnumTipoFuenteInformacion.PROCESO_MANUAL.getValue().intValue()) {
            datosDemograficosHolderFL.setProcesoManual(true);
        }
    }

    public String cancelar() {
        logger.debug(DatosDemograficosMB.class.getName().concat("cancelar()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        String resultado = "VOLVER";
        datosDemograficosHolderFL.setConsultado(false);
        if (datosDemograficosHolderFL.getSubflujo() != null && datosDemograficosHolderFL.getSubflujo()) {
            resultado = "SALIR";
        } else {
            datosDemograficosHolderFL.setPersonaConsultada(null);
        }
        return resultado;
    }

    public void verAutorizacion() {
        logger.debug(DatosDemograficosMB.class.getName().concat("verAutorizacion()"));
        documentoAutorizacion = null;
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        if (datosDemograficosHolderFL.getHistoricoSeleccionado() != null
                && datosDemograficosHolderFL.getHistoricoSeleccionado().getIdDocumento() != null) {
            try {
                List<Long> documentoList = new ArrayList<>();
                documentoList.add(datosDemograficosHolderFL.getHistoricoSeleccionado().getIdDocumento());
                byte[] documento = iRDocumentosCirculemos.consultarDocumentosPDF(documentoList);
                if (documento != null) {
                    documentoAutorizacion = new DefaultStreamedContent(new ByteArrayInputStream(documento),
                            CONTENT_TYPE, NOMBRE_ARCHIVO_AUTORIZACION);
                    RequestContext request = RequestContext.getCurrentInstance();
                    request.update("frmVerAutorizacion");
                    request.execute("PF('popupVerAutorizacion').show();");
                }
            } catch (CirculemosAlertaException e) {
                CirculemosErrorHandler.handleException(e);
            }
        } else {
            addErrorMessage(BUNDLE_PERSONA, "msg_no_existe_documento");
        }

    }

    /**
     * Consulta los documentos que tiene la persona consultada
     */
    private void consultarArchivosPersona() {
        logger.debug(DatosDemograficosMB.class.getName().concat("consultarArchivosPersona()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        ArchivoPersonaDTO archivoPersonaDTO = new ArchivoPersonaDTO();
        archivoPersonaDTO.setPersona(datosDemograficosHolderFL.getPersonaConsultada());
        datosDemograficosHolderFL.setDocumentoSeleccionado(null);
        datosDemograficosHolderFL.setDocumentos(personaEJB.consultarArchivosPersona(archivoPersonaDTO, true));
    }

    /**
     * Consulta los documentos de no ubicabilidad que tiene la persona consultada
     */
    private void consultarArchivosNoUbicaPersona() {
        logger.debug(DatosDemograficosMB.class.getName().concat("consultarArchivosNoUbicaPersona()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        ArchivoPersonaDTO archivoPersonaDTO = new ArchivoPersonaDTO();
        archivoPersonaDTO
                .setTipoArchivoPersona(new TipoArchivoPersonaDTO(EnumTipoArchivoPersona.NO_UBICABILIDAD.getValue()));
        archivoPersonaDTO.setPersona(datosDemograficosHolderFL.getPersonaConsultada());
        datosDemograficosHolderFL.setArchivoNoUbiSeleccionado(null);
        datosDemograficosHolderFL
                .setArchivosNoUbicabilidad(personaEJB.consultarArchivosPersona(archivoPersonaDTO, false));
    }

    /**
     * Consulta y despliega popup con la informacion de la direcciones relacionadas al historico de persona seleccionado
     */
    public void verDirecciones() {
        logger.debug(DatosDemograficosMB.class.getName().concat("verDireccion()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        DireccionPersonaHistoricoDTO direccionPersonaHistoricoDTO = new DireccionPersonaHistoricoDTO();
        direccionPersonaHistoricoDTO.setPersonaHistoricos(new ArrayList<PersonaHistoricoDTO>());
        direccionPersonaHistoricoDTO.getPersonaHistoricos().add(datosDemograficosHolderFL.getHistoricoSeleccionado());
        datosDemograficosHolderFL
                .setDirecciones(personaEJB.consultarDireccionPersonaHistorico(direccionPersonaHistoricoDTO));
        RequestContext request = RequestContext.getCurrentInstance();
        request.update(":frmDetalleDirecciones");
        request.execute("PF('popupDetalleDirecciones').show();");
    }

    /**
     * Consulta y despliega popup con la informacion de los telefonos relacionads al historico de persona seleccionado
     */
    public void verTelefonos() {
        logger.debug(DatosDemograficosMB.class.getName().concat("verTelefonos()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        TelefonoPersonaHistoricoDTO telefonoPersonaHistoricoDTO = new TelefonoPersonaHistoricoDTO();
        telefonoPersonaHistoricoDTO.setPersonaHistoricos(new ArrayList<PersonaHistoricoDTO>());
        telefonoPersonaHistoricoDTO.getPersonaHistoricos().add(datosDemograficosHolderFL.getHistoricoSeleccionado());
        datosDemograficosHolderFL
                .setTelefonos(personaEJB.consultarTelefonoPersonaHistorico(telefonoPersonaHistoricoDTO));
        RequestContext request = RequestContext.getCurrentInstance();
        request.update(":frmDetalleTelefonos");
        request.execute("PF('popupDetalleTelefonos').show();");
    }

    /**
     * Consulta y despliega popup con la informacion de los correos relacionados al historico de persona seleccionado
     */
    public void verCorreos() {
        logger.debug(DatosDemograficosMB.class.getName().concat("verCorreos()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        CorreoPersonaHistoricoDTO correoPersonaHistoricoDTO = new CorreoPersonaHistoricoDTO();
        correoPersonaHistoricoDTO.setPersonaHistoricos(new ArrayList<PersonaHistoricoDTO>());
        correoPersonaHistoricoDTO.getPersonaHistoricos().add(datosDemograficosHolderFL.getHistoricoSeleccionado());
        datosDemograficosHolderFL.setCorreos(personaEJB.consultarCorreoPersonaHistorico(correoPersonaHistoricoDTO));
        RequestContext request = RequestContext.getCurrentInstance();
        request.update(":frmDetalleCorreos");
        request.execute("PF('popupDetalleCorreos').show();");
    }

    /**
     * Despliega popup con informacion de no ubicabilidad
     */
    public void verNoUbicabilidad() {
        logger.debug(DatosDemograficosMB.class.getName().concat("verNoUbicabilidad()"));
        consultarArchivosNoUbicaPersona();
        RequestContext request = RequestContext.getCurrentInstance();
        request.execute("PF('popupDetalleNoUbicabilidad').show();");
    }

    /**
     * Despliega popup con informacion de documentos
     */
    public void verDocumentos() {
        logger.debug(DatosDemograficosMB.class.getName().concat("verDocumentos()"));
        consultarArchivosPersona();
        RequestContext request = RequestContext.getCurrentInstance();
        request.execute("PF('popupDocumentos').show();");
    }

    public void irAdicionarNoUbicabilidad() {
        logger.debug(DatosDemograficosMB.class.getName().concat("irAdicionarNoUbicabilidad()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        ArchivoPersonaDTO archivoPersonaDTO = new ArchivoPersonaDTO();
        archivoPersonaDTO.setTipoFuenteInformacion(new TipoFuenteInformacionDTO());
        datosDemograficosHolderFL.setArchivoNoUbiRegistrar(archivoPersonaDTO);
        datosDemograficosHolderFL.setArchivoCargado(null);
        RequestContext request = RequestContext.getCurrentInstance();
        request.update(":frmAdicionarNoUbicabilidad");
        request.execute("PF('popupAdicionarNoUbicabilidad').show();");
    }

    public void adicionarNoUbicabilidad() {
        logger.debug(DatosDemograficosMB.class.getName().concat("adicionarNoUbicabilidad()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        if (datosDemograficosHolderFL.getArchivoCargado() != null) {
            datosDemograficosHolderFL.getArchivoNoUbiRegistrar().setFechaRegistro(new Date());
            datosDemograficosHolderFL.getArchivoNoUbiRegistrar()
                    .setPersona(datosDemograficosHolderFL.getPersonaConsultada());
            datosDemograficosHolderFL.getArchivoNoUbiRegistrar().setTipoArchivoPersona(
                    new TipoArchivoPersonaDTO(EnumTipoArchivoPersona.NO_UBICABILIDAD.getValue()));
            ArchivoTransportableDTO archivoCargado = new ArchivoTransportableDTO(
                    datosDemograficosHolderFL.getArchivoCargado().getFileName(),
                    datosDemograficosHolderFL.getArchivoCargado().getContents());
            try {
                datosDemograficosHolderFL.setArchivoNoUbiRegistrar(personaEJB.registrarArchivoPersona(
                        datosDemograficosHolderFL.getArchivoNoUbiRegistrar(), archivoCargado, true));
                inicializarDatos(datosDemograficosHolderFL.getPersonaFiltro().getTipoIdentificacion().getId(),
                        datosDemograficosHolderFL.getPersonaFiltro().getNumeroIdentificacion(),
                        datosDemograficosHolderFL.getSubflujo());
                datosDemograficosHolderFL.getArchivoNoUbiRegistrar().getTipoFuenteInformacion()
                        .setNombre(tiposFuenteInformacion.get(datosDemograficosHolderFL.getArchivoNoUbiRegistrar()
                                .getTipoFuenteInformacion().getId()));
                datosDemograficosHolderFL.getArchivosNoUbicabilidad()
                        .add(datosDemograficosHolderFL.getArchivoNoUbiRegistrar());
                addInfoMessage(BUNDLE_PERSONA, "msg_registro_exitoso_no_ubicabilidad");
                RequestContext request = RequestContext.getCurrentInstance();
                request.execute("PF('popupAdicionarNoUbicabilidad').hide();");
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            }
        } else {
            addErrorMessage(BUNDLE_PERSONA, "msg_error_sin_archivo");
        }
    }

    public void irCrearPersona() {
        logger.debug(DatosDemograficosMB.class.getName().concat("irCrearPersona()"));
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        datosDemograficosHolderFL.setPupupUsuarioNoExisteVisible(false);
    }

    public void handleFileUpload(FileUploadEvent event) {
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        datosDemograficosHolderFL.setArchivoCargado(event.getFile());
    }

    /**
     * Retorna el valor que tenga en el estado, si es null retorna vacio
     * 
     * @param dto
     *            Contiene la informacion a validar
     * @return
     */
    public String validacion(Object dto) {
        Boolean validacion = null;
        String valor = null;
        if (dto instanceof DireccionPersonaHistoricoDTO) {
            validacion = ((DireccionPersonaHistoricoDTO) dto).getEstado();
        } else if (dto instanceof TelefonoPersonaHistoricoDTO) {
            validacion = ((TelefonoPersonaHistoricoDTO) dto).getEstado();
        } else if (dto instanceof CorreoPersonaHistoricoDTO) {
            validacion = ((CorreoPersonaHistoricoDTO) dto).getEstado();
        }
        if (validacion == null) {
            valor = "";
        } else if (validacion.equals(false)) {
            valor = "No";
        } else {
            valor = "Si";
        }
        return valor;
    }

    public StreamedContent getDocumentoAutorizacion() {
        return documentoAutorizacion;
    }

    public void setDocumentoAutorizacion(StreamedContent documentoAutorizacion) {
        this.documentoAutorizacion = documentoAutorizacion;
    }

    public StreamedContent getDocumentoDescargar() {
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        ArchivoTransportableDTO archivo = null;
        try {
            archivo = repositorioArchivoEJB
                    .consultarDocumento(datosDemograficosHolderFL.getDocumentoSeleccionado().getNumeroArchivo());
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
        if (archivo != null) {
            try (InputStream stream = new ByteArrayInputStream(archivo.getContenido())) {
                documentoDescargar = new DefaultStreamedContent(stream, "", archivo.getNombre());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return documentoDescargar;
    }

    public void setDocumentoDescargar(StreamedContent documentoDescargar) {
        this.documentoDescargar = documentoDescargar;
    }

    public StreamedContent getArchivoNoUbicabilidad() {
        DatosDemograficosHolderFL datosDemograficosHolderFL = findFlowObject(DatosDemograficosHolderFL.class,
                DatosDemograficosHolderFL.NOMBRE_BEAN);
        ArchivoTransportableDTO archivo = null;
        try {
            archivo = repositorioArchivoEJB
                    .consultarDocumento(datosDemograficosHolderFL.getArchivoNoUbiSeleccionado().getNumeroArchivo());
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
        if (archivo != null) {
            try (InputStream stream = new ByteArrayInputStream(archivo.getContenido())) {
                archivoNoUbicabilidad = new DefaultStreamedContent(stream, "", archivo.getNombre());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return archivoNoUbicabilidad;
    }

    public void setArchivoNoUbicabilidad(StreamedContent archivoNoUbicabilidad) {
        this.archivoNoUbicabilidad = archivoNoUbicabilidad;
    }

    public int getTamanioMaximoArchivoNoUbi() {
        return tamanioMaximoArchivoNoUbi;
    }

    public void setTamanioMaximoArchivoNoUbi(int tamanioMaximoArchivoNoUbi) {
        this.tamanioMaximoArchivoNoUbi = tamanioMaximoArchivoNoUbi;
    }

    public String getMensajeErrorTamanioArchivo() {
        return mensajeErrorTamanioArchivo;
    }

    public void setMensajeErrorTamanioArchivo(String mensajeErrorTamanioArchivo) {
        this.mensajeErrorTamanioArchivo = mensajeErrorTamanioArchivo;
    }

    public FachadaCatalogosMB getFachadaCatalogosMB() {
        return fachadaCatalogosMB;
    }

    public void setFachadaCatalogosMB(FachadaCatalogosMB fachadaCatalogosMB) {
        this.fachadaCatalogosMB = fachadaCatalogosMB;
    }

}
