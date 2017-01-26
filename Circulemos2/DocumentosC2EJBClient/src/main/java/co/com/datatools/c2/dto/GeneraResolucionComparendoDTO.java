package co.com.datatools.c2.dto;

/**
 * Contiene los datos adicionales del documento para generar una resolución.
 * 
 * @author julio.pinzon
 */
public class GeneraResolucionComparendoDTO extends GeneraDocumentoDTO {

    private static final long serialVersionUID = 1L;
    /**
     * Número del comparendo
     */
    private String numeroComparendo;

    /**
     * Numero de resolucion
     */
    private String numeroConsecutivo;

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

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getNumeroConsecutivo() {
        return numeroConsecutivo;
    }

    public void setNumeroConsecutivo(String numeroConsecutivo) {
        this.numeroConsecutivo = numeroConsecutivo;
    }

}
