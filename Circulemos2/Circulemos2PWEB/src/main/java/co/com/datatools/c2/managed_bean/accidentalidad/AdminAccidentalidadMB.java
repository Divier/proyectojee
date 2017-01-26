package co.com.datatools.c2.managed_bean.accidentalidad;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.time.DateUtils;
import org.jboss.logging.Logger;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import co.com.datatools.c2.dto.AccidentalidadDTO;
import co.com.datatools.c2.dto.AdjuntosAccidentalidadDTO;
import co.com.datatools.c2.dto.ArchivoAccidentalidadDTO;
import co.com.datatools.c2.dto.CarrilDTO;
import co.com.datatools.c2.dto.DatosAccidentalidadDTO;
import co.com.datatools.c2.dto.DelegacionDTO;
import co.com.datatools.c2.dto.DetalleAccidentalidadDTO;
import co.com.datatools.c2.dto.EstadoFisicoDTO;
import co.com.datatools.c2.dto.PrevencionDTO;
import co.com.datatools.c2.dto.RegistroAccidentalidadDTO;
import co.com.datatools.c2.dto.RespuestaAccidentalidadDTO;
import co.com.datatools.c2.dto.SentidoDTO;
import co.com.datatools.c2.dto.TipoAccidenteDTO;
import co.com.datatools.c2.dto.TipoPersonaIPATDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.ClaseVehiculoDTO;
import co.com.datatools.c2.dto.comun.TipoCategLicenciaConduccionDTO;
import co.com.datatools.c2.dto.comun.TipoServicioDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumTipoPersonaAccidente;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAccidentalidad;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.date.UtilFecha;

/**
 * Se encarga de controlar la consula, registro y modificaciones a los informes de IPAT
 * 
 * @author divier.casas
 * 
 */
