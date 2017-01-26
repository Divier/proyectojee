package co.com.datatools.seguridad.dto.comun;

import java.io.Serializable;
import java.util.Date;

/**
 * @author felipe.martinez
 */
public class InfoAutorizacion implements Serializable {
    private static final long serialVersionUID = -8251890813400371683L;

    private String nombreRecurso;
    private String nombreOperacion;
    private Date horaSolicitud;
    private boolean permitido;
    private String descripcionRecurso;

    /**
     * Crea un registo de autorizacion en la hora actual
     * 
     * @param nombreRecurso
     *            nombre del recurso
     * @param nombreOperacion
     *            nombre de la operacion
     * @param permitido
     *            indica si fue o no autorizado
     */
    public InfoAutorizacion(String nombreRecurso, String nombreOperacion, boolean permitido) {
        super();
        this.nombreRecurso = nombreRecurso;
        this.nombreOperacion = nombreOperacion;
        this.horaSolicitud = new Date();
        this.permitido = permitido;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public Date getHoraSolicitud() {
        return horaSolicitud;
    }

    public void setHoraSolicitud(Date horaSolicitud) {
        this.horaSolicitud = horaSolicitud;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }

    public String getDescripcionRecurso() {
        return descripcionRecurso;
    }

    public void setDescripcionRecurso(String descripcionRecurso) {
        this.descripcionRecurso = descripcionRecurso;
    }

    @Override
    public String toString() {
        return "InfoAutorizacion {nombreRecurso: " + nombreRecurso + ", nombreOperacion: " + nombreOperacion
                + ", horaSolicitud: " + horaSolicitud + ", permitido: " + permitido + "}";
    }
}