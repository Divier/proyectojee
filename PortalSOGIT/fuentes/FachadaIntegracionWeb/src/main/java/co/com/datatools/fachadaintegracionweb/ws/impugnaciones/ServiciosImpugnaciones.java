package co.com.datatools.fachadaintegracionweb.ws.impugnaciones;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.com.datatools.fachadainetegracionweb.dto.DetalleProcesoImpugnacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.ImpugnacionConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ProcesoImpugnacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaImpugnaciones;

@Path("/")
@Stateless
public class ServiciosImpugnaciones {

    @EJB
    private IRFachadaImpugnaciones impugnacionesEJB;

    @POST
    @Path("/consultarImpugnaciones")
    @Consumes("application/xml")
    public List<ProcesoImpugnacionReplicaDTO> consultarImpugnaciones(
            @BeanParam ImpugnacionConsultaDTO impugnacionConsultaDTO) {
        return impugnacionesEJB.consultarProcesosImpugnacion(impugnacionConsultaDTO);
    }

    @GET
    @Path("/consultarDetalleImpugnacion/{idProceso}")
    @Produces("application/xml")
    public DetalleProcesoImpugnacionDTO consultarDetalleImpugnacion(@PathParam("idProceso") BigDecimal idProceso) {
        return impugnacionesEJB.consultarDetalleImpugnacion(idProceso);
    }
}
