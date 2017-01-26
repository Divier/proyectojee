package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jeison.Rodriguez
 */
public class HistoricoFalloDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String revision;
    private Date fechaRevision;
    private String observacionRevision;
    private String observacion;

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getObservacionRevision() {
        return observacionRevision;
    }

    public void setObservacionRevision(String observacionRevision) {
        this.observacionRevision = observacionRevision;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}