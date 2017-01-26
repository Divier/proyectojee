package co.com.datatools.fachadaintegracionweb.impugnaciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.dto.DetalleProcesoImpugnacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.ImpugnacionConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ParticipanteProcesoImpugnacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.ProcesoImpugnacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaImpugnaciones;

@Stateless
public class FachadaImpugnacionesEJB implements IRFachadaImpugnaciones {

    private final static Logger LOGGER = Logger.getLogger(FachadaImpugnacionesEJB.class.getName());
	
	private final static Integer CODIGO_PROCESO_IMPUGNACION = 1;

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<ProcesoImpugnacionReplicaDTO> consultarProcesosImpugnacion(
            ImpugnacionConsultaDTO impugnacionConsultaDTO) {
        LOGGER.debug("consultarProcesosImpugnacion(ImpugnacionConsultaDTO)");
        final List<ProcesoImpugnacionReplicaDTO> procesos = new ArrayList<ProcesoImpugnacionReplicaDTO>();

        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sql.append("SELECT ");
        sql.append("pro.numero_proceso, ");
        sql.append("pro.fecha_inicio, ");
        sql.append("pro.fecha_fin, ");
        sql.append("est.nombre, ");
        sql.append("c.numero_citacion, ");
        sql.append("c.id_factura_axis, ");
        sql.append("c.fecha_infraccion, ");
        sql.append("pro.id_proceso ");
        sql.append(
                "FROM comparendo_proceso com, comparendo_persona per, proceso pro, estado_proceso est, comparendo c, tipo_proceso tp ");
        sql.append("WHERE pro.id_proceso = com.id_proceso ");
        sql.append("AND com.cicomparendo = per.cicomparendo ");
        sql.append("AND pro.id_estado_proceso = est.id_estado_proceso ");
        sql.append("AND com.cicomparendo = c.cicomparendo ");
        sql.append("AND tp.id_tipo_proceso = pro.id_tipo_proceso ");
        sql.append("AND tp.codigo = :codigoProcesoImpugnaciones ");

		params.put("codigoProcesoImpugnaciones", FachadaImpugnacionesEJB.CODIGO_PROCESO_IMPUGNACION);
		
        if (impugnacionConsultaDTO.getTipoIdentificacion() != null
                && impugnacionConsultaDTO.getNumeroIdentificacion() != null) {
            sql.append("AND per.id_tipo_identificacion = :idIdentificacion ");
            params.put("idIdentificacion", impugnacionConsultaDTO.getTipoIdentificacion());
            sql.append("AND per.numero_identificacion = :identificacion ");
            params.put("identificacion", impugnacionConsultaDTO.getNumeroIdentificacion());
        }

        if (impugnacionConsultaDTO.getNumeroProceso() != null) {
            sql.append("AND pro.numero_proceso = :numeroProceso ");
            params.put("numeroProceso", impugnacionConsultaDTO.getNumeroProceso());
        }

        if (impugnacionConsultaDTO.getAnioProceso() != null) {
            sql.append("AND YEAR(pro.fecha_inicio) = :anioProceso ");
            params.put("anioProceso", impugnacionConsultaDTO.getAnioProceso());
        }

        sql.append("ORDER BY pro.numero_proceso ASC");

        Query query = em.createNativeQuery(sql.toString());

        if (!params.isEmpty()) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {
                ProcesoImpugnacionReplicaDTO proceso = new ProcesoImpugnacionReplicaDTO();
                proceso.setNumeroProceso(objects2[0].toString());
                proceso.setFechaInicioProceso((Date) objects2[1]);
                proceso.setFechaFinProceso((Date) objects2[2]);
                proceso.setEstadoProceso(objects2[3].toString());
                proceso.setNumeroCitacion(objects2[4].toString());
                proceso.setNumeroFactura(objects2[5].toString());
                proceso.setFechaInfraccion((Date) objects2[6]);
                proceso.setIdProceso(new BigDecimal(objects2[7].toString()));
                procesos.add(proceso);
            }
        }

