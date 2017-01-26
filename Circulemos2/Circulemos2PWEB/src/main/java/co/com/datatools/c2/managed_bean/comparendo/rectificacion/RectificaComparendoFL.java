package co.com.datatools.c2.managed_bean.comparendo.rectificacion;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.managed_bean.comparendo.administracion.colombia.AdminComparendoFL;

/**
 * FL que permite manejar los datos correspondientes a una rectificacion, contiene el mismo comportamiento que AdminComparendoFL.
 * 
 * @author luis.forero(mod 2016-03-02)
 * 
 */
public class RectificaComparendoFL extends AdminComparendoFL {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "rectificaComparendoFL";

    // ENCABEZADO
    private String nomOrganismoTransito;
    private Date fechaRegistro;

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

}
