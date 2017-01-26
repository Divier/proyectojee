package co.com.datatools.c2.negocio.error;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Catalogo de errores para Administracion
 * 
 * @author robert.bautista
 * @since 2014-03-05
 */
public final class ErrorAdministracion {

    private ErrorAdministracion() {
    }

    /**
     * Catalogo errores de negocio
     */
    public enum AdministrarPersona implements ErrorInfo {
        ADM_008002("ADM_008002", "La direccion de residencia de la persona juridica no es válida."), //
        ADM_008003("ADM_008003", "La direccion de residencia de la persona no es válida."), //
        ADM_008004("ADM_008004", "La direccion de empresa de la persona no es válida."), //
        ADM_008005("ADM_008005", "El representante legal no puede tener el tipo de identificación indicado."), //
        ADM_008009("ADM_008009", "Ya existe una persona con los datos de la persona a ingresar."), //
        ADM_008012("ADM_008012", "Información de Cónyugue inválida."), //
        ADM_008013("ADM_008013", "Ya existe una persona natural con los datos del cónyugue a ingresar."), //
        ADM_008014("ADM_008014", "Ya existe una persona con los datos de la persona a ingresar."), //
        ADM_008015("ADM_008015", "El formato de documento no corresponde al tipo de documento según la configuración"), //
        ADM_008016("ADM_008016", "El representante legal debe estar registrado en el sistema"), //
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
        private AdministrarPersona(String codigoError, String descError) {
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
    public enum AdministrarFuncionario implements ErrorInfo {
        ADM_015001("ADM_015001", "No existe una persona con el tipo y número de identificación indicados."), //
        ADM_015002("ADM_015002", "Existe un funcionario vigente con los datos de la persona."), //
        ADM_015007("ADM_015007", "No se puede modificar la identidad del funcionario."), //
        ADM_015008("ADM_015008", "No se puede modificar un funcionario que no es vigente."), //
        ADM_015009("ADM_015009", "No se puede realizar la modificación pues no cumple con normas de fechas."), //
        ADM_015010("ADM_015010",
                "No se puede eliminar un Funcinario con fecha de vigencia final inferior a la fecha actual."), //
        ADM_015011("ADM_015011", "La fecha de inicio de vigencia del funcionario debe ser superior a la fecha actual."), //
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
        private AdministrarFuncionario(String codigoError, String descError) {
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
    public enum AdministrarConfigDescuentos implements ErrorInfo {

        ADM_011001("ADM_011001", "Existe una configuración los mismos datos de registro."), //
        ADM_011002("ADM_011002",
                "Existe una configuración con el mismo medio de imposición y diferente cantidad de días a partir de la imposición."), //
        ADM_011003("ADM_011003", "No es posible eliminar el descuento ya que se encuentra vigente a fecha de hoy."), //
        ADM_011004("ADM_011004",
                "El código de organismo de tránsito es obligatorio en la consulta de configuraciones de descuento."), //
        ADM_011005("ADM_011005", "La fecha de inicio debe ser mayor a hoy."), //
        ADM_011006("ADM_011006", "La fecha final debe ser mayor o igual a la fecha de inicio."), //
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
         * @param descError
         */
        private AdministrarConfigDescuentos(String codigoError, String descError) {
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

    public enum AdministrarTarifaInfraccion implements ErrorInfo {
        ADM_009001("ADM_009001",
                "Una o varias infracciones tienen asignado el mismo porcentaje de descuento más de una vez."), //
        ADM_009002("ADM_009002", "La tarifa tiene valor menor o igual a cero."), //
        ADM_009003("ADM_009003",
                "El código de organismo de tránsito no puede ser nulo para confirmar la configuración de tarifas."), //
        ADM_009004("ADM_009004", "La fecha inicial o a la fecha final de vigencia es nula"), //
        ADM_009005("ADM_009005",
                "La fecha inicial no debe ser mayor o igual a la fecha final de vigencia de la configuración de tarifas nueva."), //
        ADM_009006("ADM_009006",
                "La fecha final de vigencia de la configuración de tarifas nueva debe ser mayor a hoy."), //
        ADM_009007("ADM_009007",
                "La fecha inicial de vigencia de la configuración de tarifas nueva está en el rango de vigencia de una configuración de tarifas confirmada."), //
        ADM_009008("ADM_009008",
                "La fecha final de vigencia de la configuración de tarifas nueva está en el rango de vigencia de una configuración de tarifas confirmada."), //
        ADM_009009("ADM_009009",
                "La fecha inicial de vigencia de la configuración de tarifas modificadas debe ser mayor a hoy."), //
        ;

        private final String codigoError;
        private final String descError;

        private AdministrarTarifaInfraccion(String codigoError, String descError) {
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
    public enum AdministrarIntereses implements ErrorInfo {

        /**
         * La fecha inicial de vigencia es posterior a la fecha final de vigencia.
         */
        ADM_016001("ADM_016001", "La fecha inicial de vigencia es posterior a la fecha final de vigencia."), //
        /**
         * La fecha inicial de vigencia debe ser mayor o igual a la fecha actual.
         */
        ADM_016002("ADM_016002", "La fecha inicial de vigencia debe ser mayor o igual a la fecha actual."), //
        /**
         * La fecha final de vigencia debe ser mayor que la fecha actual.
         */
        ADM_016003("ADM_016003", "La fecha final de vigencia debe ser mayor que la fecha actual."), //
        /**
         * Existe en el organismo para la clase de interes un cruce de fechas de vigencias. Operacion no terminada
         */
        ADM_016004("ADM_016004", "Existe en el organismo para la clase de interes un cruce de fechas de vigencias. Operacion no terminada"), //
        /**
         * La fecha inicial es menor o igual a la actual, no puede ser eliminado el registro.
         */
        ADM_016005("ADM_016005", "La fecha y hora inicial de vigencia es menor o igual a la actual, no puede ser eliminado el registro."), //
        /**
         * El porcentaje de interes ingresado debe ser mayor a cero. No llevo a cabo la operacion.
         */
        ADM_016006("ADM_016006", "El porcentaje de interes ingresado debe ser mayor a cero. No llevo a cabo la operacion."), //
        /**
         * El porcentaje de tasa de interes debe ser ingresado. No se llevo a cabo la operacion
         */
        ADM_016007("ADM_016007", "El porcentaje de tasa de interes debe ser ingresado. No se llevo a cabo la operacion"), //
        /**
         * La tasa de interes diario no puede estar vacia. No se llevo a cabo la operacion
         */
        ADM_016008("ADM_016008", "La tasa de interes diario no puede estar vacia. No se llevo a cabo la operacion"), //
        /**
         * Debe seleccionar el perido de interes. No se llevo a cabo la operacion
         */
        ADM_016009("ADM_016009", "Debe seleccionar el perido de interes. No se llevo a cabo la operacion"), //
        /**
         * Debe seleccionar la clase de interes. No se llevo a cabo la operacion
         */
        ADM_016010("ADM_016010", "Debe seleccionar la clase de interes. No se llevo a cabo la operacion"), //
        ;

        /**
         * Contiene el codigo del error
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
         * @param descError
         */
        private AdministrarIntereses(String codigoError, String descError) {
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
    public enum AdministrarAmnistia implements ErrorInfo {

        ;
        /**
         * Contiene el código del error
         */
        private final String codigoError;

        /**
         * Contiene la descripción del error
         */
        private final String descError;

        private AdministrarAmnistia(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        /**
         * @return the codigoError
         */
        public String getCodigoError() {
            return codigoError;
        }

        /**
         * @return the descError
         */
        public String getDescError() {
            return descError;
        }
    }

    /**
     * Catalogo errores de negocio
     */
    public enum AdministrarInfraccion implements ErrorInfo {
        /**
         * Ya existe una infraccion con el mismo tipo de infraccion y/o nomeral de infraccion
         */
        ADM_010001("ADM_010001", "Ya existe una infraccion con el mismo tipo de infraccion y/o numeral de infraccion"), //
        /**
         * Las fechas de vigencia configuradas para la infraccion se cruzan con otra en el sistema
         */
        ADM_010002("ADM_010002", "Las fechas de vigencia configuradas para la infracción se cruzan con otra en el sistema"), //
        /**
         * La Fecha Inicial de vigencia es menor a la actual. Debe ser mayor o igual
         */
        ADM_010003("ADM_010003", "La Fecha Inicial de vigencia debe ser mayor a la actual"), //
        /**
         * La Fecha Final de vigenca debe ser mayor o igual a la Fecha Inicial
         */
        ADM_010004("ADM_010004", "La Fecha Final de vigenca debe ser mayor o igual a la Fecha Inicial"), //
        /**
         * La Fecha de vigencia inicial de la infraccion del registro consultado NO es mayor a la Fecha actual
         */
        ADM_010005("ADM_010005", "La Fecha de vigencia inicial de la infracción del registro consultado NO es mayor a la Fecha actual"), //
        /**
         * La fecha del articulo NO puede ser superior a la fecha actual
         */
        ADM_010006("ADM_010006", "La fecha del artículo NO puede ser superior a la fecha actual"), //
        /**
         * Dias en los cuales se genera la cartera debe ser mayor o igual a cero
         */
        ADM_010007("ADM_010007", "Días en los cuales se genera la cartera debe ser mayor o igual a cero"), //
        /**
         * Tipos de infractores no asociados, seleccione al menos uno
         */
        ADM_010008("ADM_010008", "Tipos de infractores no asociados, seleccione al menos uno"), //
        /**
         * Tipos de responsables solidarios no asociados, seleccione al menos uno
         */
        ADM_010009("ADM_010009", "Tipos de responsables solidarios no asociados, seleccione al menos uno"), //
        /**
         * Tipo de infraccion ya existente
         */
        ADM_010010("ADM_010010", "Tipo de infraccion ya existente"), //
        /**
         * Clasificacion ya existente.
         */
        ADM_010011("ADM_010011", "Clasificacion ya existente"), //
        /**
         * Campos tipo y numeral no ingresados. al menos uno debe ser ingresado
         */
        ADM_010012("ADM_010012", "Campos tipo de infracciOn y numeral no ingresados. Al menos uno debe ser ingresado"), //
        /**
         * Fecha fin para cierre de vigencia debe ser mayor o igual a la actual.
         */
        ADM_010013("ADM_010013", "Fecha fin para cierre de vigencia debe ser mayor o igual a la actual.")//
        ;

        private final String codigoError;
        private final String descError;

        private AdministrarInfraccion(String codigoError, String descError) {
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
    public enum AdministrarDiasNoHabiles implements ErrorInfo {
        /**
         * No puede efectuarse la operacion porque la fecha es menor o igual a la fecha actual.
         */
        ADM_019001("ADM_019001", "No puede efectuarse la operacion porque la fecha es menor o igual a la fecha actual."), //
        /**
         * Dia no habil ya registrado.
         */
        ADM_019002("ADM_019002", "Dia no habil ya registrado."), //
        /**
         * La fecha a modificar no puede ser menor a la fecha actual.
         */
        ADM_019003("ADM_019003", "La fecha a modificar no puede ser menor a la fecha actual."), //
        ;
        private final String codigoError;
        private final String descError;

        private AdministrarDiasNoHabiles(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        public String getCodigoError() {
            return codigoError;
        }

        public String getDescError() {
            return descError;
        }
    }

    /**
     * Permite manejar los errores de validaciones de negocio posibles generados desde la administacion de un centro integral de atencion
     * 
     * @author luis.forero
     */
    public enum AdministrarCentroIntegralAtencion implements ErrorInfo {
        /**
         * Existen una o varias sedes con el mismo nombre o direccion.
         */
        ADM_021001("ADM_021001", "Existen una o varias sedes con el mismo nombre o direccion."), //
        /**
         * No es posible realizar la operacion porque existen cursos asociados a la compania.
         */
        ADM_021002("ADM_021002", "No es posible realizar la operacion porque existen cursos asociados a la compania"), //
        /**
         * Persona Juridica no asociada al Centro Integral de Atencion. Ingresar persona asociada
         */
        ADM_021003("ADM_021003", "Persona Juridica no asociada al Centro Integral de Atencion. Ingresar persona asociada"), //
        /**
         * Nombre de la compania no ingresada
         */
        ADM_021004("ADM_021004", "Nombre de la compania no ingresada"), //
        /**
         * Fecha de inscripcion no ingresada
         */
        ADM_021005("ADM_021005", "Fecha de inscripcion no ingresada"), //
        /**
         * Telefono principal de la compania no ingresada
         */
        ADM_021006("ADM_021006", "Telefono principal de la compania no ingresada"), //
        /**
         * Direccion de sede no ingresada
         */
        ADM_021007("ADM_021007", "Direccion de sede no ingresada"), //
        /**
         * Correo de la sede no ingresado
         */
        ADM_021008("ADM_021008", "Correo de la sede no ingresado"), //
        /**
         * Correo ingresado no cumple con el formato. Debe contener un solo simbolo @ Debe contener al menos un punto después del símbolo @. Entre el
         * simbolo @ y el punto debe existir texto. No puede terminar en punto. El texto solo puede contener letras del alfabeto ingles.
         */
        ADM_021009("ADM_021009", "Correo ingresado no cumple con el formato. Debe contener un solo símbolo @Debe contener al menos un punto después del símbolo @.Entre el símbolo @ y el punto debe existir texto. No puede terminar en punto. El texto solo puede contener letras del alfabeto inglés."), //
        /**
         * Existe un Centro Integral de Atencion con la misma persona juridica. No es posible realizar la operacion
         */
        ADM_021010("ADM_021010", "Existe un Centro Integral de Atencion con la misma persona juridica. No es posible realizar la operacion"), //
        /**
         * Existen cursos asociados a la sede. No es posible realizar la operacion
         */
        ADM_021011("ADM_021011", "Existen cursos asociados a la sede. No es posible realizar la operacion"), //
        /**
         * Persona Juridica asociada a la CIA no pudo ser ingresada al sistema.
         */
        ADM_021012("ADM_021012", "Persona Juridica asociada a la CIA no pudo ser ingresada al sistema."), //
        /**
         * Direccion asociada a la sede no pudo ser ingresada al sistema. No se realizo la operacion
         */
        ADM_021013("ADM_021013", "Direccion asociada a la sede no pudo ser ingresada al sistema. No se realizo la operacion"), //
        /**
         * Persona asociada a la sede como encargado no fue posible ingresarla al sistema. No se realizo la operacion
         */
        ADM_021014("ADM_021014", "Persona asociada a la sede como encargado no fue posible ingresarla al sistema. No se realizo la operacion"), //
        ;
        private final String codigoError;
        private final String descError;

        private AdministrarCentroIntegralAtencion(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;
        }

        public String getCodigoError() {
            return codigoError;
        }

        public String getDescError() {
            return descError;
        }
    }

    /**
     * Catalogo errores de negocio
     */
    public enum AdministrarUsuario implements ErrorInfo {
        ADM_013001("ADM_013001", "Ya existe un usuario con el tipo y número de documento de identificación ingresado");

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
        private AdministrarUsuario(String codigoError, String descError) {
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
    public enum AdministrarConfiguracionEmail implements ErrorInfo {
        /**
         * Ya existe una configuracion de correo en el organismo con el mismo tipo de comunicacion. Operacion no realizada
         */
        ADM_029001("ADM_029001", "Ya existe una configuracion de correo en el organismo con el mismo tipo de comunicacion. Operacion no realizada."), //
        /**
         * Organismo asociado a la configuracion no encontrado
         */
        ADM_029002("ADM_029002", "Configuracion no tiene organismo de transito asociado"), //
        /**
         * Ya existente para la misma configuracion de correo
         */
        ADM_029003("ADM_029003", "Correo dentro de la lista repetido. Operacion no ejecutada"), //
        /**
         * Correo ingresado no cumple con el formato. Debe contener un solo simbolo @ Debe contener al menos un punto después del símbolo @. Entre el
         * simbolo @ y el punto debe existir texto. No puede terminar en punto. El texto solo puede contener letras del alfabeto ingles
         */
        ADM_029004("ADM_029004", "Correo ingresado no cumple con el formato. Debe contener un solo simbolo @ Debe contener al menos un punto después del símbolo @. Entre el simbolo @ y el punto debe existir texto. No puede terminar en punto. El texto solo puede contener letras del alfabeto ingles."), //
        /**
         * Configuracion no tiene asociado un tipo de comunicacion
         */
        ADM_029005("ADM_029005", "Configuracion no tiene asociado un tipo de comunicacion"), //
        /**
         * Configuracion seleccionada no encontrada en el sistema. Operacion fallida
         */
        ADM_029006("ADM_029006", "Configuracion seleccionada no encontrada en el sistema. Operacion fallida"), //
        /**
         * Tamanio del correo mayor a 50
         */
        ADM_029007("ADM_029007", "Tamaño de correo mayor a 50"), //
        ;
        private final String codigoError;
        private final String descError;

        private AdministrarConfiguracionEmail(String codigoError, String descError) {
            this.codigoError = codigoError;
            this.descError = descError;

        }

        public String getCodigoError() {
            return codigoError;
        }

        public String getDescError() {
            return descError;
        }
    }

    /**
     * Enumeracion que permite identificar los posibles errores de datos y logica en la administracion de un agente de transito
     * 
     * @author luis.forero
     * 
     */
    public enum AdministrarAgente implements ErrorInfo {
        /**
         * Agente con el numero de placa ingresado ya se encuentra ingresado.
         */
        ADM_031101("ADM_031101", "Agente con el numero de placa ingresado ya se encuentra ingresado."), //
        /**
         * Tipo de entidad de agente obligatoria se encuentra vacia.
         */
        ADM_031102("ADM_031102", "Tipo de entidad de agente obligatoria se encuentra vacia."), //
        /**
         * Placa para el mando Agente es obligatoria y no a sido ingresada.
         */
        ADM_031103("ADM_031103", "Placa para el mando Agente es obligatoria y no a sido ingresada."), //
        ;
        private final String codigoError;
        private final String descError;

        private AdministrarAgente(String codigoError, String descError) {
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

    public enum AdministrarOrganismoTransito implements ErrorInfo {
        /**
         * Correo ingresado no fue correctamente validado en el sistema. No se ejecuto la operacion
         */
        ADM_056001("ADM_056001", "Correo ingresado no fue correctamente validado en el sistema. No se ejecuto la operacion"),
        /**
         * Codigo asignado al organismo de transito existente en el sistema. No se ejecuto la operacion
         */
        ADM_056002("ADM_056002", "Codigo asignado al organismo de transito existente en el sistema. No se ejecuto la operacion"),
        /**
         * Nombre del organismo de transito existente en el sistema. No se ejecuto la operacion
         */
        ADM_056003("ADM_056003", "Nombre del organismo de transito existente en el sistema. No se ejecuto la operacion"),
        /**
         * Direccion del organismo de transito es obligatoria y no ha sido ingresada. No se ejecuto la operacion.
         */
        ADM_056004("ADM_056004", "Direccion del organismo de transito es obligatoria y no ha sido ingresada. No se ejecuto la operacion"),
        /**
         * Direccion del organismo no fue correctamente ingresada. No se completo la operacion
         */
        ADM_056005("ADM_056005", "Direccion del organismo no fue correctamente ingresada. No se completo la operacion"),
        /**
         * Departamento no ha sido seleccionado. Operacion no ejecutada
         */
        ADM_056006("ADM_056006", "Departamento no ha sido seleccionado. Operacion no ejecutada"),
        /**
         * Organismo esta siendo utilizado en la aplicacion, no puede llevarse a cabo la operacion.
         */
        ADM_056007("ADM_056007", "Organismo esta siendo utilizado en la aplicacion, no puede llevarse a cabo la operacion"), //
        ;
        private final String codigoError;
        private final String descError;

        private AdministrarOrganismoTransito(String codigoError, String descError) {
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
     * Enumeracion que permite identificar los posibles errores de datos y logica en la administracion de ubicabilidad
     * 
     * @author julio.pinzon
     * 
     */
    public enum Ubicabilidad implements ErrorInfo {
        /**
         * No se pudo generar documento de ubicabilidad
         */
        COB_015001("COB_015101", "No se pudo generar documento de ubicabilidad"), //
        COB_015002("COB_015002", "La persona a ingresar no puede ser null."), //
        COB_015003("COB_015003", "La persona debe estar asociada a un organismo de tránsito."), //
        COB_015004("COB_015004", "La persona debe estar asociada a un tipo de identificación."), //
        COB_015005("COB_015005", "La persona debe tener un número de identificación."), //
        COB_015006("COB_015006", "La persona debe tener asociado un tipo de fuente de información."), //
        COB_015007("COB_015007", "El organismo de tránsito al cual está asociada la persona, no existe."), //
        COB_015008("COB_015008", "El tipo de identificación al cual está asociada la persona, no existe."), //
        COB_015009("COB_015009", "El tipo de fuente de información de la persona, no existe."), //

        // mensajes de error asociados a dirección persona
        COB_015010("COB_015010", "DireccionPersona no tiene asociada una dirección"), //
        COB_015011("COB_015011", "La dirección debe tener una fuente de información"), //
        COB_015012("COB_015012", "El tipo fuente de información asociada a la dirección no existe"), //
        COB_015013("COB_015013", "El tipo de dirección es obligatorio."), //
        COB_015014("COB_015014", "El tipo de dirección no existe"), //
        COB_015015("COB_015015", " La dirección debe tener un tipo de ubicbailidad"), //
        COB_015016("COB_015016", "El tipo de ubicabilidad asociada a la dirección no existe"), //
        COB_015017("COB_015017", "La fuente de información es CIUDADANO, la dirección debe ser validada"), //
        COB_015018("COB_015018", "La prioridad es obligatoria para direcciones válidas, y debe ser positiva"), //
        COB_015019("COB_015019",
                "La fecha de validación es obligatoria para direcciones válidas y no debe ser superior a la actual"), //
        COB_015020("COB_015020", "El usuario de validación es obligatoria para direcciones válidas"), //
        COB_015021("COB_015021", "El tipo fuente de validación es obligatoria para direcciones validas"), //
        COB_015022("COB_015022", "No puede registrar dos direcciones iguales"), //

        // mensajes de error asociados a correo persona
        COB_015023("COB_015023", "El Correo debe tener una fuente de información"), //
        COB_015024("COB_015024", "El tipo fuente de información asociada al correo no existe"), //
        COB_015025("COB_015025", "El Correo no debe ser vacío"), //
        COB_015026("COB_015026", "El Correo tiene caracteres invalidos"), //
        COB_015027("COB_015027", "El complemento de la dirección es obligatorio"), //
        COB_015029("COB_015029", "La fuente de información es CIUDADANO, el corro debe ser validado"), //
        COB_015030("COB_015030", "La prioridad es obligatoria para correos válidos, y debe ser positiva"), //
        COB_015031("COB_015031",
                "La fecha de validación es obligatoria para correos válidos y no debe ser superior a la actual"), //
        COB_015032("COB_015032", "El usuario de validación es obligatoria para correos válidos"), //
        COB_015033("COB_015033", "El tipo fuente de validación es obligatoria para correos validos"), //
        COB_015034("COB_015034", "No puede registrar dos correos iguales"), //

        // mensajes de error asociados a teléfono persona
        COB_015035("COB_015035", "El teléfono debe tener una fuente de información"), //
        COB_015036("COB_015036", "El tipo fuente de información asociada al teléfono no existe"), //
        COB_015037("COB_015037", "El teléfono no debe ser vacío"), //
        COB_015038("COB_015038", "El teléfono debe tener una longitud maxima de 20 caracteres"), //
        COB_015039("COB_015039", "El teléfono debe tener un tipo de teléfono"), //
        COB_015040("COB_015040", "El tipo teléfono no existe"), //
        COB_015043("COB_015043", "La fuente de información es CIUDADANO, el teléfono debe ser validado"), //
        COB_015044("COB_015044", "La prioridad es obligatoria para telefonos válidos, y debe ser positiva"), //
        COB_015045("COB_015045",
                "La fecha de validación es obligatoria para telefonos válidos y no debe ser superior a la actual"), //
        COB_015046("COB_015046", "El usuario de validación es obligatoria para telefonos válidos"), //
        COB_015047("COB_015047", "El tipo fuente de validación es obligatoria para telefonos validos"), //
        COB_015048("COB_015048", "No puede registrar dos telefonos iguales"), //

        // mensajes de error asociados a persona juridica
        COB_015049("COB_015049", "El dígito de verificación ingresado no es válido."), //
        COB_015050("COB_015050", "La persona jurídica debe tener un nombre comercial."), //
        COB_015051("COB_015051", "La longitud del nombre comercial de la persona jurídica no puede ser superior a 60."), //
        COB_015052("COB_015052", "La longitud de la sigla de la persona jurídica no puede ser superior a 10."), //
        COB_015053("COB_015053", "El Municipio de origen de la persona juridica no existe."), //
        COB_015054("COB_015054", "El representante legal debe tener una fecha de inicio de vigencia."), //
        COB_015055("COB_015055", "La cuenta de correo electrónico del representante legal no tiene un formato válido."), //
        COB_015056("COB_015056",
                "La longitud del correo electrónico del representante legal no puede ser superior a 255."), //
        COB_015057("COB_015057", "El representante Legal debe tener un número de documento válido."), //
        COB_015058("COB_015058",
                "El representante debe tener una fecha de fin de vigencia posterior a la fecha de inicio de vigencia."), //

        // mensajes de error asociados a persona natural
        COB_015059("COB_015059", "El Municipio de expedición del documento de la persona no existe."), //
        COB_015060("COB_015060", "La fecha de nacimiento no puede ser superior a la fecha actual."), //
        COB_015061("COB_015061", "La fecha de fallecimiento debe ser superior a la fecha de nacimiento."), //
        COB_015062("COB_015062", "La fecha de fallecimiento no puede ser superior a la fecha actual."), //
        COB_015063("COB_015063", "La persona debe poseer un primer nombre."), //
        COB_015064("COB_015064",
                "El primer nombre de la persona debe contener letras mayusculas,minusculas y puede contener los simbolos ¿ - ."), //
        COB_015065("COB_015065", "La longitud del primer nombre no puede ser superior a 30."), //
        COB_015066("COB_015066",
                "El segundo nombre de la persona debe contener letras mayusculas,minusculas y puede contener los simbolos ¿ - ."), //
        COB_015067("COB_015067", "La longitud del segundo nombre no puede ser superior a 30."), //
        COB_015068("COB_015068", "La persona debe poseer un primer apellido."), //
        COB_015069("COB_015069",
                "El primer apellido de la persona debe contener letras mayusculas,minusculas y puede contener los simbolos ¿ - ."), //
        COB_015070("COB_015070", "La longitud del primer apellido no puede ser superior a 30."), //
        COB_015071("COB_015071",
                "El segundo apellido de la persona debe contener letras mayusculas,minusculas y puede contener los simbolos ¿ - ."), //
        COB_015072("COB_015072", "La longitud del segundo apellido no puede ser superior a 30."), //
        COB_015073("COB_015073", "El género asignado a la persona no existe."), //
        COB_015074("COB_015074", "El estado civil al cual está asociada la persona no existe."), //
        COB_015075("COB_015075", "El tipo de vivienda al cual está asociada la persona no existe."), //
        COB_015076("COB_015076", "El nivel educativo al cual está asociada la persona no existe."), //
        COB_015077("COB_015077", "La longitud del nombre de la empresa donde trabaja no puede ser superior a 60."), //
        COB_015078("COB_015078", "La longitud del cargo laboral no puede ser superior a 60."), //
        COB_015079("COB_015079", "No se ha podido enviar el documento de notificacion por correo."),//
        ;
        private final String codigoError;
        private final String descError;

        private Ubicabilidad(String codigoError, String descError) {
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
     * Enumeracion que permite identificar los posibles errores en la generacion masiva de documentos
     * 
     * @author divier.casas
     * 
     */
    public enum GeneracionMasivaDocumentos implements ErrorInfo {
        /**
         * Hubo un problema al consultar los documentos.
         */
        ADM_069001("ADM_069001", "Hubo un problema al consultar los documentos"), //
        /**
         * Hubo un problema al generar el archivo comprimido
         */
        ADM_069002("ADM_069002", "Hubo un problema al generar el archivo comprimido"), //
        ;

        private final String codigoError;
        private final String descError;

        private GeneracionMasivaDocumentos(String codigoError, String descError) {
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
     * Enumeracion que permite identificar los posibles errores en la administracion de agentes de transito
     * 
     * @author divier.casas
     * 
     */
    public enum AdminisitracionAgentes implements ErrorInfo {
        /**
         * Hubo un problema al consultar la firma del agente.
         */
        ADM_071001("ADM_071001", "Hubo un problema al consultar la firma del agente"), //
        ;

        private final String codigoError;
        private final String descError;

        private AdminisitracionAgentes(String codigoError, String descError) {
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
     * Enumeracion que permite identificar los posibles errores en la administracion de procedures de soporte
     * 
     * @author ricardo.chavarro
     * 
     */
    public enum ConsultaAnalisisCartera implements ErrorInfo {

        ADM_082001("ADM_082001", "El archivo no cumple con el formato requerido."), //
        ADM_082002("ADM_082003", "Campos vacíos."), //
        ADM_082003("ADM_082003", "Persona no existe"), //
        ADM_082004("ADM_082004", "No existe el tipo de documento"), //
        ADM_082005("ADM_082005", "Error cargando el archivo al sistema"), //
        ADM_082006("ADM_082006", "Limite de registros exedido"), //
        ;

        private final String codigoError;
        private final String descError;

        private ConsultaAnalisisCartera(String codigoError, String descError) {
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
     * 
     * @author ricardo.chavarro
     *
     */
    public enum AdminisitracionProcedures implements ErrorInfo {
        /**
         * Hubo un problema al ejecutar el procedure seleccionado.
         */
        ADM_072001("ADM_072001", "Hubo un problema al ejecutar el procedure seleccionado"), //
        ;

        private final String codigoError;
        private final String descError;

        private AdminisitracionProcedures(String codigoError, String descError) {
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
     * Enumeracion que permite identificar los posibles errores en la administracion de procedures de soporte
     * 
     * @author ricardo.chavarro
     * 
     */
    public enum CargueMasivoUbicabilidad implements ErrorInfo {

        UBIC_006001("UBIC_006001", "El archivo no cumple con el formato requerido."), //
        UBIC_006002("UBIC_006002", "Error inesperado de cargue masivo"), //
        UBIC_006003("UBIC_006003", "El archivo excede el maximo de registros permitidos"), //
        ;

        private final String codigoError;
        private final String descError;

        private CargueMasivoUbicabilidad(String codigoError, String descError) {
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
     * Enumeracion para errores de información de ubicabilidad
     * 
     * @author ricardo.chavarro
     * 
     */
    public enum ErroresInformacionUbicabilidad implements ErrorInfo {

        ADM_092001("ADM_092001", "La dirección debe contener minimo 8 caracteres"), //
        ADM_092002("ADM_092003", "La dirección debe contener al menos 2 palabaras"), //
        ADM_092003("ADM_092003", "Información de ubicabilidad no valida"), //
        ADM_092004("ADM_092004", "No existe el tipo de documento"), //
        ;

        private final String codigoError;
        private final String descError;

        private ErroresInformacionUbicabilidad(String codigoError, String descError) {
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
     * Enumeracion para errores de configuracion entidad
     * 
     * @author giovanni.velandia
     * 
     */
    public enum ConfiguracionEntidad implements ErrorInfo {

        ADM_076001("ADM_076001", "La entidad ya esta configurada"), //
        ;

        private final String codigoError;
        private final String descError;

        private ConfiguracionEntidad(String codigoError, String descError) {
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