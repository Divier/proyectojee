package co.com.datatools.fachadaintegracionweb.ws.comparendos;

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

import co.com.datatools.fachadainetegracionweb.dto.ComparendoConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ComparendoReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ComparendoReplicaDetalleDTO;
import co.com.datatools.fachadainetegracionweb.dto.EvidenciasDTO;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaComparendos;

@Path("/")
@Stateless
public class ServiciosComparendos {

    @EJB
    private IRFachadaComparendos comparendosEJB;

    @POST
    @Path("/consultarComparendos")
    @Consumes("application/xml")
    public List<ComparendoReplicaDTO> consultarComparendos(@BeanParam ComparendoConsultaDTO comparendoConsultaDto) {
        return comparendosEJB.consultarComparendos(comparendoConsultaDto);
    }

    @GET
    @Path("/consultarDetalleComparendo/{numeroComparendo}")
    @Produces("application/xml")
    public ComparendoReplicaDetalleDTO consultarDetalleComparendo(
            @PathParam("numeroComparendo") String numeroComparendo) {
        return comparendosEJB.consultarDetalleComparendo(numeroComparendo);
    }

    @GET
    @Path("/rutaEvidencias/{facturaAxis}")
    @Produces("application/xml")
    public List<EvidenciasDTO> rutaEvidencias(@PathParam("facturaAxis") Long FacturaAxis) {
        return comparendosEJB.rutaEvidencias(FacturaAxis);
    }
}
