package co.com.datatools.c2.managed_bean.administracion.procesos_financiacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.negocio.interfaces.IRFinanciacion;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Manejo procesos financiacion
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class ProcesosFinanciacionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(ProcesosFinanciacionMB.class.getName());

    @EJB
    private IRFinanciacion iRFinanciacion;

    /**
     * 
     * @author giovanni.velandia
     */
    public void generarDocumentoConveniosEnFirme() {
        LOGGER.debug("ProcesosFinanciacionMB::generarDocumentoConveniosEnFirme()");
        iRFinanciacion.generarDocumentoFirmeFinanciacionMasivo();
    }

    /**
     * 
     * @author giovanni.velandia
     */
    public void generarConveniosEnFirme() {
        LOGGER.debug("ProcesosFinanciacionMB::generarConveniosEnFirme()");
        iRFinanciacion.dejarFirmeFinanciacionMasivo();
    }

}
