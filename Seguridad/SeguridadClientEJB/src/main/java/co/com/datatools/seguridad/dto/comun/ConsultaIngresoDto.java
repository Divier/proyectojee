package co.com.datatools.seguridad.dto.comun;

import java.util.Date;

import co.com.datatools.seguridad.dto.autenticacion.IngresoDto;

/**
 * Clase que encapsula los filtros para la consulta de Ingresos de los usuarios
 * 
 * @author claudia.rodriguez
 * 
 */
public class ConsultaIngresoDto extends IngresoDto {

    private static final long serialVersionUID = 1L;
    private String recurso;
    private Date fechaIngresoInicial;
    private Date fechaIngresoFinal;

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public Date getFechaIngresoInicial() {
        return fechaIngresoInicial;
    }

    public void setFechaIngresoInicial(Date fechaIngresoInicial) {
        this.fechaIngresoInicial = fechaIngresoInicial;
    }

    public Date getFechaIngresoFinal() {
        return fechaIngresoFinal;
    }

    public void setFechaIngresoFinal(Date fechaIngresoFinal) {
        this.fechaIngresoFinal = fechaIngresoFinal;
    }

}
