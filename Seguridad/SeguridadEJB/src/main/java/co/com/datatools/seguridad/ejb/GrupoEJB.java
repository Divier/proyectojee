package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.entidades.Grupo;
import co.com.datatools.seguridad.entidades.Rol;
import co.com.datatools.seguridad.entidades.Usuario;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.helper.GrupoHelper;
import co.com.datatools.seguridad.interfaces.IRGrupo;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumClaseGrupo;
import co.com.datatools.util.GenericDao;

/**
 * Implementacion de operaciones asociadas a grupo
 * 
 * @author Felipe Martinez
 */
@Stateless(name = "GrupoEJB")
@LocalBean
public class GrupoEJB implements IRGrupo {

    private final static Logger logger = Logger.getLogger(GrupoEJB.class.getName());

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    @EJB
    private UsuarioEJB ilUsuario;

    @EJB
    private RolEJB ilRol;

    public GrupoEJB() {
        logger.debug("GrupoEJB::GrupoEJB");
    }

    @Override
    public List<GrupoDto> consultarGrupos(GrupoDto grupoDto) {
        List<GrupoDto> gruposDto = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT g FROM Grupo g WHERE 1=1 ");
        if (StringUtils.isNotBlank(grupoDto.getNombre())) {
            sql.append(" AND g.nombre LIKE :nombre");
        }
        if (grupoDto.getActivo() != null) {
            sql.append(" AND g.activo = :estado");
        }
        if (StringUtils.isNotBlank(grupoDto.getClase())) {
            sql.append(" AND g.clase = :clase");
        }
        TypedQuery<Grupo> consulta = em.createQuery(sql.toString(), Grupo.class);
        if (StringUtils.isNotBlank(grupoDto.getNombre())) {
            consulta.setParameter("nombre", "%" + grupoDto.getNombre() + "%");
        }
        if (grupoDto.getActivo() != null) {
            consulta.setParameter("estado", grupoDto.getActivo());
        }
        if (StringUtils.isNotBlank(grupoDto.getClase())) {
            consulta.setParameter("clase", EnumClaseGrupo.valueOf(grupoDto.getClase()));
        }
        List<Grupo> grupos = consulta.getResultList();
        if (grupos != null) {
            gruposDto = new GrupoHelper().toListDto(grupos);
        }

        logger.debug("Se consultaron: " + gruposDto.size() + " grupos");
        return gruposDto;
    }

    @Override
    public GrupoDto consultarGrupo(final Integer idGrupo) {
        Grupo grupo = em.find(Grupo.class, idGrupo);
        logger.debug("Se consultó el grupo con Id= " + idGrupo + " -" + grupo.getNombre());
        return new GrupoHelper().toDto(grupo, new GrupoDto());
    }

    @Override
    public Integer registrarGrupo(final GrupoDetalleDto grupo) throws SeguridadException {
        Grupo entidadGr = new Grupo();
        entidadGr.setActivo(grupo.getActivo());
        entidadGr.setNombre(grupo.getNombre());
        entidadGr.setDescripcion(grupo.getDescripcion());
        entidadGr.setClase(EnumClaseGrupo.valueOf(grupo.getClase()));
        GrupoDto grupoFiltros = new GrupoDto();
        grupoFiltros.setNombre(grupo.getNombre());
        List<Grupo> gruposEncontrados = new GenericDao<Grupo>(Grupo.class, em).findByAttribute("nombre",
                grupo.getNombre());
        if (!CollectionUtils.isEmpty(gruposEncontrados)) {
            logger.info("No se pudo registrar el grupo= " + entidadGr.getNombre() + " -"
                    + CatalogoError.GRUPO_YA_EXISTE.getDescError());
            throw new SeguridadException(CatalogoError.GRUPO_YA_EXISTE);
        }
        em.persist(entidadGr);
        logger.debug("Se registró el grupo con Id= " + entidadGr.getIdGrupo() + " -" + entidadGr.getNombre());
        return entidadGr.getIdGrupo();
    }

