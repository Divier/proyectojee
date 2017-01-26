package co.com.datatools.c2.managed_bean.formularios.asignacion;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.formularios.AsignacionDTO;
import co.com.datatools.c2.dto.formularios.CantidadRangoDTO;
import co.com.datatools.c2.dto.formularios.CausalCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.DocumentoFormularioDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.dto.formularios.RangoDisponibleDTO;
import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.StockTipoResponsableDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * ManagedBean que gestiona las paginas de asignacion de formularios
 * 
 * @author claudia.rodriguez
 * 
 */
@ManagedBean
@SessionScoped
public class AsignacionesMB extends AbstractC2ManagedBean {

    private static final String ASIGNACION_HOLDER_FL = "asignacionHolderFL";
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AsignacionesMB.class.getName());
    private static final String NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM = "labelCambioEstadosForm";

    private String msgConfirmaCantidad;
    private String extensionesPermitidas = "/(\\.|\\/)(rar|zip|7zip)$/";
    private OrganismoTransitoDTO organismoTransito;
    private PaisDTO pais;

    @EJB
    private IRAdministracionFormularios administracionFormulariosEjb;

    @EJB
    private IRFormulario formulariosEjb;

    public AsignacionesMB() {
        // Obtener el organismo de transito
        organismoTransito = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
        pais = findSessionObject(ConstantesManagedBean.CLASE_OBJ_PAIS, ConstantesManagedBean.NOMBRE_OBJ_PAIS);
    }

    /**
     * Ingresa el dia en que se realiza la asignacion
     * 
     * @author giovanni.velandia
     */
    public void ingresoDiaAsignacio() {
        logger.debug("AsignacionesMB::ingresoDiaAsignacio()");
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);
        Date now = Calendar.getInstance().getTime();
        asignacionFL.setFechaMaxima(now);
        asignacionFL.setFechaAsignacion(now);
    }

    /* NUEVO { */

    /**
     * Actualiza la busqueda de responsable segun tipo
     */
    public void cambiarTipoResponsable() {
        logger.debug("AsignacionesMB::cambiarTipoResponsable");
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);
        asignacionFL.setDeshabilitarDatosResponsable(asignacionFL.getIdTipoResponsable().equals(
                EnumTipoResponsableFormulario.ORGANISMO_TRANSITO.getValue()));
        if (!asignacionFL.isDeshabilitarDatosResponsable())
            asignacionFL.setIdTipoDocResponsable(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor());
    }

    public void consultarResponsableFormulario() {
        logger.debug("AsignacionesMB::consultarResponsableFormulario");
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);

        if (EnumTipoResponsableFormulario.EMPRESA.getValue().equals(asignacionFL.getIdTipoResponsable()))
            asignacionFL.setCodigoOrganismoConsulta(null);
        else
            asignacionFL.setNumeroDocResponsable(null);

        // Crear DTO de filtros
        UnificacionResponsableDTO unificacionResponsable = new UnificacionResponsableDTO();
        unificacionResponsable.setResponsableFormulario(new ResponsableFormularioDTO());
        unificacionResponsable.setPersona(new PersonaDTO());

        if (asignacionFL.getIdTipoResponsable() != null)
            unificacionResponsable.getResponsableFormulario().setTipoResponsable(
                    new TipoResponsableFormularioDTO(asignacionFL.getIdTipoResponsable()));

        // Asignar campos segun tipo de formulario
        if (EnumTipoResponsableFormulario.EMPRESA.getValue().equals(asignacionFL.getIdTipoResponsable())) {
            if (asignacionFL.getIdTipoDocResponsable() != null)
                unificacionResponsable.getPersona().setTipoIdentificacion(
                        new TipoIdentificacionPersonaDTO(asignacionFL.getIdTipoDocResponsable()));
            if (asignacionFL.getNumeroDocResponsable() != null)
                unificacionResponsable.getPersona().setNumeroIdentificacion(asignacionFL.getNumeroDocResponsable());
        } else if (asignacionFL.getCodigoOrganismoConsulta() != null)
            unificacionResponsable.setOrganismoTransito(new OrganismoTransitoDTO(asignacionFL
                    .getCodigoOrganismoConsulta()));

        try {
            // Realizar consulta
            UnificacionResponsableDTO unificacionResponsableDTO = administracionFormulariosEjb
                    .consultarResponsableFormularios(unificacionResponsable);

            if (unificacionResponsableDTO != null && unificacionResponsableDTO.getResponsableFormulario() != null) {
                // Datos de responsable
                if (unificacionResponsableDTO.getResponsableFormulario().getId() != null)
                    asignacionFL.setIdResponsable(unificacionResponsableDTO.getResponsableFormulario().getId());
                if (unificacionResponsableDTO.getResponsableFormulario().getCorreoResponsableFormulario() != null)
                    asignacionFL.setEmailResponsable(unificacionResponsableDTO.getResponsableFormulario()
                            .getCorreoResponsableFormulario());
                // Datos de organismo de transito
                if (unificacionResponsableDTO.getOrganismoTransito() != null
                        && unificacionResponsableDTO.getOrganismoTransito().getNombreOrganismo() != null)
                    asignacionFL.setNombreOrganismo(unificacionResponsableDTO.getOrganismoTransito()
                            .getNombreOrganismo());
                // Datos de persona juridica
                if (unificacionResponsableDTO.getPersona() != null) {
                    PersonaJuridicaDTO personaJuridicaDTO = (PersonaJuridicaDTO) unificacionResponsableDTO.getPersona();
                    if (personaJuridicaDTO.getNombreComercial() != null)
                        asignacionFL.setNombreResponsable(personaJuridicaDTO.getNombreComercial());
                }
            } else {
                addWarningMessage(NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "btnValidarResponsable",
                        NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "msg_confirmacion_registro_responsable");
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public void consultarRangosDisponibles() {
        logger.debug("AsignacionesMB::consultarRangosDisponibles");
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);
        AsignacionHolderFL asignacionHolderFL = findFlowObject(AsignacionHolderFL.class, ASIGNACION_HOLDER_FL);
        asignacionFL.setRangosDisponibles(new ArrayList<RangoDisponibleDTO>());

        // No permite asignar si no existe responsable
        if (asignacionFL.getIdResponsable() == null) {
            addErrorMessage(NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "titulo_ingresar_asignacion",
                    NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "msg_responsable_requerido");
            return;
        }

        TipoFormularioDTO tipoFormularioDTO = new TipoFormularioDTO(asignacionFL.getIdTipoFormulario());
        tipoFormularioDTO.setActivo(true);

        // Stock minimo y maximo posible a asignar
        StockTipoResponsableDTO consultaStockDTO = new StockTipoResponsableDTO();
        consultaStockDTO.setCodigoOrganismo(organismoTransito);
        consultaStockDTO.setEstadoStock(true);
        consultaStockDTO.setTipoFormulario(tipoFormularioDTO);
        consultaStockDTO.setTipoResponsable(new TipoResponsableFormularioDTO(asignacionFL.getIdTipoResponsable()));

        ResponsableFormularioDTO responsableFormularioDTO = new ResponsableFormularioDTO();
        responsableFormularioDTO.setId(asignacionFL.getIdResponsable());
        responsableFormularioDTO.setOrganismoTransito(organismoTransito);
        responsableFormularioDTO.setTipoResponsable(new TipoResponsableFormularioDTO(asignacionFL
                .getIdTipoResponsable()));

        FormularioDTO consultaFormDTO = new FormularioDTO();
        consultaFormDTO.setCodigoOrganismo(organismoTransito);
        consultaFormDTO.setEstadoFormulario(new EstadoFormularioDTO(asignacionHolderFL.getIdEstadoFormulario()));
        consultaFormDTO.setResponsableFormulario(responsableFormularioDTO);
        consultaFormDTO.setTipoFormulario(tipoFormularioDTO);

        // Stock tipo responsable llave unica: codigo organismo, tipo responsable, tipo formulario
        List<StockTipoResponsableDTO> stockTipoRespDTOList = formulariosEjb
                .consultarStockTipoResponsable(consultaStockDTO);
        asignacionFL.setCantidadFormulariosResponsable(formulariosEjb.consultarCantidadFormularios(consultaFormDTO));

        if (!stockTipoRespDTOList.isEmpty()) {
            asignacionFL.setStockMinimoForm(stockTipoRespDTOList.get(0).getStockMinimo());
            asignacionFL.setStockMaximoForm(stockTipoRespDTOList.get(0).getStockMaximo());
            asignacionFL.setMaximoAsignable(stockTipoRespDTOList.get(0).getStockMaximo()
                    - asignacionFL.getCantidadFormulariosResponsable());

            if (asignacionFL.getMaximoAsignable() < 0)
                asignacionFL.setMaximoAsignable(0);
        } else {
            addErrorMessage(NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "titulo_ingresar_asignacion",
                    NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "msg_no_existe_stock");
            asignacionFL.setMsgNoExisteStock(true);
            return;
        }

        // Consultar rangos disponibles
        RangoFormularioDTO consultaRangoDTO = new RangoFormularioDTO();
        consultaRangoDTO.setCodigoOrganismo(organismoTransito);
        consultaRangoDTO.setTipoFormulario(tipoFormularioDTO);

        List<RangoFormularioDTO> rangoFormularioDTOList;
        try {
            rangoFormularioDTOList = formulariosEjb.consultarRangosFormularioDisponibles(consultaRangoDTO);

            for (RangoFormularioDTO rango : rangoFormularioDTOList) {
                RangoDisponibleDTO rangoDisponibleDTO = new RangoDisponibleDTO();
                rangoDisponibleDTO.setId(rango.getId());
                rangoDisponibleDTO.setNumeroInicial(rango.getNumeroInicial());
                rangoDisponibleDTO.setNumeroFinal(rango.getNumeroFinal());
                rangoDisponibleDTO.setCantidadTotal(rango.getCantidadTotal());
                rangoDisponibleDTO.setCantidadDisponible(rango.getCantidadDisponible());
                asignacionFL.getRangosDisponibles().add(rangoDisponibleDTO);
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }

        // No existen rangos disponibles
        if (asignacionFL.getRangosDisponibles().isEmpty()) {
            addErrorMessage(NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "titulo_ingresar_asignacion",
                    NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "table_rangos_empty");
            return;
        }

        consultarTipoFormulario();
        asignacionFL.setDeshabilitarBotonGuardar(false);
    }

    public void limpiarRangosDisponibles() {
        logger.debug("AsignacionesMB::limpiarRangosDisponibles");
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);

        asignacionFL.setRangosDisponibles(null);
        asignacionFL.setIdTipoFormulario(null);
        asignacionFL.setStockMinimoForm(null);
        asignacionFL.setMaximoAsignable(null);
        asignacionFL.setCantidadFormularios(null);
        asignacionFL.setDeshabilitarBotonGuardar(true);
    }

    public void actualizarCantidadFormularios() {
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);
        BigDecimal cantidadFormularios = BigDecimal.ZERO;

        for (RangoDisponibleDTO rango : asignacionFL.getRangosDisponibles())
            if (rango.getCantidadIngresada() != null)
                cantidadFormularios = cantidadFormularios.add(new BigDecimal(rango.getCantidadIngresada()));

        asignacionFL.setCantidadFormularios(cantidadFormularios.intValue());
    }

    /**
     * Maneja el evento de cargar el archivo de autorizacion del rango
     * 
     * @param event
     *            Evento de cargue del archivo
     */
    public void cargarArchivo(FileUploadEvent event) {
        logger.info("AsignacionesMB::cargarArchivo");
        UploadedFile archivo = event.getFile();
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);
        asignacionFL.setDocumentoAutorizacion(archivo);
        asignacionFL.setNombreArchivo(archivo.getFileName());
    }

    /**
     * Valida que cantidad de formularios a asignar mas la cantidad actual de formularios de responsable no supere el stock maximo por tipo de
     * responsable y tipo de formulario
     * 
     * @return
     */
    public String validarCantidad() {
        logger.debug("AsignacionesMB::validarCantidad");
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);
        String flujoRetorno = "CREAR-ASIGNACION";

        actualizarCantidadFormularios();
        asignacionFL.setCantidadInferiorMinimo(false);
        asignacionFL.setCantidadInferiorMaximo(false);

        // Validar cantidad minima
        if (asignacionFL.getCantidadFormularios() < asignacionFL.getStockMinimoForm()) {
            msgConfirmaCantidad = MessageFormat.format(
                    getBundle(NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM).getString("msg_confirmacion_cantidad"),
                    asignacionFL.getNombreTipoFormulario(), asignacionFL.getStockMinimoForm(),
                    asignacionFL.getCantidadFormularios());
            asignacionFL.setCantidadInferiorMinimo(true);
            flujoRetorno = "ASIGNAR_FORMULARIO";
        }

        // Validar cantidad maxima
        if (asignacionFL.getCantidadFormularios() + asignacionFL.getCantidadFormulariosResponsable() > asignacionFL
                .getStockMaximoForm()) {
            msgConfirmaCantidad = MessageFormat.format(
                    getBundle(NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM).getString("msg_responsable_maximo_stock"),
                    asignacionFL.getCantidadFormularios(), asignacionFL.getCantidadFormulariosResponsable(),
                    asignacionFL.getStockMaximoForm());
            asignacionFL.setCantidadInferiorMaximo(true);
            flujoRetorno = "ASIGNAR_FORMULARIO";
        }

        return flujoRetorno;
    }

    /**
     * Invoca al metodo de negocio que registra el cambio de estado a asignado validando primero que se hayan ingresado los datos requeridos
     * 
     * @return true si se realiza el registro de lo contrario false gestionando el error que se haya presentado
     */
    public boolean registrar() {
        logger.debug("AsignacionesMB::registrar");
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);
        AsignacionHolderFL asignacionHolderFL = findFlowObject(AsignacionHolderFL.class, ASIGNACION_HOLDER_FL);
        boolean registra = false;

        try {
            AsignacionDTO asignacionDTO = new AsignacionDTO();
            asignacionDTO.setCantidadRango(new ArrayList<CantidadRangoDTO>());
            asignacionDTO.setDetalleCambioEstado(new DetalleCambioEstadoDTO());

            for (RangoDisponibleDTO rangoDisponibleDTO : asignacionFL.getRangosDisponibles())
                if (rangoDisponibleDTO.getCantidadIngresada() != null) {
                    if (rangoDisponibleDTO.getCantidadIngresada() > rangoDisponibleDTO.getCantidadDisponible()) {
                        addInfoMessage(NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "titulo_ingresar_asignacion",
                                NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "msg_ingresada_disponible",
                                rangoDisponibleDTO.getCantidadIngresada(), rangoDisponibleDTO.getCantidadDisponible());
                        return registra;
                    }

                    CantidadRangoDTO cantidadRangoDTO = new CantidadRangoDTO();
                    cantidadRangoDTO.setCantidad(rangoDisponibleDTO.getCantidadIngresada());
                    cantidadRangoDTO.setIdRango(rangoDisponibleDTO.getId());
                    asignacionDTO.getCantidadRango().add(cantidadRangoDTO);
                }

            CausalCambioEstadoDTO causaCambioEstDTO = new CausalCambioEstadoDTO(asignacionFL.getIdCausalCambioEstado());
            DocumentoFormularioDTO documentoFormularioDTO = new DocumentoFormularioDTO();
            documentoFormularioDTO.setNumeroDocumento(asignacionFL.getNumDocSoporte());
            EstadoFormularioDTO estadoFormularDTO = new EstadoFormularioDTO(asignacionHolderFL.getIdEstadoFormulario());
            ResponsableFormularioDTO respFormulaDTO = new ResponsableFormularioDTO(asignacionFL.getIdResponsable());
            TipoResponsableFormularioDTO tiReForDTO = new TipoResponsableFormularioDTO(
                    asignacionFL.getIdTipoResponsable());
            respFormulaDTO.setTipoResponsable(tiReForDTO);
            respFormulaDTO.setCorreoResponsableFormulario(asignacionFL.getEmailResponsable());
            if (asignacionFL.getDocumentoAutorizacion() != null) {
                ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(asignacionFL.getDocumentoAutorizacion()
                        .getFileName(), asignacionFL.getDocumentoAutorizacion().getContents());
                asignacionDTO.setArchivoSoporte(archivo);
            }

            asignacionDTO.getDetalleCambioEstado().setCausalCambioEstado(causaCambioEstDTO);
            asignacionDTO.getDetalleCambioEstado().setDocumentoFormulario(documentoFormularioDTO);
            asignacionDTO.getDetalleCambioEstado().setEstadoFormulario(estadoFormularDTO);
            asignacionDTO.getDetalleCambioEstado().setFechaAplicacionEstado(asignacionFL.getFechaAsignacion());
            asignacionDTO.getDetalleCambioEstado().setFolio(asignacionFL.getFolio());
            asignacionDTO.getDetalleCambioEstado().setObservaciones(asignacionFL.getObservaciones());
            asignacionDTO.getDetalleCambioEstado().setResponsableFormulario(respFormulaDTO);

            int cantidadTotalAsignada = formulariosEjb.asignarFormularios(asignacionDTO);

            addInfoMessage(NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "titulo_ingresar_asignacion",
                    NOMBRE_BUNDLE_CAMBIO_ESTADOS_FORM, "msg_confirmacion_asignacion", cantidadTotalAsignada);
            asignacionFL.setCantidadInferiorMinimo(false);
            asignacionFL.setCantidadInferiorMaximo(false);
            registra = true;
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }

        return registra;
    }

    /**
     * Consulta el nombre del tipo de formulario seteado al dto de flujo para hacer la asignacion
     * 
     * @author sergio.torres(17/12/2015) <br/>
     *         cambio para consumir el servicio correcto y no consultar el servicio de consultar un catalogo por cada clase
     * 
     */
    public void consultarTipoFormulario() {
        AsignacionFL asignacionFL = findFlowObject(AsignacionFL.class, AsignacionFL.NOMBRE_BEAN);
        // List<TipoFormularioDTO> resultado = null;
        if (asignacionFL != null) {

            ItemCatalogoDTO tipoFormularioDTO = getCatalogosApp().getItemCatalogoId(EnumCatalogo.TipoFormulario,
                    asignacionFL.getIdTipoFormulario());
            // resultado = administracionFormulariosEjb.consultarTipoFormulario(asignacionFL.getIdTipoFormulario());
            asignacionFL.setNombreTipoFormulario(tipoFormularioDTO.getNombre());
            asignacionFL.setIdTipoFormulario(tipoFormularioDTO.getId());
        }
    }

    /* } NUEVO */

    public boolean cargarDetalleRango() {
        return true;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public PaisDTO getPais() {
        return pais;
    }

    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }

    public String getMsgConfirmaCantidad() {
        return msgConfirmaCantidad;
    }

    public void setMsgConfirmaCantidad(String msgConfirmaCantidad) {
        this.msgConfirmaCantidad = msgConfirmaCantidad;
    }

    public String getExtensionesPermitidas() {
        return extensionesPermitidas;
    }

    public void setExtensionesPermitidas(String extensionesPermitidas) {
        this.extensionesPermitidas = extensionesPermitidas;
    }

}
