package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jul 14 11:49:31 COT 2016
 */
public class AnulacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String correoSupervisor;
    private Date fechaCorreo;
    private String justificacionCorreo;
    private String primerNombreFuncionario;
    private String segundoNombreFuncionario;
    private String primerApellidoFuncionario;
    private String segundoApellidoFuncionario;
    private Date fechaAnulacion;
    private ProcesoDTO proceso;

    // --- Constructor
    public AnulacionDTO() {
    }

    public AnulacionDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreoSupervisor() {
        return this.correoSupervisor;
    }

    public void setCorreoSupervisor(String correoSupervisor) {
        this.correoSupervisor = correoSupervisor;
    }

    public Date getFechaCorreo() {
        return this.fechaCorreo;
    }

    public void setFechaCorreo(Date fechaCorreo) {
        this.fechaCorreo = fechaCorreo;
    }

    public String getJustificacionCorreo() {
        return this.justificacionCorreo;
    }

    public void setJustificacionCorreo(String justificacionCorreo) {
        this.justificacionCorreo = justificacionCorreo;
    }

    public String getPrimerNombreFuncionario() {
        return this.primerNombreFuncionario;
    }

    public void setPrimerNombreFuncionario(String primerNombreFuncionario) {
        this.primerNombreFuncionario = primerNombreFuncionario;
    }

    public String getSegundoNombreFuncionario() {
        return this.segundoNombreFuncionario;
    }

    public void setSegundoNombreFuncionario(String segundoNombreFuncionario) {
        this.segundoNombreFuncionario = segundoNombreFuncionario;
    }

    public String getPrimerApellidoFuncionario() {
        return this.primerApellidoFuncionario;
    }

    public void setPrimerApellidoFuncionario(String primerApellidoFuncionario) {
        this.primerApellidoFuncionario = primerApellidoFuncionario;
    }

    public String getSegundoApellidoFuncionario() {
        return this.segundoApellidoFuncionario;
    }

    public void setSegundoApellidoFuncionario(String segundoApellidoFuncionario) {
        this.segundoApellidoFuncionario = segundoApellidoFuncionario;
    }

    public Date getFechaAnulacion() {
        return this.fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public ProcesoDTO getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoDTO proceso) {
        this.proceso = proceso;
    }

}
