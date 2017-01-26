package co.com.datatools.c2.negocio.error.parametrizacion;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Catalogo de errores para Parametrizacion
 * 
 * @author robert.bautista
 * @since 2014-03-05
 */
public final class ErrorParametrizacion {

    private ErrorParametrizacion() {
    }

    public enum ParametrizacionConfiguraciones implements ErrorInfo {
        ADM_066001("ADM_066001",
                "No es posible guardar el registro, los datos de entrada ingresados ya existen en otra parametrización"), //
        ADM_066002("ADM_066002", "No se cumple la validación campos obligatorios"), //
        ADM_066003("ADM_066003", "No se cumplen las validaciones de longitud de campos"), //
        ADM_066004("ADM_066004", "No se cumplen las validaciones de campos"), //
        ADM_066005("ADM_066005", "No se cumplen las validaciones de rangos de fechas"), //
        ADM_066007("ADM_066007", "No se encuentra ninguna parametrización válida para la configuración"), //
        ADM_066008("ADM_066008",
                "Se encontraron múltiples registros parametrizados que cumplen con los filtros, se esperaba solo uno"), //
        ;
        private final String codigoError;
        private final String descError;

        private ParametrizacionConfiguraciones(String codigoError, String descError) {
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

    /**
     * Catalogo errores de negocio
     */
    public enum AdministrarCatalogoSimple implements ErrorInfo {
        ADM_063001(
                "ADM_063001",
                "No es posible guardar los datos debido a que el valor en Nombre ítem o Sigla ítem, ya se encuentran registrados en el sistema."), //
        ADM_063002("ADM_063002", "El item a eliminar se encuentra en uso."), //
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
        private AdministrarCatalogoSimple(String codigoError, String descError) {
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