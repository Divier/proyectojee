package co.com.datatools.c2.enumeracion;

public enum EnumCategoriaDocumento {
    SOLICITUD_SALDO_FAVOR("/c2/solicitudSaldoFavor", null), //
    AUTORIZACION_RANGO_FORMULARIO("/c2/autorizacionRangoFormulario", null), //
    SOPORTE_CAMBIO_ESTADO_FORMULARIOS("/c2/soporteCambioEstado", null), //
    COMPARENDO_EVIDENCIA("/c2/evidenciaComparendo", null), //
    PROCESA_EVIDENCIA("/c2/evidenciaProcesaComparendo", null), //
    ENVIO_COMPA_SIMIT("/c2/envioComparendoSimit", "/c2/%s/SIMIT/%s"), //
    EVIDENCIA_COMPARENDO_INCONSISTENTE("/c2/evidenciasComparendoInconsistente", "/%s/Ingresar comparendo"), //
    EVIDENCIA_COMPARENDO("K:/imagenes/", "/%s/%s/"), //
    ARCHIVO_PLANO_NOTIF_CORREO("/c2/Notificación de comparendos", null), //
    FIRMA_PERSONA("/c2/firmaPersona", null), //
    ACCIDENTALIDAD_EVIDENCIA("/c2/evidenciaAccidentalidad",
            null), NOTIFICACION_COACTIVO_COURIER("/c2/notificaCoactivoCourier", null), //
    ARCHIVO_CARGUE_COACTIVOS("/c2/archivoCargueCoactivos", null), //
    ARCHIVO_CONSULTA_ANALISIS_UBIC("/c2/archivoAnalisisUbic", null), //
    CARGUE_MASIVO_UBICABILIDAD("/c2/cargueMasivoUbicabilidad", null), //
    CARGUE_MASIVO_UBICABILIDAD_INCONSISTENCIAS("/c2/cargueMasivoUbicabilidadInconsistencias", null), //
    DOCUMENTO_ESCRITO_MOTIVACION("/c2/escritoMotivacion", null), //
    ARCHIVO_NO_UBICABILIDAD("/c2/noUbicabilidad", null), //
    ARCHIVO_CARGUE_COACTIVOS_INCONSISTENCIAS("/c2/cargueMasivoUbicabilidaInconsistencias", null), //
    ARCHIVO_CARGUE_COACTIVOS_VALIDOS("/c2/cargueMasivoUbicabilidaValidos", null),//
    ;

    private EnumCategoriaDocumento(String carpeta, String formato) {
        this.carpeta = carpeta;
        this.formato = formato;
    }

    private String carpeta;
    private String formato;

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public String getFormato() {
        return formato;
    }

}
