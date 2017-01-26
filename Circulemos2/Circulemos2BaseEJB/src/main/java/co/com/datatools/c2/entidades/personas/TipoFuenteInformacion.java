package co.com.datatools.c2.entidades.personas;

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
import javax.persistence.Table;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @author felipe.martinez<br/>
 *         luis.forero (mod 2014-11-20)
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "tipo_fuente_informacion")
@NamedQueries({})
public class TipoFuenteInformacion implements EntidadC2, EntidadCatalogoC2 {
    private static final long serialVersionUID = 1L;

    public static final String SQ_FIND_BY_ID = "";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_fuente_informacion")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    // bi-directional many-to-one association to ScoreUbicabilidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_score_ubicabilidad")
    private ScoreUbicabilidad scoreUbicabilidad;

    public TipoFuenteInformacion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public Boolean getActivo() {
        return this.estado;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.estado = activo;
    }

    public ScoreUbicabilidad getScoreUbicabilidad() {
        return scoreUbicabilidad;
    }

    public void setScoreUbicabilidad(ScoreUbicabilidad scoreUbicabilidad) {
        this.scoreUbicabilidad = scoreUbicabilidad;
    }

}