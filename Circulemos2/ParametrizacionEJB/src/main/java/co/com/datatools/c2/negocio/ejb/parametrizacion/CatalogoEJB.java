package co.com.datatools.c2.negocio.ejb.parametrizacion;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.entidades.Catalogo;
import co.com.datatools.c2.entidades.util.EntidadCatalogoC2;
import co.com.datatools.c2.entidades.util.EntidadCatalogoCompuestoC2;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.error.parametrizacion.ErrorParametrizacion;
import co.com.datatools.c2.negocio.helpers.parametrizacion.CatalogoHelper;
import co.com.datatools.c2.negocio.helpers.parametrizacion.ItemCatalogoHelper;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.ILCatalogo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.util.GenericDao;

/**
 * Session Bean implementation class CatalogoEJB
 */
@Stateless(name = "CatalogoEJB")
@LocalBean
public class CatalogoEJB implements ILCatalogo, IRCatalogo {

    private final static Logger logger = Logger.getLogger(CatalogoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public void registrarItemCatalogo(CatalogoDTO catalogoDTO, List<ItemCatalogoDTO> itemCatalogoDTOs)
            throws CirculemosNegocioException {
        EntidadCatalogoC2 entidadCatalogoC2;
        try {
            for (ItemCatalogoDTO item : itemCatalogoDTOs) {
                entidadCatalogoC2 = ItemCatalogoHelper.toLevel1Entity(item, catalogoDTO);
                if (entidadCatalogoC2 instanceof EntidadCatalogoCompuestoC2) {
                    validarItemsCatalogo(catalogoDTO, itemCatalogoDTOs);
                } else {
                    boolean existeNombre = validarExistenciaItemXNombre(catalogoDTO, item);
                    boolean existeSigla = validarExistenciaItemXSigla(catalogoDTO, item);

                    if (existeNombre || existeSigla) {
                        throw new CirculemosNegocioException(ErrorParametrizacion.AdministrarCatalogoSimple.ADM_063001);
                    }
                }
                em.persist(entidadCatalogoC2);
            }
            logger.debugv("CatalogoEJB::registrarItemsCatalogo");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new CirculemosRuntimeException("Error al buscar o instanciar la clase: {0}.{1}",
                    catalogoDTO.getPaqueteEntidad(), catalogoDTO.getNombreEntidad());
        }
    }

    @Override
    public ItemCatalogoDTO validarItemsCatalogo(CatalogoDTO catalogoDTO, List<ItemCatalogoDTO> itemCatalogoDTOs) {
        ItemCatalogoDTO itemCatalogoDTO = null;
        boolean existe = false;
        for (ItemCatalogoDTO itemCatalogoTempDTO : itemCatalogoDTOs) {
            // Validacion para el nombre con la dependencia
            existe = validarExistenciaItemXNombreYDependencia(catalogoDTO, itemCatalogoTempDTO);
            if (existe) {
                return itemCatalogoTempDTO;
            }

            // Validacion para la sigla con la dependencia
            existe = validarExistenciaItemXSiglaYDependencia(catalogoDTO, itemCatalogoTempDTO);
            if (existe) {
                return itemCatalogoTempDTO;
            }
        }
        return itemCatalogoDTO;
    }

    /**
     * Valida que el item no se encuentre registrado con ese nombre
     * 
     * @param entidadCatalogo
     * @param nombre
     * @author giovanni.velandia
     * @return boolean
     */
    private boolean validarExistenciaItemXNombre(CatalogoDTO catalogoDTO, ItemCatalogoDTO itemCatalogoDTO) {

        GenericDao<ItemCatalogoDTO> genericDao = new GenericDao<>(ItemCatalogoDTO.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT ec FROM " + catalogoDTO.getNombreEntidad() + " ec");
        jpql.append(" WHERE 1 = 1");

        // Nombre
        if (StringUtils.isNotBlank(itemCatalogoDTO.getNombre())) {
            jpql.append(" AND ec.nombre = :nombre");
            filtros.put("nombre", itemCatalogoDTO.getNombre());
        }

        List<ItemCatalogoDTO> itemCatalogoBDDTO = genericDao.buildAndExecuteQuery(jpql, filtros);
        if (!itemCatalogoBDDTO.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Valida que el item no se encuentre registrado con ese nombre y esa dependencia en el caso de los catalogos compuestos
     * 
     * @param catalogoDTO
     * @param nombre
     * @param idDependencia
     * @author giovanni.velandia
     * @return boolean
     */
    private boolean validarExistenciaItemXNombreYDependencia(CatalogoDTO catalogoDTO, ItemCatalogoDTO itemCatalogoDTO) {

        if (StringUtils.isNotBlank(itemCatalogoDTO.getNombre())) {
            StringBuilder jpql = new StringBuilder();
            jpql.append("SELECT ec FROM " + catalogoDTO.getNombreEntidad() + " ec");
            jpql.append(" WHERE 1 = 1");
            jpql.append(" AND ec.nombre = :nombre");

            // Dependencia
            if (itemCatalogoDTO.getItemCatalogoDependenciaDTO().getId() != null) {
                jpql.append(" AND ec."
                        + catalogoDTO.getCatalogoDependenciaDTO().getNombreEntidad().substring(0, 1).toLowerCase()
                        + catalogoDTO.getCatalogoDependenciaDTO().getNombreEntidad().substring(1,
                                catalogoDTO.getCatalogoDependenciaDTO().getNombreEntidad().length())
                        + ".id = :idDependencia");
            }

            Query query = em.createQuery(jpql.toString());
            query.setParameter("nombre", itemCatalogoDTO.getNombre());

            // Dependencia
            if (itemCatalogoDTO.getItemCatalogoDependenciaDTO().getId() != null) {
                query.setParameter("idDependencia", itemCatalogoDTO.getItemCatalogoDependenciaDTO().getId());
            }

            @SuppressWarnings("unchecked")
            List<Object> resultados = query.getResultList();
            if (resultados != null && !resultados.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida que el item no se encuentre registrado con ese sigla
     * 
     * @param entidadCatalogo
     * @param sigla
     * @author giovanni.velandia
     * @return boolean
     */
    private boolean validarExistenciaItemXSigla(CatalogoDTO catalogoDTO, ItemCatalogoDTO itemCatalogoDTO) {

        GenericDao<ItemCatalogoDTO> genericDao = new GenericDao<>(ItemCatalogoDTO.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT ec FROM " + catalogoDTO.getNombreEntidad() + " ec");
        jpql.append(" WHERE 1 = 1");

        // Sigla
        if (StringUtils.isNotBlank(itemCatalogoDTO.getSigla())) {
            jpql.append(" AND ec.sigla = :sigla");
            filtros.put("sigla", itemCatalogoDTO.getSigla());
        }

        List<ItemCatalogoDTO> itemCatalogoBDDTO = genericDao.buildAndExecuteQuery(jpql, filtros);
        if (!itemCatalogoBDDTO.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Valida que el item no se encuentre registrado con ese sigla y esa dependencia en el caso de los catalogos compuestos
     * 
     * @param catalogoDTO
     * @param nombre
     * @param idDependencia
     * @author giovanni.velandia
     * @return boolean
     */
    private boolean validarExistenciaItemXSiglaYDependencia(CatalogoDTO catalogoDTO, ItemCatalogoDTO itemCatalogoDTO) {

        if (StringUtils.isNotBlank(itemCatalogoDTO.getSigla())) {
            StringBuilder jpql = new StringBuilder();
            jpql.append("SELECT ec FROM " + catalogoDTO.getNombreEntidad() + " ec");
            jpql.append(" WHERE 1 = 1");
            jpql.append(" AND ec.sigla = :sigla");

            // Dependencia
            if (itemCatalogoDTO.getItemCatalogoDependenciaDTO().getId() != null) {
                jpql.append(" AND ec."
                        + catalogoDTO.getCatalogoDependenciaDTO().getNombreEntidad().substring(0, 1).toLowerCase()
                        + catalogoDTO.getCatalogoDependenciaDTO().getNombreEntidad().substring(1,
                                catalogoDTO.getCatalogoDependenciaDTO().getNombreEntidad().length())
                        + ".id = :idDependencia");
            }

            Query query = em.createQuery(jpql.toString());
            query.setParameter("sigla", itemCatalogoDTO.getSigla());

            // Dependencia
            if (itemCatalogoDTO.getItemCatalogoDependenciaDTO().getId() != null) {
                query.setParameter("idDependencia", itemCatalogoDTO.getItemCatalogoDependenciaDTO().getId());
            }

            @SuppressWarnings("unchecked")
            List<Object> resultados = query.getResultList();
            if (resultados != null && !resultados.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<CatalogoDTO> consultarCatalogos(CatalogoDTO catalogoDTO) {
        List<CatalogoDTO> resultado = new ArrayList<CatalogoDTO>();
        GenericDao<Catalogo> catalogoDao = new GenericDao<>(Catalogo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT c FROM Catalogo c");
        jpql.append(" WHERE 1 = 1");
        if (StringUtils.isNotBlank(catalogoDTO.getNombre())) {
            jpql.append(" AND c.nombre like :nombreCatalogo");
            filtros.put("nombreCatalogo", "%" + catalogoDTO.getNombre() + "%");
        }
        if (StringUtils.isNotBlank(catalogoDTO.getCodigo())) {
            jpql.append(" AND c.codigo = :codigoCatalogo");
            filtros.put("codigoCatalogo", catalogoDTO.getCodigo());
        }
        if (StringUtils.isNotBlank(catalogoDTO.getNombreEntidad())) {
            jpql.append(" AND c.nombreEntidad = :nombreEntidad");
            filtros.put("nombreEntidad", catalogoDTO.getNombreEntidad());
        }

        // filtro para determinar si es un catalogo compuesto
        if (catalogoDTO.getIdCatalogoDependencia()) {
            jpql.append(" AND c.idCatalogoDependencia IS NOT NULL");
        } else {
            jpql.append(" AND c.idCatalogoDependencia IS NULL");
        }
        List<Catalogo> resultadoConsulta = catalogoDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            if (catalogoDTO.getIdCatalogoDependencia()) {
                for (Catalogo catalogo : resultadoConsulta) {
                    resultado.add(CatalogoHelper.toLevel1DTO(catalogo));
                }
            } else {
                for (Catalogo catalogo : resultadoConsulta) {
                    resultado.add(CatalogoHelper.toLevel0DTO(catalogo));
                }
            }
        }
        return resultado;
    }

    @Override
    public List<ItemCatalogoDTO> consultarItemsCatalogo(String entidadCatalogo, ItemCatalogoDTO itemCatalogoDTO) {
        CatalogoEJB.logger.debug("CatalogoEJB.consultarItemsCatalogo");
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

    @Override
    public void actualizarCatalogo(CatalogoDTO catalogoDTO) {
        em.merge(CatalogoHelper.toLevel1Entity(catalogoDTO, null));
        logger.debugv("CatalogoEJB::actualizarCatalogo: Se registra la numeracion con el id={0}", catalogoDTO.getId());
    }

    @Override
    public void actualizarItemCatalogo(String entidadCatalogo, String paqueteEntidad, ItemCatalogoDTO item)
            throws CirculemosNegocioException {
        EntidadCatalogoC2 entidadCatalogoC2;
        try {
            entidadCatalogoC2 = ItemCatalogoHelper.toLevel0Entity(item, entidadCatalogo, paqueteEntidad);

            CatalogoDTO catalogoDTO = new CatalogoDTO();
            catalogoDTO.setNombreEntidad(entidadCatalogo);
            boolean existeSigla = validarExistenciaItemXSigla(catalogoDTO, item);
            if (existeSigla) {
                em.merge(entidadCatalogoC2);
            } else {
                throw new CirculemosNegocioException(ErrorParametrizacion.AdministrarCatalogoSimple.ADM_063001);
            }

            logger.debugv("CatalogoEJB::actualizarItemCatalogo: Se registra la numeracion con el id={0}",
                    entidadCatalogoC2.getId());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new CirculemosRuntimeException("Error al buscar o instanciar la clase: {0}.{1}", paqueteEntidad,
                    entidadCatalogo);
        }
    }

    @Override
    public void eliminarItemCatalogo(String entidadCatalogo, String paqueteEntidad, int idItem)
            throws CirculemosNegocioException {

        // Validar entidad del catalogo
        checkNotNull(entidadCatalogo, "Se esperaba nombre de entidad de catalogo");

        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append("SELECT ec FROM " + entidadCatalogo + " ec");
            jpql.append(" WHERE ec.id = :id");

            Query query = em.createQuery(jpql.toString());
            query.setParameter("id", idItem);

            Object object = query.getSingleResult();
            em.remove(object);
            em.flush();
        } catch (Exception e) {
            throw new CirculemosNegocioException(ErrorParametrizacion.AdministrarCatalogoSimple.ADM_063002);
        }
        logger.debugv("CatalogoEJB::eliminarItemCatalogo: Se elimino la numeracion con el id={0}", idItem);
    }

    @Override
    public CatalogoDTO consultarCatalogoPorId(int idCatalogo) {
        CatalogoDTO catalogoDTO = new CatalogoDTO();
        Catalogo catalogo = em.find(Catalogo.class, idCatalogo);
        if (catalogo.getIdCatalogoDependencia() == null) {
            catalogoDTO = CatalogoHelper.toLevel0DTO(catalogo);
        } else {
            catalogoDTO = CatalogoHelper.toLevel1DTO(catalogo);
        }
        return catalogoDTO;
    }

}
