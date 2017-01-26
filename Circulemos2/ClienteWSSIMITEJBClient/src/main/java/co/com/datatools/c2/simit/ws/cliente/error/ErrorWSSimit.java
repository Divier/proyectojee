package co.com.datatools.c2.simit.ws.cliente.error;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Contiene todas las enumeraciones de los errores probables que suceden en el webservice
 * 
 * @author luis.forero(2016-03-30)
 * 
 */
public class ErrorWSSimit {

    /**
     * Errores del Web service de carga de archivos de comparendos de SIMIT.
     */
    public enum ErrorWSCargaArchivoSIMIT implements ErrorInfo {
        ;
        private String codigoError;
        private String descError;

        private ErrorWSCargaArchivoSIMIT(String codigoError, String descError) {
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
