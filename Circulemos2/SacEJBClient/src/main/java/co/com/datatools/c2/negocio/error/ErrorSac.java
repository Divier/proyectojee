package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

public final class ErrorSac {

    public ErrorSac() {

    }

    public enum ConsultarActoAdministrativo implements ErrorInfo {

        COM_0_xx("COM_0_xx", "xxx"), //
        COM_0_yy("COM_0_xx", "yyy") //
        ;

        private final String codigoError;
        private final String descError;

        private ConsultarActoAdministrativo(String codigoError, String descError) {
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
