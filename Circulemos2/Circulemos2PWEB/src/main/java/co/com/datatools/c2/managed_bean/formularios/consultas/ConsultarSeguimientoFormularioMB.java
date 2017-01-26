package co.com.datatools.c2.managed_bean.formularios.consultas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.ConsultaSeguimientoFormularioDTO;
import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * Managed Bean utilizado para llevar a cabo la logica del caso de uso <b>CU_CIR20_DAT_NUM_009 Consultar Seguimiento de formulario</b>
 * 
 * @author luis.forero(2015-09-21)
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultarSeguimientoFormularioMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConsultarSeguimientoFormularioMB.class.getName());
    private static final Class<ConsultarSeguimientoFormularioHolderFL> OBJ_CONSULTAR_SEGUIMIENTO_FORMULARIO_HOLDER_FL = ConsultarSeguimientoFormularioHolderFL.class;
    private static final String CONSULTAR_SEGUIMIENTO_FORMULARIO_HOLDER_FL = "consultarSeguimientoFormularioHolderFL";
    private static final String BUNDLE_CONSULTA_SEGUIMIENTO_FORMULARIO = "labelConsultasFormularios";

    @EJB
    private IRFormulario formularioEJB;
    @EJB
    private IRRepositorioArchivo repositorioArchivoEJB;

    private StreamedContent streamedContent;
    private static final String CONTENT_TYPE = "application/zip";

    /**
     * Permite consultar el seguimiento de un formulario en particular
     * 
     * @author divier.casas(2015-09-02)<br>
     *         luis.forero(mod 2015-09-21)
     */
    public void consultar() {
        logger.debug(ConsultarSeguimientoFormularioMB.class.getName().concat("::consultar()"));
        final ConsultarSeguimientoFormularioHolderFL consultarSeguimientoFormularioHolderFL = findFlowObject(
                OBJ_CONSULTAR_SEGUIMIENTO_FORMULARIO_HOLDER_FL, CONSULTAR_SEGUIMIENTO_FORMULARIO_HOLDER_FL);
        ConsultaSeguimientoFormularioDTO consultaSeguimientoFormularioDTO = new ConsultaSeguimientoFormularioDTO();

        if (consultarSeguimientoFormularioHolderFL.getIdTipoFormulario() != null) {
            consultaSeguimientoFormularioDTO.setTipoFormularioDTO(new TipoFormularioDTO(
                    consultarSeguimientoFormularioHolderFL.getIdTipoFormulario()));
        }
        if (consultarSeguimientoFormularioHolderFL.getNumeroFormulario() != null) {
            consultaSeguimientoFormularioDTO.setNumeroFormulario(consultarSeguimientoFormularioHolderFL
                    .getNumeroFormulario());
        }

        OrganismoTransitoDTO organismoTransitoDTO = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);

        if (organismoTransitoDTO != null) {
            consultaSeguimientoFormularioDTO.setOrganismoTransito(organismoTransitoDTO);
        }

        consultarSeguimientoFormularioHolderFL.getLstSeguimientos().clear();

        FormularioDTO formularioDTO = formularioEJB.consultarSeguimientoFormulario(consultaSeguimientoFormularioDTO);

        if (formularioDTO == null) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            consultarSeguimientoFormularioHolderFL.setLstSeguimientos(new ArrayList<SeguimientoFormularioFL>(0));
            return;
        }

        consultarSeguimientoFormularioHolderFL.setNomTipoFormulario(formularioDTO.getTipoFormulario().getNombre());

        List<SeguimientoFormularioFL> lstSeguimientos = new ArrayList<>(1);
        String separadorNombre = getBundle(BUNDLE_CONSULTA_SEGUIMIENTO_FORMULARIO).getString(
                "simbolo_separador_nom_responsable");
        for (DetalleCambioEstadoDTO segForDTO : formularioDTO.getLstDetalleCambiosEstados()) {
            SeguimientoFormularioFL seguimientoFormularioFL = new SeguimientoFormularioFL(segForDTO);
            seguimientoFormularioFL.setFormularioDTO(formularioDTO);
            ResponsableFormularioDTO responsableFormulario = segForDTO.getResponsableFormulario();
            if (responsableFormulario != null) {
                OrganismoTransitoDTO organismoTransito = responsableFormulario.getUnificacionResponsableDTO()
                        .getOrganismoTransito();
                PersonaDTO persona = responsableFormulario.getUnificacionResponsableDTO().getPersona();
                StringBuilder nombreReponsable = new StringBuilder(0);
                if (organismoTransito != null) {
                    nombreReponsable.append(organismoTransito.getCodigoOrganismo() + separadorNombre
                            + organismoTransitoDTO.getNombreOrganismo());
                }
                if (persona != null) {
                    nombreReponsable.append(StringUtils.isBlank(persona.getTipoIdentificacion().getSigla()) ? persona
                            .getTipoIdentificacion().getNombre() : persona.getTipoIdentificacion().getSigla());
                    nombreReponsable.append(separadorNombre + persona.getNumeroIdentificacion());
                    nombreReponsable.append(separadorNombre + persona.getNombreCompleto());
                }
                seguimientoFormularioFL.setNombreResponsable(nombreReponsable.toString());
            }

            lstSeguimientos.add(seguimientoFormularioFL);
        }

        consultarSeguimientoFormularioHolderFL.setLstSeguimientos(lstSeguimientos);
        CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lstSeguimientos.size());
    }

    /**
     * Permite cargar el documento asociado al detalle cambio estado del modulo del repositorio de archivos.
     * 
     * @param detalleCambioEstadoDTO
     *            detalle cambio estado con el id del documento a ser cargado.
     * 
     * @author luis.forero(2015-09-21)
     */
    public void descargarDocumento(DetalleCambioEstadoDTO detalleCambioEstadoDTO) {
        logger.debug(ConsultarSeguimientoFormularioMB.class.getName().concat("::descargarDocumento()"));
        ArchivoTransportableDTO archivoTransportableDTO;
        try {
            archivoTransportableDTO = repositorioArchivoEJB.consultarDocumento(detalleCambioEstadoDTO
                    .getDocumentoFormulario().getIdDocumento());

            streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), CONTENT_TYPE,
                    archivoTransportableDTO.getNombre());

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        }

    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }
}