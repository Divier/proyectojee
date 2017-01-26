package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.EntidadOficioDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Aug 01 11:56:19 COT 2016
 */
public class ConfiguracionEntidadDTO implements EntidadDtoC2 {

    private static final long serialVersionUID = 1L;

    private Long id;
    private EntidadOficioDTO entidadOficioDTO;
    private String contacto;
    private String direccion;
    private String siglaOficio;
    private TipoEntidadDTO tipoEntidadDTO;
    private MunicipioDTO municipioDTO;
    private Date fechaInicio;
    private Date fechaFin;

    public ConfiguracionEntidadDTO() {
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSiglaOficio() {
        return siglaOficio;
    }

    public void setSiglaOficio(String siglaOficio) {
        this.siglaOficio = siglaOficio;
    }

    public TipoEntidadDTO getTipoEntidadDTO() {
        return tipoEntidadDTO;
    }

    public void setTipoEntidadDTO(TipoEntidadDTO tipoEntidadDTO) {
        this.tipoEntidadDTO = tipoEntidadDTO;
    }

    public MunicipioDTO getMunicipioDTO() {
        return municipioDTO;
    }

    public void setMunicipioDTO(MunicipioDTO municipioDTO) {
        this.municipioDTO = municipioDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EntidadOficioDTO getEntidadOficioDTO() {
        return entidadOficioDTO;
    }

    public void setEntidadOficioDTO(EntidadOficioDTO entidadOficioDTO) {
        this.entidadOficioDTO = entidadOficioDTO;
    }
}
