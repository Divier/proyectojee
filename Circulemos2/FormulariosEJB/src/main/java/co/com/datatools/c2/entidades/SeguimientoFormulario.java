package co.com.datatools.c2.entidades;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "seguimiento_formulario")
@NamedQueries({
        @NamedQuery(name = "SeguimientoFormulario.findAll", query = "SELECT s FROM SeguimientoFormulario s"),
        @NamedQuery(
                name = "Formulario.countByRespFormEstSeguimiento",
                query = "SELECT COUNT(sf) FROM SeguimientoFormulario AS sf WHERE sf.responsableFormulario.id = :pIdRespForm AND sf.estadoFormulario.id = :pIdEstAsignado"),
        @NamedQuery(
                name = "Formulario.maxSeguimientoByFormulario",
                query = "SELECT segForm FROM SeguimientoFormulario segForm LEFT JOIN FETCH segForm.responsableFormulario rf WHERE segForm.id=(SELECT MAX(sf.id) FROM SeguimientoFormulario sf WHERE sf.formulario.id = :idFormulario)"),
        @NamedQuery(
                name = "SeguimientoFormulario.findByDetalleCambio",
                query = "SELECT segForm FROM SeguimientoFormulario as segForm JOIN FETCH segForm.formulario f WHERE segForm.detalleCambioEstado.id=:idDetalleCambioEstado"),
        @NamedQuery(
                name = "SeguimientoFormulario.findBySeguimientoEstadoActualFormulario",
                query = "SELECT sf FROM SeguimientoFormulario sf JOIN sf.detalleCambioEstado dce"
                        + " JOIN sf.formulario f WHERE dce.id = :idDetalleCambioEstado AND NOT f.estadoFormulario.id = :idEstadoActualFormulario") })
public class SeguimientoFormulario implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    /**
     * parametros de consulta:
     * <p>
     * <li><b>pIdRespForm</b> Identificador del responsable de formularios</li>
     * <li><b>pIdEstAsignado</b> Identificador del estado a consultar (CU_CIR20_DAT_ADM_031=ASIGNADO)</li>
     * <p>
     * <br>
     * SELECT COUNT(sf) FROM SeguimientoFormulario AS sf WHERE sf.responsableFormulario.id = :pIdRespForm AND sf.estadoFormulario.id = :pIdEstAsignado
     * 
     * @author luis.forero
     */
    public static final String SQ_COUNT_BY_RESP_EST_SEGUIMIENTO = "Formulario.countByRespFormEstSeguimiento";

    public static final String SQ_MAX_BY_FORMULARIO = "Formulario.maxSeguimientoByFormulario";

    /**
     * SELECT segForm FROM SeguimientoFormulario as segForm JOIN FETCH segForm.formulario f WHERE
     * segForm.detalleCambioEstado.id=:idDetalleCambioEstado
     */
    public static final String SQ_SEGUIMIENTO_BY_DETALLE_CAMBIO = "SeguimientoFormulario.findByDetalleCambio";

    public static final String SQ_SEGUIMIENTO_BY_ESTADO_ACTUAL_FORMULARIO = "SeguimientoFormulario.findBySeguimientoEstadoActualFormulario";
    public static final String SQ_SEGUIMIENTO_BY_ESTADO_ACTUAL_FORMULARIO_P1 = "idDetalleCambioEstado";
    public static final String SQ_SEGUIMIENTO_BY_ESTADO_ACTUAL_FORMULARIO_P2 = "idEstadoActualFormulario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento_formulario")
    private Long id;

    @Basic(optional = false)
    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;

    @Basic(optional = false)
    @Column(name = "usuario_registro")
    private String usuarioRegistro;

    @JoinColumn(name = "id_responsable_formulario", referencedColumnName = "id_responsable_formulario")
    @ManyToOne(fetch = FetchType.LAZY)
    private ResponsableFormulario responsableFormulario;

    @JoinColumn(name = "id_estado_formulario", referencedColumnName = "id_estado_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoFormulario estadoFormulario;

    @JoinColumn(name = "id_formulario", referencedColumnName = "id_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Formulario formulario;

    @JoinColumn(name = "id_detalle_cambio_estado", referencedColumnName = "id_detalle_cambio_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private DetalleCambioEstado detalleCambioEstado;

    @Basic(optional = false)
    @Column(name = "fecha_aplicacion_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacionEstado;

    public SeguimientoFormulario() {
    }

    public SeguimientoFormulario(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public ResponsableFormulario getResponsableFormulario() {
        return responsableFormulario;
    }

    public void setResponsableFormulario(ResponsableFormulario idResponsableFormulario) {
        this.responsableFormulario = idResponsableFormulario;
    }

    public EstadoFormulario getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(EstadoFormulario idEstadoFormulario) {
        this.estadoFormulario = idEstadoFormulario;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario idFormulario) {
        this.formulario = idFormulario;
    }

    public DetalleCambioEstado getDetalleCambioEstado() {
        return detalleCambioEstado;
    }

    public void setDetalleCambioEstado(DetalleCambioEstado idDetalleCambioEstado) {
        this.detalleCambioEstado = idDetalleCambioEstado;
    }

    public Date getFechaAplicacionEstado() {
        return fechaAplicacionEstado;
    }

    public void setFechaAplicacionEstado(Date fechaAplicacionEstado) {
        this.fechaAplicacionEstado = fechaAplicacionEstado;
    }

}
