package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

/**
 * Contiene los filtros necesarios para consultar comparendos de un organismo de tránsito por tipo de notificación, número del comparendo y datos del
 * infractor.
 * 
 * @author julio.pinzon
 */
public class ConsultaNotificacionComparendoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Código del organismo al cual pertenecen los comparendos a consultar.
     */
    private int codigoOrganismo;

    /**
     * Contiene el número del comparendo a consultar.
     */
    private String numeroComparendo;

    /**
     * Número del documento del infractor al cual consultar. Es obligatorio si el tipo de documento no es null.
     */
    private String numeroDocumentoInfractor;

    /**
     * Tipo de documento del infractor al cual consultar. Es obligatorio si el número de documento no es null.
     */
    private Integer tipoDocumentoInfractor;

    /**
     * Indica si es polca
     */
    private Boolean esPolca;

    public ConsultaNotificacionComparendoDTO() {
        super();
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getNumeroDocumentoInfractor() {
        return numeroDocumentoInfractor;
    }

    public void setNumeroDocumentoInfractor(String numeroDocumentoInfractor) {
        this.numeroDocumentoInfractor = numeroDocumentoInfractor;
    }

    public Integer getTipoDocumentoInfractor() {
        return tipoDocumentoInfractor;
    }

    public void setTipoDocumentoInfractor(Integer tipoDocumentoInfractor) {
        this.tipoDocumentoInfractor = tipoDocumentoInfractor;
    }

    public Boolean getEsPolca() {
        return esPolca;
    }

    public void setEsPolca(Boolean esPolca) {
        this.esPolca = esPolca;
    }

}