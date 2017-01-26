package co.com.datatools.c2.entidades;

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
 * The persistent class for the campo_rectifica_comparendo database table.
 * 
 */
@Entity
@Table(name = "campo_rectifica_comparendo")
@NamedQuery(name = "CampoRectificaComparendo.findAll", query = "SELECT c FROM CampoRectificaComparendo c")
public class CampoRectificaComparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campo_rectifica_comparendo")
    private Long id;

    @Column(name = "valor_nuevo")
    private String valorNuevo;

    @Column(name = "valor_original")
    private String valorOriginal;

    // bi-directional many-to-one association to RectificaComparendo
    @ManyToOne
    @JoinColumn(name = "id_rectifica_comparendo")
    private RectificaComparendo rectificaComparendo;

    // bi-directional many-to-one association to CampoEntidad
    @ManyToOne
    @JoinColumn(name = "codigo_campo")
    private CampoEntidad campoEntidad;

    public CampoRectificaComparendo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CampoEntidad getCampoEntidad() {
        return this.campoEntidad;
    }

    public void setCampoEntidad(CampoEntidad campoEntidad) {
        this.campoEntidad = campoEntidad;
    }

    public String getValorNuevo() {
        return this.valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public String getValorOriginal() {
        return this.valorOriginal;
    }

    public void setValorOriginal(String valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public RectificaComparendo getRectificaComparendo() {
        return this.rectificaComparendo;
    }

    public void setRectificaComparendo(RectificaComparendo rectificaComparendo) {
        this.rectificaComparendo = rectificaComparendo;
    }

}