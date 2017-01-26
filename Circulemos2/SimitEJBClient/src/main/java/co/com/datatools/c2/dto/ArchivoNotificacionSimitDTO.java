package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 02 15:31:39 COT 2015
 */
public class ArchivoNotificacionSimitDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer cantidadRegistros;
    private String idDocumento;
    private String rutaDocumento;
    private String numeroOficio;
    private NotificacionSimitDTO notificacionSimit;

    // --- Constructor
    public ArchivoNotificacionSimitDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getRutaDocumento() {
        return rutaDocumento;
    }

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public NotificacionSimitDTO getNotificacionSimit() {
        return notificacionSimit;
    }

    public void setNotificacionSimit(NotificacionSimitDTO notificacionSimit) {
        this.notificacionSimit = notificacionSimit;
    }

}
