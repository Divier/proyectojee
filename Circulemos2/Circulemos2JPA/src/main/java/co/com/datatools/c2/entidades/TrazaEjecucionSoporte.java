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

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "traza_ejecucion_soporte")
@NamedQueries({
        @NamedQuery(name = "TrazaEjecucionSoporte.findAll", query = "SELECT tes FROM TrazaEjecucionSoporte tes") })
public class TrazaEjecucionSoporte implements EntidadC2 {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_traza_ejecucion_soporte")
    private Long id;

    @JoinColumn(name = "id_configuracion_soporte", referencedColumnName = "id_configuracion_soporte")
    @ManyToOne(fetch = FetchType.LAZY)
    private ConfiguracionSoporte configSoporte;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_ejecucion")
    private Date fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioPersona usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConfiguracionSoporte getConfigSoporte() {
        return configSoporte;
    }

    public void setConfigSoporte(ConfiguracionSoporte configSoporte) {
        this.configSoporte = configSoporte;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public UsuarioPersona getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersona usuario) {
        this.usuario = usuario;
    }
}