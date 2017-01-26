package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.regveh.ConsultaEmpresaDTO;
import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;
import co.com.datatools.c2.entidades.EmpresaVehiculo;
import co.com.datatools.c2.negocio.helpers.regveh.EmpresaVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.regveh.extencion.EmpresaVehiculoHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILRegistroVehicularEmpresa;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicularEmpresa;
import co.com.datatools.util.GenericDao;

@Stateless(name = "RegistroVehicularEmpresaEJB")
@LocalBean
public class RegistroVehicularEmpresaEJB implements ILRegistroVehicularEmpresa, IRRegistroVehicularEmpresa {

    private final static Logger logger = Logger.getLogger(RegistroVehicularEmpresaEJB.class.getName());
    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRPersona personaEjb;

    @Override
    public List<EmpresaVehiculoDTO> consultarEmpresa(ConsultaEmpresaDTO consultaEmpresaDTO) {
        List<EmpresaVehiculoDTO> resultado = new ArrayList<EmpresaVehiculoDTO>();
        Integer codigoOrganismo = checkNotNull(consultaEmpresaDTO.getCodigoOrganismo(),
                "Codigo de organismo de transito obligatorio");

        GenericDao<EmpresaVehiculo> empresaVehiculoDao = new GenericDao<>(EmpresaVehiculo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT ev FROM EmpresaVehiculo ev");
        jpql.append(" LEFT JOIN FETCH ev.vehiculo v");
        jpql.append(" LEFT JOIN FETCH ev.personaJuridica pj");
        jpql.append(" WHERE pj.persona.organismoTransito.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", codigoOrganismo);
        if (StringUtils.isNotBlank(consultaEmpresaDTO.getNit())) {
            jpql.append(" AND pj.persona.numeroIdentificacion LIKE :numeroIdentificacion");
            filtros.put("numeroIdentificacion", "%" + consultaEmpresaDTO.getNit() + "%");
        }
        if (StringUtils.isNotBlank(consultaEmpresaDTO.getPlaca())) {
            jpql.append(" AND v.placa LIKE :placa");
            filtros.put("placa", "%" + consultaEmpresaDTO.getPlaca() + "%");
        }
        jpql.append(" AND ev.fechaInicio <= :fechaActual");
        jpql.append(" AND ev.fechaFin >= :fechaActual");
        filtros.put("fechaActual", new Date());

        List<EmpresaVehiculo> resultadoConsulta = empresaVehiculoDao.buildAndExecuteQuery(jpql, filtros);
        for (EmpresaVehiculo empresaVehiculo : resultadoConsulta) {
            EmpresaVehiculoDTO empresaVehiculoDTO = EmpresaVehiculoHelper.toLevel1DTO(empresaVehiculo);
            empresaVehiculoDTO.setPersonaJuridica((PersonaJuridicaDTO) personaEjb.consultarPersona(empresaVehiculo
                    .getPersonaJuridica().getIdPersonaJuridica()));
            List<RepresentanteLegalDTO> representantes = personaEjb.consultarRepresentastesLegales(empresaVehiculoDTO
                    .getPersonaJuridica().getId());
            empresaVehiculoDTO.getPersonaJuridica().setRepresentanteLegalList(representantes);
            resultado.add(empresaVehiculoDTO);
        }
        return resultado;
    }

    @Override
    public EmpresaVehiculoDTO consultarEmpresa(String placa, int codigoOrganismo, Date fechaInfraccion) {

        EmpresaVehiculoDTO resultado = null;

        GenericDao<EmpresaVehiculo> empresaVehiculoDTO = new GenericDao<>(EmpresaVehiculo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT ev FROM EmpresaVehiculo ev");
        jpql.append(" LEFT JOIN ev.vehiculo v");
        jpql.append(" LEFT JOIN ev.personaJuridica pj");
        jpql.append(" WHERE pj.persona.organismoTransito.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", codigoOrganismo);

        if (StringUtils.isNotBlank(placa)) {
            jpql.append(" AND UPPER(v.placa) = UPPER(:placa)");
            filtros.put("placa", placa);
        }
        if (fechaInfraccion != null) {
            jpql.append(" AND ev.fechaInicio <= :fechaInfraccion AND (ev.fechaFin IS NULL OR ev.fechaFin >= :fechaInfraccion) ");
            filtros.put("fechaInfraccion", fechaInfraccion);
        }

        List<EmpresaVehiculo> resultadoConsulta = empresaVehiculoDTO.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            resultado = EmpresaVehiculoHelperExtend.toLevel1DTOExtends(resultadoConsulta.get(0));
        }
        return resultado;
    }
}
