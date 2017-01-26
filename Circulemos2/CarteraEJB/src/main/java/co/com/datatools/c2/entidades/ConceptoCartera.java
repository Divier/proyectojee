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

/**
 * @version Sprint 5
 * 
 */
@Entity
@Table(name = "concepto_cartera")
@NamedQueries(
        value = { @NamedQuery(name = "ConceptoCartera.findAll", query = "SELECT c FROM ConceptoCartera c"),
                @NamedQuery(
                        name = "ConceptoCartera.findByCod",
                        query = "SELECT c FROM ConceptoCartera c WHERE c.codigo = :codigo") })
public class ConceptoCartera implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * SELECT c FROM ConceptoCartera c WHERE c.codigo = :codigo
     * </pre>
     */
    public static final String SQ_FIND_BY_COD = "ConceptoCartera.findByCod";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepto")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "codigo_concepto")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_saldo")
    private TipoSaldo tipoSaldo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_tipo_concepto")
    private TipoConceptoCartera tipoConceptoCartera;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    public ConceptoCartera() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoSaldo getTipoSaldo() {
        return this.tipoSaldo;
    }

    public void setTipoSaldo(TipoSaldo tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }

    public TipoConceptoCartera getTipoConceptoCartera() {
        return this.tipoConceptoCartera;
    }

    public void setTipoConceptoCartera(TipoConceptoCartera tipoConceptoCartera) {
        this.tipoConceptoCartera = tipoConceptoCartera;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}