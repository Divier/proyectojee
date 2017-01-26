package co.com.datatools.fachadaintegracionweb.administracion;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoDTO;
import co.com.datatools.fachadainetegracionweb.interfaces.ILCatalogo;
import co.com.datatools.fachadainetegracionweb.interfaces.IRCatalogo;
import co.com.datatools.fachadaintegracionweb.entidades.EntidadCatalogoC2;
import co.com.datatools.fachadaintegracionweb.entidades.EntidadCatalogoCompuestoC2;
import co.com.datatools.fachadaintegracionweb.helpers.ItemCatalogoHelper;

/**
 * Session Bean implementation class CatalogoEJB
 */
@Stateless(name = "CatalogoEJB")
@LocalBean
public class CatalogoEJB implements ILCatalogo, IRCatalogo {

    private final static Logger LOGGER = Logger.getLogger(CatalogoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<ItemCatalogoDTO> consultarItemsCatalogo(String entidadCatalogo, ItemCatalogoDTO itemCatalogoDTO) {
        LOGGER.debug("consultarItemsCatalogo(String,ItemCatalogoDTO)");
        boolean dependencia = false;

        // Validar entidad del catalogo
        checkNotNull(entidadCatalogo, "Se esperaba nombre de entidad de catalogo");

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT c FROM " + entidadCatalogo + " c");
        jpql.append(" WHERE 1 = 1");

        // Validar Filtros
        if (itemCatalogoDTO != null) {
            // Id
            if (itemCatalogoDTO.getId() != null) {
                jpql.append(" AND c.id = :id");
            } else {
                // Estado
                if (itemCatalogoDTO.getActivo() != null) {
                    jpql.append(" AND c.estado = :estado");
                }
                // Sigla
                if (StringUtils.isNotBlank(itemCatalogoDTO.getSigla())) {
                    jpql.append(" AND c.sigla = :sigla");
                }
                // Nombre
                if (StringUtils.isNotBlank(itemCatalogoDTO.getNombre())) {
                    jpql.append(" AND  c.nombre LIKE :nombre");
                }

                // Codigo
                if (StringUtils.isNotBlank(itemCatalogoDTO.getCodigo())) {
                    jpql.append(" AND  c.codigo = :codigo");
                }

                // dependencia
                if (itemCatalogoDTO.getItemCatalogoDependenciaDTO() != null
                        && itemCatalogoDTO.getItemCatalogoDependenciaDTO().getId() != null) {
                    dependencia = true;
                }

            }
        }

        Query query = em.createQuery(jpql.toString());

        /*
         * Filtros
         */
        if (itemCatalogoDTO != null) {
            // Id
            if (itemCatalogoDTO.getId() != null) {
                query.setParameter("id", itemCatalogoDTO.getId());
            } else {
                // Estado
                if (itemCatalogoDTO.getActivo() != null) {
                    query.setParameter("estado", itemCatalogoDTO.getActivo());
                }

                // Sigla
                if (StringUtils.isNotBlank(itemCatalogoDTO.getSigla())) {
                    query.setParameter("sigla", itemCatalogoDTO.getSigla());
                }

                // Codigo
                if (StringUtils.isNotBlank(itemCatalogoDTO.getCodigo())) {
                    query.setParameter("codigo", itemCatalogoDTO.getCodigo());
                }

                // Nombre
                if (StringUtils.isNotBlank(itemCatalogoDTO.getNombre())) {
                    query.setParameter("nombre", "%" + itemCatalogoDTO.getNombre() + "%");
                }
            }
        }

        @SuppressWarnings("unchecked")
        List<Object> resultados = query.getResultList();
        List<ItemCatalogoDTO> itemCatalogoDTOs = new ArrayList<ItemCatalogoDTO>();
        boolean ingresoFiltro = false;
        for (Object obj : resultados) {
            EntidadCatalogoC2 entidadCatalogoC2 = (EntidadCatalogoC2) obj;
            ItemCatalogoDTO itemCatalogoTempDTO = new ItemCatalogoDTO();
            if (entidadCatalogoC2 instanceof EntidadCatalogoCompuestoC2) {
                EntidadCatalogoCompuestoC2 entidadCatalogoCompuestoC2 = (EntidadCatalogoCompuestoC2) obj;
                if (!dependencia) {
                    itemCatalogoTempDTO = ItemCatalogoHelper.toLevel1DTO(entidadCatalogoCompuestoC2);
                    ingresoFiltro = true;
                } else {
                    if (itemCatalogoDTO.getItemCatalogoDependenciaDTO().getId()
                            .equals(entidadCatalogoCompuestoC2.getDependencia().getId())) {
                        itemCatalogoTempDTO = ItemCatalogoHelper.toLevel1DTO(entidadCatalogoCompuestoC2);
                        ingresoFiltro = true;
                    } else {
                        ingresoFiltro = false;
                    }
                }
            } else {
                itemCatalogoTempDTO = ItemCatalogoHelper.toLevel0DTO(entidadCatalogoC2);
                ingresoFiltro = true;
            }

            if (ingresoFiltro) {
                itemCatalogoDTOs.add(itemCatalogoTempDTO);
            }
        }
        return itemCatalogoDTOs;
    }
}
