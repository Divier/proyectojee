package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;
import co.com.datatools.c2.util.SearchableEnumeration;

public class ErrorFinanciacion {

    public ErrorFinanciacion() {

    }

    public enum ValidarReglaNegocioFinanciacion implements ErrorInfo {

        FIN_014_001("FIN_014_001", "El campo código tipo documento deudor esta vacio"), //
        FIN_014_002("FIN_014_002",
                "Debe ingresar la razon social debido a que el campo codigo tipo documento deudor esta con el código \"RUC\""), //
        FIN_014_003("FIN_014_003", "El campo número de identificación deudor esta vacio"), //
        FIN_014_004("FIN_014_004", "El campo primer nombre deudor esta vacio"), //
        FIN_014_005("FIN_014_005", "El campo primer apellido deudor esta vacio"), //
        FIN_014_006("FIN_014_006", "Cartera no existe para numero y tipo de obligacion"), //
        FIN_014_007("FIN_014_007", "El saldo de la cartera no coincide con el valor de la obligacion"), //
        FIN_014_008("FIN_014_008", "Formato de correo invalido del deudor"), //
        FIN_014_009("FIN_014_009", "Formato de correo invalido del codeudor"), //
        FIN_014_010("FIN_014_010", "Formato de telefono invalido del deudor"), //
        FIN_014_011("FIN_014_011", "Formato de telefono invalido del codeudor"), //
        FIN_014_012("FIN_014_012", "Cuota repetida en financiacion"), //
        FIN_014_013("FIN_014_013", "Obligacion repetida en financiacion"), //
        FIN_014_014("FIN_014_014", "Error inesperado"), //
        FIN_014_015("FIN_014_015", "La financiación ya existe"), //
        FIN_014_016("FIN_014_016", "La financiación debe tener al menos una cuota"), //
        FIN_014_017("FIN_014_017", "La financiación debe tener al menos una obligacion asociada"), //
        FIN_014_018("FIN_014_018", "La obligación tiene un estado que no permite financiarla"), //
        ;

        private final String codigoError;
        private final String descError;

        private ValidarReglaNegocioFinanciacion(String codigoError, String descError) {
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

    public enum EnumErrorCondicionesFinanciacion implements SearchableEnumeration<Integer> {
        ERROR_TIPO_CONVERSION_(1, "No corresponde al tipo de calculos de cuota"), //
        ;

        private int codigoError;
        private String descripcionError;

        private EnumErrorCondicionesFinanciacion(int codigoError) {
            this.codigoError = codigoError;
        }

        private EnumErrorCondicionesFinanciacion(int codigoError, String descripcionError) {
            this.codigoError = codigoError;
            this.descripcionError = descripcionError;
        }

        @Override
        public Integer getValue() {
            return codigoError;
        }

        public String getDescripcionError() {
            return descripcionError;
        }

    }

    public enum EnumErroresResultadoFinanciacion implements SearchableEnumeration<Integer> {
        RESULTADO_VALIDACION_EXITOSO(1, "Proceso de validación exitoso"), //
        RESULTADO_VALIDACION_ERROR(0, "Proceso de validación con errores"), //
        ;

        private int codigoError;
        private String descripcionError;

        private EnumErroresResultadoFinanciacion(int codigoError) {
            this.codigoError = codigoError;
        }

        private EnumErroresResultadoFinanciacion(int codigoError, String descripcionError) {
            this.codigoError = codigoError;
            this.descripcionError = descripcionError;
        }

        @Override
        public Integer getValue() {
            return codigoError;
        }

        public String getDescripcionError() {
            return descripcionError;
        }
    }

    public enum EnumErrorSimulacionFinanciacion implements ErrorInfo {
        FIN_018_001("FIN_018_001", "No se encuentran condiciones de financiaciones vigentes"), //
        FIN_018_002("FIN_018_002", "Monto a financiar no permitido."), //
        FIN_018_003("FIN_018_003",
                "No existe una configuración de porcentaje de cuota inicial para el valor total a financiar"), //
        FIN_018_004("FIN_018_004", "Valor minimo de la cuota inicial es menor al permitido configurado"), //
        FIN_018_005("FIN_018_005", "No se pudo generar el documento de financiación"), //
        FIN_018_006("FIN_018_006", "No se encontro ningun interes de financiación vigente en el sistema"), //
        ;

        private final String codigoError;
        private final String descError;

        private EnumErrorSimulacionFinanciacion(String codigoError, String descError) {
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

    public enum EnumErroresDejarFirmeFinanciacion implements ErrorInfo {
        FIN_024001("FIN_024001", "La financiación NO tiene pagada la primera cuota"), //
        FIN_024002("FIN_024002", "La financiación NO está prefinanciada"), //
        FIN_024003("FIN_024003", "El id de la financiación es obligatorio"), //
        ;

        private String codigoError;
        private String descripcionError;

        private EnumErroresDejarFirmeFinanciacion(String codigoError, String descripcionError) {
            this.codigoError = codigoError;
            this.descripcionError = descripcionError;
        }

        @Override
        public String getCodigoError() {
            // TODO Auto-generated method stub
            return codigoError;
        }

        @Override
        public String getDescError() {
            // TODO Auto-generated method stub
            return descripcionError;
        }

    }

    /**
     * Enum error par generacion de documentos de financiacion
     * 
     * @author giovanni.velandia
     * 
     */
    public enum EnumErroDocumentoFinanciacion implements ErrorInfo {
        FIN_029001("FIN_029001", "Error al generar documento de recibo de pago"), //
        FIN_029002("FIN_029002", "Error al generar documento de cuadro de amortizacion"), //
        ;

        private String codigoError;
        private String descripcionError;

        private EnumErroDocumentoFinanciacion(String codigoError, String descripcionError) {
            this.codigoError = codigoError;
            this.descripcionError = descripcionError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        @Override
        public String getDescError() {
            return descripcionError;
        }
    }

    public enum EnumErroresInconsistenciaFinanciacion implements ErrorInfo {
        FIN_01000001("FIN_01000001",
                "La Inconsistencia del detalle de la financiación debe tener asociada la financiación"), //
        FIN_01000002("FIN_01000002",
                "La Inconsistencia del detalle de la financiación debe tener asociadas las inconsistencias generadas"), //
        FIN_01000003("FIN_01000003",
                "La Inconsistencia del detalle de las cuotas de  financiación debe tener asociada la financiación"), //
        FIN_01000004("FIN_01000004",
                "La Inconsistencia del detalle de las cuotas de  financiación debe tener asociadas las inconsistencias generadas"), //
        ;

        private String codigoError;
        private String descripcionError;

        private EnumErroresInconsistenciaFinanciacion(String codigoError, String descripcionError) {
            this.codigoError = codigoError;
            this.descripcionError = descripcionError;
        }

        @Override
        public String getCodigoError() {
            // TODO Auto-generated method stub
            return codigoError;
        }

        @Override
        public String getDescError() {
            // TODO Auto-generated method stub
            return descripcionError;
        }

    }
}
