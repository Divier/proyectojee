package co.com.datatools.seguridad.entidades;

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
 * Operaciones asociadas a un recurso
 * 
 * @author Felipe Martinez <br>
 *         rodrigo.cruz (mod: 21/07/2015) - Auditoria
 */
@Entity
@Table(name = "recurso_operacion")
// @Audited
@NamedQueries({
        @NamedQuery(name = "RecursoOperacion.findAll", query = "SELECT r FROM RecursoOperacion r"),
        @NamedQuery(
                name = "RecursoOperacion.findByOperacion",
                query = "SELECT ro FROM RecursoOperacion ro WHERE ro.operacion.idOperacion =:idOperacion") })
public class RecursoOperacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_RECURSO_OPERACION_BY_OPERACION = "RecursoOperacion.findByOperacion";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso_operacion")
    private Integer idRecursoOperacion;

    @JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recurso recurso;

    @JoinColumn(name = "id_operacion", referencedColumnName = "id_operacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Operacion operacion;

    public RecursoOperacion() {
    }

    public RecursoOperacion(Integer idRecursoOperacion) {
        this.idRecursoOperacion = idRecursoOperacion;
    }

    public Integer getIdRecursoOperacion() {
        return idRecursoOperacion;
    }

    public void setIdRecursoOperacion(Integer idRecursoOperacion) {
        this.idRecursoOperacion = idRecursoOperacion;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

}
