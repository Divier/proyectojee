package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:56:28 COT 2016
 */
public class CargueCoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaAprobacion;
    private UsuarioPersonaDTO usuarioAprobacion;
    private CargueArchivoDTO cargueArchivo;
    private ConfiguracionCoactivoDTO configuracionCoactivo;
    private List<CoactivoDTO> coactivos;
    private List<PrecoactivoDTO> precoactivos;

    // --- Constructor
    public CargueCoactivoDTO() {
    }

    public CargueCoactivoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaAprobacion() {
        return this.fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public UsuarioPersonaDTO getUsuarioAprobacion() {
        return this.usuarioAprobacion;
    }

    public void setUsuarioAprobacion(UsuarioPersonaDTO usuarioAprobacion) {
        this.usuarioAprobacion = usuarioAprobacion;
    }

    public ConfiguracionCoactivoDTO getConfiguracionCoactivo() {
        return this.configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivoDTO configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<CoactivoDTO> getCoactivos() {
        if (this.coactivos == null) {
            this.coactivos = new java.util.ArrayList<>(1);
        }
        return this.coactivos;
    }

    public void setCoactivos(List<CoactivoDTO> coactivos) {
        this.coactivos = coactivos;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<PrecoactivoDTO> getPrecoactivos() {
        if (this.precoactivos == null) {
            this.precoactivos = new java.util.ArrayList<>(1);
        }
        return this.precoactivos;
    }

    public void setPrecoactivos(List<PrecoactivoDTO> precoactivos) {
        this.precoactivos = precoactivos;
    }

    public CargueArchivoDTO getCargueArchivo() {
        return cargueArchivo;
    }

    public void setCargueArchivo(CargueArchivoDTO cargueArchivo) {
        this.cargueArchivo = cargueArchivo;
    }

}
