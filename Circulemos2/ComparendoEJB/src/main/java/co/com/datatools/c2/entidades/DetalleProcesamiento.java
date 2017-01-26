package co.com.datatools.c2.entidades;

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

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "detalle_procesamiento")
@NamedQueries({ @NamedQuery(name = "DetalleProcesamiento.findAll", query = "SELECT d FROM DetalleProcesamiento d") })
public class DetalleProcesamiento implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_procesamiento")
    private Integer id;

    @JoinColumn(name = "codigo_campo", referencedColumnName = "codigo_campo")
    @ManyToOne(fetch = FetchType.LAZY)
    private CampoEntidad campoEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_error_procesamiento")
    private ErrorProcesamiento errorProcesamiento;

    @JoinColumn(name = "id_procesamiento", referencedColumnName = "id_procesamiento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProcesaComparendo procesaComparendo;

    public DetalleProcesamiento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CampoEntidad getCampoEntidad() {
        return campoEntidad;
    }

    public void setCampoEntidad(CampoEntidad campoEntidad) {
        this.campoEntidad = campoEntidad;
    }

    public ProcesaComparendo getProcesaComparendo() {
        return procesaComparendo;
    }

    public void setProcesaComparendo(ProcesaComparendo procesaComparendo) {
        this.procesaComparendo = procesaComparendo;
    }

    public ErrorProcesamiento getErrorProcesamiento() {
        return errorProcesamiento;
    }

    public void setErrorProcesamiento(ErrorProcesamiento errorProcesamiento) {
        this.errorProcesamiento = errorProcesamiento;
    }

}
