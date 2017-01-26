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

import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "firma_persona")
@NamedQueries({ @NamedQuery(name = "FirmaPersona.findAll", query = "SELECT e FROM FirmaPersona e") })
public class FirmaPersona implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_firma_persona")
    private Integer id;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Basic(optional = false)
    @Column(name = "numero_imagen")
    private Long numeroImagen;

    public FirmaPersona() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getNumeroImagen() {
        return numeroImagen;
    }

    public void setNumeroImagen(Long numeroImagen) {
        this.numeroImagen = numeroImagen;
    }

}
