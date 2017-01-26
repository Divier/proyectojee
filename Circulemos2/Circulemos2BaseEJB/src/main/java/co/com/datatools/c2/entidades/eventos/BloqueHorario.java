package co.com.datatools.c2.entidades.eventos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author giovanni.velandia
 *
 */
@Entity
@Table(name = "bloque_horario")
public class BloqueHorario implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloque_horario")
    private Integer idBloqueHorario;

    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;

    @JoinColumn(name = "id_configuracion_horario", referencedColumnName = "id_configuracion_horario")
    @ManyToOne(fetch = FetchType.LAZY)
    private ConfiguracionHorario configuracionHorario;

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

    public ConfiguracionHorario getConfiguracionHorario() {
        return configuracionHorario;
    }

    public void setConfiguracionHorario(ConfiguracionHorario configuracionHorario) {
        this.configuracionHorario = configuracionHorario;
    }

}
