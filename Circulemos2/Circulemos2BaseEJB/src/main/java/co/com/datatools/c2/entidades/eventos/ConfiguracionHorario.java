package co.com.datatools.c2.entidades.eventos;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author giovanni.velandia
 *
 */
@Entity
@Table(name = "configuracion_horario")
public class ConfiguracionHorario implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_horario")
    private Integer idConfiguracionHorario;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "id_usuario_registra", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioPersona usuarioRegistra;

    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;

    @JoinColumn(name = "id_usuario_actualiza", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioPersona usuarioActualiza;

    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cargo cargo;

    @JoinColumn(name = "id_dia", referencedColumnName = "id_dia")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dia dia;

    @OneToMany(mappedBy = "configuracionHorario", fetch = FetchType.LAZY)
    private List<BloqueHorario> bloqueHorarios;

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

    public UsuarioPersona getUsuarioRegistra() {
        return usuarioRegistra;
    }

    public void setUsuarioRegistra(UsuarioPersona usuarioRegistra) {
        this.usuarioRegistra = usuarioRegistra;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UsuarioPersona getUsuarioActualiza() {
        return usuarioActualiza;
    }

    public void setUsuarioActualiza(UsuarioPersona usuarioActualiza) {
        this.usuarioActualiza = usuarioActualiza;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public List<BloqueHorario> getBloqueHorarios() {
        return bloqueHorarios;
    }

    public void setBloqueHorarios(List<BloqueHorario> bloqueHorarios) {
        this.bloqueHorarios = bloqueHorarios;
    }

}
