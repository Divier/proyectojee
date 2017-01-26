package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

public final class ErrorAgente {

    public ErrorAgente() {

    }

    public enum AgregarComparendoAgente implements ErrorInfo {

        COM_089001("COM_089001", "Comparendo obligatorio"), //
        COM_089002("COM_089002", "Tipo de identificacion del agente obligatorio"), //
        COM_089003("COM_089003", "Numero de identificacion del agente obligatorio"), //
        COM_089004("COM_089004", "Nombre del agente obligatorio"), //
        COM_089005("COM_089005", "Apellido del agente obligatorio"), //
        COM_089006("COM_089006", "Placa del agente obligatorio"), //
        ;

        private final String codigoError;
        private final String descError;

        private AgregarComparendoAgente(String codigoError, String descError) {
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
