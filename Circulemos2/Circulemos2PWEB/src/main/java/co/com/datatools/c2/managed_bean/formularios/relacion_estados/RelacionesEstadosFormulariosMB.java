package co.com.datatools.c2.managed_bean.formularios.relacion_estados;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.RelacionEstadosDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * Managed bean que permite llevar a cabo la administracion de las relaciones entre los estados posibles de un formulario. <b>CU_CIR20_DAT_ADM_050</b>
 * 
 * @author luis.forero (2015-01-15)
 * 
 */
@ManagedBean
@SessionScoped
public class RelacionesEstadosFormulariosMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Class<RelacionEstadoFormularioHolderFL> OBJ_RELACION_ESTADO_FORMULARIO_HOLDER_FL = RelacionEstadoFormularioHolderFL.class;
    private static final String RELACION_ESTADO_FORMULARIO_HOLDER_FL = "relacionEstadoFormularioHolderFL";

    private static final Logger logger = Logger.getLogger(RelacionesEstadosFormulariosMB.class.getName());

    @EJB
    private IRAdministracionFormularios administracionFormulariosEJB;

    /**
     * Permite llevar a cabo la accion de consultar las relaciones de esatdos de formularios.
     * 
     * @author luis.forero(2015-01-015)
     */
    public void consultar() {
        logger.debug(RelacionesEstadosFormulariosMB.class.getName().concat("::consultar()"));
        final RelacionEstadoFormularioHolderFL relacionEstadoFormularioHolderFL = findFlowObject(
                OBJ_RELACION_ESTADO_FORMULARIO_HOLDER_FL, RELACION_ESTADO_FORMULARIO_HOLDER_FL);
        RelacionEstadosDTO relacionEstadosDTO = new RelacionEstadosDTO();

        if (relacionEstadoFormularioHolderFL.getfIdTipoFormulario() != null) {
            relacionEstadosDTO.setTipoFormulario(new TipoFormularioDTO(relacionEstadoFormularioHolderFL
                    .getfIdTipoFormulario()));
        }
        if (relacionEstadoFormularioHolderFL.getfIdEstadoActual() != null) {
            relacionEstadosDTO.setEstadoFormularioActual(new EstadoFormularioDTO(relacionEstadoFormularioHolderFL
                    .getfIdEstadoActual()));
        }
        if (relacionEstadoFormularioHolderFL.getfIdEstadoSiguiente() != null) {
            relacionEstadosDTO.setEstadoFormularioSiguiente(new EstadoFormularioDTO(relacionEstadoFormularioHolderFL
                    .getfIdEstadoSiguiente()));
        }

        OrganismoTransitoDTO organismoTransitoDTO = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);

        if (organismoTransitoDTO != null) {
            relacionEstadosDTO.setOrganismoTranisto(organismoTransitoDTO);
        }

        relacionEstadoFormularioHolderFL.getLstRelacionesEstados().clear();

        List<RelacionEstadosDTO> consultarRelacionesEstados = administracionFormulariosEJB
                .consultarRelacionesEstados(relacionEstadosDTO);
        if (consultarRelacionesEstados.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            relacionEstadoFormularioHolderFL.getLstRelacionesEstados().addAll(consultarRelacionesEstados);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(consultarRelacionesEstados.size());
        }
    }

    /**
     * Permite llevar a cabo el registro de una relacion entre estados de formularios.
     * 
     * @return true si el registro fue exitoso, false de lo contrario
     * @author luis.forero (2015-01-16)
     */
    public boolean registrarRelacionEstados() {
        logger.debug(RelacionesEstadosFormulariosMB.class.getName().concat("::registrarRelacionEstados()"));
        final RelacionEstadoFormularioHolderFL relacionEstadoFormularioHolderFL = findFlowObject(
                OBJ_RELACION_ESTADO_FORMULARIO_HOLDER_FL, RELACION_ESTADO_FORMULARIO_HOLDER_FL);
        RelacionEstadosDTO relacionEstados = relacionEstadoFormularioHolderFL.getRelacionEstados();

        OrganismoTransitoDTO organismoTransitoDTO = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
        if (organismoTransitoDTO != null) {
            relacionEstados.setOrganismoTranisto(organismoTransitoDTO);
        }

        relacionEstadoFormularioHolderFL.setRelacionEstados(relacionEstados);
        relacionEstados = relacionEstadoFormularioHolderFL.getRelacionEstados();

        try {
            administracionFormulariosEJB.registrarRelacionEstados(relacionEstados);
            CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        return true;
    }

    /**
     * Permite llevar a cabo la actualizacion de una relacion entre estados de formularios.
     * 
     * 
     * @author luis.forero (2015-01-16)
     */
    public void actualizarRelacionEstados() {
        logger.debug(RelacionesEstadosFormulariosMB.class.getName().concat("::actualizarRelacionEstados()"));
        final RelacionEstadoFormularioHolderFL relacionEstadoFormularioHolderFL = findFlowObject(
                OBJ_RELACION_ESTADO_FORMULARIO_HOLDER_FL, RELACION_ESTADO_FORMULARIO_HOLDER_FL);
        RelacionEstadosDTO relacionEstados = relacionEstadoFormularioHolderFL.getRelacionEstados();

        OrganismoTransitoDTO organismoTransitoDTO = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
        if (organismoTransitoDTO != null) {
            relacionEstados.setOrganismoTranisto(organismoTransitoDTO);
        }

        relacionEstadoFormularioHolderFL.setRelacionEstados(relacionEstados);
        relacionEstados = relacionEstadoFormularioHolderFL.getRelacionEstados();

        try {
            administracionFormulariosEJB.actualizarRelacionEstados(relacionEstados);
            CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);

        }

    }

    /**
     * Permite llevar a cabo la eliminacion de un registro seleccionado en la interfaz de consulta
     */
    public void eliminarRelacionEstados() {
        logger.debug(RelacionesEstadosFormulariosMB.class.getName().concat("::eliminarRelacionEstados()"));
        final RelacionEstadoFormularioHolderFL relacionEstadoFormularioHolderFL = findFlowObject(
                OBJ_RELACION_ESTADO_FORMULARIO_HOLDER_FL, RELACION_ESTADO_FORMULARIO_HOLDER_FL);
        if (relacionEstadoFormularioHolderFL.getRelacionEstadosSeleccionado() != null) {
            administracionFormulariosEJB.eliminarRelacionEstados(relacionEstadoFormularioHolderFL
                    .getRelacionEstadosSeleccionado().getId());
            CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
        } else {
            CirculemosAccesoBundleGeneral.addMensajeNoSeleccionItem();
        }
    }
}
