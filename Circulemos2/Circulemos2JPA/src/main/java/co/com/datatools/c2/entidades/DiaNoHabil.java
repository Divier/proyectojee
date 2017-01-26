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

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "dia_no_habil")
@NamedQueries({
        @NamedQuery(name = "DiaNoHabil.findAll", query = "SELECT d FROM DiaNoHabil d"),
        @NamedQuery(
                name = "DiaNoHabil.countDiaNoHabilByFecha",
                query = "SELECT COUNT(dnh) FROM DiaNoHabil AS dnh WHERE dnh.organismoTransito.codigoOrganismo = :codOrg AND dnh.fecha = :fec") })
public class DiaNoHabil implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * SELECT COUNT(dnh) FROM DiaNoHabil AS dnh WHERE dnh.organismoTransito.codigoOrganismo = :codOrg AND dnh.fecha = :fec
     */
    public static final String SQ_COUNT_BETWEEN_FECHA = "DiaNoHabil.countDiaNoHabilByFecha";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dia_no_habil")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "observacion")
    private String observacion;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    public DiaNoHabil() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

}
