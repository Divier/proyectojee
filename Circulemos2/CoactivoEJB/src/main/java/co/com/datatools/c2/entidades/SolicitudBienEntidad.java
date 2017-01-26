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
 * The persistent class for the precoactivo database table.
 * 
 */
@Entity
@Table(name = "solicitud_bien_entidad")
public class SolicitudBienEntidad implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_bien_entidad")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud_oficio_bien")
    private SolicitudOficioBien solicitudOficioBien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion_entidad")
    private ConfiguracionEntidad configuracionEntidad;

    public SolicitudBienEntidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SolicitudOficioBien getSolicitudOficioBien() {
        return solicitudOficioBien;
    }

    public void setSolicitudOficioBien(SolicitudOficioBien solicitudOficioBien) {
        this.solicitudOficioBien = solicitudOficioBien;
    }

    public ConfiguracionEntidad getConfiguracionEntidad() {
        return configuracionEntidad;
    }

    public void setConfiguracionEntidad(ConfiguracionEntidad configuracionEntidad) {
        this.configuracionEntidad = configuracionEntidad;
    }
}