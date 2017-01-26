package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;

/**
 * Registro nota cartera comparendo
 * 
 * @author oscar.leon
 * 
 */
public class ValidarIncosistenciaAgenteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long idFacturaAxis;
    private String fechaImposicion;
    private String horaImposicion;
    private String placaAgenteTransito;
    private String fechaValidacion;
    private String inconsistencia;

    public long getIdFacturaAxis() {
        return idFacturaAxis;
    }

    public void setIdFacturaAxis(long idFacturaAxis) {
        this.idFacturaAxis = idFacturaAxis;
    }

    public String getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(String fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public String getPlacaAgenteTransito() {
        return placaAgenteTransito;
    }

    public void setPlacaAgenteTransito(String placaAgenteTransito) {
        this.placaAgenteTransito = placaAgenteTransito;
    }

    public String getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(String fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public String getInconsistencia() {
        return inconsistencia;
    }

    public void setInconsistencia(String inconsistencia) {
        this.inconsistencia = inconsistencia;
    }

    public String getHoraImposicion() {
        return horaImposicion;
    }

    public void setHoraImposicion(String horaImposicion) {
        this.horaImposicion = horaImposicion;
    }

}
