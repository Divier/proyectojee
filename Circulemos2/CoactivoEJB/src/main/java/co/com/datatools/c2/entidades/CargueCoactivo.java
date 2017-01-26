package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.cargue.CargueArchivo;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the cargue_coactivo database table.
 * 
 */
@Entity
@Table(name = "cargue_coactivo")
@NamedQuery(name = "CargueCoactivo.findAll", query = "SELECT c FROM CargueCoactivo c")
public class CargueCoactivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cargue_coactivo")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "id_cargue_coactivo")
    private CargueArchivo cargueArchivo;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_aprobacion")
    private Date fechaAprobacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_aprobacion")
    private UsuarioPersona usuarioAprobacion;

    // bi-directional many-to-one association to ConfiguracionCoactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion_coactivo")
    private ConfiguracionCoactivo configuracionCoactivo;

    // bi-directional many-to-one association to Coactivo
    @OneToMany(mappedBy = "cargueCoactivo", fetch = FetchType.LAZY)
    private List<Coactivo> coactivos;

    // bi-directional many-to-one association to Precoactivo
    @OneToMany(mappedBy = "cargueCoactivo", fetch = FetchType.LAZY)
    private List<Precoactivo> precoactivos;

    public CargueCoactivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Coactivo> getCoactivos() {
        return this.coactivos;
    }

    public void setCoactivos(List<Coactivo> coactivos) {
        this.coactivos = coactivos;
    }

    public Coactivo addCoactivo(Coactivo coactivo) {
        getCoactivos().add(coactivo);
        coactivo.setCargueCoactivo(this);

        return coactivo;
    }

    public Coactivo removeCoactivo(Coactivo coactivo) {
        getCoactivos().remove(coactivo);
        coactivo.setCargueCoactivo(null);

        return coactivo;
    }

    public List<Precoactivo> getPrecoactivos() {
        return this.precoactivos;
    }

    public void setPrecoactivos(List<Precoactivo> precoactivos) {
        this.precoactivos = precoactivos;
    }

    public Precoactivo addPrecoactivo(Precoactivo precoactivo) {
        getPrecoactivos().add(precoactivo);
        precoactivo.setCargueCoactivo(this);

        return precoactivo;
    }

    public Precoactivo removePrecoactivo(Precoactivo precoactivo) {
        getPrecoactivos().remove(precoactivo);
        precoactivo.setCargueCoactivo(null);

        return precoactivo;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public UsuarioPersona getUsuarioAprobacion() {
        return usuarioAprobacion;
    }

    public void setUsuarioAprobacion(UsuarioPersona usuarioAprobacion) {
        this.usuarioAprobacion = usuarioAprobacion;
    }

    public ConfiguracionCoactivo getConfiguracionCoactivo() {
        return this.configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivo configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

    public CargueArchivo getCargueArchivo() {
        return cargueArchivo;
    }

    public void setCargueArchivo(CargueArchivo cargueArchivo) {
        this.cargueArchivo = cargueArchivo;
    }

}