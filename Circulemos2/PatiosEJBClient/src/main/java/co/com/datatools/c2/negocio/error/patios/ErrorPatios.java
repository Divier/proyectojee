package co.com.datatools.c2.negocio.error.patios;

import co.com.datatools.c2.error.ErrorInfo;

/**
 * Catalogo de errores para Formularios
 * 
 * @author claudia.rodriguez
 * 
 */
public final class ErrorPatios {

    private ErrorPatios() {
    }

    /**
     * Catalogo errores de negocio
     */
    public enum AdministrarNumeracion implements ErrorInfo {
        NUM_016001("NUM_016001", "Para una casilla no incremental, Los carácteres inicial y final deben ser iguales"), //
        NUM_016002("NUM_016002", "Para una casilla incremental, El carácter final debe ser mayor al inicial"), //
        NUM_016003("NUM_016003",
                "Los carácteres inicial y final deben ser del mismo tipo de dato: ambos numéricos o ambos alfabéticos"), //
        NUM_016004("NUM_016004", "Los carácteres inicial y final deben estar ambos en mayúscula o ambos en minúscula"), //
        NUM_016005("NUM_016005", "Ya existe otra numeración en el sistema con la misma configuración"), //
        NUM_016006("NUM_016006", "Error de validación: Los carácteres inicial y final solo permiten un único carácter"), //
        NUM_016007("NUM_016007",
                "La numeración no puede ser eliminada o editada porque ya existen rangos en el sistema asocidos a la numeración"), //
        /**
         * La fecha final de vigencia debe ser mayor a la fecha inicial de vigencia
         */
        NUM_016008("NUM_016008", "La fecha final de vigencia debe ser mayor a la fecha inicial de vigencia"), //
        /**
         * La cantidad de digitos debe ser mayor a cero y menor o igual a 50
         */
        NUM_016009("NUM_016009", "La cantidad de digitos debe ser mayor a cero y menor o igual a 50"), //
        NUM_016010("NUM_016010", "El tipo de formulario de la numeracion es obligatorio"), //
        NUM_016011("NUM_016011", "La fecha inicial de vigencia de la numeracion es obligatoria"), //
        NUM_016012("NUM_016012", "La fecha final de vigencia de la numeracion es obligatoria"), //
        NUM_016013("NUM_016013", "El detalle de la numeracion es obligatorio"), //
        NUM_016014("NUM_016014", "El usuario que registra la numeracion es obligatorio"), //
        NUM_016015("NUM_016015",
                "Ya existe una numeracion para el mismo tipo de formulario cruzada con las fechas de vigencia ingresadas"), //
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
        private AdministrarNumeracion(String codigoError, String descError) {
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

    public enum AdministrarRango implements ErrorInfo {

        ADM_030001("ADM_030001", "Error: no hay numeraciones vigentes para la fecha de autorizacion ingresada"), //
        ADM_030002("ADM_030002", "Error: el numero final debe ser mayor al inicial"), //
        ADM_030003("ADM_030003", "Error: No existe una numeración configurada para el rango de números ingresados"), //
        ADM_030004("ADM_030004",
                "Error: El rango ingresado ya esta registrado o hace parte de otro rango registrado en el sistema"), //
        ADM_030005("ADM_030005", "Error: Los números inicial y final deben tener el mismo formato de numeración"), //
        ADM_030006(
                "ADM_030006",
                "Error: El rango no puede ser eliminado o editado, solo los rangos con formularios en estado: Pendiente por asignar, pueder ser editados o eliminados"), //
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
        private AdministrarRango(String codigoError, String descError) {
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

    public enum AdministrarResponsableFormulario implements ErrorInfo {
        /**
         * Codigo del organismo de transito asociado es obligatorio
         */
        ADM_031001("ADM_031001", "Organismo de transito asociado es obligatorio"), //
        /**
         * Persona de responsable es obligatoria
         */
        ADM_031002("ADM_031002", "Persona de responsable es obligatoria"), //
        /**
         * La persona asociada al responsable es obligatoria
         */
        ADM_031003("ADM_031003", "La persona asociada al responsable es obligatoria"), //
        /**
         * Fecha fin de vincualcion es inferior a la fecha de inicio de vinculacion
         */
        ADM_031004("ADM_031004", "Fecha fin de vincualcion es inferior a la fecha de inicio de vinculacion"), //
        /**
         * Fecha fin de Servicio debe ser mayor a la fecha de inicio de servicio
         */
        ADM_031005("ADM_031005", "Fecha fin de Servicio debe ser mayor a la fecha de inicio de servicio"), //
        /**
         * Responsable de fomulario ya se encuentra registrado en el sistema
         */
        ADM_031006("ADM_031006", "Responsable de fomulario ya se encuentra registrado en el sistema"), //
        /**
         * Datos del responsable vacios
         */
        ADM_031007("ADM_031007", "Datos del responsable vacios."), //
        /**
         * El Responsable de formularios a eliminar tiene o ha tenido asociaciones de asignacion a numeros de formularios.
         */
        ADM_031008("ADM_031008",
                "El Responsable de formularios a eliminar tiene o ha tenido asociaciones de asignacion a numeros de formularios."), //
        /**
         * El correo del responsable es obligatorio
         */
        ADM_031009("ADM_031009", "El correo del responsable es obligatorio"), //
        /**
         * Formato del correo del responsable no es valido
         */
        ADM_031010("ADM_031010", "Formato del correo del responsable no es valido"), //
        ;

        private String codigoError;
        private String descError;

        private AdministrarResponsableFormulario(String codigoError, String descError) {
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

    public enum AdministrarRelacionEstados implements ErrorInfo {
        /**
         * Existe una relacion entre los estados existente para el tipo de formulario seleccionado. No se completo operacion
         */
        ADM_050001("ADM_050001",
                "Existe una relacion entre los estados existente para el tipo de formulario seleccionado. No se completo operacion"), //
        ;
        private final String codigoError;
        private final String descError;

        private AdministrarRelacionEstados(String codigoError, String descError) {
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

    public enum CambioEstadosFormularios implements ErrorInfo {

        /**
         * El número inicial ingresado no esta asignado al organismo de trAnsito
         */
        NUM_001001("NUM_001001", "El número inicial ingresado no esta asignado al organismo de tránsito"), //

        /**
         * El número final ingresado no esta asignado al organismo de tránsito
         */
        NUM_001002("NUM_001002", "El número final ingresado no esta asignado al organismo de tránsito"), //

        /**
         * Los números inicial y final deben tener el mismo formato de numeración
         */
        NUM_001003("NUM_001003", "Los números inicial y final de los rangos deben tener el mismo formato de numeración"), //

        /**
         * El número final debe ser mayor o igual al número inicial
         */
        NUM_001004("NUM_001004", "El número final de un rango debe ser mayor o igual al número inicial"), //

        /**
         * Los rangos de formularios deben estar contenidos en el rango seleccionado
         */
        NUM_001005("NUM_001005", "Los rangos de formularios deben estar contenidos en el rango seleccionado"),

        /**
         * El responsable de los rangos enviados no corresponde con el responsable seleccionado
         */
        NUM_001006("NUM_001006", "El responsable de los rangos enviados no corresponde con el responsable seleccionado");

        /**//**
         * Existen números entre el número inicial y número final que no estan asignados al organismo de tránsito
         */
        /*
         * NUM_001001("NUM_001001", "Error: Existen números entre el número inicial y número final que no estan asignados al organismo de tránsito"),
         * //
         *//**
         * No es posible realizar el cambio de estado a los formularios ingresados, revisar la configuración de relaciones de estados para este
         * tipo de formulario
         */
        /*
         * NUM_001002( "NUM_001002",
         * "No es posible realizar el cambio de estado a los formularios ingresados, revisar la configuración de relaciones de estados para este tipo de formulario"
         * ), //
         *//**
         * No es posible calcular el numero final porque no esta configurado el stock para el tipo de asignacion
         */
        /*
         * NUM_001003("NUM_001003", "No es posible calcular el numero final porque no esta configurado el stock para el tipo de asignacion"), //
         *//**
         * El número inicial ingresado no esta asignado al organismo de tránsito
         */
        /*
         * NUM_001004("NUM_001004", "El número inicial ingresado no esta asignado al organismo de tránsito"), //
         *//**
         * El número final ingresado no esta asignado al organismo de tránsito
         */
        /*
         * NUM_001005("NUM_001005", "El número final ingresado no esta asignado al organismo de tránsito"), //
         *//**
         * Los números inicial y final deben tener el mismo formato de numeración
         */
        /*
         * NUM_001006("NUM_001006", "Los números inicial y final deben tener el mismo formato de numeración"), //
         *//**
         * El número final debe ser mayor o igual al número inicial
         */
        /*
         * NUM_001007("NUM_001007", "El número final debe ser mayor o igual al número inicial"), //
         *//**
         * Existen números de formulario que ya tienen un responsable asignado, no es posible realizar la asignación
         */
        /*
         * NUM_001008("NUM_001008", "Existen números de formulario que ya tienen un responsable asignado, no es posible realizar la asignación"), //
         *//**
         * a cantidad total de formularios asignados al responsable supera la cantidad máxima de formularios que se permiten asignar
         */
        /*
         * NUM_001009( "NUM_001009",
         * "La cantidad total de formularios asignados ó reasignados al responsable, supera la cantidad máxima de formularios que se permiten"), //
         *//**
         * Algunos formularios en la asignación han cambiado a otro estado, no es posible eliminar o editar esta asignación
         */
        /*
         * NUM_001010("NUM_001010", "Existen formularios que han cambiado a otro estado, no es posible eliminar o editar el estado del rango"), //
         *//**
         * El estado de alguno de los formularios no corresponde al requerido
         */
        /*
         * NUM_001011("NUM_001011", "El estado de alguno de los formularios no corresponde al requerido"), //
         *//**
         * Debe seleccionar al menos un formulario
         */
        /*
         * NUM_001012("NUM_001012", "Debe seleccionar al menos un formulario"), //
         *//**
         * El responsable NO esta activo
         */
        /*
         * NUM_001013("NUM_001013", "El responsable NO esta activo"), //
         *//**
         * No es posible realizar el cambio de estado debido a que no se tiene la información del \"Stock Maximo\" y \"Stock minimo\" porque no
         * esta configurada o esta en estado inactivo
         */
        /*
         * NUM_001014( "NUM_001014",
         * "No es posible realizar el cambio de estado debido a que no se tiene la información del \"Stock Maximo\" y  \"Stock minimo\" porque no esta configurada o esta en estado inactivo"
         * ), //
         *//**
         * Uno de los responsables de formularios asignados al estado anterior, no cumple con los datos necesarios según el \"Tipo de Asignación\"
         */
        /*
         * NUM_001015( "NUM_001015",
         * "Uno de los responsables de formularios asignados al estado anterior, no cumple con los datos necesarios según el \"Tipo de Asignación\""),
         * //
         *//**
         * Uno de los responsables de formularios asignados al estado anterior, NO esta activo
         */
        /*
         * NUM_001016("NUM_001016", "Uno de los responsables de formularios asignados al estado anterior, NO esta activo"), //
         *//**
         * No hay responsable asignado al cambio de estado de los formularios
         */
        /*
         * NUM_001017("NUM_001017", "No hay responsable asignado al cambio de estado de los formularios"), //
         *//**
         * No es posible enviar correo al responsable
         */
        /*
         * NUM_001018("NUM_001018", "No es posible enviar correo al responsable"), //
         */;

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
        private CambioEstadosFormularios(String codigoError, String descError) {
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

    public enum AdministrarStockTipoResponsable implements ErrorInfo {

        /**
         * Tipo de Formulario es obligatorio no puede estar vacio. No se completo la operacion.
         */
        NUM_017001("NUM_017001", "Tipo de Formulario es obligatorio no puede estar vacio. No se completo la operacion."),
        /**
         * Tipo de Asignacion de formulario es obligatorio no puede estar vacio. No se completo la operacion.
         */
        NUM_017002("NUM_017002",
                "Tipo de Responsable de formulario es obligatorio no puede estar vacio. No se completo la operacion."),
        /**
         * Rango Minimo es obligatorio, no puede estar vacio. No se completo la operacion.
         */
        NUM_017003("NUM_017003", "Rango Minimo es obligatorio, no puede estar vacio. No se completo la operacion."),
        /**
         * Rango Maximo es obligatorio, no puede estar vacio. No se completo la operacion.
         */
        NUM_017004("NUM_017004", "Rango Maximo es obligatorio, no puede estar vacio. No se completo la operacion."),
        /**
         * Valor maximo de stock NO acepta valores negativos Debe ser MAYOR o IGUAL a el rango minimo. No se completo la operacion
         */
        NUM_017005(
                "NUM_017005",
                "Valor maximo de stock NO acepta valores negativos Debe ser MAYOR o IGUAL a el rango minimo. No se completo la operacion"), //
        /**
         * No es posible realizar la operacion debido a que los datos ya se encuentran registrados para ese tipo de formulario y tipo de asignacion
         * seleccionados
         */
        NUM_017006(
                "NUM_017006",
                "No es posible realizar la operacion debido a que los datos ya se encuentran registrados para ese tipo de formulario y tipo de asignacion seleccionados"), //
        /**
         * Valor minimo de stock no acepta valores negativos o un valor igual a cero.
         */
        NUM_017007("NUM_017007", "Valor minimo de stock no acepta valores negativos o un valor igual a cero."), //
        ;

        private final String codigoError;
        private final String descError;

        private AdministrarStockTipoResponsable(String codigoError, String descError) {
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

    public enum AdministrarStockTipoFormulario implements ErrorInfo {
        /**
         * Ya existen datos iguales para el organismo de transito con el tipo de formulario seleccionado. No es posible realizar la operacion
         */
        NUM_013001(
                "NUM_013001",
                "Ya existen datos iguales para el organismo de transito con el tipo de formulario seleccionado. No es posible realizar la operacion"), //
        /**
         * Stock ingresado debe estar en porcentaje sin decimales y se debe configurar entre 1% y 100%. No es posible realizar la operacion
         */
        NUM_013002(
                "NUM_013002",
                "Stock ingresado debe estar en porcentaje sin decimales y se debe configurar entre 1% y 100%. No es posible realizar la operacion"), //
        /**
         * Stock de formularios es obligatorio. No es posible realizar la operacion.
         */
        NUM_013003("NUM_013003", "Stock de formularios es obligatorio. No es posible realizar la operacion."), //
        /**
         * Tipo de Formulario debe ser seleccionado. No es posible realizar la operacion.
         */
        NUM_013004("NUM_013004", "Tipo de Formulario debe ser seleccionado. No es posible realizar la operacion."), //
        /**
         * No se encontro registro del stock.
         */
        NUM_013005("NUM_013005", "No se encontro el stock. No es posible realizar la operacion"), //
        ;

        private final String codigoError;
        private final String descError;

        private AdministrarStockTipoFormulario(String codigoError, String descError) {
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

    public enum FormulariosEstados implements ErrorInfo {
        NUM_008001("NUM_008001", "El numero final del formulario debe ser mayor al numero inicial"), //
        NUM_008002("NUM_008002", "Error los numeros no tienen el mismo formato"), //
        ;
        private final String codigoError;
        private final String descError;

        private FormulariosEstados(String codigoError, String descError) {
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