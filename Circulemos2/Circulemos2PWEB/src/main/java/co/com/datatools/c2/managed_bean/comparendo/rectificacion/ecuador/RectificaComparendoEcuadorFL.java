package co.com.datatools.c2.managed_bean.comparendo.rectificacion.ecuador;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.managed_bean.comparendo.administracion.ecuador.AdminComparendoEcuadorFL;

/**
 * FL que permite manejar los datos correspondientes a una rectificacion, contiene el mismo comportamiento que AdminComparendoFL.
 * 
 * @author luis.forero(mod 2016-03-02)
 * 
 */
public class RectificaComparendoEcuadorFL extends AdminComparendoEcuadorFL {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "rectificaComparendoEcuadorFL";

    // ENCABEZADO
    private String nomOrganismoTransito;
    private Date fechaRegistro;
    private String nomOrganismoMatriculaVehiculo;
    private String nomOrganismoLicencia;

    // Evidencias
    private List<EvidenciaDTO> evidencias;

    public String getNomOrganismoTransito() {
        return nomOrganismoTransito;
    }

    public void setNomOrganismoTransito(String nomOrganismoTransito) {
        this.nomOrganismoTransito = nomOrganismoTransito;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<EvidenciaDTO> getEvidencias() {
        return evidencias;
    }

    public void setEvidencias(List<EvidenciaDTO> evidencias) {
        this.evidencias = evidencias;
    }

    public String getNomOrganismoMatriculaVehiculo() {
        return nomOrganismoMatriculaVehiculo;
    }

    public void setNomOrganismoMatriculaVehiculo(String nomOrganismoMatriculaVehiculo) {
        this.nomOrganismoMatriculaVehiculo = nomOrganismoMatriculaVehiculo;
    }

    public String getNomOrganismoLicencia() {
        return nomOrganismoLicencia;
    }

    public void setNomOrganismoLicencia(String nomOrganismoLicencia) {
        this.nomOrganismoLicencia = nomOrganismoLicencia;
    }
}