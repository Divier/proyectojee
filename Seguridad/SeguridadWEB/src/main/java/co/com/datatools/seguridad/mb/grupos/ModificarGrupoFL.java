package co.com.datatools.seguridad.mb.grupos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.mb.usuarios.UsuariosFL;
import co.com.datatools.seguridad.utilidades.EnumClaseGrupo;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de modiifcacion de Grupos
 * 
 * @author claudia.rodriguez
 * 
 */
public class ModificarGrupoFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(UsuariosFL.class.getName());

    public final static String NOMBRE_BEAN = "modificaGrupoFL";

    private boolean registraGrupo;
    private GrupoDto grupoModificar;
    private Map<String, Boolean> lEstados;
    private List<SelectItem> lClaseGrupo;

    public ModificarGrupoFL() {
        logger.debug("ModificaGrupoFL.constructor");
        cargarClasesGrupo();
    }

    public void cargarClasesGrupo() {
        lClaseGrupo = new ArrayList<>();
        lClaseGrupo.add(new SelectItem(EnumClaseGrupo.Roles.name(), EnumClaseGrupo.Roles.name()));
        lClaseGrupo.add(new SelectItem(EnumClaseGrupo.Usuarios.name(), EnumClaseGrupo.Usuarios.name()));
    }

    public Map<String, Boolean> getlEstados() {
        return lEstados;
    }

    public void setlEstados(Map<String, Boolean> lEstados) {
        this.lEstados = lEstados;
    }

    public boolean isRegistraGrupo() {
        return registraGrupo;
    }

    public void setRegistraGrupo(boolean registraGrupo) {
        this.registraGrupo = registraGrupo;
    }

    public GrupoDto getGrupoModificar() {
        return grupoModificar;
    }

    public void setGrupoModificar(GrupoDto grupoModificar) {
        this.grupoModificar = grupoModificar;
    }

    public List<SelectItem> getlClaseGrupo() {
        return lClaseGrupo;
    }

    public void setlClaseGrupo(List<SelectItem> lClaseGrupo) {
        this.lClaseGrupo = lClaseGrupo;
    }

}
