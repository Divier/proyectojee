package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

public class FiltroConsultaInconsistenciasDTO implements Serializable {

    private int OrganismoTransito;
    private String numeroRecaudo;
    private String obligacionPagada;
    private Date fechaInicial;
    private Date fechaFinal;

    public int getOrganismoTransito() {
        return OrganismoTransito;
    }

    public void setOrganismoTransito(int organismoTransito) {
        OrganismoTransito = organismoTransito;
    }

    public String getNumeroRecaudo() {
        return numeroRecaudo;
    }

    public void setNumeroRecaudo(String numeroRecaudo) {
        this.numeroRecaudo = numeroRecaudo;
    }

    public String getObligacionPagada() {
        return obligacionPagada;
    }

    public void setObligacionPagada(String obligacionPagada) {
        this.obligacionPagada = obligacionPagada;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

}
