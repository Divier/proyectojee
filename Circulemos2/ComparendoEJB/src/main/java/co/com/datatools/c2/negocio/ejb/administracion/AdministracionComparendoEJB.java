package co.com.datatools.c2.negocio.ejb.administracion;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.EmpresaTransporteDTO;
import co.com.datatools.c2.dto.RutaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;
import co.com.datatools.c2.dto.comparendo.GradoAlcoholemiaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.entidades.ComparendoResolucion;
import co.com.datatools.c2.entidades.EmpresaTransporte;
import co.com.datatools.c2.entidades.GradoAlcoholemia;
import co.com.datatools.c2.entidades.Resolucion;
import co.com.datatools.c2.entidades.Ruta;
import co.com.datatools.c2.enumeraciones.EnumTipoResolucion;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.helpers.comparendos.GradoAlcoholemiaHelper;
import co.com.datatools.c2.negocio.helpers.extencion.ComparendoResolucionHelperExtend;
import co.com.datatools.c2.negocio.helpers.v2.EmpresaTransporteHelper;
import co.com.datatools.c2.negocio.helpers.v2.RutaHelper;
import co.com.datatools.c2.negocio.interfaces.administracion.ILAdministracionComparendo;
import co.com.datatools.c2.negocio.interfaces.administracion.IRAdministracionComparendo;
import co.com.datatools.util.GenericDao;

import com.google.common.base.Preconditions;

