package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class LogAmnistiaDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private int idLogAmnistia;
    private Date fechaOperacion;
    private String informacionAmnistia;
    private String operacion;
    private Integer idAmnistia;
    private UsuarioPersonaDTO usuarioDTO;

    // Constructors Declaration

    public LogAmnistiaDTO() {
    }

    // Start Methods Declaration

    public int getIdLogAmnistia() {
        return idLogAmnistia;
    }

    public void setIdLogAmnistia(int idLogAmnistia) {
        this.idLogAmnistia = idLogAmnistia;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getInformacionAmnistia() {
        return informacionAmnistia;
    }

    public void setInformacionAmnistia(String informacionAmnistia) {
        this.informacionAmnistia = informacionAmnistia;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Integer getIdAmnistia() {
        return this.idAmnistia;
    }

    public void setIdAmnistia(Integer idAmnistia) {
        this.idAmnistia = idAmnistia;
    }

    public UsuarioPersonaDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioPersonaDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

}