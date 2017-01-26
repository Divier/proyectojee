package co.com.datatools.sogit.managed_bean.financiacion;

import java.util.List;

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

import co.com.datatools.fachadainetegracionweb.dto.DetalleFinanciacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FinanciacionConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FinanciacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoReplicaDTO;
import co.com.datatools.fachadainetegracionweb.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.sogit.utilies.AbstractPortalManagedBean;
import co.com.datatools.sogit.utilies.CirculemosAccesoBundleGeneral;
import co.com.datatools.sogit.utilies.EnumCatalogo;
import co.com.datatools.sogit.utilies.EnumNavegacion;
import co.com.datatools.sogit.utilies.ExpresionesRegulares;

/**
 * Consulta las financiaciones de axis
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaFinanciacionMB extends AbstractPortalManagedBean {

    private final static Logger LOGGER = Logger.getLogger(ConsultaFinanciacionMB.class.getName());
    private static final long serialVersionUID = 1L;

    private FinanciacionConsultaDTO financiacionConsultaDTO;
    private List<FinanciacionReplicaDTO> financiacionReplicaDTOs;
    private FinanciacionReplicaDTO financiacionReplicaSelDTO;

    private static String SERVICIO_REST_FINANCIACION = "/FachadaIntegracionWeb/rest/consultarFinanciaciones";
    private static String SERVICIO_REST_FINANCIACION_DET = "/FachadaIntegracionWeb/rest/consultarDetalleFinanciacion/";

    private boolean alfanumerico;

    // Detalle
    private DetalleFinanciacionReplicaDTO detalleFinanciacionReplicaDTO;

    public ConsultaFinanciacionMB() {
        LOGGER.debug("ConsultaFinanciacionMB()");
        financiacionConsultaDTO = new FinanciacionConsultaDTO();
        financiacionReplicaDTOs = null;
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

        if (financiacionConsultaDTO.getIdIdentificacion() != null) {
            if (financiacionConsultaDTO.getIdIdentificacion()
                    .equals(Integer.valueOf(EnumTipoIdentificacion.PASAPORTE.getValor()))) {
                alfanumerico = true;
            } else {
                alfanumerico = false;
            }
        }

    }

    /**
     * Consulta financiaciones
     * 
     * @author giovanni.velandia
     * @return
     */
    public void consultarFinanciaciones() {
        LOGGER.debug("consultarFinanciaciones()");

        // Validaciones persona
        if (financiacionConsultaDTO.getIdIdentificacion() != null) {
            if (financiacionConsultaDTO.getIdentificacion() == null) {
                String idTxt = "txtNumDoc";
                if (financiacionConsultaDTO.getIdIdentificacion().equals(EnumTipoIdentificacion.PASAPORTE.getValor())) {
                    idTxt = "txtNumDocAlfn";
                }
                getFacesContext().addMessage("form-contenido:" + idTxt,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;

            }
        }

        if (financiacionConsultaDTO.getIdentificacion() != null) {
            if (financiacionConsultaDTO.getIdIdentificacion() == null) {
                getFacesContext().addMessage("form-contenido:selOnTipDoc",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        if (financiacionConsultaDTO.getIdIdentificacion() == null
                && financiacionConsultaDTO.getIdentificacion() == null) {
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

        String url = getServer() + SERVICIO_REST_FINANCIACION;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        ItemCatalogoReplicaDTO itemCatalogoReplicaDTO = getCatalogosApp().getItemCatalogoId(
                EnumCatalogo.TipoIdentificacionPersona, financiacionConsultaDTO.getIdIdentificacion());
        financiacionConsultaDTO.setCodigoIdentificacion(itemCatalogoReplicaDTO.getCodigo());

        financiacionReplicaDTOs = target.request(MediaType.APPLICATION_XML).post(
                Entity.entity(financiacionConsultaDTO, MediaType.APPLICATION_XML),
                new GenericType<List<FinanciacionReplicaDTO>>() {
                });

        if (financiacionReplicaDTOs == null || financiacionReplicaDTOs.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            return;
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(financiacionReplicaDTOs.size());
        }
    }

    /**
     * Consulta detalle financiacion
     * 
     * @author giovanni.velandia
     * @return
     */
    public void consultarDetalleFinanciacion() {
        LOGGER.debug("consultarDetalleFinanciacion()");
        String url = getServer() + SERVICIO_REST_FINANCIACION_DET + financiacionReplicaSelDTO.getNumeroFinanciacion();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        detalleFinanciacionReplicaDTO = target.request(MediaType.APPLICATION_XML)
                .get(DetalleFinanciacionReplicaDTO.class);
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

    public FinanciacionConsultaDTO getFinanciacionConsultaDTO() {
        return financiacionConsultaDTO;
    }

    public void setFinanciacionConsultaDTO(FinanciacionConsultaDTO financiacionConsultaDTO) {
        this.financiacionConsultaDTO = financiacionConsultaDTO;
    }

    public List<FinanciacionReplicaDTO> getFinanciacionReplicaDTOs() {
        return financiacionReplicaDTOs;
    }

    public void setFinanciacionReplicaDTOs(List<FinanciacionReplicaDTO> financiacionReplicaDTOs) {
        this.financiacionReplicaDTOs = financiacionReplicaDTOs;
    }

    public FinanciacionReplicaDTO getFinanciacionReplicaSelDTO() {
        return financiacionReplicaSelDTO;
    }

    public void setFinanciacionReplicaSelDTO(FinanciacionReplicaDTO financiacionReplicaSelDTO) {
        this.financiacionReplicaSelDTO = financiacionReplicaSelDTO;
    }

    public DetalleFinanciacionReplicaDTO getDetalleFinanciacionReplicaDTO() {
        return detalleFinanciacionReplicaDTO;
    }

    public void setDetalleFinanciacionReplicaDTO(DetalleFinanciacionReplicaDTO detalleFinanciacionReplicaDTO) {
        this.detalleFinanciacionReplicaDTO = detalleFinanciacionReplicaDTO;
    }

    public boolean isAlfanumerico() {
        return alfanumerico;
    }

    public void setAlfanumerico(boolean alfanumerico) {
        this.alfanumerico = alfanumerico;
    }
}
