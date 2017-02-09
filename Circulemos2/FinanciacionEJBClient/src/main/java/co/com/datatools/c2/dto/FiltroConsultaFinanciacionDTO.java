package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Se encarga de llevar los filtros de la consulta de procesos de financiacion
 * 
 * @author giovanni.velandia
 * 
 */
public class FiltroConsultaFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Integer anoFinanciacion;
    private String numeroFinanciacion;
    private Integer idTipoIdentificacion;
    private String numeroIdentificacion;

    public String getNumeroFinanciacion() {
        return numeroFinanciacion;
    }

    public void setNumeroFinanciacion(String numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
    }

    public Integer getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Integer getAnoFinanciacion() {
        return anoFinanciacion;
    }

    public void setAnoFinanciacion(Integer anoFinanciacion) {
        this.anoFinanciacion = anoFinanciacion;
    }
}
