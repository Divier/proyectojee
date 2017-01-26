package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the archivo_notificacion_simit database table.
 * 
 */
@Entity
@Table(name = "archivo_notificacion_simit")
@NamedQuery(name = "ArchivoNotificacionSimit.findAll", query = "SELECT a FROM ArchivoNotificacionSimit a")
public class ArchivoNotificacionSimit implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo_notificacion_simit")
    private Long id;

    @Basic(optional = false)
    @Column(name = "cantidad_registros")
    private Integer cantidadRegistros;

    @Basic(optional = false)
    @Column(name = "id_documento")
    private String idDocumento;

    @Basic(optional = false)
    @Column(name = "ruta_documento")
    private String rutaDocumento;

    @Column(name = "numero_oficio")
    private String numeroOficio;

    @ManyToOne
    @JoinColumn(name = "id_notificacion_simit")
    private NotificacionSimit notificacionSimit;

    public ArchivoNotificacionSimit() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadRegistros() {
        return this.cantidadRegistros;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getRutaDocumento() {
        return this.rutaDocumento;
    }

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    public NotificacionSimit getNotificacionSimit() {
        return this.notificacionSimit;
    }

    public void setNotificacionSimit(NotificacionSimit notificacionSimit) {
        this.notificacionSimit = notificacionSimit;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

}