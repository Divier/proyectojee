package co.com.datatools.c2.axis.ws.cliente.error;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Contiene todas las enumeraciones de los errores probables que suceden en el webservice
 * 
 * @author luis.forero(2016-03-30)
 * 
 */
public class ErrorWSAxis {

    /**
     * Errores del Web service de registrar financiamiento
     */
    public enum ErrorWSRegistrarFinanciamiento implements ErrorInfo {

        REG_FIN_001("REG_FIN_001", "No se encuentran datos de conexion del WS"), //
        REG_FIN_002("REG_FIN_002", "Error inesperado en llamado a WS"), //
        REG_FIN_003("REG_FIN_003", "Error al intentar registrar financiacion en AXIS"), //
        REG_FIN_004("REG_FIN_004", "Error al guardar el log de conexión a AXIS"), //
        ;
        private String codigoError;
        private String descError;

        private ErrorWSRegistrarFinanciamiento(String codigoError, String descError) {
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
     * Errores del Web service de anular financiamiento
     */
    public enum ErrorWSAnularFinanciamiento implements ErrorInfo {

        ANU_FIN_001("ANU_FIN_001", "No se encuentran datos de conexion del WS"), //
        ANU_FIN_002("ANU_FIN_002", "Error inesperado en llamado a WS"), //
        ANU_FIN_003("ANU_FIN_003", "Error al intentar anular financiacion en AXIS"), //
        ANU_FIN_004("ANU_FIN_004", "Error al guardar el log de conexión a AXIS"), //
        ;
        private String codigoError;
        private String descError;

        private ErrorWSAnularFinanciamiento(String codigoError, String descError) {
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
     * Errores del Web service de impugnacion
     */
    public enum ErrorWSImpugnacion implements ErrorInfo {

        REG_IMP_001("REG_IMP_001", "No se encuentran datos de conexion del WS"), //
        REG_IMP_002("REG_IMP_002", "Error inesperado en llamado a WS"), //
        REG_IMP_003("REG_IMP_003", "Error al intentar impugnar en AXIS"), //
        REG_IMP_004("REG_IMP_004", "Error al guardar el log de conexión a AXIS"), //
        ;
        private String codigoError;
        private String descError;

        private ErrorWSImpugnacion(String codigoError, String descError) {
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

    public enum ErrorWSCoactivo implements ErrorInfo {

        REG_COAC_001("REG_COAC_001", "No se encuentran datos de conexion del WS"), //
        REG_COAC_002("REG_COAC_002", "Error inesperado en llamado a WS"), //
        REG_COAC_003("REG_COAC_003", "Error al intentar registrar coactivo en AXIS"), //
        REG_COAC_004("REG_COAC_004", "Error al guardar el log de conexión a AXIS"), //
        ;

        private String codigoError;
        private String descError;

        private ErrorWSCoactivo(String codigoError, String descError) {
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
