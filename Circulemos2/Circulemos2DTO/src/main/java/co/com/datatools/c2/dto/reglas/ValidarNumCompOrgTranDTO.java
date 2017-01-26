package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

/**
 * Dto para regla q consulta la expresion regular de un organismo de transito para validar el numero de comparendo
 * 
 * @author felipe.martinez
 */
public class ValidarNumCompOrgTranDTO implements Serializable {

    private static final long serialVersionUID = 2538428259180652064L;

    /**
     * Parametro Entrada
     */
    private Integer codigoOrganismo;

    /**
     * Elemento de salida de drools
     */
    private String regExp;

    public ValidarNumCompOrgTranDTO() {
        super();
    }

    public ValidarNumCompOrgTranDTO(Integer codigoOrganismo) {
        this();
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getRegExp() {
        return regExp;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }
}
