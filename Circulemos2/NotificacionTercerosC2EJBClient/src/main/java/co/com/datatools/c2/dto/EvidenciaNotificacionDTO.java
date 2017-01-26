package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 14:18:51 COT 2016
 */
public class EvidenciaNotificacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private DetalleNotificacionDTO detalleNotificacion;
    private String codigoEvidencia;
    private Date fechaModificacion;
    private UsuarioPersonaDTO usuario;
    private String idEvidenciaExterno;
    private Integer tipoEvidencia;

    // --- Constructor
    public EvidenciaNotificacionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleNotificacionDTO getDetalleNotificacion() {
        return detalleNotificacion;
    }

    public void setDetalleNotificacion(DetalleNotificacionDTO detalleNotificacion) {
        this.detalleNotificacion = detalleNotificacion;
    }

    public String getCodigoEvidencia() {
        return codigoEvidencia;
    }

    public void setCodigoEvidencia(String codigoEvidencia) {
        this.codigoEvidencia = codigoEvidencia;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public UsuarioPersonaDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public String getIdEvidenciaExterno() {
        return idEvidenciaExterno;
    }

    public void setIdEvidenciaExterno(String idEvidenciaExterno) {
        this.idEvidenciaExterno = idEvidenciaExterno;
    }

    public Integer getTipoEvidencia() {
        return tipoEvidencia;
    }

    public void setTipoEvidencia(Integer tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }
}