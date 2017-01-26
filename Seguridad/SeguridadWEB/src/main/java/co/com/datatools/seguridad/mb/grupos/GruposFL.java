package co.com.datatools.seguridad.mb.grupos;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de consulta de Grupos
 * 
 * @author claudia.rodriguez
 * 
 */
public class GruposFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(GruposFL.class.getName());

    public static final String NOMBRE_BEAN = "gruposFL";

    private String nombre;
    private String estadoSeleccionado;
    private boolean consultaRealizada;
    private GrupoDto grupo;
    private List<GrupoDto> resultadoConsulta;

    public GruposFL() {
        logger.debug("GruposFL::GruposFL");
        consultaRealizada = false;
        resultadoConsulta = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    public GrupoDto getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoDto grupo) {
        this.grupo = grupo;
    }

    public List<GrupoDto> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<GrupoDto> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

}
