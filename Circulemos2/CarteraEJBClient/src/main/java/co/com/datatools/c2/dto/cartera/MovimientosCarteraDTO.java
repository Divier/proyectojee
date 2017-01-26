package co.com.datatools.c2.dto.cartera;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:23:24 COT 2015
 */
public class MovimientosCarteraDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaCreacion;
    private Date fechaMovimiento;
    private String loginUsuario;
    private String observaciones;
    private BigDecimal valorMovimiento;
    private ConceptoCarteraDTO conceptoCartera;
    private CarteraDTO cartera;
    private List<SaldoCarteraDTO> saldoCarteras;

    // --- Constructor
    public MovimientosCarteraDTO() {
    }

    public MovimientosCarteraDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaMovimiento() {
        return this.fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getValorMovimiento() {
        return this.valorMovimiento;
    }

    public void setValorMovimiento(BigDecimal valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public ConceptoCarteraDTO getConceptoCartera() {
        return this.conceptoCartera;
    }

    public void setConceptoCartera(ConceptoCarteraDTO conceptoCartera) {
        this.conceptoCartera = conceptoCartera;
    }

    public CarteraDTO getCartera() {
        return this.cartera;
    }

    public void setCartera(CarteraDTO cartera) {
        this.cartera = cartera;
    }

    public List<SaldoCarteraDTO> getSaldoCarteras() {
        return saldoCarteras;
    }

    public void setSaldoCarteras(List<SaldoCarteraDTO> saldoCarteras) {
        this.saldoCarteras = saldoCarteras;
    }

}
