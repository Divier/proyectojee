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

/**
 * The persistent class for the estado_condicion_financiacion database table.
 * 
 * @author luis.forero
 */
@Entity
@Table(name = "estado_condicion_financiacion")
public class EstadoCondicionFinanciacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_condicion_finan")
    private Long id;

    @Column(name = "activo")
    private Boolean activo;

    @JoinColumn(name = "codigo_condicion", referencedColumnName = "codigo_condicion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CondicionFinanciacion condicionFinanciacion;

    @JoinColumn(name = "id_configuracion_financiacion", referencedColumnName = "id_configuracion_financiacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ConfiguracionFinanciacion configuracionFinanciacion;

    public EstadoCondicionFinanciacion() {
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

    public CondicionFinanciacion getCondicionFinanciacion() {
        return condicionFinanciacion;
    }

    public void setCondicionFinanciacion(CondicionFinanciacion condicionFinanciacion) {
        this.condicionFinanciacion = condicionFinanciacion;
    }

    public ConfiguracionFinanciacion getConfiguracionFinanciacion() {
        return configuracionFinanciacion;
    }

    public void setConfiguracionFinanciacion(ConfiguracionFinanciacion configuracionFinanciacion) {
        this.configuracionFinanciacion = configuracionFinanciacion;
    }

}