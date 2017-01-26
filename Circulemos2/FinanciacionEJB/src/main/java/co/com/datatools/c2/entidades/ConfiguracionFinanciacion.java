package co.com.datatools.c2.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 */
@Entity
@Table(name = "configuracion_financiacion")
public class ConfiguracionFinanciacion implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_financiacion")
    private Integer id;

    @Column(name = "nombre_configuracion")
    private String nombre;

    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "configuracionFinanciacion",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<DetallePorcentajeCuotaInici> lstDetallePorcentajeCuotaInici;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "configuracionFinanciacion",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<ValorCondicionFinanciacion> lstValorCondicionFinanciacion;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "configuracionFinanciacion",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<DetalleCantidadCuota> lstDetalleCantidadCuota;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "configuracionFinanciacion",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<EstadoCondicionFinanciacion> lstEstadoCondicionFinanciacion;

    public ConfiguracionFinanciacion() {
    }

    public ConfiguracionFinanciacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<DetallePorcentajeCuotaInici> getLstDetallePorcentajeCuotaInici() {
        if (lstDetallePorcentajeCuotaInici == null) {
            lstDetallePorcentajeCuotaInici = new ArrayList<DetallePorcentajeCuotaInici>(0);
        }
        return lstDetallePorcentajeCuotaInici;
    }

    public void setLstDetallePorcentajeCuotaInici(List<DetallePorcentajeCuotaInici> lstDetallePorcentajeCuotaInici) {
        this.lstDetallePorcentajeCuotaInici = lstDetallePorcentajeCuotaInici;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public List<ValorCondicionFinanciacion> getLstValorCondicionFinanciacion() {
        return lstValorCondicionFinanciacion;
    }

    public void setLstValorCondicionFinanciacion(List<ValorCondicionFinanciacion> lstValorCondicionFinanciacion) {
        this.lstValorCondicionFinanciacion = lstValorCondicionFinanciacion;
    }

    public List<DetalleCantidadCuota> getLstDetalleCantidadCuota() {
        if (lstDetalleCantidadCuota == null) {
            lstDetalleCantidadCuota = new ArrayList<DetalleCantidadCuota>(0);
        }
        return lstDetalleCantidadCuota;
    }

    public void setLstDetalleCantidadCuota(List<DetalleCantidadCuota> lstDetalleCantidadCuota) {
        this.lstDetalleCantidadCuota = lstDetalleCantidadCuota;
    }

    public List<EstadoCondicionFinanciacion> getLstEstadoCondicionFinanciacion() {
        if (lstEstadoCondicionFinanciacion == null) {
            lstEstadoCondicionFinanciacion = new ArrayList<EstadoCondicionFinanciacion>(0);
        }
        return lstEstadoCondicionFinanciacion;
    }

    public void setLstEstadoCondicionFinanciacion(List<EstadoCondicionFinanciacion> lstEstadoCondicionFinanciacion) {
        this.lstEstadoCondicionFinanciacion = lstEstadoCondicionFinanciacion;
    }

}
