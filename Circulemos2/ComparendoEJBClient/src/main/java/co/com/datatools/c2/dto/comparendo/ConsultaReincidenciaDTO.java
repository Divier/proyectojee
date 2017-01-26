package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.enumeraciones.EnumTipoReincidencia;

/**
 * HU_CIR20_DAT_COM_066: Contiene los datos basicos para realizar la consulta de reincidencias para una persona.
 * 
 * @author rodrigo.cruz
 * 
 */
public class ConsultaReincidenciaDTO {

    private Integer idTipoDocumento;
    private String numeroDocumento;
    private int codigoOrganismo;
    private EnumTipoReincidencia tipoReincidencia;

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public EnumTipoReincidencia getTipoReincidencia() {
        return tipoReincidencia;
    }

    public void setTipoReincidencia(EnumTipoReincidencia tipoReincidencia) {
        this.tipoReincidencia = tipoReincidencia;
    }

}
