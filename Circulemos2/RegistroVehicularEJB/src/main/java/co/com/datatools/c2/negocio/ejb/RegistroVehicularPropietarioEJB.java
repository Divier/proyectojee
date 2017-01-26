package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.entidades.PropietarioVehiculo;
import co.com.datatools.c2.negocio.helpers.regveh.PropietarioVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.regveh.extencion.PropietarioVehiculoHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILRegistroVehicularPropietario;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicularPropietario;
import co.com.datatools.util.GenericDao;

@Stateless(name = "RegistroVehicularPropietarioEJB")
@LocalBean
public class RegistroVehicularPropietarioEJB implements ILRegistroVehicularPropietario, IRRegistroVehicularPropietario {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRPersona personaEjb;

    @Override
    public List<PropietarioVehiculoDTO> consultarPropietario(ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO) {
        List<PropietarioVehiculoDTO> resultado = new ArrayList<PropietarioVehiculoDTO>();
        Integer codigoOrganismo = checkNotNull(consultaRegistroVehicularDTO.getCodigoOrganismo(),
                "Codigo de organismo de transito obligatorio");

        GenericDao<PropietarioVehiculo> PropietarioVehiculoDao = new GenericDao<>(PropietarioVehiculo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT pv FROM PropietarioVehiculo pv");
        jpql.append(" LEFT JOIN FETCH pv.vehiculo v");
        jpql.append(" LEFT JOIN FETCH pv.persona p");
        jpql.append(" LEFT JOIN FETCH pv.tipoPropietario tp");
        jpql.append(" WHERE v.organismoTransito.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", codigoOrganismo);
        if (consultaRegistroVehicularDTO.getIdTipoIdentificacion() != null) {
            jpql.append(" AND p.tipoIdentificacion.id = :idTipoDocumento");
            filtros.put("idTipoDocumento", consultaRegistroVehicularDTO.getIdTipoIdentificacion());
        }
        if (StringUtils.isNotBlank(consultaRegistroVehicularDTO.getNumeroIdentificacion())) {
            jpql.append(" AND p.numeroIdentificacion LIKE :numeroIdentificacion");
            filtros.put("numeroIdentificacion", "%" + consultaRegistroVehicularDTO.getNumeroIdentificacion() + "%");
        }
        if (StringUtils.isNotBlank(consultaRegistroVehicularDTO.getPlaca())) {
            jpql.append(" AND v|.placa LIKE :placa");
            filtros.put("placa", "%" + consultaRegistroVehicularDTO.getPlaca() + "%");
        }
        List<PropietarioVehiculo> resultadoConsulta = PropietarioVehiculoDao.buildAndExecuteQuery(jpql, filtros);
        for (PropietarioVehiculo propietarioVehiculo : resultadoConsulta) {
            PropietarioVehiculoDTO propietarioVehiculoDTO = PropietarioVehiculoHelper.toLevel1DTO(propietarioVehiculo);
            propietarioVehiculoDTO.setPersona(personaEjb.consultarPersona(propietarioVehiculo.getPersona().getId()));
            resultado.add(propietarioVehiculoDTO);
        }
        if (!resultadoConsulta.isEmpty()) {
            resultado = PropietarioVehiculoHelper.toListLevel1DTO(resultadoConsulta);
        }
        return resultado;
    }

    @Override
    public PropietarioVehiculoDTO consultarMayorPropietario(ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO) {

        PropietarioVehiculoDTO resultado = null;

        GenericDao<PropietarioVehiculo> propietarioVehiculoDTO = new GenericDao<>(PropietarioVehiculo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT pv FROM PropietarioVehiculo pv");
        jpql.append(" LEFT JOIN FETCH pv.vehiculo v");
        jpql.append(" LEFT JOIN FETCH pv.persona p");
        jpql.append(" LEFT JOIN FETCH pv.tipoPropietario tp");
        jpql.append(" WHERE v.organismoTransito.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", consultaRegistroVehicularDTO.getCodigoOrganismo());

        if (consultaRegistroVehicularDTO.getIdTipoIdentificacion() != null) {
            jpql.append(" AND p.tipoIdentificacion.id = :idTipoDocumento");
            filtros.put("idTipoDocumento", consultaRegistroVehicularDTO.getIdTipoIdentificacion());
        }
        if (StringUtils.isNotBlank(consultaRegistroVehicularDTO.getNumeroIdentificacion())) {
            jpql.append(" AND p.numeroIdentificacion = :numeroIdentificacion");
            filtros.put("numeroIdentificacion", consultaRegistroVehicularDTO.getNumeroIdentificacion());
        }
        if (StringUtils.isNotBlank(consultaRegistroVehicularDTO.getPlaca())) {
            jpql.append(" AND UPPER(v.placa) = UPPER(:placa)");
            filtros.put("placa", consultaRegistroVehicularDTO.getPlaca());
        }
        if (consultaRegistroVehicularDTO.getFechaConsulta() != null) {
            jpql.append(" AND pv.fechaInicio <= :fechaConsulta AND (pv.fechaFin IS NULL OR pv.fechaFin >= :fechaConsulta) ");
            filtros.put("fechaConsulta", consultaRegistroVehicularDTO.getFechaConsulta());
        }

        List<PropietarioVehiculo> resultadoConsulta = propietarioVehiculoDTO.buildAndExecuteQuery(jpql, filtros);
        int indice = -1;
        BigDecimal mayor = new BigDecimal(0);

        for (PropietarioVehiculo propietarioVehiculo : resultadoConsulta) {
            if (propietarioVehiculo.getPorcentaje().compareTo(mayor) > 0) {
                mayor = propietarioVehiculo.getPorcentaje();
                ++indice;
            }
        }

        if (!resultadoConsulta.isEmpty()) {
            resultado = PropietarioVehiculoHelperExtend.toLevel1DTOExtends(resultadoConsulta.get(indice));
        }
        return resultado;
    }
}
