package co.com.datatools.c2.managed_bean.formularios.numeracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Clase que encapsula los datos para las paginas de creacion y actualizacion de numeraciones
 * 
 * @author claudia.rodriguez
 * 
 */
public class NumeracionFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "numeracionFL";

    /**
     * Id del tipo de formulario de la numeracion
     */
    private Integer idTipoFormulario;
    /**
     * Estado(Activo/Inactivo) de la numeracion
     */
    private Boolean estado;

    /**
     * Listado con los tipos de formularios disponibles
     */
    private List<SelectItem> listaTiposFormulario;

    /**
     * Fecha de inicio de la vigencia de la numeracion
     */
    private Date fechaInicioVigencia;
    /**
     * Fecha fin de la vigencia de la numeracion
     */
    private Date fechaFinVigencia;
    /**
     * Cantidad de dígitos de la numeración
     */
    private int digitos;

    /**
     * Lista con el detalle de cada casilla de la numeracion
     */
    private List<DetalleNumeracionDTO> detalleNumeracion = new ArrayList<DetalleNumeracionDTO>();

    /**
     * Dto con los datos de una numeracion cuyos datos seran modificados
     */
    private NumeracionFormularioDTO numeracionModificar;

    public NumeracionFL() {

    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<SelectItem> getListaTiposFormulario() {
        return listaTiposFormulario;
    }

    public void setListaTiposFormulario(List<SelectItem> listaTiposFormulario) {
        this.listaTiposFormulario = listaTiposFormulario;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public int getDigitos() {
        return digitos;
    }

    public void setDigitos(int digitos) {
        this.digitos = digitos;
    }

    public List<DetalleNumeracionDTO> getDetalleNumeracion() {
        return detalleNumeracion;
    }

    public void setDetalleNumeracion(List<DetalleNumeracionDTO> detalleNumeracion) {
        this.detalleNumeracion = detalleNumeracion;
    }

    public NumeracionFormularioDTO getNumeracionModificar() {
        return numeracionModificar;
    }

    public void setNumeracionModificar(NumeracionFormularioDTO numeracionModificar) {
        this.numeracionModificar = numeracionModificar;
    }

}
