package co.com.datatools.c2.negocio.fachada;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConfiguracionEmailDTO;
import co.com.datatools.c2.dto.CorreoEnvioDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.ILAdministracion;
import co.com.datatools.c2.negocio.interfaces.ILCargueMasivo;
import co.com.datatools.c2.negocio.interfaces.ILConfiguracionEmail;
import co.com.datatools.c2.negocio.interfaces.ILPersona;
import co.com.datatools.c2.negocio.interfaces.ILUbicabilidad;

@Stateless(name = "FachadaAdminNegocioEJB")
@LocalBean
public class FachadaAdminNegocioEJB implements IRFachadaAdminNegocio {
    private final static Logger logger = Logger.getLogger(FachadaAdminNegocioEJB.class.getName());

    @EJB
    private ILPersona iLPersona;
    @EJB
    private ILAdministracion administracionEJB;
    @EJB
    private ILConfiguracionEmail iLConfiguracionEmail;
    @EJB
    private ILUbicabilidad iLUbicabilidad;
    @EJB
    private ILCargueMasivo iLCargueMasivo;

    @Override
    public List<PersonaDTO> consultarPersona(PersonaDTO personaDTO) {
        logger.debug("FachadaAdminNegocioEJB::consultarPersona(PersonaDTO)");
        return iLPersona.consultarPersonas(personaDTO);
    }

    @Override
    public List<OrganismoTransitoDTO> consultarOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        logger.debug("FachadaAdminNegocioEJB::consultarOrganismoTransito(OrganismoTransitoDTO)");
        return administracionEJB.consultarOrganismoTransito(organismoTransito);
    }

    @Override
    public PersonaDTO consultarPersona(int organismoTransito, int tipoDocumento, String numeroDocumento)
            throws CirculemosNegocioException {
        logger.debug("FachadaAdminNegocioEJB::consultarPersona(int,int,String)");
        return iLPersona.consultarPersonaConPrioridad(organismoTransito, tipoDocumento, numeroDocumento);
    }

    @Override
    public PersonaDTO consultarPersona(Integer tipoIdentificacion, String numeroIdentificacion) {
        logger.debug("FachadaAdminNegocioEJB::consultarPersona(Integer,String)");
        return iLPersona.consultarPersona(tipoIdentificacion, numeroIdentificacion);
    }

    @Override
    public Long registrarPersona(PersonaDTO personaDTO) throws CirculemosNegocioException {
        logger.debug("FachadaAdminNegocioEJB::registrarPersona(PersonaDTO)");
        return iLPersona.registrarPersona(personaDTO);
    }

    @Override
    public TipoIdentificacionPersonaDTO consultarTipoIdentificacionPersonaJuridica(int idPais) {
        logger.debug("FachadaAdminNegocioEJB::consultarTipoIdentificacionPersonaJuridica(int)");
        return administracionEJB.obtenerTipoIdentificacionEmpresaJuridicaPais(idPais);
    }

    @Override
    public List<CorreoEnvioDTO> consultarEmailsConfigurados(int codigoOrganismo, Boolean activo,
            EnumTipoCorreo tipoEmail) {
        logger.debug("FachadaAdminNegocioEJB::consultarEmailsConfigurados(int, Boolean, EnumTipoCorreo)");
        List<ConfiguracionEmailDTO> configuraciones = iLConfiguracionEmail.consultarConfiguracionEmail(codigoOrganismo,
                tipoEmail, activo);

        List<CorreoEnvioDTO> correos = new ArrayList<CorreoEnvioDTO>();
        for (ConfiguracionEmailDTO configuracionEmailDTO : configuraciones) {
            correos.addAll(configuracionEmailDTO.getEmailList());
        }
        return correos;
    }

    @Override
    public boolean modificarPersona(PersonaDTO personaDTO) throws CirculemosNegocioException {
        logger.debug("FachadaAdminNegocioEJB::modificarPersona(PersonaDTO)");
        return iLPersona.modificarPersona(personaDTO, null);
    }

    @Override
    public boolean modificarCabeceraPersona(PersonaDTO personaDTO) {
        logger.debug("FachadaAdminNegocioEJB::modificarUltimaActualizacionPersona(PersonaDTO)");
        return iLPersona.modificarCabeceraPersona(personaDTO);
    }

    @Override
    public PersonaDTO consultarPersona(long idPersona) {
        logger.debug("FachadaAdminNegocioEJB::consultarPersona(long)");
        return iLPersona.consultarPersona(idPersona);
    }

    @Override
    public List<CorreoPersonaDTO> consultarCorreosNotificables(Long idPersona) {
        logger.debug("FachadaAdminNegocioEJB::consultarCorreosNotificables(Long)");
        return iLUbicabilidad.consultarCorreosNotificables(idPersona);
    }

    @Override
    public List<DireccionPersonaDTO> consultarDireccionPersona(DireccionPersonaDTO direccionPersonaDTO) {
        return iLUbicabilidad.consultarDireccionPersona(direccionPersonaDTO);
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public void cerrarCargueArchivo(CargueArchivoDTO cargueArchivoDTO) {
        logger.debug("FachadaAdminNegocioEJB::cerrarCargueArchivo(CargueArchivoDTO)");
        iLCargueMasivo.cerrarCargueArchivo(cargueArchivoDTO);
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public void registrarRegistroProcesado(CargueArchivoDTO cargueArchivo) {
        logger.debug("FachadaAdminNegocioEJB::registrarRegistroProcesado(CargueArchivoDTO)");
        iLCargueMasivo.registrarRegistroProcesado(cargueArchivo);
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public DocumentoResultadoCargueDTO registrarDocumentoResulCargue(DocumentoResultadoCargueDTO documentoResulDTO) {
        logger.debug("FachadaAdminNegocioEJB::registrarDocumentoResulCargue(DocumentoResultadoCargueDTO)");
        return iLCargueMasivo.registrarDocumentoResulCargue(documentoResulDTO);
    }

    public List<CorreoPersonaDTO> consultarCorreoPersona(CorreoPersonaDTO correoPersonaDTO) {
        logger.debug("FachadaAdminNegocioEJB::consultarCorreoPersona(CorreoPersonaDTO)");
        return iLUbicabilidad.consultarCorreos(correoPersonaDTO);
    }

    public List<TelefonoPersonaDTO> consultarTelefonoPersona(TelefonoPersonaDTO telefonoPersonaDTO) {
        logger.debug("FachadaAdminNegocioEJB::consultarTelefonoPersona(TelefonoPersonaDTO)");
        return iLUbicabilidad.consultarTelefonos(telefonoPersonaDTO);
    }
}