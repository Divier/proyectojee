package co.com.datatools.c2.negocio.helpers.formularios;

/**
 * Encapsula la consulta en sql de los cambios de estados de formularios de acuerdo a diferentes parametros cuyos nombres estan definidos en la
 * enumeracion: ParametrosConsulta
 * 
 * @author claudia.rodriguez
 * 
 */
public class CambioEstadosFormularioSQL {

    // public static final String SQL_BASE = "SELECT ce.id_detalle_cambio_estado,ta.id_tipo_asignacion,ta.nombre AS tipo_asignacion,"
    // + " tf.id_tipo_formulario,tf.nombre AS tipo_formulario,r.id_responsable_formulario,a.id_area_fisica,a.nombre AS area,"
    // + " p.numero_identificacion,p.id_tipo_identificacion,p.nombre1,p.nombre2,p.apellido1,p.apellido2,"
    // +
    // " ce.folio,ce.numero_inicial_rango,ce.numero_final_rango,ce.cantidad_formularios,ce.fecha_movimiento,sf.observaciones,r.placa,ti.nombre_tipo_documento,"
    // + " ef.id_estado_formulario, ef.nombre"
    // + " FROM seguimiento_formulario sf"
    // +
    // " JOIN detalle_cambio_estado ce ON sf.id_detalle_cambio_estado=ce.id_detalle_cambio_estado AND ce.id_estado_formulario IN (:id_estados_formulario)"
    // + " JOIN formulario f ON sf.id_formulario=f.id_formulario AND f.id_tipo_formulario= :id_tipo_formulario AND sf.id_seguimiento_formulario="
    // + "(SELECT MAX(segfor.id_seguimiento_formulario) FROM seguimiento_formulario segfor WHERE segfor.id_formulario=f.id_formulario)"
    // + " JOIN responsable_formulario r ON sf.id_responsable_formulario=r.id_responsable_formulario"
    // + " LEFT JOIN area_fisica a ON r.id_area_responsable=a.id_area_fisica"
    // + " JOIN persona p ON r.id_persona=p.id_persona"
    // + " JOIN tipo_asignacion_formulario ta ON sf.id_tipo_asignacion=ta.id_tipo_asignacion"
    // + " JOIN tipo_formulario tf ON tf.id_tipo_formulario=f.id_tipo_formulario"
    // + " JOIN tipo_identificacion_persona ti ON p.id_tipo_identificacion=ti.id_tipo_identificacion"
    // + " JOIN estado_formulario ef ON ef.id_estado_formulario=ce.id_estado_formulario WHERE 1=1";

    public static final String SQL_BASE = "SELECT tf.id_tipo_formulario, tf.nombre as nombre_tipo, ef.nombre as nombre_estado,"
            + "ef.id_estado_formulario, dce.id_detalle_cambio_estado, dce.numero_inicial_rango, dce.numero_final_rango,"
            + "dce.cantidad_formularios,dce.fecha_aplicacion_estado"
            + " FROM detalle_cambio_estado dce"
            + " JOIN rango_formulario rf ON dce.id_rango_formulario = rf.id_rango_formulario"
            + " JOIN tipo_formulario tf ON tf.id_tipo_formulario = rf.id_tipo_formulario"
            + " JOIN causal_cambio_estado cce ON cce.id_causal_cambio_estado = dce.id_causal_cambio_estado"
            + " JOIN estado_formulario ef ON ef.id_estado_formulario = dce.id_estado_formulario"
            + " JOIN responsable_formulario ref ON ref.id_responsable_formulario = dce.id_responsable_formulario"
            + " JOIN unificacion_responsable uf ON ref.id_responsable_formulario = uf.id_responsable_formulario"
            + " LEFT JOIN organismo_transito ot ON ot.codigo_organismo = uf.codigo_organismo"
            + " LEFT JOIN persona p ON p.id_persona = uf.id_persona"
            + " LEFT JOIN tipo_identificacion_persona tip ON p.id_tipo_identificacion = tip.id_tipo_identificacion WHERE dce.es_evento=true";

    /**
     * Constante utilizada para cargar los datos de consulta de formularios
     */
    public static final String SQL_BASE_FORMULARIO = "SELECT ta.id_tipo_asignacion,ta.nombre AS tipo_asignacion,"
            + "tf.id_tipo_formulario,tf.nombre AS tipo_formulario,r.id_responsable_formulario,ti.nombre_tipo_documento,"
            + "p.id_tipo_identificacion,p.nombre1,p.nombre2,p.apellido1,p.apellido2,pj.nombre_comercial,a.id_area_fisica,a.nombre AS area,"
            + "p.numero_identificacion,r.placa,sf.fecha_movimiento,f.numero_formulario,ef.id_estado_formulario,ef.nombre,f.id_formulario"
            + " FROM seguimiento_formulario sf"
            + " JOIN formulario f ON sf.id_formulario=f.id_formulario AND f.id_tipo_formulario= :id_tipo_formulario AND sf.id_seguimiento_formulario="
            + " (SELECT MAX(segfor.id_seguimiento_formulario) FROM seguimiento_formulario segfor WHERE segfor.id_formulario=f.id_formulario)"
            + " JOIN responsable_formulario r ON sf.id_responsable_formulario=r.id_responsable_formulario"
            + " LEFT JOIN area_fisica a ON r.id_area_responsable=a.id_area_fisica"
            + " JOIN persona p ON r.id_persona=p.id_persona"
            + " LEFT JOIN persona_juridica pj ON p.id_persona = pj.id_persona_juridica"
            + " JOIN tipo_asignacion_formulario ta ON sf.id_tipo_asignacion=ta.id_tipo_asignacion"
            + " JOIN tipo_formulario tf ON tf.id_tipo_formulario=f.id_tipo_formulario"
            + " JOIN tipo_identificacion_persona ti ON p.id_tipo_identificacion=ti.id_tipo_identificacion"
            + " JOIN estado_formulario ef ON ef.id_estado_formulario=sf.id_estado_formulario WHERE 1=1";

