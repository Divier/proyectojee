package co.com.datatools.c2.managed_bean.administracion.parametros;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.AdminParametrosDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionParametrosEJB;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;

/**
 * Se encarga de consultar los jobs de la aplicacion
 * 
 * @author divier.casas
 * 
 */
@ManagedBean
@SessionScoped
public class AdminParametrosMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AdminParametrosMB.class.getName());
    private static final String NOMBRE_BUNDLE = "labelAdminPara";

    @EJB
    private IRAdministracionParametrosEJB iRAdminPara;

    @EJB
    private IRCoactivo iRCoactivo;

    public void consultarParametros() {
        LOGGER.debug("AdminParametrosMB::consultarParametros()");
        AdminParametrosHolderFL adminParaHolderFL = findFlowObject(AdminParametrosHolderFL.class,
                AdminParametrosHolderFL.NOMBRE_BEAN);
        adminParaHolderFL.setLsParametrosDTO(null);
        adminParaHolderFL.setParaSeleccionado(null);

        List<AdminParametrosDTO> lsRespuesta = iRAdminPara.consultarParametros();

        if (lsRespuesta == null || lsRespuesta.isEmpty()) {
            addErrorMessage(NOMBRE_BUNDLE, "msg_error_no_ejecuciones");
            adminParaHolderFL.setLsParametrosDTO(new ArrayList<AdminParametrosDTO>());
            return;
        } else {
            adminParaHolderFL.setLsParametrosDTO(lsRespuesta);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lsRespuesta.size());
        }
    }

    /**
     * genera documentos para coactivo los 6000 registros (eliminar)
     * 
     * @author Jeison.Rodriguez
     */
    public void generarDocumentos() {
        LOGGER.debug("AdminParametrosMB::generarDocumentos()");
        AdminParametrosHolderFL adminParaHolderFL = findFlowObject(AdminParametrosHolderFL.class,
                AdminParametrosHolderFL.NOMBRE_BEAN);

        iRCoactivo.generarDocumentosCoactivosAnteriores();

    }

    /**
     * consulta los coactivos de los 6000 registros y los envia al ws de axis
     * 
     * @author Jeison.Rodriguez
     * @throws CirculemosNegocioException
     */
    public void ejecutarWSCoactivo() throws CirculemosNegocioException {
        LOGGER.debug("AdminParametrosMB::ejecutarWSCoactivo()");
        AdminParametrosHolderFL adminParaHolderFL = findFlowObject(AdminParametrosHolderFL.class,
                AdminParametrosHolderFL.NOMBRE_BEAN);
        // consulta los coactivos y los envia el servicio axis
        iRCoactivo.consultarCoactivosXLS();
        ;

    }

}