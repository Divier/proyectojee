package co.com.datatools.c2.entidades;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "error_procesamiento")
@NamedQueries({ @NamedQuery(name = "ErrorProcesamiento.findAll", query = "SELECT e FROM ErrorProcesamiento e"),
        @NamedQuery(
                name = "ErrorProcesamiento.findByCodigo",
                query = "SELECT e FROM ErrorProcesamiento e WHERE e.codigo = :pCodErrProc") })
public class ErrorProcesamiento implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * Consulta un error de procesamiento por su codigo.
     * 
     * SELECT e FROM ErrorProcesamiento e WHERE e.codigo = :pCodErrProc
     * 
     * @author luis.forero(2015-11-17)
     */
    public static final String SQ_ERROR_PROCESAMIENTO_POR_CODIGO = "ErrorProcesamiento.findByCodigo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_error_procesamiento")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sigla")
    private String sigla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais")
    private Pais pais;

    @OneToMany(mappedBy = "errorProcesamiento")
    private List<DetalleProcesamiento> detalleProcesamientos;

    public ErrorProcesamiento() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public List<DetalleProcesamiento> getDetalleProcesamientos() {
        return this.detalleProcesamientos;
    }

    public void setDetalleProcesamientos(List<DetalleProcesamiento> detalleProcesamientos) {
        this.detalleProcesamientos = detalleProcesamientos;
    }

    public DetalleProcesamiento addDetalleProcesamiento(DetalleProcesamiento detalleProcesamiento) {
        getDetalleProcesamientos().add(detalleProcesamiento);
        detalleProcesamiento.setErrorProcesamiento(this);

        return detalleProcesamiento;
    }

    public DetalleProcesamiento removeDetalleProcesamiento(DetalleProcesamiento detalleProcesamiento) {
        getDetalleProcesamientos().remove(detalleProcesamiento);
        detalleProcesamiento.setErrorProcesamiento(null);

        return detalleProcesamiento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

}