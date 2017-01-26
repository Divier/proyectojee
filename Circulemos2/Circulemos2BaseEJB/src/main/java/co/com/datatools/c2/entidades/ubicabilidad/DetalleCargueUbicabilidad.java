package co.com.datatools.c2.entidades.ubicabilidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.cargue.CargueArchivo;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the detalle_cargue_ubicabilidad database table.
 * 
 */
@Entity
@Table(name = "detalle_cargue_ubicabilidad")
@NamedQuery(name = "DetalleCargueUbicabilidad.findAll", query = "SELECT d FROM DetalleCargueUbicabilidad d")
public class DetalleCargueUbicabilidad implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_cargue_ubicabilidad")
    private Long id;

    // bi-directional many-to-one association to CargueUbicabilidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargue_archivo")
    private CargueArchivo cargueArchivo;

    // bi-directional many-to-one association to DireccionPersonaHistorico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion_persona_historico")
    private DireccionPersonaHistorico direccionPersonaHistorico;

    // bi-directional many-to-one association to TelefonoPersonaHistorico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_telefono_persona_historico")
    private TelefonoPersonaHistorico telefonoPersonaHistorico;

    // bi-directional many-to-one association to DireccionPersona
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_correo_persona_historico")
    private CorreoPersonaHistorico correoPersonaHistorico;

    // bi-directional many-to-one association to DetalleCargueUbicabilidadError
    @OneToMany(mappedBy = "detalleCargueUbicabilidad", fetch = FetchType.LAZY)
    private List<DetalleCargueUbicabilidadError> detalleCargueUbicabilidadErrors;

    public DetalleCargueUbicabilidad() {
    }

    public CargueArchivo getCargueArchivo() {
        return this.cargueArchivo;
    }

    public void setCargueArchivo(CargueArchivo cargueArchivo) {
        this.cargueArchivo = cargueArchivo;
    }

    public List<DetalleCargueUbicabilidadError> getDetalleCargueUbicabilidadErrors() {
        return this.detalleCargueUbicabilidadErrors;
    }

    public void setDetalleCargueUbicabilidadErrors(
            List<DetalleCargueUbicabilidadError> detalleCargueUbicabilidadErrors) {
        this.detalleCargueUbicabilidadErrors = detalleCargueUbicabilidadErrors;
    }

    public DetalleCargueUbicabilidadError addDetalleCargueUbicabilidadError(
            DetalleCargueUbicabilidadError detalleCargueUbicabilidadError) {
        getDetalleCargueUbicabilidadErrors().add(detalleCargueUbicabilidadError);
        detalleCargueUbicabilidadError.setDetalleCargueUbicabilidad(this);

        return detalleCargueUbicabilidadError;
    }

    public DetalleCargueUbicabilidadError removeDetalleCargueUbicabilidadError(
            DetalleCargueUbicabilidadError detalleCargueUbicabilidadError) {
        getDetalleCargueUbicabilidadErrors().remove(detalleCargueUbicabilidadError);
        detalleCargueUbicabilidadError.setDetalleCargueUbicabilidad(null);

        return detalleCargueUbicabilidadError;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DireccionPersonaHistorico getDireccionPersonaHistorico() {
        return direccionPersonaHistorico;
    }

    public void setDireccionPersonaHistorico(DireccionPersonaHistorico direccionPersonaHistorico) {
        this.direccionPersonaHistorico = direccionPersonaHistorico;
    }

    public TelefonoPersonaHistorico getTelefonoPersonaHistorico() {
        return telefonoPersonaHistorico;
    }

    public void setTelefonoPersonaHistorico(TelefonoPersonaHistorico telefonoPersonaHistorico) {
        this.telefonoPersonaHistorico = telefonoPersonaHistorico;
    }

    public CorreoPersonaHistorico getCorreoPersonaHistorico() {
        return correoPersonaHistorico;
    }

    public void setCorreoPersonaHistorico(CorreoPersonaHistorico correoPersonaHistorico) {
        this.correoPersonaHistorico = correoPersonaHistorico;
    }

}