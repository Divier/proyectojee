package co.com.datatools.c2.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 */
@Entity
@Table(name = "variable_condicion_finan")
public class VariableCondicionFinan implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_variable_condicion_finan")
    private Integer id;

    @Column(name = "nombre_variable")
    private String nombre;

    @Column(name = "orden")
    private Integer orden;

    @Lob
    @Column(name = "fuente_data")
    private String fuenteData;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variableCondicionFinan", fetch = FetchType.LAZY)
    private List<DetallePorcentajeCuotaInici> lstDetallePorcentajeCuotaInici;

    @JoinColumn(name = "codigo_condicion", referencedColumnName = "codigo_condicion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CondicionFinanciacion condicionFinanciacion;

    @JoinColumn(name = "codigo_tipo_variable", referencedColumnName = "codigo_tipo_variable")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoVariableFinanciacion tipoVariableFinanciacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variableCondicionFinan", fetch = FetchType.LAZY)
    private List<ValorCondicionFinanciacion> lstValorCondicionFinanciacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variableCondicionFinan", fetch = FetchType.LAZY)
    private List<DetalleCantidadCuota> lstDetalleCantidadCuota;

    public VariableCondicionFinan() {
    }

    public VariableCondicionFinan(Integer id) {
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getFuenteData() {
        return fuenteData;
    }

    public void setFuenteData(String fuenteData) {
        this.fuenteData = fuenteData;
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

    public CondicionFinanciacion getCondicionFinanciacion() {
        return condicionFinanciacion;
    }

    public void setCondicionFinanciacion(CondicionFinanciacion condicionFinanciacion) {
        this.condicionFinanciacion = condicionFinanciacion;
    }

    public TipoVariableFinanciacion getTipoVariableFinanciacion() {
        return tipoVariableFinanciacion;
    }

    public void setTipoVariableFinanciacion(TipoVariableFinanciacion tipoVariableFinanciacion) {
        this.tipoVariableFinanciacion = tipoVariableFinanciacion;
    }

    public List<ValorCondicionFinanciacion> getLstValorCondicionFinanciacion() {
        if (lstValorCondicionFinanciacion == null) {
            lstValorCondicionFinanciacion = new ArrayList<ValorCondicionFinanciacion>(0);
        }
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

}
