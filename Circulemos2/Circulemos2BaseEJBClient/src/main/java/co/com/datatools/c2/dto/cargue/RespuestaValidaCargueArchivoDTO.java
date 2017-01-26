package co.com.datatools.c2.dto.cargue;

import java.io.Serializable;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 11:58:04 COT 2016
 */
public class RespuestaValidaCargueArchivoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private boolean archivoCargado;
    private boolean archivoProcesado;
    private boolean archivoEnProceso;
    private boolean sinCargueActivo;
    private boolean archivoEnProcesoOtroUsuario;
    private CargueArchivoDTO cargue;

    // --- Constructor
    public RespuestaValidaCargueArchivoDTO() {
    }

    public boolean isArchivoCargado() {
        return archivoCargado;
    }

    public void setArchivoCargado(boolean archivoCargado) {
        this.archivoCargado = archivoCargado;
    }

    public boolean isArchivoProcesado() {
        return archivoProcesado;
    }

    public void setArchivoProcesado(boolean archivoProcesado) {
        this.archivoProcesado = archivoProcesado;
    }

    public boolean isArchivoEnProceso() {
        return archivoEnProceso;
    }

    public void setArchivoEnProceso(boolean archivoEnProceso) {
        this.archivoEnProceso = archivoEnProceso;
    }

    public boolean isSinCargueActivo() {
        return sinCargueActivo;
    }

    public void setSinCargueActivo(boolean sinCargueActivo) {
        this.sinCargueActivo = sinCargueActivo;
    }

    public CargueArchivoDTO getCargue() {
        return cargue;
    }

    public void setCargue(CargueArchivoDTO cargue) {
        this.cargue = cargue;
    }

    public boolean isArchivoEnProcesoOtroUsuario() {
        return archivoEnProcesoOtroUsuario;
    }

    public void setArchivoEnProcesoOtroUsuario(boolean archivoEnProcesoOtroUsuario) {
        this.archivoEnProcesoOtroUsuario = archivoEnProcesoOtroUsuario;
    }

}
