package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the documento_proceso database table.
 * 
 */
@Entity
@Table(name = "documento_proceso")
@NamedQuery(name = "DocumentoProceso.findAll", query = "SELECT d FROM DocumentoProceso d")
public class DocumentoProceso implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento_proceso")
    private Long id;

    @Column(name = "numero_documento")
    private Long numeroDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trazabilidad_proceso")
    private TrazabilidadProceso trazabilidadProceso;

    @Column(name = "id_comparendo_proceso")
    private Integer idComparendoProceso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento_proceso")
    private TipoDocumentoProceso tipoDocumento;

    @JoinColumn(name = "id_firma_persona", referencedColumnName = "id_firma_persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FirmaPersona firma;

    @Column(name = "responsable_generacion")
    private String responsableGeneracion;

    public DocumentoProceso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public TrazabilidadProceso getTrazabilidadProceso() {
        return trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProceso trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public Integer getIdComparendoProceso() {
        return idComparendoProceso;
    }

    public void setIdComparendoProceso(Integer idComparendoProceso) {
        this.idComparendoProceso = idComparendoProceso;
    }

    public TipoDocumentoProceso getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoProceso tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public FirmaPersona getFirma() {
        return firma;
    }

    public void setFirma(FirmaPersona firma) {
        this.firma = firma;
    }

    public String getResponsableGeneracion() {
        return responsableGeneracion;
    }

    public void setResponsableGeneracion(String responsableGeneracion) {
        this.responsableGeneracion = responsableGeneracion;
    }

}