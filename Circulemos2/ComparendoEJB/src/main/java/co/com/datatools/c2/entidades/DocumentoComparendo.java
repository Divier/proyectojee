package co.com.datatools.c2.entidades;

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

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "documento_comparendo")
@NamedQueries({ @NamedQuery(name = "DocumentoComparendo.findAll", query = "SELECT d FROM DocumentoComparendo d") })
public class DocumentoComparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento_comparendo")
    private Long id;

    @JoinColumn(name = "id_trazabilidad_comparendo", referencedColumnName = "id_trazabilidad_comparendo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TrazabilidadComparendo trazabilidadComparendo;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    public DocumentoComparendo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrazabilidadComparendo getTrazabilidadComparendo() {
        return trazabilidadComparendo;
    }

    public void setTrazabilidadComparendo(TrazabilidadComparendo trazabilidadComparendo) {
        this.trazabilidadComparendo = trazabilidadComparendo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

}
