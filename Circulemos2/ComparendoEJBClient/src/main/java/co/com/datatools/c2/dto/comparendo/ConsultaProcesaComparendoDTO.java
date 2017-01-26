package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;

public class ConsultaProcesaComparendoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismo;
    private Boolean esPolca;
    private Date fechaImposicionFinal;
    private Date fechaImposicionInicial;
    private Date fechaRegistroFinal;
    private Date fechaRegistroInicial;
    private String numeroComparendo;
    private Boolean origenExterno;
    private Boolean reportado;

    public Boolean getEsPolca() {
        return esPolca;
    }

    public void setEsPolca(Boolean esPolca) {
        this.esPolca = esPolca;
    }

    public Date getFechaImposicionFinal() {
        return fechaImposicionFinal;
    }

    public void setFechaImposicionFinal(Date fechaImposicionFinal) {
        this.fechaImposicionFinal = fechaImposicionFinal;
    }

    public Date getFechaImposicionInicial() {
        return fechaImposicionInicial;
    }

    public void setFechaImposicionInicial(Date fechaImposicionInicial) {
        this.fechaImposicionInicial = fechaImposicionInicial;
    }

    public Date getFechaRegistroFinal() {
        return fechaRegistroFinal;
    }

    public void setFechaRegistroFinal(Date fechaRegistroFinal) {
        this.fechaRegistroFinal = fechaRegistroFinal;
    }

    public Date getFechaRegistroInicial() {
        return fechaRegistroInicial;
    }

    public void setFechaRegistroInicial(Date fechaRegistroInicial) {
        this.fechaRegistroInicial = fechaRegistroInicial;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Boolean getOrigenExterno() {
        return origenExterno;
    }

    public void setOrigenExterno(Boolean origenExterno) {
        this.origenExterno = origenExterno;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Boolean getReportado() {
        return reportado;
    }

    public void setReportado(Boolean reportado) {
        this.reportado = reportado;
    }
}