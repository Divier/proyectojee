package co.com.datatools.c2.dto.comparendo;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.log.ILogueable;

public class RecibirComparendoLogDTO implements ILogueable {

    private static final long serialVersionUID = 1L;

    // Número de comparendo
    private String numeroComparendo;
    // Fecha y hora de procesamiento = Fecha y hora del sistema
    private Date fechaHoraProcesamiento;
    // Nombre de usuario
    private String nombreUsuario;
    // Estado de la transacción
    private String estadoTransaccion;
    // Origen validación
    private String origenValidacion;
    // Listado de errores
    private List<String> listadoErrores;

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Date getFechaHoraProcesamiento() {
        return fechaHoraProcesamiento;
    }

    public void setFechaHoraProcesamiento(Date fechaHoraProcesamiento) {
        this.fechaHoraProcesamiento = fechaHoraProcesamiento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setEstadoTransaccion(String estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }

    public List<String> getListadoErrores() {
        return listadoErrores;
    }

    public void setListadoErrores(List<String> listadoErrores) {
        this.listadoErrores = listadoErrores;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getOrigenValidacion() {
        return origenValidacion;
    }

    public void setOrigenValidacion(String origenValidacion) {
        this.origenValidacion = origenValidacion;
    }
}
