package co.com.datatools.c2.dto;

/**
 * Contiene la información para generar un documento de notificacion presencial o concluyente
 * 
 * @author julio.pinzon
 */
public class GeneraDocumentoNotificacionPresencialDTO extends GeneraDocumentoDTO {

    private static final long serialVersionUID = 1L;

    /**
     * Indica si es polca o no
     */
    private boolean esPolca;

    /**
     * Id del tipo de comparendo
     */
    private Integer idTipoComparendo;

    public boolean isEsPolca() {
        return esPolca;
    }

    public void setEsPolca(boolean esPolca) {
        this.esPolca = esPolca;
    }

    public Integer getIdTipoComparendo() {
        return idTipoComparendo;
    }

    public void setIdTipoComparendo(Integer idTipoComparendo) {
        this.idTipoComparendo = idTipoComparendo;
    }

}