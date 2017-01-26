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
 * The persistent class for the detalle_documento_masivo database table.
 * 
 */
@Entity
@Table(name = "detalle_documento_masivo")
@NamedQuery(name = "DetalleDocumentoMasivo.findAll", query = "SELECT d FROM DetalleDocumentoMasivo d")
public class DetalleDocumentoMasivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_documento_masivo")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_documento_masivo")
    private DocumentoMasivo documentoMasivo;

    @Column(name = "id_documento")
    private Long idDocumento;

    public DetalleDocumentoMasivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DocumentoMasivo getDocumentoMasivo() {
        return documentoMasivo;
    }

    public void setDocumentoMasivo(DocumentoMasivo documentoMasivo) {
        this.documentoMasivo = documentoMasivo;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

}