package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Valores para consultar los seguimientos de coactivos
 * 
 * @author divier.casas
 * 
 */
public class ConsultaSeguimientoCoactivoDTO implements EntidadDtoC2 {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Date fechaIniProceso;
    private Date fechaFinProceso;
    private String numeroProceso;
    private Integer tipoDocDeudor;
    private String numeroDocDeudor;

    public Date getFechaIniProceso() {
        return fechaIniProceso;
    }

    public void setFechaIniProceso(Date fechaIniProceso) {
        this.fechaIniProceso = fechaIniProceso;
    }

    public Date getFechaFinProceso() {
        return fechaFinProceso;
    }

    public void setFechaFinProceso(Date fechaFinProceso) {
        this.fechaFinProceso = fechaFinProceso;
    }

    public String getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public Integer getTipoDocDeudor() {
        return tipoDocDeudor;
    }

    public void setTipoDocDeudor(Integer tipoDocDeudor) {
        this.tipoDocDeudor = tipoDocDeudor;
    }

    public String getNumeroDocDeudor() {
        return numeroDocDeudor;
    }

    public void setNumeroDocDeudor(String numeroDocDeudor) {
        this.numeroDocDeudor = numeroDocDeudor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fechaFinProceso == null) ? 0 : fechaFinProceso.hashCode());
        result = prime * result + ((fechaIniProceso == null) ? 0 : fechaIniProceso.hashCode());
        result = prime * result + ((numeroDocDeudor == null) ? 0 : numeroDocDeudor.hashCode());
        result = prime * result + ((numeroProceso == null) ? 0 : numeroProceso.hashCode());
        result = prime * result + ((tipoDocDeudor == null) ? 0 : tipoDocDeudor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConsultaSeguimientoCoactivoDTO other = (ConsultaSeguimientoCoactivoDTO) obj;
        if (fechaFinProceso == null) {
            if (other.fechaFinProceso != null)
                return false;
        } else if (!fechaFinProceso.equals(other.fechaFinProceso))
            return false;
        if (fechaIniProceso == null) {
            if (other.fechaIniProceso != null)
                return false;
        } else if (!fechaIniProceso.equals(other.fechaIniProceso))
            return false;
        if (numeroDocDeudor == null) {
            if (other.numeroDocDeudor != null)
                return false;
        } else if (!numeroDocDeudor.equals(other.numeroDocDeudor))
            return false;
        if (numeroProceso == null) {
            if (other.numeroProceso != null)
                return false;
        } else if (!numeroProceso.equals(other.numeroProceso))
            return false;
        if (tipoDocDeudor == null) {
            if (other.tipoDocDeudor != null)
                return false;
        } else if (!tipoDocDeudor.equals(other.tipoDocDeudor))
            return false;
        return true;
    }
}