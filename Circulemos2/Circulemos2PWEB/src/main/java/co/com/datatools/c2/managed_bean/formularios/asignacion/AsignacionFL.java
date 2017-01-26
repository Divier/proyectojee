package co.com.datatools.c2.managed_bean.formularios.asignacion;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.formularios.RangoDisponibleDTO;
import co.com.datatools.c2.dto.formularios.ResultadoConsultaDetalleCambioEstado;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * @author rodrigo.cruz
 * 
 */
public class AsignacionFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "asignacionFL";

    private Integer idTipoFormulario;
    private String nombreTipoFormulario;
    private Integer idTipoResponsable;
    private String nombreTipoAsignacion;
    private String folio;
    private String numDocSoporte;
    private Integer idTipoDocResponsable;
    private String placaResponsable;
    private String numeroDocResponsable;

    private String numInicial;
    private String numFinal;
    private Integer cantidadFormularios;
    private Date fechaAsignacion;
    private Date fechaMaxima;
    private String observaciones;
    private String nombreResponsable;
    private String emailResponsable;
    private String nombreOrganismo;
    private Long idResponsable;
    private boolean deshabilitarBotonGuardar = true;
    private boolean msgNoExisteStock;
    private Integer idArea;
    private String nombreTipoIdentificacion;
    private BigInteger idDetalleCambioEstado;
    private boolean deshabilitarDatosResponsable;

    private Integer codigoOrganismoConsulta;
    private Integer stockMinimoForm;
    private Integer stockMaximoForm;
    private Integer cantidadFormulariosResponsable;
    private Integer maximoAsignable;
    private Integer idEstadoFormulario;
    private String nombreArchivo;
    private UploadedFile documentoAutorizacion;
    private Integer idCausalCambioEstado;

    private List<String> formulariosAsignar;
    private List<RangoDisponibleDTO> rangosDisponibles;

    /**
     * Indica si el valor de formulariosa asignar es inferior al minimo configurado y dispara la visualizacion de un popup informativo de confirmacion
     */
    private boolean cantidadInferiorMinimo;
    private boolean cantidadInferiorMaximo;

    public AsignacionFL() {
    }

    public void inicializarAsignacionFl(ResultadoConsultaDetalleCambioEstado resultadoSeleccionado) {
        this.idDetalleCambioEstado = resultadoSeleccionado.getIdDetalleCambioEstado();
        this.idTipoFormulario = resultadoSeleccionado.getIdTipoFormulario();
        this.nombreTipoFormulario = resultadoSeleccionado.getNombreTipoFormulario();
        this.idTipoResponsable = resultadoSeleccionado.getIdTipoAsignacion();
        this.nombreTipoAsignacion = resultadoSeleccionado.getNombreTipoAsignacion();
        this.idResponsable = resultadoSeleccionado.getIdResponsable().longValue();
        this.nombreResponsable = resultadoSeleccionado.getNombreResponsable();
        this.idArea = resultadoSeleccionado.getIdArea();
        this.nombreOrganismo = resultadoSeleccionado.getNombreArea();
        this.idTipoDocResponsable = resultadoSeleccionado.getIdTipoIdentificacion();
        this.nombreTipoIdentificacion = resultadoSeleccionado.getNombreTipoIdentificacion();
        this.numeroDocResponsable = resultadoSeleccionado.getNumeroIdentificacion();
        this.folio = resultadoSeleccionado.getFolio();
        this.numInicial = resultadoSeleccionado.getNumeroInicial();
        this.numFinal = resultadoSeleccionado.getNumeroFinal();
        this.cantidadFormularios = resultadoSeleccionado.getCantidadFormularios();
        this.fechaAsignacion = resultadoSeleccionado.getFechaMovimiento();
        this.observaciones = resultadoSeleccionado.getObservaciones();
        this.placaResponsable = resultadoSeleccionado.getPlaca();
    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public String getNombreTipoFormulario() {
        return nombreTipoFormulario;
    }

    public void setNombreTipoFormulario(String nombreTipoFormulario) {
        this.nombreTipoFormulario = nombreTipoFormulario;
    }

    public Integer getIdTipoResponsable() {
        return idTipoResponsable;
    }

    public void setIdTipoResponsable(Integer idTipoResponsable) {
        this.idTipoResponsable = idTipoResponsable;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNumDocSoporte() {
        return numDocSoporte;
    }

    public void setNumDocSoporte(String numDocSoporte) {
        this.numDocSoporte = numDocSoporte;
    }

    public Integer getIdTipoDocResponsable() {
        return idTipoDocResponsable;
    }

    public void setIdTipoDocResponsable(Integer idTipoDocResponsable) {
        this.idTipoDocResponsable = idTipoDocResponsable;
    }

    public String getPlacaResponsable() {
        return placaResponsable;
    }

    public void setPlacaResponsable(String placaResponsable) {
        this.placaResponsable = placaResponsable;
    }

    public String getNumeroDocResponsable() {
        return numeroDocResponsable;
    }

    public void setNumeroDocResponsable(String numeroDocResponsable) {
        this.numeroDocResponsable = numeroDocResponsable;
    }

    public String getNumInicial() {
        return numInicial;
    }

    public void setNumInicial(String numInicial) {
        this.numInicial = numInicial;
    }

    public String getNumFinal() {
        return numFinal;
    }

    public void setNumFinal(String numFinal) {
        this.numFinal = numFinal;
    }

    public Integer getCantidadFormularios() {
        return cantidadFormularios;
    }

    public void setCantidadFormularios(Integer cantidadFormularios) {
        this.cantidadFormularios = cantidadFormularios;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getEmailResponsable() {
        return emailResponsable;
    }

    public void setEmailResponsable(String emailResponsable) {
        this.emailResponsable = emailResponsable;
    }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public Long getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Long idResponsable) {
        this.idResponsable = idResponsable;
    }

    public boolean isDeshabilitarBotonGuardar() {
        return deshabilitarBotonGuardar;
    }

    public void setDeshabilitarBotonGuardar(boolean deshabilitarBotonGuardar) {
        this.deshabilitarBotonGuardar = deshabilitarBotonGuardar;
    }

    public List<String> getFormulariosAsignar() {
        return formulariosAsignar;
    }

    public void setFormulariosAsignar(List<String> formulariosAsignar) {
        this.formulariosAsignar = formulariosAsignar;
    }

    public boolean isCantidadInferiorMinimo() {
        return cantidadInferiorMinimo;
    }

    public void setCantidadInferiorMinimo(boolean cantidadInferiorMinimo) {
        this.cantidadInferiorMinimo = cantidadInferiorMinimo;
    }

    public boolean isCantidadInferiorMaximo() {
        return cantidadInferiorMaximo;
    }

    public void setCantidadInferiorMaximo(boolean cantidadInferiorMaximo) {
        this.cantidadInferiorMaximo = cantidadInferiorMaximo;
    }

    public boolean isMsgNoExisteStock() {
        return msgNoExisteStock;
    }

    public void setMsgNoExisteStock(boolean msgNoExisteStock) {
        this.msgNoExisteStock = msgNoExisteStock;
    }

    public String getNombreTipoAsignacion() {
        return nombreTipoAsignacion;
    }

    public void setNombreTipoAsignacion(String nombreTipoAsignacion) {
        this.nombreTipoAsignacion = nombreTipoAsignacion;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombreTipoIdentificacion() {
        return nombreTipoIdentificacion;
    }

    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) {
        this.nombreTipoIdentificacion = nombreTipoIdentificacion;
    }

    public BigInteger getIdDetalleCambioEstado() {
        return idDetalleCambioEstado;
    }

    public void setIdDetalleCambioEstado(BigInteger idDetalleCambioEstado) {
        this.idDetalleCambioEstado = idDetalleCambioEstado;
    }

    public boolean isDeshabilitarDatosResponsable() {
        return deshabilitarDatosResponsable;
    }

    public void setDeshabilitarDatosResponsable(boolean deshabilitarDatosResponsable) {
        this.deshabilitarDatosResponsable = deshabilitarDatosResponsable;
    }

    public Integer getCodigoOrganismoConsulta() {
        return codigoOrganismoConsulta;
    }

    public void setCodigoOrganismoConsulta(Integer codigoOrganismoConsulta) {
        this.codigoOrganismoConsulta = codigoOrganismoConsulta;
    }

    public Integer getStockMinimoForm() {
        return stockMinimoForm;
    }

    public void setStockMinimoForm(Integer stockMinimoForm) {
        this.stockMinimoForm = stockMinimoForm;
    }

    public Integer getStockMaximoForm() {
        return stockMaximoForm;
    }

    public void setStockMaximoForm(Integer stockMaximoForm) {
        this.stockMaximoForm = stockMaximoForm;
    }

    public Integer getCantidadFormulariosResponsable() {
        return cantidadFormulariosResponsable;
    }

    public void setCantidadFormulariosResponsable(Integer cantidadFormulariosResponsable) {
        this.cantidadFormulariosResponsable = cantidadFormulariosResponsable;
    }

    public Integer getMaximoAsignable() {
        return maximoAsignable;
    }

    public void setMaximoAsignable(Integer maximoAsignable) {
        this.maximoAsignable = maximoAsignable;
    }

    public Integer getIdEstadoFormulario() {
        return idEstadoFormulario;
    }

    public void setIdEstadoFormulario(Integer idEstadoFormulario) {
        this.idEstadoFormulario = idEstadoFormulario;
    }

    public List<RangoDisponibleDTO> getRangosDisponibles() {
        return rangosDisponibles;
    }

    public void setRangosDisponibles(List<RangoDisponibleDTO> rangosDisponibles) {
        this.rangosDisponibles = rangosDisponibles;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public UploadedFile getDocumentoAutorizacion() {
        return documentoAutorizacion;
    }

    public void setDocumentoAutorizacion(UploadedFile documentoAutorizacion) {
        this.documentoAutorizacion = documentoAutorizacion;
    }

    public Integer getIdCausalCambioEstado() {
        return idCausalCambioEstado;
    }

    public void setIdCausalCambioEstado(Integer idCausalCambioEstado) {
        this.idCausalCambioEstado = idCausalCambioEstado;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

}
