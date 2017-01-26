package co.com.datatools.c2.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "documento_formulario")
@NamedQueries({ @NamedQuery(name = "DocumentoFormulario.findAll", query = "SELECT d FROM DocumentoFormulario d") })
public class DocumentoFormulario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento_formulario")
    private Long id;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "id_documento")
    private String idDocumento;

    public DocumentoFormulario() {
    }

    public DocumentoFormulario(Long idDocumentoFormulario) {
        this.id = idDocumentoFormulario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

}
