package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * No regenerar, atributos de negocio
 * 
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:40:40 COT 2016
 */
public class ObligacionCoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer codigoTipoObligacion;
    private Date fechaObligacion;
    private CarteraDTO cartera;
    private String numeroObligacion;
    private BigDecimal valorCostasProcesales;
    private BigDecimal valorInteresMoratorios;
    private BigDecimal valorObligacion;
    private CoactivoDTO coactivo;
    // Atributos agregados
    private Long idPrecoativo;
    // Atributo agregado para validacion de la direccion
    private DireccionDTO direccionValida;
    // Atributo medio imposicion
    private String nombreTipoObligacion;
    // Atributo numero citacion
    private String numeroCitacion;

    // --- Constructor
    public ObligacionCoactivoDTO() {
    }

    public ObligacionCoactivoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoTipoObligacion() {
        return this.codigoTipoObligacion;
    }

    public void setCodigoTipoObligacion(Integer codigoTipoObligacion) {
        this.codigoTipoObligacion = codigoTipoObligacion;
    }

    public Date getFechaObligacion() {
        return this.fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public CarteraDTO getCartera() {
        return this.cartera;
    }

    public void setCartera(CarteraDTO cartera) {
        this.cartera = cartera;
    }

    public String getNumeroObligacion() {
        return this.numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public BigDecimal getValorCostasProcesales() {
        return this.valorCostasProcesales;
    }

    public void setValorCostasProcesales(BigDecimal valorCostasProcesales) {
        this.valorCostasProcesales = valorCostasProcesales;
    }

    public BigDecimal getValorInteresMoratorios() {
        return this.valorInteresMoratorios;
    }

    public void setValorInteresMoratorios(BigDecimal valorInteresMoratorios) {
        this.valorInteresMoratorios = valorInteresMoratorios;
    }

    public BigDecimal getValorObligacion() {
        return this.valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public CoactivoDTO getCoactivo() {
        return this.coactivo;
    }

    public void setCoactivo(CoactivoDTO coactivo) {
        this.coactivo = coactivo;
    }

    public Long getIdPrecoativo() {
        return idPrecoativo;
    }

    public void setIdPrecoativo(Long idPrecoativo) {
        this.idPrecoativo = idPrecoativo;
    }

    public DireccionDTO getDireccionValida() {
        return direccionValida;
    }

    public void setDireccionValida(DireccionDTO direccionValida) {
        this.direccionValida = direccionValida;
    }

    public String getNombreTipoObligacion() {
        return nombreTipoObligacion;
    }

    public void setNombreTipoObligacion(String nombreTipoObligacion) {
        this.nombreTipoObligacion = nombreTipoObligacion;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

}
