package co.com.datatools.seguridad.mb.grupos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.mb.usuarios.UsuariosFL;
import co.com.datatools.seguridad.utilidades.EnumClaseGrupo;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de creacion de Grupos
 * 
 * @author claudia.rodriguez
 * 
 */
public class CrearGrupoFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(UsuariosFL.class.getName());

    public final static String NOMBRE_BEAN = "creaGrupoFL";

    private GrupoDto grupoCrear;
    private List<SelectItem> lClaseGrupo;
    private String claseSeleccionada;

    public CrearGrupoFL() {
        logger.debug("CreaGrupoFL.constructor");
        grupoCrear = new GrupoDto();
        cargarClasesGrupo();
    }

    public void cargarClasesGrupo() {
        lClaseGrupo = new ArrayList<>();
        lClaseGrupo.add(new SelectItem(EnumClaseGrupo.Roles.name(), EnumClaseGrupo.Roles.name()));
        lClaseGrupo.add(new SelectItem(EnumClaseGrupo.Usuarios.name(), EnumClaseGrupo.Usuarios.name()));
    }

    public GrupoDto getGrupoCrear() {
        return grupoCrear;
    }

    public void setGrupoCrear(GrupoDto grupoCrear) {
        this.grupoCrear = grupoCrear;
    }

    public List<SelectItem> getlClaseGrupo() {
        return lClaseGrupo;
    }

    public void setlClaseGrupo(List<SelectItem> lClaseGrupo) {
        this.lClaseGrupo = lClaseGrupo;
    }

    public String getClaseSeleccionada() {
        return claseSeleccionada;
    }

    public void setClaseSeleccionada(String claseSeleccionada) {
        this.claseSeleccionada = claseSeleccionada;
    }

}
