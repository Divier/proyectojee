package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * 
 * @author giovanni.velandia
 *
 */
public final class ErrorConfiguracionHorario {

    public ErrorConfiguracionHorario() {

    }

    public enum ConfiguracionHorario implements ErrorInfo {

        ADM_074001("ADM_074001", "El intervalo de horas ya esta registrado"), //
        ;

        private final String codigoError;
        private final String descError;

        private ConfiguracionHorario(String codigoError, String descError) {
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
