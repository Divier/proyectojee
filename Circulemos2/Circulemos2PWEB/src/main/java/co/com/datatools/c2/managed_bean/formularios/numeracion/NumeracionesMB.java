package co.com.datatools.c2.managed_bean.formularios.numeracion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
//import co.com.datatools.c2.negocio.interfaces.IRIntegracionAdminFormulario;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * ManagedBean para el flujo de administracion de numeraciones de formularios
 * 
 * @author claudia.rodriguez - giovanni.velandia(mod 06-11-2015)
 * 
 */
@ManagedBean
@SessionScoped
public class NumeracionesMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(NumeracionesMB.class.getName());

    @EJB
    IRAdministracionFormularios administracionFormulariosEjb;
    /*
     * @EJB IRIntegracionAdminFormulario integracionAdminFormulario;
     */

    private static final String NOMBRE_BUNDLE_NUMERACION = "labelNumeracion";

    public NumeracionesMB() {
        logger.debug("NumeracionesMB::NumeracionesMB()");
    }

    public boolean validarFiltrosConsulta() {
        NumeracionHolderFL numeracionHolderFL = findFlowObject(NumeracionHolderFL.class, NumeracionHolderFL.NOMBRE_BEAN);
        if (numeracionHolderFL.getIdTipoFormulario() == null && numeracionHolderFL.getEstado() == null) {
            addErrorMessage(NOMBRE_BUNDLE_NUMERACION, "msg_ingreso_filtro");
            return false;
        }
        return true;
    }

    /**
     * Invoca al metodo de negocio que consulta las numeraciones de formulario y asigna el resultado al dto de flujo para que sea visualizado en la
     * pagina
     */
    public void consultar() {
        logger.debug("NumeracionesMB::consultar()");
        NumeracionHolderFL numeracionHolderFL = findFlowObject(NumeracionHolderFL.class, NumeracionHolderFL.NOMBRE_BEAN);

        List<NumeracionFormularioDTO> resultado = administracionFormulariosEjb.consultarNumeracionFormulario(
                numeracionHolderFL.getIdTipoFormulario(), numeracionHolderFL.getEstado());
        numeracionHolderFL.setResultadoConsulta(resultado);
        if (resultado.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        }
    }

    /**
     * Invoca al metodo de negocio que elimina una numeracion de formulario
     */
    public void eliminar() {
        logger.debug("NumeracionesMB::eliminar()");
        NumeracionHolderFL numeracionHolderFL = findFlowObject(NumeracionHolderFL.class, NumeracionHolderFL.NOMBRE_BEAN);
        try {
            administracionFormulariosEjb.eliminarNumeracionFormulario(numeracionHolderFL.getNumeracionSeleccionada()
                    .getId());
            CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
            // Eliminar la numeracion de la tabla de resultados y la tabla se refresca para mostrar el cambio
            numeracionHolderFL.getResultadoConsulta().remove(numeracionHolderFL.getNumeracionSeleccionada());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Realiza la consulta del detalle de los datos de la numeracion seleccionada con el fin de que sean visualizados en la pagina de edicion de
     * consulta, verifica previamente que la numeracion sea editable
     * 
     * @return true si la numeracion se puede editar y por tanto se cargará sus datos para edición, de lo contrario false
     */
    public boolean cargarNumeracionEdicion() {
        logger.debug("NumeracionesMB::cargarNumeracionEdicion()");
        NumeracionHolderFL numeracionHolderFL = findFlowObject(NumeracionHolderFL.class, NumeracionHolderFL.NOMBRE_BEAN);
        // Validar que la numeracion sea editable=No hayan rangos asociados
        boolean editable = administracionFormulariosEjb.validarNumeracionEditable(numeracionHolderFL
                .getNumeracionSeleccionada().getId());
        if (editable) {
            List<DetalleNumeracionDTO> detalle = administracionFormulariosEjb
                    .consultarDetalleNumeracionFormulario(numeracionHolderFL.getNumeracionSeleccionada().getId());
            NumeracionFL numeracionFL = findFlowObject(NumeracionFL.class, NumeracionFL.NOMBRE_BEAN);
            numeracionFL.setNumeracionModificar(numeracionHolderFL.getNumeracionSeleccionada());
            numeracionFL.setDetalleNumeracion(detalle);
        } else {
            addErrorMessage(NOMBRE_BUNDLE_NUMERACION, "msg_numeracion_no_editable");
        }
        return editable;
    }

    public void cargarDetalleNumeracion() {
        logger.debug("NumeracionesMB::cargarDetalleNumeracion()");
        NumeracionHolderFL numeracionHolderFL = findFlowObject(NumeracionHolderFL.class, NumeracionHolderFL.NOMBRE_BEAN);
        List<DetalleNumeracionDTO> detalle = administracionFormulariosEjb
                .consultarDetalleNumeracionFormulario(numeracionHolderFL.getNumeracionSeleccionada().getId());
        numeracionHolderFL.getNumeracionSeleccionada().setDetalleNumeracionList(detalle);
    }

    /**
     * Invoca al metodo de negocio que actualiza una numeracion de formulario, verificando previamente la correctitud de la configuración
     */
    public void modificar() {
        logger.debug("NumeracionesMB::modificar()");
        NumeracionFL numeracionFL = findFlowObject(NumeracionFL.class, NumeracionFL.NOMBRE_BEAN);
        // Validar que el detalle(casillas) tengan una configuracion correcta
        boolean valido = validarDetalleNumeracion(numeracionFL.getDetalleNumeracion());
        if (valido) {
            numeracionFL.getNumeracionModificar().setDetalleNumeracionList(numeracionFL.getDetalleNumeracion());
            try {
                administracionFormulariosEjb.actualizarNumeracionFormulario(numeracionFL.getNumeracionModificar());
                addInfoMessage(NOMBRE_BUNDLE_NUMERACION, "msg_numeracion_actualizada");
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            }
        }

    }

    /**
     * Invoca al metodo de negocio que inserta una nueva numeracion de formulario, verificando previamente la correctitud de la configuración
     * 
     * @return true si a numeracion fue guardada, de lo contrario false
     */
    public boolean registrar() {
        logger.debug("NumeracionesMB::registrar()");
        NumeracionFL numeracionFL = findFlowObject(NumeracionFL.class, NumeracionFL.NOMBRE_BEAN);
        // Validar que el detalle(casillas) tengan una configuracion correcta
        boolean valido = validarDetalleNumeracion(numeracionFL.getDetalleNumeracion());
        if (valido) {
            NumeracionFormularioDTO numeracionFormularioDTO = new NumeracionFormularioDTO();
            numeracionFormularioDTO.setActivo(true);
            numeracionFormularioDTO.setDetalleNumeracionList(numeracionFL.getDetalleNumeracion());
            numeracionFormularioDTO.setDigitos(numeracionFL.getDigitos());
            numeracionFormularioDTO.setFechaInicial(numeracionFL.getFechaInicioVigencia());
            numeracionFormularioDTO.setFechaFinal(numeracionFL.getFechaFinVigencia());
            numeracionFormularioDTO.setTipoFormulario(new TipoFormularioDTO(numeracionFL.getIdTipoFormulario()));
            try {
                administracionFormulariosEjb.registrarNumeracionFormulario(numeracionFormularioDTO);
                addInfoMessage(NOMBRE_BUNDLE_NUMERACION, "msg_numeracion_guardada");
                return true;
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
                return false;
            }
        }
        return false;
    }

    /**
     * Verifica que todos los detalles de la numeracion tengan una configuracion correcta en cuanto a las reglas de negocio, si alguna casilla esta
     * mal configurada se muestra un mensaje de error sobre el registro especifico en la tabla de consulta
     * 
     * @param detalleNumeracion
     *            Lista de los detalles a validar
     * @return true si todos los detalles estan correctamente configurados de lo contrario false
     */
    private boolean validarDetalleNumeracion(List<DetalleNumeracionDTO> detalleNumeracion) {
        logger.debug("NumeracionesMB::validarDetalleNumeracion()");
        boolean valido = true;
        for (DetalleNumeracionDTO detalleNumeracionDTO : detalleNumeracion) {
            try {
                administracionFormulariosEjb.validarDetalleNumeracion(detalleNumeracionDTO);
            } catch (CirculemosNegocioException e) {
                FacesContext.getCurrentInstance().addMessage(
                        "form-ingreso:tabla-detalle:" + detalleNumeracion.indexOf(detalleNumeracionDTO)
                                + ":idTextDigito",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getErrorInfo().getDescError()));
                valido = false;
            }
        }
        return valido;

    }

    /**
     * Se invoca desde el boton de ver detalle para un registro de la consulta de numeracion y realiza la consulta del detalle de la numeracion
     * seleccionada para poder visualizarla
     */
    public void verDetalle() {
        logger.debug("NumeracionesMB::verDetalle()");
        NumeracionHolderFL numeracionHolderFL = findFlowObject(NumeracionHolderFL.class, NumeracionHolderFL.NOMBRE_BEAN);
        List<DetalleNumeracionDTO> detalle = administracionFormulariosEjb
                .consultarDetalleNumeracionFormulario(numeracionHolderFL.getNumeracionSeleccionada().getId());
        numeracionHolderFL.getNumeracionSeleccionada().setDetalleNumeracionList(detalle);
    }

    /**
     * Se invoca desde el evento blur del campo Digitos, y permite actualizar los datos solicitados en la tabla de configuracion de casillas de la
     * numeracion de acuerdo a valor de dicho campo
     */
    public void asignarDigitosNumeracion() {
        logger.debug("NumeracionesMB::asignarDigitosNumeracion()");
        NumeracionFL numeracionFL = findFlowObject(NumeracionFL.class, NumeracionFL.NOMBRE_BEAN);
        int digitos;
        if (numeracionFL.getNumeracionModificar() != null)
            digitos = numeracionFL.getNumeracionModificar().getDigitos();
        else
            digitos = numeracionFL.getDigitos();
        if (digitos != 0) {
            // numeracionFL.setCampoDigitosDeshabilitado(true);
            int longitudActual = numeracionFL.getDetalleNumeracion().size();
            if (digitos < longitudActual) {
                // hay que eliminar elementos
                List<DetalleNumeracionDTO> casillasRemover = new ArrayList<DetalleNumeracionDTO>();
                for (int j = digitos; j < longitudActual; j++) {
                    casillasRemover.add(numeracionFL.getDetalleNumeracion().get(j));
                }
                numeracionFL.getDetalleNumeracion().removeAll(casillasRemover);

            } else {
                // Hay q agregar nuevas casillas
                for (long i = longitudActual; i < digitos; i++) {
                    DetalleNumeracionDTO detalle = new DetalleNumeracionDTO();
                    detalle.setDigito(i + 1);
                    numeracionFL.getDetalleNumeracion().add(detalle);
                }
            }
        }
    }

    /**
     * Limpia campos de la logica de negocio
     */
    public void limpiarCampos() {
        logger.debug(NumeracionesMB.class.getName().concat("::limpiarCampos()"));
        final NumeracionHolderFL numeracionHolderFL = findFlowObject(NumeracionHolderFL.class,
                NumeracionHolderFL.NOMBRE_BEAN);
        numeracionHolderFL.setNumeracionSeleccionada(null);
    }
}
