package co.com.datatools.c2.entidades;

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

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "configuracion_soporte")
@NamedQueries({ @NamedQuery(name = "ConfiguracionSoporte.findAll", query = "SELECT cs FROM ConfiguracionSoporte cs") })
public class ConfiguracionSoporte implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_soporte")
    private Long id;

    @JoinColumn(name = "id_soporte_procedimiento", referencedColumnName = "id_soporte_procedimiento")
    @ManyToOne(fetch = FetchType.LAZY)
    private SoporteProcedimiento soporte;

    @Basic(optional = false)
    @Column(name = "configuracion_ejecucion")
    private String configuracionEjecucion;

    @Column(name = "respuesta")
    private String respuesta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SoporteProcedimiento getSoporte() {
        return soporte;
    }

    public void setSoporte(SoporteProcedimiento soporte) {
        this.soporte = soporte;
    }

    public String getConfiguracionEjecucion() {
        return configuracionEjecucion;
    }

    public void setConfiguracionEjecucion(String configuracionEjecucion) {
        this.configuracionEjecucion = configuracionEjecucion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}