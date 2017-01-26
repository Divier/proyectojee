package co.com.datatools.datatimer.enumeraciones;

public enum EnumJob {

    JOB_INCONSISTENCIAS_COMPARENDOS(1, "EnvioInconsistenciasComparendos", 171), //
    JOB_INCONSISTENCIAS_RECAUDO(2, "JobEnvioInconsistenciasRecaudo", 172), //
    JOB_RECHAZOS_RECAUDO(3, "JobEnvioRechazosRecaudo"), //
    JOB_REPLICA_PAGO(4, "JobReplicarPago", 173), //
    JOB_SOLICITUD_COMPARENDOS_TERCEROS(5, "JobSolicitudComparendosTerceros", 175), //
    JOB_REGISTRAR_OBLIGACION_SAC(6, "JobRegistroObligacionSac", 177), //
    JOB_REPLICAR_NOVEDADES_SAC(7, "JobReplicarNovedadesSac", 178), //
    JOB_REPLICAR_UBICABILIDAD_SAC(8, "JobReplicarUbicabilidadSac", 179), //
    JOB_SOLICITUD_UBICABILIDAD_TERCEROS(9, "JobSolicitudUbicabilidadTerceros", 180), //
    JOB_INCONSISTENCIAS_CONCILIACION_RECAUDO(10, "JobEnvioInconsistenciasConciliacionRecaudo", 181), //
    JOB_REPLICAR_FINANCIACIONES(11, "JobReplicarFinanciaciones", 183), //
    JOB_ACTUALIZAR_GESTION(12, "JobActualizarGestion", 185), //
    JOB_RECIBIR_GESTION_COBRO(13, "JobRecibirGestionSac", 184), //
    JOB_VALIDAR_CONCILIACION_RECAUDO(14, "JobValidarConciliacionRecaudo", 182), //
    JOB_ACTUALIZAR_OBLIGACION_FINANCIADA_SAC(15, "JobActualizarObligacionFinanciadaSac", 188), //
    JOB_CIERRE_PRUEBAS_IMPUGNACION(16, "JobCierrePruebasImpugnacion", 197), //
    JOB_VALIDAR_FINANCIACION_CUOTA_PENDIENTE(17, "JobValidarFinanciacionCuotaPendiente", 199), //
    JOB_ABRIR_PROCESO_COACTIVO_OBLIGACIONES(18, "JobAbrirProcesoCoactivoObligaciones", 202), //
    JOB_VALIDAR_AGENTE(19, "JobvalidarAgente", 215), //
    JOB_ENVIO_COMPARENDO_E_NOTIFICA(20, "JobEnvioComparendoENotifica", 219), //
    JOB_CONSULTA_CIRCUITO_E_NOTIFICA(21, "JobConsultaCircuitoENotifica", 221), //
    ;

    private int id;
    private String nombreJob;
    private int parametroExpresion;

    private EnumJob(int id) {
        this.id = id;
    }

    private EnumJob(int id, String nombreJob) {
        this.id = id;
        this.nombreJob = nombreJob;
    }

    private EnumJob(int id, String nombreJob, int parametroExpresion) {
        this.id = id;
        this.nombreJob = nombreJob;
        this.parametroExpresion = parametroExpresion;
    }

    public int getId() {
        return id;
    }

    public String getNombreJob() {
        return nombreJob;
    }

    public void setNombreJob(String nombreJob) {
        this.nombreJob = nombreJob;
    }

    public int getParametroExpresion() {
        return parametroExpresion;
    }

    public void setParametroExpresion(int parametroExpresion) {
        this.parametroExpresion = parametroExpresion;
    }
}