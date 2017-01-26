package co.com.datatools.c2.managed_bean.comparendo.administracion.anulacion;

import java.util.Date;

import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * @author diego.fonseca
 * 
 */
public class AnulacionFL extends AbstractC2ManagedBean {

    /**
     * 
     * Variables que almacena los datos para guardar la anulación
     */
    private static final long serialVersionUID = 1L;
    private String correoInstitucional;
    private Date fechaCorreo;
    private String justificacionAnulacion;
    private String primerNombreAutoridadTransito;
    private String segundoNombreAutoridadTransito;
    private String primerApellidoAutoridadTransito;
    private String segundoApellidoAutoridadTransito;
    public static final String NOMBRE_BEAN = "anulacionFL";

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public Date getFechaCorreo() {
        return fechaCorreo;
    }

    public void setFechaCorreo(Date fechaCorreo) {
        this.fechaCorreo = fechaCorreo;
    }

    public String getJustificacionAnulacion() {
        return justificacionAnulacion;
    }

    public void setJustificacionAnulacion(String justificacionAnulacion) {
        this.justificacionAnulacion = justificacionAnulacion;
    }

    public String getPrimerNombreAutoridadTransito() {
        return primerNombreAutoridadTransito;
    }

    public void setPrimerNombreAutoridadTransito(String primerNombreAutoridadTransito) {
        this.primerNombreAutoridadTransito = primerNombreAutoridadTransito;
    }

    public String getSegundoNombreAutoridadTransito() {
        return segundoNombreAutoridadTransito;
    }

    public void setSegundoNombreAutoridadTransito(String segundoNombreAutoridadTransito) {
        this.segundoNombreAutoridadTransito = segundoNombreAutoridadTransito;
    }

    public String getPrimerApellidoAutoridadTransito() {
        return primerApellidoAutoridadTransito;
    }

    public void setPrimerApellidoAutoridadTransito(String primerApellidoAutoridadTransito) {
        this.primerApellidoAutoridadTransito = primerApellidoAutoridadTransito;
    }

    public String getSegundoApellidoAutoridadTransito() {
        return segundoApellidoAutoridadTransito;
    }

    public void setSegundoApellidoAutoridadTransito(String segundoApellidoAutoridadTransito) {
        this.segundoApellidoAutoridadTransito = segundoApellidoAutoridadTransito;
    }

}
