package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.EncabezadoImpugnacionDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.negocio.interfaces.IRImpugnacion;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Managed Bean para el manejo del encabezado de impugnacion
 * 
 * @author dixon.alvarez 2016-06-10
 * 
 */
@ManagedBean
@SessionScoped
public class EncabezadoImpugnacionMB extends AbstractSwfManagedBean {
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(EncabezadoImpugnacionMB.class.getName());
    private static final String ENCABEZADO_IMPUGNACION_HOLDER_FL = "evaluarExpedienteHolderFL";

    @EJB
    private IRImpugnacion irImpugnacion;

    @PostConstruct
    public void init() {
        logger.debug("EncabezadoImpugnacionMB::init()");
    }

    /**
     * Inicializa los datos que se muestran en el encabezado
     * 
     * @param idProceso
     *            Es el filtro con el que se realiza la consulta que genera los datos a mostrar en el encabezado
     */
    public void consultarEncabezado(Long idProceso) {
        logger.debug("EncabezadoImpugnacionMB::consultarEncabezado()");
        ProcesoDTO procesoDTO = new ProcesoDTO();
        procesoDTO.setId(idProceso);
        EncabezadoImpugnacionDTO encabezadoDTO = irImpugnacion.consultarEncabezado(procesoDTO);
        EncabezadoImpugnacionHolderFL encabezadoImpugnacionHolderFL = findFlowObject(
                EncabezadoImpugnacionHolderFL.class, EncabezadoImpugnacionHolderFL.NOMBRE_BEAN);
        encabezadoImpugnacionHolderFL.setCodigoInfraccion(encabezadoDTO.getCodigoInfraccion());
        encabezadoImpugnacionHolderFL.setFechaApertura(encabezadoDTO.getFechaApertura());
        encabezadoImpugnacionHolderFL.setNombreInfractor(encabezadoDTO.getNombreInfractor());
        encabezadoImpugnacionHolderFL.setNumeroComparendo(encabezadoDTO.getNumeroComparendo());
        encabezadoImpugnacionHolderFL.setNumeroProceso(encabezadoDTO.getNumeroProceso());
        encabezadoImpugnacionHolderFL.setDocumentoInfractor(encabezadoDTO.getDocumentoInfractor());
        encabezadoImpugnacionHolderFL.setPlaca(encabezadoDTO.getPlaca());
        encabezadoImpugnacionHolderFL.setDescripcionInfraccion(encabezadoDTO.getDescripcionInfraccion());
        encabezadoImpugnacionHolderFL.setNumeroCitacion(encabezadoDTO.getNumeroCitacion());
    }
}
