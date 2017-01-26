package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.enumeraciones.EnumRespuestaResolucionSancion;

/**
 * La respuesta de la generaci�n de resoluciones de sanci�n para comparendos incluye una lista de comparendos con un c�digo de respuesta y fecha y
 * hora de procesamiento.
 * 
 * 
 * @author julio.pinzon
 * 
 */
public class RespuestaResolucionComparendoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    /**
     * Codigo de respuesta de la generaci�n de la resoluci�n de sanci�n para comparendos:
     */
    private EnumRespuestaResolucionSancion respuesta;

    /**
     * Fecha y hora del procesamiento de las resoluciones de sanci�n
     */
    private Date fechaHoraProcesamiento;

    /**
     * Lista de comparendos a los cuales se les gener� resoluci�n exitosamente
     */
    private List<ComparendoDTO> comparendosResolucion;

    // --- Constructor
    public RespuestaResolucionComparendoDTO() {
    }

    public EnumRespuestaResolucionSancion getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(EnumRespuestaResolucionSancion respuesta) {
        this.respuesta = respuesta;
    }

    public Date getFechaHoraProcesamiento() {
        return fechaHoraProcesamiento;
    }

    public void setFechaHoraProcesamiento(Date fechaHoraProcesamiento) {
        this.fechaHoraProcesamiento = fechaHoraProcesamiento;
    }

    public List<ComparendoDTO> getComparendosResolucion() {
        return comparendosResolucion;
    }

    public void setComparendosResolucion(List<ComparendoDTO> comparendosResolucion) {
        this.comparendosResolucion = comparendosResolucion;
    }

}