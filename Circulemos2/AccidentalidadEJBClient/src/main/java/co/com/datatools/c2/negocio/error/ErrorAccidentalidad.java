package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

public final class ErrorAccidentalidad {

    private ErrorAccidentalidad() {

    }

    public enum AdministrarAccidentalidad implements ErrorInfo {
        /**
         * No se pudo generar documento de ubicabilidad
         */
        ACC_00201("ACC_00201", "Error al guardar el registro de accidentalidad"), //
        ACC_00202("ACC_00202", "Error al guardar archivo de accidentalidad"), //

        ;
        private final String codigoError;
        private final String descError;

        private AdministrarAccidentalidad(String codigoError, String descError) {
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