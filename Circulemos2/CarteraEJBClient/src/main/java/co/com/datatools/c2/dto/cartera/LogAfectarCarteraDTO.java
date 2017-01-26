package co.com.datatools.c2.dto.cartera;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 10:46:51 COT 2015
 */
public class LogAfectarCarteraDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer codigoExcepcion;
    private String descripcionExcepcion;
    private Date fechaCreacion;
    private Date fechaMovimiento;
    private BigDecimal valorMovimiento;
    private CarteraDTO cartera;
    private ConceptoCarteraDTO conceptoCartera;
    private UsuarioPersonaDTO usuarioPersona;

    // --- Constructor
    public LogAfectarCarteraDTO() {
    }

    public LogAfectarCarteraDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoExcepcion() {
        return this.codigoExcepcion;
    }

    public void setCodigoExcepcion(Integer codigoExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
    }

    public String getDescripcionExcepcion() {
        return this.descripcionExcepcion;
    }

    public void setDescripcionExcepcion(String descripcionExcepcion) {
        this.descripcionExcepcion = descripcionExcepcion;
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

    public BigDecimal getValorMovimiento() {
        return this.valorMovimiento;
    }

    public void setValorMovimiento(BigDecimal valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public CarteraDTO getCartera() {
        return this.cartera;
    }

    public void setCartera(CarteraDTO cartera) {
        this.cartera = cartera;
    }

    public ConceptoCarteraDTO getConceptoCartera() {
        return this.conceptoCartera;
    }

    public void setConceptoCartera(ConceptoCarteraDTO conceptoCartera) {
        this.conceptoCartera = conceptoCartera;
    }

    public UsuarioPersonaDTO getUsuarioPersona() {
        return this.usuarioPersona;
    }

    public void setUsuarioPersona(UsuarioPersonaDTO usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

}
