package co.com.datatools.c2.managed_bean.coactivo.autorizacion;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;

import co.com.datatools.c2.dto.AprobacionPrecoactivoDTO;
import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.c2.managed_bean.comun.UtilidadMB;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.FormatoReporte;
import co.com.datatools.c2.reporte.GeneradorReporte;
import co.com.datatools.c2.reporte.data.AbstractConectorPlantillaReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.util.date.UtilFecha;

/**
 * Se encarga de autorizar los coactivos
 * 
 * @author julio.pinzon 2016-09-29
 * 
 */
@ManagedBean
@SessionScoped
public class AutorizaCoactivoMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AutorizaCoactivoMB.class.getName());
    /**
     * Nombre del atributo a buscar en el ServletContext para obtener el objeto de tipo {@link AbstractConectorPlantillaReporte} para usar en la
     * aplicacion
     */
    private static final String NOMBRE_OBJ_CONECTOR_PLANTILLA = "REPORTES_CONECTOR_PLANTILLAS";

    private static final String REPORT_ID = "REPORTE_AUTORIZA_COACTIVOS";

    private static final String NOMBRE_ARCHIVO = "Reporte Autoriza Coactivos";

    private static final String BUNDLE_COACTIVO = "labelCoactivo";

    @EJB
    private IRCoactivo iRCoactivo;

    @ManagedProperty(value = "#{utilidadMB}")
    private UtilidadMB utilidadMB;

    /**
     * Consulta los precoactivos que se pueden autorizar o no
     * 
     * @author julio.pinzon 2016-09-29
     */
    public DefaultStreamedContent consultar() {
        LOGGER.debug("AutorizaCoactivoMB::consultar()");
        AutorizaCoactivoFL autorizaCoactivoFL = findFlowObject(AutorizaCoactivoFL.class,
                AutorizaCoactivoFL.NOMBRE_BEAN);
        autorizaCoactivoFL.setDescargaArchivo(false);

        List<AprobacionPrecoactivoDTO> precoactivos = iRCoactivo.consultarPrecoactivoAprobacion();
        autorizaCoactivoFL.setPrecoactivos(precoactivos);

        autorizaCoactivoFL.setMultasAutorizar(
                MessageFormat.format(getBundle(BUNDLE_COACTIVO).getString("msg_multas_validas"), precoactivos.size()));

        if (precoactivos == null || precoactivos.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            return null;
        } else {
            ContenidoReporte contenido = new ContenidoReporte();
            List<Object> listaContenido = new ArrayList<Object>();
            List<Object> registros;
            for (AprobacionPrecoactivoDTO aprobacionPrecoactivoDTO : precoactivos) {
                registros = new ArrayList<Object>();

                registros.add(aprobacionPrecoactivoDTO.getTipoIdentificacion());
                registros.add(aprobacionPrecoactivoDTO.getNumeroIdentificacion());
                registros.add(aprobacionPrecoactivoDTO.getNombreCompleto());
                registros.add(aprobacionPrecoactivoDTO.getDireccion());
                registros.add(aprobacionPrecoactivoDTO.getMedioImposicion());
                registros.add(aprobacionPrecoactivoDTO.getIdFacturaAxis().toString());
                registros.add(aprobacionPrecoactivoDTO.getNumeroCitacion());
                registros.add(UtilFecha.dateToString(aprobacionPrecoactivoDTO.getFechaInfraccion(),
                        utilidadMB.getFormatoFechaCompleta()));
                if (aprobacionPrecoactivoDTO.getFechaNotificacion() != null) {
                    registros.add(UtilFecha.dateToString(aprobacionPrecoactivoDTO.getFechaNotificacion(),
                            utilidadMB.getFormatoFechaCompleta()));
                } else {
                    registros.add(null);
                }
                DecimalFormat formatoMoneda = new DecimalFormat(utilidadMB.getFormatoMonedaCompleta());
                registros.add(formatoMoneda.format(aprobacionPrecoactivoDTO.getValorMulta()));
                registros.add(aprobacionPrecoactivoDTO.getCodigoInfraccion());
                registros.add(aprobacionPrecoactivoDTO.getDescripcionInfraccion());

                listaContenido.add(registros);
            }
            contenido.setContenido(listaContenido);
            super.obtenerEncabezadoReporte(contenido);
            contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, new ArrayList<>());

            contenido.setCodigo(REPORT_ID);

            final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                    .getContext();

            final GeneradorReporte generadorReporte = new GeneradorReporte(
                    (AbstractConectorPlantillaReporte) context.getAttribute(NOMBRE_OBJ_CONECTOR_PLANTILLA));

            autorizaCoactivoFL.setDescargaArchivo(true);
            return new DefaultStreamedContent(
                    new ByteArrayInputStream(generadorReporte.asBytes(FormatoReporte.XLSX, contenido, REPORT_ID)),
                    FormatoReporte.XLSX.contentType, NOMBRE_ARCHIVO + "." + FormatoReporte.XLSX);
            // CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(precoactivos.size());
        }
    }

    /**
     * Autorizar todos los precoactivos
     * 
     * @author julio.pinzon 2016-09-29
     */
    public void autorizar(FuncionarioDTO secretario) {
        LOGGER.debug("AutorizaCoactivoMB::autorizar()");
        AutorizaCoactivoFL autorizaCoactivoFL = findFlowObject(AutorizaCoactivoFL.class,
                AutorizaCoactivoFL.NOMBRE_BEAN);
        autorizaCoactivoFL.setDescargaArchivo(false);
        iRCoactivo.autorizarPrecoactivos(autorizaCoactivoFL.getPrecoactivos(), secretario);
        String mensaje = MessageFormat.format(getBundle(BUNDLE_COACTIVO).getString("msg_confirmacion"),
                secretario.getPersona().getNombreCompleto());
        autorizaCoactivoFL.setDescargaArchivo(false);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensaje));
    }

    /**
     * No autorizar todos los precoactivos
     * 
     * @author julio.pinzon 2016-09-29
     */
    public void noAutorizar() {
        LOGGER.debug("AutorizaCoactivoMB::noAutorizar()");
        AutorizaCoactivoFL autorizaCoactivoFL = findFlowObject(AutorizaCoactivoFL.class,
                AutorizaCoactivoFL.NOMBRE_BEAN);
        addInfoMessage(BUNDLE_COACTIVO, "msg_no_autoriza");
        autorizaCoactivoFL.setDescargaArchivo(false);
    }

    public UtilidadMB getUtilidadMB() {
        return utilidadMB;
    }

    public void setUtilidadMB(UtilidadMB utilidadMB) {
        this.utilidadMB = utilidadMB;
    }

}