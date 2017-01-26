package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author robert.bautista
 */
public class CodTipoIdentEmpJurPaisDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Contiene el codigo pais del cual se consulta el id del tipo de identificacion para persona Juridica
     */
    private String pais;

    /**
     * Contiene el codigo del tipo de identificacion para persona Juridica
     */
    @NotNull
    private String codigoIdentificacionEmpresaJuridica;

    public CodTipoIdentEmpJurPaisDTO() {
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoIdentificacionEmpresaJuridica() {
        return this.codigoIdentificacionEmpresaJuridica;
    }

    public void setCodigoIdentificacionEmpresaJuridica(String codigoIdentificacionEmpresaJuridica) {
        this.codigoIdentificacionEmpresaJuridica = codigoIdentificacionEmpresaJuridica;
    }

    @Override
    public String toString() {
        return "Pais: " + this.pais + " -- Código Documento Empresa Jurídica "
                + this.codigoIdentificacionEmpresaJuridica;
    }
}
