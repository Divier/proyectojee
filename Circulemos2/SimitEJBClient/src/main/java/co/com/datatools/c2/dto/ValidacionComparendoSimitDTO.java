package co.com.datatools.c2.dto;

import java.io.Serializable;

import co.com.datatools.c2.enumeraciones.EnumAccionComparendo;

public class ValidacionComparendoSimitDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8935764668140519520L;

    private long cicomparendo;
    private int codigoOrganismo;
    private EnumAccionComparendo origenEnvioSimit;

    public long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public EnumAccionComparendo getOrigenEnvioSimit() {
        return origenEnvioSimit;
    }

    public void setOrigenEnvioSimit(EnumAccionComparendo origenEnvioSimit) {
        this.origenEnvioSimit = origenEnvioSimit;
    }

}
