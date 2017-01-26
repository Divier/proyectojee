package co.com.datatools.sogit.managed_bean.impugnacion;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.dto.DetalleProcesoImpugnacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.ImpugnacionConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ProcesoImpugnacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.sogit.utilies.AbstractPortalManagedBean;
import co.com.datatools.sogit.utilies.CirculemosAccesoBundleGeneral;
import co.com.datatools.sogit.utilies.EnumNavegacion;
import co.com.datatools.sogit.utilies.ExpresionesRegulares;

/**
 * Consulta las impugnacion de axis
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaImpugnacionMB extends AbstractPortalManagedBean {

    private final static Logger LOGGER = Logger.getLogger(ConsultaImpugnacionMB.class.getName());
    private static final long serialVersionUID = 1L;

    private ImpugnacionConsultaDTO impugnacionConsultaDTO;
    private List<ProcesoImpugnacionReplicaDTO> procesoImpugnacionReplicaDTOs;
    private ProcesoImpugnacionReplicaDTO procesoImpugnacionReplicaSelDTO;

    private static String SERVICIO_REST_IMPUGNACION = "/FachadaIntegracionWeb/rest/consultarImpugnaciones";
    private static String SERVICIO_REST_IMPUGNACION_DET = "/FachadaIntegracionWeb/rest/consultarDetalleImpugnacion/";

    private boolean alfanumerico;

    // Detalle
    private DetalleProcesoImpugnacionDTO detalleProcesoImpugnacionDTO;

    public ConsultaImpugnacionMB() {
        LOGGER.debug("ConsultaImpugnacionMB()");
        impugnacionConsultaDTO = new ImpugnacionConsultaDTO();
        procesoImpugnacionReplicaDTOs = null;
    }

    /**
     * Se encarga de navegar a la pagina principal
     * 
     * @author giovanni.velandia
     * @return
     */
    public String menu() {
        return EnumNavegacion.principal.getRuta();
    }

    /**
     * Se verifica el numero de documento para establecer el tipo de dato
     * 
     * @author giovanni.velandia
     */
    public void verificarTipoDocumento() {
        LOGGER.debug("verificarTipoDocumento()");

        if (impugnacionConsultaDTO.getTipoIdentificacion() != null) {
            if (impugnacionConsultaDTO.getTipoIdentificacion()
                    .equals(String.valueOf(EnumTipoIdentificacion.PASAPORTE.getValor()))) {
                alfanumerico = true;
            } else {
                alfanumerico = false;
            }
        }

    }

    @PostConstruct
    public void init() {
        LOGGER.debug("init()");
    }

    /**
     * Consulta impugnaciones
     * 
     * @author giovanni.velandia
     * @return
     */
    public void consultarImpugnaciones() {
        LOGGER.debug("consultarImpugnaciones()");

        // Validaciones persona
        if (impugnacionConsultaDTO.getTipoIdentificacion() != null) {
            if (impugnacionConsultaDTO.getNumeroIdentificacion() == null) {
                String idTxt = "txtNumDoc";
                if (impugnacionConsultaDTO.getTipoIdentificacion()
                        .equals(EnumTipoIdentificacion.PASAPORTE.getValor())) {
                    idTxt = "txtNumDocAlfn";
                }
                getFacesContext().addMessage("form-contenido:" + idTxt,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        if (impugnacionConsultaDTO.getNumeroIdentificacion() != null) {
            if (impugnacionConsultaDTO.getTipoIdentificacion() == null) {
                getFacesContext().addMessage("form-contenido:selOnTipDoc",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        if (impugnacionConsultaDTO.getTipoIdentificacion() == null
                && impugnacionConsultaDTO.getNumeroIdentificacion() == null) {
            getFacesContext().addMessage("form-contenido:selOnTipDoc",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            getFacesContext().addMessage("form-contenido:txtNumDoc",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }

        String url = getServer() + SERVICIO_REST_IMPUGNACION;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        procesoImpugnacionReplicaDTOs = target.request(MediaType.APPLICATION_XML).post(
                Entity.entity(impugnacionConsultaDTO, MediaType.APPLICATION_XML),
                new GenericType<List<ProcesoImpugnacionReplicaDTO>>() {
                });

        if (procesoImpugnacionReplicaDTOs == null || procesoImpugnacionReplicaDTOs.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            return;
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(procesoImpugnacionReplicaDTOs.size());
        }
    }

    /**
     * Consulta detalle impugnacion
     * 
     * @author giovanni.velandia
     * @return
     */
    public void consultarDetalleImpugnacion() {
        LOGGER.debug("consultarDetalleImpugnacion()");
        String url = getServer() + SERVICIO_REST_IMPUGNACION_DET + procesoImpugnacionReplicaSelDTO.getIdProceso();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        detalleProcesoImpugnacionDTO = target.request(MediaType.APPLICATION_XML)
                .get(DetalleProcesoImpugnacionDTO.class);
    }

    /**********************
     * Expresiones regulares
     **********************/
    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }

    public String getExpresionAlfanumerica() {
        return ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO;
    }

    public ImpugnacionConsultaDTO getImpugnacionConsultaDTO() {
        return impugnacionConsultaDTO;
    }

    public void setImpugnacionConsultaDTO(ImpugnacionConsultaDTO impugnacionConsultaDTO) {
        this.impugnacionConsultaDTO = impugnacionConsultaDTO;
    }

    public List<ProcesoImpugnacionReplicaDTO> getProcesoImpugnacionReplicaDTOs() {
        return procesoImpugnacionReplicaDTOs;
    }

    public void setProcesoImpugnacionReplicaDTOs(List<ProcesoImpugnacionReplicaDTO> procesoImpugnacionReplicaDTOs) {
        this.procesoImpugnacionReplicaDTOs = procesoImpugnacionReplicaDTOs;
    }

    public DetalleProcesoImpugnacionDTO getDetalleProcesoImpugnacionDTO() {
        return detalleProcesoImpugnacionDTO;
    }

    public void setDetalleProcesoImpugnacionDTO(DetalleProcesoImpugnacionDTO detalleProcesoImpugnacionDTO) {
        this.detalleProcesoImpugnacionDTO = detalleProcesoImpugnacionDTO;
    }

    public ProcesoImpugnacionReplicaDTO getProcesoImpugnacionReplicaSelDTO() {
        return procesoImpugnacionReplicaSelDTO;
    }

    public void setProcesoImpugnacionReplicaSelDTO(ProcesoImpugnacionReplicaDTO procesoImpugnacionReplicaSelDTO) {
        this.procesoImpugnacionReplicaSelDTO = procesoImpugnacionReplicaSelDTO;
    }

    public boolean isAlfanumerico() {
        return alfanumerico;
    }

    public void setAlfanumerico(boolean alfanumerico) {
        this.alfanumerico = alfanumerico;
    }

}
