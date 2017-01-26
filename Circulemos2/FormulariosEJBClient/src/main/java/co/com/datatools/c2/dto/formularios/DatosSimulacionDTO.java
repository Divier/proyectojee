/**
 * 
 */
package co.com.datatools.c2.dto.formularios;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author javier.fajardo
 *
 */
public class DatosSimulacionDTO implements EntidadDtoC2 {

	private static final long serialVersionUID = 1L;

	// --- Atributos
    private String tipoDocumento;
    private String numeroDocumento;  
    private String nombresInfractor;
	private Integer numeroCuota;
	private BigDecimal valorCuotaInicial;
	
	private Date fechaSolicitud;
	private BigDecimal tasaInteresFinanciacion;
    private BigDecimal valorTotalCitacion;
    private BigDecimal valorTotalRecargos;
    private BigDecimal valorTotalCostas;
    private BigDecimal valorTotal;
    private BigDecimal valorTotalFinanciarInteres;

	// --- Constructor
    public DatosSimulacionDTO() {}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombresInfractor() {
		return nombresInfractor;
	}

	public void setNombresInfractor(String nombresInfractor) {
		this.nombresInfractor = nombresInfractor;
	}

	public Integer getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public BigDecimal getValorCuotaInicial() {
		return valorCuotaInicial;
	}

	public void setValorCuotaInicial(BigDecimal valorCuotaInicial) {
		this.valorCuotaInicial = valorCuotaInicial;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public BigDecimal getTasaInteresFinanciacion() {
		return tasaInteresFinanciacion;
	}

	public void setTasaInteresFinanciacion(BigDecimal tasaInteresFinanciacion) {
		this.tasaInteresFinanciacion = tasaInteresFinanciacion;
	}

	public BigDecimal getValorTotalCitacion() {
		return valorTotalCitacion;
	}

	public void setValorTotalCitacion(BigDecimal valorTotalCitacion) {
		this.valorTotalCitacion = valorTotalCitacion;
	}

	public BigDecimal getValorTotalRecargos() {
		return valorTotalRecargos;
	}

	public void setValorTotalRecargos(BigDecimal valorTotalRecargos) {
		this.valorTotalRecargos = valorTotalRecargos;
	}

	public BigDecimal getValorTotalCostas() {
		return valorTotalCostas;
	}

	public void setValorTotalCostas(BigDecimal valorTotalCostas) {
		this.valorTotalCostas = valorTotalCostas;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTotalFinanciarInteres() {
		return valorTotalFinanciarInteres;
	}

	public void setValorTotalFinanciarInteres(BigDecimal valorTotalFinanciarInteres) {
		this.valorTotalFinanciarInteres = valorTotalFinanciarInteres;
	}
}
