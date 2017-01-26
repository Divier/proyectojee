package co.com.datatools.c2.managed_bean.formularios.responsable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;

public class ResponsableFormularioHolderFL implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer filTipoResponsable;
    private Integer filTipoDocumento;
    private String strTipoDocumento;
    private String filNumeroDocumento;
    private Integer filCodigoOrganismo;
    private boolean porOrganismo;

    private OrganismoTransitoDTO organismoTransitoDTO;

    private List<ResponsableFormularioFL> lstResponsablesFormularios;

    private ResponsableFormularioFL respFormSeleccionado;

    public ResponsableFormularioHolderFL() {
        init();
    }

    private void init() {
        filTipoResponsable = null;
        filTipoDocumento = null;
        strTipoDocumento = null;
        filNumeroDocumento = null;
        filCodigoOrganismo = null;

        lstResponsablesFormularios = new ArrayList<>(1);

        respFormSeleccionado = null;
        organismoTransitoDTO = null;
        porOrganismo = false;
    }

    public List<ResponsableFormularioFL> getLstResponsablesFormularios() {
        return lstResponsablesFormularios;
    }

    public void setLstResponsablesFormularios(List<ResponsableFormularioFL> lstResponsablesFormularios) {
        this.lstResponsablesFormularios = lstResponsablesFormularios;
    }

    public ResponsableFormularioFL getRespFormSeleccionado() {
        return respFormSeleccionado;
    }

    public void setRespFormSeleccionado(ResponsableFormularioFL respFormSeleccionado) {
        this.respFormSeleccionado = respFormSeleccionado;
    }

    public Integer getFilTipoResponsable() {
        return filTipoResponsable;
    }

    public void setFilTipoResponsable(Integer filTipoResponsable) {
        this.filTipoResponsable = filTipoResponsable;
    }

    public Integer getFilTipoDocumento() {
        return filTipoDocumento;
    }

    public void setFilTipoDocumento(Integer filTipoDocumento) {
        this.filTipoDocumento = filTipoDocumento;
    }

    public String getFilNumeroDocumento() {
        return filNumeroDocumento;
    }

    public void setFilNumeroDocumento(String filNumeroDocumento) {
        this.filNumeroDocumento = filNumeroDocumento;
    }

    public Integer getFilCodigoOrganismo() {
        return filCodigoOrganismo;
    }

    public void setFilCodigoOrganismo(Integer filCodigoOrganismo) {
        this.filCodigoOrganismo = filCodigoOrganismo;
    }

    public OrganismoTransitoDTO getOrganismoTransitoDTO() {
        return organismoTransitoDTO;
    }

    public void setOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
    }

    public String getStrTipoDocumento() {
        return strTipoDocumento;
    }

    public void setStrTipoDocumento(String strTipoDocumento) {
        this.strTipoDocumento = strTipoDocumento;
    }

    public boolean isPorOrganismo() {
        return porOrganismo;
    }

    public void setPorOrganismo(boolean porOrganismo) {
        this.porOrganismo = porOrganismo;
    }

}
