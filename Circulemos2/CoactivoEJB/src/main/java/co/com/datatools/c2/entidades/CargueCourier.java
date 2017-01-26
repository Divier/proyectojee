package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the cargue_courier database table.
 * 
 */
@Entity
@Table(name = "cargue_courier")
@NamedQuery(name = "CargueCourier.findAll", query = "SELECT c FROM CargueCourier c")
public class CargueCourier implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargue_courier")
    private Long id;

    @Column(name = "consecutivo")
    private String consecutivo;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_cargue")
    private Date fechaCargue;

    @Column(name = "id_documento_cargue")
    private Long idDocumentoCargue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_cargue")
    private UsuarioPersona usuarioCargue;

    // bi-directional many-to-one association to DetalleCargueCourier
    @OneToMany(mappedBy = "cargueCourier", fetch = FetchType.LAZY)
    private List<DetalleCargueCourier> detalleCargueCouriers;

    public CargueCourier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getIdDocumentoCargue() {
        return this.idDocumentoCargue;
    }

    public void setIdDocumentoCargue(Long idDocumentoCargue) {
        this.idDocumentoCargue = idDocumentoCargue;
    }

    public List<DetalleCargueCourier> getDetalleCargueCouriers() {
        return this.detalleCargueCouriers;
    }

    public void setDetalleCargueCouriers(List<DetalleCargueCourier> detalleCargueCouriers) {
        this.detalleCargueCouriers = detalleCargueCouriers;
    }

    public DetalleCargueCourier addDetalleCargueCourier(DetalleCargueCourier detalleCargueCourier) {
        getDetalleCargueCouriers().add(detalleCargueCourier);
        detalleCargueCourier.setCargueCourier(this);

        return detalleCargueCourier;
    }

    public DetalleCargueCourier removeDetalleCargueCourier(DetalleCargueCourier detalleCargueCourier) {
        getDetalleCargueCouriers().remove(detalleCargueCourier);
        detalleCargueCourier.setCargueCourier(null);

        return detalleCargueCourier;
    }

    public Date getFechaCargue() {
        return fechaCargue;
    }

    public void setFechaCargue(Date fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public UsuarioPersona getUsuarioCargue() {
        return usuarioCargue;
    }

    public void setUsuarioCargue(UsuarioPersona usuarioCargue) {
        this.usuarioCargue = usuarioCargue;
    }

}