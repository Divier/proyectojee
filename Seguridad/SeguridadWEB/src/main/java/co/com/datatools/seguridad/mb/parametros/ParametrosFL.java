package co.com.datatools.seguridad.mb.parametros;

import java.util.List;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de consulta de parametros
 * 
 * @author claudia.rodriguez
 * 
 */
public class ParametrosFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ParametrosFL.class.getName());

    public static final String NOMBRE_BEAN = "parametrosFL";

    private String filtroNombre;
    private List<ParametroSeguridadDto> resultadoConsulta;
    private boolean consultaRealizada;

    public ParametrosFL() {
        logger.debug("ParametrosFL::ParametrosFL");
    }

    public String getFiltroNombre() {
        return filtroNombre;
    }

    public void setFiltroNombre(String filtroNombre) {
        this.filtroNombre = filtroNombre;
    }

    public List<ParametroSeguridadDto> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<ParametroSeguridadDto> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

}
