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

/**
 * The persistent class for the precoactivo database table.
 * 
 */
@Entity
@Table(name = "coactivo_oficio_bien")
public class CoactivoOficioBien implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coactivo_oficio_bien")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oficio_bien")
    private OficioBien oficioBien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coactivo")
    private Coactivo coactivo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_respuesta")
    private Date fechaRespuesta;

    @Column(name = "numero_doc_respuesta")
    private String numeroDocRespuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bien")
    private Bien bien;

    @Column(name = "tiene_bien")
    private Boolean tieneBien;

    public CoactivoOficioBien() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OficioBien getOficioBien() {
        return oficioBien;
    }

    public void setOficioBien(OficioBien oficioBien) {
        this.oficioBien = oficioBien;
    }

    public Coactivo getCoactivo() {
        return coactivo;
    }

    public void setCoactivo(Coactivo coactivo) {
        this.coactivo = coactivo;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getNumeroDocRespuesta() {
        return numeroDocRespuesta;
    }

    public void setNumeroDocRespuesta(String numeroDocRespuesta) {
        this.numeroDocRespuesta = numeroDocRespuesta;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Boolean getTieneBien() {
        return tieneBien;
    }

    public void setTieneBien(Boolean tieneBien) {
        this.tieneBien = tieneBien;
    }
}