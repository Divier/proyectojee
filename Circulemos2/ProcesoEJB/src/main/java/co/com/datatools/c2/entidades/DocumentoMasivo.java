package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the documento_masivo database table.
 * 
 */
@Entity
@Table(name = "documento_masivo")
@NamedQuery(name = "DocumentoMasivo.findAll", query = "SELECT d FROM DocumentoMasivo d")
public class DocumentoMasivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento_masivo")
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioPersona usuario;

    @Column(name = "limite_documentos")
    private Integer limiteDocumentos;

    @Column(name = "cantidad_documentos")
    private Integer cantidadDocumentos;

    @Column(name = "ruta_generacion")
    private String rutaGeneracion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_procesado")
    private Date fechaProcesado;

    @Column(name = "id_archivo_generado")
    private Long idArchivoGenerado;

    @OneToMany(mappedBy = "documentoMasivo", fetch = FetchType.LAZY)
    private List<DetalleDocumentoMasivo> detalles;

    public DocumentoMasivo() {
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

    public UsuarioPersona getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersona usuario) {
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

    public List<DetalleDocumentoMasivo> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDocumentoMasivo> detalles) {
        this.detalles = detalles;
    }

    public Long getIdArchivoGenerado() {
        return idArchivoGenerado;
    }

    public void setIdArchivoGenerado(Long idArchivoGenerado) {
        this.idArchivoGenerado = idArchivoGenerado;
    }

}