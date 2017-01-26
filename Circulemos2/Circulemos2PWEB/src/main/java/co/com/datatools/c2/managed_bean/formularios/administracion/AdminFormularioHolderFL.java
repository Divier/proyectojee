package co.com.datatools.c2.managed_bean.formularios.administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.SeguimientoFormularioDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Clase que encapsula los datos para la consulta de rangos
 * 
 * @author claudia.rodriguez
 * 
 */
public class AdminFormularioHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "adminFormularioHolderFL";

    private Integer idTipoFormulario;
    private Integer idTipoResponsable;
    private String nombreTipoFormulario;
    private Integer idTipoDocResponsable;
    private String nombreTipoDocResponsable;
    private String numeroDocResponsable;
    private Integer idOrganismoTransito;
    private Integer codigoOrganismoResponsable;
    private Integer idEstadoFormulario;
    private Date fechaAplicacionEstadoInicial;
    private Date fechaAplicacionEstadoFinal;
    private boolean empresa;
    private boolean organismo;
    private boolean esAnular;
    private boolean esDevolver;
    private boolean esEditar;
    private boolean esConsultar;
    private String nombreArchivo;
    private int cantidadFormularios;

    private DetalleCambioEstadoDTO cambioEstadoSeleccionado;

    private List<SeguimientoFormularioDTO> detalleFormularios;
    private List<DetalleCambioEstadoDTO> resultadoConsulta;

    public AdminFormularioHolderFL() {
        detalleFormularios = new ArrayList<SeguimientoFormularioDTO>();
        cambioEstadoSeleccionado = new DetalleCambioEstadoDTO();
    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public Integer getIdTipoResponsable() {
        return idTipoResponsable;
    }

    public void setIdTipoResponsable(Integer idTipoResponsable) {
        this.idTipoResponsable = idTipoResponsable;
    }

    public String getNombreTipoFormulario() {
        return nombreTipoFormulario;
    }

    public void setNombreTipoFormulario(String nombreTipoFormulario) {
        this.nombreTipoFormulario = nombreTipoFormulario;
    }

    public Integer getIdTipoDocResponsable() {
        return idTipoDocResponsable;
    }

    public void setIdTipoDocResponsable(Integer idTipoDocResponsable) {
        this.idTipoDocResponsable = idTipoDocResponsable;
    }

    public String getNumeroDocResponsable() {
        return numeroDocResponsable;
    }

    public void setNumeroDocResponsable(String numeroDocResponsable) {
        this.numeroDocResponsable = numeroDocResponsable;
    }

    public Integer getIdOrganismoTransito() {
        return idOrganismoTransito;
    }

    public void setIdOrganismoTransito(Integer idOrganismoTransito) {
        this.idOrganismoTransito = idOrganismoTransito;
    }

    public Integer getCodigoOrganismoResponsable() {
        return codigoOrganismoResponsable;
    }

    public void setCodigoOrganismoResponsable(Integer codigoOrganismoResponsable) {
        this.codigoOrganismoResponsable = codigoOrganismoResponsable;
    }

    public Integer getIdEstadoFormulario() {
        return idEstadoFormulario;
    }

    public void setIdEstadoFormulario(Integer idEstadoFormulario) {
        this.idEstadoFormulario = idEstadoFormulario;
    }

    public Date getFechaAplicacionEstadoInicial() {
        return fechaAplicacionEstadoInicial;
    }

    public void setFechaAplicacionEstadoInicial(Date fechaAplicacionEstadoInicial) {
        this.fechaAplicacionEstadoInicial = fechaAplicacionEstadoInicial;
    }

    public Date getFechaAplicacionEstadoFinal() {
        return fechaAplicacionEstadoFinal;
    }

    public void setFechaAplicacionEstadoFinal(Date fechaAplicacionEstadoFinal) {
        this.fechaAplicacionEstadoFinal = fechaAplicacionEstadoFinal;
    }

    public List<DetalleCambioEstadoDTO> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<DetalleCambioEstadoDTO> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public DetalleCambioEstadoDTO getCambioEstadoSeleccionado() {
        return cambioEstadoSeleccionado;
    }

    public void setCambioEstadoSeleccionado(DetalleCambioEstadoDTO cambioEstadoSeleccionado) {
        this.cambioEstadoSeleccionado = cambioEstadoSeleccionado;
    }

    public List<SeguimientoFormularioDTO> getDetalleFormularios() {
        return detalleFormularios;
    }

    public void setDetalleFormularios(List<SeguimientoFormularioDTO> detalleFormularios) {
        this.detalleFormularios = detalleFormularios;
    }

    public String getNombreTipoDocResponsable() {
        return nombreTipoDocResponsable;
    }

    public void setNombreTipoDocResponsable(String nombreTipoDocResponsable) {
        this.nombreTipoDocResponsable = nombreTipoDocResponsable;
    }

    public boolean isEmpresa() {
        return empresa;
    }

    public void setEmpresa(boolean empresa) {
        this.empresa = empresa;
    }

    public boolean isOrganismo() {
        return organismo;
    }

    public void setOrganismo(boolean organismo) {
        this.organismo = organismo;
    }

    public boolean isEsAnular() {
        return esAnular;
    }

    public void setEsAnular(boolean esAnular) {
        this.esAnular = esAnular;
    }

    public boolean isEsDevolver() {
        return esDevolver;
    }

    public void setEsDevolver(boolean esDevolver) {
        this.esDevolver = esDevolver;
    }

    public boolean isEsEditar() {
        return esEditar;
    }

    public void setEsEditar(boolean esEditar) {
        this.esEditar = esEditar;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int getCantidadFormularios() {
        return cantidadFormularios;
    }

    public void setCantidadFormularios(int cantidadFormularios) {
        this.cantidadFormularios = cantidadFormularios;
    }

    public boolean isEsConsultar() {
        return esConsultar;
    }

    public void setEsConsultar(boolean esConsultar) {
        this.esConsultar = esConsultar;
    }
}