        return procesos;
    }

    @Override
    public DetalleProcesoImpugnacionDTO consultarDetalleImpugnacion(BigDecimal idProceso) {
        LOGGER.debug("consultarDetalleImpugnacion(BigDecimal)");
        DetalleProcesoImpugnacionDTO respuesta = new DetalleProcesoImpugnacionDTO();

        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sql.append("SELECT ");
        sql.append("pro.numero_proceso, ");
        sql.append("pro.fecha_inicio, ");
        sql.append("pro.fecha_fin, ");
        sql.append("c.numero_citacion, ");
        sql.append("c.fecha_infraccion, ");
        sql.append("inf.codigo_infraccion, ");
        sql.append("confi.descripcion, ");
        sql.append("est.nombre ");
        sql.append(
                "FROM comparendo_proceso com, comparendo_persona per, proceso pro, estado_proceso est, comparendo c, infraccion inf, configuracion_infraccion confi ");
        sql.append("WHERE pro.id_proceso = com.id_proceso ");
        sql.append("AND com.cicomparendo = per.cicomparendo ");
        sql.append("AND com.cicomparendo = c.cicomparendo ");
        sql.append("AND c.id_infraccion = inf.id_infraccion ");
        sql.append("AND inf.id_infraccion = confi.id_infraccion ");
        sql.append("AND pro.id_estado_proceso = est.id_estado_proceso ");
        sql.append("AND pro.id_proceso = :idProceso ");
        params.put("idProceso", idProceso);

        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("idProceso", idProceso);

        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {
                respuesta.setNumeroProceso(objects2[0].toString());
                respuesta.setFechaInicio((Date) objects2[1]);
                respuesta.setFechaFin((Date) objects2[2]);
                respuesta.setNumeroCitacion(objects2[3].toString());
                respuesta.setFechaInfraccion((Date) objects2[4]);
                respuesta.setCodigoInfraccion(objects2[5].toString());
                respuesta.setDescripcionInfraccion(objects2[6].toString());
                respuesta.setEstadoProceso(objects2[7].toString());
                respuesta.setParticipantes(consultarParticipantesImpugnacion(idProceso));
            }
        }

        return respuesta;
    }

    private List<ParticipanteProcesoImpugnacionDTO> consultarParticipantesImpugnacion(BigDecimal idProceso) {
        LOGGER.debug("consultarParticipantesImpugnacion(BigDecimal)");
        List<ParticipanteProcesoImpugnacionDTO> respuesta = new ArrayList<ParticipanteProcesoImpugnacionDTO>();

        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sql.append("SELECT ");
        sql.append("tipop.nombre tipoprop, ");
        sql.append("tipoid.nombre tipodoc, ");
        sql.append("per.numero_identificacion, ");
        sql.append("per.nombre1 + ' ' + per.nombre2 + ' ' + per.apellido1  + ' ' + per.apellido2 nombreape, ");
        sql.append("d.complemento, ");
        sql.append("per.correo_electronico ");
        sql.append(
                "FROM proceso p, participante_proceso par, tipo_participante tipop, persona per, tipo_identificacion_persona tipoid, direccion_persona dirp, direccion d ");
        sql.append("WHERE p.id_proceso = par.id_proceso ");
        sql.append("AND par.id_tipo_participante = tipop.id_tipo_participante ");
        sql.append("AND par.id_persona = per.id_persona ");
        sql.append("AND per.id_tipo_identificacion = tipoid.id_tipo_identificacion ");
        sql.append("AND per.id_persona = dirp.id_persona ");
        sql.append("AND dirp.id_direccion = d.id_direccion  ");
        sql.append("AND p.id_proceso = :idProceso ");
        params.put("idProceso", idProceso);

        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("idProceso", idProceso);

        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {
                ParticipanteProcesoImpugnacionDTO participanteDTO = new ParticipanteProcesoImpugnacionDTO();
                participanteDTO.setTipoParticipante(objects2[0].toString());
                participanteDTO.setTipoIdentificacion(objects2[1].toString());
                participanteDTO.setNumeroIdentificacion(objects2[2].toString());
                participanteDTO.setNombreParticipante((objects2[3] != null ? objects2[3].toString() : null));
                participanteDTO.setDireccionParticipante(objects2[4].toString());
                participanteDTO.setCorreoParticipante((objects2[5] != null ? objects2[5].toString() : null));
                respuesta.add(participanteDTO);
            }
        }

        return respuesta;
    }

}
