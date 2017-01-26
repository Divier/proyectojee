package co.com.datatools.c2.entidades.comun;

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

import co.com.datatools.util.dto.EntidadC2;

/**
 * @author claudia.rodriguez
 * @version Iteracion 4
 */
@Entity
@Table(name = "direccion_organismo")
@NamedQueries({ @NamedQuery(
        name = "DireccionOrganismo.findVigenteByOrganismo",
        query = "SELECT do FROM DireccionOrganismo AS do LEFT JOIN FETCH do.direccion WHERE do.organismoTransito.codigoOrganismo = :pCodOrg AND do.fechaInicio <= :pFecAct AND do.fechaFin IS NULL ORDER BY do.fechaInicio DESC"), })
public class DireccionOrganismo implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    /**
     * Consulta que permite obtener una direccion vigente de un organismo de transito.
     * 
     * Parametros:
     * <p>
     * <li>pCodOrg Codigo del organismo</li>
     * <li>pFecAct Fecha actual del sistema</li>
     * <p>
     * <br>
     * Consulta:
     * 
     * SELECT do FROM DireccionOrganismo AS do LEFT JOIN FETCH do.direccion WHERE do.organismoTransito.codigoOrganismo = :pCodOrg AND do.fechaInicio <= :pFecAct AND
     * do.fechaFin IS NULL ORDER BY do.fechaInicio DESC
     * 
     * @author luis.forero (2015-01-21)
     */
    public static final String SQ_FIND_VIGENTE_BY_ORGANISMO = "DireccionOrganismo.findVigenteByOrganismo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_direccion_organismo")
    private Long id;

    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Direccion direccion;

    public DireccionOrganismo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
