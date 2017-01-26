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

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "relacion_estados")
@NamedQueries({
        @NamedQuery(
                name = "RelacionEstados.findByTipoForm",
                query = "SELECT r FROM RelacionEstados r WHERE r.tipoFormulario.id = :idTipoFormulario"),
        @NamedQuery(
                name = "RelacionEstados.countByEstadosTipoForm",
                query = "SELECT COUNT(r) FROM RelacionEstados AS r WHERE r.estadoFormularioActual.id = :pIdEstAct AND r.estadoFormularioSiguiente.id = :pIdEstSig AND r.tipoFormulario.id = :pIdTipForm AND r.codigoOrganismo.id = :pIdCodOrg"),
        @NamedQuery(
                name = "RelacionEstados.countByEstTipoFormNotIdRel",
                query = "SELECT COUNT(r) FROM RelacionEstados AS r WHERE r.estadoFormularioActual.id = :pIdEstAct AND r.estadoFormularioSiguiente.id = :pIdEstSig AND r.tipoFormulario.id = :pIdTipForm AND r.codigoOrganismo.id = :pIdCodOrg AND r.id <> :pIdRelEst"),

})
public class RelacionEstados implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    /**
     * Parametros:
     * <p>
     * <li>pIdEstAct id del estado actual</li>
     * <li>pIdEstSig id del estado siguiente</li>
     * <li>pIdTipForm id del tipo de formulario</li>
     * <li>pIdCodOrg id Codigo del Organismo</li>
     * </p>
     * <br>
     * SELECT COUNT(r) FROM RelacionEstados AS r WHERE r.estadoFormularioActual.id = :pIdEstAct AND r.estadoFormularioSiguiente.id = :pIdEstSig AND
     * r.tipoFormulario.id = :pIdTipForm AND r.codigoOrganismo.id = :pIdCodOrg
     * 
     */
    public static final String SQ_COUNT_BY_ESTADOS_FORM = "RelacionEstados.countByEstadosTipoForm";

    /**
     * Parametros:
     * <p>
     * <li>pIdEstAct id del estado actual</li>
     * <li>pIdEstSig id del estado siguiente</li>
     * <li>pIdTipForm id del tipo de formulario</li>
     * <li>pIdCodOrg id Codigo del Organismo</li>
     * </p>
     * <br>
     * SELECT COUNT(r) FROM RelacionEstados AS r WHERE r.estadoFormularioActual.id = :pIdEstAct AND r.estadoFormularioSiguiente.id = :pIdEstSig AND
     * r.tipoFormulario.id = :pIdTipForm AND r.codigoOrganismo.id = :pIdCodOrg AND r.id <> :pIdRelEst
     * 
     */
    public static final String SQ_COUNT_BY_EST_FORM_NOT_ID_REL = "RelacionEstados.countByEstTipoFormNotIdRel";

    /**
     * SELECT r FROM RelacionEstados r WHERE r.tipoFormulario.id = : idTipoFormulario <br>
     * Parametros:
     * <p>
     * <li>idTipoFormulario: Id del tipo de formulario</li>
     * </p>
     */
    public static final String SQ_FIND_BY_TIPO_FORMULARIO = "RelacionEstados.findByTipoForm";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_relacion_estados")
    private Long id;

    @JoinColumn(name = "id_estado_formulario_siguiente", referencedColumnName = "id_estado_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoFormulario estadoFormularioSiguiente;

    @JoinColumn(name = "id_estado_formulario_actual", referencedColumnName = "id_estado_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoFormulario estadoFormularioActual;

    @JoinColumn(name = "id_tipo_formulario", referencedColumnName = "id_tipo_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoFormulario tipoFormulario;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito codigoOrganismo;

    public RelacionEstados() {
    }

    public RelacionEstados(Long idRelacionEstados) {
        this.id = idRelacionEstados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idRelacionEstados) {
        this.id = idRelacionEstados;
    }

    public EstadoFormulario getEstadoFormularioSiguiente() {
        return estadoFormularioSiguiente;
    }

    public void setEstadoFormularioSiguiente(EstadoFormulario idEstadoFormularioSiguiente) {
        this.estadoFormularioSiguiente = idEstadoFormularioSiguiente;
    }

    public EstadoFormulario getEstadoFormularioActual() {
        return estadoFormularioActual;
    }

    public void setEstadoFormularioActual(EstadoFormulario idEstadoFormularioActual) {
        this.estadoFormularioActual = idEstadoFormularioActual;
    }

    public TipoFormulario getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(TipoFormulario idTipoFormulario) {
        this.tipoFormulario = idTipoFormulario;
    }

    public OrganismoTransito getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransito codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

}
