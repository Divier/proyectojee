package co.com.datatools.c2.dto.configuracion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * DTO que se encarga de representar la configuracion para el codigo 011 - Asocia d�as para ingresar comparendo
 * 
 * @author giovanni.velandia 2015-10-07
 * 
 */
public class AsociaDiasIngresoComparendoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Entrada
     */
    // <codigo>medioImposicion</codigo>
    // <descripcion>Tipo de comparendo usado, corresponde al medio de imposici�n.</descripcion>
    private List<String> medioImposicion;

    // <descripcion>C�digo del organismo de tr�nsito</descripcion>
    private List<String> codigoOrganismo;

    /*
     * Salida
     */
    // <codigo>diasLimite</codigo>
    // <descripcion>Cantidad de d�as l�mite para ingresar el comparendo a partir de la fecha de imposici�n.</descripcion>
    private BigDecimal diasLimite;

    public List<String> getMedioImposicion() {
        return medioImposicion;
    }

    public void setMedioImposicion(List<String> medioImposicion) {
        this.medioImposicion = medioImposicion;
    }

    public BigDecimal getDiasLimite() {
        return diasLimite;
    }

    public void setDiasLimite(BigDecimal diasLimite) {
        this.diasLimite = diasLimite;
    }

    public List<String> getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(List<String> codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

}
