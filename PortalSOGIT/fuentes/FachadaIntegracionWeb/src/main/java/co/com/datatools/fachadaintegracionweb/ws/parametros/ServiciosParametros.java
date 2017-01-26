package co.com.datatools.fachadaintegracionweb.ws.parametros;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaAdministracion;

@Path("/")
@Stateless
public class ServiciosParametros {

    @EJB
    private IRFachadaAdministracion administracionEJB;

    @GET
    @Path("/consultarValorParametro/{codigoParametro}")
    @Produces("text/plain")
    public String consultarValorParametro(@PathParam("codigoParametro") Integer codigoParametro) {
        return administracionEJB.consultarValorParametro(codigoParametro);
    }

    @GET
    @Path("/consultarValorParametro/{codigoParametro}/{codigoOrganismo}")
    @Produces("text/plain")
    public String consultarValorParametro(@PathParam("codigoParametro") Integer codigoParametro,
            @PathParam("codigoOrganismo") Integer codigoOrganismo) {
        return administracionEJB.consultarValorParametro(codigoParametro, codigoOrganismo);
    }
}
