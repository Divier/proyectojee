package co.com.datatools.c2.negocio.ejb.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.dto.comparendo.CausalInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfraccionDTO;
import co.com.datatools.c2.entidades.CausalInfraccion;
import co.com.datatools.c2.entidades.TipoInfraccion;
import co.com.datatools.c2.entidades.TipoSancion;
import co.com.datatools.c2.negocio.helpers.comparendos.CausalInfraccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoSancionHelper;
import co.com.datatools.c2.negocio.helpers.extencion.TipoInfraccionHelperExtend;
import co.com.datatools.c2.negocio.interfaces.administracion.ILAdministracionInfraccion;
import co.com.datatools.c2.negocio.interfaces.administracion.IRAdministracionInfraccion;

/**
 * Session Bean implementation class AdministracionInfraccionEJB
 */
@Stateless
public class AdministracionInfraccionEJB implements IRAdministracionInfraccion, ILAdministracionInfraccion {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<CausalInfraccionDTO> consultarCausalesInfraccion(int codigoOrganismo) {
        List<CausalInfraccionDTO> lstCausalesInfraccion = new ArrayList<CausalInfraccionDTO>(0);

        // SELECT ci FROM CausalInfraccion AS ci WHERE ci.organismoTransito.codigoOrganismo = :pCodOrganismo ORDER BY ci.nombre
        final TypedQuery<CausalInfraccion> query = em.createNamedQuery(
                CausalInfraccion.CONSULTA_CAUSALES_INFR_X_ORGANISMO, CausalInfraccion.class);
        // TODO QUERIES POR CAMBIOS EN ENTIDAD DE CAUSAL query.setParameter("pCodOrganismo", codigoOrganismo);
        final List<CausalInfraccion> lstRespuesta = query.getResultList();
        if (!lstRespuesta.isEmpty()) {
            lstCausalesInfraccion = CausalInfraccionHelper.toListLevel0DTO(lstRespuesta);
        }

        return lstCausalesInfraccion;
    }

    @Override
    public List<TipoSancionDTO> consultarTiposSancion(int idPais) {
        List<TipoSancionDTO> lstTiposSanscion = new ArrayList<TipoSancionDTO>(0);

        // SELECT ts FROM TipoSancion AS ts WHERE ts.pais.id = :pIdPais ORDER BY ts.nombre
        final TypedQuery<TipoSancion> query = em.createQuery("SELECT ts FROM TipoSancion ts", TipoSancion.class);
        // query.setParameter("pIdPais", idPais);
        final List<TipoSancion> lstRespuesta = query.getResultList();
        if (!lstRespuesta.isEmpty()) {
            lstTiposSanscion = TipoSancionHelper.toListLevel0DTO(lstRespuesta);
        }

        return lstTiposSanscion;
    }

    @Override
    public List<TipoInfraccionDTO> consultarTiposInfraccion() {
        List<TipoInfraccionDTO> listTipoInfraccionDTOs = new ArrayList<TipoInfraccionDTO>(0);

        final TypedQuery<TipoInfraccion> query = em.createNamedQuery(TipoInfraccion.CONSULTAR_TIPOS_INFRACCION,
                TipoInfraccion.class);

        final List<TipoInfraccion> listTipoInfraccions = query.getResultList();
        if (!listTipoInfraccions.isEmpty()) {
            listTipoInfraccionDTOs = TipoInfraccionHelperExtend.toListLevel1DTO(listTipoInfraccions);
        }
        return listTipoInfraccionDTOs;
    }

}
