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
 * The persistent class for the rectifica_evidencia database table.
 * 
 */
@Entity
@Table(name = "rectifica_evidencia")
@NamedQuery(name = "RectificaEvidencia.findAll", query = "SELECT r FROM RectificaEvidencia r")
public class RectificaEvidencia implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rectifica_evidencia")
    private Long id;

    @Basic(optional = false)
    @Column(name = "codigo_tipo_evidencia")
    private Integer codigoTipoEvidencia;

    @Basic(optional = false)
    @Column(name = "id_documento")
    private String idDocumento;

    // bi-directional many-to-one association to RectificaComparendo
    @ManyToOne
    @JoinColumn(name = "id_rectifica_comparendo")
    private RectificaComparendo rectificaComparendo;

    public RectificaEvidencia() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoTipoEvidencia() {
        return this.codigoTipoEvidencia;
    }

    public void setCodigoTipoEvidencia(Integer codigoTipoEvidencia) {
        this.codigoTipoEvidencia = codigoTipoEvidencia;
    }

    public String getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public RectificaComparendo getRectificaComparendo() {
        return this.rectificaComparendo;
    }

    public void setRectificaComparendo(RectificaComparendo rectificaComparendo) {
        this.rectificaComparendo = rectificaComparendo;
    }

}