@Stateless(name = "AdministracionComparendoEJB")
@LocalBean
public class AdministracionComparendoEJB implements ILAdministracionComparendo, IRAdministracionComparendo {
    private final static Logger logger = Logger.getLogger(AdministracionComparendoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminNegocio fachadaPersonaEJB;

    @Override
    public List<GradoAlcoholemiaDTO> consultarGradoAlcoholemia(int codigoOrganismo) {
        logger.debug("AdministracionComparendoEJB::consultarGradoAlcoholemia(int)");
        List<GradoAlcoholemiaDTO> lsResultado = null;

        GenericDao<GradoAlcoholemia> gradoAlcoholemiaDao = new GenericDao<>(GradoAlcoholemia.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT ga FROM GradoAlcoholemia ga ");
        jpql.append(" WHERE 1=1");
        jpql.append(" AND ga.organismoTransito.codigoOrganismo = :codigo");
        filtros.put("codigo", codigoOrganismo);

        List<GradoAlcoholemia> resultadoConsulta = gradoAlcoholemiaDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            lsResultado = GradoAlcoholemiaHelper.toListLevel1DTO(resultadoConsulta);
        }
        return lsResultado;
    }

    @Override
    public List<RutaDTO> consultarRuta(Integer pIdOrganismoTransito, RutaDTO pRuta) {
        logger.debug("AdministracionComparendoEJB::consultarRuta(Integer,RutaDTO)");
        StringBuilder sql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        sql.append(" SELECT p FROM Ruta AS p").append(" WHERE 1=1");
        // TODO: Debe cambiar firma del metodo, ya no existe organismo en ruta
        // if (pIdOrganismoTransito != null) {
        // sql.append(" AND p.organismoTransito.codigoOrganismo = :pIdOrgTra");
        // filtros.put("pIdOrgTra", pIdOrganismoTransito);
        // }
        if (pRuta != null) {
            if (pRuta.getEmpresaTransporte() != null && pRuta.getEmpresaTransporte().getId() != null) {
                sql.append(" AND p.empresaTransporte.id = :pidEmpTrasn");
                filtros.put("pidEmpTrasn", pRuta.getEmpresaTransporte().getId());
            }
        }

        GenericDao<Ruta> dao = new GenericDao<>(Ruta.class, em);
        List<Ruta> listRuta = dao.buildAndExecuteQuery(sql, filtros);

        return RutaHelper.toListLevel0DTO(listRuta);
    }

    @Override
    public List<EmpresaTransporteDTO> consultarEmpresaTransporte(int codigoOrganismo,
            EmpresaTransporteDTO empresaTransporteDTO) {
        logger.debug("AdministracionComparendoEJB::consultarEmpresaTransporte(int,EmpresaTransporteDTO)");
        List<EmpresaTransporteDTO> lstResultado = new ArrayList<>();

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT et FROM EmpresaTransporte AS et");
        jpql.append(" JOIN FETCH et.personaJuridica pj");
        jpql.append(" JOIN FETCH pj.persona p");
        jpql.append(" WHERE p.organismoTransito.codigoOrganismo = :pCodOrg");
        filtros.put("pCodOrg", codigoOrganismo);

        if (empresaTransporteDTO != null) {
            if (empresaTransporteDTO.getPersonaJuridica() != null) {
                if (empresaTransporteDTO.getPersonaJuridica().getTipoIdentificacion() != null
                        && empresaTransporteDTO.getPersonaJuridica().getTipoIdentificacion().getId() != null) {
                    jpql.append(" AND p.tipoIdentificacion.id = :pTipIdent");
                    filtros.put("pTipIdent", empresaTransporteDTO.getPersonaJuridica().getTipoIdentificacion().getId());
                }
                if (StringUtils.isNotBlank(empresaTransporteDTO.getPersonaJuridica().getNumeroIdentificacion())) {
                    jpql.append(" AND p.numeroIdentificacion = :pNumIdent");
                    filtros.put("pNumIdent", empresaTransporteDTO.getPersonaJuridica().getNumeroIdentificacion());
                }
            }
        }

        GenericDao<EmpresaTransporte> dao = new GenericDao<>(EmpresaTransporte.class, em);
        List<EmpresaTransporte> result = dao.buildAndExecuteQuery(jpql, filtros);
        if (!result.isEmpty()) {
            for (EmpresaTransporte empresaTransporte : result) {
                EmpresaTransporteDTO empTransDTO = EmpresaTransporteHelper.toLevel1DTO(empresaTransporte);
                PersonaDTO personaDTO = new PersonaDTO(empresaTransporte.getPersonaJuridica().getIdPersonaJuridica());
                PersonaJuridicaDTO personaJuridica = (PersonaJuridicaDTO) fachadaPersonaEJB
                        .consultarPersona(personaDTO);
                empTransDTO.setPersonaJuridica(personaJuridica);

                lstResultado.add(empTransDTO);
            }
        }

        return lstResultado;
    }

    @Override
    public ComparendoResolucionDTO consultarResolucionSancionActual(long cicomparendo) {
        logger.debug("AdministracionComparendoEJB::existeResolucionSancionAnulada(long)");
        ComparendoResolucionDTO resolucion = null;

        GenericDao<Resolucion> resolucionDao = new GenericDao<>(Resolucion.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT r FROM Resolucion r ");
        jpql.append(" , ComparendoResolucion cr ");
        jpql.append(" WHERE cr.comparendo.cicomparendo = :cicomparendo");
        jpql.append(" AND r.tipoResolucion.id = :tipoResolucionSancion");
        jpql.append(" AND r.id = cr.idResolucion");
        jpql.append(" ORDER BY r.fechaResolucion DESC");
        filtros.put("cicomparendo", cicomparendo);
        filtros.put("tipoResolucionSancion", EnumTipoResolucion.SANCION.getValue());

        List<Resolucion> resultadoConsulta = resolucionDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            resolucion = ComparendoResolucionHelperExtend.toLevel1DTOResolucion(resultadoConsulta.get(0));
        }
        return resolucion;
    }

    @Override
    public List<ComparendoResolucionDTO> consultarResoluciones(ComparendoResolucionDTO comparendoResolucion) {
        List<ComparendoResolucionDTO> lstComparendoResoluciones = new ArrayList<ComparendoResolucionDTO>();
        checkNotNull(comparendoResolucion, "Comparendo Resolucion Nula");
        checkNotNull(comparendoResolucion.getComparendo(), "Comparendo Nulo");

        Long cicomparendo = null;
        String numeroComparendo = null;
        Integer codigoOrganismo = null;

        // Se requiere cicomparendo o combinacion de numero de comparendo y organismo de transito
        Preconditions.checkArgument(
                (cicomparendo = comparendoResolucion.getComparendo().getCicomparendo()) != null
                        || (comparendoResolucion.getComparendo().getOrdenComparendoNacional() != null
                                && (numeroComparendo = comparendoResolucion.getComparendo()
                                        .getOrdenComparendoNacional().getNumeroComparendo()) != null
                                && comparendoResolucion.getComparendo().getOrdenComparendoNacional()
                                        .getOrganismoTransito() != null && (codigoOrganismo = comparendoResolucion
                                .getComparendo().getOrdenComparendoNacional().getOrganismoTransito()
                                .getCodigoOrganismo()) != null), "Identificador del comparendo Nulo");

        // Se lleva a cabo la consulta de los comparendos de resolucion para luego encontrar sus respectivas resoluciones.
        Map<String, Object> filtros = new HashMap<String, Object>();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT cr FROM ComparendoResolucion AS cr JOIN cr.comparendo c");
        if (cicomparendo != null) {
            jpql.append(" WHERE 1 = 1 AND c.cicomparendo = :cicomparendo");
            filtros.put("cicomparendo", cicomparendo);
        } else if (numeroComparendo != null && codigoOrganismo != null) {
            jpql.append(" JOIN c.ordenComparendoNacional ocn JOIN ocn.organismoTransito ot WHERE 1 = 1");
            jpql.append(" AND ocn.numeroComparendo = :numeroComparendo");
            filtros.put("numeroComparendo", numeroComparendo);
            jpql.append(" AND ot.codigoOrganismo = :codigoOrganismo");
            filtros.put("codigoOrganismo", codigoOrganismo);
        }
        if (comparendoResolucion.getTipoResolucion() != null || comparendoResolucion.getEstadoResolucion() != null) {
            jpql.append(" AND cr.idResolucion IN (SELECT r.id FROM Resolucion AS r WHERE 1=1");
            if (comparendoResolucion.getIdResolucion() != null) {
                jpql.append(" AND r.id = :pIdRes");
                filtros.put("pIdRes", comparendoResolucion.getIdResolucion());
            }
            if (comparendoResolucion.getTipoResolucion() != null
                    && comparendoResolucion.getTipoResolucion().getId() != null) {
                jpql.append(" AND r.tipoResolucion.id = :pIdTipRes");
                filtros.put("pIdTipRes", comparendoResolucion.getTipoResolucion().getId());
            }
            if (comparendoResolucion.getEstadoResolucion() != null
                    && comparendoResolucion.getEstadoResolucion().getId() != null) {
                jpql.append(" AND r.estadoResolucion.id = :pIdEstRes");
                filtros.put("pIdEstRes", comparendoResolucion.getEstadoResolucion().getId());
            }
            jpql.append(")");
        }

        GenericDao<ComparendoResolucion> dao = new GenericDao<ComparendoResolucion>(ComparendoResolucion.class, em);
        List<ComparendoResolucion> lstComparendos = dao.buildAndExecuteQuery(jpql, filtros);
        for (ComparendoResolucion compRes : lstComparendos) {
            TypedQuery<Resolucion> query = em.createNamedQuery(Resolucion.SQ_FIND_BY_ID_FETCH, Resolucion.class);
            query.setParameter("pIdRes", compRes.getIdResolucion());
            List<Resolucion> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                Resolucion resolucion = resultList.get(0);
                ComparendoResolucionDTO comparendoResolucionDTO = ComparendoResolucionHelperExtend.toLevel1DTO(compRes,
                        resolucion);
                lstComparendoResoluciones.add(comparendoResolucionDTO);
            }
        }

        return lstComparendoResoluciones;
    }
}
