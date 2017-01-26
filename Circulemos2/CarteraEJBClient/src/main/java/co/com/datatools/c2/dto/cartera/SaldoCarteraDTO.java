package co.com.datatools.c2.dto.cartera;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:23:35 COT 2015
 */
public class SaldoCarteraDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaCalculo;
    private Date fechaRegistro;
    private BigDecimal saldo;
    private CarteraDTO cartera;
    private TipoSaldoDTO tipoSaldo;
    private MovimientosCarteraDTO movimientoCartera;

    // --- Constructor
    public SaldoCarteraDTO() {
    }

    public SaldoCarteraDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCalculo() {
        return this.fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public CarteraDTO getCartera() {
        return this.cartera;
    }

    public void setCartera(CarteraDTO cartera) {
        this.cartera = cartera;
    }

    public TipoSaldoDTO getTipoSaldo() {
        return this.tipoSaldo;
    }

    public void setTipoSaldo(TipoSaldoDTO tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }

    public MovimientosCarteraDTO getMovimientoCartera() {
        return movimientoCartera;
    }

    public void setMovimientoCartera(MovimientosCarteraDTO movimientoCartera) {
        this.movimientoCartera = movimientoCartera;
    }

}
