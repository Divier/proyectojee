package co.com.datatools.seguridad.mb.operaciones;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRRecursoOperacion;
import co.com.datatools.seguridad.util.SeguridadErrorHandler;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean para los flujos relacionados con el CRUD de Operaciones, obtiene y asigna valores de los Dtos: OperacionesFl, CrearOperacionFL y
 * ModificarOperacionFl
 * 
 * @author claudia.rodriguez
 */

@ManagedBean
@SessionScoped
public class OperacionesMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(OperacionesMB.class.getName());

    private static final String NOMBRE_BUNDLE_OPERACION = "mensajesOperacion";

    @EJB
    private IRRecursoOperacion recursoOperacionEjb;

    public OperacionesMB() {
        logger.debug("OperacionesMB::OperacionesMB");
    }

    /**
     * Invoca al ejb para la creacion de una nueva operacion. Muestra un mensaje exitoso o de error en caso de que no pueda ser guardada la operacion
     */
    public void crearOperacion() {
        logger.debug("OperacionesMB::crearOperacion");
        CrearOperacionFL crearOperacionFL = findFlowObject(CrearOperacionFL.class, CrearOperacionFL.NOMBRE_BEAN);
        OperacionDto operacionPersistir = new OperacionDto();
        operacionPersistir.setNombreOperacion(crearOperacionFL.getNombre());
        try {
            recursoOperacionEjb.registrarOperacion(operacionPersistir);
            addInfoMessage(NOMBRE_BUNDLE_OPERACION, "msg_operacion_guardada");
            crearOperacionFL.setNombre(null);
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }

    }

    /**
     * Consulta las operaciones que cumplan con los filtros ingresados
     */
    public void consultarOperaciones() {
        logger.debug("OperacionesMB::consultarOperaciones");
        OperacionesFL operacionFL = findFlowObject(OperacionesFL.class, OperacionesFL.NOMBRE_BEAN);
        List<OperacionDto> resultados = recursoOperacionEjb.consultarOperaciones(operacionFL.getNombre());
        operacionFL.setConsultaRealizada(true);
        operacionFL.setResultadoConsulta(resultados);
        operacionFL.setOperacionSeleccionada(null);
    }

    /**
     * Invoca al ejb para eliminar la operacion seleccionada, muestra mensaje de eliminacion exitosa o de error si no es posible eliminar la operacion
     */
    public void eliminarOperacion() {
        logger.debug("OperacionesMB::eliminarOperacion");
        OperacionDto operacionSeleccionada = findFlowObject(OperacionesFL.class, OperacionesFL.NOMBRE_BEAN)
                .getOperacionSeleccionada();
        try {
            recursoOperacionEjb.eliminarOperacion(operacionSeleccionada.getIdOperacion());
            addInfoMessage(NOMBRE_BUNDLE_OPERACION, "msg_operacion_eliminada");
            OperacionesFL operacionFL = findFlowObject(OperacionesFL.class, OperacionesFL.NOMBRE_BEAN);
            operacionFL.getResultadoConsulta().remove(operacionSeleccionada);
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }
    }

    /**
     * Carga los datos de la operacion seleccionada para que puedan ser modificados
     */
    public void cargarDetalleOperacion() {
        logger.debug("OperacionesMB::cargarDetalleOperacion");
        OperacionesFL operacionesFL = findFlowObject(OperacionesFL.class, OperacionesFL.NOMBRE_BEAN);
        OperacionDto operacionSeleccionada = operacionesFL.getOperacionSeleccionada();
        ModificarOperacionFL modificarOperacionFL = findFlowObject(ModificarOperacionFL.class,
                ModificarOperacionFL.NOMBRE_BEAN);
        if (modificarOperacionFL.isPrimerIngreso()) {
            modificarOperacionFL.setOperacionModificar(operacionSeleccionada);
            modificarOperacionFL.setPrimerIngreso(false);
        }
    }

    /**
     * Invoca al ejb para actualizar los datos de la operacion, muestra mensaje para informar que la operacion fue guardada o de error en caso de que
     * no pueda ser actualizada
     */
    public void actualizarOperacion() {
        logger.debug("OperacionesMB::actualizarOperacion");
        ModificarOperacionFL modificarOperacionFL = findFlowObject(ModificarOperacionFL.class,
                ModificarOperacionFL.NOMBRE_BEAN);
        try {
            recursoOperacionEjb.actualizarOperacion(modificarOperacionFL.getOperacionModificar());
            addInfoMessage(NOMBRE_BUNDLE_OPERACION, "msg_operacion_guardada");
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }

    }

}
