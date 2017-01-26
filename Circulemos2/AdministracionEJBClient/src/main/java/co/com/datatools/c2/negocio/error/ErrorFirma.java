package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Catalogo de errores para Administracion
 * 
 * @author robert.bautista
 * @since 2014-03-05
 */
public final class ErrorFirma {

    private ErrorFirma() {
    }

    /**
     * Catalogo errores de negocio
     */
    public enum RegistrarFirma implements ErrorInfo {
        ADM_070001("ADM_070001", "La información de la firma es obligatoria."), //
        ADM_070002("ADM_070002", "La información de la persona es obligatoria."), //
        ADM_070003("ADM_070003", "Error al actualizar la persona."), //
        ADM_070004("ADM_070004", "Error al registrar la firma."), //
        ADM_070005("ADM_070005", "Error al convertir la firma."), //
        ;

        /**
         * Contiene el código del error
         */
        private final String codigoError;

        /**
         * Contiene la descripción del error
         */
        private final String descError;

        /**
         * Constructor con código y descripción
         * 
         * @param codigoError
         * 
         * @param descError
         */
        private RegistrarFirma(String codigoError, String descError) {
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