package co.com.datatools.c2.negocio.fachada;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConsultaResolucionesDTO;
import co.com.datatools.c2.dto.ResolucionDTO;
import co.com.datatools.c2.negocio.interfaces.ILResolucion;
import co.com.datatools.c2.negocio.interfaces.Resoluble;

@Stateless(name = "FachadaAdminResolucionEJB")
@LocalBean
public class FachadaAdminResolucionEJB implements IRFachadaAdminResolucion {
    private final static Logger logger = Logger.getLogger(FachadaAdminResolucionEJB.class.getName());

    @EJB
    private ILResolucion iLResolucion;

    @Override
    public void actualizarResolucion(ResolucionDTO resolucion) {
        logger.debug("FachadaAdminResolucionEJB::actualizarResolucion(ResolucionDTO)");
        iLResolucion.actualizarResolucion(resolucion);
    }

    @Override
    public long registrarResolucion(Resoluble resolucion) {
        logger.debug("FachadaAdminResolucionEJB::registrarResolucion(Resoluble)");
        return iLResolucion.generarResolucion(resolucion);
    }

    @Override
    public List<ResolucionDTO> consultarResoluciones(ConsultaResolucionesDTO consulta) {
        logger.debug("FachadaAdminResolucionEJB::consultarResoluciones(ConsultaResolucionesDTO)");
        List<ResolucionDTO> resoluciones = new ArrayList<ResolucionDTO>();
        for (Long idResolucion : consulta.getIdResolucion()) {
            resoluciones.addAll(iLResolucion.consultarResolucion(new ResolucionDTO(idResolucion)));
        }
        return resoluciones;
    }
}