@ManagedBean
@SessionScoped
public class AdminAccidentalidadMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AdminAccidentalidadMB.class.getName());
    private static final String NOMBRE_BUNDLE = "labelAdminAccidentalidad";

    private int HORA = 23;
    private int MINUTOS = 59;
    private int SEGUNDOS = 59;
    private Date fechaMinimaConsulta;
    private Date fechaMaxParteFinalizado;
    private int consecutivo = 0;

    // Lista con las capturas de un informe
    private List<byte[]> capturasInforme;
    // Objeto temporal del informe de accidentalidad
    private DatosAccidentalidadDTO datosAccidentalidadDTO;
    private String consecutivoInforme;

    /**
     * Atributo que permite identificar que tipos de archivos son permitidos
     */
    public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(pdf)$/";

    @EJB
    private IRAccidentalidad iRAccidentalidad;
    @EJB
    private IRParametro parametroEjb;

    private int tamanioMaximoArch;

    /**
     * Objeto que representa la pesatañas de las diferentes partes
     */
    private List<RegistroAccidentalidadDTO> tabs;

    /**
     * Tab activo
     */
    private int activeIndex;

    public AdminAccidentalidadMB() {
        capturasInforme = new ArrayList<>();
        tabs = new ArrayList<RegistroAccidentalidadDTO>();
    }

    public void fechaMaximaParteFinalizado() {
        AdminAccidentalidadFL adminAccidFL = findFlowObject(AdminAccidentalidadFL.class,
                AdminAccidentalidadFL.NOMBRE_BEAN);
        fechaMaxParteFinalizado = adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAccidente();
    }

    @PostConstruct
    public void init() {
        LOGGER.debug("AdminAccidentalidadMB::init");

        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
        inicializar();
    }

    /**
     * Metodo que inicializa los tab y objetos necesarios para el registro de el formulario
     */
    public void inicializar() {
        AdminAccidentalidadFL adminAccidFL = findFlowObject(AdminAccidentalidadFL.class,
                AdminAccidentalidadFL.NOMBRE_BEAN);
        fechaMinimaConsulta = UtilFecha.sumarDias(getFechaActual(), -30);
        adminAccidFL.setDatosAccidentalidadDTO(new DatosAccidentalidadDTO());
        adminAccidFL.getDatosAccidentalidadDTO().setAccidentalidad(new AccidentalidadDTO());
        adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().setDelegacion(new DelegacionDTO());
        adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().setTipoAccidente(new TipoAccidenteDTO());
        adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().setPrevencion(new PrevencionDTO());

        RegistroAccidentalidadDTO tabDefecto = new RegistroAccidentalidadDTO();

        tabDefecto.setDetalleAccidentalidadDTO(new DetalleAccidentalidadDTO());
        tabDefecto.getDetalleAccidentalidadDTO().setTipoPersonaIPAT(new TipoPersonaIPATDTO());
        tabDefecto.getDetalleAccidentalidadDTO().setEstadoFisico(new EstadoFisicoDTO());

        String mensajeRegistro = getBundle(NOMBRE_BUNDLE).getString("titulo_tab");
        mensajeRegistro = MessageFormat.format(mensajeRegistro, activeIndex + 1);
        tabDefecto.setNombreTab(mensajeRegistro);
        tabDefecto.setIdTab(activeIndex);
        tabs.add(tabDefecto);
    }

    /**
     * Restablece los tabs del formulario
     */
    public void reestablecer() {
        if (tabs != null && !tabs.isEmpty()) {
            tabs.clear();
        }
        activeIndex = 0;
        inicializar();
    }

    /**
     * Metodo que se encarga de recibir las capturas de las pantallas del informe para posteriormente guradar accidentalidad
     * 
     * @author giovanni.velandia
     * @param bImg
     */
    public void guardarInforme(byte[] bImg) {
        LOGGER.debug("AdminAccidentalidadMB::guardarInforme(byte[])");

        capturasInforme.add(bImg);

        if (capturasInforme.size() == datosAccidentalidadDTO.getLsDetalleAccidentalidad().size()) {
            generarInformePDF(capturasInforme);
            capturasInforme = new ArrayList<>();
        }
    }

    /**
     * Metodo que se encarga de convertir las imagenes de captura en un pdf
     * 
     * @author giovanni.velandia
     * @param bImgs
     */
    public void generarInformePDF(List<byte[]> bImgs) {
        LOGGER.debug("AdminAccidentalidadMB::generarInformePDF(List<byte[]>)");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, byteArrayOutputStream);
            pdfWriter.open();
            document.open();
            for (byte[] bs : bImgs) {
                Image imagen = Image.getInstance(bs);
                float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 0)
                        / imagen.getWidth()) * 100;
                imagen.scalePercent(scaler);
                document.add(imagen);
            }
            document.close();
            pdfWriter.close();

            iRAccidentalidad.guardarCapturaInforme(consecutivoInforme, byteArrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }

    }

    /**
     * Metodo que se encarga de consultar los informes de IPAT
     * 
     * @author divier.casas
     */
    public void consultar() {
        LOGGER.debug("AdminAccidentalidadMB::consultar()");
        AdminAccidentalidadHolderFL adminAccidHolderFL = findFlowObject(AdminAccidentalidadHolderFL.class,
                AdminAccidentalidadHolderFL.NOMBRE_BEAN);
        adminAccidHolderFL.setAnexos(false);

        adminAccidHolderFL.setRespuestaAccidentalidadSelDTO(null);

        adminAccidHolderFL.setRespuestaAccidentalidadDTOs(
                iRAccidentalidad.consultarAccidentalidadInforme(adminAccidHolderFL.getFiltrosAccidentalidadDTO()));

        if (adminAccidHolderFL.getRespuestaAccidentalidadDTOs() == null
                || adminAccidHolderFL.getRespuestaAccidentalidadDTOs().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            adminAccidHolderFL.setRespuestaAccidentalidadDTOs(new ArrayList<RespuestaAccidentalidadDTO>());
            return;
        } else {
            CirculemosAccesoBundleGeneral
                    .addMensajeResultadoConsulta(adminAccidHolderFL.getRespuestaAccidentalidadDTOs().size());
        }
    }

    /**
     * Se encarga de retirar el documento de la lista de anexos
     * 
     * @author giovanni.velandia
     */
    public void eliminarDocumento() {
        LOGGER.debug("AdminAccidentalidadMB::eliminarDocumento()");
        AdminAccidentalidadFL adminAccidentalidadFL = findFlowObject(AdminAccidentalidadFL.class,
                AdminAccidentalidadFL.NOMBRE_BEAN);
        adminAccidentalidadFL.getDatosAccidentalidadDTO().getAdjuntos()
                .remove(adminAccidentalidadFL.getAdjuntosAccidentalidadSelDTO());

    }

    /**
     * Metodo que se encarga de carga los datos para las opciones del informe
     * 
     * @author giovanni.velandia
     */
    public void opcionesProceso() {
        LOGGER.debug("AdminAccidentalidadMB::opcionesProceso()");
        AdminAccidentalidadHolderFL adminAccidHolderFL = findFlowObject(AdminAccidentalidadHolderFL.class,
                AdminAccidentalidadHolderFL.NOMBRE_BEAN);

        // Ver anexos
        if (adminAccidHolderFL.getRespuestaAccidentalidadSelDTO().isDocumentos()) {
            adminAccidHolderFL.setAnexos(true);
        } else {
            adminAccidHolderFL.setAnexos(false);
        }
    }

    /**
     * Metodo que se encarga de actualizar el informe de IPAT
     * 
     * @author giovanni.velandia
     * 
     */
    public boolean actualizarInforme() {
        LOGGER.debug("AdminAccidentalidadMB::actualizarInforme()");
        AdminAccidentalidadFL adminAccidFL = findFlowObject(AdminAccidentalidadFL.class,
                AdminAccidentalidadFL.NOMBRE_BEAN);

        Calendar menosUnMes = Calendar.getInstance();
        menosUnMes.setTime(UtilFecha.currentZeroTimeDate());
        menosUnMes.add(Calendar.MONTH, -1);

        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getParteFinalizado()
                .compareTo(UtilFecha.currentZeroTimeDate()) >= 0) {
            getFacesContext().addMessage("form-contenido:parteFinalizado", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_actual")));
            return false;
        }
        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getParteFinalizado()
                .compareTo(menosUnMes.getTime()) < 0) {
            getFacesContext().addMessage("form-contenido:parteFinalizado", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_mes")));
            return false;
        }

        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getParteFinalizado()
                .after(adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAccidente())) {
            getFacesContext().addMessage("form-contenido:parteFinalizado", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_acc")));
        }

        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAccidente()
                .compareTo(UtilFecha.currentZeroTimeDate()) >= 0) {
            getFacesContext().addMessage("form-contenido:fechaAccidente", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_actual")));
            return false;
        }
        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAccidente()
                .compareTo(menosUnMes.getTime()) < 0) {
            getFacesContext().addMessage("form-contenido:fechaAccidente", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_mes")));
            return false;
        }

        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAviso()
                .compareTo(UtilFecha.currentZeroTimeDate()) >= 0) {
            getFacesContext().addMessage("form-contenido:fechaAvisoCentral", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_actual")));
            return false;
        }
        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAviso()
                .compareTo(menosUnMes.getTime()) < 0) {
            getFacesContext().addMessage("form-contenido:fechaAvisoCentral", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_mes")));
            return false;
        }
        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAviso()
                .compareTo(adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAccidente()) < 0) {
            getFacesContext().addMessage("form-contenido:fechaAvisoCentral", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_acc")));
            return false;
        }

        try {
            adminAccidFL.getDatosAccidentalidadDTO()
                    .setLsDetalleAccidentalidad(new ArrayList<DetalleAccidentalidadDTO>());
            for (RegistroAccidentalidadDTO registroAccidentalidadDTO : tabs) {
                adminAccidFL.getDatosAccidentalidadDTO().getLsDetalleAccidentalidad()
                        .add(registroAccidentalidadDTO.getDetalleAccidentalidadDTO());
            }

            iRAccidentalidad.modificarAccidentalidad(adminAccidFL.getDatosAccidentalidadDTO());
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('popUpConfirIPAT').show();");
        adminAccidFL.setConfirmarIPAT(true);
        adminAccidFL
                .setMensajeConfirmIPAT(MessageFormat.format(getBundle(NOMBRE_BUNDLE).getString("msg_confir_IPA_mod"),
                        adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getConsecutivo()));

        return true;
    }

    /**
     * Cierra la ventana de confirmacion del registro del formulario IPAT
     * 
     * @author giovanni.velandia
     */
    public void confirmacionRegistroIPAT() {
        LOGGER.debug("AdminAgentesMB::confirmacionRegistroIPAT()");
        AdminAccidentalidadFL adminAccidFL = findFlowObject(AdminAccidentalidadFL.class,
                AdminAccidentalidadFL.NOMBRE_BEAN);
        AdminAccidentalidadHolderFL adminAccidHolderFL = findFlowObject(AdminAccidentalidadHolderFL.class,
                AdminAccidentalidadHolderFL.NOMBRE_BEAN);

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('popUpConfirIPAT').hide();");

        adminAccidFL.setConfirmarIPAT(false);
        adminAccidFL.setCapturarInforme(false);

        adminAccidHolderFL.getFiltrosAccidentalidadDTO()
                .setConsecutivo(adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getConsecutivo());
    }

    /**
     * Metodo que se encarga de registrar el informe de IPAT
     * 
     * @author divier.casas
     * 
     */
    public boolean guardar() {
        LOGGER.debug("AdminAccidentalidadMB::guardar()");
        AdminAccidentalidadFL adminAccidFL = findFlowObject(AdminAccidentalidadFL.class,
                AdminAccidentalidadFL.NOMBRE_BEAN);

        Calendar menosUnMes = Calendar.getInstance();
        menosUnMes.setTime(UtilFecha.currentZeroTimeDate());
        menosUnMes.add(Calendar.MONTH, -1);

        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getParteFinalizado()
                .compareTo(UtilFecha.currentZeroTimeDate()) >= 0) {
            getFacesContext().addMessage("form-contenido:parteFinalizado", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_actual")));
            return false;
        }
        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getParteFinalizado()
                .compareTo(menosUnMes.getTime()) < 0) {
            getFacesContext().addMessage("form-contenido:parteFinalizado", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_mes")));
            return false;
        }

        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getParteFinalizado()
                .compareTo(adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAccidente()) < 0) {
            getFacesContext().addMessage("form-contenido:parteFinalizado", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_acc")));
            return false;
        }

        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAccidente()
                .compareTo(UtilFecha.currentZeroTimeDate()) >= 0) {
            getFacesContext().addMessage("form-contenido:fechaAccidente", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_actual")));
            return false;
        }
        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAccidente()
                .compareTo(menosUnMes.getTime()) < 0) {
            getFacesContext().addMessage("form-contenido:fechaAccidente", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_mes")));
            return false;
        }

        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAviso()
                .compareTo(UtilFecha.currentZeroTimeDate()) >= 0) {
            getFacesContext().addMessage("form-contenido:fechaAvisoCentral", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_actual")));
            return false;
        }
        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAviso()
                .compareTo(menosUnMes.getTime()) < 0) {
            getFacesContext().addMessage("form-contenido:fechaAvisoCentral", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_mes")));
            return false;
        }
        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAviso()
                .compareTo(adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getFechaAccidente()) < 0) {
            getFacesContext().addMessage("form-contenido:fechaAvisoCentral", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_v_acc")));
            return false;
        }

        try {
            adminAccidFL.getDatosAccidentalidadDTO()
                    .setLsDetalleAccidentalidad(new ArrayList<DetalleAccidentalidadDTO>());
            for (RegistroAccidentalidadDTO registroAccidentalidadDTO : tabs) {
                adminAccidFL.getDatosAccidentalidadDTO().getLsDetalleAccidentalidad()
                        .add(registroAccidentalidadDTO.getDetalleAccidentalidadDTO());
            }

            // Objeto temporal del informe de accidentalidad
            datosAccidentalidadDTO = adminAccidFL.getDatosAccidentalidadDTO();

            adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad()
                    .setConsecutivo(iRAccidentalidad.registrarAccidentalidad(adminAccidFL.getDatosAccidentalidadDTO()));
            // Objeto temporal del informe de accidentalidad
            consecutivoInforme = adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getConsecutivo();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        adminAccidFL.setCapturarInforme(true);

        adminAccidFL.setMensajeConfirmIPAT(MessageFormat.format(getBundle(NOMBRE_BUNDLE).getString("msg_confir_IPA"),
                adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getConsecutivo()));
        return true;
    }

    /**
     * Genera una pestaña para preparar una nueva parte
     * 
     * @author divier.casas
     */
    public void adicionarParte() {
        LOGGER.debug("AdminAccidentalidadMB::adicionarParte");
        RequestContext context = RequestContext.getCurrentInstance();
        RegistroAccidentalidadDTO nuevoTab = new RegistroAccidentalidadDTO();

        nuevoTab.setDetalleAccidentalidadDTO(new DetalleAccidentalidadDTO());
        nuevoTab.getDetalleAccidentalidadDTO().setTipoPersonaIPAT(new TipoPersonaIPATDTO());
        nuevoTab.getDetalleAccidentalidadDTO().setEstadoFisico(new EstadoFisicoDTO());

        String mensajeRegistro = getBundle(NOMBRE_BUNDLE).getString("titulo_tab");
        mensajeRegistro = MessageFormat.format(mensajeRegistro, tabs.size() + 1);
        nuevoTab.setNombreTab(mensajeRegistro);
        nuevoTab.setIdTab(tabs.size());
        tabs.add(nuevoTab);
        context.execute("PF('tabView').select(" + (this.tabs.size() - 1) + ")");
    }

    /**
     * Se encarga de estructurar los datos de la persona en el formulario
     * 
     * @param idTipoPersona
     */
    public void cambiarEstructuraFormulario(Integer idTipoPersona) {
        LOGGER.debug("AdminAccidentalidadMB::cambiarEstructuraFormulario(Integer)");

        RegistroAccidentalidadDTO regAcc = tabs.get(getActiveIndex());

        regAcc.getDetalleAccidentalidadDTO().setTipoVehiculo(null);
        regAcc.getDetalleAccidentalidadDTO().setPlaca(null);
        regAcc.getDetalleAccidentalidadDTO().setTipoServicio(null);
        regAcc.getDetalleAccidentalidadDTO().setCalleCarretera(null);
        regAcc.getDetalleAccidentalidadDTO().setSentido(null);
        regAcc.getDetalleAccidentalidadDTO().setCarril(null);
        regAcc.getDetalleAccidentalidadDTO().setRetenido(null);
        regAcc.getDetalleAccidentalidadDTO().setTipoLicencia(null);
        regAcc.getDetalleAccidentalidadDTO().setClaseVehiculo(null);

        if (idTipoPersona.equals(EnumTipoPersonaAccidente.CONDUCTOR.getValue())) {
            regAcc.setConductor(true);

            regAcc.getDetalleAccidentalidadDTO().setTipoServicio(new TipoServicioDTO());
            regAcc.getDetalleAccidentalidadDTO().setSentido(new SentidoDTO());
            regAcc.getDetalleAccidentalidadDTO().setCarril(new CarrilDTO());
            regAcc.getDetalleAccidentalidadDTO().setClaseVehiculo(new ClaseVehiculoDTO());
            regAcc.getDetalleAccidentalidadDTO().setTipoLicencia(new TipoCategLicenciaConduccionDTO());
        } else {
            regAcc.setConductor(false);
        }

        if (idTipoPersona.equals(EnumTipoPersonaAccidente.PASAJERO.getValue())) {
            regAcc.setPasajero(true);
        } else {
            regAcc.setPasajero(false);
        }
    }

    /**
     * Se encarga de detectar que tipo de persona es en el formulario
     * 
     * @author giovanni.velandia
     * @param idTipoPersona
     */
    public void pintarEstructuraFormulario(Integer idTipoPersona, Integer indexTab) {
        LOGGER.debug("AdminAgentesMB::cambiarEstructuraFormulario(Integer,Integer)");

        RegistroAccidentalidadDTO regAcc = tabs.get(indexTab);

        if (idTipoPersona.equals(EnumTipoPersonaAccidente.CONDUCTOR.getValue())) {
            regAcc.setConductor(true);
        } else {
            regAcc.setConductor(false);
        }

        if (idTipoPersona.equals(EnumTipoPersonaAccidente.PASAJERO.getValue())) {
            regAcc.setPasajero(true);
        } else {
            regAcc.setPasajero(false);
        }
    }

    /**
     * Permite cargar un archivo al listado de documents
     * 
     * @param event
     * @author giovanni.velandia
     */
    public void cargaArchivoEvidencia(FileUploadEvent event) {
        LOGGER.debug("AdminAgentesMB::cargaArchivoEvidencia(FileUploadEvent)");
        final UploadedFile file = event.getFile();

        AdminAccidentalidadFL adminAccidFL = findFlowObject(AdminAccidentalidadFL.class,
                AdminAccidentalidadFL.NOMBRE_BEAN);

        if (adminAccidFL.getDatosAccidentalidadDTO().getAdjuntos() == null) {
            adminAccidFL.getDatosAccidentalidadDTO().setAdjuntos(new ArrayList<AdjuntosAccidentalidadDTO>());
        }

        AdjuntosAccidentalidadDTO adjuntosAccidentalidadDTO = new AdjuntosAccidentalidadDTO();
        consecutivo++;
        adjuntosAccidentalidadDTO.setAdjuntos(new ArchivoAccidentalidadDTO());
        adjuntosAccidentalidadDTO.setArchivo(file.getContents());
        adjuntosAccidentalidadDTO.getAdjuntos().setNombreArchivo(file.getFileName());
        adjuntosAccidentalidadDTO.setConsecutivo(adjuntosAccidentalidadDTO.getConsecutivo() + consecutivo);

        adminAccidFL.getDatosAccidentalidadDTO().getAdjuntos().add(adjuntosAccidentalidadDTO);

    }

    /**
     * Muestra el detalle de accidentalidad
     * 
     * @author giovanni.velandia
     */
    public void consultarDetalle() {
        LOGGER.debug("AdminAgentesMB::consultarDetalle()");
        AdminAccidentalidadHolderFL adminAccidHolderFL = findFlowObject(AdminAccidentalidadHolderFL.class,
                AdminAccidentalidadHolderFL.NOMBRE_BEAN);

        AdminAccidentalidadFL adminAccidFL = findFlowObject(AdminAccidentalidadFL.class,
                AdminAccidentalidadFL.NOMBRE_BEAN);

        if (tabs != null && !tabs.isEmpty()) {
            tabs.clear();
        }

        adminAccidFL.setDatosAccidentalidadDTO(iRAccidentalidad
                .consultarAccidentalidad(adminAccidHolderFL.getRespuestaAccidentalidadSelDTO().getIdAccidentalidad()));

        if (adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().getPrevencion() == null) {
            adminAccidFL.getDatosAccidentalidadDTO().getAccidentalidad().setPrevencion(new PrevencionDTO());
        }

        RegistroAccidentalidadDTO tab = null;
        if (adminAccidFL.getDatosAccidentalidadDTO().getLsDetalleAccidentalidad() != null
                && !adminAccidFL.getDatosAccidentalidadDTO().getLsDetalleAccidentalidad().isEmpty()) {
            for (DetalleAccidentalidadDTO detalle : adminAccidFL.getDatosAccidentalidadDTO()
                    .getLsDetalleAccidentalidad()) {
                tab = new RegistroAccidentalidadDTO();
                tab.setDetalleAccidentalidadDTO(detalle);

                String mensajeRegistro = getBundle(NOMBRE_BUNDLE).getString("titulo_tab");
                mensajeRegistro = MessageFormat.format(mensajeRegistro, tabs.size() + 1);
                tab.setNombreTab(mensajeRegistro);
                tab.setIdTab(tabs.size());
                tabs.add(tab);

                pintarEstructuraFormulario(tab.getDetalleAccidentalidadDTO().getTipoPersonaIPAT().getId(),
                        tab.getIdTab());
            }
        }
    }

    /**
     * Tipos de archivos recibidos en el registro de documentos para la accidentalidad
     * 
     * @author giovanni.velandia
     * @return
     */
    public String getTiposArchivosPermitidos() {
        return TIPOS_ARCHIVOS_PERMITIDOS;
    }

    public String getMesErrLimitSize() {
        return MessageFormat.format(getBundle(NOMBRE_BUNDLE).getString("msg_error_tamanio_archivo"), tamanioMaximoArch);
    }

    public void onTabChange(TabChangeEvent event) {
        activeIndex = ((TabView) event.getSource()).getIndex();
    }

    public void onTabClose(TabChangeEvent event) {
        activeIndex = ((TabView) event.getSource()).getIndex();
    }

    public List<RegistroAccidentalidadDTO> getTabs() {
        return tabs;
    }

    public void setTabs(List<RegistroAccidentalidadDTO> tabs) {
        this.tabs = tabs;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public int getTamanioMaximoArch() {
        return tamanioMaximoArch;
    }

    public void setTamanioMaximoArch(int tamanioMaximoArch) {
        this.tamanioMaximoArch = tamanioMaximoArch;
    }

    public Date getFechaActual() {
        Date fechaActual = UtilFecha.sumarDias(UtilFecha.currentZeroTimeDate(), -1);
        fechaActual = DateUtils.addHours(fechaActual, HORA);
        fechaActual = DateUtils.addMinutes(fechaActual, MINUTOS);
        fechaActual = DateUtils.addSeconds(fechaActual, SEGUNDOS);
        return fechaActual;
    }

    public Date getFechaMinimaConsulta() {
        return fechaMinimaConsulta;
    }

    public void setFechaMinimaConsulta(Date fechaMinimaConsulta) {
        this.fechaMinimaConsulta = fechaMinimaConsulta;
    }

    public Date getFechaMaxParteFinalizado() {
        return fechaMaxParteFinalizado;
    }

    public void setFechaMaxParteFinalizado(Date fechaMaxParteFinalizado) {
        this.fechaMaxParteFinalizado = fechaMaxParteFinalizado;
    }

    public List<byte[]> getCapturasInforme() {
        return capturasInforme;
    }

    public void setCapturasInforme(List<byte[]> capturasInforme) {
        this.capturasInforme = capturasInforme;
    }

    public DatosAccidentalidadDTO getDatosAccidentalidadDTO() {
        return datosAccidentalidadDTO;
    }

    public void setDatosAccidentalidadDTO(DatosAccidentalidadDTO datosAccidentalidadDTO) {
        this.datosAccidentalidadDTO = datosAccidentalidadDTO;
    }

    public String getConsecutivoInforme() {
        return consecutivoInforme;
    }

    public void setConsecutivoInforme(String consecutivoInforme) {
        this.consecutivoInforme = consecutivoInforme;
    }
}