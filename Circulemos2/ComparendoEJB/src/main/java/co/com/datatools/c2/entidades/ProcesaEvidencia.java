package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "procesa_evidencia")
@NamedQueries({ @NamedQuery(name = "ProcesaEvidencia.findAll", query = "SELECT p FROM ProcesaEvidencia p") })
public class ProcesaEvidencia implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_procesa_evidencia")
    private Integer id;

    @JoinColumn(name = "id_procesa_comparendo", referencedColumnName = "id_procesamiento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProcesaComparendo procesaComparendo;

    @Column(name = "codigo_tipo_evidencia")
    private Integer codigoTipoEvidencia;

    @Column(name = "fecha_evidencia")
    @Temporal(TemporalType.DATE)
    private Date fechaEvidencia;

    @Column(name = "id_documento")
    private String idDocumento;

    @Column(name = "url")
    private String url;

    @Column(name = "nombre_evidencia")
    private String nombre_evidencia;

    public ProcesaEvidencia() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoTipoEvidencia() {
        return codigoTipoEvidencia;
    }

    public void setCodigoTipoEvidencia(Integer codigoTipoEvidencia) {
        this.codigoTipoEvidencia = codigoTipoEvidencia;
    }

    public Date getFechaEvidencia() {
        return fechaEvidencia;
    }

    public void setFechaEvidencia(Date fechaEvidencia) {
        this.fechaEvidencia = fechaEvidencia;
    }

    public ProcesaComparendo getProcesaComparendo() {
        return procesaComparendo;
    }

    public void setProcesaComparendo(ProcesaComparendo procesaComparendo) {
        this.procesaComparendo = procesaComparendo;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre_evidencia() {
        return nombre_evidencia;
    }

    public void setNombre_evidencia(String nombre_evidencia) {
        this.nombre_evidencia = nombre_evidencia;
    }

}
