package co.com.datatools.c2.managed_bean.comparendo.reporte_inconsistencias;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comparendo.UnificacionInconsistenciasComparendoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;
import co.com.datatools.c2.enumeracion.EnumPais;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumTipoAgenteImpositor;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comparendo.administracion.AdminComparendoHelper;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.fachada.IRFachadaIntegracionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRAgente;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRProcesaComparendo;
import co.com.datatools.c2.negocio.interfaces.IRRecibirComparendo;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicular;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicularEmpresa;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicularPropietario;
import co.com.datatools.c2.negocio.interfaces.administracion.IRInfraccion;
import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.comparendo.ComparendosUtil;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.file.FileValidator;

/**
 * ManagedBean que gestiona las inconsistencias de los comparendos
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class GestionarInconsistenciasComparendoMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(GestionarInconsistenciasComparendoMB.class.getName());

    private final static int NUMERO_COMPLEMENTO_COMPARENDO = 12;
    private final static int NUMERO_COMPLEMENTO_COMPARENDO_ECU = 10;
    private final static int NUMERO_COMPLEMENTO_COMPARENDO_POLCA = 8;

    private static final String NOMBRE_BUNDLE_ADMIN_COMPARENDO = "labelAdminComparendo";

    @EJB
    private IRParametro parametroEjb;
    @EJB
    private IRInfraccion infraccionEjb;
    @EJB
    private IRRegistroVehicular regVehicularEjb;
    @EJB
    private IRRegistroVehicularEmpresa regVehicularEmpresaEjb;
    @EJB
    private IRRegistroVehicularPropietario regVehicularPropietarioEjb;
    @EJB
    private IRPersona personaEjb;
    @EJB
    private IRAgente agenteEjb;
    @EJB
    private IRRecibirComparendo iRRecibirComparendo;
    @EJB
    private IRAdministracion administracionEjb;
    @EJB
    private IRProcesaComparendo iRProcesaComparendo;
    @EJB
    private IRFachadaIntegracionTerceros irFachadaIntegracionTerceros;
    @EJB
    private IRCoactivo irComparendoTercero;

    private int tamanioMaximoArch;
    private int mediaDiasMaximo;
    private int diasMaximo;
    private Date fechaSistema;
    private Date fechaImposicionDiasMaximo;
    private Date fechaRegistroDiasMaximo;

    // Propietario propuesto por la placa del vehiculo
    private PropietarioVehiculoDTO propietario;

    /*
     * comportamiento campos gestionar inconsistencias
     */
    private boolean esConfigCamposEcuador;
    private boolean esConfigCamposColombia;

    /**
     * Atributo que permite identificar que tipos de archivos son permitidos
     */
    public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(tiff|pdf|png|jpg)$/";

    public GestionarInconsistenciasComparendoMB() {
        super();
    }

    @PostConstruct
    public void init() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::init()");

        /*
         * Comportamiento de la instalacion de la aplicacion
         */
        // Colombia
        if (getPais().getId().equals(EnumPais.COLOMBIA.getId())) {
            esConfigCamposEcuador = false;
            esConfigCamposColombia = true;
        }
        // Ecuador
        if (getPais().getId().equals(EnumPais.ECUADOR.getId())) {
            esConfigCamposEcuador = true;
            esConfigCamposColombia = false;
        }
        // Peru
        if (getPais().getId().equals(EnumPais.PERU.getId())) {
            esConfigCamposEcuador = false;
            esConfigCamposColombia = true;
        }

        // Fecha del sistema
        fechaSistema = Calendar.getInstance().getTime();

        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();

        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valorParametroDTO = parametroEjb.consultarParametro(
                EnumParametro.CANT_MEDIA_DIAS_REALIZAR_CONSULTA, getOrganismoTransito().getCodigoOrganismo(), true);
        diasMaximo = valorParametroDTO.getValorParamInt();
        mediaDiasMaximo = diasMaximo - 1;
    }

    /**
     * Metodo que permite calcular el rango de la fecha maxima de consulta imposicion
     * 
     * @author giovanni.velandia
     */
    public void calcularRangoConsultaFechaImposicion() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::calcularRangoConsultaFechaImposicion()");

        GestionarInconsistenciasComparendoHolderFL gestionarInconsistenciasComparendoHolderFL = findFlowObject(
                GestionarInconsistenciasComparendoHolderFL.class,
                GestionarInconsistenciasComparendoHolderFL.NOMBRE_BEAN);

        fechaImposicionDiasMaximo = UtilFecha.sumarDias(gestionarInconsistenciasComparendoHolderFL
                .getConsultaProcesaComparendoDTO().getFechaImposicionInicial(), mediaDiasMaximo);
    }

    /**
     * Metodo que permite calcular el rango de la fecha maxima de consulta registro
     * 
     * @author giovanni.velandia
     */
    public void calcularRangoConsultaFechaRegistro() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::calcularRangoConsultaFechaRegistro()");

        GestionarInconsistenciasComparendoHolderFL gestionarInconsistenciasComparendoHolderFL = findFlowObject(
                GestionarInconsistenciasComparendoHolderFL.class,
                GestionarInconsistenciasComparendoHolderFL.NOMBRE_BEAN);

        fechaRegistroDiasMaximo = UtilFecha.sumarDias(
                gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO().getFechaRegistroInicial(),
                mediaDiasMaximo);
    }

    /**
     * Se encarga de consultar los comparendos inconsistentes
     * 
     * @author giovanni.velandia
     */
    public void consultarProcesaComparendo() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::consultarProcesaComparendo()");
        GestionarInconsistenciasComparendoHolderFL gestionarInconsistenciasComparendoHolderFL = findFlowObject(
                GestionarInconsistenciasComparendoHolderFL.class,
                GestionarInconsistenciasComparendoHolderFL.NOMBRE_BEAN);

        gestionarInconsistenciasComparendoHolderFL.setProcesaComparendoVOs(new ArrayList<ProcesaComparendoVO>(0));

        // Fechas imposicion
        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaImposicionInicial() == null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaImposicionFinal() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_inicial");
            return;
        }

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaImposicionInicial() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaImposicionFinal() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_final");
            return;
        }

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaImposicionInicial() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaImposicionFinal() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaImposicionInicial().after(gestionarInconsistenciasComparendoHolderFL
                                .getConsultaProcesaComparendoDTO().getFechaImposicionFinal())) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_intervalo_fechas_imp");
            return;
        }

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaImposicionInicial() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaImposicionInicial().after(fechaSistema)) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_imp_actual");
            return;
        }

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaImposicionFinal() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaImposicionFinal().after(fechaImposicionDiasMaximo)) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "tit_error_fecha_imp_parametro",
                    NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_imp_parametro", diasMaximo);
            return;
        }

        // Fechas registro
        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaRegistroInicial() == null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaRegistroFinal() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_inicial");
            return;
        }

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaRegistroInicial() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaRegistroFinal() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_final");
            return;
        }

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaRegistroInicial() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaRegistroFinal() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaRegistroInicial().after(gestionarInconsistenciasComparendoHolderFL
                                .getConsultaProcesaComparendoDTO().getFechaRegistroFinal())) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_intervalo_fechas_reg");
            return;
        }

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaRegistroInicial() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                        .getFechaRegistroInicial().after(fechaSistema)) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_reg_actual");
            return;
        }

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO().getFechaRegistroFinal() != null
                && gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO().getFechaRegistroFinal()
                        .after(fechaRegistroDiasMaximo)) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "tit_error_fecha_reg_parametro",
                    NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_reg_parametro", diasMaximo);
            return;
        }

        if (isEsConfigCamposEcuador()) {
            ConsultaProcesaComparendoDTO consultaProcesaComparendoDTO = gestionarInconsistenciasComparendoHolderFL
                    .getConsultaProcesaComparendoDTO();
            consultaProcesaComparendoDTO.setCodigoOrganismo(getCodigoOrganismoTransito());
        }

        // Numero de comparendo
        if (gestionarInconsistenciasComparendoHolderFL.getNumeroComparendo() != null) {
            gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                    .setNumeroComparendo(gestionarInconsistenciasComparendoHolderFL.getNumeroComparendo());
        } else {
            gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO().setNumeroComparendo(null);
        }

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaRegistroFinal() != null) {
            gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                    .setFechaRegistroFinal(UtilFecha.upsetTime(gestionarInconsistenciasComparendoHolderFL
                            .getConsultaProcesaComparendoDTO().getFechaRegistroFinal()).getTime());
        }
        List<UnificacionInconsistenciasComparendoDTO> uniComparendoDTOs = new ArrayList<UnificacionInconsistenciasComparendoDTO>();
        uniComparendoDTOs = iRProcesaComparendo.consularErrorComparendoReporte(
                gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO());

        if (uniComparendoDTOs == null || uniComparendoDTOs.isEmpty()) {
            gestionarInconsistenciasComparendoHolderFL.setProcesaComparendoVOs(new ArrayList<ProcesaComparendoVO>(0));
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            return;
        } else {
            gestionarInconsistenciasComparendoHolderFL.setProcesaComparendoVOs(new ArrayList<ProcesaComparendoVO>(0));
            gestionarInconsistenciasComparendoHolderFL
                    .setProcesaComparendoVOs(toProcesoComparendoVOs(uniComparendoDTOs));
            gestionarInconsistenciasComparendoHolderFL.setProcesaComparendoSeleccionadoVO(null);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(uniComparendoDTOs.size());
        }
    }

    /**
     * Se encarga de convertir una lista de procesaComparendoDTO a VO
     * 
     * @author giovanni.velandia
     * @param procesaComparendoVOs
     */
    private List<ProcesaComparendoVO> toProcesoComparendoVOs(
            List<UnificacionInconsistenciasComparendoDTO> unificacionInconsistenciasComparendoDTOs) {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onCodigoOrganismoChange()");
        List<ProcesaComparendoVO> procesaComparendoVOs = new ArrayList<ProcesaComparendoVO>();
        for (UnificacionInconsistenciasComparendoDTO unificacionInconsistenciasComparendoDTO : unificacionInconsistenciasComparendoDTOs) {
            ProcesaComparendoVO procesaComparendoVO = new ProcesaComparendoVO();
            procesaComparendoVO.setUnificacionInconsistenciasComparendoDTO(unificacionInconsistenciasComparendoDTO);
            procesaComparendoVOs.add(procesaComparendoVO);
        }
        return procesaComparendoVOs;
    }

    /**
     * Organismo de transito cambia
     * 
     * @author julio.pinzon - giovanni.velandia(mod 18-12-2015)
     */
    public void onCodigoOrganismoChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onCodigoOrganismoChange()");

        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        cambiarCodOrganismoNumeroComparendo(gestionarInconsistenciasComparendoFL);

        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getCodigoInfraccion() != null
                && !gestionarInconsistenciasComparendoFL.getProcesaComparendo().getCodigoInfraccion().isEmpty()
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaRecepcion() != null
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo().getOrganismoTransito()
                        .getCodigoOrganismo() != null) {
            cambiarInfraccion();
            RequestContext.getCurrentInstance().update("descripcionInfraccion");
        }
    }

    /**
     * Cambia valor de la infraccion
     */
    private void cambiarInfraccion() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::cambiarInfraccion()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        gestionarInconsistenciasComparendoFL.setDescripcionInfraccion(null);
        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getCodigoInfraccion() != null
                && !gestionarInconsistenciasComparendoFL.getProcesaComparendo().getCodigoInfraccion().isEmpty()
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion() != null
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo().getOrganismoTransito()
                        .getCodigoOrganismo() != null) {
            ConfiguracionInfraccionDTO configuracionInfraccionDTO = infraccionEjb.consultarInfraccion(
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo().getCodigoInfraccion(),
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion());
            if (configuracionInfraccionDTO != null) {
                gestionarInconsistenciasComparendoFL
                        .setDescripcionInfraccion(configuracionInfraccionDTO.getDescripcion());
                gestionarInconsistenciasComparendoFL.setConfiguracionInfraccion(configuracionInfraccionDTO);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_no_encuentra_infraccion");
            }
        }
    }

    /**
     * Cambia valor en codigo de comparendo
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 18-12-2015)
     */
    private void cambiarCodOrganismoNumeroComparendo(
            GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL) {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::cambiarCodOrganismoNumeroComparendo()");
        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoAgenteImpositor()
                .equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
            gestionarInconsistenciasComparendoFL
                    .setCodOrganismoNumeroComparendo(gestionarInconsistenciasComparendoFL.getCodigoPolca());
        } else if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getOrganismoTransito()
                .getCodigoOrganismo().equals(ConstantesComparendo.CODIGO_OTROS)) {
            gestionarInconsistenciasComparendoFL.setCodOrganismoNumeroComparendo("");
        } else {
            gestionarInconsistenciasComparendoFL
                    .setCodOrganismoNumeroComparendo(StringUtils.rightPad(
                            String.valueOf(gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                                    .getOrganismoTransito().getCodigoOrganismo()),
                            NUMERO_COMPLEMENTO_COMPARENDO_POLCA, "0"));
        }
    }

    /**
     * Organismo de transito cambia consulta
     * 
     * @author julio.pinzon // giovanni.velandia(mod 17-12-2015)
     */
    public void onCodigoOrganismoChangeConsulta() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onCodigoOrganismoChangeConsulta()");
        cambiarCodOrganismoNumeroComparendoConsulta();
    }

    /**
     * Valor de Polca cambia consulta, metodo que permite asignar el organismo asociado al numero del comparendo
     * 
     * @author julio.pinzon// giovanni.velandia(mod 17-12-2015)
     */
    public void onPolcaChangeConsulta() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onPolcaChangeConsulta()");
        cambiarCodOrganismoNumeroComparendoConsulta();
    }

    /**
     * Cambia valor en codigo de comparendo consulta
     * 
     * @author julio.pinzon// giovanni.velandia(mod 17-12-2015)
     */
    private void cambiarCodOrganismoNumeroComparendoConsulta() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::cambiarCodOrganismoNumeroComparendoConsulta()");
        GestionarInconsistenciasComparendoHolderFL gestionarInconsistenciasComparendoHolderFL = findFlowObject(
                GestionarInconsistenciasComparendoHolderFL.class,
                GestionarInconsistenciasComparendoHolderFL.NOMBRE_BEAN);

        // Consultar parametros polca
        ValorParametroDTO valor = parametroEjb.consultarParametro(EnumParametro.CODIGO_POLCA,
                getCodigoOrganismoTransito(), true);
        gestionarInconsistenciasComparendoHolderFL.setCodigoPolca(valor.getValorParam());

        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO().getEsPolca()) {
            gestionarInconsistenciasComparendoHolderFL
                    .setCodOrganismoNumeroComparendo(gestionarInconsistenciasComparendoHolderFL.getCodigoPolca());
        } else if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO().getCodigoOrganismo()
                .equals(ConstantesComparendo.CODIGO_OTROS)) {
            gestionarInconsistenciasComparendoHolderFL.setCodOrganismoNumeroComparendo("");
        } else {
            gestionarInconsistenciasComparendoHolderFL
                    .setCodOrganismoNumeroComparendo(
                            StringUtils.rightPad(
                                    String.valueOf(gestionarInconsistenciasComparendoHolderFL
                                            .getConsultaProcesaComparendoDTO().getCodigoOrganismo()),
                                    NUMERO_COMPLEMENTO_COMPARENDO_POLCA, "0"));
        }
    }

    /**
     * Valor de numero comparendo cambia en consulta, metodo que permite rellenar el numero del comparendo
     * 
     * @author julio.pinzon// giovanni.velandia(mod 17-12-2015)
     */
    public void onNumeroComparendoChangeConsulta() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onNumeroComparendoChangeConsulta()");
        GestionarInconsistenciasComparendoHolderFL gestionarInconsistenciasComparendoHolderFL = findFlowObject(
                GestionarInconsistenciasComparendoHolderFL.class,
                GestionarInconsistenciasComparendoHolderFL.NOMBRE_BEAN);

        if (StringUtils.isNotEmpty(gestionarInconsistenciasComparendoHolderFL.getNumeroComparendo())) {
            gestionarInconsistenciasComparendoHolderFL.setNumeroComparendo(StringUtils.leftPad(
                    String.valueOf(gestionarInconsistenciasComparendoHolderFL.getNumeroComparendo()),
                    NUMERO_COMPLEMENTO_COMPARENDO, "0"));
        } else {
            gestionarInconsistenciasComparendoHolderFL.setNumeroComparendo(null);
        }
    }

    /**
     * Valor de numero comparendo cambia en consulta, metodo que permite rellenar el numero del comparendo
     * 
     * @author julio.pinzon// giovanni.velandia(mod 17-12-2015)
     */
    public void onNumeroComparendoChangeConsultaEcuador() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onNumeroComparendoChangeConsultaEcuador()");
        GestionarInconsistenciasComparendoHolderFL gestionarInconsistenciasComparendoHolderFL = findFlowObject(
                GestionarInconsistenciasComparendoHolderFL.class,
                GestionarInconsistenciasComparendoHolderFL.NOMBRE_BEAN);

        if (StringUtils.isNotEmpty(gestionarInconsistenciasComparendoHolderFL.getNumeroComparendo())) {
            gestionarInconsistenciasComparendoHolderFL.setNumeroComparendo(StringUtils.leftPad(
                    String.valueOf(gestionarInconsistenciasComparendoHolderFL.getNumeroComparendo()),
                    NUMERO_COMPLEMENTO_COMPARENDO_ECU, "0"));
        } else {
            gestionarInconsistenciasComparendoHolderFL.setNumeroComparendo(null);
        }
    }

    /**
     * Valor de Polca cambia
     * 
     * @author julio.pinzon
     */
    public void onPolcaChange(GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL) {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onPolcaChange()");
        cambiarCodOrganismoNumeroComparendo(gestionarInconsistenciasComparendoFL);
    }

    /**
     * Valor de numero comparendo cambia
     * 
     * @author julio.pinzon
     */
    public void onNumeroComparendoChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onNumeroComparendoChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        if (StringUtils.isNotEmpty(gestionarInconsistenciasComparendoFL.getNumeroComparendo())) {
            gestionarInconsistenciasComparendoFL.setNumeroComparendo(
                    StringUtils.leftPad(String.valueOf(gestionarInconsistenciasComparendoFL.getNumeroComparendo()),
                            NUMERO_COMPLEMENTO_COMPARENDO, "0"));
        }
    }

    /**
     * Cuando se debe consultar de nuevo la infraccion por cambio en fecha
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 18-12-2015)
     */
    public void onFechaImposicionChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onFechaImposicionChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                .setHoraInfraccion(gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion());
        cambiarInfraccion();
    }

    /**
     * Cuando se debe consultar de nuevo la infraccion por cambio en codigo
     * 
     * @author giovanni.velandia
     */
    public void onCodigoInfraccionChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onCodigoInfraccionChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setCodigoInfraccion(
                gestionarInconsistenciasComparendoFL.getProcesaComparendo().getCodigoInfraccion().toUpperCase());
        gestionarInconsistenciasComparendoFL.setDescripcionInfraccion(null);
        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getCodigoInfraccion() != null
                && !gestionarInconsistenciasComparendoFL.getProcesaComparendo().getCodigoInfraccion().isEmpty()
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaRecepcion() != null
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo().getOrganismoTransito()
                        .getCodigoOrganismo() != null) {
            cambiarInfraccion();
        } else {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_faltan_valores_infraccion");
        }
    }

    /**********************
     * Eventos vehiculo
     **********************/
    /**
     * Cuando se debe consultar los datos del vehiculo
     * 
     * @author julio.pinzon --giovanni.velandia(mod 18-12-2015)
     */
    public void onPlacaChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onPlacaChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);

        // reinicia valores
        reiniciarDatosVehiculo();

        if (StringUtils.isNotBlank(gestionarInconsistenciasComparendoFL.getProcesaComparendo().getPlacaVehiculo())) {
            // Reinicia datos del propietario
            gestionarInconsistenciasComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
            gestionarInconsistenciasComparendoFL.getPropietario()
                    .setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());

            gestionarInconsistenciasComparendoFL.setPlacaVehiculoDeshabilitado(false);
            gestionarInconsistenciasComparendoFL.setIdVehiculoDeshabilitado(true);
            gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdentificacionVehiculo(null);
            /*
             * Consultar la información existente por la 'Placa vehículo' por medio del caso de uso 'Consultar información de registro automotor' y
             * proponerla en los campos que se listan en esta sección ('VEHICULO')
             */
            VehiculoDTO vehiculo = regVehicularEjb
                    .consultarVehiculo(gestionarInconsistenciasComparendoFL.getProcesaComparendo().getPlacaVehiculo());
            if (vehiculo != null) {
                gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .setCodigoOrganismoMatriculaVehiculo(vehiculo.getOrganismoTransito().getCodigoOrganismo());
                gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .setNumeroLicenciaTransito(vehiculo.getLicenciaTransito());
                gestionarInconsistenciasComparendoFL.getProcesaComparendo().setNumeroMotor(vehiculo.getNumeroMotor());
                if (vehiculo.getClaseVehiculo() != null) {
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                            .setIdClaseVehiculo(vehiculo.getClaseVehiculo().getId());
                }
                if (vehiculo.getModalidad() != null) {
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                            .setIdModalidad(vehiculo.getModalidad().getId());
                }
                if (vehiculo.getRadioAccion() != null) {
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                            .setIdRadioAccion(vehiculo.getRadioAccion().getId());
                }
                if (vehiculo.getNumeroTarjetaOpera() != null) {
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                            .setNumeroTarjetaOperacion(vehiculo.getNumeroTarjetaOpera().toString());
                }
                if (vehiculo.getTipoServicio() != null) {
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                            .setIdTipoServicio(vehiculo.getTipoServicio().getId());
                }
                if (vehiculo.getColor() != null) {
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdColor(vehiculo.getColor().getId());
                }
                if (vehiculo.getLinea() != null) {
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                            .setIdLineaVehiculo(vehiculo.getLinea().getId());
                }
                if (vehiculo.getModelo() != null) {
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                            .setModelo(vehiculo.getModelo().toString());
                }

                /*
                 * Consultar la información existente por la 'Placa vehículo' por medio del caso de uso 'Consultar información de registro de empresas
                 * de transporte' donde la 'Fecha de imposición de comparendo' se encuentre entre en un intervalo 'Fecha inicial de vigencia de la
                 * empresa' y 'Fecha final de vigencia de la empresa' y proponerla en los campos que se listan en esta sección
                 */
                EmpresaVehiculoDTO empresa = regVehicularEmpresaEjb.consultarEmpresa(
                        gestionarInconsistenciasComparendoFL.getProcesaComparendo().getPlacaVehiculo(),
                        gestionarInconsistenciasComparendoFL.getProcesaComparendo().getOrganismoTransito()
                                .getCodigoOrganismo(),
                        gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion());
                if (empresa != null
                        && gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion()
                                .after(empresa.getFechaInicio())
                        && (empresa.getFechaFin() == null || gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                                .getFechaInfraccion().before(empresa.getFechaFin()))) {
                    if (empresa.getPersonaJuridica() != null) {
                        gestionarInconsistenciasComparendoFL.setEmpresaVehiculo(
                                AdminComparendoHelper.toProcesaComparendoPersonaDTO(empresa.getPersonaJuridica(),
                                        gestionarInconsistenciasComparendoFL.getEmpresaVehiculo()));
                        gestionarInconsistenciasComparendoFL.setTipoDocEmpresaRequerido(true);
                    }
                }

                /*
                 * Consultar la información existente por la 'Placa vehículo' por medio del caso de uso 'Consultar información de registro de
                 * propietarios' donde la 'Fecha de imposición de comparendo' se encuentre entre en un intervalo 'Fecha inicio de propiedad' y 'Fecha
                 * fin de propiedad',
                 */
                ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO = new ConsultaRegistroVehicularDTO();
                consultaRegistroVehicularDTO.setCodigoOrganismo(gestionarInconsistenciasComparendoFL
                        .getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());
                consultaRegistroVehicularDTO.setFechaConsulta(
                        gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion());
                consultaRegistroVehicularDTO
                        .setPlaca(gestionarInconsistenciasComparendoFL.getProcesaComparendo().getPlacaVehiculo());
                propietario = regVehicularPropietarioEjb.consultarMayorPropietario(consultaRegistroVehicularDTO);
                if (propietario != null
                        && gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion()
                                .after(propietario.getFechaInicio())
                        && (propietario.getFechaFin() == null || gestionarInconsistenciasComparendoFL
                                .getProcesaComparendo().getFechaInfraccion().before(propietario.getFechaFin()))) {
                    gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(false);
                    if (propietario.getPersona() != null) {
                        gestionarInconsistenciasComparendoFL.setPropietario(
                                AdminComparendoHelper.toProcesaComparendoPersonaDTO(propietario.getPersona(),
                                        gestionarInconsistenciasComparendoFL.getPropietario()));

                        gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(
                                gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion().equals(
                                        gestionarInconsistenciasComparendoFL.getTipoIdentificacionEmpresa().getId()));
                    }

                }
            }
        }
    }

    /**
     * Reinicia los datos de la pestania de vehiculos
     * 
     * @author julio.pinzon
     */
    private void reiniciarDatosVehiculo() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::reiniciarDatosVehiculo()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);

        // reinicia valores
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setCodigoOrganismoMatriculaVehiculo(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdClaseVehiculo(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdModalidad(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdRadioAccion(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setNumeroTarjetaOperacion(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdTipoServicio(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdColor(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setNumeroLicenciaTransito(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdLineaVehiculo(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setNumeroMotor(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setModelo(null);

        gestionarInconsistenciasComparendoFL.getEmpresaVehiculo().setNumeroIdentificacion(null);
        gestionarInconsistenciasComparendoFL.getEmpresaVehiculo().setIdTipoIdentificacion(null);
        gestionarInconsistenciasComparendoFL.getEmpresaVehiculo().setRazonSocial(null);

        gestionarInconsistenciasComparendoFL.setPlacaVehiculoDeshabilitado(false);
        gestionarInconsistenciasComparendoFL.setIdVehiculoDeshabilitado(false);
    }

    /**
     * Cuando se cambia el numero de identificacion
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 18-12-2015)
     */
    public void onIdentificacionVehiculoChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onIdentificacionVehiculoChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);

        gestionarInconsistenciasComparendoFL.setPlacaVehiculoDeshabilitado(false);
        gestionarInconsistenciasComparendoFL.setIdVehiculoDeshabilitado(false);

        if (StringUtils
                .isNotBlank(gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdentificacionVehiculo())) {
            gestionarInconsistenciasComparendoFL.setPlacaVehiculoDeshabilitado(true);
            gestionarInconsistenciasComparendoFL.setIdVehiculoDeshabilitado(false);
            gestionarInconsistenciasComparendoFL.getProcesaComparendo().setPlacaVehiculo(null);
        }
    }

    /**
     * Cuando se cambia el tipo de documento de la empresa
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 18-12-2015)
     */
    public void onTipoDocumentoEmpresaChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onTipoDocumentoEmpresaChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        if (gestionarInconsistenciasComparendoFL.getEmpresaVehiculo().getIdTipoIdentificacion() != null || StringUtils
                .isNotEmpty(gestionarInconsistenciasComparendoFL.getEmpresaVehiculo().getNumeroIdentificacion())) {
            gestionarInconsistenciasComparendoFL.setTipoDocEmpresaRequerido(true);
        } else {
            gestionarInconsistenciasComparendoFL.setTipoDocEmpresaRequerido(false);
        }
    }

    /**********************
     * Eventos infractor
     **********************/
    /**
     * Cuando se cambia el numero de documento del infractor
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 21-12-2015)
     */
    public void onTipoDocumentoInfractorChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onTipoDocumentoInfractorChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        consultarPersona(gestionarInconsistenciasComparendoFL.getInfractor(), gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        // Validacion
        gestionarInconsistenciasComparendoFL.setTipoIdJuridicoInfractor(false);
        if (gestionarInconsistenciasComparendoFL.getInfractor().getIdTipoIdentificacion() != null) {

            // Si es de tipo juridico para que solo muestre la razon social
            if (gestionarInconsistenciasComparendoFL.getInfractor().getIdTipoIdentificacion()
                    .equals(gestionarInconsistenciasComparendoFL.getTipoIdentificacionEmpresa().getId())) {
                gestionarInconsistenciasComparendoFL.setTipoIdJuridicoInfractor(true);
            }
        }

        if (gestionarInconsistenciasComparendoFL.getInfractor().getIdTipoIdentificacion() == null
                && gestionarInconsistenciasComparendoFL.getInfractor().getNumeroIdentificacion() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_infractor");
            return;
        }

        if (gestionarInconsistenciasComparendoFL.getInfractor().getIdTipoIdentificacion() != null
                && gestionarInconsistenciasComparendoFL.getInfractor().getNumeroIdentificacion() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_infractor");
            return;
        }
    }

    /**
     * Consultar persona
     * 
     * @param personaComparendo
     * @param codigoOrganismo
     * @author julio.pinzon -- giovanni.velandia(mod 21-12-2015)
     */
    private void consultarPersona(ProcesaComparendoPersonaDTO personaComparendo, Integer codigoOrganismo) {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::consultarPersona(ProcesaComparendoPersonaDTO)");
        if (StringUtils.isNotEmpty(personaComparendo.getNumeroIdentificacion())
                && personaComparendo.getIdTipoIdentificacion() != null) {
            PersonaDTO persona = null;
            try {
                persona = personaEjb.consultarPersonaConPrioridad(codigoOrganismo,
                        personaComparendo.getIdTipoIdentificacion(), personaComparendo.getNumeroIdentificacion());
            } catch (CirculemosNegocioException e) {
                LOGGER.error("Error al consultar persona (" + codigoOrganismo + ","
                        + personaComparendo.getIdTipoIdentificacion() + ","
                        + personaComparendo.getNumeroIdentificacion() + ")");
            }
            if (persona != null) {
                personaComparendo = AdminComparendoHelper.toProcesaComparendoPersonaDTO(persona, personaComparendo);
            }
        }
    }

    /**
     * Cuando se cambia el tipo de documento del infractor
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 21-12-2015)
     */
    public void onNumDocInfractorChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onNumDocInfractorChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        consultarPersona(gestionarInconsistenciasComparendoFL.getInfractor(), gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        if (gestionarInconsistenciasComparendoFL.getInfractor().getIdTipoIdentificacion() == null
                && gestionarInconsistenciasComparendoFL.getInfractor().getNumeroIdentificacion() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_infractor");
            return;
        }

        if (gestionarInconsistenciasComparendoFL.getInfractor().getIdTipoIdentificacion() != null
                && gestionarInconsistenciasComparendoFL.getInfractor().getNumeroIdentificacion() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_infractor");
            return;
        }
    }

    /**
     * Cuando se cambia el numero de documento del propietario
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 21-12-2015)
     */
    public void onTipoDocumentoPropietarioChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onTipoDocumentoPropietarioChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        consultarPersona(gestionarInconsistenciasComparendoFL.getPropietario(), gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(false);
        if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion() != null) {

            // Si es de tipo juridico para que solo muestre la razon social
            if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion()
                    .equals(gestionarInconsistenciasComparendoFL.getTipoIdentificacionEmpresa().getId())) {
                gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(true);
            }
        }

        if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion() == null
                && gestionarInconsistenciasComparendoFL.getPropietario().getNumeroIdentificacion() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_propietario");
            return;
        }

        if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion() != null
                && gestionarInconsistenciasComparendoFL.getPropietario().getNumeroIdentificacion() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_propietario");
            return;
        }
    }

    /**
     * Cuando se cambia el tipo de documento del propietario
     * 
     * @author giovanni.velandia
     */
    public void onNumDocPropietarioChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onNumDocPropietarioChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        consultarPersona(gestionarInconsistenciasComparendoFL.getPropietario(), gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion() == null
                && gestionarInconsistenciasComparendoFL.getPropietario().getNumeroIdentificacion() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_propietario");
            return;
        }

        if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion() != null
                && gestionarInconsistenciasComparendoFL.getPropietario().getNumeroIdentificacion() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_propietario");
            return;
        }

    }

    /**
     * Cuando se cambia la placa del agente transito
     * 
     * @author giovanni.velandia
     */
    public void onPlacaAgenteChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onPlacaAgenteChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);

        // reinicia datos
        reiniciarDatosAgente();

        // Se debe validar que no este diligenciado el numero de indentificacion de la persona ya que es excluyente del numero de la placa
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setNumeroIdentificacionAgente(null);

        consultarAgente(gestionarInconsistenciasComparendoFL);
    }

    /**
     * Reinicia los datos de la pestania de vehiculos
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 21-12-2015)
     */
    private void reiniciarDatosAgente() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::reiniciarDatosVehiculo()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);

        // reinicia valores
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdAgenteTransito(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setNombre1Agente(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setNombre2Agente(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setApellido1Agente(null);
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setApellido2Agente(null);

    }

    /**
     * Consulta el agente
     * 
     * @param adminComparendoFL
     * @author giovanni.velandia (mod 21-12-2015)
     */
    private void consultarAgente(GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL) {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::consultarAgente(AdminComparendoFL)");
        if (StringUtils.isNotEmpty(gestionarInconsistenciasComparendoFL.getProcesaComparendo().getPlacaAgente())
                || (StringUtils
                        .isNotEmpty(gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                                .getNumeroIdentificacionAgente())
                        && gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                                .getIdTipoIdentificacionAgente() != null)) {

            AgenteDTO agente = new AgenteDTO();
            if (StringUtils.isNotEmpty(gestionarInconsistenciasComparendoFL.getProcesaComparendo().getPlacaAgente())) {
                agente.setPlaca(gestionarInconsistenciasComparendoFL.getProcesaComparendo().getPlacaAgente());
            }

            if (StringUtils.isNotEmpty(
                    gestionarInconsistenciasComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente())
                    && gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                            .getIdTipoIdentificacionAgente() != null) {
                // Consultar Persona
                PersonaDTO personaAgente = new PersonaDTO();
                personaAgente.setNumeroIdentificacion(
                        gestionarInconsistenciasComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente());
                personaAgente.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
                personaAgente.getTipoIdentificacion().setId(
                        gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente());
                agente.setPersona(personaAgente);
            }
            // Consulta agente
            agente = agenteEjb.consultarAgente(agente).get(0);

            // donde la 'Fecha de imposición comparendo' se encuentre entre la 'Fecha inicial de vigencia' y la 'fecha final de vigencia' del agente a
            // consultar y proponerla en los campos que se listan en esta sección ('AGENTE DE TRÁNSITO'). (FA9)
            if (agente == null || (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion()
                    .before(agente.getFechaInicioVigencia())
                    || (agente.getFechaFinVigencia() != null && gestionarInconsistenciasComparendoFL
                            .getProcesaComparendo().getFechaInfraccion().after(agente.getFechaFinVigencia())))) {
                if (!gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoAgenteImpositor()
                        .equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
                    // FA9
                    addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_debe_existir_agente");
                }
            } else {
                // Datos del agente
                gestionarInconsistenciasComparendoFL.getProcesaComparendo().setIdAgenteTransito(agente.getId());
                gestionarInconsistenciasComparendoFL.getProcesaComparendo().setPlacaAgente(agente.getPlaca());
                gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .setIdTipoIdentificacionAgente(agente.getPersona().getTipoIdentificacion().getId());
                gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .setNumeroIdentificacionAgente(agente.getPersona().getNumeroIdentificacion());
                gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .setNombre1Agente(agente.getPersona().getNombre1());
                gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .setNombre2Agente(agente.getPersona().getNombre2());
                gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .setApellido1Agente(agente.getPersona().getApellido1());
                gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .setApellido2Agente(agente.getPersona().getApellido2());
            }
        }
    }

    /**********************
     * Eventos agente_transito
     **********************/
    /**
     * Cuando se cambia el numero de documento del agente transito
     */
    public void onTipoDocumentoAgenteTransitoChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onTipoDocumentoAgenteTransitoChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        // reinicia datos
        reiniciarDatosAgente();

        // Se debe validar que no este diligenciado la placa ya que es excluyente del numero de identificacion de la persona
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setPlacaAgente(null);

        // Consulta datos agente
        consultarAgente(gestionarInconsistenciasComparendoFL);

        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() == null
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .getNumeroIdentificacionAgente() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_agente");
            return;
        }

        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() != null
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .getNumeroIdentificacionAgente() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_agente");
            return;
        }
    }

    /**
     * Cuando se cambia el tipo de documento del agente transito
     */
    public void onNumDocAgenteTransitoChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onNumDocAgenteTransitoChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);

        // reinicia datos
        reiniciarDatosAgente();

        // Se debe validar que no este diligenciado la placa ya que es excluyente del numero de identificacion de la persona
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setPlacaAgente(null);

        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_agente");
            return;
        }

        // Consulta datos agente
        consultarAgente(gestionarInconsistenciasComparendoFL);

        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() == null
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .getNumeroIdentificacionAgente() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_agente");
            return;
        }

        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() != null
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .getNumeroIdentificacionAgente() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_agente");
            return;
        }
    }

    /**
     * Cuando se cambia el tipo de documento del testigo
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 21-12-2015)
     */
    public void onNumDocTestigoChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onNumDocTestigoChange()");

        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        consultarPersona(gestionarInconsistenciasComparendoFL.getTestigo(), gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        gestionarInconsistenciasComparendoFL.setRequiereTestigo(false);
        if (StringUtils.isNotEmpty(gestionarInconsistenciasComparendoFL.getTestigo().getNumeroIdentificacion())) {
            gestionarInconsistenciasComparendoFL.setRequiereTestigo(true);
        }

        // Validacion
        if (gestionarInconsistenciasComparendoFL.getTestigo().getIdTipoIdentificacion() == null
                && gestionarInconsistenciasComparendoFL.getTestigo().getNumeroIdentificacion() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_testigo");
            return;
        }

        if (gestionarInconsistenciasComparendoFL.getTestigo().getIdTipoIdentificacion() != null
                && gestionarInconsistenciasComparendoFL.getTestigo().getNumeroIdentificacion() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_testigo");
            return;
        }
    }

    /**********************
     * Eventos testigo
     **********************/
    /**
     * Cuando se cambia el numero de documento del testigo
     * 
     * @author julio.pinzon -- giovanni.velandia(mod 21-12-2015)
     */
    public void onTipoDocumentoTestigoChange() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::onTipoDocumentoTestigoChange()");

        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        consultarPersona(gestionarInconsistenciasComparendoFL.getTestigo(), gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        gestionarInconsistenciasComparendoFL.setRequiereTestigo(false);
        if (gestionarInconsistenciasComparendoFL.getTestigo().getIdTipoIdentificacion() != null) {
            gestionarInconsistenciasComparendoFL.setRequiereTestigo(true);
        }

        // Validacion
        if (gestionarInconsistenciasComparendoFL.getTestigo().getIdTipoIdentificacion() == null
                && gestionarInconsistenciasComparendoFL.getTestigo().getNumeroIdentificacion() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_testigo");
            return;
        }

        if (gestionarInconsistenciasComparendoFL.getTestigo().getIdTipoIdentificacion() != null
                && gestionarInconsistenciasComparendoFL.getTestigo().getNumeroIdentificacion() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_testigo");
            return;
        }
    }

    /**
     * Permite eliminar un documento de las evidencias.
     * 
     * @author luis.forero(2015-10-14) -- giovanni.velandia(mod 21-12-2015)
     * 
     */
    public void eliminarListaDocumentos() {
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        gestionarInconsistenciasComparendoFL.getProcesaEvidencias()
                .remove(gestionarInconsistenciasComparendoFL.getEvidenciaSeleccionada());
    }

    /**
     * Permite cargar un archivo al listado de anexos de la solicitud
     * 
     * @param event
     * @author luis.forero(2015-10-14)
     */
    public void cargaArchivoEvidencia(FileUploadEvent event) {
        LOGGER.debug(GestionarInconsistenciasComparendoMB.class.getName().concat("::cargaArchivoEvidencia()"));
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);

        final UploadedFile file = event.getFile();

        try {
            if (FileValidator.checkFileType(file.getInputstream(), file.getFileName(), ".tiff", ".pdf", ".png",
                    ".jpg")) {

                final ProcesaEvidenciaDTO procesaEvidenciaDTO = new ProcesaEvidenciaDTO();

                Integer idTipoEvidencia = gestionarInconsistenciasComparendoFL.getIdTipoEvidencia();
                if (idTipoEvidencia == null) {
                    getFacesContext().addMessage("form-ingreso-detalle-comparendo:selTipoEvidencia", new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                    return;
                }
                procesaEvidenciaDTO.setCodigoTipoEvidencia(idTipoEvidencia);
                procesaEvidenciaDTO.setNombreTipoEvidencia(
                        getCatalogosApp().getItemCatalogo(EnumCatalogo.TipoEvidencia, idTipoEvidencia).getLabel());

                List<ProcesaEvidenciaDTO> lstAnexoSolicitud = gestionarInconsistenciasComparendoFL
                        .getProcesaEvidencias();
                if (lstAnexoSolicitud == null) {
                    lstAnexoSolicitud = new ArrayList<>();
                }

                final ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(file.getFileName(),
                        file.getContents());

                procesaEvidenciaDTO.setArchivoTransportable(archivo);
                lstAnexoSolicitud.add(procesaEvidenciaDTO);

                gestionarInconsistenciasComparendoFL.setProcesaEvidencias(lstAnexoSolicitud);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_dato");
            }
        } catch (IOException e) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_dato");
        }

    }

    /**
     * Se carga el procesa comparendo para ser editado
     * 
     * @author giovanni.velandia
     */
    public void cargarProcesaComparendo() {
        LOGGER.debug(GestionarInconsistenciasComparendoMB.class.getName().concat("::cargaArchivoEvidencia()"));
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        GestionarInconsistenciasComparendoHolderFL gestionarInconsistenciasComparendoHolderFL = findFlowObject(
                GestionarInconsistenciasComparendoHolderFL.class,
                GestionarInconsistenciasComparendoHolderFL.NOMBRE_BEAN);
        gestionarInconsistenciasComparendoFL.setProcesaComparendo(gestionarInconsistenciasComparendoHolderFL
                .getProcesaComparendoSeleccionadoVO().getProcesaComparendoDTO());
        gestionarInconsistenciasComparendoFL.setExisteError(false);

        // Tipo de identificacion para empresa
        gestionarInconsistenciasComparendoFL.setTipoIdentificacionEmpresa(
                administracionEjb.obtenerTipoIdentificacionEmpresaJuridicaPais(getPais().getId()));

        // Consultar parametros polca
        ValorParametroDTO valor = parametroEjb.consultarParametro(EnumParametro.CODIGO_POLCA,
                getCodigoOrganismoTransito(), true);
        gestionarInconsistenciasComparendoFL.setCodigoPolca(valor.getValorParam());
        valor = parametroEjb.consultarParametro(EnumParametro.NOMBRE_POLCA, getCodigoOrganismoTransito(), true);
        gestionarInconsistenciasComparendoFL.setNombreEntidadPolca(valor.getValorParam());

        onCodigoOrganismoChange();
        gestionarInconsistenciasComparendoFL.setNumeroComparendo(gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getNumeroComparendo().substring(NUMERO_COMPLEMENTO_COMPARENDO_POLCA,
                        gestionarInconsistenciasComparendoFL.getProcesaComparendo().getNumeroComparendo().length()));
        onNumeroComparendoChange();

        // Direccion
        DireccionDTO direccionInfraccion = AdminComparendoHelper.toDireccionDTO(
                gestionarInconsistenciasComparendoFL.getProcesaComparendo().getProcesaDireccionComparendo());

        // Direccion infractor
        gestionarInconsistenciasComparendoFL.setDireccionInfraccion(direccionInfraccion);
        gestionarInconsistenciasComparendoFL.setDireccionInfraccionTexto(
                direccionInfraccion.toString() + " " + direccionInfraccion.getComplemento());

        // Procesa Comparendo persona
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getProcesaComparendoPersonas()) {

            // Infractor
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {

                // Confirmar que tipo de persona es para poder pintar los datos
                if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null) {
                    if (procesaComparendoPersonaDTO.getIdTipoIdentificacion()
                            .equals(gestionarInconsistenciasComparendoFL.getTipoIdentificacionEmpresa().getId())) {
                        gestionarInconsistenciasComparendoFL.setTipoIdJuridicoInfractor(true);
                    }
                }

                // Direccion
                if (procesaComparendoPersonaDTO.getProcesaDireccion() != null) {
                    DireccionDTO direccionInfractor = AdminComparendoHelper
                            .toDireccionDTO(procesaComparendoPersonaDTO.getProcesaDireccion());
                    gestionarInconsistenciasComparendoFL.setDireccionInfractorTexto(
                            direccionInfractor.toString() + " " + direccionInfractor.getComplemento());
                }
                gestionarInconsistenciasComparendoFL.setInfractor(procesaComparendoPersonaDTO);
                continue;
            }

            // Propietario
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.PROPIETARIO.getCodigo())) {

                // Confirmar que tipo de persona es para poder pintar los datos
                if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null) {
                    if (procesaComparendoPersonaDTO.getIdTipoIdentificacion()
                            .equals(gestionarInconsistenciasComparendoFL.getTipoIdentificacionEmpresa().getId())) {
                        gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(true);
                    }
                }

                gestionarInconsistenciasComparendoFL.setPropietario(procesaComparendoPersonaDTO);
                continue;
            }

            // Empresa transporte
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getCodigo())) {
                gestionarInconsistenciasComparendoFL.setEmpresaVehiculo(procesaComparendoPersonaDTO);
                continue;
            }

            // Testigo
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.TESTIGO.getCodigo())) {
                gestionarInconsistenciasComparendoFL.setTestigo(procesaComparendoPersonaDTO);
                continue;
            }
        }

        // Cargar los errores
        gestionarInconsistenciasComparendoFL.setDetalleProcesamientos(new ArrayList<DetalleProcesamientoDTO>());
        for (DetalleProcesamientoDTO detalleProcesamientoDTO : gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getDetalleProcesamientos()) {
            gestionarInconsistenciasComparendoFL.getDetalleProcesamientos().add(detalleProcesamientoDTO);
        }

        // Fecha de registro de las inconsistencias
        gestionarInconsistenciasComparendoFL.getProcesaComparendo().setFechaRecepcion(Calendar.getInstance().getTime());

        // Fecha de infraccion
        gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                .setFechaInfraccion(UtilFecha.setHoraFecha(
                        gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion(),
                        gestionarInconsistenciasComparendoFL.getProcesaComparendo().getHoraInfraccion()));
    }

    /**
     * Registra las correciones a las inconsistencias comparendo
     * 
     * @author giovanni.velandia
     */
    public boolean registrarCorreccionInconsistenciasComparendo() {
        LOGGER.debug(GestionarInconsistenciasComparendoMB.class.getName()
                .concat("::registrarCorreccionInconsistenciasComparendo()"));
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        gestionarInconsistenciasComparendoFL.setDetallesProcesamiento(new ArrayList<String>());

        // Completamos la informacion de las personas
        completarInformacionPersonas();

        ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();
        procesarComparendoDTO.setProcesaComparendoDTO(gestionarInconsistenciasComparendoFL.getProcesaComparendo());

        // Procesamiento
        procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.CORREGIR_INCONSISTENCIA);
        RespuestaValidacionDTO respuestaValidacion = null;
        procesarComparendoDTO.getProcesaComparendoDTO()
                .setDetalleProcesamientos(new ArrayList<DetalleProcesamientoDTO>());
        // ingresamos el numero del comparendo
        procesarComparendoDTO.getProcesaComparendoDTO()
                .setNumeroComparendo(gestionarInconsistenciasComparendoFL.getCodOrganismoNumeroComparendo()
                        + gestionarInconsistenciasComparendoFL.getNumeroComparendo());

        try {
            respuestaValidacion = iRRecibirComparendo.recibirComparendo(procesarComparendoDTO);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        // Validar respuesta
        if (respuestaValidacion != null) {

            // Se realiza la validacion de errores por pestaña para mostrarle al ususario
            gestionarInconsistenciasComparendoFL
                    .setRespuestaValidacionVO(ComparendosUtil.validarProcesaComparendo(respuestaValidacion));

            // Validar respuestas de errores
            if (respuestaValidacion.getCodigoResultado().equals(EnumErrorProcesamiento.RECHAZADO.getCodigo())) {
                // Error bloqueante
                LOGGER.info("Error en procesar: " + respuestaValidacion.getCodigoResultado());

                getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", respuestaValidacion
                        .getDetalleProcesamientoDTOs().get(0).getErrorProcesamiento().getDescripcion()));
                return false;
            } else if (respuestaValidacion.getCodigoResultado()
                    .equals(EnumErrorProcesamiento.INCONSISTENTE.getCodigo())) {
                // Errores no bloqueantes
                gestionarInconsistenciasComparendoFL.setExisteError(true);
                gestionarInconsistenciasComparendoFL.setErrorProcesamiento(
                        getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("msg_recibido_sin_procesar"));
                for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacion
                        .getDetalleProcesamientoDTOs()) {
                    gestionarInconsistenciasComparendoFL.getDetallesProcesamiento()
                            .add(detalleProcesamientoDTO.getErrorProcesamiento().getCodigo() + " - "
                                    + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
                    LOGGER.info(
                            "Error en procesar: " + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
                }
                return false;
            } else if (respuestaValidacion.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO.getCodigo())) {
                if (respuestaValidacion.isExisteAlerta()) {
                    for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacion
                            .getDetalleProcesamientoDTOs()) {
                        // Error al guardar documento en el repositorio
                        addWarningMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO,
                                detalleProcesamientoDTO.getErrorProcesamiento().getCodigo());
                        LOGGER.info(
                                "Alerta en procesar: " + detalleProcesamientoDTO.getErrorProcesamiento().getCodigo());
                    }
                }
                // Mensaje de guardado exitoso
                addInfoMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_satisfactorio");
            }
        }
        return true;
    }

    /**
     * Ingresa la informacion de las personas si fueron actulizadas
     * 
     * @author giovanni.velandia
     */
    private void completarInformacionPersonas() {
        LOGGER.debug(GestionarInconsistenciasComparendoMB.class.getName().concat("::completarInformacionPersonas()"));
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        // Contador general posiciones
        int posicionGeneral = 0;
        // Contador para saber la posicion del propietario
        int posicionPropietario = 0;
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : gestionarInconsistenciasComparendoFL
                .getProcesaComparendo().getProcesaComparendoPersonas()) {
            posicionGeneral++;
            // Propietario
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.PROPIETARIO.getCodigo())) {
                posicionPropietario = posicionGeneral - 1;
            }
        }

        // Ingresamos el objeto porpietario
        if (gestionarInconsistenciasComparendoFL.getPropietario().getNumeroIdentificacion() != null) {
            gestionarInconsistenciasComparendoFL.getProcesaComparendo().getProcesaComparendoPersonas()
                    .set(posicionPropietario, gestionarInconsistenciasComparendoFL.getPropietario());
        }
    }

    /**
     * Se encarga de cerrar el popup de errores del com_002
     * 
     * @author Giovanni.Velandia
     */
    public void cerrarErroresProcesaComparendo() {
        LOGGER.debug(GestionarInconsistenciasComparendoMB.class.getName().concat("::cerrarErroresProcesaComparendo()"));
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        gestionarInconsistenciasComparendoFL.setExisteError(false);
    }

    /**
     * Metodo complementario para el uso de envio por correo o descarga de archivo xls
     * 
     * @author Giovanni.Velandia
     * 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ContenidoReporte procesarContenidoReporte() {
        LOGGER.debug(GestionarInconsistenciasComparendoMB.class.getName().concat("::procesarContenidoReporte()"));

        GestionarInconsistenciasComparendoHolderFL gestionarInconsistenciasComparendoHolderFL = findFlowObject(
                GestionarInconsistenciasComparendoHolderFL.class,
                GestionarInconsistenciasComparendoHolderFL.NOMBRE_BEAN);

        ContenidoReporte contenido = new ContenidoReporte();
        List<Object> listaContenido = new ArrayList<Object>();
        List<Object> registros;

        for (ProcesaComparendoVO procesaComparendoVO : gestionarInconsistenciasComparendoHolderFL
                .getProcesaComparendoVOs()) {
            registros = new ArrayList<Object>();

            // Numero comparendo
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getNumeroComparendo() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getNumeroComparendo());
            } else {
                registros.add(null);
            }
            // Nombre de origen del comparendo
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getNombreOrigen() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getNombreOrigen());
            } else {
                registros.add(null);
            }
            // Codigo de infraccion
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getCodigoInfraccion() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getCodigoInfraccion());
            } else {
                registros.add(null);
            }
            // Fecha de registro
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getFechaRegistro() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getFechaRegistro());
            } else {
                registros.add(null);
            }
            // Fecha y hora de imposicion comparendo
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getFechaInfraccion() != null
                    && procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getHoraInfraccion() != null) {
                registros.add(
                        procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getFechaInfraccion().toString()
                                + " " + procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO()
                                        .getHoraInfraccion().toString());
            } else {
                registros.add(null);
            }
            // Nombre de campo con inconsistencia
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO()
                    .getNombreCampoInconsistencias() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO()
                        .getNombreCampoInconsistencias());
            } else {
                registros.add(null);
            }
            // Error de procesamiento
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getErrorProcesamiento() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getErrorProcesamiento());
            } else {
                registros.add(null);
            }
            // Placa vehiculo
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getPlacaVehiculo() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getPlacaVehiculo());
            } else {
                registros.add(null);
            }

            // Infractor
            // Tipo de documento del infractor
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO()
                    .getNombreTipoIdentificacionInfractor() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO()
                        .getNombreTipoIdentificacionInfractor());
            } else {
                registros.add(null);
            }

            // Numero documento del infractor
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO()
                    .getNumeroIdentificacionInfractor() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO()
                        .getNumeroIdentificacionInfractor());
            } else {
                registros.add(null);
            }
            // Nombre 1 del infractor
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getNombre1Infractor() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getNombre1Infractor());
            } else {
                registros.add(null);
            }
            // Apellido 1 del infractor
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getApellido1Infractor() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getApellido1Infractor());
            } else {
                registros.add(null);
            }

            // Usuario
            if (procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getLoginUsuario() != null) {
                registros.add(procesaComparendoVO.getUnificacionInconsistenciasComparendoDTO().getLoginUsuario());
            } else {
                registros.add(null);
            }
            listaContenido.add(registros);

        }
        contenido.setContenido(listaContenido);

        super.obtenerEncabezadoReporte(contenido);

        List listFiltros = new ArrayList<>();

        listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("label_organismo_transito"),
                gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO().getCodigoOrganismo()));

        // Fecha inicial imosicion
        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaImposicionInicial() != null) {
            listFiltros.add(
                    Arrays.asList(getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("label_fecha_imposicion_ini"),
                            gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                                    .getFechaImposicionInicial()));
        }
        // Fecha final imosicion
        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaImposicionFinal() != null) {
            listFiltros.add(
                    Arrays.asList(getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("label_fecha_imposicion_fin"),
                            gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                                    .getFechaImposicionFinal()));
        }
        // Fecha inicial registro
        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaRegistroInicial() != null) {
            listFiltros
                    .add(Arrays.asList(getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("label_fecha_registro_ini"),
                            gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                                    .getFechaRegistroInicial()));
        }
        // Fecha final registro
        if (gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                .getFechaRegistroFinal() != null) {
            listFiltros
                    .add(Arrays.asList(getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("label_fecha_registro_fin"),
                            gestionarInconsistenciasComparendoHolderFL.getConsultaProcesaComparendoDTO()
                                    .getFechaRegistroFinal()));
        }
        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, listFiltros);

        return contenido;

    }

    /**
     * Validaciones globales para algunos campos
     */
    public boolean validacionesGlobales() {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::validacionesGlobales()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);

        // Validacion para los datos del infractor y para los datos del propietario
        // Propietario
        if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion() == null
                && gestionarInconsistenciasComparendoFL.getPropietario().getNumeroIdentificacion() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_propietario");
            return true;
        }

        if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion() != null
                && gestionarInconsistenciasComparendoFL.getPropietario().getNumeroIdentificacion() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_propietario");
            return true;
        }

        // Infractor
        if (gestionarInconsistenciasComparendoFL.getInfractor().getIdTipoIdentificacion() == null
                && gestionarInconsistenciasComparendoFL.getInfractor().getNumeroIdentificacion() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_agente");
            return true;
        }

        if (gestionarInconsistenciasComparendoFL.getInfractor().getIdTipoIdentificacion() != null
                && gestionarInconsistenciasComparendoFL.getInfractor().getNumeroIdentificacion() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_agente");
            return true;
        }

        // Agente
        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() == null
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .getNumeroIdentificacionAgente() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_agente");
            return true;
        }

        if (gestionarInconsistenciasComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() != null
                && gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                        .getNumeroIdentificacionAgente() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_agente");
            return true;
        }
        return false;
    }

    /**
     * Recarga los campos de direccion
     * 
     * @author julio.pinzon - giovanni.velandia(mod 25-02-2016)
     */
    public void recargarDireccion(DireccionDTO direccion) {
        LOGGER.debug("GestionarInconsistenciasComparendoMB::recargarDireccion()");
        RequestContext context = RequestContext.getCurrentInstance();
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        if (gestionarInconsistenciasComparendoFL.isEditaDireccionInfraccion()) {
            context.update("form-ingreso:datos-lugar-infraccion");
            gestionarInconsistenciasComparendoFL.setDireccionInfraccion(direccion);
            gestionarInconsistenciasComparendoFL.setEditaDireccionInfraccion(false);
        } else if (gestionarInconsistenciasComparendoFL.isEditaDireccionInfractor()) {
            context.update("form-ingreso-detalle-comparendo:datos-direccion-infractor");
            gestionarInconsistenciasComparendoFL.setDireccionInfractor(direccion);
            gestionarInconsistenciasComparendoFL.setEditaDireccionInfractor(false);
        } else if (gestionarInconsistenciasComparendoFL.isEditaDireccionInmovilizacion()) {
            context.update("form-ingreso-detalle-comparendo:datos-direccion-inmovilizacion");
            gestionarInconsistenciasComparendoFL.setDireccionInmovilizacion(direccion);
            gestionarInconsistenciasComparendoFL.setEditaDireccionInmovilizacion(false);
        } else if (gestionarInconsistenciasComparendoFL.isEditaDireccionTestigo()) {
            context.update("form-ingreso-detalle-comparendo:datos-direccion-testigo");
            gestionarInconsistenciasComparendoFL.setDireccionTestigo(direccion);
            gestionarInconsistenciasComparendoFL.setEditaDireccionTestigo(false);
        }
        context.execute("window.scrollTo(0,885);");
    }

    /**
     * Permite cambiar los datos de propietario en dado caso de que el infractor sea el mismo propietario.
     */
    public void onEsPropietarioInfractorChange() {
        LOGGER.debug("RectificarComparendoMB::onNumDocInfractorChange()");
        GestionarInconsistenciasComparendoFL gestionarInconsistenciasComparendoFL = findFlowObject(
                GestionarInconsistenciasComparendoFL.class, GestionarInconsistenciasComparendoFL.NOMBRE_BEAN);
        if (gestionarInconsistenciasComparendoFL.isEsPropietario()) {
            gestionarInconsistenciasComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
            gestionarInconsistenciasComparendoFL.getPropietario()
                    .setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());
            gestionarInconsistenciasComparendoFL.getPropietario().setIdTipoIdentificacion(
                    gestionarInconsistenciasComparendoFL.getInfractor().getIdTipoIdentificacion());
            gestionarInconsistenciasComparendoFL.getPropietario().setNumeroIdentificacion(
                    gestionarInconsistenciasComparendoFL.getInfractor().getNumeroIdentificacion());
            gestionarInconsistenciasComparendoFL.getPropietario()
                    .setApellido1(gestionarInconsistenciasComparendoFL.getInfractor().getApellido1());
            gestionarInconsistenciasComparendoFL.getPropietario()
                    .setApellido2(gestionarInconsistenciasComparendoFL.getInfractor().getApellido2());
            gestionarInconsistenciasComparendoFL.getPropietario()
                    .setNombre1(gestionarInconsistenciasComparendoFL.getInfractor().getNombre1());
            gestionarInconsistenciasComparendoFL.getPropietario()
                    .setNombre2(gestionarInconsistenciasComparendoFL.getInfractor().getNombre2());
            gestionarInconsistenciasComparendoFL.getPropietario()
                    .setRazonSocial(gestionarInconsistenciasComparendoFL.getInfractor().getRazonSocial());

            if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion() != null) {
                if (gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion()
                        .equals(gestionarInconsistenciasComparendoFL.getTipoIdentificacionEmpresa().getId())) {
                    gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(true);
                } else {
                    gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(false);
                }
            } else {
                gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(false);
            }
        } else {
            gestionarInconsistenciasComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
            if (propietario != null
                    && gestionarInconsistenciasComparendoFL.getProcesaComparendo().getFechaInfraccion()
                            .after(propietario.getFechaInicio())
                    && (propietario.getFechaFin() == null || gestionarInconsistenciasComparendoFL.getProcesaComparendo()
                            .getFechaInfraccion().before(propietario.getFechaFin()))) {
                gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(false);
                if (propietario.getPersona() != null) {
                    gestionarInconsistenciasComparendoFL.setPropietario(
                            AdminComparendoHelper.toProcesaComparendoPersonaDTO(propietario.getPersona(),
                                    gestionarInconsistenciasComparendoFL.getPropietario()));
                    gestionarInconsistenciasComparendoFL.setRequierePropietario(true);

                    gestionarInconsistenciasComparendoFL.setTipoIdJuridicoPropietario(
                            gestionarInconsistenciasComparendoFL.getPropietario().getIdTipoIdentificacion().equals(
                                    gestionarInconsistenciasComparendoFL.getTipoIdentificacionEmpresa().getId()));
                }
            }
        }
    }

    public void prueba() {
        irComparendoTercero.registrarCoactivoJob(Integer.valueOf("11001"));
    }

    public void prueba2() {
        try {
            irComparendoTercero.generarOficioBien();
        } catch (CirculemosNegocioException e) {
            e.printStackTrace();
        }
    }

    public String getTiposArchivosPermitidos() {
        return TIPOS_ARCHIVOS_PERMITIDOS;
    }

    public String getMesErrLimitSize() {
        return MessageFormat.format(getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("msg_error_tamanio_archivo"),
                tamanioMaximoArch);
    }

    public int getTamanioMaximoArch() {
        return tamanioMaximoArch;
    }

    public void setTamanioMaximoArch(int tamanioMaximoArch) {
        this.tamanioMaximoArch = tamanioMaximoArch;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Date getFechaImposicionDiasMaximo() {
        return fechaImposicionDiasMaximo;
    }

    public void setFechaImposicionDiasMaximo(Date fechaImposicionDiasMaximo) {
        this.fechaImposicionDiasMaximo = fechaImposicionDiasMaximo;
    }

    public Date getFechaRegistroDiasMaximo() {
        return fechaRegistroDiasMaximo;
    }

    public void setFechaRegistroDiasMaximo(Date fechaRegistroDiasMaximo) {
        this.fechaRegistroDiasMaximo = fechaRegistroDiasMaximo;
    }

    /**********************
     * Expresiones regulares
     **********************/

    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }

    /**********************
     * Fin Expresiones regulares
     **********************/

    public boolean isEsConfigCamposEcuador() {
        return esConfigCamposEcuador;
    }

    public void setEsConfigCamposEcuador(boolean esConfigCamposEcuador) {
        this.esConfigCamposEcuador = esConfigCamposEcuador;
    }

    public boolean isEsConfigCamposColombia() {
        return esConfigCamposColombia;
    }

    public void setEsConfigCamposColombia(boolean esConfigCamposColombia) {
        this.esConfigCamposColombia = esConfigCamposColombia;
    }
}