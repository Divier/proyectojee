package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

public final class ErrorImpugnacion {

    public ErrorImpugnacion() {

    }

    /**
     * Catalogo errores de negocio para JUR_004 Evaluar expediente
     * 
     * @author dixon.alvarez
     */
    public enum EvaluarExpediente implements ErrorInfo {

        JUR_004001("JUR_004001", "El estado del proceso es diferente a IMPUGNADO"), //
        JUR_004002("JUR_004002", "Error al generar el documento"), //
        JUR_004003("JUR_004003", "No se ha podido enviar el documento de notificacion por correo"),//
        ;

        private final String codigoError;
        private final String descError;

        private EvaluarExpediente(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }

    }

    /**
     * Catalogo errores de negocio para JUR_005 Realizar Fallo
     * 
     * @author julio.pinzon 2016-06-15
     */
    public enum RealizarFallo implements ErrorInfo {

        JUR_005001("JUR_005001", "No se pudo generar el documento de fallo de impugnacion"), //
        ;

        private final String codigoError;
        private final String descError;

        private RealizarFallo(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }
    }

    /**
     * Catalogo errores de negocio para JUR_006 Aprobar Fallo
     * 
     * @author divier.casas 2016-06-16
     */
    public enum AprobarFallo implements ErrorInfo {

        JUR_006001("JUR_006001", "No se pudo generar el documento de aprobacion del fallo"), //
        ;

        private final String codigoError;
        private final String descError;

        private AprobarFallo(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }
    }

    /**
     * Catalogo errores de negocio para COM_059 Apertura de impugnacion
     */
    public enum AperturaImpugnacion implements ErrorInfo {

        COM_059001("COM_059001", "No se pudo generar el documento de apertura de impugnacion"), //
        ;

        private final String codigoError;
        private final String descError;

        private AperturaImpugnacion(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }

    }

    /**
     * Catalogo errores de negocio para JUR_007 Evaluar expediente back
     * 
     */
    public enum EvaluarExpedienteBack implements ErrorInfo {

        JUR_007001("JUR_007001", "El estado del proceso es diferente a ENVIADO A ABOGADO DE CONTINUACION"), //
        JUR_007002("JUR_007002", "El id del proceso es nulo"), //
        ;

        private final String codigoError;
        private final String descError;

        private EvaluarExpedienteBack(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }

    }

    /**
     * Catalogo errores de negocio para JUR_016 Cierre de pruebas
     */
    public enum CierrePruebas implements ErrorInfo {

        JUR_016001("JUR_016001", "No se pudo generar el documento de cierre de pruebas"), //
        JUR_016002("JUR_016002", "Fecha límite de las solicitudes no es inferior a la fecha actual"), //
        JUR_016003("JUR_016003", "No existen solicitudes de prueba asociadas al proceso"), //
        JUR_016004("JUR_016004", "El estado del proceso no es válido para realizar la acción"), //

        ;

        private final String codigoError;
        private final String descError;

        private CierrePruebas(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }

    }

    /**
     * Catalogo errores de negocio para JUR_011 Registro de pruebas
     */
    public enum RegistroPruebas implements ErrorInfo {

        JUR_011001("JUR_011001", "No se pudo generar el documento de registro de pruebas"), //
        JUR_011002("JUR_011002", "No se adicionó nueva información de impulsos o pruebas"), //
        JUR_011003("JUR_011003", "El registro de pruebas es obligatorio"), //

        ;

        private final String codigoError;
        private final String descError;

        private RegistroPruebas(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }

    }

    /**
     * Catalogo errores de negocio para JUR_020 Solicitar Pruebas
     */
    public enum SolicitarPruebas implements ErrorInfo {

        JUR_020001("JUR_020001", "Error en la generacion del documento de solicitud de pruebas"), //
        JUR_020002("JUR_020002", "Fecha Solicitud Obligatoria"), //
        JUR_020003("JUR_020003", "Proceso obligatorio"), //
        JUR_020004("JUR_020004", "La fecha de solicitud debe ser superior a la fecha actual"), //
        JUR_020005("JUR_020005", "Estado 'evaluado' del proceso no encontrado"), //

        ;

        private final String codigoError;
        private final String descError;

        private SolicitarPruebas(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }

    }

    /**
     * Catalogo errores de negocio para JUR_021 Generar Prorroga
     */
    public enum GenerarProrroga implements ErrorInfo {

        JUR_021001("JUR_021001",
                "Cantidad de prorrogas solicitadas superan el parametro Número de veces permitidas para prórroga"), //
        JUR_021002("JUR_021002", "Fecha límite de entrega de pruebas es menor a la Fecha actual."), //
        JUR_021003("JUR_021003", "Origen de pruebas no es DIFERENTE a CIUDADANO."), //
        JUR_021004("JUR_021004", "Solicitud de pruebas tiene impulso Definitivo"), //
        JUR_021005("JUR_021005",
                "No es posible generar la prórroga, Fecha límite de entrega de pruebas supera la fecha máxima para cerrar el proceso de Impugnación"), //
        JUR_021006("JUR_021006", "El proceso no existe"), //
        JUR_021007("JUR_021007", "Los días prorroga son requeridos"), //
        JUR_021008("JUR_021008", "La solicitud de pruebas es requerida"), //
        ;

        private final String codigoError;
        private final String descError;

        private GenerarProrroga(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }

    }

    /**
     * Catalogo errores de negocio para JUR_008 Registrar acciones
     * 
     * @author dixon.alvarez
     */
    public enum RegistrarAcciones implements ErrorInfo {

        JUR_008001("JUR_008001", "Debe registrar al menos una acción para continuar el proceso"), //
        ;

        private final String codigoError;
        private final String descError;

        private RegistrarAcciones(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }

    }

    /**
     * Catalogo errores de negocio para JUR_008 Registrar acciones
     * 
     * @author dixon.alvarez
     */
    public enum ValidarInfraccionImpugnacion implements ErrorInfo {

        JUR_001001("JUR_001001", "Fecha inicial no debe ser mayor a hoy"), //
        JUR_001002("JUR_001002",
                "Fecha inicial debe ser menor al valor del campo \"fecha final de apertura del proceso\"."), //
        JUR_001003("JUR_001003", "Fecha final no debe ser mayor a hoy"), //
        ;

        private final String codigoError;
        private final String descError;

        private ValidarInfraccionImpugnacion(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }
    }

    /**
     * Catalogo errores de negocio para JUR_014 Fallo Impugnacion
     * 
     * @author divier.casas
     */
    public enum ValidarFalloImpugnacion implements ErrorInfo {

        JUR_014001("JUR_014001", "No se ha podido enviar el documento de notificacion por correo"), //
        ;

        private final String codigoError;
        private final String descError;

        private ValidarFalloImpugnacion(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descError;
        }
    }
}