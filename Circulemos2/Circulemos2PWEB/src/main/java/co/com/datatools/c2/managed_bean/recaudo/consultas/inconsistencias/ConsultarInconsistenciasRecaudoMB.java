package co.com.datatools.c2.managed_bean.recaudo.consultas.inconsistencias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.DetallePagoInconsistenciaResDTO;
import co.com.datatools.c2.dto.FiltroConsultaInconsistenciasDTO;
import co.com.datatools.c2.dto.RechazoRecaudoResDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoReporteRecaudo;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.negocio.interfaces.IRInconsistenciaRecaudo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRRechazosRecaudo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.util.date.UtilFecha;

@ManagedBean
@SessionScoped
public class ConsultarInconsistenciasRecaudoMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ConsultarInconsistenciasRecaudoMB.class.getName());
    private static final String CONSULTAR_INCONSISTENCIAS_RECAUDO_HOLDER_FL = "consultarInconsistenciasRecaudoHolderFL";
    private static final Class<ConsultarInconsistenciasRecaudoHolderFL> OBJ_CONSULTAR_INCONSISTENCIAS_RECUADO_HOLDER_FL = ConsultarInconsistenciasRecaudoHolderFL.class;
    private List<SelectItem> tipoReporteRecaudo = new ArrayList<SelectItem>();
    private static final String NOMBRE_BUNDLE_ADMIN_COMPARENDO = "labelAdminComparendo";
    private Date fechaSistema;
    private Date fechaGeneracionDiasMaximo;
    private int mediaDiasMaximo;
    private int diasMaximo;

    @EJB
    private IRInconsistenciaRecaudo iRInconsistenciaRecaudoEJB;
    @EJB
    private IRCatalogo catalogoEJB;
    @EJB
    private IRRechazosRecaudo iRRechazosRecaudoEJB;
    @EJB
    private IRParametro parametroEjb;

    @PostConstruct
    public void init() {
        logger.debug("ConsultarInconsistenciasRecaudoMB::init()");
        tipoReporteRecaudo.addAll(consultarTipoReporteRecaudo());

        // Fecha del sistema
        fechaSistema = Calendar.getInstance().getTime();

        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valorParametroDTO = parametroEjb.consultarParametro(
                EnumParametro.CANT_MEDIA_DIAS_REALIZAR_CONSULTA, getOrganismoTransito().getCodigoOrganismo(), true);
        diasMaximo = valorParametroDTO.getValorParamInt();
        mediaDiasMaximo = diasMaximo - 1;
        calcularRangoConsultaFechaGeneracion();
    }

    /**
     * Metodo que permite calcular el rango de la fecha maxima de consulta registro
     * 
     */
    public void calcularRangoConsultaFechaGeneracion() {
        logger.debug("ConsultarInconsistenciasRecaudoMB::calcularRangoConsultaFechaRegistro()");

        ConsultarInconsistenciasRecaudoHolderFL consultarInconsistenciasRecaudoHolderFL = findFlowObject(
                OBJ_CONSULTAR_INCONSISTENCIAS_RECUADO_HOLDER_FL, CONSULTAR_INCONSISTENCIAS_RECAUDO_HOLDER_FL);

        if (consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion() != null) {
            fechaGeneracionDiasMaximo = UtilFecha
                    .sumarDias(consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion(), mediaDiasMaximo);
        }
    }

    public void consultarInconsistenciasRecuado() {
        logger.debug("ConsultarInconsistenciasRecaudoMB::consultar()");

        ConsultarInconsistenciasRecaudoHolderFL consultarInconsistenciasRecaudoHolderFL = findFlowObject(
                OBJ_CONSULTAR_INCONSISTENCIAS_RECUADO_HOLDER_FL, CONSULTAR_INCONSISTENCIAS_RECAUDO_HOLDER_FL);

        consultarInconsistenciasRecaudoHolderFL.getDetallePagoInconsistenciaResDTO().clear();
        consultarInconsistenciasRecaudoHolderFL.getRecuadoRechazosDTOList().clear();

        // Fechas registro
        if (consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion() == null
                && consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion() != null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_inicial");
            return;
        } else if (consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion() != null
                && consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion() == null) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_final");
            return;
        } else if (consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion() != null
                && consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion() != null
                && consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion()
                        .after(consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion())) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_intervalo_fechas_reg");
            return;
        } else if (consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion() != null
                && consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion().after(fechaSistema)) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_reg_actual");
            return;
        } else if (consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion() != null
                && consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion().after(fechaGeneracionDiasMaximo)) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "tit_error_fecha_reg_parametro",
                    NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_reg_parametro", diasMaximo);
            return;
        }

        if (consultarInconsistenciasRecaudoHolderFL.getTipoReporte() == EnumTipoReporteRecaudo.INCONSISTENCIAS
                .getValue()) {
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO = new FiltroConsultaInconsistenciasDTO();
            filtroConsultaInconsistenciasDTO
                    .setOrganismoTransito(consultarInconsistenciasRecaudoHolderFL.getCodigoOrganismo());
            filtroConsultaInconsistenciasDTO
                    .setNumeroRecaudo(consultarInconsistenciasRecaudoHolderFL.getNumeroRecaudo());
            filtroConsultaInconsistenciasDTO
                    .setObligacionPagada(consultarInconsistenciasRecaudoHolderFL.getNumeroObligacion());
            filtroConsultaInconsistenciasDTO
                    .setFechaInicial(consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion());
            filtroConsultaInconsistenciasDTO
                    .setFechaFinal(consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion());

            consultarInconsistenciasRecaudoHolderFL.setDetallePagoInconsistenciaResDTO(
                    iRInconsistenciaRecaudoEJB.consultarPagoInconsistenciasEnviadas(filtroConsultaInconsistenciasDTO));

            if (consultarInconsistenciasRecaudoHolderFL.getDetallePagoInconsistenciaResDTO().isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(
                        consultarInconsistenciasRecaudoHolderFL.getDetallePagoInconsistenciaResDTO().size());
            }

        } else if (consultarInconsistenciasRecaudoHolderFL.getTipoReporte() == EnumTipoReporteRecaudo.CONCILIACION
                .getValue()) {
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO = new FiltroConsultaInconsistenciasDTO();
            filtroConsultaInconsistenciasDTO
                    .setOrganismoTransito(consultarInconsistenciasRecaudoHolderFL.getCodigoOrganismo());
            filtroConsultaInconsistenciasDTO
                    .setNumeroRecaudo(consultarInconsistenciasRecaudoHolderFL.getNumeroRecaudo());
            filtroConsultaInconsistenciasDTO
                    .setObligacionPagada(consultarInconsistenciasRecaudoHolderFL.getNumeroObligacion());
            filtroConsultaInconsistenciasDTO
                    .setFechaInicial(consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion());
            filtroConsultaInconsistenciasDTO
                    .setFechaFinal(consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion());

            consultarInconsistenciasRecaudoHolderFL.setDetallePagoInconsistenciaResDTO(iRInconsistenciaRecaudoEJB
                    .consultarPagosInconsistenciasConciliacionEnviadas(filtroConsultaInconsistenciasDTO));

            if (consultarInconsistenciasRecaudoHolderFL.getDetallePagoInconsistenciaResDTO().isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(
                        consultarInconsistenciasRecaudoHolderFL.getDetallePagoInconsistenciaResDTO().size());
            }
        } else if (consultarInconsistenciasRecaudoHolderFL.getTipoReporte() == EnumTipoReporteRecaudo.NO_RECIBIDOS
                .getValue()) {
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO = new FiltroConsultaInconsistenciasDTO();
            filtroConsultaInconsistenciasDTO
                    .setOrganismoTransito(consultarInconsistenciasRecaudoHolderFL.getCodigoOrganismo());
            filtroConsultaInconsistenciasDTO
                    .setNumeroRecaudo(consultarInconsistenciasRecaudoHolderFL.getNumeroRecaudo());
            filtroConsultaInconsistenciasDTO
                    .setObligacionPagada(consultarInconsistenciasRecaudoHolderFL.getNumeroObligacion());
            filtroConsultaInconsistenciasDTO
                    .setFechaInicial(consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion());
            filtroConsultaInconsistenciasDTO
                    .setFechaFinal(consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion());

            consultarInconsistenciasRecaudoHolderFL.setRecuadoRechazosDTOList(
                    iRRechazosRecaudoEJB.consultarRechazosRecaudosEnviados(filtroConsultaInconsistenciasDTO));

            if (consultarInconsistenciasRecaudoHolderFL.getRecuadoRechazosDTOList().isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(
                        consultarInconsistenciasRecaudoHolderFL.getRecuadoRechazosDTOList().size());
            }
        }

    }

    public ContenidoReporte procesarContenidoReporte() {
        logger.debug("ConsultarInconsistenciasRecaudoMB::procesarContenidoReporte()");
        ConsultarInconsistenciasRecaudoHolderFL consultarInconsistenciasRecaudoHolderFL = findFlowObject(
                OBJ_CONSULTAR_INCONSISTENCIAS_RECUADO_HOLDER_FL, CONSULTAR_INCONSISTENCIAS_RECAUDO_HOLDER_FL);

        ContenidoReporte contenido = new ContenidoReporte();
        List<Object> listaContenido = new ArrayList<Object>();
        List<Object> registros;

        for (DetallePagoInconsistenciaResDTO detallePagoInconsistenciaResDTO : consultarInconsistenciasRecaudoHolderFL
                .getDetallePagoInconsistenciaResDTO()) {
            registros = new ArrayList<Object>();
            registros.add(detallePagoInconsistenciaResDTO.getOrganismoTransito());
            registros.add(detallePagoInconsistenciaResDTO.getFechaTransaccion());
            registros.add(detallePagoInconsistenciaResDTO.getHoraTransaccion());
            registros.add(detallePagoInconsistenciaResDTO.getNumeroRecaudo());
            registros.add(detallePagoInconsistenciaResDTO.getCuenta());
            registros.add(detallePagoInconsistenciaResDTO.getCodigoTipoCuenta());
            registros.add(detallePagoInconsistenciaResDTO.getCodigoBanco());
            registros.add(detallePagoInconsistenciaResDTO.getTotalEfectivo());
            registros.add(detallePagoInconsistenciaResDTO.getTotalCheque());
            registros.add(detallePagoInconsistenciaResDTO.getTotalRecaudo());
            registros.add(detallePagoInconsistenciaResDTO.getCodigoTipoDocumento());
            registros.add(detallePagoInconsistenciaResDTO.getNumeroIdentificacion());
            registros.add(detallePagoInconsistenciaResDTO.getObligacionPagada());
            registros.add(detallePagoInconsistenciaResDTO.getValorObligacion());
            registros.add(detallePagoInconsistenciaResDTO.getCodigoTipoRecaudo());
            registros.add(detallePagoInconsistenciaResDTO.getNumeroCuota());
            registros.add(detallePagoInconsistenciaResDTO.getInconsistencias());
            listaContenido.add(registros);
        }
        contenido.setContenido(listaContenido);
        super.obtenerEncabezadoReporte(contenido);

        List listFiltros = new ArrayList<>();
        //
        listFiltros.add(
                Arrays.asList("Organismo de Tránsito", consultarInconsistenciasRecaudoHolderFL.getCodigoOrganismo()));

        listFiltros.add(Arrays.asList("Fecha Inicio de generación de reporte",
                consultarInconsistenciasRecaudoHolderFL.getFechaInicialGeneracion()));

        listFiltros.add(Arrays.asList("Fecha Fin de generación de reporte",
                consultarInconsistenciasRecaudoHolderFL.getFechaFinalGeneracion()));

        //
        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, listFiltros);
        return contenido;

    }

    public List<SelectItem> consultarTipoReporteRecaudo() {
        logger.debug("ConsultarInconsistenciasRecaudoMB::procesarContenidoReporte()");

        List<SelectItem> itemsCatalogos = new ArrayList<SelectItem>();
        List<ItemCatalogoDTO> listaItems = catalogoEJB
                .consultarItemsCatalogo(EnumCatalogo.TipoReporteRecaudo.toString(), null);
        for (ItemCatalogoDTO itemCatalogoDTO : listaItems) {
            itemsCatalogos.add(new SelectItem(itemCatalogoDTO.getId(), itemCatalogoDTO.getNombre()));
        }
        return itemsCatalogos;

    }

    public ContenidoReporte procesarContenidoReporteRechazos() {
        logger.debug("ConsultarInconsistenciasRecaudoMB::procesarContenidoReporte()");
        ConsultarInconsistenciasRecaudoHolderFL consultarInconsistenciasRecaudoHolderFL = findFlowObject(
                OBJ_CONSULTAR_INCONSISTENCIAS_RECUADO_HOLDER_FL, CONSULTAR_INCONSISTENCIAS_RECAUDO_HOLDER_FL);

        ContenidoReporte contenido = new ContenidoReporte();
        List<Object> listaContenido = new ArrayList<Object>();
        List<Object> registros;

        for (RechazoRecaudoResDTO recaudosRechazosResDTO : consultarInconsistenciasRecaudoHolderFL
                .getRecuadoRechazosDTOList()) {
            registros = new ArrayList<Object>();
            registros.add(recaudosRechazosResDTO.getOrganismoTransito());
            registros.add(recaudosRechazosResDTO.getNumeroRecaudo());
            registros.add(recaudosRechazosResDTO.getFecha());
            registros.add(recaudosRechazosResDTO.getNombreUsuario());
            registros.add(recaudosRechazosResDTO.getEstadoLectura());
            registros.add(recaudosRechazosResDTO.getRechazos());
            listaContenido.add(registros);
        }
        contenido.setContenido(listaContenido);
        super.obtenerEncabezadoReporte(contenido);

        List listFiltros = new ArrayList<>();
        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, listFiltros);
        return contenido;

    }

    public List<SelectItem> getTipoReporteRecaudo() {
        return tipoReporteRecaudo;
    }

    public void setTipoReporteRecaudo(List<SelectItem> tipoReporteRecaudo) {
        this.tipoReporteRecaudo = tipoReporteRecaudo;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Date getFechaGeneracionDiasMaximo() {
        return fechaGeneracionDiasMaximo;
    }

    public void setFechaGeneracionDiasMaximo(Date fechaGeneracionDiasMaximo) {
        this.fechaGeneracionDiasMaximo = fechaGeneracionDiasMaximo;
    }
}
