package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.List;

import co.com.datatools.c2.dto.AccionImpugnacionBackDTO;
import co.com.datatools.c2.dto.AdjuntosMotivacionImpugnacionDTO;
import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.dto.HistoricoFalloDTO;
import co.com.datatools.c2.dto.MotivacionImpugnacionDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Utilizada para flujo del detalle de proceso
 * 
 * @author julio.pinzon 2016-07-08
 *
 */
public class DetalleProcesoFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "detalleProcesoFL";

    private String descripcionInfraccion;
    private ComparendoDTO comparendo;
    private ProcesoDTO proceso;
    private FalloImpugnacionDTO fallo;
    private MotivacionImpugnacionDTO motivacionImpugnacionDTO;
    private TrazabilidadProcesoDTO trazaSeleccionada;
    private String volver;
    private AccionImpugnacionBackDTO accionSeleccionada;
    private List<AccionImpugnacionBackDTO> listaAcciones;
    private List<AdjuntosMotivacionImpugnacionDTO> lsAdjuntos;
    private AdjuntosMotivacionImpugnacionDTO adjuntoSeleccionado;

    private SolicitudPruebasBackVO solicitudSeleccionada;
    private List<SolicitudPruebasBackVO> solicitudes;
    private List<HistoricoFalloDTO> historicoFallo;

    public DetalleProcesoFL() {
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public ComparendoDTO getComparendo() {
        return comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public ProcesoDTO getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoDTO proceso) {
        this.proceso = proceso;
    }

    public String getVolver() {
        return volver;
    }

    public void setVolver(String volver) {
        this.volver = volver;
    }

    public TrazabilidadProcesoDTO getTrazaSeleccionada() {
        return trazaSeleccionada;
    }

    public void setTrazaSeleccionada(TrazabilidadProcesoDTO trazaSeleccionada) {
        this.trazaSeleccionada = trazaSeleccionada;
    }

    public FalloImpugnacionDTO getFallo() {
        return fallo;
    }

    public void setFallo(FalloImpugnacionDTO fallo) {
        this.fallo = fallo;
    }

    public AccionImpugnacionBackDTO getAccionSeleccionada() {
        return accionSeleccionada;
    }

    public void setAccionSeleccionada(AccionImpugnacionBackDTO accionSeleccionada) {
        this.accionSeleccionada = accionSeleccionada;
    }

    public List<AccionImpugnacionBackDTO> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(List<AccionImpugnacionBackDTO> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public SolicitudPruebasBackVO getSolicitudSeleccionada() {
        return solicitudSeleccionada;
    }

    public void setSolicitudSeleccionada(SolicitudPruebasBackVO solicitudSeleccionada) {
        this.solicitudSeleccionada = solicitudSeleccionada;
    }

    public List<SolicitudPruebasBackVO> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudPruebasBackVO> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<HistoricoFalloDTO> getHistoricoFallo() {
        return historicoFallo;
    }

    public void setHistoricoFallo(List<HistoricoFalloDTO> historicoFallo) {
        this.historicoFallo = historicoFallo;
    }

    public MotivacionImpugnacionDTO getMotivacionImpugnacionDTO() {
        return motivacionImpugnacionDTO;
    }

    public void setMotivacionImpugnacionDTO(MotivacionImpugnacionDTO motivacionImpugnacionDTO) {
        this.motivacionImpugnacionDTO = motivacionImpugnacionDTO;
    }

    public List<AdjuntosMotivacionImpugnacionDTO> getLsAdjuntos() {
        return lsAdjuntos;
    }

    public void setLsAdjuntos(List<AdjuntosMotivacionImpugnacionDTO> lsAdjuntos) {
        this.lsAdjuntos = lsAdjuntos;
    }

    public AdjuntosMotivacionImpugnacionDTO getAdjuntoSeleccionado() {
        return adjuntoSeleccionado;
    }

    public void setAdjuntoSeleccionado(AdjuntosMotivacionImpugnacionDTO adjuntoSeleccionado) {
        this.adjuntoSeleccionado = adjuntoSeleccionado;
    }
}