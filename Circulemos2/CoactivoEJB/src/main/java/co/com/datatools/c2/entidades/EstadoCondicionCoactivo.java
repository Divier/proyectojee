package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "estado_condicion_coactivo")
public class EstadoCondicionCoactivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_condicion_coac")
    private Long id;

    @Column(name = "activo")
    private Boolean activo;

    @JoinColumn(name = "codigo_condicion", referencedColumnName = "codigo_condicion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CondicionCoactivo condicionCoactivo;

    @JoinColumn(name = "id_configuracion_coactivo", referencedColumnName = "id_configuracion_coactivo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ConfiguracionCoactivo configuracionCoactivo;

    public EstadoCondicionCoactivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public CondicionCoactivo getCondicionCoactivo() {
        return condicionCoactivo;
    }

    public void setCondicionCoactivo(CondicionCoactivo condicionCoactivo) {
        this.condicionCoactivo = condicionCoactivo;
    }

    public ConfiguracionCoactivo getConfiguracionCoactivo() {
        return configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivo configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

}