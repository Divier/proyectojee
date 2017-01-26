package co.com.datatools.c2.entidades.ws;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the valor_mapeo database table.
 * 
 */
@Entity
@Table(name = "valor_mapeo")
@NamedQueries({
        @NamedQuery(name = "ValorMapeo.findAll", query = "SELECT v FROM ValorMapeo v"),
        @NamedQuery(
                name = "ValorMapeo.findValoresByServicioOrigen",
                query = "SELECT v FROM ValorMapeo v JOIN FETCH v.tipoMapeo tm WHERE tm.webServiceExpuesto.id = :pIdServExp") })
public class ValorMapeo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * Consulta los valores de mapeo de un determinado web service
     * 
     * SELECT v FROM ValorMapeo v JOIN FETCH v.tipoMapeo tm WHERE tm.webServiceExpuesto.id = :<b>pIdServExp</b>
     * 
     * @author luis.forero(2015-11-18)
     */
    public static final String SQ_VALORES_BY_SERVICIO_ORIGEN = "ValorMapeo.findValoresByServicioOrigen";

    @Id
    @Column(name = "id_valor_mapeo")
    private Long id;

    @Column(name = "valor_final")
    private String valorFinal;

    @Column(name = "valor_origen")
    private String valorOrigen;

    // bi-directional many-to-one association to TipoMapeo
    @ManyToOne
    @JoinColumn(name = "id_tipo_mapeo")
    private TipoMapeo tipoMapeo;

    public ValorMapeo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public TipoMapeo getTipoMapeo() {
        return this.tipoMapeo;
    }

    public void setTipoMapeo(TipoMapeo tipoMapeo) {
        this.tipoMapeo = tipoMapeo;
    }

}