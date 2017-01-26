package co.com.datatools.c2.dto.formularios;

import java.io.Serializable;
import java.util.Date;

public class ConsultaRangoFormularioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idRango;
    private Integer codigoOrganismo;
    private Integer idTipoFormulario;
    private String numeroInicial;
    private String numeroFinal;
    private Date fechaAutorizacionInicial;
    private Date fechaAutorizacionFinal;

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public String getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(String numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public String getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public Date getFechaAutorizacionInicial() {
        return fechaAutorizacionInicial;
    }

    public void setFechaAutorizacionInicial(Date fechaAutorizacionInicial) {
        this.fechaAutorizacionInicial = fechaAutorizacionInicial;
    }

    public Date getFechaAutorizacionFinal() {
        return fechaAutorizacionFinal;
    }

    public void setFechaAutorizacionFinal(Date fechaAutorizacionFinal) {
        this.fechaAutorizacionFinal = fechaAutorizacionFinal;
    }

    public Long getIdRango() {
        return idRango;
    }

    public void setIdRango(Long idRango) {
        this.idRango = idRango;
    }

}
