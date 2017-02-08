package co.com.datatools.c2.managed_bean.coactivo.administracion.bien;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;

@ManagedBean
@SessionScoped
public class AdministracionBienMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(AdministracionBienMB.class.getName());

    @EJB
    private IRCoactivo iRCoactivo;

    /**
     * Consulta solicitud de bienes
     */
    public void consultar() {
        LOGGER.debug(AdministracionBienMB.class.getName().concat("consultar()"));

        AdministracionBienHolderFL administracionBienHolderFL = findFlowObject(AdministracionBienHolderFL.class,
                AdministracionBienHolderFL.NOMBRE_BEAN);

        administracionBienHolderFL.setRespuestaCoactivoDTOs(
                iRCoactivo.consultarCoactivo(administracionBienHolderFL.getFiltroCoactivoDTO()));

        if (administracionBienHolderFL.getRespuestaCoactivoDTOs() == null
                || administracionBienHolderFL.getRespuestaCoactivoDTOs().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            CirculemosAccesoBundleGeneral
                    .addMensajeResultadoConsulta(administracionBienHolderFL.getRespuestaCoactivoDTOs().size());
        }
    }

    /**
     * Consulta las solicitudes de los coactivos con su respectivo oficio
     * 
     * @author giovanni.velandia
     */
    public void consultarSolicitudesOficioCoactivo() {
        LOGGER.debug("AdministracionBienMB::cosultarOficioCoactivo()");
        AdministracionBienHolderFL administracionBienHolderFL = findFlowObject(AdministracionBienHolderFL.class,
                AdministracionBienHolderFL.NOMBRE_BEAN);
        RegistrarRespuestaBienFL registrarRespuestaBienFL = findFlowObject(RegistrarRespuestaBienFL.class,
                RegistrarRespuestaBienFL.NOMBRE_BEAN);
        registrarRespuestaBienFL.setSolicitudOficioCoactivoDTOs(iRCoactivo.consultarSolicitudOficioCoactivo(
                administracionBienHolderFL.getRespuestaCoactivoDTOSel().getIdCoactivo()));
        registrarRespuestaBienFL.setActivarSolCoactivo(true);
    }

    /**
     * Consulta las solicitudes de los coactivos con su respectivo oficio
     * 
     * @author giovanni.velandia
     */
    public void consultarSolicitudOficioCoactivo() {
        LOGGER.debug("AdministracionBienMB::cosultarOficioCoactivo()");
        RegistrarRespuestaBienFL registrarRespuestaBienFL = findFlowObject(RegistrarRespuestaBienFL.class,
                RegistrarRespuestaBienFL.NOMBRE_BEAN);
        registrarRespuestaBienFL.setSolicitudOficioCoactivoDTO(iRCoactivo.consultarSolicitudOficioCoactivo(
                registrarRespuestaBienFL.getSolicitudOficioCoactivoSelDTO().getIdCoactivo(),
                registrarRespuestaBienFL.getSolicitudOficioCoactivoSelDTO().getNumeroOficio()));
        registrarRespuestaBienFL.setActivarSolCoactivo(true);
    }

    /**
     * Activa el registro de informacion de bienes
     * 
     * @author giovanni.velandia
     */
    public void activarRegistroInformacionBien() {
        RegistrarRespuestaBienFL registrarRespuestaBienFL = findFlowObject(RegistrarRespuestaBienFL.class,
                RegistrarRespuestaBienFL.NOMBRE_BEAN);
        if (registrarRespuestaBienFL.isTieneBien()) {
            registrarRespuestaBienFL.setTieneBien(true);
        } else {
            registrarRespuestaBienFL.setTieneBien(false);
        }
    }

    /**
     * Cancela el popup de las solicitudes de los coactivos con su respectivo oficio
     * 
     * @author giovanni.velandia
     */
    public void cancelarSolicitudesOficioCoactivo() {
        LOGGER.debug("AdministracionBienMB::cosultarOficioCoactivo()");
        RegistrarRespuestaBienFL registrarRespuestaBienFL = findFlowObject(RegistrarRespuestaBienFL.class,
                RegistrarRespuestaBienFL.NOMBRE_BEAN);
        registrarRespuestaBienFL.setActivarSolCoactivo(false);
    }

    /**********************
     * Expresiones regulares
     **********************/
    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }
}
