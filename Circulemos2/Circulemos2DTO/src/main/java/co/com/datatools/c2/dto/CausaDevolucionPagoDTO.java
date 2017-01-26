package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:43 COT 2014
 */
public class CausaDevolucionPagoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String descripcion;
    private String detalleAdicionalDevolucion;
    private Boolean prenotificacion;
    private Boolean transaccionDebito;

    // --- Constructor
    public CausaDevolucionPagoDTO() {
    }

    public CausaDevolucionPagoDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalleAdicionalDevolucion() {
        return this.detalleAdicionalDevolucion;
    }

    public void setDetalleAdicionalDevolucion(String detalleAdicionalDevolucion) {
        this.detalleAdicionalDevolucion = detalleAdicionalDevolucion;
    }

    public Boolean getPrenotificacion() {
        return this.prenotificacion;
    }

    public void setPrenotificacion(Boolean prenotificacion) {
        this.prenotificacion = prenotificacion;
    }

    public Boolean getTransaccionDebito() {
        return this.transaccionDebito;
    }

    public void setTransaccionDebito(Boolean transaccionDebito) {
        this.transaccionDebito = transaccionDebito;
    }

}