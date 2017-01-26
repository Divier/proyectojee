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
 * The persistent class for the precoactivo_error database table.
 * 
 */
@Entity
@Table(name = "precoactivo_error")
@NamedQuery(name = "PrecoactivoError.findAll", query = "SELECT p FROM PrecoactivoError p")
public class PrecoactivoError implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precoactivo_error")
    private Long id;

    // bi-directional many-to-one association to ErrorGeneraCoactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_error_genera_coactivo")
    private ErrorGeneraCoactivo errorGeneraCoactivo;

    // bi-directional many-to-one association to Precoactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_precoactivo")
    private Precoactivo precoactivo;

    public PrecoactivoError() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ErrorGeneraCoactivo getErrorGeneraCoactivo() {
        return this.errorGeneraCoactivo;
    }

    public void setErrorGeneraCoactivo(ErrorGeneraCoactivo errorGeneraCoactivo) {
        this.errorGeneraCoactivo = errorGeneraCoactivo;
    }

    public Precoactivo getPrecoactivo() {
        return this.precoactivo;
    }

    public void setPrecoactivo(Precoactivo precoactivo) {
        this.precoactivo = precoactivo;
    }

}