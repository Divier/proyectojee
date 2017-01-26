package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;

public class ConsultaCantidadComparendosNoNotificadosDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 925437853502824681L;
    private int codigoOrganismo;
    private Date fechaFinalImposicion;
    private Date fechaFinalRegistro;
    private Date fechaInicialImposicion;
    private Date fechaInicialRegistro;
    private String numeroComparendo;
    private boolean esPolca;

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaFinalImposicion() {
        return fechaFinalImposicion;
    }

    public void setFechaFinalImposicion(Date fechaFinalImposicion) {
        this.fechaFinalImposicion = fechaFinalImposicion;
    }

    public Date getFechaFinalRegistro() {
        return fechaFinalRegistro;
    }

    public void setFechaFinalRegistro(Date fechaFinalRegistro) {
        this.fechaFinalRegistro = fechaFinalRegistro;
    }

    public Date getFechaInicialImposicion() {
        return fechaInicialImposicion;
    }

    public void setFechaInicialImposicion(Date fechaInicialImposicion) {
        this.fechaInicialImposicion = fechaInicialImposicion;
    }

    public Date getFechaInicialRegistro() {
        return fechaInicialRegistro;
    }

    public void setFechaInicialRegistro(Date fechaInicialRegistro) {
        this.fechaInicialRegistro = fechaInicialRegistro;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public boolean isEsPolca() {
        return esPolca;
    }

    public void setEsPolca(boolean esPolca) {
        this.esPolca = esPolca;
    }

}
