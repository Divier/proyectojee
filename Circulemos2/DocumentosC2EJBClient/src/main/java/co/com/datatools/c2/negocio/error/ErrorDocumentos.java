package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Catalogo de errores para documentos
 * 
 * @author julio.pinzon
 * 
 */
public final class ErrorDocumentos {

    private ErrorDocumentos() {
    }

    /**
     * Catalogo errores de negocio
     */
    public enum Documentos implements ErrorInfo {
        ADM_073001("ADM_073001", "Se presento un error al generar documento"), //
        ADM_073002("ADM_073002", "Se presento un error al consultar los documentos pdf"), //
        ADM_073003("ADM_073003", "No se encontraron datos del numero de comparendo"), //
        ADM_073004("ADM_073004", "El comparendo no tiene infractor registrado"), //
        ADM_073005("ADM_073005", "Tipo de documento generado invalido"), //
        ADM_073006("ADM_073006", "Se presento un error al consultar plantillas del proceso"), //
        ADM_073007("ADM_073007", "Se presento un error al consultar configuracion de la plantilla"), //
        /**
         * COM_055001=El comparendo no tiene infractor registrado
         */
        COM_055001("COM_055001", "El comparendo no tiene infractor registrado"), //
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
         * 
         * @param descError
         */
        private Documentos(String codigoError, String descError) {
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
     * Catalogo errores de negocio
     */
    public enum Sincronizacion implements ErrorInfo {
        ADM_000001("ADM_000001", "Los parámetros enviados no son válidos"), //
        ADM_000002("ADM_000002", "Error al registrar al agente en documentos"), //
        ADM_000003("ADM_000003", "Error al actualizar al agente en documentos"), //
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
         * 
         * @param descError
         */
        private Sincronizacion(String codigoError, String descError) {
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