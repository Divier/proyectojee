package co.com.datatools.c2.entidades.ubicabilidad;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the tipo_archivo_persona database table.
 * 
 */
@Entity
@Table(name = "tipo_archivo_persona")
@NamedQuery(name = "TipoArchivoPersona.findAll", query = "SELECT t FROM TipoArchivoPersona t")
public class TipoArchivoPersona implements EntidadC2, EntidadCatalogoC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_archivo_persona")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sigla")
    private String sigla;

    public TipoArchivoPersona() {
    }

    public TipoArchivoPersona(Integer id) {
        super();
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public Boolean getActivo() {
        return this.estado;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.estado = activo;
    }

}