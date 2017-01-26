package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the pago database table.
 * 
 */
@Entity
@NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p")
public class Pago implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_transaccion")
    private Date fechaTransaccion;

    @Column(name = "id_banco")
    private Integer idBanco;

    @Column(name = "id_tipo_cuenta")
    private Integer idTipoCuenta;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "numero_pago")
    private String numeroPago;

    @Column(name = "total_cheque")
    private BigDecimal totalCheque;

    @Column(name = "total_efectivo")
    private BigDecimal totalEfectivo;

    @Column(name = "total_recaudo")
    private BigDecimal totalRecaudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_organismo")
    private OrganismoTransito organismoTransito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_identificacion")
    private TipoIdentificacionPersona tipoIdentificacionPersona;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @OneToMany(mappedBy = "pago", cascade = CascadeType.ALL)
    private List<DetallePago> detallesPago;

    @OneToMany(mappedBy = "pago", cascade = CascadeType.ALL)
    private List<PagoConciliacionError> pagoConciliacionErrores;

    public Pago() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaTransaccion() {
        return this.fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Integer getIdBanco() {
        return this.idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdTipoCuenta() {
        return this.idTipoCuenta;
    }

    public void setIdTipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroPago() {
        return this.numeroPago;
    }

    public void setNumeroPago(String numeroPago) {
        this.numeroPago = numeroPago;
    }

    public BigDecimal getTotalCheque() {
        return this.totalCheque;
    }

    public void setTotalCheque(BigDecimal totalCheque) {
        this.totalCheque = totalCheque;
    }

    public BigDecimal getTotalEfectivo() {
        return this.totalEfectivo;
    }

    public void setTotalEfectivo(BigDecimal totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public BigDecimal getTotalRecaudo() {
        return this.totalRecaudo;
    }

    public void setTotalRecaudo(BigDecimal totalRecaudo) {
        this.totalRecaudo = totalRecaudo;
    }

    public OrganismoTransito getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public List<DetallePago> getDetallesPago() {
        return detallesPago;
    }

    public void setDetallesPago(List<DetallePago> detallesPago) {
        this.detallesPago = detallesPago;
    }

    public List<PagoConciliacionError> getPagoConciliacionErrores() {
        if (pagoConciliacionErrores == null)
            pagoConciliacionErrores = new ArrayList<PagoConciliacionError>();
        return pagoConciliacionErrores;
    }

    public void setPagoConciliacionErrores(List<PagoConciliacionError> pagoConciliacionErrores) {
        this.pagoConciliacionErrores = pagoConciliacionErrores;
    }

    public TipoIdentificacionPersona getTipoIdentificacionPersona() {
        return tipoIdentificacionPersona;
    }

    public void setTipoIdentificacionPersona(TipoIdentificacionPersona tipoIdentificacionPersona) {
        this.tipoIdentificacionPersona = tipoIdentificacionPersona;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}