package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class MotivoRectificarPagoDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer codigoMotivoRectificarPago;
    private String descripcionMotivo;
    private String nombreMotivoRectificar;

    // Constructors Declaration

    public MotivoRectificarPagoDTO() {

    }

    // Start Methods Declaration

    public Integer getCodigoMotivoRectificarPago() {
        return codigoMotivoRectificarPago;
    }

    public void setCodigoMotivoRectificarPago(Integer codigoMotivoRectificarPago) {
        this.codigoMotivoRectificarPago = codigoMotivoRectificarPago;
    }

    public String getDescripcionMotivo() {
        return descripcionMotivo;
    }

    public void setDescripcionMotivo(String descripcionMotivo) {
        this.descripcionMotivo = descripcionMotivo;
    }

    public String getNombreMotivoRectificar() {
        return nombreMotivoRectificar;
    }

    public void setNombreMotivoRectificar(String nombreMotivoRectificar) {
        this.nombreMotivoRectificar = nombreMotivoRectificar;
    }

    // Finish the class
}