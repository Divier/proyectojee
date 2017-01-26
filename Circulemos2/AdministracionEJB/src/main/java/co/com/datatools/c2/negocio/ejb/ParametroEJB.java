package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ModuloDTO;
import co.com.datatools.c2.dto.ParametroDTO;
import co.com.datatools.c2.dto.ParametroOrganismoDTO;
import co.com.datatools.c2.dto.TipoVariableDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.entidades.Parametro;
import co.com.datatools.c2.entidades.ParametroOrganismo;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.excepciones.ConfiguracionParametroException;
import co.com.datatools.c2.excepciones.ConfiguracionParametroException.ErrorParametro;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.v2.ParametroHelper;
import co.com.datatools.c2.negocio.helpers.v2.ParametroOrganismoHelper;
import co.com.datatools.c2.negocio.interfaces.ILParametro;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.util.GenericDao;

@Stateless(name = "ParametroEJB")
@LocalBean
public class ParametroEJB implements IRParametro, ILParametro {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(ParametroEJB.class.getName());

    @Override
    @Deprecated
    public List<ParametroOrganismoDTO> consultarParametroOrganismo(ParametroDTO parametroDTO, Integer codigoOrganismo) {
        logger.debug("ParametroEJB.consultarParametroOrganismo(ParametroDTO, Integer)");
        List<ParametroOrganismoDTO> result = new ArrayList<>();
        final Map<String, Object> paramVal = new HashMap<>(5);
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT po FROM ParametroOrganismo po");
        sb.append(" JOIN FETCH po.parametro p ");
        sb.append(" JOIN FETCH po.organismoTransito org ");
        sb.append(" WHERE 1=1");

        final Integer codigoParametro = parametroDTO.getCodigo();
        final Integer codigoUnidadParametro = parametroDTO.getCodigoUnidad();
        final Boolean editableOrganismo = parametroDTO.getEditableOrganismo();
        final String formato = parametroDTO.getFormato();
        final String nombreParametro = parametroDTO.getNombre();
        final String valorParametroDefecto = parametroDTO.getValorDefecto();
        Integer codigoModulo = null;
        if (parametroDTO.getModulo() != null) {
            codigoModulo = parametroDTO.getModulo().getId();
        }
        Integer codigoTipoVariable = null;
        if (parametroDTO.getTipoVariable() != null) {
            codigoTipoVariable = parametroDTO.getTipoVariable().getCodigo();
        }
        //
        if (codigoParametro != null) {
            sb.append(" AND p.codigo = :pCodigoParametro");
            paramVal.put("pCodigoParametro", codigoParametro);
        }
        if (codigoUnidadParametro != null) {
            sb.append(" AND p.codigoUnidad = :pCodigoUnidadParametro");
            paramVal.put("pCodigoUnidadParametro", codigoUnidadParametro);
        }
        if (editableOrganismo != null) {
            sb.append(" AND p.editableOrganismo = :pEditableOrganismo");
            paramVal.put("pEditableOrganismo", editableOrganismo);
        }
        if (formato != null) {
            sb.append(" AND p.formato = :pFormato");
            paramVal.put("pFormato", formato);
        }
        if (nombreParametro != null) {
            sb.append(" AND p.nombre = :pNombreParametro");
            paramVal.put("pNombreParametro", nombreParametro);
        }
        if (valorParametroDefecto != null) {
            sb.append(" AND p.valorDefecto = :pValorParametroDefecto");
            paramVal.put("pValorParametroDefecto", valorParametroDefecto);
        }
        if (codigoModulo != null) {
            sb.append(" AND p.modulo.codigo = :pCodigoModulo");
            paramVal.put("pCodigoModulo", codigoModulo);
        }
        if (codigoTipoVariable != null) {
            sb.append(" AND p.tipoVariable.codigo = :pCodigoTipoVariable");
            paramVal.put("pCodigoTipoVariable", codigoTipoVariable);
        }
        if (codigoOrganismo != null) {
            sb.append(" AND po.organismoTransito.codigoOrganismo = :pCodigoOrganismo");
            paramVal.put("pCodigoOrganismo", codigoOrganismo);
        }
        sb.append(" AND po.fechaFin IS NULL ");
        sb.append(" ORDER BY po.organismoTransito.codigoOrganismo asc, po.parametro.codigo asc");
        logger.debug("Parametros de consulta dinamica " + paramVal);
        final GenericDao<ParametroOrganismo> paramDao = new GenericDao<>(ParametroOrganismo.class, em);
        final List<ParametroOrganismo> resp = paramDao.buildAndExecuteQuery(sb.toString(), paramVal);
        for (ParametroOrganismo prmOrg : resp) {
            ParametroOrganismoDTO prmOrgDto = ParametroOrganismoHelper.toLevel0DTO(prmOrg);
            prmOrgDto.setParametro(ParametroHelper.toLevel0DTO(prmOrg.getParametro()));
            prmOrgDto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(prmOrg.getOrganismoTransito()));
            result.add(prmOrgDto);
        }
        return result;
    }

    @Override
    public boolean existeParametroOrganismo(ParametroOrganismoDTO parametroOrganismo, Integer codigoOrganismo) {
        logger.debug("ParametroEJB.existeParametroOrganismo(ParametroOrganismoDTO, Integer)");
        final Integer idParametro = parametroOrganismo.getId();
        final Date fechaInicio = parametroOrganismo.getFechaInicio();
        final Date fechaFin = parametroOrganismo.getFechaFin();
        final String valorParametro = parametroOrganismo.getValor();
        final Integer codParametro = (parametroOrganismo.getParametro() != null && parametroOrganismo.getParametro()
                .getCodigo() != null) ? parametroOrganismo.getParametro().getCodigo() : null;

        if (codigoOrganismo == null) {
            throw new CirculemosRuntimeException("Codigo Organismo debe ser diferente de null");
        }

        Map<String, Object> paramVal = new HashMap<>(5);
        StringBuilder jpql = new StringBuilder("SELECT COUNT(po) FROM ParametroOrganismo po"
                + " WHERE po.organismoTransito.codigoOrganismo = :pCodigoOrganismo");

        if (idParametro != null) {
            jpql.append(" AND po.id = :pIdParametro");
            paramVal.put("pIdParametro", idParametro);
        }
        if (fechaInicio != null) {
            jpql.append(" AND po.fechaInicio <= :pFechaInicio");
            jpql.append(" AND ( po.fechaFin IS NULL OR po.fechaFin >= :pFechaInicio )");
            paramVal.put("pFechaInicio", fechaInicio);
        }
        if (fechaFin != null) {
            jpql.append(" AND po.fechaInicio <= :pFechaFin");
            jpql.append(" AND ( po.fechaFin IS NULL OR po.fechaFin >= :pFechaFin )");
            paramVal.put("pFechaFin", fechaFin);
        }
        if (valorParametro != null) {
            jpql.append(" AND po.valor= :pValorParametro");
            paramVal.put("pValorParametro", valorParametro);
        }
        if (codParametro != null) {
            jpql.append(" AND po.parametro.codigo = :pCodigoParametro");
            paramVal.put("pCodigoParametro", codParametro);
        }

        final Query query = em.createQuery(jpql.toString());
        query.setParameter("pCodigoOrganismo", codigoOrganismo);
        for (Map.Entry<String, Object> entry : paramVal.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        final long cantidad = (long) query.getSingleResult();
        return cantidad > 0;

    }

    @Override
    public List<ParametroDTO> consultarParametroSistema(ParametroDTO parametro) {
        logger.debug("ParametroEJB.consultarParametroSistema(ParametroDTO)");
        List<ParametroDTO> result = new ArrayList<ParametroDTO>();
        GenericDao<Parametro> prmDao = new GenericDao<>(Parametro.class, em);

        final HashMap<String, Object> params = new HashMap<>();

        final Integer codigoParametro = parametro.getCodigo();
        final Integer codigoUnidadParametro = parametro.getCodigo();
        final Boolean editableOrganismo = parametro.getEditableOrganismo();
        final String formato = parametro.getFormato();
        final String nombreParametro = parametro.getNombre();
        final String valorParametroDefecto = parametro.getValorDefecto();
        final ModuloDTO moduloDTO = parametro.getModulo();
        final TipoVariableDTO tipoVariableDTO = parametro.getTipoVariable();

        if (codigoParametro != null) {
            params.put("codigo", codigoParametro);
        } else {
            if (codigoUnidadParametro != null) {
                params.put("codigoUnidad", codigoUnidadParametro);
            }
            if (editableOrganismo != null) {
                params.put("editableOrganismo", editableOrganismo);
            }
            if (formato != null) {
                params.put("formato", formato);
            }
            if (nombreParametro != null) {
                params.put("nombre", nombreParametro);
            }
            if (valorParametroDefecto != null) {
                params.put("valorDefecto", valorParametroDefecto);
            }
            if (moduloDTO != null && moduloDTO.getId() != null) {
                params.put("modulo.id", moduloDTO.getId());
            }
            if (tipoVariableDTO != null && tipoVariableDTO.getCodigo() != null) {
                params.put("tipoVariable.codigo", tipoVariableDTO.getCodigo());
            }
        }

        List<Parametro> parametros = prmDao.findByAttributes(params);

        for (Parametro prm : parametros) {
            result.add(ParametroHelper.toLevel0DTO(prm));
        }

        return result;
    }

    @Override
    public String consultarValorParametro(EnumParametro codigoParametro, int codigoOrganismo) {
        logger.debug("ParametroEJB.consultarValorParametro(EnumParametro, int)");
        ParametroDTO parametro = new ParametroDTO();
        parametro.setCodigo(codigoParametro.getCodigo());
        List<ParametroOrganismoDTO> paramOrgans = consultarParametroOrganismo(parametro, codigoOrganismo);
        if (paramOrgans.isEmpty()) {
            List<ParametroDTO> paramSistema = consultarParametroSistema(parametro);
            return paramSistema.get(0).getValorDefecto();
        } else {
            return paramOrgans.get(0).getValor();
        }
    }

    @Override
    public Map<Integer, List<ParametroDTO>> consultarParametro(ParametroDTO parametro, int codigoOrganismo) {
        logger.debug("ParametroEJB.consultarParametro(ParametroDTO,int)");
        final Map<String, Object> paramVal = new HashMap<>(5);
        final StringBuilder sb = new StringBuilder();
        sb.append(" SELECT par, org");
        sb.append(" FROM Parametro par ");
        sb.append(" LEFT JOIN par.parametroOrganismoList org");
        sb.append(" WHERE 1=1");

        if (parametro != null) {
            if (parametro.getCodigo() != null) {
                sb.append(" AND par.codigo = :pCodigoParametro");
                paramVal.put("pCodigoParametro", parametro.getCodigo());
            }
            if (parametro.getModulo() != null && parametro.getModulo().getId() != null) {
                sb.append(" AND par.modulo.id = :pCodigoModulo");
                paramVal.put("pCodigoModulo", parametro.getModulo().getId());
            }
        }

        sb.append(" AND (org = null OR (org.organismoTransito.codigoOrganismo = :pCodigoOrganismo AND org.fechaFin IS NULL))");
        paramVal.put("pCodigoOrganismo", codigoOrganismo);

        final Query query = em.createQuery(sb.toString());
        for (Iterator<Entry<String, Object>> it = paramVal.entrySet().iterator(); it.hasNext();) {
            Entry<String, Object> param = it.next();
            query.setParameter(param.getKey(), param.getValue());
        }
        @SuppressWarnings("unchecked")
        List<Object[]> resultList = query.getResultList();
        final Map<Integer, List<ParametroDTO>> resp = new HashMap<>();
        if (resultList != null && !resultList.isEmpty()) {
            for (Object[] record : resultList) {
                Parametro sistema = (Parametro) record[0];
                ParametroOrganismo organismo = (ParametroOrganismo) record[1];
                if (organismo != null) {
                    sistema.setValorDefecto(organismo.getValor());
                }
                em.detach(sistema);
                Integer codigoParametro = sistema.getCodigo();
                if (resp.containsKey(codigoParametro)) {
                    resp.get(codigoParametro).add(ParametroHelper.toLevel0DTO(sistema));
                } else {
                    resp.put(codigoParametro, new ArrayList<>(Arrays.asList(ParametroHelper.toLevel0DTO(sistema))));
                }
            }
        } else {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT par");
            sql.append(" FROM Parametro par ");
            sql.append(" WHERE 1=1");

            if (parametro != null) {
                if (parametro.getCodigo() != null) {
                    sql.append(" AND par.codigo = :pCodigoParametro");
                }
            }

            Query que = em.createQuery(sql.toString());

            if (parametro != null) {
                que.setParameter("pCodigoParametro", parametro.getCodigo());
            }
            @SuppressWarnings("unchecked")
            List<Parametro> reltList = que.getResultList();
            if (reltList != null && !reltList.isEmpty()) {
                for (Parametro param : reltList) {
                    resp.put(param.getCodigo(), new ArrayList<>(Arrays.asList(ParametroHelper.toLevel0DTO(param))));
                }
            }
        }
        return resp;
    }

    @Override
    public ValorParametroDTO consultarParametro(EnumParametro parametro, int codigoOrganismo, boolean requerido) {
        logger.debug("ParametroEJB.consultarParametro(EnumParametro, int, boolean)");
        final Map<Integer, List<ParametroDTO>> resultado = consultarParametro(new ParametroDTO(parametro.getCodigo()),
                codigoOrganismo);

        final List<ParametroDTO> list = resultado.get(parametro.getCodigo());

        if (requerido && (list == null || list.isEmpty())) {
            throw new ConfiguracionParametroException(ErrorParametro.PARAMETRO_NO_ENCONTRADO, parametro);
        }

        if (requerido && (list.get(0).getValorDefecto() == null)) {
            throw new ConfiguracionParametroException(ErrorParametro.PARAMETRO_NULL, parametro);
        }

        final ValorParametroDTO valorParametro = new ValorParametroDTO(parametro);

        for (ParametroDTO parametroDTO : list) {
            valorParametro.getValores().add(parametroDTO.getValorDefecto());
        }

        return valorParametro;
    }

}
