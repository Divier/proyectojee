package co.com.datatools.c2.dto.comun;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class OrganismoTransitoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigoOrganismo;
    private String nombreOrganismo;
    private MunicipioDTO municipio;
    private DepartamentoDTO departamento;
    private String nit;
    private String correoElectronico;
    private String codigoRunt;
    private String codigoMinisterio;
    private Boolean activo;
    private List<DireccionOrganismoDTO> direccionOrganismo;
    private List<TelefonoOrganismoDTO> telefonoOrganismoList;

    /**
     * Atributo para identificar si el organismo esta siendo actualizado. Inicializado por defecto en false.
     */
    private boolean actualizando;

    // --- Constructor
    public OrganismoTransitoDTO() {
    }

    public OrganismoTransitoDTO(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    // --- Getters-Setters
    public Integer getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getNombreOrganismo() {
        return this.nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public MunicipioDTO getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(MunicipioDTO municipio) {
        this.municipio = municipio;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCodigoRunt() {
        return codigoRunt;
    }

    public void setCodigoRunt(String codigoRunt) {
        this.codigoRunt = codigoRunt;
    }

    public String getCodigoMinisterio() {
        return codigoMinisterio;
    }

    public void setCodigoMinisterio(String codigoMinisterio) {
        this.codigoMinisterio = codigoMinisterio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<DireccionOrganismoDTO> getDireccionOrganismo() {
        return direccionOrganismo;
    }

    public void setDireccionOrganismo(List<DireccionOrganismoDTO> direccionOrganismo) {
        this.direccionOrganismo = direccionOrganismo;
    }

    public List<TelefonoOrganismoDTO> getTelefonoOrganismoList() {
        return telefonoOrganismoList;
    }

    public void setTelefonoOrganismoList(List<TelefonoOrganismoDTO> telefonoOrganismoList) {
        this.telefonoOrganismoList = telefonoOrganismoList;
    }

    public boolean isActualizando() {
        return actualizando;
    }

    public void setActualizando(boolean actualizando) {
        this.actualizando = actualizando;
    }

    public OrganismoTransitoDTO clone() {
        OrganismoTransitoDTO clone = new OrganismoTransitoDTO(new Integer(this.codigoOrganismo));

        clone.setNombreOrganismo(nombreOrganismo != null ? new String(nombreOrganismo) : null);
        if (this.municipio != null) {
            MunicipioDTO municipioDTO = new MunicipioDTO(new Integer(this.getMunicipio().getId()));
            municipioDTO
                    .setNombre(this.getMunicipio().getNombre() != null ? new String(this.getMunicipio().getNombre())
                            : null);
            clone.setMunicipio(municipioDTO);
        }
        if (this.departamento != null) {
            DepartamentoDTO departamentoDTO = new DepartamentoDTO(this.getDepartamento().getId() != null ? new Integer(
                    this.getDepartamento().getId()) : null);
            departamentoDTO.setNombre(this.getDepartamento().getNombre() != null ? new String(this.getDepartamento()
                    .getNombre()) : null);
            clone.setDepartamento(departamentoDTO);
        }
        clone.setNit(this.nit != null ? new String(this.nit) : null);
        this.setCorreoElectronico(this.correoElectronico != null ? new String(this.correoElectronico) : null);
        this.setCodigoRunt(this.codigoRunt != null ? new String(this.codigoRunt) : null);
        this.setCodigoMinisterio(this.codigoMinisterio != null ? new String(this.codigoMinisterio) : null);
        this.setActivo(this.activo != null ? new Boolean(this.activo) : null);

        return clone;
    }
}