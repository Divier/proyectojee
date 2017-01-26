package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the documento_proceso database table.
 * 
 */
@Entity
@Table(name = "rechazo_impugnacion")
@NamedQuery(name = "RechazoImpugnacion.findAll", query = "SELECT d FROM RechazoImpugnacion d")
public class RechazoImpugnacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rechazo_impugnacion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trazabilidad_proceso")
    private TrazabilidadProceso trazabilidadProceso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_rechazo")
    private TipoRechazo tipoRechazo;

    @Column(name = "observacion")
    private String observacion;

    public RechazoImpugnacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrazabilidadProceso getTrazabilidadProceso() {
        return trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProceso trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public TipoRechazo getTipoRechazo() {
        return tipoRechazo;
    }

    public void setTipoRechazo(TipoRechazo tipoRechazo) {
        this.tipoRechazo = tipoRechazo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}