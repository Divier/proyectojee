package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Valores para mostrar en la tabla de resultados de la consulta de la trazabilidad asociada al cobro coactivo
 * 
 * @author divier.casas
 * 
 */
public class RespuestaTrazabilidadDTO implements EntidadDtoC2 {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long idTrazabilidadPro;

    private String actividad;
    private Date fechaIniActividad;
    private String numActoAdmin;
    private String usuario;
    private List<DocumentoProcesoDTO> lsDocumentosProc;

    public Long getIdTrazabilidadPro() {
        return idTrazabilidadPro;
    }

    public void setIdTrazabilidadPro(Long idTrazabilidadPro) {
        this.idTrazabilidadPro = idTrazabilidadPro;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Date getFechaIniActividad() {
        return fechaIniActividad;
    }

    public void setFechaIniActividad(Date fechaIniActividad) {
        this.fechaIniActividad = fechaIniActividad;
    }

    public String getNumActoAdmin() {
        return numActoAdmin;
    }

    public void setNumActoAdmin(String numActoAdmin) {
        this.numActoAdmin = numActoAdmin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<DocumentoProcesoDTO> getLsDocumentosProc() {
        return lsDocumentosProc;
    }

    public void setLsDocumentosProc(List<DocumentoProcesoDTO> lsDocumentosProc) {
        this.lsDocumentosProc = lsDocumentosProc;
    }
}