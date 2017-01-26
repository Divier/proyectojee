package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 17:15:14 COT 2015
 */
public class InteresDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaFinal;
    private Date fechaInicial;
    private BigDecimal porcentajeInteresDiario;
    private BigDecimal porcentajeTasaInteres;
    private ClaseInteresDTO claseInteres;
    private PeriodoInteresDTO periodoInteres;
    private Boolean estado;

    // --- Constructor
    public InteresDTO() {
        super();
    }

    public InteresDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaFinal() {
        return this.fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicial() {
        return this.fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public BigDecimal getPorcentajeInteresDiario() {
        return this.porcentajeInteresDiario;
    }

    public void setPorcentajeInteresDiario(BigDecimal porcentajeInteresDiario) {
        this.porcentajeInteresDiario = porcentajeInteresDiario;
    }

    public BigDecimal getPorcentajeTasaInteres() {
        return this.porcentajeTasaInteres;
    }

    public void setPorcentajeTasaInteres(BigDecimal porcentajeTasaInteres) {
        this.porcentajeTasaInteres = porcentajeTasaInteres;
    }

    public ClaseInteresDTO getClaseInteres() {
        return this.claseInteres;
    }

    public void setClaseInteres(ClaseInteresDTO claseInteres) {
        this.claseInteres = claseInteres;
    }

    public PeriodoInteresDTO getPeriodoInteres() {
        return this.periodoInteres;
    }

    public void setPeriodoInteres(PeriodoInteresDTO periodoInteres) {
        this.periodoInteres = periodoInteres;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}