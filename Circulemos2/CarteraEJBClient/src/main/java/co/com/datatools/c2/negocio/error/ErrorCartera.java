package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Catalogo de errores para Cartera
 * 
 */
public final class ErrorCartera {

    private ErrorCartera() {
    }

    public enum RegistrarCartera implements ErrorInfo {
        CAR_003001("CAR_003001",
                "Existe cartera vigente con el tipo de obligación y número de referencia de obligación"), //
        ;

        private final String codigoError;
        private final String descError;

        private RegistrarCartera(String codigoError, String descError) {
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

    public enum AfectarCartera implements ErrorInfo {
        CAR_010001("CAR_010001", "La actividad de cartera no existe"), //
        ;

        private final String codigoError;
        private final String descError;

        private AfectarCartera(String codigoError, String descError) {
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

    public enum CambiarEstadoCartera implements ErrorInfo {
        CAR_031001("CAR_031001", "El estado de obligación nuevo es igual al estado de obligación actual"), //
        ;

        private final String codigoError;
        private final String descError;

        private CambiarEstadoCartera(String codigoError, String descError) {
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

    public enum LiquidarIntereses implements ErrorInfo {
        CAR_032001("CAR_032001", "No existe resultados para los valores de consulta ingresados"), //
        CAR_032002("CAR_032002", "Fecha inicial para calcular intereses no debe ser mayor a hoy"), //
        CAR_032003("CAR_032003",
                "Fecha final para calcular intereses no debe ser menor a la fecha inicial para calcular intereses"), //
        ;

        private final String codigoError;
        private final String descError;

        private LiquidarIntereses(String codigoError, String descError) {
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

    public enum RegistrarTasaInteres implements ErrorInfo {
        ADM_016001("ADM_016001", "Existe tasa de interés vigente para la misma clase de interés"), //
        ;

        private final String codigoError;
        private final String descError;

        private RegistrarTasaInteres(String codigoError, String descError) {
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
}