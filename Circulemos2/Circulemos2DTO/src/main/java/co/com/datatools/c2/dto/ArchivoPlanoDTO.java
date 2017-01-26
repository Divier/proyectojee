package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Jul 15 10:53:59 COT 2014
 */
public class ArchivoPlanoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String hash;
    private TipoArchivoDTO tipoArchivo;
    private OrganismoTransitoDTO organismoTransito;
    private Date fecha;
    private String rutaArchivo;
    private UsuarioPersonaDTO usuario;

    // --- Constructor
    public ArchivoPlanoDTO() {
    }

    public ArchivoPlanoDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public TipoArchivoDTO getTipoArchivo() {
        return this.tipoArchivo;
    }

    public void setTipoArchivo(TipoArchivoDTO tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRutaArchivo() {
        return this.rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

}
