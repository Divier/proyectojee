package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class TipoGarantiaDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer codigoTipoGarantia;
    private String nombreGarantia;

    // Constructors Declaration

    public TipoGarantiaDTO() {

    }

    // Start Methods Declaration

    public Integer getCodigoTipoGarantia() {
        return codigoTipoGarantia;
    }

    public void setCodigoTipoGarantia(Integer codigoTipoGarantia) {
        this.codigoTipoGarantia = codigoTipoGarantia;
    }

    public String getNombreGarantia() {
        return nombreGarantia;
    }

    public void setNombreGarantia(String nombreGarantia) {
        this.nombreGarantia = nombreGarantia;
    }

    // Finish the class
}