package co.com.datatools.c2.negocio.error.gestiondocs;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Catalogo de errores para Formularios
 * 
 * @author julio.pinzon
 * 
 */
public final class ErrorGestionDocumentos {

    private ErrorGestionDocumentos() {
    }

    /**
     * Catalogo errores de negocio
     */
    public enum GestionDocumentos implements ErrorInfo {
        GESDOC_001("GESDOC_001", "No se encontro el archivo"), //
        GESDOC_002("GESDOC_002", "No se pudo guardar archivo en el repositorio"), //
        GESDOC_FILESYSTEM_001("GESDOC_FILESYSTEM_001", "No es posible crear la carpeta"), //
        GESDOC_FILESYSTEM_002("GESDOC_FILESYSTEM_002", "No es posible escribir el archivo"), //
        GESDOC_FILESYSTEM_003("GESDOC_FILESYSTEM_003", "No es posible encontrar el archivo"), //
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
        private GestionDocumentos(String codigoError, String descError) {
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