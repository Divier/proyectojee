package co.com.datatools.c2.dto.ubicabilidad;

import java.util.List;

import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 11:58:06 COT 2016
 */
public class DetalleCargueUbicabilidadDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private CargueArchivoDTO cargueArchivo;
    private DireccionPersonaHistoricoDTO direccionPersonaHistorico;
    private TelefonoPersonaHistoricoDTO telefonoPersonaHistorico;
    private CorreoPersonaHistoricoDTO correoPersonaHistorico;
    private List<DetalleCargueUbicabilidadErrorDTO> detalleCargueUbicabilidadErrors;

    // --- Constructor
    public DetalleCargueUbicabilidadDTO() {
    }

    public DetalleCargueUbicabilidadDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CargueArchivoDTO getCargueArchivo() {
        return this.cargueArchivo;
    }

    public void setCargueArchivo(CargueArchivoDTO cargueArchivo) {
        this.cargueArchivo = cargueArchivo;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleCargueUbicabilidadErrorDTO> getDetalleCargueUbicabilidadErrors() {
        if (this.detalleCargueUbicabilidadErrors == null) {
            this.detalleCargueUbicabilidadErrors = new java.util.ArrayList<>(1);
        }
        return this.detalleCargueUbicabilidadErrors;
    }

    public void setDetalleCargueUbicabilidadErrors(
            List<DetalleCargueUbicabilidadErrorDTO> detalleCargueUbicabilidadErrors) {
        this.detalleCargueUbicabilidadErrors = detalleCargueUbicabilidadErrors;
    }

    public DireccionPersonaHistoricoDTO getDireccionPersonaHistorico() {
        return direccionPersonaHistorico;
    }

    public void setDireccionPersonaHistorico(DireccionPersonaHistoricoDTO direccionPersonaHistorico) {
        this.direccionPersonaHistorico = direccionPersonaHistorico;
    }

    public TelefonoPersonaHistoricoDTO getTelefonoPersonaHistorico() {
        return telefonoPersonaHistorico;
    }

    public void setTelefonoPersonaHistorico(TelefonoPersonaHistoricoDTO telefonoPersonaHistorico) {
        this.telefonoPersonaHistorico = telefonoPersonaHistorico;
    }

    public CorreoPersonaHistoricoDTO getCorreoPersonaHistorico() {
        return correoPersonaHistorico;
    }

    public void setCorreoPersonaHistorico(CorreoPersonaHistoricoDTO correoPersonaHistorico) {
        this.correoPersonaHistorico = correoPersonaHistorico;
    }

}
