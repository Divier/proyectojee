package co.com.datatools.seguridad.dto.autenticacion;

import java.io.Serializable;
import java.util.Date;

import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;

/**
 * Dto con la informacion basica de un HistoricoRol
 * 
 * @author claudia.rodriguez
 */
public class HistoricoRolDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idHistoricoRol;
    private Date fechaInicioRol;
    private Date fechaFinRol;
    private String xmlHistorico;
    private String usuarioModifica;
    private RolDetalleDto rolDetalleDto;

    private Integer idXsdHistorico;
    private String contenidoXsd;

    public Long getIdHistoricoRol() {
        return idHistoricoRol;
    }

    public void setIdHistoricoRol(Long idHistoricoRol) {
        this.idHistoricoRol = idHistoricoRol;
    }

    public Date getFechaInicioRol() {
        return fechaInicioRol;
    }

    public void setFechaInicioRol(Date fechaInicioRol) {
        this.fechaInicioRol = fechaInicioRol;
    }

    public Date getFechaFinRol() {
        return fechaFinRol;
    }

    public void setFechaFinRol(Date fechaFinRol) {
        this.fechaFinRol = fechaFinRol;
    }

    public String getXmlHistorico() {
        return xmlHistorico;
    }

    public void setXmlHistorico(String xmlHistorico) {
        this.xmlHistorico = xmlHistorico;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public RolDetalleDto getRolDetalleDto() {
        return rolDetalleDto;
    }

    public void setRolDetalleDto(RolDetalleDto rolDetalleDto) {
        this.rolDetalleDto = rolDetalleDto;
    }

    public Integer getIdXsdHistorico() {
        return idXsdHistorico;
    }

    public void setIdXsdHistorico(Integer idXsdHistorico) {
        this.idXsdHistorico = idXsdHistorico;
    }

    public String getContenidoXsd() {
        return contenidoXsd;
    }

    public void setContenidoXsd(String contenidoXsd) {
        this.contenidoXsd = contenidoXsd;
    }

}
