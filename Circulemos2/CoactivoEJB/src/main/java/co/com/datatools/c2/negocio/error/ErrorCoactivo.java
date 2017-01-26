package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

public final class ErrorCoactivo {

    public ErrorCoactivo() {

    }

    /**
     * Catalogo errores de negocio para COAC_002001
     * 
     * @author diego.lozano
     */
    public enum GenerarCoactivo implements ErrorInfo {

        COAC_002001("COAC_002001", "Error en generación de documentos de mandamiento de pago"), //
        COAC_002002("COAC_002002", "Error al registrar coactivo, los parametros no son válidos"), //
        COAC_002003("COAC_002003", "Error al registrar coactivo, el deudor no existe en el sistema"), //
        COAC_002004("COAC_002004", "Error al registrar coactivo, el tipo de obligación no existe"), //
        COAC_002005("COAC_002005", "Error al registrar coactivo, el tipo de costa procesal no existe"), //
        COAC_002006("COAC_002006", "Error al registrar coactivo, no existe una configuración vigente"), //
        COAC_002007("COAC_002007", "Error al registrar coactivo, no existen valores para la configuración"), //
        COAC_002008("COAC_002008", "Error en generación de documento de notificacion de coactivo"), //
        ;

        private final String codigoError;
        private final String descError;

        private GenerarCoactivo(String codigoError, String descError) {
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

    public enum NotificarCoactivoCourier implements ErrorInfo {
        COAC_003001("COAC_003001", "El archivo no contiene la estructura requerida"), //
        COAC_003002("COAC_003002", "El archivo contiene datos inconsistentes"), //
        COAC_003003("COAC_003003", "Ha ocurrido un error cargando el archivo al sistema"), //
        COAC_003004("COAC_003004", "Error al registrar coactivo, el tipo de obligación no existe"), //
        COAC_003005("COAC_003005", "Error al registrar coactivo, el tipo de costa procesal no existe"), //
        COAC_003006("COAC_003006", "Error al registrar coactivo, no existe una configuración vigente"), //
        COAC_003007("COAC_003007", "Error al registrar coactivo, no existen valores para la configuración"), //
        ;

        private final String codigoError;
        private final String descError;

        private NotificarCoactivoCourier(String codigoError, String descError) {
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
     * Catalogo errores de negocio para COAC_009
     * 
     * @author Dixon.Alvarez
     */
    public enum CargueAnalisisCoactivo implements ErrorInfo {

        COAC_009001("COAC_009001", "No existe una configuración de condiciones de coactivo vigente"), //
        COAC_009002("COAC_009002", "Ocurrio un error al leer el archivo, por favor intente de nuevo"), //
        COAC_009003("COAC_009003", "Error al intentar validar el archivo"), //
        ;

        private final String codigoError;
        private final String descError;

        private CargueAnalisisCoactivo(String codigoError, String descError) {
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
     * Catalogo de errores para la radicacion de excepciones
     * 
     * @author giovanni.velandia
     *
     */
    public enum RadicarExcepciones implements ErrorInfo {

        COAC_015001("COAC_015001", "No se puede registrar excepción. El coactivo se encuentra cerrado"), //
        COAC_015002("COAC_015002", "No se puede registrar excepción. El coactivo ya tiene una excepción"), //
        COAC_015003("COAC_015003",
                "No se puede registrar excepción. El coactivo ya venció el plazo para radicar excepciones"), //
        COAC_015004("COAC_015004", "Error al guardar los anexos"), //
        COAC_015005("COAC_015005", "No se puede registrar el fallo de excepción. El coactivo se encuentra cerrado"), //
        COAC_015006("COAC_015006",
                "No se puede registrar el fallo de excepción. El coactivo No tiene una excepción radicada"), //
        COAC_015007("COAC_015007",
                "No se puede registrar el fallo de excepción. El coactivo ya tiene el fallo de la excepción"),//
        ;

        private final String codigoError;
        private final String descError;

        private RadicarExcepciones(String codigoError, String descError) {
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