package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;

public class FuncionarioDocumentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fechaFinalFuncionario;
    private Date fechaFinalCargo;
    private Date fechaInicialFuncionario;
    private Date fechaInicialCargo;
    private byte[] firma;
    private int idCargo;
    private String nombre;
    private TipoIdentificacionPersonaDTO tipoIdentificacion;
    private String numeroIdentificacion;

    public Date getFechaFinalFuncionario() {
        return fechaFinalFuncionario;
    }

    public void setFechaFinalFuncionario(Date fechaFinalFuncionario) {
        this.fechaFinalFuncionario = fechaFinalFuncionario;
    }

    public Date getFechaFinalCargo() {
        return fechaFinalCargo;
    }

    public void setFechaFinalCargo(Date fechaFinalCargo) {
        this.fechaFinalCargo = fechaFinalCargo;
    }

    public Date getFechaInicialFuncionario() {
        return fechaInicialFuncionario;
    }

    public void setFechaInicialFuncionario(Date fechaInicialFuncionario) {
        this.fechaInicialFuncionario = fechaInicialFuncionario;
    }

    public Date getFechaInicialCargo() {
        return fechaInicialCargo;
    }

    public void setFechaInicialCargo(Date fechaInicialCargo) {
        this.fechaInicialCargo = fechaInicialCargo;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoIdentificacionPersonaDTO getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacionPersonaDTO tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

}