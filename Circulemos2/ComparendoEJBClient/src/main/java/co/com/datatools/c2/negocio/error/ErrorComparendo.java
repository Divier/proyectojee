package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

public final class ErrorComparendo {

    public ErrorComparendo() {

    }

    public enum CambiarEstadoSeguimientoComparendo implements ErrorInfo {

        COM_008001("COM_008001", "No existe comparendo impuesto y paso no es previo a imposicion de comparendo"), //
        COM_008002("COM_008002", "El infractor enviado no coincide y el comparendo no es por fotomulta"), //
        COM_008003("COM_008003", "No coincide infractor y es fotomulta - El infractor no existe"), //
        COM_008004("COM_008004", "No se pudo anular reincidencia de infractor"), //
        COM_008005("COM_008005", "No se pudo registrar trazabilidad de comparendo recibida"), //
        COM_008006("COM_008006", "No se pudo afectar la cartera del comparendo"), //
        ;

        private final String codigoError;
        private final String descError;

        private CambiarEstadoSeguimientoComparendo(String codigoError, String descError) {
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

    public enum GenerarActoAdministrativo implements ErrorInfo {

        COM_009001("COM_009001", "Inconsistencia de fechas de imposicion y notificacion en el comparendo"), //
        COM_009002("COM_009002", "La fecha de imposicion o de notificacion es nula"), //
        ;

        private final String codigoError;
        private final String descError;

        private GenerarActoAdministrativo(String codigoError, String descError) {
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

    public enum ConsultarActoAdministrativo implements ErrorInfo {

        COM_012001("COM_012001", "El rango de fechas esta incompleto"), //
        COM_012002("COM_012002", "La fecha inicial es posterior a la fecha final"), //
        COM_012003("COM_012003", "La fecha final es posterior a la fecha actual"), //
        COM_012004("COM_012004",
                "No esta configurado el parametro de cantidad de dias maximo para realizar consulta de resoluciones"), //
        COM_012005("COM_012005", "El rango de fechas supera el maximo de dias permitido por el sistema");

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

    public enum ConsultarSeguimientoComparendo implements ErrorInfo {

        COM_016001("COM_016001", "El rango de fechas esta incompleto"), //
        COM_016002("COM_016002", "La fecha inicial es posterior a la fecha final"), //
        COM_016003("COM_016003",
                "No esta configurado el párametro de cantidad de días máximo para realizar consulta de seguimiento"), //
        COM_016004("COM_016004", "El rango de fechas supera el máximo de días permitido por el sistema"), //
        COM_016005("COM_016005", "El comparendo no fue encontrado en el sistema."), //
        ;

        private final String codigoError;
        private final String descError;

        private ConsultarSeguimientoComparendo(String codigoError, String descError) {
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
    public enum ConsultarComparendo implements ErrorInfo {
        COM_021001("COM_021001", "Problemas cargando la imagen del comparendo."), //
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
        private ConsultarComparendo(String codigoError, String descError) {
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
     * Catalogo errores de negocio consulta comparendos COM-045
     */
    public enum ConsultarComparendos implements ErrorInfo {
        COM_045001("COM_045001",
                "La cantidad de dias entre la fecha inicial y final de imposicion excede el valor del parametro"), //
        COM_045002("COM_045002", "A parte del organismo de transito se debe seleccionar alguno de los otros filtros"), //
        COM_045003("COM_045003", "Datos de evidencia para nombrar archivo de evidencia son nulos"), //
        COM_045004("COM_045004", "Código de tipo de evidencia para nombrar archivo de evidencia es nulo"), //
        COM_045005("COM_045005", "Datos de archivo para nombrar archivos de evidencia son nulos"), //
        COM_045006("COM_045006", "La extensión del archivo de evidencia es inválida. Solo: TIFF, PDF, PNG, JPG"), //
        COM_045007("COM_045007", "El tamaño del archivo de evidencia es mayor al permitido"), //
        COM_045008("COM_045008", "No se encontró el ID de la evidencia a eliminar asociada al comparendo"), //
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
         * @param descError
         */
        private ConsultarComparendos(String codigoError, String descError) {
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
     * Catalogo errores de negocio envio desde COM_048 Liquidar Tarifa Comparendo
     * 
     * @author giovanni.velandia
     */
    public enum EnumLiquidarTarifaComparendo implements ErrorInfo {
        COM_048001("COM_048001", "La fecha de imposición del comparendo no puede ser mayor a hoy"), //
        COM_048002("COM_048002", "No esta configurada la infracción"), //
        COM_048003("COM_048003", "Es requerida la clase de servicio"), //
        COM_048004("COM_048004", "Es requerido el grado de alcoholemia"), //
        COM_048005("COM_048005", "El grado de alcoholemia no esta dentro del rango establecido"), //
        COM_048006("COM_048006", "Es requerido el Numero de veces reincidente"), //
        COM_048007("COM_048007", "Es requerido se negó a presentar la prueba?"), //
        COM_048008("COM_048008", "No existe resultados para los valores de consulta ingresados"), //
        ;

        private final String codigoError;
        private final String descError;

        private EnumLiquidarTarifaComparendo(String codigoError, String descError) {
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
     * Catalogo errores de negocio notificacion presencial COM-049
     */
    public enum NotificarComparendosPresencialmente implements ErrorInfo {
        COM_049001("COM_049001", "A parte del organismo de transito se debe seleccionar alguno de los otros filtros"), //
        COM_049002("COM_049002",
                "La 'Fecha Notificación'  debe ser MAYOR O IGUAL a la 'Fecha de imposición comparendo'."), //
        COM_049003("COM_049003", "Tipo de notificacion no permitida"), //
        COM_049004("COM_049004", "No se encontraron comparendos asociados a los numeros enviados"), //
        COM_049005("COM_049005", "No se enviaron comparendos a notificar"), //
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
         * @param descError
         */
        private NotificarComparendosPresencialmente(String codigoError, String descError) {
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

    public enum NotificarComparendoPorCorreoCertificado implements ErrorInfo {
        COM_052001("COM_052001",
                "El rango de dias de imposición debe ser menor al parametro MIN_DIAS_CONSULTA_GENERAL"), COM_052002(
                        "COM_052002", "Los campos no cumplen con las reglas esablecidas");
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
         * @param descError
         */
        private NotificarComparendoPorCorreoCertificado(String codigoError, String descError) {
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

    public enum NotificarComparendoSIMIT implements ErrorInfo {
        /**
         * Comparendo a actualizar notificacion de SIMIT no encontrado
         */
        SIM_001101("SIM_001101", "Comparendo a actualizar notificacion de SIMIT no encontrado"), //
        /**
         * Identificador del comparendo notificado no puede ser nulo
         */
        SIM_001102("SIM_001102", "Identificador del comparendo notificado no puede ser nulo"), //
        /**
         * Organismo de transito no puede ser nulo
         */
        SIM_001103("SIM_001103", "Organismo de transito no puede ser nulo"), //
        /**
         * Accion ejecutada no puede ser nula
         */
        SIM_001104("SIM_001104", "Accion ejecutada no puede ser nula"), //
        ;

        private String codigoError;
        private String descError;

        private NotificarComparendoSIMIT(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            return codigoError;
        }

        /*
         * (non-Javadoc)
         * 
         * @see co.com.datatools.c2.error.ErrorInfo#getDescError()
         */
        @Override
        public String getDescError() {
            return descError;
        }

    }

    /**
     * CU_CIR20_DAT_COM_054 Cargar acuse de recibo correo certificado
     */
    public enum CargarAcuseReciboCorreo implements ErrorInfo {
        COM_054001("COM_054001", "El nombre de una columna en el encabezado es nulo"), //
        COM_054002("COM_054002", "El nombre de una columna en el encabezado no corresponde a la estructura"), //
        COM_054003("COM_054003", "Formato de archivo no válido, debe ser XLS o XLSX"), //
        COM_054004("COM_054004", "Obligatorio observación o fecha de notificación, "), //
        COM_054005("COM_054005", "Obligatorio número de comparendo, "), //
        COM_054006("COM_054006", "Fecha de notificación mayor que la fecha actual, "), //
        COM_054007("COM_054007", "Fecha de notificación anterior a fecha de imposición, "), //
        COM_054008("COM_054008", "Fecha de imposición mayor que la fecha actual, "), //
        COM_054009("COM_054009", "No cumplió con las validaciones de contenido: "), //
        ;

        private final String codigoError;
        private final String descError;

        private CargarAcuseReciboCorreo(String codigoError, String descError) {
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
     * Catalogo errores de negocio generar resoluciones sancion COM-009
     */
    public enum GenerarResolucionesSancion implements ErrorInfo {
        COM_009001("COM_049001", "La fecha de generacion es obligatoria"), //
        COM_009002("COM_049002", "No se encontraron configuraciones de descuento activas"), //
        COM_009003("COM_049003", "-"), //
        COM_009004("COM_049004", "-"), //
        COM_009005("COM_049005", "-"), //
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
         * @param descError
         */
        private GenerarResolucionesSancion(String codigoError, String descError) {
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
     * Errores y mensajes que se efectuan al momento de generar una notificacion por aviso de comparendo
     * 
     * @author luis.forero(2016-02-12)
     * 
     */
    public enum GenerarNotificacionAviso implements ErrorInfo {
        /**
         * NO existen Numeros de Comparendos disponibles para notificar por aviso.
         */
        COM_055001("COM_055001", "NO existen Numeros de Comparendos disponibles para notificar por aviso."), //
        /**
         * Error al intentar generar el documento de la notificacion por aviso.
         */
        COM_055002("COM_055002", "Error al intentar generar el documento de la notificacion por aviso."), //
        ;
        private String codigoError;
        private String descError;

        private GenerarNotificacionAviso(String codigoError, String descError) {
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

    public enum NotificarEdicto implements ErrorInfo {
        COM_053001("COM_053001",
                "El rango de dias de imposición debe ser menor al parametro CANT_MEDIA_DIAS_REALIZAR_CONSULTA"), COM_053002(
                        "COM 053002", "Los campos no cumplen con las reglas esablecidas");

        private String codigoError;
        private String descError;

        private NotificarEdicto(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            // TODO Auto-generated method stub
            return codigoError;
        }

        @Override
        public String getDescError() {
            // TODO Auto-generated method stub
            return descError;
        }

    }

    public enum ObtenerOCN implements ErrorInfo {
        COM_050001("COM_050001", "Agente de tránsito no existe o no está vigente"), //
        COM_050002("COM_050002", "Responsable no existe o no está vigente"), //
        COM_050003("COM_050003", "Tipo de documento de persona juridica no valido"), //
        COM_050004("COM_050004", "Tipo de responsable es requerido"), //
        COM_050005("COM_050005", "Tipo de documento de persona juridica requerido"), //
        COM_050006("COM_050006", "Numero de documento de persona juridica requerido"), //
        COM_050007("COM_050007", "Codigo organismo de transito requerido"), //
        COM_050008("COM_050008", "Vehículo sin identificar"), //
        COM_050009("COM_050009", "La fecha de imposición es mayor o menor que la del sistema"), //
        COM_050010("COM_050010", "La hora de imposición debe ser menor que la del sistema"), //
        COM_050011("COM_050011", "La placa no es valida"), //
        COM_050012("COM_050012", "No existe formulario"), //
        COM_050013("COM_050013", "Agente de tránsito no existe o no está vigente"), //
        COM_050014("COM_050014", "Identificación de vehículo impreciso"), //
        COM_050015("COM_050015", "Organismo de transito no existe");//

        private String codigoError;
        private String descError;

        private ObtenerOCN(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        @Override
        public String getCodigoError() {
            // TODO Auto-generated method stub
            return codigoError;
        }

        @Override
        public String getDescError() {
            // TODO Auto-generated method stub
            return descError;
        }

    }

    /**
     * Validaciones para el consumo del OCN
     * 
     * @author giovanni.velandia mod(2016-10-03)
     * 
     */
    public enum ConsumirReservaOCN implements ErrorInfo {
        COM_002001("COM_002001", "El numero de comparendo no esta reservado"), //
        COM_002002("COM_002002", "El numero de comparendo ya fue consumido"), //
        COM_002003("COM_002003", "El organismo de transito es obligatorio"), //
        ;

        private String codigoError;
        private String descError;

        private ConsumirReservaOCN(String codigoError, String descError) {
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
     * Validaciones de recibir comparendo
     * 
     * @author giovanni.velandia
     * 
     */
    public enum RecibirComparendo implements ErrorInfo {
        COM_002001("COM_002001", "El organismo de transito es obligatorio"), //
        COM_002002("COM_002002", "El número de factura es obligatorio"), //
        COM_002003("COM_002003", "El organismo de transito no existe"), //
        COM_002004("COM_002004", "La fecha de infraccion es obligatoria"), //
        COM_002005("COM_002005", "La hora de infraccion es obligatoria"), //
        COM_002006("COM_002006", "El código de infraccion es obligatorio"), //
        COM_002007("COM_002007", "El número de citacion es obligatorio"), //
        COM_002008("COM_002008", "El código origen comparendo es obligatorio"),//
        ;

        private String codigoError;
        private String descError;

        private RecibirComparendo(String codigoError, String descError) {
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
     * Catalogo errores de negocio para calcular el saldo de un comparendo COM-064
     */
    public enum CalcularSaldoComparendo implements ErrorInfo {

        /**
         * El comparendo no pertenece al organismo de transito indicado.
         */
        COM_064001("COM_064001", "El comparendo no pertenece al organismo de transito indicado."), //
        COM_064002("COM_064002", "No existe una configuracion de descuento asociada.");

        private String codigoError;
        private String descError;

        private CalcularSaldoComparendo(String codigoError, String descError) {
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
     * Catalogo errores de negocio para calcular el saldo de un comparendo COM-064
     */
    public enum AnularMultaTransito implements ErrorInfo {

        /**
         * El comparendo no pertenece al organismo de transito indicado.
         */
        COM_086001("COM_086001", "Fecha inicial no puede ser mayor a la fecha actual"), //
        COM_086002("COM_086002", "Fecha final debe ser superior a la fecha inicial"), //
        COM_086003("COM_086003", "Rango de fechas supera el parametro de consulta"), //
        COM_086004("COM_086004", "No existe infractor en el comparendo"), //
        COM_086005("COM_086005", "Fecha correo es mayor a la fecha actual"), //
        COM_086006("COM_086006", "Error no se puede generar documento"), //
        ;

        private String codigoError;
        private String descError;

        private AnularMultaTransito(String codigoError, String descError) {
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

    public enum ActualizarAgenteComparendo implements ErrorInfo {

        /**
         * El comparendo no pertenece al organismo de transito indicado.
         */
        COM_091001("COM_091001", "Tipo de inconsistencia null"), //
        COM_091002("COM_091002", "Tipo de inconsistencia no esta en el catalogo"), //
        COM_091003("COM_091003", "comparendos a actualizar vacios"), //
        COM_091004("COM_091004", "agente a actualizar vacio"), //
        COM_091005("COM_091005", "agente no existe"), //
        COM_091006("COM_091006", "agente no activo"), //
        COM_091007("COM_091007", "multiples agentes con igual placa"), //
        COM_091008("COM_091008", "Rango de fechas supera el parametro de consulta"), //
        ;

        private String codigoError;
        private String descError;

        private ActualizarAgenteComparendo(String codigoError, String descError) {
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
