package co.com.datatools.seguridad.mb.recursos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRCatalogosSeguridad;
import co.com.datatools.seguridad.interfaces.IRRecursoOperacion;
import co.com.datatools.seguridad.mb.opcionesmenu.CrearOpcionMenuMB;
import co.com.datatools.seguridad.mb.opcionesmenu.ModificarOpcionMenuMB;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.seguridad.util.SeguridadErrorHandler;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean para los flujos relacionados con el CRUD de los Recursos, obtiene y asigna valores de los Dtos: RecursosFl, CrearRecursoFL y
 * ModificarRecursoFl
 * 
 * @author claudia.rodriguez
 */

@ManagedBean
@SessionScoped
public class RecursosMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(RecursosMB.class.getName());

    private static final String NOMBRE_BUNDLE_RECURSO = "mensajesRecurso";

    @EJB
    private IRCatalogosSeguridad catalogoSeguridadEjb;

    @EJB
    private IRRecursoOperacion recursoOperacionEjb;

    /**
     * Managed bean para asignar un recurso seleccionado al realizar la busqueda de recursos para asociarlos a una opcion de menu que se esta creando
     */
    @ManagedProperty(value = "#{crearOpcionMenuMB}")
    private CrearOpcionMenuMB crearOpcionMenuMB;

    /**
     * Managed bean para asignar un recurso seleccionado al realizar la busqueda de recursos para asociarlos a una opcion de menu que se esta
     * modificando
     */
    @ManagedProperty(value = "#{modificarOpcionMenuMB}")
    private ModificarOpcionMenuMB modificarOpcionMenuMB;

    private List<OperacionDto> operacionesBD;
    private boolean mostrarOperaciones;

    public RecursosMB() {
        logger.debug("RecursosMB.constructor");
    }

    /**
     * Carga la lista de aplicaciones consultando las aplicaciones existentes
     */
    public void cargarListaAplicaciones() {
        logger.debug("RecursosMB.cargarListaAplicaciones");
        RecursosFL recursoFl = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);
        if (recursoFl.getlAplicaciones() == null) {
            Map<String, String> lAplicaciones = new HashMap<>();
            lAplicaciones = catalogoSeguridadEjb.consultarAplicaciones();
            recursoFl.setlAplicaciones(lAplicaciones);
        }
    }

    /**
     * Consulta las operaciones de la aplicacion y las carga en la lista de operaciones disponibles
     */
    public void cargarListaOperaciones() {
        logger.debug("RecursosMB.cargarOperaciones");
        CrearRecursoFL crearRecursoFl = findFlowObject(CrearRecursoFL.class, CrearRecursoFL.NOMBRE_BEAN);
        if (crearRecursoFl.getOperaciones() == null) {
            operacionesBD = recursoOperacionEjb.consultarOperaciones();
            List<OperacionDto> source = new ArrayList<OperacionDto>();
            List<OperacionDto> target = new ArrayList<OperacionDto>();
            for (OperacionDto operacionDto : operacionesBD) {
                if (!operacionDto.getNombreOperacion().equals(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO)) {
                    source.add(operacionDto);
                }
            }
            DualListModel<OperacionDto> operaciones = new DualListModel<OperacionDto>(source, target);
            crearRecursoFl.setOperaciones(operaciones);
        }
    }

    /**
     * Consulta los recursos segun los filtros ingresados, sino encuentra resultados se muestra un mensaje
     */
    public void consultarRecursos() {
        logger.debug("RecursosMB.consultarRecursos");
        RecursosFL recursoFl = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);
        recursoFl.setConsultaRealizada(false);
        // Verificar los filtros
        RecursoDto dtoFiltro = new RecursoDto();
        dtoFiltro.setIdAplicacion(recursoFl.getIdAplicacion());
        dtoFiltro.setNombreRecurso(recursoFl.getNombre());
        dtoFiltro.setDescripcion(recursoFl.getDescripcion());

        List<RecursoDto> resultados = new ArrayList<>();
        resultados = recursoOperacionEjb.consultarRecursos(dtoFiltro, false);
        recursoFl.setResultadoConsulta(resultados);
        recursoFl.setRecursoSeleccionado(null);
        recursoFl.setConsultaRealizada(true);
    }

    /**
     * Consulta el detalle del recurso seleccionado en la interfaz, asigna este detalle a detalleRecurso en el DTO: recursosFL para que pueda ser
     * visualizado desde la opción "ver detalle"
     */
    public void consultarDetalle() {
        logger.debug("RecursosMB.consultarDetalle");
        RecursosFL recursosFL = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);
        RecursoDetalleDto detalleRecurso = recursoOperacionEjb.consultarRecurso(recursosFL.getRecursoSeleccionado()
                .getIdRecurso());
        recursosFL.setDetalleRecurso(detalleRecurso);
    }

    /**
     * Consulta el detalle del recurso seleccionado como padre, asigna este detalle a detalleRecursoPadre en el DTO: recursosFL para que pueda ser
     * visualizado desde la opción "ver detalle"
     */
    public void consultarDetalleRecursoPadre() {
        logger.debug("RecursosMB.consultarDetalleRecursoPadre");
        RecursosFL recursosFL = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);
        recursosFL.setDetalleRecursoPadre(recursoOperacionEjb.consultarRecurso(recursosFL.getRecursoPadreSeleccionado()
                .getIdRecurso()));
    }

    /**
     * Elimina el recurso seleccionado en la interfaz y muestra un mensaje de eliminacion exitosa o de error en caso de que no se pueda eliminar por
     * integridad referencial con Roles o por ser padre de otro recurso
     */
    public void eliminarRecurso() {
        logger.debug("RecursosMB.eliminarRecurso");
        RecursosFL recursosFL = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);
        RecursoDto recursoSeleccionado = recursosFL.getRecursoSeleccionado();
        try {
            recursoOperacionEjb.eliminarRecurso(recursoSeleccionado.getIdRecurso());
            addInfoMessage(NOMBRE_BUNDLE_RECURSO, "msg_recurso_eliminado");
            recursosFL.getResultadoConsulta().remove(recursoSeleccionado);

        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }
    }

    /**
     * Realiza la consulta de los recursos segun los filtros ingresados en la ventana emergente de busqueda de recursos para asignar un recurso padre
     */
    public void buscarRecursos() {
        logger.debug("RecursosMB.buscarRecursos");
        RecursosFL recursoFL = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);
        recursoFL.setConsultaPadreRealizada(false);
        // Verificar los filtros
        RecursoDto dtoFiltro = new RecursoDto();
        dtoFiltro.setIdAplicacion(recursoFL.getIdAplicacion());
        dtoFiltro.setNombreRecurso(recursoFL.getNombreRecPadreConsulta());
        dtoFiltro.setDescripcion(recursoFL.getDescripcionRecPadreConsulta());

        List<RecursoDto> resultadoConsultaPadre = recursoOperacionEjb.consultarRecursos(dtoFiltro, false);

        if (recursoFL.isAsignacionRecursoPadre()) {
            if (recursoFL.getRecursoSeleccionado() != null) {
                RecursoDto recursoEliminar = null;
                // Eliminar del resultado el propio recurso que se selecciono para editar
                for (RecursoDto recursoDto : resultadoConsultaPadre) {
                    if (recursoDto.getIdRecurso().equals(recursoFL.getRecursoSeleccionado().getIdRecurso())) {
                        recursoEliminar = recursoDto;
                    }
                }
                resultadoConsultaPadre.remove(recursoEliminar);
            }
        }
        recursoFL.setResultadoConsultaRecPadre(resultadoConsultaPadre);
        recursoFL.setRecursoPadreSeleccionado(null);
        recursoFL.setConsultaPadreRealizada(true);
    }

    /**
     * Invoca al ejb que crea un recurso con los datos diligenciados. Si se ha definido que el recurso tiene operaciones, valida que se ha ingresado
     * al menos una y muestra un mensaje de guardado exitoso o de error en caso de que no se pueda realizar la operacion de creacion del recurso
     */
    public boolean crearRecurso() {
        logger.debug("RecursosMB.crearRecurso");
        boolean creaRecurso = false;
        CrearRecursoFL crearRecursoFl = findFlowObject(CrearRecursoFL.class, CrearRecursoFL.NOMBRE_BEAN);
        // Si tiene operaciones, validar que se haya seleccionado al menos una
        if (crearRecursoFl.isTieneOperaciones() && crearRecursoFl.getOperaciones().getTarget().size() == 0) {
            addErrorMessage(NOMBRE_BUNDLE_RECURSO, "msg_recurso_requiere_oper");
        } else {
            RecursoDetalleDto recursoDetalle = new RecursoDetalleDto();
            recursoDetalle.setIdAplicacion(crearRecursoFl.getIdAplicacion());
            recursoDetalle.setNombreRecurso(crearRecursoFl.getNombre());
            recursoDetalle.setDescripcion(crearRecursoFl.getDescripcion());
            if (crearRecursoFl.getIdRecursoPadre() != null && !crearRecursoFl.getIdRecursoPadre().equals("")) {
                RecursoDto recPadre = new RecursoDto();
                recPadre.setIdRecurso(Integer.valueOf(crearRecursoFl.getIdRecursoPadre()));
                recursoDetalle.setPadre(recPadre);
            }
            recursoDetalle.setOperaciones(crearRecursoFl.getOperaciones().getTarget());
            try {
                recursoOperacionEjb.registrarRecurso(recursoDetalle);
                addInfoMessage(NOMBRE_BUNDLE_RECURSO, "msg_recurso_guardado");
                creaRecurso = true;
            } catch (SeguridadException e) {
                SeguridadErrorHandler.handleException(e);
            }
        }
        return creaRecurso;
    }

    /**
     * Metodo que carga los datos del recurso seleccionado, en la pagina de modificacion y es invocado cuando entra al view-state de modificacion de
     * recurso
     */
    public void cargarDetalleRecurso() {
        logger.debug("RecursosMB::cargarDetalleRecurso");
        ModificarRecursoFL modificarRecursoFL = findFlowObject(ModificarRecursoFL.class, ModificarRecursoFL.NOMBRE_BEAN);
        RecursosFL recursosFL = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);

        if (modificarRecursoFL.isPrimerIngreso()) {

            RecursoDto recursoSeleccionado = recursosFL.getRecursoSeleccionado();

            RecursoDetalleDto detalleRecurso = recursoOperacionEjb.consultarRecurso(recursoSeleccionado.getIdRecurso());
            modificarRecursoFL.setRecursoModificar(detalleRecurso);
            recursosFL.setIdAplicacion(detalleRecurso.getIdAplicacion());
            // Validar operaciones disponibles y asignadas
            if (detalleRecurso.getOperaciones() != null && detalleRecurso.getOperaciones().size() > 0) {
                modificarRecursoFL.setTieneOperaciones(true);
            } else
                modificarRecursoFL.setTieneOperaciones(false);

            // Cargar la lista de operaciones
            List<OperacionDto> sourceOp = new ArrayList<OperacionDto>();
            List<OperacionDto> targetOp = new ArrayList<OperacionDto>();
            targetOp = detalleRecurso.getOperaciones();
            boolean operacionEnRecurso = false;
            operacionesBD = recursoOperacionEjb.consultarOperaciones();
            for (OperacionDto operacionDto : operacionesBD) {
                operacionEnRecurso = false;
                if (modificarRecursoFL.isTieneOperaciones()) {
                    for (OperacionDto operRecursoDto : detalleRecurso.getOperaciones()) {
                        if (operacionDto.getIdOperacion().equals(operRecursoDto.getIdOperacion())) {
                            operacionEnRecurso = true;
                            if (operRecursoDto.getNombreOperacion()
                                    .equals(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO)) {
                                detalleRecurso.getOperaciones().remove(operRecursoDto);
                            }
                            break;
                        }
                    }
                }
                if (!operacionEnRecurso) {
                    sourceOp.add(operacionDto);
                }
            }
            modificarRecursoFL.setOperacionesRecurso(new DualListModel<OperacionDto>(sourceOp, targetOp));

            if (detalleRecurso.getPadre() != null) {
                modificarRecursoFL.setRecursoPadre(detalleRecurso.getPadre().getDescripcion());
                modificarRecursoFL.setIdRecursoPadre(String.valueOf(detalleRecurso.getPadre().getIdRecurso()));
            }

            modificarRecursoFL.setPrimerIngreso(false);
        }

    }

    /**
     * Metodo que asigna el recurso padre seleccionado al recurso que se esta creando o modificando
     */
    public void asignarRecursoPadre() {
        logger.debug("RecursosMB::asignarRecursoPadre");
        RecursosFL recursosFL = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);
        RecursoDto recursoPadreSel = recursosFL.getRecursoPadreSeleccionado();
        if (recursosFL.isAsignacionRecursoPadre()) {
            if (recursosFL.getRecursoSeleccionado() != null) {
                // Esta modificando un recurso
                ModificarRecursoFL modificarRecursoFL = findFlowObject(ModificarRecursoFL.class,
                        ModificarRecursoFL.NOMBRE_BEAN);
                modificarRecursoFL.setIdRecursoPadre(String.valueOf(recursoPadreSel.getIdRecurso()));
                modificarRecursoFL.setRecursoPadre(recursoPadreSel.getDescripcion());
            } else {
                CrearRecursoFL crearRecursoFL = findFlowObject(CrearRecursoFL.class, CrearRecursoFL.NOMBRE_BEAN);
                crearRecursoFL.setIdRecursoPadre(String.valueOf(recursoPadreSel.getIdRecurso()));
                crearRecursoFL.setRecursoPadre(recursoPadreSel.getDescripcion());
            }
        } else {
            // Es una busqueda de recurso para opciones de menu
            if (recursosFL.isModificacionMenu()) {
                modificarOpcionMenuMB.getMenuModificar().getRecurso().setIdRecurso(recursoPadreSel.getIdRecurso());
                modificarOpcionMenuMB.getMenuModificar().getRecurso()
                        .setNombreRecurso(recursoPadreSel.getDescripcion());
            } else {
                crearOpcionMenuMB.setIdRecurso(String.valueOf(recursoPadreSel.getIdRecurso()));
                crearOpcionMenuMB.setNombreRecurso(recursoPadreSel.getDescripcion());
            }
        }
    }

    /**
     * Invoca al ejb que almacena la modificacion de los datos del recurso, muestra un mensaje de guardado exitoso o de error en caso de que no se
     * pueda realizar la operacion
     */
    public void actualizarRecurso() {
        logger.debug("RecursosMB::actualizarRecurso");
        ModificarRecursoFL modificarRecursoFL = findFlowObject(ModificarRecursoFL.class, ModificarRecursoFL.NOMBRE_BEAN);

        if (modificarRecursoFL.isTieneOperaciones()
                && modificarRecursoFL.getOperacionesRecurso().getTarget().size() == 0) {
            addErrorMessage(NOMBRE_BUNDLE_RECURSO, "msg_recurso_requiere_oper");
        } else {

            RecursoDto recursoModificar = modificarRecursoFL.getRecursoModificar();
            RecursoDetalleDto detalleRecurso = new RecursoDetalleDto();
            detalleRecurso.setIdRecurso(recursoModificar.getIdRecurso());
            detalleRecurso.setDescripcion(recursoModificar.getDescripcion());
            detalleRecurso.setIdAplicacion(recursoModificar.getIdAplicacion());
            detalleRecurso.setNombreRecurso(recursoModificar.getNombreRecurso());
            if (modificarRecursoFL.isTieneOperaciones())
                detalleRecurso.setOperaciones(modificarRecursoFL.getOperacionesRecurso().getTarget());
            else
                detalleRecurso.setOperaciones(null);
            if (modificarRecursoFL.getIdRecursoPadre() != null && !modificarRecursoFL.getIdRecursoPadre().equals("")) {
                RecursoDto padre = new RecursoDto();
                padre.setIdRecurso(Integer.valueOf(modificarRecursoFL.getIdRecursoPadre()));
                detalleRecurso.setPadre(padre);
            }

            try {
                recursoOperacionEjb.actualizarRecurso(detalleRecurso);
                addInfoMessage(NOMBRE_BUNDLE_RECURSO, "msg_recurso_guardado");
            } catch (SeguridadException e) {
                SeguridadErrorHandler.handleException(e);
            }
        }

    }

    /**
     * Valida que se haya seleccionado una aplicacion en el formulario de la creacion de recurso y abre un pop-up que permite la consulta de recursos
     * de la aplicacion seleccionada con el fin de asignar un recurso padre
     */
    public void abrirBusquedaRecPadre() {
        logger.debug("RecursosMB::abrirBusquedaRecPadre");
        CrearRecursoFL crearRecursoFl = findFlowObject(CrearRecursoFL.class, CrearRecursoFL.NOMBRE_BEAN);
        if (crearRecursoFl.getIdAplicacion() == null) {
            // Mostrar mensaje requerido
            ResourceBundle bundleGeneral = getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL);
            getFacesContext().addMessage("form-content_injComi:app",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, bundleGeneral.getString("mensaje_requerido")));
        } else {
            // Mostrar popup de busqueda, filtrando por la aplicacion seleccionada y limpiando los datos de una posible busqueda anterior
            RecursosFL recursosFl = findFlowObject(RecursosFL.class, RecursosFL.NOMBRE_BEAN);
            recursosFl.setIdAplicacion(crearRecursoFl.getIdAplicacion());
            recursosFl.setNombreRecPadreConsulta(null);
            recursosFl.setDescripcionRecPadreConsulta(null);
            recursosFl.setResultadoConsultaRecPadre(new ArrayList<RecursoDto>());
            recursosFl.setConsultaPadreRealizada(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("dialogBusqueda");
            context.execute("PF('dlgBusqueda').show()");

        }
    }

    /**
     * Elimina la asignacion de un recurso padre en el formulario de creacion de recursos
     */
    public void quitarRecursoPadreCrear() {
        logger.debug("RecursosMB::quitarRecursoPadreCrear");
        CrearRecursoFL crearRecursoFl = findFlowObject(CrearRecursoFL.class, CrearRecursoFL.NOMBRE_BEAN);
        crearRecursoFl.setIdRecursoPadre(null);
        crearRecursoFl.setRecursoPadre(null);
    }

    /**
     * Elimina la asignacion de un recurso padre en el formulario de modificacion de recursos
     */
    public void quitarRecursoPadreModificar() {
        logger.debug("RecursosMB::quitarRecursoPadreModificar");
        ModificarRecursoFL modificarRecursoFL = findFlowObject(ModificarRecursoFL.class, ModificarRecursoFL.NOMBRE_BEAN);
        modificarRecursoFL.setIdRecursoPadre(null);
        modificarRecursoFL.setRecursoPadre(null);
    }

    public boolean isMostrarOperaciones() {
        return mostrarOperaciones;
    }

    public void setMostrarOperaciones(boolean mostrarOperaciones) {
        this.mostrarOperaciones = mostrarOperaciones;
    }

    public CrearOpcionMenuMB getCrearOpcionMenuMB() {
        return crearOpcionMenuMB;
    }

    public void setCrearOpcionMenuMB(CrearOpcionMenuMB crearOpcionMenuMB) {
        this.crearOpcionMenuMB = crearOpcionMenuMB;
    }

    public ModificarOpcionMenuMB getModificarOpcionMenuMB() {
        return modificarOpcionMenuMB;
    }

    public void setModificarOpcionMenuMB(ModificarOpcionMenuMB modificarOpcionMenuMB) {
        this.modificarOpcionMenuMB = modificarOpcionMenuMB;
    }

}
