package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the it_recaudo database table.
 * 
 */
@Entity
@Table(name = "it_recaudo")
@NamedQuery(name = "ItRecaudo.findAll", query = "SELECT i FROM ItRecaudo i")
public class ItRecaudo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recaudo")
    private Long idRecaudo;

    @Column(name = "codigo_banco")
    private String codigoBanco;

    @Column(name = "codigo_organismo")
    private Integer codigoOrganismo;

    @Column(name = "codigo_tipo_cuenta")
    private String codigoTipoCuenta;

    @Column(name = "codigo_tipo_identificacion")
    private String codigoTipoIdentificacion;

    @Column(name = "estado_lectura")
    private Short estadoLectura;

    @Column(name = "fecha_transaccion")
    @Temporal(TemporalType.DATE)
    private Date fechaTransaccion;

    @Column(name = "hora_transaccion")
    @Temporal(TemporalType.TIME)
    private Date horaTransaccion;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @Column(name = "numero_recaudo")
    private String numeroRecaudo;

    @Column(name = "total_cheque")
    private BigDecimal totalCheque;

    @Column(name = "total_efectivo")
    private BigDecimal totalEfectivo;

    @Column(name = "total_recaudo")
    private BigDecimal totalRecaudo;

    @Column(name = "codigo_tipo_recaudo")
    private String codigoTipoRecaudo;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "numero_obligacion")
    private String numeroObligacion;

    @Column(name = "valor_obligacion")
    private BigDecimal valorObligacion;

    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "usuario")
    private String usuario;

    public ItRecaudo() {
    }

    public long getIdRecaudo() {
        return this.idRecaudo;
    }

    public void setIdRecaudo(long idRecaudo) {
        this.idRecaudo = idRecaudo;
    }

    public String getCodigoBanco() {
        return this.codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public int getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getCodigoTipoCuenta() {
        return this.codigoTipoCuenta;
    }

    public void setCodigoTipoCuenta(String codigoTipoCuenta) {
        this.codigoTipoCuenta = codigoTipoCuenta;
    }

    public String getCodigoTipoIdentificacion() {
        return this.codigoTipoIdentificacion;
    }

    public void setCodigoTipoIdentificacion(String codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public Short getEstadoLectura() {
        return this.estadoLectura;
    }

    public void setEstadoLectura(Short estadoLectura) {
        this.estadoLectura = estadoLectura;
    }

    public Date getFechaTransaccion() {
        return this.fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Date getHoraTransaccion() {
        return this.horaTransaccion;
    }

    public void setHoraTransaccion(Date horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroIdentificacion() {
        return this.numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNumeroRecaudo() {
        return this.numeroRecaudo;
    }

    public void setNumeroRecaudo(String numeroRecaudo) {
        this.numeroRecaudo = numeroRecaudo;
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

    public String getCodigoTipoRecaudo() {
        return codigoTipoRecaudo;
    }

    public void setCodigoTipoRecaudo(String codigoTipoRecaudo) {
        this.codigoTipoRecaudo = codigoTipoRecaudo;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public void setIdRecaudo(Long idRecaudo) {
        this.idRecaudo = idRecaudo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}