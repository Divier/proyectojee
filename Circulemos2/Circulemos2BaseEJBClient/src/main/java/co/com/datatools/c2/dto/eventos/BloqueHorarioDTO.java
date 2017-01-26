package co.com.datatools.c2.dto.eventos;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * 
 * @author giovanni.velandia
 *
 */
public class BloqueHorarioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Integer idBloqueHorario;
    private Date horaInicio;
    private Date horaFin;
    private ConfiguracionHorarioDTO configuracionHorarioDTO;

    public ConfiguracionHorarioDTO getConfiguracionHorarioDTO() {
        return configuracionHorarioDTO;
    }

    public void setConfiguracionHorarioDTO(ConfiguracionHorarioDTO configuracionHorarioDTO) {
        this.configuracionHorarioDTO = configuracionHorarioDTO;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getIdBloqueHorario() {
        return idBloqueHorario;
    }

    public void setIdBloqueHorario(Integer idBloqueHorario) {
        this.idBloqueHorario = idBloqueHorario;
    }

}
