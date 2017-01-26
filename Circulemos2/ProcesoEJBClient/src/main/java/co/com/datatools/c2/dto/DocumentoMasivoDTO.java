package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Valores para documentos masivos
 * 
 */
public class DocumentoMasivoDTO implements EntidadDtoC2 {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date fechaSolicitud;
    private UsuarioPersonaDTO usuario;
    private Integer limiteDocumentos;
    private Integer cantidadDocumentos;
    private String rutaGeneracion;
    private Date fechaProcesado;
    private Long idArchivoGenerado;

    public DocumentoMasivoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public UsuarioPersonaDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public Integer getLimiteDocumentos() {
        return limiteDocumentos;
    }

    public void setLimiteDocumentos(Integer limiteDocumentos) {
        this.limiteDocumentos = limiteDocumentos;
    }

    public Integer getCantidadDocumentos() {
        return cantidadDocumentos;
    }

    public void setCantidadDocumentos(Integer cantidadDocumentos) {
        this.cantidadDocumentos = cantidadDocumentos;
    }

    public String getRutaGeneracion() {
        return rutaGeneracion;
    }

    public void setRutaGeneracion(String rutaGeneracion) {
        this.rutaGeneracion = rutaGeneracion;
    }

    public Date getFechaProcesado() {
        return fechaProcesado;
    }

    public void setFechaProcesado(Date fechaProcesado) {
        this.fechaProcesado = fechaProcesado;
    }

    public Long getIdArchivoGenerado() {
        return idArchivoGenerado;
    }

    public void setIdArchivoGenerado(Long idArchivoGenerado) {
        this.idArchivoGenerado = idArchivoGenerado;
    }

}