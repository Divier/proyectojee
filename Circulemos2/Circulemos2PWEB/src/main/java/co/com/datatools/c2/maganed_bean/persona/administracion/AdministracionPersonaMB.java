package co.com.datatools.c2.maganed_bean.persona.administracion;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;

/**
 * Managed Bean para manipulación administración de personas CU_CIR20_UBI_002
 * 
 * @author Dixon.Alvarez
 * 
 */
@ManagedBean
@SessionScoped
public class AdministracionPersonaMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(AdministracionPersonaMB.class.getName());

    private static final String BUNDLE_PERSONA = "labelAdminPersona";

    @EJB
    private IRPersona personaEJB;

    public void init() {
        logger.debug(AdministracionPersonaMB.class.getName().concat("init()"));
    }

    public void consultar() {
        logger.debug(AdministracionPersonaMB.class.getName().concat("consultar()"));
        AdministracionPersonaHolderFL administracionPersonaHolderFL = findFlowObject(
                AdministracionPersonaHolderFL.class, AdministracionPersonaHolderFL.NOMBRE_BEAN);
        administracionPersonaHolderFL.getPersonaFiltro().setOrganismoTransito(getOrganismoTransito());
        administracionPersonaHolderFL.setPersonas(new ArrayList<PersonaDTO>());
        PersonaDTO persona = personaEJB.consultarPersona(
                administracionPersonaHolderFL.getPersonaFiltro().getOrganismoTransito().getCodigoOrganismo(),
                administracionPersonaHolderFL.getPersonaFiltro().getTipoIdentificacion().getId(),
                administracionPersonaHolderFL.getPersonaFiltro().getNumeroIdentificacion());
        if (persona != null) {
            administracionPersonaHolderFL.getPersonas().add(persona);
        }
        administracionPersonaHolderFL.setPersonaSeleccionada(null);
        if (administracionPersonaHolderFL.getPersonas().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            CirculemosAccesoBundleGeneral
                    .addMensajeResultadoConsulta(administracionPersonaHolderFL.getPersonas().size());
        }
    }

    public void confirmacionPersonaPresente() {
        logger.debug(AdministracionPersonaMB.class.getName().concat("confirmacionPersonaPresente()"));
        AdministracionPersonaHolderFL administracionPersonaHolderFL = findFlowObject(
                AdministracionPersonaHolderFL.class, AdministracionPersonaHolderFL.NOMBRE_BEAN);
        administracionPersonaHolderFL.setPopupVisible(false);
        RequestContext request = RequestContext.getCurrentInstance();
        request.update("frmConfirmaPersonaPresente");
    }

    public void irRegistrarPersona() {
        logger.debug(AdministracionPersonaMB.class.getName().concat("irRegistrarPersona()"));
        AdministracionPersonaHolderFL administracionPersonaHolderFL = findFlowObject(
                AdministracionPersonaHolderFL.class, AdministracionPersonaHolderFL.NOMBRE_BEAN);
        administracionPersonaHolderFL.setPopupVisible(true);
        administracionPersonaHolderFL.setActualizar(false);
        RequestContext request = RequestContext.getCurrentInstance();
        request.update("frmConfirmaPersonaPresente");
        request.execute("PF('popupConfirmaPersonaPresente').show();");
    }

    public void irEditarPersona() {
        logger.debug(AdministracionPersonaMB.class.getName().concat("irRegistrarPersona()"));
        AdministracionPersonaHolderFL administracionPersonaHolderFL = findFlowObject(
                AdministracionPersonaHolderFL.class, AdministracionPersonaHolderFL.NOMBRE_BEAN);
        administracionPersonaHolderFL.setPopupVisible(true);
        administracionPersonaHolderFL.setActualizar(true);
        RequestContext request = RequestContext.getCurrentInstance();
        request.update("frmConfirmaPersonaPresente");
        request.execute("PF('popupConfirmaPersonaPresente').show();");
    }

    public boolean verUbicabilidad() {
        logger.debug(AdministracionPersonaMB.class.getName().concat("verUbicabilidad()"));
        AdministracionPersonaHolderFL administracionPersonaHolderFL = findFlowObject(
                AdministracionPersonaHolderFL.class, AdministracionPersonaHolderFL.NOMBRE_BEAN);
        boolean verUbicabilidad = false;
        if (administracionPersonaHolderFL.getPersonaSeleccionada() != null) {
            verUbicabilidad = true;
        }
        return verUbicabilidad;
    }
}
