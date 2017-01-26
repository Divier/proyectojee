package co.com.datatools.fachadaintegracionweb.ws.administracion;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoReplicaDTO;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaAdministracion;

@Path("/")
@Stateless
public class ServiciosAdministracion {

    @EJB
    private IRFachadaAdministracion iRFachadaAdministracion;

    @POST
    @Path("/consultarItemsCatalogo/{entidadCatalogo}")
    @Consumes("application/xml")
    public List<ItemCatalogoReplicaDTO> consultarItemsCatalogo(@PathParam("entidadCatalogo") String entidadCatalogo,
            @BeanParam ItemCatalogoReplicaDTO itemCatalogoReplicaDTO) {
        List<ItemCatalogoReplicaDTO> catalogoReplicaDTOs = iRFachadaAdministracion
                .consultarItemsCatalogo(entidadCatalogo, itemCatalogoReplicaDTO);
        return catalogoReplicaDTOs;
    }
}
