package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Aug 02 10:55:29 COT 2016
 */
public class InconsistenciaFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Long numeroFinanciacion;
    private Integer codigoOrganismo;
    private String codigoTipoIdentificacionCodeudor;
    private String codigoTipoIdentificacionDeudor;
    private Short estadoLectura;
    private Date fechaCreacion;
    private Date fechaFinanciacion;
    private Integer numeroCuotaFinanciacion;
    private String numeroIdentificacionCodeudor;
    private String numeroIdentificacionDeudor;
    private String primerApellidoCodeudor;
    private String primerApellidoDeudor;
    private String primerNombreCodeudor;
    private String primerNombreDeudor;
    private String razonSocial;
    private BigDecimal saldoFinanciacion;
    private String segundoApellidoCodeudor;
    private String segundoApellidoDeudor;
    private String segundoNombreCodeudor;
    private String segundoNombreDeudor;
    private String emailCodeudor;
    private String emailDeudor;
    private String telefonoCodeudor;
    private String telefonoDeudor;
    private String usuario;
    private BigDecimal valorTotalFinanciado;
    private Date fechaRegistro;
    private List<InconsistenciaDetalleCuotasFinanciacionDTO> detalleCuotasFinanciacionList;
    private List<InconsistenciaDetalleFinanciacionDTO> detalleFinanciacionList;

    // --- Constructor
    public InconsistenciaFinanciacionDTO() {
    }

    public InconsistenciaFinanciacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroFinanciacion() {
        return this.numeroFinanciacion;
    }

    public void setNumeroFinanciacion(Long numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
    }

    public Integer getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getCodigoTipoIdentificacionCodeudor() {
        return this.codigoTipoIdentificacionCodeudor;
    }

    public void setCodigoTipoIdentificacionCodeudor(String codigoTipoIdentificacionCodeudor) {
        this.codigoTipoIdentificacionCodeudor = codigoTipoIdentificacionCodeudor;
    }

    public String getCodigoTipoIdentificacionDeudor() {
        return this.codigoTipoIdentificacionDeudor;
    }

    public void setCodigoTipoIdentificacionDeudor(String codigoTipoIdentificacionDeudor) {
        this.codigoTipoIdentificacionDeudor = codigoTipoIdentificacionDeudor;
    }

    public Short getEstadoLectura() {
        return this.estadoLectura;
    }

    public void setEstadoLectura(Short estadoLectura) {
        this.estadoLectura = estadoLectura;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinanciacion() {
        return this.fechaFinanciacion;
    }

    public void setFechaFinanciacion(Date fechaFinanciacion) {
        this.fechaFinanciacion = fechaFinanciacion;
    }

    public Integer getNumeroCuotaFinanciacion() {
        return this.numeroCuotaFinanciacion;
    }

    public void setNumeroCuotaFinanciacion(Integer numeroCuotaFinanciacion) {
        this.numeroCuotaFinanciacion = numeroCuotaFinanciacion;
    }

    public String getNumeroIdentificacionCodeudor() {
        return this.numeroIdentificacionCodeudor;
    }

    public void setNumeroIdentificacionCodeudor(String numeroIdentificacionCodeudor) {
        this.numeroIdentificacionCodeudor = numeroIdentificacionCodeudor;
    }

    public String getNumeroIdentificacionDeudor() {
        return this.numeroIdentificacionDeudor;
    }

    public void setNumeroIdentificacionDeudor(String numeroIdentificacionDeudor) {
        this.numeroIdentificacionDeudor = numeroIdentificacionDeudor;
    }

    public String getPrimerApellidoCodeudor() {
        return this.primerApellidoCodeudor;
    }

    public void setPrimerApellidoCodeudor(String primerApellidoCodeudor) {
        this.primerApellidoCodeudor = primerApellidoCodeudor;
    }

    public String getPrimerApellidoDeudor() {
        return this.primerApellidoDeudor;
    }

    public void setPrimerApellidoDeudor(String primerApellidoDeudor) {
        this.primerApellidoDeudor = primerApellidoDeudor;
    }

    public String getPrimerNombreCodeudor() {
        return this.primerNombreCodeudor;
    }

    public void setPrimerNombreCodeudor(String primerNombreCodeudor) {
        this.primerNombreCodeudor = primerNombreCodeudor;
    }

    public String getPrimerNombreDeudor() {
        return this.primerNombreDeudor;
    }

    public void setPrimerNombreDeudor(String primerNombreDeudor) {
        this.primerNombreDeudor = primerNombreDeudor;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public BigDecimal getSaldoFinanciacion() {
        return this.saldoFinanciacion;
    }

    public void setSaldoFinanciacion(BigDecimal saldoFinanciacion) {
        this.saldoFinanciacion = saldoFinanciacion;
    }

    public String getSegundoApellidoCodeudor() {
        return this.segundoApellidoCodeudor;
    }

    public void setSegundoApellidoCodeudor(String segundoApellidoCodeudor) {
        this.segundoApellidoCodeudor = segundoApellidoCodeudor;
    }

    public String getSegundoApellidoDeudor() {
        return this.segundoApellidoDeudor;
    }

    public void setSegundoApellidoDeudor(String segundoApellidoDeudor) {
        this.segundoApellidoDeudor = segundoApellidoDeudor;
    }

    public String getSegundoNombreCodeudor() {
        return this.segundoNombreCodeudor;
    }

    public void setSegundoNombreCodeudor(String segundoNombreCodeudor) {
        this.segundoNombreCodeudor = segundoNombreCodeudor;
    }

    public String getSegundoNombreDeudor() {
        return this.segundoNombreDeudor;
    }

    public void setSegundoNombreDeudor(String segundoNombreDeudor) {
        this.segundoNombreDeudor = segundoNombreDeudor;
    }

    public String getEmailCodeudor() {
        return this.emailCodeudor;
    }

    public void setEmailCodeudor(String emailCodeudor) {
        this.emailCodeudor = emailCodeudor;
    }

    public String getEmailDeudor() {
        return this.emailDeudor;
    }

    public void setEmailDeudor(String emailDeudor) {
        this.emailDeudor = emailDeudor;
    }

    public String getTelefonoCodeudor() {
        return this.telefonoCodeudor;
    }

    public void setTelefonoCodeudor(String telefonoCodeudor) {
        this.telefonoCodeudor = telefonoCodeudor;
    }

    public String getTelefonoDeudor() {
        return this.telefonoDeudor;
    }

    public void setTelefonoDeudor(String telefonoDeudor) {
        this.telefonoDeudor = telefonoDeudor;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValorTotalFinanciado() {
        return this.valorTotalFinanciado;
    }

    public void setValorTotalFinanciado(BigDecimal valorTotalFinanciado) {
        this.valorTotalFinanciado = valorTotalFinanciado;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<InconsistenciaDetalleCuotasFinanciacionDTO> getDetalleCuotasFinanciacionList() {
        if (this.detalleCuotasFinanciacionList == null) {
            this.detalleCuotasFinanciacionList = new java.util.ArrayList<>(1);
        }
        return this.detalleCuotasFinanciacionList;
    }

    public void setDetalleCuotasFinanciacionList(
            List<InconsistenciaDetalleCuotasFinanciacionDTO> detalleCuotasFinanciacionList) {
        this.detalleCuotasFinanciacionList = detalleCuotasFinanciacionList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<InconsistenciaDetalleFinanciacionDTO> getDetalleFinanciacionList() {
        if (this.detalleFinanciacionList == null) {
            this.detalleFinanciacionList = new java.util.ArrayList<>(1);
        }
        return this.detalleFinanciacionList;
    }

    public void setDetalleFinanciacionList(List<InconsistenciaDetalleFinanciacionDTO> detalleFinanciacionList) {
        this.detalleFinanciacionList = detalleFinanciacionList;
    }

}
