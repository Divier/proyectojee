package co.com.datatools.seguridad.dto.autenticacion;

import java.io.Serializable;

/**
 * Dto que encapsula los datos de un TipoDato
 * 
 * @author claudia.rodriguez
 * 
 */
public class TipoDatoDto implements Serializable {

    private static final long serialVersionUID = 7811490052784352097L;

    private Integer idTipoDato;
    private String nombreTipoData;

    public Integer getIdTipoDato() {
        return idTipoDato;
    }

    public void setIdTipoDato(Integer idTipoDato) {
        this.idTipoDato = idTipoDato;
    }

    public String getNombreTipoData() {
        return nombreTipoData;
    }

    public void setNombreTipoData(String nombreTipoData) {
        this.nombreTipoData = nombreTipoData;
    }

}
