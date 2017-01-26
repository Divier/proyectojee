package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:19:26 COT 2014
 */
public class ConfiguracionEmailDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Boolean estado;
    private Date fechaCambio;
    private String asuntoEmail;
    private String cuerpoEmail;
    private String pieEmail;
    private TipoEmailDTO tipoEmail;
    private UsuarioPersonaDTO usuario;
    private OrganismoTransitoDTO organismoTransito;
    private List<CorreoEnvioDTO> emailList;

    // --- Constructor
    public ConfiguracionEmailDTO() {
    }

    public ConfiguracionEmailDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCambio() {
        return this.fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getAsuntoEmail() {
        return this.asuntoEmail;
    }

    public void setAsuntoEmail(String asuntoEmail) {
        this.asuntoEmail = asuntoEmail;
    }

    public String getCuerpoEmail() {
        return this.cuerpoEmail;
    }

    public void setCuerpoEmail(String cuerpoEmail) {
        this.cuerpoEmail = cuerpoEmail;
    }

    public String getPieEmail() {
        return this.pieEmail;
    }

    public void setPieEmail(String pieEmail) {
        this.pieEmail = pieEmail;
    }

    public TipoEmailDTO getTipoEmail() {
        return this.tipoEmail;
    }

    public void setTipoEmail(TipoEmailDTO tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<CorreoEnvioDTO> getEmailList() {
        if (this.emailList == null) {
            this.emailList = new java.util.ArrayList<>(0);
        }
        return this.emailList;
    }

    public void setEmailList(List<CorreoEnvioDTO> emailList) {
        this.emailList = emailList;
    }

}
