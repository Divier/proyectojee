package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

public final class ErrorRecaudo {

    public ErrorRecaudo() {

    }

    public enum ValidarReglaNegocioRecaudo implements ErrorInfo {

        REC_006_001("REC_006_001", "No existe el organismo de transito ingresado"), //
        REC_006_002("REC_006_002", "Fecha de transaccion superior a la actual"), //
        REC_006_003("REC_006_003", "Total Recaudo DEBE ser la suma de los campos"), //
        ;

        private final String codigoError;
        private final String descError;

        private ValidarReglaNegocioRecaudo(String codigoError, String descError) {
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
