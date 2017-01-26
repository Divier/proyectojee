package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * DTO que representa la regla de negocio para llevar a cabo <b>consultarConceptoDctoAmnistiaIntereses(int)</b> definida en <b>IConceptoCartera</b>
 * 
 * <pre>
 * Retorna el concepto que corresponde a "DESCUENTO AMNISTIA SOBRE INTERESES" para el organismo de transito recibido.
 * </pre>
 * 
 * @author luis.forero
 * 
 */
public class ConceptoDctoAmnistiaInteresesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    @NotNull
    private Integer idConceptoCartera;

    public ConceptoDctoAmnistiaInteresesDTO() {
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getIdConceptoCartera() {
        return idConceptoCartera;
    }

    public void setIdConceptoCartera(Integer idConceptoCartera) {
        this.idConceptoCartera = idConceptoCartera;
    }

}
