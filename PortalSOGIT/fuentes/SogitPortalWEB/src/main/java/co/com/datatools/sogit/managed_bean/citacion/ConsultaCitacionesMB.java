package co.com.datatools.sogit.managed_bean.citacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.fachadainetegracionweb.dto.ComparendoConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ComparendoReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ComparendoReplicaDetalleDTO;
import co.com.datatools.fachadainetegracionweb.dto.EvidenciasDTO;
import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoReplicaDTO;
import co.com.datatools.fachadainetegracionweb.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.sogit.utilies.AbstractPortalManagedBean;
import co.com.datatools.sogit.utilies.CirculemosAccesoBundleGeneral;
import co.com.datatools.sogit.utilies.EnumCatalogo;
import co.com.datatools.sogit.utilies.ExpresionesRegulares;
import co.com.datatools.sogit.utilies.UtilidadMB;

/**
 * Consulta las citaciones de axis
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaCitacionesMB extends AbstractPortalManagedBean {

    private final static Logger LOGGER = Logger.getLogger(ConsultaCitacionesMB.class.getName());
    private static final long serialVersionUID = 1L;

    private ComparendoConsultaDTO comparendoConsultaDTO;
    private List<ComparendoReplicaDTO> comparendoReplicaDTOs;
    private ComparendoReplicaDetalleDTO comparendoReplicaDetalleDTO;
    private ComparendoReplicaDTO comparendoReplicaSelDTO;

    private static final String NOMBRE_BUNDLE_CITACIONES = "bundleCitaciones";

    private static String SERVICIO_REST_COMPARENDO = "/FachadaIntegracionWeb/rest/consultarComparendos";
    private static String SERVICIO_REST_COMPARENDO_DET = "/FachadaIntegracionWeb/rest/consultarDetalleComparendo/";
    private static String RUTA_EVIDENCIAS = "/FachadaIntegracionWeb/rest/rutaEvidencias/";

    private boolean alfanumerico;

    @ManagedProperty(value = "#{utilidadMB}")
    private UtilidadMB utilidadMB;

    private List<String> evidencias;
    private String rutaEvidenciasCompleta;

    public ConsultaCitacionesMB() {
        comparendoConsultaDTO = new ComparendoConsultaDTO();
        comparendoReplicaDTOs = null;
    }

    @PostConstruct
    public void init() {
        LOGGER.debug("init()");
    }

    /**
     * Se verifica el numero de documento para establecer el tipo de dato
     * 
     * @author giovanni.velandia
     */
    public void verificarTipoDocumento() {
        LOGGER.debug("verificarTipoDocumento()");

        if (comparendoConsultaDTO.getIdIdentificacion() != null) {
            if (comparendoConsultaDTO.getIdIdentificacion()
                    .equals(Integer.valueOf(EnumTipoIdentificacion.PASAPORTE.getValor()))) {
                alfanumerico = true;
            } else {
                alfanumerico = false;
            }
        }
    }

    /**
     * Consultar imagenes de una citacion
     * 
     * @author giovanni.velandia
     */
    public void consultarEvidencias() {
        LOGGER.debug("verificarTipoDocumento()");
        evidencias = new ArrayList<>();
        String rutaServidor = utilidadMB.rutaServerEvidencias();
        List<EvidenciasDTO> evidenciasDTOs = rutaEvidencias(Long.valueOf(comparendoReplicaSelDTO.getNumeroFactura()));
        if (evidenciasDTOs != null && !evidenciasDTOs.isEmpty()) {
            for (EvidenciasDTO evidenciasDTO : evidenciasDTOs) {
                evidencias.add(rutaServidor + evidenciasDTO.getRuta());
            }
        }
    }

    /**
     * Metodo que se encarga de pintar la imagen en el componente de primefaces
     * 
     * @author giovanni.velandia
     * @return
     * @throws IOException
     */
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String filename = context.getExternalContext().getRequestParameterMap().get("filename");
            return new DefaultStreamedContent(new FileInputStream(new File(rutaEvidenciasCompleta, filename)));
        }
    }

    /**
     * Consulta citaciones
     * 
     * @author giovanni.velandia
     * @return
     */
    public void consultarComparendos() {
        LOGGER.debug("consultarComparendos()");

        // Validaciones citacion
        if (comparendoConsultaDTO.getNumeroCitacion() != null) {
            if (comparendoConsultaDTO.getAnioInfraccion() == null) {
                getFacesContext().addMessage("form-contenido:calFiltroAnio",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        // Validaciones persona
        if (comparendoConsultaDTO.getIdIdentificacion() != null) {
            if (comparendoConsultaDTO.getIdentificacion() == null) {
                String idTxt = "txtNumDoc";
                if (comparendoConsultaDTO.getIdIdentificacion().equals(EnumTipoIdentificacion.PASAPORTE.getValor())) {
                    idTxt = "txtNumDocAlfn";
                }
                getFacesContext().addMessage("form-contenido:" + idTxt,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        if (comparendoConsultaDTO.getIdentificacion() != null) {
            if (comparendoConsultaDTO.getIdIdentificacion() == null) {
                getFacesContext().addMessage("form-contenido:selOnTipDoc",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        // Validaciones minimo un filtro
        if (comparendoConsultaDTO.getIdentificacion() == null && comparendoConsultaDTO.getPlacaVehiculo() == null
                && comparendoConsultaDTO.getNumeroCitacion() == null) {
            addInfoMessage(NOMBRE_BUNDLE_CITACIONES, "msg_camp_opcional");
            return;
        }

        String url = getServer() + SERVICIO_REST_COMPARENDO;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        if (comparendoConsultaDTO.getIdIdentificacion() != null) {
            ItemCatalogoReplicaDTO itemCatalogoReplicaDTO = getCatalogosApp().getItemCatalogoId(
                    EnumCatalogo.TipoIdentificacionPersona, comparendoConsultaDTO.getIdIdentificacion());
            comparendoConsultaDTO.setCodigoIdentificacion(itemCatalogoReplicaDTO.getCodigo());
        }

        comparendoReplicaDTOs = target.request(MediaType.APPLICATION_XML).post(
                Entity.entity(comparendoConsultaDTO, MediaType.APPLICATION_XML),
                new GenericType<List<ComparendoReplicaDTO>>() {
                });

        if (comparendoReplicaDTOs == null || comparendoReplicaDTOs.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            return;
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(comparendoReplicaDTOs.size());
        }
    }

    /**
     * Consulta detalle citaciones
     * 
     * @author giovanni.velandia
     * @return
     */
    public void consultarDetalleComparendo() {
        LOGGER.debug("consultarDetalleComparendo()");
        String url = getServer() + SERVICIO_REST_COMPARENDO_DET + comparendoReplicaSelDTO.getNumeroFactura();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        comparendoReplicaDetalleDTO = target.request(MediaType.APPLICATION_XML).get(ComparendoReplicaDetalleDTO.class);
        consultarEvidencias();
    }

    /**
     * Consultar las rutas de las evidencias
     * 
     * @author giovanni.velandia
     */
    public List<EvidenciasDTO> rutaEvidencias(Long facturaAxis) {
        LOGGER.debug("rutaEvidencias()");
        List<EvidenciasDTO> evidencias = null;
        String url = getServer() + RUTA_EVIDENCIAS + facturaAxis;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        evidencias = target.request(MediaType.APPLICATION_XML).get(new GenericType<List<EvidenciasDTO>>() {
        });
        return evidencias;
    }

    /**********************
     * Expresiones regulares
     **********************/
    public String getExpresionAlfanumerica() {
        return ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO;
    }

    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }

    public ComparendoConsultaDTO getComparendoConsultaDTO() {
        return comparendoConsultaDTO;
    }

    public void setComparendoConsultaDTO(ComparendoConsultaDTO comparendoConsultaDTO) {
        this.comparendoConsultaDTO = comparendoConsultaDTO;
    }

    public List<ComparendoReplicaDTO> getComparendoReplicaDTOs() {
        return comparendoReplicaDTOs;
    }

    public void setComparendoReplicaDTOs(List<ComparendoReplicaDTO> comparendoReplicaDTOs) {
        this.comparendoReplicaDTOs = comparendoReplicaDTOs;
    }

    public ComparendoReplicaDetalleDTO getComparendoReplicaDetalleDTO() {
        return comparendoReplicaDetalleDTO;
    }

    public void setComparendoReplicaDetalleDTO(ComparendoReplicaDetalleDTO comparendoReplicaDetalleDTO) {
        this.comparendoReplicaDetalleDTO = comparendoReplicaDetalleDTO;
    }

    public ComparendoReplicaDTO getComparendoReplicaSelDTO() {
        return comparendoReplicaSelDTO;
    }

    public void setComparendoReplicaSelDTO(ComparendoReplicaDTO comparendoReplicaSelDTO) {
        this.comparendoReplicaSelDTO = comparendoReplicaSelDTO;
    }

    public boolean isAlfanumerico() {
        return alfanumerico;
    }

    public void setAlfanumerico(boolean alfanumerico) {
        this.alfanumerico = alfanumerico;
    }

    public List<String> getEvidencias() {
        return evidencias;
    }

    public void setEvidencias(List<String> evidencias) {
        this.evidencias = evidencias;
    }

    public String getRutaEvidenciasCompleta() {
        return rutaEvidenciasCompleta;
    }

    public void setRutaEvidenciasCompleta(String rutaEvidenciasCompleta) {
        this.rutaEvidenciasCompleta = rutaEvidenciasCompleta;
    }

    public UtilidadMB getUtilidadMB() {
        return utilidadMB;
    }

    public void setUtilidadMB(UtilidadMB utilidadMB) {
        this.utilidadMB = utilidadMB;
    }
}
