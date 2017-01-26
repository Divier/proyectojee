package co.com.datatools.sogit.managed_bean.gaceta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.dto.DetalleGacetaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FechaPublicacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.FiltrosGacetaDTO;
import co.com.datatools.fachadainetegracionweb.dto.GacetaDTO;
import co.com.datatools.sogit.managed_bean.citacion.ConsultaCitacionesMB;
import co.com.datatools.sogit.utilies.AbstractPortalManagedBean;
import co.com.datatools.sogit.utilies.EnumNavegacion;
import co.com.datatools.sogit.utilies.ExpresionesRegulares;
import co.com.datatools.sogit.utilies.UtilidadMB;

/**
 * Se encarga de las publicaciones de las notificaciones de citaciones a los ciudadanos
 * 
 * @author giovanni.velandia
 *
 */
@ManagedBean
@SessionScoped
public class GacetaMB extends AbstractPortalManagedBean {

    private final static Logger LOGGER = Logger.getLogger(ConsultaCitacionesMB.class.getName());
    private static final long serialVersionUID = 1L;

    private static String SERVICIO_REST_GACETA = "/FachadaIntegracionWeb/rest/consultarGaceta";
    private static String SERVICIO_REST_DET_GACETA = "/FachadaIntegracionWeb/rest/consultarDetalleGaceta";
    private static String SERVICIO_REST_DET_GACETA_FILTROS = "/FachadaIntegracionWeb/rest/consultarDetalleGacetaFiltros";
    private static String SERVICIO_REST_GACETA_FECHAS_P = "/FachadaIntegracionWeb/rest/consultarFechasPublicacion";

    private static final String NOMBRE_BUNDLE_GACETA = "bundleGaceta";

    private GacetaDTO gacetaDTO;

    private List<DetalleGacetaDTO> detalleGacetaDTOs;
    private List<FechaPublicacionDTO> fechasPublicacionDTOs;
    private FechaPublicacionDTO fechaPublicacionDTO;
    private FiltrosGacetaDTO filtrosGacetaDTO;

    private List<SelectItem> fechasPublicacion;

    // OBjeto para el filtro
    private List<DetalleGacetaDTO> detalleGacetaFiltroDTOs;
    private String formatoFecha;
    private boolean buscarPublicAnteriores;

    private String fechaSeleccionada;
    private String fechaPublicacionSel;

    private boolean datosPublicacion;

    public GacetaMB() {
        fechaPublicacionDTO = new FechaPublicacionDTO();
        filtrosGacetaDTO = new FiltrosGacetaDTO();
    }

    @PostConstruct
    public void init() {
        LOGGER.debug("init()");
        UtilidadMB utilidadMB = new UtilidadMB();
        formatoFecha = utilidadMB.getFormatoFecha();

        consultarFechasPublicacion();
        if (fechasPublicacionDTOs != null && !fechasPublicacionDTOs.isEmpty()) {
            String fechaTemp = convertirFecha(fechasPublicacionDTOs.get(0).getFechaPublicacion());
            Date fechaSelDate = convertirFecha(fechaTemp);
            fechaPublicacionDTO.setFechaPublicacion(fechaSelDate);

            fechaSeleccionada = convertirFecha(fechaPublicacionDTO.getFechaPublicacion());
            fechaPublicacionSel = convertirFecha(fechaPublicacionDTO.getFechaPublicacion());
        }
        consultarGaceta();
        consultarPublicaciones();

    }

