package co.com.datatools.fachadaintegracionweb.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "tipo_identificacion_persona")
@NamedQueries({ @NamedQuery(
        name = "TipoIdentificacionPersona.findByCodigo",
        query = "SELECT t FROM TipoIdentificacionPersona t WHERE t.codigo= :codigo") })
public class TipoIdentificacionPersona implements EntidadC2, EntidadCatalogoC2 {
    private static final long serialVersionUID = 1L;

    public static final String SQ_FIND_BY_CODIGO = "TipoIdentificacionPersona.findByCodigo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_identificacion")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "sigla")
    private String sigla;

    public TipoIdentificacionPersona() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Boolean getActivo() {
        return this.estado;
    }

    public void setActivo(Boolean activo) {
        this.estado = activo;
    }
}