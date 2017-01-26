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
 * Entidad no utilizada en la logica de negocio
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "configuracion_rol")
@NamedQueries({ @NamedQuery(name = "ConfiguracionRol.findAll", query = "SELECT c FROM ConfiguracionRol c") })
// @Audited
public class ConfiguracionRol implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_rol")
    private Integer idConfiguracionRol;

    // @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_rol_1", referencedColumnName = "id_rol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rol1;

    // @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_rol_2", referencedColumnName = "id_rol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rol2;

    // @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_tipo_restriccion", referencedColumnName = "id_tipo_restriccion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoRestriccionRol tipoRestriccionRol;

    public ConfiguracionRol() {
    }

    public ConfiguracionRol(Integer idConfiguracionRol) {
        this.idConfiguracionRol = idConfiguracionRol;
    }

    public Integer getIdConfiguracionRol() {
        return idConfiguracionRol;
    }

    public void setIdConfiguracionRol(Integer idConfiguracionRol) {
        this.idConfiguracionRol = idConfiguracionRol;
    }

    public TipoRestriccionRol getTipoRestriccionRol() {
        return tipoRestriccionRol;
    }

    public void setTipoRestriccionRol(TipoRestriccionRol tipoRestriccionRol) {
        this.tipoRestriccionRol = tipoRestriccionRol;
    }

}