    @Override
    public void eliminarGrupo(final Integer idGrupo) throws SeguridadException {
        if (validarGrupoAsociadoUsuarios(idGrupo)) {
            logger.debug("No se pudo eliminar el grupo con Id= " + idGrupo + " -"
                    + CatalogoError.GRUPO_VS_USUARIO.getDescError());
            throw new SeguridadException(CatalogoError.GRUPO_VS_USUARIO);
        } else {
            if (validarGrupoAsociadoRoles(idGrupo)) {
                logger.debug("No se pudo eliminar el grupo con Id= " + idGrupo + " -"
                        + CatalogoError.GRUPO_VS_ROL.getDescError());
                throw new SeguridadException(CatalogoError.GRUPO_VS_ROL);
            }
        }
        Grupo entidadGrupo = em.find(Grupo.class, idGrupo);
        em.remove(entidadGrupo);
        logger.debug("Se eliminó el grupo con Id= " + entidadGrupo.getIdGrupo() + " -" + entidadGrupo.getNombre());
    }

    @Override
    public void actualizarGrupo(final GrupoDetalleDto grupo) throws SeguridadException {
        // Validar que no exista un grupo con el mismo nombre
        GrupoDto grupoFiltros = new GrupoDto();
        grupoFiltros.setNombre(grupo.getNombre());

        List<Grupo> gruposEncontrados = new GenericDao<Grupo>(Grupo.class, em).findByAttribute("nombre",
                grupo.getNombre());
        if (!CollectionUtils.isEmpty(gruposEncontrados)
                && !gruposEncontrados.get(0).getIdGrupo().equals(grupo.getIdGrupo())) {
            logger.info("No se pudo modificar el grupo: " + grupo.getNombre() + " -"
                    + CatalogoError.GRUPO_YA_EXISTE.getDescError());
            throw new SeguridadException(CatalogoError.GRUPO_YA_EXISTE);
        }

        Grupo entidadGrupo = em.find(Grupo.class, grupo.getIdGrupo());
        if (!grupo.getClase().equals(entidadGrupo.getClase().name())) {
            // Si se cambio la clase validar que no hayan actualmente grupos o usuarios ya asociados a dicho grupo
            if (validarGrupoAsociadoUsuarios(entidadGrupo.getIdGrupo())) {
                logger.debug("No se pudo modificar el grupo con Id= " + entidadGrupo.getIdGrupo() + " -"
                        + CatalogoError.GRUPO_VS_USUARIO.getDescError());
                throw new SeguridadException(CatalogoError.GRUPO_VS_USUARIO_MODIFICACION);
            } else {
                if (validarGrupoAsociadoRoles(entidadGrupo.getIdGrupo())) {
                    logger.debug("No se pudo modificar el grupo con Id= " + entidadGrupo.getIdGrupo() + " -"
                            + CatalogoError.GRUPO_VS_ROL.getDescError());
                    throw new SeguridadException(CatalogoError.GRUPO_VS_ROL_MODIFICACION);
                }
            }
        }
        // Actualizar el grupo
        Grupo entidadModificada = new GrupoHelper().toEntity(grupo, entidadGrupo);
        em.merge(entidadModificada);
    }

    /**
     * Valida si el grupo con ID enviado esta asociado con usuarios en el sistema
     * 
     * @param idGrupo
     *            ID del grupo a validar
     * @return true si el grupo esta asociado a usuarios de lo contrario false
     */
    public boolean validarGrupoAsociadoUsuarios(Integer idGrupo) {
        TypedQuery<Usuario> queryUsuariosByGrupo = em.createNamedQuery(Usuario.SQ_USUARIO_BY_GRUPO, Usuario.class);
        queryUsuariosByGrupo.setParameter("idGrupo", idGrupo);
        List<Usuario> usuariosByGrupo = queryUsuariosByGrupo.getResultList();
        if (!CollectionUtils.isEmpty(usuariosByGrupo)) {
            return true;
        }
        return false;
    }

    /**
     * Valida si el grupo con ID enviado esta asociado con roles en el sistema
     * 
     * @param idGrupo
     *            ID del grupo a validar
     * @return true si el grupo esta asociado a roles de lo contrario false
     */
    public boolean validarGrupoAsociadoRoles(Integer idGrupo) {
        TypedQuery<Rol> queryRolByGrupo = em.createNamedQuery(Rol.SQ_ROL_BY_GRUPO, Rol.class);
        queryRolByGrupo.setParameter("idGrupo", idGrupo);
        List<Rol> rolesByGrupo = queryRolByGrupo.getResultList();
        if (!CollectionUtils.isEmpty(rolesByGrupo)) {
            return true;
        }
        return false;
    }

}
