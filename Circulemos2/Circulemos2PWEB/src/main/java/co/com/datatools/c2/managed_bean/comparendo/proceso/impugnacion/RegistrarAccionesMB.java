package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.AccionImpugnacionBackDTO;
import co.com.datatools.c2.dto.CaracteristicaPruebaDTO;
import co.com.datatools.c2.dto.TipoAccionBackDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoAccionBack;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comun.FachadaCatalogosMB;
import co.com.datatools.c2.negocio.interfaces.IRImpugnacion;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Managed Bean para el manejo de registro de acciones HU_CIR20_DAT_JUR_008
 * 
 * @author dixon.alvarez 2016-06-10
 * 
 */
@ManagedBean
@SessionScoped
public class RegistrarAccionesMB extends AbstractSwfManagedBean {
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(RegistrarAccionesMB.class.getName());

    private static final String NOMBRE_BUNDLE_IMPUGNACION = "labelProcesoImpugnacion";

    @ManagedProperty(value = "#{encabezadoImpugnacionMB}")
    EncabezadoImpugnacionMB encabezadoImpugnacion;

    @ManagedProperty(value = "#{fachadaCatalogosMB}")
    FachadaCatalogosMB fachadaCatalogosMB;

    @EJB
    private IRImpugnacion irImpugnacion;

    private String tituloAgregarAccion;
    private Map<Integer, String> tiposAccionBack;
    private Map<Integer, String> caracteristicasPrueba;

    /**
     * Inicializa los datos que se utilizan en la pagina
     * 
     * @return True, si cumple con las validaciones que permiten ingresar a la pagina de registrar acciones
     */
    public boolean inicializarDatos() {
        logger.debug("RegistrarAccionesMB::inicializarDatos()");
        boolean exitoso = true;
        RegistrarAccionesFL registrarAccionesFL = findFlowObject(RegistrarAccionesFL.class,
                RegistrarAccionesFL.NOMBRE_BEAN);
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        // obtiene el proceso seleccionado
        if (impugnacionHolderFL.getComparendoSeleccionado() == null
                || impugnacionHolderFL.getComparendoSeleccionado().getIdProceso() == null) {
            addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_error_proceso_no_seleccionado");
            return false;
        }
        // Valida si el estado actual del proceso permite registrar acciones
        if (!EnumEstadoProceso.ECUADOR_IMPUGNACION_PENDIENTE_GESTION_ESPECIALISTA.getValue().equals(
                impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())) {
            addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_error_estado_registrar_acciones");
            return false;
        }
        registrarAccionesFL.setIdProceso(impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());
        encabezadoImpugnacion.consultarEncabezado(registrarAccionesFL.getIdProceso());
        tiposAccionBack = new HashMap<Integer, String>();
        for (SelectItem item : fachadaCatalogosMB.catTipoAccionBack()) {
            tiposAccionBack.put((Integer) item.getValue(), item.getLabel());
        }
        caracteristicasPrueba = new HashMap<Integer, String>();
        for (SelectItem item : fachadaCatalogosMB.catCaracteristicaPrueba()) {
            caracteristicasPrueba.put((Integer) item.getValue(), item.getLabel());
        }
        return exitoso;
    }

    /**
     * Inicializa los datos para crear un nuevo registro de accion
     */
    public void inicializarRegistro() {
        logger.debug("RegistrarAccionesMB::inicializarRegistro()");
        tituloAgregarAccion = getBundle("labelProcesoImpugnacion").getString("label_registrar_accion");
        RegistrarAccionesFL registrarAccionesFL = findFlowObject(RegistrarAccionesFL.class,
                RegistrarAccionesFL.NOMBRE_BEAN);
        registrarAccionesFL.setRegistroNuevo(true);
        AccionImpugnacionBackDTO accionRegistrar = new AccionImpugnacionBackDTO();
        accionRegistrar.setCaracteristicaPrueba(new CaracteristicaPruebaDTO());
        accionRegistrar.setTipoAccionBack(new TipoAccionBackDTO());
        registrarAccionesFL.setAccionRegistrar(accionRegistrar);
    }

    /**
     * Inicializa los datos con la accion seleccionada para su edicion
     */
    public void inicializarEdicion() {
        logger.debug("RegistrarAccionesMB::inicializarEdicion()");
        tituloAgregarAccion = getBundle("labelProcesoImpugnacion").getString("label_editar_accion");
        RegistrarAccionesFL registrarAccionesFL = findFlowObject(RegistrarAccionesFL.class,
                RegistrarAccionesFL.NOMBRE_BEAN);
        registrarAccionesFL.setRegistroNuevo(false);
        registrarAccionesFL.setAccionRegistrar(registrarAccionesFL.getAccionSeleccionada());
    }

    /**
     * Elimina una accion de la lista
     */
    public void eliminar() {
        logger.debug("RegistrarAccionesMB::eliminar()");
        RegistrarAccionesFL registrarAccionesFL = findFlowObject(RegistrarAccionesFL.class,
                RegistrarAccionesFL.NOMBRE_BEAN);
        registrarAccionesFL.getAcciones().remove(registrarAccionesFL.getAccionSeleccionada());
        registrarAccionesFL.setAccionSeleccionada(null);
    }

