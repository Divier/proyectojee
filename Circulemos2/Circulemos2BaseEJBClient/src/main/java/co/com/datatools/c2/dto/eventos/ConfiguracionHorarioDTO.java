package co.com.datatools.c2.dto.eventos;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * 
 * @author giovanni.velandia
 *
 */
public class ConfiguracionHorarioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Integer idConfiguracionHorario;
    private Date fechaInicio;
    private Date fechaFin;
    private UsuarioPersonaDTO usuarioRegistraDTO;
    private Date fechaActualizacion;
    private UsuarioPersonaDTO usuarioActualizaDTO;
    private CargoDTO cargoDTO;
    private DiaDTO diaDTO;

    private List<BloqueHorarioDTO> bloqueHorarioDTOs;

    public Integer getIdConfiguracionHorario() {
        return idConfiguracionHorario;
    }

    public void setIdConfiguracionHorario(Integer idConfiguracionHorario) {
        this.idConfiguracionHorario = idConfiguracionHorario;
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

    public UsuarioPersonaDTO getUsuarioRegistraDTO() {
        return usuarioRegistraDTO;
    }

    public void setUsuarioRegistraDTO(UsuarioPersonaDTO usuarioRegistraDTO) {
        this.usuarioRegistraDTO = usuarioRegistraDTO;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UsuarioPersonaDTO getUsuarioActualizaDTO() {
        return usuarioActualizaDTO;
    }

    public void setUsuarioActualizaDTO(UsuarioPersonaDTO usuarioActualizaDTO) {
        this.usuarioActualizaDTO = usuarioActualizaDTO;
    }

    public CargoDTO getCargoDTO() {
        return cargoDTO;
    }

    public void setCargoDTO(CargoDTO cargoDTO) {
        this.cargoDTO = cargoDTO;
    }

    public DiaDTO getDiaDTO() {
        return diaDTO;
    }

    public void setDiaDTO(DiaDTO diaDTO) {
        this.diaDTO = diaDTO;
    }

    public List<BloqueHorarioDTO> getBloqueHorarioDTOs() {
        return bloqueHorarioDTOs;
    }

    public void setBloqueHorarioDTOs(List<BloqueHorarioDTO> bloqueHorarioDTOs) {
        this.bloqueHorarioDTOs = bloqueHorarioDTOs;
    }

}
