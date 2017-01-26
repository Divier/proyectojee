package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "anulacion")
@NamedQueries({ @NamedQuery(name = "Anulacion.findAll", query = "SELECT ac FROM Anulacion ac") })
public class Anulacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_anulacion")
    private Long id;

    @Column(name = "correo_supervisor")
    private String correoSupervisor;

    @Basic(optional = false)
    @Column(name = "fecha_correo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorreo;

    @Column(name = "justificacion_correo")
    private String justificacionCorreo;

    @Column(name = "primer_nombre_funcionario")
    private String primerNombreFuncionario;

    @Column(name = "segundo_nombre_funcionario")
    private String segundoNombreFuncionario;

    @Column(name = "primer_apellido_funcionario")
    private String primerApellidoFuncionario;

    @Column(name = "segundo_apellido_funcionario")
    private String segundoApellidoFuncionario;

    @Basic(optional = false)
    @Column(name = "fecha_anulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulacion;

    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proceso proceso;

    public Anulacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreoSupervisor() {
        return correoSupervisor;
    }

    public void setCorreoSupervisor(String correoSupervisor) {
        this.correoSupervisor = correoSupervisor;
    }

    public Date getFechaCorreo() {
        return fechaCorreo;
    }

    public void setFechaCorreo(Date fechaCorreo) {
        this.fechaCorreo = fechaCorreo;
    }

    public String getJustificacionCorreo() {
        return justificacionCorreo;
    }

    public void setJustificacionCorreo(String justificacionCorreo) {
        this.justificacionCorreo = justificacionCorreo;
    }

    public String getPrimerNombreFuncionario() {
        return primerNombreFuncionario;
    }

    public void setPrimerNombreFuncionario(String primerNombreFuncionario) {
        this.primerNombreFuncionario = primerNombreFuncionario;
    }

    public String getSegundoNombreFuncionario() {
        return segundoNombreFuncionario;
    }

    public void setSegundoNombreFuncionario(String segundoNombreFuncionario) {
        this.segundoNombreFuncionario = segundoNombreFuncionario;
    }

    public String getPrimerApellidoFuncionario() {
        return primerApellidoFuncionario;
    }

    public void setPrimerApellidoFuncionario(String primerApellidoFuncionario) {
        this.primerApellidoFuncionario = primerApellidoFuncionario;
    }

    public String getSegundoApellidoFuncionario() {
        return segundoApellidoFuncionario;
    }

    public void setSegundoApellidoFuncionario(String segundoApellidoFuncionario) {
        this.segundoApellidoFuncionario = segundoApellidoFuncionario;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

}
