package co.com.datatools.c2.dto.formularios;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * 
 * @author diego.fonseca 25-11-2015
 */
public class EstadoFormularioAsignacionDTO implements EntidadDtoC2 {
    private List<CifrasControlEstadoFormularioAsignacionDTO> cifrasControl;
    private int codigoOrganismo;
    private int idTipoFormulario;
    private String nombreOrganismo;
    private String nombreTipoFormulario;
    private UnificacionResponsableDTO responsable;

    public EstadoFormularioAsignacionDTO() {
        // TODO Auto-generated constructor stub
    }

    public List<CifrasControlEstadoFormularioAsignacionDTO> getCifrasControl() {
        return cifrasControl;
    }

    public void setCifrasControl(List<CifrasControlEstadoFormularioAsignacionDTO> cifrasControl) {
        this.cifrasControl = cifrasControl;
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public int getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(int idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public String getNombreTipoFormulario() {
        return nombreTipoFormulario;
    }

    public void setNombreTipoFormulario(String nombreTipoFormulario) {
        this.nombreTipoFormulario = nombreTipoFormulario;
    }

    public UnificacionResponsableDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(UnificacionResponsableDTO responsable) {
        this.responsable = responsable;
    }

}
