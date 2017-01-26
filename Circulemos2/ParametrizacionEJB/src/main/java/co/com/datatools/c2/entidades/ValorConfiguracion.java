package co.com.datatools.c2.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Felipe.Martinez
 */
@Entity
@Table(name = "valor_configuracion")
@NamedQueries({ @NamedQuery(
        name = "ValorConfiguracion.findByCodigoConfiguracion",
        query = "SELECT vc FROM ValorConfiguracion vc LEFT JOIN FETCH vc.configuracion c WHERE c.codigo = :pCodigo") })
public class ValorConfiguracion implements Serializable {

    private static final long serialVersionUID = 3548898716653884911L;

    public static final String SQ_FIND_BY_CODIGO_CONF = "ValorConfiguracion.findByCodigoConfiguracion";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_valor_configuracion")
    private Integer id;

    @Basic(optional = false)
    @Lob
    @Column(name = "valor")
    private String valor;

    @JoinColumn(name = "id_configuracion", referencedColumnName = "id_configuracion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Configuracion configuracion;

    public ValorConfiguracion() {
    }

    public ValorConfiguracion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

}
