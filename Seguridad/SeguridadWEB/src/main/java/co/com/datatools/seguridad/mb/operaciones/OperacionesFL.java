package co.com.datatools.seguridad.mb.operaciones;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de consulta de Operaciones
 * 
 * @author claudia.rodriguez
 * 
 */
public class OperacionesFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(OperacionesFL.class.getName());

    public static final String NOMBRE_BEAN = "operacionesFL";

    private String nombre;
    private List<OperacionDto> resultadoConsulta;
    private boolean consultaRealizada;
    private OperacionDto operacionSeleccionada;

    public OperacionesFL() {
        logger.debug("OperacionesFL::OperacionesFL");
        consultaRealizada = false;
        resultadoConsulta = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<OperacionDto> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<OperacionDto> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    public OperacionDto getOperacionSeleccionada() {
        return operacionSeleccionada;
    }

    public void setOperacionSeleccionada(OperacionDto operacionSeleccionada) {
        this.operacionSeleccionada = operacionSeleccionada;
    }

}
