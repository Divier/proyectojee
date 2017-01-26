package co.com.datatools.c2.entidades.personas;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "nivel_educativo")
@NamedQueries({ @NamedQuery(name = "NivelEducativo.findAll", query = "SELECT n FROM NivelEducativo n") })
public class NivelEducativo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "codigo_nivel_educativo")
    private Integer codigo;

    @Column(name = "nombre_nivel_educativo")
    private String nombre;

    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pais pais;

    public NivelEducativo() {
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
