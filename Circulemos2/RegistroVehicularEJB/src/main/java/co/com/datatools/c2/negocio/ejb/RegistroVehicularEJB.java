package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;
import co.com.datatools.c2.entidades.Vehiculo;
import co.com.datatools.c2.negocio.helpers.regveh.VehiculoHelper;
import co.com.datatools.c2.negocio.interfaces.ILRegistroVehicular;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicular;
import co.com.datatools.util.GenericDao;

@Stateless(name = "RegistroVehicularEJB")
@LocalBean
public class RegistroVehicularEJB implements ILRegistroVehicular, IRRegistroVehicular {

    private final static Logger logger = Logger.getLogger(RegistroVehicularEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<VehiculoDTO> consultarInformacionVehiculo(ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO) {
        logger.debug("RegistroVehicularEJB::consultarInformacionVehiculo(ConsultaRegistroVehicularDTO)");
        List<VehiculoDTO> resultado = new ArrayList<VehiculoDTO>();
        Integer codigoOrganismo = checkNotNull(consultaRegistroVehicularDTO.getCodigoOrganismo(),
                "Codigo de organismo de transito obligatorio");

        GenericDao<Vehiculo> VehiculoDao = new GenericDao<>(Vehiculo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT v FROM Vehiculo v");
        jpql.append(" LEFT JOIN FETCH v.modalidad m");
        jpql.append(" LEFT JOIN FETCH v.color c");
        jpql.append(" LEFT JOIN FETCH v.claseVehiculo cv");
        jpql.append(" LEFT JOIN FETCH v.radioAccion ra");
        jpql.append(" LEFT JOIN FETCH v.tipoServicio ts");
        jpql.append(" LEFT JOIN FETCH v.linea lv");
        jpql.append(" LEFT JOIN FETCH v.propietarioVehiculoList pv");
        jpql.append(" LEFT JOIN pv.persona p");
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
            jpql.append(" AND v.placa LIKE :placa");
            filtros.put("placa", "%" + consultaRegistroVehicularDTO.getPlaca() + "%");
        }
        jpql.append(" AND pv.fechaFin IS NULL");
        List<Vehiculo> resultadoConsulta = VehiculoDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            resultado = VehiculoHelper.toListLevel1DTO(resultadoConsulta);
        }
        return resultado;
    }

    @Override
    public VehiculoDTO consultarVehiculo(String placa) {
        logger.debug("RegistroVehicularEJB::consultarVehiculo(String)");

        VehiculoDTO resultado = null;

        GenericDao<Vehiculo> vehiculoDao = new GenericDao<>(Vehiculo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT v FROM Vehiculo v");
        jpql.append(" LEFT JOIN FETCH v.modalidad m");
        jpql.append(" LEFT JOIN FETCH v.color c");
        jpql.append(" LEFT JOIN FETCH v.claseVehiculo cv");
        jpql.append(" LEFT JOIN FETCH v.radioAccion ra");
        jpql.append(" LEFT JOIN FETCH v.tipoServicio ts");
        jpql.append(" LEFT JOIN FETCH v.linea lv");
        jpql.append(" LEFT JOIN FETCH v.organismoTransito ot");
        jpql.append(" WHERE 1=1");

        if (StringUtils.isNotBlank(placa)) {
            jpql.append(" AND UPPER(v.placa) = UPPER(:placa)");
            filtros.put("placa", placa);
        }

        jpql.append(" ORDER BY v.id DESC");

        List<Vehiculo> resultadoConsulta = vehiculoDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            resultado = VehiculoHelper.toLevel1DTO(resultadoConsulta.get(0));
        }
        return resultado;
    }
}