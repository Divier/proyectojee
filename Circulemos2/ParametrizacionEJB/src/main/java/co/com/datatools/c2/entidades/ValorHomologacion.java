package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the valor_homologacion database table.
 * 
 */
@Entity
@Table(name = "valor_homologacion")
@NamedQuery(name = "ValorHomologacion.findAll", query = "SELECT v FROM ValorHomologacion v")
public class ValorHomologacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_valor_homologacion")
    private Long id;

    @Column(name = "valor_final")
    private String valorFinal;

    @Column(name = "valor_origen")
    private String valorOrigen;

    // bi-directional many-to-one association to TipoHomologacion
    @ManyToOne
    @JoinColumn(name = "id_tipo_homologacion")
    private TipoHomologacion tipoHomologacion;

    public ValorHomologacion() {
    }

    public Long getIdValorHomologacion() {
        return this.id;
    }

    public void setIdValorHomologacion(Long id) {
        this.id = id;
    }

    public String getValorFinal() {
        return this.valorFinal;
    }

    public void setValorFinal(String valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getValorOrigen() {
        return this.valorOrigen;
    }

    public void setValorOrigen(String valorOrigen) {
        this.valorOrigen = valorOrigen;
    }

    public TipoHomologacion getTipoHomologacion() {
        return this.tipoHomologacion;
    }

    public void setTipoHomologacion(TipoHomologacion tipoHomologacion) {
        this.tipoHomologacion = tipoHomologacion;
    }

}