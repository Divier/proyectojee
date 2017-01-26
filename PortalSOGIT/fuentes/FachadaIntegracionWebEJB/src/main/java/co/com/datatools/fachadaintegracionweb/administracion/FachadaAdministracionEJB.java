package co.com.datatools.fachadaintegracionweb.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoDTO;
import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoReplicaDTO;
import co.com.datatools.fachadainetegracionweb.enumeracion.ItemCatalogoDTOHelper;
import co.com.datatools.fachadainetegracionweb.interfaces.ILCatalogo;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaAdministracion;

@Stateless
public class FachadaAdministracionEJB implements IRFachadaAdministracion {

    private final static Logger LOGGER = Logger.getLogger(FachadaAdministracionEJB.class.getName());

    @EJB
    private ILCatalogo iLCatalogo;

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<ItemCatalogoReplicaDTO> consultarItemsCatalogo(String entidadCatalogo,
            ItemCatalogoReplicaDTO itemCatalogoRepDTO) {
        LOGGER.debug("consultarItemsCatalogo(String,int)");

        List<ItemCatalogoReplicaDTO> itemCatalogoReplicaDTOs = new ArrayList<ItemCatalogoReplicaDTO>();
        List<ItemCatalogoDTO> itemCatalogoDTOs = null;
        if (itemCatalogoRepDTO != null) {
            ItemCatalogoDTO itemCatalDTO = ItemCatalogoDTOHelper.toItemCatalogoDTO(itemCatalogoRepDTO);
            itemCatalogoDTOs = iLCatalogo.consultarItemsCatalogo(entidadCatalogo, itemCatalDTO);
            for (ItemCatalogoDTO itemCatalogoDTO : itemCatalogoDTOs) {
                ItemCatalogoReplicaDTO itemCatalogoReplicaDTO = ItemCatalogoDTOHelper
                        .toItemCatalogoReplicaDTO(itemCatalogoDTO);
                itemCatalogoReplicaDTOs.add(itemCatalogoReplicaDTO);
            }
        }

        itemCatalogoDTOs = iLCatalogo.consultarItemsCatalogo(entidadCatalogo, null);
        for (ItemCatalogoDTO itemCatalogoDTO : itemCatalogoDTOs) {
            ItemCatalogoReplicaDTO itemCatalogoReplicaDTO = ItemCatalogoDTOHelper
                    .toItemCatalogoReplicaDTO(itemCatalogoDTO);
            itemCatalogoReplicaDTOs.add(itemCatalogoReplicaDTO);
        }

        return itemCatalogoReplicaDTOs;
    }

    @Override
    public String consultarValorParametro(Integer parametro) {
        LOGGER.debug("consultarValorParametro(Tnteger)");
        String valorParametro = null;

        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("valor_parametro_defecto ");
        sql.append("FROM parametro ");
        sql.append("WHERE codigo_parametro = :valorParametro");

        Query query = em.createNativeQuery(sql.toString());

        query.setParameter("valorParametro", parametro);

        @SuppressWarnings("unchecked")
        List<String> objects = query.getResultList();
        if (objects != null) {
            for (String objeto : objects) {
                valorParametro = objeto;
            }
        }

        return valorParametro;
    }

    @Override
    public String consultarValorParametro(Integer parametro, Integer codigoOrganismo) {
        LOGGER.debug("consultarValorParametro(Tnteger)");
        String valorParametro = null;

        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("valor_parametro ");
        sql.append("FROM parametro_organismo ");
        sql.append("WHERE codigo_parametro = :valorParametro ");
        sql.append("AND codigo_organismo = :codigoOrganismo");

        Query query = em.createNativeQuery(sql.toString());

        query.setParameter("valorParametro", parametro);
        query.setParameter("codigoOrganismo", codigoOrganismo);

        @SuppressWarnings("unchecked")
        List<String> objects = query.getResultList();
        if (objects != null) {
            for (String objeto : objects) {
                valorParametro = objeto;
            }
        }

        return valorParametro;
    }
}
