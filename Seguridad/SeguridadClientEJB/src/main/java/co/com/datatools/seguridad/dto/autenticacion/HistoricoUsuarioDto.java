package co.com.datatools.seguridad.dto.autenticacion;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto con la informacion basica de un HistoricoUsuario
 * 
 * @author claudia.rodriguez
 */
public class HistoricoUsuarioDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String password;
    private Date fechaModPass;
    private Date fechaInicio;
    private Date fechaFin;
    private String xmlHistorico;
    private String descripcionCambio;
    private String usuarioRealizaCambio;
    private boolean cierraHistoricoAnterior;

    private UsuarioDetalleDto usuarioDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaModPass() {
        return fechaModPass;
    }

    public void setFechaModPass(Date fechaModPass) {
        this.fechaModPass = fechaModPass;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getXmlHistorico() {
        return xmlHistorico;
    }

    public void setXmlHistorico(String xmlHistorico) {
        this.xmlHistorico = xmlHistorico;
    }

    public UsuarioDetalleDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDetalleDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public String getDescripcionCambio() {
        return descripcionCambio;
    }

    public void setDescripcionCambio(String descripcionCambio) {
        this.descripcionCambio = descripcionCambio;
    }

    public String getUsuarioRealizaCambio() {
        return usuarioRealizaCambio;
    }

    public void setUsuarioRealizaCambio(String usuarioRealizaCambio) {
        this.usuarioRealizaCambio = usuarioRealizaCambio;
    }

    public boolean isCierraHistoricoAnterior() {
        return cierraHistoricoAnterior;
    }

    public void setCierraHistoricoAnterior(boolean cierraHistoricoAnterior) {
        this.cierraHistoricoAnterior = cierraHistoricoAnterior;
    }

}
