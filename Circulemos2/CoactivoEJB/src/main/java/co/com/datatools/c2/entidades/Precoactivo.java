package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
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

import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.personas.Funcionario;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the precoactivo database table.
 * 
 */
@Entity
@Table(name = "precoactivo")
@NamedQuery(name = "Precoactivo.findAll", query = "SELECT p FROM Precoactivo p")
public class Precoactivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precoactivo")
    private Long id;

    // bi-directional many-to-one association to CargueCoactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargue_coactivo")
    private CargueCoactivo cargueCoactivo;

    // bi-directional many-to-one association to EstadoPrecoactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_precoactivo")
    private EstadoPrecoactivo estadoPrecoactivo;

    // bi-directional many-to-one association to Persona
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_deudor")
    private Persona persona;

    // bi-directional many-to-one association to Funcionario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    // bi-directional many-to-one association to PrecoactivoError
    @OneToMany(mappedBy = "precoactivo", fetch = FetchType.LAZY)
    private List<PrecoactivoError> precoactivoErrors;

    @Column(name = "codigo_tipo_obligacion")
    private Integer codigoTipoObligacion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_obligacion")
    private Date fechaObligacion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartera")
    private Cartera cartera;

    @Column(name = "numero_obligacion")
    private String numeroObligacion;

    @Column(name = "valor_interes_moratorios")
    private BigDecimal valorInteresMoratorios;

    @Column(name = "valor_obligacion")
    private BigDecimal valorObligacion;

    // bi-directional many-to-one association to Direccion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;

    public Precoactivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CargueCoactivo getCargueCoactivo() {
        return this.cargueCoactivo;
    }

    public void setCargueCoactivo(CargueCoactivo cargueCoactivo) {
        this.cargueCoactivo = cargueCoactivo;
    }

    public EstadoPrecoactivo getEstadoPrecoactivo() {
        return this.estadoPrecoactivo;
    }

    public void setEstadoPrecoactivo(EstadoPrecoactivo estadoPrecoactivo) {
        this.estadoPrecoactivo = estadoPrecoactivo;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<PrecoactivoError> getPrecoactivoErrors() {
        return this.precoactivoErrors;
    }

    public void setPrecoactivoErrors(List<PrecoactivoError> precoactivoErrors) {
        this.precoactivoErrors = precoactivoErrors;
    }

    public PrecoactivoError addPrecoactivoError(PrecoactivoError precoactivoError) {
        getPrecoactivoErrors().add(precoactivoError);
        precoactivoError.setPrecoactivo(this);

        return precoactivoError;
    }

    public PrecoactivoError removePrecoactivoError(PrecoactivoError precoactivoError) {
        getPrecoactivoErrors().remove(precoactivoError);
        precoactivoError.setPrecoactivo(null);

        return precoactivoError;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getCodigoTipoObligacion() {
        return codigoTipoObligacion;
    }

    public void setCodigoTipoObligacion(Integer codigoTipoObligacion) {
        this.codigoTipoObligacion = codigoTipoObligacion;
    }

    public Date getFechaObligacion() {
        return fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public Cartera getCartera() {
        return cartera;
    }

    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public BigDecimal getValorInteresMoratorios() {
        return valorInteresMoratorios;
    }

    public void setValorInteresMoratorios(BigDecimal valorInteresMoratorios) {
        this.valorInteresMoratorios = valorInteresMoratorios;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}