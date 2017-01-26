package co.com.datatools.c2.managed_bean.formularios.consultas.cambio_estado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.CifrasControlEstadoFormularioAsignacionDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioAsignacionDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.formularios.consultas.ConsultaFormulariosEstadosMB;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * 
 * @author diego.fonseca
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultarEstadosAsignacionMB extends AbstractC2ManagedBean {

    @EJB
    IRAdministracionFormularios administracionFormulariosEJB;

    @EJB
    IRFormulario formularioEJB;

    @EJB
    private IRAdministracion administracionEJB;

    private final static long serialVersionUID = 1L;

    private static final String CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL = "consultarEstadosAsignacionHolderFL";

    private static final Class<ConsultarEstadosAsignacionHolderFL> OBJ_CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL = ConsultarEstadosAsignacionHolderFL.class;

    private static final String NOMBRE_BUNDLE_FORM = "labelCambioEstado";

    private static final Logger LOGGER = Logger.getLogger(ConsultaFormulariosEstadosMB.class.getName());

    @PostConstruct
    public void init() {
        final ConsultarEstadosAsignacionHolderFL consultarEstadosAsignacionHolderFL = findFlowObject(
                OBJ_CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL, CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL);
        OrganismoTransitoDTO organismoTransitoDTO = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
        consultarEstadosAsignacionHolderFL.setOrganismoTransitoDTO(organismoTransitoDTO);
    }

    /**
     * 
     * @return
     * @author diego.fonseca (mod 2015-11-19)
     * 
     *         metodo en el cual se crea una lista de los organismos asociados segun el organismo de transito en sesion si es multiorganismo retorna
     *         una lista con todos los organismos asociados si no lo es retorna el mismo organismo en sesion
     */
    public List<SelectItem> consultaOrganismosAsociados() {
        LOGGER.debug(ConsultaFormulariosEstadosMB.class.getName().concat("::consultaOrganismosAsociados()"));
        List<SelectItem> itemsCatalogos;
        itemsCatalogos = new ArrayList<SelectItem>();
        OrganismoTransitoDTO organismoTransitoDTOSesion = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
        Integer codigoOrganismo = organismoTransitoDTOSesion.getCodigoOrganismo();
        List<OrganismoTransitoDTO> organismoTransitoDTO = administracionEJB
                .consultarOrganismosAsociados(codigoOrganismo);
        if (organismoTransitoDTO.size() > 0) {
            for (OrganismoTransitoDTO organismoTransito : organismoTransitoDTO) {
                itemsCatalogos.add(new SelectItem(organismoTransito.getCodigoOrganismo(), organismoTransito
                        .getNombreOrganismo()));
            }
        }
        return itemsCatalogos;
    }

    /**
     * 
     * @author diego.fonseca
     * 
     *         metodo que crea una lista de todos los responsables de un organismo de transito
     */
    public List<SelectItem> consultarResponsables() {
        LOGGER.debug(ConsultaFormulariosEstadosMB.class.getName().concat("::consultarResponsables()"));
        ConsultarEstadosAsignacionHolderFL consultarEstadosAsignacionHolderFL = findFlowObject(
                OBJ_CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL, CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL);
        List<SelectItem> itemsCatalogos = new ArrayList<SelectItem>();
        List<UnificacionResponsableDTO> lista = administracionFormulariosEJB.consultarResponsablesOrganismo(
                consultarEstadosAsignacionHolderFL.getOrganismoTransitoDTO().getCodigoOrganismo(),
                consultarEstadosAsignacionHolderFL.getTipoFormulario());
        for (UnificacionResponsableDTO unificacionResponsableDTO : lista) {
            if (unificacionResponsableDTO.getPersona() != null) {
                itemsCatalogos.add(new SelectItem(unificacionResponsableDTO.getId(), unificacionResponsableDTO
                        .getPersona().getNombreCompleto()));
            } else {
                itemsCatalogos.add(new SelectItem(unificacionResponsableDTO.getId(), unificacionResponsableDTO
                        .getOrganismoTransito().getNombreOrganismo()));
            }
        }
        return itemsCatalogos;

    }

    /**
     * @author diego.fonseca
     * 
     *         metodo que consulta la cantidad de formularios segun el estado para el evento Asignado
     */
    public void consultar() {
        LOGGER.debug(ConsultaFormulariosEstadosMB.class.getName().concat("::consultar()"));
        EstadoFormularioAsignacionDTO estadoFormularioAsignacionDTO = null;
        ConsultarEstadosAsignacionHolderFL consultarEstadosAsignacionHolderFL = findFlowObject(
                OBJ_CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL, CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL);
        try {
            estadoFormularioAsignacionDTO = formularioEJB.consultarEstadosFormulariosAsignacion(
                    consultarEstadosAsignacionHolderFL.getTipoFormulario(),
                    consultarEstadosAsignacionHolderFL.getIdResponsable());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        }
        if (estadoFormularioAsignacionDTO.getResponsable().getPersona() != null) {
            PersonaJuridicaDTO personaJuridicaDTO = (PersonaJuridicaDTO) estadoFormularioAsignacionDTO.getResponsable()
                    .getPersona();
            consultarEstadosAsignacionHolderFL.setPersonaJuridica(personaJuridicaDTO);
        }
        consultarEstadosAsignacionHolderFL.setEstadoFormularioAsignacionDTO(estadoFormularioAsignacionDTO);
        if (estadoFormularioAsignacionDTO.getCifrasControl().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(estadoFormularioAsignacionDTO.getCifrasControl()
                    .size());
        }

    }

    /**
     * 
     * @author diego.fonseca
     * 
     *         metodo complementario para el uso de envio por correo o descarga de archivo xls
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ContenidoReporte procesarContenidoReporte() {
        LOGGER.debug(ConsultaFormulariosEstadosMB.class.getName().concat("::procesarContenidoReporte()"));
        ConsultarEstadosAsignacionHolderFL consultarEstadosAsignacionHolderFL = findFlowObject(
                OBJ_CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL, CONSULTAR_ESTADOS_ASIGNACION_HOLDER_FL);

        ContenidoReporte contenido = new ContenidoReporte();
        List<Object> listaContenido = new ArrayList<Object>();
        List<Object> registros;

        for (CifrasControlEstadoFormularioAsignacionDTO ccefAsignacionDTO : consultarEstadosAsignacionHolderFL
                .getEstadoFormularioAsignacionDTO().getCifrasControl()) {
            registros = new ArrayList<Object>();
            if (consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getNombreOrganismo() != null) {
                registros.add(consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO()
                        .getNombreOrganismo()
                        + " "
                        + consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getNombreOrganismo());
            } else {
                registros.add(null);
            }

            if (consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getResponsable()
                    .getOrganismoTransito() != null) {
                registros.add(consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getResponsable()
                        .getOrganismoTransito().getCodigoOrganismo()
                        + "-"
                        + consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getResponsable()
                                .getOrganismoTransito().getNombreOrganismo());
            } else {
                registros.add(consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getResponsable()
                        .getPersona().getTipoIdentificacion().getNombre()
                        + "-"
                        + consultarEstadosAsignacionHolderFL.getPersonaJuridica().getNumeroIdentificacion()
                        + "-"
                        + consultarEstadosAsignacionHolderFL.getPersonaJuridica().getNombreComercial());
            }

            if (consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getNombreTipoFormulario() != null) {
                registros.add(consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO()
                        .getNombreTipoFormulario());
            } else {
                registros.add(null);
            }
            if (ccefAsignacionDTO.getIdDetalleCambioEstado() != 0) {
                registros.add(ccefAsignacionDTO.getIdDetalleCambioEstado().toString());
            } else {
                registros.add(null);
            }
            if (ccefAsignacionDTO.getFechaAplicacion() != null) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - H:mm");
                String fechaConFormato = formato.format(ccefAsignacionDTO.getFechaAplicacion());
                registros.add(fechaConFormato);
            } else {
                registros.add(null);
            }
            if (ccefAsignacionDTO.getNombreEstado() != null) {
                registros.add(ccefAsignacionDTO.getNombreEstado());
            } else {
                registros.add(null);
            }
            registros.add("" + ccefAsignacionDTO.getCantidad());
            listaContenido.add(registros);
        }
        contenido.setContenido(listaContenido);

        super.obtenerEncabezadoReporte(contenido);

        List listFiltros = new ArrayList<>();
        listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE_FORM).getString("lbl_org_transito"),
                consultarEstadosAsignacionHolderFL.getOrganismoTransitoDTO().getCodigoOrganismo() + " "
                        + consultarEstadosAsignacionHolderFL.getOrganismoTransitoDTO().getNombreOrganismo()));
        if (consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getResponsable()
                .getOrganismoTransito() != null) {
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE_FORM).getString("lbl_responsable"),
                    consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getCodigoOrganismo()
                            + "-"
                            + consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO()
                                    .getNombreOrganismo()));
        } else {
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE_FORM).getString("lbl_responsable"),
                    consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getResponsable().getPersona()
                            .getTipoIdentificacion().getNombre()
                            + "-"
                            + consultarEstadosAsignacionHolderFL.getPersonaJuridica().getNumeroIdentificacion()
                            + "-" + consultarEstadosAsignacionHolderFL.getPersonaJuridica().getNombreComercial()));
        }
        listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE_FORM).getString("lbl_tipo_formulario"),
                consultarEstadosAsignacionHolderFL.getEstadoFormularioAsignacionDTO().getNombreTipoFormulario()));

        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, listFiltros);
        return contenido;

    }

}
