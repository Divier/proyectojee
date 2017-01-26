package co.com.datatools.c2.entidades.comun;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "tipo_via")
public class TipoVia implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_tipo_via")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nombre_tipo_via")
    private String nombre;

    @ManyToOne
    @Basic(optional = false)
    @JoinColumn(name = "id_pais")
    private Pais pais;

    public TipoVia() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

}