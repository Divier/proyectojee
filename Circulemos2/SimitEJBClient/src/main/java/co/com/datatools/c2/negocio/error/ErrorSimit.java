package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

public final class ErrorSimit {

    public ErrorSimit() {

    }

    /**
     * Catalogo errores de negocio envio a Simit SIM_006
     */
    public enum EnumGenerarNotificacionComparendo implements ErrorInfo {
        SIM_006001("SIM_006001", "NO existen comparendo disponibles para enviar"), //
        ;

        /**
         * Contiene el codigo del error
         */
        private final String codigoError;

        /**
         * Contiene la descripcion del error
         */
        private final String descError;

        /**
         * Constructor con codigo y descripcion
         * 
         * @param codigoError
         * @param descError
         */
        private EnumGenerarNotificacionComparendo(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return this.codigoError;
        }

        @Override
        public String getDescError() {
            return this.descError;
        }
    }

    public enum EnumRegistrarNotificacionComparendo implements ErrorInfo {
        SIM_008001("SIM_008001", "El rango de fechas supera el número de días de la consulta"), //
        SIM_008002("SIM_008002", "Fallo - Error de conexión al repositorio"), //
        ;

        private final String codigoError;
        private final String descError;

        private EnumRegistrarNotificacionComparendo(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return this.codigoError;
        }

        @Override
        public String getDescError() {
            return this.descError;
        }
    }

    public enum EnumValidarComparendosParaNotificarASimit implements ErrorInfo {
        SIM_009001("SIM_009001", "Se debe corregir la información del comparendo. Campo fecha infracción"), //
        SIM_009002("SIM_009002", "Se debe corregir la información del comparendo. Campo hora infracción"), //
        SIM_009003("SIM_009003",
                "Se debe corregir la información del comparendo. Campo dirección del lugar de la infracción"), //
        SIM_009004("SIM_009004", "Se debe corregir la información del comparendo. Campo municipio"), //
        SIM_009005("SIM_009005", "Se debe corregir la información del comparendo. Campo radio de acción"), //
        SIM_009006("SIM_009006",
                "Se debe corregir la información del comparendo. Campo numero de documento del infractor"), //
        SIM_009007("SIM_009007",
                "Se debe corregir la información del comparendo. Campo tipo de documento del infractor"), //
        SIM_009008("SIM_009008", "Se debe corregir la información del comparendo. Campo placa agente"), //
        SIM_009009("SIM 009009", "Se debe corregir la información del comparendo. Campo valor comparendo"), //
        SIM_009010("SIM_009010",
                "Se debe corregir la información del comparendo. Campo municipio del organismo de tránsito"), //
        SIM_009011("SIM_009011", "Se debe corregir la información del comparendo. Campo estado del comparendo"), //
        SIM_009012("SIM_009012", "Se debe corregir la información del comparendo. Campo codigo de infracción"), //
        SIM_009013("SIM_009013", "Se debe corregir la información del comparendo. Campo grado de alcoholemia"), //
        ;

        private final String codigoError;
        private final String descError;

        private EnumValidarComparendosParaNotificarASimit(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return this.codigoError;
        }

        @Override
        public String getDescError() {
            return this.descError;
        }

    }

    /**
     * SIM_001 "Notificar comparendo a SIMIT por Webservice"
     * 
     * @author luis.forero
     * 
     */
    public enum EnumNotificacionComparendoSIMIT implements ErrorInfo {
        /**
         * Identificador (cicomparendo) Comparendo por notificar a SIMIT vacio
         */
        SIM_001001("SIM_001001", "Identificador (cicomparendo) Comparendo por notificar a SIMIT vacio"), //
        /**
         * Origen de notificacion SIMIT se encuentra vacia
         */
        SIM_001002("SIM_001002", "Origen de notificacion SIMIT se encuentra vacia"), //
        ;
        private String codigoError;
        private String descError;

        private EnumNotificacionComparendoSIMIT(String codigoError, String descError) {
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
