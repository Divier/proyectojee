package co.com.datatools.c2.entidades;

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

@Entity
@Table(name = "ejecucion_job_coactivo")
public class EjecucionJobCoactivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejecucion_job_coactivo")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion_coactivo")
    private ConfiguracionCoactivo configuracionCoactivo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_ejecucion")
    private Date fechaEjecucion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConfiguracionCoactivo getConfiguracionCoactivo() {
        return configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivo configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

}