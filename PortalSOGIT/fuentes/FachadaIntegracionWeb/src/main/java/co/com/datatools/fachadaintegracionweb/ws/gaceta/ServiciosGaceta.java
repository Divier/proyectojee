package co.com.datatools.fachadaintegracionweb.ws.gaceta;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import co.com.datatools.fachadainetegracionweb.dto.DetalleGacetaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FechaPublicacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.FiltrosGacetaDTO;
import co.com.datatools.fachadainetegracionweb.dto.GacetaDTO;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaGaceta;

@Path("/")
@Stateless
public class ServiciosGaceta {

    @EJB
    private IRFachadaGaceta iRFachadaGaceta;

    @POST
    @Path("/consultarGaceta")
    @Consumes("application/xml")
    public GacetaDTO consultarGaceta(@BeanParam FechaPublicacionDTO fechaPublicacionDTO) {
        return iRFachadaGaceta.consultarGaceta(fechaPublicacionDTO.getFechaPublicacion());
    }

    @POST
    @Path("/consultarDetalleGacetaFiltros")
    @Consumes("application/xml")
    public List<DetalleGacetaDTO> consultarDetalleGacetaFiltros(@BeanParam FiltrosGacetaDTO filtrosGacetaDTO) {
        return iRFachadaGaceta.consultarDetalleGaceta(filtrosGacetaDTO);
    }

    @POST
    @Path("/consultarDetalleGaceta")
    @Consumes("application/xml")
    public List<DetalleGacetaDTO> consultarDetalleGaceta(@BeanParam FechaPublicacionDTO fechaPublicacionDTO) {
        return iRFachadaGaceta.consultarDetalleGaceta(fechaPublicacionDTO.getFechaPublicacion());
    }

    @GET
    @Path("/consultarFechasPublicacion")
    @Produces("application/xml")
    public List<FechaPublicacionDTO> consultarFechasPublicacion() {
        return iRFachadaGaceta.consultarFechasPublicacion();
    }
}
