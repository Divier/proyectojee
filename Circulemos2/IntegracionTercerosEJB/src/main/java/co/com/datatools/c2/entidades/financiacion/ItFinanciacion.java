package co.com.datatools.c2.entidades.financiacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the it_financiacion database table.
 * 
 */
@Entity
@Table(name = "it_financiacion")
@NamedQuery(name = "ItFinanciacion.findAll", query = "SELECT i FROM ItFinanciacion i")
public class ItFinanciacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "numero_financiacion")
    private Long numeroFinanciacion;

    @Column(name = "codigo_organismo")
    private Integer codigoOrganismo;

    @Column(name = "codigo_tipo_identificacion_codeudor")
    private String codigoTipoIdentificacionCodeudor;

    @Column(name = "codigo_tipo_identificacion_deudor")
    private String codigoTipoIdentificacionDeudor;

    @Column(name = "estado_lectura")
    private Short estadoLectura;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_financiacion")
    private Date fechaFinanciacion;

    @Column(name = "numero_cuota_financiacion")
    private Integer numeroCuotaFinanciacion;

    @Column(name = "numero_identificacion_codeudor")
    private String numeroIdentificacionCodeudor;

    @Column(name = "numero_identificacion_deudor")
    private String numeroIdentificacionDeudor;

    @Column(name = "primer_apellido_codeudor")
    private String primerApellidoCodeudor;

    @Column(name = "primer_apellido_deudor")
    private String primerApellidoDeudor;

    @Column(name = "primer_nombre_codeudor")
    private String primerNombreCodeudor;

    @Column(name = "primer_nombre_deudor")
    private String primerNombreDeudor;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "saldo_financiacion")
    private BigDecimal saldoFinanciacion;

    @Column(name = "segundo_apellido_codeudor")
    private String segundoApellidoCodeudor;

    @Column(name = "segundo_apellido_deudor")
    private String segundoApellidoDeudor;

    @Column(name = "segundo_nombre_codeudor")
    private String segundoNombreCodeudor;

    @Column(name = "segundo_nombre_deudor")
    private String segundoNombreDeudor;

    @Column(name = "email_codeudor")
    private String emailCodeudor;

    @Column(name = "email_deudor")
    private String emailDeudor;

    @Column(name = "telefono_codeudor")
    private String telefonoCodeudor;

    @Column(name = "telefono_deudor")
    private String telefonoDeudor;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "valor_total_financiado")
    private BigDecimal valorTotalFinanciado;

    // bi-directional many-to-one association to ItDetalleCuotasFinanciacion
    @OneToMany(mappedBy = "itFinanciacion")
    private List<ItDetalleCuotasFinanciacion> detalleCuotasFinanciacionList;

    public Long getNumeroFinanciacion() {
        return numeroFinanciacion;
    }

    public void setNumeroFinanciacion(Long numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getCodigoTipoIdentificacionCodeudor() {
        return codigoTipoIdentificacionCodeudor;
    }

    public void setCodigoTipoIdentificacionCodeudor(String codigoTipoIdentificacionCodeudor) {
        this.codigoTipoIdentificacionCodeudor = codigoTipoIdentificacionCodeudor;
    }

    public String getCodigoTipoIdentificacionDeudor() {
        return codigoTipoIdentificacionDeudor;
    }

    public void setCodigoTipoIdentificacionDeudor(String codigoTipoIdentificacionDeudor) {
        this.codigoTipoIdentificacionDeudor = codigoTipoIdentificacionDeudor;
    }

    public Short getEstadoLectura() {
        return estadoLectura;
    }

    public void setEstadoLectura(Short estadoLectura) {
        this.estadoLectura = estadoLectura;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinanciacion() {
        return fechaFinanciacion;
    }

    public void setFechaFinanciacion(Date fechaFinanciacion) {
        this.fechaFinanciacion = fechaFinanciacion;
    }

    public Integer getNumeroCuotaFinanciacion() {
        return numeroCuotaFinanciacion;
    }

    public void setNumeroCuotaFinanciacion(Integer numeroCuotaFinanciacion) {
        this.numeroCuotaFinanciacion = numeroCuotaFinanciacion;
    }

    public String getNumeroIdentificacionCodeudor() {
        return numeroIdentificacionCodeudor;
    }

    public void setNumeroIdentificacionCodeudor(String numeroIdentificacionCodeudor) {
        this.numeroIdentificacionCodeudor = numeroIdentificacionCodeudor;
    }

    public String getNumeroIdentificacionDeudor() {
        return numeroIdentificacionDeudor;
    }

    public void setNumeroIdentificacionDeudor(String numeroIdentificacionDeudor) {
        this.numeroIdentificacionDeudor = numeroIdentificacionDeudor;
    }

    public String getPrimerApellidoCodeudor() {
        return primerApellidoCodeudor;
    }

    public void setPrimerApellidoCodeudor(String primerApellidoCodeudor) {
        this.primerApellidoCodeudor = primerApellidoCodeudor;
    }

    public String getPrimerApellidoDeudor() {
        return primerApellidoDeudor;
    }

    public void setPrimerApellidoDeudor(String primerApellidoDeudor) {
        this.primerApellidoDeudor = primerApellidoDeudor;
    }

    public String getPrimerNombreCodeudor() {
        return primerNombreCodeudor;
    }

    public void setPrimerNombreCodeudor(String primerNombreCodeudor) {
        this.primerNombreCodeudor = primerNombreCodeudor;
    }

    public String getPrimerNombreDeudor() {
        return primerNombreDeudor;
    }

    public void setPrimerNombreDeudor(String primerNombreDeudor) {
        this.primerNombreDeudor = primerNombreDeudor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public BigDecimal getSaldoFinanciacion() {
        return saldoFinanciacion;
    }

    public void setSaldoFinanciacion(BigDecimal saldoFinanciacion) {
        this.saldoFinanciacion = saldoFinanciacion;
    }

    public String getSegundoApellidoCodeudor() {
        return segundoApellidoCodeudor;
    }

    public void setSegundoApellidoCodeudor(String segundoApellidoCodeudor) {
        this.segundoApellidoCodeudor = segundoApellidoCodeudor;
    }

    public String getSegundoApellidoDeudor() {
        return segundoApellidoDeudor;
    }

    public void setSegundoApellidoDeudor(String segundoApellidoDeudor) {
        this.segundoApellidoDeudor = segundoApellidoDeudor;
    }

    public String getSegundoNombreCodeudor() {
        return segundoNombreCodeudor;
    }

    public void setSegundoNombreCodeudor(String segundoNombreCodeudor) {
        this.segundoNombreCodeudor = segundoNombreCodeudor;
    }

    public String getSegundoNombreDeudor() {
        return segundoNombreDeudor;
    }

    public void setSegundoNombreDeudor(String segundoNombreDeudor) {
        this.segundoNombreDeudor = segundoNombreDeudor;
    }

    public String getEmailCodeudor() {
        return emailCodeudor;
    }

    public void setEmailCodeudor(String emailCodeudor) {
        this.emailCodeudor = emailCodeudor;
    }

    public String getEmailDeudor() {
        return emailDeudor;
    }

    public void setEmailDeudor(String emailDeudor) {
        this.emailDeudor = emailDeudor;
    }

    public String getTelefonoCodeudor() {
        return telefonoCodeudor;
    }

    public void setTelefonoCodeudor(String telefonoCodeudor) {
        this.telefonoCodeudor = telefonoCodeudor;
    }

    public String getTelefonoDeudor() {
        return telefonoDeudor;
    }

    public void setTelefonoDeudor(String telefonoDeudor) {
        this.telefonoDeudor = telefonoDeudor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValorTotalFinanciado() {
        return valorTotalFinanciado;
    }

    public void setValorTotalFinanciado(BigDecimal valorTotalFinanciado) {
        this.valorTotalFinanciado = valorTotalFinanciado;
    }

    public List<ItDetalleCuotasFinanciacion> getDetalleCuotasFinanciacionList() {
        return detalleCuotasFinanciacionList;
    }

    public void setDetalleCuotasFinanciacionList(List<ItDetalleCuotasFinanciacion> detalleCuotasFinanciacionList) {
        this.detalleCuotasFinanciacionList = detalleCuotasFinanciacionList;
    }

    public List<ItDetalleFinanciacion> getDetalleFinanciacionList() {
        return detalleFinanciacionList;
    }

    public void setDetalleFinanciacionList(List<ItDetalleFinanciacion> detalleFinanciacionList) {
        this.detalleFinanciacionList = detalleFinanciacionList;
    }

    // bi-directional many-to-one association to ItDetalleFinanciacion
    @OneToMany(mappedBy = "itFinanciacion")
    private List<ItDetalleFinanciacion> detalleFinanciacionList;

    public ItFinanciacion() {
    }

}