    /**
     * Crea una nueva AccionImpugnacionBackDTO y la adiciona a la lista
     */
    public void registrar() {
        logger.debug("RegistrarAccionesMB::registrar()");
        RegistrarAccionesFL registrarAccionesFL = findFlowObject(RegistrarAccionesFL.class,
                RegistrarAccionesFL.NOMBRE_BEAN);
        registrarAccionesFL.getAccionRegistrar().setFechaAccion(new Date());
        if (registrarAccionesFL.isRegistroNuevo()) {
            if (registrarAccionesFL.getAcciones() == null) {
                registrarAccionesFL.setAcciones(new ArrayList<AccionImpugnacionBackDTO>());
            }
            registrarAccionesFL.getAcciones().add(registrarAccionesFL.getAccionRegistrar());
        } else {
            int indice = registrarAccionesFL.getAcciones().indexOf(registrarAccionesFL.getAccionSeleccionada());
            registrarAccionesFL.getAcciones().set(indice, registrarAccionesFL.getAccionRegistrar());
        }
    }

    /**
     * Persiste todas las acciones agregadas
     * 
     * @return True, si persiste todo correctamente
     */
    public boolean guardarAcciones() {
        logger.debug("RegistrarAccionesMB::guardarAcciones()");
        boolean guardadoExitoso = false;
        RegistrarAccionesFL registrarAccionesFL = findFlowObject(RegistrarAccionesFL.class,
                RegistrarAccionesFL.NOMBRE_BEAN);
        try {
            irImpugnacion.registrarAccionesBack(registrarAccionesFL.getAcciones(), registrarAccionesFL.getIdProceso());
            ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                    ImpugnacionHolderFL.NOMBRE_BEAN);
            impugnacionHolderFL.getComparendoSeleccionado().setIdEstadoProceso(
                    EnumEstadoProceso.ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION.getValue());
            addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_registro_acciones_exitoso");
            guardadoExitoso = true;
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            guardadoExitoso = false;
        }
        return guardadoExitoso;
    }

    /**
     * Devuelve el nombre del idTipoAccion enviado como parametro
     * 
     * @param idTipoAccion
     *            Indica el id tipo de accion al cual se va buscar el nombre
     * @return Nombre de un tipo de accion
     */
    public String nombreTipoAccion(Integer idTipoAccion) {
        if (tiposAccionBack == null || tiposAccionBack.isEmpty()) {
            tiposAccionBack = new HashMap<Integer, String>();
            for (SelectItem item : fachadaCatalogosMB.catTipoAccionBack()) {
                tiposAccionBack.put((Integer) item.getValue(), item.getLabel());
            }
        }
        return tiposAccionBack.get(idTipoAccion);
    }

    /**
     * Devulve el nombre de una caracteristica de prueba
     * 
     * @param idCaracteristica
     *            Id de la caracteristica de prueba a buscar
     * @return Nombre de una caracteristica de prueba
     */
    public String nombreCaracteristicaPrueba(Integer idCaracteristica) {
        if (caracteristicasPrueba == null || caracteristicasPrueba.isEmpty()) {
            caracteristicasPrueba = new HashMap<Integer, String>();
            for (SelectItem item : fachadaCatalogosMB.catCaracteristicaPrueba()) {
                caracteristicasPrueba.put((Integer) item.getValue(), item.getLabel());
            }
        }
        return caracteristicasPrueba.get(idCaracteristica);
    }

    /**
     * Genera un String de acuerdo al tipo de accion
     * 
     * @param accionImpugnacionBackDTO
     *            Contiene la informacion con la cual se genera el valor a retornar
     * @return String correspondiente a la descipcion de una accion impugnacion back
     */
    public String descripcionConcatenada(AccionImpugnacionBackDTO accionImpugnacionBackDTO) {
        if (accionImpugnacionBackDTO.getTipoAccionBack().getId().equals(EnumTipoAccionBack.PRUEBAS.getValue())) {
            return nombreCaracteristicaPrueba(accionImpugnacionBackDTO.getCaracteristicaPrueba().getId()) + " - "
                    + accionImpugnacionBackDTO.getDescripcion();
        } else {
            return accionImpugnacionBackDTO.getDescripcion();
        }
    }

    /**
     * Es utilizado para crear un identificador unico en la tabla de acciones a registrar
     * 
     * @param accionImpugnacionBackDTO
     *            Contiene la informacion con la que se genera el identificador
     * @return long
     */
    public long rowKey(AccionImpugnacionBackDTO accionImpugnacionBackDTO) {
        if (accionImpugnacionBackDTO != null && accionImpugnacionBackDTO.getId() != null) {
            return accionImpugnacionBackDTO.getId();
        }
        return accionImpugnacionBackDTO.getFechaAccion().getTime();
    }

    public EncabezadoImpugnacionMB getEncabezadoImpugnacion() {
        return encabezadoImpugnacion;
    }

    public void setEncabezadoImpugnacion(EncabezadoImpugnacionMB encabezadoImpugnacion) {
        this.encabezadoImpugnacion = encabezadoImpugnacion;
    }

    public String getTituloAgregarAccion() {
        return tituloAgregarAccion;
    }

    public void setTituloAgregarAccion(String tituloAgregarAccion) {
        this.tituloAgregarAccion = tituloAgregarAccion;
    }

    public FachadaCatalogosMB getFachadaCatalogosMB() {
        return fachadaCatalogosMB;
    }

    public void setFachadaCatalogosMB(FachadaCatalogosMB fachadaCatalogosMB) {
        this.fachadaCatalogosMB = fachadaCatalogosMB;
    }

}
