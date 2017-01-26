package co.com.datatools.fachadaintegracionweb.ws.financiaciones;

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

import co.com.datatools.fachadainetegracionweb.dto.DetalleFinanciacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FinanciacionConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FinanciacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaFinanciaciones;

@Path("/")
@Stateless
public class ServiciosFinanciaciones {

    @EJB
    IRFachadaFinanciaciones financiacionesEJB;

    @POST
    @Path("/consultarFinanciaciones")
    @Consumes("application/xml")
    public List<FinanciacionReplicaDTO> consultarFinanciaciones(
            @BeanParam FinanciacionConsultaDTO financiacionConsulta) {
        return financiacionesEJB.consultarFinanciaciones(financiacionConsulta);
    }

    @GET
    @Path("/consultarDetalleFinanciacion/{numeroFinanciacion}")
    @Produces("application/xml")
    public DetalleFinanciacionReplicaDTO consultarDetalleFinanciacion(
            @PathParam("numeroFinanciacion") BigDecimal numeroFinanciacion) {
        return financiacionesEJB.consultarDetalleFinanciacion(numeroFinanciacion);
    }
}
