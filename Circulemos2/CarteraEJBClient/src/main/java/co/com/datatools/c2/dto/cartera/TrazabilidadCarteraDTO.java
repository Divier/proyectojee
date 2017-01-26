package co.com.datatools.c2.dto.cartera;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:24:24 COT 2015
 */
public class TrazabilidadCarteraDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaRegistro;
    private String loginUsuario;
    private EstadoObligacionDTO estadoObligacion;
    private CarteraDTO cartera;
    private ActividadCarteraDTO actividadCartera;

    // --- Constructor
    public TrazabilidadCarteraDTO() {
    }

    public TrazabilidadCarteraDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public EstadoObligacionDTO getEstadoObligacion() {
        return this.estadoObligacion;
    }

    public void setEstadoObligacion(EstadoObligacionDTO estadoObligacion) {
        this.estadoObligacion = estadoObligacion;
    }

    public CarteraDTO getCartera() {
        return this.cartera;
    }

    public void setCartera(CarteraDTO cartera) {
        this.cartera = cartera;
    }

    public ActividadCarteraDTO getActividadCartera() {
        return this.actividadCartera;
    }

    public void setActividadCartera(ActividadCarteraDTO actividadCartera) {
        this.actividadCartera = actividadCartera;
    }

}
