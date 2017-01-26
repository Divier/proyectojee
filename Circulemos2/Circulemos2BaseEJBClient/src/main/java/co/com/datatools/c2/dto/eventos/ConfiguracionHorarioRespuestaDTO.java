package co.com.datatools.c2.dto.eventos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * De vuelve los resultados de la consulta de configuracion horario
 * 
 * @author giovanni.velandia
 *
 */
public class ConfiguracionHorarioRespuestaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idConfiguracionHorario;
    private String nombreCargo;
    private String nombreDia;
    private Date fechaInicioVigencia;
    private Date fechaFinVigencia;
    private List<BloqueHorarioDTO> bloqueHorarioDTOs;

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public Integer getIdConfiguracionHorario() {
        return idConfiguracionHorario;
    }

    public void setIdConfiguracionHorario(Integer idConfiguracionHorario) {
        this.idConfiguracionHorario = idConfiguracionHorario;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public List<BloqueHorarioDTO> getBloqueHorarioDTOs() {
        return bloqueHorarioDTOs;
    }

    public void setBloqueHorarioDTOs(List<BloqueHorarioDTO> bloqueHorarioDTOs) {
        this.bloqueHorarioDTOs = bloqueHorarioDTOs;
    }

}
