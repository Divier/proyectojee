package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the notificacion_simit database table.
 * 
 */
@Entity
@Table(name = "notificacion_simit")
@NamedQuery(name = "NotificacionSimit.findAll", query = "SELECT n FROM NotificacionSimit n")
public class NotificacionSimit implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_simit")
    private Long id;

    @Basic(optional = false)
    @Column(name = "cantidad_registros")
    private Integer cantidadRegistros;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;

    @ManyToOne
    @JoinColumn(name = "codigo_organismo")
    private OrganismoTransito codigoOrganismo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento_envio_simit")
    private TipoDocumentoEnvioSimit tipoDocumentoEnvioSimit;

    @ManyToOne
    @JoinColumn(name = "id_tipo_resultado_envio_simit")
    private TipoResultadoEnvioSimit tipoResultadoEnvioSimit;

    @OneToMany(mappedBy = "notificacionSimit", cascade = { CascadeType.PERSIST })
    private List<ArchivoNotificacionSimit> archivoNotificacionSimits;

    public NotificacionSimit() {
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

    public Date getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public TipoDocumentoEnvioSimit getTipoDocumentoEnvioSimit() {
        return this.tipoDocumentoEnvioSimit;
    }

    public void setTipoDocumentoEnvioSimit(TipoDocumentoEnvioSimit tipoDocumentoEnvioSimit) {
        this.tipoDocumentoEnvioSimit = tipoDocumentoEnvioSimit;
    }

    public TipoResultadoEnvioSimit getTipoResultadoEnvioSimit() {
        return this.tipoResultadoEnvioSimit;
    }

    public void setTipoResultadoEnvioSimit(TipoResultadoEnvioSimit tipoResultadoEnvioSimit) {
        this.tipoResultadoEnvioSimit = tipoResultadoEnvioSimit;
    }

    public List<ArchivoNotificacionSimit> getArchivoNotificacionSimits() {
        return this.archivoNotificacionSimits;
    }

    public void setArchivoNotificacionSimits(List<ArchivoNotificacionSimit> archivoNotificacionSimits) {
        this.archivoNotificacionSimits = archivoNotificacionSimits;
    }

    public OrganismoTransito getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransito codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

}