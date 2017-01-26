package co.com.datatools.seguridad.mb.grupos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRGrupo;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.seguridad.util.SeguridadErrorHandler;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean para los flujos relacionados con el CRUD de los Grupos, obtiene y asigna valores de los Dtos: GruposFl, CreaGrupoFL y ModificaGrupoFl
 * 
 * @author claudia.rodriguez
 */
@ManagedBean
@SessionScoped
public class GruposMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(GruposMB.class.getName());

    private static final String NOMBRE_BUNDLE_GRUPO = "mensajesGrupo";

    private List<SelectItem> lEstadosGrupo;

    @EJB
    private IRGrupo grupoEjb;

    public GruposMB() {
        logger.debug("GruposMB::GruposMB");
        cargarEstados();
    }

    /**
     * Consulta los grupos de acuerdo a los filtros ingresados por el usuario y asigna el resultado al Dto del flujo de consulta de Grupos. Si no
     * encuentra resultados se muestra un mensaje
     */
    public void consultarGrupos() {
        logger.debug("GruposMB::consultarGrupos");
        GruposFL grupoFl = findFlowObject(GruposFL.class, GruposFL.NOMBRE_BEAN);
        grupoFl.setConsultaRealizada(false);
        // Verificar los filtros ingresados
        GrupoDto grupoFiltros = new GrupoDto();
        grupoFiltros.setNombre(grupoFl.getNombre());
        if (grupoFl.getEstadoSeleccionado() != null) {
            grupoFiltros.setActivo(new Boolean(grupoFl.getEstadoSeleccionado()));
        }
        List<GrupoDto> resultadoConsulta = new ArrayList<>();
        resultadoConsulta = grupoEjb.consultarGrupos(grupoFiltros);
        grupoFl.setResultadoConsulta(resultadoConsulta);
        grupoFl.setConsultaRealizada(true);
    }

    /**
     * Elimina el grupo seleccionado en la interfaz y muestra un mensaje de eliminacion exitosa o de error en caso de que no se pueda eliminar por
     * integridad referencial con Usuarios o Roles
     */
    public void eliminarGrupo() {
        logger.debug("GruposMB::eliminarGrupo");
        GrupoDto grupoSeleccionado = ((ModificarGrupoFL) findFlowObject(ModificarGrupoFL.class,
                ModificarGrupoFL.NOMBRE_BEAN)).getGrupoModificar();
        try {
            grupoEjb.eliminarGrupo(grupoSeleccionado.getIdGrupo());
            addInfoMessage(NOMBRE_BUNDLE_GRUPO, "msg_grupo_eliminado");
            final GruposFL grupoFl = (GruposFL) findFlowObject(GruposFL.class, GruposFL.NOMBRE_BEAN);
            grupoFl.getResultadoConsulta().remove(grupoSeleccionado);
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }
    }

    /**
     * Llena la lista de Estados del grupo con los valores posibles para Activo/Inactivo
     */
    public void cargarEstados() {
        logger.debug("GruposMB::cargarEstados");
        lEstadosGrupo = new ArrayList<>();
        lEstadosGrupo.add(new SelectItem(Boolean.TRUE, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL)
                .getString("label_activo")));
        lEstadosGrupo.add(new SelectItem(Boolean.FALSE, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL)
                .getString("label_inactivo")));
    }

    /**
     * Crea un grupo con los datos ingresados y muestra el mensaje de creacion exitosa o de error en caso de que no se pueda crear porque exista otro
     * grupo con el mismo nombre ingresado
     */
    public void crearGrupo() {
        logger.debug("GruposMB::crearGrupo");
        final CrearGrupoFL creaGrupoFl = findFlowObject(CrearGrupoFL.class, CrearGrupoFL.NOMBRE_BEAN);
        GrupoDetalleDto grupoDetalle = new GrupoDetalleDto();
        grupoDetalle.setNombre(creaGrupoFl.getGrupoCrear().getNombre());
        grupoDetalle.setActivo(true);
        grupoDetalle.setDescripcion(creaGrupoFl.getGrupoCrear().getDescripcion());
        grupoDetalle.setClase(creaGrupoFl.getClaseSeleccionada());
        try {
            grupoEjb.registrarGrupo(grupoDetalle);
            addInfoMessage(NOMBRE_BUNDLE_GRUPO, "msg_grupo_guardado");
            creaGrupoFl.setGrupoCrear(new GrupoDto());
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }

    }

    /**
     * Modifica el grupo seleccionado en la interfaz y muestra el mensaje de modificacion exitosa o de error en caso de que no se pueda modificar
     * porque exista otro grupo con el mismo nombre ingresado
     */
    public void modificarGrupo() {
        logger.debug("GruposMB::modificarGrupo");
        final ModificarGrupoFL modificaGrupoFl = findFlowObject(ModificarGrupoFL.class, ModificarGrupoFL.NOMBRE_BEAN);
        GrupoDto grupoModificado = new GrupoDto();
        grupoModificado = modificaGrupoFl.getGrupoModificar();
        GrupoDetalleDto grupoDetalle = new GrupoDetalleDto();
        grupoDetalle.setIdGrupo(grupoModificado.getIdGrupo());
        grupoDetalle.setNombre(grupoModificado.getNombre());
        grupoDetalle.setDescripcion(grupoModificado.getDescripcion());
        grupoDetalle.setActivo(grupoModificado.getActivo());
        grupoDetalle.setClase(grupoModificado.getClase());
        try {
            grupoEjb.actualizarGrupo(grupoDetalle);
            addInfoMessage(NOMBRE_BUNDLE_GRUPO, "msg_grupo_guardado");

        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }

    }

    public List<SelectItem> getlEstadosGrupo() {
        return lEstadosGrupo;
    }

    public void setlEstadosGrupo(List<SelectItem> lEstadosGrupo) {
        this.lEstadosGrupo = lEstadosGrupo;
    }

}
