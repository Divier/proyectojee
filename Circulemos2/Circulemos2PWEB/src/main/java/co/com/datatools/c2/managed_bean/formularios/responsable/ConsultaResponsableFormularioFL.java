package co.com.datatools.c2.managed_bean.formularios.responsable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;

/**
 * Estructura de flujo para manejo de los datos de consulta de una persona, organismo o entidad a la cual este asociado un responsable de formularios
 * 
 * @author luis.forero(2015-09-02)
 * 
 */
public class ConsultaResponsableFormularioFL implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idTipoDocumento;
    private String strTipoDocumento;
    private String numeroIdentificacion;

    private List<UnificacionResponsableFL> listResultado;
    private UnificacionResponsableFL unificacionResponsableSeleccionado;

    private EnumTipoResponsableFormulario tipoResponsableFormulario;

    /**
     * 
     */
    public ConsultaResponsableFormularioFL() {
        listResultado = new ArrayList<>(0);
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getStrTipoDocumento() {
        return strTipoDocumento;
    }

    public void setStrTipoDocumento(String strTipoDocumento) {
        this.strTipoDocumento = strTipoDocumento;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public List<UnificacionResponsableFL> getListResultado() {
        return listResultado;
    }

    public void setListResultado(List<UnificacionResponsableFL> listResultado) {
        this.listResultado = listResultado;
    }

    public UnificacionResponsableFL getUnificacionResponsableSeleccionado() {
        return unificacionResponsableSeleccionado;
    }

    public void setUnificacionResponsableSeleccionado(UnificacionResponsableFL unificacionResponsableSeleccionado) {
        this.unificacionResponsableSeleccionado = unificacionResponsableSeleccionado;
    }

    public EnumTipoResponsableFormulario getTipoResponsableFormulario() {
        return tipoResponsableFormulario;
    }

    public void setTipoResponsableFormulario(EnumTipoResponsableFormulario tipoResponsableFormulario) {
        this.tipoResponsableFormulario = tipoResponsableFormulario;
    }

}