    public static final String SQL_BASE_FORMULARIO_COUNT = "SELECT COUNT(sf.id_seguimiento_formulario)"
            + " FROM seguimiento_formulario sf"
            + " JOIN formulario f ON sf.id_formulario=f.id_formulario AND f.id_tipo_formulario= :id_tipo_formulario AND sf.id_seguimiento_formulario="
            + " (SELECT MAX(segfor.id_seguimiento_formulario) FROM seguimiento_formulario segfor WHERE segfor.id_formulario=f.id_formulario)"
            + " JOIN tipo_formulario tf ON tf.id_tipo_formulario=f.id_tipo_formulario"
            + " JOIN estado_formulario ef ON ef.id_estado_formulario=sf.id_estado_formulario"
            + " JOIN responsable_formulario r ON sf.id_responsable_formulario=r.id_responsable_formulario"
            + " JOIN persona p ON r.id_persona=p.id_persona"
            + " LEFT JOIN persona_juridica pj ON p.id_persona = pj.id_persona_juridica"
            + " JOIN tipo_identificacion_persona ti ON p.id_tipo_identificacion=ti.id_tipo_identificacion WHERE 1=1";

    public static final String SQL_CONDICION_FECHA_MOVIMIENTO = " AND sf.fecha_movimiento= :fecha_movimiento";

    public static final String SQL_CONDICION_NUMERO_INICIAL_FORMULARIO = " AND ce.trama= :trama AND ce.numero_final_rango>= :numero_inicial_formulario";
    public static final String SQL_CONDICION_NUMERO_FINAL_FORMULARIO = " AND ce.trama= :trama AND ce.numero_inicial_rango<= :numero_final_formulario";
    public static final String SQL_CONDICION_PLACA_RESPONSABLE = " AND r.placa= :placa";
    public static final String SQL_CONDICION_AREA_RESPONSABLE = " AND r.id_area_responsable= :id_area";
    public static final String SQL_CONDICION_DOCUMENTO_IDENTIFICACION = " AND p.id_tipo_identificacion= :id_tipo_identificacion AND p.numero_identificacion= :numero_identificacion";

    public static final String SQL_CONDICION_RANGO_FECHA_INICIAL_FINAL = " AND sf.fecha_movimiento BETWEEN :fecha_inicio AND :fecha_fin";
    public static final String SQL_CONDICION_ESTADO_FORMULARIO = " AND ef.id_estado_formulario = :id_estado_formulario";
    // public static final String SQL_CONDICION_BETWEEN_FECHA_MOVIMIENTO =
    // " AND :fecha_movimiento_desde <= sf.fecha_movimiento AND sf.fecha_movimiento <= :fecha_movimiento_hasta";
    public static final String SQL_CONDICION_BETWEEN_FECHA_APLICACION = " AND :fecha_aplicacion_desde <= dce.fecha_aplicacion_estado AND dce.fecha_aplicacion_estado <= :fecha_aplicacion_hasta";
    public static final String SQL_CONDICION_CODIGO_ORGANISMO = " AND rf.codigo_organismo = :codigo_organismo";
    public static final String SQL_CONDICION_TIPO_FORMULARIO = " AND tf.id_tipo_formulario = :id_tipo_formulario";
    public static final String SQL_CONDICION_ESTADOS_FORMULARIO = " AND ef.id_estado_formulario IN (:id_estados_formulario)";
    public static final String SQL_CONDICION_CODIGO_ORGANISMO_RESPONSABLE = " AND ot.codigo_organismo = :codigo_organismo_responsable";

    public static final String SQL_ORDER_BY = " ORDER BY dce.fecha_aplicacion_estado DESC";

    public static final String SQL_GROUP_BY = " GROUP BY(dce.id_detalle_cambio_estado)";

    public enum ParametrosConsulta {
        id_tipo_formulario, id_estado_formulario, fecha_movimiento, trama, numero_inicial_formulario, numero_final_formulario, placa, id_area, id_tipo_identificacion, numero_identificacion, fecha_movimiento_desde, fecha_movimiento_hasta, fecha_aplicacion_hasta, fecha_aplicacion_desde, codigo_organismo, codigo_organismo_responsable;
    }

}