    /**
     * Convierte la fecha
     * 
     * @author giovanni.velandia
     * @return
     */
    private Date convertirFecha(String fecha) {
        LOGGER.debug("convertirFecha(String)");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatoFecha);
            return simpleDateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convierte la fecha
     * 
     * @author giovanni.velandia
     * @return
     */
    public String convertirFecha(Date fecha) {
        LOGGER.debug("convertirFecha(Date)");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatoFecha);
        return simpleDateFormat.format(fecha);
    }

    /**
     * Metodo que se encarga de consultar la informacion de la gaceta
     */
    private void consultarGaceta() {
        LOGGER.debug("consultarGaceta()");

        String url = getServer() + SERVICIO_REST_GACETA;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        gacetaDTO = target.request(MediaType.APPLICATION_XML)
                .post(Entity.entity(fechaPublicacionDTO, MediaType.APPLICATION_XML), GacetaDTO.class);
        if (gacetaDTO != null) {
            datosPublicacion = true;
        }
    }

    /**
     * Se encarga de consultar las publicaciones con una fecha determinada
     * 
     * @author giovanni.velandia
     */
    private void consultarPublicaciones() {
        LOGGER.debug("consultarPublicaciones()");

        String url = getServer() + SERVICIO_REST_DET_GACETA;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        detalleGacetaDTOs = target.request(MediaType.APPLICATION_XML).post(
                Entity.entity(fechaPublicacionDTO, MediaType.APPLICATION_XML),
                new GenericType<List<DetalleGacetaDTO>>() {
                });
    }

    /**
     * Metodo que se encarga de cosnultar las publicaciones por fecha
     * 
     * @author giovanni.velandia
     */
    public void consultarPublicacionesFecha() {
        LOGGER.debug("consultarPublicacionesFecha()");
        Date fechaSelDate = convertirFecha(fechaSeleccionada);
        fechaPublicacionDTO.setFechaPublicacion(fechaSelDate);
        consultarPublicaciones();
        consultarGaceta();
    }

    /**
     * Se encarga de consultar todas las fechas de publicacion de las gacetas
     * 
     * @author giovanni.velandia
     */
    private void consultarFechasPublicacion() {
        LOGGER.debug("consultarFechasPublicacion()");
        fechasPublicacionDTOs = new ArrayList<>();
        String url = getServer() + SERVICIO_REST_GACETA_FECHAS_P;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        fechasPublicacionDTOs = target.request(MediaType.APPLICATION_XML)
                .get(new GenericType<List<FechaPublicacionDTO>>() {
                });

        fechasPublicacion = new ArrayList<>();
        if (fechasPublicacionDTOs != null && !fechasPublicacionDTOs.isEmpty()) {
            for (FechaPublicacionDTO fechaPublicacionDTO : fechasPublicacionDTOs) {
                String fechaTemp = convertirFecha(fechaPublicacionDTO.getFechaPublicacion());
                fechasPublicacion.add(new SelectItem(fechaTemp, fechaTemp));
            }
        }
    }

    /**
     * Se encarga de dirigir a la consulta de la gaceta
     * 
     * @author giovanni.velandia
     * @return
     */
    public String activarConsultaGaceta() {
        filtrosGacetaDTO = new FiltrosGacetaDTO();
        return EnumNavegacion.consultarGaceta.getRuta();
    }

    /**
     * Se encarga de consultar las publicaciones con filtros determinados
     * 
     * @author giovanni.velandia
     */
    public void consultarPublicacionesFiltros() {
        LOGGER.debug("consultarPublicaciones()");

        String url = getServer() + SERVICIO_REST_DET_GACETA_FILTROS;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        if ((filtrosGacetaDTO.getPlacaVehiculo() == null || filtrosGacetaDTO.getPlacaVehiculo().isEmpty())
                && (filtrosGacetaDTO.getPropietario() == null || filtrosGacetaDTO.getPropietario().isEmpty())
                && (fechaPublicacionSel == null || fechaPublicacionSel.isEmpty())) {
            addInfoMessage(NOMBRE_BUNDLE_GACETA, "msg_camp_opcional");
            return;
        }

        if (fechaPublicacionSel.equals("todos")) {
            fechaPublicacionSel = null;
        }

        filtrosGacetaDTO.setFechaPublicacion(null);
        if (fechaPublicacionSel != null) {
            if (!buscarPublicAnteriores) {
                filtrosGacetaDTO.setFechaPublicacion(convertirFecha(fechaPublicacionSel));
            }
        }

        detalleGacetaDTOs = target.request(MediaType.APPLICATION_XML).post(
                Entity.entity(filtrosGacetaDTO, MediaType.APPLICATION_XML), new GenericType<List<DetalleGacetaDTO>>() {
                });

        // Fecha de consulta del la busqueda de filtro
        if (detalleGacetaDTOs != null && !detalleGacetaDTOs.isEmpty()) {
            String fechaTemp = convertirFecha(detalleGacetaDTOs.get(0).getGaceta().getFechaPublicacion());
            Date fechaSelDate = convertirFecha(fechaTemp);
            fechaPublicacionDTO.setFechaPublicacion(fechaSelDate);

            fechaSeleccionada = convertirFecha(fechaPublicacionDTO.getFechaPublicacion());

            gacetaDTO = detalleGacetaDTOs.get(0).getGaceta();
        }

        filtrosGacetaDTO = new FiltrosGacetaDTO();
    }

    /**
     * Se encarga de navegar a la pagina principal
     * 
     * @author giovanni.velandia
     * @return
     */
    public String gaceta() {
        consultarPublicaciones();
        return EnumNavegacion.gaceta.getRuta();
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

    /**********************
     * Expresiones regulares
     **********************/
    public String getExpresionAlfanumerica() {
        return ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO;
    }

    public String getExpresionAlfabetico() {
        return ExpresionesRegulares.REGEX_SOLO_LETRAS;
    }

    public List<DetalleGacetaDTO> getDetalleGacetaDTOs() {
        return detalleGacetaDTOs;
    }

    public void setDetalleGacetaDTOs(List<DetalleGacetaDTO> detalleGacetaDTOs) {
        this.detalleGacetaDTOs = detalleGacetaDTOs;
    }

    public FechaPublicacionDTO getFechaPublicacionDTO() {
        return fechaPublicacionDTO;
    }

    public void setFechaPublicacionDTO(FechaPublicacionDTO fechaPublicacionDTO) {
        this.fechaPublicacionDTO = fechaPublicacionDTO;
    }

    public List<DetalleGacetaDTO> getDetalleGacetaFiltroDTOs() {
        return detalleGacetaFiltroDTOs;
    }

    public void setDetalleGacetaFiltroDTOs(List<DetalleGacetaDTO> detalleGacetaFiltroDTOs) {
        this.detalleGacetaFiltroDTOs = detalleGacetaFiltroDTOs;
    }

    public List<FechaPublicacionDTO> getFechasPublicacionDTOs() {
        return fechasPublicacionDTOs;
    }

    public void setFechasPublicacionDTOs(List<FechaPublicacionDTO> fechasPublicacionDTOs) {
        this.fechasPublicacionDTOs = fechasPublicacionDTOs;
    }

    public List<SelectItem> getFechasPublicacion() {
        return fechasPublicacion;
    }

    public void setFechasPublicacion(List<SelectItem> fechasPublicacion) {
        this.fechasPublicacion = fechasPublicacion;
    }

    public String getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(String fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public GacetaDTO getGacetaDTO() {
        return gacetaDTO;
    }

    public void setGacetaDTO(GacetaDTO gacetaDTO) {
        this.gacetaDTO = gacetaDTO;
    }

    public FiltrosGacetaDTO getFiltrosGacetaDTO() {
        return filtrosGacetaDTO;
    }

    public void setFiltrosGacetaDTO(FiltrosGacetaDTO filtrosGacetaDTO) {
        this.filtrosGacetaDTO = filtrosGacetaDTO;
    }

    public boolean isBuscarPublicAnteriores() {
        return buscarPublicAnteriores;
    }

    public void setBuscarPublicAnteriores(boolean buscarPublicAnteriores) {
        this.buscarPublicAnteriores = buscarPublicAnteriores;
    }

    public String getFechaPublicacionSel() {
        return fechaPublicacionSel;
    }

    public void setFechaPublicacionSel(String fechaPublicacionSel) {
        this.fechaPublicacionSel = fechaPublicacionSel;
    }

    public boolean isDatosPublicacion() {
        return datosPublicacion;
    }

    public void setDatosPublicacion(boolean datosPublicacion) {
        this.datosPublicacion = datosPublicacion;
    }

}
